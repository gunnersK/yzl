package com.yzl.utils.enums;

public enum AuthorityEnum {
	
	TASK_ISSUED_ADD("sys:rwxf:tj","���������·����Ȩ��"),
	TASK_AUDIT("sys:rwgzz:sh","�����������Ȩ��"),
	;
	private AuthorityEnum() {
	}
	private AuthorityEnum(String perms, String mark) {
		this.perms = perms;
		this.mark = mark;
	}
	private String 	perms;//Ȩ�ޱ�ʶ
	private String mark;//Ȩ������
	public String getPerms() {
		return perms;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}

	
}
