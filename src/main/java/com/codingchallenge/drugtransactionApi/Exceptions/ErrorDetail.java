package com.codingchallenge.drugtransactionApi.Exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String path;
	private String errormessage;
	private Map<String, List<InputValidationError>> errors = new HashMap<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public Map<String, List<InputValidationError>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<InputValidationError>> errors) {
		this.errors = errors;
	}

}
