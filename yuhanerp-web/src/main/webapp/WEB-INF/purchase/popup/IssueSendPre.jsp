<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
  
  window.name="parent2";
  
  
  $(document).ready(function(){
	    $("#UpdateBtn").click(function(){
	    	
	    	
	    	var partnerId = $("#partnerId").val();
	    	var rawJobOrderId = $("#rawJobOrderId").val();
	    	var requestId = $("#requestId").val();
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	var stockUseQuantity = $("#stockUseQuantity").val();
	    	var issuePricell = $("#issuePricell").val();
	    	var orderQuantity = $("#orderQuantity").val();
	    	var receiveDate = $("#receiveDate").val();
	    	var stockId =  $("#stockId").val();
	    
	        
	        if(confirm("이대로 발주메일을 보내시겠습니까?")){
	        	document.form1.action = "${path}/mail/purchase/issue";
	        	document.form1.submit();
	        }
	    });
	   
	});	
	
  </script>

</head>

<body class="sub">
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>발주</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
       <c:forEach items="${data }" var="item">
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                    <div class="popListTit">
                       <h4>Order No : [${item[0].jobOrderNo }]</h4>
                       
                    </div>
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:10%;" />
                                        <col style="width:8%;" />
                                        <col style="width:4%;" />
                                        <col style="width:4%;" />
                                        <col style="width:7%;" />
                                        <col style="width:12%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:6%;" />
                                        <col style="width:5%;" />
                                        <col style="width:6%;" />
                                        <col style="width:5%;" />
                                        <col style="width:10%;" />
                                        
                                        </colgroup>
                                        <tr>
                                            <th>견적NO</th>
                                            <th>OderNo</th>
                                            <th>UNIT</th>
                                            <th>구매수량</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>재고수량</th>
                                            <th>최저가</th>
                                            <th>최고가</th>
                                            <th>견적가</th>
                                            <th>재고사용</th>
                                            <th>발주가</th>
                                            <th>발주수량</th>
                                            <th>납기일</th>
                                           
                                        </tr>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${item }" var="line">
                                
                                        <tr>
                                        
                                            <td><input type="text" id="requestId" name="requestId" value="${line.requestId}" readonly></td>
                                            <td><input type="text" id="jobOrderNo" name="jobOrderNo" value="${line.jobOrderNo}" readonly></td>
                                            <td><input type="text" id="unitNo" name="unitNo" value="${line.unitNo}" readonly></td>
                                            <td><input type="number" id="quantity" name="quantity" value="${line.quantity}" readonly></td>
                                            <td><input type="text" id="description" name="description" value="${line.description}" readonly></td>
                                   			
                                            <td><a href="javascript:PopWin('/purchase/stockUse/${line.modelNo}/${No}','1300','500','no');" class="btn_line_blue">${line.modelNo}</a></td>
                                            
                                            <td><input type="text" id="maker" name="maker" value="${line.maker}" readonly></td>
                                            <td><input type="number" id="stockQuantity" name="stockQuantity" value="${line.stockQuantity}" readonly></td>
                                            <td><input type="number" id="minPrice" name="minPrice" value="${line.minPrice}" readonly></td>
                                            <td><input type="number" id="maxPrice" name="maxPrice" value="${line.maxPrice}" readonly></td>
                                            <td><input type="number" id="estimatedPrice" name="estimatedPrice" value="${line.estimatedPrice}" readonly></td>                              
                  							<td><input type="number" id="stockUseQuantity${No}" name="stockUseQuantity" value="${line.stockUseQuantity}" readonly></td>
                                            <td><input type="number" id="issuePricell" name="issuePricell" value="${line.issuePricell}"></td>
                                            <td><input type="number" id="orderQuantity" name="orderQuantity"></td>  
                                         	<td><input type="date" id="receiveDate" name="receiveDate" 
            								value="<fmt:formatDate value="${line.receiveDate}" pattern="yyyy-MM-dd"/>"></td>
                                            
                                  
                                        </tr>
                                        <input type="hidden" id="jobPurchaseId" name="jobPurchaseId" value="${line.jobPurchaseId}">
                                        <input type="hidden" id="stockId${No}" name="stockId" value=" ">
                                        <c:set var="No" value="${No + 1 }" />
                                        </c:forEach>
                                    </table>
                                    
                                    <input type="hidden" id="partnerId" name="partnerId" value="${partnerId}">
                                    <input type="hidden" id="rawJobOrderId" name="rawJobOrderId" value="${rawJobOrderId}">
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        </c:forEach>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">

            	<button class="btn_blue" type="button" id="UpdateBtn">확인</button> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
