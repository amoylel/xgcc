/**
 * Created the com.xcc.web.pages.IPages.java
 * @created 2017年2月11日 上午11:56:46
 * @version 1.0.0
 */
package com.xcc.web.view;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.model.IModel;

/**
 * com.xcc.web.view.IView.java
 * @author XChao
 */
public interface IView {

	void generator(IModel model, String viewPath, DefaultRequest request, HttpServletResponse response) throws Exception;
}
