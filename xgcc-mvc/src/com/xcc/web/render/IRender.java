/**
 * Created the IRender.XIRender.java
 * @created 2016年9月24日 下午7:44:26
 * @version 1.0.0
 */
package com.xcc.web.render;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionPorxy;
import com.xcc.web.model.IModel;

/**
 * 页面渲染器，用于渲染action的数据到页面。 用户自定义的页面渲染器必须实现改接口
 * @see com.xcc.web.render.xssh.mvc.web.render.JsonRender
 * @see com.xssh.mvc.web.render.VelocityRender
 * @author XChao
 */
public interface IRender {

	/**
	 * 页面渲染方法
	 * @param model 视图和数据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void render(IModel model, ActionPorxy porxy, DefaultRequest request, HttpServletResponse response) throws Exception;
}
