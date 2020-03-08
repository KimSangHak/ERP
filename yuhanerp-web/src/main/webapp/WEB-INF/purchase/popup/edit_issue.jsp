<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  	
  		$(document).ready(function(){
  			
  			$("#deleteBtn").click(function(){
		   		
  				var jobOrderId = $("#jobOrderId").val();	
  				var issueRequestId = $("#issueRequestId").val();
  				var jobPurchaseId = $("#jobPurchaseId").val();
  				var cancleReason = $("#cancleReason").val();  	
  						  
  				if(confirm("발주를 취소 하시겠습니까?")){
  				        	
  				        	
  					document.form1.target="parent";
  				    document.form1.action = "${path}/purchase/issue/cancle";
  				    document.form1.submit();
  				    self.close();        	
  				}
  			});
  			
  			
  			$("#delete").on("click", function() {

        		var chk = $("input[name='record']:checked").length;

        		if(chk > 0) {

            		$("input[name='record']:checked").each(function() {
            	
            			var trid = $(this).closest('tr').attr('id');
            	
            			$(".jobPurchaseId"+trid).remove();
            			$(".jobOrderId"+trid).remove();
            			$(".issueRequestId"+trid).remove();

                		$(this).closest("tr").next().remove();

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
		});	
	
		
	
 	</script>

</head>

<body class="sub">
<div id="container">

<form id="form1" name="form1" enctype="multipart/form-data" method="post">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>발주  LIST</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>발주 상세 LIST</h2>
              
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">
            
            	
                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20px;" />
                        <col style="width:20px;" />
                        <col style="width:130px;" />
                        <col style="width:100px;" />
                        <col style="width:70px;" />
                        <col style="width:30px;" />
                        <col style="width:50px;" />
                        <col style="width:150px;" />
                        <col style="width:50px;" />
                        <col style="width:70px;" />
                        <col style="width:30px;" />
                        <col style="width:30px;" />
                        <col style="width:150px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                
                        </colgroup>
                        <thead>
                            <tr>
                            	
								<th>선택</th>
								<th>Seq</th>
                            	<th>발주 No.</th>
                                <th>발주일</th>
                                <th>Order No.</th>
                                <th>UNIT</th>
                                <th>Description</th>
                                <th>Model No./Size</th>
                                <th>Maker</th>                         
                                <th>구매처</th>
                                <th>구매등록수량</th>
                                <th>발주수량</th>
                                <th>납기일</th>
                                <th>단가</th>
                                <th>공급가</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        <c:set var="No" value="${pageNo + 1}"></c:set>
                        <c:forEach items="${data}" var="entry">
                        	<fmt:parseNumber var="Sum" value="${entry.quantity*entry.issuePrice}" integerOnly="true"/>
                            <tr id="${No}">
                            	
                            	<td><input type="checkbox" name="record" id="record${No}"></td>
                            	<td>${entry.jobPurchaseId}</td>
           						<td>${entry.issueRequestId}</td>
                                <td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${entry.jobOrderNo}</td>
                                <td>${entry.unitNo}</td>
                                <td>${entry.description}</td>
                                <td>${entry.modelNo}</td>                     
                                <td>${entry.maker}</td>                               
                                <td>${entry.partnerName}</td>
                                <td>${entry.purchasequatity}</td>
                                <td>${entry.quantity}</td>
                                <td><fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatNumber value="${entry.issuePrice}" pattern="#,###" /></td>
                                <td><fmt:formatNumber value="${Sum}" pattern="#,###" /></td>
                               
                                
                            </tr>
                      		
                      			<input type="hidden" id="jobPurchaseId" name="jobPurchaseId" class="jobPurchaseId${No}" value="${entry.jobPurchaseId}">
                      			<input type="hidden" id="jobOrderId" name="jobOrderId" class="ijobOrderId${No}" value="${entry.jobOrderId}">
                      			<input type="hidden" id="issueRequestId" name="issueRequestId" class="issueRequestId${No}" value="${entry.issueRequestId}">
                      			<c:set var="No" value="${No + 1 }" />
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- //게시판 -->
            
            </div>
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        <!-- //팝업 콘텐츠 -->
         <div>
        	<span>취소사유</span>
            <span><input type="text" id="cancleReason" name="cancleReason" value=" " style="width:200px;"></span>
          
         </div>
         <div class="popBtn">
            
            	<button class="btn_gray" type="button" id="deleteBtn">발주 취소</button>
            	<button class="btn_blue" type="button" id="allChex">전체선택</button> 
            	<button class="btn_blue" type="button" id="delete">선택 된 LIST 행삭제</button>  
              
            </div>
        
        
        
	</div>
	
	</form>
</div>
</body>
</html>
