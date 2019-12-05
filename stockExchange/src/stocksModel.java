import java.sql.Timestamp;
public class stocksModel {
	private long idSTOCKS;
	private String Stock_Name;
	private String Company;
	private long Price;
	private int Shares;
	private java.util.Date TimeStamp;
	
	public long getStockID() {
		return idSTOCKS;
	}
	public void setStockID(long StockID) {
		this.idSTOCKS = StockID;
	}
	
	public String getStockName() {
		return Stock_Name;
	}
	public void setStockName(String stockName) {
		this.Stock_Name = stockName;
	}
	
	public String getCompany() {
		return Company;
	}
	public void setCompany(String companyName) {
		this.Company = companyName;
	}
	
	public long getPrice() {
		return Price;
	}
	public void setPrice(long price) {
		this.Price = price;
	}
	
	public int getShares() {
		return Shares;
	}
	public void setShares(int shares) {
		this.Shares = shares;
	}
	
	public java.util.Date getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(java.util.Date timeStamp) {
		this.TimeStamp = timeStamp;
	}
}

