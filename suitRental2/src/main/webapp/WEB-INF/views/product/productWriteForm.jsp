<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    
    //검색하고 나서 결과를 보여줄때 검색 조건을 그대로 노출
    //if("${gender}" == "female"){ //gender 가 female 일 경우 셋팅
    if("female" == "female"){
    
        //라디오 버튼 값으로 선택
        $('input:radio[name="gender"][value="${product.female}"]').prop('checked', true);
        //셀렉트 박스 값으로 선택
        $("select[name='female']").val("2").attr("selected", "selected");
        //남성 카테고리 hide
        $( "#viewMale" ).hide();
        //여성 카테고리 show
        $( "#viewFemale" ).show();
       /*  //여성 일때  비활성화
        $( "#mdName" ).prop( "disabled", true );
        // 입력값 초기화;
        $( "#mdName" ).val(""); */
    };
    
    //라디오 버튼 변경시 이벤트
    $("input[name='gender']:radio").change(function () {
            //라디오 버튼 값을 가져온다.
            var gender = this.value;
                            
            if(gender == "female"){//여성인 경우
                //여성일때 남성 카테고리 hide
                $( "#viewMale" ).hide();
                //여성일때 남성 카테고리 show
                $( "#viewFemale" ).show();
               /*  //여성일때  비활성화
                $( "#mdName" ).prop( "disabled", true );
                $( "#mdName" ).val(""); */
            }else if(gender == "male"){//남성인 경우
                //남성일때 남성 카테고리 show
                $( "#viewMale" ).show();
                //남성일때 여성 카테고리 hide
                $( "#viewFemale" ).hide();
               /*  //남성일때  활성화
                $( "#mdName" ).prop( "disabled", false ); */
            }
                            
        });
    
    $('#register_form').submit(function(){
		
		if($('#code').val().trim()==''){
			alert('상품코드를 입력하세요!');
			$('#code').focus();
			$('#code').val('');
			return false;
		}
		if($('#name').val().trim()==''){
			alert('상품명을 입력하세요!');
			$('#name').focus();
			$('#name').val('');
			return false;
		}
		if($('#brand').val().trim()==''){
			alert('브랜드를 입력하세요!');
			$('#brand').focus();
			$('#brand').val('');
			return false;
		}
		if($('#price').val()==''){
			alert('가격을 입력하세요!');
			$('#price').focus();
			$('#price').val('');
			return false;
		}
		if($('#contents').val().trim()==''){
			alert('상세정보를 입력하세요!');
			$('#contents').focus();
			$('#contents').val('');
			return false;
		}
		
	});
    
    
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h3>상품 등록 페이지</h3>
	<form id="productWrite_form" action="productWrite.do" method="post" enctype="multipart.form-data">
		<table border="0">
			<tr>
				<th>상품 코드</th>
				<td align="left">
					<input type="text" name="code" id="code" value="${product.code}" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="name" id="name" value="${product.name}" maxlength="20">
				</td>
			</tr>
			<tr>
				<th>브랜드</th>
				<td>
					<input type="text" name="brand" id="brand" value="${product.brand}" maxlength="20">
				</td>
			</tr>
			<tr>
				<th>상품 이미지</th>
				<td>
					<input type="file" name="image" id="image" value="${product.image}">
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="gender" id="radioMale" value="${product.male}" ><label for="radioMale">남성</label>
					<input type="radio" name="gender" id="radioFemale" value="${product.female}" ><label for="radioFemale">여성</label>
					<br>
        			<br>
        <span id="viewMale">
            남성 사이즈: 
            <select class="form-control male" name="size" style="width:200px">
                <option value="">100</option>
                <option value="1">105</option>
                <option value="2">110</option>
                <option value="3">115</option>
            </select>
        </span>
        <span id="viewFemale" style="display:none">
            여성 사이즈: 
            <select class="form-control female" name="size" style="width:200px">
                <option value="">44</option>
                <option value="1">55</option>
                <option value="2">66</option>
                <option value="3">77</option>
            </select>
        </span>
        	</td>
        	</tr>
			<tr>
				<th>가격</th>
				<td>
					<input type="text" name="price" id="price" value="${product.price}" maxlength="10">
				</td>
			</tr>
			<tr>
				<th>상세 설명</th>
				<td>
					<textarea name="contents" row="5" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="상품등록">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>