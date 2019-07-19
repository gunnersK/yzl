package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcExample;
import com.yzl.pojo.YzlMessage;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlEpcMapper {
    int countByExample(YzlEpcExample example);

    int deleteByExample(YzlEpcExample example);

    int deleteByPrimaryKey(Integer ecode);

    int insert(YzlEpc record);

    int insertSelective(YzlEpc record);

    List<YzlEpc> selectByExample(YzlEpcExample example);

    YzlEpc selectByPrimaryKey(Integer ecode);

    int updateByExampleSelective(@Param("record") YzlEpc record, @Param("example") YzlEpcExample example);

    int updateByExample(@Param("record") YzlEpc record, @Param("example") YzlEpcExample example);

    int updateByPrimaryKeySelective(YzlEpc record);

    int updateByPrimaryKey(YzlEpc record);
    
  //ģ����ѯ
    List<YzlEpc> selectByCondition(String value);

	YzlEpc selectByEname(String ename);
	
	String queryMaxMark();

	//���ݱ�Ų�ѯ
	YzlEpc selectByMark(String gclb);

	List<YzlEpc> gclb();
	//���������·��������� ��ѯ����
	List<YzlEpc> queryEpcByTaskProgressData(@Param("year")String year, @Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys);
	//����С��Ϊ���ͨ���������������  ����year ������Ȩ��
	List<YzlEpc> queryEpcByXbTaskWoring(@Param("year")String year,@Param("disCode")String disCode,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	//����������𼯺ϲ�ѯepctaskprogress�·������������Ĺ���
	List<YzlEpc> queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB);
	
	//����������𼯺ϲ�ѯxb�����������Ĺ���
	List<YzlEpc> queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("stats")List<String> stats);

	List<YzlEpc> queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
}