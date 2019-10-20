<jsp:useBean id="loginBean" scope="session" type="model.LoginBean"/>
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
				<input id="tab-1" type="radio" name="tab" checked><label for="tab-1" class="tab">Connection</label>
				<input id="tab-2" type="radio" name="tab"><label for="tab-2" class="tab">Inscription</label>
				<div class="tab-group">
					<form class="tab-group2 tab-1-htm" method="POST" action="">
						<div class="group">
							<label for="user" class="label">Identifient</label>
							<input id="user" type="text" class="input" name="${loginBean.formFieldLogin}" value="${loginBean.login}"/>
						</div>
						<div class="group">
							<label for="pass" class="label">Mot de passe</label>
							<input id="pass" type="password" class="input" data-type="password" name="${loginBean.formFieldPwd}">
						</div>
						<div class="group">
							<input type="hidden" name="${loginBean.formFieldType}" value="sign-in-form"/>
							<input type="submit" class="button" value="Se connecter">
						</div>
						<div class="hr"></div>
					</form>
					<form class="tab-2-htm" method="POST" action="">
						<div class="group">
							<label for="user2" class="label">Identifient</label>
							<input id="user2" type="text" class="input" name="${loginBean.formFieldLogin}" value="${loginBean.login}"/>
						</div>
						<div class="group">
							<label for="pass2" class="label">Mot de passe</label>
							<input id="pass2" type="password" class="input" data-type="password" name="${loginBean.formFieldPwd}">
						</div>
						<div class="group">
							<label for="pass3" class="label">Mot de passe</label>
							<input id="pass3" type="password" class="input" data-type="password" name="${loginBean.formFieldConfPwd}">
						</div>
						<div class="group">
							<input type="hidden" name="${loginBean.formFieldType}" value="sign-up-form"/>
							<input type="submit" class="button" value="S'inscrire">
						</div>
						<div class="hr"></div>
					</form>
				</div>
			</div>
		</div>
		<script src="<%= request.getContextPath()%>/vendor/bootstrap.min.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/jquery-1.11.1.min.js"></script>
	</body>
</html>