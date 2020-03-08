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
	  
	  $("#allChex").click(function(){
	    	
			for(i=0; i < form1.record.length; i++) {
				form1.record[i].checked = true;
			}
	    
	      
	    });
	  
	  
	  $("#delete").on("click", function() {

	        var chk = $("input[name='record']:checked").length;

	        if(chk > 0) {

	            $("input[name='record']:checked").each(function() {
	            	
	            	
	            	var trid = $(this).closest('tr').attr('id');
	    	    	
	    			$(".id"+trid).remove();

	                $(this).closest("tr").remove();

	            });

	        } else {

	            alert("삭제할 항목 없음");

	        }

	    });
	  
	    $("#UpdateBtn").click(function(){
	    	
	    	
	    	var id = $("#id").val();
	    	var dateLeave = $("#dateLeave").val();
	    	var hourLeave = $("#hourLeave").val();
	    
	    
	        
	        if(confirm("이대로 직원 연월차를 수정 하시겠습니까?")){
	        	document.form1.target="parent";
	        	document.form1.action = "${path}/mymenu/leave/editUsrleavePop/do";
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
        	<h1>직원 연월차 수정</h1>
        </div>
        <!-- //팝업 타이틀 -->
    

                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      									
      									<col style="width:10%;" />
                                        <col style="width:20%;" />
                                        <col style="width:32%;" />
                                        <col style="width:10%;" />
                                        <col style="width:8%;" />
                                        <col style="width:10%;" />
                                        <col style="width:8%;" />
                        
                                      
                                        </colgroup>
                                        <tr>
                                            
                                            
                                            <th>선택</th>
                                            <th>이름</th>
                                            <th>현재 연월차 수량</th>
                                            <th colspan="4">입력 연월차 수량(8시간 기준)</th>
                                           
                                            
                                        </tr>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${data}" var="line">
                                        
                                        
                                
                                        <tr id="${No}">
                                        
                                            <td><input type="checkbox" name="record" id="record${No}"></td>
                                            <td>${line.name}</td>
                                            <td>${line.dateLeave} 일 ${line.hourLeave} 시간</td>
                                            <td colspan="2"><input style="width:100px;" type="number" id="dateLeave" name="dateLeave" value=0 width="20px;">&nbsp;일 </td>
                                            <td colspan="2"><input style="width:100px;" type="number" id="hourLeave" name="hourLeave" value=0 width="20px;">&nbsp;시간</td>
                                           
                                            
                                  
                                        </tr>
                                        <input type="hidden" id="id" name="id" class="id${No}" value="${line.id}">
                                      
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
      			<a href="#" class="btn_gray" id="allChex">전체선택</a>
            	<a href="#" class="btn_gray" id="delete">선택 된 LIST 행 제외</a>
            	<a href="#" class="btn_blue" id="UpdateBtn">수정</a>
            	
              
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
