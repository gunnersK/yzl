<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
   <%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%--  <%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %>  --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <!-- <link rel="stylesheet" href="assets/css/font-awesome.min.css" /> -->
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
 
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script><%-- 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/file-download.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.fileDownload.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/javascript" src="js/taskIssued.js"></script>
<title>xxx</title>
<style type="text/css">
/* .datagrid-body{
	width: 1045px !important;
} */
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
/* .layout-body{
width: 143px !important;
} */
/* .panel-body-noheader{
width: 143px !important;
} */
#iframe{
	white-space: nowrap;
}
.tree{
	overflow-y: hidden;

}
 
table.uploadFileTablle {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.uploadFileTablle th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.uploadFileTablle td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}

/* #labelName{
position: absolute;
margin-left:150px;
margin-top:35px; -3 
z-index: 1000
} */
.zl{/* //.combo */
	position: absolute;
	margin-left:210px;
	margin-top:33px;/* -6px */
	height:18px !important;
	z-index: 1000
}


#autore{
	position: absolute;
	margin-left:225px;
	margin-top:10px;
	z-index: 1000
}
.label_name{
	position: absolute;
	margin-left:5px;
	margin-top:-9px;
	z-index: 1000
}
/* #year{
position: absolute;
margin-left:10px;
margin-top:-10px;
z-index: 1000
} */
.button-style{
	border-radius:4px;
	color: gree;
}
.spinner{
width:68px !important;
height:22px;
position: relative;
margin-left:2px;
margin-right:10px;
z-index: 100
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



/* //提示条的样式 */
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 28px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
        border-right-color: rgb(254, 182, 84);
        border-right-style: solid;
        border-right-width: 1px;
        border-left-color: rgb(254, 182, 84);
        border-left-style: solid;
        border-left-width: 1px;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}
/* body {
    margin: 0px;
    padding: 0px;
} */
.top_box {
    line-height: 26px;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips .icon_tip1 {
    background-position: 0 -4px;
    height: 30px;
    margin: 0px 10px 0 10px;
    width: 18px;

/*     background-position: 0 -26px;
    height: 30px;
    margin: 3px 10px 0 10px;
    width: 15px; */
}
.top_tips a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
}
a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
    blr: expression(this.onFocus=this.blur());
    outline: none;
}
.left {
    float: left;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
        background-position-x: 0px;
        background-position-y: 0px;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
}
.right {
    float: right;
}
.top_tips .icon_close {
    background-position: 0 -26px;
    height: 28px;
    margin: 3px 10px 0 10px;
    width: 15px;
    text-indent: -999em;
}
body {
    font-family: Verdana,Arial,simsun,sans-serif,"Microsoft YaHei",Mingliu,Verdana,Helvetica,Lucida;
    font-size: 12px;
    color: #333;
}
.top_box {
    margin: 0 auto;
    width: 300px;
    line-height: 21px;
    overflow: hidden;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 20px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}
.addTaskToolbar{
	margin-left: 20px;
}
.defaultButton{
	text-align: center;
	background-color: #ecf4ff;
	height:23px;
	width:100px;
	border:solid 1px #ccc;
	border-radius:3px;
	margin:0;
}
.defaultButton:hover{
	background-color: #cddaef;
}
.defaultTextInput{
	position:relative;
	top:2px;
}
.addFilePath{
	width: 100px;
	height:23px;
	display:block;
	background-color: #ecf4ff;
	text-align:center;
	bottom:18px;
	border-radius:3px;
	border: solid 1px #95b8e7;
}
.addFileInput{
	width: 100px;
	height:23px;
	position: relative;
	bottom:23px;
	opacity:0;
	border: solid 1px black;
}
.addToolBarDiv{	
	float:left;
	height:80px;
	width: 25%;
}

</style>

</head>

<body class="easyui-layout" >


		<!-- 左边的树 -->
		<div data-options="region:'west',title:'',split:true,border:false" style="width:140px;margin-left: 0px;">
		    <ul id="tree" class="easyui-tree">
		    
		    </ul>
	    </div> 

	    <!-- 右边的表格 -->
		<div id="DataTemplateDiv" data-options="region:'center',title:'',border:false" style="padding:2px 20px 25px 0px;background:#eee;">
 		<%-- <c:if test="${sessionScope.updateDataNumber > 0}">
			<div class="top_tips" id="toptips">
				<div class="top_box">
					<div style="display: block;" class="top_news">
						<div class="icons icon_tip1 left"></div>
						<div  id="hint" class="left">
								<a target="_blank"  onClick='topRead();' href="javascript:void(0);">温馨提示：当前有<font style="color:red;">${sessionScope.updateDataNumber}条</font>下发任务已更新</a>
						</div>
						<a href="javascript:void(0);" title="关闭提示，不再提醒" id="read" class="icons icon_close right">关闭提示，不再提醒</a>
					</div>
				</div>
			</div>
		</c:if> --%>
					<table  id='DataTemplateTable' style="displae:none;"></table>
		</div>
	<div id="addTaskDialog" class="easyui-dialog">
		<div class="topDistanse"></div>
		<div style="width:97%; height:80px; padding-left:3%;">
			<form action="#" id="AddTaskForm">
				<div class="addToolBarDiv" style="width: 25%;">
      				<div>
      					<%-- 年份:&nbsp;&nbsp;<input id="addYear" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"style="width:90px;height:21px;"> --%>
      					年份:&nbsp;&nbsp;<input id="addYear" readonly="readonly" class="defaultTextInput">
      				</div>
      				<div class="bottomDistance"></div>
					<div>
						计划:&nbsp;&nbsp;<input id="addJh" class="defaultTextInput" placeholder="输入整数或小数" onkeyup="value=value.replace(/[^\d.]/g,'')" />
					</div>
				</div>	
				<div class="addToolBarDiv" style="width: 49%;">
					<div>
						工程类别:&nbsp;&nbsp;<input id="addGclb" class="easyui-combobox" style="width: 250px" name="project" />&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div class="bottomDistance"></div>
	 				<div>
	 					造林类别:&nbsp;&nbsp;<input id="addZllb" class="easyui-combobox" name="dept" style="width: 250px" /> &nbsp;&nbsp; 
					</div>
				</div>
				<div class="addToolBarDiv" style="width: 25%;">
					<div style="width: 100px;height:23px;"> 
						<input type="button" class="defaultButton" value="上传文件">
						<input type="file" multiple="multiple" id="addFileInput" name="uploadFile" class="addFileInput" onchange="">
					</div>
      				<div class="bottomDistance"></div>
					<div>
						<input type="button" value="添加" id="addTaskBtn"	 class="defaultButton">
					</div>
				</div>
			</form>
		</div>
		<div class="bottomDistance"></div>
		<div style="height:350px; width:585px; float: left;">
			<table id="addTaskTable"></table>
		</div>
		<div style="height:350px; width:200px; ">
			<table id="addTaskFiles"></table>
		</div>
	</div>
     <div id="toolbar" style="height:50px;dispaly:none">
    	<label style="padding-left:23px;margin-top: 26px;font-size: 12px" class="">年份:</label>
       	<input id="year" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:90px;;height:21px;margin-top: 0px">
       	
    	<label style="font-size: 12px;" class="" id="labelName">工程类别:</label>
    	<!-- <label style="" class="label_name">工程类别:</label> -->
		<input id="gclb" class="easyui-combobox" style="width: 100px" name="project"/>&nbsp;&nbsp;
       	
    	<label style="font-size: 12px;margin-left: 5px;" class="" id="labelName">造林类别:</label> 
		<input id="zllb" class="easyui-combobox" name="dept" style="width: 100px" /> &nbsp;&nbsp;	
    	
       	<!-- <input id=searchKey value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: 20px"> -->
       	<shiro:hasPermission name="sys:rwxf:tj">
       		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a> -->
       		<a class="easyui-linkbutton" id="addBtn" data-options="iconCls:'icon-add',plain:true">添加</a>
       	</shiro:hasPermission>
       	
    	<shiro:hasPermission name="sys:rwxf:bc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>    
    	</shiro:hasPermission>
    	
       	<shiro:hasPermission name="sys:rwxf:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="modify()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="remove()">删除</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:qxbj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="cancelEdit()">取消</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:dc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="derive()">导出</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:wndr">
    	 <!-- id="dataDr" --><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="ddd(this)" >往年数据导入</a>
    	</shiro:hasPermission>
    	
		<%-- <shiro:hasPermission name="sys:rwxf:dr">
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="toLead()">导入</a><!-- //onclick="toLead()"id="btn" -->
		</shiro:hasPermission> --%>
		
   </div>
  <input type="file" name="excelName" id="file" onchange="dataDrs()" style="display:none"><!-- onchange="fileUpload()" -->
  <input type="file" name="excelNameing" id="fileing" onchange="dataDrsing()" onclick="dataclick()" style="display:none">
  <!-- <div id="p" class="easyui-progressbar" style="width:400px;"></div> -->
  
   <!-- 文件上传窗口 -->
   <!-- 文件上传窗口 -->
    <div id="uploadFileDiv" class="easyui-window" title="上传窗口" style="margin-top:0px;width:850px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>
			        <div id="uploadFileButton">
	<!-- 						<input multiple="multiple"  type="file" id="uploadFile"  name="uploadFile" class="easyui-linkbutton picFileUpload"></input>
						<span id="spaninfo"></span> -->
						<a id="uploadFileAlabel" href="javascript:void(0);" class="a-upload" style="margin-left: 15%;margin-top: 10px;"><!-- //style="margin-left: 100px; -->
						<input id="uploadFile" multiple="multiple" type="file" name="uploadFile" class="easyui-linkbutton picFileUpload">点击这里上传文件
						</a><span id="spaninfo"></span>
					</div>   
			<div class="pics"  style="margin-top:3px;margin-left:15%;text-align:center;">
				<br/>
  				<table class="uploadFileTablle" border="1">
					<tr>
						<th width="351px" cellspacing="0" cellpadding="0" style="text-align:center;border-color:gray;"><font  color="gray">文件名</font></th>
						<th width="300px"  cellspacing="0" cellpadding="0" style="text-align:center;border-color:gray"><font  color="gray">操作</font></th>				   
				    </tr>
			    </table>
			</div>
	    </table>
	    
	    <input type="hidden" name="itemParams"/>
	</form>
	<input style="margin-left:46%;margin-top:25%;mapadding:5px" type="button" value="关闭" onclick="closedForm()" class="button-style"> 

</div>

<div id="mask" class="datagrid-mask" style="display:none;width: 100%"></div>
<div id= "msg" class="datagrid-mask-msg" style="display:none;"></div>
</body>

</html>
