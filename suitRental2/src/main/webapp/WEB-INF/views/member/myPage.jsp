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
		<h2>회원정보</h2>
			<h3>회원탈퇴</h3>
			<ul>
				<li>
					<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'">
				</li>
			</ul>
		</div>
		<div class="mypage-div">
			<h3>연락처</h3>
			<ul>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.address1} ${member.address2}</li>
				<li>가입일 : ${member.reg_date}</li>
				<li>최근 정보 수정일 : ${member.modify_date}</li>
				<li><input type="button" value="연락처 수정" onclick="location.href='modifyUserForm.do'"></li>
			</ul>
			<h3>비밀번호 수정</h3>
			<ul>
				<li>
					<input type="button" value="비밀번호 수정" onclick="location.href='modifyPasswordForm.do'">
				</li>
			</ul>
		</div>
	</div>
</body>
</html>