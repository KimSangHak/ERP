<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title></title>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/datatables.min.js"></script>

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/css/datatables.jqueryui.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

<script type="text/javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="/js/ux.js"></script>
</head>

<body class="sub">
	<div id="container">

		<div class="popForm">

			<!-- 팝업 타이틀 -->
			<div class="popTitArea">
				<h1>후처리 스킵 등록</h1>
			</div>
			<!-- //팝업 타이틀 -->

			<!-- 팝업 콘텐츠 -->
			<div class="popCont">

				<!-- 팝업 서브타이틀 -->
				<div class="pcTit mt20">
					<h2>후처리 스킵 등록</h2>
				</div>
				<!-- //팝업 서브타이틀 -->


				<!-- 테이블 콘텐츠 -->
				<div class="popList">

					<!-- 게시판 -->
					<div class="popBoard" >
						<table id="TBL">
							<colgroup span="2">
								<col style="width: 100px;" />
								<col style="width: 100px;" />
								<col style="width: 80px;" />
								<col style="width: 80px;" />
								<col style="width: 70px;" />
								<col style="width: 70px;" />
								<col style="width: 70px;" />
								<col style="width: 100px;" />
								<col style="width: 120px;" />
								<col style="width: 80px;" />
								<col style="width: 100px;" />
							</colgroup>
							<thead>
								<tr>
									<th>업체</th>
									<th>Device</th>
									<th>도면번호</th>
									<th>설계자</th>
									<th>Description</th>
									<th>Dimensions</th>
									<th>Mat’l</th>
									<th>열처리</th>
									<th>수량</th>
									<th>Spare</th>
									<th>후처리</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<!-- //게시판 -->

				</div>
				<!-- //테이블 콘텐츠 -->

				<!-- 
				<div class="progress">
					<input type="checkbox" id="markFinished"> 완품으로 함께 등록 처리
				</div>
				-->

				<!-- 팝업 버튼 -->
				<div class="popBtn">
					<a href="#" class="btn_gray" id="CloseWindowButton">취소</a> <a href="#" id="SaveButton" class="btn_blue">후처리
						스킵</a>
				</div>
				<!-- //팝업 버튼 -->

			</div>
			<!-- //팝업 콘텐츠 -->

		</div>
	</div>

	<script type="text/javascript">
		var drawingNo = "${targetIdList}";

		$(document).ready(function() {

			$("#SaveButton").click(function(e){
				e.preventDefault();
				
				$.ajax({
					url : "/ps/postprocess/update/skip",
					data : { "id" : drawingNo, "finished" : false /*$("#markFinished").is("checked")*/ },
					type : "POST",
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							alert("처리되었습니다");
							opener.location.reload();
							window.close();
						}else{
							alert(obj.reason);
						}							
					},error : function(){
						alert("서버 오류로 인해 처리할 수 없습니다");
					}
				});
			});
			
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
					url : "/ps/postprocess/data/reg?id=" + drawingNo
				},
				"columns" : [ {
					"data" : "outsourcingPartnerName"
				},{
					"data" : "device"
				}, {
					"data" : "designDrawingNo"
				}, {
					"data" : "designUserName"
				}, {
					"data" : "description"
				},{
					"data" : "dimension"
				},{
					"data" : "material"
				}, {
					"data" : "thermal"
				}, {
					"data" : "quantity"
				}, {
					"data" : "spare"
				}, {
					"data" : "postprocessing"
				} ]
			});

		});
	</script>

</body>
</html>
