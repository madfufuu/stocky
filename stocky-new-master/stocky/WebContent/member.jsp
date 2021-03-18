<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="java.sql.*" %>
<%@ page import="servlets.DBConnection" %>

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
				<li class="nav-item active">
					<a class="nav-link" href="member.jsp">Member Home<span class="sr-only">(current)</span></a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">Trade Stocks</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">123</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">123</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">123</a>
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
					
					Connection con = servlets.DBConnection.initializeDatabase();
					Statement stmt = con.createStatement();
					String sql = "SELECT First_Name, Last_Name, Wallet_Balance FROM USERS WHERE Email_Address = \'" + email + "\'";
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();
					String firstName = rs.getString("First_Name");
					String LastName = rs.getString("Last_Name");
					String balance = rs.getString("Wallet_Balance");
						
					out.println("<h2 class='col-sm-12' style='margin-top:0px;'>" + servlets.Utils.getGreetings() + ", " + firstName + " " + LastName + "</h2><br />");
					out.println("<h2 class='col-sm-12'>" + "Balance: $" + balance + "</h2><br />");
					
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

	<div class="container" style="text-align: center;">


		<a href="addBank.jsp">
			<button type="button" class="btn btn-primary" id="addBankAccount-button">Add Bank
				Account</button>
		</a>

		<a href="stockchart.jsp">
			<button type="button" class="btn btn-primary" id="addBankAccount-button">View Current Stock</button>
		</a>

		<form action="http://localhost:8180/webApplication/ViewProfile"
			method="post">
			<button type="submit" class="btn btn-primary" id="viewProfile-button">View
				Personal Profile</button>
		</form>
		
		<form action="http://localhost:8180/webApplication/TransferService"
			method="post">
			<button type="submit" class="btn btn-primary" id="viewProfile-button">Transfer Money</button>
		</form>


	</div>


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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/2b9f2b0ca1.js" crossorigin="anonymous"></script>
</body>
</html>