package com.yzl.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlRole {
    /**
     * ��ɫ���/��ɫ��
     */
    private Integer id;

    /**
     * ��ɫ����
     */
    private String name;

    /**
     * �ؼ���
     */
    private String code;

    /**
     * ��ɫ����
     */
    private String description;

    /**
     * ����ʱ��
     */
    private Date createTine;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTine() {
        return createTine;
    }

    public void setCreateTine(Date createTine) {
        this.createTine = createTine;
    }
}