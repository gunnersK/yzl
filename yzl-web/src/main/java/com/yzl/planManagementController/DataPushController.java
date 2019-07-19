package com.yzl.planManagementController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yzl.planManagementService.DataPushService;
import com.yzl.utils.attr;
import com.yzl.utils.attrs;

@Controller
public class DataPushController {

	@Autowired
	private DataPushService dataPushService;
	
	@RequestMapping(value="/addDataPush")//,method=RequestMethod.POST
	@ResponseBody
	//taskProgress���� taskProgress  String taskProgress
	//dname����, epcname����, tname���̵��ֶΣ� ,time ʱ�� ��SBMJ�Լ��ϱ������ HSMJ��ʵ�����HGMJ��ʵ�ϸ������SJYLMJ������������HSYLMJ��ʵ�������
	public void push(String bsm) throws Exception {
		System.out.println(bsm);
		String sendGet = nanningSynch.sendGet("http://10.3.4.68/yzlmapapi/gis/GetProjectInfo", "prjId="+bsm+"&isReported=true&hasGeo=false");
		//System.out.println(sendGet);
		
		//attrs attrs = JSONObject.parseObject(sendGet, attrs.class);
		//String json = "{\"attr\":{\"GCLB\":\"ʯĮ������\",\"ZLLB\":\"��ɽ����\",\"COUNTY\":\"450122\",\"OBJECTID\":\"16\",\"Shape\":\"System.__ComObject\",\"CITY\":\"4501\",\"COUNTY\":\"450122\",\"TOWN\":\"450122334\",\"XIAO_BAN\":1231,\"GCLB\":\"1\",\"ZLLB\":\"20\",\"JHND\":\"2017\",\"ZYND\":\"2018\",\"ZLQ_DILEI\":\"161\",\"DILEI\":\"120\",\"XTJSBMJ\":123123,\"BZ1\":\"3\",\"BZ2\":\"2\",\"BZ3\":\"23\",\"Shape_Length\":0.114845856659622,\"Shape_Area\":0.000657047113567022,\"BSM\":\"Y825E24EFB1DB49BE82C398ECE093458\"}}";
		attrs attrs=JSONObject.parseObject(sendGet,attrs.class);
		System.out.println(attrs.toString());
		dataPushService.push(attrs);
	}
}
