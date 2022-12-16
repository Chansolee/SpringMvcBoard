<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.boNo }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boTitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boWriter }</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.boIp }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.boMail }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${board.boPass }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boContent }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boDate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boHit }</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>
			<span id="recArea">${board.boRec }</span>
			<span class="btn btn-success" id="recBtn">추천</span>
			</td>
			
		</tr>
		<tr>
			<th>부모글</th>
			<td>${board.boParent }</td>
		</tr>
		
 		
				<tr>
					<th>첨부파일</th>
					<td>
		<c:forEach items="${board['attatchList']}" var="attatch"> 
			<c:url value="/board/download.do" var="downloadURL">
				<c:param name="what" value="${attatch.attNo }"/>
			</c:url>
					<a href="${downloadURL }">${attatch['attFilename'] }</a>
			</c:forEach>
			</td>
				</tr>	
	
		<tr>
			<td colspan="2">
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.boNo }" />
				</c:url>
				<a href="${updateURL }" class="btn btn-primary">글 수정</a>
				<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">글 삭제</a>


			</td>
		</tr>
	</table>
	
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">비밀번호 삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <input id="boardpass" type="password" />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-danger" id="deleteBtn">확인</button>
      </div>
    </div>
  </div>
</div>	

<!-- TestDrivenDevelopment vs EventDrivenDevelopment -->

<form id="deleteForm" method="post" action="${cPath }/board/boardDelete.do">
	
	<input type="hidden" name="boNo" value="${board.boNo }"/>
	<input id ="boPass" type="hidden" name="boPass" />
</form>
<script>
	let deleteForm = $("#deleteForm");
 	let deleteBtn = $("#deleteBtn").on("click", function(event){
 		if(confirm("진짜 삭제하시겠습니까?")){
 			let boPass = $("#boardpass").val();
 			 $("#boPass").val(boPass);
	 		deleteForm.submit();
 		}
 	});
 	
 	
 	
   $("#recBtn").on("click", function(){
 		 alert("추천하셨습니다.");
 		
 		
 		 $.ajax({
			url : "${cPath }/board/boardRecommend.do",
			data :{
				what:${board.boNo }
			},
			dataType : "json",
			success : function(res) {
				console.log(res);
				$("#recArea").html(res);

			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
 		
 	});
</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	