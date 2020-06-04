package com.lsq.demo.spring.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LSQHandlerAdapter {

    private Map<String, Integer> paramMapping = new HashMap<>();

    public LSQHandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public LSQModelAndView handle(HttpServletResponse response, HttpServletRequest reques, LSQHandlerMapping handlerMapping) throws Exception{
        // 根据用户请求的信息 与 method中的参数进行匹配

        // 1.准备好形参
        Class<?>[] params = handlerMapping.getMethod().getParameterTypes();
        // 2.拿到自定义命名参数所在的位置
        // 用户上传的参数列表
        Map<String, String[]> reqMaps = reques.getParameterMap();
        // 3.构造实参列表
        Object[] paramValues = new Object[params.length];
        for (Map.Entry<String, String[]> enty : reqMaps.entrySet()) {
            String paramName = enty.getKey();

            String[] value = enty.getValue();
            if (this.paramMapping.containsKey(paramName)) {
                continue;
            }
            int index = paramMapping.get(paramName);
            // 因为前端上送的都是String,在送给controller的方法的时候，要进行强制转化
            // TODO：这里先不处理，但其实应该进行强制转化的
            paramValues[index] = Arrays.toString(value);
        }
        // TODO: 这里应该先判断一下是否需要送这两个参数
        int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
        paramValues[reqIndex] = reques;
        int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
        paramValues[respIndex] = response;
        // 4.利用反射机制调用
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);
        if (result == null){
            return null;
        }
        boolean isModle = handlerMapping.getMethod().getReturnType() == LSQModelAndView.class;
        if (isModle){
            return (LSQModelAndView) result;
        }
        return null;
    }
}
