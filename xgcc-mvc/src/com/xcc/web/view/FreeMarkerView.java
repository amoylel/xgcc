/**
 * Created the com.xcc.web.pview.FreeMarkerView.java
 * @created 2017年2月13日 下午3:51:36
 * @version 1.0.0
 */
package com.xcc.web.view;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.model.IModel;

/**
 * com.xcc.web.view.FreeMarkerView.java
 * @author XChao
 */
public class FreeMarkerView implements IView {

	@Override
	public void generator(IModel model, String viewPath, DefaultRequest request, HttpServletResponse response)
			throws Exception {

	}

}
