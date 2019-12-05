<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="java.sql.*" %>
<%@ page import="servlets.DBConnection" %>
<%@ page import="servlets.Utils"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="./style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<title>Welcome!</title>
</head>

<body>
	<div class="jumbotron text-center" style="margin-bottom:0" id="jumbotron-top">
  		<h1 class="text-light">Welcome to Stocky,</h1>
 		<p class="text-light">A place to start your stock exchange.</p> 
	</div>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
  		<a class="navbar-brand" href="index.jsp">Stocky</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link" href="member.jsp">Member Home<span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="stockMarket.jsp">View Stock Market</a>
				</li>

				<li class="nav-item active">
					<a class="nav-link" href="tradeStocks.jsp">Trade Stocks</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">My Stocks</a>
				</li>
			</ul>

			<form class="" action="http://localhost:8180/webApplication/Logout" method="GET">
				<button type="submit" class="btn btn-default btn-sm" id="logout-button">
					<span class="glyphicon glyphicon-log-out"></span> Log out
				</button>
			</form>
		</div>
	</nav>

	<div class="container">
		
		<%
			Cookie ck[] = request.getCookies();
			if (ck != null) {
				
				try {
				for (int i = 0; i < ck.length; i++) {
					Cookie cookie = ck[i];
					System.out.print("CookieName : " + cookie.getName() + ",  ");
					System.out.println("Value: " + cookie.getValue());
					String email = cookie.getValue();
					out.println("<p id='emailAddress' hidden>"+email+"</p>");
					
					Connection con = servlets.DBConnection.initializeDatabase();
					Statement stmt = con.createStatement();
					String sql = "SELECT First_Name, Last_Name, Wallet_Balance FROM USERS WHERE Email_Address = \'" + email + "\'";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();
					String firstName = rs.getString("First_Name");
					String LastName = rs.getString("Last_Name");
					String balance = rs.getString("Wallet_Balance");
						
					//out.println("<h2 class='col-sm-12' style='margin-top:0px;'>" + servlets.Utils.getGreetings() + ", " + firstName + " " + LastName + "</h2><br />");
					//out.println("<h2 class='col-sm-12'>" + "Balance: $" + balance + "</h2><br />");
					
					if(!servlets.Utils.isMarketOpen()){
						out.println("<h2 class='col-sm-12'>Market is currently closed.</h2>");
					}
					
					
				}
				}catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			} else {
				System.out.println("Not authenticated on member.jsp!");
				//request.getRequestDispatcher("index.jsp").include(request, response);
				response.sendRedirect("index.jsp");
			}
		%>

		
	</div>
	<p class="title">Search for the current price of a stock</p>
	<div class="container">
		<div class="row justify-content-center">
			<form action="#" class="col-sm-6" id="getStockForm">
				<div class="form-group">
					<label for="inputStockName">Stock Name</label> <input
						class="form-control" id="inputStockName" name="inputStockName"
						type="text" placeholder="Enter Stock's Name" required>
				</div>
				<button id = "viewStockButton"type="button" class="btn btn-primary">View Stock</button>
			</form>
		</div>
	</div>
	<br>
	
	<form>
	<div class="formDiv">
		<table id="currentStockPrice" class="dataTables_wrapper">
			<thead>
				<tr>
					<th>StockID</th>
					<th>Stock Name</th>
					<th>Company</th>
					<th>Price</th>
					<th>Shares</th>
					<th>Last Updated</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	</form>
	
	<br>
	<fieldset id="detectStockOpen" disabled>
	<div class="container">
		<div class="row justify-content-center">
			<form action="#" class="col-sm-6" id="buyStockForm">
				<div class="form-group">
					<label for="buyAmount">Shares</label> <input
						class="form-control" id="inputShareAmount" name="inputShareAmount"
						type="number" placeholder="Enter Amount of Shares to Buy" required>
				</div>
				<button id="buyStockButton" type="submit" class="btn btn-primary">Buy Stock</button>
			</form>
		</div>
	</div>
	</fieldset>
	
	
	<br>
	<p class="title">Search for the history of a stock</p>
	<div class="container">
		<div class="row justify-content-center">
			<form action="http://localhost:8280/stockExchange/getStockPrice" method="post" class="col-sm-6"
				id="getStockForm2">
				<div class="form-group">
					<label for="inputEmail">Stock Name</label> <input
						type="email" class="form-control" id="inputStockName" name="inputStockName"
						type="text" placeholder="Enter Stock's Name" required>
				</div>
				<div class="form-group">
					<label for="inputPassword">Start Date</label> <input
						type="date" class="form-control" id="inputStartDate" name="inputStartDate" required>
				</div>
				<div class="form-group">
					<label for="inputFirstName">End Date</label> <input
						type="date" class="form-control" id="inputEndDate" name="inputEndDate" required>
				</div>
				<button type="button" class="btn btn-primary">View Stock History</button>
			</form>
		</div>
	</div>
	<br>
	
	
	<form>
	<div class="formDiv" style="display:none">
		<table id="stockTable" class="dataTables_wrapper">
			<thead>
				<tr>
					<th>StockID</th>
					<th>Stock Name</th>
					<th>Company</th>
					<th>Price</th>
					<th>Shares</th>
					<th>Last Updated</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	</form>
	<br>
	
	
	<div class="jumbotron text-center" style="margin-bottom:0">
		<p>© Copyright 2019 Stocky • All rights reserved.</p>
		<div>
			<a href="#" class="fab fa-facebook"></a> <a href="#"
				class="fab fa-twitter"></a> <a href="#" class="fab fa-google"></a> <a
				href="#" class="fab fa-linkedin"></a> <a href="#"
				class="fab fa-youtube"></a> <a href="#" class="fab fa-instagram"></a>
		</div>
	</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/2b9f2b0ca1.js" crossorigin="anonymous"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

</body>
</html>

<script type="text/javascript" charset="utf-8">

$(document).ready(function() {
	var open = <%= servlets.Utils.isMarketOpen() %>;
	
	if (open == false) {
		$("#detectStockOpen").prop('disabled', false);
	}else {
		$("#detectStockOpen").prop('disabled', true);
	}
});

$("#viewStockButton").on("click", function() {
	//alert($("#inputStockName").val());
	var table = $("#currentStockPrice").dataTable({
		"processing": true,
		"serverSide": true,
		"bPaginate": false,
		"bLengthChange": false,
		"bFilter": false,
		"bInfo": false,
		"bAutoWidth": false,
		"ajax": {
			"url": "http://localhost:8280/stockExchange/getStockPrice",
			"type": "POST",
			"data": {
				"inputStockName": $("#inputStockName").val()
			}
		},
		"aoColumns" : [
			{"sWidth": "10%", "aaData": "idSTOCKS"},
			{"sWidth": "10%", "aaData": "STOCK_NAME"},
			{"sWidth": "20%", "aaData": "Company"},
			{"sWidth": "20%", "aaData": "Price"},
			{"sWidth": "20%", "aaData": "Shares"},
			{"sWidth": "20%", "aaData": "TimeStamp"}
		],
		"bDestroy": true,
		"initComplete": function(settings, json) {
			if (document.getElementById("currentStockPrice").rows[1].cells[4]!=null) {
				$("#detectStockOpen").prop('disabled', false);
			}
			else {
				$("#detectStockOpen").prop('disabled', true);
			}
		}
	});
	$("#currentStockPrice").show();
	$("#buyStockForm").show();
	
	//alert("Done");
}); 

$("#buyStockForm").on("submit", function() {
	var email = document.getElementById("emailAddress").innerHTML;
	var x = parseInt(document.getElementById("currentStockPrice").rows[1].cells[4].innerHTML);
	var price = parseFloat(document.getElementById("currentStockPrice").rows[1].cells[3].innerHTML);
	//alert(x);
	var shares = parseInt($("#inputShareAmount").val());
	if (x < shares) {
		alert("Please enter a valid shares amount.");
	}else {
		$.post(
			"http://localhost:8180/webApplication/buyStocks",
			{
				shareAmount: shares,
				price: price,
				email: email,
				stockName: $("#inputStockName").val()
			},
			function (data, status) {
				alert(data + status);
			});
	}
	
});
</script>

<style>
.title {
	font-size: 30px;
	text-align: center;
}

#currentStockPrice {
	width: 70%;
	display: none;
	text-aslign: center;
	border:1px solid black;
	margin-left:auto;
	margin-right:auto;
}
.titleDiv {
	font-size: 30px;
	text-align: center;
}

#buyStockForm {
	margin: auto;
	text-align: center;
	display:none;
}
</style>

