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
	
	//Ìõ¼þ²éÑ¯
	List<YzlDistrict> selectByConditions(YzlDistrictVo yzlDistrictVo);

	YzlDistrict ver(String county);
	
	List<DistrictTreeVO> queryTreeNode(HttpServletRequest request);

//	List<YzlDistrict> selectByConditions(YzlDistrict yzlDistrict, int page, int rows);
}
