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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
		String userBankAccountNum = request.getParameter("inputBankAccountNum");
		String userPassword = request.getParameter("inputPassword");
		String userAddress = request.getParameter("inputAddress");
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			PreparedStatement st = con.prepareStatement("insert into USERS values(?, ?, ?, ?, ?, ?)"); 
			st.setString(1, userEmail);
			st.setString(2, userFirstName);
			st.setString(3, userLastName);
//			st.setInt(4, Integer.valueOf(userBankAccountNum));
			st.setString(4, userPassword);
			st.setString(5, userAddress);
			st.setString(6, Double.toString(0));
			
			st.executeUpdate();
			
			Cookie ck = new Cookie("email", userEmail);  
			ck.setMaxAge(60 * 60); 
			ck.setPath("/");
            response.addCookie(ck);  
			request.setAttribute("email", userEmail);
			
			//request.setAttribute("firstName", userFirstName);
			//request.getRequestDispatcher("http://localhost:8080/stocky/member.jsp").forward(request, response);
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
