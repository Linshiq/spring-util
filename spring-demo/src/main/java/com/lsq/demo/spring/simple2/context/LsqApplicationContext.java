package com.lsq.demo.spring.simple2.context;

import com.lsq.demo.spring.annotation.LSQAutowired;
import com.lsq.demo.spring.annotation.LSQController;
import com.lsq.demo.spring.annotation.LSQService;
import com.lsq.demo.spring.simple2.beans.LsqBeanDefinition;
import com.lsq.demo.spring.simple2.beans.LsqBeanWrapper;
import com.lsq.demo.spring.simple2.core.LsqBeanFactory;
import com.lsq.demo.spring.simple2.support.LSQBeanPostProcessor;
import com.lsq.demo.spring.simple2.support.LsqBeanDefinitionReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class LsqApplicationContext extends LSQDefaultListableBeanFactory implements LsqBeanFactory {

    private String[] configLocation;

    private LsqBeanDefinitionReader beanDefinitionReader;

    private Map<String, LsqBeanDefinition> beanDefinitionMap = new HashMap<>();

    // 用于存储所有被代理的对象
    private Map<String, LsqBeanWrapper> beanWrapperMap = new HashMap<>();

    // 用来保证注册式单例的容器
    private Map<String, Object> beanCacheMap = new HashMap<>();

    public LsqApplicationContext(String... location) {
        this.configLocation = location;
        this.refresh();
    }

    public void refresh() {

        //定位
        this.beanDefinitionReader = new LsqBeanDefinitionReader(configLocation);
        //加载
        List<String> beanDefinitions = beanDefinitionReader.loadBeanDefinitions();
        //注册
        doRegister(beanDefinitions);
        //依赖注入
        doAutowired();

        System.out.println();
    }

    private void doAutowired() {
        // TODO：如何解决还未初始化的问题
        // 先把所有bean加载
        for(Map.Entry<String, LsqBeanDefinition> beanDifinitionEntry : this.beanDefinitionMap.entrySet()){

            String beanName = beanDifinitionEntry.getKey();
            // TODO:延迟加载不做
            getBean(beanName);
        }
        // 开始实际注入
        for(Map.Entry<String, LsqBeanWrapper> beanDifinitionEntry : this.beanWrapperMap.entrySet()){

            String beanName = beanDifinitionEntry.getKey();
            // TODO:延迟加载不做
            populateBean(beanName,beanDifinitionEntry.getValue().getWrapperInstance());
        }
    }

    private void populateBean(String beanName,Object instance){

        Class clazz = instance.getClass();

        if (!clazz.isAnnotationPresent(LSQController.class) && !clazz.isAnnotationPresent(LSQService.class)){
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(LSQAutowired.class)){
                continue;
            }

            LSQAutowired autowired = field.getAnnotation(LSQAutowired.class);

            String autowiredBenaName = autowired.value().trim();

            if (StringUtils.isEmpty(autowiredBenaName)){
                autowiredBenaName = toLowerCaseFirstOne(field.getType().getSimpleName());
            }

            field.setAccessible(true);
            try {
                Object obj = beanWrapperMap.get(autowiredBenaName).getWrapperInstance();
                field.set(instance,obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void doRegister(List<String> beanDefinitions) {

        if (CollectionUtils.isEmpty(beanDefinitions)) {
            return;
        }
        try {
            for (String beanClassName : beanDefinitions) {
                // beanName 3种情况
                // 1 默认类名小写
                // 2 自定义名字
                // 3 接口注入

                Class<?> beanClass = Class.forName(beanClassName);
                // 接口是不允许实例化的
                // 使用其实现类进行实例化，但如果有多个实现类，则报错
                if (beanClass.isInterface()) {
                    continue;
                }
                LsqBeanDefinition beanDefinition = beanDefinitionReader.regitsterBean(beanClassName);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                    Class[] interfaces = beanClass.getInterfaces();
                    for (Class c : interfaces) {
                        // 多个实现类则覆盖，但其实应该报错
                        this.beanDefinitionMap.put(c.getName(), beanDefinition);
                    }
                }
                // 容器注册完毕
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过读取beanDifinition中的信息，通过反射new一个实例返回
     * Spring会使用BeanWrapper进行包装返回
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {

        LsqBeanDefinition bean = beanDefinitionMap.get(beanName);
        if (bean == null) {
            return null;
        }

        if (beanWrapperMap.containsKey(beanName)){
            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        }

        try {
            Object instance = instantionBean(bean);
            if (instance == null) {
                return null;
            }
            LSQBeanPostProcessor postProcessor = new LSQBeanPostProcessor();
            postProcessor.postProcessBeforeInitialization(bean,beanName);
            LsqBeanWrapper beanWrapper = new LsqBeanWrapper(instance);
            beanWrapperMap.put(beanName, beanWrapper);
            postProcessor.postProcessAfterInitialization(bean,beanName);
            // 这期间可以处理一些自己想要处理的事情
            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据BeanDefinition返回一个Bean对象
     *
     * @param beanDefinition
     * @return
     */
    private Object instantionBean(LsqBeanDefinition beanDefinition) {
        Object instance = null;
        try {
            String classname = beanDefinition.getBeanClassName();
            // TODO 考虑单例的问题
            if (this.beanCacheMap.containsKey(classname)) {
                instance = this.beanCacheMap.get(classname);
            } else {
                Class clazz = Class.forName(classname);
                instance = clazz.newInstance();
                this.beanCacheMap.put(classname, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String var1, Object... var2) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> var1) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> var1, Object... var2) throws BeansException {
        return null;
    }

    public Properties getConfig(){
        return beanDefinitionReader.getConfig();
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public String[] getBeanDefinitionNams(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }
}
