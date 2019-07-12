package com.yzl.planManagementService.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.MonitoringstatisticsService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.trees;

@Service
@Transactional
public class MonitoringstatisticsServiceImpl implements MonitoringstatisticsService{

	@Autowired
	private YzlMenuMapper menuMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlXbMapper xbMapper;
	
	private static List<List<YzlMonitoringstatistics>> list = new ArrayList<>();
	
	//װС������
	private static List<List<YzlXb>> xbLists = new ArrayList<>();
	
//	private static String gcs = null;
//	private static String zls = null;
	private static String mak = "mak";

	@Override
	public List<YzlTask> findByEcode(String epcode) {
		List<YzlTask> tasks = menuMapper.selectByEcode(Integer.valueOf(epcode));
		return tasks;
	}

	//ҳ����ߵ����ı��
	@Override
	public EasyUIResult show(Integer page, Integer rows, String did, String epcode, String tpcode, String time) {
		
		List<YzlMonitoringstatistics> list=new ArrayList<>();
		YzlUser user = getUser();
		//PageHelper.startPage(page, rows);
		
		List<YzlDistrict> flag=new ArrayList<>();
		
		if (user.getUsername().equals("mark")) {//Ϊϵͳ����Ա
			//��ѯ������
			flag = districtMapper.selectFindAllFlag();
		}else{
			
			//��ż��Ȩ�޵ļ���
			List<YzlMenu> mList = new ArrayList<>();
			
			//�����û���ȡ����Ӧ��Ȩ��
			List<YzlMenu> menus = epcTaskProgressMapper.findByUid(Integer.valueOf(String.valueOf(user.getId())));
			
			for (YzlMenu yzlMenu : menus) {//�������е�Ȩ��
				String perms = yzlMenu.getPerms();
				if (perms!=null) {
					if (perms.contains("JC")) {//���Ȩ������JC����ӵ�������
						mList.add(yzlMenu);
					}
				}
			}
			
			//�������ͳ�ƵĶ���
			YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
			//���Ȩ�޵����ļ���
			List<YzlDistrict> districts=new ArrayList<>();
			for (YzlMenu yzlMenu : mList) {//�������û�ӵ�еļ��Ȩ��
				
				String perms = yzlMenu.getPerms();//��ȡȨ�ޱ�ʶ
				String substring = perms.substring(0, 2);//��ȡ
				
				if (!substring.equals("JC")) {//��ΪJC˵��������
					
					YzlDistrict dYzlDistrict = new YzlDistrict();
					//��ȡ�س����ŵ�������
					String sub = yzlMenu.getName();
					String county = sub.substring(sub.indexOf("��")+1, sub.length());
					
					dYzlDistrict.setCounty(county);
					districts.add(dYzlDistrict);
				}else {//������
					//��ȡ������
					monitoringstatistics.setCity(yzlMenu.getName());
					YzlDistrict district = new YzlDistrict();
					flag.add(district);
				}
			}
			monitoringstatistics.setCountys(districts);
			list.add(monitoringstatistics);
			//System.out.println();
		}
		
		
		//PageInfo<YzlMonitoringstatistics> pageInfo=new PageInfo<>(list);
//		int i= 1/0;
		EasyUIResult result=new EasyUIResult();
		
		result.setRows(list);
		result.setTotal(flag.size());
		
		return result;
	}

	@Override
	public List<YzlDistrict> sel() {
		List<YzlDistrict> list = districtMapper.selectFindAllFlag();
		return list;
	}

	//�����ز�ѯ����
	@Override
	public List<YzlEpc> findByCity(String county) {
		List<YzlEpc> epcs =  epcTaskProgressMapper.selectByCounty(county);
		return epcs;
	}

	@Override
	public List<YzlTask> findByEcodeAndDname(String ecode, String county) {
		
		List<YzlTask> list = epcTaskProgressMapper.selectByEcodeAndDname(ecode,county);
		
		return list;
	}

	//ҳ���ұߵ����ݱ��
	@Override//ecode ���̱��  county ��  tname �ֶ���
	public EasyUIResult findByTEC(String ecode,String county,String tname,Integer rows,Integer page,String time) {
		time="2018";
		System.out.println(time+"--------------");
		//System.out.println(ecode+county+tname);
		YzlUser user = getUser();//��ȡ��ǰ��¼�û�
		//���������Ʋ�ѯ
		YzlDistrict district = districtMapper.selectByCounty(county);
		//���������ѯ
		YzlTask task = taskMapper.selectByTname(tname);
		
		//��ǰҳ����  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
		int page2 = (page-1)*rows;
		
		//�ܼ�¼��
		//List<YzlEpcTaskProgress> ETPList=new ArrayList<>();
		List<YzlMonitoringstatistics> ETPList=new ArrayList<>();
		YzlMonitoringstatistics m=new YzlMonitoringstatistics();
		//��ҳ��ѯ�Ľ��
		//List<YzlEpcTaskProgress> list=new ArrayList<>();
		List<YzlMonitoringstatistics> list=new ArrayList<>();
		//Ϊϵͳ����Ա
		//if (user.getUsername().equals("mark")) {
			//�ܼ�¼��
			//ETPList = epcTaskProgressMapper.selectAll(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()));
			//ecode���̱�� ��dcode������ţ�tcode������
			//��ȡһ�������
			List<YzlMonitoringstatistics> monitoringstatistics = epcTaskProgressMapper.selectAlls(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),time);
			
			YzlMonitoringstatistics yzlMonitoringstatisticss = new YzlMonitoringstatistics();
			
			
			Integer inspectionreportareaSum=0;//�ϱ����
			Integer verifyAreaSum=0;//��ʵ���
			Integer certifiedAreaSUm=0;//��ʵ�ϸ����
			Integer designForestAreaSum=0;//����������
			Integer verifyForestAreaSum=0;//��ʵ�������
			Integer CLMJSum=0;//�������	
			Integer fileSizeSum=0;//�е������;
			Integer countyInspectionAreaSum=0;//��չ�ؼ��Լ����
			Integer managementAreaSum=0;//�ܻ����
			
			for (YzlMonitoringstatistics yzlMonitoringstatistics : monitoringstatistics) {//����һ�������
				//�õ�ÿһ��С����ϱ����
				String inspectionreportarea = yzlMonitoringstatistics.getInspectionreportarea();
				if (StringUtils.isNotBlank(inspectionreportarea)) {
					inspectionreportareaSum+=Integer.valueOf(inspectionreportarea);
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getVerifyarea())) {
					verifyAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getVerifyarea());
				}//CertifiedArea
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getCertifiedarea())) {
					certifiedAreaSUm+=Integer.valueOf(yzlMonitoringstatistics.getCertifiedarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getDesignforestarea())) {
					designForestAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getDesignforestarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getVerifyforestarea())) {
					verifyForestAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getVerifyforestarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getClmj())) {
					CLMJSum+=Integer.valueOf(yzlMonitoringstatistics.getClmj());
				}
				/*if (StringUtils.isNotBlank(yzlMonitoringstatistics.getFilesize())) {
					fileSizeSum+=Integer.valueOf(yzlMonitoringstatistics.getFilesize());
				}*/
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getCountyinspectionarea())) {
					countyInspectionAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getCountyinspectionarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getManagementarea())) {
					managementAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getManagementarea());
				}
			}
			
			//�����ʵ��areaVerificationRate �����ʵ��=2��ʵ���  / 1�Լ��ϱ����
			if(inspectionreportareaSum > 0 ) {
				Double as = (double) (verifyAreaSum/inspectionreportareaSum);
				m.setAreaverificationrate(String.valueOf(as*100));
			}
			
			//verifyAreaPassRate��ʵ����ϸ���=3��ʵ�ϸ���� / 2��ʵ���
			if (verifyAreaSum>0) {
				Double as = (double) (certifiedAreaSUm/verifyAreaSum);
				m.setVerifyareapassrate(String.valueOf(as*100));
			}
			//reportAreaQualificationRate�ϱ�����ϸ���=3��ʵ�ϸ���� / 1�Լ��ϱ����
			if(inspectionreportareaSum>0) {
				Double as = (double) (certifiedAreaSUm/inspectionreportareaSum);
				m.setReportareaqualificationrate(String.valueOf(as*100));
			}
			//���������ϱ��ϸ����= ȫ���ۼӡ�3��ʵ�ϸ������
			//countySelfInspectionAreaȫ���Լ�ϸ����=���С�5��չ�ؼ��Լ������ �ۼ�
			m.setCountyselfinspectionarea(String.valueOf(countyInspectionAreaSum));
			
			//calculatedVerificationArea15.�����ʵ���= ȫ���ۼӡ�2��ʵ�����
			m.setCalculatedverificationarea(String.valueOf(verifyAreaSum));
			//�����Ч�ϸ����= ȫ���ۼ�"�������"(������������ݱ����У��˱��δʹ�õ���)
			//�����ϱ���Ч�ϸ���=16�����Ч�ϸ���� / 13���������ϱ��ϸ����
			//fileRate������=3�е������ / 1�Լ��ϱ����
			if (inspectionreportareaSum>0) {
				Double as = (double) (fileSizeSum/inspectionreportareaSum);
				m.setFilerate(String.valueOf(as*100));
			}
			
			//selfCheckingRate�Բ���=2��ʵ��� / 1�Լ��ϱ����
			if(inspectionreportareaSum>0) {
				Double as = (double) (verifyAreaSum/inspectionreportareaSum);
				m.setSelfcheckingrate(String.valueOf(as*100));
			}
			
			//afforestationRate������=7��ʵ������� / 6����������
			if (designForestAreaSum>0) {
				Double as = (double) (verifyForestAreaSum/designForestAreaSum);
				m.setAfforestationrate(String.valueOf(as*100));
			}
			
			//theManagementRate�ܻ���=8�ܻ���� / 2��ʵ���
			if(verifyAreaSum>0) {
				Double as = (double) (managementAreaSum/verifyAreaSum);
				m.setManagementarea(String.valueOf(as*100));
			}
			//System.out.println("�ܼ�¼��"+ETPList.size());
			//��ҳ��ѯ
			//list = epcTaskProgressMapper.selectAllPage(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2);
			//List<YzlMonitoringstatistics> monitoringstatisticsList = epcTaskProgressMapper.selectAllPages(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2,time);
			//System.out.println("���ϴ�С"+list.size());
	//	}else {
			//�����û�id��ѯ����û�ӵ�е����м�¼
			//ETPList = epcTaskProgressMapper.selectByUidAndL(user.getId(),ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()));
			
			//��ҳ��ѯ
			//list = epcTaskProgressMapper.selectByUidAndLPage(user.getId(),ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2);
		//}
		
		//List<YzlMonitoringstatistics> monitoringstatistics = new ArrayList<>();
		//��ȡ��������·�������
		/*for (YzlEpcTaskProgress yzlEpcTaskProgress : list) {
			
			
			//��������
			YzlMonitoringstatistics yzlMonitoringstatistics = new YzlMonitoringstatistics();
			
			//�õ��·����������
			Float taskprogress = yzlEpcTaskProgress.getTaskprogress();
			
			//ģ���   �����ʵ���
			Float calculatedverificationarea = taskprogress-10;
			
			//���������  =  �����ʵ���  ����    �ƻ�����
			Double taskcompletionrate = Double.valueOf(String.valueOf(calculatedverificationarea))/Double.valueOf(String.valueOf(taskprogress));
			
			//���԰ٷֱȺ�
			String of = String.valueOf(taskcompletionrate*100);
			
			if (of.length()==4) {
				yzlMonitoringstatistics.setTaskcompletionrate(of);
			}else {
				//��ȡǰ4λ
				 String substring = of.substring(0, 4);
				 
				 //��ȡ�����
				 String d5g = of.substring(4, 5);
				 
				 //�ж��Ƿ���ڵ���5��������
				 if (Integer.valueOf(d5g)>=5) {
					BigDecimal avs=new BigDecimal(substring);
					BigDecimal av = avs.add(new BigDecimal(String.valueOf(0.1)));
					yzlMonitoringstatistics.setTaskcompletionrate(String.valueOf(av));
				}else {
					yzlMonitoringstatistics.setTaskcompletionrate(substring);
				}
			}
			String substring = yzlEpcTaskProgress.getCreateTimetoString().substring(0, 4);
			yzlMonitoringstatistics.setTime(substring);
			yzlMonitoringstatistics.setScheduledtask(String.valueOf(taskprogress));
			yzlMonitoringstatistics.setCalculatedverificationarea(String.valueOf(calculatedverificationarea));
			
			//��ӵ�������
			monitoringstatistics.add(yzlMonitoringstatistics);
		}*/
			monitoringstatistics.add(m);
		EasyUIResult result = new EasyUIResult();
		result.setRows(monitoringstatistics);
		result.setTotal(ETPList.size());
		
		return result;
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
	
	//����
	@Override
	public String text(String name) {
		return name;
	}

	//��
	@Override
	public List<trees> show_trees() {
		
		//��ȡ�û���Ӧ�õ�Ȩ��
		List<String> permsList = getMenu();
		
		//��ѯ������ȥ��
		List<YzlDistrict> citys = districtMapper.selectByDisinctCity(permsList);
		
		List<trees> zjqList = new ArrayList<>();
		
		//����������
		trees zjq = new trees();
		
		zjq.setId("GX450");
		zjq.setText("����׳��������");
		zjq.setState("open");
		
		//����е�������
		List<trees> cityList = new ArrayList<>();
		
		for (YzlDistrict yzlDistrict : citys) {//�������е���
			
			//����
			trees cityTree = new trees();
			
			cityTree.setText(yzlDistrict.getCity());
			cityTree.setId(yzlDistrict.getFlag());
			
			
			//�����в�ѯ�����е���
			List<YzlDistrict> countys = districtMapper.selectByCity(yzlDistrict.getCity(),permsList);
			
			if (countys.size()==1 || citys.size()==1) {//���ֻ��һ����ֱ�Ӵ򿪽ڵ�esle����
				cityTree.setState("open");
			}else {
				cityTree.setState("closed");
			}
			//��������ļ���
			List<trees> tList = new ArrayList<>();
			
			for (YzlDistrict yzlDistrict2 : countys) {
				
				//����
				trees countyTree = new trees();
				
				countyTree.setId(String.valueOf(yzlDistrict2.getDcode()));
				countyTree.setText(yzlDistrict2.getCounty());
				tList.add(countyTree);
			}
			
			cityTree.setChildren(tList);
			cityList.add(cityTree);
		}
		zjq.setChildren(cityList);
		zjqList.add(zjq);
		
		return zjqList;
	}

	//sname������
	//������
	
	@Override
	public EasyUIResult monitMunicipality(String sname,String time,String stime,Integer page, Integer rows,String cityCode,String zllb,String gclb) {
//		int i = 1/0;
		//��ռ��ϵ�����
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		//��ȡ��ǰ��¼�û���Ӧ��Ȩ��
		List<String> permsList = getMenu();
		//���ͳ��
		List<YzlMonitoringstatistics> monit = new ArrayList<>();
		
		//��ǰҳ����  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
		int page2= (page - 1)*rows;
		String times = time;
		
		if (stime!=null && !"null".equals(stime)) {
			times = stime;
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//�ܼ�¼��
		List<YzlXb> total = xbMapper.selecTtotalMunicipality(sname,times,permsList,cityCode,zllb,gclb);
		xbLists.add(total);//���
		
		//��ѯ���и��ݵ�������ȷ�������

		List<YzlXb> xbList = xbMapper.selectByGropMunicipality(sname,times,permsList,page2,rows,cityCode,zllb,gclb);
		
		//����
		long currentTimeMillis = System.currentTimeMillis();
		String mark = "qu";
		monit = getMonit(xbList,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"��");
		
		List<YzlMonitoringstatistics> mo = new ArrayList<>();
		//List<YzlXb> list2 = xbLists.get(0);
		
		for (YzlMonitoringstatistics  yzlmo : monit) {
			mo.add(yzlmo);
		}
		
		//��ҳ
		EasyUIResult result = getResult(mo, Long.valueOf(total.size()));
		
		return result;
	}

	
	//��
	@Override
	public EasyUIResult moniCity(String flag,Integer page,Integer rows,String sname,String stime,String times,String cityCode,String zllb,String gclb) {
		
		//��ռ��ϵ�����
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		
		//��ȡ�û���Ӧ��Ȩ��
		List<String> permsList = getMenu();
		
		if (!mak.equals(flag)) {
			page = 1;
		}
		System.out.println("2mak:="+mak);
		System.out.println("page:="+page);
		System.out.println("flag:="+flag);
		System.out.println("times:="+times);
		//��ѯ��������е���
		List<YzlDistrict> countyList = districtMapper.selectByFlags(flag,permsList);
		
		//��ȡ�е��������
		String anumber = null;
		if (countyList.size()>0) {
			YzlDistrict district = countyList.get(0);
			String number = district.getAnumber();
			anumber = number.substring(0, 4);
		}
		
		//���ͳ��
		List<YzlMonitoringstatistics> monitoringstatisticsList = new ArrayList<>();
		
		//��ǰҳ����  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
		int page2= (page - 1)*rows;
		mak = flag;
		
		String string = times;
		if (stime!=null && !"null".equals(stime)) {
			string = stime;
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//�ܼ�¼��
		List<YzlXb> total = xbMapper.selecTtotalCity(anumber,sname,string,permsList,cityCode,zllb,gclb);
		xbLists.add(total);
		
		//��ѯ���и��ݵ�������ȷ�������
		List<YzlXb> xbList = xbMapper.selectByGropCity(anumber,sname,string,permsList,page2,rows,cityCode,zllb,gclb);
		
		long currentTimeMillis = System.currentTimeMillis();
		String mark  = "sh";
		//����....
		monitoringstatisticsList = getMonit(xbList,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"��");
		
		//��ǰҳ����  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
		
		System.out.println(monitoringstatisticsList.size());
		
		//PageInfo<YzlXb> pageInfo = new PageInfo<>(xbList);
		EasyUIResult result = getResult(monitoringstatisticsList, Long.valueOf(total.size()));
		
		return result;
	}
	
	//��
	@Override//dcode�صı��
	public EasyUIResult monitCounty(String dcode, Integer page, Integer rows,String sname,String stime,String time,String zllb,String gclb) {
		
		//��ռ���
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		
		//�����ر�Ų�ѯ��Ӧ���������
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		String anumber = district.getAnumber();
		System.out.println(anumber+"=======");
		//�������
		String number = null;
		if (district!=null) {
			number = district.getAnumber();
		}
		System.out.println(number);
		//��ǰҳ����  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
		int page2= (page - 1)*rows;
		
		String string = time.trim();
		if (stime != null && !"null".equals(stime)) {
			string = stime.trim();
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//�����ز�ѯÿһ�������Ӧ�������ܼ�¼��
		List<YzlXb> xbTotal = xbMapper.selecTtotalCounty(number,sname,string,zllb,gclb);
		xbLists.add(xbTotal);
		
		//�����ز�ѯÿһ�������Ӧ������
		List<YzlXb> xbs = xbMapper.selectByGropCounty(number,sname,string,page2,rows,zllb,gclb);
		
		long currentTimeMillis = System.currentTimeMillis();
		String mark  = "xian";
		List<YzlMonitoringstatistics> monit = getMonit(xbs,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"��");
		//��ҳ
		EasyUIResult result = getResult(monit, Long.valueOf(xbTotal.size()));
		
		return result;
	}
	
	//��
	private String getDB(Float args1,Float args2) {
		Float areaVer = args1/args2;
		return String.format("%.2f", areaVer*100);
	}
	
	//����
	private List<YzlMonitoringstatistics> getMonit(List<YzlXb> xbList,String mark) {
		
		List<YzlMonitoringstatistics> monitoringstatisticsList = new ArrayList<>();
		
//		String zh = null;
		String db = null;
//		String gc = null;
//		String zl = null;
//		String county = null;
		String time = null;
		List<YzlEpcTaskProgress> yzlEpcTaskProgresses = new ArrayList<>();
		List<YzlXb> xbs = new ArrayList<>();
		Float inspectionReportAreaAndSfydaSum=0f;//�����Ƿ��е���ͳ���ϱ����
		Float inspectionReportAreaSumAndSfzjSum = 0f;//�����Ƿ�չ�ؼ��Լ����ͳ���ϱ����
		Float inspectionReportAreaAndSfghSum = 0f;//�����Ƿ�ܻ����ͳ���ϱ����
		YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
		
		String countyCode = null;
		String gclb = null;
		String zllb = null;
		YzlDistrict district = null;
		YzlEpc epc = null;
		YzlTask task = null;
		//�ƻ����� 
		Float taskprogress = 0F;
		//һ����һ����ȵ���ͳ��
		Float inspectionReportAreaSum = 0F;//�Լ��ϱ�ͳ��
		Float verifyAreaSum = 0f;//��ʵ���
		Float certifiedAreaSum = 0f;//��ʵ�ϸ����
		Float designForestAreaSum = 0f;//����������
		Float verifyForestAreaSum = 0f;//��ʵ�������
		Float clmjSum = 0f;//�������
		String ccty = null;
		String city = null;
		
		for (YzlXb yzlXbs : xbList) {
					
			gclb = yzlXbs.getGclb();
			zllb = yzlXbs.getZllb();
			countyCode = yzlXbs.getCounty();
			 time = yzlXbs.getZynd();
			  city = yzlXbs.getCity();
			  
			if ("qu".equals(mark)) {
				ccty = city;
			}else {
				ccty = countyCode;
			}
			 
			//��ѯ�·�������
			 yzlEpcTaskProgresses = epcTaskProgressMapper.selectTaskIssued(gclb,zllb,ccty,time);

			//��ѯĳ����֪����ȵ�С��
			 xbs = xbMapper.selectByCountyAndTime(ccty,gclb,zllb,time);
			
			if (xbs.size()>0) {
				
				 monitoringstatistics = new YzlMonitoringstatistics();
				
				
				if(yzlEpcTaskProgresses.size() > 0 && yzlEpcTaskProgresses.get(0) != null) {
					taskprogress = yzlEpcTaskProgresses.get(0).getTaskprogress();
				}
						
				monitoringstatistics.setScheduledtask(taskprogress.toString());
				
				
				String xtjsbmj = yzlXbs.getXtjsbmj();
				if (StringUtils.isNotBlank(xtjsbmj)) {
					inspectionReportAreaSum = Float.valueOf(xtjsbmj);
				}
				
				
				String hsmj = yzlXbs.getHsmj();
				if (StringUtils.isNotBlank(hsmj)) {
					verifyAreaSum = Float.valueOf(hsmj);
				}
				
				String hgmj = yzlXbs.getHgmj();
				if (StringUtils.isNotBlank(hgmj)) {
					certifiedAreaSum = Float.valueOf(hgmj);
				}
				
				
				String sjylmj = yzlXbs.getSjylmj();
				if (StringUtils.isNotBlank(sjylmj)) {
					designForestAreaSum = Float.valueOf(sjylmj);
				}
				
				
				String hsylmj = yzlXbs.getHsylmj();
				if (StringUtils.isNotBlank(hsylmj)) {
					verifyForestAreaSum = Float.valueOf(hsylmj);
				}
				
				
				String clmj = yzlXbs.getClmj();
				if (StringUtils.isNotBlank(clmj)) {
					clmjSum = Float.valueOf(clmj);
				}
				
//				Float inspectionReportAreaAndSfydaSum=0f;//�����Ƿ��е���ͳ���ϱ����
//				Float inspectionReportAreaSumAndSfzjSum = 0f;//�����Ƿ�չ�ؼ��Լ����ͳ���ϱ����
//				Float inspectionReportAreaAndSfghSum = 0f;//�����Ƿ�ܻ����ͳ���ϱ����
				
				for (YzlXb yzlXb : xbs) {
					
					 countyCode = yzlXb.getCounty();//�����صõ���
					
					 gclb = yzlXb.getGclb();//�������
					
					 zllb = yzlXb.getZllb();//�������
					
					if (StringUtils.isNotBlank(countyCode)) {
						 district = districtMapper.selectByNumber(countyCode);
						monitoringstatistics.setCity(district.getCity());
					}
					if (StringUtils.isNotBlank(gclb)) {
						 epc = epcMapper.selectByMark(gclb);
						monitoringstatistics.setEname(epc.getEname());
					}
					if (StringUtils.isNotBlank(zllb)) {
						 task = taskMapper.selectByMark(zllb);
						monitoringstatistics.setTname(task.getTname());
					}
					
					//fileSize�е������=���С��Ƿ��е�������ʶʱ��ʵ�ʾ���1���Լ��ϱ���������������0
					if (yzlXb.getSfyda() != null && yzlXb.getSfyda() != "0") {//�����Ƿ��е���ͳ���ϱ����
						inspectionReportAreaAndSfydaSum += Float.valueOf(yzlXb.getXtjsbmj());
					}
					//��չ�ؼ��Լ����=���С��Ƿ�չ�ؼ��Լ��������ʶʱ��ʵ�ʾ���1���Լ��ϱ���������������0
					if (yzlXb.getSfzj()!=null && yzlXb.getSfzj() != "0") {
						inspectionReportAreaSumAndSfzjSum += Float.valueOf(yzlXb.getXtjsbmj());
					}
					//�ܻ����=���С��Ƿ�ܻ��������ʶʱ��ʵ�ʾ���1���Լ��ϱ���������������0
					if (yzlXb.getSfgh() !=null && yzlXb.getSfgh() != "0") {
						inspectionReportAreaAndSfghSum+=Float.valueOf(yzlXb.getXtjsbmj());
					}
				}
				
				monitoringstatistics.setFilesize(inspectionReportAreaAndSfydaSum.toString());//�е������
				monitoringstatistics.setCountyinspectionarea(inspectionReportAreaSumAndSfzjSum.toString());//��չ�ؼ��Լ����countyInspectionArea
				monitoringstatistics.setManagementarea(inspectionReportAreaAndSfghSum.toString());//�ܻ����managementArea
				
				//���������  ����   �ϰ�������Լƻ�����
 				//Float taskCompletionRate = 0f;
 				if (taskprogress != null && taskprogress != 0f) {//StringUtils.isNotBlank(taskprogress.toString())
 					 db = getDB(inspectionReportAreaSum,taskprogress);
// 					String zh = zh(db);
 					monitoringstatistics.setTaskcompletionrate(db);
				}else {
					monitoringstatistics.setTaskcompletionrate("0.0");
				}
 				
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {//areaVerificationRate�����ʵ��=2��ʵ���  / 1�Լ��ϱ����
					 db = getDB(verifyAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);Float areaVer = args2/args1;
					monitoringstatistics.setAreaverificationrate(db);//areaVerificationRate�����ʵ��=2��ʵ���  / 1�Լ��ϱ����
				}else {
					monitoringstatistics.setAreaverificationrate("0.0");
				}
				//verifyAreaPassRate��ʵ����ϸ���=3��ʵ�ϸ���� / 2��ʵ���
				if (verifyAreaSum != 0 && verifyAreaSum != null) {
					 db = getDB(certifiedAreaSum, verifyAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setVerifyareapassrate(db);
				}else {
					monitoringstatistics.setVerifyareapassrate("0.0");
				}
				//reportAreaQualificationRate�ϱ�����ϸ���=3��ʵ�ϸ���� / 1�Լ��ϱ����
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(certifiedAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setReportareaqualificationrate(db);
				}else {
					monitoringstatistics.setReportareaqualificationrate("0.0");
				}
				//afforestationReportsEligibleYear���������ϱ��ϸ����= ȫ���ۼӡ�3��ʵ�ϸ������
				monitoringstatistics.setAfforestationreportseligibleyear(certifiedAreaSum.toString());
				//countySelfInspectionAreaȫ���Լ�ϸ����=���С�5��չ�ؼ��Լ������ �ۼ�
				monitoringstatistics.setCountyselfinspectionarea(inspectionReportAreaSumAndSfzjSum.toString());
				//calculatedVerificationArea�����ʵ���= ȫ���ۼӡ�2��ʵ�����
				monitoringstatistics.setCalculatedverificationarea(verifyAreaSum.toString());
				//calculateQualifiedArea�����Ч�ϸ����= ȫ���ۼ�"�������"(������������ݱ����У��˱��δʹ�õ���)
				monitoringstatistics.setCalculatequalifiedarea(clmjSum.toString());
				//�����ϱ���Ч�ϸ���=16�����Ч�ϸ���� / 13���������ϱ��ϸ����
				
				//fileRate������=3�е������ / 1�Լ��ϱ����
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(inspectionReportAreaAndSfydaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setFilerate(db);
				}else {
					monitoringstatistics.setFilerate("0.0");
				}
				
				//selfCheckingRate�Բ���=2��ʵ��� / 1�Լ��ϱ����
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(verifyAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setSelfcheckingrate(db);
				}else {
					monitoringstatistics.setSelfcheckingrate("0.0");
				}
				//afforestationRate������=7��ʵ������� / 6����������
				if (designForestAreaSum != 0 && designForestAreaSum != null) {
					 db = getDB(verifyForestAreaSum, designForestAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setAfforestationrate(db);
				}else {
					monitoringstatistics.setAfforestationrate("0.0");
				}
				//theManagementRate�ܻ���=8�ܻ���� / 2��ʵ���
				if (verifyAreaSum != 0 && verifyAreaSum != null) {
					 db = getDB(inspectionReportAreaAndSfghSum, verifyAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setThemanagementrate(db);
				}else {
					monitoringstatistics.setThemanagementrate("0.0");
				}
				if (StringUtils.isNotBlank(time)) {
					monitoringstatistics.setTime(time);
				}
				
				//inspectionReportArea�Լ��ϱ�ͳ��
				monitoringstatistics.setInspectionreportarea(inspectionReportAreaSum.toString());
				//verifyArea��ʵ���
				monitoringstatistics.setVerifyarea(verifyAreaSum.toString());
				//certifiedArea��ʵ�ϸ����
				monitoringstatistics.setCertifiedarea(certifiedAreaSum.toString());
				//designForestArea����������
				monitoringstatistics.setDesignforestarea(designForestAreaSum.toString());
				//verifyForestArea��ʵ�������
				monitoringstatistics.setVerifyforestarea(verifyForestAreaSum.toString());
				//���ݲ�ѯ������
				 district = districtMapper.selectByNumber(countyCode);
				monitoringstatistics.setCounty(district.getCounty());
				monitoringstatisticsList.add(monitoringstatistics);
			}
		}
		return monitoringstatisticsList;
	}
	
	//��ҳ����
	private EasyUIResult getResult(List<YzlMonitoringstatistics> list,Long total) {
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	//�����������
	@Override
	public List<YzlTask> zllb() {
		List<YzlTask> tasks = taskMapper.selectshow();
		return tasks;
	}
	
	//���й������
	@Override
	public List<YzlEpc> gclb() {
		List<YzlEpc> epcs = epcMapper.gclb();
		return epcs;
	}
	
	//����
	@Override//discod 1����2�У�3��  sname������stime����ʱ�䣬puptimeϵͳʱ��
	public YzlResult deriveMunicipality(HttpServletResponse response,String discod,String sname,String stime,String puptime,String qccty) throws IOException{
		
		if("1".equals(discod)) {
			EasyUIResult monitMunicipality = monitMunicipality(sname, puptime, stime, 1, 10,null,null,null);
			System.out.println(monitMunicipality);
		}
		if("2".equals(discod)) {
			EasyUIResult moniCity = moniCity(qccty, 1, 10, sname, stime, puptime,null,null,null);
			System.out.println(moniCity);
		}
		if("3".equals(discod)) {
			EasyUIResult monitCounty = monitCounty(qccty, 1, 10, sname, stime, puptime,null,null);
			System.out.println(monitCounty);
		}
		List<YzlXb> xxb = new ArrayList<>();
		
		for (List<YzlXb> list2 : xbLists) {
			for (YzlXb yzlXb : list2) {
				xxb.add(yzlXb);
			}
		}
		
		List<YzlMonitoringstatistics> monit = getMonit(xxb,null);
		
		HSSFWorkbook workbook = new HSSFWorkbook();//��������������wxcel�ĵ�����
		
		HSSFSheet sheet = workbook.createSheet();//����������
//		sheet.setColumnWidth(0, 4*256);//���õ�Ԫ��Ŀ�
		
		HSSFRow row = sheet.createRow(1);//��sheet�ﴴ����
		HSSFCell cell0 = row.createCell(0);//�����ﴴ����
		row.setHeightInPoints(30);//�и�
		
		//���õ�Ԫ������
		cell0.setCellValue("���ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ����ֱ���");
		//�ϲ���Ԫ��//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 35));
		
		HSSFRow row1 = sheet.createRow(3);//��sheet�ﴴ����
		
		//�����ﴴ����
		HSSFCell cell1 = row1.createCell(1);//������
		cell1.setCellValue("��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 1, 1));
		
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellValue("��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 2, 2));
		
		HSSFCell cell3 = row1.createCell(3);
		cell3.setCellValue("������� ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 3, 3));
		
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellValue("������� ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 4, 4));
		
		HSSFCell cell5 = row1.createCell(5);
		cell5.setCellValue("ʱ��");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 5, 5));
		
		HSSFCell cell6 = row1.createCell(6);
		cell6.setCellValue("������");//�Լ��ϱ����
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 6, 16));//�ϲ���
		
		HSSFCell cell7 = row1.createCell(17);
		cell7.setCellValue("�˲��������");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 17, 19));
		
		HSSFCell cell8 = row1.createCell(20);
		cell8.setCellValue("����������");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 20, 26));
		
		HSSFCell cell9 = row1.createCell(27);
		cell9.setCellValue("����ָ��");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 27, 35));
		
		//�ڽ�һ��
		HSSFRow row2 = sheet.createRow(5);//��sheet�ﴴ����
		
		HSSFCell cell10 = row2.createCell(6);
		cell10.setCellValue("�Լ��ϱ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 6, 6));
		
		HSSFCell cell11 = row2.createCell(7);
		cell11.setCellValue("��ʵ���  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 7, 7));
		
		HSSFCell cell12 = row2.createCell(8);
		cell12.setCellValue("��ʵ�ϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 8, 8));
		
		HSSFCell cell13 = row2.createCell(9);
		cell13.setCellValue("��ҵ������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));
		
		HSSFCell cell14 = row2.createCell(10);
		cell14.setCellValue("����ҵ���ʩ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 10, 10));
		
		HSSFCell cell15 = row2.createCell(11);
		cell15.setCellValue("�е������");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 11, 11));
		
		HSSFCell cell16 = row2.createCell(12);
		cell16.setCellValue("��չ�ؼ��Լ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 12, 12));
		
		HSSFCell cell17 = row2.createCell(13);
		cell17.setCellValue("���������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 13, 13));
		
		HSSFCell cell18 = row2.createCell(14);
		cell18.setCellValue("��ʵ������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 14, 14));
		
		HSSFCell cell19 = row2.createCell(15);
		cell19.setCellValue("�������  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 15, 15));
		
		HSSFCell cell20 = row2.createCell(16);
		cell20.setCellValue("�ܻ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 16, 16));
		
		
		HSSFCell cell21 = row2.createCell(17);
		cell21.setCellValue("�����ʵ��  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 17, 17));
		
		HSSFCell cell22 = row2.createCell(18);
		cell22.setCellValue("��ʵ����ϸ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 18, 18));
		
		HSSFCell cell23 = row2.createCell(19);
		cell23.setCellValue("�ϱ�����ϸ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 19, 19));
		
		
		HSSFCell cell24 = row2.createCell(20);
		cell24.setCellValue("�����ϱ�������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 20, 20));
		
		HSSFCell cell25 = row2.createCell(21);
		cell25.setCellValue("���ֵ����ϱ��ϸ� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 21, 21));
		
		HSSFCell cell26 = row2.createCell(22);
		cell26.setCellValue("ȫ���Լ�ϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 22, 22));
		
		HSSFCell cell27 = row2.createCell(23);
		cell27.setCellValue("�����ʵ���");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 23, 23));
		
		HSSFCell cell28 = row2.createCell(24);
		cell28.setCellValue("������ɺϸ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 24, 24));
		
		HSSFCell cell29 = row2.createCell(25);
		cell29.setCellValue("�ƻ�����  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 25, 25));
		
		HSSFCell cell30 = row2.createCell(26);
		cell30.setCellValue("��������� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 26, 26));
		
		
		HSSFCell cell31 = row2.createCell(27);
		cell31.setCellValue("��ҵ�����");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 27, 27));
		
		HSSFCell cell32 = row2.createCell(28);
		cell32.setCellValue("�����ʩ���� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 28, 28));
		
		HSSFCell cell33 = row2.createCell(29);
		cell33.setCellValue("������  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 29, 29));
		
		HSSFCell cell34 = row2.createCell(30);
		cell34.setCellValue("�Բ���");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 30, 30));
		
		HSSFCell cell35 = row2.createCell(31);
		cell35.setCellValue("�Լ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 31, 31));
		
		HSSFCell cell36 = row2.createCell(32);
		cell36.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 32, 32));
		
		HSSFCell cell37 = row2.createCell(33);
		cell37.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 33, 33));
		
		HSSFCell cell38 = row2.createCell(34);
		cell38.setCellValue("������ ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 34, 34));
		
		HSSFCell cell39 = row2.createCell(35);
		cell39.setCellValue("�ܻ��� ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 35, 35));
		
		Integer rows = 8;
		//Integer ce = 1;
		for (int i = 0; i < monit.size(); i++) {
			
			YzlMonitoringstatistics yzlMonitoringstatistics = monit.get(i);
		//for (YzlMonitoringstatistics yzlMonitoringstatistics : monit) {
			
//			System.out.println("-------"+rows);
//			System.out.println("======="+ce++);
			
			HSSFRow hssfRow = sheet.createRow(rows++);
			
			hssfRow.createCell(1).setCellValue(yzlMonitoringstatistics.getCity());//��
			hssfRow.createCell(2).setCellValue(yzlMonitoringstatistics.getCounty());//��
			hssfRow.createCell(3).setCellValue(yzlMonitoringstatistics.getEname());//�������
			hssfRow.createCell(4).setCellValue(yzlMonitoringstatistics.getTname());//�������
			hssfRow.createCell(5).setCellValue(yzlMonitoringstatistics.getTime());//ʱ��
			
			hssfRow.createCell(6).setCellValue(yzlMonitoringstatistics.getInspectionreportarea());//�Լ��ϱ���� inspectionReportArea
			hssfRow.createCell(7).setCellValue(yzlMonitoringstatistics.getVerifyarea());//��ʵ��� verifyArea
			hssfRow.createCell(8).setCellValue(yzlMonitoringstatistics.getCertifiedarea());//��ʵ�ϸ����certifiedArea
			hssfRow.createCell(9).setCellValue(yzlMonitoringstatistics.getJobdesignarea());//��ҵ������jobDesignArea
			hssfRow.createCell(10).setCellValue(yzlMonitoringstatistics.getDesignconstructionaccordingoperation());//����ҵ���ʩ�����designConstructionAccordingOperation
			hssfRow.createCell(11).setCellValue(yzlMonitoringstatistics.getFilesize());//�е������ fileSize
			hssfRow.createCell(12).setCellValue(yzlMonitoringstatistics.getCountyinspectionarea());//��չ�ؼ��Լ����countyInspectionArea
			hssfRow.createCell(13).setCellValue(yzlMonitoringstatistics.getDesignforestarea());//���������� designForestArea
			hssfRow.createCell(14).setCellValue(yzlMonitoringstatistics.getVerifyforestarea());//��ʵ�������verifyForestArea
			hssfRow.createCell(15).setCellValue(yzlMonitoringstatistics.getBarearea());//������� bareArea
			hssfRow.createCell(16).setCellValue(yzlMonitoringstatistics.getManagementarea());//�ܻ���� managementArea
			
			hssfRow.createCell(17).setCellValue(yzlMonitoringstatistics.getAreaverificationrate());//�����ʵ��areaVerificationRate
			hssfRow.createCell(18).setCellValue(yzlMonitoringstatistics.getVerifyareapassrate());//��ʵ����ϸ���verifyAreaPassRate
			hssfRow.createCell(19).setCellValue(yzlMonitoringstatistics.getReportareaqualificationrate());//�ϱ�����ϸ���reportAreaQualificationRate
			
			hssfRow.createCell(20).setCellValue(yzlMonitoringstatistics.getReportretentionrateyear());//�����ϱ��������reportRetentionRateYear
			hssfRow.createCell(21).setCellValue(yzlMonitoringstatistics.getAfforestationreportseligibleyear());//���ֵ����ϱ��ϸ�afforestationReportsEligibleYear
			hssfRow.createCell(22).setCellValue(yzlMonitoringstatistics.getCountyselfinspectionarea());//ȫ���Լ�ϸ����countySelfInspectionArea
			hssfRow.createCell(23).setCellValue(yzlMonitoringstatistics.getCalculatedverificationarea());//�����ʵ���calculatedVerificationArea
			hssfRow.createCell(24).setCellValue(yzlMonitoringstatistics.getCalculatequalifiedarea());//������ɺϸ���� calculateQualifiedArea
			hssfRow.createCell(25).setCellValue(yzlMonitoringstatistics.getScheduledtask());//�ƻ�����scheduledTask
			hssfRow.createCell(26).setCellValue(yzlMonitoringstatistics.getTaskcompletionrate());//���������taskCompletionRate

			hssfRow.createCell(27).setCellValue(yzlMonitoringstatistics.getJobdesignrate());//��ҵ����� jobDesignRate
			hssfRow.createCell(28).setCellValue(yzlMonitoringstatistics.getAccordingdesignconstructionrate());//�����ʩ����accordingDesignConstructionRate
			hssfRow.createCell(29).setCellValue(yzlMonitoringstatistics.getByinputtingrate());//������ byInputtingRate
			hssfRow.createCell(30).setCellValue(yzlMonitoringstatistics.getSelfcheckingrate());//�Բ���selfCheckingRate
			hssfRow.createCell(31).setCellValue(yzlMonitoringstatistics.getSelfcheckingsrate());//�Լ���selfCheckingsRate
			hssfRow.createCell(32).setCellValue(yzlMonitoringstatistics.getFilerate());//������ fileRate
			hssfRow.createCell(33).setCellValue(yzlMonitoringstatistics.getRaisingrates());//������ raisingRates
			hssfRow.createCell(34).setCellValue(yzlMonitoringstatistics.getAfforestationrate());//������afforestationRate
			hssfRow.createCell(35).setCellValue(yzlMonitoringstatistics.getThemanagementrate());//�ܻ��� theManagementRate
		}
		
		HSSFCellStyle style = workbook.createCellStyle();//������ʽ
		HSSFFont font = workbook.createFont();//������ʽ
		
		//�ӱ߿�
//		style.setBorderBottom(BorderStyle.THIN);//�±߿� 
//		style.setBorderLeft(BorderStyle.THIN);//��߿� 
//		style.setBorderRight(BorderStyle.THIN);//�ұ߿� 
//		style.setBorderTop(BorderStyle.THIN); //�ϱ߿�
		
		//����
		style.setVerticalAlignment(VerticalAlignment.CENTER);// ��ֱ���У����¾��У�
		style.setAlignment(HorizontalAlignment.CENTER);// ���Ҿ���
		
		//��������
		font.setFontName("����_GB2312");//��������
		font.setFontHeightInPoints((short) 12);//����Ĵ�С
		font.setItalic(false);//�����Ƿ�Ϊб�� 
		font.setBold(true);//����Ӵ�
		style.setWrapText(true);//�Զ�����
		
		style.setFont(font);//������Ҫ�õ���������ʽ
		
		List<HSSFCell> list = new ArrayList<>();
		list.add(cell0);list.add(cell1);list.add(cell2);list.add(cell3);list.add(cell4);list.add(cell5);list.add(cell6);list.add(cell7);list.add(cell8);list.add(cell9);list.add(cell10);
		list.add(cell11);list.add(cell12);list.add(cell13);list.add(cell14);list.add(cell15);list.add(cell16);list.add(cell17);list.add(cell18);list.add(cell19);list.add(cell20);
		list.add(cell21);list.add(cell22);list.add(cell23);list.add(cell24);list.add(cell25);list.add(cell26); list.add(cell27); list.add(cell28);list.add(cell29);list.add(cell30);
		list.add(cell31);list.add(cell32);list.add(cell33);list.add(cell34);list.add(cell35);list.add(cell36);list.add(cell37);list.add(cell38);list.add(cell39);
		
		//��Ⱦ��Ԫ��
		for (HSSFCell hssfCell : list) {
			hssfCell.setCellStyle(style);
		}
		
		//"D://EXXXX.xls"
		/*String path = "D://";//�̷�·��
		String fileName = "excel1.xls";//�ļ�����
		int i=1;
		//��ȡĳ���̷��µ������ļ�����
		File file = new File(path);
		String[] fi = file.list();
		
		for (String string : fi) {
			
			if (string.contains(".")) {
				int indexOf = string.indexOf(".");//��ȡ����ֵ��±�����
				String substring = string.substring(indexOf, string.length());//��ȡ��׺
				
				if (substring.equals(".xls")) {//�����׺��Ϊ.xls
					if (fileName.equalsIgnoreCase(string)) {
						fileName = "excel" + ++i + ".xls";
					}
				}
			}
		}*/
		//path+fileName
		//OutputStream outputStream = response.getOutputStream();
		response.reset();
		OutputStream os;
					os = response.getOutputStream();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("content-disposition", "attachment;filename=excel1.xls");
					workbook.write(os);
				
				//workbook.close();
				os.close();
			
			return YzlResult.ok(400);
	}
	
	//���񷢲��ĵ���
	@Override
	public YzlResult taskDr(MultipartFile[] exceName) throws IOException {
		
		InputStream inputStream = null;
		for (MultipartFile multipartFile : exceName) {
			inputStream = multipartFile.getInputStream();
		}
		
		//ʹ��poi����excel�ļ�
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		
		//��ȡָ����sheet����
		HSSFSheet sheet = workbook.getSheet("�˲�ƻ������");
		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			
			
			if (rowNum <= 7) {
				continue;
			}
			
			row.getCell(0).setCellType(CellType.STRING);
			
			if (row.getCell(0).getStringCellValue().equals("һ")|| row.getCell(0).getStringCellValue().equals("��")
			|| row.getCell(0).getStringCellValue().equals("��")|| row.getCell(0).getStringCellValue().equals("��") || 
			row.getCell(0).getStringCellValue().equals("��")|| row.getCell(0).getStringCellValue().equals("��")|| 
			row.getCell(0).getStringCellValue().equals("��")|| row.getCell(0).getStringCellValue().equals("��")|| 
			row.getCell(0).getStringCellValue().equals("��")|| row.getCell(0).getStringCellValue().equals("ʮ")|| row.getCell(0).getStringCellValue().equals("ʮһ")
		|| row.getCell(0).getStringCellValue().equals("ʮ��")|| row.getCell(0).getStringCellValue().equals("ʮ��")|| row.getCell(0).getStringCellValue().equals("ʮ��")) {
				continue;
			}
			for (Cell cell : row) {
				System.out.println();
				if (cell != null) {
					cell.setCellType(CellType.STRING);
					String stringCellValue = cell.getStringCellValue();
					System.out.print(stringCellValue + " ,");
				}
				
			}
		}
	
		System.out.println(exceName);
		return null;
	}

	//��ѯ���е���
	@Override
	public List<YzlDistrict> AllCity() {
		List<YzlDistrict> list = monitoringstatisticsMapper.AllCity();
		return list;
	}

	//���й���
	@Override
	public List<YzlEpc> AllEpc() {
		List<YzlEpc> list = monitoringstatisticsMapper.AllGclb();
		return list;
	}

	//��ѯĳ�����µ���
	@Override
	public List<YzlDistrict> AllCounty(String cityCode) {
		List<YzlDistrict> list = monitoringstatisticsMapper.AllCounty(cityCode);
		return list;
	}

	//�ַ�����ȡ��������
//		private String zh(String valueOf) {
//			//0.7433333
//			int i = valueOf.indexOf(".");
//			String substring3 = valueOf.substring(i, valueOf.length());
//			
//			Double floats = 0.0;
//			if (valueOf.length()>=5 && substring3.length()>=2) {//0.3870968  387096.8
//				//��ȡ����λ
//				String substring = valueOf.substring(4, 5);
//				//��ȡǰ4λ
//				String string = valueOf.substring(0, 4);
//				
//				if (Integer.valueOf(substring)>=5) {//�жϵ���λ���Ƿ���ڵ�������������
//					Double of = Double.valueOf(string);
//					//0.21000000000000002
//					floats = of+0.01;
//					if (floats.toString().length()>4) {
//						String valueOf2 = floats.toString();
//						floats = Double.valueOf(valueOf2.substring(0, 4));
//					}
//				}else {
//					floats = Double.valueOf(string);
//				}
//				
//			}else {
//				floats = Double.valueOf(valueOf);
//			}
//			
//			BigDecimal bigDecimal = new BigDecimal("100");
//			BigDecimal fBigDecimal = new BigDecimal(floats.toString());
//			BigDecimal multiply = bigDecimal.multiply(fBigDecimal);
//			
//			String valueOf2 = multiply.toString();
//			int indexOf = valueOf2.indexOf(".");
//			
//			String substring2 = "0.0";
//			String substring = valueOf2.substring(indexOf, valueOf2.length());
//			if (substring.length()>=3) {
//				substring2 = valueOf2.substring(0, valueOf2.length()-1);
//			}else {
//				substring2 = valueOf2;
//			}
//			return substring2;
//		}
}
