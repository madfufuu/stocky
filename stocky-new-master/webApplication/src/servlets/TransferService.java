package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransferService
 */
@WebServlet("/TransferService")
public class TransferService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferService() {
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
			String sql = "SELECT Bank_Account_Num FROM BANK_ACCOUNT WHERE User_ID = \'" + userEmail + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			
			List<String> bankAccountList = new ArrayList<String>();
			while(rs.next()) {
				String bankAccount = rs.getString("Bank_Account_Num");
				System.out.println(bankAccount);
				bankAccountList.add(bankAccount);
			}
			
			
//			request.setAttribute("first_name", firstName);
			if(!bankAccountList.isEmpty()) {
				response.sendRedirect("http://localhost:8080/stocky/Transfer.jsp?bankAccounts=" 
						+ String.join(",", bankAccountList));
			}else {
				response.sendRedirect("http://localhost:8080/stocky/error.jsp");
			}
			
			
			 
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
