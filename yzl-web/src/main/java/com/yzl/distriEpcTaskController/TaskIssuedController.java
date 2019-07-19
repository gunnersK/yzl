package com.yzl.distriEpcTaskController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.LogService.RedisCache;
import com.yzl.distriEpcTaskService.TaskIssuedService;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;

/***
 * ���񷢲����� Controller
 * @author administrator_
 *
 */
@Controller
public class TaskIssuedController {

	@Autowired
	private TaskIssuedService taskIssuedService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private MessageService messageService;
	
	//ͨ��year��ҳ��ѯ�·�������ͨ���ؼ�������ţ���ҵ��ȣ�����������������з���
	@RequestMapping("/taskIssued/pageQueryByAreaCodeAndGCLBAndYear")
	@ResponseBody
	public EasyUIResult  pageQueryByAreaCodeAndGCLB(@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="10")Integer rows
			,HttpServletRequest request,String areaCode,String year,String GCLB){
	 	 EasyUIResult result = taskIssuedService.queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(page,rows,request,areaCode,year,GCLB);
	 	 return result;
	}

	//ͨ��area��year��GCLB��ҳ��ѯ�·�������ͨ���ؼ�������ţ���ҵ��ȣ�����������������з���
	@RequestMapping("/taskIssued/pageQueryByYear")
	@ResponseBody
	public EasyUIResult pageQueryByAreaCodeAndYearAndGCLB(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
				, @RequestParam(value="rows",defaultValue="10")int rows,String year,String GCLB){//TODO
		//��ҳ��ѯ
		EasyUIResult result = taskIssuedService.queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(request,page, rows,year, GCLB);
		return result;
	}
	
	//��ӷ�������
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/add/taskIssued")
	@ResponseBody
	public YzlResult add(HttpServletRequest request,@RequestParam("params")List<String> params){
		YzlResult result = taskIssuedService.add(request,params);
		return result;
	}
	
	//�޸ķ�������
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/update/taskIssued")
	@ResponseBody
	public YzlResult update(HttpServletRequest request,@RequestParam("params")List<String> params){
		YzlResult result = taskIssuedService.update(request,params);
		return result;
	}
	
	//ɾ������������
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="taksWorkdelIsuued")
	@RequestMapping("/delete/taskIssued")
	@ResponseBody
	public YzlResult delete(String[] params,String param,String year,HttpServletRequest request){
		//���param��Ϊ�գ���ǰֱ̨����һ������
		if(!StringUtils.isBlank(param)){
			params=new String[1];
			//��Ҫɾ����������ӵ�������
			params[0]=param;
		}
		return taskIssuedService.delete(params,year,request);
	}

	

	//����
	@RequestMapping("/derive")
	@ResponseBody
	public YzlResult derive(HttpServletResponse response,String year,String click,String areaCode,String ZLLB,String GCLB) throws IOException {
		return taskIssuedService.derive(response,year,click,areaCode,ZLLB,GCLB);
	}
	
	//����/TaskIssued/toLead/dataDr
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/TaskIssued/toLead")
	@ResponseBody
	public String toLead(@RequestParam("excelNameing")MultipartFile[] excelName,HttpServletRequest request) {
		
		String lead = taskIssuedService.toLead(excelName);
		
		messageService.getRecordsByUserId(request);
		return lead;
	}
	
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/TaskIssued/toLead/dataDr")
	@ResponseBody
	public String toLeadDataDr(@RequestParam("excelName")MultipartFile[] excelName, String year) {
		return taskIssuedService.toLeadDataDr(excelName, year);
	}
	
	
	/***
	 * �ļ��ϴ�
	 * @param uploadFile
	 * @param response
	 * @param request
	 * @return
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/taskIssued/file/upload")
	@ResponseBody
	public Map uploadFile(@RequestParam("uploadFile")MultipartFile[] uploadFile,HttpServletResponse response,HttpServletRequest request) {
		for (MultipartFile multipartFile : uploadFile) {
			System.out.println("multipartFile="+multipartFile.getName());
			System.out.println("getOriginalFilename="+multipartFile.getOriginalFilename());
		}
		System.out.println("uploadFile="+uploadFile.length);
		System.out.println("----------------");
		Map result = taskIssuedService.uploadFile(uploadFile,response,request);
		return result;
	}
	
	/**
	 * ɾ�����ϴ����ļ�
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)delIsuueFile
//	@RedisCache(type="delIsuueFile")
	@RequestMapping("/taskIssued/delete/uploadFile")
	@ResponseBody
	public YzlResult delete_uploadFile( @RequestParam(value="deleteFileName",required=false)String deleteFileName,@RequestParam(value="countyCode",required=false)String countyCode
			,@RequestParam(value="year",required=false)String year,@RequestParam(value="uploadFiles",required=false)String uploadFiles,@RequestParam(value="cityName",required=false,defaultValue="")String cityName,HttpServletRequest request) {
		YzlResult result = taskIssuedService.delete_uploadFileService(deleteFileName,countyCode,year,uploadFiles,cityName,request);
		return result;
	}
	
	/***
	 * ҳ���ѯ��������
	 * @param request
	 * @param page
	 * @param rows
	 * @param year
	 * @param areaCode
	 * @param ZLLB
	 * @param ZLLB
	 * @return
	 */
//	@RedisCache(type="taskIssued")
	@RequestMapping("/taskIssued/queryTaskData")
	@ResponseBody
	public EasyUIResult queryTaskData(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
			,@RequestParam(value="rows",defaultValue="10")int rows,String year,String areaCode,String ZLLB,String usr,@RequestParam("GCLB")String GCLB){
		EasyUIResult result = taskIssuedService.queryTaskData(page, rows, year, areaCode,ZLLB,GCLB);
		return result;
	}
	
	/***
	 *  �������
	 * @param taskNumbers "jh"+�������+"Y"+�������+":"+�����·��Ļ���  ƴ�Ӷ��� 
	 * @param year        //��ҵ��� �� �ƻ����
	 * @param countyCode   //�ؼ��������   String disCode,String usr,--δ֪����
	 * @param uploadFileUrlList //�ϴ��ļ����ļ��� ����
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="taksWorkdel")///,String disCode,String usr
	@RequestMapping("/taskIssued/add")
	@ResponseBody
	public YzlResult addData(@RequestParam("taskNumbers")List<String> taskNumbers,String year,
			String countyCode,String uploadFiles,HttpServletRequest request){
//		System.out.println("taskNumbersw=5555555555555555555555"+taskNumbers.size());
		YzlResult result = taskIssuedService.addData(taskNumbers,year,countyCode,uploadFiles);
		messageService.getRecordsByUserId(request);
		return result;
	}
	
	/***
	 * �������
	 * @param taskNumbers "jh"+�������+"Y"+�������+":"+�����·��Ļ���  ƴ�Ӷ��� 
	 * @param year			//��ҵ��� �� �ƻ����
	 * @param countyCode   �ؼ��������
	 * @param uploadFiles   �ϴ��ļ����ļ��� ����
	 * @return
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
//	@RedisCache(type="taksWorkdelIsuuedUpdate")
	@RequestMapping("/taskIssued/update")
	@ResponseBody
	public YzlResult updateData(@RequestParam("taskNumbers")List<String> taskNumbers,String year,String countyCode,String uploadFiles,HttpServletRequest request){
		YzlResult result = taskIssuedService.updateData(taskNumbers,year,countyCode,uploadFiles);
		messageService.getRecordsByUserId(request);
		return result;
	}
	
	
	/**
	 * �ļ�����
	 * @param fileUrl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/taskIssued/download/uploadFile")
	@ResponseBody
	public YzlResult downloadUploadFile(@RequestParam("fileUrl")String fileUrl,HttpServletRequest request,HttpServletResponse response){
		try {
			String dowloadFileUrl = new String(fileUrl.getBytes("iso-8859-1"), "utf-8");
			System.out.println("=========+++++++++============"+dowloadFileUrl);
			taskIssuedService.downloadUploadFile(dowloadFileUrl,request,response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return YzlResult.ok();
	}
	/***
	 * �����ϴ����ļ�
	 * @param year			//��ҵ��� �� �ƻ����
	 * @param countyCode   �ؼ��������
	 * @param uploadFiles   �ϴ��ļ����ļ��� ����
	 * @return
	 */
	@RequestMapping("/taskIssued/fileUpload/update")
	@ResponseBody
	public YzlResult updateFileUpload(String year,String countyCode,String uploadFiles){
		System.out.println("casa");
		System.out.println("year="+year+" countyCode="+countyCode +"  uploadFiles="+uploadFiles);
		return taskIssuedService.updateUploadFileData(year,countyCode,uploadFiles,null);
	}
	
	/***
	 * ɾ�����ݿ����ϴ����ļ�
	 * @param year
	 * @param countyCode
	 * @param uploadFiles
	 * @return
	 */
/*	@RequestMapping("/taskIssued/fileUpload/delete")
	@ResponseBody
	public YzlResult deleteFileUpload(String year,String countyCode,String uploadFiles){
		System.out.println("delete year="+year+" countyCode="+countyCode +"  uploadFiles="+uploadFiles);
		return taskIssuedService.updateUploadFileData(year,countyCode,uploadFiles,null);
	}*/
	

	
}
