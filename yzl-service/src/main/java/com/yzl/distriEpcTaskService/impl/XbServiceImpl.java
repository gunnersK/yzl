package com.yzl.distriEpcTaskService.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.distriEpcTaskService.XbService;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.TaskStatuUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DrilldownNode;
import com.yzl.utils.vo.HighchartsResultVO;


@Service
public class XbServiceImpl implements XbService {

	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	/**����ͼ*/
	/***����ͼ������ʾ*/
	@Override
	public List<HighchartsResultVO> pie(HttpServletRequest request,String year) {
		//List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		//���yearΪ�� ���ȡ��ǰʱ������ ����ֵ
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> aNumbers = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(CollectionUtils.isEmpty(aNumbers)){
			throw new YzlException(ResultEnum.USER_NOT_MENU);
		}
		List<String> taskStat = new ArrayList<>();
		//��ѯС�������ͨ�������� ������������Ž��з���
		List<TaskDTO> XTJSBMJGroupByCity = xbMapper.queryXTJSBMJAndCityGroupByCity(aNumbers,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		//��ѯ�����·������� ������������Ž��з���
		//List<TaskDTO> SumXTJSBMJGroupByCity = xbMapper.queryXTJSBMJGroupByCity(aNumbers,year,null);

		Double CityXTJSBMJ = 0.0;
		CityXTJSBMJ = xbMapper.sumCityXTJSBMJ(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());//ͳ�����������������ֵ
		
		BigDecimal CityXTJSBMJDecimal = null;
		
		if (CityXTJSBMJ == null) {
			CityXTJSBMJDecimal = new BigDecimal("0");
		}else {
			CityXTJSBMJDecimal = new BigDecimal(CityXTJSBMJ);
		}
		
		List<HighchartsResultVO> highchartsResultVO = new ArrayList<>();
		for (TaskDTO CityXTJSBMJtaskDTO : XTJSBMJGroupByCity ) {
			//��ȡ�м���������
			String cityCode = CityXTJSBMJtaskDTO.getaNumber();
			String cityName = CityXTJSBMJtaskDTO.getCity();
			HighchartsResultVO resultVO = new HighchartsResultVO();	
			BigDecimal CityXTJSBMJtaskNumber=CityXTJSBMJtaskDTO.getTaskNumber();
			if(CityXTJSBMJDecimal!=BigDecimal.ZERO){//�ж��Ƿ�Ϊ0
				Double number = CityXTJSBMJtaskNumber.divide(CityXTJSBMJDecimal, 4,RoundingMode.HALF_UP).doubleValue();
				resultVO.setY(number*100);
//				resultVO.setY(number);
			}else{
				resultVO.setY(0.0);
			}
			resultVO.setName(cityName);
			resultVO.setDrilldown(cityName);
			//�����ӽڵ�
			DrilldownNode drilldownNode = new DrilldownNode();
		    drilldownNode.setName(cityName);
		    drilldownNode.setId(cityName);
		    //drilldownNode.setY(number*100);
			 //������� ��ѯ�����ͨ����С������  ������������Ž��з���
		    List<TaskDTO> countyFinishNumber = xbMapper.queryXTJSBMJAndCountyByCityCode(aNumbers, year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString(),cityCode);// ��ָ�������ͨ��
		    List<List> voList= new ArrayList<>();
	    	for (TaskDTO countyfinishTaskNumber : countyFinishNumber) {
				//��װ�ؼ�����
				List data = new ArrayList();
				data.add(countyfinishTaskNumber.getCounty());
    			if(CityXTJSBMJtaskNumber!=BigDecimal.ZERO){
    				//���㵱ǰ��������ɰٷֱ� �������� ȡС�����4λ
    				Double cityNumber = countyfinishTaskNumber.getTaskNumber().divide(CityXTJSBMJtaskNumber, 4,RoundingMode.HALF_UP).doubleValue();
    				data.add(cityNumber*100);
//    				data.add(cityNumber);
    			}else{
    				data.add(0);
    			}
					voList.add(data);
			}
	    	drilldownNode.setData(voList);
	    	resultVO.setDrilldownNode(drilldownNode);
	    	highchartsResultVO.add(resultVO);
		}
		//���������·������ж��Ƿ�������������Ա   
		if(!aNumbers.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			//��������������Ա������ֻ��ȡ���ؼ���
			List<HighchartsResultVO> countHighchartsResultVOList = new ArrayList<>();
			for (HighchartsResultVO countyHighchartsResultVO : highchartsResultVO) {
				DrilldownNode countyDrilldownNode = countyHighchartsResultVO.getDrilldownNode();
				List<List> data = countyDrilldownNode.getData();
				for (List list : data) {
					HighchartsResultVO resultHighchartsVO = new HighchartsResultVO();
					for (int i=0; list.size()>i;i++) {
						resultHighchartsVO.setName((String)list.get(0));
						resultHighchartsVO.setY((Double)list.get(1));
						break;
					}
					countHighchartsResultVOList.add(resultHighchartsVO);
				}
			}
			return countHighchartsResultVOList;
		}
	    return highchartsResultVO;
	}

	
	/***����ͼ������ʾ*/
	@Override
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request,String year) {
		//List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		//���yearΪ�� ���ȡ��ǰʱ������ ����ֵ
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> aNumbers = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(CollectionUtils.isEmpty(aNumbers)){
			throw new YzlException(ResultEnum.USER_NOT_MENU);
		}
		List<String> taskStat = new ArrayList<>();
		taskStat.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		//��ѯС�������ͨ�������� ������������Ž��з���
		List<TaskDTO> XTJSBMJGroupByCity = xbMapper.queryXTJSBMJGroupByCity(aNumbers,year,taskStat);
		//��ѯ�����·������� ������������Ž��з���
		List<TaskDTO> TaskGroupByCity = epcTaskProgressMapper.queryTaskGroupByCity(aNumbers,year);	
		
		List<HighchartsResultVO> highchartsResultVO = new ArrayList<>();
		for (TaskDTO CityXTJSBMJtaskDTO : TaskGroupByCity ) {
			//��ȡ�м���������
			String cityCode = CityXTJSBMJtaskDTO.getaNumber().substring(0, 4);
			int cityFlag=0;//�ж��·�������  �Ƿ������ύ
			for (TaskDTO CitytaskDTO : XTJSBMJGroupByCity ) {
				try {
					Map<String,Object> map = new HashMap<>();
					//�ж������������Ƿ�һ��
					if(CitytaskDTO.getaNumber().equals(cityCode)){
						cityFlag++;
						//����������ɱ���   �������� ȡС�����4λ
						Double number = CitytaskDTO.getTaskNumber().divide(CityXTJSBMJtaskDTO.getTaskNumber(), 4,RoundingMode.HALF_UP).doubleValue();
						//ȡ��������
						String city = CityXTJSBMJtaskDTO.getCity();
						//��װ�м����� model
						HighchartsResultVO resultVO = new HighchartsResultVO();
						
						resultVO.setName(city);
						resultVO.setDrilldown(city);
						resultVO.setY(number*100);
//						System.out.println("====number===="+number);
						//�����ӽڵ�
						DrilldownNode drilldownNode = new DrilldownNode();
					    drilldownNode.setName(city);
					    drilldownNode.setId(city);
					    drilldownNode.setY(12.5D);
					    xbMapper.queryXTJSBMJGroupByCounty(aNumbers);
					    //������� ��ѯ���񷢲�������  ������������Ž��з���
					    List<TaskDTO> epcTaskNumber = epcTaskProgressMapper.selectTaskNumberGroupByCountyAndByMark(aNumbers, cityCode,year);
					  //������� ��ѯ�����ͨ����С������  ������������Ž��з���
					    List<TaskDTO> finishNumber = xbMapper.queryXTJSBMJGroupCountyAndByCity(aNumbers, cityCode,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode());// ��ָ�������ͨ��
					    List<List> voList= new ArrayList<>();
					    for (TaskDTO countyTaskNumber : epcTaskNumber) {
					    	//��ʶ ��ǰ�ص�������û�п�ʼ   Ĭ��Ϊ0����û�н���
					    	int countyFlag=0;
					    	for (TaskDTO countyfinishTaskNumber : finishNumber) {
								if(countyTaskNumber.getaNumber().equals(countyfinishTaskNumber.getaNumber())){
									//���㵱ǰ��������ɰٷֱ� �������� ȡС�����4λ
									Double cityNumber = countyfinishTaskNumber.getTaskNumber().divide(countyTaskNumber.getTaskNumber(), 4,RoundingMode.HALF_UP).doubleValue();
									//��װ�ؼ�����
									List data = new ArrayList();
									data.add(countyTaskNumber.getCounty());
									data.add(cityNumber*100);
//									System.out.println("====number1===="+number);
									voList.add(data);
									countyFlag++;
								}
							}
					    	//�������û���ύ �������ͨ�� ��������ɱ�����0%���
					    	if(countyFlag==0){
								List data = new ArrayList();
								data.add(countyTaskNumber.getCounty());
								data.add(0.0D);
								voList.add(data);
					    	}
					    	
						}	
					    drilldownNode.setData(voList);
					    resultVO.setDrilldownNode(drilldownNode);
					    highchartsResultVO.add(resultVO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(cityFlag==0){//�������0 ���·�������  û�н����ύ���������ͨ��
				HighchartsResultVO resultVO = new HighchartsResultVO();
				String city = CityXTJSBMJtaskDTO.getCity();
				resultVO.setName(city);
				resultVO.setDrilldown(city);
				resultVO.setY(0.0d);//����Ϊ0
				List<TaskDTO> epcTaskNumber = epcTaskProgressMapper.selectTaskNumberGroupByCountyAndByMark(aNumbers, cityCode,year);
				List<List> voList= new ArrayList<>();
				for (TaskDTO taskDTO : epcTaskNumber) {	
					List data = new ArrayList();
					data.add(taskDTO.getCounty());
					data.add(0.0D);
					voList.add(data);
				}
				//�����ӽڵ�
				DrilldownNode drilldownNode = new DrilldownNode();
				drilldownNode.setData(voList);
				drilldownNode.setId(city);
				drilldownNode.setName(city);
				resultVO.setDrilldownNode(drilldownNode);
				highchartsResultVO.add(resultVO);
			}
		}
		if(!aNumbers.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			List<HighchartsResultVO> countHighchartsResultVOList = new ArrayList<>();
			for (HighchartsResultVO countyHighchartsResultVO : highchartsResultVO) {
				DrilldownNode countyDrilldownNode = countyHighchartsResultVO.getDrilldownNode();
				List<List> data = countyDrilldownNode.getData();
				for (List list : data) {
					HighchartsResultVO resultHighchartsVO = new HighchartsResultVO();
					for (int i=0; list.size()>i;i++) {
						resultHighchartsVO.setName((String)list.get(0));
						resultHighchartsVO.setY((Double)list.get(1));
						break;
					}
					countHighchartsResultVOList.add(resultHighchartsVO);
				}
			}
			return countHighchartsResultVOList;
		}
		return highchartsResultVO;
	}


	@Override
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLB(HttpServletRequest request,Integer page,Integer rows,String year) {
		//���ҳ�洫���yearΪ���� �õ�ǰʱ����и�ֵ
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
		stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
		
		List<CountyTaskWorkingDTO> countyTaskWorkingDTOList = xbMapper.queryTaskWorkingByGroupCountyAndGCLBAndZLLB(markList,year,stats);
		
		return this.pageQueryUtils(countyTaskWorkingDTOList, page, rows);
	}
	
	

	@Override
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(HttpServletRequest request, Integer page,
			Integer rows, String areaCode,String year) {
		//�����ǰ����Null,��Ĭ���ǵ�ǰʱ�����
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		List<Integer> stats = TaskStatuUtils.getTaskStatUnreviewed();
		List<CountyTaskWorkingDTO> countyTaskWorkingDTOList = xbMapper.queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(markList,areaCode,year,stats);
		return this.pageQueryUtils(countyTaskWorkingDTOList, page, rows);
	}
	
	
	//�����ݽ��з�ҳ����
	private EasyUIResult pageQueryUtils(List<CountyTaskWorkingDTO> countyTaskWorkingDTOList,Integer page,Integer rows){
		List<HashMap> resultList = new ArrayList<>();
		//�����жϵ�ǰ�����Ƿ��Ѿ���ӹ�
		List<Integer> idList = new ArrayList<>();
		try {
			for (int i = 0; i < countyTaskWorkingDTOList.size(); i++) {
				CountyTaskWorkingDTO IcountyTaskWorkingDTO = countyTaskWorkingDTOList.get(i);
				Integer IcountyNumber = IcountyTaskWorkingDTO.getCountyNumber();
				//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
				if(idList.contains(IcountyTaskWorkingDTO.getId())) continue;
				//��װ����
				HashMap<Object, Object> resultMap = new HashMap<>();
				resultMap.put("city", IcountyTaskWorkingDTO.getCity());
				resultMap.put("county", IcountyTaskWorkingDTO.getCounty());
				resultMap.put("JHND", IcountyTaskWorkingDTO.getJHND());
				resultMap.put("ZYND", IcountyTaskWorkingDTO.getZYND());
				//�����ͳ����������ϡ��ù��̱��+�����������ƴ��ΪKey
				resultMap.put(IcountyTaskWorkingDTO.getGCLB()+"T"+IcountyTaskWorkingDTO.getZLLB(), IcountyTaskWorkingDTO.getXTJSBMJ().setScale(5, BigDecimal.ROUND_HALF_UP));//����ȡ����ȡС��λ��5λ
				for (int j = i+1; j < countyTaskWorkingDTOList.size(); j++) {
					//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
					CountyTaskWorkingDTO JcountyTaskWorkingDTO = countyTaskWorkingDTOList.get(j);
					//�жϵ�ǰ�����Ƿ�����ӹ��������ӹ���ֱ����������ѭ�� ���ڽ��б����ж�
					if(idList.contains(JcountyTaskWorkingDTO.getId())) continue;
					//��ȡ���������
					Integer JcountyNumber = JcountyTaskWorkingDTO.getCountyNumber();
					//�ж��ǲ���ͬһ���ؼ�
					if(IcountyNumber.equals(JcountyNumber)){
						//�ж���ҵ��Ⱥͼƻ�����Ƿ�һ��
						if(IcountyTaskWorkingDTO.getZYND().equals(JcountyTaskWorkingDTO.getZYND())
								&&IcountyTaskWorkingDTO.getJHND().equals(JcountyTaskWorkingDTO.getJHND())){
							//�����ͳ����������ϡ��ù��������+�����������ƴ��ΪKey                      
							resultMap.put(JcountyTaskWorkingDTO.getGCLB()+"T"+JcountyTaskWorkingDTO.getZLLB(), JcountyTaskWorkingDTO.getXTJSBMJ().setScale(5, BigDecimal.ROUND_HALF_UP));//����ȡ����ȡС��λ��5λ
							
							idList.add(JcountyTaskWorkingDTO.getId());
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
		List<HashMap> result = new ArrayList<HashMap>();
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


	
}
