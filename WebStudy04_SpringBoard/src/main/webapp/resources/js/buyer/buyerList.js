/**
 * 
 */
let updateForm = $("#updateForm");
let updateBtn = $("#updateBtn").on("click", function(event) {
	let what = viewModal.data("what");
	if (!what) return false;
	updateForm.get(0).what.value = what;
	updateForm.submit();
});
let deleteForm = $("#deleteForm");
let deleteBtn = $("#deleteBtn").on("click", function(event) {
	let what = viewModal.data("what");
	if (!what) return false;
	if (confirm("진짜 삭제할까?")) {
		deleteForm.get(0).what.value = what;
		deleteForm.submit();
	}
});
let viewModal = $("#exampleModal").on("hidden.bs.modal", function(event) {
	$(this).find(".modal-body").empty();
	viewForm.get(0).reset();
	viewModal.data("what", "");
}).on("show.bs.modal", function(event) {
	let dataTr = event.relatedTarget;
	let what = $(dataTr).data('what');
	console.log(what);
	viewModal.data("what", what);
	viewForm.find('[name=what]').val(what);
	viewForm.submit();
});
let viewForm = $("#viewForm").on("submit", function(event) {
	event.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = $(this).serialize(); // ajaxForm 적용
	$.ajax({
		url: url,
		method: method,
		data: data,
		dataType: "html",
		success: function(resp) {
			viewModal.find(".modal-body").html(resp);
		},
		error: function(errorResp) {
			console.log(errorResp.status);
			viewModal.find(".modal-body").html(errorResp.responseText);
		}
	});
	return false;
});