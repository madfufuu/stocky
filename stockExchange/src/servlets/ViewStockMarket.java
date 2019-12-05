package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class ViewStockMarket
 */
@WebServlet("/viewStockMarket")
public class ViewStockMarket extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SEARCH_TERM;
    private String COL_NAME;
    private String DIR;
    private int START;
    private int AMOUNT;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStockMarket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie ck[] = request.getCookies();
//		String userEmail = "";
//		if (ck != null) {
//			for (int i = 0; i < ck.length; i++) {
//				Cookie cookie = ck[i];
//				System.out.print("CookieName : " + cookie.getName() + ",  ");
//				System.out.println("Value: " + cookie.getValue());
//				userEmail = cookie.getValue();
//				break;
//			}
//		} else {
//			response.sendRedirect("http://localhost:8080/stocky/error.jsp");
//		}
	
		String[] cols = {"idSTOCKS", "Stock_Name", "Company", "Price", "Shares", "TimeStamp"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir= "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sDir = request.getParameter("sSortDir_0");
		
		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
				start = 0;
			}
		}
		if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
				amount = 10;
			}
		}
		if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
				col = 0;
		}
		if (sDir != null) {
			if (!sDir.equals("asc"))
				dir = "desc";
		}

		String colName = cols[col];
		
		
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		COL_NAME = colName;
		DIR = dir;
		START = start;

		try {
			result = getStockData(request);
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}

public JSONObject getStockData(HttpServletRequest request) throws SQLException, ClassNotFoundException {
	JSONObject result = new JSONObject();
	JSONArray array = new JSONArray();
	String searchSQL = "";
	
	// @formatter:off
	//String sql = "SELECT " + "idSTOCKS, STOCK_NAME, Company, Price, TimeStamp, Shares"+ "FROM " + "STOCKS";
	//String sql = "SELECT idSTOCKS, STOCK_NAME, Company, Price, TimeStamp, Shares FROM STOCKS";
	String sql = "SELECT s1.idSTOCKS, s1.Stock_Name, s1.Company, s1.Price, s1.TimeStamp, s1.Shares\n" + 
			"FROM stocky.STOCKS s1 LEFT JOIN stocky.STOCKS s2\n" + 
			" ON (s1.Stock_Name = s2.Stock_Name AND s1.TimeStamp < s2.TimeStamp)\n" + 
			"WHERE s2.TimeStamp IS NULL";
	
	// @formatter:on
	
	sql += searchSQL;
	sql += " order by " + COL_NAME + " " + DIR;
	sql += " limit " + START + ", " + AMOUNT;
	
	try {
		Connection con = DBConnection.initializeDatabase();
		PreparedStatement statement = con.prepareStatement(sql);
		//statement.setBoolean(1, false);
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			JSONArray ja = new JSONArray();
			
			ja.put(rs.getString("idSTOCKS"));
			ja.put(rs.getString("STOCK_NAME"));
			ja.put(rs.getString("Company"));
			ja.put(rs.getString("Price"));
			ja.put(rs.getString("Shares"));
			ja.put(rs.getString("TimeStamp"));
			
			
			array.put(ja);
		}
		statement.close();
		rs.close();
		
		// @formatter:off
		//String query = "SELECT " + "COUNT(*) as count " + "FROM " + "STOCKS";
		//@formatter:on
		
		//if (SEARCH_TERM != "") {
			//query += searchSQL;
		
	//		PreparedStatement stmt = con.prepareStatement(query);
	//		stmt.setBoolean(1, false);
	//		
	//		ResultSet resultSet = stmt.executeQuery();
	//		
	//		if (resultSet.next()) {
	//		totalAfterFilter = resultSet.getInt("count");
	//		}
			// stmt.close();
			//resultSet.close();
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

//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie ck[] = request.getCookies();
//		String userEmail = "";
//		
//		if (ck != null) {
//			for (int i = 0; i < ck.length; i++) {
//				Cookie cookie = ck[i];
//				System.out.print("CookieName : " + cookie.getName() + ",  ");
//				System.out.println("Value: " + cookie.getValue());
//				userEmail = cookie.getValue();
//				break;
//			}
//		} else {
//			response.sendRedirect("http://localhost:8080/stocky/error.jsp");
//		}
//		
//		
//		try {
//			Connection con = DBConnection.initializeDatabase();
//			
//			Statement stmt = con.createStatement();
//			String sql = "SELECT First_Name, Last_Name, Address FROM USERS WHERE Email_Address = \'" + userEmail + "\'";
//			ResultSet rs = stmt.executeQuery(sql);
//			rs.next();
//			String firstName = rs.getString("First_Name");
//			String LastName = rs.getString("Last_Name");
//			String address = rs.getString("Address");
//			
////			request.setAttribute("first_name", firstName);
//			System.out.println(firstName);
//			System.out.println(LastName);
//			System.out.println(address);
//			response.sendRedirect("http://localhost:8080/stocky/viewProfile.jsp?first_name=" 
//			+ firstName + "&last_name=" + LastName + "&address=" + address 
//			+ "&email=" + userEmail);
//			 
//            con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
    
    

}
