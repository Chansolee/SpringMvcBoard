<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Welcome page</h4>
<h4> model : ${now }</h4>
<div id="weather"></div>
</body>
</html>

<script type="text/javascript">
let city = "Seoul";
let apiURI = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+"b420b092545934402f72342f733541ef";
$.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    async: "false",
    success: function(resp) {
    	console.log(resp);
    	console.log("현재온도 : "+ (resp.main.temp- 273.15) );
    	console.log("현재습도 : "+ resp.main.humidity);
    	console.log("날씨 : "+ resp.weather[0].main );
    	console.log("상세날씨설명 : "+ resp.weather[0].description );
    	console.log("날씨 이미지 : "+ resp.weather[0].icon );
    	console.log("바람   : "+ resp.wind.speed );
    	console.log("나라   : "+ resp.sys.country );
    	console.log("도시이름  : "+ resp.name );
    	console.log("구름  : "+ (resp.clouds.all) +"%" ); 		   
    	
     	let temp = resp.main.temp- 273.15;
    	let humidity = resp.main.humidity;
    	let main = resp.weather[0].main ;
		let description = resp.weather[0].description;
	    let icon = resp.weather[0].icon;
	    let wind = resp.wind.speed ;
	    let country = resp.sys.country ;
	    let name = resp.name;
	    let all = resp.clouds.all ; 		
    	
    	
	
// 	let table =	
// 		`<table border="1" class="table table-bordered table-striped">
// 		<tr>
// 			<th>현재온도</th>
// 			<td>${resp.main['temp- 273.15'] }</td>
// 		</tr>
// 		<tr>
// 			<th>현재습도</th>
// 			<td>${resp.main.humidity }</td>
// 		</tr>
// 		<tr>
// 			<th>날씨</th>
// 			<td>${resp.weather[0].main }</td>
// 		</tr>
// 		<tr>
// 			<th>상세날씨설명</th>
// 			<td>${resp.weather[0].description }</td>
// 		</tr>
// 		<tr>
// 			<th>날씨 이미지</th>
// 			<td>${resp.weather[0].icon }</td>
// 		</tr>
// 		<tr>
// 			<th>바람</th>
// 			<td>${resp.wind.speed }</td>
// 		</tr>
// 		<tr>
// 			<th>나라</th>
// 			<td>${resp.sys.country  }</td>
// 		</tr>
// 		<tr>
// 			<th>도시이름</th>
// 			<td>${wea.name }</td>
// 		</tr>
// 		<tr>
// 			<th>구름</th>
// 			<td>${resp.clouds.all }+"%"</td>
// 		</tr>
// 		</table>`;
		
	
// 		$("#weather").html(table2);
    }
})

	
</script>