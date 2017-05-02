package com.xcc.web.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Control {
	/**
	 * 控制器的名字属性
	 * @return 控制器名称，默认去控制器的简单类名称，
	 */
	public String name() default "";

	/**
	 * 配置控制器的url地址
	 * @return url相对地址字串
	 */
	public String url() default "";

	/**
	 * 控制器下的所有action 后缀
	 * @return 返回后缀字串
	 */
	public String suffix() default "";

	/**
	 * 给该控制器重新指定一个拦截器
	 * @return
	 */
	public String use() default "";

	/**
	 * 默认视图渲染器名称
	 * @return
	 */
	public String view() default "";
}
