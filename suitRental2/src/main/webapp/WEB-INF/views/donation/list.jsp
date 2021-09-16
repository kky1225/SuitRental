<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기부 게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
tr{
	border-bottom: 1px solid gray;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#search_form').submit(function(){
		if($('#keyword').val().trim()==''){
			alert('검색어를 입력하세요.');
			$('#keyword').val('').focus();
			return false;
		}	
	});
});
</script>
</head>
<body>
<div class="page-main">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h4 style="margin-bottom:10px;">기부 게시판</h4>
	
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
		 class="btn btn-dark">
		<input type="button" value="목록" onclick="location.href='list.do'" class="btn btn-dark">
		<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'" class="btn btn-dark">
	</div>
	<c:if test="${count==0 }">
		<div class="result-display">
			등록된 게시물이 없습니다.
		</div>
	</c:if>
	<c:if test="${count!=0 }">
	<table class="table table-borderless" style="text-align:center;">
		<thead class="thead-dark">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		</thead>
		<c:forEach var="donation" items="${list}">
		<Tr>
			<td>${donation.donation_num }</td>
			<td><a href='detail.do?donation_num=${donation.donation_num }'>${donation.title }</a></td>
			<td>${donation.mem_id }</td>
			<td>${donation.reg_date }</td>
			<td>${donation.hit }</td>
		</Tr>
		</c:forEach>
	</table>
	<div class="align-center">
		${pagingHtml }
	</div>
	</c:if>
	<form id="search_form" action="list.do" method="get">		
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1">제목</option>
					<option value="2">작성자</option>
					<option value="3">내용</option>
				</select>
			</li>
			<li>
				<input type="search" size="16" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기" class="btn btn-outline-dark" style="font-size:14px;">
			</li>
		</ul>
	</form>
</div>
</body>
</html>