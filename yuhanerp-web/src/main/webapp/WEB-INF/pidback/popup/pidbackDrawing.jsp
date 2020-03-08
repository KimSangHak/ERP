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
	  
		var Id = $("#Id").val();
		var UseQuantity = $("#UseQuantity").val();
		var No = $("#No").val();
		
		
		
		
		  $(".SubmitButton").click(function(){
			  
			  var orderNoBase = $("#orderNoBase").val();
			  var orderNoExtra = $("#orderNoExtra").val();
			  var drawingNo = $("#drawingNo").val();
			  
			  if(orderNoBase == ""){
				  orderNoBase = "Z";
		    	}
			  
			  if(orderNoExtra == ""){
				  orderNoExtra = "Z";
		    	}
			  
			  if(drawingNo == ""){
				  drawingNo = "Z";
		    	}
              
			  var url = '/pidback/pidbackInsD/repopup/' + orderNoBase +'/'+ orderNoExtra +'/'+ drawingNo;
			  
			  location.href = url;
   			  
		    });

		
	    $(".UpdateBtn").click(function(){
	    	
	    	var trid = $(this).closest('tr').attr('id');
	    	
	    	
	    	
	    	
	    	window.opener.document.getElementById("pid").value = $("#Id"+trid).val();
	        window.opener.document.getElementById("kind").value = "D";
	        window.opener.document.getElementById("kindPidback").value = "도면";
	        window.opener.document.getElementById("number").value = $("#designDrawingNo"+trid).val();
	        window.opener.document.getElementById("orderId").value = $("#orderId"+trid).val();
	        self.close();
	      
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
        	<h1>발주</h1>
        </div>
        <!-- //팝업 타이틀 -->
        
        
       			 <div class="searchArea">
        
       				 <div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:12%;;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                
                                
                                	<th>도면 번호</th>
                                    <td>
            
                                    	<c:choose>
                                    		<c:when test="${orderNoBase eq 'Z'}">
                                    			<input type="text" id="orderNoBase" name="orderNoBase" value="" class="w140" placeholder="OrderNo"> -
                                    		</c:when>
                                    		<c:otherwise>
                  								<input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w140" placeholder="OrderNo"> -                  		
                                    		</c:otherwise>
                                    	</c:choose>
                                    	
                                    	<c:choose>
                                    		<c:when test="${orderNoExtra eq 'Z'}">
                                    			<input type="text" id="orderNoExtra" name="orderNoExtra" value="" class="w140" placeholder="AS 번호"> -
                                    		</c:when>
                                    		<c:otherwise>
                  								<input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra}" class="w140" placeholder="AS 번호"> -                  		
                                    		</c:otherwise>
                                    	</c:choose>
                                    	
                                    	<c:choose>
                                    		<c:when test="${drawingNo eq 'Z'}">
                                    			<input type="text" id="drawingNo" name="drawingNo" value="" class="w140" placeholder="도면 번호">
                                    		</c:when>
                                    		<c:otherwise>
                  								<input type="text" id="drawingNo" name="drawingNo" value="${drawingNo}" class="w140" placeholder="도면 번호">                  		
                                    		</c:otherwise>
                                    	</c:choose>
                                        
                                    </td>
                                    
                                   
                                   
                                    
                                </tr>
                            </table>
                        </div>
        
        				
                          <div class="searchBtn">
                          		<a href="#" class="btn_blue SubmitButton">검색</a>
                          </div>
                          
       			 </div>
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
                                        <col style="width:5%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:15%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:10%;" />
                                        </colgroup>
                                        <tr>
                                            <th>Seq</th>
                                            <th>도면번호</th>
                                            <th>OrderNo</th>
                                            <th>Device</th>
                                            <th>품명</th>                  
                                            <th>구매수량</th>
                                            <th>spare</th>
                                            <th>선택</th>                                
                                            
                                        </tr>
                                        <c:set var="SNo" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${data}" var="line">
                                        
                                        <tr id="${SNo}">
                                        
                                            <td><input type="text" id="Id${SNo}" name="Id" class="Id${SNo}" value="${line.id}" readonly></td>
                                            <td><input type="text" id="designDrawingNo${SNo}" name="designDrawingNo" class="designDrawingNo${SNo}" value="${line.designDrawingNo}" readonly></td>
                                            <td>${line.jobOrderNo}</td>
                                            <td>${line.device}</td>
                                            <td>${line.description}</td>
                                            <td>${line.quantity}</td>
                                            <td>${line.spare}</td>                              
                                            <td><a href="#" class="btn_gray UpdateBtn">선택</a></td>
                                          
                                  
                                        </tr>
                                        <input type="hidden" id="orderId${SNo}" name="orderId" value="${line.orderId}">
                                        <c:set var="SNo" value="${SNo + 1 }" />
                                        
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
            
            	

            	<!--  <button class="btn_blue" type="button" id="UpdateBtn">확인</button> -->
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
