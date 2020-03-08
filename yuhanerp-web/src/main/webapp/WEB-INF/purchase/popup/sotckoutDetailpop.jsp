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
    		
    		var stockID = $("#stockID").val();
    		var registrationReason = $("#registrationReason").val();
    		var stockAble = $("#stockAble option:selected").val();
    		var inQuantity = $("#inQuantity").val();
    	
    		
        if(confirm("해당 내용으로 재입고 하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/restockinner";
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
        	<h1>불출상세 및 재입고 등록</h1>
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
                        
                       
                        <tr>
                         	<th>사용 OrderNo <span class="ncBlue"></span></th>
                            <td><input type="text" name="orderNo" value="${partner.orderNo}" readonly></td>
                        </tr>
                       
                        <tr>
                            <th>불출날짜<span class="ncBlue"></span></th>
                            <td><input type="date" id="passDate" name="passDate" 
           						value="<fmt:formatDate value="${partner.passDate}" pattern="yyyy-MM-dd"/>" readonly></td>  
                        </tr>
                        <tr>
                            <th>인계자 <span class="ncBlue"></span></th>
                            <td><input type="text" name="pusr" value="${pusr}" readonly></td>
                        </tr>
                         <tr>
                            <th>인수부서<span class="ncBlue"></span></th>
                            <td><input type="text" name="deptName" value="${deptName}" readonly></td>
                        </tr>
                        <tr>
                            <th>인수자 <span class="ncBlue"></span></th>
                            <td><input type="text" name="rusr" value="${rusr}" readonly></td>
                        </tr>
                       <tr>
                            <th>불출수량 <span class="ncBlue"></span></th>
                            <td><input type="number" name="outQuantity" value="${partner.outQuantity}" readonly></td>
                        </tr>
                       
                       
                      
                        <tr>
                         	<th>불출사유 <span class="ncBlue"></span></th>
                            <td><input type="text" name="outReason" value="${partner.outReason}" readonly></td>
                        </tr>
                        
                        <tr>
                            <th>재입고수량 <span class="ncBlue">*</span></th>
                            <td><input type="number" name="inQuantity"></td>
                        </tr>
                        
                        <tr>
                         	<th>재입고사유 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="registrationReason" placeholder="재입고시 해당 칸에 꼭 사유를 명시해주세요."></td>
                        </tr>
                        <tr>
                        	<th>재고 신품 불용 여부 <span class="ncBlue">*</span></th>
                        	<td>
                        		<select  name="stockAble" id="w260">
                         			<option value="Y" selected>신품</option>
                         			<option value="N">불용재고</option>
                      			</select>
                        	</td>
                        </tr>
                        
                     	<input type="hidden" id="id" name="id"  value="${partner.id}">
                        <input type="hidden" id="stockID" name="stockID"  value="${partner.stockID}">
                        <input type="hidden" id="passUser" name="passUser"  value="${partner.passUser}">
                        <input type="hidden" id="receiverUsr" name="id"  value="${partner.receiverUsr}">
                     
                        </c:forEach>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="EditBtn">재입고</a>
                
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
