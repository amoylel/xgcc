/**
 * Created the com.xsy.web.render.XEntrysRender.java
 * @created 2016年10月6日 下午8:55:56
 * @version 1.0.0
 */
package com.xcc.web.render;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionPorxy;
import com.xcc.web.model.IModel;

/**
 * com.xsy.web.render.XEntrysRender.java
 * @author XChao
 */
public class JsonRender implements IRender {

	@Override
	public void render(IModel model, ActionPorxy porxy, DefaultRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(model.getDataModel()));
		out.flush();
		out.close();
	}

}
