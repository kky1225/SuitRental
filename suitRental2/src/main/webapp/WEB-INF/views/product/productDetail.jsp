<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>제품 상세</h2>
	<ul>
		<li>상품코드 : ${x_code}</li>
		<li>상품명 : ${productDetailVO.x_name}</li>
		<li>브랜드 : ${productDetailVO.x_brand} <br><br> </li>
		<li><hr size="1" noshade width="100%"></li>
		<li><img src="${pageContext.request.contextPath}/upload/${productDetailVO.x_file}" class="detail-img"></li>
		<li>가격 : ${price}</li>
		<li>성별 : <c:if test="${productDetailVO.x_gender == 'male'}">남자</c:if>
				  <c:if test="${productDetailVO.x_gender == 'female'}">여자</c:if>
		</li>
		<li>사이즈 : ${productDetailVO.x_size}</li>
		<li>총 대여수 : ${productDetailVO.x_rental_count}</li>
		<li>조회수 : ${productDetailVO.x_hit}</li>
		<li>좋아요수 : ${productDetailVO.x_like}</li>
		<li>옷 종류 : <c:if test="${productDetailVO.x_type == 'jacket'}">자켓</c:if>
					<c:if test="${productDetailVO.x_type == 'shirts'}">셔츠</c:if>
					<c:if test="${productDetailVO.x_type == 'slacks'}">슬랙스</c:if>
					<c:if test="${productDetailVO.x_type == 'shoes'}">구두</c:if>
		</li>
		<li>구매수 : ${productDetailVO.x_purchase}</li>
	</ul>
	<c:if test="${!empty board.x_file}">
	<div class="align-center">
		<img src="${pageContext.request.contextPath}/uploadFile/${board.x_file}" class="detail-img">
	</div>
	</c:if>
	<p>
		${productDetailVO.x_contents}
	</p>
	<hr size="1" noshade width="100%">
	<div class="align-right">
		<c:if test="${user_auth == 2}">
			<input type="button" value="상품 대여" onclick="location.href='${pageContext.request.contextPath}/rental/rentalForm.do?x_code=${productDetailVO.x_code}'">	
			<c:if test="${check == true}">
				<input type="button" value="좋아요" onclick="location.href='likeyUp.do?x_code=${x_code}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
			</c:if>
			<c:if test="${check == false}">
				<input type="button" value="좋아요 취소" onclick="location.href='likeyDown.do?x_code=${x_code}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
			</c:if>
			<input type="button" value="후기 작성" onclick="location.href='../review/reviewWriteForm.do?x_code=${x_code}'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
		</c:if>
		<c:if test="${user_auth == 3}">
		<input type="button" value="수정" id="modify_btn" onclick="location.href='productModifyForm.do?x_code=${x_code}'">
		<input type="button" value="삭제" id="delete_btn">
					<script type="text/javascript">
						var delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick = function(){
							var choice = confirm('정말 삭제하시겠습니까?');
							if(choice){
								location.replace('productDelete.do?x_code=${productDetailVO.x_code}');
							}
						};
		</script>
		</c:if>
		<input type="button" value="목록" onclick="location.href='productList.do'">
	</div>
	<div class="page-main">
			<h2>상품 후기</h2>
			<c:if test="${count == 0}">
				<div class="result-display">
					등록된 후기가 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
				<table>
					<tr>
						<th>사진</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
					<c:forEach var="reviewVO" items="${reviewList}">
						<tr>
							<td><c:if test="${!empty reviewVO.filename}"><img src="${pageContext.request.contextPath}/upload/${reviewVO.filename}" class="detail-img" border="0" width="100" height="100"></c:if>
							<c:if test="${empty reviewVO.filename}">등록된 사진이 없음</c:if></td>
							<td><a href="../review/reviewDetail.do?review_num=${reviewVO.review_num}">${reviewVO.title}</a></td>
							<td>${reviewVO.id}</td>
							<td>${reviewVO.reg_date}</td>
							<td>${reviewVO.hit}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					${pagingHtml}
				</div>
			</c:if>
		</div>
</body>
</html>