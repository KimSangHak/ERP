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
	  
	  
	  
	  $("#registeBtn").click(function(){
	   		
			var rquantity = $("#rquantity").val();	
			var id = $("#id").val();
			var deptcode = $("#deptcode option:selected").val();

					  
			if(confirm("재인계 하시겠습니까?")){
			        	
			        	
				document.form1.target="parent";
			    document.form1.action = "${path}/transition/eReInsPart/do";
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
        	<h1>타부서 인계</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
   
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
            
      
                	<div class="popList">
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      									
      								
                                        <col style="width:20%;" />
                                        <col style="width:30%;" />
                                        <col style="width:30%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />


                                     
                                    
                                        </colgroup>
                                        <tr>

                                     
                                            <th>OderNo</th>
                                            <th>품목</th>
                                            <th>Model No/Size</th>
                                            <th>재인계 가능 수량</th>                                           
                                            <th>재인계 수량</th>

     
                                            
                                        </tr>
                                   		
                                   		<c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${data}" var="line">
                                        <fmt:parseNumber var="result" value="${line.quantity-line.rquantity}" integerOnly="true"/>
                                      

                                        <tr id="${No}">
                                        

                                            <td>${line.jobOrderNo}</td>
                                            <td>${line.description}</td>
                                           	<td>${line.modelNo}</td>                   
											<td style="color: red;">${result}</td>
                                           	<td><input type="number" id="rquantity" name="rquantity" value="${result}"></td>
                                        </tr>
                                        
                                        
                                         <input type="hidden" id="id" name="id" value="${line.id}">
                                        
                                        
                            			
                            			<c:set var="No" value="${No + 1 }" />
                                        </c:forEach>
                                    </table>
                                    
                   
                                    
                        </div>
                        <!-- //게시판 -->
                    
           				 <div class="popBtn">
           				 
           				 	</br>
           				 	<span>
           				 		<select name="deptcode" id="deptcode" style="width:263px;">
                   					<option value="AA">부서 선택</option>
                   					 <c:forEach items="${dept}" var="line2">
                   					 	<option value="${line2.id}">${line2.deptName}</option>
                   					 </c:forEach>
                 				</select>
                 			</span>
           				 
           				 	</br>
           				 	
           				 

              				
            			 </div>
            			 
            			 
            			  <div class="popBtn">
            
            					<button class="btn_gray" type="button" id="registeBtn">인계하기</button>
         
              
            			 </div>
            
            			
                        
                       
            
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->
            
            
		</div>
                
            
        </div>

        <!-- //팝업 콘텐츠 -->
        
          
            
            
            
        
        	
        	
	</div>


</div>
</form>
</body>
</html>
