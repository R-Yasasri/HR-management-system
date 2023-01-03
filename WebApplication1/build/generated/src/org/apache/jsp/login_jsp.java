package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/modal.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("        <link rel=\"icon\" href=\"favicon.ico\">\n");
      out.write("        <title>Login</title>\n");
      out.write("        <!-- Simple bar CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/simplebar.css\">\n");
      out.write("        <!-- Fonts CSS -->\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <!-- Icons CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/feather.css\">\n");
      out.write("        <!-- Date Range Picker CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/daterangepicker.css\">\n");
      out.write("        <!-- App CSS -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/app-light.css\" id=\"lightTheme\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/app-dark.css\" id=\"darkTheme\" disabled>\n");
      out.write("\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("\n");
      out.write("            body{\n");
      out.write("                overflow: hidden;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body class=\"light \">\n");
      out.write("\n");
      out.write("        <div class=\"wrapper vh-100\">\n");
      out.write("            <div class=\"row align-items-center h-100\">\n");
      out.write("                <form class=\"col-lg-3 col-md-4 col-10 mx-auto text-center\" method=\"POST\" action=\"Login\">\n");
      out.write("                    <a class=\"navbar-brand mx-auto mt-2 flex-fill text-center\" href=\"./index.html\">\n");
      out.write("                        <img src=\"assets/images/logo.png\" id=\"ayuSeekLogo\" height=\"120px\" width=\"120px\">\n");
      out.write("                    </a>\n");
      out.write("                    <h1 class=\"h6 mb-3\">Sign in</h1>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"inputEmail\" class=\"sr-only\">Email address</label>\n");
      out.write("                        <input type=\"text\" id=\"inputEmail\" class=\"form-control form-control-lg\" placeholder=\"User name\" required=\"\" autofocus=\"\" name=\"username\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"inputPassword\" class=\"sr-only\">Password</label>\n");
      out.write("                        <input type=\"password\" id=\"inputPassword\" class=\"form-control form-control-lg\" placeholder=\"Password\" required=\"\" name=\"pass\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"checkbox mb-3\">\n");
      out.write("                        <label>\n");
      out.write("                            <input type=\"checkbox\" value=\"remember-me\"> Stay logged in </label>\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"btn btn-lg btn-success btn-block\" type=\"submit\">Let me in</button>\n");
      out.write("                    <p class=\"mt-5 mb-3 text-muted\">© 2021 HRM</p>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
      out.write("<div class=\"modal fade\" id=\"defaultModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"defaultModalLabel\" aria-hidden=\"true\">\n");
      out.write("    <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("        <div class=\"modal-content\" id=\"modalContent\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <h5 class=\"modal-title\" id=\"defaultModalLabel\">Error</h5>\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                    <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                </button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\" id=\"modalBody\"> </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <button type=\"button\" class=\"btn mb-2 btn-secondary\" data-dismiss=\"modal\">Close</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<button type=\"button\" class=\"btn mb-2 btn-primary\" data-toggle=\"modal\" data-target=\"#defaultModal\" id=\"modalTrigger\" style=\"display: none;\"> Launch demo modal </button>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("    function showErrorModal(msg) {\n");
      out.write("        document.getElementById(\"defaultModalLabel\").innerHTML = \"Error\";\n");
      out.write("        document.getElementById(\"modalBody\").innerHTML = msg;\n");
      out.write("        document.getElementById(\"modalContent\").style.border = \"2px red solid\";\n");
      out.write("        showModal();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function showSuccessModal(msg) {\n");
      out.write("        document.getElementById(\"defaultModalLabel\").innerHTML = \"Success\";\n");
      out.write("        document.getElementById(\"modalBody\").innerHTML = msg;\n");
      out.write("        document.getElementById(\"modalContent\").style.border = \"2px blue solid\";\n");
      out.write("        showModal();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function showModal() {\n");
      out.write("        document.getElementById(\"modalTrigger\").click();\n");
      out.write("    }\n");
      out.write("</script>");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery.min.js\"></script>\n");
      out.write("        <script src=\"js/popper.min.js\"></script>\n");
      out.write("        <script src=\"js/moment.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"js/simplebar.min.js\"></script>\n");
      out.write("        <script src='js/daterangepicker.js'></script>\n");
      out.write("        <script src='js/jquery.stickOnScroll.js'></script>\n");
      out.write("        <script src=\"js/tinycolor-min.js\"></script>\n");
      out.write("        <script src=\"js/config.js\"></script>\n");
      out.write("        <script src=\"js/apps.js\"></script>\n");
      out.write("        <!-- Global site tag (gtag.js) - Google Analytics -->\n");
      out.write("        <script async src=\"https://www.googletagmanager.com/gtag/js?id=UA-56159088-1\"></script>\n");
      out.write("        <script>\n");
      out.write("            window.dataLayer = window.dataLayer || [];\n");
      out.write("\n");
      out.write("            function gtag()\n");
      out.write("            {\n");
      out.write("                dataLayer.push(arguments);\n");
      out.write("            }\n");
      out.write("            gtag('js', new Date());\n");
      out.write("            gtag('config', 'UA-56159088-1');\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        ");


            if (session.getAttribute("error") != null) {

        
      out.write("    \n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("            showErrorModal('");
      out.print( session.getAttribute("error"));
      out.write("');\n");
      out.write("\n");
      out.write("        </script>  \n");
      out.write("\n");
      out.write("        ");

//                System.out.println(session.getAttribute("error"));
                session.removeAttribute("error");
            }

        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
