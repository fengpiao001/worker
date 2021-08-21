package com.apising.common.lang.domain;

public class Result<T> {
	private Integer code;
	private String message;
	private T data;

	public Result() {
		this.code = 0;
		this.message = "OK";
		this.data = null;
	}

	public Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result<T> data(T data) {
		this.data = data;
		return this;
	}

	public Result<T> code(int code) {
		this.code = code;
		return this;
	}

	public Result<T> message(String message) {
		this.message = message;
		return this;
	}

	public static <T> Result<T> success(T data) {
		return new Result<T>().data(data);
	}

	public static <T> Result<T> fail(int code, String message, T data) {
		return new Result<>(code, message, data);
	}

	public static <T> Result<T> fail(int code, String message) {
		return new Result<>(code, message, null);
	}
}
