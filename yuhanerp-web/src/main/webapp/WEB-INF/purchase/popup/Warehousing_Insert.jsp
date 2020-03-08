<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/jquery.form.min.js"></script>
    
    <script type="text/javascript" src="/js/menu.js"></script>
    
     <script type="text/javascript">
    
   
    	$(document).ready(function(){
    	$("#InsertBtn").click(function(){
    		
    		var BuyNum = $("#BuyNum").val();
    		var warehousing_quantity = $("#warehousing_quantity").val();
    		var out_quantity = $("#out_quantity").val();
    		
        if(confirm("입고품을 등록하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/Warehousing_ins/pop/ins";
        	document.form1.submit();
        	self.close();
        	
        }
    
       
    });
    
    $("#escBtn").click(function(){
   
    	self.close();
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
        	<h1>구매품 입고 처리</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>발주 품목 정보</h2>
                
            </div>
            
           
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                	
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:70px;" />
                        <col style="width:60px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:110px;" />
                        <col style="width:110px;" />
                        <col style="width:100px;" />
                        <col style="width:110px;" />
                        <col style="width:50px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        <col style="width:40px;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>Order No.</th>
                                <th>발주번호</th>
                                <th>UNIT</th>
                                <th>Description</th>
                                <th>Model No./Size</th>
                                <th>Maker</th>
                                <th>구매처</th>
                                <th>구매수량</th>
                                <th>발주수량</th>
                                <th>재고수량</th>
                                <th>납기일</th>
                                <th>입고일</th>
                                <th>입고수량</th>
                                <th>반품수량</th>
                                <th>상태</th>
                                <th>반품처리</th>
                            </tr>
                        </thead>
                          
                         
                        
                        <c:forEach items="${data}" var="data">
                        <tbody>
                            <tr>
                                <td><input type="text" id="job_order_no" name="job_order_no" value="${data.job_order_no}" readonly></td>
                                <td><input type="text" id="BuyNum" name="BuyNum" value="${id}" readonly></td>
                                <td><input type="text" id="unit_no" name="unit_no" value="${data.unit_no }" readonly></td>
                                <td><input type="text" id="description" name="description" value="${data.description}" readonly></td>
                                <td><input type="text" id="model_no" name="model_no" value="${data.model_no}" readonly></td>
                                <td><input type="text" id="maker" name="maker" value="${data.maker}" readonly></td>
                                <td><input type="text" id="partnerName" name="partnerName" value="${data.partnerName}" readonly></td>
                                <td>구매수량</td>
                                <td>${data.quantity }</td>
                                <td>재고수량</td>
                                <td>납기일</td>
                                <td>입고일</td>
                                <td><input type="number" id="warehousing_quantity" name="warehousing_quantity" value=${data.quantity }></td>
                                <td><input type="number" id="out_quantity" name="out_quantity" value=0 readonly></td>
                                 <c:choose>
                         			<c:when test="${data.stage eq 'R'}">
                            			<td>구매등록</td>
                             		</c:when>
                             		<c:when test="${data.stage eq 'E'}">
                                		<td>견적요청</td>
                             		</c:when>
                             		<c:when test="${data.stage eq 'P'}">
                                		<td>구매요청</td>
                             		</c:when>
                             		<c:when test="${data.stage eq 'F'}">
                                		<td>입고완료</td>
                             		</c:when>
                         		</c:choose>
                                <td>
                                <a href="javascript:PopWin('/purchase/','600','410','no');" class="btn_line_gray">반품</a></td>
                            </tr>
                        </tbody>
                       
                        </c:forEach>
                    </table>
                   
                </div>
                <!-- //게시판 -->
            
            </div>
       
                </div>
                
            </div>
            
               <div class="popBtn">
         		<a href="#" class="btn_blue" id="InsertBtn">입고품 등록</a>
                <a href="#" class="btn_gray" id="escBtn">취소</a>    
        	   </div>
            </form>
            
        </div>
        
        
    
            <!-- //테이블 콘텐츠 -->
        
   


</body>
</html>
