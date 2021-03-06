package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] ck = request.getCookies();
        
        
        if( ck != null ) {
           
           
           for (int i = 0; i < ck.length; i++) {
              Cookie cookie = ck[i];
              
              if((cookie.getName()).compareTo("email") == 0 ) {
                 cookie.setMaxAge(0);
                 cookie.setPath("/");
                 response.addCookie(cookie);
                 System.out.println("Deleted cookie: " + cookie.getName());
                 
                 
              }
          
           }
           response.sendRedirect("http://localhost:8080/stocky/index.jsp");
        } else {
           System.out.println("No cookies founds");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
