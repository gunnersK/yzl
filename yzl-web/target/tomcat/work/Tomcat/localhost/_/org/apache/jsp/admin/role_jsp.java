/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-07-14 13:44:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class role_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    \r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<!-- 导入jquery核心类库 -->\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<!-- 导入easyui类库 -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/portal.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/default.css\">\t\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.portal.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.cookie.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"\r\n");
      out.write("\ttype=\"text/javascript\"></script>\r\n");
      out.write("<!-- 导入ztree类库 -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ztree/zTreeStyle.css\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ztree/jquery.ztree.all-3.5.js\"\r\n");
      out.write("\ttype=\"text/javascript\"></script>\t\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var Mids=null;\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//初始化树形控件\r\n");
      out.write("/* \t\tvar setting = {\r\n");
      out.write("\t\t\t    async: {\r\n");
      out.write("\t\t\t        enable: true,\r\n");
      out.write("\t\t\t        url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/function/getParentNode',\r\n");
      out.write("\t\t\t        autoParam:[\"functionTree\",\"nameq\"],\r\n");
      out.write("\t\t\t        otherParam:{\"functionTree\":\"namec\"},\r\n");
      out.write("\t\t\t        dataFilter: filter,\r\n");
      out.write("\t\t\t        type: \"get\"\r\n");
      out.write("\t\t\t    },\r\n");
      out.write("\t\t\t    callback: {\r\n");
      out.write("\t\t\t        beforeAsync: beforeAsync,\r\n");
      out.write("\t\t\t        onAsyncSuccess: onAsyncSuccess,\r\n");
      out.write("\t\t\t    },\r\n");
      out.write("\t\t\t\tcheck : {\r\n");
      out.write("\t\t\t\t\tenable : true,\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t};\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\tfunction filter(treeId, parentNode, childNodes) {\r\n");
      out.write("\t\t    if (!childNodes) return null;\r\n");
      out.write("\t\t    for (var i=0, l=childNodes.length; i<l; i++) {\r\n");
      out.write("\t\t        childNodes[i].name = childNodes[i].name.replace(/\\.n/g, '.');\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    return childNodes;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction beforeAsync() {\r\n");
      out.write("\t\t\t    curAsyncCount++;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction onAsyncSuccess(event, treeId, treeNode, msg) {\r\n");
      out.write("\t\t\t    curAsyncCount--;\r\n");
      out.write("\t\t\t    if (curStatus == \"expand\") {\r\n");
      out.write("\t\t\t        expandNodes(treeNode.children);\r\n");
      out.write("\t\t\t    } else if (curStatus == \"async\") {\r\n");
      out.write("\t\t\t        asyncNodes(treeNode.children);\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\r\n");
      out.write("\t\t\t    if (curAsyncCount <= 0) {\r\n");
      out.write("\t\t\t        curStatus = \"\";\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar curStatus = \"init\", curAsyncCount = 0, goAsync = false;\r\n");
      out.write("\t\t\tfunction expandAll() {\r\n");
      out.write("\t\t\t\tif (!check()) {\r\n");
      out.write("\t\t\t        return;\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t    var zTree = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t    expandNodes(zTree.getNodes());\r\n");
      out.write("\t\t\t    if (!goAsync) {\r\n");
      out.write("\t\t\t        curStatus = \"\";\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tfunction expandNodes(nodes) {\r\n");
      out.write("\t\t\t    if (!nodes) return;\r\n");
      out.write("\t\t\t    curStatus = \"expand\";\r\n");
      out.write("\t\t\t    var zTree = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t    for (var i=0, l=nodes.length; i<l; i++) {\r\n");
      out.write("\t\t\t    \tMids.push(nodes[i].id);\r\n");
      out.write("\t\t\t        zTree.expandNode(nodes[i], true, false, false);//展开节点就会调用后台查询子节点\r\n");
      out.write("\t\t\t        if (nodes[i].isParent && nodes[i].zAsync) {\r\n");
      out.write("\t\t\t            expandNodes(nodes[i].children);//递归\r\n");
      out.write("\t\t\t        } else {\r\n");
      out.write("\t\t\t            goAsync = true;\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction check() {\r\n");
      out.write("\t\t\t    if (curAsyncCount > 0) {\r\n");
      out.write("\t\t\t        return false;\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t\t    return true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t$(document).ready(function(){\r\n");
      out.write("\t\t\t    $.fn.zTree.init($(\"#functionTree\"), setting);\r\n");
      out.write("\t\t\t    setTimeout(function(){\r\n");
      out.write("\t\t\t        expandAll(\"functionTree\");\r\n");
      out.write("\t\t\t    },300);//延迟加载\r\n");
      out.write("\t\t\t}); */\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tMids = new Array();//定义一个数组存放MenuId\t\r\n");
      out.write("\t\t\t//递归查询所有树节点\r\n");
      out.write(" \t\t\tfunction FindIdsByRecursion(row){\r\n");
      out.write("\t\t\t\t//获取子节点\r\n");
      out.write("\t\t\t\tvar children = row.children;\r\n");
      out.write("\t\t\t\tif(children!=null||children != \"\"){\r\n");
      out.write("\t\t\t\t\t//把父节点添加到数组\r\n");
      out.write("\t\t\t\t\tMids.push(row.id);\r\n");
      out.write("\t\t\t\t\tconsole.log(children.length);\r\n");
      out.write("\t\t\t\t\tfor(var i=0;i<children.length;i++){\r\n");
      out.write("\t\t\t\t\t\tFindIdsByRecursion(children[i]);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//如果没有子节点直接添加到数组\r\n");
      out.write("\t\t\t\t\tMids.push(row.id);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 授权树初始化\r\n");
      out.write("\t\tvar setting = {\r\n");
      out.write("\t\t\tdata : {\r\n");
      out.write("\t\t\t\tkey : {\r\n");
      out.write("\t\t\t\t\ttitle : \"t\",\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tsimpleData : {\r\n");
      out.write("\t\t\t\t\tenable : true\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcheck : {\r\n");
      out.write("\t\t\t\tenable : true,\r\n");
      out.write("\t\t\t\tnocheckInherit: true,\r\n");
      out.write("\t\t\t}/* ,\r\n");
      out.write("\t\t\tcallback: { \r\n");
      out.write("\t\t\t\tonClick: function (e, treeId, treeNode, clickFlag) { \r\n");
      out.write("\t\t\t\t\tvar zTree = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t\t\tconsole.log(e+\" || \"+ treeId+\" || \"+ treeNode+\" || \"+ clickFlag);\r\n");
      out.write("\t\t\t\t\tzTree.checkNode(treeNode, !treeNode.checked, true); \r\n");
      out.write("\t\t\t\t}  \r\n");
      out.write("\t\t\t} */\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/function/getParentNode',\r\n");
      out.write("\t\t\ttype : 'POST',\r\n");
      out.write("\t\t\tdataType : 'json',\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t//console.log(\"树初始化\");\r\n");
      out.write("\t\t\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\t\t\t//获取所有数据id 用作回显数据\r\n");
      out.write("\t\t\t\t\tFindIdsByRecursion(data[i]);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\t\t\tif(data[i].icon != null){\r\n");
      out.write("\t\t\t\t\t\tdata[i].icon=null;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.fn.zTree.init($(\"#functionTree\"), setting, data);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\terror : function(msg) {\r\n");
      out.write("\t\t\t\t//alert('树加载异常!');\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//alert('树加载异常!');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t// 数据表格属性\r\n");
      out.write("\t\t$(\"#grid\").datagrid({\r\n");
      out.write("\t\t\ttoolbar :\"#tb\",\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/role/pageQuery',\r\n");
      out.write("\t\t\tpageList: [10,30,50],\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\tfit:true,\r\n");
      out.write("\t\t\tcolumns : [[\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t    \tcheckbox : true,\r\n");
      out.write("\t\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\t\ttitle : '编号',\r\n");
      out.write("\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'name',\r\n");
      out.write("\t\t\t\t\ttitle : '名称',\r\n");
      out.write("\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t}, \r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'description',\r\n");
      out.write("\t\t\t\t\ttitle : '描述',\r\n");
      out.write("\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'createTime',\r\n");
      out.write("\t\t\t\t\ttitle : '创建时间',\r\n");
      out.write("\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t]]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("</script>\t\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\">\r\n");
      out.write("\t<div data-options=\"region:'center'\">\r\n");
      out.write("\t\t<table id=\"grid\"></table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"tb\">\r\n");
      out.write("     \t\r\n");
      out.write("    \t");
      if (_jspx_meth_shiro_005fhasPermission_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    \t\r\n");
      out.write("    \t");
      if (_jspx_meth_shiro_005fhasPermission_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    \t\r\n");
      out.write("    \t");
      if (_jspx_meth_shiro_005fhasPermission_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    \t\r\n");
      out.write("    </div>\r\n");
      out.write("\t<div id=\"loading\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 修改窗口 -->\r\n");
      out.write("\t\t<div id=\"modifyDiv\"   class=\"easyui-window\" region=\"center\" data-options=\"region:'center',closable:false,draggable:true,closed:true,iconCls:'icon-edit',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true\" style=\"overflow-y:auto;height: 600px; width:450px;\" border=\"false\">\r\n");
      out.write("\t\t\t<form id=\"modifyForm\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/role/updateRole\" method=\"post\">\r\n");
      out.write("\t\t\t\t<table   id=\"modifyTab\" fit=\"true\"   width=\"400px\" height=\"500px\"  class=\"table-edit\"   align=\"center\">\r\n");
      out.write("\t\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">角色信息</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>名称</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name='id'/>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"name\" class=\"easyui-validatebox\" data-options=\"required:true\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>描述</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<textarea name=\"description\" rows=\"4\" cols=\"60\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>授权</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" id=\"MenuIds\" name=\"functionIds\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<ul id=\"functionTree\" class=\"ztree\"></ul>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t   <br><br><br>\r\n");
      out.write("\t\t     <button style=\"margin-left: 250px\" class=\"btn btn-primary\" type=\"button\" id=\"modifyBtn\">保存</button>\r\n");
      out.write("\t\t     <button style=\"margin-left: 50px\" class=\"btn btn-primary\" type=\"button\" id=\"cancelBtn\">取消</button>\r\n");
      out.write("\t\t     <script type=\"text/javascript\">\r\n");
      out.write("\t\t     //删除\r\n");
      out.write("\t\t     function deldata(){\r\n");
      out.write("\t\t\t\t\tvar rows = $(\"#grid\").datagrid('getSelections');\r\n");
      out.write("\t\t\t\t\tif(rows.length>0){\r\n");
      out.write("\t\t\t\t\t\t$.messager.confirm('提示','你确定删除这'+ rows.length +'条数据吗？',function(r){\r\n");
      out.write("\t\t\t\t\t\t\tif(r){\r\n");
      out.write("\t\t\t\t\t\t\t\tvar array = new Array();\r\n");
      out.write("\t\t\t\t\t\t\t\tfor(var i=0;i<rows.length;i++){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//把所有的id添加到集合中\r\n");
      out.write("\t\t\t\t\t\t\t\t\tarray.push(rows[i].id);\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t//用,把id隔开\r\n");
      out.write("\t\t\t\t\t\t\t\tvar ids = array.join(\",\");\r\n");
      out.write("\t\t\t\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/delete/role\",{\"ids\":ids},function(data){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t$(\"#grid\").datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示','<h1>请最少选择一行进行删除!</h1>','warning');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t     \r\n");
      out.write("\t\t     //修改\r\n");
      out.write("\t\t     function update(){\r\n");
      out.write("\t\t\t\t\tvar rows = $(\"#grid\").datagrid('getSelections');\r\n");
      out.write("\t\t\t\t\tvar mid = new Array(Mids);\r\n");
      out.write("\t\t\t\t\t//console.info(mid);\r\n");
      out.write("\t\t\t\t\tif(rows.length == 1){\r\n");
      out.write("\t\t\t\t\t\tvar rowsData  = $(\"#grid\").datagrid(\"getSelected\");\r\n");
      out.write("\t\t\t\t\t\t$(\"#modifyDiv\").window('open');\r\n");
      out.write("\t\t\t\t\t\t$(\"#modifyTab\").form('load',rowsData);\r\n");
      out.write("\t\t\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/function/getFunctionByRoleId\",{\"roleId\":rowsData.id},function(data){\r\n");
      out.write("\t\t\t\t\t\t\t//关联角色的权限数据回显，并设置为选中状态\r\n");
      out.write("\t\t\t\t\t\t\tvar rows = data.data;\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif(rows != null && rows.length>0){\r\n");
      out.write("\t\t\t\t\t\t\t\tfor(var i=0;i<rows.length;i++){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfor(var j=0;j<Mids.length;j++){//console.info(1);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//判断id是否相同\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tif(rows[i].id==Mids[j]){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t//获取当前树对象\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tvar treeObj = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t//获取当前节点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tvar node = treeObj.getNodesByParam(\"id\",rows[i].id);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t//勾选当前选中的节点\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\ttreeObj.checkNode(node[0],true,false);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t$.messager.alert('提示','<h1>请选择一行进行编辑!</h1>','warning');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t     //添加\r\n");
      out.write("\t\t     function add(){\r\n");
      out.write("\t\t\t\t\tlocation.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/admin/role_add.jsp';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t     //取消\r\n");
      out.write("\t\t\t \t\t$(\"#cancelBtn\").click(function(){\r\n");
      out.write("\t\t\t \t\t\t$(\"#modifyDiv\").window('close');\r\n");
      out.write("\t\t\t\t\t\t//获取当前树对象\r\n");
      out.write("\t\t\t\t\t\tvar treeObj = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t\t\t\ttreeObj.checkAllNodes(false);\r\n");
      out.write("\t\t\t \t\t});\r\n");
      out.write("\t\t\t     \r\n");
      out.write("\t\t\t     \t//修改\r\n");
      out.write("\t\t\t\t\t$(\"#modifyBtn\").click(function(){\r\n");
      out.write("\t\t\t\t\t\t//表单校验\r\n");
      out.write("\t\t\t\t\t\tvar trObj = $.fn.zTree.getZTreeObj(\"functionTree\");\r\n");
      out.write("\t\t\t\t\t\t//获取所有被勾选的权限\r\n");
      out.write("\t\t\t\t\t\tvar nodes = trObj.getCheckedNodes(true);\r\n");
      out.write("\t\t\t\t\t\t//定义一个集合，用来存放权限 Id\r\n");
      out.write("\t\t\t\t\t\tvar nodeArray = new Array();\r\n");
      out.write("\t\t\t\t\t\tfor(var i=0;i<nodes.length;i++){\r\n");
      out.write("\t\t\t\t\t\t\t//把id存入集合\r\n");
      out.write("\t\t\t\t\t\t\tnodeArray.push(nodes[i].id);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t//把所有权限Id用','，隔开\r\n");
      out.write("\t\t\t\t\t\tvar ids = nodeArray.join(',');\r\n");
      out.write("\t\t\t\t\t\t//把ids设置到隐藏域\r\n");
      out.write("\t\t\t\t\t\t$(\"#MenuIds\").val(ids);\r\n");
      out.write("\t\t\t\t\t\tvar r = $(\"#modifyForm\").form(\"validate\");\r\n");
      out.write("\t\t\t\t\t\tif(r){\r\n");
      out.write("\t\t\t\t\t\t\t//表单提交\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#modifyForm\").submit();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t     </script>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
    // /admin/role.jsp(239,5) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f0.setName("sys:jsgl:tj");
    int _jspx_eval_shiro_005fhasPermission_005f0 = _jspx_th_shiro_005fhasPermission_005f0.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    \t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"add()\">添加</a>\r\n");
        out.write("    \t");
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
    // /admin/role.jsp(243,5) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f1.setName("sys:jsgl:xg");
    int _jspx_eval_shiro_005fhasPermission_005f1 = _jspx_th_shiro_005fhasPermission_005f1.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    \t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit',plain:true\" onclick=\"update()\">修改</a>\r\n");
        out.write("    \t");
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
    // /admin/role.jsp(247,5) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_shiro_005fhasPermission_005f2.setName("sys:jsgl:sc");
    int _jspx_eval_shiro_005fhasPermission_005f2 = _jspx_th_shiro_005fhasPermission_005f2.doStartTag();
    if (_jspx_eval_shiro_005fhasPermission_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    \t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-cancel',plain:true\" onclick=\"deldata()\">删除</a>\r\n");
        out.write("    \t");
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
