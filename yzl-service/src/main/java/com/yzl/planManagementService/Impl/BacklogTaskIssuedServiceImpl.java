package com.yzl.planManagementService.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.BacklogTaskIssuedService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.BacklogVO;

@Service
public class BacklogTaskIssuedServiceImpl implements BacklogTaskIssuedService{
	
	//��־��
	private static Logger logger = Logger.getLogger(BacklogTaskIssuedServiceImpl.class);
	
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlTaskMapper taskMapper;	
	@Autowired
	private YzlEpcMapper epcMapper;	
	@Autowired
	private YzlDistrictMapper districtMapper;


	@Override
	public EasyUIResult queryItems(int page, int rows) {
		
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		
		List<BacklogVO> backlogVOList = new ArrayList<>();
	
		backlogVOList = epcTaskProgressMapper.queryBacklogList(PermsList);
		
		for(BacklogVO backlog : backlogVOList){
			if(backlog.getStat().equals("1")){
				backlog.setName("<p style='color:blue'>����˵�����</p>");
			}
			if(backlog.getStat().equals("3")){
				backlog.setName("<p style='color:red'>���˻ص�����</p>");
			}
			System.out.println("======backlog====="+backlog.toString());
		}
		EasyUIResult easyUIResult = new EasyUIResult();
		//�����ҳ��ʼλ��
		int beginIndex = (page-1)*rows;
		//�����ҳ����λ��
		int lastIndex = beginIndex+rows;
		//��������ܼ�¼�� �� ��ҳ�������Ϊ �ܼ�¼��
		if(lastIndex>backlogVOList.size()) {
			lastIndex=backlogVOList.size();
		}
		if(rows>backlogVOList.size()){
			easyUIResult.setRows(backlogVOList);
		}else{
			//��ȡҳ��Ҫ��ʾ������
			List<BacklogVO> subList = backlogVOList.subList(beginIndex, lastIndex);
			easyUIResult.setRows(subList);
		}
		easyUIResult.setTotal(backlogVOList.size());
		//ͳ�ƴ������
		return easyUIResult;
	}
	
	@Override
	public EasyUIResult pageQuery(int page, int rows) {
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		 String keywork = loginUser.getKeywork();
		//�����û�Id��ѯ����Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<BacklogVO> backlogVOList = new ArrayList<>();
		//ͳ�������·�������
		//int messageCount = messageMapper.countMessageNumber(PermsList,keywork);
		List<BacklogVO> IssuedTaskbacklogVOList = messageMapper.countMessageNumberGroupByCountyCode(PermsList, keywork);
		for (BacklogVO IssuedTaskbacklogVO : IssuedTaskbacklogVOList) {
			IssuedTaskbacklogVO.setName("�Ѹ��µ�����");
			
			IssuedTaskbacklogVO.setCheck("<a  class='checkTaskissed' id='taskissued'  value='&backlogTaskIssued.jsp&' href='#'>����鿴</a>");
			backlogVOList.add(IssuedTaskbacklogVO);
		}
		List<Integer> stats = new ArrayList<>();
		if(PermsList.contains(AuthorityEnum.TASK_AUDIT.getPerms())){//�ж��Ƿ�������Ȩ��
			stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());//����������ѯ����˵�����
		}
		stats.add(TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode());
		List<BacklogVO> xBacklogVOList = xbMapper.queryCountyAndCityAndUpdateTimeAndStat(stats,PermsList);
		String TaskCityAudit = TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode().toString();//�����״̬
		for (BacklogVO xbBacklogVO : xBacklogVOList) {
			xbBacklogVO.setNumber(1);
			/*		xbBacklogVO.setCity(city);
					xbBacklogVO.setCounty(county);*/
			if(TaskCityAudit.equals(xbBacklogVO.getStat())){//�ж��Ƿ��Ǵ����״̬
				xbBacklogVO.setName("����˵�����");
				xbBacklogVO.setCheck("<a class='checkTaskissed' id='" + TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode() +">"+ "' value='&backlogTaskWorking.jsp&' href='#'>����鿴</a>");
			}else{
				xbBacklogVO.setName("���˻ص�����");
				xbBacklogVO.setCheck("<a class='checkTaskissed' id='" + TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode() +">"+ "' value='&backlogTaskWorking.jsp&' href='#'>����鿴</a>");
			}
			backlogVOList.add(xbBacklogVO);
		}
		//��װҳ����ʾ��¼���ܼ�¼��
		EasyUIResult easyUIResult = new EasyUIResult();
		//�����ҳ��ʼλ��
		int beginIndex = (page-1)*rows;
		//�����ҳ����λ��
		int lastIndex = beginIndex+rows;
		//��������ܼ�¼�� �� ��ҳ�������Ϊ �ܼ�¼��
		if(lastIndex>backlogVOList.size()) {
			lastIndex=backlogVOList.size();
		}
		if(rows>backlogVOList.size()){
			easyUIResult.setRows(backlogVOList);
		}else{
			//��ȡҳ��Ҫ��ʾ������
			List<BacklogVO> subList = backlogVOList.subList(beginIndex, lastIndex);
			easyUIResult.setRows(subList);
		}
		easyUIResult.setTotal(backlogVOList.size());
		//ͳ�ƴ������
		return easyUIResult;
	}

	
	@Override
	public List<YzlTask> getTableHeader(String year, String disCode, String GCLB,String ZLLB) {
		List<YzlTask> resultTaskList = new ArrayList<>();//���ؽ����taskList
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		String keywork = loginUser.getKeywork();
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);

		List<YzlTask> taskList= new ArrayList<>();
		List<String> flagTaskList= new ArrayList<>();//�洢zllb��mark�����ж�mark�Ƿ�����ӹ�
		//����Ȩ�� ����������ţ���ݲ�ѯ �����·��е� task
		System.out.println("disCode="+disCode);
		int i=0;
		if(StringUtils.isBlank(ZLLB)){
			for (YzlMessage message : messagesList) {
				List<YzlTask> queryTaskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(year,disCode, authoritys, GCLB,message);
				for (YzlTask queryTask : queryTaskList) {
					if(!flagTaskList.contains(queryTask.getMark())){
						flagTaskList.add(queryTask.getMark());
						taskList.add(queryTask);
					}
				}
			}
		}else{
			YzlTaskExample taskExample = new YzlTaskExample();
			Criteria criteria = taskExample.createCriteria();
			criteria.andMarkEqualTo(ZLLB);
			taskList = taskMapper.selectByExample(taskExample);
		}
		for (YzlTask task : taskList) {
			String zllb = task.getMark();//��ȡ�������
			//����Ȩ�� ����������ţ���ݣ�������� ��ѯ�����·��е� epc
			List<YzlEpc> epcList = new ArrayList<>();
			List<String> flagEpcList = new ArrayList<>();
			for (YzlMessage message : messagesList) {
				List<YzlEpc> queryEpcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(year, disCode, authoritys, zllb,GCLB,message);
				for (YzlEpc queryEpc : queryEpcList) {
					if(!flagEpcList.contains(queryEpc.getMark())){
						flagEpcList.add(queryEpc.getMark());
						epcList.add(queryEpc);
					}
				}
			}
			List<YzlEpc> resultEpcList = new ArrayList<>();//���ؽ�����ӽڵ�epcList
			for (YzlEpc epc : epcList) {
				epc.setField(zllb+"T"+epc.getMark());
				resultEpcList.add(epc);
			}
			task.setList(resultEpcList);//�ѹ���list��װ��task��
			resultTaskList.add(task);//�������װ�����ص�������
		}
		return resultTaskList;
	}

	
	@Override
	public EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String ZLLB, String GCLB) {
		
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode());
		if(StringUtils.isBlank(year)){//�����ݵ��� �� ���ȡ��ǰʱ������
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//�������Ϊ�� ���׳��쳣
			logger.error("��Ҫ��ѯ�ĵ����������Ϊ��-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.AREA_CODE_ISNULL);
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		String keywork = loginUser.getKeywork();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		//��ȡ��ǰ��¼�û�������Ȩ��
		List<Map<String,String>> resultList = new ArrayList<>();
		if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//�����Ⱦ��ǵ�ǰѡ�����������
			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB); //��ѯ��ͳ���м�����
		}else{
			//��ѯ��ͳ���ؼ�����
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB);
		}
		//dataTreating(epcTaskProgressList, areaCode, authoritys, year);
		//��װҳ����ʾ��¼���ܼ�¼��
		EasyUIResult easyUIResult = new EasyUIResult();
		//�����ҳ��ʼλ��
		Integer beginIndex = (page-1)*rows;
		//�����ҳ����λ��
		Integer lastIndex = beginIndex+rows;
		//��������ܼ�¼�� �� ��ҳ�������Ϊ �ܼ�¼��
		if(lastIndex>resultList.size()){
			lastIndex=resultList.size();
		}
		List<Map<String,String>> result = new ArrayList();
		//��ȡҳ����Ҫ��ʾ����
		if(resultList.size()<rows){
			result=resultList;
		}else{
			result = resultList.subList(beginIndex, lastIndex);
		}
		easyUIResult.setRows(result);
		easyUIResult.setTotal(resultList.size());
		return easyUIResult;
	}

	//��ѯ�м����ݲ�����ͳ��
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���м�����
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);
		List<YzlEpcTaskProgress> epcTaskProgressList = new ArrayList<>();
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = new ArrayList<>();
		for (YzlMessage message : messagesList) {
			List<YzlEpcTaskProgress> epcList = epcTaskProgressMapper.queryCityDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, areaCode, authoritys,ZLLB,GCLB,message);
			List<YzlEpcAndTaskStaticti> epcAndTaskList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, stats, authoritys, areaCode, ZLLB, GCLB, message);
			epcTaskProgressList.addAll(epcList);
			epcAndTaskStatictiList.addAll(epcAndTaskList);
		}
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//���б��
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//������ظ����б����ӽ�ȥ
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//��ѯ������ɵ����� ��ͳ���м�����
		try {
			for (String string : hashSet) {//���������б��
				for (int i = 0; i < epcTaskProgressList.size(); i++) {
					HashMap map = new HashMap<>();
					YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
					if (string.equals(epcTaskProgressList.get(i).getCitycode())) {//������б�����
						List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
						HashMap countDataMap = new HashMap<>();
						YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//������������Ų�ѯ����
						map.put("city", district.getCity());
						int log=0;//��¼��ǰ�����Ƿ����������� ����ƥ���
						for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
							YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
							//�ѹ��� ��������ź��������͹��������ͬ�Ľ��кϲ�
							if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //�ж��м���������Ƿ����
									&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //�ж���������Ƿ����
										&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//�жϹ�������Ƿ����
								log++;//��ƥ���  ������
								//��ȡ��������ɵ�����
								Double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
								double zjh =XTJSBMJDouble/IepcTaskProgress.getTaskprogress().doubleValue();
								// ������������ݳ��������������õ�  ������ɱ����Ľ��   �ý������ȡ������С����2λ
								map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
								map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
							}
						}
						if(log==0){//log Ϊ0 ���ʾû���������е�����������ƥ�䣬����0����
							map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
							map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
						}
						map.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������			
						//��װ�м������·������� ������С�����4λ
						map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					}
					
					List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
					String filesUrl = IepcTaskProgress.getFilesUrl();//���ļ�·�����в��
					Set<String> FilesUrlSet= new HashSet();//�洢�ļ�·�� ��ȥ�ظ�
					
					if(!StringUtils.isBlank(filesUrl)){	 
						String[] filesUrlArray = filesUrl.split(",");
						for (String fileUrl : filesUrlArray) {
							if(StringUtils.isBlank(fileUrl)){//�ж��Ƿ�Ϊ���ļ���
								continue;
							}
							FilesUrlSet.add(fileUrl);
						}
					}
					map.put("fileName", FilesUrlSet);
					map.put("countyCode", IepcTaskProgress.getCountycode());////��װ�ؼ��������
					map.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������			
					//��װ�м������·������� ������С�����4λ
					map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					//������ͳ�ƺ÷�װ��List������
					countDataList.add(map);
//					taskIssuedDTOs.add(map);
					record++;
				}
				int filesNumber = 0;
			}
			
			List<String> messageCountyCodeList= messageMapper.queryCountyCodeByStatuAndCountyCode(authoritys, keywork);//��ѯ���¼�¼��
			List<Map> AddedMap = new ArrayList<>();//�洢 �Ѿ��ϲ�����Map  �ж��Ƿ��ظ����
			for (int m=0;m<countDataList.size();m++){
				Map mMap = countDataList.get(m);
				String cityCode = (String) mMap.get("cityCode");
				if(StringUtils.isBlank(cityCode)){
					logger.error("�м��������Ϊ��");
					throw new YzlException(ResultEnum.CITY_IS_NULL);
				}
				Set<String> mMapFilesSet = (Set<String>) mMap.get("fileName");//��ȡmMap���ļ�����
				if(!AddedMap.contains(mMap)){
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						Set<String> nMapFilesSet = (Set<String>) nMap.get("fileName");//��ȡ��ǰnMap���ļ�����
						if(cityCode.equals(nMap.get("cityCode"))){
							mMap.putAll(nMap); //ͬһ����  �����ݽ��кϲ�
							//nMap.put("filesUrl", "<a src='' ����鿴("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//�ۼ� �ļ�����
							mMapFilesSet.addAll(nMapFilesSet);//ȥ�ظ�
							mMap.put("fileName", mMapFilesSet);//����filesUrl�������
							AddedMap.add(nMap);//���Ѿ��ϲ�����Map ��ŵ��ж�List 
						}
					}
					mMap.put("filesUrl", "<a class='uploadEcho'  href='#' value='"+ mMap.get("fileName") +"' >����鿴("+((Set<String>)mMap.get("fileName")).size()+")</a>");//�ۼ� �ļ�����
					resultList.add(mMap);
				}
			}		
		} catch (Exception e) {
			logger.error("�쳣��Ϣ="+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	//��ѯ�ؼ����ݲ�����ͳ��
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���ؼ�����
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);
		List<YzlEpcTaskProgress> epcTaskProgressList = new ArrayList<>();
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = new ArrayList<>();
		for (YzlMessage message : messagesList) {
			List<YzlEpcTaskProgress> epcList = epcTaskProgressMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, areaCode, authoritys,ZLLB,GCLB,message);
			List<YzlEpcAndTaskStaticti> epcAndTaskList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, stats, authoritys, areaCode,ZLLB,GCLB,message);
			epcTaskProgressList.addAll(epcList);
			epcAndTaskStatictiList.addAll(epcAndTaskList);
		}
		//��ѯ������ɵ����� ��ͳ���ؼ�����
//		taskIssuedDTOs.add(epcTaskProgressList);
		//��ѯ������ɵ����� ��ͳ���м�����
		
		try {
			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//������������Ų�ѯ����
				double taskprogress = IepcTaskProgress.getTaskprogress();//��ȡ�����·���������
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				int  log =0;//��¼��ǰ�����·� �� �������Ƿ�����ƥ���
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //�ж��ؼ���������Ƿ����
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//�ж���������Ƿ����
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //�жϹ�������Ƿ����
						log++;//��ƥ��ģ�������
						//��ȡ�����·������� 
						//��ȡ��������ɵ�����
						double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
						// ������������ݳ��������������õ� ռ������ɱ����Ľ��   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//��װ ���������ռ����ƻ����ı���  ����һλС��
						countDataMap.put("stat", JepcAndTaskStaticti.getStat());
						countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  String.format("%.1f", zjhNumber*100)+"%");
						countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
					}
				}
				if(log==0){//log Ϊ0 ���ʾû���������е�����������ƥ�䣬����0����
					countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  0);
					countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
				}
				countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������
				String filesUrl = IepcTaskProgress.getFilesUrl();//���ļ�·�����в��
				Set<String> FilesUrlSet= new HashSet();//�洢�ļ�·�� ��ȥ�ظ�
				if(!StringUtils.isBlank(filesUrl)){
					 String[] filesUrlArray = filesUrl.split(",");//����ļ�
					for (String fileUrl : filesUrlArray) {
						if(StringUtils.isBlank(fileUrl)){
							continue;
						}
						FilesUrlSet.add(fileUrl);//����ļ�·��
					}
				}
				countDataMap.put("fileUrl", FilesUrlSet);
				//countDataMap.put("filesNumber", FilesUrlSet.size());//��װ�м��������
				//countDataMap.put("filesUrl", "<a href='www.baidu.com' >����鿴("+FilesUrlSet.size()+")</a>");//�ۼ� �ļ�����
				countDataMap.put("countyCode", IepcTaskProgress.getCountycode());////��װ�ؼ��������
				//��װ�ؼ������·������ݲ� ����С�����4λ
				countDataMap.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", taskprogress));
				countDataList.add(countDataMap);
			}
			
			List<Map> AddedMap = new ArrayList<>();//�洢 �Ѿ��ϲ�����Map  �ж��Ƿ��ظ����
			for (int m=0;m<countDataList.size();m++){//����List��ͳ�ƺõ����� ����ͬ�غϲ�
				Map mMap = countDataList.get(m);
				if(!AddedMap.contains(mMap)){
					Map resultMap = new HashMap<>();
					Set<String> mMapFilesUrlSet = (Set<String>) mMap.get("fileUrl");//��ȡmMap���ļ�����
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						Set<String> nMapFilesUrlSet = (Set<String>) nMap.get("fileUrl");//��ȡmMap���ļ�����
						String countyCode = (String) mMap.get("countyCode");
						if(StringUtils.isBlank(countyCode)){
							logger.error("�ؼ��������Ϊ��");
							throw new YzlException(ResultEnum.COUNTY_IS_NULL);
						}
						if(countyCode.equals(nMap.get("countyCode"))){
							mMap.putAll(nMap); //ͬһ����  �����ݽ��кϲ�
							//nMap.put("filesUrl", "<a src='' ����鿴("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//�ۼ� �ļ�����
							mMapFilesUrlSet.addAll(nMapFilesUrlSet);//ȥ�ظ�
							mMap.put("fileUrl", mMapFilesUrlSet);//����filesUrl�������
							AddedMap.add(nMap);//���Ѿ��ϲ�����Map ��ŵ��ж�List 
						}
					}
					mMap.put("filesUrl", "<a class='uploadEcho'   href='javascript:void(0);' value='"+ mMap.get("fileUrl") +"'  >����鿴("+((Set<String>)mMap.get("fileUrl")).size()+")</a>");//�ۼ� �ļ�����
					resultList.add(mMap);
				}
			}
		} catch (Exception e) {
			logger.error("�쳣��Ϣ="+e);
			e.printStackTrace();
		}
		return resultList;
	}

	
	
}
