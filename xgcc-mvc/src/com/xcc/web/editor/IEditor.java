/**
 * Created the com.xsy.web.editor.XIEditor.java
 * @created 2016年9月24日 下午8:14:53
 * @version 1.0.0
 */
package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.web.DefaultRequest;

/**
 * 参数获取接口
 * @author XChao
 */
public interface IEditor {

	/**
	 * @param paramName 参数名称
	 * @param paramType 参数类型
	 * @param request
	 * @return
	 */
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) throws Exception;
}
