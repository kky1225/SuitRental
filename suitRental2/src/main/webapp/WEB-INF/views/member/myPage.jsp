<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<div> 
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h4 style="text-align:center; margin-top:50px;">회원정보</h4>
		<br><br>
		<div class="d-grid gap-2">
  			<button class="btn btn-lg btn-dark" type="button" onclick="location.href='deleteUserForm.do'">회원탈퇴</button>
  			<button class="btn btn-lg btn-dark" type="button" onclick="location.href='modifyUserForm.do'">개인정보 수정</button>
  			<button class="btn btn-lg btn-dark" type="button" onclick="location.href='modifyPasswordForm.do'">비밀번호 수정</button>
		</div>
			<br><br>
			<c:if test="${member.auth == 2}">
			<c:if test="${count == 0}">
				<div class="result-display">
				주문 내역이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
			<h6 class="align-center">주문 목록</h6>
			<br>
			<table>
				<tr>
					<th>주문번호</th>
					<th>제품코드</th>
					<th>제품명</th>
					<th>대여일</th>
					<th>반납일</th>
					<th>대여방법</th>
					<th>반납방법</th>
					<th>주문 수정</th>
					<th>주문 취소</th>
				</tr>
				<c:forEach var="order" items="${list}">
				<tr>
					<td>${order.rent_num}</td>
					<td>${order.x_code}</td>
					<td>${order.x_name}</td>
					<td>${order.rental_date}</td>
					<td>${order.return_date}</td>
					<td>${order.rental_type}</td>
					<td>${order.return_type}</td>
					<td><input type="button"class="btn btn-dark" value="주문 수정" onclick="location.href='modifyItemForm.do?rent_num=${order.rent_num}'"></td>
					<td><input type="button"class="btn btn-dark" value="주문 취소" onclick="location.href='deleteItemForm.do?rent_num=${order.rent_num}'"></td>
				</tr>	
				</c:forEach>
			</table>
			<div class="align-center">
				${pagingHtml}
			</div>
			</c:if>
		</c:if>
		</div>
	</div>
</body>
</html>