<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		// 点击保存
		$('#save').click(function(){
			var r = $("#enginneeringManage").form("validate");
			if(r){
				$("#enginneeringManage").submit();
				/* $.messager.alert("提示", "增加成功！"); */
				 $(function () {
            		$.messager.alert("操作提示", "增加成功！");
            		location.href='${pageContext.request.contextPath}/admin/engineeringManage.jsp';
    			}); 
    		
				
			}
		});
		
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'north'">
	<div class="datagrid-toolbar">
		<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
	</div>
</div>
<div data-options="region:'center'">
	<form id="enginneeringManage" action="/addEpc" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr>
						<td>工程名称</td>
						<td><input type="text" name="ename" class="easyui-validatebox" data-options="required:true" /></td>
						
					</tr>
					</table>
			</form>
</div>
</body>
</html>