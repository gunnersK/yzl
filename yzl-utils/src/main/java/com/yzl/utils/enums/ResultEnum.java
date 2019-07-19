package com.yzl.utils.enums;

public enum ResultEnum {
	//����
	PARAM_ERROR(1,"��������ȷ"),
	
	PROJECT_ID_ISNULL(2,"����idΪnull"),
	
	CITY_IS_NULL(3,"������Ϊ��"),
	
	COUNTY_IS_NULL(4,"��/����Ϊ��"),
	
	REGION_ID_ISNULL(5,"����idΪ��"),
	
	DISTRICT_MARK_ISNULL(6,"������ʶΪ��"),
	
	AREA_CODE_ISNULL(13,"����idΪnull"),
	
	DISTRICT_LIST_IS_NULL(101,"��ѯ���ĵ�������Ϊ��"),
	
	USER_NOT_MENU(201,"��ǰ�û�û���κ�Ȩ��"),
	
	TASK_BASE_NOT_NUMBER(105,"�·�������������Ǵ�����"),
	
	LOGIN_USER_NOT_EXIST(104,"��ǰ��¼���û�������/Ϊ��"),
	
	DELETE_PARAM_IS_NULL(105,"��Ҫɾ��������Ϊ��"),
	
	TASKBASE_NOT_IS_NUMBER(106,"�����·��������Ǵ����֣����ܰ���������ַ�"),

	ZYND_OR_JHND_IS_NULL(107,"��ҵ��Ȼ�ƻ���ȵĲ���ֵΪ��"),
	
	COUNTY_OR_CITY_CODE_IS_NULL(108,"�м����ؼ���������Ų���ֵΪ��"),

	DATA_INSERT_FAILURE(109,"�����������ʧ��"),
	
	DATA_UPDATE_FAILURE(122,"�����������ʧ��"),
	
	COUNTYCODE_PARAM_ERROR(20001,"�ؼ���Ŵ���"),
	
	COUNTYCODE_YEAR_ERROR(20011,"���Ϊ��")
	;
	//����״̬��
	private int code;
	//����������Ϣ
	private String message;
	

	
	ResultEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	ResultEnum() {
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
