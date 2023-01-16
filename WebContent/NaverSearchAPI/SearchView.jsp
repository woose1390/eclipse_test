<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
// [검색 요청] 버튼 클릭하면 실행할 메서드를 정의합니다.
$(function(){
			$('#searchBtn').click(function(){
		$.ajax({
			// URL은 서블릿의 요청명을 사용하고
			url : "../NaverSearchAPI.do", // 요청 URL
			// get 방식으로 전송합니다.
			type : "get", // HTTP 메서드
			// 매개변수로는 검색어와 검색색 시작 위치를 전달하고
			data : {
				keyword : $('#keyword').val(),// 검색어
				startNum : $('#startNum option:selected').val() // 검색 시작 위치
			},
			// 콜백 데이터 형식은 JSON으로 지정합니다.
			dataType : "json", // 응답 데이터 형식
			// 마지막으로 요청 성공 시 혹은 실패 시 호출할 콜백 메서드를 설정합니다.
			success : sucFuncJson, // 요청 성공 시 호출할 메서드 설정
			error : errFunc // 요청 실패 시 호출할 메서드 설정
		});
	});
});
// 검색 성공 시 결과를 화면에 표시해 줍니다.
function sucFuncJson(d) { // 성공 시의 콜백 메서드 sucFuncJson() 입니다.}
 	console.log(typeof(d)); // d는 object입니다.
 	var str = "";
 // $.each 메서드를 사용하여 콜백 데이터 중 items 부분을 반복 파싱합니다.
    // 콜백 데이터에는 앞서 확인한 검색 결과가 담겨 있으며,
    // items 요소에는 개별 블로그 정보가 원소로 들어가 있습니다.
    // 파싱된 데이터는 10개씩 문자열에 누적 저장된 후
    $.each(d.items, function(index, item){
	 str += "<ul>";
	 str += "  <li style='text-style:'>" + (index + 1) + "</li>";
	 str += "  <li>" + item.title + "</li>";
	 str += "  <li>" + item.discription + "</li>";  
	 str += "  <li>" + item.bloggername + "</li>";  
	 str += "  <li>" + item.bloggerlink + "</li>";  
	 str += "  <li>" + item.postdate + "</li>";  
	 str += "  <li><a href='"+ item.link +"' target='_blnk' style='text-decoration:none; color:red;'>"+ item.link + " 바로가기 " + "</a></li>";  
	 str += "</ul>";
 });
 // id가 "searchResult"인 영역의 HTML 형태로 출력합니다.
 // 출력 HTML 영역은 <div class="row" id="searchResult">여기에 검색 결과가 출력됩니다.</div>입니다.
 $('#searchResult').html(str);
}

//실패했을 때 경고창을 띄워줍니다.
function errFunc(e){
	alert( "실패 : " + e.status);
}

</script>

<style type="text/css">
	ul{
	border: 2px #cccccc solid;
	}
</style>
</head>
<body>
<div >
	<!--  검색을 위한 form 태그를 정의합니다.-->
	<form id="searchFrm">
		한 페이지에 10개씩 출력됨 <br>
		<!--  검색 시작 위치를 페이지 단위로 선택하고 -->
		<select id="startNum">
			<option value="1">1페이지
			<option value="11">2페이지
			<option value="21">3페이지
			<option value="31">4페이지
			<option value="41">5페이지
		</select>
		
		<!--  검색어 입력 필드를 생성해 줍니다. -->
		<input type="text" id="keyword" placeholder="검색어를 입력하세요!"/>
		<!--  검색 요청 버튼을 생성해 줍니다. -->
		<button type="button" id="searchBtn">검색 요청</button>
	</form>
</div>
<!--  검색 결과가 출력되는 영역입니다. 다음의 자바스크립트 코드에서 결과로 ㅂ다은
	  JSON 데이터를 파싱해서, 아래 영역에 채워주게 합니다.  -->
	  <div class="row" id="searchResult">
	  	여기에 검색 결과가 출력됩니다.
	  </div>
</body>
</html>