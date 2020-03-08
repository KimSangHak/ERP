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
	
	
	
	 $("#conformButton").click(function(){

		 	var orderNo = $("#orderNo option:selected").val();
		 	var customerId = $("#customerId option:selected").val();
		 	var partnerId = $("#partnerId option:selected").val();
	    	var roundNo = $("#roundNo").val();
	   
	    	
	        if(confirm("구매 LIST로 등록 하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/purchase/purchaseroundRobin/insert/do";
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

<div id="container">
	
	<form id="form1" name="form1" enctype="multipart/form-data" method="post">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>품의서 구매 LIST 등록</h1>
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
                        		<c:when test="${line.signR ne null}">
                            		<td id="taCenter"><img src="${line.signR}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>
                            
                            <c:choose>
                        		<c:when test="${line.signM ne null}">
                            		<td id="taCenter"><img src="${line.signM}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>
                            
                             <c:choose>
                        		<c:when test="${line.signP ne null}">
                            		<td id="taCenter"><img src="${line.signP}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>
                            
                             <c:choose>
                        		<c:when test="${line.signJ ne null}">
                            		<td id="taCenter"><img src="${line.signJ}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>
                            
                             <c:choose>
                        		<c:when test="${line.signC ne null}">
                            		<td id="taCenter"><img src="${line.signC}" alt=""/></td>
                            	</c:when>
                            	
                            	<c:otherwise>
									 <td id="taCenter">&nbsp;</td>
								</c:otherwise>
                            </c:choose>							
                            
                        </tr>
                        <tr>
                        	<th>부서</th>
                            <td colspan="7">${line.deptName}</td>
                            
                        </tr>
                        <tr>
                        	<th>직위</th>
                            <td colspan="2">${line.position}</td>
                            <th colspan="2">성명</th>
                            <td colspan="3">${line.usrName}</td>
                        </tr>
                        
                        <tr>
                        	<th>OrderNo*</th>
                        	
                            <td colspan="7">
                            	<select name="orderNo" id="orderNo" style="width:263px;">
                            		<option value="0">*선택</option>
                   					<c:forEach items="${orderNodata}" var="data">
                    					<option value="${data.id}" <c:if test="${data.id == line.jobOrderId}">selected</c:if>>${data.jobOrderNo}</option>
                   					 </c:forEach>
                 				 </select>
                            </td>
                        </tr>
                        <tr>
                        	<th>고객사*</th>
                        	
                            <td colspan="7">
                            	 <select name="customerId" id="customerId" style="width:263px;">
                   					<option value="N">*선택</option>
                   					<c:forEach items="${customerData}" var="data2">
                    					<option value="${data2.id}" <c:if test="${data2.id == line.customerId}">selected</c:if>>${data2.name}</option>
                   					 </c:forEach>
                 				 </select>
                            </td>
                        </tr>
                        
                          <tr>
                        	<th>매입처*</th>
                        	
                            <td colspan="7">
                            	 <select name="partnerId" id="partnerId" style="width:263px;">
                   					<option value="N">*선택</option>
                   					<c:forEach items="${partner}" var="data3">
                    					<option value="${data3.id}">${data3.partnerName}</option>
                   					 </c:forEach>
                 				 </select>
                            </td>
                        </tr>
                        
                        <tr>
                        	<th>title</th>
                            <td colspan="7">${line.title}</td>
                        </tr>
                        <tr>
                        	<th>품의 요청 사유</th>
                            <td colspan="7">
                            	
                            	<textarea id="reason" name="reason" rows="5">${line.requestReason}</textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
                 <div class="etcArea">
            	<p>위와 같이 품의서를 제출하오니 허락하여 주시기 바랍니다.</p>
                <p>
                	<span>신청 날짜 : <fmt:formatDate value="${line.regDate}" pattern="yyyy-MM-dd"/></span>
                	&nbsp;&nbsp;&nbsp;
                    <span>신청자 : ${line.usrName}</span>
                    <input type="hidden" id="roundNo" name="roundNo" value="${line.roundNo}">
              
                </p>
              
            </div>
            
            
            <div class="listBox">
                        	<table>              	
                        		
                             
                                		<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:15%;" />
                                		<col style="width:20%;" />
                                		<col style="width:15%;" />
                                		<col style="width:10%;" />
                                		<col style="width:15%;" />
                                		<col style="width:15%;" />              
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                			<th>품명</th>
                                        	<th>ModelNo/Size</th>
                                    		<th>Maker</th>
                                        	<th>수량</th>
                                        	<th>납기요청일</th>
                                        	<th>단가</th>                                          
                                    	</tr>
                        
                                </thead>
                                
                                
                                <tbody id="serviceTbody">
                                
                                <c:set var="sumprice" value="${pageNo}"></c:set>
                                
                  					<c:forEach items="${data2}" var="entry">
                  					
                  					<fmt:parseNumber var="unitsum" value="${entry.quantity * entry.price}" integerOnly="true"/>
                                			<tr>
                                				<td>${entry.description}</td>
                                    			<td>${entry.modelNo}</td>
                                        		<td>${entry.maker}</td>
                                        		<td>${entry.quantity}</td>
                                        		<td><fmt:formatDate value="${entry.requestDate}" pattern="yyyy-MM-dd"/></td>
												<td><fmt:formatNumber value="${entry.price}" pattern="#,###" /></td>
                                    		</tr>
                                    		
                                    		<c:set var="sumprice" value="${sumprice + unitsum}" />
 									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        
                        
                        
                       			 <div class="etcArea">
                        			</br>
                    					&nbsp;&nbsp;&nbsp;최종금액 : <fmt:formatNumber value="${sumprice}" pattern="#,###" />
                   					
                   					</br>     
            					</div>
                        

             <div class="popBtn">

                <a href="#" class="btn_gray" id="Cancle">취소</a>
          
                <a href="#" id="conformButton" class="btn_blue">구매 리스트 등록</a>
			  
               
             </div>
                
            </div>
            </div>
            </div>
            </form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
   
	




</body>
</html>