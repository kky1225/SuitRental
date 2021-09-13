<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>대여폼 작성</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript">
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2)+1;

		var dateString = year + '-' + month  + '-' + day;

		console.log(dateString);
		
		
		
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
						<input type="text" value="${dto.x_name}" name="name" readonly>
					</li>
					<br>
					<li>
						<label for="rental_date">대여 날짜</label>
						<input type="date" id="rental_date" name="rental_date">
					</li><br>
					<li>
						<label for="return_date">반납 날짜</label>
						<input type="date" id="return_date" name="return_date">
					</li><br> 
					<div>
						<label for="rental_type">대여 방법</label>
						<input class="aa" type="radio" name ="rental_type" id ="visit" value = "visit">방문
						<input class="aa" type="radio" name ="rental_type" id ="delivery" value = "delivery">배송									
					</div> 
					
					<li>
						<label for="return_type">반납 방법</label>
						<input type="radio" name="return_type" id="visit" value="visit">방문
						<input type="radio" name="return_type" id="delivery" value="delivery">배송
					</li><br>
					
				</ul>
				<div class="align-center">
					<input type="submit" value="대여하기">
					<input type="button" value="상품목록" onclick="location.href='${pageContext.request.contextPath}/product/productList.do'">
				</div>
			</form>
		</div>
	</body>
</html>