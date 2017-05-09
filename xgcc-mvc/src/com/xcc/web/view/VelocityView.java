/**
 * Created the com.xcc.web.pages.VelocityPages.java
 * @created 2017年2月11日 下午12:12:15
 * @version 1.0.0
 */
package com.xcc.web.view;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.log.NullLogChute;

import com.xcc.web.DefaultRequest;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;
import com.xcc.web.entity.IUser;
import com.xcc.web.model.IModel;

/**
 * com.xcc.web.view.VelocityView.java
 * @author XChao
 */
public class VelocityView implements IView {
	private IApplicationContext context = XInitContext.getContext();
	private org.apache.velocity.app.VelocityEngine velocityEngine;

	public VelocityView() throws Exception {
		Properties properties = new Properties();
		File loadPthFile = new File(this.context.getWebRealPath(), "WEB-INF");
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, loadPthFile.toString());
		properties.setProperty(Velocity.ENCODING_DEFAULT, context.getEncoding());
		properties.setProperty(Velocity.INPUT_ENCODING, context.getEncoding());
		properties.setProperty(Velocity.OUTPUT_ENCODING, context.getEncoding());
		properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class.getName());
		velocityEngine = new org.apache.velocity.app.VelocityEngine();
		velocityEngine.init(properties);
	}

	@Override
	public void generator(IModel model, String viewPath, DefaultRequest request, HttpServletResponse response)
		throws Exception {
		Writer writer = null;
		try {
			Map<String, Object> data = new HashMap<>();
			for (String key : model.keySet()) {
				data.put(key, model.getData(key));
			}
			data.put("sysBasePath", context.getWebRootPath());
			data.put("VH", VMUtils.class);
			data.put("usession", request.getSession().getAttribute(IUser.IUSER_KEY));
			Template template = velocityEngine.getTemplate(viewPath);
			VelocityContext context = new VelocityContext(data);
			writer = response.getWriter();
			template.merge(context, writer);
			writer.flush();
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	public void evaluate(Map<String, Object> data, String value, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Writer writer = null;
		try {
			data.put("sysBasePath", context.getWebRootPath());
			data.put("VH", VMUtils.class);
			data.put("usession", request.getSession().getAttribute(IUser.IUSER_KEY));
			VelocityContext context = new VelocityContext(data);
			writer = response.getWriter();
			velocityEngine.evaluate(context, writer, "", value);
			writer.flush();
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 生成字符串内容
	 * @param data
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String generator(Map<String, Object> data, String path) throws Exception {
		data.put("sysBasePath", context.getWebRootPath());
		data.put("VH", VMUtils.class);
		Template template = velocityEngine.getTemplate(path);
		VelocityContext context = new VelocityContext(data);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}

	/**
	 * 生成字符串内容
	 * @param data 数据
	 * @param content velocity字符串
	 * @return
	 * @throws Exception
	 */
	public String evaluate(Map<String, Object> data, String content) throws Exception {
		data.put("sysBasePath", context.getWebRootPath());
		data.put("VH", VMUtils.class);
		VelocityContext context = new VelocityContext(data);
		StringWriter writer = new StringWriter();
		velocityEngine.evaluate(context, writer, "", content);
		return writer.toString();
	}

}
