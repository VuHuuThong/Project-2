package com.javaweb.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String error;
    private List<String> detial =new ArrayList<>();
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetial() {
		return detial;
	}
	public void setDetial(List<String> detial) {
		this.detial = detial;
	}
    
    }
