package com.yzl.mapper;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskProgressSheetExample;
import com.yzl.utils.vo.DrilldownNode;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YzlTaskProgressSheetMapper {
    int countByExample(YzlTaskProgressSheetExample example);

    int deleteByExample(YzlTaskProgressSheetExample example);

    int deleteByPrimaryKey(Long id);
    
    int insert(YzlTaskProgressSheet record);

    int insertSelective(YzlTaskProgressSheet record);

    List<YzlTaskProgressSheet> selectByExample(YzlTaskProgressSheetExample example);

    YzlTaskProgressSheet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlTaskProgressSheet record, @Param("example") YzlTaskProgressSheetExample example);

    int updateByExample(@Param("record") YzlTaskProgressSheet record, @Param("example") YzlTaskProgressSheetExample example);

    int updateByPrimaryKeySelective(YzlTaskProgressSheet record);

    int updateByPrimaryKey(YzlTaskProgressSheet record);
    
    //���ݹ��̱�Ų�ѯ����������Ⱥ͵���   �������idΪ�� ���ѯ����
    List<YzlTaskProgressSheet> queryTaskDistrictByEcode(@Param("ecode")Integer ecode);
    
    //�����������
    void updateTaskProgressSheet(YzlTaskProgressSheet taskProgressSheet);
    //ɾ���������
    void deleteByEcodeAndDpcodeAndCreateTime(YzlTaskProgressSheet taskProgressSheet);
    //ͳ��
    Integer countCreateTimeAlsoDistinct(@Param("epcode")Integer epcode);
    //��ѯ��������ɵ�����
    List<YzlTaskProgressSheet> queryByEcodeAndMark(@Param("list")List<String> marks,@Param("ecode")Integer ecode,@Param("area")String area,@Param("year")String year);
    //��ѯ����δ��ɵ�����
    List<YzlTaskProgressSheet> queryByUserpermsAndEcode(@Param("list")List<String> marks,@Param("ecode")Integer ecode);
    //����mark��ѯ�������
    List<YzlTaskProgressSheet> queryByMark(@Param("list")List<String> marks);
    //���ݹ���id��ѯ��ƥ���mark
    List<YzlTaskProgressSheet>  queryByEcodeAndContraryMark(YzlTaskProgressSheet taskProgressSheet);
    //���ݹؼ�������
    List<YzlTaskProgressSheet> searchKey(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //�ؼ��ֺ�mark����
    List<YzlTaskProgressSheet> searchKeyAndMark(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //�ؼ��ֺ͹���ID��mark����
    List<YzlTaskProgressSheet> searchKeyAndEcode(@Param("ecode")Integer ecode,@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //���ݹ���id��mark�͹ؼ��������������
    List<YzlTaskProgressSheet> searchKeyAndEcodeMark(@Param("ecode")Integer ecode,@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    //���ݹؼ��ֺͲ�ƥ���mark����
    List<YzlTaskProgressSheet> searchKeyAndContraryMark(@Param("mark")String mark,@Param("searchKey")String searchKey);
    
    //��ѯҳ��ͼ������
    List<Map> queryTaskGroupByCity();
    //��ѯ������в�ȥ�ظ�
    List<Map> queryTaskDistinctCity();
    //��ѯ�������County���з���
    List<Map> queryTaskGroupByCounty(String city);
    //�����û�Ȩ�޲�ѯ���� ���������Ϊ���������Ӧ�Ĺ���,area����name��year����ʱ������
    List<YzlTaskProgressSheet> queryByUserPower(@Param("list")List marks,@Param("area")String area,@Param("year")String year);
    //��ѯ����������Ȳ�������id������id���з��� 
    List<YzlTaskProgressSheet> queryTaskProgressGroupByDpcodeAndTpcode(@Param("list")List marks);
    //�����û�Ȩ�� �� �ؼ��ֽ�����������������Ȳ�������id������id���з��� 
    List<YzlTaskProgressSheet> searchTaskProgressGroupByDpcodeAndTpcode(@Param("list")List marks,@Param("searchKey")String searchKey);
   //ͨ������id��ѯ�������
    List<YzlTaskProgressSheet> queryTaskprogressByDcode(@Param("dcode")Integer dcode);
    //���ݹ���Id ������id�͵���Id����ͳ��
    YzlTaskProgressSheet countByEcodeAndTpcodeAndDpcode(@Param("ecode")Integer ecode,@Param("Tpcode")Integer Tpcode,@Param("Dpcode")Integer Dpcode);

    void  update11(YzlTaskProgressSheet taskProgressSheet);

}