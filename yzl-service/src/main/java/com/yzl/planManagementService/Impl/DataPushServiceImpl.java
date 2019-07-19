package com.yzl.planManagementService.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFShapeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.planManagementService.DataPushService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.attr;
import com.yzl.utils.attrs;

@Transactional
@Service
public class DataPushServiceImpl implements DataPushService{

	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Override
	public String text(String name) {
		// TODO Auto-generated method stub
		return name;
	}

	//String taskProgress,taskProgress����
	//dname����, epcname����, tname���̵��ֶΣ�  ,time ʱ�� ��SBMJ�ϱ������ HSMJ��ʵ�����HGMJ�ϸ������SJYLMJ������������HSYLMJ��ʵ������� ��YDAMJ�е������ ��KZXJZJMJ��չ�ؼ��Լ���� ��GHMJ�ܻ����
	@Override
	public void push(attrs attrs) throws Exception {
		
		System.out.println(attrs.getAttr().getCOUNTY());
		
		if (attrs.getAttr().getCOUNTY().equals("450122")) {
			attrs.setCOUNTY("����");
		}else if (attrs.getAttr().getCOUNTY().equals("450123")) {
			attrs.setCOUNTY("������");
		}else if (attrs.getAttr().getCOUNTY().equals("450124")) {
			attrs.setCOUNTY("��ɽ��");
		}else if (attrs.getAttr().getCOUNTY().equals("450125")) {
			attrs.setCOUNTY("������");
		}else if (attrs.getAttr().getCOUNTY().equals("450126")) {
			attrs.setCOUNTY("¡����");
		}
		
		
		System.out.println(attrs.getAttr().getGCLB());
		System.out.println(attrs.getAttr().getZLLB());
		
		if (attrs.getAttr().getGCLB().equals("4")) {
			attrs.setGCLB("ʯĮ������");
		}else if (attrs.getAttr().getGCLB().equals("3")) {
			attrs.setGCLB("����֡������ֹ���");
		}else if (attrs.getAttr().getGCLB().equals("7")) {
			attrs.setGCLB("�Ͳ�Ӫ������Ŀ");
		}else if (attrs.getAttr().getGCLB().equals("5")) {
			attrs.setGCLB("���ֲ�����Ŀ");
		}else if (attrs.getAttr().getGCLB().equals("2")) {
			attrs.setGCLB("�˸����ֹ���");
		}
		
		if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("��ɽ����");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("�˹�����");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("��Ч�ָ���");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("�����˹���������");
		}else if (attrs.getAttr().getZLLB().equals("450122")) {
			attrs.setZLLB("�˸��ػ���");
		}
		
		//attrs.setGCLB("ʯĮ������");
		attrs.setZLLB("��ɽ����");
		
		System.out.println(attrs.getCOUNTY());
		String county2 = attrs.getCOUNTY();//��
		String gclb = attrs.getGCLB();//����
		String zllb = attrs.getZLLB();//�������
		
		
		
		YzlDistrict district = districtMapper.selectByCounty(county2);
		YzlTask task = taskMapper.selectByTname(zllb);
		YzlEpc epc = epcMapper.selectByEname(gclb);
		
		String city = district.getCity();
		String county = district.getCounty();
		
		//String[] strings = PinYin4jUtils.getHeadByString(city);
		//String[] stringx = PinYin4jUtils.getHeadByString(county);
		
		//String string = StringUtils.join(strings);
		//String string2 = StringUtils.join(stringx);
		
		//String mark = string+"-"+string2;
		
		YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
		
		System.out.println(district.getDcode());
		System.out.println(epc.getEcode());
		
		//monitoringstatistics.setMark(mark);
		monitoringstatistics.setDid(Long.valueOf(district.getDcode()));//����
		monitoringstatistics.setEid(Long.valueOf(epc.getEcode()));//����
		monitoringstatistics.setTime(attrs.getAttr().getZYND());//ʱ��
		monitoringstatistics.setTid(String.valueOf(task.getTcode()));//����
		monitoringstatistics.setDesignforestarea(attrs.getAttr().getSJYLMJ());//�����������
		monitoringstatistics.setVerifyforestarea(attrs.getAttr().getHSYLMJ());//��ʵ�������
		monitoringstatistics.setInspectionreportarea(attrs.getAttr().getXTJSBMJ());//�ϱ����
		monitoringstatistics.setVerifyarea(attrs.getAttr().getHSMJ());//��ʵ���
		monitoringstatistics.setCertifiedarea(attrs.getAttr().getHGMJ());//��ʵ�ϸ����
		//monitoringstatistics.setFilesize(attrs.getSFYDA());//�е������
		//monitoringstatistics.setInspectionreportarea(SBMJ);//�ϱ����
		//monitoringstatistics.setCountyinspectionarea(attrs.getSFZJ());//�Ƿ�չ�ؼ��Լ����
		monitoringstatistics.setManagementarea(attrs.getAttr().getSFGH());//�ܻ����
		monitoringstatistics.setSfyda(attrs.getAttr().getSFYDA());//�Ƿ��е������
		monitoringstatistics.setSffy(attrs.getAttr().getSFFY());//�Ƿ���
		monitoringstatistics.setSfgh(attrs.getAttr().getSFGH());//�Ƿ�ܻ�
		monitoringstatistics.setSfzj(attrs.getAttr().getSFZJ());//�Ƿ�չ�ؼ��Լ�
		monitoringstatistics.setYlfs(attrs.getAttr().getYLFS());//���ַ�ʽ
		monitoringstatistics.setClmj(attrs.getAttr().getCLMJ());//�������

		int i = monitoringstatisticsMapper.insertSelective(monitoringstatistics);
		System.out.println(i);
		//��ȡ��ǰ��¼���û�setInspectionReportArea
		//Subject subject = SecurityUtils.getSubject();
		//Session session = subject.getSession();
		//YzlUser user = (YzlUser) session.getAttribute("user");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date parse = sdf.parse(attrs.getAttr().getZYND());
		
		YzlTaskProgressSheet epcTaskProgress = new YzlTaskProgressSheet();
		epcTaskProgress.setCreatetime(parse);//����ʱ��
		epcTaskProgress.setDpcode(Integer.valueOf(district.getDcode()));//����
		//epcTaskProgress.setMark(mark);//Ȩ�ޱ�ʶ
		epcTaskProgress.setTaskprogress(Float.parseFloat(attrs.getAttr().getXTJSBMJ()));//�������
		System.out.println(attrs.getAttr().getXTJSBMJ()+"11111111111111111111");
		epcTaskProgress.setTpcode(Integer.valueOf(task.getTcode()));//����Id
		epcTaskProgress.setEpcode(Integer.valueOf(epc.getEcode()));//����id
		
		System.out.println(epc.getEcode()+" "+task.getTcode()+" "+district.getDcode()+"+111186");
		
		YzlTaskProgressSheet taskProgressSheet = taskProgressSheetMapper.countByEcodeAndTpcodeAndDpcode(Integer.valueOf(epc.getEcode()), Integer.valueOf(task.getTcode()), Integer.valueOf(district.getDcode()));
		//�����¼�����������������
		if(ObjectUtils.isEmpty(taskProgressSheet)){
			System.out.println(taskProgressSheet+"222222222222222222");
			taskProgressSheetMapper.insert(epcTaskProgress);
		}else{
			//�޸���������  
			System.out.println(taskProgressSheet);
			taskProgressSheet.setTaskprogress(taskProgressSheet.getTaskprogress()+epcTaskProgress.getTaskprogress());
			taskProgressSheetMapper.update11(taskProgressSheet);
		}
		
//		String mark = StringUtils.join(strings)+"-"+StringUtils.join(stringx);
//		//ת����
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//		
//		Date parse = sdf.parse(time);
		
		/*YzlTaskProgressSheet record = new YzlTaskProgressSheet();
		
		record.setDpcode(district.getDcode());
		record.setTpcode(task.getTcode());
		record.setEpcode(epc.getEcode());
		record.setCreatetime(parse);
		//record.setTaskprogress(Float.valueOf(taskProgress));
		record.setMark(mark);
		record.setCreator(null);
		int insertSelective = taskProgressSheetMapper.insertSelective(record);*/
//		System.out.println(insertSelective);
	}

}
