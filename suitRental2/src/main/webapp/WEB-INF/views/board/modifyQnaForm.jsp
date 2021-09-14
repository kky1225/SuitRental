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
		$('#modifyQna_form').submit(function(){
			if($('#title').val().trim()==''){
				alert('제목을 입력하세요.');
				$('#title').val('').focus();
				return false;
			}
			if($('#q_content').val().trim()==''){
				alert('내용을 입력하세요.');
				$('#q_content').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<h2>QnA 글 수정</h2>
	<form action="modifyQna.do" id="modifyQna_form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qna_num" value="${boardQna.qna_num}">
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="${boardQna.title}" maxlength="50">
			</li>
			<li>
				<label for="q_content">내용</label>
				<textarea rows="5" cols="30" name="q_content" id="q_content">${boardQna.q_content}</textarea>
			</li>
			<li>
				<label for="filename">파일</label>
				<input type="file" name="filename" id="filename" accept="image/gif, image/png, image/jpeg">
				<c:if test="${!empty boardQna.filename}">
					<br>
					<span>(${boardQna.filename})파일이 등록되어 있습니다.<br>수정 시 기존파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='listQna.do'">
		</div>
	</form>
</div>

</body>
</html>