<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

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
	<h3 style="text-align:center; margin-top:50px;"><b>공지사항</b></h3>
	
	<div class="list-space align-right">
		<input type="button" value="글쓰기" onclick="location.href='writeNoticeForm.do'"
			<c:if test="${user_auth != 3}">disabled="disabled"</c:if>
		 class="btn btn-dark">
	</div>
	<c:if test="${count==0 }">
		<div class="result-display">
			등록된 게시물이 없습니다.
		</div>
	</c:if>
	<c:if test="${count!=0 }">
		<table class="table table-hover">
		 	<thead>
		 	<tr>
		 		<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
		 	</tr>
		 	</thead>
		 	<tbody>
		 	<c:forEach var="notice" items="${list}">
		 		<tr class="table-light">
		 			<td>${notice.notice_num }</td>
		 			<td><a href='noticeDetail.do?notice_num=${notice.notice_num }'>${notice.title }</a></td>
		 			<td>${notice.mem_id }</td>
		 			<td>${notice.reg_date }</td>
		 			<td>${notice.hit }</td>
		 		</tr>
		 	</c:forEach>
		 	</tbody>
		</table>
		<div class="align-center">
			${pagingHtml }
		</div>
	</c:if>
	<form id="search_form" action="noticeList.do" method="get">		
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
				<input type="submit" value="찾기">
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