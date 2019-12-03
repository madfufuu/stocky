/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.29
 * Generated at: 2019-11-30 17:13:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;
import servlets.DBConnection;

public final class Transfer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("servlets.DBConnection");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("\t<!-- Required meta tags -->\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("\n");
      out.write("\t<!-- Bootstrap CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./style.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("\n");
      out.write("\t<title>Transfer Service</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t<div class=\"jumbotron text-center\" style=\"margin-bottom:0\" id=\"jumbotron-top\">\n");
      out.write("  \t\t<h1 class=\"text-light\">Welcome to Stocky,</h1>\n");
      out.write(" \t\t<p class=\"text-light\">A place to start your stock exchange.</p> \n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<nav class=\"navbar navbar-expand-lg navbar-dark bg-primary sticky-top\">\n");
      out.write("  \t\t<a class=\"navbar-brand\" href=\"index.jsp\">Stocky</a>\n");
      out.write("  \t\t<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("    \t\t<span class=\"navbar-toggler-icon\"></span>\n");
      out.write("  \t\t</button>\n");
      out.write("\n");
      out.write("  \t\t<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("\t\t\t<ul class=\"navbar-nav mr-auto\">\n");
      out.write("\t\t\t\t<li class=\"nav-item active\">\n");
      out.write("\t\t\t\t\t<a class=\"nav-link\" href=\"member.jsp\">Member Home<span class=\"sr-only\">(current)</span></a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\n");
      out.write("\t\t\t\t<li class=\"nav-item\">\n");
      out.write("\t\t\t\t\t<a class=\"nav-link\" href=\"#\">Trade Stocks</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\n");
      out.write("\t\t\t\t<li class=\"nav-item\">\n");
      out.write("\t\t\t\t\t<a class=\"nav-link\" href=\"#\">123</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\n");
      out.write("\t\t\t\t<li class=\"nav-item\">\n");
      out.write("\t\t\t\t\t<a class=\"nav-link\" href=\"#\">123</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\n");
      out.write("\t\t\t\t<li class=\"nav-item\">\n");
      out.write("\t\t\t\t\t<a class=\"nav-link\" href=\"#\">123</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\n");
      out.write("\t\t\t</ul>\n");
      out.write("\n");
      out.write("\t\t\t<form class=\"\" action=\"http://localhost:8180/webApplication/Logout\" method=\"GET\">\n");
      out.write("\t\t\t\t<button type=\"submit\" class=\"btn btn-default btn-sm\" id=\"logout-button\">\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-log-out\"></span> Log out\n");
      out.write("\t\t\t\t</button>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</nav>\n");
      out.write("\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t");

			Cookie ck[] = request.getCookies();
			if (ck != null) {
				
			} else {
				System.out.println("Not authenticated on member.jsp!");
				//request.getRequestDispatcher("index.jsp").include(request, response);
				response.sendRedirect("index.jsp");
			}
		
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t<h2>Transfer:</h2>\n");
      out.write("\t\t<h3 class=\"text-center\">Available Bank Accounts:</h3>\n");
      out.write("\n");
      out.write("\t\t<div class=\"row justify-content-center\">\n");
      out.write("\n");
      out.write("\t\t\t<form\n");
      out.write("\t\t\t\taction=\"http://localhost:8180/webApplication/TransferServiceAction\"\n");
      out.write("\t\t\t\tmethod=\"post\" class=\"col-sm-6\" id=\"signupForm\">\n");
      out.write("\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t");

						String bankAccounts = request.getParameter("bankAccounts");
						List<String> bankAccountsList = Arrays.asList(bankAccounts.split(","));

						for (String bankAccount : bankAccountsList) {
							//out.println("<h3 class='col-sm-12 text-center'>" + bankAccount + "</h3><br />");
							out.println("<input type='radio' id='bankAccount' name='bankAccount' value=" + bankAccount + "> "
									+ bankAccount + "<br>");
						}
					
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t<label for=\"inputTransferAmount\">Transfer Amount</label> <input\n");
      out.write("\t\t\t\t\t\t\ttype=\"text\" class=\"form-control\" id=\"inputTransferAmount\"\n");
      out.write("\t\t\t\t\t\t\tname=\"inputTransferAmount\" placeholder=\"Transfer Amount\" required>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<button type=\"submit\" name=\"to\" class=\"btn btn-primary\">Transfer\n");
      out.write("\t\t\t\t\tTo</button>\n");
      out.write("\t\t\t\t<button type=\"submit\" name=\"from\" class=\"btn btn-primary\">Transfer\n");
      out.write("\t\t\t\t\tFrom</button>\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<div class=\"jumbotron text-center\" style=\"margin-bottom:0\">\n");
      out.write("\t\t<p>© Copyright 2019 Stocky • All rights reserved.</p>\n");
      out.write("\t\t<div>\n");
      out.write("\t\t\t<a href=\"#\" class=\"fab fa-facebook\"></a> <a href=\"#\"\n");
      out.write("\t\t\t\tclass=\"fab fa-twitter\"></a> <a href=\"#\" class=\"fab fa-google\"></a> <a\n");
      out.write("\t\t\t\thref=\"#\" class=\"fab fa-linkedin\"></a> <a href=\"#\"\n");
      out.write("\t\t\t\tclass=\"fab fa-youtube\"></a> <a href=\"#\" class=\"fab fa-instagram\"></a>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!-- Optional JavaScript -->\n");
      out.write("    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\n");
      out.write("\t<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n");
      out.write("\t<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\n");
      out.write("\t<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\n");
      out.write("\t<script src=\"https://kit.fontawesome.com/2b9f2b0ca1.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
