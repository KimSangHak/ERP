<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	// 선택한 리스트 발주 수정 팝업
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<base href="/" />
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/datatables.min.js"></script>
<script type="text/javascript" src="/js/datatables.jqueryui.min.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="/js/ux.js"></script>
<script type="text/javascript" src="/js/auto_datepicker.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/css/datatables.jqueryui.min.css">
<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
<style type="text/css">

	.dataTables_wrapper .ui-toolbar
	{
	padding : 0px;
	}
</style>
</head>

<body class="sub">
	<div id="container">

		<div class="popForm">

			<!-- 팝업 타이틀 -->
			<div class="popTitArea">
				<h1>가공 발주 수정</h1>
			</div>
			<!-- //팝업 타이틀 -->

			<!-- 팝업 콘텐츠 -->
			<div class="popCont">

				<!-- 팝업 서브타이틀 -->
				<div class="pcTit mt20">
					<h2>&nbsp;</h2>
					<div class="btn_area">
						<span class="pl20"> <a
							href="javascript:PopWin('/popup/abort_reason?js=abortOrder','600','420','no');"
							class="btn_line_gray">일괄 발주 취소</a>
						</span>
					</div>
				</div>
				<!-- //팝업 서브타이틀 -->


				<!-- 테이블 콘텐츠 -->
				<div class="popList">

					<!-- 게시판 -->
					<form method="POST" action="/ps/process/update_order">
					<div class="popBoard" >
						<table id="TBL">
							<caption></caption>
							<colgroup span="2">
								<col style="width: 100px;" />
								<col style="width: 120px;" />
								<col style="width: 100px;" />
								<col style="width: 80px;" />
								<col style="width: 120px;" />
								<col style="width: 90px;" />
								<col style="width: 70px;" />
								<col style="width: 70px;" />
								<col style="width: 60px;" />
								<col style="width: 60px;" />
								<col style="width: 80px;" />
								<col style="width: 120px;" />
								<col style="width: 100px;" />
							</colgroup>
							<thead>
								<tr>
									<th>업체</th>
									<th>Device</th>
									<th>도면<br />번호
									</th>
									<th>설계자</th>
									<th>Description</th>
									<th>Dimensions</th>
									<th>Mat’l</th>
									<th>열처리</th>
									<th>수량</th>
									<th>Spare</th>
									<th>후처리</th>
									<th>납기일</th>
									<th>발주 단가</th>
								</tr>
							</thead>
							<tbody>
							</tbody>

						</table>
					</div>
					</form>
					<!-- //게시판 -->

				</div>
				<!-- //테이블 콘텐츠 -->

				<!-- 팝업 버튼 -->
				<div class="popBtn">
					<a href="#" class="btn_gray" id="CloseWindowButton">취소</a> <a href="#" id="UpdateButton" class="btn_blue">수정
						확인</a>
				</div>
				<!-- //팝업 버튼 -->

			</div>
			<!-- //팝업 콘텐츠 -->
		</div>
	</div>
	
	<script type="text/javascript">
	
		var drawingNo = "<c:out value='${id}' />";
		
		// 발주 취소 사유
		function abortOrder(abortReason, callerWindow){
			$.ajax({
				async : false,
				url : "/ps/process/abort_order",
				data : {
					id : drawingNo,
					reason : abortReason
				},
				dataType : "json",
				type : "POST",
				success : function(obj){
					if(obj.result == "OK"){
						callerWindow.alert("취소 처리되었습니다");
						window.close();
						opener.location.reload();
					}else{
						callerWindow.alert(obj.reason);
					}
				},error : function(){
					callerWindow.alert("서버와 통신 중 오류가 발생하였습니다");
				}
			});
		}
		
		$(document).ready(function(){
			
			// data-table 불러오고~
			$("#TBL").DataTable({
				"paging" : false,
				"ordering" : false,
				"info" : false,
				"searching" : false,
				"language" : {
					"lengthMenu" : "Display _MENU_ records per page",
					"zeroRecords" : "표시할 데이터가 없습니다",
					"info" : "Showing page _PAGE_ of _PAGES_",
					"infoEmpty" : "No records available",
					"infoFiltered" : "(filtered from _MAX_ total records)"
				},
				"serverSide" : true,
				"ajax" : {
					url : "/ps/process/data/order_list",
					data : function (d){
						d.id = drawingNo; 
					}		
				},
				"columns" : [
					{"data" : "customerName"},
					{"data" : "device"},
					{"data" : "designDrawingNo"},
					{"data" : "designUserName"},

					{"data" : "description"},
					{"data" : "dimension"},
					{"data" : "material"},
					{"data" : "thermal"},
					{"data" : "quantity"},
					{"data" : "spare"},

					{"data" : "postprocessing"},
					{"data" : "deliveryPlanDate", "render" : function(data) {
						return '<input type="text" class="lazyAutoDatePicker" name="outsourcingFinishDate" value="' + $('<div/>').text(data).html() + '">';
					}},
					{ "data" : "unitPrice", 'render': function (data, type, full, meta){
			             return'<input type="hidden" name="uid" value="' + $('<div/>').text(full.uid).html() + '"><input type="number" name="unitPrice" value="' + $('<div/>').text(data).html() + '">';
			             }  
					}
					
				]
			});
			
			
			$("#UpdateButton").click(function(e){
				e.preventDefault();
				
				$("form").ajaxSubmit({
					
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							window.opener.location.reload();
							alert("변경했습니다");
							window.close();
						}else{
							alert(obj.reason);							
						}
					},
					error : function(){
						alert("서버 오류가 발생하였습니다");
					}					
				});	
			});
						
		});
	
	
	</script>
</body>
</html>
