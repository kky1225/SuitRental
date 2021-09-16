<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 $('#modify_form').submit(function(){
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
		
		if($('#file_delete').click(function(){
			$('#filename').val('');
			$('#file_check1').text('');
			$('#file_check2').attr('value', 'none');
		}));
		
		if($('#filename').change(function(){
			$('#file_check1').text('');
			$('#file_check2').attr('value', 'none');
			$('#filename_text').val($(this)[0].files[0].name);
		}));
 });
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h5 class="align-center" style="margin-bottom:10px;">공지사항 수정</h5>
	<form action="modifyNotice.do" method="post" id="modify_form" enctype="multipart/form-data">
		<input type="hidden" name="notice_num" value="${notice.notice_num}" >
		<input type="hidden" name="mem_num" value="${notice.mem_num}" >
		<ul>
			<li>
				<label for="title" class="form-label">제목</label>
				<input type="text" id="title" name="title" class="form-control mt-4" value="${notice.title }" maxlength="50">
			</li>
			<li>
				<label for="content" class="form-label">내용</label>
				<textarea rows="5" cols="40" class="form-control" name="content" id="content">${notice.content }</textarea>
			</li>
			<li>
				<c:if test="${!empty notice.filename}">
					<p id="file_check1" style="margin-top:10px; margin-left:12px;">${notice.filename}</p>
					<input type="hidden" name="file_check2" id="file_check2" value="${notice.filename}">
				</c:if>
				<c:if test="${empty notice.filename}">
					<input type="hidden" name="file_check2" id="file_check2" value="none">
				</c:if>
			</li>
			<li>
						<div class="row">
						<div class="col-auto" style="margin-left:-14px;">
							<input id="filename_text" class="form-control mt-4" value="파일선택" style="width:200px; margin-left:14px;" readonly>
						</div>
						<div class="col-auto">
							<label for="filename" class="btn btn-dark" style="width:60px; margin-top:26px;">파일</label>
						</div>
							<input type="button" name="file_delete" id="file_delete" class="btn btn-dark" style="width:100px; height:38px; margin-top:26px;" value="파일 삭제">
							<input type="file" id="filename" name="filename" style="display:none;">
						</div>
					</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정" class="btn btn-dark" style="width:120px; margin-top:10px;">
			<input type="button" value="목록" onclick="location.href='noticeList.do'" class="btn btn-dark" style="width:120px; margin-top:10px;">
		</div>
	</form>
</div>

<!-- 푸터 -->
<div id="footer">
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>

</body>
</html>