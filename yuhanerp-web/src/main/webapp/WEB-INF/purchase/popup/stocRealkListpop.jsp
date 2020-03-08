<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	

    <script language="JavaScript" src="/js/menu.js"></script>
    
    
    <script type="text/javascript">
    
    
		
    	$(document).ready(function(){
    	$("#EditBtn").click(function(){
    		
    		var id = $("#id").val();
    		var passDate = $("#passDate").val();
    		var passUser = $("#passUser option:selected").val();
    		var receiverUsr = $("#receiverUsr option:selected").val();
    		var outQuantity = $("#outQuantity").val();
    		var outReason = $("#outReason").val();
    		var orderNo = $("#orderNo option:selected").val();
    		var type = $("#type").val();
    		
        if(confirm("불출 등록 하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/stockOutReal";
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
<form id="form1" name="form1" enctype="multipart/form-data" method="post">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>불출등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:90px;" />
                        <col style="width:150px;" />
                        
                        <c:forEach items="${data}" var="partner">
                        
                         <fmt:parseNumber var="stockqty" value="${partner.quantity}" integerOnly="true"/>
                         <fmt:parseNumber var="abQt" value="${partner.abQuantity}" integerOnly="true"/>
                         <fmt:parseNumber var="outQt" value="${partner.outQuantity}" integerOnly="true"/>
                         <fmt:parseNumber var="remind" value="${stockqty-(abQt+outQt)}" integerOnly="true"/>
                        <tr>
                            <th>CodeNo.<span class="ncBlue"></span></th>
                            <td><input type="text" name="id" id="w120" value="J${partner.id}" readonly></td>
                        </tr>
                        <tr>
                            <th>Maker <span class="ncBlue"></span></th>
                            <td><input type="text" name="maker" value="${partner.maker}" readonly></td>
                        </tr>
                        <tr>
                        	<th>품명 <span class="ncBlue"></span></th>
                            <td><input type="text" name="description" value="${partner.description}" readonly></td>
                        </tr>
                        <tr>
                        	<th>모델명 <span class="ncBlue"></span></th>
                            <td><input type="text" name="modelNo" value="${partner.modelNo}" readonly></td>
                        </tr>
                        <tr>
                         	<th>불출자 <span class="ncBlue">*</span></th>
                            <td>
                            	<select name="passUser" id="passUser" style="width:100px;">
                    				<option value=" ">선택</option>
                         			<c:forEach items="${userName}" var="item">
                         				<option value="${item}">${item}</option>
                         			</c:forEach>
                     			</select>
                            </td>
                        </tr>
                        <tr>
                         	<th>수령부서 <span class="ncBlue">*</span></th>
                            <td>           
                            	<select name="receiverUsr" id="receiverUsr" style="width:100px;">
                    				<option value=" ">선택</option>
                         			<c:forEach items="${deptName}" var="item">
                         				<option value="${item}">${item}</option>
                         			</c:forEach>
                     			</select>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>불출날짜<span class="ncBlue">*</span></th>
                              <td><input type="date" id="passDate" name="passDate"></td>         
                        </tr>
                        <tr>
                            <th>잔여수량 <span class="ncBlue"></span></th>
                            <td><input type="number" name="remind" value="${remind}" readonly></td>
                        </tr>
                        
                        <tr>
                            <th>불출수량 <span class="ncBlue">*</span></th>
                            <td><input type="number" name="outQuantity"></td>
                        </tr>
                        
                        <tr>
                         	<th>사유 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="outReason"></td>
                        </tr>
                        
                         <tr>
                         	<th>사용 OrderNo <span class="ncBlue">*</span></th>
                            <td>
                            
                            	 <select name="orderNo" id="orderNo" style="width:263px;">
                   					<option value="">선택</option>
                   					<c:forEach items="${orderNodata}" var="data">
                    					<option value="${data.id}">${data.jobOrderNo}</option>
                   					 </c:forEach>
                 				 </select>
                            
                            </td>
                        </tr>
                        
                        <c:choose>
                        	
                        	<c:when test="${partner.abQuantity > 0}">
                        		
                        		  <tr>
                         			    <th>일반 불출 /대기 수량 불출 <span class="ncBlue">*</span></th>
                            			<td>           
                            				<select name="type" id="type" style="width:150px;">
                    							<option value="B">일반불출</option>
                         						<option value="A">불출대기수량에서 불출</option>
                     						</select>
                            			</td>
                        		 </tr>

                        	</c:when>
                        	
                        	<c:otherwise>
                        	
                        		<input type="hidden" id="type" name="type" value="B">
                        	
                        		
                        	
                        	</c:otherwise>
                        
                        </c:choose>
                      
                       
                        
                     
                        </c:forEach>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="EditBtn">등록</a>
                
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
