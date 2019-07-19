package com.yzl.adminService.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.IUserService;
import com.yzl.mapper.YzlUserMapper;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserExample;
import com.yzl.pojo.YzlUserExample.Criteria;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.PageBean;
import com.yzl.utils.YzlResult;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	YzlUserMapper userMapper;

	
	//��¼�ж�
	@Override
	public YzlUser findByUsername(String username) {
		//����userName��ѯ�û�
		YzlUserExample example = new YzlUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<YzlUser> list = userMapper.selectByExample(example);
		//�ж��Ƿ��ѯ������
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}
	
	
	//�û��޸�����
	@Override
	public int updatePassword(YzlUser user){
		return userMapper.updateByPrimaryKey(user);
	}

	//��ҳ��ѯ
	@Override
	public EasyUIResult findAll(int page,int rows) {
		YzlUserExample example = new YzlUserExample();
		Criteria criteria = example.createCriteria();
		//��ҳ��ѯ
		int total = userMapper.countByExample(example);
		Integer start = (page-1)*rows;
	//	List<YzlUser> list = userMapper.findAll(start,Integer.valueOf(rows));	
		//System.out.println("��ʼҳ="+start+rows);
//		List<YzlUser> list = userMapper.findAll(start,Integer.valueOf(rows));
		//��װpojo
		EasyUIResult result = new EasyUIResult();
		//result.setRows(list);
		result.setTotal(total);
		return result;
	}


	@Override
	public YzlResult save(YzlUser user) {
		try {
			//���ù�����ɫ��ϵ
			//user.setRoleId(user.getYzlRole().getId());
			//����״̬��Ĭ�ϵ�һ�����Ϊ����
			//user.setState("1");//1Ϊ������0Ϊ����
			user.setStatus("1");//1Ϊ������0Ϊ����
			user.setKeywork(UUID.randomUUID().toString());
			user.setStatus("1");//1Ϊ������0Ϊ����
			userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "���ݲ���ʧ��");
		}
		return YzlResult.ok();
	}


	@Override
	public YzlResult update(YzlUser user) {
		try {
			//user.setRoleId(user.getYzlRole().getId());
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "�����޸�ʧ��");
		}
		return YzlResult.ok();
	}


}
