package com.yzl.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-05-05
 */
public class YzlMessage {
    /**
     * idΨһ��ʶ
     */
    private Long id;

    /**
     * ��ʾ��Ϣ(����)
     */
    private String content;

    /**
     * �����Ľ�ɫid(�����ֶΣ������ô�)
     */
    private Integer rid;

    /**
     * ����ʱ��
     */
    private Date createtime;

    /**
     * ��¼�û���״̬
     */
    private String statu;

    /**
     * ���޸����������
     */
    private Long number;

    /**
     * ����������ţ�������Ȩ���ж�
     */
    private String countycode;

    /**
     * �������
     */
    private String gclb;

    /**
     * �������
     */
    private String zllb;

    /**
     * ��ҵ���
     */
    private String zynd;

    /**
     * �ƻ����
     */
    private String jhnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu == null ? null : statu.trim();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode == null ? null : countycode.trim();
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

    public String getZynd() {
        return zynd;
    }

    public void setZynd(String zynd) {
        this.zynd = zynd == null ? null : zynd.trim();
    }

    public String getJhnd() {
        return jhnd;
    }

    public void setJhnd(String jhnd) {
        this.jhnd = jhnd == null ? null : jhnd.trim();
    }
}