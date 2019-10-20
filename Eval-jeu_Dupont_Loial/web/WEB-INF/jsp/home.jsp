<jsp:useBean id="homeBean" scope="session" type="model.HomeBean"/>
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
		  <div class="text-center mb-4">
			  <h1>Liste du TOP 10</h1>
		  </div>
	  <input id="tab-1" type="radio" name="tab" <c:if test="${!homeBean.scorePerso}">checked</c:if>><label for="tab-1" class="tab">Tout le monde</label>
	  <input id="tab-2" type="radio" name="tab" <c:if test="${homeBean.scorePerso}">checked</c:if>><label for="tab-2" class="tab">Personnel</label>
	  <c:if test="${ !empty homeBean.errResult}">
		  <div class="alert alert-warning" role="alert">
			  <p>${homeBean.errResult}</p>
		  </div>
	  </c:if>
		<div class="tab-group">
			<div class="tab-1-htm">
				<c:if test="${homeBean.listTopSize == 0}">
					<div class="group">
						<p>Aucun partie n'a été jouée !</p>
					</div>
				</c:if>
				<c:if test="${homeBean.listTopSize != 0}">
					<table class="table">
						<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Nom</th>
							<th scope="col">Score</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="user" items="${ homeBean.listTop }" varStatus="status">
							<tr>
								<th scope="row">${status.index + 1}</th>
								<td>${user.user}</td>
								<td>${user.score}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<div class="group">
					<form action="question" method="GET">
						<input type="submit" class="button" value="${homeBean.valBtStart}">
					</form>
				</div>
				<div class="hr"></div>
			</div>
			<div class="tab-group2 tab-2-htm">
				<c:if test="${homeBean.listTopPersoSize == 0}">
					<div class="group">
						<p>Aucun partie n'a été jouée !</p>
					</div>
				</c:if>
				<c:if test="${homeBean.listTopPersoSize != 0}">
				<table class="table">
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nom</th>
						<th scope="col">Score</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="user" items="${ homeBean.listTopPerso }" varStatus="status">
						<tr>
							<th scope="row">${status.index + 1}</th>
							<td>${user.user}</td>
							<td>${user.score}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</c:if>
				<div class="group">
					<form action="question" method="GET">
						<input type="submit" class="button" value="${homeBean.valBtStart}">
					</form>
				</div>
				<div class="hr"></div>
			</div>
		</div>
	</div>
</div>
<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
</body>
</html>