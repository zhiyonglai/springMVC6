package com.lzy.web.controller.model;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
/**
 * 为了加入 CDATA 验证创建的@interface类
 * @author Shinelon
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target({ ElementType.FIELD })  
public @interface XStreamCDATA {  
  
}  