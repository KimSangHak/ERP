<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/datatables.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
	<script type="text/javascript" src="/js/ux.js"></script>	
	<script type="text/javascript" src="/js/datatables.min.js"></script>
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>거래명세표 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>${partnerName } 거래명세표 등록</h2>
                
                <div class="btn_area">
                	<span class="pr10">거래명세표 날짜 : </span>
                	<span>
                    	<input type="text" class="AutoDatePicker" style="width: 140px" id="issueDate" value="${today}" />
                    </span>
                    <span class="pr10">네고 금액</span>
                    <span>
                    	<input type="text" class="" style="width : 100px" id="negoPrice" value="0"/>
                    </span>
                </div>                
            </div>
            <!-- //팝업 서브타이틀 -->
            
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <form method="POST" id="FormData" action="/ps/bill/make/statement">
                <div class="popBoard">
                    <table id="TBL" style="width : 100%">
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:120px;" />
                        <col style="width:120px;" />
                        <col style="width:90px;" />
                        <col style="width:120px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:90px;" />
                        <col style="width:90px;" />
                        <col style="width:90px;" />
                        </colgroup>
                        <thead>
	                        <tr>
	                        	<th>도면번호</th>
	                            <th>Description</th>
	                            <th>Mat'l</th>
	                            <th>열처리</th>
	                            <th>도면수량</th>
	                            <th>Spare</th>
	                            <th>후처리</th>
	                            <th>입고일</th>
	                            <th>품명</th>
	                            <th>수량</th>
	                            <th>단가</th>
	                            <th>합계</th>
	                            <th>항목제외</th>
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
                <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                <a href="#" id="SubmitForm" class="btn_blue">거래명세표 등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->        
	</div>
</div>

<script type="text/javascript">

	var mainTable; 
	
	function makeUploadData(){
		var rows = [];
		$("#TBL tbody tr").each( function(){
			
			var eachRow = {};
			$(this).find("input").each(function(){
				var id = $(this).attr("name");
				var val = $(this).val();
				eachRow[ id ] = $(this).attr("type") == "checkbox" ? ($(this).is(":checked") ? val : "") :val;
			});
			
			rows.push(eachRow);
		});		
		return JSON.stringify(rows);
	}
	
	$(document).ready(function(){
		
		// 등록
		$("#SubmitForm").click(function(e){
			e.preventDefault();
			
			if( $("#issueDate").val() == ""){
				alert("발행 날짜를 입력해 주세요");
				return;
			}
			
			if( $("#negoPrice").val() == "" ){
				alert("네고 금액을 입력해 주세요");
				return;
			}
									
			var payload = makeUploadData();
			console.log( "data is " + payload);
			
			var url = "/ps/bill/make/statement";
			url += "?partnerId=${partnerId}";
			url += "&requestType=${requestType}";
			url += "&outsourcingOrderId=${id}";
			url += "&issueDate=" + $("#issueDate").val();
			url += "&negoPrice=" + $("#negoPrice").val();
			
			$.ajax({
				url : url,
				contentType : "application/json",
				method : "POST",
				data : payload,
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						alert("등록되었습니다");
						window.opener.location.reload();
						window.close();
					}else {
						alert(obj.reason);
					}
				},error : function(){
					alert("서버와 통신 오류가 발생하였습니다");
				}
			});
		});
		
		// 단가, 수량 변경시 합계 변경
		$(document).on("change", "input[name='totalQuantity'] , input[name='unitPrice'] ", function(){
			
			var TR = $(this).parent().parent();
			var quantity, unitPrice;
			quantity = TR.find("input[name='totalQuantity']").val();
			unitPrice = TR.find("input[name='unitPrice']").val();
			
			TR.find("input[name='sum']").val( quantity * unitPrice );
		});
		
		mainTable = $("#TBL").DataTable({
			"paging" : false,
			"ordering" : false,
			"info" : false,
			"searching" : false,
			"language" : dataTableLanguageCommon,
			"serverSide" : true,
			"ajax" : {
				url : "/ps/bill/data/reg",
				data : function(d){
					d.id = "${id}",
					d.partnerId = "${partnerId}",
					d.requestType = "${requestType}"
					d.jobOrderId = "${jobOrderId}"
				}
			},
			"columns" : [
		
				
				{"data" : "designDrawingNo", render : function(data){
					return '<input type="text" name="designDrawingNo" value="' + data + '" />';
				}},
				
				{"data" : "description"},
				{"data" : "material"},					
				{"data" : "thermal"},					
				{"data" : "quantity"},
				{"data" : "spare"},
				{"data" : "postprocessing"},
				{"data" : "finishDate"},
				{"data" : "description", render : function(data){
					return '<input type="text" name="itemName" value="' + data + '" />';
				}},
				{"data" : "totalQuantity", render : function(data){
					return '<input type="text" name="totalQuantity" value="' + data + '" />';
				}},
				{"data" : "unitPrice" ,render : function(data){
					return '<input type="text" name="unitPrice" value="' + data + '" />';
				}},
				{"data" : null ,render : function(data){
					return '<input type="text" name="sum" value="" />';
				}},
				{"data" : "uid", render : function(data) {
					return '<input type="checkbox" name="exclude" value="' + data + '" /><input type="hidden" name="uid" value="' + data + '" />';	
				}}
			],
			"initComplete" : function(setting, json){
				
				$("input[name='unitPrice']").trigger("change");
				
			}
		});
				
		
	});


</script>





</body>
</html>
