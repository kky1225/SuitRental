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
		<form action="answerQna.do" id="answerQna_form" method="post">
			<input type="hidden" name="qna_num" value="${boardQna.qna_num}">
			<ul>
				<li>
					<h5 style="text-align:center; margin-top:50px;"><b>QnA 답변</b></h5>
				</li>
				<li>
					<label for="title">질문 제목</label>
					<input type="text" class="form-control form-label mt-4" id="title" name="title" value="${boardQna.title}" maxlength="50" readonly>					
				</li>
				<li>
					<label>질문 내용</label>
					<textarea cols="30" class="form-control" rows="3" name="q_content" id="q_content" readonly>${boardQna.q_content}</textarea>
				</li>
				<li>
					<br>
					<label for="a_content">답변</label>
					<textarea cols="30" class="form-control" rows="5" name="a_content" id="a_content"></textarea>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" class="btn btn-dark" value="등록" style="width:120px; margin-top:10px; margin-left:20px;">
				<input type="button" class="btn btn-dark" value="목록" onclick="location.href='listQna.do'" style="width:120px; margin-top:10px; margin-left:8px;">
			</div>
		</form>
	</div>
	
	<!-- 푸터 -->
	<div id="footer">
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>

</body>
</html>