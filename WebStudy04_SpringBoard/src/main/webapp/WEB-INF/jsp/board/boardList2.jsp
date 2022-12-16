<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>추천수</th>

			</tr>
		</thead>
		<tbody id="listBody">
		</tbody>

		<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagingArea">
				</div>
				
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" method="post">
	<input type="text" name="page" />
	<input type="text" name="searchType" value="${simpleCondition.searchType}"/>
	<input type="text" name="searchWord" value="${simpleCondition.searchWord}"/>
</form>
 <form id='viewForm' action="${pageContext.request.contextPath }/board/boardView.do">
	<input type='hidden' name='what'  />
</form>
<form id='updateForm' action="${pageContext.request.contextPath }/board/boardUpdate.do">
	<input type='hidden' name='what'  />
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="updateBtn">UPDATE</button>
        <button type="button" class="btn btn-danger" id="deleteBtn">DELETE</button>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src='${pageContext.request.contextPath }/resources/js/board/boardList.js?<%=System.currentTimeMillis()%>'></script>
<script>
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(event){
		let inputTags = searchUI.find(":input[name]");
		$.each(inputTags, function(index, inputTag){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.get(0)[name].value = value;
		});
		searchForm.submit();
	});

	let pageTag = $("[name=page]");
	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	let pagingArea = $(".pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	let listBody = $("#listBody");
	
	let searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		pagingArea.empty();
		listBody.empty();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json", // Accept vs Content-Type
			success : function(resp) {
				
				
				console.log(resp);
				let boardList = resp.dataList;
				
				
				let trTags = [];
				if(boardList.length > 0){
					$.each(boardList, function(index, board){
				let viewURL = "${pageContext.request.contextPath}/board/boardView.do?what="+board.boNo;
						let trTag = $("<tr>").attr("data-what",board.boNo)
									.attr("data-bs-toggle","modal")
									.attr("data-bs-target","#exampleModal")
									.append(
								 $("<td>").html(
									$("<a>").attr("href",viewURL)
											.text(board.boNo)
								)
								, $("<td>").html(board.boTitle)
								, $("<td>").html(board.boWriter)
								, $("<td>").html(board.boDate)
								, $("<td>").html(board.boHit)
								, $("<td>").html(board.boRec)
								);
					trTags.push(trTag);
					});
				}else{
					let trTag = $("<tr>").html(
									$("<td>").attr("colspan", "8")
											.text("게시글이없음.")
								);
					trTags.push(trTag);
				}
				console.log(trTags);
				pagingArea.html(resp.pagingHTML);
				listBody.append(trTags);
				
				pageTag.val("");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		return false;
	}).trigger("submit");
</script>
</html>