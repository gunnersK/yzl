package com.yzl.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.MessageBodyWriter;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yzl.pojo.YzlUser;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.UserEnum;
import com.yzl.utils.exception.YzlException;



public class LoginUserUtils {
	
	private static Logger logger = Logger.getLogger(LoginUserUtils.class);
	//��ȡ��ǰ��¼�û�
	public static YzlUser getLoginSession(HttpServletRequest request){
		 YzlUser user = (YzlUser) request.getSession().getAttribute(UserEnum.LOGIN_USER.getStatus());
		 if(user==null){
			 throw new YzlException(ResultEnum.LOGIN_USER_NOT_EXIST);
		 }
		 
		 return user;
	}

	//��ȡ��ǰ��¼�û�
	public static YzlUser getLoginUser(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		YzlUser user = (YzlUser) session.getAttribute(UserEnum.LOGIN_USER.getStatus());
		 if(user==null){
			 logger.error("û�л�ȡ����¼�û�");
			 throw new YzlException(ResultEnum.LOGIN_USER_NOT_EXIST);
		 }
		return user;
	}

	
	
	
	
	
	
	
}
