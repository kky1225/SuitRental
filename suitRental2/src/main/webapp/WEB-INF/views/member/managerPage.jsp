<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h2>관리자 페이지</h2>
		<h3>대여 금지 회원 지정</h3>
			<ul>
				<li>
					<input type="button" value="권한 수정" onclick="location.href='updateAuthForm.do'">
				</li>
			</ul>
	</div>
</body>
</html>