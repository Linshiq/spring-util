package com.lsq.demo.spring.mvc;

import com.lsq.demo.spring.annotation.LSQController;
import com.lsq.demo.spring.annotation.LSQRequestMapping;
import com.lsq.demo.spring.annotation.LSQRequestParam;
import com.lsq.demo.spring.simple2.context.LsqApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class LSQDispatcherServlet {

    private List<LSQHandlerMapping> handlerMappings = new ArrayList<>();
    private List<LSQViewResolver> viewResolvers = new ArrayList<>();
    private Map<LSQHandlerMapping,LSQHandlerAdapter> handlerAdapterMap = new HashMap<>();


    public void init(HttpServletResponse response,HttpServletRequest request){
        LsqApplicationContext context = new LsqApplicationContext("application.properties");

        initHandlerMappings(context);
        initHandlerAdapters(context);
        initViewResolvers(context);
    }

    private void initViewResolvers(LsqApplicationContext context){
        // 解析Controller返回的页面响应
        // Property文件中定义模板页面的位置
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);

        for (File template : templateRootDir.listFiles()){
            //TODO 应该加个判断，是否有目录
            this.viewResolvers.add(new LSQViewResolver(template.getName(),template));
        }
    }

    private void initHandlerAdapters(LsqApplicationContext context){
        // 记录每一个RequestMapping下的方法的每一个参数的顺序，方便method反射调用的将参数灌入
        for(LSQHandlerMapping handlerMapping : handlerMappings){
            // 每一个方法有一个参数列表，这里保存是形参列表
            Map<String,Integer> paramMapping = new HashMap<>();

            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            // 这里读取命名参数
            for (int i = 0;i<pa.length;i++){
                for (Annotation a : pa[i]){
                    if (a instanceof LSQRequestParam){
                        String paramName = ((LSQRequestParam) a).name();
                        if (!StringUtils.isEmpty(paramName)){
                            paramMapping.put(paramName,i);
                        }
                    }
                }
            }

            // 处理非命名参数
            // 只处理Response和Request
            Class[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0;i<paramTypes.length;i++){
                Class type = paramTypes[i];
                if (type == HttpServletResponse.class || type == HttpServletRequest.class){
                    paramMapping.put(type.getName(),i);
                }
            }

            this.handlerAdapterMap.put(handlerMapping,new LSQHandlerAdapter(paramMapping));
        }
    }

    /**
     * 将Controller中配置的RequestMapping和Method进行一一对应
     * @param context
     */
    private void initHandlerMappings(LsqApplicationContext context){

        String[] beanNams = context.getBeanDefinitionNams();

        for (String beanName : beanNams){
            Object controller = context.getBean(beanName);
            Class clazz = controller.getClass();
            if (!clazz.isAnnotationPresent(LSQController.class)){
                continue;
            }

            String baseUrl = "";

            if (clazz.isAnnotationPresent(LSQRequestMapping.class)){
                LSQRequestMapping lsqRequestMapping = (LSQRequestMapping) clazz.getAnnotation(LSQRequestMapping.class);
                // TODO:这里图方便，只取第一条
                baseUrl = lsqRequestMapping.value()[0];
            }

            Method[] methods = clazz.getMethods();

            for (Method method : methods){
                if (!method.isAnnotationPresent(LSQRequestMapping.class)){
                    continue;
                }
                LSQRequestMapping lsqRequestMapping = (LSQRequestMapping) method.getAnnotation(LSQRequestMapping.class);
                // TODO:这里图方便，只取第一条
                String regex = ("/" + baseUrl + lsqRequestMapping.value()[0].replaceAll("/+","/"));
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new LSQHandlerMapping(pattern,method,controller));
                System.out.println("Mapping add " + regex + method);
            }
        }
    }
}
