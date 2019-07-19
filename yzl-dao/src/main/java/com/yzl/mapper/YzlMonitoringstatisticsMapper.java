package com.yzl.mapper;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlMonitoringstatisticsExample;
import com.yzl.pojo.YzlXb;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlMonitoringstatisticsMapper {
    int countByExample(YzlMonitoringstatisticsExample example);

    int deleteByExample(YzlMonitoringstatisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlMonitoringstatistics record);

    int insertSelective(YzlMonitoringstatistics record);

    List<YzlMonitoringstatistics> selectByExample(YzlMonitoringstatisticsExample example);

    YzlMonitoringstatistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlMonitoringstatistics record, @Param("example") YzlMonitoringstatisticsExample example);

    int updateByExample(@Param("record") YzlMonitoringstatistics record, @Param("example") YzlMonitoringstatisticsExample example);

    int updateByPrimaryKeySelective(YzlMonitoringstatistics record);

    int updateByPrimaryKey(YzlMonitoringstatistics record);

	List<YzlMonitoringstatistics> select();
	
////�����еı�Ų�ѯС����е�ӵ�е��صı��
	List<String> selectByCityNumber(String string);
	
	List<YzlXb> selectCity(String city);
	
	List<YzlXb> text();

	//��ȡ����û���Ӧ��Ȩ��
	List<YzlMenu> selectByMeun(Long id);

	List<YzlMenu> selectByMenuId(@Param("id")Long id,@Param("menuId")Long menuId);

	List<YzlDistrict> AllCity();

	List<YzlEpc> AllGclb();

	List<YzlDistrict> AllCounty(@Param("cityCode")String cityCode);
}