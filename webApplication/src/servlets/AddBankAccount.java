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
 * Servlet implementation class AddBankAccount
 */
@WebServlet("/AddBankAccount")
public class AddBankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBankAccount() {
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
		
		
		String userBANum = request.getParameter("inputBANum");
		String userBARoutingNum = request.getParameter("inputBARoutingNum");
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			PreparedStatement st = con.prepareStatement("insert into BANK_ACCOUNT values(?, ?, ?, ?)"); 
			st.setString(1, userEmail);
			st.setString(2, userBANum);
			st.setString(3, userBARoutingNum);
			st.setString(4, String.valueOf(Utils.getBankAccountBalance()));
			
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
