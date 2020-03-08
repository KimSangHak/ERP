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
	  
	
	  
	
	  
	  $("#delete").on("click", function() {

	        var chk = $("input[name='record']:checked").length;

	        if(chk > 0) {

	            $("input[name='record']:checked").each(function() {
	            	
	            	
	            	var trid = $(this).closest('tr').attr('id');
	    	    	
	    			$(".drawingId"+trid).remove();
	    		
	                $(this).closest("tr").remove();

	            });

	        } else {

	            alert("삭제할 항목 없음");

	        }

	    });
	  
	$("#allChex").click(function(){
	    	
  			for(i=0; i < form1.record.length; i++) {
  				form1.record[i].checked = true;
  			}
	    
	      
	    });
	  
	  
	  
	    $("#UpdateBtn").click(function(){

	  
			var drawingId =  $("#drawingId").val();
			var rqty =  $("#rqty").val();
		
	
	        if(confirm("재가공 수량만큼 사내가공으로 재가공 요청 합니다. 진행하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "/ps/carry/carryNG/do";
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
        	<h1>NG 재가공 처리</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
            
  
                
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                        <col style="width:20%;" />
                                      
                                    
                                        </colgroup>
                                        <tr>
                                            
                                            <th>선택</th>
                                            <th>도면번호</th>
                                            <th>제작 수량</th>
                                            <th>NG 수량</th>
                                            <th>재가공 수량</th>
                                          
                                          
                                            
                                        </tr>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${item}" var="line">
                                        
                                        

                                        <tr id="${No}">
                                            <td><input type="checkbox" name="record" id="record${No}"></td>
                                           	<td>${line.drawNo}</td>
                                           	<td>${line.qty}</td>
                                           	<td style="color:RED;">${line.failQuantity}</td>       
                                           	<td><input type="number" id="rqty" name="rqty" value=0></td>
                                            
                                        </tr>

                                        <input type="hidden" id="drawingId${No}" name="drawingId" class="drawingId${No}" value="${line.drawingId}">

                                        
                                        <c:set var="No" value="${No + 1 }" />
                                        </c:forEach>
                                    </table>
                                    
                                   
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">
			
              	<a href="#" class="btn_blue" id="UpdateBtn">확인</a>
              	<a href="#" class="btn_blue" id="allChex">전체선택</a>
              	<a href="#" class="btn_gray" id="delete">선택 된 LIST 행제외</a>
            </div>
        
        
        
	</div>

</div>
</form>
</body>
</html>
