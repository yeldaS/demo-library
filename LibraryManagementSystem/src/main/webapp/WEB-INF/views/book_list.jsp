<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
<html>
<head>
<title>Book List Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<link href="<c:url value='/resources/book-list.css' />" rel="stylesheet">

<script
	src="https://www.google.com/recaptcha/api.js?onload=vcRecaptchaApiLoaded&render=explicit"
	async defer></script>

</head>
<body ng-app="libraryApp" class="ng-cloak"
	ng-controller="BookController as controller">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="lead">Library Management System Demo Version
				0.0.1</span>
		</div>
	</div>


	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Books </span>
		</div>
		<div class="tablecontainer">
			<table class="table">
				<tbody>
					<tr>
						<td><button type="button" ng-click="controller.add()"
								class="btn-primary">Add New Book</button></td>
					</tr>
				</tbody>
			</table>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Book Name</th>
						<th>Author</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="book in controller.books" ng-hide="spinnerShow">
						<td><span ng-bind="book.bookName"></span></td>
						<td><span ng-bind="book.author"></span></td>
						<td>
							<button type="button" ng-click="controller.edit(book)"
								class="btn-success">Edit</button> |
							<button type="button" ng-click="controller.remove(book)"
								class="btn-danger">Remove</button>
						</td>
					</tr>
					<tr ng-show="spinnerShow" align="center">
						<td colspan="3"><img
							src="https://loading.io/spinners/book/index.flip-book-loader.svg"
							class="ajax-loader" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script
		src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script>
	<!-- 	<script -->
	<!-- 		src=“https://www.google.com/recaptcha/api.js?onload=vcRecaptchaApiLoaded&render=explicit“ -->
	<!-- 		async defer></script> -->
	<script
		src="<c:url value='/resources/captcha/angular-recaptcha.min.js' />"></script>
	<script src="<c:url value='/resources/book-service.js' />"></script>
	<script src="<c:url value='/resources/book-controller.js' />"></script>
</body>
</html>
