<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--  <link href="assets/css/bootstrap.min.css" rel="stylesheet" /> -->
<!--   <link rel="stylesheet" href="assets/css/font-awesome.min.css" /> -->
  <!-- <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css"> -->
<!--   <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/> -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" charset="utf-8" src="js/taskWorking.js"></script>
<title>xxx</title>
<style type="text/css">
.datagrid-body{
style:"width:625px"
}
span.searchbox{
margin-bottom:-6px;
}
.layout-split-west{/* panel layout-panel layout-panel-west  */
width:155px !important; 
}
.layout-panel-center{
left: 156px !important;
}



/*a  upload */
.a-upload {
    padding: 4px 10px;
    height: 20px;
    line-height: 20px;
    position: relative;
    cursor: pointer;
    color: #888;
    background: #fafafa;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1
}

.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.a-upload:hover {
    color: #444;
    background: #eee;
    border-color: #ccc;
    text-decoration: none
}

.bcbtn{
border-radius:4px;
color: gree;
}
.qxbtn{
border-radius:4px;
}
/* panel datagrid *//* datagrid-wrap panel-body panel-body-noheader panel-body-noborder */
.panel{001
}

li {
list-style-type: none;
}
#nav ul {
display: none
}
#nav li:hover ul{
display: block;
}
#hid{
display: none;
}
#pros li ul{
display: none;
}
#pros p{
margin:0px;
/* top: 2px; */
} 
#pros{
height:20px;
margin-bottom: 2px;
}
.ptl{
text-decoration: none;
color: #4169E1;
}


</style>
</head>

<body class="easyui-layout">
		<!-- <div class="easyui-layout" style=" width: 1310px;height: 550px;margin-top: -5px"> -->
		    <div data-options="region:'west',title:'',split:true,border:false" style="height: 550px;width:142px;margin-left: 5px;">
		    
			    <ul id="tt" class="easyui-tree" >
			    
			    </ul>
		    </div>
				
			<div id="ce" data-options="region:'center',title:'',border:false" style="padding:5px;background:#eee;">
				<table id="tab" style="display:none;height: 530px;"></table>
			</div>
    <!-- </div> -->
    
    <div id="tbs" style="height:28px;dispaly:none;  ">
       	<label class="" id="labelName" style="font-size: 12px;">年份:</label>
       	<input id=year value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:70px;;height:19px;margin-top: 0px">&nbsp;&nbsp;
    	
    	<label class="" id="labelName" style="font-size: 12px;">工程类别:</label>
		<input style="width: 100px" id="epc" class="easyui-combobox" name="dept" data-options="valueField:'mark',textField:'ename',url:'${pageContext.request.contextPath }/taskWorking/show_epcs'" />&nbsp;&nbsp;
			
       	<shiro:hasPermission name="sys:rwgzz:sh">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="auditFunction()">审核</a>
       	</shiro:hasPermission>
       	
       	<shiro:hasPermission name="sys:rwgzz:th">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="backFunction()">退回</a>
       	</shiro:hasPermission>
       	
       	<shiro:hasPermission name="sys:rwgzz:tj">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="submitFunction()">提交</a>
       	</shiro:hasPermission>
       	&nbsp;&nbsp;
   </div>
    <input type="hidden" id="pagex" />
	<input type="hidden" id="pagey" />
   
   <div id='tooltip' style="display: none;background-color: #F8F8F8;border-radius: 5px" onmouseout="tooltipOut()" onmouseover="tooltipOver()">
	 <table  width="100" border="0" style="border-color: gray;margin-left: 20px"><!-- //overflow: hidden; text-overflow: ellipsis; white-space: nowrap; -->
	     <tr><td id="tdBack" style="margin-left:2px;width: 50px;border-color:#ccc;"><a href="#" style="text-decoration:none;" onclick="beBack()"><font color="red">被退回</font></a></td></tr>
    	 <tr><td id="tdSubmit" style="margin-left:2px;width: 50px;border-color:#ccc;"><a href="#" style="text-decoration:none;" onclick="beSubmit()"><font color="red">待提交</font></a></td></tr>
    	 <tr><td id="tdAudit" style="margin-left:2px;width: 50px;border-color:#ccc;"><a href="#" style="text-decoration:none;" onclick="beAudit()"><font color="red">待审核</font></a></td></tr>
	 </table>
	</div>
    
    <script type="text/javascript">
    
    </script>
   
    <!-- 详情弹出窗口 -->
    <div id="opt" class="easyui-window" title="操作详情" style="width:1052px;height:400px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
		
		<table id="optab"></table>

	</div>

    <!-- 上传窗口 -->
    <div id="upt" class="easyui-window" title="上传窗口" style="width:850px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true,closable:false,resizable:false"> 
		
		<p style="margin-top: 20px;margin-left: 45px;position: absolute;">情况说明:</p>
		<textarea rows="7" cols="100" id="tet" style="margin-top: 20px;margin-left: 100px"></textarea><br><br>
		<!-- <a href="javascript:;" class="file">选择文件 -->
		<a href="javascript:;" class="a-upload" style="margin-left: 100px;margin-top: 10px"><!-- //style="margin-left: 100px; -->
		<input id="fileupload" type="file" name="fileName" data-url="${pageContext.request.contextPath}/taskWorking/upFile" >点击这里上传文件
		</a><span id="spaninfo"></span>
		
		<br>
		
		<table style="margin-left: 100px;" border="1"  bordercolor=	#FFFFFF  cellspacing="0" cellpadding="0">
			<thead >
				<tr>
					<th width="351px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray;"><font color="gray">文件名</font></th>
					<th width="300px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray"><font color="gray">操作</font></th>
					<th width="100px" align="center" style="display: none;border-color:gray">id</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
		
		<br><br>

		
		<input style="margin-left: 340px; margin-top: 50px; " type="button" value="确定" onclick="save()" class="bcbtn"> 
		<input style="margin-left: 70px" type="button" value="取消" onclick="cancel()" class="qxbtn">
	</div>
	
	<div id="proceed" class="easyui-window" title="事项窗口" style="width:380px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true,closable:false,resizable:false"> 
		
		<table id="ptab" style="margin-left: 100px;width: 365px;height: 300px">
			
		</table>
		
		<input style="margin-left: 90px; margin-top: 50px; " type="button" value="确定" onclick="save()" class="bcbtn"> 
		<input style="margin-left: 70px" type="button" value="取消" onclick="cancelProceed()" class="qxbtn">
	</div>
</body>
</html>
