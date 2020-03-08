
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/ux.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="/js/auto_datepicker.js"></script>

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/css/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

<!-- datatables -->
<script type="text/javascript" src="/js/datatables.min.js"></script>
<style type="text/css">
	.dataTables_wrapper .ui-toolbar {
		padding : 0px ;
	}
</style>

</head>

<body class="sub">
	<div id="container">

		<div class="popForm">

			<!-- 팝업 타이틀 -->
			<div class="popTitArea">
				<h1>선택도면 ${title } 입고 처리</h1>
			</div>
			<!-- //팝업 타이틀 -->

			<!-- 팝업 콘텐츠 -->
			<div class="popCont">

	            <!-- 팝업 서브타이틀 -->
	            <div class="pcTit mt20">
	                <h2>선택도면 ${title } 입고 처리</h2>
	                <c:if test="${scene == 'C' }">
	                <div class="btn_area">
		                <span>후처리업체</span>
		                <span class="pl20">
	                        <c:import url="/internal/util/select/postprocess_partner">
	                        	<c:param value="partnerId" name="controlName"/>
	                            <c:param value="true" name="showUnspecifiedItem"/>
	                            <c:param value="" name="cssClass"/>
	                            <c:param value="width: 100px" name="style" />
                            </c:import>

		                </span>
		                <span class="pl20">
		                    <input type="text" readonly="readonly" class="AutoDatePicker" style="width: 100px" id="defaultDeliveryDate" >
		                </span>
		                <span>
		                    <a href="#" id="setDefaultDeliveryDate" class="btn_line_gray">납기일 일괄 적용</a>
		                </span>
	                </div>
	                </c:if>
	            </div>
	            <!-- //팝업 서브타이틀 -->

				<!-- 테이블 콘텐츠 -->
				<div class="popList">

					<!-- 게시판 -->
					<div class="popBoard">
						<table id="TBL">
							<caption></caption>
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
									<th>도면번호</th>
									<th>발주번호</th>
									<th>설계자</th>
									<th>Mat’l</th>
									<th>열처리</th>

									<th>수량</th>
									<th>Spare</th>
									<th>후처리</th>
									<th>가공업체</th>
									<th>가공납기일</th>

									<c:if test="${scene != 'C' }">
									<th>후처리 결과</th>
									</c:if>
									<c:if test="${scene == 'C' }">
									<th>납기 예정일</th>
									</c:if>
								</tr>
							</thead>
							<tbody>

							 </tbody>
						</table>
					</div>
					<!-- //게시판 -->

				</div>
				<!-- //테이블 콘텐츠 -->

				<!-- 주의사항 -->
				<c:if test="${scene != 'B'}">
				<div class="care_text">
					<h6>※ 주의 ※</h6>
					<p>완품입고 처리를 할 경우, 후처리가 안된 제품은 후처리를 스킵 처리합니다</p>
				</div>
				<!-- /주의사항 -->
				</c:if>

				<!-- 팝업 버튼 -->
				<div class="popBtn">
					<a href="#" id="CloseWindowButton" class="btn_gray">취소</a> <a href="#" class="btn_blue SaveButton">${title }
						등록</a>
				</div>
				<!-- //팝업 버튼 -->

			</div>
			<!-- //팝업 콘텐츠 -->



		</div>
	</div>

	<script type="text/javascript">
	
		var drawingNo = "<c:out value='${id}' />";
		var scene = "${scene}";
	
		$(document).ready(function() {

			$("#setDefaultDeliveryDate").click(function(e){
				e.preventDefault();
				
				var defaultDeliveryDate = $("#defaultDeliveryDate").val();
				
				$("input[name='coatingFinishPlanDate']").each(function(){
					$(this).val(defaultDeliveryDate);
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
					url : "/ps/carry/data/in?drawings=" + drawingNo							
				},
				"columns" : [
					{ "data" : "designDrawingNo"},
					{"data" : "outsourcingOrderNo"},
					{"data" : "designUserName"},
					{"data" : "material"},
					{"data" : "thermal"},
					
					{"data" : "quantity"},
					{"data" : "spare"},
					{"data" : "postprocessing"},
					{"data" : "outsourcingPartnerName"},
					{"data" : "deliveryPlanDate"},
					
					<c:if test="${scene == 'C' }">
					{"data" : null, "render" : function(data, type, row) {
						
						return '<input type="text" class="lazyAutoDatePicker" name="coatingFinishPlanDate" />';
						
					} }
					</c:if>
					<c:if test="${scene != 'C'}">
					{"data" : "postprocessing"}
					</c:if>
				]
			});
			
			// 등록
			$(".SaveButton").click(function(e){
				e.preventDefault();
				
				// 후처리 업체 선택 여부
				var selectedPartnerId = $("#partnerId").val();
				if(selectedPartnerId == ""){
					alert("후처리 업체를 선택해 주세요");
					return;
				}
				
				// 요청
				$.ajax({
					"url" : "/ps/carry/perform/in",
					"data" : { 
						<c:if test="${scene == 'C'}">
						"partnerId" : selectedPartnerId,
						"coatingFinishPlanDate" : function(){
							var dates = [];
							$("input[name='coatingFinishPlanDate']").each(function(){
								dates.push( $(this).val() );
							});
							return dates.join();
						},
						</c:if>
						"id" : drawingNo,
						"scene" : scene
						},
					"type" : "POST",
					"dataType" : "json",
					"success" : function(obj){
						if(obj.result == "OK"){
							alert("${title} 입고 처리 되었습니다");
							opener.location.reload();
							window.close();
						}else{
							alert(obj.reason);
						}
					},
					"error" : function(){
						alert("서버 통신 중 오류가 발생하였습니다");
					}
				});
			});

		});
	</script>
</body>
</html>
