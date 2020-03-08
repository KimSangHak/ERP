<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script language="JavaScript" src="/js/menu.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
  <script type="text/javascript">
	$(document).ready(function(){
		
	
		
		$("input[name=stockUseQuantity]").change(function(e) {
			e.preventDefault();
			
			var trid = $(this).closest('tr').attr('id');
			
			var orderQty = document.getElementById("orderQuantity"+trid).value;
			var stockUseQty = document.getElementById("stockUseQuantity"+trid).value;
			
			document.getElementById("orderQuantity"+trid).value = orderQty - stockUseQty;
			

	    });

		
		 $("#EstimateApplyAll").click(function(e){
				e.preventDefault();
				
				var val = $("#GlobalDate").val();
				console.log("date is " + val);
				
				$("input[name='receiveDate']").each(function(){
					$(this).val(val);	
				});
			});
		 
		 
		 $("#IssueApplyAll").click(function(e){
				e.preventDefault();
				
				var val = $("#GlobalDate2").val();
				console.log("date is " + val);
				
				$("input[name='ireceiveDate']").each(function(){
					$(this).val(val);	
				});
			});
		
		$("#UpdateBtn").click(function(){
		
		var jobOrderId = $("#jobOrderId").val();	
		var jobPurchaseId = $("#jobPurchaseId").val();
		var estimateRequestId = $("#estimateRequestId").val();
    	var receiveDate = $("#receiveDate").val();
    	var estimatedPrice = $("#estimatedPrice").val();
    	
        
        if(confirm("견적요청LIST를 수정하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/history/estimate/updated";
        	document.form1.submit();
        	self.close();        	
        }
    });
		
	$("#deleteBtn").click(function(){
		   		
		var jobOrderId = $("#jobOrderId").val();	
		var estimateRequestId = $("#estimateRequestId").val();
		var jobPurchaseId = $("#jobPurchaseId").val();
		    	
				  
		if(confirm("견적요청LIST를 삭제하시겠습니까?")){
		        	
		        	
			document.form1.target="parent";
		    document.form1.action = "${path}/purchase/history/estimate/deleted";
		    document.form1.submit();
		    self.close();        	
		}
	});
    
    $("#SendIssueBtn").click(function(){
   		
    	var selectPartnerName = $("#selectPartnerName option:selected").val();
    	var ijobOrderIds = $("#ijobOrderIds").val();	
    	var estimateRequestId = $("#estimateRequestId").val();
    	var jobPurchaseId = $("#jobPurchaseId").val();
    	var requestId = $("#requestId").val();
    	var modelNo = $("#modelNo").val();
    	var ireceiveDate = $("#ireceiveDate").val();
    	var orderQuantity = $("#orderQuantity").val();
    	var issuePrice = $("#issuePrice").val();
    	var stockId =  $("#stockId").val();
		var stockUseQuantity =  $("#stockUseQuantity").val();
		var maker =  $("#maker").val();
		  
        if(confirm("이대로 발주메일을 보내겠습니까?")){
        	
        	
        	
        	document.form1.action = "${path}/mail/purchasenew/issue";
        	document.form1.submit();
              	
        }
    });
    
    $("#delete").on("click", function() {

        var chk = $("input[name='record']:checked").length;

        if(chk > 0) {

            $("input[name='record']:checked").each(function() {
            	
            	var trid = $(this).closest('tr').attr('id');
            	
            	$(".estimateRequestId"+trid).remove();
            	$(".jobPurchaseId"+trid).remove();
            	$(".ijobOrderIds"+trid).remove();
            	$(".requestId"+trid).remove();
            	$(".stockId"+trid).remove();

     

                $(this).closest("tr").remove();

            });

        } else {

            alert("삭제할 항목 없음");

        }

    });
    
    $("#allChex").click(function(){
    	
			for(i=0; i < form1.record.length; i++) {
				form1.record[i].checked = true;
			}
    
      
    });
  
});
</script>

</head>

<body class="sub">
<div id="container">

<form id="form1" name="form1" enctype="multipart/form-data" method="post">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>견적 LIST 수정</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>견적 LIST</h2>
              
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">
            
            	
                    	 <select name="selectPartnerName" id="selectPartnerName" style="width:263px;">
                         	
                            	<c:forEach items="${partnerName}" var="name">
                                	<option value="${name}"<c:if test="${name == onePartnerName}">selected</c:if>>${name}</option>
                               	</c:forEach>
                         </select>
                         
                        <span>
                    		<input type="date" style="width: 180px" id="GlobalDate" >
                    	</span>
                		<span>
                    		<a href="#" class="btn_line_gray" id="EstimateApplyAll">견적납기일 일괄 적용</a>
                    	</span>
                    	
                    	<span>
                    		<input type="date" style="width: 180px" id="GlobalDate2" >
                    	</span>
                		<span>
                    		<a href="#" class="btn_line_gray" id="IssueApplyAll">발주납기일 일괄 적용</a>
                    	</span>
                    	

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:30px;" />
                        <col style="width:50px;" />
                        <col style="width:50px;" />
                        <col style="width:30px;" />
                        <col style="width:70px;" />
                        <col style="width:150px;" />
                        <col style="width:70px;" />
                        <col style="width:50px;" />
                   
                        <col style="width:100px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:150px;" />
                         <col style="width:30px;" />
                        <col style="width:30px;" />
                        <col style="width:80px;" />
                        <col style="width:50px;" />
                        <col style="width:50px;" />
                        <col style="width:70px;" />
                
                        </colgroup>
                        <thead>
                            <tr>
                            	<th>선택</th>
                            	<th>Seq</th>
                                <th>Order No.</th>
                                <th>UNIT</th>
                                <th>Description</th>
                                <th>Model No./Size</th>
                                <th>Maker</th>
                                <th>구매처</th>
                             
                                <th>견적납기일</th>
                                <th>견적가</th>
                                <th>최저가</th>
                                <th>최고가</th>
                                <th>발주납기일</th>
                                <th>구매등록수량</th>
                                <th>견적수량</th>
                                <th>현재고수량</br>(재고사용시만)</th>
                                <th>재고사용수량</th>
                                <th>발주수량</th>
                                <th>발주가</th>
                                
                              	
                         
                            </tr>
                        </thead>
                        
                        <tbody>
                        <c:set var="No" value="${pageNo + 1}"></c:set>
                        <c:forEach items="${data}" var="entry">
                            <tr id="${No}">
                                <td><input type="checkbox" name="record" id="record${No}"></td>
                                <td>${entry.jobPurchaseId}</td>                         
                                <td>${entry.jobOrderNo}</td>
                                <td>${entry.unitNo}</td>
                                <td>${entry.description}</td>
                                <td><input type="text" id="modelNo" name="modelNo" value="${entry.modelNo}"></td>
                                <td><input type="text" id="maker" name="maker" value="${entry.maker}"></td>
                                <td>${entry.partnerName}</td>
                               
                                <td><input type="date" id="receiveDate" name="receiveDate" 
            						value="<fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/>"></td>
                                <td><input type="number" name="estimatedPrice" value="${entry.estimatedPrice}"></td>
                                <td><fmt:formatNumber value="${entry.minPrice}" pattern="#,###" /></td>
                                <td><fmt:formatNumber value="${entry.maxPrice}" pattern="#,###" /></td>
                                <td><input type="date" id="ireceiveDate" name="ireceiveDate" 
            					value="<fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/>"></td>
            					 <td>${entry.dqty}</td>
                                <td>${entry.quantity}</td>
            					<td>${entry.sqty}</td> 
            					<td><input type="number" id="stockUseQuantity${No}" name="stockUseQuantity" value=0></td>
            					<td><input type="number" id="orderQuantity${No}" name="orderQuantity" value="${entry.quantity}"></td>                          
                               	<td><input type="number" id="issuePrice" name="issuePrice" value="${entry.estimatedPrice}"></td>
                                
                            </tr>
                      			<input type="hidden" id="estimateRequestId" name="estimateRequestId" class="estimateRequestId${No}" value="${entry.estimateRequestId}">
                      			
                      			<c:choose>
                      				<c:when test="${entry.stockId eq null}">
                      					<input type="hidden" id="stockId${No}" name="stockId" class="stockId${No}" value=" ">
                      				</c:when>
                      				<c:otherwise>
                      					<input type="hidden" id="stockId${No}" name="stockId" class="stockId${No}" value="${entry.stockId}">
                      				</c:otherwise>
                      			
                      			</c:choose>
                      			<input type="hidden" id="jobPurchaseId" name="jobPurchaseId" class="jobPurchaseId${No}" value="${entry.jobPurchaseId}">
                      			<input type="hidden" id="ijobOrderIds" name="ijobOrderIds" class="ijobOrderIds${No}" value="${entry.jobOrderId}">
                      			<input type="hidden" id="requestId" name="requestId" class="requestId${No}" value="aa">
                      			<c:set var="No" value="${No + 1 }" />
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- //게시판 -->
            
            </div>
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">
            	<!-- 
            	<button class="btn_gray" type="button" id="deleteBtn">삭제</button>
            	<button class="btn_blue" type="button" id="UpdateBtn">수정</button>
            	<button class="btn_blue" type="button" id="SendIssueBtn">발주 메일 보내기</button>
            	<button class="btn_blue" type="button" id="allChex">전체선택</button>
            	<button class="btn_blue" type="button" id="delete">선택 된 LIST 행제외</button>
            	
            	 -->
            	<a href="#" class="btn_gray" id="deleteBtn">삭제</a>  
            	<a href="#" class="btn_blue" id="UpdateBtn">수정</a>
            	<a href="#" class="btn_blue" id="SendIssueBtn">발주 메일 보내기</a>
              	<a href="#" class="btn_blue" id="allChex">전체선택</a>
              	<a href="#" class="btn_gray" id="delete">선택 된 LIST 행제외</a>  
              
            </div>
        
        
        
	</div>
	
	</form>
</div>
</body>
</html>
