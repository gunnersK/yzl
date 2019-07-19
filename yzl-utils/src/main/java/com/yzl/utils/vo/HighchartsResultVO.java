package com.yzl.utils.vo;

import java.util.List;
import java.util.Map;

public class HighchartsResultVO {
	
	//��������
	private String name;
	//����������ɱ���
	private Double y;
	//�ӽڵ�name��  �ӽڵ��id��Ӧ
	private String drilldown;
	
	//�ӽڵ�
	private DrilldownNode drilldownNode;
	
	public String getDrilldown() {
		return drilldown;
	}
	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}



	
	public DrilldownNode getDrilldownNode() {
		return drilldownNode;
	}
	public void setDrilldownNode(DrilldownNode drilldownNode) {
		this.drilldownNode = drilldownNode;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "HighchartsResultVO [name=" + name + ", y=" + y + ", drilldown=" + drilldown + ", drilldownNode="
				+ drilldownNode + "]";
	}



	
	
	
}
