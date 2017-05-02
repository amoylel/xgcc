/**   
 * Created the com.xcc.db.model.DBField.java
 * @created 2017年4月26日 下午4:32:43 
 * @version 1.0.0 
 */
package com.xcc.db.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * com.xcc.db.model.DBField.java 
 * @author XChao  
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Documented
public @interface DBField {
	/**
	 * 对应数据库名称
	 */
	public String value() default "";
}
