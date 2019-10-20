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
		<!--input id="tab-1" type="radio" name="tab" checked><label for="tab-1" class="tab">Connection</label>
		<input id="tab-2" type="radio" name="tab"><label for="tab-2" class="tab">Inscription</label>
		<div class="tab-group">
			<div class="tab-1-htm">
				<div class="group">
					<label for="user">Identifient</label>
					<input id="user" type="text" value="asasaasas"/>
				</div>
				<div class="group">
					<label for="pass">Mot de passe</label>
					<input id="pass" type="password" data-type="password">
				</div>
				<div class="group">
					<input type="submit" value="Se connecter">
				</div>
				<div class="hr"></div>
			</div>
			<form class="tab-2-htm" method="POST" action="">
				<div class="group">
					<label for="user2">Identifient</label>
					<input id="user2" type="text" value="rfeerggettrtrthr"/>
				</div>
				<div class="group">
					<label for="pass2">Mot de passe</label>
					<input id="pass2" type="password" data-type="password">
				</div>
				<div class="group">
					<label for="pass3">Mot de passe</label>
					<input id="pass3" type="password" data-type="password">
				</div>
				<div class="group">
					<input type="submit" value="S'inscrire">
				</div>
				<div class="hr"></div>
			</form>
		</div>
		<form method="GET" action="question">
			<input type="submit" value="Question">
			<div class="hr"></div>
		</form-->
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