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
			  
			  var modelNo = $("#modelNo").val();
              
			  var url = '/purchase/stockUse/' + modelNo +'/'+ No;
			  
			  location.href = url;
   			  
		    });

		
	    $(".UpdateBtn").click(function(){
	    	
	    	var trid = $(this).closest('tr').attr('id');
	    	
	    	var orderQty = opener.document.getElementById("orderQuantity"+No).value;
	    	
	    	
	    	
	        if(confirm("재고사용을 하시겠습니까?")){
	        	
	        	window.opener.document.getElementById("stockUseQuantity"+No).value = $("#UseQuantity"+trid).val();
	        	window.opener.document.getElementById("stockId"+No).value = $("#Id"+trid).val();
	        	window.opener.document.getElementById("orderQuantity"+No).value = orderQty - $("#UseQuantity"+trid).val();
	        	
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
                                    
                                    <th>Model No.</th>
                                    <td>
                                        <input type="text" id="modelNo" name="modelNo" value="${modelNo}" class="w130">
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
                                        <col style="width:10%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        </colgroup>
                                        <tr>
                                            <th>재고번호</th>
                                            <th>Description</th>
                                            <th>Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>재고수량</th>
                                            <th>재고사용수량</th>
                                            <th>재고사용</th>
                                            
                                        </tr>
                                        <c:set var="SNo" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${data}" var="line">
                                         <fmt:parseNumber var="Qty" value="${line.quantity}" integerOnly="true"/>
                                         <fmt:parseNumber var="abQty" value="${line.abQuantity}" integerOnly="true"/>
                                         <fmt:parseNumber var="outQty" value="${line.outQuantity}" integerOnly="true"/>
                                         <fmt:parseNumber var="Key" value="${Qty-(abQty+outQty)}" integerOnly="true"/>
                                        <tr id="${SNo}">
                                        
                                            <td><input type="text" id="Id${SNo}" name="Id" class="Id${SNo}" value="J${line.id}" readonly></td>
                                            <td>${line.description}</td>
                                            <td>${line.modelNo}</td>
                                            <td>${line.maker}</td>
                                            <td>${Key}</td>
                                            <td><input type="number" min="0" max=${Key} id="UseQuantity${SNo}" class="UseQuantity${SNo}" name="UseQuantity"></td>
                                            <td><a href="#" class="btn_gray UpdateBtn">재고사용</a></td>
                                          
                                  
                                        </tr>
                                        <c:set var="SNo" value="${SNo + 1 }" />
                                        </c:forEach>
                                    </table>
                                    
               				<input type="hidden" id="No" name="No" value="${No}">
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
