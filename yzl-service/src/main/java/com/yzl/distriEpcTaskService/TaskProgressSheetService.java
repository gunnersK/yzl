package com.yzl.distriEpcTaskService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.HighchartsResultVO;

public interface TaskProgressSheetService {
	
	//��ҳ��ѯ�����������
	EasyUIResult pageQuery(HttpServletRequest request,int page,int rows , String searchKey,String area,String year);
	
	//��ҳ��ѯ ���ݹ���id
	public EasyUIResult pageQueryByEcode(HttpServletRequest request ,Integer page, Integer rows,Integer Ecode ,String area,String year,String searchKey);
	//�����������\
	YzlResult updateTaskProgressSheet(HttpServletRequest request, Map<String, String> params);
	//�ύ����
	YzlResult submitTaskProgressSheet(HttpServletRequest request, Map<String, String> params);

	YzlResult sendBack(HttpServletRequest request, Map<String, String> params);

	YzlResult approve(HttpServletRequest request, Map<String, String> params);
	
	//��ҳ��ѯ ���ݹ���id
	public EasyUIResult pageQueryFinishTaskByEcode(HttpServletRequest request,Integer page, Integer rows,Integer Ecode,String searchKey);
	//��ҳ��ѯ����ɵ�����
	EasyUIResult pageQueryFinishTask(HttpServletRequest request,Integer page, Integer rows,String searchKey);

	 List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request); 
}
