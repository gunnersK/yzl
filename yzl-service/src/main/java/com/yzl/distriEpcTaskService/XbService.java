package com.yzl.distriEpcTaskService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.HighchartsResultVO;

public interface XbService {

	//highchartsͼ����ʾ
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request,String year);
	//ͳ�����Լ��ϱ���� ͨ��County��GCLB��ZLLB���з���
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLB(HttpServletRequest request,Integer page,Integer rows,String year);
	//ͳ�����Լ��ϱ���� ͨ��County��GCLB��ZLLB���з����ͨ������������Ž��в�ѯ
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(HttpServletRequest request,Integer page,Integer rows,String areaCode,String year);
	List<HighchartsResultVO> pie(HttpServletRequest request, String year);
}
