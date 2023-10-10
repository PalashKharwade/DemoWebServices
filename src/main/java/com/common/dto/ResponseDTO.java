package com.common.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
	
	private int code;
	private String msg;
	private Object response;
	private HttpStatus status;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the obj
	 */
	public Object getResponse() {
		return response;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setResponse(Object obj) {
		this.response = obj;
	}
	/**
	 * @return the status
	 */
	public HttpStatus getHttpStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setHttpStatus(HttpStatus status) {
		this.status = status;
	}
	public ResponseDTO(int code, String msg, Object response, HttpStatus status) {
		super();
		this.code = code;
		this.msg = msg;
		this.response = response;
		this.status = status;
	}
	public ResponseDTO() {
		super();
	}
	
	 

}
