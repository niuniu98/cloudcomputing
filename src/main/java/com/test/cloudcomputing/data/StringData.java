package com.test.cloudcomputing.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringData {
	String name;
	String value;
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
	
}
