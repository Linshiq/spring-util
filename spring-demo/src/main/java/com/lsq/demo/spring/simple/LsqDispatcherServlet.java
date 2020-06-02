package com.lsq.demo.spring.simple;

import com.lsq.demo.spring.annotation.LSQAutowired;
import com.lsq.demo.spring.annotation.LSQController;
import com.lsq.demo.spring.annotation.LSQService;
import com.lsq.demo.spring.simple.controller.ControllerOne;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

@Component
public class LsqDispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private List<String> classNmaes =  new ArrayList<>();

    private Map<String,Object> beanNameMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用doPost");

    }

    @Override
    public void init() throws ServletException {
        // 扫描配置
        doLoadConfig("application.properties");

        // 扫描包下的类
        // 获取properties文件中配置的属性 例如 xxxname = com.lsq.xxx
        doScanner(properties.getProperty("scanPackage"));

        // 注册
        doRegister();

        doAutowired();
        ControllerOne controllerOne = (ControllerOne) beanNameMap.get("controllerOne");
        System.out.println(controllerOne.serviceTest.getClass());
    }

    private void doRegister() {

        if (classNmaes.isEmpty()){
            return;
        }

        for (String classname : classNmaes){
            try {
                Class clazz = Class.forName(classname);

                if (clazz.isAnnotationPresent(LSQController.class)){

                    // TODO:需要把beanname修改为首字母小写

                    // 在Spring中这一步不会直接put instance ,而是put的beanDefinition
                    beanNameMap.put(toLowerCaseFirstOne(clazz.getSimpleName()),clazz.newInstance());
                } else if(clazz.isAnnotationPresent(LSQService.class)){

                    LSQService service = (LSQService) clazz.getAnnotation(LSQService.class);
                    String name = service.value();

                    if (StringUtils.isEmpty(name)){
                        name = toLowerCaseFirstOne(clazz.getSimpleName());
                    }

                    Object instance = clazz.newInstance();
                    beanNameMap.put(name,instance);
                    // 这一步存在疑惑？ 为什么？
                    Class[] interfaces = clazz.getInterfaces();

                    for (Class c : interfaces){
                        beanNameMap.put(c.getName(),interfaces);
                    }

                } else {
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doAutowired() {
        if (beanNameMap.isEmpty()){
            return;
        }

        for (Map.Entry<String, Object> entry : beanNameMap.entrySet()){
            Object value = entry.getValue();
            Field[] fields = value.getClass().getDeclaredFields();

            for (Field field : fields){
                if (!field.isAnnotationPresent(LSQAutowired.class)){
                    continue;
                }

                LSQAutowired autowired = field.getAnnotation(LSQAutowired.class);

                String benaName = autowired.value().trim();

                if (StringUtils.isEmpty(benaName)){
                    benaName = toLowerCaseFirstOne(field.getType().getSimpleName());
                }

                field.setAccessible(true);
                try {
                    Object obj = beanNameMap.get(benaName);
                    field.set(value,obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String packname) {

        // replaceAll作用是把.替换为文件路径 例如 xxx.xxx.xx = xxx/xxx/xxx
        String path = "" + packname.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(path);

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packname);
            } else {
                classNmaes.add(packname+"."+file.getName().replace(".class",""));
            }
        }
    }

    private void doLoadConfig(String location) {

      //  InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
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
}
