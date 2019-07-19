package com.yzl.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.adminService.UserService;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;
	
	/**
	 * ��ʾ�����û�
	 * @return
	 */
	@RequestMapping("admin/userlist")
	@ResponseBody
	public EasyUIResult findAll(Integer page,Integer rows){
		
		EasyUIResult result = userService.findAll(page,rows);
		return result;
	}
	
	/**
	 * ����û�ҳ��
	 * @return
	 */
	@RequestMapping("/userinfo_add")
	public String userinfo_add() {
		
		return "admin/userinfo";
	}
	
	/**
	 * ���ذ�ť
	 * @return
	 */
	@RequestMapping("ret")
	public String ret() {
		return "admin/userinfo";
	}
	
	/**
	 * ����
	 * @param user
	 * @return
	 */
	@RequestMapping("/user_addUser")
	public String addUser(YzlUser user,String [] roleIds){
		
		YzlResult result = userService.addUser(user,roleIds);
		/*if (result.getData()!="200") {
			
			return "admin/userinfo";
		}*/
		
		return "admin/userlist";
	}
	
	/**
	 * ɾ��
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteUser")
	public YzlResult deleteUser(String [] ids) {
		
		YzlResult result = userService.deleteUser(ids);
		
		return result.ok();
	}
	
	/**
	 * �޸�
	 * @param user
	 * @return
	 */
	@RequestMapping("updateUser")
	public String updateUser(YzlUser user) {
		
		YzlResult result = userService.updateUser(user);
		
		return "admin/userlist";
	}
	
	/**
	 * �û���У��
	 * @param username
	 * @return
	 */
	@RequestMapping("verifyUsername")
	@ResponseBody
	public YzlUser findByUsername(String username) {
		YzlUser user = userService.findByUsername(username);
		return user;
	}
}
