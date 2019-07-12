package com.yzl.distriEpcTaskService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TaskIssuedService {
	
	//ͨ��year��GCLB��ҳ��ѯ�·�������ͨ���ؼ�������ţ���ҵ��ȣ�����������������з���
	EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows, HttpServletRequest request, String areaCode, String year,String GCLB);

	//ͨ��area��year��GCLB��ҳ��ѯ�·�������ͨ���ؼ�������ţ���ҵ��ȣ�����������������з���
	EasyUIResult queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(HttpServletRequest request,int page,int rows,String year,String GCLB);

	YzlResult add(HttpServletRequest request, List<String> params);

	YzlResult update(HttpServletRequest request, List<String> params);

	YzlResult delete(String[] paramList,String year,HttpServletRequest request);

	YzlResult derive(HttpServletResponse response,String year,String click,String areaCode,String ZLLB,String GCLB) throws IOException;

	String toLead(MultipartFile[] excelName);
	
	EasyUIResult  queryTaskData(Integer page,Integer rows, String year,String areaCode,String ZLLB,String GCLB);
	//�������
	YzlResult addData(List<String> taskNumbers, String year, String countyCode);
	//��������
	YzlResult updateData(List<String> taskNumbers, String year, String countyCode, String uploadFiles);
	//�ϴ��ļ�
	Map uploadFile(MultipartFile[] files,HttpServletResponse response,HttpServletRequest request);
	//ɾ�����ϴ����ļ�
	YzlResult delete_uploadFileService(String folderName, String countyCode, String year, String uploadFiles, String cityName,HttpServletRequest request);
	//�����ļ�
	YzlResult downloadUploadFile(String fileUrl,HttpServletRequest request,HttpServletResponse response);
	//�������ݿ��ļ�
	YzlResult updateUploadFileData(String year, String countyCode, String uploadFiles, String cityName);

	String toLeadDataDr(MultipartFile[] excelName);
	
	YzlResult addData(List<String> taskNumbers, String year, String countyCode,String uploadFiles);


}
