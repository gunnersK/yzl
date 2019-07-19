package com.yzl.mapper;

import com.alibaba.dubbo.config.support.Parameter;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlTaskMapper {
    int countByExample(YzlTaskExample example);

    int deleteByExample(YzlTaskExample example);

    int deleteByPrimaryKey(Integer tcode);

    int insert(YzlTask record);

    int insertSelective(YzlTask record);

    List<YzlTask> selectByExample(YzlTaskExample example);

    YzlTask selectByPrimaryKey(Integer tcode);

    int updateByExampleSelective(@Param("record") YzlTask record, @Param("example") YzlTaskExample example);

    int updateByExample(@Param("record") YzlTask record, @Param("example") YzlTaskExample example);

    int updateByPrimaryKeySelective(YzlTask record);

    int updateByPrimaryKey(YzlTask record);
    
  //���ݹ���id��ѯ��������
    List<YzlTask> queryByXbGCLB(@Param("ecode") int ecode,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("disCode")String disCode,@Param("stats")List<Integer> stats);
//  �־�������ѯ+��ҳ
    List<YzlTask> selectByCondition(String value);
    //ͨ�������·����ݵ�GCLB��ѯ����
    List<YzlTask> queryByTaskIssuedGCLB(@Param("ecode") Integer ecode,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode);
    //�������Ʋ�ѯ
	YzlTask selectByTname(String tname);

	//���ݱ�Ų�ѯ
	YzlTask selectByMark(String zllb);

	List<YzlTask> selectshow();

	List<YzlTask> findByEpcEcode(int ecode);
	//���ݹ��̱�� �� ���ͳ��С�������ҵ�������������
	Integer countyXbTaskNumberByGCLLAndYear(@Param("GCLB")String GCLB,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("disCode")String disCode,@Param("stats")List<Integer> stats);
	//���ݹ��̱�� �� ���ͳ�������·��������������
	Integer countyIssueTaskNumberByGCLLAndYear(@Param("areaCode")String areaCode,@Param("GCLB")String GCLB,@Param("year")String year,@Param("authoritys")List<String> authoritys);
	//��ѯͨ������ecode
	List<YzlTask> findByEpcEcode(Integer ecode);

	List<YzlTask> select();
	//����������𼯺ϲ�ѯEpctaskprogress����������������
	List<YzlTask> queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("GCLB")String GCLB,@Param("ZLLB")String ZLLB);
	
	//����������𼯺ϲ�ѯxb����������������
	List<YzlTask> queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("stats")List<String> stats,@Param("zllb")String zllb);
	//����������𼯺ϲ�ѯEpctaskprogress����������������
	List<YzlTask> queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	
}