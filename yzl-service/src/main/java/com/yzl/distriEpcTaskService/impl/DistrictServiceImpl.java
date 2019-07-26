package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.distriEpcTaskService.DistrictService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlDistrictExample.Criteria;
import com.yzl.pojo.YzlDistrictVo;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.DisEnums;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DistrictTreeVO;

import net.sourceforge.pinyin4j.PinyinHelper;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	
	/***
	 * �������service
	 */
	@Override
	public void save(YzlDistrict district) {
		String city = district.getCity();
		String county = district.getCounty();
		
		//�еļ���
		//String[] citys = PinYin4jUtils.getHeadByString(city);
		//����
		//String citys = city.substring(0, city.length()-1);
		String sub = city+county;
		//String[] headByString = PinYin4jUtils.getHeadByString(sub);
		//String join = StringUtils.join(headByString);
		
		//district.setShortcode(join);
		
		//����
		//String pinyin = PinYin4jUtils.hanziToPinyin(county,"");
		//district.setCitycode(pinyin);
		
		//String joc = StringUtils.join(citys);
		//��ȡ�����жԵ��Ƕ�Ӧ�ı��
		DisEnums[] values = DisEnums.values();
/*		for (DisEnums disEnums : values) {
			if (disEnums.toString().equals(joc)) {
				district.setFlag(disEnums.getMark());
			}
		}*/
		
		if(StringUtils.isBlank(city)){
			throw new YzlException(ResultEnum.CITY_IS_NULL);
		}
		if(StringUtils.isBlank(county)){
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		//ȡ�м�+�ؼ�ÿ���ֵ�����ĸ,����'-'���ֿ�
		//String[] headPYs = PinYin4jUtils.getHeadByString(city+"-"+county);
		//������ĸƴ������
		//String headPy = StringUtils.join(headPYs,"");
	//	district.setMark(headPy);
		districtMapper.insert(district);
	}

	
	/***
	 * ������ҳ��ѯservice
	 */
	@Override
	public EasyUIResult pageQuery(int page,int rows) {
		//���÷�ҳ��ѯ����
		PageHelper.startPage(page, rows);
		YzlDistrictExample example = new YzlDistrictExample();
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		PageInfo<YzlDistrict> pageInfo = new PageInfo<>(list);
		
		//��װ��ҳ����
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(pageInfo.getList());
		easyUIResult.setTotal((int)pageInfo.getTotal());
		return easyUIResult;
	}

	/***
	 * ����id ɾ��service
	 */
	@Override
	public void delete(String ids) {
		if(ids!=null && ids != ""){
			String[] dIds = ids.split(",");
			for (String id : dIds) {
				districtMapper.deleteByPrimaryKey(Integer.valueOf(id));
			}
		}
	}

/*����*/
	@Override
	public YzlResult searchByCityOrCounty(String searchKey) {
		List<YzlDistrict> list = districtMapper.findByCityOrCounty(searchKey);
		if(list!=null&&list.size()>0){
			return YzlResult.ok(list);
		}
		return YzlResult.build(400, "δ�������κν����");
	}

	
	/**
	 * ��ѯ���е���
	 */
	@Override
	public YzlResult queryDistinctCity() {
		List<Map> citys = districtMapper.queryDistinctCity();
		if(citys!=null && citys.size()>0){
			return YzlResult.ok(citys);
		}
		return YzlResult.build(400, "δ��ѯ���κε�����");
	}


	
	/***
	 * ����������ѯ ��/�� 
	 */
	@Override
	public List<YzlDistrict> queryCountyByCityName(String cityName) {
		YzlDistrictExample example = new YzlDistrictExample();
		Criteria criteria = example.createCriteria();
		criteria.andCityEqualTo(cityName);
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}


	@Override
	public List<YzlDistrict> show(String q) {
		List<YzlDistrict> list = new ArrayList<YzlDistrict>();
		if (StringUtils.isNotBlank(q)) {
			list = districtMapper.selectValue(q);
		}else {
			YzlDistrictExample example=new YzlDistrictExample();
			list = districtMapper.selectByExample(example);
		}
		if (list.size()>0) {
			for (YzlDistrict yzlDistrict : list) {
				yzlDistrict.setName(yzlDistrict.getCity()+"��"+yzlDistrict.getCounty());
			}
		}
		return list;
	}


	@Override
	public YzlDistrict ver(String county) {
		
		YzlDistrictExample example=new YzlDistrictExample();
		example.createCriteria().andCountyEqualTo(county);
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		
		YzlDistrict district = null;
		if (list!=null && list.size()>0) {
			district = list.get(0);
		}
		return district;
	}


	//��ѯ�������νڵ�չʾ
	@Override
	public List<DistrictTreeVO> queryTreeNode(HttpServletRequest request) {
		List<DistrictTreeVO> districtTreeVOList = new ArrayList<>();
		/*������߼����ڵ�*/
		DistrictTreeVO districtTreeVO = new DistrictTreeVO();
		districtTreeVO.setText(DistrictEnum.REGION_ADMIN.getName());
		//Ĭ�ϸ��ڵ��Ǵ�״̬
		districtTreeVO.setState("open");
		/*�������*/
		districtTreeVO.setId(DistrictEnum.REGION_ADMIN.getCode());
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> queryPermsByUserId = menuMapper.queryPermsByUserId(Integer.parseInt(loginUser.getId().toString()));
		//�����û�Ȩ�޲�ѯ���е���
		List<DistrictTreeVO> districtTreeVOChildren = districtMapper.queryRecursionDistrict(queryPermsByUserId);
		//���Ҷ�ӽڵ�
		for (int i = 0; i < districtTreeVOChildren.size(); i++) {
			List<DistrictTreeVO> nodeChildren = districtMapper.findDistrictByCityCode(queryPermsByUserId, districtTreeVOChildren.get(i).getId());
			districtTreeVOChildren.get(i).setChildren(nodeChildren);
			if (districtTreeVOChildren.size()==1) {
				districtTreeVOChildren.get(i).setState("open");
			}
		}
		//����ӽڵ�
		districtTreeVO.setChildren(districtTreeVOChildren);
		districtTreeVOList.add(districtTreeVO);
		return districtTreeVOList;
	}


	@Override
	public EasyUIResult  selectByConditions(YzlDistrictVo yzlDistrictVo,String city,String county,int page, int rows) {
		
		PageHelper.startPage(page, rows);
		YzlDistrict yzlDistrict = new YzlDistrict();
		yzlDistrict.setCity(city);
		yzlDistrict.setCounty(county);		
		yzlDistrictVo.setYzlDistrict(yzlDistrict);		
		List<YzlDistrict> list = districtMapper.selectByCityOrCounty(yzlDistrictVo);
		PageInfo<YzlDistrict> pageInfo = new PageInfo<>(list);
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(pageInfo.getList());
		easyUIResult.setTotal((int)pageInfo.getTotal());
		return easyUIResult;
	}
}
