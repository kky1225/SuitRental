<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 질문하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#writeQna_form').submit(function(){
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
		<h2>QnA 작성</h2>
		<form action="writeQna.do" id="writeQna_form" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="title">제목</label>
					<input type="text" id="title" name="title" maxlength="50">
				</li>
				<li>
					<label for="q_content">내용</label>
					<textarea rows="5" cols="30" name="q_content" id="q_content"></textarea>
				</li>
				<li>
					<label for="filename">파일</label>
					<input type="file" name="filename" id="filename" accept="image/gif, image/png, image/jpeg">
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