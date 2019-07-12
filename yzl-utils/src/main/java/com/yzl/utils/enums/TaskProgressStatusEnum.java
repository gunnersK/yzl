package com.yzl.utils.enums;

public enum TaskProgressStatusEnum {
	//��������˻� ���������ʶĩβ ���� back
	TASKPROGRESS_SEND_BACK("back","���񲻺ϸ�,�ϼ��˻�"),
	
	TASKPROGRESS_PROVINCE_CHECK("check","��������������"),
	
	TASKPROGRESS_SUCCEED("finish","��������ɲ����ͨ��"),
	
	TASKPROGRESS_CITY_CHECK("audit","���м�����Ա���"),
	
	TASKPROGRESS_PROVINCE_AUDIT("ROOT","���������Ȩ��"),
	;
	//������ȱ�ʶ
	private String code;
	//���������Ϣ
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private TaskProgressStatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	private TaskProgressStatusEnum() {
	}
	
	
}
