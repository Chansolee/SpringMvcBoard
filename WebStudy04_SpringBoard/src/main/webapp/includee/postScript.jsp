<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
<script src="${cPath }/resources/js/bootstrap-notify.min.js"></script>
<script type="text/javascript">
	let url = "ws://localhost${cPath}/ws/pushMsg"
 	let webSocket = new WebSocket(url);
	
	webSocket.onopen=function(event){
		console.log("연결수립=============");		
		console.log(event); //발생한 이벤트의 레퍼런스		
	}
	
	webSocket.onclose=function(event){ 
		console.log("=============연결종료");		
		console.log(event); 		
	}
	
	webSocket.onmessage=function(event){ 
		console.log("메시지 수신"+event.data);
		//넘어온 데이터 언먀살링하기
		let newBoard = JSON.parse(event.data);
		let boNo = newBoard.boNo;
		let boWriter = newBoard.boWriter;
// 		<a href="">누구님의 새글</a>
		let notiMsg = $("<a>").attr("href","${cPath}/board/boardView.do?what="+boNo)
							  .text(boWriter+"님의 새글");
		
		$.notify({
			// options
			title: "새글 알림"
			,message:notiMsg.html()
		},{
			// settings
			type: "info"
		   ,placement:{
			   from:"bottom"
			  ,align:"center"
		   }
		});
	}
	webSocket.onerror=function(event){ 
		console.log("에러발생");		
		console.log(event); 			
	}
</script>