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
	
	
	
	  $("#deleteButton").click(function(){
		   
		  	
	    	
	    	
	    	var deleteReason = $("#deleteReason").val();
	    	var id = $("#id").val();
	    
		
	    	
	        if(confirm("휴가원을 취소 하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/mymenu/leave/delte/do";
	        	document.form1.submit();
	        	self.close();
	        }
	    });
	  
	  $("#changeBtn").click(function(){
		   

	    	var id = $("#id").val();
	    
	    	
	        if(confirm("휴가원 변경 신청을 하시겠습니까?")){
	        	
	        	
	        	event.preventDefault();
	        	location.href  = "/mymenu/leave/changePop/" + id;
	
	        	//document.form1.action = "/mymenu/leave/changePop";
	        	//document.form1.submit();

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
                            <td colspan="6">${line.deptName}</td>
                            
                        </tr>
                        <tr>
                        	<th>직위</th>
                            <td colspan="2">${line.position}</td>
                            <th colspan="2">성명</th>
                            <td colspan="2">${line.usrName}</td>
                        </tr>
                        
                        <tr>
                        	<th>휴가종류</th>
                        	
                            <td colspan="6">
                            	${line.title}
                            </td>
                        </tr>
                        <tr>
                        	<th>시작 날짜</th>
                        	
                            <td colspan="6">
                            	<fmt:formatDate value="${line.requestStrdate}" pattern="yyyy-MM-dd"/>
                            </td>
                        </tr>
                          <tr>
                        	<th>종료 날짜</th>
                        	
                            <td colspan="6">
                            	<fmt:formatDate value="${line.requestEnddate}" pattern="yyyy-MM-dd"/>
                            </td>
                        </tr>
                        <c:choose>
                        	<c:when test="${line.kind eq 'C'}">
                        		 <tr>
                        			<th>연차 사용 *시간</th>
                            		<td colspan="6">${line.requestHour} 시간</td>
                       			 </tr>
                        	</c:when>
                        	<c:otherwise>	
                        		 <tr>
                        			<th>연차 사용 *일 수</th>
                            		<td colspan="6">${line.requestDate} 일</td>
                       			 </tr>
                        	</c:otherwise>
                        </c:choose>

                        <tr>
                        	<th>휴가 요청 사유</th>
                            <td colspan="6">
                            	
                            	<textarea id="reason" name="reason" rows="5">${line.requestReason}</textarea>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
                 <div class="etcArea">
            	<p>위와 같이 휴가원을 제출하오니 허락하여 주시기 바랍니다.</p>
                <p>
                	<span>신청 날짜 : <fmt:formatDate value="${line.regDate}" pattern="yyyy-MM-dd"/></span>
                	&nbsp;&nbsp;&nbsp;
                    <span>신청자 : ${line.usrName}</span>
                    <input type="hidden" id="id" name="id" value="${line.id}">
              
                </p>
              
            </div>
             
                       <c:choose>
                        	<c:when test="${isOwn eq 'Y'}">
                        	
                        		<c:choose>
                        			<c:when test="${line.conformStage eq 'R'}">
                        				<c:choose>
                        				
                        					<c:when test="${line.deleted eq 'N'}">
                 								 <div class="etcArea">
                    								취소사유 :     
                   									<textarea id="deleteReason" name="deleteReason" rows="5" placeholder="휴가원 취소시 사유를 입력해주세요." style="width:263px;"></textarea>     
            									</div>
                 							</c:when>
                 							<c:otherwise>
                 			
                 							</c:otherwise>
                        				</c:choose>
                        			
                        			</c:when>
                        			
                        			<c:when test="${line.conformStage eq 'M' && isManager eq 'Y'}">
                 						<c:choose>
                 							<c:when test="${line.deleted eq 'N'}">
                 								취소사유 :     
                   								<textarea id="deleteReason" name="deleteReason" rows="5" placeholder="휴가원 취소시 사유를 입력해주세요." style="width:263px;"></textarea>
                 							</c:when>
                 							<c:otherwise>
                 			
                 							</c:otherwise>
                 						</c:choose>
                 					</c:when>
                 					
                 					<c:when test="${line.conformStage eq 'J' && isManager eq 'Y'}">
                 						<c:choose>
                 							<c:when test="${line.deleted eq 'N'}">
                 								취소사유 :     
                   								<textarea id="deleteReason" name="deleteReason" rows="5" placeholder="휴가원 취소시 사유를 입력해주세요." style="width:263px;"></textarea>
                 							</c:when>
                 							<c:otherwise>
                 			
                 							</c:otherwise>
                 						</c:choose>
                 					</c:when>
                 					
                 					<c:otherwise>
 					
 									</c:otherwise>  
                        			
                 				</c:choose>
                            	
                            </c:when>
                            	
                            <c:otherwise>
								
							</c:otherwise>
                        </c:choose>			  
                
         
             <div class="popBtn">

                <a href="#" class="btn_gray" id="Cancle">취소</a>
                
                <c:choose>
                	<c:when test="${isOwn eq 'Y'}">
                		<c:choose>
                 			<c:when test="${line.conformStage eq 'R'}">
                 				<c:choose>
                 					<c:when test="${line.deleted eq 'N'}">
                 						<a href="#" id="deleteButton" class="btn_blue">휴가원 취소</a>
                 					</c:when>
                 					<c:otherwise>
                 			
                 					</c:otherwise>
                 				</c:choose>
                 			</c:when>
                 			
                 			<c:when test="${line.conformStage eq 'M' && isManager eq 'Y'}">
                 				<c:choose>
                 					<c:when test="${line.deleted eq 'N'}">
                 						<a href="#" id="deleteButton" class="btn_blue">휴가원 취소</a>
                 					</c:when>
                 					<c:otherwise>
                 			
                 					</c:otherwise>
                 				</c:choose>
                 			</c:when>
                 			
                 			<c:when test="${line.conformStage eq 'J' && isManager eq 'Y'}">
                 				<c:choose>
                 					<c:when test="${line.deleted eq 'N'}">
                 						<a href="#" id="deleteButton" class="btn_blue">휴가원 취소</a>
                 					</c:when>
                 					<c:otherwise>
                 			
                 					</c:otherwise>
                 				</c:choose>
                 			</c:when>
                            	
                    		<c:otherwise>
								<a href="#" class="btn_blue">휴가원 취소 불가</a>
							</c:otherwise>
                 		</c:choose>			  
                	
                	</c:when>
 					<c:otherwise>
 					
 					</c:otherwise>               
                
                </c:choose>
                <c:choose>
                	<c:when test="${isUpdate eq 'Y' && line.deleted eq 'N'}">
                 		<a href="#" id="changeBtn" class="btn_blue">휴가 변경 신청</a>
                 	</c:when>
                 	<c:when test="${isUpdate eq 'N' && line.deleted eq 'N'}">
                 		<a href="#" class="btn_blue">휴가 변경 불가</a>
                 	</c:when>
                 	<c:otherwise>
 						
 					</c:otherwise>
                	
                
                </c:choose>
                
                
               
             </div>
                
            </div>
            </div>
            </div>
            </form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
   
	




</body>
</html>