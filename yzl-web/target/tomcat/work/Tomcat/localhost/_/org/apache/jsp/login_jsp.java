/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-07-11 07:36:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML ><!-- PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\" -->\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/login.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/png.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/cas.login.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ztree/zTreeStyle.css\" type=\"text/css\"/>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ztree/jquery.ztree.all-3.5.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.ocupload-1.1.2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/md5.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<title>登录</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("/* body.login{\r\n");
      out.write("\tbackground-image: url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/index_logo.jpg);\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\t/* background-size:100% 100%; */\r\n");
      out.write("\t/* background-size:contain|cover;\r\n");
      out.write("\tbackground-size:1700px 1000px;\r\n");
      out.write("\tbackground-position: center center;\r\n");
      out.write("}  */ \r\n");
      out.write("body {\r\n");
      out.write("\tbackground-image:url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/bjt.jpg);\r\n");
      out.write("\tbackground-repeat:no-repeat; \r\n");
      out.write("\tbackground-position:0px 90px;\r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write(".login_boder{\r\n");
      out.write("    background: url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/dlbjt.png) no-repeat;\r\n");
      out.write("    background-color: white; /* 白色 */\r\n");
      out.write("    height: 290px;\r\n");
      out.write("    width: 300px;\r\n");
      out.write("    border-radius: 5px;\r\n");
      out.write("}\r\n");
      out.write(".yhan{\r\n");
      out.write("background-image: url(images/yhan.png);/*设置小图标*/\r\n");
      out.write("background-size: 25px 25px;/*小图标的大小*/\r\n");
      out.write("background-position: 5px 4px;/*小图标在input的位置*/\r\n");
      out.write("background-repeat: no-repeat;/*背景小图标不重复*/\r\n");
      out.write("padding: 0px 0px 0px 40px;/*设置input内边距*/\r\n");
      out.write("/*设置input样式好看*/\r\n");
      out.write("border:1px solid #ddd;\r\n");
      out.write("margin: 0px;\r\n");
      out.write("width: 180px;\r\n");
      out.write("    height: 36px;\r\n");
      out.write("    border: 1px solid #cad2db;\r\n");
      out.write("    border-radius: 5px;\r\n");
      out.write("    line-height: 36px;\r\n");
      out.write("    webkit-border-radius: 5px;\r\n");
      out.write("}\r\n");
      out.write(".mman{\r\n");
      out.write("background-image: url(images/mman.png);/*设置小图标*/\r\n");
      out.write("background-size: 25px 25px;/*小图标的大小*/\r\n");
      out.write("background-position: 5px 4px;/*小图标在input的位置*/\r\n");
      out.write("background-repeat: no-repeat;/*背景小图标不重复*/\r\n");
      out.write("padding: 0px 0px 0px 40px;/*设置input内边距*/\r\n");
      out.write("/*设置input样式好看*/\r\n");
      out.write("border:1px solid #ddd;\r\n");
      out.write("margin: 0px;\r\n");
      out.write("width: 180px;\r\n");
      out.write("    height: 36px;\r\n");
      out.write("    border: 1px solid #cad2db;\r\n");
      out.write("    border-radius: 5px;\r\n");
      out.write("    line-height: 36px;\r\n");
      out.write("    webkit-border-radius: 5px;\r\n");
      out.write("}\r\n");
      out.write("/* .sub_button{\r\n");
      out.write("background: url(../images/djan.png) no-repeat -153px -850px;\r\n");
      out.write("} */\r\n");
      out.write(".rem_sub input.sub_button{\r\n");
      out.write("float: left;\r\n");
      out.write("/* background: url(../images/djan.png) no-repeat -153px -850px; */\r\n");
      out.write("    background: no-repeat -0px -850px;\r\n");
      out.write("    width: 200px;\r\n");
      out.write("    color: #f9f9f9;\r\n");
      out.write("    border-color: #0dea53;\r\n");
      out.write("    \r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("/* $(function(){\r\n");
      out.write("\t$(\"#loginname\").css({ background-image: \"url(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/yhan.jpg)\"});\r\n");
      out.write("}) */\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"\" onkeydown=\"keyLogin();\">\r\n");
      out.write("\t<div class=\"login_logo\" style=\"margin-top:15px;margin-left: -800px;margin-bottom: 5px;\"><img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/logos.png\" alt=\"\" /><!-- <p style=\"font-size:30px; color:blue;margin-top:0px;margin-left: -100px\">广西营造林登录系统</p> --></div>\r\n");
      out.write("\t\r\n");
      out.write("<!-- <div class= \"bd\" style=\"height: 700px;margin-top: -100px\"> -->\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"login_m\" style=\"margin-left: 900px;padding-top: 50px\">\r\n");
      out.write("\t\t<div class=\"login_boder\" style=\"margin-left: 0px;\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<h1 style=\"margin-left: 100px;margin-top: 8px;color: #008844;\">用户登录</h1>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<div class=\"login_padding\" style=\"padding-top: 20px\">\r\n");
      out.write("\t\t\t\t<h2>用户名</h2>\r\n");
      out.write("\t\t\t<form id=\"formlogin\" method=\"post\" >\t\r\n");
      out.write("\t\t\t\t<label>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"loginname\" name=\"username\" class=\"yhan\"/>\r\n");
      out.write("\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t<h2>密码</h2>\r\n");
      out.write("\t\t\t\t<label>\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" name=\"password\" id=\"password\" class=\"mman\"/>\r\n");
      out.write("\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t<!-- 错误回显 -->\r\n");
      out.write("\t\t\t\t\t <span style=\"color:red;\" id=\"sfbestNameErr\"></span>\r\n");
      out.write("\t\t\t\t\t <span style=\"color:red\" id=\"sfbestPwdErr\"></span>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t <br/>\r\n");
      out.write("\t\t\t\t <div class=\"rem_sub_l\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" name=\"checkbox\" id=\"save_me\"/>\r\n");
      out.write("\t\t\t\t\t\t<label for=\"checkbox\">记住密码</label>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\t<div class=\"rem_sub\" style=\"margin-top: 0px;padding-left: -100px\">\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<label style=\"margin-left: -10px\">\r\n");
      out.write("\t\t\t\t\t\t\t<input  type=\"button\" class=\"sub_button\" name=\"button\" id=\"login_sub\" value=\"登录\" style=\"background-color: #06a538;opacity: 0.7;border-color:#f9f9f9;margin-left: 10px \"/>\r\n");
      out.write("\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t  </form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div><!--login_boder end-->\r\n");
      out.write("\t</div><!--login_m end-->\r\n");
      out.write("<!-- </div> -->\r\n");
      out.write("\r\n");
      out.write("<br />\r\n");
      out.write("<br />\r\n");
      out.write("<br />\r\n");
      out.write("<br />\r\n");
      out.write("<br/>\r\n");
      out.write("<!-- <p style=\"font-size:15px; color:green\" align=\"center\">版权所有：广西壮族自治区林业厅 All Rights Reserved | 技术支持：恒瑞信息</p> -->\r\n");
      out.write("<p style=\"font-size:15px;\" align=\"center\">版权所有：广西壮族自治区林业厅 All Rights Reserved | 技术支持：恒瑞信息</p>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("//点击回车键登录\r\n");
      out.write("function keyLogin(){  \r\n");
      out.write("   if (event.keyCode==13){  //回车键的键值为13\r\n");
      out.write("\t   $(\"#login_sub\").trigger('click');\r\n");
      out.write("    }  \r\n");
      out.write("}\r\n");
      out.write("$(function(){\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t$.ajaxSettings.async = false;//取消同步\r\n");
      out.write("/* \twindow.event.returnValue = false;//（IE7只加入这个）\r\n");
      out.write("\r\n");
      out.write("*/\r\n");
      out.write("\tif (window != top)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t/* $.messager.alert('提示框','用户已过期请重新登录!!'); */\r\n");
      out.write("\t    top.location.href = location.href;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t$(\"#login_sub\").click(function(){\r\n");
      out.write("\t\tLOGIN.login();\r\n");
      out.write("\t});\r\n");
      out.write("\t//var redirectUrl = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${redirect}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar LOGIN = {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tcheckInput:function() {\r\n");
      out.write("\t\t\t\t$(\"#sfbestNameErr\").attr(\"class\", \"\").html(\"\").prev().attr(\"class\", \"border\");\r\n");
      out.write("\t\t\t\t$(\"#sfbestPwdErr\").attr(\"class\", \"\").html(\"\").prev().attr(\"class\", \"border\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(!$(\"#formlogin #loginname\").val()) {\r\n");
      out.write("\t\t\t\t\t$(\"#sfbestNameErr\").attr(\"class\", \"error\").show().html(\"请输入用户名\").prev().attr(\"class\", \"border-error\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(!$(\"#formlogin input[name='password']\").val()) {\r\n");
      out.write("\t\t\t\t    $(\"#sfbestPwdErr\").attr(\"class\", \"error\").show().html(\"请输入密码\").prev().attr(\"class\", \"border-error\");\r\n");
      out.write("\t\t\t        return false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#username\").val($(\"#loginname\").val());\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tdoLogin:function() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tvar password = $(\"#password\").val();\r\n");
      out.write("\t\t\t\t//对表单输入的值进行Md5加密\r\n");
      out.write("\t\t\t\tvar md5Password = md5(password);\r\n");
      out.write("\t\t\t\t//把加密后的值  设置回表单\r\n");
      out.write("\t\t\t\t$(\"#password\").val(md5Password);\r\n");
      out.write("\t\t\t\tvar der = $.ajax({\r\n");
      out.write("\t\t\t\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/login', \r\n");
      out.write("\t\t\t\t\t\tdata:$(\"#formlogin\").serialize(),\r\n");
      out.write("\t\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\tif (data.status == 200) { \r\n");
      out.write("\t\t\t\t\t//\t$.messager.alert('提示','登录成功!','info',function(){\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示','登录成功!','info',function(){\r\n");
      out.write("\t\t\t\t\t\t\t\t$.messager.show({title:'提示',msg:\"登录成功,页面加载中请稍等...\",timeout:2000,showType:'slide'});\r\n");
      out.write("\t\t\t\t\t\t\t\t//跳转到首页\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t//加载首页导航栏\r\n");
      out.write("\t\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\t\t\tasync:false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/menu_user',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//console.info(data);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}); \r\n");
      out.write("\t\t\t\t\t\t\t\t//alert();\r\n");
      out.write("\t\t\t\t\t\t\t\t//$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/index\",function(){});//跳首页controller\r\n");
      out.write("\t\t\t\t\t\t\t//location.href = \"http://192.168.43.21:8088/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\t\t\t\t\t\tlocation.href = \"http://localhost:8088/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t \t\r\n");
      out.write("\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t$.messager.show({title:'提示',msg:\"登录失败，原因是：\" + data.msg,timeout:3000,showType:'slide'});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tlogin:function() {\r\n");
      out.write("\t\t\t\tif (this.checkInput()) {\r\n");
      out.write("\t\t\t\t\tthis.doLogin();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t};\r\n");
      out.write("});\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
