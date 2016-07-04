package com.ct.em;

public enum Status{
	SUCCESS("success"),ERROR("error"),WARN("warn");
	
	private String value;
	Status(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
