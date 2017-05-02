package com.xcc.web.editor;

import javax.servlet.http.HttpServletResponse;

import com.xcc.db.DBFactory;
import com.xcc.db.IDB;
import com.xcc.web.DefaultRequest;
import com.xcc.web.core.ActionParameter;

public class DBEditor implements IEditor {
	public Object getParamValue(String paramName, Class<?> paramType, DefaultRequest request,
		HttpServletResponse response) {
		try {
			IDB db = DBFactory.create(paramName);
			request.setAttribute(ActionParameter.PARAMETER_KEY + paramName, db);
			return db;
		} catch (Exception e) {
			throw new ParamBindException(e);
		}
	}

}
