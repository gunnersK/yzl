package com.yzl.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-04-17
 */
public class YzlLog {
	private String hi;
	
    public String getHi() {
		return hi;
	}

	public void setHi(String hi) {
		this.hi = hi;
	}

	/**
     * id
     */
    private Integer id;

    /**
     * �������û�
     */
    private String name;

    /**
     * ִ�еĲ���
     */
    private String operate;

    /**
     * ������ʱ��
     */
    private String operateTime;

    /**
     * �������ĵ������
     */
    private String dcode;

    /**
     * �������Ĺ������
     */
    private String gclb;

    /**
     * ���������������
     */
    private String zllb;

    /**
     * ������̵�ʱ��
     */
    private String year;

    /**
     * �ļ�����
     */
    private String filename;

    /**
     * ����
     */
    private String lea;

    /**
     * ״̬
     */
    private String stat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime == null ? null : operateTime.trim();
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode == null ? null : dcode.trim();
    }

    public String getGclb() {
        return gclb;
    }

    public void setGclb(String gclb) {
        this.gclb = gclb == null ? null : gclb.trim();
    }

    public String getZllb() {
        return zllb;
    }

    public void setZllb(String zllb) {
        this.zllb = zllb == null ? null : zllb.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getLea() {
        return lea;
    }

    public void setLea(String lea) {
        this.lea = lea == null ? null : lea.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }
}