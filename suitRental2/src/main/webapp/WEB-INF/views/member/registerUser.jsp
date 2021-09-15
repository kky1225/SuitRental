<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 완료</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>회원가입 완료</h2>
			<div class="result-display">
				<div class="align-center">
					회원가입이 완료되었습니다!!
					<p>
					<p>
					<input type="button" class="btn btn-dark" value="로그인" style="width:330px; margin-top:30px;" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
					<input type="button" class="btn btn-dark" value="홈으로" style="width:330px; margin-top:30px;" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
			</div>
		</div>
	</body>
</html>