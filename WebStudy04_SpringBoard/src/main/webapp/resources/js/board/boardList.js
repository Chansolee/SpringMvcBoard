/**
 * 
 */
 	let updateForm = $("#updateForm");
 	let updateBtn = $("#updateBtn").on("click", function(event){
 		let what = viewModal.data("what");
 		if(!what) return false;
 		updateForm.get(0).what.value = what;
	 	updateForm.submit();
 	});
 	let deleteForm = $("#deleteForm");
 	let deleteBtn = $("#deleteBtn").on("click", function(event){
 		let what = viewModal.data("what");
 		if(!what) return false;
 		if(confirm("진짜 삭제할까?")){
	 		deleteForm.get(0).what.value = what;
	 		deleteForm.submit();
 		}
 	});
	let viewModal = $("#exampleModal").on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
		viewForm.get(0).reset();
		viewModal.data("what", "");
	}).on("show.bs.modal", function(event){
		let dataTr = event.relatedTarget;
		let what = $(dataTr).data('what');
		viewModal .data("what", what);
		viewForm.find('[name=what]').val(what);
		viewForm.submit();
	});
	let viewForm = $("#viewForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize(); // ajaxForm 적용
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				let board = resp;
			let table =	
				`<table border="1" class="table table-bordered table-striped">
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
					<td>${board.boRec }</td>
				</tr>
				<tr>
					<th>부모글</th>
					<td>${board.boParent }</td>
				</tr>`
				
			table +=
				
					`<tr>
						<th>첨부파일</th>`
			 
						$.each(board.attatchList, function (index, attatch) {
						table +=	`<td>${attatch.attFilename }</td>`
				 
				
						})
			table +=`</tr>	
						</table>`;
				viewModal.find(".modal-body").html(table);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
				viewModal.find(".modal-body").html(errorResp.responseText);
			}
		});
		return false;
	});