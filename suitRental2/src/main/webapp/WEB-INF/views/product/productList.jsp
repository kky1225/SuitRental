<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

</head>
<body>
<div class="page-main">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<h3> 상품 리스트 </h3>
<div class="list-space align-right">
			<input type="button" value="상품등록" onclick="location.href='productWriteForm.do'"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
			<!-- <input type="button" value="목록" onclick="location.href='ProductList.do'"> -->
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">		
		</div>
<table width=80% border = "0" cellspacing="0">
	<c:set var = "list" value ="${List }" />
	<c:if test="${!empty list }">
		<c:forEach items="${list }" var="board">
	<tr>
		<td><a href="productDetail.do?x_code">${board.getX_file() }</a></td>
		<td><a href="productDetail.do?">${board.getX_name() }</a></td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty list}">
	<tr>
		<td>
			<h3>등록된 게시물이 없습니다.</h3>
		</td>
	</tr>
	</c:if>
	<div class="align-center">
		</div>
 <c:if test="${page > block }">
         <a href="productList_list.do?page=1">[맨처음]</a>
         <a href="productList_list.do?page=${startBlock - 1 }">◀</a>
      </c:if>
      <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
         <c:if test="${i == page }">
            <b><a href="productList_list.do?page=${i }">[${i }]</a></b>
         </c:if>
         
         <c:if test="${i != page }">
            <a href="productList_list.do?page=${i }">[${i }]</a>
         </c:if>
      </c:forEach>
      
      <c:if test="${endBlock < allPage }">
         <a href="productList_list.do?page=${endBlock + 1 }">▶</a>
         <a href="productList_list.do?page=${allPage }">[마지막]</a>
      </c:if> 
      <div></div>
      <br> <br>     
</table>
			
</div>
</body>
</html>