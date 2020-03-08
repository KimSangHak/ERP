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

function calDate(_date1, _date2){
	
	 	var diffDate_1 = _date1 instanceof Date ? _date1 :new Date(_date1);
	    var diffDate_2 = _date2 instanceof Date ? _date2 :new Date(_date2);
	 
	    diffDate_1 =new Date(diffDate_1.getFullYear(), diffDate_1.getMonth()+1, diffDate_1.getDate());
	    diffDate_2 =new Date(diffDate_2.getFullYear(), diffDate_2.getMonth()+1, diffDate_2.getDate());
	 
	    var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
	    diff = Math.ceil(diff / (1000 * 3600 * 24)) + 1;
	 
	    return diff;

}

	
	
$(document).ready(function(){	
	

	
	
	  $("#registerButton").click(function(){
		   
		  	
	    	
	    	
	    	var userId = $("#userId").val();
	    	var img1Path = $("#img1Path").val();
	    	var deptCode = $("#deptCode").val();
	    	var title = $("#title option:selected").val();
	    	var requestStrdate =  $("#requestStrdate").val();
	    	var requestEnddate =  $("#requestEnddate").val();
	    	var requestDate =  $("#requestDate").val();
	    	var requestReason =  $("#requestReason").val();
	    	
	    	
	    	if(title == "연차휴가" || title == "생리휴가" || title == "병가"){
	    		
	    		if (calDate(requestStrdate,requestEnddate) > requestDate || calDate(requestStrdate,requestEnddate) < requestDate) {
	            	alert("휴가 종류, 시작 날짜, 종료 날짜, 연차 사용 일 수 를 다시 확인해주세요.");
	            	event.preventDefault();
	            	requestDate.focus();
	        	}
	    	}
	    	
			if(title == "조퇴" || title == "외출" || title == "오전반차" || title == "오후반차"){
	    		
	    		if (calDate(requestStrdate,requestEnddate) != 1) {
	            	alert("조퇴/외출/반차 시에는 시작 날짜와 종료 날짜를 같게 선택해 주세요.");
	            	event.preventDefault();
	            	requestStrdate.focus();
	        	}
	    	}
			
			if(requestStrdate>requestEnddate){
				alert("시작/종료 날짜를 확인하세요.");
            	event.preventDefault();
            	requestStrdate.focus();
			}
			
			if(title == "오전반차" || title == "오후반차"){
	    		
	    		if (requestDate != 4) {
	            	alert("오전/오후 반차 시에는 연차 사용 시간을 4 로 입력해주세요.");
	            	event.preventDefault();
	            	requestDate.focus();
	        	}
	    	}
	    	
	    	if(title == ""){
	    		alert("휴가 종류를 선택해주세요.");
            	event.preventDefault();
            	title.focus();
	    	}
	    	
	    	if(requestReason == ""){
	    		alert("휴가 요청 사유를 입력해주세요.");
            	event.preventDefault();
            	requestReason.focus();
	    	}
	    	
	    	if(requestDate == ""){
	    		alert("휴가 사용 일/시간 을 입력해주세요.");
            	event.preventDefault();
            	requestDate.focus();
	    	}
	    	
	    	if(requestStrdate == ""){
	    		alert("시작날짜를 입력해주세요.");
            	event.preventDefault();
            	requestStrdate.focus();
	    	}
	    	
	    	if(requestEnddate == ""){
	    		alert("종료날짜를 입력해주세요.");
            	event.preventDefault();
            	requestStrdate.focus();
	    	}

	    	
	        if(confirm("이대로 휴가원을 등록하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/mymenu/leave/insert/do";
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
        	<h1>휴가원 신청</h1>
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
                            <th rowspan="2" colspan="2" id="taCenter" style="font-size:20px; font-weight:700;">휴 가 원</th>
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
                        	<th>휴가종류</th>
                        	
                            <td colspan="6">
                            	 <select name="title" id="title" style="width:263px;">
                            	 	<option value="">선택</option>
                   					<option value="연차휴가">연차휴가</option>
                   					<option value="생리휴가">생리휴가</option>
                   					<option value="병가">병가</option>
                   					<option value="경조휴가">경조휴가</option>
                   					<option value="공가">공가</option>
                   					<option value="조퇴">조퇴</option>
                   					<option value="외출">외출</option>
                   					<option value="오전반차">오전반차</option>
                   					<option value="오후반차">오후반차</option>
                 				 </select>
                            </td>
                        </tr>
                        
                        <tr>
                        	<th>시작 날짜</th>
                            <td colspan="6"><input type="date" id="requestStrdate" name="requestStrdate" class="w140"></td>
                        </tr>
                        
                        <tr>
                        	<th>종료 날짜</th>
                            <td colspan="6"><input type="date" id="requestEnddate" name="requestEnddate" class="w140"></td>
                        </tr>

                        <tr>
                        	<th>연차 사용 일 수</br>*(조퇴/외출 시 시간)</br>*(반차 시 4 입력)</br>*(정수만 입력)</th>
                            <td colspan="6"><input type="number" id="requestDate" name="requestDate" style="width:263px;">일 또는 (시간)
                            	</br>
                            	<b style="color:red;">*ex) 1시간 외출 -> 1</b>
                            	</br>
                            	<b style="color:red;">*ex) 오후 4시 조퇴 -> 2</b>
                            	</br>
                            	<b style="color:red;">*ex) 오전/오후 반차 -> 4</b>
                            	</br>
                            	<b style="color:red;">*ex) 연차 -> 사용 '일' 수(하루 사용 -> 1)</b>
                            
                            </td>
                        </tr>
                        <tr>
                        	<th>휴가 요청 사유</th>
                            <td colspan="6">
                            	
                            	<textarea id="requestReason" name="requestReason" rows="5"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
                 <div class="etcArea">
            	<p>위와 같이 휴가원을 제출하오니 허락하여 주시기 바랍니다.</p>
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
                <a href="#" id="registerButton" class="btn_blue">휴가원 등록</a>
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