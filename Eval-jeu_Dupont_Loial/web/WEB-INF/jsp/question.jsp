<!DOCTYPE html>
<html class="no-js" lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<title>Indentifiez-vous</title>
		<link rel="stylesheet" href="<%= request.getContextPath()%>/vendor/bootstrap.min.css" id="bootstrap-css">
		<link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
	</head>
	<body>
		<div class="wrap-back">
			<div class="wrap-front">
				<form method="POST" action="question">
					<div class="group">
						<label for="pass" class="label">Mot de passe</label>
						<input id="pass" type="password" class="input" data-type="password" name="form-pwd">
					</div>
					<div class="group">
						<input type="text" class="input" name="form-reponse" value="${loginBean.login}"/>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Se connecter">
					</div>
					<div class="hr"></div>
				</form>
			</div>
		</div>
		<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
	</body>
</html>