package com.yzl.adminController;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.adminService.FunctionService;
import com.yzl.adminService.IUserService;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.planManagementController.taskWorkingController;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.MD5Utils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.UserEnum;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private FunctionService fun;
	@Autowired
	private YzlMenuMapper menuMapper;
	/***
	 * ��¼����
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.GET)
	@ResponseBody
	public YzlResult login(HttpServletRequest httpServletRequest, String username,String password){
		try {
			username = new String(username.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//ʹ��shiro����ṩ�ķ�ʽ������֤
		Subject subject = SecurityUtils.getSubject();//��ȡ��ǰ��¼�û���������״̬Ϊ��δ��֤
		//�û�����������
		System.out.println("username="+username);
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);//��֤
		} catch (UnknownAccountException e) {
			return  YzlResult.build(400, e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return YzlResult.build(400, e.getMessage());
		}catch (LockedAccountException e) {
			return YzlResult.build(400, e.getMessage());
		}
		YzlUser loginUser = (YzlUser) subject.getPrincipal();
		httpServletRequest.getSession().setAttribute(UserEnum.LOGIN_USER.getStatus(), loginUser);
		UUID uuid = UUID.randomUUID();
		return YzlResult.ok(uuid.toString());
	}
	/***
	 * �޸�����
	 */
	@RequestMapping(value="/change/password",method=RequestMethod.POST)
	@ResponseBody
    public YzlResult changePaswd(HttpServletRequest httpServletRequest, String oldps, String newps){
		//ȡ��ǰ�ѵ�¼�û�
		YzlUser user = (YzlUser) httpServletRequest.getSession().getAttribute(UserEnum.LOGIN_USER.getStatus());
		//�ж��û��Ƿ��¼
		if(user != null){
			//�ж������Ƿ���ȷ
			if(user.getPassword().equals(oldps)){
				//����������
				user.setPassword(newps);
				//ִ�и���
				int flag = userService.updatePassword(user);
				if(flag == 1){	
					//�Ѹ��ĺ���û���Ϣ���´���session
					httpServletRequest.getSession().setAttribute(UserEnum.LOGIN_USER.getStatus(), user);
					return YzlResult.ok();
				}
			}
		}
		return YzlResult.build(400, "�������");
	}
	/***
	 * �˳�ϵͳ
	 */
	@RequestMapping("/user/logout")
	@ResponseBody
	public YzlResult logout(HttpServletRequest httpServletRequest){
		httpServletRequest.getSession().invalidate();
		httpServletRequest.setAttribute(UserEnum.LOGIN_USER.getStatus(), "");
		return YzlResult.ok();
	}
	
	//��ҳ��ѯ
	@RequestMapping("/user/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int rows,int page){
		EasyUIResult result = userService.findAll(page,rows);
		return result;
	}
	
	//����û�
	@RequestMapping("/user/addUser")
	@ResponseBody
	public String add(YzlUser user){
		//���������MD5����
		user.setPassword(MD5Utils.md5(user.getPassword()));
		YzlResult result = userService.save(user);
		if(result.getStatus()==200){
			return "";
		}
		return "";
	}
	
	/***
	 * �޸��û�
	 * @return
	 */
	@RequestMapping("/user/updateUser")
	public String update(YzlUser user){
		YzlResult result = userService.update(user);
		return "user";
	}
	

}
