<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu | Không thể đăng nhập</title>
<base href="${pageContext.servletContext.contextPath}">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resource/css/base.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Karma", sans-serif
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
		<div class="row" style="margin-top: 20px;">
		
			<div class="col-md-4 col-md-offset-4">
				<form action="${pageContext.servletContext.contextPath}/quen-mat-khau.htm" method="post">
					<div class="panel panel-default">
			
					    <div class="panel-body">
							<strong>Tìm tài khoản của bạn</strong>
							<hr>
					    	<p>Vui lòng nhập email để tìm kiếm tài khoản của bạn.</p>
					    	<input type="email" name="email" placeholder="Email" class="form-control">
					    	<hr>
					    	
					    		<button type="button" class="btn col-md-2 col-md-offset-7" onclick="window.location.href='${pageContext.servletContext.contextPath}/dang-nhap.htm'"><strong>Hủy</strong></button>
					    		<button class="btn btn-primary col-md-3"><strong>Tìm kiếm</strong></button>
					    	
					    </div>	
					    
					</div>
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>