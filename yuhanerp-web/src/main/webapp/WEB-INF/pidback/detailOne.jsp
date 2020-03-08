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

	
	$("#updatePid").click(function(e){

    	var id = $("#id").val();
    	var body = $("#body").val();
		var title = $("#title").val();

        if(confirm("피드백을 수정 하시겠습니까?")){
        	
        	e.preventDefault();
        	document.form1.action = "${path}/pidback/configPidback/do";
        	document.form1.submit();

        }
    });
	
	$("#deletePid").click(function(e){

    	var id = $("#id").val();
    
        if(confirm("피드백을 삭제 하시겠습니까?")){
        	
        	e.preventDefault();
        	document.form1.action = "${path}/pidback/deletePidback/do";
        	document.form1.submit();

        }
    });
	
	
	$(".updateComment").click(function(e){

		
		var trid = $(this).closest('tr').attr('id');
		
		var id = $("#id").val();
		var sid = $("#sid" + trid).val();
		var stitle = $("#stitle" + trid).val();
	

        if(confirm("댓글을 수정 하시겠습니까?")){
        	
        	
        	e.preventDefault();
        	 var url = '${path}/pidback/configComment/do/' + id +'/'+ sid +'/'+ stitle;
			  
			 location.href = url;

        }
    });
	
	
	$(".deleteComment").click(function(e){

		
		var trid = $(this).closest('tr').attr('id');
		
		var id = $("#id").val();
		var sid = $("#sid" + trid).val();
	
	

        if(confirm("댓글을 삭제 하시겠습니까?")){
        	
        	
        	e.preventDefault();
        	 var url = '${path}/pidback/deleteComment/do/' + id +'/'+ sid;
			  
			 location.href = url;

        }
    });
	
	
	
	  $("#insComent").click(function(e){
		   

	    	var id = $("#id").val();
			var sbody = $("#sbody").val();
	    
		
	    	
	        if(confirm("댓글을 등록 하시겠습니까?")){
	        	
	        	e.preventDefault();
	        	document.form1.action = "${path}/pidback/insComment/do";
	        	document.form1.submit();

	        }
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
        	<h1>피드백 내용</h1>
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
                        	<th>작성자 부서</th>
                            <td colspan="7">${data.deptName}</td>
                            
                        </tr>
                        <tr>
                        	<th>작성자 직위</th>
                            <td colspan="2">${data.position}</td>
                            <th colspan="2">작성자</th>
                            <td colspan="3">${data.usrName}</td>
                        </tr>
                        
                         <tr>
                        	<th>작성일</th>
                            <td colspan="7">
                            <fmt:formatDate value="${data.whenCreated}" pattern="yyyy-MM-dd"/>
                            </td>
                            
                        </tr>
                        <tr>
                        	<th>종류</th>
                            <td colspan="7">
                            
                            	<c:choose>
                            	
                            		<c:when test="${data.kind eq 'P'}">
                            			구매품
                            		</c:when>
                            		<c:otherwise>
                            			도면
                            		</c:otherwise>
                            	
                            	</c:choose>
                            
                            </td>
                            
                        </tr>
                        
                         <tr>
                        	<th>구매/도면 번호</th>
                            <td colspan="7">
                            	<c:choose>
                            	
                            		<c:when test="${data.kind eq 'P'}">
                            			${data.jobPurchaseId}
                            		</c:when>
                            		<c:otherwise>
                            			${data.fdrawingNo}
                            		</c:otherwise>
                            	
                            	</c:choose>
                            
                            </td>
                            
                        </tr>
                      
                        <tr>
                        	<th>title</th>
                            <td colspan="7">
                            	<c:choose>
             						<c:when test="${data.userId eq loginUsr}">
             		  					<input type="text" id="title" name="title" value="${data.title}" style="width:1000px;">
             						</c:when>
             						<c:otherwise>
             							${data.title}
             						</c:otherwise>

             					</c:choose>   
                            </td>
                        </tr>
                        <tr>
                        	<th>피드백 내용</th>
                            <td colspan="7">
                            
                            
                            	<c:choose>
             						<c:when test="${data.userId eq loginUsr}">
             	
             		  					<textarea id="body" name="body" rows="15">${data.body}</textarea>
             						</c:when>
             						<c:otherwise>
             							<textarea id="body" name="body" rows="15" readonly>${data.body}</textarea>
             						</c:otherwise>
             
             					</c:choose>   
                            	
                            	
                            </td>
                        </tr>
                    </table>
                    
              
                    <input type="hidden" id="id" name="id" value="${data.id}">
                </div>
                <!-- //게시판 -->
                
             <c:choose>
             	<c:when test="${data.userId eq loginUsr}">
             	
             		  </br>  
             		  <a href="#" id="updatePid" class="btn_blue">수정</a>
             		  <a href="#" id="deletePid" class="btn_gray">삭제</a>  
             	
             	</c:when>
             	<c:otherwise>
             	
             	</c:otherwise>
             
             </c:choose>   
            
           
           
            </br>
            </br>
            </br>
            </br>    
            <div class="popTitArea">
        		<h1>댓글</h1>
        	</div>
        	
        	
        	</br>
            </br>
        	
        	<div>
        	
        		<textarea id="sbody" name="sbody" rows="1" cols="150" placeholder="댓글을 입력하세요."></textarea>
        		<a href="#" id="insComent" class="btn_blue">댓글 등록</a>
        	</div>    
            
            
            <div class="listBox">
                        	<table>              	
                        		
                             
                                		<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:10%;" />
                                		<col style="width:50%;" />
                                		<col style="width:13%;" />
                                		<col style="width:13%;" />
                                		<col style="width:14%;" />                
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                			<th>작성자</th>
                                        	<th>내용</th>
                                    		<th>작성일</th>
                                        	<th>최근수정일</th>
                                        	<th></th>                                    
                                    	</tr>
                        
                                </thead>
                                
                                
                                <tbody id="serviceTbody">
                                
                               
                                	<c:set var="SNo" value="${pageNo + 1}"></c:set>
                  					<c:forEach items="${data2}" var="entry">
                  					
                  					
                                			<tr id="${SNo}">
                                				<td>${entry.usrName}</td>
                                    			<td>
                                    				<c:choose>
                                    					<c:when test="${entry.userId eq loginUsr}">
                                    						<input type="text" id="stitle${SNo}" name="stitle${SNo}" value="${entry.smallBody}" style="width:1000px;text-align:center;">
                                    					</c:when>
                                    					<c:otherwise>
                                    						${entry.smallBody}
                                   					</c:otherwise>
                                    				
                                    				</c:choose>
                                  			
                                    			</td>
                                        		<td><fmt:formatDate value="${entry.whenCreated}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                        		<td><fmt:formatDate value="${entry.whenUpdated}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                        		
                                        		<c:choose>
                                        			<c:when test="${entry.userId eq loginUsr}">
                                        				<td>
                                        					<a href="#" class="btn_gray updateComment">수정</a>
                                        					<a href="#" class="btn_gray deleteComment">삭제</a>
                                        				</td>
                                        			
                                        			</c:when>
                                        			
                                        			<c:otherwise>
                                        				<td></td>
                                        			</c:otherwise>
                                        		</c:choose>
                                        		
                                    		</tr>
                                    		
                                    		<input type="hidden" id="sid${SNo}" name="sid${SNo}" value="${entry.id}">
                                    		<c:set var="SNo" value="${SNo + 1 }" />
 									</c:forEach>
                                </tbody>
                            </table>
                        </div>                       
                	
         
             <div class="popBtn">

              
                
                
               
             </div>
                
            </div>
            </div>
            </div>
            </form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
   
	




</body>
</html>