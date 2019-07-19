package com.yzl.adminController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.yzl.adminService.FunctionService;
import com.yzl.pojo.YzlMenu;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.TreeVO;


@Controller
public class FunctionController {

	              
	
	@Autowired
	FunctionService functionService;
	
	@RequestMapping(value="function/getParentNode",produces={"text/json; charset=UTF-8"})
	@ResponseBody
	public String getParentNode(HttpServletResponse response){
		List<TreeVO> list = functionService.getMenu();
		String json = JSONArray.toJSONString(list);
		response.setContentType("text/json;charset=utf-8");
		return json;
	}
	
	
	/***
	 * ���Ȩ��
	 * @param menu
	 * @return
	 */
	@RequestMapping("function/addFunction")
	public String addFunction(YzlMenu menu){
		System.out.println("Generatemenu="+menu.getGeneratemenu());
		functionService.save(menu);
		return "admin/function";
	}
	
	/***
	 * Ȩ�޷�ҳ��ѯ
	 * @return
	 */
	@RequestMapping("function_manage/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int page,int rows){
		EasyUIResult result = functionService.getFunctions(page,rows);
		return result;
	}
	
	/***
	 * ɾ��Ȩ��
	 */
	@RequestMapping("function/deleteBatch")
	public String deleteBath(String ids){
		functionService.delete(ids);
		return "admin/function";
	}
	
	/***
	 * �޸�Ȩ��
	 */
	@RequestMapping("function/updateFunction")
	public String update(YzlMenu menu){
		functionService.update(menu);
		return "admin/function";
	}
	
	//��ȡȨ��
	@RequestMapping("/menu_user")
	/*@ResponseBody*/
 	public /*List<YzlMenu>*/String getMenu(){
		List<YzlMenu> list = functionService.getMenus();
		/*for (YzlMenu yzlMenu : list) {
			System.out.println(yzlMenu);
		}*/
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("list", list);
		return "redirect:/index";
		//return list;
	}
}
