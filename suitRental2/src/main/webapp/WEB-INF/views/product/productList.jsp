<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 게시판 목록</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h3 style="text-align:center; margin-top:50px;"><b>SUIT</b></h3>
			<div class="list-space align-right">
				<c:if test="${user_auth == 3}">
				<input type="button" value="상품 등록" class="btn btn-dark" onclick="location.href='productWriteForm.do'"
					<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
				</c:if>
			</div>
			<div class="list-space align-left">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/product/productList.do?list=1" style="color:black; font-size:20px;">좋아요 순</a>
				<a class="navbar-brand"></a>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/product/productList.do?list=2" style="color:black; font-size:20px;">판매 순</a>
			</div>
			<c:if test="${count == 0 && list == 1}">
				<div class="result-display">
					등록된 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0 && list == 1}">
				<table class="table table-hover">
					<tr>
						<th>사진</th>
						<th>이름</th>
						<th>브랜드</th>
						<th>가격</th>
						<th>좋아요</th>
					</tr>
					<c:forEach var="productDetailVO" items="${productList}">
						<tr class="table-light">
							<td><img src="${pageContext.request.contextPath}/upload/${productDetailVO.x_file}" class="detail-img" border="0" width="100" height="100"></td>
							<td><a href="productDetail.do?x_code=${productDetailVO.x_code}">${productDetailVO.x_name}</a></td>
							<td>${productDetailVO.x_brand}</td>
							<td>${productDetailVO.x_price}</td>
							<td>${productDetailVO.x_like}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					${pagingHtml}
				</div>
			</c:if>
			<c:if test="${count == 0 && list == 2}">
				<div class="result-display">
					등록된 게시물이 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0 && list == 2}">
				<div class="result-display">
					등록된 게시물이 없습니다.
				</div>
				<div class="align-center">
					${pagingHtml}
				</div>
			</c:if>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</body>
</html>