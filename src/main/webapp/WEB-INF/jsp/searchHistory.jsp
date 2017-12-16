<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jstl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색 히스토리 - Kakao books search</title>

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
				<h3>검색 히스토리</h3>
				<form action="./searchHistory" id="searchForm" name="searchForm" method="get">
					<input type="hidden" name="page" value="${searchHistoryPage.number}">
					<input type="hidden" name="size" value="${searchHistoryPage.size }">
					<div class="form-row">
						<div class="form-group">
							<select id="select-sort" name="sort" class="form-control">
								<option value="regdate,desc" ${param.sort=='regdate,desc'?'selected="selected"':'' }>시간역순 </option>
								<option value="regdate,asc" ${param.sort=='regdate,asc'?'selected="selected"':'' }>시간순 </option>
							</select>
						</div>
					</div>
				</form>
				<ul class="list-group">
					<c:if test="${empty searchHistoryPage.content }">
						<li>empty data.</li>
					</c:if>
					<c:forEach var="b" items="${searchHistoryPage.content }">
						<li class="list-group-item"><dl>
								<dt>
									<span>검색필드: ${b._target }, 카테고리: ${b._category }</span>
								</dt>
								<dd>
									검색어: <a href="./detail?isbn=9791158390785">${b.search_word }</a>
									<span class="blockquote-footer text-right"><fmt:formatDate value="${b.regdate }" pattern="yyyy. MM. dd HH:mm:ss"/></span>
								</dd>
							</dl></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="paging-layout center-block"></div>
	</div>


	<script type="text/javascript" src="/js/jquery.bootpag.min.js"></script>
	<script type="text/javascript">

		function btnUnbookmark() {
			const $this = $(this);
			const ISBN = $this.data("isbn");
			$.ajax({
				url : "/ajax/unbookmark",
				method: "POST",
				data : {
					"isbn" : ISBN
				},
				success : function(res) {
					console.log(res);
					alert("취소되었습니다.");
				},
				error : function(res) {
					console.log(res);
					alert(res);
				},
				complete : function() {
					$this.prop( "disabled", true );
				}
			});
		}
	
		$(document).ready(function() {
			<c:if test="${not empty searchHistoryPage.content }">
				$('.paging-layout').bootpag({
				    total: ${searchHistoryPage.totalPages},
				    page: ${searchHistoryPage.number+1},
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
			</c:if>
		});
		
		$('#searchForm select[name=sort]').on('change',function(){
			var frm = document.searchForm
			frm.page.value = 0;
			frm.submit();
		});
	</script>
</body>
</html>