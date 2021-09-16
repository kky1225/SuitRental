<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#search_form').submit(function() {
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	})
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h5 style="text-align:center; margin-top:50px; margin-right:20px;"><b>회원목록(관리자 전용)</b></h5>
		<div class="list-space align-right">
			<input type="button"class="btn btn-dark" value="등급 변경" onclick="location.href='updateAuthForm.do'">
			<input type="button" class="btn btn-dark" value="목록" onclick="location.href='managerPage.do'">
		</div>
		<c:if test="${count==0}">
		<div class="result-display">
			등록된 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count>0}">
			<table>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>가입일</th>
					<th>등급</th>
				</tr>
				<c:forEach var="member" items="${list}">
				<tr>
					<td>
						<c:if test="${member.auth > 0}">
						${member.id}	
						</c:if>
						<c:if test="${member.auth == 0}">
						${member.id}
						</c:if>
					</td>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td>${member.phone}</td>
					<td>${member.reg_date}</td>
					<td>
					<c:if test="${member.auth == 0}">탈퇴</c:if>
					<c:if test="${member.auth == 1}">대여금지</c:if>
					<c:if test="${member.auth == 2}">일반</c:if>
					<c:if test="${member.auth == 3}">관리</c:if>
					</td>
				</tr>	
				</c:forEach>
			</table>
			<div class="align-center">
				${pagingHtml}
			</div>
		</c:if>
		<form id="search_form" action="managerPage.do" method="get">	<!-- 검색은 get 방식 -->
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1">ID</option>
						<option value="2">이름</option>
						<option value="3">email</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword">
				</li>
				<li>
					<input type="submit" value="찾기">
				</li>
			</ul>
		</form>
	</div>
</body>
</html>