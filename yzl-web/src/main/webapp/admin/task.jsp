<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/additional-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
<style type="text/css">
.button1 {
    background-color: #4CAF50; /* Green */
    color: white;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}

	.button2 {
    background-color: red; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}
</style>
<script type="text/javascript">
	// 工具栏
	var toolbar = '#tb';
	/* //定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}, {
		field : 'username',
		title : '名称',
		width : 80,
		rowspan : 2
	} ] ]; */


	// 定义工程id
	var columns = [ [ {
		field : 'tcode',
		checkbox : true,
		rowspan : 2,
		width:100
	}, {
		field : 'tname',
		title : '任务名',
		width : 300,
		rowspan : 2,
		align : 'center'
	}, ] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [10,20,30,40],
			pagination : true,
			toolbar : toolbar,
			url : "/show_task",
			idField : 'ecode', 
			/* frozenColumns : frozenColumns, */
			columns : columns,
			//onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
			//onSelect:selectOne
		});
		
		$("body").css({visibility:"visible"});
		
	});
	
	//添加
    function add(){
			location.href='${pageContext.request.contextPath}/admin/task_add.jsp';
		}
	
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);
		doView();
	}
	
	// 单击
	/* function onClickRow(rowIndex){

	} */
	
	/* function doAdd() {
		$('#addTaskDialog').dialog('open');	
	} */
/* 	$("#addBtn").click(function(){
		/if(districtCode.length < 6){
			$.messager.alert('提示','选择市级以下的县区才能添加任务!','warning');
		}
		else{
			$("#addYear").val($("#year").val());
			$('#addTaskDialog').dialog('open');	
		} 
		 $('#addTaskDialog').dialog('open');	 
	}); */

	var mak="";
	function doView() {
		//alert("编辑用户");
		
		var item = $('#grid').datagrid('getSelected');
		var items = $('#grid').datagrid('getSelections');
		
		if(items==0 || items.length>1){
			$.messager.alert('错误提示','<h1>请选择一个!!!</h1>','error');
			return;
		}
		//装用户拥有角色id
		var userIds=item.roleIdList;
		mak = item.password;
		item.password="";
		/* 数据回显 */
		$("#editForm").form('load',item);
		$("#editWindow").window('open');
		if(userIds != null && userIds.length > 0 ){
			for(var i=0;i<userIds.length;i++){
				var rid=userIds[i];
				var c= $('td>#'+rid);
				c.checked=true;
				var ss = document.getElementById(rid);
				ss.checked=true;
				//console.log(ss);
			}
		}
	}

	function deldata(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length>0){
			$.messager.confirm('提示','你确定要删除这'+ rows.length +'条数据吗？',function(r){
				if(r){
					var array = new Array();
					for(var i=0;i<rows.length;i++){
						//获取所有被选行的id
						var id = rows[i].tcode;
						array.push(id);
					}
					//把所有id拼起来，用","隔开
					var ids = array.join(",");
					$.post("${pageContext.request.contextPath}/deleterTask",{"ids":ids},function(){
						$.messager.alert('提示','<h1>删除成功</h1>','warning');
						location.href='${pageContext.request.contextPath}/admin/task.jsp';
					});
				//location.href='${pageContext.request.contextPath}/function/delete';
				}
		   });
		}else{
			$.messager.alert('提示','<h1>请最少选择一行进行删除!</h1>','warning');
		}
	}
	
	function doDelete() {
		
		var ids = [];
		var rows = $('#grid').datagrid('getSelections');
		
		
		if(rows.length>0){
			$.messager.confirm('失效提示','确定选定的数据?',function(data){
				if(data){
					toPrompt("失效提示","某某条数据失效成功");
					var ids = [];												// 用来接收每行的id
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].ecode);									// 将选定的行的id加入到数组中
					}
																				// 获得的数据是一个数组所在转换成用逗号隔开的字符串
					var transID=ids.join(',');
					var params = {"ids":transID};
					alert(transID);															// 进行后台数据交互
					$.post("/deleteEpc",params, function(data){
	        			if(data.status == 200){
	        				$("#grid").datagrid('load');						// 调用该方法刷新当前页
	    					$("#grid").datagrid('unselectAll');					//删除完成后取消所有选定,防止有不明问题出现	
	        				toPrompt("删除提示","删除成功");
	        			}else if(data.status == 402)
	        			{
	        				toPrompt("删除提示","删除异常");
	        				}
	        		});	
					/* $.ajax({
					    url:'/deleteEpc', 
						type:'post',
						async:false,
						traditional: true,
						data:{"ids":ids}
					});		 */
					
										// 删除完成后取消所有选定,防止有不明问题出现	
				}
		});
		}else{
			$.messager.alert("温馨提示","请选中需要删除的列",'warning');
		}
	}
	/* 	 for(var i=0; i<items.length; i++){
		    ids.push(items[i].edcode);
	/* 	    if(items[i].username=="mark"){
				$.messager.alert("提示框","mark为系统用户,不能删除!!",'info');
				return;
			} 
		}  */
		
		
/* 		$.ajax({
		    url:'/deleteEpc', 
			type:'post',
			async:false,
			traditional: true,
			data:{"ids":ids}
		});		
		$('#grid').datagrid('reload');
		$('#grid').datagrid('uncheckAll');
	}
	 */
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
	<div id="tb">
     	<%-- 
    	<shiro:hasPermission name="sys:yhgl:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doView()">修改</a>
    	</shiro:hasPermission> --%>
    	
    	<shiro:hasPermission name="sys:yhgl:xz">
    		<a class="easyui-linkbutton" id="addBtn" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加任务</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:yhgl:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deldata()">删除任务</a>
    	</shiro:hasPermission>	
    </div>
    
    
	
</body>
</html>