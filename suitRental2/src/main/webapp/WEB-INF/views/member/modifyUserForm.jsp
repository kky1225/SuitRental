<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#modify_form').submit(function() {
			if($('#name').val().trim() == ''){
				alert('이름을 입력하세요!');
				$('#name').val('').focus();
				return false;
			}
			if($('#phone').val().trim() == ''){
				alert('전화번호를 입력하세요!');
				$('#phone').val('').focus();
				return false;
			}
			if($('#email').val().trim() == ''){
				alert('이메일을 입력하세요!');
				$('#email').val('').focus();
				return false;
			}
			if($('#zipcode').val().trim() == ''){
				alert('우편번호를 입력하세요!');
				$('#zipcode').val('').focus();
				return false;
			}
			if($('#address1').val().trim() == ''){
				alert('주소를 입력하세요!');
				$('#address1').val('').focus();
				return false;
			}
			if($('#address2').val().trim() == ''){
				alert('나머지 주소를 입력하세요!');
				$('#address2').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h2 class="align-center">회원정보 수정</h2>
		<form id="modify_form" action="modifyUser.do" method="post">
			<!-- 세션에 회원번호가 있기 때문에 hidden으로 처리할 필요가 없다 -->
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" name="name" id="name" value="${member.name}" maxlength="10">
				</li>
				<li>
					<label for="phone">전화번호</label>
					<input type="text" name="phone" id="phone" value="${member.phone}" maxlength="15">
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email" id="email" value="${member.email}" maxlength="50">
				</li>
				<li>
					<label for="zipcode">우편번호</label>
					<input type="text" name="zipcode" id="zipcode" value="${member.zipcode}" maxlength="5">
				</li>
				<li>
					<label for="address1">주소</label>
					<input type="text" name="address1" id="address1" value="${member.address1}" maxlength="30">
				</li>
				<li>
					<label for="address2">나머지 주소</label>
					<input type="text" name="address2" id="address2" value="${member.address2}" maxlength="30">
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="MY페이지" onclick="location.href='myPage.do'">
			</div>
		</form>
	</div>
</body>
</html>