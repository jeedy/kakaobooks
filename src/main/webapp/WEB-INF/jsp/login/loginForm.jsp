<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login 페이</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<%-- <fieldset>
		<legend>로그인</legend>
		
		<form action="/loginForm" method="POST">
			<input type="text" name="account" value="" class="${param.resultCode?'error':'' }">
			<input type="password" name="pwd" value="" class="${param.resultCode?'error':'' }">
			<button type="submit">로그인</button>
		</form>
	</fieldset> --%>
	
	<div class="container">
	  <div class="alert" style="${param.resultCode?'display:block':'display:none'}">잘못된 아이디입니다.</div>
      <form class="form-signin" action="/loginForm" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputAcount" class="sr-only">Email address</label>
        <input type="text" id="inputAcount" name="account" class="form-control" placeholder="account" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="pwd" class="form-control" placeholder="Password" required>
        <!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인 </button>
      </form>

    </div> <!-- /container -->
</body>
</html>