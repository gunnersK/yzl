package com.yzl.distriEpcTaskService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TaskService {

	//��ҳ��ѯ
	EasyUIResult findByAll(String value,Integer page,Integer rows);
	//����������
	YzlTask findByName(String name);
	//���
	YzlResult addTask(YzlTask task);
	//����ɾ��
	YzlResult deleterTask(String [] ids);
	//���ݹ���id��ѯ��������  ��ǰ��ʽ���������񷢲�����
	YzlResult queryByEpcEcode(HttpServletRequest request,int ecode,String year,String areaCode);
	
	EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode, int page, int rows, String value,String area,String year);
	//��ѯ��������
	YzlResult findAll();
	//��ѯ�������񣬸���Ȩ���ж�
	List<YzlTask> queryAllByPerms(String year,String areaCode);
	//����������
	YzlResult addTaskProgress(HttpServletRequest request,String epcId,Map<String, String> params);
	//�޸ķ���������
	YzlResult updateTaskProgress(HttpServletRequest request, Map<String, String> params);
	//ɾ���ѷ���������
	YzlResult deleteByEcodeAndCreateTime(List<YzlEpcTaskProgress> params);
	//����
	EasyUIResult searchKey(HttpServletRequest request,String searchKey,Integer page,Integer rows);
	//���ݹ���id��ѯ��������  ��ǰ��ʽ������������ȹ���
	YzlResult queryByXbGCLB(HttpServletRequest request,int ecode,String year,String disCode);
	
	YzlResult sub(String[] subData,String time,String zllb,String [] countys);
	
	YzlResult back(String [] backData,String time,String zllb,String [] countys);
	
	List<YzlTask> ChangedEpc(String time, String code, String epc);
	
	EasyUIResult epcData(String time, String code, String epc,Integer page,Integer rows);
	
	//���
	YzlResult audit(String[] auditData,String time,String zllb,String [] countys);
	List<List<YzlXb>> checkOut(String eids,String year,String [] countys);

	
	//-----------------��������
	List<YzlTask> getTableHeader(String year,String disCode,String GCLB);
}
