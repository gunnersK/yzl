package com.yzl.adminService;


import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface UserService {

	//���
	public YzlResult addUser(YzlUser user,String[] rolTd);

	//��ѯ����
	public EasyUIResult findAll(Integer page,Integer rows);
	
	//�����û�����ѯ
	YzlUser findByUsername(String username);

	//����ɾ��
	YzlResult deleteUser(String[] ids);
	
	//�޸�
	YzlResult updateUser(YzlUser user);

}
