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
 * Servlet implementation class GetStockPrice
 */
@WebServlet("/getStockPrice")
public class getStockPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String SEARCH_TERM;
    private String COL_NAME;
    private String DIR;
    private int START;
    private int AMOUNT;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getStockPrice() {
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
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stockName = request.getParameter("inputStockName");
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
			result = getStockPrice(stockName, request);
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

public JSONObject getStockPrice(String stockName, HttpServletRequest request) throws SQLException, ClassNotFoundException {
	JSONObject result = new JSONObject();
	JSONArray array = new JSONArray();
	String searchSQL = "";
	
	// @formatter:off
	//String sql = "SELECT " + "idSTOCKS, STOCK_NAME, Company, Price, TimeStamp, Shares"+ "FROM " + "STOCKS";
	String sql = "SELECT idSTOCKS, STOCK_NAME, Company, Price, TimeStamp, Shares FROM STOCKS WHERE STOCK_NAME=\'" + stockName + "\' AND TimeStamp = (select max(TimeStamp)) ORDER BY TimeStamp DESC LIMIT 1";
	//System.out.println(sql);
	
	// @formatter:on
	
	//sql += searchSQL;
	//sql += " order by " + COL_NAME + " " + DIR;
	
	//sql += " limit " + START + ", " + AMOUNT;
	
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
