<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="UploadServlet" method="post" enctype="multipart/form-data">
	
	<input type="text" name="location"> 장소 <br/>
	<input type="text" name="length"> 거리 <br/>
	<input type="text" name="expect_time"> 예상시간 <br/>
	<input type="text" name="difficulty"> 난이도 <br/>
	<input type="file" name="file_id"  accept=".gpx" > 
	<input type="submit" value="전송">
	
		
	</form>
</body>
</html>