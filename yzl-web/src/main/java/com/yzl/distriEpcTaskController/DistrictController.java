package com.yzl.distriEpcTaskController;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yzl.distriEpcTaskService.DistrictService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictVo;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.DistrictTreeVO;


/***
 * ��������
 * @author administrator_
 *
 */
@Controller
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;
	
	/***
	 * ��ӵ���
	 * @param district
	 * @return
	 */
	@RequestMapping("district/addDistrict")
	public String addRegion(YzlDistrict district){
		try {
			districtService.save(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/����";
	}
	
	/***
	 * ��ҳ��ѯ
	 * @return
	 */
	@RequestMapping("district/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int page,int rows){
		EasyUIResult result = districtService.pageQuery(page,rows);
		return result;
	}
	
	/***
	 * ����Idɾ������
	 * @return
	 */
	@RequestMapping("/district/delete")
	@ResponseBody
	public YzlResult delete(String ids){
		districtService.delete(ids);
		return YzlResult.ok();
	}
	
	/*
	 * ��������������������ѯ
	 */
	@RequestMapping("/district/selectByCityorCounty")
	@ResponseBody
	public EasyUIResult selectByCityorCounty(YzlDistrictVo yzlDistrictVo,String city,String county,@RequestParam(value="page",defaultValue="1")int page,@RequestParam(value="rows",defaultValue="10")int rows){
		
		EasyUIResult result = districtService.selectByConditions(yzlDistrictVo,city,county,page,rows);
		return result;	
	}
	/***
	 * ����
	 */
	@RequestMapping("/district/search")
	@ResponseBody
	public YzlResult search(String searchKey){
		YzlResult result = districtService.searchByCityOrCounty(searchKey);
		return result;
	}
	
	/***
	 * ��ѯ����
	 * @param districtFile
	 * @return
	 */
	@RequestMapping("/district/queryDistinctCity")
	@ResponseBody
	public YzlResult queryCity(){
		YzlResult result = districtService.queryDistinctCity();
		return result;
	}
	
	/***
	 * ����������ѯ���е���/��
	 * @return
	 */
	@RequestMapping("/district/queryCountyByCityName")
	@ResponseBody
	public List<YzlDistrict> queryCountyByCityName(@RequestParam("cityName")String cityName){
		List<YzlDistrict> districts = districtService.queryCountyByCityName(cityName);
		return districts;
	}
	
	
	/*****
	 * xml�����������
	 * @param districtFile
	 * @return
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value="district/importXls",method=RequestMethod.POST)
	public String importXls(@RequestParam ("districtFile") CommonsMultipartFile districtFile ){
		int flag=1;
		try {
			//��ȡ�ϴ��ļ���
			InputStream stream = districtFile.getInputStream();
			//ʹ��POI����Excel�ļ�
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(stream);
			//ȥexcel�ĵ�һ��sheet
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			//����������
			for (Row row : sheet) {
				//��ȡ������
				int rowNum = row.getRowNum();
				//��һ���Ǳ�ͷ��������
				if(rowNum==0){
					continue;
				}
				
				//ȡ��ǰ�еĵ�һ����Ԫ������ ���Դ�����
				String city = row.getCell(0).getStringCellValue();
				String county = row.getCell(1).getStringCellValue();
				//��װ����
				YzlDistrict district = new YzlDistrict();
				district.setCity(city);
				district.setCounty(county);
				//��ӵ����ݿ�
				districtService.save(district);
			}
		} catch (IOException e) {
			e.printStackTrace();
			flag=0;
		}
		return "����";
	}
	
	//չʾ���е���
	@RequestMapping("/show_diss")
	@ResponseBody
	public List<YzlDistrict> show(String q){
		return districtService.show(q);
	}
	
	//����У��
	@RequestMapping("/verifyCounty")
	@ResponseBody
	public YzlDistrict ver(String county){
		return districtService.ver(county);
	}
	
	//��ѯ���е��� ����չʾ
	@RequestMapping("/district/queryTreeNode")
	@ResponseBody
	public List<DistrictTreeVO> queryTreeNode(HttpServletRequest request){
		return districtService.queryTreeNode(request);
	}
}
