package com.yzl.planManagementService;

import com.yzl.utils.attrs;

public interface DataPushService {

	String text(String name);
	
	//String taskProgress,taskProgress����
	//dname����, epcname����, tname���̵��ֶ� ,time ʱ��
	void push(attrs attrs) throws Exception;
}
