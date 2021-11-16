package com.qkl.nav_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author qiukailong
 * @title:
 * @projectName NewDriverSchool
 * @description:
 * @date 2021/11/3
 */
@Target(ElementType.TYPE)
public @interface Destination {
    /**
     * 页面在路由中的名称
     *
     * @return
     */
    String pageUrl();

    /**
     * 是否作为路由的第一个启动页
     *
     * @return
     */
    boolean asStarter() default false;
}
