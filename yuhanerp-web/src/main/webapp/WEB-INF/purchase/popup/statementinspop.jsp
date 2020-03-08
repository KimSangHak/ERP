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
  
  
  $(document).on("keyup", "input:number[numberOnly]", function() {

		$(this).number(true);

	});


  
  $(document).ready(function(){
	  
	  $("input[name=issuedQuantity]").change(function(e) {
			e.preventDefault();
			
			
			var sum = 0;
			
			var trid = $(this).closest('tr').attr('id');
			
			var uqty = document.getElementById("issuedQuantity"+trid).value;
			var uprice = document.getElementById("issuedUnitPrice"+trid).value;
			
			document.getElementById("unitSumPrice"+trid).value = uqty * uprice;
			
			var count = $('input[name=issuedItemName]').length;
			
			for(var i=1; i<=count; i++){
				
				var qty = document.getElementById("issuedQuantity"+i).value;
				var price = document.getElementById("issuedUnitPrice"+i).value;
				
				sum = sum + (qty * price);
		      
		    }
			
			var nego = document.getElementById("negoPrice").value;
			
			document.getElementById("sumPrice").value = sum - nego;
			

	    });
	  
	  
	  $("input[name=issuedUnitPrice]").change(function(e) {
			e.preventDefault();
			
			
			var sum = 0;
			
			var trid = $(this).closest('tr').attr('id');
			
			var uqty = document.getElementById("issuedQuantity"+trid).value;
			var uprice = document.getElementById("issuedUnitPrice"+trid).value;
			
			document.getElementById("unitSumPrice"+trid).value = uqty * uprice;
			
			var count = $('input[name=issuedItemName]').length;
			
			for(var i=1; i<=count; i++){
				
				var qty = document.getElementById("issuedQuantity"+i).value;
				var price = document.getElementById("issuedUnitPrice"+i).value;
				
				sum = sum + (qty * price);
		      
		    }
			
			var nego = document.getElementById("negoPrice").value;
			
			document.getElementById("sumPrice").value = sum - nego;
			

	    });
	  
	  
	  $("input[name=negoPrice]").change(function(e) {
			e.preventDefault();
			
			
			var sum = 0;
			
			var count = $('input[name=issuedItemName]').length;
			
			for(var i=1; i<=count; i++){
				
				var qty = document.getElementById("issuedQuantity"+i).value;
				var price = document.getElementById("issuedUnitPrice"+i).value;
				
				sum = sum + (qty * price);
		      
		    }
			
			
			var nego = document.getElementById("negoPrice").value;
			
		
			
			document.getElementById("sumPrice").value = sum - nego;
			

	    });

	  
	  
	    $("#UpdateBtn").click(function(){
	    	
	    	
	    	var id = $("#id").val();
	    	var issueId = $("#issueId").val();
	    	var negoPrice = $("#negoPrice").val();
	    	var issuedItemName = $("#issuedItemName").val();
	    	var issuedQuantity = $("#issuedQuantity").val();
	    	var issuedUnitPrice = $("#issuedUnitPrice").val();
	    	var issueDate = $("#issueDate").val();
	    	var buyKind = $("#buyKind option:selected").val();
	    	var sumPrice = $("#sumPrice").val();
	        
	        if(confirm("이대로 거래명세표를 등록하시겠습니까?")){
	        	
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/purchase/statementIns/pop/insertdo";
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
        	<h1>거래명세표 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
    
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                
                
                  
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      									<col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:7%;" />
                                        <col style="width:7%;" />
                                        <col style="width:7%;" />
                                        <col style="width:8%;" />
                                        <col style="width:5%;" />
                                        <col style="width:7%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:7%;" />
                             

                                    
                                      
                                        </colgroup>
                                        <tr>
                                            <th>No</th>
                                            <th>OderNo</th>
                                            <th>UNIT</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>거래처</th>  
                                            <th>발주수량</th>
                                            <th>입고일</th>
                                            <th>입고수량</th>
                                            
                                            <th>거래명세</br>품명</th>
                                            <th>거래명세</br>수량</th>
                                            <th>거래명세</br>공급단가</th>
                                            <th>거래명세</br>공급가</th>
                                  

                                         
                                            
                                         
                                        </tr>
                                        
                                        
                                        <c:set var="sumPrice" value="${pageNo}"></c:set>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                      
                                 
                                        <c:forEach items="${data}" var="line">
                                     	
                                     	<fmt:parseNumber var="unitsum" value="${line.issuePrice * line.warehousingQuantity}" integerOnly="true"/>
                                
                                        <tr id="${No}">
                                        
                                            <td>${No}</td>
                                            <td>${line.jobOrderNo}</td>
                                            <td>${line.unitNo}</td>
                                            <td>${line.description}</td>
                                            <td>${line.modelNo}</td>
                                            <td>${line.maker}</td>
                                   			<td>${line.partnerName}</td>
                                   			<td>${line.orderQuantity}</td>
                                   			<td><fmt:formatDate value="${line.wareHousingDate}" pattern="yyyy-MM-dd"/></td>
                                   			<td>${line.warehousingQuantity}</td>
                                   			
                                   			<td><input type="text" id="issuedItemName" name="issuedItemName" value="${line.modelNo}"></td>
                                   			<td><input type="number" id="issuedQuantity${No}" name="issuedQuantity" value="${line.warehousingQuantity}"></td>
                                   			<td><input type="number" id="issuedUnitPrice${No}" name="issuedUnitPrice" value="${line.issuePrice}"></td>
                                   			<td><input type="number" id="unitSumPrice${No}" name="unitSumPrice" value="${unitsum}" readonly></td>                    			
                                   		
            							<c:set var="sumPrice" value="${sumPrice + unitsum}" />
            							
                                        </tr>
                                     	<input type="hidden" id="issueId" name="issueId" value="${line.issueId}">
                           				<input type="hidden" id="id" name="id" value="${line.id}">
                           				
                           				<c:set var="No" value="${No + 1 }" />
                                        </c:forEach>
                                    </table>
                           
                           
                           
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>

        <!-- //팝업 콘텐츠 -->
        
        	<div>
        		
        		<span>총 내고 금액 : </span>
                <span><input type="number" id="negoPrice" name="negoPrice" value=0></span>
                
                <span>총 합계 금액(네고포함 최종) : </span>
                <span><input type="number" id="sumPrice" name="sumPrice" value="${sumPrice}"></span>
        	
        	
        	</div>
        		<span>거래명세 날짜: </span>
        		<span>
        			<input type="date" id="issueDate" name="issueDate" pattern="yyyy-MM-dd"/>
        		</span>
        	
        	<div>
        		<span>결제방법 : </span>
                 <span>
                 	<select name="buyKind" id="buyKind" style="width: 100px;">
                        <option value="B">계산서</option>
                        <option value="H">현금</option>
                        <option value="C">카드결제</option>
                   </select>
                 
                 </span>    
        	
        	
        	</div>
        
        
            <div class="popBtn">
            
            	
                
             
                
                 
		
            	<span><button class="btn_blue" type="button" id="UpdateBtn">완료</button></span> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
