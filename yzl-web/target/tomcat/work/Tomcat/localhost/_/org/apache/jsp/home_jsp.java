/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-07-11 07:36:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"assets/css/font-awesome.min.css\" />\r\n");
      out.write(" \t\t<link rel=\"stylesheet\" href=\"assets/css/jquery-ui-1.10.3.full.min.css\">\r\n");
      out.write("\t\t<link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"assets/css/ace.min.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"assets/css/ace-rtl.min.css\" />\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"assets/css/ace-skins.min.css\" />\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\"/>\r\n");
      out.write(" <head>\r\n");
      out.write("<!--         <meta charset=\"utf-8\"><link rel=\"icon\" href=\"https://jscdn.com.cn/highcharts/images/favicon.ico\"> -->\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("        <style>\r\n");
      out.write("            /* css 代码  */\r\n");
      out.write("        </style>\r\n");
      out.write("<!--         <script src=\"https://code.highcharts.com.cn/jquery/jquery-1.8.3.min.js\"></script>\r\n");
      out.write("        <script src=\"https://code.highcharts.com.cn/highcharts/highcharts.js\"></script>\r\n");
      out.write("        <script src=\"https://code.highcharts.com.cn/highcharts/modules/exporting.js\"></script>\r\n");
      out.write("        <script src=\"https://code.highcharts.com.cn/highcharts/modules/data.js\"></script>\r\n");
      out.write("        <script src=\"https://code.highcharts.com.cn/highcharts/modules/drilldown.js\"></script> -->\r\n");
      out.write("    </head>\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".index_style{\r\n");
      out.write("\tpadding: 0px;\r\n");
      out.write("}\r\n");
      out.write("#tableDiv{\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write(".pagination{\r\n");
      out.write("\twidth:100%\r\n");
      out.write("}\r\n");
      out.write("#sector{\r\n");
      out.write("/* \r\n");
      out.write("\tmax-width: 500px;\r\n");
      out.write("\tmin-width: 250px;\r\n");
      out.write("\tmargin: 0 auto; */\r\n");
      out.write("\tmax-height: 330px;\r\n");
      out.write("\tmin-height: 260px;\r\n");
      out.write("\twidth: 500px;\r\n");
      out.write("\tmargin: 0 auto; \r\n");
      out.write("\tmargin-top:20px; \r\n");
      out.write("}\r\n");
      out.write("#container{\r\n");
      out.write("\theight: 400px;\r\n");
      out.write("\tmax-width: 2000px;\r\n");
      out.write("\tmin-width: 1000px;\r\n");
      out.write("\tmargin: 0 auto;\r\n");
      out.write("}\r\n");
      out.write("/* 图形 下钻后返回上层的按钮 */\r\n");
      out.write(".highcharts-button-box{\r\n");
      out.write("\theight: 30px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/jquery.min.js\"></script>    \r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/ace-extra.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/highcharts/highcharts.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/highcharts/drilldown.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/highcharts/exporting.js\"></script>\r\n");
      out.write("<!-- \t\t<script src=\"https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js\"></script>  -->\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-easyui-1.4.1/jquery.min.js\"></script>\r\n");
      out.write("  \t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-easyui-1.4.1/plugins/jquery.messager.js\"></script>\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t<!-- 导入jquery核心类库 -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.ocupload-1.1.2.js\"></script>\r\n");
      out.write("\t\t<script src=\"js/text.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t//ajax请求获取数据\r\n");
      out.write("\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/xb/show/highcharts\",{\"year\":$(\"#sectorYear\").val()},function(data){\r\n");
      out.write("\t\tvar drilldownNodeList = new Array();\r\n");
      out.write("\t\t//取柱形图下钻 节点的数据\r\n");
      out.write("\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\tdrilldownNodeList.push(data[i].drilldownNode);\r\n");
      out.write("\t\t\tdata[i].drilldownNode==null\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//柱形图初始化\r\n");
      out.write("\t\tcontarinerInit(data,drilldownNodeList);\r\n");
      out.write("\t\t//sectorInint(data,drilldownNodeList);\r\n");
      out.write("\t\t/* 饼形图初始化 */\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//ajax请求获取饼形图的数据 \r\n");
      out.write("\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/xb/show/pie\",{\"year\":$(\"#sectorYear\").val()},function(data){\r\n");
      out.write("\t\tvar drilldownNodeList = new Array();\r\n");
      out.write("\t\t//取饼形图下钻 节点的数据\r\n");
      out.write("\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\tdrilldownNodeList.push(data[i].drilldownNode);\r\n");
      out.write("\t\t\tdata[i].drilldownNode==null\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//饼形图初始化\r\n");
      out.write("\t\tsectorInint(data,drilldownNodeList);\r\n");
      out.write("\t\t/* 饼形图初始化 */\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t//柱形图初始化\r\n");
      out.write("\tfunction contarinerInit(data,drilldownNodeList){\r\n");
      out.write("    \tHighcharts.setOptions({\r\n");
      out.write("\t\t\tlang: {\r\n");
      out.write("\t\t\t\tdrillUpText: '《返回',  // 下钻后返回按钮\r\n");
      out.write("\t\t\t\tnoData:\"数据为空\",\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// 柱形图  \r\n");
      out.write("\t\tHighcharts.chart('container', {\r\n");
      out.write("\t\t\tcredits:{\r\n");
      out.write("\t\t\t     enabled: false // 禁用版权信息\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tchart: {\r\n");
      out.write("\t\t\t\ttype: 'column'\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\ttitle: {\r\n");
      out.write("\t\t\t\ttext:  '<span style=\"font-size:21px\">'+$(\"#containerYear\").val() +'</span>年造林完成比例', \r\n");
      out.write("\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsubtitle: {\r\n");
      out.write("\t\t\t\ttext: '',\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\txAxis: {\r\n");
      out.write("\t\t\t\ttype: 'category'\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tyAxis: {\r\n");
      out.write("\t\t\t\ttitle: {\r\n");
      out.write("\t\t\t\t\ttext: '总的造林任务完成比例'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tlegend: {\r\n");
      out.write("\t\t\t\tenabled: false\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tplotOptions: {\r\n");
      out.write("\t\t\t\tseries: {\r\n");
      out.write("\t\t\t\t\tborderWidth: 0,\r\n");
      out.write("\t\t\t\t\tdataLabels: {\r\n");
      out.write("\t\t\t\t\t\tenabled: true,\r\n");
      out.write("\t\t\t\t\t\tformat: '{point.y:.1f}%'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\ttooltip: {\r\n");
      out.write("\t\t\t\theaderFormat: '<span style=\"font-size:11px\">{series.name}</span><br>',\r\n");
      out.write("\t\t\t\tpointFormat: '<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%'\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tseries: [{\r\n");
      out.write("\t\t\t\tname: '广西营造林',\r\n");
      out.write("\t\t\t\tcolorByPoint: true,\r\n");
      out.write("\t\t\t\tdata: data\r\n");
      out.write("\t\t\t}],\r\n");
      out.write("\t\t\tdrilldown: {\r\n");
      out.write("\t\t\t\tseries:  drilldownNodeList,\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t//按钮样式\r\n");
      out.write("\t\t\tnavigation: {\r\n");
      out.write("\t\t\t\tbuttonOptions: {\r\n");
      out.write("\t\t\t\t\ttheme: {\r\n");
      out.write("\t\t\t\t\t\t// Good old text links\r\n");
      out.write("\t\t\t\t\t\tstyle: {\r\n");
      out.write("\t\t\t\t\t\t\tcolor: 'blue',\r\n");
      out.write("\t\t\t\t\t\t\ttextDecoration: 'underline'\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t//打印 下载\r\n");
      out.write("\t\t\texporting: {\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tbuttons: {\r\n");
      out.write("\t\t\t\t\tcontextButton: {\r\n");
      out.write("\t\t\t\t\t\tenabled: false\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\texportButton: {\r\n");
      out.write("\t\t\t\t\t\ttext: '下载',\r\n");
      out.write("\t\t\t\t\t\t// Use only the download related menu items from the default context button\r\n");
      out.write("\t\t\t\t\t\tmenuItems: Highcharts.getOptions().exporting.buttons.contextButton.menuItems.splice(2)\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tprintButton: {\r\n");
      out.write("\t\t\t\t\t\ttext: '打印',\r\n");
      out.write("\t\t\t\t\t\tonclick: function () {\r\n");
      out.write("\t\t\t\t\t\t\tthis.print();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t//饼形图初始化\r\n");
      out.write("\tfunction sectorInint(data,drilldownNodeList){\r\n");
      out.write("\t\t\t\t        // 创建图例\r\n");
      out.write("\t\t\t        \tHighcharts.setOptions({\r\n");
      out.write("\t\t\t\t\t\t\tlang: {\r\n");
      out.write("\t\t\t\t\t\t\t\tdrillUpText: '《返回',\r\n");
      out.write("\t\t\t\t\t\t\t\tnoData:\"数据为空\",\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t        Highcharts.chart('sector',{\r\n");
      out.write("\t\t\t\t\t\t\tcredits:{\r\n");
      out.write("\t\t\t\t\t\t\t     enabled: false // 禁用版权信息\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t            chart: {\r\n");
      out.write("\t\t\t\t                type: 'pie'\r\n");
      out.write("\t\t\t\t            },\r\n");
      out.write("\t\t\t\t\t\t\ttitle: {\r\n");
      out.write("\t\t\t\t\t\t\t\ttext: \"<span style='mfont-size:15px;'><span>\"+$('#sectorYear').val() +\"</span>年各地区造林完成量所占比例\",\r\n");
      out.write("\t\t\t\t\t\t\t\t//x:-20\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t            plotOptions: {\r\n");
      out.write("\t\t\t\t                series: {\r\n");
      out.write("\t\t\t\t                    dataLabels: {\r\n");
      out.write("\t\t\t\t                        enabled: true,\r\n");
      out.write("\t\t\t\t                        format: '{point.name}: {point.y:.1f}%',\r\n");
      out.write("\t\t\t\t                    }\r\n");
      out.write("\t\t\t\t                }\r\n");
      out.write("\t\t\t\t            },\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t            tooltip: {\r\n");
      out.write("\t\t\t\t                headerFormat: '<span style=\"font-size:11px\">{series.name}</span><br>',\r\n");
      out.write("\t\t\t\t                pointFormat: '<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.1f}%'\r\n");
      out.write("\t\t\t\t            },\r\n");
      out.write("\t\t\t\t\t\t\tseries: [{\r\n");
      out.write("\t\t\t\t\t\t\t\tname: '广西',\r\n");
      out.write("\t\t\t\t\t\t\t\tcolorByPoint: true,\r\n");
      out.write("\t\t\t\t\t\t\t\tdata: data\r\n");
      out.write("\t\t\t\t\t\t\t}],\r\n");
      out.write("\t\t\t\t            drilldown: {//下钻的数据\r\n");
      out.write("\t\t\t\t                series: drilldownNodeList,\r\n");
      out.write("/* \t\t\t\t\t\t\t\ttheme: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfill: 'white',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t'stroke-width': 1,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstroke: 'silver',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tr: 0,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstates: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thover: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tfill: '#bada55'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tselect: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tstroke: '#039',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tfill: '#bada55'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t} */\r\n");
      out.write("\t\t\t\t            },\r\n");
      out.write("\t\t\t\t\t\t\t//按钮样式\r\n");
      out.write("\t\t\t\t\t\t\tnavigation: {\r\n");
      out.write("\t\t\t\t\t\t\t\tbuttonOptions: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttheme: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t// Good old text links\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tstyle: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tcolor: 'blue',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttextDecoration: 'underline'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t            exporting: {\r\n");
      out.write("\t\t\t\t\t\t\t\tbuttons: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcontextButton: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tenabled: false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\texportButton: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttext: '下载',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tx:-30,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ty:-15,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tonclick: function () {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tthis.exportChart();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\tprintButton: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttext: '打印',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tx:9,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ty:-15,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tonclick: function () {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tthis.print();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t        });\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t//柱形图点击年份的时候触发\r\n");
      out.write("\t$(\"#containerYear\").numberspinner({\r\n");
      out.write("\t\t\"onChange\":function(){\r\n");
      out.write("\t\t\t//ajax请求获取数据\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/xb/show/highcharts\",{\"year\":$(\"#containerYear\").val()},function(data){\r\n");
      out.write("\t\t\t\tvar drilldownNodeList = new Array();\r\n");
      out.write("\t\t\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\t\t\tdrilldownNodeList.push(data[i].drilldownNode);\t\r\n");
      out.write("\t\t\t\t\tdata[i].drilldownNode==null\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//柱形图初始化\r\n");
      out.write("\t\t\t\tcontarinerInit(data,drilldownNodeList);\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t//饼形图点击年份的时候触发\r\n");
      out.write("\t$(\"#sectorYear\").numberspinner({\r\n");
      out.write("\t\t\"onChange\":function(){\r\n");
      out.write("\t\t\t//ajax请求获取数据\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/xb/show/pie\",{\"year\":$(\"#sectorYear\").val()},function(data){\r\n");
      out.write("\t\t\t\tvar drilldownNodeList = new Array();//存储下钻的数据\r\n");
      out.write("\t\t\t\t//取饼形图下钻 节点的数据\r\n");
      out.write("\t\t\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\t\t\tdrilldownNodeList.push(data[i].drilldownNode);\r\n");
      out.write("\t\t\t\t\tdata[i].drilldownNode==null\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//饼形图初始化\r\n");
      out.write("\t\t\t\tsectorInint(data,drilldownNodeList);\r\n");
      out.write("\t\t\t\t/* 饼形图初始化 */\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write(" \t$(\"#DataTemplateTable\").datagrid({\r\n");
      out.write("\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/backlogTaskIssued/pageQuery',\r\n");
      out.write("\t\tfit:true,\r\n");
      out.write("\t\t//url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+\"&ZLLB=\"+ZLLB+\"&usr=\"+usr,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\tcheckOnSelect:false,\r\n");
      out.write("\t\tfitColumns:false,\r\n");
      out.write("\t\tborder : false,\r\n");
      out.write("\t\tstriped : true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tfrozenColumns:[\r\n");
      out.write("\t\t\t\t\t\t[\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',width:100,title:'序号',  rowspan:'2',field:'id',checkbox:true},//frozen:true,\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'待办事项',\tfield:'name'},\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'数量',field:'number'},\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'时间',field:'updateTime'},\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'市',field:'city'},\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'县/区',field:'county'},\r\n");
      out.write("\t\t\t\t\t\t\t{align:'center',rowspan:2,width:100,title:'查看',field:'check'}\r\n");
      out.write("\t\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t\t],\r\n");
      out.write("    \tonClickCell: function(index,field,value){\r\n");
      out.write("    \t\t//截取jsp文件名\r\n");
      out.write("\t\t\tvar begin = value.indexOf(\"value='&\");//获取前索引\r\n");
      out.write("\t\t\tvar end = value.indexOf(\"&'\");//获后前索引\r\n");
      out.write("\t\t\tvar valJsp = value.substring(begin,end);//截取\r\n");
      out.write("\t\t\tvar val = valJsp.split(\"value='&\")[1];\r\n");
      out.write("   \t\t\tvar jsp = val;\r\n");
      out.write("   \t\t\t//截取状态\r\n");
      out.write("   \t\t\tidValueBegin = value.indexOf(\"id='\");\r\n");
      out.write("   \t\t\tidValueEnd = value.indexOf(\">'\");\r\n");
      out.write("   \t\t\tconsole.log(\"value=\"+value);\r\n");
      out.write("   \t\t\tvar idValue = value.substring(idValueBegin,idValueEnd);//截取\r\n");
      out.write("   \t\t\tvar statu = idValue.split(\"id='\")[1];//去掉前缀\r\n");
      out.write("   \t\t\tvar cname=\"待办任务\"\r\n");
      out.write("   \t\t\tif(statu==1){\r\n");
      out.write("    \t\t\tcname = \"待审核的任务\";\r\n");
      out.write("   \t\t\t}else if(statu==3){\r\n");
      out.write("   \t\t\t\tcname = \"被退回的任务\";\r\n");
      out.write("   \t\t\t}\r\n");
      out.write("    \t\tvar _iframe = window.parent;\r\n");
      out.write("    \t\t_iframe.$('#nav_list').find('li.home').removeClass('active');\r\n");
      out.write("    \t\t//_iframe.$(\"[name='taskIssued.jsp']\").parent().addClass('active');;\r\n");
      out.write("    \t\t_iframe.$(\"#iframe\").attr(\"src\", jsp).ready();\r\n");
      out.write("    \t\t_iframe.$(\"#iframe\").attr(\"value\", statu).ready();//设置需要查询的状态\r\n");
      out.write("    \t\t_iframe.$(\"#Bcrumbs\").attr(\"href\", jsp).ready();\r\n");
      out.write("    \t\t_iframe.$(\".Current_page a\").attr('href', jsp).ready();\r\n");
      out.write("    \t\t_iframe.$(\".Current_page\").html(cname).ready();\r\n");
      out.write("    \t\t_iframe.$(\"#parentIframe\").html(\"\").css(\"display\",\r\n");
      out.write("    \t\t\t\t\"none\").ready();\r\n");
      out.write("    \t\t\t\r\n");
      out.write("    \t}\r\n");
      out.write("\t}); \r\n");
      out.write(" \t\r\n");
      out.write("\r\n");
      out.write("  $(function(){\r\n");
      out.write("\t \t//获取当前浏览器的类型\r\n");
      out.write("\t\tvar userAgent = navigator.userAgent;\r\n");
      out.write("\t\tconsole.info(userAgent);\r\n");
      out.write("\t\tif(userAgent.indexOf(\"Firefox\") > -1){//判断是否是火狐\r\n");
      out.write("\t\t\t//$(\"#tet\").css(\"width\",\"335px\");\r\n");
      out.write("\t\t$(\"#upt\").css(\"height\",\"400px\");\r\n");
      out.write("\t\t$(\"#tet\").css(\"width\",\"650px\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(userAgent.indexOf(\"Trident\") > -1){\r\n");
      out.write("\t\t\t$(\"#upt\").css(\"height\",\"400px\");\r\n");
      out.write("\t    \t$(\"#tet\").css(\"width\",\"650px\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(userAgent.indexOf(\"QQBrowser\") > -1){//QQBrowser frame xjdd_style\r\n");
      out.write("\t\t\t//console.info(userAgent.indexOf(\"QQBrowser\"));: ;: ;\r\n");
      out.write("\t\t\t$(\".col-xs-12\").css(\"width\",\"1700px\");\r\n");
      out.write("\t\t\t$(\".highcharts-root\").css(\"width\",\"1700px\");\r\n");
      out.write("\t\t\t$(\"#sector\").css(\"margin-left\",\"100px\");\r\n");
      out.write("\t\t\t$(\"#highcharts-jil4tm2-3\").css(\"width\",\"1200px\");\r\n");
      out.write("\t\t\t$(\"#container\").css(\"width\",\"1300px\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(userAgent.indexOf(\"InfoPath\") > -1){//QQBrowser frame xjdd_style\r\n");
      out.write("\t\t\t//console.info(userAgent.indexOf(\"QQBrowser\"));: ;: ;: ;\r\n");
      out.write("\t\t\t$(\".col-xs-12\").css(\"width\",\"1700px\");\r\n");
      out.write("\t\t\t$(\".highcharts-root\").css(\"width\",\"1700px\");\r\n");
      out.write("\t\t\t$(\"#sector\").css(\"margin-left\",\"100px\");\r\n");
      out.write("\t\t\t$(\"#highcharts-jil4tm2-3\").css(\"width\",\"1200px\");\r\n");
      out.write("\t\t\t$(\"#container\").css(\"width\",\"1300px\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(userAgent.indexOf(\"Chrome\") > -1){//谷歌\r\n");
      out.write("\t\t\t//console.info(userAgent.indexOf(\"QQBrowser\"));: ;: ;\r\n");
      out.write("\t\t\t//$(\".frame xjdd_style\").css(\"width\",\"555px\");!important\r\n");
      out.write("\t\t\t$(\".xjdd_style\").css(\"width\",\"555px\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(userAgent.indexOf(\"Firefox\") > -1){//火狐\r\n");
      out.write("\t\t\t//console.info(userAgent.indexOf(\"QQBrowser\"));: ;: ;Firefox\r\n");
      out.write("\t\t\t//$(\".frame xjdd_style\").css(\"width\",\"555px\");!important\r\n");
      out.write("\t\t\t$(\".xjdd_style\").css(\"width\",\"555px\");\r\n");
      out.write("\t\t}\r\n");
      out.write("  })\r\n");
      out.write(" \t\r\n");
      out.write("/*  \t$(\"#taskissued\").click(function(){\r\n");
      out.write(" \t\t\r\n");
      out.write(" \t}); */\r\n");
      out.write("\r\n");
      out.write(" \t\r\n");
      out.write("/*  \t$(\"#taskWorking\").click(function(){\r\n");
      out.write("\t\tvar cid = 'taskIssued.jsp';\r\n");
      out.write("\t\tvar cname = \"任务下发\";\r\n");
      out.write("\t\t$('#nav_list').find('li.home').removeClass('active');\r\n");
      out.write("\t\t$(\"[name='taskIssued.jsp']\").parent().addClass('active');;\r\n");
      out.write("\t\t$(\"#iframe\").attr(\"src\", cid).ready();\r\n");
      out.write("\t\t$(\"#Bcrumbs\").attr(\"href\", cid).ready();\r\n");
      out.write("\t\t$(\".Current_page a\").attr('href', cid).ready();\r\n");
      out.write("\t\t$(\".Current_page\").html(cname).ready();\r\n");
      out.write("\t\t$(\"#parentIframe\").html(\"\").css(\"display\",\r\n");
      out.write("\t\t\t\t\"none\").ready();\r\n");
      out.write(" \t}); */\r\n");
      out.write("});\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("        <style type=\"text/css\">\r\n");
      out.write("         html { overflow-x:hidden; }\r\n");
      out.write("         .datagrid-view{\r\n");
      out.write("         /* margin-left:-10px;margin-top:-10px;\r\n");
      out.write("         width: 700px !important; */\r\n");
      out.write("         }\r\n");
      out.write("         .datagrid-pager{\r\n");
      out.write("         \t/* margin-top: 20px !important;margin-left:-10px !important; */\r\n");
      out.write("         \t\r\n");
      out.write("         }\r\n");
      out.write("         .datagrid-wrap{\r\n");
      out.write("         width: 690px !important;\r\n");
      out.write("         }\r\n");
      out.write("        </style>\r\n");
      out.write("\t<body >\r\n");
      out.write("<div class=\"page-content\" id=\"tt\">\r\n");
      out.write("\t\t<!-- <div class=\"page-header\">\r\n");
      out.write("\t\t\t<h1>首页展示<small></small></h1>\r\n");
      out.write("\t\t</div>/.page-header width:1284px;-->\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div class=\"col-xs-12\" style=\"width:1313px;\">\t\t\t\t\t\t\t\r\n");
      out.write("\t                         <div class=\"index_style\" style=\"float:left\">\r\n");
      out.write("\t                        <!-- 待办事项padding:5px; -->\r\n");
      out.write("\t                        \t<div id=\"tableDIV\" style=\"border:1px solid #CCC;position: absolute;z-index:1000;width:700px;height:340px;margin-left: 15px;margin-top: 10px;\">\r\n");
      out.write("\t                        \t\t\t <table  id='DataTemplateTable' style=\"displae:none;\"></table>\r\n");
      out.write("\t                        \t</div> \t\t\t\r\n");
      out.write("\t                       \t\t\t<!-- 饼形图 width:510px; -->\r\n");
      out.write("\t                     \t\t<div style=\"position: absolute;z-index:1000;width:945px;height:340px;margin-left:735px;margin-top:10px;min-height:300px;max-height:400px;\" class=\"frame xjdd_style\">\r\n");
      out.write("\t                         \t\t <!-- <div style=\"margin-top:-30px;background-color:initial\" class=\"n_tit\"><span>年各地区造林完成量所占比例</span><a href=\"/news/index.html\" class=\"more\"></a></div>  -->\r\n");
      out.write("\t                         \t  \t<div style=\"margin-top: -35px;margin-left: -30px;\">\r\n");
      out.write("\t                         \t\t<label   class=\"label_name\"><b>年份:</b></label>\r\n");
      out.write("\t  \t\t\t\t\t\t\t<input style=\"width:110px;height:19px;\"  id=\"sectorYear\" value=");
      out.print(new SimpleDateFormat("yyyy").format(new Date()));
      out.write("; data-options=\"min:2000,max:2050,editable:false\" class=\"easyui-numberspinner\"  />\r\n");
      out.write("\t                     \t       </div>\r\n");
      out.write("\t                     \t         <!-- <div style=\"margin-top:-30px;background-color:initial;font-size: 24px;\" class=\"\"><font id=\"showSectorYear\"></font><span>年各地区造林完成量所占比例</span><a href=\"/news/index.html\" class=\"more\"></a></div>  -->\r\n");
      out.write("\t                           \t<div style=\"margin-left: -50px;\"  id=\"sector\" class=\"content\"></div>\r\n");
      out.write("\t                         \t</div> \r\n");
      out.write("\t                        </div>\r\n");
      out.write("\t                           <div  style=\"border:1px solid #CCC;margin-top:360px;absolute;margin-left:15px;z-index:1000;\" class=\"frame ddgl_style\">\r\n");
      out.write("\t                            \t<!-- 柱形图  width:800px;-->\r\n");
      out.write("\t                            \t<div style=\"margin-top:15px;width:1313px;\">\r\n");
      out.write("\t                          \t \t<label style=\"margin-top:0px;margin-left:10px;\" class=\"label_name\"> <b>年份:</b></label>\r\n");
      out.write("\t  \t\t\t\t\t\t\t\t<input id=\"containerYear\" value=");
      out.print(new SimpleDateFormat("yyyy").format(new Date()));
      out.write("; data-options=\"min:2000,max:2050,editable:false\" class=\"easyui-numberspinner\"  style=\"margin-top:0px;width:110px;;height:19px;\" />\r\n");
      out.write("\t                           \t</div>\r\n");
      out.write("\t                           <!--  \t<span  class=\"title_name\">柱形图</span> -->\r\n");
      out.write("\t                            \t<div  style=\"width:1000px\" id=\"container\" class=\"content\"> </div>\r\n");
      out.write("\t                            </div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("           \r\n");
      out.write("\t</div>\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("function read(){\r\n");
      out.write("\twindow.location.href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/taskIssued.jsp\";\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
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