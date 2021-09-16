<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>

	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<ul>
			<li>
				<h5 style="text-align:center; margin-top:50px;"><b>QnA 답변</b></h5>
			</li>
			<li>글제목 : [답변] ${answerBoard.title}</li>
			<li>작성자 : 관리자</li>
		</ul>
		<hr size="1" noshade width="100%">
		<p>
			${answerBoard.a_content}
		</p>
		<hr>
		<p>
			[질문]
			<br>
			${answerBoard.q_content}
			<c:if test="${!empty answerBoard.filename}">
				<div class="align-center">
					<img src="${pageContext.request.contextPath}/upload/${answerBoard.filename}" class="detail-img">
				</div>
			</c:if>
		</p>
		<hr size="1" noshade width="100%">
		<div class="align-right">
			<input type="button" class="btn btn-dark" value="목록" onclick="location.href='listQna.do'">
		</div>
	</div>
	
	<div id="footer">
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>

</body>
</html>