<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('#login_form').submit(function(){
					if($('#id').val().trim()==''){
						alert('아이디를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					if($('#passwd').val().trim()==''){
						alert('비밀번호를 입력하세요');
						$('#passwd').val('').focus();
						return false;
					}
				});
			});
</script>
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h5 style="text-align:center; margin-top:50px; margin-right:20px;"><b>로그인</b></h5>
			<form id="login_form" action="login.do" method="post">
				<ul>
					<li>
      					<label for="id">아이디</label>
        				<input type="text" class="form-control form-label mt-4" name="id" id="id" maxlength="12" autocomplete="off">
      				</li>
      				<li>
						<label for="passwd">비밀번호</label>
      					<input type="password" class="form-control form-label mt-4" name="passwd" id="passwd" maxlength="12">
      				</li>
      				<li>
      					<input type="submit" class="btn btn-dark" value="로그인" style="width:330px; margin-top:50px;">
      				</li>
      				<li>
      					<input type="button" class="btn btn-dark" value="회원가입" style="width:330px; margin-top:40px;" onclick="location.href='registerUserForm.do'">
      				</li>
				</ul>
			</form>
		</div>
	</body>
</html>