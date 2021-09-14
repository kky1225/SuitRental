<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 질문글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#answerQna_form').submit(function(){
			if($('#a_content').val().trim()==''){
				alert('내용을 입력하세요.');
				$('#a_content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>

	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<h2>QnA 답변 등록</h2>
		<form action="answerQna.do" id="answerQna_form" method="post">
			<input type="hidden" name="qna_num" value="${boardQna.qna_num}">
			<ul>
				<li>
					<label for="title">질문 제목</label>
					${boardQna.title}
				</li>
				<li>
					<label>질문 내용</label>
					${boardQna.q_content}
				</li>
				<li>
					<label for="a_content">답변</label>
					<textarea rows="5" cols="30" name="a_content" id="a_content"></textarea>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='listQna.do'">
			</div>
		</form>
	</div>

</body>
</html>