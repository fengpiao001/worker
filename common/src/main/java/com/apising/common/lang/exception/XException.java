package com.apising.common.lang.exception;

/**
 * 用于判断是否是可以中断程序异常
 *
 * @author user
 */
public class XException extends RuntimeException {
	private static final long serialVersionUID = 7473490583498156230L;

	// 是否是需要中断程序
	private boolean needInterrupt;
	// 用于展示给前端的信息
	private String showText;

	private int errorCode = 501;


	public XException() {
		super();
	}

	public XException(String message, Throwable cause) {
		super(message, cause);
		this.needInterrupt = true;
	}

	public XException(String message) {
		super(message, null);
		this.needInterrupt = true;
	}

	public XException(Throwable cause) {
		super(cause);
		this.needInterrupt = true;
	}

	public XException setNeedInterrupt(boolean needInterrupt) {
		this.needInterrupt = needInterrupt;
		return this;
	}

	public XException setShowText(String showText) {
		this.showText = showText;
		return this;
	}

	public XException setErrorCode(int errorCode){
		this.errorCode = errorCode;
		return this;
	}

	public int getErrorCode(){
		return this.errorCode;
	}

	public boolean isNeedInterrupt() {
		return needInterrupt;
	}

	public String getShowText() {
		return showText;
	}

	public void toThrow() {
		if (needInterrupt) {
			throw this;
		}
	}

}
