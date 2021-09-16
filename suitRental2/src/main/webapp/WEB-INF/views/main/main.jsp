<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>		<!-- Link Swiper -->
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<!-- header 시작 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<!-- header 끝 -->
		
		<p>
		
		<!-- 슬라이드 배너 -->
		<!-- Swiper -->
	    <div class="swiper mySwiper">
	      <div class="swiper-wrapper">
	        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/main01.jpg" alt="메인이미지"></div>
	        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/main02.jpg" alt="메인이미지"></div>
	      </div>
	      <div class="swiper-pagination"></div>
	    </div>
	
	    <!-- Swiper JS -->
	    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	
	    <!-- Initialize Swiper -->
	    <script>
	      var swiper = new Swiper(".mySwiper", {
	        pagination: {
	          el: ".swiper-pagination",
	        },
	      });
	    </script>
	    <!-- 슬라이드 배너 끝 -->
	 			   
	    <div>
	    	<h5 style="text-align:center; margin-top:50px;"><b>상품</b></h5>
	    	<c:if test="${count == 0}">
				<div class="result-display">
					등록된 상품이 없습니다.
				</div>
				</c:if>
				<c:if test="${count > 0}">
				<div>
					<c:forEach var="product" items="${list}">
					<div class="horizonal-area">
						<a href="../product/productDetail.do?x_code=${product.x_code}">
							<c:if test="${!empty product.x_file}">
								<img src="${pageContext.request.contextPath}/upload/${product.x_file}">
							</c:if>
							<c:if test="${empty productDetailVO.x_file}">
								<img src="${pageContext.request.contextPath}/images/blank.gif">
							</c:if>
							<span>${productDetailVO.x_name}</span>
						</a>
					</div>
					</c:forEach>
				</div>
				</c:if>
	    </div>
	</div>	
    
    <!-- 푸터 -->
    <div id="footer">
    	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>

</body>
</html>