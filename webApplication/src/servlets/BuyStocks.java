package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;;


/**
 * Servlet implementation class GetStockPrice
 */
@WebServlet("/buyStocks")
public class BuyStocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SEARCH_TERM;
    private String COL_NAME;
    private String DIR;
    private int START;
    private int AMOUNT;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyStocks() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = request.getParameter("email");
		String stockName = request.getParameter("stockName");
		String shareAmountS = request.getParameter("shareAmount");
		double price = Double.parseDouble(request.getParameter("price"));
		int shareAmount = Integer.parseInt(shareAmountS);
		//String[] cols = {"idSTOCKS", "Stock_Name", "Company", "Price", "Shares", "TimeStamp"};
		JSONObject result = new JSONObject();

		try {
			result = buyStock(userEmail, price, shareAmount, stockName, request);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		out.print(result);
		
	}

public JSONObject buyStock(String userEmail, double price, int shareAmount, String StockName, HttpServletRequest request) throws SQLException, ClassNotFoundException {
	JSONObject result = new JSONObject();
	JSONArray array = new JSONArray();
	String searchSQL = "";
	double balance = 0;
	// @formatter:off
	//String sql = "SELECT " + "idSTOCKS, STOCK_NAME, Company, Price, TimeStamp, Shares"+ "FROM " + "STOCKS";
	String sql = "SELECT Wallet_Balance FROM USERS WHERE Email_Address=\'" + userEmail + "\'";
	//System.out.println(sql);

	try {
		Connection con = DBConnection.initializeDatabase();
		PreparedStatement statement = con.prepareStatement(sql);
		//statement.setBoolean(1, false);
		
		ResultSet rs = statement.executeQuery();
		//System.out.println(rs);
		
		while (rs.next()) {
			//JSONArray ja = new JSONArray();
			balance = Double.parseDouble(rs.getString("Wallet_Balance"));
			//array.put(ja);
		}
		
		if(balance > price * shareAmount ) {
			//String sql2 = "UPDATE USERS SET Wallet_Balance=" + Double.toString(balance - price*shareAmount) +" WHERE Email_Address=\'" + userEmail + "\'";
			PreparedStatement statement2 = con.prepareStatement("UPDATE USERS SET Wallet_Balance=" + Double.toString(balance - price*shareAmount) +" WHERE Email_Address=\'" + userEmail + "\'");
			statement2.executeUpdate();
			
			PreparedStatement statement4 = con.prepareStatement("SELECT id, Shares FROM USER_STOCKS WHERE USER=\'"+userEmail+"\' AND Stock_Name=\'"+StockName+"\'" );
			ResultSet rs4 = statement4.executeQuery();
			if (!rs4.next()) {
				//String sql3 = ;
				//PreparedStatement statement3 = con.prepareStatement("INSERT INTO USER_STOCKS values(?, ?, ?, ?, ?, ?)");
				PreparedStatement statement3 = con.prepareStatement("INSERT INTO USER_STOCKS(User, Stock_Name, Shares, Buy_Time, Sell_Time) values(?, ?, ?, ?, ?)");
				Timestamp buyTime = new Timestamp(System.currentTimeMillis());
				statement3.setString(1, userEmail);
				statement3.setString(2, StockName);
				statement3.setString(3, Integer.toString(shareAmount));
				statement3.setString(4, buyTime.toString());
				statement3.setString(5, null);
				
				statement3.executeUpdate();
			} else {
				int tempShares = Integer.parseInt(rs4.getString("Shares"));
				String tempID = rs4.getString("id");
				PreparedStatement statement5 = con.prepareStatement("UPDATE USER_STOCKS SET Shares="+Integer.toString(tempShares + shareAmount)+" WHERE id="+tempID);
				statement5.executeUpdate();
			}
			
			
		}else {
			System.out.println("no can do");
		}
		
		
		statement.close();
		rs.close();
		
		con.close();
		//}
		try {
		//result.put("iTotalRecords", total);
		//result.put("iTotalDisplayRecords", totalAfterFilter);
		result.put("aaData", array);
		} catch (Exception e) {
		
		}
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	System.out.println(result);
	return result;
}


}
