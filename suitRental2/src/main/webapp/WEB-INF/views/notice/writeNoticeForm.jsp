<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 폼</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 $('#write_form').submit(function(){
		if($('#title').val().trim()==''){
			alert('제목을 입력하세요.');
			$('#title').val('').focus();
			return false;
		} 
		if($('#content').val().trim()==''){
			alert('내용을 입력하세요.');
			$('#content').val('').focus();
			return false;
		} 
	 });
 });
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h5 class="align-center" style="margin-bottom:10px;">공지사항 작성</h5>
	<form action="writeNotice.do" method="post" id="write_form" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="title" class="form-label">제목</label>
				<input type="text" id="title" name="title" class="form-control mt-4" style="width:285px;" maxlength="50">
			</li>
			<li>
				<label for="content" class="form-label">내용</label>
				<textarea rows="5" cols="40" name="content" id="content" class="form-control"></textarea>
			</li>
			<li>
				<label for="filename" class="form-label">파일</label>
				<input type="file" id="filename" name="filename" class="form-control" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록" class="btn btn-dark" style="width:120px; margin-top:20px;">
			<input type="button" value="목록" onclick="location.href='noticeList.do'" class="btn btn-dark" style="width:120px; margin-top:20px;">
			
		</div>
	</form>
</div>

<div id="footer">
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>

</body>
</html>