package com.yzl.utils.enums;

public enum KeyNameEnum {

	county("county"),//��
	JHND("JHND"),//�ƻ����
	ZYND("ZYND"),//��ҵ���
	CITY_CODE("cityCode"),
	JH("jh"),//�ƻ�
	ZJH("zjh"),//�ƻ�
	WC("wc"),//�ƻ�
	
	;
	private String name;

	
	
	private KeyNameEnum(String name) {
		this.name = name;
	}	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
