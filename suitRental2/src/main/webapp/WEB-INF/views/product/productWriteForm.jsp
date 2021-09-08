<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <!--  주석 보이시나요? -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 등록</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>제품 등록 페이지</h2>
	<form id="productwrite_form" action="productwrite.do" method="post">
		<table border=0>
			<tr>
				<th>상품 코드</th>
				<td align="left">
					<input type="text" name="code" id="code" value="${product.code}" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>제품명</th>
				<td>
					<input type="text" name="name" id="name" value="${product.name}" maxlength="20">
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input type="text" name="price" id="price" value="${product.price}" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
					<input type="text" name="stock" id="stock" value="${product.stock}" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>사이즈</th>
				<td>
					<input type="text" name="price" id="price" value="${product.price}" maxlength="8">
				</td>
			</tr>
		
		</table>
	</form>
</div>
</body>
</html>