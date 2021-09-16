<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!--   
<h1 class="align-center">회원제 게시판</h1>
<div class="align-left">
	<a href="${pageContext.request.contextPath}/product/productList.do">상품목록</a>
	<a href="${pageContext.request.contextPath}/review/reviewList.do">리뷰 게시판</a>
	<c:if test="${!empty user_num && user_auth == 2}">
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	<c:if test="${!empty user_num && user_auth == 3}">
		<a href="${pageContext.request.contextPath}/member/managerPage.do">관리자 페이지</a>
	</c:if>
</div>
<div class="align-right">
	<c:if test="${!empty user_num}">
		[<span>${user_id}</span>]
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	<c:if test="${empty user_num}">
		<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
	</c:if>
</div>
-->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<nav class="navbar navbar-expand-lg navbar-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.do">정장대여</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="menu">
			<ul class="navbar-nav me-auto">
				<li class="nav-product">
					<a class="nav-link" href="${pageContext.request.contextPath}/product/productList.do?list=1">상품</a>
				</li>
				<li class="nav-review">
					<a class="nav-link" href="${pageContext.request.contextPath}/review/reviewList.do">리뷰</a>
				</li>
				<li class="nav-donation">
					<a class="nav-link" href="${pageContext.request.contextPath}/donation/list.do">기부</a>
				</li>
				<li class="nav-q&a">
					<a class="nav-link" href="${pageContext.request.contextPath}/board/listQna.do">Q&amp;A</a>
				</li>
				<li class="nav-notice">
					<a class="nav-link" href="${pageContext.request.contextPath}/notice/noticeList.do">공지사항</a>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<c:if test="${!empty user_num && user_auth == 2}">
					<a class="nav-link" href="${pageContext.request.contextPath}/member/myPage.do">마이페이지</a>
					</c:if>
					<c:if test="${!empty user_num && user_auth == 3}">
					<a class="nav-link" href="${pageContext.request.contextPath}/member/managerPage.do">관리자페이지</a>
					</c:if>
				</li>
				<c:if test="${empty user_num}">
				<li class="nav-login">
					<a class="nav-link float-left" href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
				</li>
				<li class="nav-register">
					<a class="nav-link float-left" href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
				</li>
				</c:if>
				<c:if test="${!empty user_num}">
				<li class="nav-logout">
					<a class="nav-link float-left" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>