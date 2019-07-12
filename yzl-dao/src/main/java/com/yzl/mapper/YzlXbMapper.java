package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.pojo.YzlXbExample;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.pojo.dto.XbDTO;
import com.yzl.utils.CountZLLBDTO;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.vo.BacklogVO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YzlXbMapper {
    int countByExample(YzlXbExample example);

    int deleteByExample(YzlXbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlXb record);

    int insertSelective(YzlXb record);

    List<YzlXb> selectByExample(YzlXbExample example);

    YzlXb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlXb record, @Param("example") YzlXbExample example);

    int updateByExample(@Param("record") YzlXb record, @Param("example") YzlXbExample example);

    int updateByPrimaryKeySelective(YzlXb record);

    int updateByPrimaryKey(YzlXb record);
    
    //��ѯ�����ؼ��ϱ��������ͨ��County���з���
    public List<XbDTO> queryXTJSBMJGroupByCounty(@Param("anumbers")List<String> anumbers);

    //��ѯ�����ؼ��ϱ��������ͨ��City���з���
    public List<TaskDTO> queryXTJSBMJGroupByCity(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("stats")List<String> stats);
    //����city��ѯ�ؼ��ϱ������ͨ���ط���
   List<TaskDTO> queryXTJSBMJGroupCountyAndByCity(@Param("anumbers")List<String> anumbers,@Param("city")String cityCode,@Param("year")String year,@Param("stat")Integer stat);
   
   ////�����еı�Ų�ѯС����е�ӵ�е��صı��
	List<String> selectByCityNumber(@Param("anumber")String anumber,@Param("gc")String gc,@Param("zl")String zl);

	//�����еı�Ų�ѯС����е�ӵ�����
	List<String> selectByYer(@Param("anumber")String anumber,@Param("times")String times);

	//��ѯĳ����֪����ȵ�С��
	List<YzlXb> selectByCountyAndTime(@Param("county")String county,@Param("gclb")String gclb,@Param("zllb")String zllb, @Param("time")String time);

	//��ѯ���и��ݵ�������ȷ������� ��������
	List<YzlXb> selectByGropCity(@Param("anumber")String anumber,@Param("sname")String sname,@Param("times")String times,@Param("permsList")List<String> permsList,@Param("page")Integer page,@Param("rows")Integer rows,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//�ܼ�¼�� ������
	List<YzlXb> selecTtotalCity(@Param("anumber")String anumber,@Param("sname")String sname,@Param("times")String times,@Param("permsList")List<String> permsList,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//��ѯ���и��ݵ�������ȷ������� ��������
	List<YzlXb> selectByGropCounty(@Param("number")String number,@Param("sname")String sname,@Param("time")String time, @Param("page")Integer page, @Param("rows")Integer rows,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//�ܼ�¼�� ������
	List<YzlXb> selecTtotalCounty(@Param("number")String number,@Param("sname")String sname,@Param("time")String time,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//��ѯ����������
	List<YzlXb> selectByGropMunicipality(@Param("sname")String sname,@Param("time")String time,@Param("permsList")List<String> permsList, @Param("page")Integer page, @Param("rows")Integer rows,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);

	//�ܼ�¼��
	List<YzlXb> selecTtotalMunicipality(@Param("sname")String sname,@Param("time")String time,@Param("permsList")List<String> permsList,@Param("cityCode")String cityCode,@Param("zllb")String zllb,@Param("gclb")String gclb);
	
	//��ѯ��������ء��������������� ���з���
	List<CountyTaskWorkingDTO> queryTaskWorkingByGroupCountyAndGCLBAndZLLB(@Param("aNumbers")List<String> aNumbers,@Param("year")String year,@Param("stats")List<Integer> stats);
	
	//���ݵ����������,����״̬ ��year��ѯ��������ء��������������� ���з���
	List<CountyTaskWorkingDTO> queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(@Param("aNumbers")List<String> aNumbers,@Param("areaCode")String areaCode,@Param("year")String year,@Param("stats")List<Integer> stats);

	//�ύ
	int updateByMark(@Param("city")String city, @Param("county")String county, @Param("JHND")String jHND, @Param("ZYND")String zYND,@Param("stat")String stat,@Param("stats")List<String> stats,@Param("zllb")String zllb);

	List<YzlTask> selectByCodeAndEpc(@Param("time")String time, @Param("code")String code,@Param("epc") String epc,@Param("stat")String stat);

	List<CountyTaskWorkingDTO> seletByEpcAndTask(@Param("time")String time, @Param("code")String code,@Param("epc") String epc,@Param("authoritys")List<String> authoritys,@Param("stats")List<Integer> stats);

	List<YzlXb> selectByEidAndTime(@Param("eid")String eid, @Param("year")String year,@Param("county")String county);

	List<YzlTask> selectEpcTabName(@Param("stats")List<String> stats,@Param("year")String year,@Param("disCode")String disCode,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	//���ݹ��̲�ѯӵ�е�����
	List<YzlEpc> selectByEpcPossessTask(@Param("tcode")String tcode,@Param("year")String year,@Param("stats")List<String> stat,@Param("disCode")String disCode,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	List<YzlEpcAndTaskStaticti> selectByGroupStaticti(@Param("year")String year, @Param("stat")Integer stat);
	
	//��ѯ�������״̬����ȥ��
	List<String> selectByCityAndTimeAndStat(@Param("year")String year, @Param("stats")List<String> stats,@Param("menu")List<String> menu);

	//��ѯ����н����ж��ٸ�����
	List<YzlEpcAndTaskStaticti> selectByCityAndEpc(@Param("stats")List<String> stats,@Param("year")String year,@Param("city")String city,@Param("disCode")String disCode,@Param("citys") List<String> citys,@Param("menu")List<String> menu,@Param("zllb")String zllb);

	
	//�õ������������̽��������
	List<YzlEpcAndTaskStaticti> selectByEidAndCityAndTime(@Param("stat")Integer stat,@Param("year")String year,@Param("city")String city, @Param("eid")Integer eid);

	//�����б�Ų�ѯ������
	String selectByCity(String city);

	List<String> selectByCityAndTimeAndStatCountyAndCity(@Param("year")String year, @Param("stats")List<String> stats, @Param("disCode")String disCode,@Param("menu")List<String> menu);

	//��ѯ����������״̬���ж��ٸ�����
	List<YzlXb> selectByeid(@Param("eid")String eid,@Param("stat")Integer stat,@Param("year")String year,@Param("menu")List<String> menu,@Param("Anumbers")List<String> Anumbers);

	//��ѯ���������л�����Ӧ�õ�Anumber���
	List<String> selectByFlagOrDcode(String nid);

	//���ݹ�������ѯӵ�ж��ٸ�����
	List<YzlXb> selectByEpc(@Param("year")String year,@Param("stat") Integer stat, @Param("ename")String ename,@Param("Anumbers")List<String> Anumbers,@Param("menu")List<String> menu);

	//����������������ļƻ������Ƕ���
	YzlEpcTaskProgress selectByPlanTask(@Param("zllb")String zllb, @Param("gclb")String gclb, @Param("county")String county, @Param("jhnd")String jhnd, @Param("zynd")String zynd);

	////������״̬��������������˶���
	YzlEpcAndTaskStaticti selectByCompleteTask(@Param("zllb")String zllb, @Param("gclb")String gclb, @Param("county")String county, @Param("jhnd")String jhnd, @Param("zynd")String zynd,@Param("stats")List<String> stats);

	//��ѯ����������ӵ�ж��ٸ�����//	tcode ���������	yearʱ��		stat״̬		nid���ػ�������
	List<String> selectByTaskPossessEpc(@Param("tcode")String tcode, @Param("year")String year,@Param("stat") String stat, @Param("nid")String nid);

	/*List<YzlEpcAndTaskStaticti> selectByCityAndEpcInMenu(@Param("stat")Integer stat,@Param("year") String year,@Param("citys") List<String> citys, @Param("disCode")String disCode);*/
	List<YzlTask> selectByCodeAndEpc();
	//		<!-- ���ݵ���������ź���ҵ��Ȳ�ѯ�ؼ��Լ��ϱ������ͨ�� �ؼ�������������������ҵ��ȡ��ƻ���Ƚ��з��� -->
	List<YzlEpcAndTaskStaticti> queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB);
	//���ݵ���������ź���ҵ��Ȳ�ѯ�ؼ��Լ��ϱ������ͨ�� �ؼ�������������������ҵ��ȡ��ƻ���Ƚ��з��� -->
	List<YzlEpcAndTaskStaticti> queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB);

	List<YzlEpcAndTaskStaticti> selectByCityAndEpcComplation(@Param("stats")List<String> stats,@Param("year")String year,@Param("city")String city,@Param("disCode")String disCode,@Param("citys") List<String> citys,@Param("menu")List<String> menu,@Param("zllb")String zllb);
	//ͳ���м������������
	List<CountZLLBDTO> countCityZllbNumber(@Param("stats")List<Integer> stats);
	//ͳ���ؼ������������
	List<CountZLLBDTO> countCountyZllbNumber(@Param("stats")List<Integer> stats);
	//ͳ�ƴ���˵ĸ���
	int countCountyNmberByStat(@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	
	List<YzlEpcAndTaskStaticti> queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	List<YzlEpcAndTaskStaticti> queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(@Param("year")String year,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	List<BacklogVO> queryCountyAndCityAndUpdateTimeAndStat(@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	

	//��ѯ�·�������
	List<YzlEpcTaskProgress> selectByCityCountyTaskIssued(@Param("year")String year,@Param("menu")List<String> menu,@Param("zllb")String zllb,@Param("stats")List<String> stats);

	//��ѯ�����л���ɵ�����
	List<YzlXb> selectByCityComplation(@Param("year")String year,@Param("stats") List<String> stats, @Param("menu")List<String> menu,@Param("disCode") String disCode,@Param("zllb") String zllb,@Param("proceed")String proceed);

	List<YzlTask> selectByTaskIssuedTableHead(@Param("year")String year, @Param("disCode")String disCode,@Param("zllb") String zllb,@Param("menu") List<String> menu,@Param("stats")List<String> stats);

	List<YzlEpc> selectByZllb(@Param("tcode")String tcode,@Param("year")String year,@Param("disCode") String disCode,@Param("menu") List<String> menu,@Param("zllb") String zllb,@Param("stats")List<String> stats);

	List<YzlEpcTaskProgress> selectByCountyCountyTaskIssued(@Param("year")String year,@Param("menu") List<String> menu,@Param("disCode") String disCode,@Param("zllb") String zllb,@Param("stats")List<String> stats,@Param("proceed")String proceed);

	int updateByEpcTaskTable(@Param("zllb")String zllb, @Param("county")String county,@Param("ZYND") String ZYND, @Param("stats")List<String> stats,@Param("code")Integer code);

	
	List<YzlXb> queryDemo(@Param("messageList")List<YzlMessage> messageList);
	//List<YzlXb> selectByCountyComplation(@Param("year")String year,@Param("stats") List<String> stats,@Param("menu") List<String> menu,@Param("disCode") String disCode);


	
	Double sumCityXTJSBMJ(String statu);
	
    //��ѯ�����ؼ��ϱ��������������ͨ��City���з���
    public List<TaskDTO> queryXTJSBMJAndCityGroupByCity(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("statu")String statu);
    //��ѯ�����ؼ��ϱ��������������ͨ��County���з���
    public List<TaskDTO> queryXTJSBMJAndCountyByCityCode(@Param("anumbers")List<String> anumbers,@Param("year")String year,@Param("statu")String statu,@Param("cityCode")String cityCode);

}