package com.yzl.pojo;

import java.util.List;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlMenu {
    /**
     * ���/�˵���
     */
    private Long menuId;

    /**
     * ��Ȩ�ޱ��
     */
    private String pid;

    /**
     * �˵�����
     */
    private String name;

    /**
     * ��Ȩ��(����ö��ŷָ����磺user:list,user:create)'
     */
    private String perms;

    /**
     * ��˵���
     */
    private String description;

    /**
     * �˵�url
     */
    private String url;

    /**
     * �Ƿ����ɲ˵�
     */
    private String generatemenu;

    /**
     * ����
     */
    private Integer orderNum;
    

    
    
    private List<YzlMenu> children;
    
    public Long getId(){
    	return menuId;
    }
    
    public List<YzlMenu> getChildren() {
		return children;
	}

	public void setChildren(List<YzlMenu> children) {
		this.children = children;
	}

	private String icon;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getGeneratemenu() {
        return generatemenu;
    }

    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu == null ? null : generatemenu.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}