<jsp:useBean id="curCalcul" scope="request" type="bo.Calcul"/>
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
						<label for="pass" class="label">${curCalcul.calcul}</label>
						<input id="pass" type="text" class="input" name="form-reponse" value="${curCalcul.resultat}"/>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Suivent">
					</div>
					<div class="hr"></div>
				</form>
			</div>
		</div>
		<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
	</body>
</html>