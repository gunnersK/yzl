package com.yzl.utils.dto;

import java.math.BigDecimal;

import com.yzl.pojo.YzlDistrict;

public class TaskIssuedDTO {
	
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
	 * �������
	 */
	private BigDecimal 	taskNumber;
	/***
	 * ���񷢲���
	 * @return
	 */
	private String creator;
	
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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

	public BigDecimal getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(BigDecimal taskNumber) {
		this.taskNumber = taskNumber;
	}
	@Override
	public String toString() {
		return "CountyTaskWorkingDTO [city=" + city + ", county=" + county + ", cityNumber=" + cityNumber
				+ ", countyNumber=" + countyNumber + ", GCLB=" + GCLB + ", ZLLB=" + ZLLB + ", JHND=" + JHND + ", ZYND="
				+ ZYND + ", XTJSBMJ=" + taskNumber + "]";
	}

	
	
}
