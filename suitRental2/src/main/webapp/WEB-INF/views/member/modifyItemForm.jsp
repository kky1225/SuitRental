<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	// 오늘 날짜 구하기
	var today = new Date();

	var year = today.getFullYear();
	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	var day = ('0' + today.getDate()).slice(-2)+1;

	var dateString = year + '-' + month  + '-' + day;

	console.log(dateString);

	$(function() {
		$('#modify_form').submit(function() {
			if($('#rental_date').val().trim()==''){
				alert('대여 날짜를 입력하세요');
				$('#rental_date').val('').focus();
				return false;
			}
			if($('#return_date').val().trim()==''){
				alert('반납 날짜를 입력하세요');
				$('#return_date').val('').focus();
				return false;
			}
			/* 대여 날짜는 현재 날짜를 기준으로 다음날부터 가능 */
			if($('#rental_date').val() < dateString){
				alert('대여 날짜는 다음날부터 가능합니다!');
				$('#rental_date').val('').focus();
				return false;
			}
			/* 반납 날짜는 대여 날짜보다 늦어야 된다*/
			if($('#rental_date').val() > $('#return_date').val()){
				alert('반납 날짜가 대여 날짜보다 늦어야 됩니다!');
				$('#return_date').val('').focus();
				return false;
			}
			
		});
	});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<h5 style="text-align:center; margin-top:50px;"><b>주문정보 수정</b></h5>
		<form action="modifyItem.do" method="post" id="modify_form">
			<input type="hidden" value="${order.rent_num}" name="rent_num">
			<ul>
				<li>
					<label for="x_code">제품코드</label>
					<input type="text" class="form-control form-label mt-4" id="x_code" name="x_code" value="${order.x_code}" disabled="disabled">
				</li>
				<li>
					<label for="x_code">제품명</label>
					<input type="text" class="form-control form-label mt-4" id="x_code" name="x_code" value="${order.x_name}" disabled="disabled">
				</li>
				<li>
					<label for="rental_date">대여 날짜</label>
					<input type="date" class="form-control form-label mt-4" id="rental_date" name="rental_date" value="${order.rental_date}">
				</li>
				<li>
					<label for="return_date">반납 날짜</label>
					<input type="date" class="form-control form-label mt-4" id="return_date" name="return_date" value="${order.return_date}">
				</li>
				<li>
				<br>
					<label for="rental_type">대여 방법</label>
					방문<input type="radio" name="rental_type" id="rental_type1" value="방문"
					<c:if test="${order.rental_type.equals('방문')}">checked="checked"</c:if>>
					배송<input type="radio" name="rental_type" id="rental_type2" value="배송"
					<c:if test="${order.rental_type.equals('배송')}">checked="checked"</c:if>>
				</li>
				<li>
				<br>
					<label for="return_type">반납 방법</label>
					방문<input type="radio" name="return_type" id="return_type1" value="방문" 
					<c:if test="${order.return_type.equals('방문')}">checked="checked"</c:if>>
					배송<input type="radio" name="return_type" id="return_type2" value="배송"
					<c:if test="${order.return_type.equals('배송')}">checked="checked"</c:if>>
				</li>
				<li>
					<input type="submit" class="btn btn-dark" value="주문수정" style="width:330px; margin-top:30px;">
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
































