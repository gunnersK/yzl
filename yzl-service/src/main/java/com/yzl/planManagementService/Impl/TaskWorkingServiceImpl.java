package com.yzl.planManagementService.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlLogMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.TaskWorkingService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlLog;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlProceed;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;

@Transactional
@Service
public class TaskWorkingServiceImpl implements TaskWorkingService{

	@Autowired
	private YzlXbMapper XbMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;	
	
	@Autowired
	private YzlEpcMapper epcMapper;

	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlLogMapper logMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	//��ѯ��ͷ
	@Override
	public List<YzlEpc> taskTab(String year, String disCode,String gclb,String stat) {
//		System.out.println(stat);��
//		if(zllb.equals("10")) {
//			zllb = null;
//		}
		//Ҫ��ѯ��״̬
		List<String> stats = new ArrayList<>();
		if (stat == null || stat.equals("undefined") || stat.equals("[object Object]") ) {
			stats.add("0");
			stats.add("1");
			stats.add("3");
		}else {
			stats.add(stat);
		}
		//��ȡ�û���Ӧ��ȫЫ
		List<String> menu = getMenu();
		//��ѯ�����ɵı�ͷ
//		List<YzlTask> tasks = XbMapper.selectEpcTabName(stats,year,disCode,menu,zllb);
		//��ѯ�·��ı�ͷ
		List<YzlEpc> epcs = XbMapper.selectByTaskIssuedTableHead(year, disCode, gclb, menu,stats);
		
		for (YzlEpc epc : epcs) {
			String ecode = epc.getMark();
			//��ѯ����������ӵ�еĹ���
			//List<YzlEpc> epcs = XbMapper.selectByEpcPossessTask(tcode,year,stats,disCode,menu,zllb);
			List<YzlTask> tasks = XbMapper.selectByGclb(ecode,year,disCode,menu,gclb,stats);
//					HashMap<String, String> hashMap = new HashMap<>();
			for (YzlTask yzlTask : tasks) {//���������͹���ƴ��һ��
				yzlTask.setField(ecode+"T"+yzlTask.getMark());
			}
			epc.setList(tasks);
//			if (epcs.size()>0) {
//				System.out.println("========================="+task.getList());
////						epcAndTask.add(hashMap);
//			}
		}
		return epcs;
	}
	
	//����չʾ
	@Override
	public EasyUIResult epcTaskData(String year, String disCode, String gclb,Integer page, Integer rows,String stat,String proceed) {
		
//		if(gclb.equals("10")) {
//			gclb = null;
//		}
		//Ҫ��ѯ��״̬
		List<String> stats = new ArrayList<>();
		if (stat == null || stat.equals("undefined") || stat.equals("[object Object]") ) {
			stats.add("0");
			stats.add("1");
			stats.add("3");
		}else {
			stats.add(stat);
		}
		
		List<Map<String,String>> list = new ArrayList<>();
		
		//��ȡ�û���Ӧ��ȫЫ
		List<String> menu = getMenu();
		
		
		if (disCode.equals("null") || disCode.equals("GX450")) {//��ѯ����������
//			citys = XbMapper.selectByCityAndTimeAndStat(year, stats,menu);
			list = selectByCityAndCount(year,stats,menu,disCode,gclb);
			
		}else {//��ѯ��������л���
//			citys = XbMapper.selectByCityAndTimeAndStatCountyAndCity(year, stats,disCode,menu);
			list = selectByCountyCount(year,stats,menu,disCode,gclb,proceed);
		}
		
		//��ʼ ���� ��ǰҳ-1����ÿҳ��¼��
		int page2 = (page-1)*rows;
		int lastIndex = page2+rows;
		//��������е�����û�з�ҳ�Ķ�ðѼ��ϵĴ�С����ҳ
		if(lastIndex>list.size()) {
			lastIndex = list.size();
		}
		List<Map<String,String>> subList = new ArrayList<>();
		//��ȡ
		if (list.size()<rows) {
			subList = list;
		}else {
			subList = list.subList(page2, lastIndex);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(subList);
		result.setTotal(list.size());
		
		return result;
	}
	
	//���ص�ͳ�ƺͲ�ѯ
	private List<Map<String, String>> selectByCountyCount(String year, List<String> stats, List<String> menu,String disCode,String gclb,String proceed) {
		//ͳ���ص������·�
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCountyCountyTaskIssued(year,menu,disCode,gclb,stats,proceed);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//װ�صļ��ϲ����ظ�
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSet.add(yzlEpcTaskProgress.getCountycode());
		}
		List<Map<String,String>> lists = new ArrayList<>();//���ص�����
		
		//��ѯ������ɵ����ݲ�ͳ���м�����
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year, stats, menu, disCode,gclb,proceed);
		
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
					hashMap.put("jh"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), String.format("%.2f", float1));//�ƻ�
					hashMap.put("city", list.get(0).getCity());//��
					hashMap.put("county", list.get(0).getCounty());//��
					String hgmj = yzlXb.getHgmj();//��ɵ�����
					Float wc = Float.valueOf(hgmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), String.format("%.2f", Float.valueOf(hgmj)));
					hashMap.put("zjh"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), String.format("%.2f", wc*100));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
					hashMap.put(yzlEpcTaskProgress.getGclb()+"T"+yzlEpcTaskProgress.getZllb(), yzlXb.getXtjsbmj());
					hashMap.put("gclb", yzlEpcTaskProgress.getGclb());
					hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>���� </div> </div>  </a>");
					break;
				}
				
			}
			if (flag == 0) {
				hashMap.put("countycode", yzlEpcTaskProgress.getCountycode());
				hashMap.put("stat", yzlEpcTaskProgress.getStat());
				hashMap.put("jh"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), String.format("%.2f", float1));//�ƻ�
				hashMap.put("city", list.get(0).getCity());//��
				hashMap.put("county", list.get(0).getCounty());//��
				hashMap.put("wc"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), "0");//���
				hashMap.put("zjh"+yzlEpcTaskProgress.getGclb()+"Y"+yzlEpcTaskProgress.getZllb(), "0");//ռ�ƻ� ���� ��ɵĳ��Լƻ�
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
				hashMap.put(yzlEpcTaskProgress.getGclb()+"T"+yzlEpcTaskProgress.getZllb(), "0");
				hashMap.put("gclb", yzlEpcTaskProgress.getGclb());
				hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>����</div> </div>  </a>");
			}
			flag = 0;
			lists.add(hashMap);
		}
		
		List<Map<String,String>> countyList = new ArrayList<>();
		for (String countyCode : hashSet) {//�صı��
			
			Integer back = 0;
			Integer submit = 0;
			Integer audit = 0;
			
			LinkedHashSet<String> backList = new LinkedHashSet<>();
			LinkedHashSet<String> submitList = new LinkedHashSet<>();
			LinkedHashSet<String> auditList = new LinkedHashSet<>();
			
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {//
				
				String string2 = map.get("countycode");
				if (countyCode.equals(string2)) {
					Set<Entry<String,String>> entrySet = map.entrySet();
//					System.out.println("entrySet================="+entrySet);
					for (Entry<String, String> entry : entrySet) {
//						System.out.println("entry================="+entry);
						if (entry.getKey().equals("stat")) {
							switch (entry.getValue()) {
							case "0":
								int x=0;
								String backString = map.get("gclb");//�������ı��
								for (String string : backList) {
									if (backString.equals(string)) {
										x++;
									}
								}
								if (x==0) {
									submit++;
								}
								backList.add(backString);
								break;
							case "1":
								int y = 0;
								String submitString = map.get("gclb");//�������ı��
								for (String string : submitList) {
									if (submitString.equals(string)) {
										y++;
									}
								}
								if (y==0) {
									audit++;	
								}
								submitList.add(submitString);		
								break;
							case "3":
								int z = 0;
								String auditString = map.get("gclb");//�������ı��
								for (String string : auditList) {
									if (auditString.equals(string)) {
										z++;
									}
								}
								if (z==0) {
									back++;	
								}
								auditList.add(auditString);	
								break;

							default:
								break;
							}
						}
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			hashMap.put("back", back.toString());
			hashMap.put("submit", submit.toString());
			hashMap.put("audit", audit.toString());
			
			countyList.add(hashMap);
			 back = 0;
			 submit = 0;
			 audit = 0;
		}
		return countyList;
	}

	//���е�ͳ�ƺͲ�ѯ
	private List<Map<String,String>> selectByCityAndCount(String year,List<String> stats,List<String> menu,String disCode,String gclb){
		//ͳ���е������·�
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCityCountyTaskIssued(year,menu,gclb,stats);
		LinkedHashSet<String> hashSetCity = new LinkedHashSet<>();
		
		//������е��в�ȥ��
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSetCity.add(yzlEpcTaskProgress.getCitycode());
		}
		
		List<Map<String,String>> lists = new ArrayList<>();
		
		//��ѯ������ɵ����ݲ�ͳ���м�����
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year,stats,menu,disCode,gclb,null);
		
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
					
					hashMap.put("citycode", epcTaskProgress.getCitycode());
					hashMap.put("stat", yzlXb.getStat());
					hashMap.put("jh"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), String.format("%.2f", float1));//�ƻ�
					hashMap.put("city", list.get(0).getCity());//��
					String hgmj = yzlXb.getHgmj();//��ɵ�����
					Float wc = Float.valueOf(hgmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), String.format("%.2f", Float.valueOf(hgmj)));
					hashMap.put("zjh"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), String.format("%.2f", wc*100));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
					hashMap.put(epcTaskProgress.getGclb()+"T"+epcTaskProgress.getZllb(), yzlXb.getXtjsbmj());
					hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()'><div><li>����   <ul><a href='#'><li>���˻�<li><li>���ύ</li><li>�����</li></a></ul> </li></div> </div>  </a>");
					break;
				}
			}
			if (flag == 0) {
				hashMap.put("citycode", epcTaskProgress.getCitycode());
				hashMap.put("stat", epcTaskProgress.getStat());
				hashMap.put("jh"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), String.format("%.2f", float1));//�ƻ�
				hashMap.put("city", list.get(0).getCity());//��
				hashMap.put("wc"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), "0");
				hashMap.put("zjh"+epcTaskProgress.getGclb()+"Y"+epcTaskProgress.getZllb(), "0");//ռ�ƻ� ���� ��ɵĳ��Լƻ�
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
				hashMap.put(epcTaskProgress.getGclb()+"T"+epcTaskProgress.getZllb(), "0");
				hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()'><div><li>����   <ul><a href='#'><li>���˻�<li><li>���ύ</li><li>�����</li></a></ul> </li></div> </div>  </a>");
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
		
		return disList;
	}
	
	
	private List<HashMap<String, String>> municipality(List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti,String disCode){
		
		List<String> stats = new ArrayList<>();
		stats.add("0");
		stats.add("1");
		stats.add("3");
		
		List<HashMap<String, String>> hashMaps = new ArrayList<>();
		HashMap<String, String> hashMap = null;
		String cityCode = null;
		String zllb = null;
		String gclb = null;
		String county2 = null;
		String jhnd = null;
		String zynd = null;
		String stat = null;
		String city = null;
		String ccty = null;
		
		String ct = null;
		YzlEpcTaskProgress plan = null;
		YzlEpcAndTaskStaticti epcAndTaskStaticti = null;
		String hgmj = null;
		String jh = null;
		Float zjh = null;
		for (YzlEpcAndTaskStaticti yzlEpcAndTaskStaticti2 : YzlEpcAndTaskStaticti) {//����
			
			 hashMap = new HashMap<>();
			 cityCode = yzlEpcAndTaskStaticti2.getCity();//��ȡ�е��������
			 zllb = yzlEpcAndTaskStaticti2.getZLLB();//�������
			 gclb = yzlEpcAndTaskStaticti2.getGCLB();//�������
			 county2 = yzlEpcAndTaskStaticti2.getCounty();//�صı��
			 jhnd = yzlEpcAndTaskStaticti2.getJHND();//�ƻ����
			 zynd = yzlEpcAndTaskStaticti2.getZYND();//��ҵ���
			 stat = yzlEpcAndTaskStaticti2.getStat();//״̬
			 city = yzlEpcAndTaskStaticti2.getCity();
			
			hashMap.put("stat", stat);
			
 			 ct = XbMapper.selectByCity(cityCode);//�����б�Ų�ѯ������
			
			if (yzlEpcAndTaskStaticti2.getCounty()!=null && !disCode.equals("null") && !disCode.equals("GX450")) {
				String county = yzlEpcAndTaskStaticti2.getCounty();
				
				YzlDistrict district = districtMapper.selectByNumber(county);
				
				hashMap.put("county", district.getCounty());
			}
			
			//����������������ļƻ������Ƕ���
			if(disCode.equals("null") || disCode.equals("GX450")) {
				ccty = city;
			}else {
				ccty = county2;
			}//����������������ļƻ������Ƕ���
			 plan = XbMapper.selectByPlanTask(zllb,gclb,ccty,jhnd,zynd);
			
			if (plan!=null) {
				jh = plan.getTaskprogress().toString();
			}else {
				jh = "0.00";
			}
			
			hashMap.put("jh"+zllb+"Y"+gclb, jh);//�ƻ�
			
			//������״̬��������������˶���
			 epcAndTaskStaticti = XbMapper.selectByCompleteTask(zllb,gclb,ccty,jhnd,zynd,stats);
			//��ɵ�����ɵ���������15����10000
			
			if (epcAndTaskStaticti != null) {
				hgmj = epcAndTaskStaticti.getHGMJ();
			}
			String chardisPos = String.format("%.2f", Double.valueOf(hgmj));
//			String chardisPos = chardisPos(XTJSBMJ);
			
			hashMap.put("wc"+zllb+"Y"+gclb, chardisPos);
			
			if (plan!=null && chardisPos != null) {
				zjh = Float.valueOf(chardisPos)/Float.valueOf(plan.getTaskprogress())*100;
			}else {
				zjh = 0.0f;
			}
			
					
			hashMap.put("zjh"+zllb+"Y"+gclb, String.format("%.2f", zjh));//ռ�ƻ� ���� ��ɵĳ��Լƻ�
			hashMap.put("city", ct);//��
			hashMap.put(yzlEpcAndTaskStaticti2.getZLLB()+"T"+yzlEpcAndTaskStaticti2.getGCLB(), yzlEpcAndTaskStaticti2.getHGMJ());
			//"<a class='uploadEcho'  href='#' value='"+ mMap.get("fileName") +"' >����鿴("+((Set<String>)mMap.get("fileName")).size()+")</a>"
			//particulars
			hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >����</a>");
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
			return map;
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
					
					permsList.add(perms);
				}
			}
			
			return permsList;
		}

	
	@Override
	public YzlDistrict Ddis(String dcode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		return district;
	}

	
	//��ѯ���е��������
	@Override
	public List<YzlEpc> show_epc() {
		List<YzlEpc> list = epcMapper.gclb();
		return list;
	}
	
	@Override
	public List<List<YzlXb>> checkOut(String eids,String year,String [] countys,String [] bdgwj,String [] json) {
		
		String[] split = eids.split(",");
		
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
		
		for (String string : split) {
			linkedHashSet.add(string);
		}
		
		List<List<YzlXb>> list = new ArrayList<>();
		for (String county : countys) {
			for (String eic : linkedHashSet) {
				List<YzlXb> yzlXbs = XbMapper.selectByEidAndTime(eic,year,county);
				if (yzlXbs!=null && yzlXbs.size()>0) {
					list.add(yzlXbs);
				}
			}
		}
		return list;
	}

	/**
	 * ��־��ѯ
	 */
	@Override
	public EasyUIResult findLog(String row,String time,String county,Integer page,Integer rows,String zllb) {
		
		//���������Ʋ�ѯ
		YzlDistrict district = new YzlDistrict();
		if (county.length() >= 6) {
			YzlDistrictExample example = new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andAnumberEqualTo(county);
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			district = list.get(0);
		}else {
			district = districtMapper.selectByCounty(county);
		}
		String anumber = null;
		if (district!=null) {
			anumber = district.getAnumber();//�صı��
		}else {
			anumber = county;
		}
		
		HashSet<String> hashSet = new HashSet<>();
		String[] split = row.split(",");//�ַ����ָ�
		for (String string : split) {
			
			int indexOf = string.indexOf("T");//��ȡ�ַ����±�����
			String substring = string.substring(0, indexOf);//�õ�T�ַ�ǰ�������
			hashSet.add(substring);
		}
		
		StringBuffer bf = new StringBuffer();
		Iterator<String> iterator = hashSet.iterator();//����hashSet����
		while (iterator.hasNext()) {
			String next = iterator.next();
			bf.append(next+",");
		}
		
//		YzlLogExample example = new YzlLogExample();
//		example.setOrderByClause("operate_time DESC");
//		Criteria criteria = example.createCriteria();//���ò�ѯ����
//		criteria.andDcodeEqualTo(anumber);
//		criteria.andYearEqualTo(time);
//		criteria.andGclbEqualTo(bf.toString());
//		criteria.andZllbNotLike(zllb);
//		criteria.andGclbNotLike(zllb);
//		List<YzlLog> list = logMapper.selectByExample(example);//��ѯ����������
		List<YzlLog> list = logMapper.findLog(anumber,time,zllb);
		//��ʼ ���� ��ǰҳ-1����ÿҳ��¼��
		int page2 = (page-1)*rows;
		
		//��������е�����û�з�ҳ�Ķ�ðѼ��ϵĴ�С����ҳ
		if(rows>list.size()) {
			rows = list.size();
		}
		List<YzlLog> pages = new ArrayList<>();
		//��ȡ
		if (list.size()<10) {
			pages = list;
		}else if(page2+rows >= list.size()) {
			pages = list.subList(page2, list.size());
		}else {
			pages = list.subList(page2, page2+rows);
		}
		
		//����ҳ����ʾ��־
		for (YzlLog yzlLog : pages) {
			yzlLog.setDcode(district.getCounty());
			String filename = yzlLog.getFilename();
			if (filename != null) {
//				String substring2 = null;
//				String [] file = new String[10];
				List<String> list2 = new ArrayList<>();
				//d/20190317/�ǺǺǺǺ�
				String[] split2 = filename.split(",");
				
				for (int i = 0;i<split2.length;i++) {
					int indexOf = split2[i].indexOf("/");
					String substring = split2[i].substring(indexOf+1, split2[i].length());//ȡ�õ�һ��б�ܵĺ󲿷�
					int indexOf2 = substring.indexOf("/");
					String substring2 = substring.substring(indexOf2+1, substring.length());//ȡ�õڶ���б�ܵĺ󲿷�
					list2.add("<a class='ptl'  href='#' >"+substring2+"</a>");
				}
				String string = list2.toString();
				String substring = string.substring(1, string.length()-1);
				yzlLog.setFilename(substring);
			}
			yzlLog.setHi(filename);
			String gclb = yzlLog.getGclb();//�õ��������
			String[] split2 = gclb.split(",");//�и�
			StringBuffer buffer = new StringBuffer();
			
			for (String string : split2) {
				YzlEpc epc = epcMapper.selectByMark(string);//���ݹ��̱�Ų�ѯ
				buffer.append(epc.getEname()+",");
			}
			yzlLog.setGclb(buffer.toString());
			//yzlLog.setDcode(county);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(pages);
		result.setTotal(list.size());
		return result;
	}


	/**
	 *�ļ��ϴ�
	 * @throws Exception 
	 */
	@Override
	public YzlResult upFile(MultipartFile[] fileName,HttpServletRequest request) {
		
		//����uuid
		UUID uuid = UUID.randomUUID();
		
		InputStream is = null;
		String filename = null;
		for (MultipartFile multipartFile : fileName) {
				try {
					is = multipartFile.getInputStream();
					//�õ��ļ���������
					System.out.println("inputStream=="+is);
					String contentType = multipartFile.getContentType();
					System.out.println("contentType=="+contentType);
					String name = multipartFile.getName();//�õ���������
					System.out.println("name=="+name);
					filename = multipartFile.getOriginalFilename();//�õ��ϴ����ļ�����
					System.out.println("originalFilename=="+filename);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		//��ȡ�ϴ���·��
		String path = request.getServletContext().getRealPath("/upload");

		//��ȡ��ǰ������,���õ�ǰ�����ڵ��ļ���
		Date date = new Date();
		//����ת�ַ���
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
		String format = simpleDateFormat.format(date);
		path+="/"+format;
		
		File file = new File(path);
		
		//�ж��ϴ���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			//û�оʹ���
			file.mkdirs();
		}
		
			FileOutputStream os;
			try {
				os = new FileOutputStream(file+"\\"+uuid.toString()+"="+filename);//�ļ���ǰ���uuid����
				//����һ��������
				byte bte [] = new byte[1024]; 
				
				//�ж��������е������Ƿ��Ѿ���ȡ��
				int len = 0;
//				int read = is.read(bte);
				//�����������뵽��������
				while ((len = is.read(bte)) > 0) {
					os.write(bte, 0, len);
				}
				request.getSession().setAttribute("filename",uuid.toString()+"/"+format+"/"+filename);
				//�ر�������
				is.close();
				//�ر������
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new YzlResult(400);
			}
		
		YzlResult result = YzlResult.build(200, uuid.toString()+"/"+format+"/"+filename,200);
			
		return result;
	}


	//�ļ�����
	@Override
	public YzlResult takWorkingDeriveFile(String file,HttpServletRequest request,HttpServletResponse response) {
		
		String userAgent = request.getHeader("User-Agent");
		
		String[] split = file.split(",");
		for (String string : split) {
			//4b9c5ff6-5b98-4975-876a-24d668c9dd81/20190317/�½� Microsoft Word �ĵ�.docx
			int indexOf = string.indexOf("/");
			//��ȡǰ���uuid
			String uuid = string.substring(0, indexOf);
			
			String fp = string.substring(indexOf+1,string.length());
			int indexOf2 = fp.indexOf("/");
			
			//��ȡ�ļ��е�����  �����⴮���� 20190317
			String FilePath = fp.substring(0, indexOf2);
			//�ļ�����   �½� Microsoft Word �ĵ�.docx
			String fileName = fp.substring(indexOf2+1, fp.length());
			
			//��ȡ���ص�·��
			String realPath = request.getServletContext().getRealPath("/upload"+"/"+FilePath);
			
			File file2 = new File(realPath);
			//�ж��ļ�·���Ƿ����
			if (!file2.exists() && !file2.isDirectory()) {
				return new YzlResult("�ļ�·�������ڣ���");
			}
			
			//�����ļ���������
			try {
				FileInputStream fis = new FileInputStream(realPath+"\\"+uuid+"="+fileName);
				
				if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {//�ж��Ƿ�Ϊie�����
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); 
				}else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				
				// 3.��������ͷ��һ����
				// ��̬����ļ���MIME������:
				String mimeType = request.getServletContext().getMimeType(fileName);
				response.setContentType(mimeType);
				// ����Content-Dispostion:
				response.setCharacterEncoding("utf-8");
				response.setHeader("content-disposition", "attachment;filename="+fileName);
				response.setContentType("application/x-download;");
				//��ȡ��һ����������������
				ServletOutputStream os = response.getOutputStream();
				
				//����һ��������
				byte bte [] = new byte[1024]; 
				
				//�ж��������е������Ƿ��Ѿ���ȡ��
				int len = 0;
//				int read = is.read(bte);
				//�����������뵽��������
				while ((len = fis.read(bte)) > 0) {
					os.write(bte, 0, len);
				}
				fis.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		System.out.println(file);
		return new YzlResult(200);
	}

	@Override
	public YzlResult delFile(String msg,HttpServletRequest request) {
		
		if(msg != null) {
			//4b9c5ff6-5b98-4975-876a-24d668c9dd81/20190317/�½� Microsoft Word �ĵ�.docx
			int indexOf = msg.indexOf("/");
			//uuid
			String uuid = msg.substring(0, indexOf);//��ȡ��һ���� 4b9c5ff6-5b98-4975-876a-24d668c9dd81
			
			String substring2 = msg.substring(indexOf+1, msg.length());//��ȡ������� 20190317/�½� Microsoft Word �ĵ�.docx
			int indexOf2 = substring2.indexOf("/");
			//�ļ��е�����
			String folderName = substring2.substring(0, indexOf2);//��ȡ��� 20190317
			
			//�ļ���
			String fileName = substring2.substring(indexOf2+1, substring2.length());
			
			//��ȡ�ļ���·��
			String realPath = request.getServletContext().getRealPath("/upload"+"/"+folderName);
			
			File file = new File(realPath+"/"+uuid.toString()+"="+fileName);
			boolean delete = file.delete();
			if (delete) {
				return new YzlResult(200);
			}
		}
		return new YzlResult(400);
	}

	@Override
	public YzlDistrict code(String disCode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(disCode));
		return district;
	}

	@Override
	public YzlDistrict findByFlag(String flag) {
		YzlDistrict district = districtMapper.selectByFlag(flag);
		return district;
	}

	@Override
	public List<YzlProceed> proceed(String[] gclbs,String county,String year) {
		
		YzlDistrict district = districtMapper.selectByNumber(county);
		
		List<String> stats = new ArrayList<>();
		stats.add("0");
		stats.add("1");
		stats.add("3");
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
		for (String string : gclbs) {
			hashSet.add(string);
		}
		
		List<YzlProceed> proceeds = new ArrayList<>();
//		System.out.println("district.getAnumber()============"+district.toString()+"year=="+year);
		
		for (String gclb : hashSet) {
			System.out.println("zllb============"+gclb);
			List<YzlEpcTaskProgress> epcTaskProgresses = epcTaskProgressMapper.selectByProceed(gclb,district.getAnumber(),year,stats);
			for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
				String gclb2 = yzlEpcTaskProgress.getGclb();
				YzlEpc epc = epcMapper.selectByMark(gclb2);
				YzlProceed proceed = new YzlProceed();
				proceed.setName(epc.getEname());
				proceed.setStat(yzlEpcTaskProgress.getStat());
				proceed.setNameAnumber(epc.getMark());
				proceed.setGclb(yzlEpcTaskProgress.getGclb());
				proceeds.add(proceed);
			}
		}
		return proceeds;
	}

}
