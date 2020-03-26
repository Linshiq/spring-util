package com.lsq.demo.annotation;


import java.lang.annotation.*;

/**
 * <p>
 * 文件功能说明：
 *
 * </p>
 *
 * @Author linshiqin
 * <p>
 * <li>2019年6月13日-下午3:00:39</li>
 * <li>修改记录</li>
 * <li>-----------------------------------------------------------</li>
 * <li>标记：修订内容</li>
 * <li>linshiqin：创建注释模板</li>
 * <li>-----------------------------------------------------------</li>
 * </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited // 表示该注解可以被继承
public @interface ServiceCode {

    String value() default "";
}
