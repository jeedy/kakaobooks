<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jstl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kakao books search - bookmark page</title>

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
			<div class="col list-bookmark">
				<h3>북마크 리스트</h3>
				<form action="./bookmarks" id="searchForm" name="searchForm" method="get">
					<input type="hidden" name="page" value="${bookmarkPage.number}">
					<input type="hidden" name="size" value="${bookmarkPage.size }">
					<div class="form-row">
						<div class="form-group">
							<select id="select-sort" name="sort" class="form-control">
								<option value="regdate,asc" ${param.sort=='regdate,asc'?'selected="selected"':'' }>시간순 </option>
								<option value="title,asc" ${param.sort=='title,asc'?'selected="selected"':'' }>이름순 </option>
								<option value="regdate,desc" ${param.sort=='regdate,desc'?'selected="selected"':'' }>시간역순 </option>
								<option value="title,desc" ${param.sort=='title,desc'?'selected="selected"':'' }>이름역순 </option>
							</select>
						</div>
					</div>
				</form>
				<ul class="list-group">
					<c:forEach var="b" items="${bookmarkPage.content }">
						<li class="list-group-item"><dl>
								<dt>
									<a href="./detail?isbn=9791158390785">${b.title }</a> | 
									<span><fmt:formatDate value="${b.regdate }" pattern="yyyy. MM. dd HH:mm:ss"/></span>
									<button type="button" class="btn btn-light btn-bookmark delete">북마크 취소</button>
								</dt>
							</dl></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="paging-layout"></div>
	</div>


	<script type="text/javascript" src="/js/jquery.bootpag.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('.paging-layout').bootpag({
			    total: ${bookmarkPage.totalPages},
			    page: ${bookmarkPage.number+1},
			    maxVisible: 10,
			    leaps: true,
			    firstLastUse: true,
			    first: '←',
			    last: '→',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first'
			}).on("page", function(event, num){
				console.log(num);
				var frm = document.searchForm
				frm.page.value = num-1;
				frm.submit();
			});
		});
		
		$('#searchForm select[name=sort]').on('change',function(){
			var frm = document.searchForm
			frm.page.value = 0;
			frm.submit();
		});
	</script>
</body>
</html>