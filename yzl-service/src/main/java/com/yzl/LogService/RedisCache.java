package com.yzl.LogService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//redis����ע��
@Target(value=ElementType.METHOD)//�����ڷ�����
@Retention(value=RetentionPolicy.RUNTIME)
public @interface RedisCache {

	//String cacheKey(); //�����key
	
	String type();//����
	
	//long timeOut(); //��Ч��
}
