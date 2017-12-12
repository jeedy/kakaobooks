<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jstl.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kakao books search</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>카카오 책 검색 API</h1>
		<div class="row">
			<div class="col-12">
				<form id="search_form" name="search_form" onsubmit="return submitSearch();">
					<input type="hidden" name="page" value="1">
					<select name="category">
						<option value="">카테고리</option>
						<c:forEach var="c" items="${EnumCategory }">
							<option value="${c.code }">${c.desc}</option>
						</c:forEach>
					</select>
					
					<select name="target">
						<c:forEach var="t" items="${EnumTarget }">
							<option>${t }</option>
						</c:forEach>
					</select>
					<input name="searchWord">
					<button type="submit">submit</button>
				</form>
				
			</div>
		</div>
		<hr>
		<h2>책 리스트 </h2>
		<div class="row">
			<div id="books">
				<ul class="list-group">
				</ul>
				<div id="resultMessage" class="alert" role="alert" style="display: none;">검색된 책이 없습니다.</div>
			</div>
		</div>
		<hr>
	</div>
<script type="text/javascript">
function submitSearch(page){
	var pg = page?page:1;
	var frm = document.search_form;
	frm.page.value=pg;
	if(frm.searchWord.value==""){
		alert("검색어를 입력하세요. ");
	}else{
		$.ajax({
			url : "/ajax/searchBooks",
			data : $(frm).serialize(),
			success : function( res ){
				if(res.meta.total_count<1){
					$("#books > ul").html("");
					$("#books > #resultMessage").show();
				}else{
					$("#books > #resultMessage").hide();
					var html = "";
					$(res.documents).each(function(idx){
						var authors = "", trans="",thumbnail="";
						$(this.authors).each(function(){
							authors+= (this+" ")
						});
						$(this.translators).each(function(){
							trans+= (this+" ")
						});
						if(this.thumbnail) {
							thumbnail = "<img src='"+this.thumbnail+"' width='100'>";
						}
						
						html+="<li class='list-group-item'>";
						html+="<dl><dt>"+this.title+" | "+this.publisher+" <a href='#'>bookmark</a></dt>";
						html+="<dd>"+thumbnail+" 저자: "+authors+"<br> 번역자: "+trans+"<br> 상태: "+this.status+" </dd></dl></li>";
					});
					if(!res.meta.is_end){
						html+="<li><button onclick='submitSearch("+(pg+1)+"); $(this).parent().remove();'>더보기 </button></li>";
					}
					if(pg>1){
						$("#books > ul").append(html);
					}else{
						$("#books > ul").html(html);
					}
				}
				console.log(res);
			},
			error : function( res){
				console.log(res);
				alert( res );
			}, 
			complete: function(){
			}
		});
	}
	
	return false;
}

$(document).ready(function(){
	$("#books > .list-group").on("mouseenter", ".list-group-item",function(){
		$(this).find("dd").show();
	});
	$("#books > .list-group").on("mouseleave", ".list-group-item",function(){
		$(this).find("dd").hide();
	});
});
</script>
</body>
</html>