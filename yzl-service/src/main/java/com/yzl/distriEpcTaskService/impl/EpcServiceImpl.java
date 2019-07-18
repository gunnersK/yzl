package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.distriEpcTaskService.EpcService;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlTaskEpcMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcExample;
import com.yzl.pojo.YzlEpcExample.Criteria;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlEpcTaskProgressExample;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskEpc;
import com.yzl.pojo.YzlTaskEpcExample;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;

@Transactional
@Service
public class EpcServiceImpl implements EpcService{

	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskEpcMapper taskEpcMapper;
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlMenuMapper menuMapper;
	
	//��ҳ��ѯ
	@Override
	public List<YzlEpc> findAll(String year, String areaCode) {
		List<YzlEpc> list= new  ArrayList<>();
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		if(!authoritys.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			list = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, areaCode, authoritys, null);
		}else{
			//���÷�ҳ��Ϣ
			YzlEpcExample example=new YzlEpcExample();
			list = epcMapper.selectByExample(example);
		}
		return list;
	}
	
	//��ҳ��ѯ
	@Override
	public EasyUIResult pageQuery(String value,Integer page,Integer rows) {
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		//���÷�ҳ��Ϣ
		PageHelper.startPage(page, rows);
		List<YzlEpc> list=null;
		if (value == null || value.equals("")) {
			YzlEpcExample example=new YzlEpcExample();
			list = epcMapper.selectByExample(example);
		}else {
			list = epcMapper.selectByCondition(value);
		}
		PageInfo<YzlEpc> pageInfo=new PageInfo<>(list);
		
		EasyUIResult result=new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	//�����û�����ѯ
	@Override
	public YzlEpc findByName(String name) {
		YzlEpcExample example=new YzlEpcExample();
		Criteria criteria = example.createCriteria();
		criteria.andEnameEqualTo(name);
		
		List<YzlEpc> list = epcMapper.selectByExample(example);
		
		if(list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	//���
	@Override
	public YzlResult addEpc(YzlEpc epc) {
		
//		int insert = epcMapper.insert(epc);
		int insert = epcMapper.insertSelective(epc);
		if(insert>0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//����ɾ������
	@Override
	public YzlResult deleterEpc(String[] ids) {
		
		List<YzlTaskEpc> list=null;
		
		for (String string : ids) {
			YzlTaskEpcExample example=new YzlTaskEpcExample();
			example.createCriteria().andEcodeEqualTo(Integer.valueOf(string));
			list = taskEpcMapper.selectByExample(example);
		}
		if (list!=null && list.size()>0) {
			return YzlResult.ok(300);
		}
		
		int sum=0;
		if (ids!=null && ids.length>0) {
			for (String string : ids) {
				
				YzlTaskEpcExample example=new YzlTaskEpcExample();
				example.createCriteria().andEcodeEqualTo(Integer.valueOf(string));
				taskEpcMapper.deleteByExample(example);
				
				
				
				epcMapper.deleteByPrimaryKey(Integer.valueOf(string));
				sum+=1;
			}
		}
		if (sum==ids.length) {
			return YzlResult.ok(200);
		}else {
			return YzlResult.ok(400);
		}
	}

//	//����year areaCode zll gclb��ѯ����
//	@Override
//	public List<YzlEpc> queryByYearAndAreaCodeAndZLLBAndGCLB(String year,String areaCode,String ZLLB,String GCLB) {
//		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
//		
//		//ͨ���·����������в�ѯ ����
//		if(!authoritys.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
//			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, areaCode, authoritys, ZLLB, GCLB);
//			return epcList;
//		}else{
//			//��ѯ����
//			YzlEpcExample epcExample = new YzlEpcExample();
//			Criteria epcCreateCriteria = epcExample.createCriteria();
//			if(StringUtils.isNotBlank(GCLB)){//���GCLB��Ϊ�� ��GCLB����
//				epcCreateCriteria.andMarkEqualTo(GCLB);
//			}
//			return epcMapper.selectByExample(epcExample);
//		}
//	}
	
	
	//���ݹ��̲�ѯ��Ӧ��������� �������ݴ�����ʾ��ҳ��
	private List<YzlTaskNumberEpcResult>  XbQueryEpcMappingTaskUtils(List<YzlEpc> epcs,String year,List<String> authoritys,String disCode,List<Integer> stats){
/*		YzlTaskEpcExample example = new YzlTaskEpcExample();
		//��ѯ����͹��̶�Զ�������
		List<YzlTaskEpc> taskEpcs = taskEpcMapper.selectByExample(example);*/
		List<YzlTaskNumberEpcResult> taskNumberEpcResults = new ArrayList<>();
		//��װ���й���
		List<Integer> taskNumber = new ArrayList<>();
		Iterator<YzlEpc> EpcIterator = epcs.iterator();
		//��ÿ�����̶�Ӧ�����������
		while (EpcIterator.hasNext()) {
			//��¼ÿ�������ж��ٸ�����
			YzlEpc epc = EpcIterator.next();
			Integer count = taskMapper.countyXbTaskNumberByGCLLAndYear(epc.getMark(), year,authoritys,disCode,stats);
			//�����ǰ��������Ϊ0�������
			if(count != 0 || count != null){
				//�ѵ�ǰ���̺�����������ӵ�������
				YzlTaskNumberEpcResult taskNumberEpcResult = new YzlTaskNumberEpcResult(epc,count);
				taskNumberEpcResults.add(taskNumberEpcResult);
			}else{
				//��û������Ĺ��̴Ӽ�����ɾ��
				EpcIterator.remove();
			}
		}
		return taskNumberEpcResults;
	}
	
	//���ݹ��̲�ѯ��Ӧ��������� �������ݴ�����ʾ��ҳ��
	private List<YzlTaskNumberEpcResult>  IssueQueryEpcMappingTaskUtils(String areaCode,List<YzlEpc> epcs,String year,List<String> authoritys){
/*		YzlTaskEpcExample example = new YzlTaskEpcExample();
		//��ѯ����͹��̶�Զ�������
		List<YzlTaskEpc> taskEpcs = taskEpcMapper.selectByExample(example);*/
		List<YzlTaskNumberEpcResult> taskNumberEpcResults = new ArrayList<>();
		//��װ���й���
		List<Integer> taskNumber = new ArrayList<>();
		Iterator<YzlEpc> EpcIterator = epcs.iterator();
		//��ÿ�����̶�Ӧ�����������
		while (EpcIterator.hasNext()) {
			//��¼ÿ�������ж��ٸ�����
			YzlEpc epc = EpcIterator.next();
			Integer count = taskMapper.countyIssueTaskNumberByGCLLAndYear(areaCode,epc.getMark(), year,authoritys);
			//�����ǰ��������Ϊ0�������
			if(count != 0 || count != null){
				//�ѵ�ǰ���̺�����������ӵ�������
				YzlTaskNumberEpcResult taskNumberEpcResult = new YzlTaskNumberEpcResult(epc,count);
				taskNumberEpcResults.add(taskNumberEpcResult);
			}else{
				//��û������Ĺ��̴Ӽ�����ɾ��
				EpcIterator.remove();
			}
		}
		return taskNumberEpcResults;
	}
	/*ͨ�������·��������� ��ѯ����*/
	@Override
	public List<YzlTaskNumberEpcResult> queryAllAndNumber(String year,String areaCode){
		//ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		List<YzlEpc> epcs = epcMapper.queryEpcByTaskProgressData(year,areaCode,authoritys);
		return this.IssueQueryEpcMappingTaskUtils(areaCode,epcs,year,authoritys);

	}
	
	//ͨ��С����ɵ������в�ѯ����
	@Override
	public List<YzlTaskNumberEpcResult> queryByXbData(String year,String disCode){
		
		List<Integer> stats = new ArrayList<>();
		
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		
		stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
		stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
		
		List<YzlEpc> epcs = epcMapper.queryEpcByXbTaskWoring(year,disCode,stats,authoritys);
		return this.XbQueryEpcMappingTaskUtils(epcs,year,authoritys,disCode,stats);
	}

	@Override
	public List<YzlEpc> show() {
		YzlEpcExample example=new YzlEpcExample();
		List<YzlEpc> list = epcMapper.selectByExample(example);
		return list;
	}




}
