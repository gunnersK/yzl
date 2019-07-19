package com.yzl.distriEpcTaskController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.yzl.distriEpcTaskService.TemplateService;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Controller
public class TemplateController {

	@Autowired
	private TemplateService templateService;
	
	//��ѯ����
	@RequestMapping("/show_template")
	@ResponseBody
	public EasyUIResult findAll(String value,Integer page,Integer rows) {
		//easyui��ҳ��Ҫ��json��ʽ{"total":"111","rows":[{},{},{},{}]}
		EasyUIResult result = templateService.findAll(value,page,rows);
		PageHelper.startPage(page, rows);
		
		//�ܼ�¼��
		return result;
	}
	
	//���
	@RequestMapping("/addTemplate")
	@ResponseBody
	public YzlResult addTemplate(String eid,String [] tids) {
			YzlResult result = templateService.addTemplate(eid,tids);
			return result;
	}
	//ɾ��
	@RequestMapping("/deleterTemplate")
	@ResponseBody
	public YzlResult deleterTemplate(String [] etdis) {
			YzlResult result = templateService.deleterTemplate(etdis);
			return result;
	}
	
	//�޸�
	@RequestMapping("/updateTemplate")
	@ResponseBody
	public YzlResult updateTemplate(String edname,String [] ids) {
		try {
			YzlResult result = templateService.updateTemplate(edname,ids);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
		}
	}
	
	//չʾ��������
	@RequestMapping("/show_tasks")
	@ResponseBody
	public List<YzlTask> showTask(){
		return templateService.showTask();
	}
}
