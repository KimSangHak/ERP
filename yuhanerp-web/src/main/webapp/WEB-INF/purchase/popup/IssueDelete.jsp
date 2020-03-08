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
	    	
	    	var reason = $("#reason").val();
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	
	        
	        if(confirm("해당 사유로 발주를 취소 하시겠습니까?")){
	        	
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/purchase/deleteIssue/start";
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
        	<h1>발주 취소</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
    
         <div>
         
         
         
         			<table>
         				<tr>
                        	<th>사유</th>
                            <td colspan="50" rowspan="20">
                            	<textarea style="width:100%; height:100%;" id="reason" name="reason" rows="20" cols="50"></textarea>
                            </td>
                        </tr>
                     </table>
                     
                     <input type="hidden" id="jobPurchaseId" name="jobPurchaseId" value="${jobPurchaseId}">   
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">

            	<button class="btn_blue" type="button" id="UpdateBtn">확인</button> 
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
