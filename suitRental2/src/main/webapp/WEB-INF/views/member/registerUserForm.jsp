<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				var idChecked = 0;
				
				$('#message_id').text('');
				
				$('#register_form #id').keyup(function(){
					
					$.ajax({
						url:'checkDuplicatedId.do',
						type:'post',
						data:{id:$('#id').val()},
						dataType:'json',
						cache:false,
						timeout:30000,
						success:function(param){
							if(param.result == 'idNotFound'){
								idChecked = 1;
								$('#message_id').css('color','green').text('사용 가능');
							}else if(param.result == 'idDuplicated'){
								idChecked = 0;
								$('#message_id').css('color','red').text('아이디 중복');
							}else{
								idChecked = 0;
								alert('아이디 중복 체크 오류 발생');
							}
						},
						error : function() {
							idChecked = 0;
							alert('네트워크 오류 발생');
						}
					});
				});
				
				$('#message_passwd').text('');
				
				$('#register_form #passwd_check').keyup(function(){
					if($('#passwd').val().trim() == $('#passwd_check').val().trim()){
						$('#message_passwd').text('비밀번호 일치').css('color','green').style("text-align", "center");
					}else{
						$('#message_passwd').text('비밀번호 불일치').css('color','red').style("text-align", "center");;
					}
				})
				
				$('#register_form #passwd').keyup(function(){
					if($('#passwd_check').val().trim() == ''){
						$('#message_passwd').text('');
					}else{
						
						if($('#passwd').val().trim() == $('#passwd_check').val().trim()){
							$('#message_passwd').text('비밀번호 일치').css('color','green');
						}else{
							$('#message_passwd').text('비밀번호 불일치').css('color','red');
						}
					}
				})
				
				$('#register_form #passwd_check').keydown(function(){
					$('#message_passwd').text('');
				});	
				
				$('#register_form').submit(function(){
					if($('#id').val().trim() == ''){
						alert('아이디를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if(idChecked == 0){
						alert('아이디가 중복되었습니다');
						return false;
					}
					
					if($('#passwd').val().trim() == ''){
						alert('비밀번호를 입력하세요');
						$('#passwd').val('').focus();
						return false;
					}
					
					if($('#passwd_check').val().trim() == ''){
						alert('비밀번호 확인을 입력하세요');
						$('#passwd_check').val('').focus();
						return false;
					}
					
					if($('#passwd').val().trim() != $('#passwd_check').val().trim()){
						alert('비밀번호와 비밀번호 확인의 값이 다릅니다.');
						$('#passwd_check').val('').focus();
						return false;
					}
					
					if($('#name').val().trim() == ''){
						alert('이름을 입력하세요');
						$('#name').val('').focus();
						return false;
					}
					
					if($('#phone').val().trim() == ''){
						alert('전화번호를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if($('#email').val().trim() == ''){
						alert('이메일 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if($('#zipcode').val().trim() == ''){
						alert('우편번호를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if($('#address1').val().trim() == ''){
						alert('주소를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if($('#address2').val().trim() == ''){
						alert('상세주소를 입력하세요');
						$('#id').val('').focus();
						return false;
					}
					
					if($(':radio[name="gender"]:checked').length < 1){
						alert('성별을 입력하세요');
						return false;
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h5 style="text-align:center; margin-top:50px; margin-right:20px;"><b>회원가입</b></h5>
		<form id="register_form" action="registerUser.do" method="post">
			<ul>
				<li>
      				<label for="id">아이디</label>
        			<input type="text" class="form-control form-label mt-4" name="id" id="id" maxlength="12" autocomplete="off">
					<span id="message_id"></span>
				</li>
				<li>
					<label for="passwd">비밀번호</label>
      				<input type="password" class="form-control form-label mt-4" name="passwd" id="passwd" maxlength="12">
				</li>
				<li>
					<label for="passwd_check">비밀번호 확인</label>
      				<input type="password" class="form-control form-label mt-4" name="passwd_check" id="passwd_check" maxlength="12">
					<span id="message_passwd"></span>
				</li>
				<li>
					<label for="name">이름</label>
        			<input type="text" class="form-control form-label mt-4" name="name" id="name" maxlength="10">
				</li>
				<li>
					<label for="phone">전화번호</label>
        			<input type="text" class="form-control form-label mt-4" name="phone" id="phone" maxlength="15">
				</li>
				<li>
					<label for="email">이메일</label>
        			<input type="email" class="form-control form-label mt-4" name="email" id="email" maxlength="50">
				</li>
				<li>
					<label for="zipcode">우편번호</label>
					<input type="text" class="form-control form-label mt-4" name="zipcode" id="zipcode" maxlength="5">
				</li>
				<li>
					<label for="address1">주소</label>
					<input type="text" class="form-control form-label mt-4" name="address1" id="address1" maxlength="30">
				</li>
				<li>
					<label for="address2">상세 주소</label>
					<input type="text" class="form-control form-label mt-4" name="address2" id="address2" maxlength="30">
				</li>
				<li>
					<label for="gender" class="mt-4">성별</label>
					<input type="radio" class="mt-4" name="gender" id="male" value="male">남자
					<input type="radio" class="mt-4" name="gender" id="female" value="female" style="margin-left:30px;">여자
				</li>
				<li>
					<input type="submit" class="btn btn-dark" value="등록" style="width:330px; margin-top:30px;">
				</li>
			</ul>
		</form>
		</div>
	</body>
</html>