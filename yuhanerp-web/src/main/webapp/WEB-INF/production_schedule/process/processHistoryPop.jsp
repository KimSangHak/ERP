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
  
  window.name="parent2";
  
  
  $(document).ready(function(){
	  

	   
	});	
	
  </script>

</head>

<body class="sub">
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>${designDrawingNo} 상세이력</h1>
        </div>
        
        <!-- //팝업 타이틀 -->
        
        				<div>
        					현재 위치 : &nbsp; &nbsp; ${whereDept}
         				
                         
                         </div>
        					
        			
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      									
      									
                                        <col style="width:21%;" />
                                        <col style="width:21%;" />
                                        <col style="width:21%;" />
                                        <col style="width:36%;" />
                        
                                      
                                        </colgroup>
                                        <tr>
                                            
                                            
                                           
                                            <th>도면번호</th>
                                            <th>처리자</th>
                                            <th>처리날짜</th>
                                            <th>이력</th>
                                            
                                           
                                            
                                        </tr>
                                      
                                        <c:forEach items="${data}" var="line">
                                        
                                        
                                
                                        <tr>
                                        
                                            <td>${line.designDrawingNo}</td>
                                            <td>${line.usrName}</td>
                                            <td><fmt:formatDate value="${line.regDate}" pattern="yyyy-MM-dd"/></td>
                                            <td>${line.description}</td>
                                           
                                            
                                  
                                        </tr>
                                       </c:forEach>
                                    </table>
                                    
                                   
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>

        <!-- //팝업 콘텐츠 -->
        
        
       
        
        
        
        
	</div>


</div>
</form>
</body>
</html>
