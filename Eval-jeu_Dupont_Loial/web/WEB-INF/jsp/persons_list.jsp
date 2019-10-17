<!DOCTYPE html>
<html class="no-js" lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<title>Gestion des salariés - Liste des salariés</title>
		<link rel="stylesheet" type="text/css"
		      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
		<link rel="stylesheet" type="text/css"
		      href="<%= request.getContextPath()%>/vendor/foundation-6.5.1/css/foundation.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/style.css"/>
	</head>
	<body>
		<div class="callout large primary">
			<div class="row column text-center">
				<h1>Gestion des salariés - Liste des salariés</h1>
			</div>
		</div>
		<div class="row small-8 small-centered">
			<table>
				<thead>
					<tr>
						<th>Nom complet</th>
						<th>Email</th>
						<th>Salaire</th>
						<th class="text-center">Actions</th>
					</tr>
				</thead>
				<tbody>
					<form id="delete-form" action="persons" method="POST">
						<input id="form-method" type="hidden" name="form-method" value="DELETE"/>
						<input id="form-id" type="hidden" name="form-id" value=""/>
					</form>
					<c:forEach var="person" items="${ sessionScope.persons }" varStatus="status">
						<tr>
							<td>${person.name}</td>
							<td>${person.email}</td>
							<td>${person.salary}</td>
							<td class="text-center">
								<a class="button" href="<c:url value="/persons?action=details&id=${status.index}" />"><i
										class="fa fa-edit"></i></a>
								<a class="button alert delete-link" data-id-to-del="${status.index}"
								   href="javascript:void(0);"><i class="fa fa-user-times"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="text-right">
				<a class="button align-right" href="<c:url value="/persons?action=details" />"><i
						class="fa fa-user-plus"></i> Ajouter</a>
			</div>
		</div>
		<script src="<%= request.getContextPath()%>/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
		<script src="<%= request.getContextPath()%>/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
		<script>
			$(document).foundation();
			document.documentElement.setAttribute('data-useragent', navigator.userAgent);
			$(document).ready(function () {
				$("a.delete-link").on("click", function () {
					$("#form-id").attr("value", $(this).attr("data-id-to-del"));
					$("form#delete-form").submit();
				});
			});
		</script>
	</body>
</html>
