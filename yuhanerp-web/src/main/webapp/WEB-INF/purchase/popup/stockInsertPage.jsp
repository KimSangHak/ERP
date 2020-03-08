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
	    	var requestId = $("#requestId").val();
	    	var modelNo = $("#modelNo").val();
	    	var maker = $("#maker").val();
	    	var description = $("#description").val();
	    	var issuePrice = $("#issuePrice").val();
	    	var wareHousingQuantity = $("#wareHousingQuantity").val();
	    	var registrationUser = $("#registrationUser option:selected").val();
	    	var registrationReason = $("#registrationReason").val();
	    	var stockAble = $("#stockAble option:selected").val();
	    	
	        if(confirm("재고등록을 하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/purchase/insertStockMotion";
	        	document.form1.submit();
	        	self.close();
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
        	<h1>재고등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
     
                <div class="popList">
                
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:10%;" />
                                        <col style="width:8%;" />
                                        <col style="width:8%;" />
                                        <col style="width:5%;" />
                                        <col style="width:7%;" />
                                        <col style="width:7%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                      
                                        
                                        </colgroup>
                                        <tr>
                                            <th>발주No</th>
                                            <th>OrderNo</th>
                                            <th>UNIT</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>구매처</th>
                                            <th>수량</th>
                                       
                                           
                                        </tr>
                                      
                                        <c:forEach items="${data}" var="line">
                                
                                        <tr>
                                        
                                            <td><input type="text" id="requestId" name="requestId" value="${line.requestId}" readonly></td>
                                            <td><input type="text" id="jobOrderNo" name="jobOrderNo" value="${line.jobOrderNo}" readonly></td>
                                            <td><input type="text" id="unitNo" name="unitNo" value="${line.unitNo}" readonly></td>
                                            <td><input type="text" id="description" name="description" value="${line.description}" readonly></td>
                                            <td><input type="text" id="modelNo" name="modelNo" value="${line.modelNo}" readonly></td>
                                            <td><input type="text" id="maker" name="maker" value="${line.maker}" readonly></td>
                                            <td><input type="text" id="partnerName" name="partnerName" value="${line.partnerName}" readonly></td>
                                            <td><input type="number" id="wareHousingQuantity" name="wareHousingQuantity" value="${line.wareHousingQuantity}"></td>
                                            
                                  
                                        </tr>
                                        <input type="hidden" id="jobPurchaseId" name="jobPurchaseId" value="${line.jobPurchaseId}">
										<input type="hidden" id="issuePrice" name="issuePrice" value="${line.issuePrice}">
                                       
                                        </c:forEach>
                                    </table>
                                    
                       
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>

        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">
            	
            	<div>
            		<span>사유: </span>
			 		<span><input type="text" id="registrationReason" name="registrationReason"></span>
			 	</div>
			 	<div>
            		<span>재고등록자: </span>
			 		<span>
			 		
			 		
			 		<select name="registrationUser" id="registrationUser" style="width:100px;">
                    	<option value=" ">선택</option>
                         <c:forEach items="${userName}" var="item">
                         	<option value="${item}">${item}</option>
                         </c:forEach>
                     </select>
			 		</span>
			 	</div>
			 	
				
				
				<div>
				<span>
					  <select  name="stockAble" id="w260">
                         <option value="Y" selected>신품</option>
                         <option value="N">불용재고</option>
                      </select>
				
				</span>
            	<span><button class="btn_blue" type="button" id="UpdateBtn">재고등록</button></span>
              	</div>
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
