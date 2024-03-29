package com.novation.eligibility.ui.web.model;

public class Tier {
	private String name;
	private String value;
	
	
	public Tier() {
		
	}
	
	public Tier(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Tier [name=" + name + ", value=" + value + "]";
	}
	
	
}
