package com.yzl.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yzl.planManagementController.CompletionTaskController;
import com.yzl.planManagementController.taskWorkingController;
import com.yzl.pojo.YzlUser;

///������ 
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	//��¼��������
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//ȡ�û���¼��Ϣ
		YzlUser user = (YzlUser) request.getSession().getAttribute("user");
		if(user==null){
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		return true;
	}

}
//package com.yzl.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import com.yzl.pojo.YzlUser;
//
////
//public class LoginInterceptor implements HandlerInterceptor {
//
//	//��¼��������
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
//		
//		//ȡ�û���¼��Ϣ
//		YzlUser user = (YzlUser) request.getSession().getAttribute("user");
//		String url = request.getRequestURI();//��ȡ�����·��
//		System.out.println("�����·��"+url);
//		
//		if (user == null || user.equals("")) {// && !url.equals("/user/login")
//			if (request.getHeader("x-requested-with")!=null) {//�ж��Ƿ�Ϊajax����// ��ajax����� ajax������һ��x-requested-with���ԣ�������x-requested-with������Ϊajax����
//				response.setHeader("sessionStatus", "timeout");//����responseͷ��ǰ̨�������ж��Ƿ�session����
//				String contextPath = request.getSession().getServletContext().getContextPath();
//				System.out.println("--------------"+contextPath);
//				response.sendRedirect(request.getSession().getServletContext().getContextPath());
//				return false;
//			}
//		}
//		return true;
//	}
//		
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//}
