<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@include file="/source/includes/header.jsp"%>

		<div class="wrapper">
			<div class="logo">
				<img src="source/images/logo.jpg" alt="">
			</div>
			<div class="text-center mt-4 name"> Chat Bizarre </div>
			<form class="p-3 mt-3" action="./login" method="post">
				<div class="form-field d-flex align-items-center"> 
					<span class="far fa-user"></span> 
					<input type="text" name="userName" id="userName" placeholder="Username">
				</div>
				<button type="submit" class="btn mt-3">Login</button>
			</form>
		</div>
		<script type='text/javascript' src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
		<script type='text/javascript'></script>

<%@include file="/source/includes/footer.jsp"%>