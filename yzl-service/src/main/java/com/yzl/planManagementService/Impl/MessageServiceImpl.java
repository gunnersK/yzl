package com.yzl.planManagementService.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;


@Service

public class MessageServiceImpl implements MessageService{

	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlUserRoleMapper userRoleMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	
	
	
	


	/**
	 * �����Ѷ�״̬
	 */
	@Override
	public YzlResult updateStatuByUserId() {
		//��ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		System.err.println("user="+loginUser);
		//�����û�Id��ѯ����Ȩ��
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		int result = messageMapper.updateStatuByUserIdAndCountCode(loginUser.getKeywork(), authoritys);
		System.err.println(loginUser.getKeywork());
		if(!(result>0)){
			return YzlResult.build(500, "��Ϣ����ʧ��");
		}
		return YzlResult.ok();
	}



	/***
	 * �����û�id ͳ�Ƽ�¼��
	 */
	@Override
	public int getRecordsByUserId(HttpServletRequest request) {
		//��ȡ��ǰ��¼�û�
//		YzlUser loginUser = LoginUserUtils.getLoginUser();
//		getLoginUser
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		 String keywork = loginUser.getKeywork();
		//�����û�Id��ѯ����Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		int messageCount = 0;
		try {
			messageCount = messageMapper.countMessageNumber(PermsList,keywork);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("updateDataNumber", messageCount);
		return messageCount;
	}

}
