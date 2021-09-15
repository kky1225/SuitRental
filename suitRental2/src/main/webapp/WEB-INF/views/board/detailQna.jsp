<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>

	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />		
		<ul>
			<li>
				<h5 style="text-align:center; margin-top:50px;"><b>Q&amp;A</b></h5>
			</li>
			<li>글번호 : ${boardQna.qna_num}</li>
			<li>글제목 : ${boardQna.title}</li>
			<li>작성자 : ${boardQna.id}</li>
			<li>조회수 : ${boardQna.hit}</li>
		</ul>
		<hr size="1" noshade width="100%">
		<p>
			${boardQna.q_content}
		</p>
		<c:if test="${!empty boardQna.filename}">
			<div class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${boardQna.filename}" class="detail-img">
			</div>
		</c:if>
			
		<hr size="1" noshade width="100%">
		<div class="align-right">
			작성일 : ${boardQna.reg_date}
			
			<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정 가능 --%>
			<c:if test="${user_num == boardQna.mem_num}">
				<input type="button" class="btn btn-dark" value="수정" onclick="location.href='modifyQnaForm.do?qna_num=${boardQna.qna_num}'">
			</c:if>
			<%-- 로그인한 회원번호와 작성자 회원번호가 일치하거나 관리자일 경우 삭제 가능 --%>
			<c:if test="${user_num == boardQna.mem_num || user_auth ==  3}">
				<input type="button" class="btn btn-dark" value="삭제" id="delete_btn">
				
				<script type="text/javascript">
					var delete_btn = document.getElementById('delete_btn');
					
					// 이벤트 연결
					delete_btn.onclick =  function(){
						var choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('deleteQna.do?qna_num=${boardQna.qna_num}');
						}
					};
				</script>
			</c:if>
			
			<%-- 관리자 답글 기능 --%>
			<c:if test="${!empty user_num && user_auth ==  3}">
				<input type="button" class="btn btn-dark" value="답변" onclick="location.href='answerQnaForm.do?qna_num=${boardQna.qna_num}'">
			</c:if>
			
			<input type="button" class="btn btn-dark" value="목록" onclick="location.href='listQna.do'">
		</div>
	</div>

</body>
</html>