package com.yzl.planManagementService;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface CompletionTaskService {

	List<YzlEpc> epcTab(String year,String disCode, String zllb);
	

	EasyUIResult epcTaskData(String year,String disCode,Integer page,Integer rows,String usr,String zllb);
	
	YzlDistrict Ddis(String dcode);
	//
	void derive(String nid,String year,HttpServletResponse response,String disCode,String zllb);
	//�˻�
	YzlResult back(String[] backData, String time,String zllb,String [] countys);

	//��ѯ��������
	 EasyUIResult queryTaskData(Integer page, Integer rows, String year, String areaCode,String ZLLB,String usr);

}
