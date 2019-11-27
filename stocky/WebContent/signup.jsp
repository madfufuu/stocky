<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="./style.css">

	<title>Registration</title>
</head>

<body>
	<div class="jumbotron text-center" style="margin-bottom:0">
  		<h1>Welcome to Stocky,</h1>
 		<p>a place to start your stock exchange.</p> 
	</div>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
  		<a class="navbar-brand" href="index.jsp">Stocky</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="#">Buy and Sell Stocks</a>
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

		</div>
	</nav>


	<div class="container">
		<div class="row justify-content-center">
			<form action="Registration" method="post" class="col-sm-6"
				id="signupForm">
				<div class="form-group">
					<label for="inputEmail">Email address</label> <input
						type="email" class="form-control" id="inputEmail" name="inputEmail"
						aria-describedby="emailHelp" placeholder="Enter email"> <small
						id="emailHelp" class="form-text text-muted">We'll never
						share your email with anyone else.</small>
				</div>
				<div class="form-group">
					<label for="inputPassword">Password</label> <input
						type="password" class="form-control" id="inputPassword" name="inputPassword"
						placeholder="Password">
				</div>
				<div class="form-group">
					<label for="inputFirstName">First Name</label> <input
						type="text" class="form-control" id="inputFirstName" name="inputFirstName"
						placeholder="Your First Name">
				</div>
				<div class="form-group">
					<label for="inputLastName">Last Name</label> <input
						type="text" class="form-control" id="inputLastName" name="inputLastName"
						placeholder="Your Last Name">
				</div>
				<div class="form-group">
					<label for="inputAddress">Physical Address</label> <input
						type="text" class="form-control" id="inputAddress" name="inputAddress"
						placeholder="Your Address">
				</div>
				<div class="form-group">
					<label for="inputBankAccountNum">Bank Account Number</label> <input
						type="text" class="form-control" id="inputBankAccountNum" name="inputBankAccountNum"
						placeholder="Your Address">
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1">
					<label class="form-check-label" for="exampleCheck1">Placeholder</label>
				</div>
				<button type="submit" class="btn btn-primary">Register!</button>
			</form>
		</div>
	</div>

	<div class="jumbotron text-center" style="margin-bottom:0">
		<p>Footer</p>
	</div>
	


	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>