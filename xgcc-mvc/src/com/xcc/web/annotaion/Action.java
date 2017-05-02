package com.xcc.web.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xcc.web.render.IRender;
import com.xcc.web.render.PagesRender;

/**
 * @author XChao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Action {
	/**
	 * 指定视类型
	 * @return
	 */
	public View value() default View.page;

	/**
	 * 该视访问地址
	 * @return 返回action的尾部地址
	 */
	public String url() default "";

	/**
	 * action后缀，默认取系统全局配置后缀
	 * @return
	 */
	public String suffix() default "";

	/**
	 * 该Action重新指定拦截器
	 * @return
	 */
	public String use() default "";

	/**
	 * 使用视渲染器的名称
	 * @return
	 */
	public String view() default "";

	/**
	 * 用户自定义返回类型,必须和XView.defined 联合使用
	 * @return
	 */
	public Class<? extends IRender> defined() default PagesRender.class;

	/**
	 * 设置是否可以异步返回, 该功能暂时未实现
	 * @return
	 */
	public boolean async() default false;

	/**
	 * 指定请求的访问权限码，该码可以根据系统逻辑自定义
	 * @return SessionWay对象
	 */
	public int permiss() default 0;

	/**
	 * 指定权限菜单分组, menuid值相同,表示为同一组菜单权限
	 * @return
	 */
	public int menuid() default 0;

	/**
	 * 该资源权限是否为需要显示的菜单, 如果不需要显示, 则表示为普通资源
	 * @return
	 */
	public boolean ismenu() default false;

	/**
	 * 指定菜单类型, 如果type相同,菜表示为同一类别菜单, 比如: 运营系统的所有菜单type值都应该一样
	 * @return
	 */
	public int type() default 0;

	/**
	 * 对权限进行描述, 如果该项为 "" 时, 则显示该咨询不在权限控制中显示
	 * @return 返回权限描述信息
	 */
	public String remaker() default "";

	/**
	 * 返回该菜单的上级菜单ID
	 * @return
	 */
	public int parentid() default 0;

	/**
	 * 只有当该级菜单的上级菜单ID指向了实际菜单ID时,该名称才生效,否则,该级菜单读取的应该是该上级菜单ID指向的实际菜单名称
	 * @return 返回该菜单的上级菜单名称
	 */
	public String parentname() default "";
}
