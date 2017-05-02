/**
 * Created the com.xcc.web.core.XInitConfig.java
 * @created 2017年4月25日 上午10:30:58
 * @version 1.0.0
 */
package com.xcc.web.config;

import java.io.InputStream;

import com.alibaba.fastjson.JSONObject;
import com.xcc.utils.JSONFileUtil;

/**
 * com.xcc.web.core.XInitConfig.java
 * @author XChao
 */
public class XInitConfig {
	// 全局config配置
	private JSONObject config;

	public XInitConfig(InputStream stream) {
		this.config = JSONFileUtil.readToObject(stream);
	}

	public String getEncode() {
		return this.config.getString("encoding");
	}

	public String getActionSuffix() {
		return this.config.getString("action-suffix");
	}

	public long getWorkerId() {
		return this.config.getLongValue("worker-id");
	}

	public String getInitClazz() {
		return this.config.getString("init-clazz");
	}

	public JSONObject getInitLogging() {
		return this.config.getJSONObject("init-logging");
	}

	public JSONObject getHandlers() {
		return this.config.getJSONObject("handlers");
	}

	public JSONObject getDataSource() {
		return this.config.getJSONObject("data-source");
	}

	public JSONObject getInterceptors() {
		return this.config.getJSONObject("interceptors");
	}

	public JSONObject getPackages() {
		return this.config.getJSONObject("packages");
	}

	public JSONObject getTasks() {
		return this.config.getJSONObject("tasks");
	}
	
	public JSONObject getInitParamter() {
		return this.config.getJSONObject("init-paramter");
	}

	public String getLoginUrl() {
		return this.config.getString("login-url");
	}

	public String getAccessControlAllowOrigin() {
		return this.config.getString("access-control-allow-origin");
	}

	public JSONObject getFileUpload() {
		return this.config.getJSONObject("file-upload");
	}
}
