package com.yzl.LogService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//����ע��
@Retention(RetentionPolicy.RUNTIME) // ����ʱ�ɼ�
public @interface LogAnno {
	String opreateType();  //��¼��־����������
}
