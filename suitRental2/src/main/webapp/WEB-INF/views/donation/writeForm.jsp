<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>기부 글작성폼</title>
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
		})
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h5 class="align-center" style="margin-bottom:10px;">기부하기</h5>
	<form action="write.do" method="post" id="write_form" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="title" class="form-label">제목</label>
				<input type="text" id="title" name="title" class="form-control mt-4" style="width:285px;" maxlength="50">
			</li>
			<li>
				<label for="content" class="form-label">내용</label>
				<textarea rows="5" cols="40" name="content" id="content" class="form-control"></textarea>
				<br>
				<span style="font-size:11pt;color:#999;padding-left:45px;">*물품의 종류, 갯수, 사이즈 등 설명을 입력하세요.*</span>
			</li>
			<li style="margin-top:10px;">
				<label for="filename" class="form-label">파일</label>
				<input type="file" id="filename" name="filename" class="form-control" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록" class="btn btn-dark" style="width:120px; margin-top:20px;">
			<input type="button" value="목록" onclick="location.href='list.do'" class="btn btn-dark" style="width:120px; margin-top:20px;">
			
		</div>
	</form>
</div>
</body>
</html>