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
	  
	  
	  $("input[name=outQuantity]").change(function(e) {
			e.preventDefault();

			var outQty = document.getElementById("outQuantity").value;
			var wQty = document.getElementById("quantity").value;
			
			document.getElementById("wareHousingQuantity").value = wQty - outQty;
	
	    });
	 
		 
		
		
		 $("#deleteBtn").click(function(){
			 
			 var jobPurchaseId = $("#jobPurchaseId").val();
			 var issueRequestId = $("#issueRequestId").val();
			 var jobOrderId = $("#jobOrderId").val();
			 var outQuantity = $("#outQuantity").val();
			 var outUser = $("#outUser option:selected").val();
			 var outReason = $("#outReason").val();
		    	
		        
		        if(confirm("전체 반품 후 재구매 등록 하시겠습니까? 바로 구매등록 됩니다.")){
		        	
		        	
		        	document.form2.action = "${path}/purchase/stockReBuy";
		        	document.form2.submit();
		        	window.opener.closeWin();



	
		        
		
		        	
		        }
		 });
		
		
	    $("#UpdateBtn").click(function(){
	    	
	    	 
			var wareHousingQuantity = $("#wareHousingQuantity").val();
			var outQuantity = $("#outQuantity").val();
			var outUser = $("#outUser option:selected").val();
			var reason = $("#outReason").val();
			var No = $("#No").val();
			
			var warHousingQty = opener.document.getElementById("wareHousingQuantity"+No).value;
	    	
	        
	        if(confirm("재구매 등록 없이 일부/전체 반품 하시겠습니까?")){
	        	
	        	window.opener.document.getElementById("wareHousingQuantity"+No).value = warHousingQty - $("#outQuantity").val();
	        	window.opener.document.getElementById("outQuantity"+No).value = $("#outQuantity").val();
	        	window.opener.document.getElementById("outUser"+No).value = $("#outUser").val();
	        	window.opener.document.getElementById("outReason"+No).value = $("#outReason").val();
	        	self.close();
	        }
	    });
	   
	});	
	
  </script>

</head>

<body class="sub">
<form id="form2" name="form2" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>반품등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:20%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        <col style="width:20%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        </colgroup>
                                        <tr>
                                            <th>발주No</th>
                                            <th>OderNo</th>
                                            <th>UNIT</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>구매처</th>
                                            <th>수량</th>
                                            
                                        </tr>
                                        <c:set var="wqty" value="0"></c:set>
                                        <c:forEach items="${data}" var="line">
                                        
                                        <fmt:parseNumber var="pwqty" value="${line.quantity}" integerOnly="true"/>
                                      
                                        <tr>
                                        
                                            <td>${line.issueRequestId}</td>
                                            <td>${line.jobOrderNo}</td>
                                            <td>${line.unitNo}</td>
                                            <td>${line.description}</td>
                                            <td>${line.modelNo}</td>
                                            <td>${line.maker}</td>
                                            <td>${line.partnerName}</td>
                                            <td><input type="number" id="quantity" name="quantity" value="${line.quantity}" readonly ></td>
                                          
                                  
                                        </tr>
                                        
                                    <input type="hidden" id="issueRequestId" name="issueRequestId" value="${line.issueRequestId}">
                     				<input type="hidden" id="jobOrderId" name="jobOrderId" value="${line.jobOrderId}">
                     				<input type="hidden" id="jobPurchaseId" name="jobPurchaseId" value="${line.jobPurchaseId}">	
                                       
                                       <c:set var="wqty" value="${wqty + pwqty}"/>
                                        </c:forEach>
                                    </table>
                                    
                                    <div>
                                    	<table>
         									<tr>
                        						<th>사유</th>
                            					<td colspan="50">
                            						<textarea style="width:100%; height:100%;" id="outReason" name="outReason" cols="50"></textarea>
                            					</td>
                        					</tr>
                     					</table>
                     				</div>
                     				
                     				<input type="hidden" id="No" name="No" value="${No}">
                     				                                    
               
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        
        	<div class="popBtn">
				<span>반품수량: </span>
			 	<span><input type="number" id="outQuantity" name="outQuantity"></span>
             	<span>입고수량: </span>
			 	<span><input type="number" id="wareHousingQuantity" name="wareHousingQuantity" value="${wqty}"></span>
              
            </div>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">
            
            	<span>반품 확인자: </span>
			 	<span>
			 		<select name="outUser" id="outUser" style="width:100px;">
                    	<option value=" ">선택</option>
                         <c:forEach items="${userName}" var="item">
                         	<option value="${item}">${item}</option>
                         </c:forEach>
                     </select>
			 	</span>

            	<span><button class="btn_blue" type="button" id="UpdateBtn">재구매 등록 없이 일부/전체 반품 확인</button></span> 
            	
            	<span><button class="btn_blue" type="button" id="deleteBtn">전체 반품 후 재구매 등록</button></span> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
