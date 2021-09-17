<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>리뷰 게시글 작성</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#write_form').submit(function(){
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
				
				if($('#filename').change(function(){
					$('#filename_text').val($(this)[0].files[0].name);
				}));
			});
		</script>
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<form id="write_form" action="reviewWrite.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="x_code" value="${x_code}">
				<ul>
					<li>
						<h5 style="text-align:center; margin-top:50px;"><b>리뷰 작성</b></h5>
					</li>
					<li>
						<label for="title" class="form-label mt-4">제목</label>
						<input type="text" class="form-control" name="title" id="title" maxlength="50" style="width:420px;">
					</li>
					<li>
						<label for="content" class="form-label mt-4">내용</label>
						<textarea cols="30" class="form-control" rows="5" name="content" id="content"></textarea>
					</li>
					<li>
						<div class="row">
						<div class="col-auto" style="margin-left:-14px;">
							<input id="filename_text" class="form-control mt-4" value="파일선택" style="width:200px; margin-left:14px;" readonly>
						</div>
						<div class="col-auto" style="margin-top:26px;">
							<label for="filename" class="btn btn-dark" style="width:60px;">파일</label>
						</div>
						<input type="file" id="filename" name="filename" style="display:none;">
						</div>
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" class="btn btn-dark" value="작성" style="width:120px; margin-left:20px; margin-top:30px;">
					<input type="button"  class="btn btn-dark" value="목록" onclick="location.href='reviewList.do'" style="width:120px; margin-left:8px; margin-top:30px;">
				</div>
			</form>
		</div>
		
		<!-- 푸터 -->
		<div id="footer">
			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		</div>
		
	</body>
</html>