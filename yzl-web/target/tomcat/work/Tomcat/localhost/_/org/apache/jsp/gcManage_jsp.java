/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-07-16 03:30:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class gcManage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("  <link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"assets/css/font-awesome.min.css\" />\r\n");
      out.write("  <!--[if IE 7]>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"assets/css/font-awesome-ie7.min.css\" />\r\n");
      out.write("  <![endif]-->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"assets/css/ace.min.css\" />\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"css/style.css\"/>\r\n");
      out.write("<title>ä¾åºåç®¡ç</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"page-content\">\r\n");
      out.write("<div class=\"gys_style\">\r\n");
      out.write(" <div class=\"Manager_style\">\r\n");
      out.write("    <div class=\"title_name\">工程管理</div>\r\n");
      out.write("    <button type=\"button\" class=\"btn btn-primary\" id=\"Add_Ship_btn\">新增工程</button>\r\n");
      out.write("    <div id=\"Add_Ship_style\" style=\"display:none\">\r\n");
      out.write("    <div class=\"Add_Manager_style\">\r\n");
      out.write("    <div class=\"add_user_style clearfix\">\r\n");
      out.write("     <ul class=\"clearfix\">\r\n");
      out.write("      <li><label class=\"label_name\">å·¥ç¨åç§°</label> <input name=\"å·¥ç¨åç§°\" type=\"text\"  class=\"name_text\"/><i style=\"color:#F60; \">*</i></li>\r\n");
      out.write("      <li><label class=\"label_name\">å·¥ç¨ç¼å·</label><input name=\"å·¥ç¨ç¼å·\" type=\"text\"  class=\"name_text\"/><i style=\"color:#F60; \">*</i></li>\r\n");
      out.write("      <li><label class=\"label_name\">æ²¹è¹èç³»çµè¯</label><input name=\"æ²¹è¹èç³»çµè¯\" type=\"text\"  class=\"name_text\"/><i style=\"color:#F60; \">*</i></li>\r\n");
      out.write("      <li><label class=\"label_name\">æ²¹è¹èç³»é®ç®±</label><input name=\"\" type=\"text\"  class=\"text_add\"/></li>\r\n");
      out.write("      </ul>    \r\n");
      out.write("      <div class=\"Remark\"><label class=\"label_name\">å¤æ³¨</label><textarea name=\"\" cols=\"\" rows=\"\" style=\" width:434px; height:200px; padding:5px;\"></textarea></div>\r\n");
      out.write("<!--      <div class=\"btn_operating\"><button  type=\"button\" class=\"btn btn-primary\" id=\"submit\">ä¿å­</button><button  type=\"button\" class=\"btn btn-warning\">éç½®</button></div>-->\r\n");
      out.write("      </div>       \r\n");
      out.write("      </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"Manager_style\">\r\n");
      out.write("     <span class=\"title_name\">å·¥ç¨ä¿¡æ¯</span>\r\n");
      out.write("     <table class=\"table table-striped table-bordered table-hover\">\r\n");
      out.write("      <thead>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <th>åºå·</th>\r\n");
      out.write("        <th>å·¥ç¨åç§°</th>\r\n");
      out.write("        <th>å·¥ç¨ç¼å·</th>\r\n");
      out.write("        <!-- <th>èç³»çµè¯</th>\r\n");
      out.write("        <th>èç³»é®ç®±</th> -->\r\n");
      out.write("        <th>å¤æ³¨</th>\r\n");
      out.write("        <th>æä½</th>\r\n");
      out.write("       </tr>\r\n");
      out.write("      </thead>\r\n");
      out.write("      <tbody>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td>1</td><td>é¿é¿è¨å¾·å·A</td><td>åæå¸®</td><!-- <td>13505140602</td><td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>\r\n");
      out.write("        <td><button type=\"button\" class=\"btn btn-primary\">ä¿®æ¹</button> <button type=\"button\" class=\"btn btn-warning\">å é¤</button></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td>2</td><td>åéå·A</td><td>åæå¸®</td><!-- <td>13505140602</td><td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>\r\n");
      out.write("        <td><button type=\"button\" class=\"btn btn-primary\">ä¿®æ¹</button> <button type=\"button\" class=\"btn btn-warning\">å é¤</button></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td>3</td><td>ççæµ·å·A</td><td>åæå¸®</td><!-- <td>13505140602</td><td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>\r\n");
      out.write("        <td><button type=\"button\" class=\"btn btn-primary\">ä¿®æ¹</button> <button type=\"button\" class=\"btn btn-warning\">å é¤</button></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("        <td>4</td><td>åäº¬å·A</td><td>åæå¸®</td><!-- <td>13505140602</td><td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>\r\n");
      out.write("        <td><button type=\"button\" class=\"btn btn-primary\">ä¿®æ¹</button> <button type=\"button\" class=\"btn btn-warning\">å é¤</button></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("      </tbody>\r\n");
      out.write("     </table>\r\n");
      out.write("      <div class=\"page_style\">\r\n");
      out.write("  <select name=\"\" size=\"1\">\r\n");
      out.write("    <option value=\"1\">10</option>\r\n");
      out.write("    <option value=\"2\">20</option>\r\n");
      out.write("    <option value=\"3\">30</option>\r\n");
      out.write("  </select>\r\n");
      out.write("  <a href=\"\" class=\"icon-step-backward page_btn\" ></a>\r\n");
      out.write("  <a href=\"\" class=\"icon-caret-left page_btn\"></a>\r\n");
      out.write("  ç¬¬\r\n");
      out.write("  <select name=\"\" size=\"1\">\r\n");
      out.write("    <option value=\"1\">1</option>\r\n");
      out.write("    <option value=\"2\">2</option>\r\n");
      out.write("    <option value=\"3\">3</option>\r\n");
      out.write("  </select>\r\n");
      out.write("  å±2é¡µ\r\n");
      out.write("   <a href=\"\" class=\" icon-caret-right page_btn\"></a>\r\n");
      out.write("  <a href=\"\" class=\"icon-step-forward page_btn\"></a>\r\n");
      out.write("  </div>\r\n");
      out.write("    </div>  \r\n");
      out.write("    </div>  \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!--[if !IE]> -->\r\n");
      out.write("\t\t<script src=\"assets/js/jquery.min.js\"></script>\r\n");
      out.write("\t\t<!-- <![endif]-->\r\n");
      out.write("\r\n");
      out.write("\t\t<!--[if !IE]> -->\r\n");
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\twindow.jQuery || document.write(\"<script src='assets/js/jquery-2.0.3.min.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- <![endif]-->\r\n");
      out.write("\r\n");
      out.write("\t\t<!--[if IE]>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write(" window.jQuery || document.write(\"<script src='assets/js/jquery-1.10.2.min.js'>\"+\"<\"+\"/script>\");\r\n");
      out.write("</script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("<script src=\"assets/layer/layer.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("$('#Add_Ship_btn').on('click', function(){\r\n");
      out.write("    layer.open({\r\n");
      out.write("        type: 1,\r\n");
      out.write("        title: 'æ·»å è¹åª',\r\n");
      out.write("\t\tshadeClose: true, //ç¹å»é®ç½©å³é­å±\r\n");
      out.write("        area : ['600px' , ''],\r\n");
      out.write("        content:$('#Add_Ship_style'),\r\n");
      out.write("\t\tbtn:['æäº¤','åæ¶'],\r\n");
      out.write("\t\tyes:function(index, layero){\r\n");
      out.write("\t\t\t var num=0;\r\n");
      out.write("\t\t\t var str=\"\";\r\n");
      out.write("     $(\".name_text\").each(function(n){\r\n");
      out.write("          if($(this).val()==\"\")\r\n");
      out.write("          {\r\n");
      out.write("               \r\n");
      out.write("\t\t\t    layer.alert(str+=\"\"+$(this).attr(\"name\")+\"ä¸è½ä¸ºç©ºï¼\\r\\n\",{\r\n");
      out.write("                title: 'æç¤ºæ¡',\t\t\t\t\r\n");
      out.write("\t\t\t\ticon:0,\t\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("          }); \r\n");
      out.write("\t\t     num++;\r\n");
      out.write("             return false;            \r\n");
      out.write("          } });\r\n");
      out.write("\t\t  if(num>0)\r\n");
      out.write("     {\r\n");
      out.write("        \r\n");
      out.write("          return false;\r\n");
      out.write("     }\r\n");
      out.write("          else{\r\n");
      out.write("\t\t\t  \r\n");
      out.write("\t\t\t  layer.alert('æ·»å æåï¼',{\r\n");
      out.write("               title: 'æç¤ºæ¡',\t\t\t\t\r\n");
      out.write("\t\t\ticon:1,\t\t\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t\t   layer.close(index);\t\r\n");
      out.write("\t\t  }\t\t  \t\t     \t\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("    });\r\n");
      out.write("});\r\n");
      out.write("//jQuery(document).ready(function(){  \r\n");
      out.write("// \r\n");
      out.write("//  $(\"#submit\").click(function(){\r\n");
      out.write("//\t// var num=0;\r\n");
      out.write("//     var str=\"\";\r\n");
      out.write("//     $(\".name_text\").each(function(n){\r\n");
      out.write("//          if($(this).val()==\"\")\r\n");
      out.write("//          {\r\n");
      out.write("//              // num++;\r\n");
      out.write("//\t\t\t   layer.alert(str+=\"\"+$(this).attr(\"name\")+\"ä¸è½ä¸ºç©ºï¼\\r\\n\",{\r\n");
      out.write("//                title: 'æç¤ºæ¡',\t\t\t\t\r\n");
      out.write("//\t\t\t\ticon:0,\t\t\t\t\r\n");
      out.write("//          }); \r\n");
      out.write("//             // layer.msg(str+=\"\"+$(this).attr(\"name\")+\"ä¸è½ä¸ºç©ºï¼\\r\\n\");\r\n");
      out.write("//             layer.close(index);\r\n");
      out.write("//          }\r\n");
      out.write("//\t\t  else{\r\n");
      out.write("//\t\t\t  \r\n");
      out.write("//\t\t\t  layer.alert('æ·»å æåï¼',{\r\n");
      out.write("//               title: 'æç¤ºæ¡',\t\t\t\t\r\n");
      out.write("//\t\t\ticon:1,\t\t\r\n");
      out.write("//\t\t\t  });\r\n");
      out.write("//\t\t  }\r\n");
      out.write("//\t\t  \r\n");
      out.write("//     });\r\n");
      out.write("//    \r\n");
      out.write("//})\r\n");
      out.write("// });\r\n");
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
