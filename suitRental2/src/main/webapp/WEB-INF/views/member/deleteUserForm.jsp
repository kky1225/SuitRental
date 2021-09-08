<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#delete_form').submit(function() {
			if($('#id').val().trim()==''){
				alert('ID를 입력하세요!');
				$('#id').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#cpasswd').val().trim()==''){
				alert('비밀번호 확인을 입력하세요!');
				$('#cpasswd').val('').focus();
				return false;
			}
			if($('#passwd').val() != $('#cpasswd').val()){
				aleret('비밀번호와 비밀번호 확인 불일치');
				$('#cpasswd').val('').focus();
				return false;
			}
		});	// end of submit
		
		// 비밀번호 확인까지 한 후 다시 비밀번호를 수정하면 비밀번호 확인 및 메시지 초기화
		$('#passwd').keyup(function() {
			$('#cpasswd').val('');
			$('#message_id').text('');
		});
		
		// 비밀번호와 비밀번호 확인
		$('#cpasswd').keyup(function() {
			if($('#passwd').val() == $('#cpasswd').val()){
				$('#message_id').css('color','blue').text('비밀번호 일치');
			}else{
				$('#message_id').text('');
			}
		});
		
	})
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h2 class="align-center">회원탈퇴</h2>
		<form action="deleteUser.do" method="post" id="delete_form">
			<ul>
				<li>
					<label for="id">ID</label>
					<input type="text" name="id" id="id" maxlength="12">
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" id="passwd" maxlength="12">
				</li>
				<li>
					<label for="cpasswd">비밀번호 확인</label>
					<input type="password" name="cpasswd" id="cpasswd" maxlength="12">
					<span id="message_id"></span>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="회원탈퇴">
				<input type="button" value="MY페이지" onclick="location.href='myPage.do'">
			</div>
		</form>
	</div>
</body>
</html>