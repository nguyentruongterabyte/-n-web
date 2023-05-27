<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">

</head>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
}

.errors{
     color:red; font-style: italic;
}
</style>
<body>
    <div class="container">

		<c:if test="${message.type ne null}">
			<div id="toast">
				<div class="toast toast--${message.type}">
					<div class="toast__icon">
						<c:choose>
							<c:when test="${message.type eq 'success'}">
								<span class="glyphicon glyphicon-ok-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'error'}">
								<span class="glyphicon glyphicon-exclamation-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'info'}">
								<span class="glyphicon glyphicon-info-sign"></span>
							</c:when>
							<c:when test="${message.type eq 'warning'}">
								<span class="glyphicon glyphicon-warning-sign"></span>
							</c:when>
						</c:choose>
					</div>
					<div class="toast__body">
						<h3 class="toast__title"></h3>
						<p class="toast__msg">${message.content}</p>
					</div>
					<div class="toast__close">
						<span class="glyphicon glyphicon-remove-circle"></span>
					</div>
				</div>
			</div>
		</c:if>

    	<div class="row">
    		<div class="col-md-6 col-md-offset-3">
		        <div class="card card-container">
		        	<div class="row">
		        		<div class="col-md-offset-4">
		            		<img id="profile-img" class="profile-img-card img img-rounded" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
		        		</div>
		        	</div>
		            <p id="profile-name" class="profile-name-card"></p>
		            <form:form class="form-signin" modelAttribute="user" method="post" action="${pageContext.servletContext.contextPath}/dang-nhap.htm">
		                <span id="reauth-email" class="reauth-email"></span>
		                <form:input path="username" type="text" id="inputUsername" class="form-control" placeholder="Username"/>
		                <form:errors path="username"/>
		                <form:input path="password" type="password" id="inputPassword" class="form-control mt-16" placeholder="Password"/>
		                <form:errors path="password"/>
		                <form:input path="email" type="hidden" value="@gmail.com"/>
		                
		                <div class="mt-16"><img src="${pageContext.request.contextPath}/captcha/index.htm"></div>
		                <input name="captcha" type="text" placeholder="Enter captcha" class="form-control">
		                <h6 class="mb-0 text-sm errors">${reCaptcha}</h6>
		                
		                <div id="remember" class="checkbox">
		                    <label>
		                        <input type="checkbox" value="remember-me"> Remember me
		                    </label>
		                </div>
		                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
		            </form:form><!-- /form -->
		            <a href="#" class="forgot-password">
		                Forgot the password?
		            </a>
		        </div><!-- /card-container -->
    		
    		</div>
    	</div>
    </div><!-- /container -->
</body>
</html>