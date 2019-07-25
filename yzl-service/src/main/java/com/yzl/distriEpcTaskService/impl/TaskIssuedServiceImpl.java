package com.yzl.distriEpcTaskService.impl;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.distriEpcTaskService.TaskIssuedService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlRoleMenuMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.AdministrativeCode;
import com.yzl.utils.CheckStringIsNumberUtils;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.FileSpliceMark;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;
import com.yzl.utils.dto.TaskIssuedDTO;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.KeyNameEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class TaskIssuedServiceImpl implements TaskIssuedService {

	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlRoleMenuMapper roleMenuMapper;
	@Autowired
	private YzlEpcMapper epcMapper;
	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlUserRoleMapper userRoleMapper;

	
	private String uploadFolderURI="uploadFile"; //�ϴ��ļ���·��
	
	private static Logger logger = Logger.getLogger(TaskIssuedServiceImpl.class);
	
	private static List<Map<String,String>> taskIssuedDTOs = new ArrayList<>();//���ڴ�ȡ����������
	
	
	//ͨ����ҵ��Ⱥ͵���������Ų�ѯ�����·����ݸ����ء���������ؼ���Ž��з���
/*	@Override
	public EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows,
			HttpServletRequest request, String areaCode, String year,String GCLB) {
		taskIssuedDTOs.clear();
		//�����ǰ����Null,��Ĭ���ǵ�ǰʱ�����
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		//��ȡ��ǰ��¼�û���Ȩ��
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		List<TaskIssuedDTO> TaskIssuedDTOList = epcTaskProgressMapper.queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(markList,areaCode,year,GCLB);
		taskIssuedDTOs.add(TaskIssuedDTOList);
		
		EasyUIResult result = this.pageQueryUtils(TaskIssuedDTOList, page, rows);
		return result;
	}*/
	
	//��ѯ����������ȣ�GCLB�����������
	@Override
	public EasyUIResult queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(HttpServletRequest request,int page,int rows
			,String year,String GCLB){
		//��ռ���
		taskIssuedDTOs.clear();
		//���YearΪ�� ��ȡ��ǰʱ��ΪĬ��ֵ
		if(StringUtils.isBlank(year)){
      		year = new SimpleDateFormat("yyyy").format(new Date());
		}
		List<TaskIssuedDTO> epcTaskProgresses = new ArrayList<>();
		//ȡ��ǰ��½�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//ȡ��ǰ�û���ӵ�е�Ȩ��
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		//����Ȩ������ʾ��ѯ���
		epcTaskProgresses = epcTaskProgressMapper.queryByYearAndGCLB(PermsList,year,GCLB);
		
//		taskIssuedDTOs.add(epcTaskProgresses);
		
		//�����ѯ���ݲ�Ϊ�� ������ݽ��з�ҳ��ʾ����
		EasyUIResult result = this.pageQueryUtils(epcTaskProgresses,page,rows);
		 @SuppressWarnings("unchecked")
		List<HashMap<Object,Object>> list = (List<HashMap<Object, Object>>) result.getRows();
		 //��ѯ������Ȳ� ���ݵ���Idȥ�ظ�  �����Ϊ�ܼ�¼��
		 return result;
	}

	//�����ݽ��з�ҳ����
	private EasyUIResult pageQueryUtils(List<TaskIssuedDTO> TaskIssuedDTOList,Integer page,Integer rows){
		List<HashMap<Object,Object>> resultList = new ArrayList<>();
		//�����жϵ�ǰ�����Ƿ��Ѿ���ӹ�
		List<Integer> idList = new ArrayList<>();
		try {
			for (int i = 0; i < TaskIssuedDTOList.size(); i++) {
				TaskIssuedDTO ItaskIssuedDTO = TaskIssuedDTOList.get(i);
				Integer IcountyNumber = ItaskIssuedDTO.getCountyNumber();
				//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
				if(idList.contains(ItaskIssuedDTO.getId())) continue;
				//��װ����
				HashMap<Object, Object> resultMap = new HashMap<>();
				resultMap.put("city", ItaskIssuedDTO.getCity());
				resultMap.put("county", ItaskIssuedDTO.getCounty());
				resultMap.put("JHND", ItaskIssuedDTO.getJHND());
				resultMap.put("ZYND", ItaskIssuedDTO.getZYND());
				String XZJSBMJnumber = ItaskIssuedDTO.getTaskNumber().setScale(5, BigDecimal.ROUND_HALF_DOWN).toString();
				//�����ͳ����������ϡ��ù��̱��+�����������ƴ��ΪKey
				resultMap.put(ItaskIssuedDTO.getGCLB()+"T"+ItaskIssuedDTO.getZLLB(), XZJSBMJnumber);//����ȡ����ȡС��λ��5λ
				for (int j = i+1; j < TaskIssuedDTOList.size(); j++) {
					//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
					TaskIssuedDTO JtaskIssuedDTO = TaskIssuedDTOList.get(j);
					//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
					if(idList.contains(JtaskIssuedDTO.getId())) continue;
					//��ȡ���������
					Integer JcountyNumber = JtaskIssuedDTO.getCountyNumber();
					//�ж��ǲ���ͬһ���ؼ�
					if(IcountyNumber.equals(JcountyNumber)){
						//�ж���ҵ��Ⱥͼƻ�����Ƿ�һ��
						if(ItaskIssuedDTO.getZYND().equals(JtaskIssuedDTO.getZYND())
								&&ItaskIssuedDTO.getJHND().equals(JtaskIssuedDTO.getJHND())){
							//�����ͳ����������ϡ��ù��������+�����������ƴ��ΪKey
							String XZJSBMJNmuber2 = JtaskIssuedDTO.getTaskNumber().setScale(5, BigDecimal.ROUND_HALF_UP).toString();
							resultMap.put(JtaskIssuedDTO.getGCLB()+"T"+JtaskIssuedDTO.getZLLB(),XZJSBMJNmuber2);//����ȡ����ȡС��λ��5λ
							//resultMap.put(JtaskIssuedDTO.getGCLB()+"T"+JtaskIssuedDTO.getZLLB(), String.valueOf(JtaskIssuedDTO.getTaskNumber()));
							idList.add(JtaskIssuedDTO.getId());
						}
					}
				}
				resultList.add(resultMap);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		List<HashMap<Object,Object>> result = new ArrayList<HashMap<Object,Object>>();
		//��ȡҳ����Ҫ��ʾ����
		if(resultList.size()<10){
			result=resultList;
		}else{
			result = resultList.subList(beginIndex, lastIndex);
		}
		easyUIResult.setRows(result);
		easyUIResult.setTotal(resultList.size());
		return easyUIResult;
	}

	@Override
	public YzlResult add(HttpServletRequest request, List<String> params) {
		if(params==null || params.size()<=0){
			return YzlResult.build(400, "û��������Ҫ��ӵ�����");
		}
		int result=0;
		//��ȡ��ǰ��¼�û�
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//���ݷ�װ
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
		epcTaskProgress.setCreatetime(new Date());
		epcTaskProgress.setCreator(loginUser.getName());//������
//		epcTaskProgress.setStat("0");
		for (String param : params) {
			if(!StringUtils.isBlank(param)){
			 String[] Strs = param.split(":");
				 if(Strs.length==2){
					 switch (Strs[0]) {
						case "county":  //���keyΪcounty��Ϊ��������� ����
							String countyCode = Strs[1];
							epcTaskProgress.setCountycode(countyCode);//�������������
							String cityCode = countyCode.substring(0, 4);//ȡǮ4λ�� �м��������
							epcTaskProgress.setCitycode(cityCode);
															break;
						case "JHND"://���keyΪJHND��Ϊ�ƻ���� ������
							epcTaskProgress.setJhnd(Strs[1]);
															break;
						case "ZYND"://���keyΪZYND��Ϊ��ҵ��� ������
							epcTaskProgress.setZynd(Strs[1]);
															break;
						default:
							String[] epcTasks = Strs[0].split("T"); //Strs����0 Ϊ���������+T+���������ƴ�ӳ�
							if(epcTasks.length==2){
								epcTaskProgress.setGclb(epcTasks[0]);//����0Ϊ�������
								epcTaskProgress.setZllb(epcTasks[1]);//����1Ϊ�������
							}
							if(CheckStringIsNumberUtils.isDecimals(Strs[1])){//�ж��·�������� �Ƿ�Ϊ����
								epcTaskProgress.setTaskprogress(Float.valueOf(Strs[1]));//����1Ϊ�����·����ݻ���
								result += epcTaskProgressMapper.insert(epcTaskProgress);//ִ�в���
							}else{
								new YzlException(ResultEnum.TASK_BASE_NOT_NUMBER);
							}
															break;
						}
				 }
			}
			
		}
		String countyCode = epcTaskProgress.getCountycode();
		YzlMessage message = new YzlMessage();
		//message.setContent("�����·���������ע�����");
		//message.setStat(MessageStatus.MESSAGE_STATU_NOT_READ.getStat());
		message.setCreatetime(new Date());
		message.setCountycode(countyCode);
		Integer menuId = menuMapper.selectbyPerms(countyCode);
		List<Integer> roleIdList = roleMenuMapper.selectBymenuId((long)menuId);
		for (Integer roleId : roleIdList) {
			message.setRid(roleId);
			messageMapper.insert(message);
		}
		if(result<=0){
			return YzlResult.build(201, "���ݲ���ʧ��");
		}
		return YzlResult.ok();
	}

	/***
	 * ���������·�����
	 */
	@Override
	public YzlResult update(HttpServletRequest request, List<String> params) {
		if(params==null || params.size()<=0){
			return YzlResult.build(400, "û��������Ҫ�޸ĵ�����");
		}
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<Float> taskNumberList = new ArrayList<>();
		epcTaskProgress.setModifier(loginUser.getName());//�޸���
		for (String param : params) {
			if(!StringUtils.isBlank(param)){
			 String[] Strs = param.split(":");
				 if(Strs.length==2){
					 int i=0;
					 switch (Strs[0]) {
						case "county":  //���keyΪcounty��Ϊ��������� ����
							YzlDistrict district = districtMapper.selectByCounty(Strs[1]);//����������ѯ
							String countyCode = district.getAnumber();//��ȡ�ؼ��������
							epcTaskProgress.setCountycode(countyCode);//�������������
							String cityCode = countyCode.substring(0, 4);//ȡǮ4λ�� �м��������
							epcTaskProgress.setCitycode(cityCode);
							i++;
															break;
						case "JHND"://���keyΪJHND��Ϊ�ƻ���� ������
							epcTaskProgress.setJhnd(Strs[1]);
							i++;
															break;
						case "ZYND"://���keyΪZYND��Ϊ��ҵ��� ������
							epcTaskProgress.setZynd(Strs[1]);
							i++;
															break;
						default:
							if(Strs[0].contains("T")){
								int count=0;
								String[] epcTasks = Strs[0].split("T"); //Strs����0 Ϊ���������+T+���������ƴ�ӳ�
								if(epcTasks.length==2){
										epcTaskProgress.setGclb(epcTasks[0]);//����0Ϊ�������
										epcTaskProgress.setZllb(epcTasks[1]);//����1Ϊ�������
										 count = epcTaskProgressMapper.countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
									
									if(CheckStringIsNumberUtils.isDecimals(Strs[1])){//�ж��·�������� �Ƿ�Ϊ����
										epcTaskProgress.setTaskprogress(Float.valueOf(Strs[1]));//����1Ϊ�����·����ݻ���
										//��ѯ�������ݣ� ˵�����ݲ����ڣ���ִ�в���
										if(count==0){
											epcTaskProgress.setCreator(loginUser.getName());//������
											epcTaskProgressMapper.insert(epcTaskProgress);//ִ�в���
										}else{
											
											epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);//ִ���޸�
										}
									}else{
										new YzlException(ResultEnum.TASK_BASE_NOT_NUMBER);
									}
								}
							}
							break;
					  }
				 }
			}
			
		}
		return YzlResult.ok();
	}

	/***
	 * ɾ������ �·�����
	 */
	@Override
	public YzlResult delete(String[] paramsJson,String year,HttpServletRequest request) {
		if(paramsJson==null||paramsJson.length<0){
			//����Ϊ���쳣
			throw new YzlException(ResultEnum.DELETE_PARAM_IS_NULL);
		}
		//�ַ����������ת����List
		List<HashMap<String, String>> DataList = this.StrJsonArraysConvertList(paramsJson);
		for (HashMap<String, String> DatahashMap : DataList) {
				Set<Entry<String,String>> entrySet = DatahashMap.entrySet();
				//��������
				String countyCode=null;//�ر��
				String cityCode = null;//�б��
				String JHND=year;//�ƻ�ʱ��
				String ZYND=year;//��ҵʱ��
				Set<String> zllbSet = new HashSet<>();
				Set<String> gclbSet = new HashSet<>();
//				List<String> file = new ArrayList<>();
				
				for (Entry<String, String> entry : entrySet) {
					//ȡ����ת�����HashMapKey��ֵ
					String key = entry.getKey();
					String string = key.substring(0, 2);
					
					if (key.equals(KeyNameEnum.county.getName())) {
						String county = entry.getValue();
						//ȡ����������
						YzlDistrict district = districtMapper.selectByCounty(county);
						countyCode = district.getAnumber();
					}
					if (string.contains(KeyNameEnum.JH.getName())) {//�ж��Ƿ��Ǽƻ�����
						//��ȡ�ַ���
						String substring = key.substring(2, key.length());//��jh����ĸȥ��
						int indexOf = substring.indexOf("Y");
						
						String zllb = substring.substring(0,indexOf);//�������
						String gclb = substring.substring(indexOf+1, substring.length());//�������
						gclbSet.add(gclb);//��װ���й������
						zllbSet.add(zllb);//��װ�����������
					}
					if (key.equals(KeyNameEnum.CITY_CODE.getName())) {
						cityCode = entry.getValue();
					}
				}
				//��ȡ�ļ�·��
				String url = request.getServletContext().getRealPath("uploadFile/");
//				System.out.println(url);

				List<String> epcTaskProgresses = epcTaskProgressMapper.selectByZJCCZ(ZYND,JHND,countyCode,cityCode,gclbSet,zllbSet);
				for (String string : epcTaskProgresses) {
					if (!StringUtils.isBlank(string)) {
						String path = url+string;
						File file = new File(path);
						file.delete();//ɾ���ļ�
					}
				}
				
//				String filesUrl = epcTaskProgresses.getFilesUrl();
				//ִ��
				epcTaskProgressMapper.deleteByZYNDAndJHNDAndCountyCode(ZYND,JHND,countyCode,cityCode,zllbSet,gclbSet);
		}
		return YzlResult.ok();
	}
	
	//����
	@Override
	public YzlResult derive(HttpServletResponse response,String year,String clcik,String areaCode,String ZLLB,String GCLB) throws IOException {
		EasyUIResult queryTaskData = queryTaskData(1, 10, year, areaCode, ZLLB,GCLB);
		long total = queryTaskData.getTotal();
		List<?> rows = queryTaskData.getRows();
		HSSFWorkbook workbook = new HSSFWorkbook();//excel����
		
		HSSFSheet sheet = workbook.createSheet();//����һ��������
		
		HSSFRow row1 = sheet.createRow(1);//��sheet�ﴴ����
		
		HSSFCellStyle style = workbook.createCellStyle();//������ʽ
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//���еĹ������
		
		//������ȡ���ԵĹ��������ȥ��
		for (Map<String, String> map : taskIssuedDTOs) {
			Set<Entry<String,String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
//				String value = entry.getValue();
				
				String substring = key.substring(0, 2);//jh
				if (substring.equals("jh")) {
					
					int indexOf = key.indexOf("Y");
					String gclb = key.substring(2, indexOf);//�������
					hashSet.add(gclb);
					
				}
			}
		}

		//��ȡ�û�������Ȩ��
		List<String> authoritys = getLoginUserAuthoritys();
		

		//�����ڶ���
		HSSFRow row = sheet.createRow(2);
		HSSFRow row2 = sheet.createRow(3);//����������
		HSSFRow row4 = sheet.createRow(4);//����������
		
		int start = 2;
		int estart = 2;
		for (String gclb : hashSet) {//���������������
			YzlEpc epc = epcMapper.selectByMark(gclb);
			//��ѯ�������ӵ�еĹ���
			List<String> epcTaskProgresses = epcTaskProgressMapper.selectByTaskPosessEpc(year,gclb,clcik,authoritys);
			
			int size = epcTaskProgresses.size()*3;
			//�ϲ���Ԫ��
			sheet.addMergedRegion(new CellRangeAddress(2, 2, start, start+size-1));
			
			//һ����ͷ
			String ename = epc.getEname();
			HSSFCell cell = row.createCell(start);
			cell.setCellValue(ename);
			cell.setCellStyle(style);
			
			//������ͷ
			for (String zllb : epcTaskProgresses) {
				
				YzlTask task = taskMapper.selectByMark(zllb);
				System.out.println("zllb========="+zllb+"=====task========"+task);
				sheet.addMergedRegion(new CellRangeAddress(3, 3, estart, estart+2));
				HSSFCell cell2 = row2.createCell(estart);
				cell2.setCellValue(task.getTname());
				cell2.setCellStyle(style);
				
				int atr = 1;
				for(int j=estart;j<estart+3;j++) {
					
					HSSFCell cell3 = row4.createCell(j);
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
			
			start +=epcTaskProgresses.size()*3;
		}
		
		
		int st = 5;
		//�����������ݽ������
		for (Map<String, String> map : taskIssuedDTOs) {
			
			Set<Entry<String,String>> entrySet = map.entrySet();
			
			HSSFRow createRow = sheet.createRow(st);//�ӵ����п�ʼ���½�����
			
			for (Entry<String, String> entry : entrySet) {
				
				String key = entry.getKey();
				if (key.equalsIgnoreCase("fileName")) {
					continue;
				}
				
				String value = null;
				if(!"fileUrl".equals(key)) {
					value = String.valueOf(entry.getValue());
				}
				
				
				//��ȡǰ�������ַ�
				String string = key.substring(0, 2);
				
				if (key.equals("city")) {//���Ϊ��
					HSSFCell cell = createRow.createCell(0);
					cell.setCellValue(value);
				}
				if (clcik.length() == 4 || clcik.length() == 6) {
					if (key.equals("county")) {
						HSSFCell cell = createRow.createCell(1);
						cell.setCellValue(value);
					}
					
				}
				if (string.equals("jh")) {//���Ϊ�ƻ�
					//jh������ĸȥ����
					String substring = key.substring(2, key.length());
					//16Y8
					int indexOf = substring.indexOf("Y");
					String gclb = substring.substring(0, indexOf);//���������
					String zllb = substring.substring(indexOf+1, substring.length());//���̱��
					
					//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
					gainEpcPutLocation(zllb, gclb, sheet, createRow, value, string);
					
				}
				
				if (key.contains("wc")) {
					//wc������ĸȥ����
					String substring = key.substring(2, key.length());
					//16Y8
					int indexOf = substring.indexOf("Y");
					String gclb = substring.substring(0, indexOf);//���������
					String zllb = substring.substring(indexOf+1, substring.length());//���̱��
					
					//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
					gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
				}
				
				if (string.equals("zj")) {
					//zjh������ĸȥ����
					String substring = key.substring(3, key.length());
					//16Y8
					int indexOf = substring.indexOf("Y");
					String gclb = substring.substring(0, indexOf);//���������
					String zllb = substring.substring(indexOf+1, substring.length());//���̱��
					
					//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
					gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
				}
				
//				if (string.equals("zj")) {
//					//zjh������ĸȥ����
//					String substring = key.substring(3, key.length());
//					//16Y8
//					int indexOf = substring.indexOf("Y");
//					String zllb = substring.substring(0, indexOf);//���������
//					String gclb = substring.substring(indexOf+1, substring.length());//���̱��
//					
//					//��ȡָ���Ĺ��̰����ݷŵ�ָ����λ��
//					gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
//				}
			}
			st++;
		}
		
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
		
		sheet.setColumnWidth(0, 4*280);
		
		//��Ⱦ��Ԫ��
		
		response.reset();
		OutputStream os = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename=excel1.xls");
		
		workbook.write(os);
		
		os.close();
		
		return YzlResult.ok(200);
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
//				int lastRow = region.getLastRow();//������
				int firstColumn = region.getFirstColumn();//��ʼ��
				int lastColumn = region.getLastColumn();//������
				
				if (firstRow == 2) {
					HSSFRow row = sheet.getRow(firstRow);
					HSSFCell cell = row.getCell(firstColumn);
					String ename = cell.getStringCellValue();//ȡ����//ȡ�����������������
					cell.setCellValue(ename);//�Ż�ȥ
					//�����ȡ���Ǳ�ͷ
					if (epc.getEname().equals(ename)) {//�ж������е��������ͱ�ͷ���Ƿ�һ��
						//��ͬ�ͽ���
						
						HSSFRow row2 = sheet.getRow(3);//��ȡ����
						
						for(int k=firstColumn; k <= lastColumn;k++) {
							HSSFCell cell2 = row2.getCell(k);
							if (cell2 != null) {
								String tname = cell2.getStringCellValue();//��������
								cell2.setCellValue(tname);
								
								if (task.getTname().equals(tname)) {//�жϹ������Ƿ�һ��
									
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
	
	//private ��json������д���
	private List<HashMap<String, String>> StrJsonArraysConvertList(String [] paramsJson){
		List<HashMap<String, String>> resultList = new ArrayList<>();
		for (String string : paramsJson) {//��������
			HashMap<String, String> hashMap = new HashMap<>();
			String substring = string.substring(1, string.length()-1);//��ͷ��βȥ��
			String[] strs = substring.split(",");//���ݶ��� , ���зָ�
			for (String str : strs) {//�����ָ�������
				if(str.indexOf(KeyNameEnum.WC.getName())>0||str.indexOf("filesUrl") > 0 || str.contains(KeyNameEnum.ZJH.getName())){
					continue;
				}
				if(str.indexOf(":")>0){
					String[] strings = str.split(":");//����:ð�Ž��зָ�
						for (int i=0;i<strings.length;i++) {//�������鲢�Ұѷָ�õ�����Ū��key��value��ʽ�ŵ�map��
							if (i==1) {
								continue;
							}
								String key = strings[i].substring(1, strings[i].length()-1);
								
								String value = strings[i+1].substring(1, strings[i+1].length()-1);
								hashMap.put(key, value);
						}
				}
			}
			resultList.add(hashMap);
			
		}
		return resultList;
	}

	@SuppressWarnings("null")
	@Override
	public String toLead(MultipartFile[] excelName) {
		
		
		for (MultipartFile multipartFile : excelName) {
			try {
				//��ȡ�ļ���������
				InputStream is = multipartFile.getInputStream();
				
				//ʹ��poi����excel�ĵ�
				HSSFWorkbook workbook = new HSSFWorkbook(is);
				
				//��ȡָ����sheet����
				HSSFSheet sheet = workbook.getSheet("Sheet2");
				String time = null;
				//�������sheet�������к���
				for (Row row : sheet) {
					//��ȡ�к�
					int rowNum = row.getRowNum();
					
					//��ȡʱ��
					if (rowNum == 1) {
						Cell cell = row.getCell(0);
						String value = cell.getStringCellValue();
						int indexOf = value.indexOf("��");
						time = value.substring(indexOf-4,indexOf);//��ʱ���ȡ����
					}
					
					if (rowNum < 2) {//С�����еĶ�����
						continue;
					}
					
					for (Cell cell : row) {//������һ���е�������
						//��ȡ������
						int columnIndex = cell.getColumnIndex();
						int col = columnIndex;
						if (rowNum == 2) {
							if (columnIndex > 0) {
								
								String tname = cell.getStringCellValue();//ȡ����������
								System.out.println(tname + "---");
								
								if (!"".equals(tname)) {//�����Ϊ��
									
									//�����������͵����Ʋ�ѯ�������
									YzlTask task = taskMapper.selectByTname(tname);
									
									if (task == null) {//���Ϊ��˵��û����������Ϊ������
										YzlTask other = taskMapper.selectByTname("����");
										//���ݹ������Ʋ�ѯ
//										String string = tname.replaceAll("\r", "");
										 Pattern p = Pattern.compile("\\s*|\t|\r|\n");//ʹ��������ʽȥ�����пո�
								         Matcher m = p.matcher(tname);
								         String string = m.replaceAll("");
								         
										YzlEpc epc = epcMapper.selectByEname(string);
										
										for(int i=5;;i++) {//�ӵ����п�ʼ��ȡ����
											HSSFRow row2 = sheet.getRow(i);
											
											if (row2 == null) {
												break;
											}
											HSSFCell cell2 = row2.getCell(col);//col����һ�������ݵ�������
											cell2.setCellType(CellType.STRING);//������תΪ�ַ������
											
											String dataValue = cell2.getStringCellValue();
											
											if (!"".equals(dataValue) && !"0".equals(dataValue)) {//������ݲ�Ϊ�ղ��Ҳ�Ϊ0
												HSSFCell cell3 = row2.getCell(0);
												cell3.setCellType(CellType.STRING);
												String county = cell3.getStringCellValue();//��ȡ��������
												//ȥ���ո�
												String cy = county.replace(" ", "");
												
												int insert = insert(cy, epc.getMark(), other.getMark(), dataValue,time);
												System.out.println(insert);
											}
										}
									}else {
										int tstart = col + 1;//��ʾ�����������һ��λ��
										int tend = 0;// ������¼���̽���λ�õļ�¼
										
										for(int i = tstart;;i++) {
											
											Cell cell2 = row.getCell(i);
											String value = cell2.getStringCellValue();
											
											if (!"".equals(value)) {//��������˵���Ѿ�������һ���������
												tend = i - 1;
												break;
											}
										}
										
										//ȡ���������������ж��ٸ�����
										stop:for(int a=rowNum+1;;a++) {//�����п�ʼ
											
											HSSFRow row2 = sheet.getRow(a);//ȡ��һ�е�ֲ
											
											for(int k=tstart-1;k<=tend;k++) {//����������������ռ����кſ�ʼ�ͽ���
												
												HSSFCell cell2 = row2.getCell(k);
												
												int index = cell2.getColumnIndex();
												
												String stringCellValue = cell2.getStringCellValue();//ȡ�ù�������
												System.out.println(stringCellValue);
												
												if (tend == index) {
													break stop;
												}
												//���ݹ������Ʋ�ѯ
												YzlEpc epc = epcMapper.selectByEname(stringCellValue);
												
												if (epc != null) {
													
													for(int x=6;;x++) {//������̲�Ϊ�մӵ��廰��ʼ��������
														
														HSSFRow row3 = sheet.getRow(x);
														if (row3 == null) {
															break;
														}
														HSSFCell cell3 = row3.getCell(k);
														cell3.setCellType(CellType.STRING);//������תΪ�ַ������
														String dataValue = cell3.getStringCellValue();
														
														if (!"".equals(dataValue) && !"0".equals(dataValue)) {//������ݲ�Ϊ�ղ��Ҳ�Ϊ0
															
															HSSFCell cell4 = row3.getCell(0);
															String county = cell4.getStringCellValue();//��ȡ��������
															//ȥ���ո�
															String cy = county.replace(" ", "");
															//�����ݴ���������ݿ�
															int insert = insert(cy, epc.getMark(), task.getMark(), dataValue,time);
															
															//����Ϊ��������
															YzlMessage message = new YzlMessage();
															message.setCountycode(cy);
															message.setCreatetime(new Date());
															message.setNumber(1L);
															message.setJhnd(time);
															message.setZynd(time);
															message.setGclb(epc.getMark());
															message.setZllb(task.getMark());
															messageMapper.insert(message);
															//System.out.println(insert);
														}
														
													}
													
												}
												
											}
										}
									}
								}
							}
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "1";
	}
	
	@Override
	public String toLeadDataDr(MultipartFile[] excelName, String year)  {
		
		//��ѯ���е�����
		List<YzlTask> list = taskMapper.select();
		
		InputStream inputStream = null;
		for (MultipartFile multipartFile : excelName) {
			try {
				inputStream = multipartFile.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//ʹ��poi����excel�ĵ�
//		HSSFWorkbook workbook = null;XSSFWorkbook 
//		XSSFWorkbook workbook = null;
		Workbook workbook= null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e1) {
			try {
				workbook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			
			//��ȡָ����sheet����
			//�������е��к���
//			HSSFSheet sheet = workbook.getSheet("�˲�ƻ������");
			int sheets = workbook.getNumberOfSheets();
			for(int i = 0;i < sheets;i++) {
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println(sheets);
			}
			
//			XSSFSheet sheet = (XSSFSheet) workbook.getSheet("�˲�ƻ������");
			XSSFSheet sheet = (XSSFSheet) workbook.getSheet("�ƻ���ȡ");
//			HSSFSheet sheet = workbook.getSheet("Sheet2");
			
			String epcName = "";
			String taskName = "";
			YzlEpc epc = null;
			YzlTask task = null;
			
			Row epcRow = sheet.getRow(3);
			short lastCellNum = epcRow.getLastCellNum();
			
			Row taskRow = sheet.getRow(6);
			
			Map<Integer, Map<String, String>> epcTaskMap = new HashMap<>();

			for(int i = 2; i < lastCellNum; i++){
				if(epcRow.getCell(i).toString() != ""){
					epcName = (epcRow.getCell(i).toString());
					System.out.println(epcName);
					if((epc = epcMapper.selectByEname(epcName)) != null){
						System.out.println(epc.getMark());
						continue;
					}
					continue;
				}
				if((taskName = taskRow.getCell(i).toString()) != ""){
					System.out.println(taskName);
					if((task = taskMapper.selectByTname(taskName)) != null){
						System.out.println(task.getMark());
						Map map = new HashMap<>();
//						System.out.println("epc================"+epc);
//						System.out.println("epc.getMark()================"+epc.getMark());
						map.put("epcMark", epc.getMark());
						map.put("epcName", epc.getEname());
						map.put("taskMark", task.getMark());
						map.put("taskName", task.getTname());
						epcTaskMap.put(new Integer(i),map);
					}
				}
				System.out.println(epcName+"===="+i+"====="+taskName);
			}
			
			for(Row row : sheet){
				YzlDistrict county = districtMapper.selectByCounty(row.getCell(1).toString());
				if(county == null){
					continue;
				}
				for(int i = 2; i <= lastCellNum; i++){
					if(epcTaskMap.get(i) != null){
						System.out.println("map.toString()=========="+epcTaskMap.get(i).get("epcMark")+"="+epcTaskMap.get(i).get("epcName")
								+"="+epcTaskMap.get(i).get("taskMark")+"="+epcTaskMap.get(i).get("taskName"));
						float taskProgress = checkCellNum(row.getCell(i).toString());
						if(taskProgress != 0 ){
							System.out.println("taskProgress============"+taskProgress);
//							continue;
							YzlUser loginUser = LoginUserUtils.getLoginUser();//��ȡ��ǰ��¼�û�
							YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
							epcTaskProgress.setTaskprogress(taskProgress);
							epcTaskProgress.setGclb(epcTaskMap.get(i).get("epcMark"));
							epcTaskProgress.setZllb(epcTaskMap.get(i).get("taskMark"));
							epcTaskProgress.setCitycode(county.getCitycode());
							epcTaskProgress.setCountycode(county.getAnumber());
							epcTaskProgress.setZynd(year);
							epcTaskProgress.setJhnd(year);
							epcTaskProgress.setCreatetime(new Date());
							epcTaskProgress.setUpdatetime(new Date());
							epcTaskProgress.setCreator(loginUser.getName());
							epcTaskProgress.setStat("0");
							epcTaskProgressMapper.insert(epcTaskProgress);
						}
					}
				}
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "2";
		} 
	}
	
//���鵥Ԫ������ָ�ʽ	
	private float checkCellNum(String num){
		float result = 0;
		try {
			result = Float.parseFloat(num);
		} catch (NumberFormatException e) {
			return (float)0;
		}
		return result;
	}
			
			
			
			
			
			
//			for (Row row : sheet) {
//				
//				System.out.println("====================="+sheet.getRow(3).getCell(2));
//				System.out.println("====================="+sheet.getRow(3).getCell(3));
//				if(row != null){break;}
//				
//				//��ȡ�к�
//				int rowNum = row.getRowNum();
//				
//				if (rowNum<3) {//С�����еĶ�����
//					continue;
//				}
//				
//				for (Cell cell : row) {
//					int columnIndex = cell.getColumnIndex();//��ȡ������
//					
//					if (rowNum == 4) {//�����е�ʱ�������
//						break;
//					}
//					if (rowNum == 3) {
//						if (columnIndex > 8) {//columnIndex�ھ��п�ʼ
//							
//							cell.setCellType(CellType.STRING);//������תΪ�ַ������
//							String stringCellValue = cell.getStringCellValue();//ȡ�Ĺ�����
//							System.out.println(stringCellValue+"   ");
//							
//							if (!stringCellValue.equals("")) {
//								YzlEpc epc = epcMapper.selectByEname(stringCellValue);//���ݹ�������ѯ����
//								String Emark = epc.getMark();//���̱�� id   ���ﱨ��ָ��˵��û�в�ѯ���ù���
//								
//								int start = columnIndex+1;//���̵ĺ�һ��λ��
//								int end = 0; //������ռλ�õĽ�������
//								for(int i=start;;i++) {
//									
//									Cell cell2 = row.getCell(i);
//									
//									if (cell2 == null) {//˵�������һ��������
//										end = i-1;
//										break;
//									}else {
//										String stringCellValue2 = cell2.getStringCellValue();
//										if (!stringCellValue2.equals("")) {
//											end = i-1;
//											break;
//										}
//									}
//									
//								}
//								
//								//ȡ������������
//								for(int i=rowNum+2;i<=6;i++) {//rowNum�����п�ʼ
//									//HSSFRow row2 = 
//									XSSFRow row2 = sheet.getRow(i);//��ȡ��һ�е�ֲ
//									
//									int celStart = 0;
//									int celEnd = 0;
//									
//									int Taskcel = columnIndex;//������������
//									int subCel = 0;
//									
//									for(int j=start-1;j<=end;j++) {//�������������ռ���кſ�ʼ�ͽ���
//										
//										celStart = i;
//										
////										HSSFCell cell2 = row2.getCell(j);
//										XSSFCell cell2 = row2.getCell(j);
//										cell2.setCellType(CellType.STRING);
//										String stringCellValue2 = cell2.getStringCellValue();//������ֵ
//										
//										for (YzlTask yzlTask : list) {//������������
//											
//											boolean contains = false;
//											String tname = yzlTask.getTname();
//											
//											if (i==6) {
//												
//												boolean sub = stringCellValue2.contains("��");
//												
//												if (sub) {
//													subCel = j;//�Ƶ�����
//												}
//											}
//											contains = stringCellValue2.contains(tname);//�Ƿ�����������
//											
//											
//											if (contains) {//˵�������������
//												String Tmark = yzlTask.getMark();//������
//												
//												String firstTime = null;//���ǰ���һ����ʱ��
//												String secondTime = null;//��õڶ���ʱ��
//												
//												String substring = null;
//												
//												if (i==6) {//����ǵ�����
//													
//													//��ȡ�����еı���
////													HSSFRow hssfRow = sheet.getRow(5);
//													XSSFRow hssfRow = sheet.getRow(5);
////													HSSFCell cell3 = hssfRow.getCell(subCel);
//													XSSFCell cell3 = hssfRow.getCell(subCel);
//													substring = cell3.getStringCellValue();
//													
//												}else {//���ǵ�����
//													int indexOf = stringCellValue2.indexOf(tname);//�����������������������λ��
//													
//													substring = stringCellValue2.substring(0, indexOf);//����ǰ����ַ�
//												}
//												int first = 0;//�ƻ�ʱ��
//												int second = 0;//ʵʩʱ��
//												
//												//���������
//												for(int k=0;k<substring.length();k++) {
//													
//													char charAt = substring.charAt(k);//���ÿһ���ַ�
//													String valueOf = String.valueOf(charAt);//ת���ַ���
//													
//													if (valueOf.equals("��")) {
//														if (first==0) {
//															first = k;
//														}else {
//															second = k;
//														}
//													}
//												}
//												firstTime = substring.substring(0, first);//���ǰ���һ����ʱ��
//												if (second != 0) {
//													secondTime = substring.substring(second-4, second);//��õڶ���ʱ��
//												}
//												
//												
//											//��������
//											YzlEpcTaskProgress yzlEpcTaskProgress = new YzlEpcTaskProgress();
//											
//											yzlEpcTaskProgress.setGclb(Emark);
//											yzlEpcTaskProgress.setZllb(Tmark);
//											yzlEpcTaskProgress.setJhnd(firstTime);
//											
//											if (secondTime == null) {
//												yzlEpcTaskProgress.setZynd(firstTime);
//											}else {
//												yzlEpcTaskProgress.setZynd(secondTime);
//											}
//											//columnIndex
//											
//											for(Row row3 : sheet) {
//												
//												int rowNum2 = row3.getRowNum();//������
//												
//												if (rowNum2 > 7) {
//													
//													Cell cell3 = row3.getCell(0);
//													cell3.setCellType(CellType.STRING);//������תΪ�ַ������
//													
//													String zero = cell3.getStringCellValue();
//													
//													if (!zero.equals("һ") && !zero.equals("��") && !zero.equals("��") && !zero.equals("��") && !zero.equals("��")
//												&& !zero.equals("��") && !zero.equals("��") && !zero.equals("��") && !zero.equals("��") && !zero.equals("ʮ")
//												&& !zero.equals("ʮһ") && !zero.equals("ʮ��") && !zero.equals("ʮ��") && !zero.equals("ʮ��")) {
//														
//														//��ȡ��������Ӧ��ֵ
//														Cell tdata = row3.getCell(Taskcel);
//														tdata.setCellType(CellType.STRING);//������תΪ�ַ������
//														String tvalue = tdata.getStringCellValue();
//														
//														if (tvalue != "" && !tvalue.equals("0")) {//�����������ֵ��Ϊ�ղŲŲ�ѯ�����
//															Cell cell4 = row3.getCell(1);
//															String county = cell4.getStringCellValue();//���������
//															//ȥ���ո�
//															String cy = county.replace(" ", "");
//															System.out.println(cy+"====="+stringCellValue+"==="+tname+"==="+zero);
//															//���������Ʋ�ѯ
//															YzlDistrict district = districtMapper.selectByCounty(cy);
//															
//															yzlEpcTaskProgress.setCitycode(district.getCitycode());//���ﱨ��ָ��˵��û��ѯ�������
//															
//															yzlEpcTaskProgress.setCountycode(district.getAnumber());
//															yzlEpcTaskProgress.setTaskprogress(Float.valueOf(tvalue));
//															yzlEpcTaskProgress.setCreatetime(new Date());
//															yzlEpcTaskProgress.setStat("0");
//															//�����ݲ������ݿ�
//															int insertSelective = epcTaskProgressMapper.insertSelective(yzlEpcTaskProgress);
//															System.out.println(insertSelective);
//														}
//														
//													}
//												}
//											}
//										}
//									}
//										Taskcel++;
//								}
//							}
//						}
//					}else {
//						cell.setCellType(CellType.STRING);//������תΪ�ַ������
//					}
//				}
//			}
//		}
//			return "1";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "2";
//		}
		
//	}
	
	/**
	 * �����ݴ���������ݿ�
	 * @param cy ����������
	 * @param emark ���̵ı��
	 * @param tmark �������ı��
	 * @param dataValue Ҫ���������
	 * @param time ʱ��
	 * @return
	 */
	private int insert(String cy,String emark,String tmark,String dataValue,String time) {
		//�������Ʋ�ѯ����
		YzlDistrict district = districtMapper.selectByCounty(cy);
		
		if (district!=null) {
			//��������
			YzlEpcTaskProgress yzlEpcTaskProgress = new YzlEpcTaskProgress();
			
			yzlEpcTaskProgress.setGclb(emark);
			yzlEpcTaskProgress.setZllb(tmark);
			yzlEpcTaskProgress.setJhnd(time);
			yzlEpcTaskProgress.setZynd(time);
			yzlEpcTaskProgress.setCitycode(district.getCitycode());
			yzlEpcTaskProgress.setCountycode(district.getAnumber());
			yzlEpcTaskProgress.setTaskprogress(Float.valueOf(dataValue));
			yzlEpcTaskProgress.setCreatetime(new Date());
			yzlEpcTaskProgress.setStat("0");
			
			//�����ݲ������ݿ�
			int insertSelective = epcTaskProgressMapper.insertSelective(yzlEpcTaskProgress);
			//System.out.println(insertSelective);
			return insertSelective;
		}else {
			return 0;
		}
	}
	///-----------------------------��������
	
	private List<String> getLoginUserAuthoritys(){
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		return authoritys;
	}
	
	//ͨ����ҵ��Ⱥ͵���������Ų�ѯ�����·����ݸ����ء���������ؼ���Ž��з���
	@Override
	public EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows,
			HttpServletRequest request, String areaCode, String year,String GCLB) {
		taskIssuedDTOs.clear();
		//�����ǰ����Null,��Ĭ���ǵ�ǰʱ�����
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		//��ȡ��ǰ��¼�û���Ȩ��
		List<String> authoritys = getLoginUserAuthoritys();
		List<TaskIssuedDTO> TaskIssuedDTOList = epcTaskProgressMapper.queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(authoritys,areaCode,year,GCLB);
//		taskIssuedDTOs.add(TaskIssuedDTOList);
		EasyUIResult result = this.pageQueryUtils(TaskIssuedDTOList, page, rows);
		return result;
	}
	
	
	//��ѯ�м����ݲ�����ͳ��
	

	//��ѯ�м����ݲ�����ͳ��
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		taskIssuedDTOs.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���м�����
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys,ZLLB,GCLB,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//���б��
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//������ظ����б����ӽ�ȥ
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//��ѯ������ɵ����� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,GCLB);
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
						int log = 0;
						for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
							YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
							//�ѹ��� ��������ź��������͹��������ͬ�Ľ��кϲ�
							if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //�ж��м���������Ƿ����
									&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //�ж���������Ƿ����
										&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//�жϹ�������Ƿ����
								String HGMJ = JepcAndTaskStaticti.getHGMJ();
								if(HGMJ == null){break;}
								log++;
								//��ȡ��������ɵ�����
								Double hgmj = Double.valueOf(JepcAndTaskStaticti.getHGMJ());
								double zjh =hgmj/IepcTaskProgress.getTaskprogress().doubleValue();
								// ������������ݳ��������������õ�  ������ɱ����Ľ��   �ý������ȡ������С����2λ
								map.put("zjh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.format("%.2f", zjh*100)+"%");
								map.put("wc"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.valueOf(hgmj));
							}
						}
						if (log == 0) {
							map.put("zjh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), 0+"");
							map.put("wc"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), 0+"");
						}
						map.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������			
						//��װ�м������·������� ������С�����4λ
						map.put("jh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					}
					
					List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
				/*	HashMap countDataMap = new HashMap<>();
					YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
					YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//������������Ų�ѯ����
					countDataMap.put("city", district.getCity());
					for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
						YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
						if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //�ж��м���������Ƿ����
								&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //�ж���������Ƿ����
									&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//�жϹ�������Ƿ����
							//��ȡ��������ɵ�����
							Double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
							double zjh =XTJSBMJDouble/IepcTaskProgress.getTaskprogress().doubleValue();
							// ������������ݳ��������������õ�  ������ɱ����Ľ��   �ý������ȡ������С����2λ
							countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
							countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
						}
					}
					countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������			
					//��װ�м������·������� ������С�����4λ
					countDataMap.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
*/					//������ͳ�ƺ÷�װ��List������
/*					if (record < epcTaskProgressList.size()) {
						countDataList.add(map);
					}*/
//					String filesUrl = IepcTaskProgress.getFilesUrl();//���ļ�·�����в��
					String filesUrl = IepcTaskProgress.getFilesUrl();
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
					map.put("jh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
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
				for (String messageCountyCode : messageCountyCodeList) {
					String messageCityCode = AdministrativeCode.countyCodeConvertCityCode(messageCountyCode);
					if(cityCode.equals(messageCityCode)){
						mMap.put("newData", "<font color='red'>*</font>");
					}
				}
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
		taskIssuedDTOs.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//���ݽ������ҳ��List
		//��ѯ�����·������� ��ͳ���ؼ�����
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys,ZLLB,GCLB,null);
		//��ѯ������ɵ����� ��ͳ���ؼ�����
//		taskIssuedDTOs.add(epcTaskProgressList);
		//��ѯ������ɵ����� ��ͳ���м�����
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,GCLB);
		System.out.println(stats+"stats8888888888888888888888888888888888888888888888888888888");
		try {
			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//�洢�Ѿ���ӹ�������id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//������������Ų�ѯ����
				double taskprogress=0L;
				if(IepcTaskProgress.getTaskprogress()!=null){
					taskprogress = IepcTaskProgress.getTaskprogress();//��ȡ�����·���������
				}
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				int log = 0;
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //�ж��ؼ���������Ƿ����
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//�ж���������Ƿ����
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //�жϹ�������Ƿ����
						String HGMJ = JepcAndTaskStaticti.getHGMJ();
						if(HGMJ == null){break;}
						log++;
						//��ȡ�����·������� 
						//��ȡ��������ɵ�����
					//double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
						double hgmj = Double.valueOf(JepcAndTaskStaticti.getHGMJ());
						System.out.println(hgmj+"XTJSBMJDouble4444***********************************************");
						// ������������ݳ��������������õ� ռ������ɱ����Ľ��   
						double zjhNumber = hgmj/taskprogress;
						//��װ ���������ռ����ƻ����ı���  ����һλС��
						countDataMap.put("zjh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(),  String.format("%.1f", zjhNumber*100)+"%");
						countDataMap.put("wc"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.valueOf(hgmj));
					}
				}
				if (log == 0) {
					countDataMap.put("zjh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(),  0);
					countDataMap.put("wc"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), 0);
				}
				countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//��װ�м��������
//				String filesUrl = IepcTaskProgress.getFilesUrl();//���ļ�·�����в��
				String filesUrl = IepcTaskProgress.getFilesUrl();
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
				countDataMap.put("jh"+IepcTaskProgress.getGclb()+"Y"+IepcTaskProgress.getZllb(), String.format("%.4f", taskprogress));
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
						List<String> messageCountyCodeList= messageMapper.queryCountyCodeByStatuAndCountyCode(authoritys, keywork);//��ѯ���¼�¼��
						for (String messageCountyCode : messageCountyCodeList) {
							if(countyCode.equals(messageCountyCode)){
								mMap.put("newData", "<font color='red'>*</font>");
							}
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
	
	//ҳ���ѯ�������� areaCode:�������  ZLLB:�������  GCLB���������
	@Override
	public EasyUIResult queryTaskData(Integer page, Integer rows, String year, String areaCode,String ZLLB,String GCLB) {
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
		//�����û�Id��ѯ����Ȩ�ޱ�ʶ
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		//��ȡ��ǰ��¼�û�������Ȩ��
		List<Map<String,String>> resultList = new ArrayList<>();
		if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//�����Ⱦ��ǵ�ǰѡ�����������
			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB); //��ѯ��ͳ���м�����
		}else{
			//��ѯ��ͳ���ؼ�����
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB);
		}
		for (Map<String, String> map : resultList) {
			taskIssuedDTOs.add(map); //���ڴ�ȡ����������
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

	
	@Override
	public YzlResult addData(List<String> taskNumbers, String year, String countyCode,String uploadFiles) {
		if(StringUtils.isBlank(year)){
			logger.error("����ƻ�����ҵ���Ϊ��: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("�����������Ϊ��: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		long flag=0L;//��¼���ݲ���� ����
		YzlUser loginUser = LoginUserUtils.getLoginUser();//��ȡ��ǰ��¼�û�
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();//������� ��װ���ݵĶ���
		epcTaskProgress.setZynd(year);//��װ��ҵ���
		epcTaskProgress.setJhnd(year);//��װ�ƻ����
		epcTaskProgress.setCountycode(countyCode);//��װ�ر��
		epcTaskProgress.setCreator(loginUser.getName());//������								//��ǰ��¼�û�			
		//��������ŵ�ǰ4λ ���б��
		String cityCode = countyCode.substring(0, 4);//��ȡ ���������
		epcTaskProgress.setCitycode(cityCode);//��װ���������
		for (String param : taskNumbers) {
			if(param.contains("jh") && param.contains("Y")){
				String taskDataKeyAndValue = param.split("jh")[1];//��ȡ��jhǰ׺
				String[] KeyAndValue = taskDataKeyAndValue.split("Y");//��key=(ZLLB+GCLB)  value�����·��Ļ��� ��ȡ�ֿ�
				if(KeyAndValue.length==2){
					String ZLLB = KeyAndValue[0];//��ȡZLLB
					String taskDataGCLBAndValue = KeyAndValue[1];
					if(StringUtils.isBlank(taskDataGCLBAndValue)){
						continue;
					}
					if(taskDataGCLBAndValue.contains(":")){
						String[] GCLBAndValue = taskDataGCLBAndValue.split(":");
						if (GCLBAndValue.length == 2) {
							String GCLB = GCLBAndValue[0];//��ȡ�������3
							float newtaskBaseNumber=0f;//�洢�����·����ݵĻ���
							if(!StringUtils.isBlank(GCLBAndValue[1])&&CheckStringIsNumberUtils.isFloat(GCLBAndValue[1])){
								newtaskBaseNumber = Float.valueOf(GCLBAndValue[1]);//��ȡ�·�����Ļ���
							}else{
								logger.error("�����·��Ļ������Ǵ����֣�taskBase="+GCLBAndValue[1]);
								throw new YzlException(ResultEnum.TASKBASE_NOT_IS_NUMBER);
							}
							//���ݷ�װ  
							epcTaskProgress.setGclb(GCLB);
							epcTaskProgress.setZllb(ZLLB);
							//epcTaskProgress.setStat("0");
							//���ݹ�����𡢼ƻ���ȡ���ҵ��ȡ����������ؼ���� ��ѯ�������Ƿ�����ӹ�
							List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
							Date currentTime = new Date();
							if(epcTaskProgressList.size()>0){
								for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {
									float oldNumber = yzlEpcTaskProgress.getTaskprogress()==null?0f:yzlEpcTaskProgress.getTaskprogress();
									yzlEpcTaskProgress.setUpdatetime(currentTime);
									yzlEpcTaskProgress.setTaskprogress(oldNumber+newtaskBaseNumber);
									flag += epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(yzlEpcTaskProgress);//ִ�и���.
								}
							}else{
								
								epcTaskProgress.setFilesUrl(uploadFiles);//���ļ����Ž����ݿ�
								epcTaskProgress.setCreatetime(currentTime);//����ʱ��
								epcTaskProgress.setUpdatetime(currentTime);//����ʱ��
								epcTaskProgress.setTaskprogress(Float.valueOf(GCLBAndValue[1]));
								epcTaskProgress.setStat("0");
								//flag += epcTaskProgressMapper.insert(epcTaskProgress);//ִ�в���
								flag += epcTaskProgressMapper.insertSelective(epcTaskProgress);
							}
							
							//����Ϊ��������
							YzlMessage message = new YzlMessage();
							message.setCountycode(countyCode);
							message.setCreatetime(currentTime);
							message.setNumber(1L);
							message.setJhnd(year);
							message.setZynd(year);
							message.setGclb(GCLB);
							message.setZllb(ZLLB);
							messageMapper.insert(message);
						}
					}
				}
			}
		}
		if(flag==0){
			logger.error("�����·����ݲ���ʧ��");
			throw new YzlException(ResultEnum.DATA_INSERT_FAILURE);
		}
		return YzlResult.ok();
	}
	

	@Override
	public YzlResult updateData(List<String> taskNumbers, String year, String countyCode, String uploadFiles) {
		if(StringUtils.isBlank(year)){
			logger.error("����ƻ�����ҵ���Ϊ��: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("�����������Ϊ��: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		if(StringUtils.isBlank(year)){
			logger.error("����ƻ�����ҵ���Ϊ��: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("�����������Ϊ��: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		long number=0l;//��¼���ݲ���� ����
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();//������� ��װ���ݵĶ���
		epcTaskProgress.setZynd(year);//��װ��ҵ���
		epcTaskProgress.setJhnd(year);//��װ�ƻ����
		if(!CheckStringIsNumberUtils.isInteger(countyCode)){//�ж��Ƿ��Ǵ�����
			//��������Ϊ�ؼ���ţ���������Ϊ�ؼ�����
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//�����ص����Ʋ�ѯ����
			countyCode = district.getAnumber();//��ȡ�ؼ����
		}
		epcTaskProgress.setCountycode(countyCode);//��װ�ر��
		//��������ŵ�ǰ4λ ���б��
		String cityCode = countyCode.substring(0, 4);//��ȡ ���������
		epcTaskProgress.setCitycode(cityCode);//��װ���������
		YzlUser user = LoginUserUtils.getLoginUser();//��ȡ��ǰ��¼�û�
		String name = user.getName();//��ȡ�û���
		for (String param : taskNumbers) {
			if(param.contains("jh") && param.contains("Y")){
				String taskDataKeyAndValue = param.split("jh")[1];//��ȡ��jhǰ׺
				String[] KeyAndValue = taskDataKeyAndValue.split("Y");//��key=(ZLLB+GCLB)  value�����·��Ļ��� ��ȡ�ֿ�
				if(KeyAndValue.length==2){
					String ZLLB = KeyAndValue[0];//��ȡZLLB
					String taskDataGCLBAndValue = KeyAndValue[1];
					if(taskDataGCLBAndValue.contains(":")){
						String[] GCLBAndValue = taskDataGCLBAndValue.split(":");
						if(GCLBAndValue.length==2){
							String GCLB = GCLBAndValue[0];//��ȡ�������
							String taskBase = GCLBAndValue[1];//��ȡ�·�����Ļ���
							//���ݷ�װ
							epcTaskProgress.setGclb(GCLB);//�������
							epcTaskProgress.setZllb(ZLLB);//�������
							Date currentTime = new Date();
							epcTaskProgress.setUpdatetime(currentTime);
							epcTaskProgress.setModifier(name);//�޸���
							if(!CheckStringIsNumberUtils.isDecimals(taskBase)){//�ж���������Ƿ��Ǵ�����
								logger.error("�����·��Ļ������Ǵ����֣�taskBase="+taskBase);
								throw new YzlException(ResultEnum.TASKBASE_NOT_IS_NUMBER);
							}
							epcTaskProgress.setTaskprogress(Float.valueOf(taskBase));
							//���ݹ�����𡢼ƻ���ȡ���ҵ��ȡ����������ؼ���� ��ѯ�������Ƿ�����ӹ�
							int count = epcTaskProgressMapper.countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
							if(count==0){//����δ��ӹ�
//								epcTaskProgress.setFilesUrl(uploadFiles);//�ϴ�������ļ�
								epcTaskProgress.setStat("0");
								epcTaskProgress.setFilesUrl(uploadFiles);
								epcTaskProgress.setCreator(name);//���ݴ�����
								epcTaskProgress.setCreatetime(currentTime);//����ʱ��
								//ִ�в���
								number += epcTaskProgressMapper.insertSelective(epcTaskProgress);
							}else{
								//ִ�и���
								number += epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);//ִ�и���
							}
							//����Ϊ��������
							YzlMessage message = new YzlMessage();
							message.setCountycode(countyCode);
							message.setCreatetime(currentTime);
							message.setNumber(1L);
							message.setJhnd(year);
							message.setZynd(year);
							message.setGclb(GCLB);
							message.setZllb(ZLLB);
							messageMapper.insert(message);
						}
					}
				}
			}
		}
		if(number==0){
			logger.error("�����·����ݸ���ʧ��");
			throw new YzlException(ResultEnum.DATA_INSERT_FAILURE);
		}

		return YzlResult.ok();
	}
	
	
	//�ϴ�
	@Override
	public Map uploadFile(MultipartFile[] files,HttpServletResponse response,HttpServletRequest request) {
		//����uuid
		UUID uuid = UUID.randomUUID();
		InputStream inputStream = null;
		String originalFilename = null;
		String fileUrl = null;
		for (MultipartFile multipartFile : files) {
			try {
				inputStream = multipartFile.getInputStream();
				originalFilename = multipartFile.getOriginalFilename();//��ȡ�ļ��������Լ���ʽ
				//�������uuid
				String contextPath = request.getContextPath();
				//String string = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());//��ȡ�ļ��ĺ�׺
				//�µ��ļ���
				//String fileName = uuis+string;
				//��ȡ�ϴ���·��
				String url = request.getServletContext().getRealPath("");
				//��ȡ�ϴ���·��
				String folder = request.getServletContext().getRealPath(uploadFolderURI);
				//��ȡ��ǰ������,���õ�ǰ�����ڵ��ļ���
				Date date = new Date();
				//����ת�ַ���
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
				String format = simpleDateFormat.format(date);
				System.out.println("============================"+folder);
				folder+="\\"+format;//ƴ�ӳɾ���·��
				File file = new File(folder);
				//�����ļ�Ŀ¼v
				// ���Ŀ¼�����ھʹ���
			   if (!file.exists()) {
				   file.mkdirs();//�����ļ�
			   }
			   //������ݿ�洢�ļ���(Ψһ��ʶ)
			   String fileName=uuid.toString()+FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark()+originalFilename;
			   //�ļ�·��+�ļ���ƴ�ӷ���ҳ��
			   fileUrl=format+"\\"+fileName;
			   //��ȡ�����
				FileOutputStream os = new FileOutputStream(file+"\\"+fileName);
				//������ȡ�����浽ָ��Ŀ¼
				int tem=0;
				byte byteData [] = new byte[1024];
				while ((tem=inputStream.read(byteData))!=(-1)) {
					os.write(byteData, 0, tem);
				}
				//���ϴ����ļ����浽��Ӧ���ļ�Ŀ¼��
				os.flush();
				os.close();
				//�ر���
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map resultMap = new HashMap<>();
		InetAddress localHost = null;
		try {
			localHost = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMap.put("url", "http://"+localHost+":8080/yzl-web/"+uploadFolderURI+originalFilename);
		resultMap.put("fileName", originalFilename);
		resultMap.put("status", 200);
		resultMap.put("fileUrl", fileUrl);
		return resultMap;
	}

	@Override
	public YzlResult delete_uploadFileService(String deleteFileName, String countyCode, String year,String uploadFiles,String cityName,HttpServletRequest request) {
		if(StringUtils.isBlank(year)){
			logger.error("���Ϊ��");
			throw new YzlException(ResultEnum.COUNTYCODE_YEAR_ERROR);
		}
		if(StringUtils.isBlank(countyCode)&&StringUtils.isBlank(cityName)){
			logger.error("�ؼ��������Ϊ��");
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		if(countyCode!=null&&!CheckStringIsNumberUtils.isInteger(countyCode)){//�ж��Ƿ��Ǵ�����
			//��������Ϊ�ؼ���ţ���������Ϊ�ؼ�����
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//�����ص����Ʋ�ѯ����
			countyCode = district.getAnumber();//��ȡ�ؼ����
		}
		
		String realPath = request.getServletContext().getRealPath("/uploadFile"+"/"+deleteFileName);
		//�h���ļ�
		File file = new File(realPath);
		if(file.exists()){//�ж��ļ��Ƿ����
			file.delete(); //ִ��ɾ��
		}
		if(file.exists()){//�ļ�ɾ����ɺ����ж��ļ��Ƿ񻹴��ڣ�������ɾ��ʧ��
			return YzlResult.build(400, "�ļ�ɾ��ʧ��");			
		}
/*		if(deleteFileName.indexOf("\\")>0||deleteFileName.indexOf("&")>0)
		{
			deleteFileName = deleteFileName.substring(deleteFileName.indexOf("\\")+1, deleteFileName.indexOf("=="));
		}
		deleteFileName=deleteFileName.trim();
		String cityCode=null;
		if(cityName!=null&&!CheckStringIsNumberUtils.isInteger(cityName)){
			cityCode = districtMapper.selectCitycodeByCity(cityName);//�����е����Ʋ�ѯ����
		}
		YzlUser user = LoginUserUtils.getLoginUser();
		//�h�����ݿ��е��ļ�
		epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);*/
		return this.updateUploadFileData(year, countyCode, uploadFiles, cityName);
	}

	
	
	@Override
	public YzlResult downloadUploadFile(String fileUrl,HttpServletRequest request,HttpServletResponse response) {
		String fileNmae=fileUrl.trim();//ȥ�ո�
		String uri = request.getServletContext().getRealPath(uploadFolderURI);
		String url = uri+"\\"+fileNmae;
//		System.out.println("=========================="+url);
//		if(url != null) {return null;}
		File file = new File(url);
		try {
			if (!file.exists()) {
				response.sendError(404, "File not found!");
			}
			// ����һ����������������
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[10240];
			int len = 0;
			response.reset(); //�����趨

			String fileName=fileUrl;
			System.out.println("fineNmae="+fileName);
			if(fileUrl.contains(FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark())){//�ж��Ƿ�����ļ���ʶ��
				String[] fileMarkAndfileName = fileUrl.split(FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark());//��Ψһ��ʶ���ļ������в��
				if(fileMarkAndfileName.length==2){
					fileName = fileMarkAndfileName[1];//��ȡ�ļ���
				}
			}
			// 3.��������ͷ��һ����
			// ��̬����ļ���MIME������:
			String mimeType = request.getServletContext().getMimeType(fileName);
			response.setContentType(mimeType);
			String agent = (String)request.getHeader("USER-AGENT");  
			String downloadFileName;
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
            {
                downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";    
            }
            else
            {
                downloadFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
			System.out.println("dowloadFileName==============================="+downloadFileName);
			response.setHeader("content-disposition", "attachment;filename="+downloadFileName);
			//��ȡ��һ����������������
			ServletOutputStream servletOutputStream = response.getOutputStream();
			// ��ʼ���
			while ((len = br.read(buf)) > 0)
			{
				servletOutputStream.write(buf, 0, len);
			}
			// �ر�������
			br.close();
			servletOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return YzlResult.ok();
	}

	@Override
	public YzlResult updateUploadFileData(String year, String countyCode, String uploadFiles,String cityName) {
		if(StringUtils.isBlank(year)){
			logger.error("���Ϊ��");
			throw new YzlException(ResultEnum.COUNTYCODE_YEAR_ERROR);
		}
		YzlUser user = LoginUserUtils.getLoginUser();
		if(StringUtils.isBlank(countyCode)&&StringUtils.isBlank(cityName)){
			logger.error("�ؼ��������Ϊ��");
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		
		String cityCode="";
		if(StringUtils.isNotEmpty(countyCode)&&!CheckStringIsNumberUtils.isInteger(countyCode)){//�ж��Ƿ��Ǵ�����
			//��������Ϊ�ؼ���ţ���������Ϊ�ؼ�����
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//�����ص����Ʋ�ѯ����
			countyCode = district.getAnumber();//��ȡ�ؼ����
			cityCode = district.getCitycode();
		}else if(StringUtils.isNotEmpty(cityName)&&!CheckStringIsNumberUtils.isInteger(cityName)){
			cityCode = districtMapper.selectCitycodeByCity(cityName);//�����е����Ʋ�ѯ����
		}
		int flag = epcTaskProgressMapper.updatefilesUrlByCountCodeAndZYND(year, countyCode,cityCode, uploadFiles, user.getName(),new Date());
		if(flag==0){
			logger.error("�ļ��ϴ��������ݿ�ʧ��");
			return YzlResult.build(400, "�ļ��ϴ�ʧ��");
		}
		//����Ϊ��������
		//YzlMessage message = new YzlMessage();
		//message.setCountycode(countyCode);
		//message.setCreatetime(new Date());
		//message.setNumber(1L);
		//messageMapper.insert(message);
		return YzlResult.ok();
	}

	@Override
	public YzlResult addData(List<String> taskNumbers, String year, String countyCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
