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
		<li>상품명 : ${productDetailVO.x_name}</li>
		<li>브랜드 : ${productDetailVO.x_brand} <br><br> </li>
		<li><hr size="1" noshade width="100%"></li>
		<li><img src="${pageContext.request.contextPath}/upload/${productDetailVO.x_file}" class="detail-img"></li>
		<li>가격 : ${price}</li>
		<li>성별 : ${productDetailVO.x_gender}</li>
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
		${board.x_contents}
	</p>
	<hr size="1" noshade width="100%">
	<div class="align-right">
		작성일 : ${board.x_reg_date}
		<input type="button" value="목록" onclick="location.href='productList.do'">
	</div>
</body>
</html>
