package com.yzl.mapper;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlDistrictVo;
import com.yzl.utils.vo.DistrictTreeVO;
import com.yzl.utils.vo.TreeVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface YzlDistrictMapper {
    int countByExample(YzlDistrictExample example);

    int deleteByExample(YzlDistrictExample example);

    int deleteByPrimaryKey(Integer dcode);

    int insert(YzlDistrict record);

    int insertSelective(YzlDistrict record);

    List<YzlDistrict> selectByExample(YzlDistrictExample example);

    YzlDistrict selectByPrimaryKey(Integer dcode);

    int updateByExampleSelective(@Param("record") YzlDistrict record, @Param("example") YzlDistrictExample example);

    int updateByExample(@Param("record") YzlDistrict record, @Param("example") YzlDistrictExample example);

    int updateByPrimaryKeySelective(YzlDistrict record);

    int updateByPrimaryKey(YzlDistrict record);
    
    //������ѯ
    List<YzlDistrict> selectByCityOrCounty(YzlDistrictVo yzlDistrictVo);
    
  //���ݵ���id��ѯ������ʶ
    String findMarkByDcode(@RequestParam("dcode")Integer dcode);
    
    //���ݵ���������Ų����id(����)
    Integer findDCodeByAnumber(String anumber);

    List<YzlDistrict> findByCityOrCounty(@Param("searchKey") String searchKey);
    //��ѯ�����м� ��ȥ�ظ�
    List<String> findDistinctCity();
    //
    List<Map> queryDistinctCity();
	List<YzlDistrict> selectValue(@Param("q")String q);
	
	YzlDistrict selectByFlag(@Param("flag")String flag);
	
	//���������ʲ�ѯ�õ�������־
	YzlDistrict selectByDname(String dname);
	
	String findMarkByCounty(String county);

	List<YzlDistrict> selectFindAllFlag();

	//���������Ʋ�ѯ������
	List<YzlDistrict> selectByCity(@Param("city")String city,@Param("permsList")List<String> permsList);

	//���������Ʋ�ѯ
	YzlDistrict selectByCounty(String county);
	
	//����������ѯ���������
	String selectCitycodeByCity(String cityName);
	
	//��ѯ���е���ȥ��
	List<YzlDistrict> selectByDisinctCity(@Param("permsList")List<String> permsList);
	
	//���ݱ�־��ѯ��ӵ�е���
	List<YzlDistrict> selectByFlags (@Param("flag")String flag,@Param("permsList")List<String> permsList);

	//���ݱ�Ų�ѯ
	YzlDistrict selectByNumber(String county);
	//�����û�Ȩ�� �ݹ��ѯ�����ڵ�
	List<DistrictTreeVO> queryRecursionDistrict(@Param("aNumbers")List<String> aNumbers);

	List<DistrictTreeVO>  findDistrictByCityCode(@Param("aNumbers")List<String> aNumbers,@Param("cityCode")String cityCode);

}