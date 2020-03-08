<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	
	<style>
	
	#Supplier tbody tr td, #Supplier tbody tr th {
		padding : 5px;
		border : 1px solid black;
	}
	
	
	</style>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>거래명세표</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>거래명세표</h2>
           		<div class="btn_area">
           			<span>거래명세일 : <fmt:formatDate value="${statement.issueDate }" pattern="yyyy-MM-dd" /> </span>
           		</div>
            </div>
            <!-- //팝업 서브타이틀 -->
            
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">
				<div class="popBoard">
					<table id="Supplier" style="width : 300px"  />
						<colgroup>
							<col style="width : 100px;" />
							<col style="width : 100px;" />
							<col style="width : 100px;"/>
						</colgroup>
						<thead>
						</thead>
						<tbody>
							<tr>
								<th rowspan="3">공급자</th>
								<th>사업자번호</th>
								<td>${statement.corporateNum }</td>
							</tr>
							<tr>
								<th>상호</th>
								<td>${statement.partnerName }</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${statement.corporatePhone }</td>
							</tr>
						</tbody>
					</table>
				</div>
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
                        </colgroup>
                        <thead>
	                        <tr>
	                        	<th>도면번호</th>
	                            <th>품명</th>
	                            <th>수량</th>
	                            <th>공급단가</th>
	                            <th>공급가액</th>
	                            <th>세액</th>
	                            <th>합계</th>
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
        </div>
        <!-- //팝업 콘텐츠 -->        
	</div>
</div>

<script type="text/javascript">

	var mainTable; 
	
	$(document).ready(function(){
		
		mainTable = $("#TBL").DataTable({
			"paging" : false,
			"ordering" : false,
			"info" : false,
			"searching" : false,
			"language" : dataTableLanguageCommon,
			"serverSide" : true,
			"ajax" : {
				url : "/ps/bill/data/view",
				data : function(d){
					d.statementId = "${statementId}";
				}
			},
			"columns" : [
				{"data" : "representiveDrawingNo"},
				{"data" : "issuedItemName"},
				{"data" : "issuedQuantity", render : $.fn.dataTable.render.number(",", ".")},					
				{"data" : "issuedUnitPrice", render : $.fn.dataTable.render.number(",", ".")},					
				{"data" : "suppliedPrice", render : $.fn.dataTable.render.number(",", ".")},
				{"data" : "suppliedVat", render : $.fn.dataTable.render.number(",", ".")},
				{"data" : "suppliedTotal", render : $.fn.dataTable.render.number(",", ".")}				
			],
			"initComplete" : function(setting, json){
				
				$("input[name='unitPrice']").trigger("change");
				
			}
		});
				
		
	});


</script>





</body>
</html>
