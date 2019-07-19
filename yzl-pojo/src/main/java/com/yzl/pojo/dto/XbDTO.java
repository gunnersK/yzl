package com.yzl.pojo.dto;

import java.math.BigDecimal;

import com.yzl.pojo.YzlDistrict;

/**
 * @author administrator_
 *
 */
public class XbDTO {
	
	
    /**
     * ��ʶ��
     */
    private String bsm;

    /**
     * ����������
     */
    private String city;

    /**
     * ����������
     */
    private String county;
    
    
    /**
     * �������
     */
    private String zllb;

    /**
     * �ƻ����
     */
    private String jhnd;

    /**
     * ��ҵ���
     */
    private String zynd;
    
    /**
     * ״̬
     */
    private String stat;
    
    /**
     * ���ַ�ʽ
     */
    private String ylfs;
    
    /**
     * ��ʵ�������
     */
    private String hsylmj;

    /**
     * ������������
     */
    private String zlztlx;
    
    //�ؼ��Լ��ϱ����
    private BigDecimal countyUPArea;
    
    //����
    private YzlDistrict district;
    

	public BigDecimal getCountyUPArea() {
		return countyUPArea;
	}

	public void setCountyUPArea(BigDecimal countyUPArea) {
		this.countyUPArea = countyUPArea;
	}

	public String getBsm() {
		return bsm;
	}

	public void setBsm(String bsm) {
		this.bsm = bsm;
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

	public String getZllb() {
		return zllb;
	}

	public void setZllb(String zllb) {
		this.zllb = zllb;
	}

	public String getJhnd() {
		return jhnd;
	}

	public void setJhnd(String jhnd) {
		this.jhnd = jhnd;
	}

	public String getZynd() {
		return zynd;
	}

	public void setZynd(String zynd) {
		this.zynd = zynd;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getYlfs() {
		return ylfs;
	}

	public void setYlfs(String ylfs) {
		this.ylfs = ylfs;
	}

	public String getHsylmj() {
		return hsylmj;
	}

	public void setHsylmj(String hsylmj) {
		this.hsylmj = hsylmj;
	}

	public String getZlztlx() {
		return zlztlx;
	}

	public void setZlztlx(String zlztlx) {
		this.zlztlx = zlztlx;
	}

	@Override
	public String toString() {
		return "XbDTO [bsm=" + bsm + ", city=" + city + ", county=" + county + ", zllb=" + zllb + ", jhnd=" + jhnd
				+ ", zynd=" + zynd + ", stat=" + stat + ", ylfs=" + ylfs + ", hsylmj=" + hsylmj + ", zlztlx=" + zlztlx
				+ ", countyUPArea=" + countyUPArea + ", district=" + district + "]";
	}
	
}
