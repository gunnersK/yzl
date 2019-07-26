package com.yzl.distriEpcTaskController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.LogService.LogAnno;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

import net.sf.json.JSONObject;

@Controller
public class TaskController {


	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MessageService messageService;
	//��ҳ��ѯ��������
	@RequestMapping("/show_task")
	@ResponseBody
	public EasyUIResult pagefindByAll(String value,Integer page,Integer rows){
		EasyUIResult result = taskService.findByAll(value,page,rows);
		return result;
	}
	
	@RequestMapping("/task/queryAll")
	@ResponseBody
	public List<YzlTask> queryAll(String year,String areaCode){
		List<YzlTask> taskList = taskService.queryAllByPerms(year, areaCode);
		return taskList;
	}
	
	/****
	 *  ��ѯ��������
	 * @param name
	 * @return
	 */
	@RequestMapping("/task/findAll")
	@ResponseBody
	public YzlResult findAll(){
		YzlResult result = taskService.findAll();
		return result;
	}
	
	/***
	 * ��ѯ��������
	 * @return
	 */
	@RequestMapping("/task/query")
	@ResponseBody
	public List<YzlTask> queryByYearAndAreaCodeAndZLLBAndGCLB(String year,String disCode,String ZLLB,String GCLB){
		List<YzlTask> tasks=null;
		try {
			tasks = taskService.queryByYearAndAreaCodeAndZLLBAndGCLB(year, disCode, ZLLB, GCLB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
	//�û���У��
	@RequestMapping("/verifyTname")
	@ResponseBody
	public YzlTask findByName(String name) {
		
		YzlTask task = taskService.findByName(name);
		if (task==null) {
			return null;
		}
		return task;
	}
	
	//���
	@RequestMapping("/addTask")
	@ResponseBody
	public YzlResult addTask(YzlTask task) {
		try {
			YzlResult result = taskService.addTask(task);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
			//throw new RuntimeException("���������");
		}
	}
	
	//ɾ��
	@RequestMapping("/deleterTask")
	@ResponseBody
	public YzlResult deleterTask(String [] ids) {
		 
		try {
			YzlResult result = taskService.deleterTask(ids);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
			//throw new RuntimeException("ɾ��������");
		}
	}
	
	/***
	 * ���ݹ���id��ѯ��������
	 * @return
	 */
	@RequestMapping("/task/queryByEpcEcode")
	@ResponseBody
	public YzlResult queryByEpcEcode(HttpServletRequest request,Integer Ecode,String year,String areaCode){
		YzlResult result = taskService.queryByEpcEcode(request,Ecode,year,areaCode);
		return result;
	}
	
	
	//���ݹ���id��ҳ��ѯ�����������
	@RequestMapping("/progress/queryProgressByEpc")
	@ResponseBody
	public EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode,@RequestParam(value="page",defaultValue="1")Integer page
			, @RequestParam(value="rows",defaultValue="10")Integer rows,String searchKey,String area,String year){
		//��ҳ��ѯ
		EasyUIResult result = taskService.pageQueryProgressByEpc(request,ecode,page,rows,searchKey,area,year);
		JSONObject fromObject = JSONObject.fromObject(result);
		System.out.println(fromObject);
		return result;
	}
	
	//��ҳ��ѯ�����������
/*	@RequestMapping("/progress/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
				, @RequestParam(value="rows",defaultValue="10")int rows,String area,String year,String searchKey){
		//��ҳ��ѯ
		EasyUIResult result = taskService.pageQuery(request,page, rows,searchKey,area,year);
		return result;
	}*/
	
	
	/***
	 * ����������
	 * @return
	 */
	@RequestMapping("/add/taskProgress")
	@ResponseBody
	public YzlResult addTaskProgress(HttpServletRequest request,@RequestParam("epcode")String epcId,@RequestParam("params")List<String> params ){
		Map<String,String> paramMap = new HashMap<>();
		//�Ѽ������ڵ�����Ԫ�ز���K,V��
		for (String str : params) {
			String[] Strs = str.split("=");
			if(Strs.length==2){
				paramMap.put(Strs[0], Strs[1]);
			}
		}
		taskService.addTaskProgress(request,epcId,paramMap);
		return YzlResult.ok();
	}
	
	/***
	 * �޸��������
	 * @return
	 */
	@RequestMapping("/update/taskProgress")
	@ResponseBody
	public YzlResult updateTaskProgress(HttpServletRequest request,@RequestParam("params")List<String> params){
		Map<String,String> paramMap = new HashMap<>();
		//�Ѽ������ڵ�����Ԫ�ز���K,V��
		for (String str : params) {
			String[] Strs = str.split("=");
			if(Strs.length==2){
				paramMap.put(Strs[0], Strs[1]);
			}
		}
		taskService.updateTaskProgress(request, paramMap);
		return YzlResult.ok();
	}
	
	@RequestMapping("/index/test")
	public String testData(@RequestParam("year")String year,Model model){
		//��ҳ��ѯ
		//EasyUIResult result = taskService.pageQuery(1, 10, 1);
		//Object json = JSONArray.toJSON(result);
		//model.addAttribute("pageQuery",json);
		return "2019year";
	}
	
	
	@RequestMapping("/search/taskProgress")
	@ResponseBody
	public EasyUIResult searchKey(HttpServletRequest request,@RequestParam("searchKey")String searchKey,@RequestParam("page")Integer page
				,@RequestParam("rows")Integer rows){
		EasyUIResult result = taskService.searchKey(request,searchKey,page,rows);
		return result;
	}
	
	/***
	 * ���ݹ���id��ѯ��������
	 * @return
	 */
	@RequestMapping("/task/queryByEcode")
	@ResponseBody
	public YzlResult queryByEcpde(HttpServletRequest request,Integer Ecode,String year,String disCode){
		YzlResult result = taskService.queryByXbGCLB(request, Ecode,year,disCode);
		return result;
	}
	
//	@CacheEvict(value = "taskWork",allEntries=true)
//	@Caching(evict= {@CacheEvict(value = "taskWork",key = "#disCode+*",beforeInvocation=true) })
//	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="�ύ")
	//�ύ
	@RequestMapping("/submit")
	@ResponseBody
	public YzlResult sub(String[]  subData,String disCode,String usr,String subDatas,String time,String lea,String gclb,String [] countys,String [] fileNames,HttpServletRequest request) {
		if (subDatas!=null) {
			subData = null;
			subData =new String[1];
			subData[0] = subDatas;
		}
		YzlResult sub = taskService.sub(subData,time,gclb,countys);
		messageService.getRecordsByUserId(request);
		return sub;
	}

	//�˻�
//	@CacheEvict(value = "taskWork",allEntries=true)
//	@Caching(evict= {@CacheEvict(value="taskWork",key = "#disCode+*",beforeInvocation=true)})
//	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="�˻�")
	@RequestMapping("/backData")
	@ResponseBody
	public YzlResult back( String[] backData,String disCode,String usr,String backDatas,String time,String lea,String [] fileNames,String [] countys,String gclb,HttpServletRequest request) {
		if (backDatas!=null) {
			backData = null;
			backData =new String [1];
			backData[0] = backDatas;
		}
		YzlResult back = taskService.back(backData,time,gclb,countys);
		messageService.getRecordsByUserId(request);
		return back;
	}
	
//	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="���")
	//���
	@RequestMapping("/auditData")
	@ResponseBody
	public YzlResult audit( String[] auditData,String disCode,String auditDatas,String time,String gclb,String [] countys,String lea,String [] fileNames,HttpServletRequest request) {
		if (auditDatas!=null) {
			auditData = null;
			auditData =new String [1];
			auditData[0] = auditDatas;
		}
		YzlResult yzlResult = taskService.audit(auditData,time,gclb,countys);
		messageService.getRecordsByUserId(request);
		return yzlResult;
	}
	
	//ѡ�񹤳�
	@RequestMapping("/ChangedEpc")
	@ResponseBody
	public List<YzlTask> ChangedEpc(String time,String code,String epc) {
		return taskService.ChangedEpc(time,code,epc);
	}
	
	//
	@RequestMapping("/epcData")
	@ResponseBody
	public EasyUIResult epcData(String time,String code,String epc,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows) {
		return taskService.epcData(time,code,epc,page,rows);
	}
	
	
	//�������� ----------------------------------------------------------------
	@RequestMapping("/task/getTableHeader")
	@ResponseBody
	public List<YzlEpc> getTableHeader(String year,String disCode,String ZLLB){
		List<YzlEpc> result = taskService.getTableHeader(year, disCode,ZLLB);
		return result;
	}
	

	
}
