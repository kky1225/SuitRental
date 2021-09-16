<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기부 상세 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var currentPage;
		var count;
		var rowCount;
		//댓글 목록
		function selectData(pageNum){
			currentPage = pageNum;
			if(pageNum==1){
				//처음 호출 시에는 해당 id의 div의 내부 내용물을 제거
				$('#output').empty();
			}
			
			//로딩 이미지 노출
			$('#loading').show();
			
			$.ajax({
				type:'post',
				data:{pageNum:pageNum, donation_num:$('#donation_num').val()},
				url:'donationReplyList.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(param){
					$('#loading').hide();			//로딩이미지 감추기
					count = param.count;
					rowCount=param.rowCount;
					
					$(param.list).each(function(index,item){
						var output = '<div class="item">';
						output+='<h5>' + item.id + '</h5>';
						output+='<div class="sub-item">';
						output+='<p>'+item.re_content+'</p>';
						output+='<span style="font-size:14px; color:gray;">'+item.re_date+'</span>';
						//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
						if($('#user_num').val()==item.mem_num){
							output+=' <input type="button" value="수정" data-renum="'+item.re_num+'" data-memnum="'+item.mem_num+'" class="modify-btn btn-dark">';
							output+=' <input type="button" value="삭제" data-renum="'+item.re_num+'" data-memnum="'+item.mem_num+'" class="delete-btn btn-dark">';
						}
						output+='<hr side="1" noshade width="100%">';
					    output+='</div>';
						output+='</div>';
						
						$('#output').append(output);
					});
					//page button처리
					if(currentPage>=Math.ceil(count/rowCount)){
						$('.paging-button').hide();
					}else{
						$('.paging-button').show();
					}
					
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			})
			
		}
		//페이지처리 이벤트 연결
		$('.paging-button input').click(function(){
			selectData(currentPage+1);
		});
		
		//댓글 등록
		$('#reply_form').submit(function(event){
			if($('#re_content').val().trim()==''){
				alert('댓글을 입력하세요.');
				$('#re_content').val('').focus();
				return false;
			}
			var form_data = $(this).serialize();
			$.ajax({
				type:'post',
				data:form_data,
				url:'writeDonationReply.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(param){
					if(param.result=='logout'){
						alert('로그인 해야 작성할 수 있습니다.');	
					}else if(param.result=='success'){
						initForm();
						selectData(1);
					}else{
						alert('등록시 오류발생');
					}
				},
				error:function(){
					alert('네트워크 오류');
				} 
			})
			
			//기본 이벤트 제거
			event.preventDefault();
			
		});
		
		//댓글 작성 폼 초기화
		function initForm(){
			$('textarea').val('');
			$('#re_first .letter-count').text('300/300');
		}
		//textarea에 내용 입력시 글자수 체크
		$(document).on('keyup','textarea',function(){
			//입력 글자수
			var inputlength=$(this).val().length;
			if(inputlength > 300){
				$(this).val($(this).val().substring(0,300));
			}else{
				var remain = 300-inputlength;
				remain+='/300';
				if($(this).attr('id')=='re_content'){
					$('#re_first .letter-count').text(remain);				//등록폼 글자수
				}else{
					$('#mre_first .letter-count').text(remain);				//수정폼 글자수
				}
			}
		});
		//댓글 수정 버튼 클릭시 수정폼 노출
		$(document).on('click','.modify-btn',function(){
			//댓글번호
			var re_num=$(this).attr('data-renum');
			//작성자 번호
			var num=$(this).attr('data-memnum');
			//댓글 내용
			var content=$(this).parent().find('p').html().replace(/<br>/gi,'\n');
			//수정 폼
			var modifyUi ='<form id="mre_form">';
				modifyUi+=' <input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';
				modifyUi+=' <input type="hidden" name="mem_num" id="muser_num" value="'+num+'">';
				modifyUi+=' <textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content form-control">'+content+'</textarea>';
				modifyUi+='	<div id="mre_first"><span class="letter-count">300/300</span></div>';
				modifyUi+='	<div id="mre_second" class="align-right" style="margin-bottom:60px;margin-right:30px;">';
				modifyUi+='		<input type="submit" value="수정" class="btn btn-dark">';
				modifyUi+='		<input type="button" value="취소" class="re-reset btn btn-dark">';
				modifyUi+='	</div>';
				modifyUi+='	<hr size="1" noshade width="96%">';
				modifyUi+='</form>';
			
			//이전 수정하던 수정폼 초기화
			initModifyForm();
			//해당 댓글 데이터 감추기
			$(this).parent().hide();
			//해당 댓글의 수정폼 나타내기
			$(this).parents('.item').append(modifyUi);
			
			//입력한 글자수 셋팅
			var inputlength = $('#mre_content').val().length;
			var remain = 300-inputlength;
			remain+='/300';
			//문서객체에 반영
			$('#mre_first .letter-count').text(remain);
		});
		
		//수정폼에서 취소 버튼 클릭시 수정폼 초기화
			$(document).on('click','.re-reset',function(){
			initModifyForm();
			});
		//댓글 수정 폼 초기화
		function initModifyForm(){
			$('.sub-item').show();
			$('#mre_form').remove();
		}
		//댓글 수정
		$(document).on('submit','#mre_form',function(event){
			if($('#mre_content').val().trim()==''){
				alert('내용을 입력하세요.');
				$('#mre_content').val('').focus();
				return false;
			}
			//폼에 입력한 데이터 반환
			var form_data = $(this).serialize();
			//수정
			$.ajax({
				url:'modifyDonationReply.do',
				type:'post',
				data:form_data,
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(param){
					if(param.result=='logout'){
						alert('로그인해야 수정할 수 있습니다.');
					}else if(param.result=='success'){
						$('#mre_form').parent().find('p').html($('#mre_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\n/g,'<br>'));
						//수정폼 삭제 및 초기화
						initModifyForm();
					}else if(param.result=='wrongAccess'){
						alert('타인의 글을 수정할 수 없습니다.');
					}else{
						alert('수정 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
			//기본 이벤트 제거
			event.preventDefault();
			
		});
		
		//댓글 삭제
		$(document).on('click','.delete-btn',function(){
			//댓글번호
			var re_num = $(this).attr('data-renum');
			//댓글 작성자 번호
			var mem_num = $(this).attr('data-memnum');
			
			$.ajax({
				type:'post',
				data:{re_num:re_num, mem_num:mem_num},
				url:'deleteDonationReply.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(param){
					if(param.result=='logout'){
						alert('로그인해야 삭제할 수 있습니다.');
					}else if(param.result=='success'){
						alert('삭제완료');
						selectData(1);
					}else if(param.result=='wrongAccess'){
						alert('타인의 글을 삭제할 수 없습니다.');
					}else{
						alert('삭제 중 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			})
			
		})
		//초기 데이터(목록) 호출
		selectData(1);
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h5 style="text-align:center; margin-top:50px;"><b>기부</b></h5>
	
	<ul>
		<li>글번호 : ${donation.donation_num } </li>
		<li>제목 : ${donation.title } </li>
		<li>작성자 : ${donation.mem_id } </li>
		<li>조회수 : ${donation.hit } </li>
	</ul>
	<hr size="1" noshade width="100%">
	<c:if test="${!empty donation.filename}">
		<div>
			<img src="${pageContext.request.contextPath }/upload/${donation.filename}" class="detail-img">
		</div>
	</c:if>
	<p>
		${donation.content }
	</p>
	<hr size="1" noshade width="100%">
	<div class="align-right">
	작성일 : ${donation.reg_date }
	수정일 : ${donation.modify_date}
	
	<%--로그인한 회원번호와 작성자의 회원번호가 일치 시 수정, 삭제 가능 --%>
	<c:if test="${user_num == donation.mem_num }">
		<input type="button" value="수정" class="btn btn-dark" onclick="location.href='modifyDonationForm.do?donation_num=${donation.donation_num}'">
		<input type="button" value="삭제" id="delete_btn" class="btn btn-dark">
			<script type="text/javascript">
				var delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick=function(){
					var choice = confirm('삭제하시겠습니까?');
					if(choice){
						location.replace('deleteDonation.do?donation_num=${donation.donation_num}');
					}
				};
			</script>
	</c:if>
		<input type="button" value="목록" class="btn btn-dark" onclick="location.href='list.do'">
	</div>
	
	<!-- 댓글 시작 -->
	<div id="reply_div">
		<span class="re-title" >댓글</span>
		<form id="reply_form">
			<input type="hidden" id="donation_num" name="donation_num" value="${donation.donation_num }">
			<input type="hidden" id="user_num" name="user_num" value="${user_num }">
			<textarea rows="3" cols="50" id="re_content" name="re_content" class="rep-content form-control" 
			<c:if test="${empty user_num }">disabled="disabled"</c:if>><c:if test="${empty user_num }">로그인 해야 작성할 수 있습니다.</c:if></textarea>
	
		<c:if test="${!empty user_num}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="전송" class="btn btn-dark" style="width:100px;">
			</div>
		</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 시작 -->
	<div id="output"></div>
	<div class="paging-button align-right" style="display:none;">
		<input type="button" value="댓글 더보기" class="btn btn-dark" >
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
	</div>
	<!-- 댓글 목록 출력 끝 -->
	<!-- 댓글 끝 -->
</div>

	<!-- 푸터 -->
	<div id="footer">
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>

</body>
</html>