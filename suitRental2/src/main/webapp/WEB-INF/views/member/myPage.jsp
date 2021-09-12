<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<div> 
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h2 class="align-center">회원정보</h2>
			<h3>회원탈퇴</h3>
			<ul>
				<li>
					<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'">
				</li>
			</ul>
		</div>
		<div class="mypage-div">
			<h3>개인정보</h3>
			<ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.address1} ${member.address2}</li>
				<li>가입일 : ${member.reg_date}</li>
				<li>최근 정보 수정일 : ${member.modify_date}</li>
				<li><input type="button" value="개인정보 수정" onclick="location.href='modifyUserForm.do'"></li>
			</ul>
			<h3>비밀번호 수정</h3>
			<ul>
				<li>
					<input type="button" value="비밀번호 수정" onclick="location.href='modifyPasswordForm.do'">
				</li>
			</ul>
		</div>
		<c:if test="${member.auth == 2}">
		<div class="mypage-div">
			<h3>주문 목록</h3>
			<c:if test="${count == 0}">
				<div class="result-display">
				주문 내역이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
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
					<td><a href="detail.do?order_num=${order.rent_num}">${order.x_code}</a></td>
					<td>${order.x_name}</td>
					<td>${order.rental_date}</td>
					<td>${order.return_date}</td>
					<td>${order.rental_type}</td>
					<td>${order.return_type}</td>
					<td><input type="button" value="주문 수정" onclick="location.href='modifyItemForm.do?rent_num=${order.rent_num}'"></td>
					<td><input type="button" value="주문 취소" onclick="location.href='deleteItemForm.do?rent_num=${order.rent_num}'"></td>
				</tr>	
				</c:forEach>
			</table>
			<div class="align-center">
				${pagingHtml}
			</div>
			</c:if>
		</div>
		</c:if>
	</div>
</body>
</html>