package com.yzl.planManagementController;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.planManagementService.TaskWorkingService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlProceed;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;

@Controller
public class taskWorkingController {

	@Autowired
	private TaskWorkingService taskWorkingService;
	

	@RequestMapping("/tak/a")
	private String a(){
		return "index.jsp";
	}
	
	//��ѯ��ͷ
	@RequestMapping("/takWorking/taskTab")
	@ResponseBody
	public List<YzlEpc> taskTab(String year,String disCode,String gclb){
		YzlUser user = LoginUserUtils.getLoginUser();
		return taskWorkingService.taskTab(year,disCode,gclb,null);
	}
	
	//����չʾ

//	@Cacheable(value = "taskWork",key = "#disCode+':'+#zllb+':'+#page+':'+#rows+':'+#usr")//key = "#disCode+':'+#uuid",,keyGenerator = "SpringCacheKeyGenret"
//	@RedisCache(type = "taksWorkadd")
	@RequestMapping(value="/takWorking/epcTaskData",produces="application/json;charset=utf-8")  //String year,String disCode,String zllb,Integer page,Integer rows,String usr
	@ResponseBody								//String year,String disCode,String usr,String zllb,Integer page,Integer rows
	public EasyUIResult epcTaskData(String year,String disCode,String usr,String gclb,Integer page,Integer rows,String stat,String proceed) {
		return taskWorkingService.epcTaskData(year,disCode,gclb,page,rows,stat,proceed);
	}
	
	@RequestMapping("/takWorking/Ddis")
	@ResponseBody
	public YzlDistrict Ddis(String dcode) {
		return taskWorkingService.Ddis(dcode);
	}
	
	@RequestMapping("/takWorking/CityFlag")
	@ResponseBody
	public YzlDistrict cityFlag(String flag) {
		return taskWorkingService.findByFlag(flag);
	}
	
	//��ѯ���е��������
	@RequestMapping("/taskWorking/show_epcs")
	@ResponseBody
	public List<YzlEpc> show_epc(){
		return taskWorkingService.show_epc();
	}
	
	//������������ѯ��ͷ
	@RequestMapping("/taskWorking/taskTab")
	@ResponseBody
	public List<YzlEpc> ZLLBFindTable(String year,String disCode,String gclb,String stat){
		return taskWorkingService.taskTab(year, disCode,gclb,stat);
	}
	
//	@LogAnno(opreateType="���")
	//���/taskWorking/checkout
	@RequestMapping("/taskWorking/checkout")
	@ResponseBody
	public List<List<YzlXb>> checkOut(String  eids,String disCode,String usr,String year,String [] countys,String [] json,String lea,String [] bdgwj){
		return taskWorkingService.checkOut(eids,year,countys,bdgwj,json);
	}
	
	/**
	 * ��־��ѯ
	 * @param time
	 * @param row
	 * @return
	 */
	@RequestMapping("/takWorking/findLog")
	@ResponseBody				//String row,String time,String county,Integer page,Integer rows,String zllb
	public EasyUIResult findLog(String row,String time,String county,Integer page,Integer rows,String gclb) {
		//System.out.println("ccc");
		String countyName = null;
		try {
			countyName = new String(county.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return taskWorkingService.findLog(row,time,countyName,page,rows,gclb);
	}
	
	/**
	 * �ļ��ϴ�
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/taskWorking/upFile")
	@ResponseBody
	public YzlResult upFile(@RequestParam("fileName")MultipartFile[] fileName,HttpServletRequest request) {
		return taskWorkingService.upFile(fileName,request);
	}
	
	/**
	 * �ļ�����
	 * @param file
	 * @return
	 */
	@RequestMapping("/takWorking/deriveFile")
	@ResponseBody
	public YzlResult deriveFile(String file,HttpServletRequest request,HttpServletResponse response) {
		return taskWorkingService.takWorkingDeriveFile(file,request,response);
	}
	
	@RequestMapping("/takWorking/delFile")
	@ResponseBody
	public YzlResult delFile(String msg,HttpServletRequest request) {
		return taskWorkingService.delFile(msg,request);
	}
	
	//����
	@RequestMapping("/takWorking/proceed")
	@ResponseBody
	public List<YzlProceed> proceed(String [] gclbs,String county,String year) {
		return taskWorkingService.proceed(gclbs,county,year);
	}
	
	@RequestMapping("/takWorking/stat")
	@ResponseBody
	public String getStatByGCLB(String addGclbMark, String addAnumber, String addYear){
		return taskWorkingService.selectStatByGCLB(addGclbMark, addAnumber, addYear);
	}
}
