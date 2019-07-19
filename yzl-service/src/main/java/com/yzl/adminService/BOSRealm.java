package com.yzl.adminService;

import java.util.Collection;
import java.util.List;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlUserMapper;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlUser;

public class BOSRealm  extends AuthorizingRealm {
	
	
	@Autowired
	YzlUserMapper userMapper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
//	@Autowired
//	private YzlUserRoleMapper userRoleMapper;
	@Autowired
	YzlMenuMapper menuMapper;
	
//	@Autowired
//	private SessionDAO sessionDao;
	
	/*��ȡ��Ȩ��Ϣ*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//����ǰ�û���Ȩ��Ȩ��
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		YzlUser user =(YzlUser) principalCollection.getPrimaryPrincipal();
		//�ж��Ƿ���ϵͳ����Ա
		if (user.getUsername().equals("mark")) {
			//��ѯ���н�ɫ
			List<YzlRole> list = roleService.findAll();
			for (YzlRole yzlRole : list) {
				authorizationInfo.addRole(String.valueOf(yzlRole.getCode()));
			}
			//���еĹ���Ȩ��
			List<YzlMenu> menu = functionService.findAllMenu();
			for (YzlMenu yzlMenu : menu) {
				if (StringUtils.isNotBlank(yzlMenu.getPerms())) {
					authorizationInfo.addStringPermission(yzlMenu.getPerms());
				}
			}
		}else {
			//�������ͨ�û�
			//��ѯ���û�ӵ�еĽ�ɫid
//			Integer uid=Integer.valueOf(String.valueOf(user.getId())); 
			Long id = user.getId();
			List<String> list = menuMapper.selectByUserIdPerms(id.toString());
			for (String perms : list) {
				if (StringUtils.isNotBlank(perms)) {
					authorizationInfo.addStringPermission(perms);
				}
			}
			/*YzlUserRoleExample example=new YzlUserRoleExample();
			example.createCriteria().andUserIdEqualTo(uid);
			List<YzlUserRole> list = userRoleMapper.selectByExample(example);
			
			List<YzlRole> roles = new ArrayList<YzlRole>();
			for (YzlUserRole yzlUserRole : list) {
				Integer roleId = yzlUserRole.getRoleId();
				YzlRole role = roleService.findByRoleId(roleId);//���ݽ�ɫid��ѯ
				roles.add(role);
			}*/
			//�����û���ѯ��ɫ
			/*if(roles != null && roles.size()>0) {
				for (YzlRole yzlRole : roles) {
					String code = yzlRole.getCode();
					authorizationInfo.addRole(String.valueOf(yzlRole.getCode()));
					//��ȡĳ����ɫӵ�еĹ���Ȩ��
					Integer id = yzlRole.getId();
					List<YzlMenu> menus = functionService.findFunctionByRoleId(Long.valueOf(id));//���ݽ�ɫid��ѯ
					for (YzlMenu yzlMenu : menus) {
						if (StringUtils.isNotBlank(yzlMenu.getPerms())) {
							authorizationInfo.addStringPermission(yzlMenu.getPerms());
						}
					}
				}
			}*/
			
		}
		return authorizationInfo;
	}

	
	/**
	 * ��֤�û����
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//System.out.println("realm�е���֤����ִ���ˣ�");
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		Session session = SecurityUtils.getSubject().getSession();
		
		String password = new String((char[]) token.getCredentials());
		
		String username = myToken.getUsername();
//		Object principal = myToken.getPrincipal();
		//�����û�����ѯ�û�
		YzlUser user = userMapper.selectByUsername(username);
//		YzlUserExample example = new YzlUserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andUsernameEqualTo(username);
		
//		List<YzlUser> list = userMapper.selectByExample(example);
		//System.out.println(list.size());
		if(user == null ){
			//�û���������
			throw new UnknownAccountException("�˺Ż����벻����");
		}
		
		//�������
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("�˺Ż����벻��ȷ");
		}
		
		if(user.getStatus().equals("����")) {
			throw new LockedAccountException("�˺��ѱ�����,����ϵ����Ա");
		}
		
		//��ȡ���ߵ�sessio
//		Collection<Session> collections = sessionDao.getActiveSessions();
//		for (Session sessions : collections) {
//			System.out.println("��¼���û�="+sessions.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
//			if (sessions.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
//				sessions.setTimeout(0);
//				//Subject subject = new Subject().Builder().session(session).buildSubject();
//				break;
//			}
//		}
		
//		YzlUser user = list.get(0);
//		Long id = user.getId();
//		String string = user.getUsername();
		
		session.setTimeout(7200000);//���ù���ʱ��
//		session.setTimeout(120000);
		session.setAttribute("user", user);
		
//		session.setAttribute(id+string, id+string);
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		YzlUser object = (YzlUser) session.getAttribute("user");
		return info;
	}
}
