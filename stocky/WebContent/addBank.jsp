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

	<title>Add another bank</title>
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
					<a class="nav-link" href="stockMarket.jsp">View Stock Market</a>
				</li>

				<li class="nav-item">
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
				
			} else {
				System.out.println("Not authenticated on member.jsp!");
				//request.getRequestDispatcher("index.jsp").include(request, response);
				response.sendRedirect("index.jsp");
			}
		%>
		
		<h2>Add new bank information:</h2>
		
		<div class="row justify-content-center">
			<form action="http://localhost:8180/webApplication/AddBankAccount" method="post" class="col-sm-6"
				id="signupForm">
				<div class="form-group">
					<label for="inputBANum">Bank Account Number</label> <input
						type="text" class="form-control" id="inputBANum" name="inputBANum"
						placeholder="Your Bank Account Number" required>
				</div>
				<div class="form-group">
					<label for="inputBARoutingNum">Routing Number</label> <input
						type="text" class="form-control" id="inputBARoutingNum" name="inputBARoutingNum"
						placeholder="Your Routing Number" required>
				</div>
				<button type="submit" class="btn btn-primary">Add This Bank</button>
			</form>
		</div>

		
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