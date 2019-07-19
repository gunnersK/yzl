package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskProgressSheetExample;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.CheckStringIsNumberUtils;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.ObjTOJson;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DrilldownNode;
import com.yzl.utils.vo.HighchartsResultVO;
@Service
public class TaskProgressSheetServiceImpl implements com.yzl.distriEpcTaskService.TaskProgressSheetService {

	
	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	private List<YzlTaskProgressSheet> taskProgressSheets;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	

	
	//��ҳ��ѯ����
	@Override
	public EasyUIResult pageQuery(HttpServletRequest request ,int page, int rows, String searchKey,String area,String year) {
		List<YzlTaskProgressSheet> taskProgressSheetList = new ArrayList<>();
		//ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(StringUtils.isBlank(searchKey)){
			//0��ʾ��ѯ����
			System.out.println(area+year);
			taskProgressSheetList = taskProgressSheetMapper.queryByUserPower(PermsList,area,year);
		}else{
			taskProgressSheetList = taskProgressSheetMapper.searchKey(PermsList,searchKey);
		}
		EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheetList, page,rows);
		//0Ĭ��ͳ����������s
		//Integer total = taskProgressSheetMapper.countCreateTimeAlsoDistinct(0);
		//easyUIResult.setTotal(total);
		return easyUIResult;
	}
	
	//��ҳ��ѯ ���ݹ���id
	@Override
	public EasyUIResult pageQueryByEcode(HttpServletRequest request,Integer page, Integer rows,Integer ecode,String area,String year,String searchKey){
		//List<YzlTaskProgressSheet> taskProgressSheetList = taskProgressSheetMapper.queryTaskDistrictByEcode(Ecode);
		List<String> marks = new ArrayList<>();
		List<YzlTaskProgressSheet> taskProgressSheetList = new ArrayList<>();
		//ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		//ȡ��ǰ�û���ӵ��δ�������Ȩ��
		for (String mark : PermsList) {
			//�ų�����ɵ��������
			if(!mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				//���δ��������ʶ
				marks.add(mark);
			}
		}
		//�ж������ؼ����Ƿ�Ϊ��
		if(StringUtils.isBlank(searchKey)){
		//���ݹ���id������״̬�෴ֵ��ѯ����
			//taskProgressSheetList = taskProgressSheetMapper.queryByEcodeAndContraryMark(taskProgressSheet);
			taskProgressSheetList = taskProgressSheetMapper.queryByEcodeAndMark(marks, ecode,area,year);
		}else{
			//�����ؼ��ֲ�Ϊ�գ����ݹؼ��ֽ������� 
			taskProgressSheetList = taskProgressSheetMapper.searchKeyAndEcode(ecode, marks, searchKey);
		}
		 EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheetList,page,rows);
		//���ݹ���Idͳ��
		Integer total = taskProgressSheetMapper.countCreateTimeAlsoDistinct(ecode);
		easyUIResult.setTotal(total);
		return easyUIResult;
	}
	
	//��ҳ��ѯ
	public EasyUIResult pageQueryProgressUtils(List<YzlTaskProgressSheet> epcTaskProgresses,Integer page,Integer rows){
		//�����ܼ�¼�� ����ȡ��
		//��װҪ���ص�json
		List<Map> dataList = new ArrayList<>();
		//�����ж��Ƿ��Ѿ�����뼯����
		List idL = new ArrayList<>();
		for(int i=0;i<epcTaskProgresses.size();i++){
			//�жϵ�ǰ�����Ƿ��Ѿ���ӽ��뼯����
			if(!idL.contains(epcTaskProgresses.get(i).getId())){
				HashMap<Object, Object> epcTaskProgressesMap = new HashMap<>();
				//ȡ�������
				YzlTaskProgressSheet taskProgressSheet = epcTaskProgresses.get(i);
				//��ӵ�һ��������뼯����
				//����id�͹���idƴΪ�ַ��� ��Ϊҳ��fild��ֵ
				String k = taskProgressSheet.getTpcode()+ "T" +taskProgressSheet.getEpcode() ;
				epcTaskProgressesMap.put(k , taskProgressSheet.getTaskprogress());
				
				//��ӵ���id
				YzlDistrict district = taskProgressSheet.getDistrict();
				epcTaskProgressesMap.put("id", taskProgressSheet.getEpcode());
				 epcTaskProgressesMap.put("city",district.getCity());
				epcTaskProgressesMap.put("county", district.getCounty());
				epcTaskProgressesMap.put("mark", taskProgressSheet.getMark());
				epcTaskProgressesMap.put("dcode", district.getDcode());
				epcTaskProgressesMap.put("createtime", taskProgressSheet.getCreateTimetoString());
				String taskProgressMark = taskProgressSheet.getMark();
				if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='green'>�����</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='red'>�˻�</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='orange'>���м����</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='green'>�����������</font>");	
				}else{
					epcTaskProgressesMap.put("checkMark", "<font color='blue'>���������</font>");
				}
				for(int j=i+1;j<epcTaskProgresses.size();j++){
					//ȡ�������
					YzlTaskProgressSheet ptaskProgress = epcTaskProgresses.get(j);
					//�Ƚ�����������ȵĵ����Ƿ���ͬ�ʹ���ʱ���Ƿ�һ��
					//&&taskProgressSheet.getEpcode()!=ptaskProgress.getEpcode()
					//&&taskProgressSheet.getUpdatetime().equals(ptaskProgress.getUpdatetime())
					if(taskProgressSheet.getDpcode()==ptaskProgress.getDpcode()
							&&taskProgressSheet.getMark().equals(ptaskProgress.getMark())){
						//������Ŀ�����ݾʹ���ͬһ�� ��Ŀ��ʾ����json��ʽ
						//����id�͹���idƴΪ�ַ��� ��Ϊҳ��fild��ֵ
						String key =ptaskProgress.getTpcode()+"T"+ptaskProgress.getEpcode();
						epcTaskProgressesMap.put(key, ptaskProgress.getTaskprogress());
						//���Ѿ���ӵ������е�������ȵ�id��ӵ���һ�������������ж��Ƿ��Ѿ����뼯����
						idL.add(ptaskProgress.getId());	
					}
				}
				dataList.add(epcTaskProgressesMap);
			}
		}
		EasyUIResult result = new EasyUIResult();
		//�����ҳ��ʼλ��
		int beginIndex = (page-1)*rows;
		//�����ҳ����λ��
		int lastIndex = beginIndex+rows;
		//��������ܼ�¼�� �� ��ҳ�������Ϊ �ܼ�¼��
		if(lastIndex>dataList.size()){
			lastIndex=dataList.size();
		}
		//ȡҳ��Ҫ��ʾ������
		List<Map> resultList = dataList.subList(beginIndex, lastIndex);
		//��װmodel
		result.setRows(resultList);
		result.setTotal(resultList.size());
		return result;
	}
	
	
	//�ύ����
	public YzlResult submitTaskProgressSheet(HttpServletRequest request, Map<String, String> params){
		//����id
		Integer dpcode=null;
		//����id
		Integer ecode=null;
		//����ʱ��
		String databaseTime = null;
		//������ʶ
		String mark=null;
		Set<String> ParamsSet = params.keySet();
		Iterator<String> iterator = ParamsSet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if(key.equals("id")){
				String ecodeStr = params.get(key);
				ecode = Integer.parseInt(ecodeStr);
				iterator.remove();
			}else if(key.equals("createtime")){
				databaseTime = params.get(key);
				iterator.remove();
			}else if(key.equals("mark")){
				//�жϵ�ǰ�����Ƿ� �Ǳ��˻ع���
				mark = params.get(key);
				if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())){
					//ɾ�����˻ر�־��׺
					mark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())[0];
				}
				iterator.remove();
			}else if(key.equals("dcode")){
				String dcodeStr = params.get(key);
				iterator.remove();
			}
		}
/*		//�ѱ�־��ֳ� ��  ����
		if(mark==null){
			throw new YzlException(ResultEnum.DISTRICT_MARK_ISNULL);
		}
		String updateMark=null;
		if(mark.contains("-")){
			String[] citymarks = mark.split("-");
			//ȡ�м���־
			updateMark = citymarks[0];
			//�ж��Ƿ������������˻�
		}
*/
		for (String k : ParamsSet) {
			//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
			String[] epcTask = k.split("T");
			String task = null;
			//�ж��Ƿ�Ϊ����   �����������Ϊ����id
			if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
				//ȡ��һ�� ����Id
				task = epcTask[0];
				//��װModel
				YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
				taskProgressSheet.setDpcode(dpcode);
				//ȡ����id
				taskProgressSheet.setEpcode(ecode);
				//�������� ������ʶ  Ϊ���м����
				taskProgressSheet.setMark(mark + TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode());
				System.out.println("mark="+mark);
				taskProgressSheet.setTpcode(Integer.parseInt(task));
				taskProgressSheet.setDatabasetime(databaseTime);
				System.out.println(taskProgressSheet);
				//���ݲ��������
				taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
			}
		}
		return YzlResult.ok();
	}
	
	
	//�޸�����
	@Override
	public YzlResult updateTaskProgressSheet(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
			//����id
			Integer dpcode=null;
			//����id
			Integer ecode=null;
			//����ʱ��
			String databaseTime = null;
			
			Set<String> ParamsSet = params.keySet();
			Iterator<String> iterator = ParamsSet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}
			}
			Date currentTime  = new Date();
			for (String k : ParamsSet) {
				//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
				String[] epcTask = k.split("T");
				String task = null;
				//�ж��Ƿ�Ϊ����   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//ȡ��һ�� ����Id
					task = epcTask[0];
					//��װModel
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					taskProgressSheet.setDpcode(dpcode);
					taskProgressSheet.setUpdatetime(currentTime);
					//ȡ����id
					taskProgressSheet.setEpcode(ecode);
					//ȡ��ǰ��¼�û�
					YzlUser loginUser = LoginUserUtils.getLoginSession(request);
					taskProgressSheet.setModifier(loginUser.getUsername());
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					//ȡ�������
					String progress = params.get(k);
					taskProgressSheet.setTaskprogress(Float.parseFloat(progress));
					//���ݲ��������
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "�����޸�ʧ��");
		}
		return YzlResult.ok();
	}
	//�˻�����
	@Override
	public YzlResult sendBack(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
			String mark=null;
			//����id
			Integer dpcode=null;
			//����id
			Integer ecode=null;
			//����ʱ��
			String databaseTime = null;
			Set<String> ParamsSet = params.keySet();
			Iterator<String> iterator = ParamsSet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}else if(key.equals("mark")){
					mark = params.get(key);
					iterator.remove();
				}
			}
			String updateMark=null;
/*			if(mark.equals("ROOT")){
				String markByDcode = districtMapper.findMarkByDcode(dpcode);
				String[] marks = markByDcode.split("-");
				if(marks.length==2){
					updateMark = marks[0];
				}
			}else{*/
			
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
				updateMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())[0];
			}else if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
				updateMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())[0];
			}
				//updateMark = districtMapper.findMarkByDcode(dpcode);	
			//}
			for (String k : ParamsSet) {
				//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
				String[] epcTask = k.split("T");
				String task = null;
				//�ж��Ƿ�Ϊ����   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//ȡ��һ�� ����Id
					task = epcTask[0];
					//��װModel
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					//ȡ����id
					taskProgressSheet.setEpcode(ecode);
					//ȡ��ǰ��¼�û�
					YzlUser loginUser = LoginUserUtils.getLoginSession(request);
					taskProgressSheet.setModifier(loginUser.getUsername());
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					taskProgressSheet.setMark(updateMark+"back");
					System.out.println("mar�˻�k="+taskProgressSheet.getMark());
					//ȡ�������
					//���ݲ��������
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "�����޸�ʧ��");
		}
		return YzlResult.ok();
	}

	
	//���ͨ���������
	@Override
	public YzlResult approve(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
			String mark=null;
			//����id
			Integer dpcode=null;
			//����id
			Integer ecode=null;
			//����ʱ��
			String databaseTime = null;
			Set<String> ParamsSet = params.keySet();
			Iterator<String> iterator = ParamsSet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}else if(key.equals("mark")){
					mark = params.get(key);
					iterator.remove();
				}
			}
			String updateMark=null;
			//�ж��Ƿ��� �������˻ص�����
			//ȡ��ǰ��¼�û�
			YzlUser loginUser = LoginUserUtils.getLoginSession(request);
			//ȡ��ǰ�û���ӵ�е�Ȩ��
			List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
			//ȡ��ǰ�û���ӵ��δ�������Ȩ��
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
				//�ж��Ƿ�ӵ�д����������Ȩ��
				if(PermsList.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_AUDIT.getCode())){
					String firstMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())[0];
					//���������ͨ������������ɲ� ͨ�����
					updateMark = firstMark + TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode();
				}else {
					return YzlResult.build(303, "��û����˸����ݵ�Ȩ��,����Ҫ������ϵ��������Ա");
				}
			}else if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
				//�м����ͨ�����ύ�����������
				//�ж��Ƿ�ӵ�д��м���˵�Ȩ��
				if(PermsList.contains(mark)){
					String firstMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())[0];
					//
					updateMark = firstMark + TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode();
					//
				}else {
					return YzlResult.build(303, "��û����˸����ݵ�Ȩ��,����Ҫ������ϵ��������Ա");
				}
			}
			System.out.println("Ȩ��="+updateMark);
			for (String k : ParamsSet) {
				//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
				String[] epcTask = k.split("T");
				String task = null;
				//�ж��Ƿ�Ϊ����   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//ȡ��һ�� ����Id
					task = epcTask[0];
					//��װModel
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					//ȡ����id
					taskProgressSheet.setEpcode(ecode);
					//ȡ��ǰ��¼�û�
/*					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					taskProgressSheet.setModifier(user.getUsername());*/
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					taskProgressSheet.setMark(updateMark);
					//ȡ�������
					//���ݲ��������
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "�����޸�ʧ��");
		}
		return YzlResult.ok();
	}
	
	
	/***
	 * ���ݹ���id��ѯ��������ɵ�����
	 */
	@Override
	public EasyUIResult pageQueryFinishTaskByEcode(HttpServletRequest request,Integer page, Integer rows, Integer Ecode,String searchKey) {
		List<YzlTaskProgressSheet> taskProgressSheets = new ArrayList<>();
		//ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<String> markList = new ArrayList<>();
		for (String mark : PermsList) {
			//��ȡ��ǰ�û���ѯ ���������������Ȩ��
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				markList.add(mark);
			}
		}
		List<YzlTaskProgressSheet> taskprogressList = taskProgressSheetMapper.queryTaskProgressGroupByDpcodeAndTpcode(PermsList);
		//�ж������ؼ��֣��Ƿ�Ϊ�գ������Ϊ�����������
		if(StringUtils.isBlank(searchKey)){
			PageHelper.startPage(page, rows);
			//���ݹ���id��ѯ��������ɵ����� ���������id=0���ѯ��������ɵ�����
			taskProgressSheets  = taskProgressSheetMapper.queryByEcodeAndMark(markList,Ecode,null,null);

		}else{
			//���ݹؼ��ֽ�������
			taskProgressSheets = taskProgressSheetMapper.searchKeyAndEcodeMark(Ecode,markList, searchKey);
		}
		EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheets, page,rows);
		return easyUIResult;
	}

	/***
	 * ��ѯ��������ɵ�����
	 */
	@Override
	public EasyUIResult pageQueryFinishTask(HttpServletRequest request,Integer page, Integer rows, String searchKey) {
		List<String> list = new ArrayList<>();
		list.add("��ɽ�˹�����");
		list.add("�����˹���������");
		list.add("��Ч�ָ���");
		list.add("��ɽ����");
		list.add("�˸�����");
		list.add("������ֲ��ҩ��");
		//ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		//�洢�鿴���������Ȩ��
		List<String> markList = new ArrayList<>();
		for (String mark : PermsList) {
			//��ȡ��ǰ�û���ѯ ���������������Ȩ��
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				markList.add(mark);
			}
		}
		List<YzlTaskProgressSheet> taskProgressSheetsList = new ArrayList<>();
		List<YzlTaskProgressSheet> taskProgressSheetListTwo  = new ArrayList<>();
		EasyUIResult easyUIResult = new EasyUIResult();	
		//�ж������ؼ��֣��Ƿ�Ϊ�գ������Ϊ�����������
		if(StringUtils.isBlank(searchKey)){
			//����Ȩ�޲�ѯ����������ȣ���ͨ������id������id����
			taskProgressSheetsList = taskProgressSheetMapper.queryTaskProgressGroupByDpcodeAndTpcode(markList);
			//����Ȩ�޲�ѯ�����������
			taskProgressSheetListTwo = taskProgressSheetMapper.queryByMark(markList);

		}else{
			//����Ȩ�޺������ؼ��ֲ�ѯ����������ȣ���ͨ������id������id����
			taskProgressSheetsList = taskProgressSheetMapper.searchTaskProgressGroupByDpcodeAndTpcode(markList, searchKey);
			//����Ȩ�޺������ؼ��ֲ�ѯ�����������
			taskProgressSheetListTwo = taskProgressSheetMapper.searchKeyAndMark(markList, searchKey);	
		}
			
			//�Խ�����з�װ
			List targetList = new ArrayList<>();
			//�洢�Ѿ���װ���Ķ���id
			List IdList = new ArrayList<>();
			for (int i=0;i<taskProgressSheetsList.size();i++) {
				YzlTaskProgressSheet taskProgressSheet = taskProgressSheetsList.get(i);
				System.out.println("");
				//�жϵ�ǰ�������Ƿ�����Ҫ��͵Ķ���
				Float sum=0f;

				//�жϵ�ǰ��������Ѿ����з�װ����   ��װ������Ҫ�ڷ�װs
				if(!IdList.contains(taskProgressSheetsList.get(i).getId())){
					//�Զ������ҳ����Ҫ�ĸ�ʽ���з�װ
					Map map  = new HashMap<>();
					//��װ����   key��ҳ��field��Ӧ
					map.put(taskProgressSheet.getTask().getTname(), taskProgressSheet.getTaskprogress());
					map.put("city", taskProgressSheet.getDistrict().getCity());
					map.put("county", taskProgressSheet.getDistrict().getCounty());
					map.put("planningTime", taskProgressSheet.getCreateTimetoString());
					if(list.contains(taskProgressSheet.getTask().getTname())){
						//����Ҫ��͵�������Ƚ������
						sum+=taskProgressSheet.getTaskprogress();//�ϼ�
					}
					for (int j = i+1; j < taskProgressSheetsList.size(); j++) {
						
						//�жϵ����Ƿ����
						YzlTaskProgressSheet taskProgressSheetTwo = taskProgressSheetsList.get(j);
						if(list.contains(taskProgressSheetTwo.getTask().getTname())){
							sum+=taskProgressSheetTwo.getTaskprogress();
						}
						//�жϵ����Ƿ����   ��ȵ�����з�װ
						if(taskProgressSheet.getDpcode()==taskProgressSheetTwo.getDpcode())
						{
							map.put(taskProgressSheetTwo.getTask().getTname(), taskProgressSheetTwo.getTaskprogress());
							//���Ѿ���װ�Ķ����id ��ӵ�������
							IdList.add(taskProgressSheetTwo.getId());
						}
					}



					for (YzlTaskProgressSheet taskProgressSheetTwo : taskProgressSheetListTwo) {
						if(taskProgressSheet.getDpcode()==taskProgressSheetTwo.getDpcode()){
							map.put(taskProgressSheetTwo.getTpcode()+"T"+taskProgressSheetTwo.getEpcode(), taskProgressSheetTwo.getTaskprogress());
							//����sum
						}
					}
					//��װ��Ҫͳ�Ƶ�����ĺ�
					map.put("sum", sum);
					//��װmap
					targetList.add(map);
				}
			}
			//�����ҳ��ʼλ��
			int beginIndex = (page-1)*rows;
			//�����ҳ����λ��
			int lastIndex = beginIndex+rows;
			//��������ܼ�¼�� �� ��ҳ�������Ϊ �ܼ�¼��
			if(lastIndex>targetList.size()){
				lastIndex=targetList.size();
			}
			//��ȡҳ����Ҫ��ʾ����
			List result = targetList.subList(beginIndex, lastIndex);
			//��װ���ݺ��ܼ�¼��
			easyUIResult.setRows(result);
			easyUIResult.setTotal(result.size());
			//taskProgressSheets = taskProgressSheetMapper.queryByMark(markList);
			return easyUIResult;
	}

	@Override
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request) {
		
/*		List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		List<Map> epcTaskProgressHighchartsList = epcTaskProgressMapper.queryTaskGroupByCity();
		List<HighchartsResultVO> list = new ArrayList<>();
		for (Map taskProgressSheetMap : taskProgressSheetHighchartsList) {
			for (Map epcTaskProgressMap : epcTaskProgressHighchartsList) {
				Map<String,Object> map = new HashMap<>();
				if(taskProgressSheetMap.get("city").equals(epcTaskProgressMap.get("city"))){
				    Double	progress = (Double)taskProgressSheetMap.get("taskprogreses")/(Double)epcTaskProgressMap.get("taskprogreses");
				    HighchartsResultVO resultVO = new HighchartsResultVO();
				    String city = (String)taskProgressSheetMap.get("city");
				    resultVO.setName(city);
				    resultVO.setDrilldown(city);
				    resultVO.setY(progress*100);
				    DrilldownNode drilldownNode = new DrilldownNode();
				    drilldownNode.setName(city);
				    drilldownNode.setId(city);
				    List<Map> taskProgressSheetList= taskProgressSheetMapper.queryTaskGroupByCounty(city);
				    List<List> maps= new ArrayList<>();
				    for (Map taskProgressSheet : taskProgressSheetList) {
						List data = new ArrayList();
						data.add(taskProgressSheet.get("county"));
						data.add((Double)taskProgressSheet.get("taskprogress")*100);
						maps.add(data);
					}
				    drilldownNode.setData(maps);
				    resultVO.setDrilldownNode(drilldownNode);
				    list.add(resultVO);
				    continue;
				}
			}
		}*/
		return null;
	}
	
/*	public List<HighchartsResultVO> qcueryTaskGroupByCity(HttpServletRequest request) {
		List<String> citys= districtMapper.findDistinctCity();
		for (String city : citys) {
			
		}
		List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		List<Map> epcTaskProgressHighchartsList = epcTaskProgressMapper.queryTaskGroupByCity();
		List<Map> list = new ArrayList<>();
		for (Map taskProgressSheetMap : taskProgressSheetHighchartsList) {
			for (Map taskProgressSheetc : epcTaskProgressHighchartsList) {
				Map<String,Object> map = new HashMap<>();
				if(taskProgressSheetc.get("name").equals(taskProgressSheetc.get("name"))){
				    double	Result = (Double)taskProgressSheetMap.get("y")/(Double)taskProgressSheetc.get("y");
				    map.put("name", taskProgressSheetMap.get("name"));
				    map.put("y", Result*100);
				    list.add(map);
				    continue;
				}
			}
		}
		return null;
	}*/
}
