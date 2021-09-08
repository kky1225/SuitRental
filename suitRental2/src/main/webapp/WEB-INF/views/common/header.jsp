<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<h1 class="align-center">회원제 게시판</h1>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
	<c:if test="${!empty user_num}">
		[<span>${user_id}</span>]
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	<c:if test="${empty user_num}">
		<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
	</c:if>
	<c:if test="${!empty user_num && user_auth == 2}">
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
</div>