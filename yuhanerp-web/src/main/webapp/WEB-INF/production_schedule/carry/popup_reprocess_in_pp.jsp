<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	// 외주 발주 요청 
	// 파워포인트 슬라이드 101.
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/" />
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>선택 외주 ${label } 요청</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>${partnerName } ${label } 요청</h2>
                <div class="btn_area">
                <span class="pl20">
                    <input type="text" readonly="readonly" class="AutoDatePicker" style="width: 150px" id="defaultDeliveryDate" >
                </span>
                <span>
                    <a href="#" id="setDefaultDeliveryDate" class="btn_line_gray">납기일 일괄 적용</a>
                </span>
                </div>
            </div>
            <!-- //팝업 서브타이틀 -->
            
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                	<form id="Form" method="POST">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:100px;" />	<!-- 업체 -->
                        <col style="width:170px;" />	<!-- Device -->
                        <col style="width:100px;" />		<!-- 도면 -->
                        <col style="width:60px;" />		<!-- 설계자 -->
                        <col style="width:100px;" />		<!-- Description -->
                        <col style="width:90px;" />		<!-- Dimensions -->
                        <col style="width:70px;" />		<!-- Mat’l -->
                        <col style="width:55px;" />		<!-- 열처리 -->
                        <col style="width:50px;" />		<!-- 수량 -->
                        <col style="width:55px;" />		<!-- Spare -->
                        <col style="width:80px;" />		<!-- 후처리 -->
                        <col style="width:90px;" />		<!-- 납기일 -->
                        <col style="width:100px;" />	<!-- 단가 -->
                        <col style="width:100px;" />	<!-- 공급가 -->
                        <col style="width:100px;" />	<!-- 도면 파일URL -->
                        </colgroup>
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
                            <th>납기예정일</th>
                            <th>단가</th>
                            <th>공급가</th>
                            <th>도면 파일URL</th>
                        </tr>
                        <c:forEach items="${data }" var="item">
                        <tr>
                        	<td>${item.customerName }</td>
                            <td>${item.device }</td>
                            <td>${item.designDrawingNo }</td>
                            <td>${item.designUserName }</td>
                            <td>${item.description }</td>
                            <td>${item.dimension }</td>
                            <td>${item.material }</td>
                            <td>${item.thermal }</td>
                            <td>${item.quantity }</td>
                            <td>${item.spare }</td>
                            <td>${item.postprocessing }</td>                            
                            <td><input type="text" name="deliveryDate" readonly="readonly" class="AutoDatePicker"  value="<fmt:formatDate  value="${item.processFinishPlanDate }" pattern="yyyy-MM-dd" />" > </td>
                            <td><input type="number" name="unitPrice" id="unitPrice" class="unitPrice" value=""></td>
                            <td></td>
                            <td>
                            	<input type="text" name="drawingUrl" value="">
                            	<input type="hidden" name="jobDrawingId" value="${item.id }"/>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    </form>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 -->
            
           
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray">취소</a>
                <a href="#" class="btn_blue" id="requestEstimation">${label } 요청 하기
</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>

	<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 납기일 일괄 적용
		$("#setDefaultDeliveryDate").click(function(e){
			e.preventDefault();
			$("input[name='deliveryDate']").val( $("#defaultDeliveryDate").val() );			
		});
		
		// 납기일 일괄 적용
		$(".unitPrice").change(function(e){
			e.preventDefault();
			var priceInput = $(this);
			var tr = priceInput.parent().parent();
			var td = tr.children();
			var price = Number(priceInput.val());
			var qty = Number(td.eq(8).text()) + Number(td.eq(9).text());
			var sumPrice = price*qty;
			td.eq(13).text(sumPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		});
		
		// 견적 요청하기
		$("#requestEstimation").click(function(e){
			e.preventDefault();
			
			var emptyTextbox = 0;
			$("input[name='deliveryDate']").each(function(){
				if($.trim($(this).val()) == '')
					emptyTextbox++;
			});
			if( emptyTextbox > 0) {
				alert("납기일을 전부 입력해 주세요");
				return;
			}
			
			emptyTextbox = 0;
			$("input[name='drawingUrl']").each(function(){
				if($.trim($(this).val()) == '')
					emptyTextbox++;
			});
		
			var withPostprocessing = $("#requestWithPostprocessing").prop("checked");
			$("#Form").ajaxSubmit({
				url : "/ps/process/ppreoutsourcing",
				success : function(obj){
					if( obj.result == "OK"){
						// obj.value 에는 "견적-요청ID" 들어있음 
						alert("정상적으로 요청되었습니다");
						
						
						window.close();
					}else
						alert(obj.reason);
				},
				error : function(){
					alert("견적 요청 중 오류가 발생하였습니다");
				}
			});
		});
	});
	
	</script>

</body>
</html>
