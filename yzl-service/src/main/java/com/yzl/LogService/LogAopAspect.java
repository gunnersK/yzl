package com.yzl.LogService;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlLog;
import com.yzl.pojo.YzlUser;

@Component
@Aspect
public class LogAopAspect {

	@Autowired
	private LogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private YzlDistrictMapper yzlDistrictMapper;
	
	@Around("@annotation(com.yzl.LogService.LogAnno)")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		
		//����ִ��ǰ�Ĵ��� �൱��ǰ��֪ͨ
		//��ȡ����ǩ��
		MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		
		//��ȡ����
		Method method = methodSignature.getMethod();
		
		//��ȡ���������ע��
		LogAnno logAnno = method.getAnnotation(LogAnno.class);
		
		//��ȡ��������������ֵ
		String opreateType = logAnno.opreateType();
		
		//������־����
		YzlLog log = new YzlLog();
		
		String[] fileNames = null;
		
		String lea = null;
		//��ȡ�ϴ����ļ�����
		String filename = (String) request.getSession().getAttribute("filename");
//		log.setFilename(filename);
		//ʱ��
		String time = null;
		List<HashSet<String>> list = new ArrayList<>();//װhashSet���ϵ�
		
		if ("�ύ".equals(opreateType)) {
			System.out.println("=======�ύ==========");
			Object[] args = pjp.getArgs();//��ȡǰ�˴�������ֵ
			
			//��һ������ת������
			String[] strings =(String[]) args[0];
			fileNames =(String[]) args[8];//����ļ�
			
			if (strings != null && strings.length > 0) {
				
				for (String string : strings) {
					
					HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
					
					Gson gson = new Gson();
					
					HashMap<String,String> map = gson.fromJson(string, HashMap.class);//��һ��json�ַ���ת��HashMap����
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						
						String key = entry.getKey();
						String value = entry.getValue();
						
						if ("county".equals(key)) {//���Ϊ��
							String county = SelectByCounty(value);//�õ��صı��
							hashSet.add(county);
						}
						
						if (key.contains("T")) {//�������T˵���ǹ��̺�����
							String gclb = capture(key);//�õ��������
							hashSet.add(gclb);
						}
					}
					list.add(hashSet);
				}
			}
			//�ڶ�������
			String object =(String) args[3];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//���Ϊ��
						String county = SelectByCounty(value);//�õ��صı��
						hashSet.add(county);
					}
					if (key.contains("T")) {//�������T˵���ǹ��̺�����
						String gclb = capture(key);//�õ��������
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			//����������    ȡʱ��
			time =(String) args[4];
			lea =(String) args[5];
			log.setStat("0");
		}
		if ("���".equals(opreateType)) {
			System.out.println("=======���==========");
			//��ȡǰ�˴�������ֵ
			Object[] args = pjp.getArgs();
			
			//ʱ��
			time =(String) args[3];
			
			String[] json =(String[]) args[0];//ǿת������
			fileNames =(String[]) args[7];//ǿת������
			
			if (json != null && json.length > 0) {
				for (String string : json) {//��������
					if(string != "" && string!=null) {
						HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
						
						Gson gson = new Gson();
						
						HashMap<String,String> map = gson.fromJson(string, HashMap.class);//��һ��json�ַ���ת��HashMap����
						Set<Entry<String,String>> entrySet = map.entrySet();
						for (Entry<String, String> entry : entrySet) {
							
							String key = entry.getKey();
							String value = entry.getValue();
							
							if ("county".equals(key)) {//���Ϊ��
								String county = SelectByCounty(value);//�õ��صı��
								hashSet.add(county);
							}
							
							if (key.contains("T")) {//�������T˵���ǹ��̺�����
								String gclb = capture(key);//�õ��������
								hashSet.add(gclb);
							}
						}
						list.add(hashSet);//��ӵ�������
					}
					
				}
			}
			//�ڶ�������
			String object =(String) args[2];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//���Ϊ��
						String county = SelectByCounty(value);//�õ��صı��
						hashSet.add(county);
					}
					if (key.contains("T")) {//�������T˵���ǹ��̺�����
						String gclb = capture(key);//�õ��������
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			lea =(String) args[6];
			log.setStat("1");
		}
		
		if ("�˻�".equals(opreateType) || "�������˻�".equals(opreateType)) {
			System.out.println("=======�˻�==========");
			//��ȡǰ�˴�������ֵ
			Object[] args = pjp.getArgs();
			//ʱ��
			time =(String) args[4];
			//��һ������ת������
			String[] strings =(String[]) args[0];
			fileNames =(String[]) args[6];//����ļ�
			
			if (strings != null && strings.length > 0) {
				
				for (String string : strings) {
					
					HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
					
					Gson gson = new Gson();
					
					HashMap<String,String> map = gson.fromJson(string, HashMap.class);//��һ��json�ַ���ת��HashMap����
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						
						String key = entry.getKey();
						String value = entry.getValue();
						
						if ("county".equals(key)) {//���Ϊ��
							String county = SelectByCounty(value);//�õ��صı��
							hashSet.add(county);
						}
						
						if (key.contains("T")) {//�������T˵���ǹ��̺�����
							String gclb = capture(key);//�õ��������
							hashSet.add(gclb);
						}
					}
					list.add(hashSet);
				}
			}
			//�ڶ�������
			String object =(String) args[3];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//װ���̺͵������
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//���Ϊ��
						String county = SelectByCounty(value);//�õ��صı��
						hashSet.add(county);
					}
					if (key.contains("T")) {//�������T˵���ǹ��̺�����
						String gclb = capture(key);//�õ��������
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			lea =(String) args[5];
			if ("�˻�".equals(opreateType)) {
				log.setStat("1");
			}
			if ("�������˻�".equals(opreateType)) {
				log.setStat("2");
			}
		}
		
		if (fileNames!=null) {
			StringBuffer bf = new StringBuffer();
			for (String string : fileNames) {
				bf.append(string+",");
			}
			log.setFilename(bf.toString());
		}
		
		log.setLea(lea);
		log.setOperate(opreateType);//����˵��
		log.setYear(time);//��ҵ���
		
		YzlUser user = (YzlUser)request.getSession().getAttribute("user");
		log.setName(user.getUsername());//���ò�����
		
		Object result = null;
		
		try {
			//�ô�����ִ��
			result = pjp.proceed();
			// 2.�൱�ں���֪ͨ(�����ɹ�ִ��֮��������)
			//...
		} catch (Throwable e) {
			//�൱���쳣֪ͨ
			e.printStackTrace();
		}finally {
			//�൱������֪ͨ
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = simpleDateFormat.format(date);
			
			log.setOperateTime(format);//���ò���ʱ��
			
			//����
			if (list.size() > 1) {//����1˵������������
				for (HashSet<String> hashSet : list) {
					
					StringBuffer bf = new StringBuffer();//ƴ��ÿһ�����̱��
					Iterator<String> iterator = hashSet.iterator();
					while (iterator.hasNext()) {//��������
						String string = iterator.next();
						
						if (string.length()>=6) {//���صı��
							log.setDcode(string);//���õ������
						}else {
							bf.append(string+",");
						}
					}
					log.setGclb(bf.toString());
					logService.addLog(log);//�����־
				}
			}else {
				if (list.size() != 0) {
					StringBuffer bf = new StringBuffer();
					HashSet<String> hashSet = list.get(0);
					
					Iterator<String> iterator = hashSet.iterator();
					while (iterator.hasNext()) {//��������
						String string = iterator.next();
						
						if (string.length()>=6) {//���صı��
							log.setDcode(string);
						}else {
							bf.append(string+",");
						}
					}
					log.setGclb(bf.toString());
					logService.addLog(log);//�����־
				}
				
			}
		}
		request.getSession().removeAttribute("filename");
		return result;
		
	}
	
	//���������Ʋ�ѯ�ر��
	private String SelectByCounty(String county) {
			YzlDistrict district = yzlDistrictMapper.selectByCounty(county);
			String anumber = district.getAnumber();
			return anumber;
	}
	
	//�ַ�����ȡ�õ��������
	private String capture(String string) {
		int indexOf = string.indexOf("T");//��ȡ���ַ����±�
		String gclb = string.substring(0, indexOf);//��ȡָ�����ַ�
		return gclb;
	}
}
