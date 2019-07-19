package com.yzl.utils.enums;

public enum TaskWorkSatusEnum {
	
	
	TASK_COUNTY_WORK(0,"����״̬��"),
	TASK_CITY_AUDIT(1,"�����"),
	TASK_CITY_VERIFIED(2,"���ͨ���������"),
	TASK_PROVINCE_AUDIT(3,"�˻�")
	;
	
	
	//�������״̬��
	private Integer code;
	//���������Ϣ
	private String msg;

	
	private TaskWorkSatusEnum() {
	}
	private TaskWorkSatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	
	
	
	
}
