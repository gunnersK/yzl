package com.yzl.utils.vo;

import java.util.List;

import com.yzl.pojo.YzlMenu;

public class TreeVO {

	
	private String id;
    /**
     * ��Ȩ�ޱ��
     */
    private String pid;
    /**
     * ·��
     */
	private String url;
	/***
	 * �Ƿ��ǲ˵�
	 */
	private String generatemenu;
	/**
	 * ͼƬ
	 */
	private String icon;
	/***
	 * ����
	 */
	private String name;
	/***
	 * �ӽڵ�
	 */
	List<YzlMenu> children;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<YzlMenu> getChildren() {
		return children;
	}
	public void setChildren(List<YzlMenu> children) {
		this.children = children;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
