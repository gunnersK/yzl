package com.yzl.LogService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlDistrictExample.Criteria;
import com.yzl.utils.EasyUIResult;

//import redis.clients.jedis.Jedis;

@Component
@Aspect
public class AopRedisCache {

//	@Autowired
//	private Jedis jedis;
	
//	@Pointcut("execution(public * com.yzl.*Service..*.*(..))")//�����
//	public void aopRedis() {
		
//	}
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Resource
	private RedisUtils redisUtils;

	@Around("@annotation(com.yzl.LogService.RedisCache)")//����֪ͨ,��Ŀ�귽�����ǰ������ǿ����
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
		
		//����ִ��ǰ�Ĵ��� �൱��ǰ��֪ͨ
		//��ȡ����ǩ��
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		
		//��ȡ����
		Method method = methodSignature.getMethod();
		
		//��ȡ���������ע��
		RedisCache redisCache = method.getAnnotation(RedisCache.class);
		
		//��ȡ��������������ֵ
		String opreateType = redisCache.type();
		
		//���jedis����
//		Jedis jedis = JedisPoolClient.getJedis();
		Object[] args = joinPoint.getArgs();
		String disCode = null;//����
		String username = null;//��¼���û���
		String gclb=null;
		Integer page = null;
		String time = null;
		String condition = null;
		String zllb = null;
		String cityCode = null;
		String Mon_zllb = null;
		String Mon_gclb = null;
		
		if (!opreateType.equals("taskIssued") && !opreateType.contains("mon") && !opreateType.equals("delIsuueFile")) {
			disCode =(String) args[1];//��ȡ�ڶ������������ı��
			username = (String) args[2];//��ȡ������������ǰ��¼���û���
		}
		
		
//		System.out.println(disCode+"-------------");
		if (disCode !=null && disCode.length()==2) {
			YzlDistrictExample example = new YzlDistrictExample();
			Criteria criteria = example.createCriteria();
			criteria.andFlagEqualTo(disCode);
			List<YzlDistrict> district = districtMapper.selectByExample(example);
			disCode = district.get(0).getCitycode();
		}
		String stat = null;
		if(opreateType.equals("taksWorkadd")) {//��������
			time =(String) args[0];//ʱ��
			gclb =(String) args[3];//����
			stat =(String) args[6];//״̬
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"taskwork"+username+time+gclb+stat);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
			
		}else if(opreateType.equals("completionTask")) {//���
			time =(String) args[0];//ʱ��
			page = (Integer) args[3];
			zllb = (String) args[5];//�������
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"completion"+username+time+page+zllb);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitMunicipality")) {//���ͳ��������
//			String time1 =(String) args[0];//ʱ��
			disCode = "GX450";
			condition =(String) args[1];//����
			time =(String) args[2];//ʱ��
			
			if ((String) args[0]!=null) {
				time = (String) args[0];
			}
			page =(Integer) args[3];//ҳ��
			username =(String) args[5];//�û���show_xbMunicipality
			cityCode = (String) args[6];//�б��
			Mon_zllb = (String) args[7];//�������
			Mon_gclb = (String) args[8];//�������
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"monitMunicipality"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitCity")) {//���ͳ����
			disCode =(String) args[0];//�������
			YzlDistrict district = districtMapper.selectByFlag(disCode);
			disCode = district.getCitycode();
			page =(Integer) args[1];//ҳ��
			condition =(String) args[3];//����
			
			time =(String) args[5];//ʱ��
			if ((String) args[4]!=null) {
				time =(String) args[4];//ʱ��
			}
			username =(String) args[6];//�û���
			cityCode = (String) args[7];//�б��
			Mon_zllb = (String) args[8];//�������
			Mon_gclb = (String) args[9];//�������
			
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"monitCity"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitCounty")) {//���ͳ����
			disCode =(String) args[0];//�������
			
			YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(disCode));
			disCode = district.getAnumber();
			
			page =(Integer) args[1];//ҳ��
			condition =(String) args[3];//����
			
			time =(String) args[5];//ʱ��
			if ((String) args[4]!=null) {
				time =(String) args[4];//ʱ��
			}
			username =(String) args[6];//�û���
			Mon_zllb = (String) args[7];//�������
			Mon_gclb = (String) args[8];//�������
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"monitCounty"+username+time+page+condition+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}
		
		//���Ϊ�·�����
		if (opreateType.equals("taskIssued")) {
			page =(Integer) args[1];//ҳ��
			time =(String) args[3];//ʱ��
			disCode =(String) args[4];//����
			 gclb =(String) args[5];//�������
			username = (String) args[6];//�û���
			if (disCode.equals("45")) {
				disCode = "GX450";
			}
			//�ȴӻ�����ȡ����
			String CacheData = (String) redisUtils.get(disCode+"taskIssued"+username+time+gclb+page);
			if(StringUtils.isNotBlank(CacheData)) {//�ж������Ƿ�Ϊ��
				//�����Ϊ��ֱ�Ӵӻ�����ȡ����   �����ݷ���
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}
		
		if (opreateType.equals("taksWorkdelIsuuedUpdate")) {
			disCode =(String) args[2];//����
			
			YzlDistrict district = districtMapper.selectByCounty(disCode);
			String string = district.getCitycode();
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			if (keys.size() > 0) {
				for (String city : keys) {
					if(city.contains(string)) {
						redisUtils.del(city);
					}
					if (city.contains("GX45")) {
						 redisUtils.del(city);
					}
				}
			}
		}
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		if (opreateType.equals("taksWorkdelIsuued") ) {//ɾ��key
			
			HashMap<String,Object> hashMap = new HashMap<>();
			
			if (disCode.equals("") || disCode == null) {
				String [] array =(String[]) args[0];//�������
				for (String string : array) {
					hashMap = JSONObject.parseObject(string, HashMap.class);
					Object object = hashMap.get("cityCode");
					hashSet.add(object.toString());
				}
			}else {
				 hashMap = JSONObject.parseObject(disCode, HashMap.class);
				 Object object = hashMap.get("cityCode");
				 hashSet.add(object.toString());
			}
			
		}
		
		if (opreateType.equals("taksWorkdel") || opreateType.equals("taksWorkdelIsuued")) {//ɾ��key
			if (disCode != null && disCode.length() == 6 &&  disCode != "null") {
				disCode = disCode.substring(0, 4);
				System.out.println(disCode);
			}
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			if (keys != null && keys.size()>0) {
				for (String string : keys) {
					if (hashSet != null && hashSet.size() > 0) {
						for (String city : hashSet) {
							if(string.contains(city)) {
								redisUtils.del(string);
							}
						}
					}
					if(string.contains(disCode)) {
						redisUtils.del(string);
					}
					if (string.contains("GX45")) {
						 redisUtils.del(string);
					}
					if (string.contains("taskIssued")) {
						redisUtils.del(string);
					}
				}
			}
		}
		if (opreateType.equals("delIsuueFile")) {
			
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			for (String string : keys) {
				if (string.contains("taskIssued")) {
					redisUtils.del(string);
				}
			}
		}
		
		Object object = null;
		
		try {
			
			//������û������ֱ��ִ�д�������ѯ���ݿ�
			//ִ�д�����
			object = joinPoint.proceed();
			
			if (object!=null && opreateType.equals("taksWorkadd")) {
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"taskwork"+username+time+gclb+stat, jsonString);
			}else if(opreateType.equals("completionTask")) {
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"completion"+username+time+page+zllb, jsonString);
			}else if(opreateType.equals("taskIssued")) {
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"taskIssued"+username+time+gclb+page, jsonString);
			}else if (opreateType.equals("monitMunicipality")) {
				
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"monitMunicipality"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb, jsonString);
			}else if(opreateType.equals("monitCity")){
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"monitCity"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb, jsonString);
			}else if (opreateType.equals("monitCounty")) {
				String jsonString = JSONObject.toJSONString(object);
				//ʹ�õ�����Ÿ���¼���û�����Ϊkey
				redisUtils.set(disCode+"monitCounty"+username+time+page+condition+Mon_zllb+Mon_gclb, jsonString);
			}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
		}
		return object;
	}

	//�ж��Ƿ�����ĸ
	public boolean check(String fstrData){
        char   c   =   fstrData.charAt(0);
        
        if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z'))){  
              return   true;   
        }else{
              return   false;   
        }   
    }
	//�ж��Ƿ�Ϊ����
	public boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	 }
}