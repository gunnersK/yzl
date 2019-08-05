$(function(){
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
		
//father you am father 	  

		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");

	//前台ajax每次请求都会执行的一段js
/* 	$(document).ajaxComplete(function(event, xhr, settings){
		    if(xhr.responseText && xhr.responseText == "SESSION_OUT"){
		        alert("SESSION_OUT");
		        //ajax 访问 session 失效
		        window.location.href = "${pageContext.request.contextPath }/login";//这边写跳转登录页面网址
		    }
		});
	 */
		jQuery(document).ready(
				function() {
					//初始化宽度、高度    
					$("#main-container").height($(window).height() - 76);
					$("#iframe").height($(window).height() - 155);
					$(".sidebar").height($(window).height() - 99);
					var thisHeight = $("#nav_list").height(
							$(window).height() - 185);
					$(".submenu").height($(thisHeight).height() - 160);
					$("#nav_list").children(".submenu").css("height",
							thisHeight);

					//当文档窗口发生改变时 触发  
					$(window).resize(
							function() {
								$("#main-container").height(
										$(window).height() - 76);
								$("#iframe").height($(window).height() - 155);
								$(".sidebar").height($(window).height() - 99);
								var thisHeight = $("#nav_list").height(
										$(window).height() - 185);
								$(".submenu").height(
										$(thisHeight).height() - 160);
								$("#nav_list").children(".submenu").css(
										"height", thisHeight);
							});
					$(".iframeurl").bind(
							"click",
							function() {
								var cid = $(this).attr("name");
								var cname = $(this).attr("title");
								$("#iframe").attr("src", cid).ready();
								$("#Bcrumbs").attr("href", cid).ready();
								$(".Current_page a").attr('href', cid).ready();
								$(".Current_page").html(cname).ready();
								$("#parentIframe").html("").css("display",
										"none").ready();
							});

				});

		/*********************点击事件*********************/
		$(document).ready(function() {
			$('#nav_list').find('li.home').click(function() {
				$('#nav_list').find('li.home').removeClass('active');
				$(this).addClass('active');
			});

		})
		//时间设置
		function currentTime() {
			var d = new Date(), str = '';
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			var day = d.getDate();
			var hours = d.getHours();
			var minutes = checkNum(d.getMinutes());
			var seconds = checkNum(d.getSeconds());
			str = year+'年'+month+'月'+day+'日'+hours+'时'+minutes+'分'+seconds+'秒';
			return str;
		}
		function checkNum(num){
			if(num < 10){
				num = "0"+num;
			}
			return num;
		}
		setInterval(function() {
			$('#time').html(currentTime)
		}, 1000);
		//修改密码
		$('.change_Password')
				.on(
						'click',
						function() {
							layer
									.open({
										type : 1,
										title : '修改密码',
										area : [ '300px', '300px' ],
										shadeClose : true,
										content : $('#change_Pass'),
										btn : [ '确认修改' ],
										yes : function(index, layero) {
											if ($("#password").val() == "") {
												layer.alert('原密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}
											if ($("#Nes_pas").val() == "") {
												layer.alert('新密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}

											if ($("#c_mew_pas").val() == "") {
												layer.alert('确认新密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}
											if (!$("#c_mew_pas").val
													|| $("#c_mew_pas").val() != $(
															"#Nes_pas").val()) {
												layer.alert('密码不一致!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											} else {

												$
														.post(
																"/change/password",
																{
																	oldps : $(
																			"#password")
																			.val(),
																	newps : $(
																			"#Nes_pas")
																			.val()
																},
																function(data) {

																	if (data.status == 200) {
																		layer
																				.alert(
																						'修改成功！',
																						{
																							title : '提示框',
																							icon : 1,
																						});
																		layer
																				.close(index);
																		//清空表单
																		$(
																				"#c_mew_pas")
																				.val(
																						"");
																		$(
																				"#password")
																				.val(
																						"");
																		$(
																				"#Nes_pas")
																				.val(
																						"");
																	} else {
																		layer
																				.alert(
																						data.msg
																								+ "，请重新输入a！",
																						{
																							title : '提示框',
																							icon : 0,
																						});
																		//清空表单
																		$(
																				"#c_mew_pas")
																				.val(
																						"");
																		$(
																				"#password")
																				.val(
																						"");
																		$(
																				"#Nes_pas")
																				.val(
																						"");
																	}

																});
											}
										}
									});
						});
		$('#Exit_system')
				.on(
						'click',
						function() {
							layer
									.confirm(
											'是否确定退出系统？',
											{
												btn : [ '是', '否' ]
											//按钮
											},
											function() {
												$
														.post(
																"/user/logout",
																function() {
																	location.href = "login.jsp";
																})
											});
						});

		$.ajaxSetup({
			complete : function(XMLHttpRequest, textStatus) {
				if (textStatus == "parsererror") {
					$.messager.alert('提示信息', "登陆超时！请重新登陆！", 'info', function() {
						window.location.href = 'login';
					});
				} else if (textStatus == "error") {
					$.messager.alert('提示信息', "请求超时！请稍后再试！", 'info');
				}
			}
		});
		function ajaxPost(url, params, callback) {
			var result = null;
			var headers = {};
			headers['CSRFToken'] = $("#csrftoken").val();

			$.ajax({
						type : 'post',
						async : false,
						url : url,
						data : params,
						dataType : 'json',
						headers : headers,
						success : function(data, status) {
							result = data;
							if (data && data.code && data.code == '101') {
								modals.error("操作失败，请刷新重试，具体错误：" + data.message);
								return false;
							}
							if (callback) {
								callback.call(this, data, status);
							}
						},
						error : function(err, err1, err2) {
							console
									.log("ajaxPost发生异常，请仔细检查请求url是否正确，如下面错误信息中出现success，则表示csrftoken更新，请忽略");
							console.log(err.responseText);
							if (err && err.readyState && err.readyState == '4') {
								var sessionstatus = err
										.getResponseHeader("session-status");
								if (sessionstatus == "timeout") {
									//如果超时就处理 ，指定要跳转的页面
									window.location.href = basePath + "/";
								} else {//csrf异常
									var responseBody = err.responseText;
									if (responseBody) {
										responseBody = "{'retData':"
												+ responseBody;
										var resJson = eval('(' + responseBody
												+ ')');
										$("#csrftoken").val(
												resJson.csrf.CSRFToken);
										this.success(resJson.retData, 200);
									}
									return;
								}
							}
							modals.error({
								text : JSON.stringify(err) + '<br/>err1:'
										+ JSON.stringify(err1) + '<br/>err2:'
										+ JSON.stringify(err2),
								large : true
							});
						}
					});
			return result;
			}
});
