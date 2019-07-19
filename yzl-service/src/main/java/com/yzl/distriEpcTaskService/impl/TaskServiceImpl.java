package com.yzl.distriEpcTaskService.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcExample;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.TaskStatuUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlEpcMapper epcMapper;

	@Autowired
	private YzlMessageMapper messageMapper;

	
/*    @Value("${IP_ADDRESS}")
    private String IP_ADDRESS;
    @Value("${PORT}")
    private String PORT;
    @Value("${UPLOAD_FILE_URI}")
    private  String UPLOAD_FILE_URI;
*/

	//��ѯ����
	@Override
	public EasyUIResult findByAll(String value,Integer page,Integer rows) {
		
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		
		//���÷�ҳ��Ϣ
		PageHelper.startPage(page,rows);
		List<YzlTask> list=null;
		
		if (value==null || value.equals("")) {
			YzlTaskExample example=new YzlTaskExample();
			list = taskMapper.selectByExample(example);
		}else {
			list = taskMapper.selectByCondition(value);
		}
		
		PageInfo<YzlTask> pageInfo=new PageInfo<>(list);
		
		EasyUIResult result=new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public YzlTask findByName(String name) {
		YzlTaskExample example=new YzlTaskExample();
		Criteria criteria = example.createCriteria();
		criteria.andTnameEqualTo(name);
		List<YzlTask> list = taskMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public YzlResult addTask(YzlTask task) {
		
		int insert = taskMapper.insertSelective(task);
		if (insert > 0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//����ɾ��
/*	@Override
	public YzlResult deleterTask(String[] ids) {
		
		List<YzlEpcTaskProgress> list2=new ArrayList<>();
		
		for (int i = 0; i < ids.length; i++) {
			//��ѯ���id�ڵ����ű���û�й���
			YzlEpcTaskProgressExample example=new YzlEpcTaskProgressExample();
			com.yzl.pojo.YzlEpcTaskProgressExample.Criteria criteria=example.createCriteria();
			criteria.andTpcodeEqualTo(Integer.valueOf(ids[i]));
			
			List<YzlEpcTaskProgress> list = epcTaskProgressMapper.selectByExample(example);
			for (int j = 0; j < list.size(); j++) {
				list2.add(list.get(i));
			}
		}
		//����й�������ɾ��
		if(list2 != null && list2.size() > 0) {
			return YzlResult.ok(300);
		}
		
		int sum=0;
		//ѭ��ɾ��
		if (ids!=null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				int ko = taskMapper.deleteByPrimaryKey(Integer.valueOf(ids[i]));
				sum+=ko;
			}
		}
		if (sum==ids.length) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}*/

	
	//���ݹ���id��ѯ��������  ��ǰ�������������
	@Override
	public YzlResult queryByEpcEcode(HttpServletRequest request,int ecode,String year,String areaCode) {
		//���yearΪ�գ� ���õ�ǰʱ�����
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		//����year ����mark ����ѯ�������
		List<YzlTask> taskList = taskMapper.queryByTaskIssuedGCLB(ecode,year,authoritys,areaCode);
		List<YzlTask> resultList = new ArrayList<>();
		if(taskList != null && taskList.size()>0){
			for (YzlTask task : taskList) {
				//������id+����id��ҳ���field
				task.setField(task.getEpc().getMark() +"T"+ task.getMark());
				resultList.add(task);
			}
			return YzlResult.ok(resultList);
		}
		return YzlResult.build(400, "δ��ѯ�������б�");
	}
	
	//���ݹ���id��ѯ�������� ��ǰ������������ȹ���
	@Override
	public YzlResult queryByXbGCLB(HttpServletRequest request,int ecode,String year,String disCode) {
		YzlUser user = LoginUserUtils.getLoginSession(request);
		//��ȡ��¼�û���Ȩ��
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(user.getId().toString()));
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
		stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
		
		List<YzlTask> list = taskMapper.queryByXbGCLB(ecode,year,authoritys,disCode,stats);
		List<YzlTask> resultList = new ArrayList<>();
		if(list != null && list.size()>0){
			for (YzlTask task : list) {
				//������id�͹���id���ַ���ƴ�ӣ�������ҳ���field
				task.setField(task.getEpc().getMark()+"T"+task.getMark());
				resultList.add(task);
			}
			return YzlResult.ok(resultList);
		}
		return YzlResult.build(400, "δ��ѯ�������б�");
	}

	
	//���ݹ���id��ҳ��ѯ�����������
	@Override
	public EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode, int page,int rows , String value,String area,String year){
		List<YzlEpcTaskProgress> epcTaskProgresses = new ArrayList<>();
		//ȡ��ǰ��½�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(StringUtils.isBlank(value)){
			//epcTaskProgresses = epcTaskProgressMapper.queryTaskDistrictByEcode(PermsList,ecode);\
			epcTaskProgresses = epcTaskProgressMapper.queryTaskDistrictByEcodeAndAreaAndYear(PermsList,ecode,area,year);
		}else{
			epcTaskProgresses = epcTaskProgressMapper.searchKeyAndEcode(PermsList,ecode, value);
		}
		EasyUIResult result = this.pageQueryProgressUtils(epcTaskProgresses, page,rows);
		return result;
	}

	//TODO �żٻ�������
	//��ҳ��ѯ
	public EasyUIResult pageQueryProgressUtils(List<YzlEpcTaskProgress> epcTaskProgresses,Integer page,Integer rows){
		//�������Ϊ�գ���ֱ�ӷ��ؿ�
		
		if(epcTaskProgresses==null && epcTaskProgresses.size()==0){
			return new EasyUIResult();
		}
		//�����ܼ�¼�� ����ȡ��
 		//int total = (int) Math.ceil((double)epcTaskProgresses.size()/columns);
		//��װҪ���ص�json
		List<Map> dataList = new ArrayList<>();
		//�����ж��Ƿ��Ѿ�����뼯����
		List idL = new ArrayList<>();
		for(int i=0;i<epcTaskProgresses.size();i++){
			//�жϵ�ǰ�����Ƿ��Ѿ���ӽ��뼯����
			if(!idL.contains(epcTaskProgresses.get(i).getId())){
				HashMap<Object, Object> epcTaskProgressesMap = new HashMap<>();
				//ȡ�������
				YzlEpcTaskProgress eTaskProgress = epcTaskProgresses.get(i);
				//����id�͹���idƴΪ�ַ��� ��Ϊҳ��fild��ֵ
				String k = eTaskProgress.getZllb()+ "T" +eTaskProgress.getGclb();
				//��ӵ�һ��������뼯����
				epcTaskProgressesMap.put(k , eTaskProgress.getTaskprogress());
				//�ѵ�ÿһ��ĵ�����ӵ�������
				//��ӵ���id
				epcTaskProgressesMap.put("id", eTaskProgress.getGclb());
				epcTaskProgressesMap.put("city",eTaskProgress.getDistrict().getCity());
				epcTaskProgressesMap.put("county", eTaskProgress.getDistrict().getCounty());
				epcTaskProgressesMap.put("dcode", eTaskProgress.getCitycode());
				epcTaskProgressesMap.put("creator", eTaskProgress.getCreator());
				epcTaskProgressesMap.put("JHND", eTaskProgress.getJhnd());
				epcTaskProgressesMap.put("ZYND", eTaskProgress.getZynd());
				for(int j=i+1;j<epcTaskProgresses.size();j++){
					//ȡ�������
					YzlEpcTaskProgress ptaskProgress = epcTaskProgresses.get(j);
					//�Ƚ�����������ȵĵ����Ƿ���ͬ�ʹ�����/����id/����ʱ����޸�ʱ�� �Ƿ�һ��4						System.out.println("boolean="+((eTaskProgress.getGclb()==ptaskProgress.getGclb()&&eTaskProgress.getTpcode()==ptaskProgress.getTpcode())||eTaskProgress.getGclb()!= ptaskProgress.getGclb()));
					if(eTaskProgress.getCountycode()==ptaskProgress.getCountycode()
						//�ж�id��ͬ������id��ͬ �� ����id��ͬ �����������ͬ   
							){
						//����id�͹���idƴΪ�ַ��� ��Ϊҳ��fild��ֵ
						String key =ptaskProgress.getZllb()+"T"+ptaskProgress.getGclb();
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
		List<Map> resultList = dataList.subList(beginIndex, lastIndex);
		//��װҳ����Ҫ��ʾ������
		result.setRows(resultList);
		result.setTotal(resultList.size());
		return result;
	}
	
	//��ѯ��������
	@Override
	public YzlResult findAll() {
		YzlTaskExample example = new YzlTaskExample();
		List<YzlTask> list = taskMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return YzlResult.ok(list);
		}
		return YzlResult.build(400, "�����ͷ��ѯʧ��!");
	}

	//��ѯ��������
	@Override
	public List<YzlTask> queryAllByPerms(String year,String areaCode) {
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		List<YzlTask> list = new ArrayList<>();
		if(!authoritys.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){//�ж��Ƿ���������·�
			//����������������·��������ݽ��в�ѯ
			list = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, areaCode, authoritys, null,null);
		}else{
			YzlTaskExample example = new YzlTaskExample();
			list = taskMapper.selectByExample(example);
		}
		return list;
	}

	
	
	//�·�����   ������Ч
	@Override
	public YzlResult addTaskProgress(HttpServletRequest request,String epcId, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		if(StringUtils.isBlank(epcId)){
			throw new YzlException(ResultEnum.PROJECT_ID_ISNULL);
		}
		//����id
		Integer epcode = Integer.parseInt(epcId);
		//����id
		Integer dpcode=null;
		Set<String> ParamsSet = params.keySet();
		Iterator<String> iterator = ParamsSet.iterator();
		while (iterator.hasNext()) {
			//��ȡ����id
			String key = iterator.next();
			if(key.equals("county")){
				//��ȡ����id
				String dpcodeStr =params.get(key);
				dpcode = Integer.parseInt(dpcodeStr);
				iterator.remove();
				break;
			}
		}


		if(Objects.isNull(dpcode)){
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		//�߳�ͬ����
	    synchronized(this) {
			try {
				//���̵߳ȴ�һ���ֹ���ִ���ʱ����ͬ
				Thread.sleep(1000);
				//���ݵ���id��ѯ����
				Integer dcode= dpcode;
				String mark = districtMapper.findMarkByDcode(dcode);
				if(StringUtils.isBlank(mark)){
					throw new YzlException(ResultEnum.DISTRICT_MARK_ISNULL);
				}
				Date currentTime  = new Date();
				for (String k : ParamsSet) {
					//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
					String[] epcTask = k.split("T");
					//ȡ��һ�� ����Id
					String task = epcTask[0];
					//��װModel
					YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
					/*epcTaskProgress.setEpcode(epcode);
					epcTaskProgress.setDpcode(dpcode);
					//���õ�����ʶ
					epcTaskProgress.setMark(mark);*/
					epcTaskProgress.setCreatetime(currentTime);
					epcTaskProgress.setUpdatetime(currentTime);
					//ȡ��ǰ��¼�û�
					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					epcTaskProgress.setCreator(user.getUsername());
				//	epcTaskProgress.setTpcode(Integer.parseInt(task));
					//ȡ�������
					String progress = params.get(k);
					epcTaskProgress.setTaskprogress(Float.parseFloat(progress));
					//���ݲ��������
					epcTaskProgressMapper.insert(epcTaskProgress);

					//״̬��ʾ Ϊ�ĸ������ģ������ϼ������
					//taskProgressSheetMapper.insert(taskProgressSheet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return YzlResult.build(400, "��������ʧ��");
			}
			
			return YzlResult.ok();
	    }
	}
	
	List<YzlEpcTaskProgress> findByCreateTime(HttpServletRequest request){
		//ȡ��ǰ��½�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<YzlEpcTaskProgress> epcTaskProgresses = epcTaskProgressMapper.selectByCreateTime(PermsList,2018+"");
		return epcTaskProgresses;
	}
	
	//TODO �����޸�Ϊ��ӵ����񣬣����Ľ�
	//�޸�����  ������Ч
	@Override
	public YzlResult updateTaskProgress(HttpServletRequest request, Map<String, String> params) {
/*		try {
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
				if(key.equals("county")){
					//��ȡ����id
					String dpcodeStr = params.get(key);
					if(CheckStringIsNumberUtils.isInteger(dpcodeStr)){
						dpcode = Integer.parseInt(dpcodeStr);
						
					}
					iterator.remove();
				}
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}
				if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}
			}
			Date currentTime  = new Date();
			for (String k : ParamsSet) {
				//��ǰK������id+"T"+����idƴ�����ģ� ����Ҫ������id�����
				String[] epcTask = k.split("T");
				//�ж��Ƿ�Ϊ����   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])&& StringUtils.isNotBlank(params.get(k))){
					//ȡ��һ�� ����Id
					String task = epcTask[0];
					//��װModel
					YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
					epcTaskProgress.setDpcode(dpcode);
					epcTaskProgress.setUpdatetime(currentTime);
					//ȡ����id
					epcTaskProgress.setEpcode(ecode);
					//ȡ��ǰ��¼�û�
					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					epcTaskProgress.setCreator(user.getUsername());
					epcTaskProgress.setTpcode(Integer.parseInt(task));
					epcTaskProgress.setUpdatetime(currentTime);
					//ȡ�������
					String progress = params.get(k);
					epcTaskProgress.setTaskprogress(Float.parseFloat(progress));
					epcTaskProgress.setDatabasetime(databaseTime);
					//���ݲ��������
					epcTaskProgressMapper.updateTaskProgress(epcTaskProgress);
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "�����޸�ʧ��");
		}*/
		return YzlResult.ok();
	}

	@Override
	public YzlResult deleteByEcodeAndCreateTime(List<YzlEpcTaskProgress> params) {
		for (YzlEpcTaskProgress epcTaskProgress : params) {
			epcTaskProgressMapper.deleteByEcodeAndCreateTime(epcTaskProgress);
			//ɾ�����������ͬʱ���������ɾ��
			YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
			BeanUtils.copyProperties(epcTaskProgress, taskProgressSheet);
			taskProgressSheetMapper.deleteByEcodeAndDpcodeAndCreateTime(taskProgressSheet);
		}
		return YzlResult.ok();
	}

	@Override
	public EasyUIResult searchKey(HttpServletRequest request,String searchKey,Integer page,Integer rows) {
		//ȡ��ǰ��½�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<YzlEpcTaskProgress> list = epcTaskProgressMapper.searchKey(PermsList,searchKey);
		EasyUIResult result = this.pageQueryProgressUtils(list,page,rows);
		return null;
	}

	@Override
	public YzlResult deleterTask(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	//�ύ
	@Override
	public YzlResult sub(String[] subData,String time,String zllb,String [] countys) {
		
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
//		stats.add("0");
//		stats.add("3");
		
		for (String string : subData) {
			HashMap hashMap = JSONObject.parseObject(string, HashMap.class);
			String stat = (String)hashMap.get("stat");
			hashSet.add(stat);
		}
		List<String> stats = new ArrayList<>(hashSet);
		Integer code = TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode();//״̬1����	���
		
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
			
			
		}
		
		if (result > 0 || tableResult > 0) {
			
			/*for (String fl : subData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//����Ϊ��������
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//private ��json������д���
	private List<HashMap<String, String>> dispose(String [] subData){
		
		List<HashMap<String, String>> list = new ArrayList<>();
		
		for (String string : subData) {//��������
			HashMap<String, String> hashMap = new HashMap<>();
			String substring = string.substring(1, string.length()-1);//��ͷ��βȥ��
			String[] split = substring.split(",");//���ݶ��ý��зָ�
			
			for (String string2 : split) {//�����ָ�������
				
				String[] split2 = string2.split(":");//����:ð�Ž��зָ�
				for (int i=0;i<split2.length;i++) {//�������鲢�Ұѷָ�õ�����Ū��key��value��ʽ�ŵ�map��
					if (i==1) {
						continue;
					}
					String substring2 = split2[i].substring(1, split2[i].length()-1);
					String substring3 = split2[i+1].substring(1, split2[i+1].length()-1);
					hashMap.put(substring2, substring3);
				}
			}
			list.add(hashMap);
			
		}
		return list;
	}

	
	//�˻�
	@Override
	public YzlResult back(String[] backData,String time,String zllb,String [] countys) {
		
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("1");
		Integer code = TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode();
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			/*for (String fl : backData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//����Ϊ��������
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<YzlTask> ChangedEpc(String time, String code, String epc) {
		//List<YzlTask> tasks = xbMapper.selectByCodeAndEpc(time,code,epc);
		Integer stat = TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode();
		List<YzlTask> tasks = xbMapper.selectByCodeAndEpc(time,code,epc,String.valueOf(stat));
		for (YzlTask yzlTask : tasks) {
			yzlTask.setField(epc+"T"+yzlTask.getMark());
		}
		return tasks;
	}

	
	@Override
	public EasyUIResult epcData(String time, String code, String epc,Integer page,Integer rows) {
		
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		//time ʱ�䣬	epc		���̱��, code   �У��أ�������������һ�����
		List<CountyTaskWorkingDTO> CountyTaskWorkingDTOList = xbMapper.seletByEpcAndTask(time,code,epc,authoritys,TaskStatuUtils.getTaskStatUnreviewed());
		return PageQueryTis(CountyTaskWorkingDTOList, page, rows);
	}

	//�����ݽ��д���
	private EasyUIResult PageQueryTis(List<CountyTaskWorkingDTO> CountyTaskWorkingDTOList,Integer page,Integer rows) {
		
		List<HashMap> result = new ArrayList<>();
		
		for (CountyTaskWorkingDTO countyTaskWorkingDTO : CountyTaskWorkingDTOList) {
			String city = countyTaskWorkingDTO.getCity();
			HashMap<Object, Object> hashMap = new HashMap<>();
			String county = countyTaskWorkingDTO.getCounty();
			YzlDistrict district = districtMapper.selectByNumber(county);
			hashMap.put("city", district.getCity());
			hashMap.put("county", district.getCounty());
			hashMap.put("JHND", countyTaskWorkingDTO.getJHND());
			hashMap.put("ZYND", countyTaskWorkingDTO.getZYND());
			hashMap.put(countyTaskWorkingDTO.getGCLB()+"T"+countyTaskWorkingDTO.getZLLB(), countyTaskWorkingDTO.getXTJSBMJ());
			
			if(countyTaskWorkingDTO.getStat() == TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode()){
				hashMap.put("stat","<font color='red'>���������</font>");
			}else if(countyTaskWorkingDTO.getStat() == TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode()){
				hashMap.put("stat","<font color='green'>�����</font>");
			}
			result.add(hashMap);
		}
		//��ʼҲ  ����   ��ǰҳ��1����ÿҳ��¼��
		int pages = (page-1)*rows;
		List<HashMap> resuleMap = new ArrayList<>();
		for (int i=pages;i<result.size();i++) {
			resuleMap.add(result.get(i));
		}
		EasyUIResult results = new EasyUIResult();
		results.setTotal(result.size());
		results.setRows(resuleMap);
		return results;
	}

	//���
	@Override
	public YzlResult audit(String[] auditData,String time,String zllb,String [] countys) {
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("1");
		Integer code = TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode();
		
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			/*for (String fl : auditData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//����Ϊ��������
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<List<YzlXb>> checkOut(String eids,String year,String [] countys) {
		
		String[] split = eids.split(",");
		
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
		
		for (String string : split) {
			linkedHashSet.add(string);
		}
		
		List<List<YzlXb>> list = new ArrayList<>();
		for (String county : countys) {
			for (String eic : linkedHashSet) {
				List<YzlXb> yzlXbs = xbMapper.selectByEidAndTime(eic,year,county);
				if (yzlXbs!=null && yzlXbs.size()>0) {
					list.add(yzlXbs);
				}
			}
		}
		return list;
	}


	//---��������
	//��ȡ�û�Ȩ��
	private List<String> getAuthoritys(){
		YzlUser user = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(user.getId().toString()));
		return authoritys;
	}
	
	
	
	//��ȡ�����·����ݱ��ı�ͷ
	@Override
	public List<YzlEpc> getTableHeader(String year, String disCode, String ZLLB) {
		List<YzlEpc> resultEpcList = new ArrayList<>();//���ؽ����epcList
		List<String> authoritys = getAuthoritys();//��ȡ��ǰ��¼�û���Ȩ��
		//����Ȩ�� ����������ţ���ݲ�ѯ �����·��е� task
		List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys,ZLLB);
		for (YzlEpc epc : epcList) { 
			String GCLB = epc.getMark();//��ȡ�������
			//����Ȩ�� ����������ţ���ݣ�������� ��ѯ�����·��е� epc
			List<YzlTask> taskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys,GCLB, ZLLB);
			List<YzlTask> resultTaskList = new ArrayList<>();//���ؽ�����ӽڵ�epcList
			for (YzlTask task : taskList) {
				task.setField(GCLB+"T"+task.getMark());
				resultTaskList.add(task);
			}
			epc.setList(resultTaskList);//�ѹ���list��װ��task��
			resultEpcList.add(epc);//�������װ�����ص�������
		}
		return resultEpcList;
//		List<YzlTask> resultTaskList = new ArrayList<>();//���ؽ����taskList
//		List<String> authoritys = getAuthoritys();//��ȡ��ǰ��¼�û���Ȩ��
//		//����Ȩ�� ����������ţ���ݲ�ѯ �����·��е� task
//		List<YzlTask> taskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys,GCLB);
//		for (YzlTask task : taskList) {
//			String ZLLB = task.getMark();//��ȡ�������
//			//����Ȩ�� ����������ţ���ݣ�������� ��ѯ�����·��е� epc
//			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB,GCLB);
//			List<YzlEpc> resultEpcList = new ArrayList<>();//���ؽ�����ӽڵ�epcList
//			for (YzlEpc epc : epcList) {
//				epc.setField(ZLLB+"T"+epc.getMark());
//				resultEpcList.add(epc);
//			}
//			task.setList(resultEpcList);//�ѹ���list��װ��task��
//			resultTaskList.add(task);//�������װ�����ص�������
//		}
//		return resultTaskList;
	}

	@Override
	public List<YzlTask> queryByYearAndAreaCodeAndZLLBAndGCLB(String year, String areaCode, String ZLLB, String GCLB) {
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		
		//ͨ���·����������в�ѯ����
		if(!authoritys.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			List<YzlTask> taskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, areaCode, authoritys, GCLB, ZLLB);
			return taskList;	
		}else{
			//��ѯ����
			YzlTaskExample taskExample = new YzlTaskExample();
			Criteria taskCreateCriteria = taskExample.createCriteria();
			if(StringUtils.isNotBlank(ZLLB)){//���GCLB��Ϊ�� ��GCLB����
				taskCreateCriteria.andMarkEqualTo(ZLLB);
			}
			return taskMapper.selectByExample(taskExample);
		}
	}
}
