<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰 게시판 목록</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#search_form').submit(function(){
					if($('#keyword').val().trim('') == ''){
						alert('검색어를 입력하세요');
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
			<h2>리뷰 게시판 목록</h2>
			<form id="search_form" action="reviewList.do" method="get">
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
			<div class="list-space align-right">
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
			<c:if test="${count == 0}">
				<div class="result-display">
					등록된 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
				<table>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
					<c:forEach var="reviewVO" items="${reviewList}">
						<tr>
							<td>${reviewVO.review_num}</td>
							<td><a href="reviewDetail.do?review_num=${reviewVO.review_num}">${reviewVO.title}</a></td>
							<td>${reviewVO.id}</td>
							<td>${reviewVO.reg_date}</td>
							<td>${reviewVO.hit}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					${pagingHtml}
				</div>
			</c:if>
		</div>
	</body>
</html>