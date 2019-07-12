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
import com.yzl.planManagementService.*;
import com.yzl.distriEpcTaskService.impl.TaskIssuedServiceImpl;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.AdministrativeCode;
import com.yzl.utils.CountZLLBDTO;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.PageBean;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.BacklogVO;

@Service
public class BacklogXbServiceImpl implements BacklogXbService{
	
	//��־��
	private static Logger logger = Logger.getLogger(BacklogXbServiceImpl.class);
	
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
	public List<YzlTask> getTableHeader(String year, String disCode, String GCLB,String ZLLB,String statu) {
		List<YzlTask> resultTaskList = new ArrayList<>();//���ؽ����taskList
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		String keywork = loginUser.getKeywork();
		List<String> messageCountys = messageMapper.queryDISTINCTCountyCodeByStatuAndCountyCode(authoritys, keywork);
		List<String> stats = new ArrayList<>();
		stats.add(statu);//���Ҫ����ѯ��״̬
		List<YzlTask> taskList = taskMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, stats, ZLLB);
		for (YzlTask task : taskList) {
			String mark = task.getMark();//��ȡ�������
			//����Ȩ�� ����������ţ���ݣ�������� ��ѯ�����·��е� epc
			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB, stats);
			List<YzlEpc> resultEpcList = new ArrayList<>();//���ؽ�����ӽڵ�epcList
			for (YzlEpc epc : epcList) {
				epc.setField(ZLLB+"T"+epc.getMark());
				resultEpcList.add(epc);
			}
			task.setList(resultEpcList);//�ѹ���list��װ��task��
			resultTaskList.add(task);//�������װ�����ص�������
		}
		return resultTaskList;
	}

	
	@Override
	public EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String ZLLB, String GCLB,String statu) {

		if(StringUtils.isBlank(year)){//�����ݵ��� �� ���ȡ��ǰʱ������
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//�������Ϊ�� ���׳��쳣
			logger.error("��Ҫ��ѯ�ĵ����������Ϊ��-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		String keywork = loginUser.getKeywork();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		List<Integer> stats = new ArrayList<>();
		if(StringUtils.isNotBlank(statu)){
			stats.add(Integer.valueOf(statu));
		}
		//��ȡ��ǰ��¼�û�������Ȩ��
		List<Map<String,String>> resultList = new ArrayList<>();
		List<String> messageCountys = new ArrayList<>();
		messageCountys = messageMapper.queryDISTINCTCountyCodeByStatuAndCountyCode(authoritys, keywork);
		if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//�����Ⱦ��ǵ�ǰѡ�����������
			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB,messageCountys); //��ѯ��ͳ���м�����
		}else{
			//��ѯ��ͳ���ؼ�����
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB,messageCountys);
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
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB,List<String> messageCountyList){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���м�����
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys, ZLLB, GCLB,stats);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//���б��
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//������ظ����б����ӽ�ȥ
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//��ѯ������ɵ����� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode, ZLLB, GCLB);
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
										&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb()) && IepcAndTaskStaticti.getStat().equals(JepcTaskProgress.getStat())){//�жϹ�������Ƿ����
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
		return resultList;
	}
	
	
	//��ѯ�ؼ����ݲ�����ͳ��
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB,List<String> messageCountyList){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���ؼ�����
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys, ZLLB, GCLB,stats);
		//��ѯ������ɵ����� ��ͳ���ؼ�����
//		taskIssuedDTOs.add(epcTaskProgressList);
		//��ѯ������ɵ����� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode, ZLLB, GCLB);
		try{
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
					
					String Jepcounty = JepcTaskProgress.getCountycode();
					String Jezllb = JepcTaskProgress.getZllb();
					String Jegclb = JepcTaskProgress.getGclb();
					String Jestat = JepcTaskProgress.getStat();
					
					String Iepcounty = IepcAndTaskStaticti.getCounty();//��
					String Iezllb = IepcAndTaskStaticti.getZLLB();
					String Iegclb = IepcAndTaskStaticti.getGCLB();
					String Iestat = IepcAndTaskStaticti.getStat();
					
					if(Iepcounty.equals(JepcTaskProgress.getCountycode()) //�ж��ؼ���������Ƿ����
							&& Iezllb.equals(JepcTaskProgress.getZllb())	//�ж���������Ƿ����
								&& Iegclb.equals(JepcTaskProgress.getGclb()) && Iestat.equals(JepcTaskProgress.getStat())){ //�жϹ�������Ƿ����
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
				countDataMap.put("stat", IepcAndTaskStaticti.getStat());
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
			
			
/*			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//������������Ų�ѯ����
				double taskprogress = IepcTaskProgress.getTaskprogress();//��ȡ�����·���������
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //�ж��ؼ���������Ƿ����
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//�ж���������Ƿ����
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //�жϹ�������Ƿ����
						//��ȡ�����·������� 
						//��ȡ��������ɵ�����
						double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
						// ������������ݳ��������������õ� ռ������ɱ����Ľ��   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//��װ ���������ռ����ƻ����ı���  ����һλС��
						countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  String.format("%.1f", zjhNumber*100)+"%");
						countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
					}
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
				countDataMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
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
			}*/
		} catch (Exception e) {
			logger.error("�쳣��Ϣ="+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	
}
