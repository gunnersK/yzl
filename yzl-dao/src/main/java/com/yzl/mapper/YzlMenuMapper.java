package com.yzl.mapper;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMenuExample;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.vo.TreeVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlMenuMapper {
    int countByExample(YzlMenuExample example);

    int deleteByExample(YzlMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(YzlMenu record);

    int insertSelective(YzlMenu record);

    List<YzlMenu> selectByExample(YzlMenuExample example);

    YzlMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") YzlMenu record, @Param("example") YzlMenuExample example);

    int updateByExample(@Param("record") YzlMenu record, @Param("example") YzlMenuExample example);

    int updateByPrimaryKeySelective(YzlMenu record);

    int updateByPrimaryKey(YzlMenu record);
    //�ݹ��ѯ����Ȩ��
    List<TreeVO> findAllRecursion();
    //���ݽ�ɫ��ѯȨ��
    List<TreeVO> findFunctionByRoleId(@Param("roleId") Long roleId);

	List<YzlMenu> findMenuByRoleId(@Param("valueOf")Long valueOf);

	List<YzlMenu> selectByUserId(Integer id);

	List<YzlMenu> findAll();

	List<YzlMenu> findByPid(Long pid);

	//�����û�id��ѯȨ�ޱ�ʶ
	List<String> queryPermsByUserId(@Param("userId")Integer userId);
	
	//��ҳ��ѯ
	List<YzlEpcTaskProgress> selectByUid(@Param("userId")Integer userId,@Param("page")Integer page,@Param("rows")Integer rows,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//ͳ��
	List<YzlEpcTaskProgress> selectByUidStiti(@Param("userId")Integer id,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//��ѯ����
	List<YzlEpcTaskProgress> selectFindAllETP(@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);

	//��ҳ��ѯ����
	List<YzlEpcTaskProgress> selectFindAllETPAndPage(@Param("page")Integer page,@Param("rows") Integer rows,@Param("dpcode")String did,@Param("epcode")String epcode,@Param("tpcode")String tpcode,@Param("time")String time);
	
	//��ѯ������̱��ӵ�е�����
	List<YzlTask> selectByEcode(Integer epcode);

	Integer selectbyPerms(String perms);

	//���ڵ�
	List<YzlMenu> selectByUserIdHost(Integer id);

	//�ӽڵ�
	List<YzlMenu> selectByUserIdSon(Integer id);

	List<String> selectByUserIdPerms(String id);
}