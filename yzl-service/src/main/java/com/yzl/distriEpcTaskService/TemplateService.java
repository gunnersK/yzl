package com.yzl.distriEpcTaskService;

import java.util.List;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TemplateService {

	//��ѯ����
	EasyUIResult findAll(String value,Integer page,Integer rows);
	//���
	YzlResult addTemplate(String eid,String [] tids);
	//ɾ��
	YzlResult deleterTemplate(String[] etdis);
	//�޸�
	YzlResult updateTemplate(String edname, String[] ids);
	//�ܼ�¼��
//	Integer getTotalCount();
	//��������
	List<YzlTask> showTask();
}
