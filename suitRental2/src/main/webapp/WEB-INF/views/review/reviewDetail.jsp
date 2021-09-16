<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	</head>
	<body>
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			<h2>게시글 보기</h2>
			<ul>
				<li>글번호 : ${reviewVO.review_num}</li>
				<li>글제목 : ${reviewVO.title}</li>
				<li>작성자 : ${reviewVO.id}</li>
				<li>조회수 : ${reviewVO.hit}</li>
			</ul>
			<hr size="1" noshade width="100%">
			<c:if test="${!empty reviewVO.filename}">
				<div class="align-center">
					<img src="${pageContext.request.contextPath}/upload/${reviewVO.filename}" class="detail-img">
				</div>
			</c:if>
			<p>
				${reviewVO.content}
			</p>
			<hr size="1" noshade width="100%">
			<div class="align-right">
				작성일 : ${reviewVO.reg_date}
				<c:if test="${!empty reviewVO.modify_date}">최근 수정일 : ${reviewVO.modify_date}</c:if>
				<c:if test="${user_num == reviewVO.mem_num}">
					<input type="button" class="btn btn-dark" value="수정" onclick="location.href='reviewModifyForm.do?review_num=${reviewVO.review_num}'">
					<input type="button" class="btn btn-dark" value="삭제" id="delete_btn">
					<script type="text/javascript">
						var delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick = function(){
							var choice = confirm('정말 삭제하시겠습니까?');
							if(choice){
								location.replace('reviewDelete.do?review_num=${reviewVO.review_num}');
							}
						};
					</script>
				</c:if>
				<input type="button" class="btn btn-dark" value="목록" onclick="location.href='reviewList.do'">
			</div>
			<div id="loading" style="display:none;">
				<img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
			</div>
		</div>
	</body>
</html>