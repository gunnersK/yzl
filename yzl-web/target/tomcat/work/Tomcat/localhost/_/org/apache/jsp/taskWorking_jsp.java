/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-07-16 05:11:17 UTC
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

public final class taskWorking_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write(" <!--  <link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\" /> -->\r\n");
      out.write("<!--   <link rel=\"stylesheet\" href=\"assets/css/font-awesome.min.css\" /> -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"assets/css/jquery-ui-1.10.3.full.min.css\">\r\n");
      out.write("<!--   <link rel=\"stylesheet\" href=\"assets/css/ace.min.css\" />\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"css/style.css\"/> -->\r\n");
      out.write("  <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-easyui-1.4.1/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-easyui-1.4.1/plugins/jquery.messager.js\"></script>\r\n");
      out.write("<!-- 导入jquery核心类库 -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/app/assets/javascripts/jquery-fileupload/vendor/jquery.ui.widget.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/app/assets/javascripts/jquery-fileupload/jquery.iframe-transport.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/app/assets/javascripts/jquery-fileupload/jquery.fileupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"js/taskWorking.js\"></script>\r\n");
      out.write("<title>xxx</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".datagrid-body{\r\n");
      out.write("style:\"width:625px\"\r\n");
      out.write("}\r\n");
      out.write("span.searchbox{\r\n");
      out.write("margin-bottom:-6px;\r\n");
      out.write("}\r\n");
      out.write(".layout-split-west{/* panel layout-panel layout-panel-west  */\r\n");
      out.write("width:155px !important; \r\n");
      out.write("}\r\n");
      out.write(".layout-panel-center{\r\n");
      out.write("left: 156px !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("/*a  upload */\r\n");
      out.write(".a-upload {\r\n");
      out.write("    padding: 4px 10px;\r\n");
      out.write("    height: 20px;\r\n");
      out.write("    line-height: 20px;\r\n");
      out.write("    position: relative;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("    color: #888;\r\n");
      out.write("    background: #fafafa;\r\n");
      out.write("    border: 1px solid #ddd;\r\n");
      out.write("    border-radius: 4px;\r\n");
      out.write("    overflow: hidden;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    *display: inline;\r\n");
      out.write("    *zoom: 1\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".a-upload  input {\r\n");
      out.write("    position: absolute;\r\n");
      out.write("    font-size: 100px;\r\n");
      out.write("    right: 0;\r\n");
      out.write("    top: 0;\r\n");
      out.write("    opacity: 0;\r\n");
      out.write("    filter: alpha(opacity=0);\r\n");
      out.write("    cursor: pointer\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".a-upload:hover {\r\n");
      out.write("    color: #444;\r\n");
      out.write("    background: #eee;\r\n");
      out.write("    border-color: #ccc;\r\n");
      out.write("    text-decoration: none\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".bcbtn{\r\n");
      out.write("border-radius:4px;\r\n");
      out.write("color: gree;\r\n");
      out.write("}\r\n");
      out.write(".qxbtn{\r\n");
      out.write("border-radius:4px;\r\n");
      out.write("}\r\n");
      out.write("/* panel datagrid *//* datagrid-wrap panel-body panel-body-noheader panel-body-noborder */\r\n");
      out.write(".panel{001\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("li {\r\n");
      out.write("list-style-type: none;\r\n");
      out.write("}\r\n");
      out.write("#nav ul {\r\n");
      out.write("display: none\r\n");
      out.write("}\r\n");
      out.write("#nav li:hover ul{\r\n");
      out.write("display: block;\r\n");
      out.write("}\r\n");
      out.write("#hid{\r\n");
      out.write("display: none;\r\n");
      out.write("}\r\n");
      out.write("#pros li ul{\r\n");
      out.write("display: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\t\t<!-- <div class=\"easyui-layout\" style=\" width: 1310px;height: 550px;margin-top: -5px\"> -->\r\n");
      out.write("\t\t    <div data-options=\"region:'west',title:'',split:true,border:false\" style=\"height: 550px;width:142px;margin-left: 5px;\">\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t\t    <ul id=\"tt\" class=\"easyui-tree\" >\r\n");
      out.write("\t\t\t    \r\n");
      out.write("\t\t\t    </ul>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t<div id=\"ce\" data-options=\"region:'center',title:'',border:false\" style=\"padding:5px;background:#eee;\">\r\n");
      out.write("\t\t\t\t<table id=\"tab\" style=\"display:none;height: 530px;\"></table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("    <!-- </div> -->\r\n");
      out.write("    \r\n");
      out.write("    <div id=\"tbs\" style=\"height:28px;dispaly:none;  \">\r\n");
      out.write("    \t\r\n");
      out.write("    \t<label class=\"\" id=\"labelName\" style=\"font-size: 12px;\">造林类别:</label>\r\n");
      out.write("\t\t<input style=\"width: 100px\" id=\"task\" class=\"easyui-combobox\" name=\"dept\" data-options=\"valueField:'mark',textField:'tname',url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/taskWorking/show_tasks'\" />&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\r\n");
      out.write("       \t");
      if (_jspx_meth_shiro_005fhasPermission_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("       \t\r\n");
      out.write("       \t");
      if (_jspx_meth_shiro_005fhasPermission_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("       \t\r\n");
      out.write("       \t");
      if (_jspx_meth_shiro_005fhasPermission_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("       \t&nbsp;&nbsp;\r\n");
      out.write("       \t<label class=\"\" id=\"labelName\" style=\"font-size: 12px;\">年份:</label>\r\n");
      out.write("       \t<input id=year value=");
      out.print(new SimpleDateFormat("yyyy").format(new Date()));
      out.write("; data-options=\"min:2000,max:2050,editable:false\" class=\"easyui-numberspinner\"  style=\"width:70px;;height:19px;margin-top: 0px\">\r\n");
      out.write("   </div>\r\n");
      out.write("    <input type=\"hidden\" id=\"pagex\" />\r\n");
      out.write("\t<input type=\"hidden\" id=\"pagey\" />\r\n");
      out.write("   \r\n");
      out.write("   <div id='tooltip' style=\"display: none;background-color: #F8F8F8;border-radius: 5px\" onmouseout=\"tooltipOut()\" onmouseover=\"tooltipOver()\">\r\n");
      out.write("\t <table  width=\"100\" border=\"0\" style=\"border-color: gray;margin-left: 20px\"><!-- //overflow: hidden; text-overflow: ellipsis; white-space: nowrap; -->\r\n");
      out.write("\t     <tr><td id=\"tdBack\" style=\"margin-left:2px;width: 50px;border-color:#ccc;\"><a href=\"#\" style=\"text-decoration:none;\" onclick=\"beBack()\"><font color=\"red\">被退回</font></a></td></tr>\r\n");
      out.write("    \t <tr><td id=\"tdSubmit\" style=\"margin-left:2px;width: 50px;border-color:#ccc;\"><a href=\"#\" style=\"text-decoration:none;\" onclick=\"beSubmit()\"><font color=\"red\">待提交</font></a></td></tr>\r\n");
      out.write("    \t <tr><td id=\"tdAudit\" style=\"margin-left:2px;width: 50px;border-color:#ccc;\"><a href=\"#\" style=\"text-decoration:none;\" onclick=\"beAudit()\"><font color=\"red\">待审核</font></a></td></tr>\r\n");
      out.write("\t </table>\r\n");
      out.write("\t</div>\r\n");
      out.write("    \r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("    \r\n");
      out.write("    </script>\r\n");
      out.write("   \r\n");
      out.write("    <!-- 详情弹出窗口 -->\r\n");
      out.write("    <div id=\"opt\" class=\"easyui-window\" title=\"操作详情\" style=\"width:1052px;height:400px\" data-options=\"maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true\"> \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<table id=\"optab\"></table>\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("    <!-- 上传窗口 -->\r\n");
      out.write("    <div id=\"upt\" class=\"easyui-window\" title=\"上传窗口\" style=\"width:850px;height:450px\" data-options=\"maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true,closable:false,resizable:false\"> \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<p style=\"margin-top: 20px;margin-left: 45px;position: absolute;\">情况说明:</p>\r\n");
      out.write("\t\t<textarea rows=\"7\" cols=\"100\" id=\"tet\" style=\"margin-top: 20px;margin-left: 100px\"></textarea><br><br>\r\n");
      out.write("\t\t<!-- <a href=\"javascript:;\" class=\"file\">选择文件 -->\r\n");
      out.write("\t\t<a href=\"javascript:;\" class=\"a-upload\" style=\"margin-left: 100px;margin-top: 10px\"><!-- //style=\"margin-left: 100px; -->\r\n");
      out.write("\t\t<input id=\"fileupload\" type=\"file\" name=\"fileName\" data-url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/taskWorking/upFile\" >点击这里上传文件\r\n");
      out.write("\t\t</a><span id=\"spaninfo\"></span>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<table style=\"margin-left: 100px;\" border=\"1\"  bordercolor=\t#FFFFFF  cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<thead >\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th width=\"351px\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-color:gray;\"><font color=\"gray\">文件名</font></th>\r\n");
      out.write("\t\t\t\t\t<th width=\"300px\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-color:gray\"><font color=\"gray\">操作</font></th>\r\n");
      out.write("\t\t\t\t\t<th width=\"100px\" align=\"center\" style=\"display: none;border-color:gray\">id</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t\t<tbody id=\"tbody\">\r\n");
      out.write("\t\t\t</tbody>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<br><br>\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<input style=\"margin-left: 340px; margin-top: 50px; \" type=\"button\" value=\"确定\" onclick=\"save()\" class=\"bcbtn\"> \r\n");
      out.write("\t\t<input style=\"margin-left: 70px\" type=\"button\" value=\"取消\" onclick=\"cancel()\" class=\"qxbtn\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"proceed\" class=\"easyui-window\" title=\"事项窗口\" style=\"width:380px;height:450px\" data-options=\"maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true,closable:false,resizable:false\"> \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<table id=\"ptab\" style=\"margin-left: 100px;width: 365px;height: 300px\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<input style=\"margin-left: 90px; margin-top: 50px; \" type=\"button\" value=\"确定\" onclick=\"save()\" class=\"bcbtn\"> \r\n");
      out.write("\t\t<input style=\"margin-left: 70px\" type=\"button\" value=\"取消\" onclick=\"cancelProceed()\" class=\"qxbtn\">\r\n");
      out.write("\t</div>\r\n");
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

  private boolean _jspx_meth_shiro_005fhasPermission_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f0 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f0.setParent(null);
    // /taskWorking.jsp(132,8) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f0.setName("sys:rwgzz:sh");
    int _jspx_eval_shiro_005fhasPermission_005f0 = _jspx_th_shiro_005fhasPermission_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("       \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-save',plain:true\" onclick=\"auditFunction()\">审核</a>\r\n");
        out.write("       \t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f0);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasPermission_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f1 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f1.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f1.setParent(null);
    // /taskWorking.jsp(136,8) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f1.setName("sys:rwgzz:th");
    int _jspx_eval_shiro_005fhasPermission_005f1 = _jspx_th_shiro_005fhasPermission_005f1.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("       \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-back',plain:true\" onclick=\"backFunction()\">退回</a>\r\n");
        out.write("       \t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f1);
    return false;
  }

  private boolean _jspx_meth_shiro_005fhasPermission_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  shiro:hasPermission
    org.apache.shiro.web.tags.HasPermissionTag _jspx_th_shiro_005fhasPermission_005f2 = (org.apache.shiro.web.tags.HasPermissionTag) _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.get(org.apache.shiro.web.tags.HasPermissionTag.class);
    _jspx_th_shiro_005fhasPermission_005f2.setPageContext(_jspx_page_context);
    _jspx_th_shiro_005fhasPermission_005f2.setParent(null);
    // /taskWorking.jsp(140,8) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f2.setName("sys:rwgzz:tj");
    int _jspx_eval_shiro_005fhasPermission_005f2 = _jspx_th_shiro_005fhasPermission_005f2.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("       \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-ok',plain:true\" onclick=\"submitFunction()\">提交</a>\r\n");
        out.write("       \t");
        int evalDoAfterBody = _jspx_th_shiro_005fhasPermission_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_shiro_005fhasPermission_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fshiro_005fhasPermission_0026_005fname.reuse(_jspx_th_shiro_005fhasPermission_005f2);
    return false;
  }
}
