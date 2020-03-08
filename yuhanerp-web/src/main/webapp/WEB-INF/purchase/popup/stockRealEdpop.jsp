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
    		
    		var maker = $("#maker").val();
    		var description = $("#description").val();
    		var modelNo = $("#modelNo").val();
    		var remind = $("#remind").val();
    		var issuePrice = $("#issuePrice").val();
    		
        if(confirm("재고 List를 수정 하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/stockEdReal";
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
        	<h1>재고수정</h1>
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
                            <td><input type="text" name="maker" value="${partner.maker}"></td>
                        </tr>
                        <tr>
                        	<th>품명 <span class="ncBlue"></span></th>
                            <td><input type="text" name="description" value="${partner.description}"></td>
                        </tr>
                        <tr>
                        	<th>모델명 <span class="ncBlue"></span></th>
                            <td><input type="text" name="modelNo" value="${partner.modelNo}"></td>
                        </tr>

                        <tr>
                            <th>잔여수량 <span class="ncBlue"></span></th>
                            <td><input type="number" name="remind" value="${remind}"></td>
                        </tr>
                        
                        <tr>
                            <th>단가 <span class="ncBlue"></span></th>
                            <td><input type="number" name="issuePrice" value="${partner.issuePrice}"></td>
                        </tr>

                        </c:forEach>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="EditBtn">수정</a>
                
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
