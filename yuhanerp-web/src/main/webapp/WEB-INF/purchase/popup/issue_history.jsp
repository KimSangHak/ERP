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
  
 
  
  $(document).ready(function(){
	    $("#UpdateBtn").click(function(){
	    	
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	
	        
	        if(confirm("발주를 취소 하시겠습니까?")){
	        	document.form1.action = "${path}/purchase/deleteIssue";
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
        	<h1>발주/견적 history</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
    
                <div class="popList">
                 
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	 <table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:150px;" />
                                	<col style="width:70px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
									<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>발주No</th>
                                        	<th>발주일</th>
                                        	<th>OrderNo</th>
                                        	<th>UNIT</th>
                                        	<th>Description</th>
                                        	<th>Model/Size</th>
                                        	<th>Maker</th>
                                        	<th>구매처</th>
                                        	<th>최저가</th>
                                        	<th>최고가</th>
                                        	<th>구매수량</th>
                                        	<th>발주수량</th>
                                        	<th>재고수량</th>
                                        	<th>납기일</th>
                                        	<th>단가</th>
                                        	<th>공급가</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               	 		<fmt:parseNumber var="qty" value="${entry.order_quantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="price" value="${entry.issue_price}" integerOnly="true"/>
                                        <fmt:parseNumber var="Sum" value="${qty*price}" integerOnly="true"/>
                                        
                                        <fmt:parseNumber var="stockqty" value="${entry.stockQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="abQuantity" value="${entry.abQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="remind" value="${stockqty-abQuantity}" integerOnly="true"/>
                               	 		
                                		<tr>
                                		
                                			<td>${entry.request_id}</td>
                                        	<td>${entry.issue_date}</td>
                                        	<td>${entry.job_order_no}</td>
                                        	<td>${entry.unit_no}</td>
                                        	<td>${entry.description}</td>
                                        	<td>${entry.model_no}</td>
                                        	<td>${entry.maker}</td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.minPrice}</td>
                                        	<td>${entry.maxPrice}</td>
                                        	<td>${entry.quantity}</td>
                                        	<td>${entry.order_quantity}</td>
                                        	<td>${remind}</td>
                                        	<td>${entry.receive_date}</td>
                                        	<td>${entry.issue_price}</td>
                                        	<td>${Sum}</td>
                                    	</tr>
                                    	
                                   
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                            	
                            	
                            	 <c:choose>
                    				<c:when test="${empty data2}">
                    					<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>신규발주 견적요청 내용이 없습니다.</h1>
                        			</c:when>
                        			<c:otherwise>
                        			
                        			<div>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
                        				
                        			</div>
                        			<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:150px;" />
                                	<col style="width:70px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
									
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>견적No</th>
                                        	<th>견적요청일</th>
                                        	<th>OrderNo</th>
                                        	<th>UNIT</th>
                                        	<th>Description</th>
                                        	<th>Model/Size</th>
                                        	<th>Maker</th>
                                        	<th>구매처</th>
                                        	<th>수량</th>
                                        	<th>납기일</th>
                                        	<th>견적가</th>
                                        	
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data2}" var="entry2">
                               	 		
                               	 		
                                		<tr>
                                		
                                			<td>${entry2.requestId}</td>
                                        	<td>${entry2.issueDate}</td>
                                        	<td>${entry2.jobOrderNo}</td>
                                        	<td>${entry2.unitNo}</td>
                                        	<td>${entry2.description}</td>
                                        	<td>${entry2.modelNo}</td>
                                        	<td>${entry2.maker}</td>
                                        	<td>${entry2.partnerName}</td>
                                        	<td>${entry2.orderQuantity}</td>
                                        	<td>${entry2.receiveDate}</td>
                                        	<td>${entry2.estimatedPrice}</td>
                                        	
                                    	</tr>
                                    	
                                   
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                        				
                            			
                        			</c:otherwise>
                    			</c:choose>
                           
                                    
                     
                           <input type="hidden" id="jobPurchaseId" name="jobPurchaseId" value="${jobPurchaseId}">          
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">

            	<button class="btn_blue" type="button" id="UpdateBtn">발주취소</button> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
