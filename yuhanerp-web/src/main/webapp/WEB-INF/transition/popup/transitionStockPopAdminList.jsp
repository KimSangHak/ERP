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
  
  
  

	
  </script>

</head>

<body class="sub">
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>인수 확인 LIST</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
   
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
            
      
                
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      
                                        <col style="width:9%;" />
                                        <col style="width:12%;" />
                                        <col style="width:15%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:12%;" />
                                        <col style="width:12%;" />
                                     
                                    
                                        </colgroup>
                                        <tr>
                                            
                                            <th>인수자</th>
                                            <th>인수부서</th>
                                            <th>OderNo</th>
                                            <th>품목</th>
                                            <th>Model No/Size</th>
                                            <th>입고수량</th>                                           
                                            <th>인계자</th>
     
                                            
                                        </tr>
                     
                                        <c:forEach items="${data}" var="line">
                                        
                                      

                                        <tr>
                                        
                                            
                                            <td>${line.receiveUsrName}</td>
                                            <td>${line.deptName}</td>
                                           	<td>${line.jobOrderNo}</td>
                                           	<td>${line.description}</td>
                                            <td>${line.modelNo}</td>                                
											<td>${line.quantity}</td>
                                           	<td>${line.userName}</td>
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
