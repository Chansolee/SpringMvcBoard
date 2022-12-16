<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 이항 연산자로 4칙 연산 처리. -->
Send Data Type
<input type="radio" name="sendDataType" value="json" checked/>JSON
<input type="radio" name="sendDataType" value="parameter"/>Parameter
<hr />
Receive Data Type
<input type="radio" name="receiveDataType" value="html"/>HTML
<input type="radio" name="receiveDataType" value="json"  checked/>JSON
<form action="<%=request.getContextPath()%>/calculate" name="calForm" method="post">
	<input type="number" name="leftOp" />
	<select name="operator">
		<%
			for(OperatorType single :OperatorType.values()){
				%>
					<option value="<%=single.name()%>"><%=single.getSign() %></option>
				<%
			}
		%>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="=">
</form>
<div id="resultArea">

</div>
<script type="text/javascript">
	let resultArea = $("#resultArea");	
	let makeSendData = function(settings){
		let inputs = calForm.find(":input[name]");
		let data = {};
		$.each(inputs, function(index, input){
			let name = this.name;
			let value = $(this).val();
			let type = this.type;
// 			data.leftObj = 23;
// 			data['leftObj']=23;
			if(type=='number'){
				data[name] = parseInt(value);
			}else{
				data[name] = value;
			}
		});
		console.log(data);
		let sendDataType = $("[name=sendDataType]:checked").val();
		if(sendDataType == 'json'){
			settings.data = JSON.stringify(data);
			settings.contentType = "application/json;charset=UTF-8";
		}else{
			settings.data = data;
		}
	}
	
	let successFunctions = {
	 	json : function(resp){
	 		resultArea.html("JSON response : "+JSON.stringify(resp));
	 	}
		, html : function(resp){
			resultArea.html("HTML response : "+resp)
		}
	}
	
	let makeReceiveDataType = function(settings){
		let receiveDataType = $("[name=receiveDataType]:checked").val();
		let dataType = receiveDataType;
		settings.dataType = dataType;
		settings.success=successFunctions[dataType]
			
		
	}

	let calForm = $(document.calForm).on("submit", function(event){
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
		
		let settings = {
				url : url,
				method : method,
				success : function(resp) {
					console.log(resp);
					calForm.after(resp.expression);
					
				},
				error : function(errorResp) {
					alert(errorResp.status);
				}
			};
		
		makeSendData(settings);
		
		makeReceiveDataType(settings);
		
		$.ajax(settings);
		return false;
	});
</script>


















