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
 * Servlet implementation class TransferServiceAction
 */
@WebServlet("/TransferServiceAction")
public class TransferServiceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServiceAction() {
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
		
		
		String bankAccount = request.getParameter("bankAccount");
		double transferAmount = Double.parseDouble(request.getParameter("inputTransferAmount"));
		
		try {
			Connection con = DBConnection.initializeDatabase();
			
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			String sql = "SELECT Wallet_Balance FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			String sqlBank = "SELECT Balance FROM BANK_ACCOUNT WHERE User_ID = \'" + userEmail + "\'";
			ResultSet rsBank = stmt2.executeQuery(sqlBank);
			
			double wallet_balance = 0.0;
			double newWalletBalance = 0.0;
			double bankBalance = 0.0;
			double newBankBalance = 0.0;
			if (request.getParameter("to") != null) {
//			    System.out.println(bankAccount);
//			    System.out.println(transferAmount);
				rs.next();
				rsBank.next();
				bankBalance = Double.parseDouble(rsBank.getString("Balance"));
				wallet_balance = Double.parseDouble(rs.getString("Wallet_Balance"));
				newWalletBalance = wallet_balance - transferAmount;
				newBankBalance = bankBalance + transferAmount;
			} else if (request.getParameter("from") != null) {
//				System.out.println(bankAccount);
//			    System.out.println(transferAmount);
				rs.next();
				rsBank.next();
				bankBalance = Double.parseDouble(rsBank.getString("Balance"));
				wallet_balance = Double.parseDouble(rs.getString("Wallet_Balance"));
				newWalletBalance = wallet_balance + transferAmount;
				newBankBalance = bankBalance - transferAmount;
			}
			
			/* Update wallet balance */
			PreparedStatement st = con.prepareStatement("update USERS set Wallet_Balance = " 
					+ newWalletBalance + " where Email_Address = \'" + userEmail + "\'"); 
			st.executeUpdate();
			
			/* Update bank balance */
			PreparedStatement stBank = con.prepareStatement("update BANK_ACCOUNT set Balance = " 
					+ newBankBalance + " where User_ID = \'" + userEmail + "\'"); 
			stBank.executeUpdate();
			
			response.sendRedirect("http://localhost:8080/stocky/member.jsp");
			
			st.close();
			stBank.close();
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
