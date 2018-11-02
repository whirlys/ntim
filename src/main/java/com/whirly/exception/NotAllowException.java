package com.whirly.exception;

public class NotAllowException extends RuntimeException {

	private static final long serialVersionUID = 4401440535641871948L;

	private int errorCode = 10;

	private String errorMsg;

	public NotAllowException(String errorMsg, Throwable e) {
		super(errorMsg, e);
		this.errorMsg = errorMsg;
	}

	public NotAllowException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
