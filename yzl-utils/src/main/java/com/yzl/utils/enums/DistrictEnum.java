package com.yzl.utils.enums;

import org.omg.CORBA.PRIVATE_MEMBER;


public enum DistrictEnum {

	REGION_ADMIN("45","����������"),
	
	
	;
	
	
	 DistrictEnum() {
	}
	 DistrictEnum(String code,String name) {
		this.name = name;
		this.code = code;
	}
	/**
	 * ��������
	 */
	private String name;
	/***
	 * �������
	 */
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
