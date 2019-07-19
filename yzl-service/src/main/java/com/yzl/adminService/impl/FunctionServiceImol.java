package com.yzl.adminService.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.FunctionService;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMenuExample;
import com.yzl.pojo.YzlMenuExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.TreeVO;


@Service
@Transactional
public class FunctionServiceImol implements FunctionService {

	@Autowired
	YzlMenuMapper menuMapper;
	
	/**
	 * �ݹ��ѯ���в˵�
	 */
	@Override
	public List<TreeVO> getMenu() {
		//��ѯ���в˵�����JSON��ʽ����
		List<TreeVO> list = menuMapper.findAllRecursion();
		return list;
	}

	/***
	 * ���
	 */
	@Override
	public boolean save(YzlMenu menu) {
		boolean flag=true;
		try {
			menuMapper.insert(menu);
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	//��ҳ��ѯ
	@Override
	public EasyUIResult getFunctions(int page, int rows) {
		//���ò�ѯ����
		PageHelper.startPage(page, rows);
		YzlMenuExample example = new YzlMenuExample();
		Criteria criteria = example.createCriteria();
		List<YzlMenu> list = menuMapper.selectByExample(example);
		
		PageInfo<YzlMenu> pageInfo = new PageInfo<>(list);
		//��װ��ѯ�����pojo
		EasyUIResult result = new EasyUIResult();
		//��װ�ܼ�¼��
		result.setTotal((int)pageInfo.getTotal());
		//��װ����
		result.setRows(pageInfo.getList());
		return result;
	}
	
	//����ɾ��
	@Override
	public void delete(String ids) {
		String[] idList = ids.split(",");
		for (String id : idList) {
			this.RecurveDelete(Long.valueOf(id));
		}
	}

	
	//�ݹ�ɾ��
	public void RecurveDelete(long id){
		//��ѯ��Ȩ���Ƿ��Ǹ��ڵ�
		YzlMenu menu = menuMapper.selectByPrimaryKey(id);
		//����id��ѯ�Ƿ����ӽڵ�
		YzlMenuExample example = new YzlMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(menu.getMenuId().toString());
		List<YzlMenu> list = menuMapper.selectByExample(example);
		//���Ϊ�վ���û�и��ӽڵ�
		if(list==null || list.size()==0){
			//ִ��ɾ��
			menuMapper.deleteByPrimaryKey(menu.getMenuId());
		}else{
			//����Ǹ��ڵ㣬�������ѯ
			for (YzlMenu yzlMenu : list) {
				this.RecurveDelete(yzlMenu.getMenuId());
			}
			//�˳�ѭ�� ɾ�����ڵ�
			menuMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void update(YzlMenu menu) {
		menuMapper.updateByPrimaryKey(menu);
	}

	@Override
	public List<YzlMenu> findAllMenu() {
		YzlMenuExample example=new YzlMenuExample();
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<YzlMenu> findFunctionByRoleId(Long valueOf) {
		
		return menuMapper.findMenuByRoleId(valueOf);
	}

	public List<YzlMenu> findAll(){
		
		//��ѯpidΪnull�ľ������˵�
		List<YzlMenu> list = menuMapper.findAll();
		if (list != null && list.size() > 0) {
			for (YzlMenu yzlMenu : list) {
				dg(yzlMenu);
			}
		}
		return list;
	}
	
	//�ݹ��ѯ
	private void dg(YzlMenu yzlMenu) {
		List<YzlMenu> chile = menuMapper.findByPid(yzlMenu.getMenuId());
		for (YzlMenu yzlMenu2 : chile) {
			//ע�͵ݹ�
			dg(yzlMenu2);
		}
		yzlMenu.setChildren(chile);
	}
	
	@Override
	public List<YzlMenu> getMenus() {
		
		Session session = SecurityUtils.getSubject().getSession();
		YzlUser user  = (YzlUser) session.getAttribute("user");
		
		Integer id = Integer.valueOf(String.valueOf(user.getId()));
		
		if (id.equals(35)) {
			//��ȡ����Ȩ��
			
			return findAlls();
		}
		//�����û���ȡ��Ӧ��Ȩ��
//		List<YzlMenu> list = menuMapper.selectByUserId(id);
		
		//װ���ڵ�ļ���
		List<YzlMenu> menus2 = menuMapper.selectByUserIdHost(id);
		
		//װ�ӽڵ�ļ���
		List<YzlMenu> menus = menuMapper.selectByUserIdSon(id);
				
/*		if (list.size()>0) {
			for (YzlMenu yzlMenu : list) {
				//���pidΪ��˵�������ڵ�
				String pid = yzlMenu.getPid();
				//System.out.println(pid);
				if("".equals(yzlMenu.getPid()) || yzlMenu.getPid() == null) {
					menus2.add(yzlMenu);
				}else {
					menus.add(yzlMenu);
				}
			}
		}*/
		if (menus2.size()>0) {
			for (YzlMenu yzlMenu : menus2) {//�������ڵ�
				if (menus.size()>0) {
					List<YzlMenu> mList=new ArrayList<>();
					for (YzlMenu yzlMenus : menus) {//�����ӽڵ�
						Long menuId = yzlMenu.getMenuId();//���ڵ�id
						String pid = yzlMenus.getPid();//�ӽڵ�pid
						if (String.valueOf(menuId).equals(pid)) {
							mList.add(yzlMenus);
						}
					}
					yzlMenu.setChildren(mList);
				}
			}
		}
		for(int i=0;i<menus2.size();i++) {
			
			YzlMenu yzlMenu = menus2.get(i);
			int count = 0;
			if ("Ӫ���ָ�������".equals(yzlMenu.getName())) {
				List<YzlMenu> list = yzlMenu.getChildren();
				for (YzlMenu yzlMenu2 : list) {
					String generatemenu = yzlMenu2.getGeneratemenu();
					if ("1".equals(generatemenu)) {
						count++;
						break;
					}
				}
				if (count==0) {
					menus2.remove(i);
				}
			}
			
		}
		
		return menus2;
	}
	
	public List<YzlMenu> findAlls(){
		//��ѯpidΪnull�ľ������˵�
		List<YzlMenu> list = menuMapper.findAll();
		if (list != null && list.size() > 0) {
			for (YzlMenu yzlMenu : list) {
				List<YzlMenu> chile = menuMapper.findByPid(yzlMenu.getMenuId());
				yzlMenu.setChildren(chile);
			}
		}
		return list;
	}
	
}
