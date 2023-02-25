package com.third.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @descript 每个检查项
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditItem {
    String value() default "";//检测值
    String tip() default "";//检测不通过提示
    AuditType mediaType() default AuditType.MSG;//检测类型
}
