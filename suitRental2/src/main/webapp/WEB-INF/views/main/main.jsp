<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>메인</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		</div>
		<c:if test="${empty productDetailVO}">
			<div class="result-display">
				등록된 상품이 없습니다.
			</div>
		</c:if>
		<c:if test="${!empty productDetailVO}">
		<div class="container">
			<div id="carouselIndicators" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
					<li data-target="#carouselIndicators" data-slide-to="1"></li>
					<li data-target="#carouselIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="${pageContext.request.contextPath}/upload/${productDetailVO.x_file}" class="d-block w-100" width="100%" height="600">
						<div class="carousel-caption d-md-block">
							<h5 align="left" style="color:black">${productDetailVO.x_name}</h5>
							<h5 align="left" style="color:black">${productDetailVO.x_brand}</h5>
						</div>
					</div>
					<c:forEach var="productList" items="${productList}">
					<div class="carousel-item">
						<img src="${pageContext.request.contextPath}/upload/${productList.x_file}" class="d-block w-100" width="100%" height="600">
						<div class="carousel-caption d-md-block">
							<h5 align="left" style="color:black">${productList.x_name}</h5>
							<h5 align="left" style="color:black">${productList.x_brand}</h5>
						</div>
					</div>
					</c:forEach>
				</div>
				<a class="carousel-control-prev" href="#carouselIndicators" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="carousel-control-next" href="#carouselIndicators" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		</c:if>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		<script src="../js/jquery-3.6.0.min.js"></script>
		<script src="../js/bootstrap.bundle.min.js"></script>
	</body>
</html>