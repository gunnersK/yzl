package com.yzl.distriEpcTaskService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictVo;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.DistrictTreeVO;

public interface DistrictService {
	
	
	void save(YzlDistrict district);

	EasyUIResult pageQuery(int page,int rows);

	void delete(String ids);

	YzlResult searchByCityOrCounty(String searchKey);

	YzlResult queryDistinctCity();

	List<YzlDistrict> queryCountyByCityName(String cityName);

	List<YzlDistrict> show(String q);
	
	//条件查询
	//List<YzlDistrict> selectByConditions(YzlDistrictVo yzlDistrictVo);

	YzlDistrict ver(String county);
	
	List<DistrictTreeVO> queryTreeNode(HttpServletRequest request);
	//条件查询
	EasyUIResult selectByConditions(YzlDistrictVo yzlDistrictVo, String city, String county, int page, int rows);
	
	//根据地区行政编号查地区id(主键)
	Integer queryDcodeByAnumber(String anumber);

//	List<YzlDistrict> selectByConditions(YzlDistrict yzlDistrict, int page, int rows);
}
