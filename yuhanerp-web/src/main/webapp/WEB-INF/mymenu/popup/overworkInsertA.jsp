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
		   
		  	
	    	
	    	
	    	var userId = $("#userId").val();
	    	var img1Path = $("#img1Path").val();
	    	var deptCode = $("#deptCode").val();
	    	var title = $("#title").val();
	    	var requestDate =  $("#requestDate").val();
	    	var endhour =  $("#endhour").val();
	    	var endmin =  $("#endmin").val();
	    	var requestReason =  $("#requestReason").val();

	    	
	        if(confirm("이대로 연장근무를 신청하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/mymenu/overworkA/insert/do";
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
        	<h1>연장근무 신청</h1>
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
                        
                        <tr>
                            <th rowspan="2" colspan="2" id="taCenter" style="font-size:20px; font-weight:700;">연장 근무 신청서</th>
                            <th rowspan="2">결제</td>
                            <th id="taCenter">담당</th>
                            <th id="taCenter">팀장</th>
                            <th id="taCenter">이사/공장장</th>
                            <th id="taCenter">대표이사</th>
                            
                        </tr>
                        <tr>
                        	<c:choose>
                        		<c:when test="${signPath ne null}">
                            		<td id="taCenter"><img src="${signPath}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>	
                            
                            <td id="taCenter">&nbsp;</td>
                            <td id="taCenter">&nbsp;</td>
                            <td id="taCenter">&nbsp;</td>
                            
                        </tr>
                        <tr>
                        	<th>부서</th>
                            <td colspan="6">${line.deptName}</td>
                            
                        </tr>
                        <tr>
                        	<th>직위</th>
                            <td colspan="2">${line.position}</td>
                            <th colspan="2">성명</th>
                            <td colspan="2">${line.name}</td>
                        </tr>
                        
                        
                        <tr>
                        	<th>title</th>
                            <td colspan="6"><input type="text" id="title" name="title" style="width:263px;"></td>
                        </tr>
                        
                    
                        <tr>
                        	<th>근무 일자</th>
                            <td colspan="6"><input type="date" id="requestDate" name="requestDate" class="w140"></td>
                        </tr>
                        
                        <tr>
                        	<th>시작 시간</th>
                            <td colspan="6">22:00</td>
                        </tr>
                        
                        <tr>
                        	<th>종료 시간</br>*(24시간계 입력)</br>*두자리 입력(공란0)</br><b>*숫자만 입력</b></th>
                            <td colspan="6">
                            	<input type="text" id="endhour" name="endhour" style="width:130px;">
                            	&nbsp;:&nbsp;
                            	<input type="text" id="endmin" name="endmin" style="width:130px;">
                            	</br>
                            	<b style="color:red;">*ex) 새벽 0시 8분 -> 24 : 08</b>
                            	</br>
                            	<b style="color:red;">*ex) 새벽  1시 8분 -> 01 : 08</b>
                            </td>
                        </tr>

                     
                        <tr>
                        	<th>사유</br>*장비번호, 업무내용</br>*상세내용 입력</th>
                            <td colspan="6">
                            	<textarea id="requestReason" name="requestReason" rows="5"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
                 <div class="etcArea">
            	<p>위와 같이 연장근무를 신청하오니 허락하여 주시기 바랍니다.</p>
                <p>
                	<span>신청 날짜 : <input type="text" class="w100" value="${today}" readonly>&nbsp;</span>
                    <span>신청자 : <input type="text" class="w100" value="${line.name}" readonly></span>
                    <input type="hidden" id="userId" name="userId" value="${line.id}">
                    <input type="hidden" id="img1Path" name="img1Path" value="${signPath}">
                    <input type="hidden" id="deptCode" name="deptCode" value="${line.deptCode}">
                </p>
              
            </div>
            
            
            
            
           
             <div class="popBtn">

                <a href="#" class="btn_gray" id="Cancle">취소</a>
                <a href="#" id="registerButton" class="btn_blue">연장근무 신청</a>
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