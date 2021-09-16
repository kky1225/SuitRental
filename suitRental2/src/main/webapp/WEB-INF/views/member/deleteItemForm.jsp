<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 취소</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h5 style="text-align:center; margin-top:50px;"><b>주문취소</b></h5>
	<form action="deleteItem.do" method="post">
		<input type="hidden" value="${order.rent_num}" name="num">
			<ul>
				<li>
					<label for="x_code">제품코드</label>
					<input type="text" class="form-control form-label mt-4" id="x_code" name="x_code" value="${order.x_code}" disabled="disabled">
				</li>
				<li>
					<label for="x_code">제품명</label>
					<input type="text" class="form-control form-label mt-4" id="x_code" name="x_code" value="${order.x_name}" disabled="disabled">
				</li>
				<li>
					<label for="rental_date">대여 날짜</label>
					<input type="date" class="form-control form-label mt-4" id="rental_date" name="rental_date" value="${order.rental_date}" disabled="disabled">
				</li>
				<li>
					<label for="return_date">반납 날짜</label>
					<input type="date" class="form-control form-label mt-4" id="return_date" name="return_date" value="${order.return_date}" disabled="disabled">
				</li>
				<li>
				<br>
					<label for="rental_type">대여 방법</label>
					방문<input type="radio" name="rental_type" id="rental_type1" disabled="disabled" value="방문"
					<c:if test="${order.rental_type.equals('방문')}">checked="checked"</c:if>>
					배송<input type="radio" name="rental_type" id="rental_type2" disabled="disabled" value="배송"
					<c:if test="${order.rental_type.equals('배송')}">checked="checked"</c:if>>
				</li>
				<li>
				<br>
					<label for="return_type">반납 방법</label>
					방문<input type="radio" name="return_type" id="return_type1" disabled="disabled" value="방문" 
					<c:if test="${order.return_type.equals('방문')}">checked="checked"</c:if>>
					배송<input type="radio" name="return_type" id="return_type2" disabled="disabled" value="배송"
					<c:if test="${order.return_type.equals('배송')}">checked="checked"</c:if>>
				</li>
				<li>
					<input type="submit" class="btn btn-dark" value="주문취소" style="width:330px; margin-top:30px;">
				</li>
				<li>
					<input type="button" class="btn btn-dark" value="MY페이지" style="width:330px; margin-top:30px;" onclick="location.href='myPage.do'">
				</li>
			</ul>
	</form>
</div>

<!-- 푸터 -->
<div id="footer">
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>