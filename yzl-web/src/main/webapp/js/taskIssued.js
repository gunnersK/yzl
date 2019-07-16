$(function(){
	$("#addTaskDialog").dialog({
		width:800,
		height:550,
		title:'添加任务',
		iconCls:'icon-edit',
		closed:true,
		modal:true,
		buttons : [  
					{
					   text : '确定',
					   iconCls : 'icon-ok',
					   handler : function(){
						   addTimFn();
					   },
					},{
		        	   text : '取消',
		        	   iconCls : 'icon-cancel',
		        	   handler : function(){
		        		   $('#addTaskDialog').dialog('close');		
		        	   },
		           },{
		        	   text : '清空',
		        	   iconCls : 'icon-cut',
		        	   handler : function(){
		        		   $.messager.confirm('清空提示','确定清空所有输入框、任务和文件列表?',function(data){
		       				if(data){
		       					clearAddInput();		
		       				}
		       			});
		        	   },
		           }
		          ],
		 // 页面打开之后触发的事件--清空页面上次添加任务的信息
		 onOpen : function(){
			 clearAddInput();
		 }
	});
	
	
//	getCurrentDis();
	initWidgets();  
	
	$('#dataDr').fileupload({
        dataType: 'json',
        add: function (e, data) {
            data.context = $('<p/>').text('Uploading...').appendTo(document.body);
            data.submit();
        },
        done: function (e, data) {
            data.context.text('Upload finished.');
        }
    });

	/* $("#dataDr").on('click',function(){
		$("#file").trigger("click");
	}); */
	$.ajaxSettings.async=false;//同步
    //初始化树
    $("#tree").tree({
   		url:'/district/queryTreeNode',
   		animate:true,
   		onClick:TreeNodeEvent
   	});
		$("#uploadFileAlabel").hide();//隐藏上传文件按钮
	initHeader();//取表头数据
	initTables();//加载数据表格数据
	var record;
 	$("#DataTemplateTable").datagrid({ 
 		onClickRow: function(){
			if(record==1){
				$("#DataTemplateTable").datagrid('unselectAll');
			}
 		},
    	onClickCell: function(index,field,value){
    		record=0;
    		console.log("field="+field+" index="+index);
    		if(field!='filesUrl'){
    			return;
    		}else{
    			record=1;//记录操作状态
    			currentOnClickCellIdenx=index;//当前点击单元格索引值
				var _eleDiv = $("#uploadFileButton");
				_eleDiv.siblings(".pics").find("table").children().find("td").remove(); //清空显示上传的文件
//				alert(value);
				var begin = value.indexOf("value='[");
				var end = value.indexOf("]'");
				var filesStr = value.substring(begin,end);
				//去掉前缀
				var fileStr = filesStr.split("value='[")[1];
				var files = fileStr.split(",");
				//添加到数据库
    			var rows = $('#DataTemplateTable').datagrid('getRows'); 
    			var row = rows[currentOnClickCellIdenx];//获取当前上传文件的行
				var taskNumbers = new Array();
	    		//var existFiles = row["fileName"];//获取当前点击行所有文件
				var year = $("#year").val();//获取年份
				var countyCode = row["county"];//获取县级行政编号
				var cityName = row["city"];//获取县级行政编号
				
				if($.trim(files).length>0){//判断是否有文件
					for(var i=0;i<files.length;i++){
						//文件显示拼接	
						var fileNameAndFileUrl = files[i].split("==");
						_eleDiv.siblings(".pics").find("table").append('\
							<tr><td style="border-color:gray;" align="center" ><a href="javascript:void(0);" class="download_uploadFile"  target="_blank">'+ fileNameAndFileUrl[1] +'</a>\
								<input name="fileUrl" value="'+ files[i] +'"  type="hidden"></input></td>\
									<td align="center" style="border-color:gray;"><input class="delete_uploadFile  button-style" type="button" value="删除"/></td></tr>');//文件删除按钮
									
									var node =$("#tree").tree('getSelected');
									if(node==null||node.id=='45'){
										$(".delete_uploadFile").hide();
									}
						//删除文件事件
						$(".delete_uploadFile").each(function(i,e){
							$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
							$(e).click(function(){
								var deleteFileName = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
								var rows = $('#DataTemplateTable').datagrid('getRows');    
								var row = rows[i];    // your row data
								for(var key in row){
									console.log("key="+key+" :value="+row[key]);
								}
								$(e).parent().parent().remove();//删除当前页面的元素
								//获取删除后，所有还存在的文件
							    var _eleDiv = $("#uploadFileButton");
								var children = _eleDiv.siblings(".pics").find("table").children();
								var uploadFileUrlArray = [];
								for(var i =0;i<children.length;i++){
									uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
								}
								var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
								$.post("/taskIssued/delete/uploadFile",{"deleteFileName":deleteFileName,"year":year,"cityName":cityName,"countyCode":countyCode,"uploadFiles":uploadFiles},function(AjaxResult){
									if(AjaxResult.status==200){
										$("#DataTemplateTable").datagrid('reload');
									}else{
										$.messager.alert('提示',AjaxResult.msg);
									}
								}); 
							});
						});
						//绑定下载事件
						$(".download_uploadFile").each(function(i,e){//绑定删除事件
							$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
							$(e).click(function(){
								var fileName = $(e).val();	
								var fileUrl = $(this).siblings("input[name='fileUrl']").val();//获取文件夹根路径
								//文件下载请求
//								alert(fileUrl);
//								window.location.href="/uploadFile/"+fileUrl;
								window.location.href="/taskIssued/download/uploadFile?fileUrl="+fileUrl;
							});
						});
					}
				}
				$("#uploadFileDiv").window('open');
			}
    	}
	});
	var year = $("#year").val();
   	var root =$("#tree").tree('getRoot');//获取顶级父节点
   	var targetNode=root.id;//默认为父节点id
   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
   	if(node!=null&&node.id != null){
   		targetNode = node.id;
   	}
 	$('#zllb,#addZllb').combobox({    
 	    url:'/task/queryAll?year='+year+"&areaCode="+targetNode,    
 	    valueField:'mark',    
 	    textField:'tname',
 	    editable:false
 	});   
 	
 	$('#gclb,#addGclb').combobox({    
 	    url:'/epc/queryAll?year='+year+"&areaCode="+targetNode,    
 	    valueField:'mark',    
 	    textField:'ename',
 	    editable:false
 	});  
 	
	 
		//文件上传 
	$('#uploadFile').fileupload({
		dataType: 'json',//返回的格式
		url:encodeURI('/taskIssued/file/upload'),
		done: function (e, data) { //上传结束的操作 
			//var _ele = $("#uploadFile");
			if (data.result.status!=200) //上传失败时候 弹出消息
			{
				alert(data.result);
			}
			else	
			{
				alert(data.result.url);
				//添加到数据库
    			var rows = $('#DataTemplateTable').datagrid('getRows'); 
    			var row = rows[currentOnClickCellIdenx];//获取当前上传文件的行
				var taskNumbers = new Array();
	    		//var existFiles = row["fileName"];//获取当前点击行所有文件
				var year = $("#year").val();//获取年份
				var countyCode = row["county"];//获取县级行政编号
				//文件上传成功，在页面进行回显
				var _eleDiv = $("#uploadFileButton");
				console.log("data="+data);
				console.log("data.result="+data.result);
				console.log("data.result.fileUrl="+data.result.fileUrl);
				console.log("data.result.fileName="+data.result.fileName);
					_eleDiv.siblings(".pics").find("table").append('\
							<tr><td  align="center" ><a href="javascript:void(0);" class="download_uploadFile"  target="_blank">'+data.result.fileName +'</a>\
							<input name="fileUrl" value="'+data.result.fileUrl+'"  type="hidden"></input></td>\
								<td  align="center" ><input class="delete_uploadFile button-style" type="button" value="删除"/></td></tr>');//文件删除按钮
				    var _eleDiv = $("#uploadFileButton");
					var children = _eleDiv.siblings(".pics").find("table").children();
					var uploadFileUrlArray = [];
					for(var i =0;i<children.length;i++){
						uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
					}
					var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
	    			// 数据库添加文件
 	   			   $.ajax({
					   //判断是添加还是修改，请求不同的url
						url:"/taskIssued/fileUpload/update",
						dataType: "json",
						type: "post",
						data: {"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式
						traditional: true,
						success: function (data) {
							if(data.status!=200){
								$.messager.alert("提示",data.msg,"warning");
							}
							$("#DataTemplateTable").datagrid('reload');  
	  					}
					}); 
					
					
					
					//删除文件事件
					$(".delete_uploadFile").each(function(i,e){
						$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
						$(e).click(function(){
							var deleteFileName = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
							var rows = $('#DataTemplateTable').datagrid('getRows');    
							var row = rows[0];    // your row data
		    				var cityName = row["city"];//获取县级行政编号
		    				var countyCode = row["county"];//获取县级行政编号
							$(e).parent().parent().remove();//删除当前页面的元素
		    				var year = $("#year").val();//获取年份
							//获取删除后，所有还存在的文件
						    var _eleDiv = $("#uploadFileButton");
							var children = _eleDiv.siblings(".pics").find("table").children();
							var uploadFileUrlArray = [];
							for(var i =0;i<children.length;i++){
								uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
							}
							var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
							$.post("/taskIssued/delete/uploadFile",{"deleteFileName":deleteFileName,"year":year,"cityName":cityName,"countyCode":countyCode,"uploadFiles":uploadFiles},function(AjaxResult){
								if(AjaxResult.status==200){
									$("#DataTemplateTable").datagrid('reload');
								}else{
									$.messager.alert('提示',AjaxResult.msg);
								}
							}); 
						});
					});
					
				//绑定删除事件				
/* 					$(".delete_uploadFile").each(function(i,e){
						$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
						$(e).click(function(){
							var fileUrl = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
								$.post("/taskIssued/delete/uploadFile",{"fileUrl":fileUrl},function(AjaxResult){
								if(AjaxResult.status==200){
									$(e).parent().parent().remove();//删除当前页面的元素
									//获取所有已存在文件
								    var _eleDiv = $("#uploadFileButton");
									var children = _eleDiv.siblings(".pics").find("table").children();
									var uploadFileUrlArray = [];
									for(var i =0;i<children.length;i++){
										uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
									}
									var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
									//数据库删除文件
									$.post("/taskIssued/fileUpload/delete"
											,{"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},function(data){
				    							$("#DataTemplateTable").datagrid('reload');  
									}); 
								}else{
									$.messager.alert('提示',AjaxResult.msg);
								}
							}); 
						});
				}); */
				//绑定下载事件
				$(".download_uploadFile").each(function(i,e){//绑定删除事件
					alert();
					$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
					$(e).click(function(){
						var fileName = $(e).val();	
						var fileUrl = $(this).siblings("input[name='fileUrl']").val();//获取文件夹根路径
						//文件下载请求
						alert("/uploadFile/"+encodeURI(fileUrl));return;
						window.location.href="/uploadFile/"+encodeURI(fileUrl);
//						window.location.href="/taskIssued/download/uploadFile?fileUrl="+encodeURI(fileUrl);
					});
				});
			}
		},
 }); 
		
});


var node;
//当前所选地区代码
var districtCode = "";

//删除任务
function delTask(){
	$(this).css("display","inline");
	var index = $("#addTaskTable").datagrid('getRowIndex',$("#addTaskTable").datagrid('getSelected'));
	$("#addTaskTable").datagrid('deleteRow',index);
}
//删除文件
function delFile(){
	$(this).css("display","inline");
	var index = $("#addTaskFiles").datagrid('getRowIndex',$("#addTaskFiles").datagrid('getSelected'));
	$("#addTaskFiles").datagrid('deleteRow',index);
}

//初始化控件
function initWidgets(){
//点击年份的时候触发
	$("#year").numberspinner({
		"onChange":function(){
			ZLLB = $("#zllb").combobox('getValue');	
			//如果工程类别选项不为空 则按照工程类别对应方法进行初始化 表格
			initHeader();//取表头数据
			initTables();//加载数据表格数据
		}
	});
	
//选择 查看工程触发
	$("#zllb").combobox({
		onSelect:function(){
			initHeader();
			initTables();
		}
	});
	
//选择 查看工程触发
	$("#gclb").combobox({
		onSelect:function(){
			initHeader();
			initTables();
		}
	});
	
//添加按钮	
	$("#addBtn").click(function(){
		if(districtCode.length < 6){
			$.messager.alert('提示','选择市级以下的县区才能添加任务!','warning');
		}
		else{
			$("#addYear").val($("#year").val());
			$('#addTaskDialog').dialog('open');	
		}
	});	


//添加任务的表格
	$("#addTaskTable").datagrid({
		fit:true,
		nowrap:false,
		singleSelect:true,
		rownumbers:true,
		fitColumns:true,
		border : false,
		striped : true,
		columns:[[
		          {field:'addArea',title:'地区',align:'center',width:160},
		          {field:'addYear',title:'年份',align:'center',width:100},
		          {field:'addZllb',title:'造林类别',align:'center',width:200},
		          {field:'addGclb',title:'工程类别',align:'center',width:200},
		          {field:'addJh',title:'计划',align:'center',width:100,
		        	  styler:function(value,row,index){
		        		  return "border-right:0";
		        	  }
		          },	
		          {field:'addDel',title:'',align:'center',width:20,
		        	  formatter:function(value,row,index){
		        		  return "<a class='delTaskHref' onclick='delTask()' style='display:none;'><img src='images/del_icon.jpg' style='width:12px;height:12px;border-radius:6px;'></a>";
		        	  },
		        	  styler:function(value,row,index){
		        		  return "border-right:0";
		        	  }
		          }
		        ]],
		        onSelect:function(index,row){
		        	$("#addTaskTable").datagrid('unselectAll');
		        	var delArray = $(".delTaskHref");
		        	delArray.eq(index).css("display","inline");
		        },
		        onUnselectAll:function(index,row){
		        	var delArray = $(".delTaskHref");
		        	delArray.each(function(){
		        		$(this).css("display","none");
		        	});
		        }
		        
	});
//添加文件的表格	
	$("#addTaskFiles").datagrid({
		fit:true,
		nowrap:false,
//		singleSelect:true,
//		rownumbers:true,
		fitColumns:true,
		border : false,
		striped : true,
		columns:[[
		          {field:'fileName',title:'文件',align:'center',width:150,
		        	  styler:function(value,row,index){
		        		  return "border-right:0";
		        	  }
		          },
		          {field:'addDel',title:'',align:'center',width:15,
		        	  formatter:function(value,row,index){
		        		  return "<a class='delFileHref' onclick='delFile()' style='display:none;'><img src='images/del_icon.jpg' style='width:12px;height:12px;border-radius:6px;'></a>";
		        	  },
		        	  styler:function(value,row,index){
		        		  return "border-right:0";
		        	  }
		          }
		        ]],
		        onSelect:function(index,row){
		        	$("#addTaskFiles").datagrid('unselectAll');
		        	var delArray = $(".delFileHref");
		        	delArray.eq(index).css("display","inline");
		        },
		        onUnselectAll:function(index,row){
		        	var delArray = $(".delFileHref");
		        	delArray.each(function(){
		        		$(this).css("display","none");
		        	});
		        }
	});
//添加任务按钮	
	$("#addTaskBtn").click(function(){
		if(checkAddInput()){
			node = $("#tree").tree('getSelected');
			var parent = $('#tree').tree('getParent',node.target);
			var params = {"addArea":parent.text+"-"+node.text, 
					"addYear":$("#addYear").val(), 
					"addZllb":$("#addZllb").combobox('getText'), 
					"addGclb":$("#addGclb").combobox('getText'), 
					"addJh":$("#addJh").val(), 
					"addZllbMark":$("#addZllb").combobox('getValue'),
					"addGclbMark":$("#addGclb").combobox('getValue')};
//				"addUpFile":$("#addFileInput").val().substring(12)};
			$('#addTaskTable').datagrid('appendRow',params);
//		var row = $('#addTaskTable').datagrid('getRows');
//		alert(row[0].addZllbv);
		}
	});
	
//上传文件按钮	
	$('#addFileInput').fileupload({
		dataType: 'json',//返回的格式
		url:encodeURI('/taskIssued/file/upload'),
		done: function (e, data){
//			alert(data.result.status);
			if(data.result.status == 200){
				var params = {"fileUrl":data.result.fileUrl, 
						"fileName":data.result.fileName};
				$('#addTaskFiles').datagrid('appendRow',params);
			}
		}
	});
	
//检验特殊工程类别
	$("#addZllb").combobox({
		onSelect: function(){
			if($("#addZllb").combobox('getValue')>=28){
				$("#addGclb").combobox({disabled:true});
				$("#addGclb").mouseover(function(){
					alert();
				});
			} else{
				$("#addGclb").combobox({disabled:false});
			}
		}
		
	}); 
	
}

//添加任务之前的表单检验
function checkAddInput(){
	var addJh = $("#addJh").val().split(".");
	if($("#addZllb").combobox('getValue') == ""){
		$.messager.show({title:'提示',msg:"请选择造林类别",timeout:3000});
	} else if($("#addGclb").combobox('getText') == "" && $("#addZllb").combobox('getValue')<28){
		$.messager.show({title:'提示',msg:"请选择工程类别",timeout:3000});
	} else if($("#addJh").val() == ""){
		$.messager.show({title:'提示',msg:"请输入计划（整数或小数）",timeout:3000});
	} else if(addJh.length >= 2){
//		验证输入的小数是否合法，根据以下三点来判断
//		1.只要有小数点，addJh的长度最少有2
//		2.如果小数点在第一位，addJh[0]是空串，如果小数点在最后一位，addJh[1]是空串
//		3.整个字符串有超过一个如果小数点，则长度大于2
		if(addJh[0] == "" || addJh[1] == "" || addJh.length > 2){     
			$.messager.show({title:'提示',msg:"请输入格式正确的计划",timeout:3000});
		} else{
			return true;
		}
	} else {
		return true;
	}
}

//清空任务输入框以及任务列表
function clearAddInput(){
	$("#addZllb").combobox('setValue',""); 
	$("#addGclb").combobox('setValue',""); 
	$("#addJh").val("");
	$("#addFilePath").val("");
	clearTasks();
	clearFiles();
}

//获取当前所选地区
function getCurrentDis(){
	districtCode = $("#tree").tree('getSelected').id;
	if(!districtCode.length > 0){
		alert();
	}
}

//清除任务列表
function clearTasks(){
	var rows = $('#addTaskTable').datagrid('getRows');
	for(var i = rows.length-1; i >= 0; i--){
		$('#addTaskTable').datagrid('deleteRow',i);
	} 
}

//清除文件列表
function clearFiles(){
	var rows = $('#addTaskFiles').datagrid('getRows');
	for(var i = rows.length-1; i >= 0; i--){
		$('#addTaskFiles').datagrid('deleteRow',i);
	} 
}

//确认添加方法
function addTimFn(){
   var rows = $('#addTaskTable').datagrid('getRows');
   if(rows.length != 0){
	   $('#addTaskTable').datagrid('checkAll');
	   $('#addTaskFiles').datagrid('checkAll');
	   var fileRows = $('#addTaskFiles').datagrid('getSelections');
	   var uploadFileUrlArray = [];
	   for(var i = 0; i < fileRows.length; i++){
		   uploadFileUrlArray.push(fileRows[i].fileUrl);
	   }
	   var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
//				alert(uploadFiles);return;
	   var countyCode = districtCode;
	   var taskRows = $('#addTaskTable').datagrid('getSelections');
	   var year = $("#addYear").val();
	   var taskNumbers = [];
	   for(var i = 0; i < taskRows.length; i++){
		   var ZLLB = taskRows[i].addZllbMark;
		   var GCLB = taskRows[i].addGclbMark;
//		   alert(GCLB=="");return;
		   var jh = taskRows[i].addJh;
		   var taskNumber = "jh"+ZLLB+"Y"+GCLB+":"+jh;
		   taskNumbers.push(taskNumber);
//			        alert(taskNumbers);
	   }
	   
	   $.messager.confirm('提示','确定添加这些任务？',function(data){
		   if(data){
			   $.ajax({
				   url: "/taskIssued/add",
				   dataType: "json",
				   type: "post",
				   data: { "taskNumbers": taskNumbers ,"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式,"disCode":areaCode,"usr":usr
				   traditional: true,
				   success : function(data) {
					   if (data.status == 200) {							
						   initHeader();//取表头数据
						   initTables();//加载数据表格数据
					   }
					   return true;
				   },
				   error : function(data, XMLHttpRequest, textStatus,
						   errorThrown) {
					   alert('异常！');
					   return false;
				   }
			   });
			   $.messager.alert('提示','添加成功!','info');
			   $('#addTaskDialog').dialog('close');			
		   }
	   });
   } else{
	   $.messager.alert('提示','任务列表不能为空','warning');
   }
   //initTables();

	
					
// map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));					
	   /*$.ajax({
		   //判断是添加还是修改，请求不同的url
			url:"/taskIssued/fileUpload/update",
			dataType: "json",
			type: "post",
			data: {"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式
			traditional: true,
			success: function (data) {
				if(data.status!=200){
					$.messager.alert("提示",data.msg,"warning");
				}
				$("#DataTemplateTable").datagrid('reload');  
			}
		}); */
	//}
}




function ddd(){
	$("#file").trigger('click');
}
function dataDrs(){
	$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("<img  class ='img1' /> 数据导入中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	//创建formdata对象
	var formData = new FormData();
	//给formData对象添加<input>标签,注意与input标签的ID一致
	formData.append('excelName', $('#file')[0].files[0]);
	formData.append('year', $("#year").val());
	//$("#mask").css({display: 'block',height: $(window).height()});
	//$("#msg").html("加载中，请稍候。。。").css({display: 'block',left: ($(document.body).outerWidth(true) - 190) / 2,top: ($(window).height() - 45) / 2});
	
	//console.info(22222);
	//$.ajaxSettings.async=false;
	$.ajax({
		async:true,
		url:'/TaskIssued/toLead/dataDr',
		type : 'POST',
		data : formData,
		contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
		processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
		dataType: 'json',//这里是返回类型，一般是json,text等
		clearForm: true,//提交后是否清空表单数据
		success:function(data){
			$(".datagrid-mask").remove();
	        $(".datagrid-mask-msg").remove();
			//$.messager.progress('close');
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    		//$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
			
		},
		error:function(data){
			//alert("搓搓！");
		},
		beforeSend: function () {
	    },
	    complete: function () {
	    }
	});

}
//弹出加载层
function load() {
	console.info(1111111);
	 $("<div class='datagrid-mask'></div>").css({
	        display: 'block',
	        width: '100%',
	        height: $(window).height()
	    }).appendTo("body");
	    $("<div class='datagrid-mask-msg'></div>").html("加载中，请稍候。。。").appendTo("body").css({
	        display: "block",
	        left: ($(document.body).outerWidth(true) - 190) / 2,
	        top: ($(window).height() - 45) / 2
	    });
	    
	console.info(22222);
}

//取消加载层  
function disLoad() {
  $(".datagrid-mask").remove();
  $(".datagrid-mask-msg").remove();
}   
$("#zllb").combobox({
	onLoadSuccess:load
});
function load(){
 	var as = $("#zllb").next();
	as.attr("id","zl");
}

//当前登录的用户
var usr = "${sessionScope.user.username}";
var databtn = true;

function toLead(){
	$("#fileing").trigger('click');
}
function dataclick(){
	
}
function dataDrsing(){
	$("<div class=\"datagrid-mask\" id='123' value = '333'></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");
	$("<div class=\"datagrid-mask-msg\" id='345' value='777'></div>").html("<img  class ='img1' /> 正在运行，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	//创建formdata对象
	var formData = new FormData();
	//给formData对象添加<input>标签,注意与input标签的ID一致
	formData.append('excelNameing', $('#fileing')[0].files[0]);
	    
	//$.ajaxSettings.async=false;
	
	$.ajax({
		async:true,
		url:'/TaskIssued/toLead',
		type : 'POST',
		data : formData,
		contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
		processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
		dataType: 'json',//这里是返回类型，一般是json,text等
		clearForm: true,//提交后是否清空表单数据
		success:function(data){
			$(".datagrid-mask").remove();
	        $(".datagrid-mask-msg").remove();
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    		//$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
			
		},
	});
}

$("#btn").upload({
	action:'/TaskIssued/toLead',
	
	name:'excelName',
	onComplete: function(data) {
		
		if(databtn){
			databtn=false;
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		//$("#grid").datagrid("reload");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
		}
		
 },// 请求完成时 调用函数
}); 

function pre(){
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...'
		});
}

var dataDr = true;
/* $("#dataDr").upload({
	action:'/TaskIssued/toLead/dataDr',
	name:'excelName',
	onComplete: function(data) {
		
		console.info(data);
		if(dataDr){
			
			$.messager.progress('close');
			dataDr = false;
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
		}
		
 },// 请求完成时 调用函数
}); */





//$("#dataDr").prev("div").attr("id","test");
$("#dataDr").parent().prop("id","dat");
$("#dat").css({"margin-left":"730px"});
	$("#dat").css({"margin-top":"-25px"});
	$("#dat").css({"line-height":"15px"});
	$("#dat").css({"height":"20px"});
	$("#dat").css({"width":"105px"});
/* $("#dataDr").css({"margin-left":"100px"}); */
//if(par!=null){
///	console.info(par+"11111111");
//}
$("#btn").parent().prop("id","bnt");
$("#bnt").css({"margin-left":"838px"});
$("#bnt").css({"margin-top":"-25px"});
$("#bnt").css({"line-height":"15px"});
$("#bnt").css({"height":"50px"});
$("#bnt").css({"width":"78px"});

var areaCode = "45";
var ZLLB;
    //导出
    function derive(){
    	var node =$("#tree").tree('getSelected');//点击的行政编号
    	var year = $("#year").val();//获取时间
    	var click = null;
    	if(node != null){
    		areaCode = node.id;
    		click = node.id;
    	}else{
    		click = 45;
    	}
    	window.location.href="/derive?year="+year+'&click='+click+"&areaCode="+areaCode+"&ZLLB="+ZLLB;
    }
    

    
    var header = new Array();//存储表头数据
  	//存储添加固定表头的数据
	var frozenColumnsTab = new Array();
    var district="";
    //初始化表格表头
    function initHeader(){
    	//初始化存放表头数据的变量
    	frozenColumnsTab = new Array();//初始化固定表头的数据
    	$.ajaxSettings.async=false;
    	header=new Array();//初始化 存储表头数据
    	var columnsOneTab = new Array();	//一级表头数据
    	var columnsTowTab = new Array();	//二级表头数据
    	var columnsThreeTab = new Array();	//三级表头数据
       	//查询所有地区
       	$.ajaxSettings.async = false;
		$.post("/district/queryDistinctCity",function(data){//获取地区数据
  	        	if(data.status==200){
  	        		//获取地区数据
  	        		district=data.data;
  	        	}else{
  	        		$.messager.show({title:'警告',msg:'地区数据加载失败！',timeout:8000,});
  	        	}
		}); 
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var targetNode=root.id;//默认为父节点id
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	if(node!=null&&node.id != null){
	   		targetNode = node.id;
	   	}
	   	if(targetNode=='45'){
	   		//添加需要固定表头的数据
	   		frozenColumnsTab.push([	   		   

				{align:'center',width:100,title:'序号',  rowspan:'4',field:'id',checkbox:true},//frozen:true,
				{align:'center',width:100,title:'市名', rowspan:'4',	field:'city'},
			]);
			//添加固定表头
			columnsOneTab.push({align:'center',width:100,title:'文件',  'rowspan':3,	field:'filesUrl'});
	   	}else{
	   		//添加固定表头的数据
	   		frozenColumnsTab.push([
/* 	   			   		   		   {align:'center',width:12,title:'',  'rowspan':4,	field:'newData',formatter : function(data, row, index) {
		   			   					if(data!=''&&data!=null){
		   			   						$("#DataTemplateTable").datagrid("checkRow",index);
		   			   					}
		   			   					return data;
	   		   					   }}, */
	   		   					{align:'center',width:100,title:'序号',  rowspan:'3',field:'id',checkbox:true},//,frozen:true
	   							  {align:'center',width:100,title:'市名', 'rowspan':3,	field:'city',editor:{ type:'combobox', options:{
	   								data:district, valueField: "city", textField: "city",
	   								onSelect:function(data){
	   							    	//获取市名的文本值
	   									//设置县级联动
	   									var rowIndex=0;//当前编辑行索引,默认为0
	   									 //如果是添加获取第一行，行号c
	   									if(flag=='add'){
	   										rowIndex=0;
	   									}else{
	   										//是修改，获取当前选择行，行号
	   	                                 	var row = $('#DataTemplateTable').datagrid('getSelected');  
	   	                                	rowIndex = $('#DataTemplateTable').datagrid('getRowIndex',row);//获取行号  
	   									}
	   									var target = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
	   									
	   									//清空县级列表数据
	   									target.combobox('clear');
	   									//县级发送远程服务，根据市名查询所有县/区
	   									var url=encodeURI('/district/queryCountyByCityName?cityName='+data.city);
	   									//县级下拉框列表重载数据
	   									target.combobox('reload',url);
	   								}
	   							  }}},
	   					{align:'center',width:100,title:'县名',	 'rowspan':3,	field:'county',editor:{ type:'combobox', options:{
	   					data:district ,valueField: "anumber",textField: "county"
	   					}}},
	   		 ]);
			//添加表头
			columnsOneTab.push(  
				{align:'center',width:100,title:'文件',nowrap:false,  'rowspan':3,	field:'filesUrl'});
	   	}
	    var year = $("#year").val();//获取当前选取的年份
	    ZLLB = $("#zllb").combobox('getValue');//工程field
		var GCLB = $("#gclb").combobox('getValue');//工程field
		var zllbText = $("#zllb").combobox('getText');//获取工程名称
		if(ZLLB=="" || ZLLB==null){	
			$.post("/task/getTableHeader",{"year":year,"disCode":targetNode,"GCLB":GCLB},function(data){
				for(var i=0;i<data.length;i++){
					if(data[i].mark >= 28){
						columnsOneTab.push({field:'',title:data[i].tname,width:100,colspan:3,'rowspan':2,align:'center'});
						columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+"",title:'计划',width:115,align:'center',editor:{ type:'text', options:{}}},
								{field:'wc'+data[i].mark+"Y"+"",title:'完成',width:115,align:'center'},
								{field:'zjh'+data[i].mark+"Y"+"",title:'占计划%',width:115,align:'center'});
					}
					else{
						//拼接一级表头数据
						columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
						var epc = data[i].list;
						if(data[i].list.length > 0){
							for(var j=0;j<epc.length;j++){
								var ename = epc[j].ename;
								var field = epc[j].field;
								//拼接二级表头数据
								columnsTowTab.push({title:''+ename+'',width:115*3,align:'center',colspan:3});//field:''+field+'',
								//拼接三级表头数据
								columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:115,align:'center',editor:{ type:'text', options:{}}},
													 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:115,align:'center'},
													 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:115,align:'center'});
							}
						}
					}
				}
				header.push(columnsOneTab);
				header.push(columnsTowTab);
				header.push(columnsThreeTab);
			});
		}else{
			$.post("/epc/query",{"GCLB":GCLB,"ZLLB":ZLLB,"year":year,"disCode":targetNode},function(data){//查询所有工程
				if(data.length > 0){
					if(ZLLB >= 28){
						columnsOneTab.push({field:'',title:zllbText,width:100,colspan:3,'rowspan':2,align:'center'});
						columnsThreeTab.push({field:'jh'+ZLLB+"Y"+"",title:'计划',width:100,align:'center',editor:{ type:'text', options:{}}},
								{field:'wc'+ZLLB+"Y"+"",title:'完成',width:100,align:'center'},
								{field:'zjh'+ZLLB+"Y"+"",title:'占计划%',width:100,align:'center'});
					} else{
						//拼接一级表头数据
						columnsOneTab.push({field:'',title:zllbText,width:100*data.length,colspan:data.length*3,align:'center'});
						for(var i=0;i<data.length;i++){
							var ename = data[i].ename;//获取工程名称
							//拼接二级表头数据  
							var createTree = $("#zllb").combobox('getValue');
							//if(createTree!=30&&createTree!=31&&createTree!=28&&createTree!=29)
							if(createTree<28)
								//拼接二级表头数据
								columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
							//拼接三级表头数据
							columnsThreeTab.push({field:'jh'+ZLLB+"Y"+data[i].mark,title:'计划',width:100,align:'center',editor:{ type:'text', options:{}}},
									{field:'wc'+ZLLB+"Y"+data[i].mark,title:'完成',width:100,align:'center'},
									{field:'zjh'+ZLLB+"Y"+data[i].mark,title:'占计划%',width:100,align:'center'});
						}
					}
				}
			header.push(columnsOneTab);
			header.push(columnsTowTab);
			header.push(columnsThreeTab);
		    });
		}
	}

    var currentOnClickCellIdenx;//当前点击单元格的索引值
    //页面加载 初始化
   
    
  /*    function toLead(){
    	$("#btn").upload({
    		action:'/TaskIssued/toLead',
    		name:'excelName',
    	});
  }  */
  
  
      function Map() {
          var obj = {};
          this.put = function(key, value) {
              obj[key] = value;//把键值绑定到obj对象上
          }
          //size方法，获取Map容器的个数
          this.size = function() {
              var count = 0;
              for(var attr in obj) {
                  count++;
              }
              return count;
          }
          //get方法，根据key获取value的值
          this.get = function(key) {
              if(obj[key] || obj[key] === 0 || obj[key] === false) {
                  return obj[key];
              } else {
                  return null;
              }
          }
          //remove方法,删除方法
          this.remove = function(key) {
              if(obj[key] || obj[key] === 0 || obj[key] === false) {
                  delete obj[key];
              }
          }
          //each方法,遍历方法
          this.eachMap = function(callBack) {
              for(var attr in obj) {
                  callBack(attr, obj[attr]);
              }
          }
      }
  

   ///*  }); */

	//Tree节点点击事件
	function TreeNodeEvent(){
		$("#zllb").combobox('clear');//清楚工程列表
		$("#gclb").combobox('clear');//清楚工程列表
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	getCurrentDis();
	   	if(node==null||node.id=="45"){
	   		$("#uploadFileAlabel").hide();//隐藏文件上传标签
	   	}else{
	   		$("#uploadFileAlabel").show();//显示文件上传标签
	   	}
		initHeader();//取表头数据
		initTables();//加载数据表格数据
	}
	var updateData =new Map();//存储需要修改行，修改前的旧数据

	//初始化不指定工程的数据
	function initTables(){
		var node =$("#tree").tree('getSelected');//获取选择的节点
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var areaCode=root.id;//默认为父节点id
    	if(node!=null && node.id != null){
    		areaCode=node.id;	
    	}
		var GCLB = $("#gclb").combobox('getValue');//工程field
    	var ZLLB = $("#zllb").combobox('getValue');//获取当前选择的造林类别;
    	 ZLLB = $("#zllb").combobox('getValue');//获取当前选择的造林类别;
    	//页面加载初始化表格
    	var year = $("#year").val();
		$.ajaxSettings.async = false; 
		$("#DataTemplateTable").datagrid({
    		url:encodeURI('/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&GCLB="+GCLB),
    		//rownumbers:true,
			fit:true,
    		//url:'/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&usr="+usr,
    		nowrap:false,
    		rownumbers:true,
    		pagination:true,
    		pageList:[15,30,45,60],
    		pageSize:15,
    		//checkOnSelect:false,
    		fitColumns:false,
    		border : false,
			striped : true,
    		columns:header,
    		frozenColumns:frozenColumnsTab,
    		toolbar:'#toolbar',
    		//结束编辑 触发
	    	onAfterEdit:function(index , record){
	    		var updated = $("#DataTemplateTable").datagrid('getChanges','updated');
			    for(var onekey in updated){
					console.log("onekey="+onekey);		    	
			    }
	    		var _eleDiv = $("#uploadFileButton");
				var children = _eleDiv.siblings(".pics").find("table").children();
				var uploadFileUrlArray = [];
				for(var i =0;i<children.length;i++){
					uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
				}
				var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
				_eleDiv.siblings(".pics").find("table").children().find("td").remove();  //清空显示上传的文件
				var taskNumbers = new Array();
				//var EpcTaskList = new Array();
				var existFiles="";
				if(flag=='add'){//判斷是否是添加操作
				//判断数组是否有k和v的值
		    		for(var key in record){
						if(key=="fileName"){
							existFiles = record[key];//获取当前点击行已存在的文件
						}
						if(key.indexOf('jh')==0){//判断是否为jh字母开头的  true为任务下发数量的Key
							taskNumbers.push(key+":"+record[key]);//添加到任务基数的数值中
						}
						
					}
				}
				if(uploadFiles==null || uploadFiles==""){
					uploadFiles=existFiles;
				}
				var year = $("#year").val();
				var countyCode = record["county"];//获取县级行政编号
				var messageContent = $("#messageContent").val();
				if(flag!='add'){//判斷是否是修改操作
					taskNumbers = new Array();;
		    		for(var recordKey in record){
						if(recordKey=="fileName"){
							existFiles = record[recordKey];//获取当前点击行已存在的文件
						}
						if(recordKey.indexOf('jh')==-1){//判断是否为jh字母开头的  true为任务下发数量的Key 否則跳過此次循環
		    				continue;
		    			}
		    			updateData.eachMap(function(key ,value) {//遍历旧数据
							if(key==recordKey){//判断Key值是否想等
								var recordVal= record[recordKey];//获取改变行的新数据
								if(recordVal!=value){//旧数据的值和新数据的值不等于，说明数据被改变了
									taskNumbers.push(recordKey+":"+record[recordKey]);//添加到数据传入后台进行修改
								}
							}
						});
					}
				}
				// ajax请求
     			   $.ajax({
				   //判断是添加还是修改，请求不同的url
					url: flag=='add'?"/taskIssued/add":"/taskIssued/update",
					dataType: "json",
					type: "post",
					data: { "taskNumbers": taskNumbers ,"disCode":areaCode,"usr":usr,"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式,"disCode":areaCode,"usr":usr
					traditional: true,
					success: function (data) {
						if(data.status!=200){
							$.messager.alert("提示",data.msg,"warning");
						}
						$("#DataTemplateTable").datagrid('reload');  
  					}
				});    
	    	}, 
	    	
    	});
		//获取当前浏览器的类型
    	/* var userAgent = navigator.userAgent;
		//console.info(userAgent);
		if(node != null){
			if(userAgent.indexOf("Trident") > -1){//判断是否是ie浏览器
				$(".datagrid-body").css({"width":"1590px !important"});
			}else if(userAgent.indexOf("Firefox") > -1){
				$(".datagrid-body").css({"width":"848px"});
				console.info(userAgent);
			}
		} */
		
	}
	
	
	
	/*修改*/
	function modify(){
		  var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
		  var rowIndex=$('#DataTemplateTable').datagrid('getRowIndex',$('#DataTemplateTable').datagrid('getSelected'));
		  var rows=$('#DataTemplateTable').datagrid('getRows');
	  	  var row = rows[rowIndex];
	  	  for(var key in row){
	  		  if(key.indexOf("jh")!=-1){
	  			updateData.put(key,row[key]);
	  		  }
	  	  }
		  if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	      $.messager.alert("提示","选择市级以下地区才可以修改!",'warning');
	  	      return
	  	  }
		  var rows = $('#DataTemplateTable').datagrid('getSelections');
		  if(rows.length!=1){
		  	$.messager.alert('提示','只能选择一条数据进行修改','warning');
		  }else{
			  if(editing == undefined){
				  //获取要修改行索引
				  flag='edit';
			 	  editing = $('#DataTemplateTable').datagrid('getRowIndex',rows[0]);
			 	  $('#DataTemplateTable').datagrid('beginEdit',editing);
				  var cityEditor = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'city',type:'combobox'}).target;
				  var countyEditor = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
				  $(cityEditor).combobox('disable');//让市选择框失效
				  $(countyEditor).combobox('disable');//让县选择框失效
			  }
		 }
	}
	
//    //点击年份的时候触发
//    $("#year").numberspinner({
//    	"onChange":function(){
//    		alert();
//    		 ZLLB = $("#zllb").combobox('getValue');	
//    		//如果工程类别选项不为空 则按照工程类别对应方法进行初始化 表格
//    			initHeader();//取表头数据
//    			initTables();//加载数据表格数据
//    	}
//    });
//
//	//选择 查看工程触发
//    $("#zllb").combobox({
//    	onSelect:zllbchanged
//    });
//	
//	function zllbchanged(){
//		initHeader();
//		initTables();
//    }
//	
//	//选择 查看工程触发
//	$("#gclb").combobox({
//		onSelect:gclbchanged
//	});
//	function gclbchanged(){
//		alert()
//		initHeader();
//		initTables();
//	}
	
	$("#searchKey").searchbox({
  		 searcher:searched
	});
	
	function searched(searchKey){
		var url='/TaskWorking/pageQueryBySearchKey?searchKey='+searchKey;
	    $("#DataTemplateTable").datagrid({	
	    	url:url,
	    	fit:true,
	    	border : false,
			pagination:true,
			rownumbers:true, 
    		checkOnSelect:false,
    		fitColumns:false,
    		border : false,
			striped : true,
			columns:header,
    		toolbar:'#toolbar',
    		
		});
	}
	
	var editing;
    var flag=undefined; ////判断新增和修改方法
	 //导入
   function drExcel(){
	   	$("#dr").upload({
	   		action:'/dr',
	   		name:'exceName'
	   	});
   }
   

	//关闭文件上传窗口
	function closedForm(){
		$("#uploadFileDiv").window('close');
	} 
   
   
  /**添加*/
	function add(){ 
			$("#DataTemplateTable").datagrid('endEdit',editing);
		   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	  	    if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	    	$.messager.alert("提示","选择市级以下地区才可以添加!",'warning');
	  	      	return
	  	    }
	  	    $('#DataTemplateTable').datagrid('unselectAll');
			editing=0;
			$("#DataTemplateTable").datagrid("insertRow", {index: 0, row: {}});
			//将新插入的那一行开户编辑状态
			$("#DataTemplateTable").datagrid("beginEdit", 0);
			flag='add';
	}
  

  

	
	/*批量删除*/
	function remove(){
		  var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	  	  if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	      $.messager.alert("提示","选择市级以下地区才可以删除!",'warning');
	  	      return
	  	  }
		var year = $("#year").val();
		//获取全部选中的行
		var rows = $('#DataTemplateTable').datagrid('getSelections');
		//判断是否有选中的行
		if(rows.length>0){
			//批量删除
		$.messager.confirm('警告','你确定要删除当前选中的'+rows.length+'条已发布的任务吗？',function(r){
			if(r){
		    	var params = new Array();
		    	var param="";
		    	//如果要删除的数据只有一条 则 直接传json字符串到后台
		    	if(rows.length==1){
		    		param = JSON.stringify(rows[0]);
		    	}else{
		    	//如果要删除一条以上数据则 则传string[]数组 到后台
		    		for(var i=0;i<rows.length;i++){
		        		var ro = JSON.stringify(rows[i]);
		        		params.push(ro);
		        	}
		    	}
	    		
				$.ajax({
				   //判断是添加还是修改，请求不同的url
					url: "/delete/taskIssued",
					dataType: "json",
					type: "post",
					data: {"params": params ,"param":param,"year":year},//使	用这种数组方式的，得加下一句才可以，使用传统方式
					traditional: true, 
					success: function (data) {
							$("#DataTemplateTable").datagrid('reload');
	    				}
				});
					
			}
		  });
			//删除完毕 重新设置editing状态
			editing = undefined;
		}else{
			$.messager.alert('提示','请最少选择一行进行删除','warning');
		}
	}
	/*保存*/
	function save(){
		$("#DataTemplateTable").datagrid('endEdit',editing);
		editing = undefined;
	}

	   
	/*取消编辑状态*/
	function cancelEdit(){
		//回滚事物
        $('#DataTemplateTable').datagrid('rejectChanges');  
        var _eleDiv = $("#uploadFileButton");
        _eleDiv.siblings(".pics").find("table").children().find("td").remove(); //清空显示上传的文件
        editing = undefined;  
	}
	
 	$("#read").click(function(){
  	 	    $.post("/message/read",function(){
  	 	    	$("#hint").load(location.href + " #hint");//在此刷新页面
	    	//去掉定时器的方法  
	    	//window.setTimeout(timerc); 
	    	$("#toptips").hide();//隐藏消息提示的div
	    });  
  	 }); 

