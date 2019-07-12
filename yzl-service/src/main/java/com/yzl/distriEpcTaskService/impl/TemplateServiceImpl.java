package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.distriEpcTaskService.TemplateService;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlTaskEpcMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlEpcTaskProgressExample;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskEpc;
import com.yzl.pojo.YzlTaskEpcExample;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Service
public class TemplateServiceImpl implements TemplateService{

	/*@Autowired
	private YzlEpcTaskProgressMapper taskProgressMapper;*/
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskEpcMapper taskEpcMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	//��ѯ����
	@Override
	public EasyUIResult findAll(String value,Integer page,Integer rows) {
		
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		
		LinkedHashSet<YzlEpc> epcSet=new LinkedHashSet<>();
		
		List<Integer> tatolCount=null;
		List<YzlEpc> vague=null;
		if (value == null || value == "") {
			//���м�¼��   
			tatolCount= taskEpcMapper.selectByDistinctEcode();//��Զ��ϵ��
			 
			//��ǰҳ  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
			int page2=(page-1)*rows;
			//��ҳ��ȡ�����������й����Ĺ����ظ���ȥ��
			List<Integer> list = taskEpcMapper.selectByDistinctEcodePage(page2,rows);//��Զ��ϵ��
			if (list != null && list.size() > 0) {
				
				for (Integer integer : list) {
					//��ȡ�����еĹ���Id
					Integer epcode = integer;
					YzlTaskEpcExample example=new YzlTaskEpcExample();
					example.createCriteria().andEcodeEqualTo(epcode);
					
					//��ѯ�������ӵ�е�����
					List<YzlTaskEpc> TaskIds = taskEpcMapper.selectByExample(example);
					
					//����װ����ļ���
					List<YzlTask> tasksList=new ArrayList<>();
					for (YzlTaskEpc YzlTaskEpc : TaskIds) {
						//��ȡ�����е�����id
						Integer tpcode = YzlTaskEpc.getTcode();
						//����id��ѯ���������Ž�������
						YzlTask task = taskMapper.selectByPrimaryKey(tpcode);
						tasksList.add(task);
					}
					//����id��ѯ����
					YzlEpc epc = epcMapper.selectByPrimaryKey(epcode);
					//�ѹ���ӵ�е�����Ž�����
					epc.setList(tasksList);
					
					int sum=0;
					//����LinkHashSet��ֲ�ͷŽ�LinkHashSet�ļ��ϵ�ֲ�Ƚ�������ǵ����ֲ�һ������ӽ�ȥ
					if (epcSet != null && epcSet.size() > 0) {
						for(YzlEpc epc2:epcSet) {
							if(!epc2.getEname().equals(epc.getEname())) {
								sum+=1;
							}
						}
						if (sum==epcSet.size()) {
							epcSet.add(epc);
						}
					}else {
						epcSet.add(epc);
					}
				}
			}
		}else {
			 vague = taskEpcMapper.selectVague(value);
			//��ǰҳ  ����	��ǰҳ	-1	���Ե�ǰҳ��ʾ�ļ�¼��
			int page2=(page-1)*rows;
			//����������ַ��õ����̶���+��ҳ
			List<YzlEpc> list = taskEpcMapper.selectVaguePage(value,page2,rows);
			if (list != null && list.size() > 0) {
				for (YzlEpc yzlEpc : list) {
					//���ݹ���id�õ���ӵ�е��������
					List<YzlTask> tasks = taskEpcMapper.selectByEcode(yzlEpc.getEcode());
					yzlEpc.setList(tasks);
					epcSet.add(yzlEpc);
				}
			}
		}
		EasyUIResult result=new EasyUIResult();
		
		if (value == null || value == "") {
			result.setTotal(tatolCount.size());//�ܼ�¼��
			result.setRows(new ArrayList<>(epcSet));
			return result;
		}else {
			result.setTotal(vague.size());
			result.setRows(new ArrayList<>(epcSet));
			
			return result;
		}
	}

	//���
		@Override
		public YzlResult addTemplate(String eid, String[] tids) {
			
			//���ݹ���id��ѯ
			List<YzlTaskEpc> listTaskEpc = this.findByEid(eid);
			
			if (listTaskEpc.size()>0 && listTaskEpc!=null) {
				return YzlResult.ok(300);
			}
			int sum=0;
			if (tids.length>0) {
				for (String string : tids) {
					
					YzlTaskEpc record=new YzlTaskEpc();
					record.setEcode(Integer.valueOf(eid));
					record.setTcode(Integer.valueOf(string));
					
					int si = taskEpcMapper.insert(record);
					sum+=si;
				}
			}
			
			if (sum==tids.length) {
				return YzlResult.ok(200);
			}
			return YzlResult.ok(400);
		}

	//���ݹ���id��ѯ
	public List<YzlTaskEpc> findByEid(String eid){
		
		YzlTaskEpcExample example=new YzlTaskEpcExample();
		example.createCriteria().andEcodeEqualTo(Integer.valueOf(eid));
		List<YzlTaskEpc> list = taskEpcMapper.selectByExample(example);
		
		return list;
	}

	//ɾ��
	@Override
	public YzlResult deleterTemplate(String[] etdis) {
		
		List<List<YzlEpcTaskProgress>> epcTaskProgresses =new ArrayList<>();
		List<YzlEpcTaskProgress> etp = null;
		for (String string : etdis) {
			
			YzlEpcTaskProgressExample example=new YzlEpcTaskProgressExample();
			etp = epcTaskProgressMapper.selectByExample(example);
			if (etp!=null&&etp.size()>0) {
				epcTaskProgresses.add(etp);
			}
		}
		
		if (epcTaskProgresses!=null && epcTaskProgresses.size()>0) {
			return YzlResult.ok(300);
		}
		
		int dum=0;
		for (int i = 0; i < etdis.length; i++) {
			String string = etdis[i];
			taskEpcMapper.deleteByEcode(Integer.valueOf(string));
			dum+=1;
		}
		if (dum>0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//�޸�
	@Override
	public YzlResult updateTemplate(String edname, String[] ids) {
		//���ݹ������Ʋ�����̱��
		Integer ecode = taskEpcMapper.selectByEpcEname(edname);
		//���ݹ��̱��ɾ��
		int ko = taskEpcMapper.deleteByEcode(ecode);
		//��������
		YzlResult result = this.addTemplate(String.valueOf(ecode), ids);
		Integer data = (Integer) result.getData();
		
		if (ko > 0 && data==200) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<YzlTask> showTask() {
		YzlTaskExample example=new YzlTaskExample();
		List<YzlTask> list = taskMapper.selectByExample(example);
		return list;
	}

}
