<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰 게시글 수정</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#modify_form').submit(function(){
					if($('#title').val().trim() == ''){
						alert('제목을 입력하세요');
						$('#title').val('').focus();
						return false;
					}
					
					if($('#content').val().trim() == ''){
						alert('내용을 입력하세요');
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
				}));
				
			});
		</script>
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>리뷰 게시글 수정</h2>
			<form id="modify_form" action="reviewModify.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="review_num" value="${reviewVO.review_num}">
				<ul>
					<li>
						<label for="title">제목</label>
						<input type="text" name="title" id="title" maxlength="50" value="${reviewVO.title}">
					</li>
					<li>
						<label for="content">내용</label>
						<textarea cols="30" rows="5" name="content" id="content">${reviewVO.content}</textarea>
					</li>
					<li>
						<label for="filename">파일</label>
						<input type="file" name="filename" id="filename">
						<c:if test="${!empty reviewVO.filename}">
						<p id="file_check1">${reviewVO.filename}</p>
						<input type="hidden" name="file_check2" id="file_check2" value="${reviewVO.filename}">
						<input type="button" name="file_delete" id="file_delete" value="파일 삭제">
						</c:if>
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="등록">
					<input type="button" value="목록" onclick="location.href='reviewList.do'">
				</div>
			</form>
		</div>
	</body>
</html>