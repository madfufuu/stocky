package servlets;


import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		boolean isAuthenticated = false;
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			Statement stmt = con.createStatement();
			String sql = "SELECT Email_Address, Password FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			
			String dbEmailAddress = "";
			while(rs.next()){
	            //Retrieve by column name
	            dbEmailAddress = rs.getString("Email_Address");
	            String dbPassword = rs.getString("Password");
	            
	            if(userPassword.equals(dbPassword)) {
	            	isAuthenticated = true;
	    		}
	            break;
	         }
			
			if(isAuthenticated) {
				//String retriveNameSql = "SELECT First_Name FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
				//ResultSet rs1 = stmt.executeQuery(retriveNameSql);
				
				//rs1.next();
				//String userFirstName = rs1.getString("First_Name");
				
				Cookie ck = new Cookie("email", dbEmailAddress);  
				ck.setMaxAge(60 * 60); 
	            response.addCookie(ck);  
				//response.sendRedirect("member.jsp");
				request.setAttribute("email", dbEmailAddress);
				//request.getRequestDispatcher("member.jsp").forward(request, response);
				response.sendRedirect("member.jsp");
			}else {
				response.sendRedirect("error.jsp");
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
