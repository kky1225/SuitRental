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
    	if($('#gender').val() == "male"){
            $( "#viewFemale" ).hide();
            $( "#viewMale" ).show();
    	}
    
    	if($('#gender').val() == "female"){
            $( "#viewmale" ).hide();
            $( "#viewFemale" ).show();
    	}

    
  //라디오 버튼 변경시 이벤트
    $("input[name='gender']:radio").change(function () {
            //라디오 버튼 값을 가져온다.
            var gender = this.value;
             
            if(gender == "male"){//남성인 경우
                //남성일때 남성 카테고리 show
                $("#viewMale").show();
                $("#viewFemale").hide();
            }
            
            if(gender == "female"){//여성인 경우
                $("#viewMale").hide();
                $("#viewFemale").show();
            }
                            
        });
	
	if($('#filename').change(function(){
		$('#file_check1').text('');
	}));
    
    $('#productWrite_form').submit(function(){
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
		if($('#filename').val()==''){
			if($('#file_check2').val() == 'none'){
				alert('상품 이미지를 등록하세요!');
				return false;
			}
		}
		
		if($(':radio[name="gender"]:checked').length < 1){
			alert('성별을 입력하세요');
			return false;
		}
		
		if($(':radio[name="type"]:checked').length < 1){
			alert('종류를 입력하세요');
			return false;
		}
		
		if($('#price').val()==''){
			alert('가격을 입력하세요!');
			$('#price').focus();
			$('#price').val('');
			return false;
		}
		if($('#stoke').val()==''){
			alert('재고를 입력하세요!');
			$('#stoke').focus();
			$('#stoke').val('');
			return false;
		}
		
		if($('#content').val().trim()==''){
			alert('상세정보를 입력하세요!');
			$('#contents').focus();
			$('#contents').val('');
			return false;
		}
		
	});
    
    if($('#filename').change(function(){
		$('#filename_text').val($(this)[0].files[0].name);
	}));
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h3 style="text-align:center; margin-top:50px;"><b>상품수정</b></h3>
	<form id="productWrite_form" action="productModify.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="x_code" value="${productDetailVO.x_code}">
		<table border="0" id="pd-write">
			<tr>
				<th><label for="content" class="form-label mt-4">상품명</label></th>
				<td>
					<input type="text" class="form-control" name="name" id="name" maxlength="20" value="${productDetailVO.x_name}">
				</td>
			</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">브랜드</label></th>
				<td>
					<input type="text" class="form-control" name="brand" id="brand" maxlength="20" value="${productDetailVO.x_brand}">
				</td>
			</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">이미지</label></th>
				<td>
					<div class="row">
						<div class="col-auto" style="margin-left:-14px;">
							<input id="filename_text" class="form-control mt-4" value="파일선택" style="width:200px; margin-left:14px; margin-bottom:20px;" readonly>
						</div>
						<div class="col-auto" style="margin-top:26px;">
							<label for="filename" class="btn btn-dark" style="width:60px;">파일</label>
						</div>
						<input type="file" id="filename" name="filename" style="display:none;">
					</div>
					<c:if test="${!empty productDetailVO.x_file}">
						<p id="file_check1">${productDetailVO.x_file}</p>
						<input type="hidden" name="file_check2" id="file_check2" value="${productDetailVO.x_file}">
					</c:if>
				</td>
			</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">성별</label></th>
				<td>
					<c:if test="${productDetailVO.x_gender == 'male'}">
						<input type="radio" name="gender" id="male" value="male" checked style="margin-top:40px;">남자
						<input type="radio" name="gender" id="female" value="female">여자
					</c:if>
					<c:if test="${productDetailVO.x_gender == 'female'}">
						<input type="radio" name="gender" id="male" value="male" style="margin-top:40px;">남자
						<input type="radio" name="gender" id="female" value="female" checked>여자
					</c:if>
					<br>
        			<br>
        	</td>
        	<tr>
        		<th><label for="content" class="form-label mt-4">사이즈</label></th>
        		<td>
        		 <c:if test="${productDetailVO.x_gender == 'male' && productDetailVO.x_size == 100}">
        <span id="viewMale">
        	남성 사이즈:
        	<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100" selected>100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115">115</option>
            </select>
        </span>
        <span id="viewFemale" style="display:none">
            여성 사이즈: 
            <select class="form-control female" name="female_size" id="female_size" style="width:200px">
                <option value="44">44</option>
                <option value="55">55</option>
                <option value="66">66</option>
                <option value="77">77</option>
            </select>
        </span>
        </c:if>
		 <c:if test="${productDetailVO.x_gender == 'male' && productDetailVO.x_size == 105}">
		 <span id="viewMale">
        	남성 사이즈: 
            <select class="form-control male" name="male_size" id="male_size" style="width:200px">
                <option value="100">100</option>
                <option value="105" selected>105</option>
                <option value="110">110</option>
                <option value="115">115</option>
            </select>
          </span>
          <span id="viewFemale" style="display:none">
            여성 사이즈: 
            <select class="form-control female" name="female_size" id="female_size" style="width:200px">
                <option value="44">44</option>
                <option value="55">55</option>
                <option value="66">66</option>
                <option value="77">77</option>
            </select>
       	 </span>
		</c:if>
		<c:if test="${productDetailVO.x_gender == 'male' && productDetailVO.x_size == 110}">
		<span id="viewMale">
        	남성 사이즈: 
            <select class="form-control male" name="male_size" id="male_size" style="width:200px">
                <option value="100">100</option>
                <option value="105">105</option>
                <option value="110" selected>110</option>
                <option value="115">115</option>
            </select>
         </span>
         <span id="viewFemale" style="display:none">
            여성 사이즈: 
            <select class="form-control female" name="female_size" id="female_size" style="width:200px">
                <option value="44">44</option>
                <option value="55">55</option>
                <option value="66">66</option>
                <option value="77">77</option>
            </select>
        </span>
		</c:if>
		<c:if test="${productDetailVO.x_gender == 'male' && productDetailVO.x_size == 115}">
		 <span id="viewMale">
        	남성 사이즈: 
            <select class="form-control male" name="male_size" id="male_size" style="width:200px">
                <option value="100">100</option>
                <option value="105">105</option>
                <option value="110">110</option>
                <option value="115" selected>115</option>
            </select>
         </span>
		<span id="viewFemale" style="display:none">
            여성 사이즈: 
            <select class="form-control female" name="female_size" id="female_size" style="width:200px">
                <option value="44">44</option>
                <option value="55">55</option>
                <option value="66">66</option>
                <option value="77">77</option>
            </select>
        </span>
        </c:if>
		<c:if test="${productDetailVO.x_gender == 'male' && productDetailVO.x_size == 115}">
		 <span id="viewMale">
        		남성 사이즈: 
            	<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100">100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115" selected>115</option>
            	</select>
           </span>
           <span id="viewFemale" style="display:none">
           		 여성 사이즈: 
           		 <select class="form-control female" name="female_size" id="female_size" style="width:200px">
               		 <option value="44">44</option>
                	 <option value="55">55</option>
               		 <option value="66">66</option>
              		 <option value="77">77</option>
            	</select>
        	</span>
			</c:if>
        	<c:if test="${productDetailVO.x_gender == 'female' && productDetailVO.x_size == 44}">
        	<span id="viewMale" style="display:none">
           		 남성 사이즈: 
           		<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100">100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115">115</option>
            	</select>
        	</span>
        	<span id="viewFemale">
            	여성 사이즈: 
          		  <select class="form-control female" name="female_size" id="female_size" style="width:200px">
               		 <option value="44" selected>44</option>
              	 	 <option value="55">55</option>
              	 	 <option value="66">66</option>
             	 	 <option value="77">77</option>
           		 </select>
       		 </span>
			</c:if>
			<c:if test="${productDetailVO.x_gender == 'female' && productDetailVO.x_size == 55}">
			<span id="viewMale" style="display:none">
            	남성 사이즈: 
           		<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100">100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115">115</option>
            	</select>
        	</span>
			<span id="viewFemale">
           		 여성 사이즈: 
           		 <select class="form-control female" name="female_size" id="female_size" style="width:200px">
             	   <option value="44">44</option>
             	   <option value="55" selected>55</option>
             	   <option value="66">66</option>
             	   <option value="77">77</option>
            	</select>
       		 </span>
			</c:if>
			<c:if test="${productDetailVO.x_gender == 'female' && productDetailVO.x_size == 66}">
			<span id="viewMale" style="display:none">
           		 남성 사이즈: 
           		<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100">100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115">115</option>
            	</select>
        	</span>
			<span id="viewFemale">
            	여성 사이즈: 
            	<select class="form-control female" name="female_size" id="female_size" style="width:200px">
                	<option value="44">44</option>
                	<option value="55">55</option>
                	<option value="66" selected>66</option>
                	<option value="77">77</option>
            	</select>
       		 </span>
			</c:if>
			<c:if test="${productDetailVO.x_gender == 'female' && productDetailVO.x_size == 77}">
			<span id="viewMale" style="display:none">
            	남성 사이즈: 
           		<select class="form-control male" name="male_size" id="male_size" style="width:200px">
                	<option value="100">100</option>
                	<option value="105">105</option>
                	<option value="110">110</option>
                	<option value="115">115</option>
            	</select>
        	</span>
			<span id="viewFemale">
            	여성 사이즈: 
            	<select class="form-control female" name="female_size" id="female_size" style="width:200px">
              	 	<option value="44">44</option>
              	 	<option value="55">55</option>
               		<option value="66">66</option>
               	 	<option value="77" selected>77</option>
            	</select>
       		 </span>
			</c:if>
			</td>
        	</tr>
        	<tr>
				<th><label for="content" class="form-label mt-4">종류</label></th>
				<td>
					<c:if test="${productDetailVO.x_type == 'jacket'}">
					<input type="radio" name="type" id="jacket" value="jacket" checked>재킷
					<input type="radio" name="type" id="shirts" value="shirts">셔츠
					<input type="radio" name="type" id="slacks" value="slacks">슬랙스
					<input type="radio" name="type" id="shoes" value="shoes">구두
					</c:if>
					<c:if test="${productDetailVO.x_type == 'shirts'}">
					<input type="radio" name="type" id="jacket" value="jacket">재킷
					<input type="radio" name="type" id="shirts" value="shirts" checked>셔츠
					<input type="radio" name="type" id="slacks" value="slacks">슬랙스
					<input type="radio" name="type" id="shoes" value="shoes">구두
					</c:if>
					<c:if test="${productDetailVO.x_type == 'slacks'}">
					<input type="radio" name="type" id="jacket" value="jacket">재킷
					<input type="radio" name="type" id="shirts" value="shirts">셔츠
					<input type="radio" name="type" id="slacks" value="slacks" checked>슬랙스
					<input type="radio" name="type" id="shoes" value="shoes">구두
					</c:if>
					<c:if test="${productDetailVO.x_type == 'shoes'}">
					<input type="radio" name="type" id="jacket" value="jacket">재킷
					<input type="radio" name="type" id="shirts" value="shirts">셔츠
					<input type="radio" name="type" id="slacks" value="slacks">슬랙스
					<input type="radio" name="type" id="shoes" value="shoes" checked>구두
					</c:if>
        		</td>
        	</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">가격</label></th>
				<td>
					<input type="number" class="form-control" name="price" id="price" maxlength="10" value="${productDetailVO.x_price}">
				</td>
			</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">재고</label></th>
				<td>
					<input type="number" class="form-control" name="stock" id="stock" maxlength="10" value="${productDetailVO.x_stock}">
				</td>
			</tr>
			<tr>
				<th><label for="content" class="form-label mt-4">상세 설명</label></th>
				<td>
					<textarea name="content" class="form-control" rows="5" cols="50" id="content">${productDetailVO.x_contents}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" class="btn btn-dark" value="상품등록" style="width:120px; margin-left:20px; margin-top:30px;">
					<input type="button" class="btn btn-dark" value="목록" onclick="location.href='productList.do'" style="width:120px; margin-left:8px; margin-top:30px;">
				</td>
			</tr>
		</table>
	</form>
</div>

<!-- 푸터 -->
<div id="footer">
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>

</body>
</html>