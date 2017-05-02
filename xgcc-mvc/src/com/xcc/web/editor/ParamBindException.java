package com.xcc.web.editor;

public class ParamBindException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParamBindException(String message, String paramName) {
		this(message, null, paramName);
	}

	public ParamBindException(Exception e, String paramName) {
		this(e.getMessage(), e, paramName);
	}

	public ParamBindException(String message, Exception e, String paramName) {
		super("[" + paramName + "]" + message, e);
	}

	public ParamBindException(Exception e) {
		super(e);
	}
}
