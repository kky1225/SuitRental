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
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>리뷰 게시판 목록</h2>
			<div class="list-space align-right">
				<input type="button" value="글쓰기" onclick="location.href='reviewWriteForm.do'"
					<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
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