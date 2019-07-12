package com.yzl.mapper;

import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserRole;
import com.yzl.pojo.YzlUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlUserRoleMapper {
    int countByExample(YzlUserRoleExample example);

    int deleteByExample(YzlUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlUserRole record);

    int insertSelective(YzlUserRole record);

    List<YzlUserRole> selectByExample(YzlUserRoleExample example);

    YzlUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlUserRole record, @Param("example") YzlUserRoleExample example);

    int updateByExample(@Param("record") YzlUserRole record, @Param("example") YzlUserRoleExample example);

    int updateByPrimaryKeySelective(YzlUserRole record);

    int updateByPrimaryKey(YzlUserRole record);
    // �����û�Id��ѯ���еĽ�ɫId -->
	List<Integer> queryRoleIdsByUserId(Integer userId);
	
    // �����û�Id��ѯ���еĽ�ɫId ���ؽ��Ϊ�ַ���-->
	List<String> queryRoleIdsByUserIdResultStr(Integer userId);

	YzlUser selectByUsername(String username);
}