package com.yzl.adminService;

import java.util.List;

import com.yzl.pojo.YzlUserRole;


public interface UserRoleService {

	//����uid��ѯ
	List<YzlUserRole> findByUid(String uid);

}
