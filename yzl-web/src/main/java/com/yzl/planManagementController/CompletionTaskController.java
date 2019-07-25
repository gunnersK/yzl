package com.yzl.planManagementController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.distriEpcTaskService.TaskIssuedService;
import com.yzl.planManagementService.CompletionTaskService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Controller
public class CompletionTaskController {

	@Autowired
	private CompletionTaskService completionTaskService;
	@Autowired 
	private TaskIssuedService taskIssuedService; 
	
	@RequestMapping("/completionTask/taskTab")
	@ResponseBody
	public List<YzlEpc> epcTab(String year,String disCode,String usr,String gclb){
		if ("GX45".equals(disCode)) {
			disCode = "45";
		}
		return completionTaskService.epcTab(year,disCode,gclb);
	}
	
	
//	@Cacheable(value= "completionWorkEpcTaskData",key = "#year+':'+#disCode+':'+#usr+':'+#page")
//	@RedisCache(type="completionTask")
	@RequestMapping(value = "/completionTask/epcTaskData",produces="application/json;charset=utf-8")
	@ResponseBody
	public EasyUIResult epcTaskData(String year,String disCode,String usr,Integer page,Integer rows,String gclb) {
		if ("GX45".equals(disCode)) {
			disCode = "45";
		}
		//return completionTaskService.epcTaskData(year,disCode,page,rows,usr);
		EasyUIResult result = completionTaskService.queryTaskData(page, rows, year, disCode,gclb,usr);
		return result;
	}
	
	//根据造林类别查询表头
	@RequestMapping("/Completion/taskTab")
	@ResponseBody
	public List<YzlEpc> ZLLBFindTable(String year,String disCode,String gclb){
		return completionTaskService.epcTab(year, disCode,gclb);
	}
	
	//根据dcode获取地区
	@RequestMapping("/completionTask/Ddis")
	@ResponseBody
	public YzlDistrict Ddis(@RequestParam("dcode")String dcode) {
		return completionTaskService.Ddis(dcode);
	}
	
	
	//导出
	@RequestMapping("/completionTask/derive")
	@ResponseBody
	public void derive(String nid,String year,HttpServletResponse response,String disCode,String gclb) {
		completionTaskService.derive(nid,year,response,disCode,gclb);
	}
	
//	@CacheEvict(value = {"completionWorkEpcTaskData","completionWorktb","taskWork"},allEntries=true)
//	@RedisCache(type="taksWorkdel")
//	@LogAnno(opreateType="自治区退回")
	@RequestMapping("/completionTask/backData")
	@ResponseBody
	public YzlResult back(String[] backData,String disCode,String usr,String backDatas,String time,String lea,String [] fileNames,String gclb,String [] countys) {
		if (backDatas!=null) {
			backData = null;
			backData =new String [1];
			backData[0] = backDatas;
		}
		YzlResult back = completionTaskService.back(backData,time,gclb,countys);
		return back;
	}
}
