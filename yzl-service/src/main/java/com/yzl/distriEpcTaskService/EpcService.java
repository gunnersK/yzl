package com.yzl.distriEpcTaskService;

import java.util.List;

import com.yzl.pojo.YzlEpc;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;

public interface EpcService {

	//��ѯ���й���
	List<YzlEpc> findAll(String year, String areaCode);
	//��ҳ��ѯ
	EasyUIResult pageQuery(String value,Integer page,Integer rows);
	//�����û�����ѯ����
	YzlEpc findByName(String name);
	//��ӹ���
	YzlResult addEpc(YzlEpc epc);
	//����ɾ��
	YzlResult deleterEpc(String [] ids);
	List<YzlEpc> queryByYearAndAreaCodeAndZLLBAndGCLB(String year,String areaCode,String ZLLB,String GCLB);
	List<YzlTaskNumberEpcResult> queryAllAndNumber(String year,String areaCode);
	List<YzlEpc> show();
	//ͨ��С����ɵ������в�ѯ����
	List<YzlTaskNumberEpcResult> queryByXbData(String year,String disCode);
	//
}
