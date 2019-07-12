package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskEpc;
import com.yzl.pojo.YzlTaskEpcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlTaskEpcMapper {
    int countByExample(YzlTaskEpcExample example);

    int deleteByExample(YzlTaskEpcExample example);

    int insert(YzlTaskEpc record);

    int insertSelective(YzlTaskEpc record);

    List<YzlTaskEpc> selectByExample(YzlTaskEpcExample example);

    int updateByExampleSelective(@Param("record") YzlTaskEpc record, @Param("example") YzlTaskEpcExample example);

    int updateByExample(@Param("record") YzlTaskEpc record, @Param("example") YzlTaskEpcExample example);

    //���м�¼��ȥ��
	List<Integer> selectByDistinctEcode();

	//��ҳ��ȡ���й���idȥ��
	List<Integer> selectByDistinctEcodePage(int page2, Integer rows);

	//ģ����ѯ
	List<YzlEpc> selectVague(String value);

	//ģ���ӷ�ҳ
	List<YzlEpc> selectVaguePage(String value, int page2, Integer rows);

	//���ݹ���id��ѯ��ӵ�е�����
	List<YzlTask> selectByEcode(Integer ecode);

	//���ݹ���idɾ��
	int deleteByEcode(Integer ecode);

	//���ݹ�����������̱��
	Integer selectByEpcEname(String edname);


}