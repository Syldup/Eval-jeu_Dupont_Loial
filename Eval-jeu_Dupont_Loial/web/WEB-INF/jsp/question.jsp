<jsp:useBean id="questBean" scope="session" type="model.QuestionBean"/>
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
				<c:if test="${ !empty loginBean.errResult}">
					<div class="alert alert-warning" role="alert">
						<p>${loginBean.errResult}</p>
					</div>
				</c:if>
				<div class="text-center mb-4">
					<h1>Resoudre</h1>
				</div>
				<form class="tab-group" method="POST" action="question">
					<div class="group">
						<input type="hidden" value="${questBean.resultat}"/>
						<label for="resp">${questBean.calcul}</label>
						<input id="resp" type="text" class="input" name="${questBean.formFieldResp}" required/>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Suivent">
					</div>
					<p>
						<div>Ex :</div>
						<div class="d-inline-block ml-2">6.17 * 2<br/>rac 2<br/>8 / 0<br/>rac -2</div>
						<div class="d-inline-block ml-1">=>  12.34<br/>=>  1.41<br/>=>  NaN<br/>=>  NaN</div>
					</p>
					<div class="hr"></div>
				</form>
			</div>
		</div>
		<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
	</body>
</html>