<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>권한 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#updateAuth_form').submit(function() {
			if($('#manager_id').val().trim()==''){
				alert('관리자 ID를 입력하세요!');
				$('#manager_id').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('관리자 비밀번호를 입력하세요!');
				$('#passwd').val('').focus();
				return false;
			}
			if($('#member_id').val().trim()==''){
				alert('회원 ID를 입력하세요!');
				$('#member_id').val('').focus();
				return false;
			}
		});
	})
</script>
</head>
<body>	
	<div class="page-main">
		<div>
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2 class="align-center">회원 권한 변경</h2>
			<form action="updateAuth.do" id="updateAuth_form" method="post">
				<ul>
					<li>
						<label for="manager_id">관리자 ID</label>
						<input type="text" id="manager_id" name="manager_id" maxlength="12">
					</li>
					<li>
						<label for="passwd">비밀번호</label>
						<input type="password" id="passwd" name="passwd" maxlength="12">
					</li>
					<li>
						<label for="member_id">회원 ID</label>
						<input type="text" id="member_id" name="member_id" maxlength="12">
					</li> 
					<li>
						<label for="auth">회원 등급</label>
						<input type="radio" name="auth" value="1" checked="checked"> 대여 금지 회원
						<input type="radio" name="auth" value="2"> 일반 회원
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="수정">
					<input type="button" value="관리자 페이지" onclick="location.href='managerPage.do'">
				</div>
			</form>
		</div>
	</div>
</body>
</html>