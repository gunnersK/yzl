package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlEpcTaskProgressExample;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.utils.dto.TaskIssuedDTO;
import com.yzl.utils.vo.BacklogVO;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface YzlEpcTaskProgressMapper {
    int countByExample(YzlEpcTaskProgressExample example);

    int deleteByExample(YzlEpcTaskProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlEpcTaskProgress record);

    int insertSelective(YzlEpcTaskProgress record);

    List<YzlEpcTaskProgress> selectByExample(YzlEpcTaskProgressExample example);

    YzlEpcTaskProgress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlEpcTaskProgress record, @Param("example") YzlEpcTaskProgressExample example);

    int updateByExample(@Param("record") YzlEpcTaskProgress record, @Param("example") YzlEpcTaskProgressExample example);

    int updateByPrimaryKeySelective(YzlEpcTaskProgress record);

    int updateByPrimaryKey(YzlEpcTaskProgress record);
    
    //���ݹ��̱��ɾ��
    int deleteByEcode(Integer ecodes);
    //��ѯ���еĹ���ȥ���ظ���+��ҳ
    List<Integer> selectByDistinctEcodePage(Integer start,Integer end);
  //��ѯ���еĹ���ȥ���ظ���+��ҳ
    List<Integer> selectByDistinctEcode();
    //���ݹ���id��ѯ�����������
    List<YzlEpcTaskProgress> selectDistinctByEpcode(int epcode);
    //���ݹ������Ʋ鹤�̱��
    Integer selectByEpcEname(String ename);
  //ģ����ѯ//����������ַ��õ����̶���+��ҳ
    List<YzlEpc> selectVaguePage(String ename,Integer start,Integer end);
    //ģ����ѯ//����������ַ��õ����̶���
    List<YzlEpc> selectVague(String ename);
    
    //List<Integer> selectListEcode(List<Map<Integer, Integer>> lm);
    //List<Map<Integer, Integer>> selectListET(String value);
    //����һ�����̱�ŷ��ع���ӵ�е������������
    List<YzlTask> selectByEcode(Integer ecode);
    List<YzlTask> selectByEcodes(@Param("ecode")String ecode,@Param("year")String year);
    //���ݹ��̱�Ų�ѯ����������Ⱥ͵���
    List<TaskIssuedDTO> queryByYearAndGCLB(@Param("list")List<String> list,@Param("year")String year,@Param("GCLB")String GCLB);
    
    //ͳ��ͨ�� ���ݲ����ݵ���idȥ�ظ�
    long countTaskNumber();
    //ͳ�ƴ���ʱ����� ��ѯ
    List<YzlEpcTaskProgress> selectByCreateTime(@Param("list")List<String> marks,@Param("year")String year);
    //�޸ķ���������
    void updateTaskProgress(YzlEpcTaskProgress epcTaskProgress);
    
    void deleteByEcodeAndCreateTime(YzlEpcTaskProgress epcTaskProgress);
    
    List<YzlEpcTaskProgress> searchKey(@Param("list")List<String> marks,@Param("searchKey")String searchKey);
    
    List<YzlEpcTaskProgress> searchKeyAndEcode(@Param("list")List<String> marks,@Param("ecode")int ecode,@Param("searchKey")String searchKey);
    
    List<TaskDTO> queryTaskGroupByCity(@Param("aNumbers")List<String> aNumbers,@Param("year")String year);

    List<YzlEpcTaskProgress> queryTaskDistrictByEcodeAndAreaAndYear(@Param("list")List<String> permsList,@Param("ecode") Integer ecode,@Param("area") String area,@Param("year") String year);

    //�����ز�ѯӵ�еĹ���
	List<YzlEpc> selectByCounty(String county);

	//�����غ͹��̲�ѯ����ӵ�е��ֶ�
	List<YzlTask> selectByEcodeAndDname(@Param("ecode")String ecode,@Param("county")String county);

	//��ѯ�������ͨ��mark����county����
	List<TaskDTO> selectTaskNumberGroupByCountyAndByMark(@Param("aNumbers")List<String> anumbers,@Param("cityCode")String cityCode,@Param("year")String year);
	
	//��ѯ����
	List<YzlEpcTaskProgress> selectAll(@Param("ecode")String ecode,@Param("county")String county,@Param("tname")String tname);

	//��ҳ��ѯ
	List<YzlEpcTaskProgress> selectAllPage(@Param("ecode")String ecode,@Param("county")String county,@Param("tname")String tname,@Param("rows")Integer rows,@Param("page")int page2);

	//�����û�id��ѯ����û�ӵ�е����м�¼
	List<YzlEpcTaskProgress> selectByUidAndL(@Param("uid")Integer uid,@Param("epcode")String ecode,@Param("dpcode")String dcode,@Param("tpcode")String tcode);

	List<YzlEpcTaskProgress> selectByUidAndLPage(@Param("uid")Integer uid,@Param("epcode")String ecode,@Param("dpcode")String dcode,@Param("tpcode")String tcode,@Param("rows")Integer rows,@Param("page")Integer page);

	//�����û���ȡ��Ȩ��
	List<YzlMenu> findByUid(Integer uid);

	//
	List<YzlMonitoringstatistics> selectAlls(@Param("ecode")String ecode,@Param("dcode")String dcode,@Param("tcode")String tcode,@Param("time")String time);

	List<YzlMonitoringstatistics> selectAllPages(@Param("ecode")String ecode,@Param("dcode")String dcode,@Param("tcode")String tcode,@Param("rows")Integer rows,@Param("page") int page,@Param("time")String time);
	
	//ͨ����ҵ��Ⱥ͵���������Ų�ѯ�����·����ݸ����ء���������ؼ���Ž��з���
	List<TaskIssuedDTO> queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(@Param("aNumbers")List<String> aNumbers ,@Param("areaCode")String areaCode,@Param("year")String year,@Param("GCLB")String GCLB);
	//����    ͨ����ҵ��ȣ��ƻ���ȣ������������������������
	int updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	//ɾ��   ͨ����ҵ��ȣ��ƻ���ȣ������������������������
	int deleteByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	//ɾ��   ͨ����ҵ��ȣ��ƻ���ȣ����������
	void deleteByZYNDAndJHNDAndCountyCode(@Param("ZYND")String ZYND,@Param("JHND")String JHND, @Param("countyCode")String countyCode,@Param("cityCode")String cityCode,@Param("zllbs")Set<String> zllbs,@Param("gclbs")Set<String> gclbs);
	//���ݼ�¼�� ���ݹ�����𡢼ƻ���ȡ���ҵ��ȡ�����������������
	int countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);
	
	List<YzlTask> selectByEpcIdAndTime(@Param("epcid")String epcid, @Param("year")String year);

	YzlEpc selectByEpcId(String eid);

	YzlTask selectByTid(String tid);

	//���ݹ��̲�ѯ��Ӧ������
	List<YzlEpcTaskProgress> selectByEpc(@Param("ename")String ename,@Param("year")String year);

	List<YzlEpcTaskProgress> selectTaskIssued(@Param("gc")String gc, @Param("zl") String zl, @Param("county")String county,@Param("time") String time);
	//��ѯZLLB��ȥ�ظ��� ͨ���û�Ȩ�ޣ���ҵ��ȣ������������
	//��ѯ��ͳ���м����� ����������
	List<YzlEpcTaskProgress> queryCityData(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("stats")List<Integer> stats);
	//��ѯ��ͳ���ؼ����� ����������
	List<YzlEpcTaskProgress> queryCountyData(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("stats")List<Integer> stats);

	List<String> selectByZJCCZ(@Param("zynd")String zYND,@Param("jhnd") String jHND,@Param("countyCode") String countyCode,@Param("cityCode") String cityCode,
			@Param("gclbs")Set<String>  gclbs,@Param("zllbs")Set<String> zllbs);
	//��ѯ����������ӵ�еĹ���
	List<String> selectByTaskPosessEpc(@Param("year")String year,@Param("gclb")String gclb, @Param("code")String clcik,@Param("authority")List<String> authoritys);
	//�����ؼ�������ź���ҵ��� �����ϴ����ļ�
	int updatefilesUrlByCountCodeAndZYND(@Param("year")String year,@Param("countyCode")String countyCode,@Param("cityCode")String cityCode,@Param("filesUrl")String filesUrl
			,@Param("modifier")String modifier ,@Param("updateTime")Date updateTime);
	
	//�����ؼ�������ź���ҵ���ɾ���ϴ����ļ�
	int deletefilesUrlByCountCodeAndZYND(@Param("year")String year,@Param("countyCode")String countyCode,@Param("cityCode")String cityCode
			,@Param("modifier")String modifier,@Param("uploadFiles") String uploadFiles,@Param("deleteFileCode") String deleteFileCode);
	//��ѯ����·������ݲ�ͳ���ؼ����� ����������
	List<YzlEpcTaskProgress>  queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
	//��ѯ����·������ݲ�ͳ���м����� ����������
	List<YzlEpcTaskProgress> queryCityDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
	
	//���ݼ�¼�� ���ݹ�����𡢼ƻ���ȡ���ҵ��ȡ�����������������
	List<YzlEpcTaskProgress>  queryByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(YzlEpcTaskProgress epcTaskProgress);

	//���ݼƻ���ȡ����������
//	List<YzlEpcTaskProgress>  selectByJhndAndCountyCode(YzlEpcTaskProgress epcTaskProgress);

	List<YzlEpcTaskProgress> selectByProceed(@Param("gclb")String gclb,@Param("anumber")String anumber, @Param("year")String year,@Param("stats")List<String> stats);
	
	//��ѯ��ҳ����������б�
	List<BacklogVO> queryBacklogList(@Param("authoritys")List<String> authoritys);
	
	//��ѯĳ�����̵�״̬
	String selectStatByGCLB(@Param("GCLB")String GCLB, @Param("countyCode")String countyCode, @Param("JHND")String JHND);
}