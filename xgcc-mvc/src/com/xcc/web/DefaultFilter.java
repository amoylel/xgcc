package com.xcc.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;

/**
 * Servlet Filter implementation class DefaultFilter
 */
@WebFilter(value="/*", servletNames="DefaultServlet")
public class DefaultFilter implements Filter {
	private IApplicationContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		context = XInitContext.getContext();
	}

	public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
//		response.setHeader("Access-Control-Allow-Origin", "<请求方域名如:http://www.sohu.com>");
		// 为允许哪些Origin发起跨域请求. 这里设置为”*”表示允许所有，通常设置为所有并不安全，最好指定一下。
		response.setHeader("Access-Control-Allow-Origin", context.getAccessControl());
		// Access-Control-Allow-Methods 为允许请求的方法. 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, TRACE, HEAD");
		// Access-Control-Max-Age 表明在多少秒内，不需要再发送预检验请求，可以缓存该结果 
		response.setHeader("Access-Control-Max-Age", "3600");
		// Access-Control-Allow-Headers 表明它允许跨域请求包含content-type头，这里设置的x-requested-with ，表示ajax请求
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
