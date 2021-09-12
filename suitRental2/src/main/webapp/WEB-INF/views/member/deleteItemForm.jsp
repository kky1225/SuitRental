<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>주문 취소</h2>
	<form action="deleteItem.do" method="post">
		<input type="hidden" value="${order.rent_num}" name="num">
			<div class="align-center">
				주문을 취소하시겠습니까?<br>
				<input type="submit" value="주문취소">
				<input type="button" value="MY페이지" onclick="location.href='myPage.do'">
			</div>
	</form>
</div>
</body>
</html>