package com.lzy.web.controller.model;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
/**
 * Ϊ�˼��� CDATA ��֤������@interface��
 * @author Shinelon
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target({ ElementType.FIELD })  
public @interface XStreamCDATA {  
  
}  