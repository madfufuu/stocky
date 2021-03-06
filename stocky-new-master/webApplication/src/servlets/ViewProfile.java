package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfile() {
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
		
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			Statement stmt = con.createStatement();
			String sql = "SELECT First_Name, Last_Name, Address FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String firstName = rs.getString("First_Name");
			String LastName = rs.getString("Last_Name");
			String address = rs.getString("Address");
			
//			request.setAttribute("first_name", firstName);
			System.out.println(firstName);
			System.out.println(LastName);
			System.out.println(address);
			response.sendRedirect("http://localhost:8080/stocky/viewProfile.jsp?first_name=" 
			+ firstName + "&last_name=" + LastName + "&address=" + address 
			+ "&email=" + userEmail);
			 
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
