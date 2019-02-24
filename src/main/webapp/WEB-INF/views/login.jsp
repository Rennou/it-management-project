<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Login</title>
		
		<link rel="stylesheet" href="styles/style.css">
		
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	</head>

<body>

	

	<div class="container">

		<div class="body-content">

			<form class="form-horizontal formLogin" action="/login" method = "post">
				<div class="caption h3 ">Log in</div>
				<div class="form-group">
					<label class="col-sm-1" for="username">Username:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="username"
							placeholder="username" name="username" size="10">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1" for="pwd">Password:</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" name="password"
							placeholder="password" name="password">
					</div>
				</div>

				<button type="submit" class="btn btn-info btn-lg">Login</button>
			</form>
			
		</div>


	</div>
</body>
</html>

