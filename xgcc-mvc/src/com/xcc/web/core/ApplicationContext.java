/**
 * Created the com.xsy.web.core.XDefaultApplicationContext.java
 * @created 2016年9月26日 下午3:53:33
 * @version 1.0.0
 */
package com.xcc.web.core;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

import com.xcc.web.interceptor.InterceptorPorxy;
import com.xcc.web.preprocessor.PreprocessorPorxy;
import com.xcc.web.render.IRender;
import com.xcc.web.scheduler.TaskPorxy;
import com.xcc.web.view.ViewPorxy;

/**
 * com.xsy.web.core.XDefaultApplicationContext.java
 * @author XChao
 */
public class ApplicationContext implements IApplicationContext {
	// 跨域配置信息
	private String accessControl = "*";
	private ServletContext context;
	// web服务器物理绝对地址
	private String webRealPath;
	// web服务Url访问绝对地址(不包含域名部分)
	private String webRootPath;
	// 编码设置
	private String encoding = "UTF-8";
	// URL访问后缀
	private String actionSuffix = ".htm";
	// 主键生成机器唯一编码
	private long workerId;
	// 初始化参数配置信息
	private final Map<String, String> parameters = new HashMap<>();
	// 初始化处理器代理信息
	private PreprocessorPorxy preprocessorPorxy;
	// 拦截器代理
	private final Map<String, InterceptorPorxy> interceptorsPorxy = new HashMap<>();
	// 拦截器堆栈信息
	private final Map<String, List<String>> interceptorStacks = new HashMap<>();
	// 定时任务执行代理类
	private final Map<String, TaskPorxy> tasksPorxy = new HashMap<>();
	// 控制器方法代理
	private final Map<String, ActionPorxy> actionsPorxy = new HashMap<>();
	// 视图处理器
	private final Map<Class<? extends IRender>, IRender> renders = new HashMap<>();
	// 页面视图渲染器
	private final Map<String, ViewPorxy> viewsPorxy = new HashMap<>();
	// 默认视渲染器名称
	private String defaultView;
	// 文件上传配置信息 上传缓冲区大小
	private int sizeThreshold = 4096;
	// 文件上配置信息 上传临时文件
	private String repository = "/temp";
	// 文件上传配置信息， 一次请求最大文件限制
	private long sizeMax = -1;
	// 登录地址配置
	private String loginUrl;

	public ApplicationContext(ServletContext servletContext) {
		this.context = servletContext;
		this.webRealPath = this.getRealPath("/") + "/";
		this.webRootPath = this.getContextPath() + "/";
	}

	public Dynamic addFilter(String arg0, Class<? extends Filter> arg1) {
		return this.context.addFilter(arg0, arg1);
	}

	public Dynamic addFilter(String arg0, Filter arg1) {
		return this.context.addFilter(arg0, arg1);
	}

	public Dynamic addFilter(String arg0, String arg1) {
		return this.context.addFilter(arg0, arg1);
	}

	public void addListener(Class<? extends EventListener> arg0) {
		this.context.addListener(arg0);
	}

	public void addListener(String arg0) {
		this.context.addListener(arg0);
	}

	public <T extends EventListener> void addListener(T arg0) {
		this.context.addListener(arg0);
	}

	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, Class<? extends Servlet> arg1) {
		return this.context.addServlet(arg0, arg1);
	}

	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, Servlet arg1) {
		return this.context.addServlet(arg0, arg1);
	}

	public javax.servlet.ServletRegistration.Dynamic addServlet(String arg0, String arg1) {
		return this.context.addServlet(arg0, arg1);
	}

	public <T extends Filter> T createFilter(Class<T> arg0) throws ServletException {
		return this.context.createFilter(arg0);
	}

	public <T extends EventListener> T createListener(Class<T> arg0) throws ServletException {
		return this.context.createListener(arg0);
	}

	public <T extends Servlet> T createServlet(Class<T> arg0) throws ServletException {
		return this.context.createServlet(arg0);
	}

	public void declareRoles(String... arg0) {
		this.context.declareRoles(arg0);
	}

	public Object getAttribute(String arg0) {
		return this.context.getAttribute(arg0);
	}

	public Enumeration<String> getAttributeNames() {
		return this.context.getAttributeNames();
	}

	public ClassLoader getClassLoader() {
		return this.context.getClassLoader();
	}

	public ServletContext getContext(String arg0) {
		return this.context.getContext(arg0);
	}

	public String getContextPath() {
		return this.context.getContextPath();
	}

	public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
		return this.context.getDefaultSessionTrackingModes();
	}

	public int getEffectiveMajorVersion() {
		return this.context.getEffectiveMajorVersion();
	}

	public int getEffectiveMinorVersion() {
		return this.context.getEffectiveMinorVersion();
	}

	public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
		return this.context.getEffectiveSessionTrackingModes();
	}

	public FilterRegistration getFilterRegistration(String arg0) {
		return this.context.getFilterRegistration(arg0);
	}

	public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
		return this.context.getFilterRegistrations();
	}

	public String getInitParameter(String arg0) {
		return this.context.getInitParameter(arg0);
	}

	public Enumeration<String> getInitParameterNames() {
		return this.context.getInitParameterNames();
	}

	public JspConfigDescriptor getJspConfigDescriptor() {
		return this.context.getJspConfigDescriptor();
	}

	public int getMajorVersion() {
		return this.context.getMajorVersion();
	}

	public String getMimeType(String arg0) {
		return this.context.getMimeType(arg0);
	}

	public int getMinorVersion() {
		return this.context.getMinorVersion();
	}

	public RequestDispatcher getNamedDispatcher(String arg0) {
		return this.context.getNamedDispatcher(arg0);
	}

	public String getRealPath(String arg0) {
		return this.context.getRealPath(arg0);
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		return this.context.getRequestDispatcher(arg0);
	}

	public URL getResource(String arg0) throws MalformedURLException {
		return this.context.getResource(arg0);
	}

	public InputStream getResourceAsStream(String arg0) {
		return this.context.getResourceAsStream(arg0);
	}

	public Set<String> getResourcePaths(String arg0) {
		return this.context.getResourcePaths(arg0);
	}

	public String getServerInfo() {
		return this.context.getServerInfo();
	}

	@Deprecated
	public Servlet getServlet(String arg0) throws ServletException {
		return this.context.getServlet(arg0);
	}

	public String getServletContextName() {
		return this.context.getServletContextName();
	}

	@Deprecated
	public Enumeration<String> getServletNames() {
		return this.context.getServletNames();
	}

	public ServletRegistration getServletRegistration(String arg0) {
		return this.context.getServletRegistration(arg0);
	}

	public Map<String, ? extends ServletRegistration> getServletRegistrations() {
		return this.context.getServletRegistrations();
	}

	@Deprecated
	public Enumeration<Servlet> getServlets() {
		return this.context.getServlets();
	}

	public SessionCookieConfig getSessionCookieConfig() {
		return this.context.getSessionCookieConfig();
	}

	public String getVirtualServerName() {
		return this.context.getVirtualServerName();
	}

	@Deprecated
	public void log(Exception arg0, String arg1) {
		this.context.log(arg0, arg1);
	}

	public void log(String arg0, Throwable arg1) {
		this.context.log(arg0, arg1);
	}

	public void log(String arg0) {
		this.context.log(arg0);
	}

	public void removeAttribute(String arg0) {
		this.context.removeAttribute(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		this.context.setAttribute(arg0, arg1);
	}

	public boolean setInitParameter(String arg0, String arg1) {
		return this.context.setInitParameter(arg0, arg1);
	}

	public void setSessionTrackingModes(Set<SessionTrackingMode> arg0) {
		this.context.setSessionTrackingModes(arg0);
	}

	public String getAccessControl() {
		return this.accessControl;
	}

	public void setAccessControl(String accessControl) {
		this.accessControl = accessControl;
	}

	public String getWebRealPath() {
		return this.webRealPath;
	}

	public String getWebRootPath() {
		return this.webRootPath;
	}

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public long getWorkerId() {
		return this.workerId;
	}

	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getActionSuffix() {
		return this.actionSuffix;
	}

	public void setActionSuffix(String actionSuffix) {
		this.actionSuffix = actionSuffix;
	}

	public String getParameter(String name) {
		return this.parameters.get(name);
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public void addParameter(String name, String value) {
		this.parameters.put(name, value);
	}

	public PreprocessorPorxy getPreprocessorPorxy() {
		return this.preprocessorPorxy;
	}

	public void setPreprocessorPorxy(PreprocessorPorxy preprocessor) {
		this.preprocessorPorxy = preprocessor;
	}

	public InterceptorPorxy getInterceptor(String interceptorName) {
		return this.interceptorsPorxy.get(interceptorName);
	}

	public void addInterceptor(String interceptorName, InterceptorPorxy interceptorPorxy) {
		this.interceptorsPorxy.put(interceptorName, interceptorPorxy);
	}

	public List<String> getInterceptorStack(String name) {
		return this.interceptorStacks.get(name);
	}

	public void addInterceptorStack(String name, List<String> interceptorStack) {
		this.interceptorStacks.put(name, interceptorStack);
	}

	public TaskPorxy getTask(String taskName) {
		return this.tasksPorxy.get(taskName);
	}

	public void addTask(String taskName, TaskPorxy taskPorxy) {
		this.tasksPorxy.put(taskName, taskPorxy);
	}

	public ActionPorxy getAction(String actionName) {
		return this.actionsPorxy.get(actionName);
	}

	public Map<String, ActionPorxy> getActionMap() {
		return this.actionsPorxy;
	}

	public void addAction(String actionName, ActionPorxy actionPorxy) {
		this.actionsPorxy.put(actionName, actionPorxy);
	}

	public IRender getRender(Class<? extends IRender> clazz) {
		return this.renders.get(clazz);
	}

	public void addRender(Class<? extends IRender> clazz, IRender render) {
		this.renders.put(clazz, render);
	}

	public ViewPorxy getView(String viewName) {
		return this.viewsPorxy.get(viewName);
	}

	public void addView(String viewName, ViewPorxy viewPorxy) {
		this.viewsPorxy.put(viewName, viewPorxy);
	}

	public String getDefaultView() {
		return defaultView;
	}

	public void setDefaultView(String defaultView) {
		this.defaultView = defaultView;
	}

	public void setFileUploadSizeThreshold(int sizeThreshold) {
		this.sizeThreshold = sizeThreshold;
	}
	

	public int getFileUploadSizeThreshold() {
		return this.sizeThreshold;
	}

	public void setFileUploadRepository(String repository) {
		this.repository = repository;
	}

	public String getFileUploadRepository() {
		return this.repository;
	}

	public void setFileUploadSizeMax(long sizeMax) {
		this.sizeMax = sizeMax;
	}

	public long getFileUploadSizeMax() {
		return this.sizeMax;
	}

	public String getLoginUrl() {
		return this.loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
