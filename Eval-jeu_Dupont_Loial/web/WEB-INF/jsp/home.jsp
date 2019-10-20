<jsp:useBean id="homebean" scope="session" type="model.HomeBean"/>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title>Accueil</title>
	<link rel="stylesheet" href="<%= request.getContextPath()%>/vendor/bootstrap.min.css" id="bootstrap-css">
	<link rel="stylesheet" href="<%= request.getContextPath()%>/css/style.css"/>
</head>
<body>
<div class="wrap-back">
	<div class="wrap-front">
		<div class="callout large primary">
			<div class="row column text-center">
				<h1>Liste du TOP 10 des joueurs</h1>
			</div>
		</div>
		<div class="row small-8 small-centered">
			<table>
				<thead>
				<tr>
					<th>Nom</th>
					<th>Score</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="user" items="${ sessionScope.topListe }" varStatus="status">
					<tr>
						<td>${user.username}</td>
						<td>${user.bestParte}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<form action="question" method="get">
				<input type="submit" class="button" value=" Question ">
			</form>
		</div>
	</div>
</div>
<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
</body>
</html>