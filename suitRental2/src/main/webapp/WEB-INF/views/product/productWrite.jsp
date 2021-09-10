<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="kr.product.dao.ProductDAO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	request.setCharacterEncoding("utf-8");
	String msg="", url="";
	
	String uploadPath=request.getRealPath("uploadFile");
	
	int maxSize=1024*1024*10;	//10MB
	String filename="";
	String originFile="";
	
	try{
		MultipartRequest multi=new MultipartRequest(request, uploadPath, maxSize,"utf-8",new DefaultFileRenamePolicy());
	
		ProductDAO pdao=ProductDAO.getInstance();
		int n=pdao.registerProduct(multi);
	
			if(n>0){
				msg="상품등록이 완료되었습니다.";
				url="productList.jsp";
			}else{
				msg="상품등록을 실패했습니다.";
				url="productWriteForm.jsp";
			}
	}catch(Exception e){
		msg="이미지 파일 업로드 실패";
		url="productWriteForm.jsp";
		e.printStackTrace();
	}


%>

<script>
alert("<%=msg%>");
location.href="<%=url%>";

</script>