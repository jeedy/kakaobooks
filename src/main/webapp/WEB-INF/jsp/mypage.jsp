<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jstl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kakao books search</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="main-top-layout">
		<div class="container">
			<%@ include file="/include/gnb.jsp"%>
			<h1>test</h1>
		</div>
	</div>
	<div class="container mt-3">
		<div class="row">
			<div class="col">
				<h3>북마크 리스트 </h3>
				<ul class="list-bookmark"></ul>
			</div>
			<div class="col">
				<h3>최근 검색한 리스트 </h3>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function btnBookmark() {
	    var ISBN = $(this).data("isbn");
	    $.ajax({
			url : "/ajax/bookmark",
			data : $(frm).serialize(),
			success : function(res) {
	
			    console.log(res);
			},
			error : function(res) {
			    console.log(res);
			    alert(res);
			},
			complete : function() {
			}
	    });
	}

	$(document).ready(
		function() {
		    $("#books > .list-group").on("mouseenter",
			    ".list-group-item", function() {
				$(this).find("dd").show();
			    });
		    $("#books > .list-group").on("mouseleave",
			    ".list-group-item", function() {
				$(this).find("dd").hide();
			    });
		    $("#books > .list-group").on("click", "btn-book-mark",
			    btnBookmark);
		});
    </script>
</body>
</html>