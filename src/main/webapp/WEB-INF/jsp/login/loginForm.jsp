<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login 페이</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<fieldset>
		<legend>로그인</legend>
		<div class="alert" style="${param.resultCode?'display:block':'display:none'}">잘못된 아이디입니다.</div>
		<form action="/loginForm" method="POST">
			<input type="text" name="account" value="" class="${param.resultCode?'error':'' }">
			<input type="password" name="pwd" value="" class="${param.resultCode?'error':'' }">
			<button type="submit">로그인</button>
		</form>
	</fieldset>
</body>
</html>