<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>대여폼 작성</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2)+1;

		var dateString = year + '-' + month  + '-' + day;

		console.log(dateString);
		
		$(function() {
			$('#rental_form').submit(function() {
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
				
				if($(':radio[name="rental_type"]:checked').length < 1){
					alert('대여 방법을 입력하세요!');
					return false;
				}
				
				if($(':radio[name="return_type"]:checked').length < 1){
					alert('반납 방법을 입력하세요!');
					return false;
				}
				
			});
		});
		
		</script>
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>대여</h2>
			<form id="rental_form" method="post" action="rental.do">
				<ul>
					<li>
						<input type="hidden" value="${dto.x_code}" name="code">
						<input type="hidden" value="${dto.mem_num}" name="num">
					</li>
				
					 <li>
						<label for="name">제품명</label>
						<input type="text" value="${dto.x_name}" class="form-control form-label mt-4"  name="name" readonly>
					</li>
					<br>
					<li>
						<label for="rental_date">대여 날짜</label>
						<input type="date" id="rental_date" class="form-control form-label mt-4"  name="rental_date">
					</li><br>
					<li>
						<label for="return_date">반납 날짜</label>
						<input type="date" id="return_date" class="form-control form-label mt-4" name="return_date">
					</li><br><br> 
					<li>
						<label for="rental_type">대여 방법</label>
						<input class="aa" type="radio" name ="rental_type" id ="visit" value = "방문">방문
						<input class="aa" type="radio" name ="rental_type" id ="delivery" value = "배송">배송									
					</li><br>
					<li>
						<label for="return_type">반납 방법</label>
						<input type="radio" name="return_type" id="visit" value="방문">방문
						<input type="radio" name="return_type" id="delivery" value="배송">배송
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" class="btn btn-dark" style="width:330px; margin-top:30px;" value="대여하기">
					<input type="button" class="btn btn-dark" style="width:330px; margin-top:30px;" value="상품목록" onclick="location.href='${pageContext.request.contextPath}/product/productList.do'">
				</div>
			</form>
		</div>
		
		<div id="footer">
			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		</div>

	</body>
</html>