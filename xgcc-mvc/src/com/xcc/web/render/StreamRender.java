/**
 * Created the com.xsy.web.render.XStreamRender.java
 * @created 2016年10月6日 下午9:32:50
 * @version 1.0.0
 */
package com.xcc.web.render;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionPorxy;
import com.xcc.web.model.IModel;

/**
 * com.xsy.web.render.XStreamRender.java
 * @author XChao
 */
public class StreamRender implements IRender {

	@Override
	public void render(IModel model, ActionPorxy porxy, DefaultRequest request, HttpServletResponse response)
		throws Exception {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = model.getInputStream();
			outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} finally {
				if(outputStream != null) {
					outputStream.close();
				}
			}
		}
	}

}
