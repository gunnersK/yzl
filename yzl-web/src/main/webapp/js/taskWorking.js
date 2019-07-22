var x;
    var y;
    $(function(){
    	$(document).mousemove(function (e) {
       	 $("#pagex").value = e.pageX;//pageX() 属性是鼠标指针的位置，相对于文档的左边缘。
       	 $("#pagey").value = e.pageY;//pageY() 属性是鼠标指针的位置，相对于文档的上边缘。
       	 x = e.pageX;
       	 y = e.pageY;
       	//console.info('x'+e.pageX);
       	//console.info('y'+e.pageY);
       	});
    })
    
    //当前登录的用户
    var usr = "${sessionScope.user.username}";
    var msg;
    var shmsg ;
    var msgth ;
    var msgtj ;
    var lea;//留言
    var sh1 = false;//sh1
    var sh2 = true;//sh2
    var th1 = false;//sh1
    var th2 = true;//sh2
    var tj1 = false;//sh1
    var tj2 = true;//sh2
    var stat;//记录状态  为1的时候执行退回   为2的时候执行提交    为3的时候执行审核
    var muniLength = null;//多少个市
    var coutyLength = null;//多少个县
    var countNode = null;//县的节点
    var cityNode = null;//市的节点
    var disCode;
    var bdgwj = new Array;//保存多个文件
    var epcMark = null;//工程编号
  //退回
    var backRows = null;
    var backNode = null;
    var backTime = null;
    var backCountys = new Array;//所有的县
  //提交
    var submitRows = null;
    var subCountys = new Array;
 	 //审核
 	 var auditRows = null;
 	 var auditCounty = new Array;
 	var zllbCode;
 	var back = 0;//计数
	var submit = 0;
	var audit = 0;
    function backFunction(){
    	backNode =$("#tt").tree('getSelected');//获取选择的节点
    	if(backNode!=null){
    		disCode = backNode.id;
    	}
    	stat = 1;
    	backTime = $("#year").val();//获取时间
    	backRows = $("#tab").datagrid('getSelections');//获取选择的行
    	if(backRows.length == 0){
       		$.messager.alert('错误提示','必须选择一个','info');
       		return;
       	}
    	
   		for(var i=0;i<backRows.length;i++){
   			var r = JSON.stringify(backRows[i]);//转成json串
       		//将json字符串转成jsn对象
       		var JsonObj = eval('('+r+')');
       		var states = JsonObj.stat;
       		var county = JsonObj.county;
       		if(states == '3' || states == '0'){
       			$.messager.alert('错误提示','未提交不能退回！！','info');
           		return;
       		}
           	if(county == undefined){
           		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
           		return ;
           	}else{
           		backCountys.push(county);
           	}
   		}
    	
    	var backData = new Array();
    	var backDatas = new Array();
    	if(backRows.length == 1){
    		backDatas.push(JSON.stringify(backRows[0]));
    	}else{
	   		for(var i=0;i<backRows.length;i++){
	       		var ro = JSON.stringify(backRows[i]);
	       		backData.push(ro);
       	}
    }
    	if(th2){
    		$("#upt").window("open");
    	}
    	
    	th1=false;
    	th2=true;
    }
    
    //点击保存后的退回走这里
    function saveBack(){
    	if(th1){
   			var backData = new Array();
   	    	var backDatas = new Array();
   	    	
   	    	if(backRows.length == 1){
   	    		backDatas.push(JSON.stringify(backRows[0]));
   	    	}else{
   	    		for(var i=0;i<backRows.length;i++){
   	        		var ro = JSON.stringify(backRows[i]);
   	        		backData.push(ro);
   	        	}
   	    	}
   	    	
      		$.ajaxSettings.async = false;//取消异步
   	    	$.ajax({
   	    		type:'post',
   	    		traditional: true,
   	    		async:false,
   	    		url:'/backData',
   	    		data:{'backData':backData,"disCode":disCode,"usr":usr,'backDatas':backDatas,"time":backTime,"lea":lea,"fileNames":bdgwj,"countys":backCountys,"zllb":epcMark,"fileNames":bdgwj},
   				dataType:'json',
   	    		success:function(data){
   					if(data.data==200){
   						$.messager.alert('提示框','成功','info');
   						back = 0;
   						submit = 0;
   						audit = 0;
   						init();//获取数据表格表头数据
   				    	inTable();//初始化数据表格
   						//$("#tab").datagrid('reload');
   						$("#tbody").html("");
   						bdgwj = [];
   					}else{
   						$.messager.alert('提示框','出错!!','info');
   					}
   					
   				},
   	    	});
   	    	tj1=false;
           	tj2=true;
    	}
    }
    
    
    function submitFunction(){
    	var node =$("#tt").tree('getSelected');
    	if(node!=null){
    		disCode = node.id;
    	}
    	stat = 2;
    	submitRows = $("#tab").datagrid('getSelections');
    	if(submitRows.length == 0){
    		$.messager.alert('错误提示','必须选择一个','info');
    		return;
    	}
    	var zl = new Array();
   		for(var i=0;i<submitRows.length;i++){
   			var r = JSON.stringify(submitRows[i]);//转成json串
       		//将json字符串转成jsn对象
       		var JsonObj = eval('('+r+')');
       		var states = JsonObj.stat;
       		var county = JsonObj.county;
       		if(states == '1'){
       			$.messager.alert('错误提示','以提交,不能重复提交！！','info');
           		return;
       		}
           	if(county == undefined){
           		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
           		return ;
           	}else{
           		subCountys.push(county);
           	}
   		}
    	
    	if(tj2){
    		$("#upt").window("open");
    	}

    	tj1=false;
    	tj2=true;
    }
    
  	 //点击保存后的提交走这里
    function saveSubmit(){
    	if(tj1){
   			var time = $("#year").val();//获取时间
   			
   			var subData = new Array();
   	    	var subDatas = new Array();
   	    	
   	    	if(submitRows.length == 1){
   	    		subDatas.push(JSON.stringify(submitRows[0]));
   	    	}else{
   	    		for(var i=0;i<submitRows.length;i++){
   	        		var ro = JSON.stringify(submitRows[i]);
   	        		subData.push(ro);
   	        	}
   	    	}
   	    	
      		$.ajaxSettings.async = false;//取消异步
   	    	$.ajax({
   	    		type:'post',
   	    		traditional: true,
   	    		async:false,
   	    		url:'/submit',
   	    		data:{'subData':subData,"disCode":disCode,"usr":usr,'subDatas':subDatas,"time":time,"lea":lea,"zllb":epcMark,"countys":subCountys,"fileNames":bdgwj},
   				dataType:'json',
   	    		success:function(data){
   					if(data.data==200){
   						$.messager.alert('提示框','成功','info');
   						back = 0;
   						submit = 0;
   						audit = 0;
   						//$("#tab").datagrid('reload');
   						init();//获取数据表格表头数据
    					inTable();//初始化数据表格
   						$("#tbody").html("");
   						bdgwj = [];
   					}else{
   						$.messager.alert('提示框','失败','info');
   					}
   				},
   	    	});
   	    	th1=false;
           	th2=true;
    	}
  	 }

     function auditFunction(){
     	//获取选择的节点
   		var node =$("#tt").tree('getSelected');
     	if(node!=null){
     		disCode = node.id;
     	}
     	stat = 3;
     	auditRows = $("#tab").datagrid('getSelections');
     	
     	if(auditRows.length == 0){
     		$.messager.alert('错误提示','必须选择一个','info');
     		return;
     	}
     	
    		for(var i=0;i<auditRows.length;i++){
    			var r = JSON.stringify(auditRows[i]);//转成json串
        		//将json字符串转成jsn对象
        		var JsonObj = eval('('+r+')');
        		var states = JsonObj.stat;
        		var county = JsonObj.county;
        		if(states == '3' || states == '0'){
     			$.messager.alert('错误提示','请先提交！！','info');
         		return;
     		}
            	if(county == undefined){
            		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
            		return ;
            	}else{
            		auditCounty.push(county);
            	}
    		}
     	if(sh2){
     		$("#upt").window("open");
     	}
     	sh1=false;
         sh2=true; 
     }
  
  	//点击保存后的提交走这里
  	function saveAudit(){
  		if(sh1){
  			var time = $("#year").val();//获取时间
			var eids = new Array();
	    	var countys = new Array;
	    	var json = new Array;
	    	
	    	$.ajaxSettings.async = false;//取消异步
			var auditData = new Array();
	    	var auditDatas = new Array();
	    	
	    	if(auditRows.length == 1){
	    		auditDatas.push(JSON.stringify(auditRows[0]));
	    	}else{
	    		for(var i=0;i<auditRows.length;i++){
	        		var ro = JSON.stringify(auditRows[i]);
	        		auditData.push(ro);
	        	}
	    	}
	    	$.ajax({
	    		type:'post',
	    		traditional: true,
	    		async:false,
	    		url:'/auditData',
	    		data:{'auditData':auditData,"disCode":disCode,'auditDatas':auditDatas,"time":time,"zllb":epcMark,"countys":auditCounty,"lea":lea,"fileNames":bdgwj},
				dataType:'json',
	    		success:function(data){
					if(data.data==200){
						$.messager.alert('提示框','成功','info');
						back = 0;
						submit = 0;
						audit = 0;
						//$("#tab").datagrid('reload');
						init();//获取数据表格表头数据
    					inTable();//初始化数据表格
						$("#tbody").html("");
						bdgwj = [];
					}else{
						$.messager.alert('提示框','失败','info');
					}
				},
	    	});
		}	
  	}
  	
  //添加固定表头
	var frozenColumnsTab = new Array();
	var columnsTabHead = new Array();
	
  //选择 查看工程触发
//    $("#epc").combobox({
//    	onSelect:changed
//    });
  	function changed(preceedStat,beBack,beSubmit,beAudit){
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	
	  	//获取时间
	    var year = $("#year").val();
	    var node =$("#tt").tree('getSelected');//点击的行政编号
	    epcMark = $("#epc").combobox('getValue');//工程编号
//	    if(epcMark == '10'){
//	    	epcMark = '';
//	    }
		var text = $("#epc").combobox('getText');//工程名称
		
		
		var oldNode = null;
    	var id = null;
    	if(node != null && node != 'null' && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	 
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	//disCode = 
        }
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	//var replaceCityNode = Object.assign({},cityNode);
        	var replaceCityNode = $.extend(true,{},cityNode);
        	//var replaceCityNode = JSON.parse(JSON.stringify(cityNode));
        	console.info(replaceCityNode+"----------------------");
        	node = replaceCityNode;
        	//disCode = 
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        }
        if(zllbCode != undefined){
 			node.id = zllbCode;
 			//epcMark=zllbCode;
 			
     		frozenColumnsTab = null;
     		columnsTabHead = null;
     	}
        frozenColumnsTab = new Array();
 		columnsTabHead = new Array();
	  	if(node!=null){
			if(node.id != "GX450"){
				disCode = node.id;
				
				//如果为市
				var pattern2 = new RegExp("[A-Za-z]+");
	    		if(pattern2.test(node.id)){
	    			frozenColumnsTab.push(
	    					[
	    						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
	    					{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    	    			{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
	    	    			{field:'back',title:'退回',width:100,rowspan:'2',hidden:true,align:'center',
        	    				formatter: function(value,row,index){
        	    					if (beBack != undefined) {
        	    						back = beBack;
									}else{
										back = value;
									}
        	    					
        	    				}},
        	    			{field:'submit',title:'提交',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					if (beSubmit != undefined) {
           	    						submit = beSubmit;
									}else{
										submit = value;
									}
        	    					
           	    				}},
        	    			{field:'audit',title:'审核',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					if (beAudit != undefined) {
           	    						audit = beAudit;
									}else{
										audit = value;
									}
        	    					
           	    				}},
        	    			
        	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
        	    				formatter: function(value,row,index){
        	    					//<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>
             	    		         if (value == "1"){
              	    		             //return "<p style='color:blue;'>待审核</p>";
             	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:blue;text-decoration: none;'>待审核("+audit+")</a></p> </div>"
              	    		          } else if (value == "0"){
              	    		             //return "<p style='color:green;'>待提交</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:green;text-decoration: none;'>待提交("+submit+")</a></p> </div>"
              	    		          }else if (value == "3"){
              	    		             //return "<p style='color:red;'>被退回</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:red;text-decoration: none;'>被退回("+back+")</a></p> </div>"
              	    		          }else{
              	    		             return "<p style='color:gray;'>无状态</p>";
              	    		          }  			
              	    		      }},
	       	    		   {field:'proceeding',title:'事项',hidden:true,width:100,rowspan:'2',align:'center',hidden:true},
	       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
	    				]);
	    		}
	    		
	    		//验证是否是数字
	    	   	//是说明是县
	    		var pattern3 = new RegExp("[0-9]+");
	    		if(pattern3.test(node.id)){
	    			
	    			if(zllbCode != undefined){
	    				frozenColumnsTab.push(
	    						[
	    							{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
	    							{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    	    	    			{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
	    	    	    			{field:'back',title:'退回',width:100,rowspan:'2',hidden:true,align:'center',
	            	    				formatter: function(value,row,index){
	            	    					if (beBack != undefined) {
	            	    						back = beBack;
	    									}else{
	    										back = value;
	    									}
	            	    					
	            	    				}},
	            	    			{field:'submit',title:'提交',width:100,rowspan:'2',hidden:true,align:'center',
	               	    				formatter: function(value,row,index){
	               	    					if (beSubmit != undefined) {
	               	    						submit = beSubmit;
	    									}else{
	    										submit = value;
	    									}
	            	    					
	               	    				}},
	            	    			{field:'audit',title:'审核',width:100,rowspan:'2',hidden:true,align:'center',
	               	    				formatter: function(value,row,index){
	               	    					if (beAudit != undefined) {
	               	    						audit = beAudit;
	    									}else{
	    										audit = value;
	    									}
	            	    					
	               	    				}},
	            	    			
	            	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
	            	    				formatter: function(value,row,index){
	            	    					//<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>
	                 	    		         if (value == "1"){console.info(1);
	                  	    		             //return "<p style='color:blue;'>待审核</p>";
	                 	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:blue;text-decoration: none;'>待审核("+audit+")</a></p> </div>"
	                  	    		          } else if (value == "0"){console.info(0);
	                  	    		             //return "<p style='color:green;'>待提交</p>";
	                  	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:green;text-decoration: none;'>待提交("+submit+")</a></p> </div>"
	                  	    		          }else if (value == "3"){console.info(3);
	                  	    		             //return "<p style='color:red;'>被退回</p>";
	                  	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:red;text-decoration: none;'>被退回("+back+")</a></p> </div>"
	                  	    		          }else{
	                  	    		             return "<p style='color:gray;'>无状态</p>";
	                  	    		          }  			
	                  	    		      }},
	    	       	    		   {field:'proceeding',title:'事项',hidden:true,width:100,rowspan:'2',align:'center'},
	    	       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
	    					]);
	    			}else{
	    				frozenColumnsTab.push(
		    					[
		    					{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
		    					{field:'back',title:'退回',width:100,rowspan:'2',hidden:true,align:'center',
	        	    				formatter: function(value,row,index){
	        	    					if (beBack != undefined) {
	        	    						back = beBack;
										}else{
											back = value;
										}
	        	    					
	        	    				}},
	        	    			{field:'submit',title:'提交',width:100,rowspan:'2',hidden:true,align:'center',
	           	    				formatter: function(value,row,index){
	           	    					if (beSubmit != undefined) {
	           	    						submit = beSubmit;
										}else{
											submit = value;
										}
	        	    					
	           	    				}},
	        	    			{field:'audit',title:'审核',width:100,rowspan:'2',hidden:true,align:'center',
	           	    				formatter: function(value,row,index){
	           	    					if (beAudit != undefined) {
	           	    						audit = beAudit;
										}else{
											audit = value;
										}
	        	    					
	           	    				}},
	        	    			
	        	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
	        	    				formatter: function(value,row,index){
	        	    					//<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>
	             	    		         if (value == "1"){console.info(1);
	              	    		             //return "<p style='color:blue;'>待审核</p>";
	             	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:blue;text-decoration: none;'>待审核("+audit+")</a></p> </div>"
	              	    		          } else if (value == "0"){
	              	    		             //return "<p style='color:green;'>待提交</p>";
	              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:green;text-decoration: none;'>待提交("+submit+")</a></p> </div>"
	              	    		          }else if (value == "3"){console.info(3);
	              	    		             //return "<p style='color:red;'>被退回</p>";
	              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs("+audit+","+submit+","+back+")' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:red;text-decoration: none;'>被退回("+back+")</a></p> </div>"
	              	    		          }else{
	              	    		             return "<p style='color:gray;'>无状态</p>";
	              	    		          }  			
	              	    		      }}, 
		       	    		   {field:'proceeding',title:'事项',hidden:true,width:100,rowspan:'2',align:'center'},
		       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
		    				]);
	    			}
	    			
	    			$.ajax({
	    				async:false,
	    				url:'/takWorking/Ddis',
	    				dataType:'json',
	    				data:{"dcode":node.id},
	    				success:function(data){
	    					//var si = JOSN.stringify(data);
	    					//var obj = JSON.parse(data);&& data.length>0 && data != "[object Object]"
	    					console.info(data+"------------");
	    					if(data!=null ){
	    						disCode = data.anumber;
	    					}
	    					
	    				},
	    				
	    			});
	    		}
	    		
			}else{
				frozenColumnsTab.push(
						[
							/* {field:'',title:'',checkbox="true",align:'center'}, */
							{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
							{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
		    			/* {field:'county',title:'县',width:100,rowspan:'2',align:'center'}, */
					]);
			}
		}else{
			frozenColumnsTab.push(
					[
						/* {field:'',title:'',checkbox="true",align:'center'}, */
						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    			/* {field:'county',title:'县',width:100,rowspan:'2',align:'center'}, */
				]);
		}
		
		$.ajax({
			type:'post',
			traditional: true,
			async:false,
			url:'/taskWorking/taskTab',
			data:{"year":year,"zllb":epcMark,"disCode":disCode,"stat":preceedStat},
			dataType:'json',
			success:function(data){
				
		    	for(var i=0;i<data.length;i++){
					columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
					
					var epc = data[i].list;
					
					if(data[i].list.length > 0){
						for(var j=0;j<epc.length;j++){
							var ename = epc[j].ename;
							var field = epc[j].field;
							columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
							columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:100,align:'center'},
												 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:100,align:'center'},
												 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:100,align:'center'});
						}
					}
				}
		    	columnsTabHead.push(columnsOneTab);
				columnsTabHead.push(columnsTowTab);
				columnsTabHead.push(columnsThreeTab);
		    	
				inTable(preceedStat);
			},
		})
		
  	}
  
    function derive(){
    	
    	var node =$("#tt").tree('getSelected');//获取选择的节点
    	//获取时间
    	var year = $("#year").val();
    	var id = null;
    	if(node != null){
    		id = node.id;
    	}
    	window.location.href="/completionTask/derive?nid="+id+'&year='+year;
    }
	
    //页面加载
    $(function(){
    	$.ajaxSettings.async = false;
    	//初始化树
        $("#tt").tree({
       		url:'/show_dis',
       		animate:true,
       		onClick:click,
       		onLoadSuccess:success
       	});
    	init();//获取数据表格表头数据
    	inTable();//初始化数据表格
    	
	  //选择 查看工程触发
        $("#epc").combobox({
        	onSelect:changed
        });
		

        $("#year").numberspinner({
        	"onChange":function(){
        		var node =$("#tt").tree('getSelected');
        		
        		if(node==null){
        			init();//获取数据表格表头数据
        			inTable();//初始化数据表格
        		}else{
        			init();//获取数据表格表头数据
       				inTable();//初始化数据表格
        		}
        	}
        });
    	
    })
    //页面加载时初始化
    function init(){
    	var year = $("#year").val();
    	
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	
    	var node =$("#tt").tree('getSelected');//获取选择的节点
    	var zllb = $("#epc").combobox('getValue');//获取选择的工程编号
    	
    	var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	if(zllbCode != undefined){
    		node.id=zllbCode;
    		frozenColumnsTab = null;
    		columnsTabHead = null;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	//var replaceCityNode = Object.assign({},cityNode);
        	var replaceCityNode = $.extend(true,{},cityNode);
        	node = replaceCityNode;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        }
    	var disCode = null;
    	
    	frozenColumnsTab = new Array();
   	 	columnsTabHead = new Array();
   	 	
    	if(node!=null){
    		if(node.id != "GX450"){
    			disCode = node.id;
    			//如果为市
    			var pattern2 = new RegExp("[A-Za-z]+");
        		if(pattern2.test(node.id)){
        			frozenColumnsTab.push(
        					[
       						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
       						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
        	    			{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
        	    			
        	    			{field:'back',title:'退回',width:100,rowspan:'2',hidden:true,align:'center',
        	    				formatter: function(value,row,index){
        	    					back = value;
        	    				}},
        	    			{field:'submit',title:'提交',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					submit = value;
           	    				}},
        	    			{field:'audit',title:'审核',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					audit = value;
           	    				}},
        	    			
        	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
        	    				formatter: function(value,row,index){
        	    					//<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>
             	    		         if (value == "1"){console.info(1);
              	    		             //return "<p style='color:blue;'>待审核</p>";
             	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:blue;text-decoration: none;'>待审核("+audit+")</a></p> </div>"
              	    		          } else if (value == "0"){console.info(0);
              	    		             //return "<p style='color:green;'>待提交</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:green;text-decoration: none;'>待提交("+submit+")</a></p> </div>"
              	    		          }else if (value == "3"){console.info(3);
              	    		             //return "<p style='color:red;'>被退回</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:red;text-decoration: none;'>被退回("+back+")</a></p> </div>"
              	    		          }else{
              	    		             return "<p style='color:gray;'>无状态</p>";
              	    		          }  			
              	    		      }},
              	    		    {field:'proceeding',title:'事项',hidden:true,width:100,rowspan:'2',align:'center'},
              	    		    {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
        				]);
        		}
        		
        		//验证是否是数字
        	   	//是说明是县
        		var pattern3 = new RegExp("[0-9]+");
        		if(pattern3.test(node.id)){
        			frozenColumnsTab.push(
        					[
       						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
       						{field:'back',title:'退回',width:100,rowspan:'2',hidden:true,align:'center',
        	    				formatter: function(value,row,index){
        	    					back = value;
        	    				}},
        	    			{field:'submit',title:'提交',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					submit = value;
           	    				}},
        	    			{field:'audit',title:'审核',width:100,rowspan:'2',hidden:true,align:'center',
           	    				formatter: function(value,row,index){
           	    					audit = value;
           	    				}},
        	    			
        	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
        	    				formatter: function(value,row,index){
        	    					//<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>
             	    		         if (value == "1"){console.info(1);
              	    		             //return "<p style='color:blue;'>待审核</p>";
             	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:blue;text-decoration: none;'>待审核("+audit+")</a></p> </div>"
              	    		          } else if (value == "0"){console.info(0);
              	    		             //return "<p style='color:green;'>待提交</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:green;text-decoration: none;'>待提交("+submit+")</a></p> </div>"
              	    		          }else if (value == "3"){console.info(3);
              	    		             //return "<p style='color:red;'>被退回</p>";
              	  return "<div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><p ><a href='#' style='color:red;text-decoration: none;'>被退回("+back+")</a></p> </div>"
              	    		          }else{
              	    		             return "<p style='color:gray;'>无状态</p>";
              	    		          }  			
              	    		      }},
           	    		   {field:'proceeding',title:'事项',hidden:true,width:100,rowspan:'2',align:'center'},
           	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
        				]);
        			$.ajax({
        				async:false,
        				url:'/takWorking/Ddis',
        				dataType:'json',
        				data:{"dcode":node.id},
        				success:function(data){
        					if(data!=null){
        						disCode = data.anumber;
        					}
        					
        				},
        				
        			});
        		}
        		
    		}else{
    			frozenColumnsTab.push(
    					[
    						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
    						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
    				]);
    		}
    	}else{
    		frozenColumnsTab.push(
					[
						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
				]);
    	}
		
		//获取数据库中的数据表头
		$.ajax({
			async:false,
			url:'/takWorking/taskTab',
//			url:'/task/getTableHeader',
			dataType:'json',
			data:{"year":year,"disCode":disCode,"gclb":zllb},
			success:function(data){
				for(var i=0;i<data.length;i++){
					columnsOneTab.push({field:'',title:data[i].ename,width:130*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
					
					var task = data[i].list;
					
					if(data[i].list.length > 0){
						for(var j=0;j<task.length;j++){
							var tname = task[j].tname;
							var field = task[j].field;
							columnsTowTab.push({title:''+tname+'',width:130*3,align:'center',colspan:3});//field:''+field+'',
							columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+task[j].mark,title:'计划',width:130,align:'center'},
												 {field:'wc'+data[i].mark+"Y"+task[j].mark,title:'完成',width:130,align:'center'},
												 {field:'zjh'+data[i].mark+"Y"+task[j].mark,title:'占计划%',width:130,align:'center'});
						}
					}
				}
				
			},
		});
		
		columnsTabHead.push(columnsOneTab);
		columnsTabHead.push(columnsTowTab);
		columnsTabHead.push(columnsThreeTab);
		
	}
    
    //初始化表格
    function inTable(preceedStat){
    
    
	var node =$("#tt").tree('getSelected');//获取选择的节点
    var oldNode = null;
	var id = null;
	if(node != null && node.id != "GX450"){
			oldNode = node;
			id = oldNode.id;
	}
	
	//判断是否只有一个市和一个县
    if(muniLength == 1 && coutyLength == 1){
    	node = countNode;
    }
  //muniLength=1和coutyLength大于1说明是市管理员
    if(muniLength == 1 && coutyLength > 1){
    	//var replaceCityNode = Object.assign({},cityNode);
    	var replaceCityNode = $.extend(true,{},cityNode);
    	node = replaceCityNode;
    }
    var pattern3 = new RegExp("[0-9]+");
    if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
    	node = oldNode;
    }
    
    if(zllbCode != undefined){
		node.id = zllbCode;
	}
    	var disCode = "GX450";
    	
    	if(node!=null){
    		if(node.id != "GX450"){
    			
    			//如果为市
    			var pattern2 = new RegExp("[A-Za-z]+");
        		if(pattern2.test(node.id)){
        			disCode = node.id;
        		}
        		//验证是否是数字
        	   	//是说明是县
        		var pattern3 = new RegExp("[0-9]+");
        		if(pattern3.test(node.id)){
        			$.ajax({
        				async:false,
        				url:'/takWorking/Ddis',
        				dataType:'json',
        				data:{"dcode":node.id},
        				success:function(data){
        					if(data!= null){
        						disCode = data.anumber;
        					}else{
        						disCode=node.id;
        					}
        				},
        				
        			});
        		}
    		}
    	}
    	//获取时间
    	var year = $("#year").val();
    	//var url = HttpUtility.UrlEncode('/takWorking/epcTaskData?year='+year+'&disCode='+disCode+'&usr='+usr+'&zllb='+epcMark);
    	var url = encodeURI('/takWorking/epcTaskData?year='+year+'&disCode='+disCode+'&usr='+usr+'&gclb='+epcMark+'&stat='+preceedStat);
    	$.ajaxSettings.async=false;
    	$("#tab").datagrid({
    		fit:true,
			url:url,
    		frozenColumns:frozenColumnsTab,
    		columns:columnsTabHead,
    		rownumbers:true,
    		border : false,
    		striped : true,
    		pagination:true,
    		fitColumns:false,
    		onLoadSuccess:successLoad,
    		toolbar:'#tbs',
    		onClickCell: clickCell
    	});
    	zllbCode = undefined;
    	preceedStat = undefined;
    }
    var rowIndexa;
    function clickCell(rowIndexs, field, value){
    	rowIndexa = rowIndexs;
			if("particulars" == field){
				var nodeLog =$("#tt").tree('getSelected');//获取选择的节点
				//var logCode = nodeLog.id;
				var time = $("#year").val();;//时间
				var coy ;//县
				
				var data = new Array();
				
				var rows = $("#tab").datagrid("getRows");//获取表格索引数据
				var row = rows[rowIndexs]; //根据索引获取指定行的值对象
				var r = JSON.stringify(row);//转成json串
				var JsonObj = eval('('+r+')');//将json串转成json对象
				delete JsonObj.particulars;
				//遍历json对象
				for(var key in JsonObj){
					var T = key.indexOf("T");//判断是否包含这个字母（是代表是工程和造林类别）
					if(T != -1){
						data.push(key);
					}
					var county = key.search("county");//是否为县
					if(county != -1){
						coy = JsonObj[key];
					}
				}
				var js = data.join();
        		$("#opt").window("open");
        		var datefilename;//用来存储文件的时间+名称
        		var logurl = encodeURI('/takWorking/findLog?row='+data.join()+'&time='+time+'&county='+coy+'&zllb='+epcMark);
        		/* 操作详情的表格 */
        	    $("#optab").datagrid({
        	    	fit:true,
        	    	striped:true,
        	    	url:logurl,
        	    	columns:[[
        	    		{field:'stat',title:'工作步骤',width:100,align:'center',
        	    			formatter: function(value){
        	    				if(value=="1" || value == '0'){
        	    					return "任务工作中";
        	    				}else if(value == '2'){
        	    					return "造林任务完成验收";
        	    				}
        	    			}},
        	    		{field:'name',title:'用户名',width:100,align:'center'},
        	    		{field:'operate',title:'执行的操作',width:80,align:'center'},
        	    		{field:'operateTime',title:'操作的时间',width:160,align:'center'},
        	    		{field:'dcode',title:'县名称',width:100,align:'center'},
        	    		{field:'gclb',title:'工程类别',align:'center',//width:140,
        	    			},
        	    		{field:'year',title:'工程的时间',width:100,align:'center'},
        	    		{field:'filename',title:'文件',width:118,align:'center'},
        	    		{field:'lea',title:'留言',width:200,align:'center'},
        	    		{field:'hi',title:'hi',width:100,align:'center',hidden:'true'},
        	    	]],
        	    	border : false,
        	    	//显示分页栏
        	    	pagination:true,
        	    	rownumbers:true,
        	    	onLoadSuccess:function(data){
        	    		console.info(data);
        	    	},
        	    	onClickCell: function (rowIndex, field, value){
        	    		if("filename" == field){
        	    			var time = year;//时间
            				var coy ;//县
            				
            				var data = new Array();
            				
            				var rows = $("#optab").datagrid("getRows");//获取表格索引数据
            				var row = rows[rowIndex]; //根据索引获取指定行的值对象
            				var r = JSON.stringify(row);//转成json串
            				var JsonObj = eval('('+r+')');//将json串转成json对象
            				var file = JsonObj.hi;
            				//遍历json对象
            				var dcurl = encodeURI("/takWorking/deriveFile?file="+file);
        	    			window.location.href=dcurl;
        	    		}
        	    	}
        	    });
			}

    }
    var rowIndexa;
    function successLoad(data){
    	console.info(data);
    	back = 0;
		submit = 0;
		audit = 0;
    	$(".datagrid-row").mouseover(function(e){
    		rowIndexa = $(this).children("td").eq(0).text();//得到行索引
    		//var rows = $("#tab").datagrid("getRows");//获取表格索引数据rowIndexa
    	})
    }
    var i=0;
    //鼠标退出
    function outs(){
    	//$("#tooltip").html('');
    	i=0;
    	$("#tooltip").css({
    		 "display": "none",
    		 'z-index':'1000',
    		 "left":''+x+'px',
    		 'top':''+y+'px',
    		});
    	console.info(11);
    }
	function a(){
		alert(11);
	}
	
	var backSecond = 0;
	var auditSecond = 0;
	var submitSecond = 0;
	
    //鼠标经过
    function overs(audits,submits,backs){
    	
    	if(i==0){
    		i++;
    		var year = $("#year").val();
    		var rows = $("#tab").datagrid("getRows");//获取表格索引数据rowIndexa
			var row = rows[rowIndexa-1]; //根据索引获取指定行的值对象
			var r = JSON.stringify(row);//转成json串
			var JsonObj = eval('('+r+')');//将json串转成json对象
			var county = JsonObj.county;
			var anumber = JsonObj.countycode;
			
			var array = new Array();
			
			for(var key in JsonObj){//遍历json对象
				var index = key.indexOf("T");
				if(index != -1){
					var zl = key.substring(0,index);
					array.push(zl);
				}
			}
			
			var urls = encodeURI('/takWorking/proceed?zllbs='+array+'&county='+county+'&year='+year);
			$.getJSON(urls,function(data){
				back = 0;//计数
				submit = 0;
				audit = 0;
				if(audits == undefined && submits == undefined && backs == undefined){
					for(var i=0;i<data.length;i++){
						if(data[i].stat == '0'){
							submit++;
						}
						if(data[i].stat == '1'){
							audit++;			
									}
						if(data[i].stat == '3'){
							back++;
							}
					}
				}else{
					back = backs;//计数
					submit = submits;
					audit = audits;
				}
				
			})
			backSecond = back;
			auditSecond = audit;
			submitSecond = submit;
	
    		$("#tdBack").html('<a href="#" style="text-decoration:none;" onclick="beBack('+anumber+')"><font color="red">被退回('+back+')</font></a>');
    		$("#tdSubmit").html('<a href="#" style="text-decoration:none;" onclick="beSubmit('+anumber+')"><font color="red">待提交('+submit+')</font></a>');
    		$("#tdAudit").html('<a href="#" style="text-decoration:none;" onclick="beAudit('+anumber+')"><font color="red">待审核('+audit+')</font></a>');
    		
        	$("#tooltip").css({
        		'position':'absolute',
        		 "display": "block",
        		 "left":''+x+'px',
        		 'top':''+y+'px',
        		 'z-index':'1000',
        	});
    	}
    	
    }
    function tooltipOut(){
    	$("#tooltip").css({"display": "none"});
    }
	function tooltipOver(){
		$("#tooltip").css({"display": "block"});
		
    }
	//查看被退回
	function beBack(anumber){
		//back = 0;
		//submit = 0;
		//audit = 0;
		$("#epc").combobox('setValue','');//工程编号
		zllbCode = anumber;
		changed('3',back,submit,audit);
		$("#tooltip").css({"display": "none"});
	}
	//查看待提交
	function beSubmit(anumber){
		//back = 0;
		//submit = 0;
		//audit = 0;
		$("#epc").combobox('setValue','');//工程编号
		zllbCode = anumber;
		changed('0',back,submit,audit);
		$("#tooltip").css({"display": "none"});
	}
	//查看待审核
	function beAudit(anumber){
		//back = 0;
		//submit = 0;
		//audit = 0;
		$("#epc").combobox('setValue','');//工程编号
		zllbCode = anumber;
		changed('1',back,submit,audit);
		$("#tooltip").css({"display": "none"});
	}
//    $("#year").numberspinner({
//    	"onChange":function(){
//    		
//    		var node =$("#tt").tree('getSelected');
//    		
//    		if(node==null){
//    			init();//获取数据表格表头数据
//    			inTable();//初始化数据表格
//    		}else{
//    			init();//获取数据表格表头数据
//   				inTable();//初始化数据表格
//    		}
//    	}
//    });
    
  	function success(node,data){
  		//获取所有的市
  		var children = data[0].children;
  		//console.info(children);
  		
  		//多少个市
  		 muniLength = children.length;
  		//console.info(muniLength);
  		
  		//获取第一个市下的县
  		var second = children[0].children;
  		//console.info(second);
  		
  		//多少个县
  		 coutyLength = second.length;
  		//console.info(coutyLength);
  		var a = second.target;
  		//条件满足说明为县
  		if(muniLength == 1 && coutyLength == 1){
  			//disCode = second[0].id;
  			
  			var tar = $("#tt").tree("find",second[0].id);
  			countNode = tar;
  			$.ajax({
				async:false,
				url:'/takWorking/Ddis',
				dataType:'json',
				data:{"dcode":tar.id},
				success:function(dataCounty){
					disCode = dataCounty.anumber;
					console.info(disCode+"==========");
				},
				
			});
  			//var bo = $("#tt").tree("select",tar);
  			//$("#tt").tree("setValue",disCode);
  			//click(second);
  		}
  		if(muniLength == 1 && coutyLength > 1){
  			var tar = $("#tt").tree("find",children[0].id);
  			cityNode = tar;
  			$.ajax({
				async:false,
				url:'/takWorking/CityFlag',
				dataType:'json',
				data:{"flag":tar.id},
				success:function(dataCity){
					disCode = dataCity.citycode;
				},
				
			});
  		}
  	}
    
   	function click(node){
   		$("#epc").combobox('setValue','');//工程编号
   		epcMark = null;
   		init();//加载表头
		inTable();//初始化表格
   	}
    	
    $(function(){
    	
    	/* 文件上传 */
    	$('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
            	console.info(data);
            	var st = data.result.data;
            	
            	var upFilename = data.result.msg;
            	var upFilenames = upFilename.split("/");
            	
            	var fileName=null;
            	if(upFilenames.length > 1){
            		fileName = upFilenames[2];
            	}
            	
            	//$("#upt").window("close");
            	if(data.result.data == 200){
            		$("#spaninfo").css("color","green");
            		$("#spaninfo").html("上传成功");
            		$("#tbody").append("<tr> <td align='center' style='border-color:gray'>"+fileName+"</td> <td align='center' style='border-color:gray'><button class='bcbtn' id='"+data.result.msg+"' onclick='delFileName(this)'>删除</button></td> <td style='display: none'></td> </tr>");
            	}
            	msg = data.result.msg;
            	bdgwj.push(msg);
            	
            }
        });
    	//获取当前浏览器的类型
    	var userAgent = navigator.userAgent;
    	
    	if(userAgent.indexOf("Firefox") > -1){//判断是否是火狐
    		//$("#tet").css("width","335px");
    	$("#upt").css("height","400px");
    	$("#tet").css("width","650px");
    	}
    	
    	if(userAgent.indexOf("Trident") > -1){
    		$("#upt").css("height","400px");
        	$("#tet").css("width","650px");
    	}
    	if(userAgent.indexOf("QQBrowser")){//QQBrowser
    		console.info(userAgent.indexOf("QQBrowser"));
    	}
    })
    
    //保存
   	function save(){
    	$("#upt").window("close");
   		//获取文本框内容
   		lea = $("#tet").val();
   		
   		if(stat == 1){////状态  为1的时候执行退回   为2的时候执行提交    为3的时候执行审核
       		msgth = msg;
       		th1=true;
           	th2=false;
           	saveBack();
       	}
		if(stat == 2){
			msgtj = msg;
			tj1=true;
           	tj2=false;
           	saveSubmit();   		
		   }
		if(stat == 3){
			shmsg = msg;
			sh1=true;
           	sh2=false;
           	saveAudit();
		}
		$("#spaninfo").html("");
    	$("#tet").val(null);
   	}
    
    //删除文件
    function delFile(){
    	$.ajax({
				async:false,
				url:'/takWorking/delFile',
				dataType:'json',
				data:{"msg":msg},
				success:function(data){
					var d = data.data;
				},
				
			});
    }
    function cancel(){
    	$("#upt").window("close");
    	$("#spaninfo").html("");
    	$("#tet").val(null);
    	$("#tbody").html("");
    	delFile();
    }
    //删除指定行
    function delFileName(obj){
    	msg = obj.id;
    	//删除数组中的文件
    	for(var i=0;i<=bdgwj.length;i++){
    		if(msg == bdgwj[i]){
    			var f = bdgwj.splice(i);
    			console.info(f);
    		}
    	}
    	
    	delFile();
    	
    	$(obj).parents("tr").remove();
    }
    function cancelProceed(){
    	$("#proceed").window("close");
    	$("#ptab").html("");
    	$("#ptab").datagrid('load');
    }