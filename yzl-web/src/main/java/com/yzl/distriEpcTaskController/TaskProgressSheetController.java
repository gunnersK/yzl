package com.yzl.distriEpcTaskController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yzl.distriEpcTaskService.TaskProgressSheetService;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.ObjTOJson;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.HighchartsResultVO;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


@Controller
public class TaskProgressSheetController {

	@Autowired
	private TaskProgressSheetService taskProgressSheetService;
	/***
	 * ��ҳ��ѯ�����������
	 * @param page
	 * @param rows
	 * @param columns
	 * @return
	 */
	@RequestMapping("/taskProgressSheet/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows")Integer rows
			,Integer columns,String searchKey,String area,String year){
		EasyUIResult easyUIResult = taskProgressSheetService.pageQuery(request,page, rows,searchKey,area,year);
		return easyUIResult;
	}
	
	
	/***
	 * ���ݹ���id��ѯ��ǰ�û�����Ȩ���������
	 * @param page
	 * @param rows
	 * @param columns
	 * @return
	 */
	@RequestMapping("/taskProgressSheet/pageQueryByEcode")
	@ResponseBody
	public EasyUIResult pageQueryByEcode(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows")Integer rows
			,Integer ecode,String area,String year,String searchKey){
		if(ecode==null){
			throw new YzlException(ResultEnum.PROJECT_ID_ISNULL);
		}
		
		EasyUIResult easyUIResult = taskProgressSheetService.pageQueryByEcode(request,page, rows, ecode,area,year,searchKey);
		
		return easyUIResult;
	}
	
	/***
	 * �޸��������
	 * @return
	 */
	@RequestMapping("/update/taskProgressSheet")
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
		taskProgressSheetService.updateTaskProgressSheet(request, paramMap);
		return YzlResult.ok();
	}
	
	//�ύ����
	@RequestMapping("/submit/taskProgressSheet")
	@ResponseBody
	public YzlResult submit(HttpServletRequest request,@RequestParam("paramList")List<String> paramList){
		//taskProgressSheetService.updateTaskProgressSheet(request, params);
		Map<String,String> map = new HashMap<>();
		for (String params : paramList) {
			System.out.println(params);
				//����ֵ��   mark=NNS-XNQ;
			String[] paramStrs = params.split("=");
			if(paramStrs.length==2){
				map.put(paramStrs[0], paramStrs[1]);
			}
		}
		taskProgressSheetService.submitTaskProgressSheet(request, map);
		return YzlResult.ok();
	}
	
	//�˻�
	@RequestMapping("/sendback/taskProgressSheet")
	@ResponseBody
	public YzlResult sendBack(HttpServletRequest request,@RequestParam("paramList")List<String> paramList){
		Map<String,String> map = new HashMap<>();
		for (String params : paramList) {
			System.out.println(params);
				//����ֵ��   mark=NNS-XNQ;
			String[] paramStrs = params.split("=");
			if(paramStrs.length==2){
				map.put(paramStrs[0], paramStrs[1]);
			}
		}
		taskProgressSheetService.sendBack(request, map);
		return YzlResult.ok();
	}
	
	//���ͨ��
	@RequestMapping("/approve/taskProgressSheet")
	@ResponseBody
	public YzlResult approve(HttpServletRequest request,@RequestParam("paramList")List<String> paramList){
		Map<String,String> map = new HashMap<>();
		for (String params : paramList) {
			System.out.println(params);
				//����ֵ��   mark=NNS-XNQ;
			String[] paramStrs = params.split("=");
			if(paramStrs.length==2){
				map.put(paramStrs[0], paramStrs[1]);
			}
		}
		
		taskProgressSheetService.approve(request, map);
		
		return YzlResult.ok();
	}
	
	/***
	 * ��ѯ��������ɵ�����By����id
	 * @param page
	 * @param rows
	 * @param columns
	 * @return
	 */
	@RequestMapping("taskProgressSheet/finish/pageQueryByEcode")
	@ResponseBody
	public EasyUIResult pageQueryFinishTaskByEcode(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows")Integer rows
			,Integer Ecode,String searchKey){
		EasyUIResult easyUIResult = taskProgressSheetService.pageQueryFinishTaskByEcode(request,page, rows, Ecode,searchKey);
		return easyUIResult;
	}
	
	/***
	 * ��ѯ��������ɵ�����
	 * @param page
	 * @param rows
	 * @param columns
	 * @return
	 */
	/*@RequestMapping("taskProgressSheet/finish/pageQuery")
	@ResponseBody*/
	public EasyUIResult pageQueryFinishTask(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows")Integer rows
			,String searchKey){
		EasyUIResult easyUIResult = taskProgressSheetService.pageQueryFinishTask(request,page, rows, searchKey);
		return easyUIResult;
	}
	
	/***
	 *
	 */
	

}
