package com.yzl.planManagementService.Impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.CompletionTaskService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class CompletionTaskServiceImpl implements CompletionTaskService{

	@Autowired
	private YzlXbMapper xbMapper;

	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlXbMapper XbMapper;
	private static Logger logger = Logger.getLogger(CompletionTaskServiceImpl.class);
	private static List<List<Map<String, String>>> list = new ArrayList<>();
//	private static List<HashMap<String, String>> epcAndTask = new ArrayList<>();//���� ��� �ƻ�  ���  ռ�ƻ�% 
	String uid;
	//��ѯ��ͷ
//	@Override
//	public List<YzlTask> epcTab(String year,String disCode,String zllb) {
//		
//		//��ѯ��״̬
//		List<String> stats = new ArrayList<>();
//		stats.add("2");
//		//��ȡ�û���Ӧ��ȫЫ
//		//List<String> menu = getMenu();
//		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
//		//��ѯ�����ɵı�ͷ
//		List<YzlTask> resultTaskList = new ArrayList<>();//���ؽ����taskList
//		//����Ȩ�� ����������ţ���ݲ�ѯ �����·��е� task
////		List<YzlTask> taskList = taskMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, stats,zllb);
//		List<YzlTask> taskList = XbMapper.selectByTaskIssuedTableHead(year, disCode, zllb, authoritys,stats);
//		
//		for (YzlTask task : taskList) {
//			String ZLLB = task.getMark();//��ȡ�������
//			//����Ȩ�� ����������ţ���ݣ�������� ��ѯ�����·��е� epc
//			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB, stats);
//			task.setList(epcList);//�ѹ���list��װ��task��
//			resultTaskList.add(task);//�������װ�����ص�������
//		}
//		return resultTaskList;
//	}
	
	//��ѯ��ͷ
		@Override
		public List<YzlEpc> epcTab(String year, String disCode,String zllb) {
			System.out.println(disCode);
			if ("10".equals(zllb)) {
				zllb = null;
			}
			//Ҫ��ѯ��״̬
			List<String> stats = new ArrayList<>();
			stats.add("2");
			//��ȡ�û���Ӧ��ȫЫ
			List<String> menu = getMenu();
			//��ѯ�����ɵı�ͷ
//			List<YzlTask> tasks = XbMapper.selectEpcTabName(stats,year,disCode,menu,zllb);
			//��ѯ�·��ı�ͷ
			List<YzlEpc> epcs = XbMapper.selectByTaskIssuedTableHead(year, disCode, zllb, menu,stats);
			
			for (YzlEpc epc : epcs) {
				String ecode = epc.getMark();
				//��ѯ����������ӵ�еĹ���
				//List<YzlEpc> epcs = XbMapper.selectByEpcPossessTask(tcode,year,stats,disCode,menu,zllb);
				List<YzlTask> tasks = XbMapper.selectByGclb(ecode,year,disCode,menu,zllb,stats);
//						HashMap<String, String> hashMap = new HashMap<>();
				for (YzlTask yzlTask : tasks) {//���������͹���ƴ��һ��
					yzlTask.setField(ecode+"T"+yzlTask.getMark());
				}
				if (tasks.size()>0) {
					epc.setList(tasks);
//							epcAndTask.add(hashMap);
				}
			}
			return epcs;
		}
	
	@Override
	public EasyUIResult queryTaskData(Integer page, Integer rows, String year, String areaCode,String ZLLB,String usr) {
		list.clear();
		if ("10".equals(ZLLB)) {
			ZLLB = null;
		}
		List<String> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		if(StringUtils.isBlank(year)){//�����ݵ��� �� ���ȡ��ǰʱ������
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//�������Ϊ�� ���׳��쳣
			logger.error("��Ҫ��ѯ�ĵ����������Ϊ��-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		List<String> menu = getMenu();
//		YzlUser loginUser = LoginUserUtils.getLoginUser();
//		String userId = loginUser.getId().toString();
//		String keywork = loginUser.getKeywork();
//		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		//��ȡ��ǰ��¼�û�������Ȩ��
		List<Map<String,String>> resultList = new ArrayList<>();
	//	if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//�����Ⱦ��ǵ�ǰѡ�����������
		if(StringUtils.isBlank(areaCode) || areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){
//			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork); //��ѯ��ͳ���м�����
			resultList = selectByCityAndCount(year, stats, menu, areaCode, ZLLB);
		}else{
			resultList = selectByCountyCount(year, stats, menu, areaCode, ZLLB);
			//return epcTaskData(year, areaCode, page, rows, usr);
//			resultList = queryCountyDataAndCount(year, areaCode, stats, authoritys, ZLLB, keywork);//��ѯ��ͳ���ؼ�����
		}
/*		}else{
			//��ѯ��ͳ���ؼ�����
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork);
		}*/

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

	
	
	
	
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork){
		list.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���ؼ�����
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys,ZLLB,null,stats);
		//��ѯ������ɵ����� ��ͳ���ؼ�����
//		taskIssuedDTOs.add(epcTaskProgressList);
		//��ѯ������ɵ����� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,null);
		try {
			for (int i = 0; i < epcAndTaskStatictiList.size(); i++) {//����������ɵ�����
				List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
				HashMap countDataMap = new HashMap<>();
				YzlEpcAndTaskStaticti IepcAndTaskStaticti = epcAndTaskStatictiList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcAndTaskStaticti.getCounty());//������������Ų�ѯ����
				double XTJSBMJDouble = Double.valueOf(IepcAndTaskStaticti.getXTJSBMJ());//��ȡ�����·���������
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				for (int j = 0; j < epcTaskProgressList.size(); j++) {
					YzlEpcTaskProgress JepcTaskProgress = epcTaskProgressList.get(j);
					if(IepcAndTaskStaticti.getCounty().equals(JepcTaskProgress.getCountycode()) //�ж��ؼ���������Ƿ����
							&&IepcAndTaskStaticti.getZLLB().equals(JepcTaskProgress.getZllb())	//�ж���������Ƿ����
								&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb())){ //�жϹ�������Ƿ����
						//��ȡ�����·������� 
						//��ȡ��������ɵ�����
						double taskprogress = Double.valueOf(JepcTaskProgress.getTaskprogress());
						// ������������ݳ��������������õ� ռ������ɱ����Ľ��   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//��װ ���������ռ����ƻ����ı���  ����һλС��
						countDataMap.put("jh"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.format("%.4f", taskprogress));
						countDataMap.put("zjh"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(),  String.format("%.1f", zjhNumber*100)+"%");
					}
				}
				countDataMap.put("wc"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.valueOf(XTJSBMJDouble));
				countDataMap.put(IepcAndTaskStaticti.getZLLB()+"T"+IepcAndTaskStaticti.getGCLB(), String.valueOf(XTJSBMJDouble));
				countDataMap.put("cityCode", IepcAndTaskStaticti.getCity());//��װ�м��������
				//countDataMap.put("filesNumber", FilesUrlSet.size());//��װ�м��������
				//countDataMap.put("filesUrl", "<a href='www.baidu.com' >����鿴("+FilesUrlSet.size()+")</a>");//�ۼ� �ļ�����
				countDataMap.put("countyCode", IepcAndTaskStaticti.getCounty());////��װ�ؼ��������
				//��װ�ؼ������·������ݲ� ����С�����4λ
				countDataMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
				countDataList.add(countDataMap);
			}
			
			List<Map> AddedMap = new ArrayList<>();//�洢 �Ѿ��ϲ�����Map  �ж��Ƿ��ظ����
			for (int m=0;m<countDataList.size();m++){//����List��ͳ�ƺõ����� ����ͬ�غϲ�
				Map mMap = countDataList.get(m);
				if(!AddedMap.contains(mMap)){
					Map resultMap = new HashMap<>();
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						String countyCode = (String) mMap.get("countyCode");
						if(StringUtils.isBlank(countyCode)){
							logger.error("�ؼ��������Ϊ��");
							throw new YzlException(ResultEnum.COUNTY_IS_NULL);
						}
						if(countyCode.equals(nMap.get("countyCode"))){
							mMap.putAll(nMap); //ͬһ����  �����ݽ��кϲ�
							//nMap.put("filesUrl", "<a src='' ����鿴("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//�ۼ� �ļ�����
							AddedMap.add(nMap);//���Ѿ��ϲ�����Map ��ŵ��ж�List 
						}
					}
					resultList.add(mMap);
				}
			}
		} catch (Exception e) {
			logger.error("�쳣��Ϣ="+e);
			e.printStackTrace();
		}
		list.add(resultList);
		return resultList;
	}
	
	
	//���е�ͳ�ƺͲ�ѯ
	private List<Map<String,String>> selectByCityAndCount(String year,List<String> stats,List<String> menu,String disCode,String zllb){
		//ͳ���е������·�
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCityCountyTaskIssued(year,menu,zllb,stats);
		LinkedHashSet<String> hashSetCity = new LinkedHashSet<>();
		
		//������е��в�ȥ��
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSetCity.add(yzlEpcTaskProgress.getCitycode());
		}
		
		List<Map<String,String>> lists = new ArrayList<>();
		
		//��ѯ������ɵ����ݲ�ͳ���м�����
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year,stats,menu,disCode,zllb,null);
		
		int flag = 0 ;
		for (YzlEpcTaskProgress epcTaskProgress : epcTaskProgresses) {//�������е���
			
			//�����б�Ų�ѯ
			YzlDistrictExample example=new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andCitycodeEqualTo(epcTaskProgress.getCitycode());
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			
			HashMap<String, String> hashMap = new HashMap<>();
			Float float1 = epcTaskProgress.getTaskprogress();//�·�����������
			
			for (YzlXb yzlXb : xbs) {//��������������
				if (epcTaskProgress.getCitycode().equals(yzlXb.getCity()) && epcTaskProgress.getGclb().equals(yzlXb.getGclb()) 
				&& epcTaskProgress.getZllb().equals(yzlXb.getZllb()) && epcTaskProgress.getZynd().equals(yzlXb.getZynd())) {//����б����ͬ
					flag++;
					hashMap.put(epcTaskProgress.getZllb()+"zl", yzlXb.getXtjsbmj());
					hashMap.put("citycode", epcTaskProgress.getCitycode());
					hashMap.put("stat", yzlXb.getStat());
					hashMap.put("jh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", float1));//�ƻ�
					hashMap.put("city", list.get(0).getCity());//��
					String xtjsbmj = yzlXb.getXtjsbmj();//��ɵ�����
					Float wc = Float.valueOf(xtjsbmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", Float.valueOf(xtjsbmj)));
					hashMap.put("zjh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", wc*100));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
					hashMap.put(epcTaskProgress.getZllb()+"T"+epcTaskProgress.getGclb(), yzlXb.getXtjsbmj());
					break;
				}
			}
			if (flag == 0) {
				hashMap.put(epcTaskProgress.getZllb()+"zl", "0");
				hashMap.put("citycode", epcTaskProgress.getCitycode());
				hashMap.put("stat", epcTaskProgress.getStat());
				hashMap.put("jh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", float1));//�ƻ�
				hashMap.put("city", list.get(0).getCity());//��
				hashMap.put("wc"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");
				hashMap.put("zjh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");//ռ�ƻ� ���� ��ɵĳ��Լƻ�
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
				hashMap.put(epcTaskProgress.getZllb()+"T"+epcTaskProgress.getGclb(), "0");
			}
			lists.add(hashMap);
			flag = 0;
		}
		List<Map<String,String>> disList = new ArrayList<>();
		
		for (String string : hashSetCity) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {
				String citycode = map.get("citycode");
				Set<Entry<String,String>> set = map.entrySet();
				if (string.equals(citycode)) {
					for (Entry<String, String> entry : set) {
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			disList.add(hashMap);
		}
		
		for (Map<String, String> map : disList) {
			String string = map.get("citycode");
			
			Set<Entry<String,String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				
				if (key.contains("zl")) {
					
					String substring = key.substring(0, key.indexOf("z"));
					
					Float float1 = Float.valueOf(entry.getValue());
					for (Map<String, String> Old_map : disList) {
						
						Set<Entry<String,String>> entrySet2 = Old_map.entrySet();
						String string2 = Old_map.get("citycode");
						
						for (Entry<String, String> entry2 : entrySet2) {
							String key2 = entry2.getKey();
							if (key2.contains("wc"+substring) && string.equals(string2)) {
								float1+= Float.valueOf(entry2.getValue());
							}
						}
					}
					Float aFloat = float1-Float.valueOf(entry.getValue());
					entry.setValue(aFloat.toString());
				}
			}
		}
		list.add(disList);
		return disList;
	}
	
	
	//���ص�ͳ�ƺͲ�ѯ
	private List<Map<String, String>> selectByCountyCount(String year, List<String> stats, List<String> menu,String disCode,String zllb) {
		//ͳ���ص������·�
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCountyCountyTaskIssued(year,menu,disCode,zllb,stats,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//װ�صļ��ϲ����ظ�
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSet.add(yzlEpcTaskProgress.getCountycode());
		}
		List<Map<String,String>> lists = new ArrayList<>();//���ص�����
		
		//��ѯ������ɵ����ݲ�ͳ���м�����
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year, stats, menu, disCode,zllb,null);
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			
			//�����б�Ų�ѯ
			YzlDistrictExample example=new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andAnumberEqualTo(yzlEpcTaskProgress.getCountycode());
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			
			HashMap<String, String> hashMap = new HashMap<>();
			Float float1 = yzlEpcTaskProgress.getTaskprogress();//�·�����������
			
			int flag = 0;
			
			for (YzlXb yzlXb : xbs) {
				
				if (yzlEpcTaskProgress.getCountycode().equals(yzlXb.getCounty()) && yzlEpcTaskProgress.getGclb().equals(yzlXb.getGclb()) 
						&& yzlEpcTaskProgress.getZllb().equals(yzlXb.getZllb()) && yzlEpcTaskProgress.getZynd().equals(yzlXb.getZynd())) {//����б����ͬ
					flag++;
					
					hashMap.put("countycode", yzlEpcTaskProgress.getCountycode());
					hashMap.put("stat", yzlXb.getStat());
					hashMap.put("jh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", float1));//�ƻ�
					hashMap.put("city", list.get(0).getCity());//��
					hashMap.put("county", list.get(0).getCounty());//��
					String xtjsbmj = yzlXb.getXtjsbmj();//��ɵ�����
					Float wc = Float.valueOf(xtjsbmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", Float.valueOf(xtjsbmj)));
					hashMap.put("zjh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", wc*100));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
					hashMap.put(yzlEpcTaskProgress.getZllb()+"T"+yzlEpcTaskProgress.getGclb(), yzlXb.getXtjsbmj());
					break;
				}
				
			}
			if (flag == 0) {
				hashMap.put("countycode", yzlEpcTaskProgress.getCountycode());
				hashMap.put("stat", yzlEpcTaskProgress.getStat());
				hashMap.put("jh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", float1));//�ƻ�
				hashMap.put("city", list.get(0).getCity());//��
				hashMap.put("county", list.get(0).getCounty());//��
				hashMap.put("wc"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), "0");//���
				hashMap.put("zjh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), "0");//ռ�ƻ� ���� ��ɵĳ��Լƻ�
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
				hashMap.put(yzlEpcTaskProgress.getZllb()+"T"+yzlEpcTaskProgress.getGclb(), "0");
			}
			flag = 0;
			lists.add(hashMap);
		}
		List<Map<String,String>> countyList = new ArrayList<>();
		for (String countyCode : hashSet) {//�صı��
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {//
				
				String string2 = map.get("countycode");
				if (countyCode.equals(string2)) {
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			countyList.add(hashMap);
		}
		list.add(countyList);
		return countyList;
	}
	

	//��ѯ�м����ݲ�����ͳ��
	/*private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork){
		list.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//���б��
		for (YzlEpcAndTaskStaticti epcAndTaskStaticti : epcAndTaskStatictiList) {//������ظ����б����ӽ�ȥ
			hashSet.add(epcAndTaskStaticti.getCity());
		}
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys,ZLLB,null);

		int record = 0;
		//��ѯ������ɵ����� ��ͳ���м�����
		try {
			for (String string : hashSet) {//���������б��

				for (int i = 0; i < epcAndTaskStatictiList.size(); i++) {
					HashMap<String, String> map = new HashMap<>();
					YzlEpcAndTaskStaticti IepcAndTaskStaticti = epcAndTaskStatictiList.get(i);
					if (string.equals(epcAndTaskStatictiList.get(i).getCity())) {//������б�����
					//	List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
						for (int j = 0; j < epcTaskProgressList.size(); j++) {
							YzlEpcTaskProgress JepcTaskProgress = epcTaskProgressList.get(j);
							if(IepcAndTaskStaticti.getCity().equals(JepcTaskProgress.getCitycode()) //�ж��м���������Ƿ����
									&&IepcAndTaskStaticti.getZLLB().equals(JepcTaskProgress.getZllb()) //�ж���������Ƿ����
										&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb())){//�жϹ�������Ƿ����
								//��ȡ��������ɵ�����
								Double XTJSBMJDouble = Double.valueOf(IepcAndTaskStaticti.getXTJSBMJ());
								// ������������ݳ��������������õ�  ������ɱ����Ľ��   �ý������ȡ������С����2λ
								double zjh =XTJSBMJDouble/JepcTaskProgress.getTaskprogress().doubleValue();
								map.put("zjh"+JepcTaskProgress.getZllb()+"Y"+JepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
								map.put("jh"+JepcTaskProgress.getZllb()+"Y"+JepcTaskProgress.getGclb(), String.format("%.4f", Double.valueOf(JepcTaskProgress.getTaskprogress())));
							}
						}
						//��װ�м������·������� ������С�����4λ
					}
					YzlDistrict district = districtMapper.selectByNumber(IepcAndTaskStaticti.getCounty());//������������Ų�ѯ����
					map.put("city", district.getCity());
					map.put("wc"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.valueOf(IepcAndTaskStaticti.getXTJSBMJ()));
					map.put(IepcAndTaskStaticti.getZLLB()+"T"+IepcAndTaskStaticti.getGCLB(), String.valueOf(IepcAndTaskStaticti.getXTJSBMJ()));
					map.put("cityCode", IepcAndTaskStaticti.getCity());//��װ�м��������			
					//��װ�м������·������� ������С�����4λ
					//������ͳ�ƺ÷�װ��List������
					if (record < epcTaskProgressList.size()) {
						countDataList.add(map);
					}
					//String filesUrl = IepcTaskProgress.getFilesUrl();//���ļ�·�����в��
					Set<String> FilesUrlSet= new HashSet();//�洢�ļ�·�� ��ȥ�ظ�
					
				}
			}
			List<Map> AddedMap = new ArrayList<>();//�洢 �Ѿ��ϲ�����Map  �ж��Ƿ��ظ����
			
			
			for (int m=0;m<countDataList.size();m++){
				Map mMap = countDataList.get(m);
				//ͳ�������������
				List<CountZLLBDTO> cityZllbNumberDTOList = xbMapper.countCityZllbNumber(stats);
				if(countDataList.size()==1){
					for (CountZLLBDTO countZLLBDTO : cityZllbNumberDTOList) {
							//��Ӹ�����������ͳ��
							mMap.put(countZLLBDTO.getZLLB(),countZLLBDTO.getXTJSBMJ());
					}
					resultList.add(mMap);//��ӵ����صļ���
					break;
				}
				String cityCode = (String) mMap.get("cityCode");//��ȡ�м��������
				if(StringUtils.isBlank(cityCode)){
					logger.error("�м��������Ϊ��");
					throw new YzlException(ResultEnum.CITY_IS_NULL);
				}
				//resultList.add(mMap);
				if(!AddedMap.contains(mMap)){
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						if(cityCode.equals(nMap.get("cityCode"))){
							for (CountZLLBDTO countZLLBDTO : cityZllbNumberDTOList) {
								if(cityCode.equals(countZLLBDTO.getCity())){
									//��Ӹ�����������ͳ��
									nMap.put(countZLLBDTO.getZLLB(),countZLLBDTO.getXTJSBMJ());
								}
							}
							mMap.putAll(nMap); //ͬһ����  �����ݽ��кϲ�
							AddedMap.add(nMap);//���Ѿ��ϲ�����Map ��ŵ��ж�List 
						}
					}
					resultList.add(mMap);
				}
			}		
		} catch (Exception e) {
			logger.error("�쳣��Ϣ="+e);
			e.printStackTrace();
		}
		list.add(resultList);
		return resultList;
	}*/
	
	
	//����չʾ
	@Override
	public EasyUIResult epcTaskData(String year,String disCode,Integer page,Integer rows,String usr,String zllb) {
		list.clear();
		//Ҫ��ѯ��״̬
		List<String> stats = new ArrayList<>();
		stats.add("2");
		List<Integer> stas = new ArrayList<>();
		stas.add(2);
		//��ȡ�û���Ӧ��ȫЫ
		List<String> menu = getMenu();

		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		
		List<HashMap<String, String>> maps = new ArrayList<>();
		List<String> citys = new ArrayList<>();
		
		//��ѯ������ɵ����� ��ͳ���м�����
		//�õ�ָ���ĵ���
		if (disCode.equals("null") || disCode.equals("GX450")) {//��ѯ����������
			citys = xbMapper.selectByCityAndTimeAndStat(year, stats,menu);
		}else {//��ѯ��������л���
			citys = xbMapper.selectByCityAndTimeAndStatCountyAndCity(year, stats,disCode,menu);
		}
		List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti = new ArrayList<>();
		/*if (menu.size()>0) {
			YzlEpcAndTaskStaticti = XbMapper.selectByCityAndEpcInMenu(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode(),year,citys,disCode);*/
			//�������е���
			for (String city : citys) {
					//�ó�ÿ���н��������
					YzlEpcAndTaskStaticti = xbMapper.selectByCityAndEpcComplation(stats,year,city,disCode,citys,menu,zllb);
					List<HashMap<String,String>> municipality = municipality(YzlEpcAndTaskStaticti,disCode);
					HashMap<String,String> hashMap = disposeData(municipality);
					if (hashMap.size()>0) {
						maps.add(hashMap);
					}
					if (menu.size() > 0 && disCode.length() != 2 && disCode.length() != 6) {
						break;
					}
			}
//		list = maps;

		//��ʼ ���� ��ǰҳ-1����ÿҳ��¼��
		int page2 = (page-1)*rows;
		//��������е�����û�з�ҳ�Ķ�ðѼ��ϵĴ�С����ҳ
		if(rows>maps.size()) {
			rows = maps.size();
		}
		List<HashMap<String,String>> subList = new ArrayList<>();
		//��ȡ
		if (maps.size()<10) {
			subList = maps;
		}else {
			maps.subList(page2, rows);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(subList);
		result.setTotal(maps.size());
		
		return result;
	}
	
	private List<HashMap<String, String>> municipality(List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti,String disCode){
		
		List<String> stats = new ArrayList<>();
		stats.add("2");
		
		List<HashMap<String, String>> hashMaps = new ArrayList<>();
		
		YzlEpcAndTaskStaticti epcAndTaskStaticti = null;
		HashMap<String, String> hashMap = null;
		String cityCode = null;
		String zllb = null;
		String gclb = null;
		String county2 = null;
		String jhnd = null;
		String zynd = null;
		String city = null;
		String ccty = null;
		String ct = null;
		String jh = null;
		Float zjh = null;
		String XTJSBMJ = null;
		YzlEpcTaskProgress plan = null;
		
		for (YzlEpcAndTaskStaticti yzlEpcAndTaskStaticti2 : YzlEpcAndTaskStaticti) {//����
			hashMap = new HashMap<>();
			cityCode = yzlEpcAndTaskStaticti2.getCity();//��ȡ�е��������
			zllb = yzlEpcAndTaskStaticti2.getZLLB();//�������
			gclb = yzlEpcAndTaskStaticti2.getGCLB();//�������
			county2 = yzlEpcAndTaskStaticti2.getCounty();//�صı��
			jhnd = yzlEpcAndTaskStaticti2.getJHND();//�ƻ����
			zynd = yzlEpcAndTaskStaticti2.getZYND();//��ҵ���
			city = yzlEpcAndTaskStaticti2.getCity();
			
			ct = xbMapper.selectByCity(cityCode);//�����б�Ų�ѯ������
			
			
			if (yzlEpcAndTaskStaticti2.getCounty()!=null && !disCode.equals("null") && !disCode.equals("GX450")) {
				
				String countyCode = yzlEpcAndTaskStaticti2.getCounty();
				
				YzlDistrict district = districtMapper.selectByNumber(countyCode);
				
				hashMap.put("county", district.getCounty());
				hashMap.put("countyCode", countyCode);
			}
			if (disCode.equals("null") || disCode.equals("GX450")) {
				ccty = city;
			}else {
				ccty = county2;
			}
			//����������������ļƻ������Ƕ���
			plan = xbMapper.selectByPlanTask(zllb,gclb,ccty,jhnd,zynd);
			
			if (plan!=null) {
				jh = plan.getTaskprogress().toString();
			}else {
				jh = "0.00";
			}
			
			hashMap.put("jh"+zllb+"Y"+gclb, jh);//�ƻ�
			
			//������״̬��������������˶���
			epcAndTaskStaticti = xbMapper.selectByCompleteTask(zllb,gclb,ccty,jhnd,zynd,stats);
			//��ɵ�����ɵ���������15����10000
			
			if (epcAndTaskStaticti != null) {
				XTJSBMJ = epcAndTaskStaticti.getXTJSBMJ();
			}
			String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
//			String chardisPos = chardisPos(XTJSBMJ);
			
			hashMap.put("wc"+zllb+"Y"+gclb, chardisPos);
			
			if (plan!=null && chardisPos != null) {
				zjh = Float.valueOf(chardisPos)/Float.valueOf(plan.getTaskprogress())*100;
			}else {
				zjh = 0.0f;
			}
			
			hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
			hashMap.put("zjh"+zllb+"Y"+gclb, String.format("%.2f", zjh));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
			hashMap.put("city", ct);//��
			hashMap.put("cityCode", cityCode);//���������
			hashMap.put(yzlEpcAndTaskStaticti2.getZLLB()+"T"+yzlEpcAndTaskStaticti2.getGCLB(), yzlEpcAndTaskStaticti2.getXTJSBMJ());
			hashMaps.add(hashMap);
			
			zjh = null;
			jh = null;
		}
		return hashMaps;
	}
	
	//���ݴ���
	private HashMap<String, String> disposeData(List<HashMap<String, String>> hashMaps) {
		
		HashMap<String, String> map = new HashMap<>();
		
		for (HashMap<String, String> hashMap : hashMaps) {
			
			Set<Entry<String,String>> entrySet = hashMap.entrySet();
			
			for (Entry<String, String> entry : entrySet) {
				
				String key = entry.getKey();
				String value = entry.getValue();
				map.put(key, value);
			}
		}
		
//		HashMap<String,String> towDispose = towDispose(map);
		
		return map;
	}

	@Override
	public YzlDistrict Ddis(String dcode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		return district;
	}

	//��ȡ��ǰ��¼���û�
	private static YzlUser getUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		YzlUser user = (YzlUser) session.getAttribute("user");
		return user;
	}
	
	//��ȡ�û���Ӧ��Ȩ��
	private List<String> getMenu(){
		
		//��ȡ��ǰ��¼���û�
		YzlUser user = getUser();
		Long id = user.getId();
		
		//�еı��
		List<YzlMenu> mYzlMenus = new ArrayList<>();
		//�صı��
		List<String> permsList = new ArrayList<>();
		
		if (id!=35) {//˵������ϵͳ����Ա
			//��ȡ����û���Ӧ��Ȩ��
			List<YzlMenu> menus = monitoringstatisticsMapper.selectByMeun(id);
			
			for (YzlMenu yzlMenu : menus) {
				Long menuId = yzlMenu.getMenuId();
				//��ȡĳ�����µ���
				List<YzlMenu> mYzlMenuss = monitoringstatisticsMapper.selectByMenuId(id,menuId);
				for (YzlMenu yzlMenu2 : mYzlMenuss) {
					mYzlMenus.add(yzlMenu2);
				}
			}
			
			for (YzlMenu yzlMenu : mYzlMenus) {
				String perms = yzlMenu.getPerms();
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher matcher = pattern.matcher(perms);
				if (matcher.matches()) {
					permsList.add(perms);
				}
			}
		}
		
		return permsList;
	}

	//����
	@Override
	public void derive(String nid, String year, HttpServletResponse response,String disCode,String new_zllb) {
		EasyUIResult result = queryTaskData(1, 10, year, disCode, new_zllb, null);
//		EasyUIResult result = epcTaskData(1, 10, year, disCode, new_zllb, null);
		
		
//		EasyUIResult result = epcTaskData(year, disCode, 1, 10, null,null);
		
		//�����ı��
//		List<String> Anumbers = new ArrayList<>();
//		
//		if (!nid.equals("null") && !nid.equals("GX450")) {
//				Anumbers = xbMapper.selectByFlagOrDcode(nid);
//		}
		
		//������������
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = workbook.createCellStyle();//������ʽ
		HSSFSheet sheet = workbook.createSheet();//����һ��sheet
		
		//�������ϻ�ȡ���е��������
		LinkedHashSet<String> hashSet = gainZllb();
		
		//��ͷ������hashSet:���е�������� style:��ʽ year:ʱ�� nid:����������
		makeTable(sheet, hashSet, style, year, disCode);
		
		int st = 5;
		
		
		//�������ж����ݽ������
		for (List<Map<String, String>> list2 : list) {
			for (Map<String, String> hashMap : list2) {
				
				Set<Entry<String,String>> entrySet = hashMap.entrySet();
				
				HSSFRow createRow = sheet.createRow(st);//���庽��ʼ//�½�����
				
				for (Entry<String, String> entry : entrySet) {
					String key = entry.getKey();
					String value = entry.getValue();
					
					//��ȡǰ�������ַ�
					String string = key.substring(0, 2);
					if (key.contains("zl")) {
						//��zl������ĸȥ��
						int indexOf = key.indexOf("z");
						String tmark = key.substring(0,indexOf);//�������ı��
						YzlTask task = taskMapper.selectByMark(tmark);
						
						//��ȡ�ϲ���Ԫ���ֲ
						int regions = sheet.getNumMergedRegions();
						for(int i=0;i<regions;i++) {
							CellRangeAddress region = sheet.getMergedRegion(i);
							
							int firstRow = region.getFirstRow();//��ʼ��
							int lastRow = region.getLastRow();//������
							int firstColumn = region.getFirstColumn();//��ʼ��
//							int lastColumn = region.getLastColumn();//������
							
							if (firstRow == 2 && lastRow == 4) {
								HSSFRow row = sheet.getRow(firstRow);
								HSSFCell cell = row.getCell(firstColumn);
								String zllb = cell.getStringCellValue();//�õ�������������
								cell.setCellValue(zllb);//�Ż�ȥ
								if (task.getTname().equals(zllb)) {
									HSSFCell createCell = createRow.createCell(firstColumn);
									createCell.setCellValue(value);
									break;
								}
							}
						}
						
					}
					if (key.equals("city")) {
						HSSFCell cell = createRow.createCell(0);
						cell.setCellValue(value);
					}
					if (nid.length()>1 && key.equals("county")) {
						HSSFCell cell = createRow.createCell(1);
						cell.setCellValue(value);
					}
					if (string.equals("jh")) {
						//jh������ĸȥ����
						String substring = key.substring(2, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//���������
						String gclb = substring.substring(indexOf+1, substring.length());//���̱��
						
						//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value, string);
						
					}
					if (key.contains("wc")) {
						//wc������ĸȥ����
						String substring = key.substring(2, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//���������
						String gclb = substring.substring(indexOf+1, substring.length());//���̱��
						
						//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
					}
					if (string.equals("zj")) {
						//zjh������ĸȥ����
						String substring = key.substring(3, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//���������
						String gclb = substring.substring(indexOf+1, substring.length());//���̱��
						
						//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
					}
				}
				st++;
			}
		}
		//�Ա����ʽ����ȾRendering
		rendering(workbook, style);
		
		response.reset();
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename=excel1.xls");
			
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//�������ϻ�ȡ���е��������
	private LinkedHashSet<String> gainZllb(){
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
		for (List<Map<String, String>> list2 : list) {
			for (Map<String, String> hashMap : list2) {
				Set<Entry<String,String>> entrySet = hashMap.entrySet();
				for (Entry<String, String> entry : entrySet) {
					String key = entry.getKey();
					if (key.contains("T")) {
						int indexOf = key.indexOf("T");
						String substring = key.substring(0,indexOf);
						hashSet.add(substring);
					}
				}
			}
		}
		return hashSet;
	}
	
	//��ͷ������
	private void makeTable(HSSFSheet sheet,LinkedHashSet<String> hashSet,HSSFCellStyle style,String year,String nid) {
		
		HSSFRow row3 = sheet.createRow(2);//�����е�3��
		HSSFRow row4 = sheet.createRow(3);//�����е�4��
		HSSFRow row5 = sheet.createRow(4);//�����е�5��
		
		int start = 2;//��ʼλ��
		int estart = 2;
		
		if (nid.equals("45")) {
			for (String string : hashSet) {
				//���ݱ�Ų�ѯ�������
				YzlTask task = taskMapper.selectByMark(string);
				sheet.addMergedRegion(new CellRangeAddress(2, 4, start, start));//�ϲ���Ԫ��
//				sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
				//һ����ͷ
				HSSFCell cell = row3.createCell(start);
				cell.setCellValue(task.getTname());
				cell.setCellStyle(style);//������ʽ
				start++;
				estart++;
			}
		}

		//���������������
		for (String tcode : hashSet) {
			//���ݱ�Ų�ѯ�������
			YzlTask task = taskMapper.selectByMark(tcode);
			//��ѯ���������ӵ�еĹ���
			List<String> tas = xbMapper.selectByTaskPossessEpc(tcode,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString(),nid);
//			List<YzlEpc> epcs = XbMapper.selectByZllb(tcode,year,disCode,menu,zllb,stats);
			int size = tas.size()*3;//�����ͷ��ռ�ݵı����
			if (tas.size()>0) {
				sheet.addMergedRegion(new CellRangeAddress(2, 2, start, size+start-1));//�ϲ���Ԫ��
			}
			//һ����ͷ
			HSSFCell cell = row3.createCell(start);
			cell.setCellValue(task.getTname());
			cell.setCellStyle(style);//������ʽ
			
			
			//������ͷ
			for (String ecode : tas) {
				
				YzlEpc epc = epcMapper.selectByMark(ecode);//���ݹ��̱�Ų�ѯ
				sheet.addMergedRegion(new CellRangeAddress(3, 3, estart, estart+2));
				HSSFCell cell2 = row4.createCell(estart);
				cell2.setCellValue(epc.getEname());
				cell2.setCellStyle(style);
				
				int atr = 1;
				for(int j=estart;j<estart+3;j++) {
					
					HSSFCell cell3 = row5.createCell(j);
					switch (atr) {
					case 1:
						cell3.setCellValue("�ƻ�");
						cell3.setCellStyle(style);
						break;
					case 2:
						cell3.setCellValue("���");
						cell3.setCellStyle(style);
						break;
					case 3:
						cell3.setCellValue("ռ�ƻ�%");
						cell3.setCellStyle(style);
						break;
					default:
						break;
					}
					atr++;
				}
				
				estart+=3;
			}
			
			start+=tas.size()*3;
		}
	}
	
	//�Ա����ʽ����ȾRendering
	private void rendering(HSSFWorkbook workbook,HSSFCellStyle style) {
		
		HSSFFont font = workbook.createFont();//������ʽ
		
		//����
		style.setVerticalAlignment(VerticalAlignment.CENTER);// ��ֱ���У����¾��У�
		style.setAlignment(HorizontalAlignment.CENTER);// ���Ҿ���
		
		//��������
		font.setFontName("����_GB2312");//��������
		font.setFontHeightInPoints((short) 10);//����Ĵ�С
		font.setItalic(false);//�����Ƿ�Ϊб�� 
		font.setBold(true);//����Ӵ�
		style.setWrapText(true);//�Զ�����
		
		style.setFont(font);//������Ҫ�õ���������ʽ
		
//		sheet.setColumnWidth(0, 4*280);
		
	}
	
	//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
	private void gainEpcPutLocation(String zllb,String gclb,HSSFSheet sheet,HSSFRow createRow,String value,String ex) {
		
		YzlTask task = taskMapper.selectByMark(zllb);
		YzlEpc epc = epcMapper.selectByMark(gclb);
		
		//��ȡ�ϲ���Ԫ���ֲ
		int regions = sheet.getNumMergedRegions();
		stop:for(int i=0;i<regions;i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			
			int firstRow = region.getFirstRow();//��ʼ��
//			int lastRow = region.getLastRow();//������
			int firstColumn = region.getFirstColumn();//��ʼ��
			int lastColumn = region.getLastColumn();//������
			
			if (firstRow == 2) {
				HSSFRow row = sheet.getRow(firstRow);
				HSSFCell cell = row.getCell(firstColumn);
				String tname = cell.getStringCellValue();//ȡ����//ȡ�����������������
				cell.setCellValue(tname);//�Ż�ȥ
				
				//�����ȡ���Ǳ�ͷ
				
				if (task.getTname().equals(tname)) {//�ж������е��������ͱ�ͷ���Ƿ�һ��
					//��ͬ�ͽ���
					
					HSSFRow row2 = sheet.getRow(3);//��ȡ����
					
					for(int k=firstColumn; k <= lastColumn;k++) {
						HSSFCell cell2 = row2.getCell(k);
						if (cell2 != null) {
							String ename = cell2.getStringCellValue();//��������
							cell2.setCellValue(ename);
							
							if (epc.getEname().equals(ename)) {//�жϹ������Ƿ�һ��
								
								if (ex.equals("jh")) {
									HSSFCell cell3 = createRow.createCell(k);
									cell3.setCellValue(value);
								}else if(ex.equals("wc")){
									HSSFCell cell3 = createRow.createCell(k+1);
									cell3.setCellValue(value);
								}else if(ex.equals("zj")){
									HSSFCell cell3 = createRow.createCell(k+2);
									cell3.setCellValue(value);
								}
								
								break stop;
							}
						}
					}
					
				}
				
			}
		}
	}

	//�����ɵ��˻�
	@Override
	public YzlResult back(String[] backData, String time,String zllb,String [] countys) {
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("2");
		Integer code = TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode();
		
		for (String string : countys) {
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			return new YzlResult(200);
		}
		return new YzlResult(400);
	}

	
}
