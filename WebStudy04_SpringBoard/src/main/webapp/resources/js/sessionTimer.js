/**
 * 
 */
window.addEventListener("DOMContentLoaded",function(event){
	$.fn.sessionTimer=function(timeout, msgModal){
		if(!timeout || typeof timeout != 'number' || timeout <= 0)
			throw Error("타이머 처리 불가");
		if(!msgModal || msgModal===undefined){
			let text = `
						<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        세션 만료 시간이 1분 남았음. 연장 할래?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						        <button type="button" class="btn btn-primary ctrlBtn">YES</button>
						        <button type="button" class="btn btn-warning ctrlBtn">NO</button>
						      </div>
						    </div>
						  </div>
						</div> `;
			
			
			msgModal = "#exampleModal";			
			$('body').append(text);
		}
		
		const SPEED = 100;
		
		let timer = timeout;
		let timerJob = null;
		let msgJob = null;
		let timerArea = this;
		let msgArea = $(msgModal).on("click", ".ctrlBtn", function(){
			let command = $(this).text();
			if('YES' == command){
				init();
				$.ajax({
					method : "head"
				});
			}
			msgArea.modal('hide');
		});
		
		let init = function(){
			timer = timeout;
			if(timerJob==null)
				timerJob = setInterval(function(){
					timerArea.html(moment(--timer*1000).format('mm:ss'));
					if(timer <= 0)
						clearInterval(timerJob);
				}, 1 * SPEED);
			if(msgJob!=null)
				clearTimeout(msgJob);
			msgJob = setTimeout(function(){
				msgArea.modal('show');
			}, (timeout - 60) * SPEED);
		}
		
		init();
	}
});