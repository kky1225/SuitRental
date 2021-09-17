<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h3 style="text-align:center; margin-top:50px;"><b>제품 상세</b></h3>
	
	<div class="product-detail">
		<div class="product-img">
			<img src="${pageContext.request.contextPath}/upload/${productDetailVO.x_file}" class="detail-img" width="300px">
		</div>
		<div class="product-info">
			<ul>
				<li><h4>${productDetailVO.x_name}</h4></li>
				<li>${productDetailVO.x_contents}<br><br></li>
				<li><b>상품코드</b><span class="badge rounded-pill bg-light">${x_code}</span></li>
				<li>가 격 : ${price}</li>
				<li>브랜드 : ${productDetailVO.x_brand}</li>
				<li>분류 : <c:if test="${productDetailVO.x_type == 'jacket'}">자켓</c:if>
							<c:if test="${productDetailVO.x_type == 'shirts'}">셔츠</c:if>
							<c:if test="${productDetailVO.x_type == 'slacks'}">슬랙스</c:if>
							<c:if test="${productDetailVO.x_type == 'shoes'}">구두</c:if>
				</li>
				<li>성별 : <c:if test="${productDetailVO.x_gender == 'male'}">남자</c:if>
						  <c:if test="${productDetailVO.x_gender == 'female'}">여자</c:if>
				</li>
				<li>사이즈 : ${productDetailVO.x_size}</li>
			</ul>
			<hr size="1" noshade width="100%">
			<ul class="info-etc">				
				<li>조회수 : ${productDetailVO.x_hit}</li>
				<li>좋아요수 : ${productDetailVO.x_like}</li>
				<li>총 대여수 : ${productDetailVO.x_purchase_cnt}</li>
			</ul>
		</div>
	</div>

	<c:if test="${!empty board.x_file}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/uploadFile/${board.x_file}" class="detail-img">
		</div>
	</c:if>
	
	<div class="align-right">
		<c:if test="${user_auth == 2}">
			<c:if test="${likey == true}">
				<input type="button" class="btn btn-dark"  value="좋아요" onclick="location.href='likeyUp.do?x_code=${x_code}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
			</c:if>
			<c:if test="${likey == false}">
				<input type="button" class="btn btn-dark"  value="좋아요 취소" onclick="location.href='likeyDown.do?x_code=${x_code}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
			</c:if>
			<input type="button" class="btn btn-dark"  value="상품 대여" onclick="location.href='${pageContext.request.contextPath}/rental/rentalForm.do?x_code=${productDetailVO.x_code}'">	
		</c:if>
		<c:if test="${user_auth == 3}">
		<input type="button" class="btn btn-dark"  value="수정" id="modify_btn" onclick="location.href='productModifyForm.do?x_code=${x_code}'">
		<input type="button" class="btn btn-dark"  value="삭제" id="delete_btn">
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
		<input type="button" class="btn btn-dark"  value="목록" onclick="location.href='productList.do?list=1'">
	</div>
	<div class="page-main">
			<h3 style="text-align:center; margin-top:50px;"><b>제품 후기</b></h3>
			<c:if test="${review == true}">
				<div class="align-right">
				<input type="button" class="btn btn-dark"  value="후기 작성" onclick="location.href='../review/reviewWriteForm.do?x_code=${x_code}'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				>
			</div>
			</c:if>
			<c:if test="${review == false}">
				<div class="align-right">
				<input type="button" class="btn btn-dark"  value="후기 작성" onclick="location.href='../review/reviewWriteForm.do?x_code=${x_code}'"
				disabled="disabled">
				</div>
			</c:if>
			<c:if test="${count == 0}">
				<div class="result-display">
					등록된 후기가 없습니다.
				</div>
			</c:if>
			<c:if test="${count > 0}">
				<table>
					<c:forEach var="reviewVO" items="${reviewList}">
						<tr onclick="location.href='../review/reviewDetail.do?review_num=${reviewVO.review_num}'">
							<td><c:if test="${!empty reviewVO.filename}"><a href="../review/reviewDetail.do?review_num=${reviewVO.review_num}"><img src="${pageContext.request.contextPath}/upload/${reviewVO.filename}" class="detail-img" border="0" width="100" height="100"></a></c:if>
							<c:if test="${empty reviewVO.filename}"><a style="color:black;">사진 없음</a></c:if></td>
							<td colspan="2" width="200">${reviewVO.content}</td>
							<td>작성자 : ${reviewVO.id}<br>
								작성일 : ${reviewVO.reg_date}</td>
						</tr>
					</c:forEach>
				</table>
				<div class="align-center">
					${pagingHtml}
				</div>
			</c:if>
		</div>
</div>
</body>
</html>