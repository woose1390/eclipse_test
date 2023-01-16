<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인화면</title>
	<!-- https://getbootstrap.com/ CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
	
<link rel="stylesheet" href="../css/main.css">

<!-- JSTL replace 함수로 개행문자 줄바꿈 처리 적용 -->
<%
pageContext.setAttribute("newLineChar", "\n");
%>
<c:set var="data" value="${article.content}" />
</head>
<body>
	<div class="container">
		<div class="box-wrapper">
			<p>제목 :
			<div id="title_box">${article.title}</div>
			</p>
			<!-- JSTL replace 함수로 개행문자 줄바꿈 처리 적용 -->
			<p>글 내용 :
			<div id="content_box">${fn:replace(data, newLineChar, "<br> ")}</div>

			</p>
			<form action="/index.jsp">
				<input type="submit" value="처음으로">
			</form>
		</div>
	</div>
</body>
</html>