<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<base href="/" />
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

	  $("#registerButton").click(function(){
		   
		  	
	    	
	    	
	    	var orderId = $("#orderId").val();
	    	var pid = $("#pid").val();
	    	var userId = $("#userId").val();
	    	var deptCode = $("#deptCode").val();
	    	var title =  $("#title").val();
			var body =  $("#body").val();
			var kind =  $("#kind").val();
	    	
	    	
			
			  if (pid == "") {
		            alert("구매품/도면 을 선택해주세요.");
		            return;
		      }
			  
			  if (pid == "") {
		            alert("구매품/도면 을 선택해주세요.");
		            return;
		      }
			  
		
	        if(confirm("이대로 피드백을 등록하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/pidback/insertDo";
	        	document.form1.submit();
	        	self.close();
	        }
	    });
	  
	  $("#Cancle").click(function(){
		   
	    	self.close();
	    });
	
});	



</script>


</head>

<body class="sub">
<c:forEach items="${data}" var="line">
<div id="container">
	
	<form id="form1" name="form1" enctype="multipart/form-data" method="post">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>피드백 작성</h1>
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
                        <col style="width:150px;" />
                        <col style="width:100px;" />
                        <col style="width:50px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        
               
                        <tr>
                        	<th>부서</th>
                            <td colspan="7">${line.deptName}</td>
                            
                        </tr>
                        <tr>
                        	<th>직위</th>
                            <td colspan="2">${line.position}</td>
                            <th colspan="2">성명</th>
                            <td colspan="3">${line.name}</td>
                        </tr>
                        
                        
                        
                        
                  
                        
                          <tr>
                        	<th>선택</th>
                        	
                            <td colspan="7">
                            	 <a href="javascript:PopWin('${path}/pidback/pidbackInsP/repopup/---/---','1500','1500','no');" class="btn_blue" id="purcahseBtn">구매품 선택</a>&nbsp;&nbsp;
                            	 <a href="javascript:PopWin('${path}/pidback/pidbackInsD/repopup/Z/Z/Z','1500','1500','no');" class="btn_blue" id="drawingBtn">도면 선택</a>
                            </td>
                        </tr>
                        
                        
                        <tr>
                        	<th>종류</th>
                            <td colspan="7"><input type="text" id="kindPidback" name="kindPidback" value="" readonly></td>
                            
                        </tr>
                        
                         <tr>
                        	<th>구매/도면 번호</th>
                            <td colspan="7"><input type="text" id="number" name="number" value="" readonly></td>
                            
                        </tr>
                        <!-- 
                        <tr>
                        	<th>OrderNo</th>
                        	
                            <td colspan="7">
                            	 <select name="orderNo" id="orderNo" style="width:263px;">
                   					<option value="0">선택</option>
                   					<c:forEach items="${orderNodata}" var="data">
                    					<option value="${data.id}">${data.jobOrderNo}</option>
                   					 </c:forEach>
                 				 </select>
                            </td>
                        </tr>
                        -->
                       
                        <tr>
                        	<th>title</th>
                            <td colspan="7"><input type="text" id="title" name="title" style="width:263px;"></td>
                        </tr>
                        <tr>
                        	<th>피드백 내용</th>
                            <td colspan="7">
                            	
                            	<textarea id="body" name="body" rows="10"></textarea>
                            </td>
                        </tr>
                    </table>
                    
                    <input type="hidden" id="pid" name="pid" value="">
                    <input type="hidden" id="kind" name="kind" value="">
                    <input type="hidden" id="orderId" name="orderId" value="">
                    <input type="hidden" id="userId" name="userId" value="${line.id}">
                    <input type="hidden" id="deptCode" name="deptCode" value="${line.deptCode}">
                </div>
                <!-- //게시판 -->
                
            	
            
            
            
             <div class="popBtn">

                <a href="#" class="btn_gray" id="Cancle">취소</a>
                <a href="#" id="registerButton" class="btn_blue">피드백 등록</a>
             </div>
                
            </div>
            </div>
            </div>
            </form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
   
	

</c:forEach>


</body>
</html>