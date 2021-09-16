<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
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
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<h3 style="text-align:center; margin-top:50px;"><b>Q&amp;A</b></h3>
		<div class="list-space align-right">
			<input type="button"  class="btn btn-dark"  value="글쓰기" onclick="location.href='writeQnaForm.do'"
				<c:if test="${empty user_num}"> disabled = "disabled" </c:if>
			>	<!-- 로그인 안되어있을 시 글쓰기 비활성화 -->
			<input type="button"  class="btn btn-dark"  value="목록" onclick="location.href='listQna.do'">
			<input type="button"  class="btn btn-dark"  value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		<c:if test="${count == 0}">
			<div class="result-display">
				등록된 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count > 0}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="row">글번호</th>
						<th scope="row">제목</th>
						<th scope="row">작성자</th>
						<th scope="row">작성일</th>
						<th scope="row">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="boardQna" items="${listQna}">
					<tr class="table-light">
						<td>${boardQna.qna_num}</td>
						<td>
	                    	[질문]<a href="detailQna.do?qna_num=${boardQna.qna_num}">${boardQna.title}</a>
						</td>
						<td>${boardQna.id}</td>
						<td>${boardQna.reg_date}</td>
						<td>${boardQna.hit}</td>
					</tr>
					<c:if test="${!empty boardQna.a_content}">
						<tr class="table-light">
							<td></td>
							<td>
		                    	┖[답변]<a href="answerDetail.do?qna_num=${boardQna.qna_num}">${boardQna.title}</a>
							</td>
							<td>관리자</td>
							<td>${boardQna.ans_date}</td>
							<td></td>
						</tr>
					</c:if>
					</c:forEach>
				<tbody>
			</table>
			
			<!-- 버튼 부분 -->
			<div class="align-center">
				${pagingHtml}
			</div>

		</c:if>
		
		<!-- 검색 부분 -->
		<form id="search_form" action="listQna.do" method="get">
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

</body>
</html>