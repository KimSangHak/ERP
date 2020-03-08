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

function addRow(){
	 
	   mytable = document.getElementById('serviceTbody');  //행을 추가할 테이블
	   row = mytable.insertRow(mytable.rows.length);  //추가할 행

	   
	   cell1 = row.insertCell(0);
	   cell2 = row.insertCell(1);  //실제 행 추가 여기서의 숫자는 컬럼 수
	   cell3 = row.insertCell(2);
	   cell4 = row.insertCell(3);
	   cell5 = row.insertCell(4);
		
	   cell1.innerHTML = "<td><input type='text' id='description'  name='description' value='없음'></td>";
	   cell2.innerHTML = "<td><input type='text' id='modelNo'  name='modelNo' value='없음'></td>"; //추가한 행에 원하는  요소추가
	   cell3.innerHTML = "<td><input type='text' id='maker'  name='maker' value='없음'></td>";
	   cell4.innerHTML = "<td><input type='number' id='quantity' name='quantity' value = 1></td>";
	   cell5.innerHTML = "<td><input type='date' id='requestDate' name='requestDate' class='w140'></td>";
	}
	
	

	

	

$(document).ready(function(){	
	
	$('#btn-delete-row').click(function(e) {
		e.preventDefault();
    	
		$('#serviceTbody > tr:last').remove();
  	});
	
	
	  $("#registerButton").click(function(){
		   
		  	
	    	
	    	
	    	var userId = $("#userId").val();
	    	var img1Path = $("#img1Path").val();
	    	var deptCode = $("#deptCode").val();
	    	var orderNo = $("#orderNo option:selected").val();
	    	var customerId = $("#customerId option:selected").val();
	    	var title =  $("#title").val();
			var reason =  $("#reason").val();
			
			var description =  $("#description").val();
			var modelNo =  $("#modelNo").val();
			var maker =  $("#maker").val();
			var quantity = $("#quantity").val();
			var requestDate =  $("#requestDate").val();
			
			var noOrderNo = $("#noOrderNo").prop("checked");
			
			
			
			
			  if (orderNo == 0 && noOrderNo == false) {
		            alert("OrderNo를 선택 혹은, 소모품 여부 체크를 해주세요.");
		            event.preventDefault();
		            return;
		        }
			  
			  if (orderNo == 77 && noOrderNo == false) {
		            alert("OrderNo를 선택 혹은, 소모품 여부 체크를 해주세요.");
		            event.preventDefault();
		            return;
		        }
			
			  if(title == ""){
		    		alert("title를 입력해주세요.");
	            	event.preventDefault();
	            	title.focus();
		    	}
			  
			  if(reason == ""){
		    		alert("요청 사유를 입력해주세요.");
	            	event.preventDefault();
	            	reason.focus();
		     	}
			  if(requestDate == ""){
				  alert("납기 요청일을 확인해 주세요.");
	              event.preventDefault();
	              return;
			  }

	        if(confirm("이대로 구매품의서를 등록하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/mymenu/roundRobin/insert/do";
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
        	<h1>구매 품의서 신청</h1>
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
                            <th rowspan="2" colspan="2" id="taCenter" style="font-size:20px; font-weight:700;">구매 품의서</th>
                            <th rowspan="2">결제</td>
                            <th id="taCenter">담당</th>
                            <th id="taCenter">팀장</th>
                            <th id="taCenter">구매부</th>
                            <th id="taCenter">재무이사</th>
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
                            <td id="taCenter">&nbsp;</td>
                            
                        </tr>
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
                        	<th>OrderNo</th>
                        	
                            <td colspan="7">
                            	 <select name="orderNo" id="orderNo" style="width:263px;">
                   					<option value="0">선택</option>
                   					<c:forEach items="${orderNodata}" var="data">
                    					<option value="${data.id}">${data.jobOrderNo}</option>
                   					 </c:forEach>
                 				 </select>
                 				 
                 				 &nbsp;&nbsp;<input type="checkbox" id="noOrderNo"> 소모품 체크(OrderNo 없음)
                            </td>
                        </tr>
                        <tr>
                        	<th>고객사</th>
                        	
                            <td colspan="7">
                            	 <select name="customerId" id="customerId" style="width:263px;">
                   					<option value="ZZ">고객사 없음</option>
                   					<c:forEach items="${customerData}" var="data2">
                    					<option value="${data2.id}">${data2.name}</option>
                   					 </c:forEach>
                 				 </select>
                            </td>
                        </tr>
                        <tr>
                        	<th>title</th>
                            <td colspan="7"><input type="text" id="title" name="title" style="width:263px;"></td>
                        </tr>
                        <tr>
                        	<th>품의 요청 사유</th>
                            <td colspan="7">
                            	
                            	<textarea id="reason" name="reason" rows="5"></textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
                 <div class="etcArea">
            	<p>위와 같이 품의서를 제출하오니 허락하여 주시기 바랍니다.</p>
                <p>
                	<span>신청 날짜 : <input type="text" class="w100" value="${today}" readonly>&nbsp;</span>
                    <span>신청자 : <input type="text" class="w100" value="${line.name}" readonly></span>
                    <input type="hidden" id="userId" name="userId" value="${line.id}">
                    <input type="hidden" id="img1Path" name="img1Path" value="${signPath}">
                    <input type="hidden" id="deptCode" name="deptCode" value="${line.deptCode}">
                    
                    			</br>
                            	<b style="color:red;">*반드시 공란 없이 채워주세요.</b>
                            	</br>
                            	<b style="color:red;">*반드시 OrderNo가 있을 경우 선택해주세요. </b>
                            	</br>
                            	<b style="color:red;">*ex) Maker 없다면 -> '없음' 입력</b>
                            	</br>
                            	<b style="color:red;">*수량은 반드시 정수로만 입력해주세요.</b>
                            	</br>
                            	<b style="color:red;">*ex) 1 box -> 1 또는 개별 갯수 </b>
                            	
                </p>
              
            </div>
            
            
            <div class="listBox">
                        	<table>              	
                        		
                             
                                		<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:15%;" />
                                		<col style="width:15%;" />
                                		<col style="width:25%;" />
                                		<col style="width:15%;" />
                                		<col style="width:20%;" />                 
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                			<th>품명</th>
                                        	<th>ModelNo/Size</th>
                                    		<th>Maker</th>
                                        	<th>수량</th>
                                        	<th>납기요청일</th>                                        
                                    	</tr>
                        
                                </thead>
                                
                                <tbody id="serviceTbody">
                                
                  
                                			<tr>
                                				<td><input type="text" id="description"  name="description" value="없음"></td>
                                    			<td><input type="text" id="modelNo"  name="modelNo" value="없음"></td>
                                        		<td><input type="text" id="maker"  name="maker" value="없음"></td>
                                        		<td><input type="number" id="quantity" name="quantity" value=1></td>
                                        		<td><input type="date" id="requestDate" name="requestDate" class="w140"></td>
                                     
                                        	
                                    		</tr>
 
                                </tbody>
                            </table>
                        </div>
            
             <div class="popBtn">
             
             	<button type="button" onclick="javascript:addRow()" class="gray">행 추가</button>
             	<button id='btn-delete-row'>행 삭제</button>
            
             </div>
             <div class="popBtn">

                <a href="#" class="btn_gray" id="Cancle">취소</a>
                <a href="#" id="registerButton" class="btn_blue">구매 품의서 등록</a>
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