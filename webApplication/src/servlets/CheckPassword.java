package servlets;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class CheckPassword
 */
@WebServlet("/CheckPassword")
public class CheckPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPassword() {
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
		String userEmail = request.getParameter("inputEmail");
		String userFirstName = request.getParameter("inputFirstName");
		String userLastName = request.getParameter("inputLastName");
		String userAddress = request.getParameter("inputAddress");
		boolean isMatch = false;
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			Statement stmt = con.createStatement();
			String sql = "SELECT Email_Address, Password, First_Name, Last_Name, Address FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			
			String dbEmailAddress = "";

			while(rs.next()){
	            //Retrieve by column name
	            dbEmailAddress = rs.getString("Email_Address");
	            String dbFirstName = rs.getString("First_Name");
	            String dbLastName = rs.getString("Last_Name");
	            String dbAddress = rs.getString("Address");
	            
	            String addr = dbAddress.replace("\n", " ");
	            
	            if(userEmail.equals(dbEmailAddress) && userFirstName.equals(dbFirstName) && userLastName.equals(dbLastName) && userAddress.equals(addr)) {
	            	isMatch = true;
	    		}
	            break;
	         }
			
			if(isMatch) {
				//String retriveNameSql = "SELECT First_Name FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
				//ResultSet rs1 = stmt.executeQuery(retriveNameSql);
				
				//rs1.next();
				//String userFirstName = rs1.getString("First_Name");
				
				Cookie ck = new Cookie("email", dbEmailAddress);  
				ck.setMaxAge(60 * 60); 
				ck.setPath("/");
	            response.addCookie(ck);  
				
				request.setAttribute("email", dbEmailAddress);
				
				response.sendRedirect("http://localhost:8080/stocky/showpassword.jsp");
			}else {
				   response.sendRedirect("http://localhost:8080/stocky/error.jsp");

			}
			
			rs.close();
	        stmt.close();
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

