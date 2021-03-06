package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ck[] = request.getCookies();
		String userEmail = "";
		
		if (ck != null) {
			for (int i = 0; i < ck.length; i++) {
				Cookie cookie = ck[i];
				System.out.print("CookieName : " + cookie.getName() + ",  ");
				System.out.println("Value: " + cookie.getValue());
				userEmail = cookie.getValue();
				break;
			}
		} else {
			response.sendRedirect("http://localhost:8080/stocky/error.jsp");
		}
		
		
		String userFirstName = request.getParameter("inputFirstName");
		String userLastName = request.getParameter("inputLastName");
		String userAddress = request.getParameter("inputAddress");
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			PreparedStatement st = con.prepareStatement("update USERS set First_Name = \'" 
			+ userFirstName + "\', Last_Name = \'" + userLastName + "\', Address = \'" + userAddress
			+ "\' where Email_Address = \'" + userEmail + "\'"); 
			
			st.executeUpdate();
			
			response.sendRedirect("http://localhost:8080/stocky/member.jsp");
			
			st.close(); 
            con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
