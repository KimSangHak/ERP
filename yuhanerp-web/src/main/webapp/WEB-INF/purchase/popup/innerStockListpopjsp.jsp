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
  

  
  function deleterow(No){
	  
	  
	  $("#"+No).remove();
		
  }
  
  function closeWin(){  
	  top.window.opener = top;
	  top.window.open('','_parent','');
	  top.window.close();
	 }



  
	

  
  
  $(document).ready(function(){
	    
	  $("#UpdateBtn").click(function(){
	    	
	    	
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	var receiverUsr = $("#receiverUsr option:selected").val();
	    	var warehousingQuantity = $("#warehousingQuantity").val();
	    
	    	if(form1.receiverUsr.value == " "){
	    		
	    		alert("인수부서를 선택해주세요.");
	    		return false;
	    	}

	        if(confirm("이대로 불출 등록을 하시겠습니까?")){
	        	
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/purchase/ListReceivePass";
	        	document.form1.submit();
	        	self.close();
	        }
	    });
	    
		$("#insertBtn").click(function(){
	    	
	    	
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	var issueRequestId = $("#issueRequestId").val();
	    	var modelNo = $("#modelNo").val();
	    	var maker = $("#maker").val();
	    	var description = $("#description").val();
	    	var issuePrice = $("#issuePrice").val();
	    	var stockUseQuantity = $("#stockUseQuantity").val();
	    	var registrationReason = $("#registrationReason").val();
	    	var stockAble = $("#stockAble").val();
	    	
	    

	        if(confirm("이대로 재고 등록을 하시겠습니까?")){
	        	
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/purchase/insertStockMotion";
	        	document.form1.submit();
	        	self.close();
	        }
	    });
	    
	    $("#delete").on("click", function() {

			var chk = $("input[name='record']:checked").length;

			if(chk > 0) {

	    		$("input[name='record']:checked").each(function() {
	    	
	    			var trid = $(this).closest('tr').attr('id');
	    	
	    		
	    			$(".jobPurchaseId"+trid).remove();
	    			$(".issueRequestId"+trid).remove();
	    			$(".jobOrderId"+trid).remove();
	    			$(".issuePrice"+trid).remove();


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
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>반출 및 재고등록</h1>
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
                                        <col style="width:7%;" />
                                        <col style="width:7%;" />
                                        <col style="width:8%;" />
                                        <col style="width:7%;" />
                                      
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:8%;" />
                                        <col style="width:8%;" />
                                      
                                        <col style="width:5%;" />
                                        <col style="width:8%;" />
                                     	<col style="width:5%;" />
                                        <col style="width:5%;" />
                                        
                                        <c:choose>
                                            <c:when test="${stage eq 'O'}">
                                            	 <col style="width:5%;" />
                                            	 <col style="width:5%;" />
                                            </c:when>
                                            <c:otherwise>
                                            	
                                            </c:otherwise>
                                            	
                                       </c:choose>
                                       
                      
                                    
                                      
                                        </colgroup>
                                        <tr>
                                        	
                                            <th>선택</th>
                                            <th>Seq</th>
                                            <th>OderNo</th>
                                            <th>UNIT</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                      
                                            <th>구매처</th>
                                            <th>구매수량</th>
                                            <th>발주수량</th>
                                            <th>재고수량</th>
                                            <th>입고수량</th>
                                            <th>납기일</th>
                                         	
                                        <c:choose>
                                            <c:when test="${stage eq 'O'}">
                                            	 <th>재고등록가능수량</th>
                                            	 <th>재고등록수량</th>
                                            </c:when>
                                            <c:otherwise>
                                            	
                                            </c:otherwise>
                                            	
                                       </c:choose>
                                     
  
                                         
                                            
                                            
                                            
                                        </tr>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${data}" var="line">
                                  
                                        <tr id="${No}">
                                        	
                                            <td><input type="checkbox" name="record" id="record${No}"></td>
                                            <td>${line.jobPurchaseId}</td>
                                            <td><input type="text" id="jobOrderNo" name="jobOrderNo" value="${line.jobOrderNo}" readonly></td>
                                            <td><input type="text" id="unitNo" name="unitNo" value="${line.unitNo}" readonly></td>
                                            <td><input type="text" id="description" name="description" value="${line.description}" readonly></td>
                                            <td><input type="text" id="modelNo" name="modelNo" value="${line.modelNo}" readonly></td>
                                            <td><input type="text" id="maker" name="maker" value="${line.maker}" readonly></td>
                                          
                                   			<td><input type="text" id="partnerName" name="partnerName" value="${line.partnerName}" readonly></td>
                                   			<td><input type="number" id="dquantitiy" name="dquantitiy" value="${line.dquantitiy}" readonly></td>
                                   			<td><input type="number" id="quantity" name="quantity" value="${line.quantity}" readonly></td> 
                                   			<td><input type="number" id="stockQuantity" name="stockQuantity" value="${line.stockQuantity}" readonly></td>
                                   			<td><input type="number" id="warehousingQuantity" name="warehousingQuantity" value="${line.warehousingQuantity}" readonly></td>
                                   			<td><input type="date" id="receiveDate" name="receiveDate" 
            								value="<fmt:formatDate value="${line.receiveDate}" pattern="yyyy-MM-dd"/>"></td>
            								
            								<c:choose>
                                            <c:when test="${stage eq 'O'}">
                                            	<td><input type="number" id="possibleQty" name="possibleQty" value="${line.possibleQty}" readonly></td>
                                            	<td><input type="number" id="stockUseQuantity" name="stockUseQuantity"></td>
                                            </c:when>
                                            <c:otherwise>
                                            	
                                            </c:otherwise>
                                            	
                                       </c:choose>
            							
            								
            								       
                                        </tr>
                                        
                                   
                           				<input type="hidden" id="jobPurchaseId" name="jobPurchaseId" class="jobPurchaseId${No}" value="${line.jobPurchaseId}">
                           				<input type="hidden" id="issueRequestId" name="issueRequestId" class="issueRequestId${No}" value="${line.issueRequestId}">
                           				<input type="hidden" id="jobOrderId" name="jobOrderId" class="jobOrderId${No}" value="${line.jobOrderId}">
                           				<input type="hidden" id="issuePrice" name="issuePrice" class="issuePrice${No}" value="${line.issuePrice}">
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
        
            <div class="popBtn">
            
            		<c:choose>
                       <c:when test="${stage eq 'O'}">
                         	<div>
            					<span>재고등록 사유: </span>
			 					<span><input type="text" id="registrationReason" name="registrationReason"></span>
			 					<span>
					  				<select  name="stockAble" id="w260">
                         				<option value="Y" selected>신품</option>
                         				<option value="N">불용재고</option>
                      				</select>
				
								</span>
			 				</div>
                         	
                         
                       </c:when>
                       <c:otherwise>
                          	<span>인수부서: </span>
			 				<span>
			 	 				<select name="receiverUsr" id="receiverUsr" style="width:100px;">
                    					<option value=" ">선택</option>
                        			 <c:forEach items="${deptName}" var="item">
                         				<option value="${item.id}">${item.deptName}</option>
                         			</c:forEach>
                     			</select>
			 	
			 				</span>        	
                       </c:otherwise>
                                            	
                   </c:choose>
			
			 		<c:choose>
                       <c:when test="${stage eq 'O'}">
                         <!--  <span><button class="btn_blue" type="button" id="insertBtn">재고 등록</button></span>  -->
                          <span><a href="#" class="btn_blue" id="insertBtn">재고 등록</a></span>
                       </c:when>
                       <c:otherwise>
                         <!--  <span><button class="btn_blue" type="button" id="UpdateBtn">불출 등록</button></span>  -->
                          <span><a href="#" class="btn_blue" id="UpdateBtn">불출 등록</a></span>               	
                       </c:otherwise>
                                            	
                   </c:choose>
			 	
			 	<!-- 
			 	<button class="btn_blue" type="button" id="allChex">전체선택</button>  
            	<button class="btn_blue" type="button" id="delete">선택 된 LIST 행제외</button> 
            	 -->
            	
            	<a href="#" class="btn_blue" id="allChex">전체선택</a>
              	<a href="#" class="btn_gray" id="delete">선택 된 LIST 행제외</a> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
