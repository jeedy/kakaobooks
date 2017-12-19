<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Books search - login</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	</head>
<body class="login">
    
	<div class="container">
    <div class="row">
      <div class="col">
        <form class="form-signin" action="/loginForm" method="POST">
          <h2 class="form-signin-heading">Books search by Kakao</h2>
          <label for="inputAcount" class="sr-only">사용자계정</label>
          <input type="text" id="inputAcount" name="account" class="form-control" placeholder="Account" required autofocus />
          <label for="inputPassword" class="sr-only">패스워드</label>
          <input type="password" id="inputPassword" name="pwd" class="form-control" placeholder="Password" required />
          <div class="error-message alert text-danger ${resultCode}" style="">계정정보가 정확하지 않습니다. 다시 입력해주세요.</div>
          
          <button class="btn btn-lg btn-block" type="submit">로그인 </button>
          <hr>
          <ul class="list-group text-muted">
              <li class="list-group-item">👀 <strong>처음 사용자</strong>는 계정과 패스워드를 입력하고 로그인 하시면됩니다.</li>
              <li class="list-group-item">👀 <strong> 기존 사용자</strong>는 처음에 입력하신 계정과 패스워드를 입력하고 로그인 하시면됩니다.</li>
          </ul>
        </form>
      </div>
    </div>

  </div> <!-- /container -->
</body>
</html>