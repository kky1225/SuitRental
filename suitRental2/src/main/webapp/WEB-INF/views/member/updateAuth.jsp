<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check == true}">
	<script type="text/javascript">
		alert('회원 권한 수정 완료!');
		location.href = 'managerPage.do';
	</script>
</c:if>

<c:if test="${check == false}">
	<script type="text/javascript">
		alert('정보를 다시 입력하세요!');
		history.go(-1);
	</script>
</c:if>