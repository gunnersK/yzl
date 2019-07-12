package com.yzl.utils.dto;

import java.math.BigDecimal;

import com.yzl.pojo.YzlDistrict;

public class CountyTaskWorkingDTO {
	
	/***
	 * Ψһ��ʶ
	 */
	private Integer id;
	/***
	 * �м�����
	 */
	private String city;
	/***
	 * �ؼ�����
	 */
	private String 	county;
	/***
	 * ���������
	 */
	private Integer cityNumber;
	/***
	 * ���������
	 */
	private Integer countyNumber;
	/***
	 * �������
	 */
	private String 	GCLB;
	/***
	 * �������
	 */
	private String 	ZLLB;
	/**
	 * �ƻ����
	 */
	private String 	JHND;
	/***
	 * ��ҵ���
	 */
	private String 	ZYND;
	/***
	 * ��ͳ���ϱ����
	 */
	private BigDecimal 	XTJSBMJ;
	/***
	 * �·�������
	 */
	private BigDecimal taskProgress;
	/****
	 * ����״̬ 
	 */
	private Integer stat;
	
	
	public BigDecimal getTaskProgress() {
		return taskProgress;
	}
	public void setTaskProgress(BigDecimal taskProgress) {
		this.taskProgress = taskProgress;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Integer getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(Integer cityNumber) {
		this.cityNumber = cityNumber;
	}
	public Integer getCountyNumber() {
		return countyNumber;
	}
	public void setCountyNumber(Integer countyNumber) {
		this.countyNumber = countyNumber;
	}
	public String getGCLB() {
		return GCLB;
	}
	public void setGCLB(String gCLB) {
		GCLB = gCLB;
	}
	public String getZLLB() {
		return ZLLB;
	}
	public void setZLLB(String zLLB) {
		ZLLB = zLLB;
	}
	public String getJHND() {
		return JHND;
	}
	public void setJHND(String jHND) {
		JHND = jHND;
	}
	public String getZYND() {
		return ZYND;
	}
	public void setZYND(String zYND) {
		ZYND = zYND;
	}
	public BigDecimal getXTJSBMJ() {
		return XTJSBMJ;
	}
	public void setXTJSBMJ(BigDecimal xTJSBMJ) {
		XTJSBMJ = xTJSBMJ;
	}
	

	public Integer getStat() {
		return stat;
	}
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	@Override
	public String toString() {
		return "CountyTaskWorkingDTO [city=" + city + ", county=" + county + ", cityNumber=" + cityNumber
				+ ", countyNumber=" + countyNumber + ", GCLB=" + GCLB + ", ZLLB=" + ZLLB + ", JHND=" + JHND + ", ZYND="
				+ ZYND + ", XTJSBMJ=" + XTJSBMJ + "]";
	}

	
	
}
