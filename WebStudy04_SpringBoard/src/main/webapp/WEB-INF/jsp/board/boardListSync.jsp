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
			<c:choose>
				<c:when test="${not empty pagingVO.dataList }">
					<c:forEach items="${pagingVO.dataList }" var="board">
						<c:url value="/boardSync/boardView.do" var="viewURL">
							<c:param name="what" value="${board.boNo}" />
						</c:url>
						<tr>
							<td><a href="${viewURL }">${board.boNo}</a></td>
							<td>${board.boTitle}</td>
							<td>${board.boWriter}</td>
							<td>${board.boDate}</td>
							<td>${board.boHit}</td>
							<td>${board.boRec}</td>

						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">게시글이 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>

		<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagingArea">
					${pagingVO.pagingHTML }
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
</body>
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
	

</script>
</html>