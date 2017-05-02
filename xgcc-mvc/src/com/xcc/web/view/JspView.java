/**
 * Created the com.xcc.web.pages.JSPPages.java
 * @created 2017年2月11日 下午12:40:02
 * @version 1.0.0
 */
package com.xcc.web.view;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;
import com.xcc.web.entity.USession;
import com.xcc.web.model.IModel;

/**
 * com.xcc.web.view.JspView.java
 * @author XChao
 */
public class JspView implements IView {
	private IApplicationContext context = XInitContext.getContext();

	@Override
	public void generator(IModel model, String viewPath, DefaultRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute("error", model.getError());
		request.setAttribute("message", model.getMessage());
		for (String key : model.keySet()) {
			request.setAttribute(key, model.getData(key));
		}
		request.setAttribute("sysBasePath", context.getWebRootPath());
		request.setAttribute("usession", request.getSession().getAttribute(USession.XUSESSION_KEY));
		request.getRequestDispatcher("/WEB-INF/" + viewPath).forward(request, response);
	}

}
