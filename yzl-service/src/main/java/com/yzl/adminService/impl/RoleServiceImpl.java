package com.yzl.adminService.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.RoleService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlRoleMapper;
import com.yzl.mapper.YzlRoleMenuMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlRoleExample;
import com.yzl.pojo.YzlRoleExample.Criteria;
import com.yzl.pojo.YzlRoleMenu;
import com.yzl.pojo.YzlRoleMenuExample;
import com.yzl.pojo.YzlUserRoleExample;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.TreeVO;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	YzlRoleMapper roleMapper;
	
	@Autowired
	YzlRoleMenuMapper roleMenuMapper;
	
	@Autowired
	YzlMenuMapper menuMapper;
	@Autowired
	YzlUserRoleMapper userRoleMapper;
	@Autowired 
	YzlDistrictMapper districtMapper;
	
	
	@Override
	public void add(YzlRole role, String functionIds) {
		String[] fids = functionIds.split(",");
		//���ɹؼ���
		role.setCode(UUID.randomUUID().toString());
		
		role.setCreateTine(new Date());
		//�����ɫ����������
		roleMapper.insert(role);
		if(functionIds!=null && functionIds != ""){
			//��ȡ����Ȩ��id
			for (String fid : fids) {
				//���б�֮�����
				YzlRoleMenu roleMenuKey = new YzlRoleMenu();
				roleMenuKey.setMenuId(Integer.valueOf(fid));
				roleMenuKey.setRoleId(role.getId());
				roleMenuMapper.insert(roleMenuKey);
			}
		}
	}
	@Override
	public EasyUIResult pageQuery(int page, int rows) {
		//���÷�ҳ����
		PageHelper.startPage(page, rows);
		YzlRoleExample example = new YzlRoleExample();
		Criteria criteria = example.createCriteria();
		//ִ�в�ѯ
		List<YzlRole> list = roleMapper.selectByExample(example);
		PageInfo<YzlRole> pageInfo = new PageInfo<>(list);
		//��װ���ؽ����
		EasyUIResult result = new EasyUIResult();
		result.setTotal((int)pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	
	//ɾ��
	@Override
	public void delete(String ids) {
		String[] rid = ids.split(",");
		//�������� ��ȡ����Id
		for (String sid : rid) {
			int id = Integer.valueOf(sid);
			//����Idѭ��ɾ��
			roleMapper.deleteByPrimaryKey(id);
			//ɾ������Ȩ�ޱ�
			YzlRoleMenuExample example = new YzlRoleMenuExample();
			com.yzl.pojo.YzlRoleMenuExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andRoleIdEqualTo(id);
			List<YzlRoleMenu> list = roleMenuMapper.selectByExample(example);
			//ִ��ɾ��
			roleMenuMapper.deleteByExample(example);
			
			//ɾ����ɫ�����û���
			YzlUserRoleExample uRExample = new YzlUserRoleExample();
			com.yzl.pojo.YzlUserRoleExample.Criteria uRCreateCriteria = uRExample.createCriteria();
			uRCreateCriteria.andRoleIdEqualTo(id);
			//ִ��ɾ��
			userRoleMapper.deleteByExample(uRExample);
			
			//ɾ����ɫ����Ȩ�ޱ�
			YzlRoleMenuExample roleMenuExample = new YzlRoleMenuExample();
			com.yzl.pojo.YzlRoleMenuExample.Criteria roleMenuCriteria = roleMenuExample.createCriteria();
			roleMenuCriteria.andRoleIdEqualTo(id);
			//ִ��ɾ��
			roleMenuMapper.deleteByExample(roleMenuExample);
		}
	}

	
	/***
	 * ���ݽ�ɫid��ѯȨ��
	 */
	@Override
	public YzlResult getFunctionByRoleId(long roleId) {
		List<TreeVO> list = null;
		try {
			list = menuMapper.findFunctionByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "���ݼ���ʧ�ܣ�");
		}
		return YzlResult.ok(list);
	}
	
	
	@Override
	public List<YzlRole>  findAll() {
		YzlRoleExample example=new YzlRoleExample();
		Criteria criteria = example.createCriteria();
		//��ѯ���еĽ�ɫ�����������û�
		List<YzlRole> list = roleMapper.selectByExample(example);
		return list;
	}
	
	
	@Override
	public YzlResult update(YzlRole role, String functionIds) {
		System.out.println("cs"+functionIds);
		System.out.println("c"+role);
		//�޸Ľ�ɫ
		roleMapper.updateByPrimaryKey(role);
		//�Ѿ�Ȩ��ȫ��ɾ��
		YzlRoleMenuExample example = new YzlRoleMenuExample();
		com.yzl.pojo.YzlRoleMenuExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andRoleIdEqualTo(role.getId());
		roleMenuMapper.deleteByExample(example);
		//��Ȩ�޽��й���
		if(functionIds != null && functionIds != ""){
			String[] fids = functionIds.split(",");
			for (String fid : fids) {
				YzlRoleMenu roleMenuKey = new YzlRoleMenu();
				roleMenuKey.setRoleId(role.getId());
				roleMenuKey.setMenuId(Integer.valueOf(fid));
				roleMenuMapper.insert(roleMenuKey);
			}
		}
		return null;
	}
	
	@Override
	public YzlRole findByRoleId(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

}
