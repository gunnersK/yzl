package com.yzl.utils.vo;

import java.util.List;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yzl.utils.trees;

public class DistrictTreeVO {
	
	/***
	 * �ڵ�Id
	 */
	private String id;
	/***
	 * �ڵ�����
	 */
	private String text;
	/***
	 * ���ڵ��״̬   
	 */
	private String state = "closed"; //�ڵ�Ĭ���ǹرյ�
	/***
	 * �ӽڵ�
	 */
	private List<DistrictTreeVO> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		//�����Ҷ�ӽڵ㣬Ĭ��״̬�Ǵ� oepn
		if(CollectionUtils.isEmpty(children)){
			return "open";
		}
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<DistrictTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<DistrictTreeVO> children) {
		
		this.children = children;
		if(children!=null&&children.size()==1){
			state="open";//���ֻ��һ���ڵ㣬��Ϊ��״̬
			id=children.get(0).id;
		}
	}
	@Override
	public String toString() {
		return "DistrictTreeVO [id=" + id + ", text=" + text + ", state=" + state + ", children=" + children + "]";
	}
	
	
	
}
