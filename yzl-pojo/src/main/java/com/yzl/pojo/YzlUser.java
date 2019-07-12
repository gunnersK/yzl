package com.yzl.pojo;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-03-13
 */
public class YzlUser {
    /**
     * �û����/�û���
     */
    private Long id;

    /**
     * ��¼����
     */
    private String username;

    /**
     * ����
     */
    private String password;

    /**
     * ������λ
     */
    private String work;

    /**
     * ����
     */
    private String email;

    /**
     * �Ա�
     */
    private String sex;

    /**
     * ����
     */
    private String name;

    /**
     * �绰
     */
    private String telephone;

    /**
     * ״̬0����1����
     */
    private String status;

    /**
     * ������id
     */
    private Integer createUserId;

    /**
     * ����ʱ��
     */
    private Date createTime;

    /**
     * ��ע
     */
    private String remark;

    /**
     * �ؼ���
     */
    private String keywork;
    
    private String createTimes;
    
    private List<Integer> roleIdList;
    
    
    public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(String keywork) {
        this.keywork = keywork == null ? null : keywork.trim();
    }

	@Override
	public String toString() {
		return "YzlUser [id=" + id + ", username=" + username + ", password=" + password + ", work=" + work + ", email="
				+ email + ", sex=" + sex + ", name=" + name + ", telephone=" + telephone + ", status=" + status
				+ ", createUserId=" + createUserId + ", createTime=" + createTime + ", remark=" + remark + ", keywork="
				+ keywork + ", createTimes=" + createTimes + ", roleIdList=" + roleIdList + "]";
	}
    
}