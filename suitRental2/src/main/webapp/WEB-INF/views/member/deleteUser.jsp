<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check == true}">
	<script>
		alert('회원탈퇴가 완료되었습니다.');
		location.href='${pageContext.request.contextPath}/main/main.do'
	</script>
</c:if>

<c:if test="${check == false}">
	<script>
		alert('ID 또는 비밀번호 불일치!');
		history.go(-1);
	</script>
</c:if>