<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>test</title>
</head>
<body>
<h1>회사명:${company }</h1>
<h1>부서:${dept }</h1>
<h1>이름:${myname }</h1>
<hr>
<form action="paramtest">
	id : <input name="userid" type="number"><br>
	name : <input name="username" type="text"><br>
	<input value="서버전송" type="submit"><br>

</form>

<form action="paramtest2">
	id : <input name="userid" type="number"><br>
	name : <input name="username" type="text"><br>
	<input value="서버전송2" type="submit"><br>

</form>
</body>
</html>