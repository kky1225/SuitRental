<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#password_form').submit(function() {
			if($('#id').val().trim()==''){
				alert('ID를 입력하세요!');
				$('#id').val('').focus();
				return false;
			}
			if($('#origin_passwd').val().trim()==''){
				alert('현재 비밀번호를 입력하세요!');
				$('#origin_passwd').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('새 비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#cpasswd').val().trim()==''){
				alert('새 비밀번호 확인을 입력하세요!');
				$('#cpasswd').val('').focus();
				return false;
			}
			if($('#passwd').val() != $('#cpasswd').val()){
				alert('새 비밀번호와 새 비밀번호 확인이 불일치!');
				$('#cpasswd').val('').focus();
				return false;
			}
		});// end of submit
		
		// 새 비밀번호 확인까지 한 후 다시 새 비밀번호를 수정하려고 하면 새비밀번호 확인을 초기화
		$('#passwd').keyup(function() {	// 새 비밀번호 칸에서 키보드를 입력하는 순간
			$('#cpasswd').val('');
			$('#message_cpasswd').text('');
		});
		
		// 새 비밀번호
		$('#cpasswd').keyup(function() {
			if($('#passwd').val()==$('#cpasswd').val()){
				$('#message_cpasswd').css('color','blue').text('새 비밀번호 일치');
			}else{
				$('#message_cpasswd').text('');
			}
		});
	});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h5 style="text-align:center; margin-top:50px;"><b>비밀번호 수정</b></h5>
		<form action="modifyPassword.do" method="post" id="password_form">
			<ul>
				<li>
					<label for="id">ID</label>
					<input type="text" class="form-control form-label mt-4" id="id" name="id" maxlength="12">
				</li>
				<li>
					<label for="origin_passwd">현재 비밀번호</label>
					<input type="password" class="form-control form-label mt-4" id="origin_passwd" name="origin_passwd" maxlength="12">
				</li>
				<li>
					<label for="passwd">새 비밀번호</label>
					<input type="password" class="form-control form-label mt-4" name="passwd" id="passwd" maxlength="12">
				</li>
				<li>
					<label for="cpasswd">새 비밀번호 확인</label>
					<input type="password" class="form-control form-label mt-4" name="cpasswd" id="cpasswd" maxlength="12">
					<span id="message_cpasswd"></span>
				</li>
				<li>
					<input type="submit" class="btn btn-dark" value="비밀번호 수정" style="width:330px; margin-top:30px;">
				</li>
				<li>
					<input type="button" class="btn btn-dark" value="MY페이지" style="width:330px; margin-top:30px;" onclick="location.href='myPage.do'">
				</li>
			</ul>
		</form>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>