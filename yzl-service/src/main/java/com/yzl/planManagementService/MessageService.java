package com.yzl.planManagementService;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.YzlResult;

public interface MessageService {

	//��ѯ��ǰ�û����ܼ�¼��
	int getRecordsByUserId(HttpServletRequest request);

	YzlResult updateStatuByUserId();

}
