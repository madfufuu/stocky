

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
			st.setInt(4, Integer.valueOf(userBankAccountNum));
			st.setString(5, userPassword);
			st.setString(6, userAddress);
			
			st.executeUpdate();
			request.setAttribute("firstName", userFirstName);
			request.getRequestDispatcher("member.jsp").forward(request, response);
			
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
