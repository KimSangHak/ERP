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
	           
	    			$(".jobPurchaseId"+trid).remove();
	    			$(".stockId"+trid).remove();
	    			$(".requestId"+trid).remove();
	    			$(".modelNo"+trid).remove();

	   

	                $(this).closest("tr").remove();

	            });

	        } else {

	            alert("삭제할 항목 없음");

	        }

	    });
	  
	  
	  
	    $("#UpdateBtn").click(function(){
	   
  	
	    	var selectPartnerName = $("#selectPartnerName option:selected").val();
	    	var rawJobOrderId = $("#rawJobOrderId").val();
	    	var requestId = $("#requestId").val();
	    	var modelNo =  $("#modelNo").val();
	    	var jobPurchaseId = $("#jobPurchaseId").val();
	    	var EmodelNo = $("#EmodelNo").val();
	    	var material = $("#material").val();
	    	var orderQuantity = $("#orderQuantity").val();
	    	var comment = $("#comment").val();
	    	var description = $("#description").val();
	    	var stockId =  $("#stockId").val();

	    	
	        if(confirm("이대로 견적 메일을 보내시겠습니까?")){
	        	document.form1.action = "/mail/purchasenew/estimate";
	        	document.form1.submit();
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
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>견적</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
       <c:forEach items="${data}" var="item">
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                    <div class="popListTit">
                       <h4>Order No : [${item[0].jobOrderNo }]</h4>
                       
                    </div>
                    
                  
                
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
            
      
                    	 <select name="selectPartnerName" id="selectPartnerName" style="width:263px;">
                         	
                            	<c:forEach items="${partnerName}" var="name">
                                	<option value="${name}"<c:if test="${name == onePartnerName}">selected</c:if>>${name}</option>
                               	</c:forEach>
                         </select>
                    
                
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:6%;" />
                                        <col style="width:10%;" />
                                        <col style="width:15%;" />
                                        <col style="width:14%;" />
                                        <col style="width:9%;" />
                                        <col style="width:9%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />
                                        <col style="width:5%;" />   
                                        <col style="width:7%;" />
                                    
                                        </colgroup>
                                        <tr>
                                            
                                            <th>선택</th>
                                            <th>Seq</th>
                                            <th>OderNo</th>
                                            <th>Description</th>
                                            <th>(기존)Model No/Size</th>
                                            <th>(변경)Model No/Size</th>
                                            <th>MAKER</th>
                                            <th>재질</th>
                                            <th>등록구매수량</th>
                                            <th>재고수량</th>
                                            <th>재고사용수량</th>
                                            <th>실견적수량</th>
                                            <th>COMMENT</th>
                                          
                                            
                                        </tr>
                                        <c:set var="No" value="${pageNo + 1}"></c:set>
                                        <c:forEach items="${item}" var="line">
                                        
                                        <fmt:parseNumber var="stockqty" value="${line.stockQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="abQuantity" value="${line.abQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="remind" value="${stockqty-abQuantity}" integerOnly="true"/>
                                        
                                        <fmt:parseNumber var="Sum" value="${line.quantity+line.spare}" integerOnly="true"/>
                                
                                        <tr id="${No}">
                                        
                                            
                                            <td><input type="checkbox" name="record" id="record${No}"></td>
                                            <td>${line.id}</td>
                                            <td><input type="text" id="jobOrderNo" name="jobOrderNo" value="${line.jobOrderNo}" readonly></td>
                                           	<td><input type="text" id="description" name="description" value="${line.description}" readonly></td>
                                            <td><a href="javascript:PopWin('/purchase/stockUse/${line.modelNo}/${No}','1300','500','no');" class="btn_line_blue">${line.modelNo}</a></td>
                                            <td><input type="text" id="EmodelNo" name="EmodelNo" value="${line.modelNo}" readonly></td>
                                           	<td><input type="text" id="maker" name="maker" value="${line.maker}"></td>
                                           	<td><input type="text" id="material" name="material" value=" "></td>
                                           	<td><input type="number" id="quantity" name="quantity" value="${Sum}" readonly></td>
                                           	<td><input type="number" id="stockQuantity" name="stockQuantity" value="${remind}" readonly></td>
                                           	<td><input type="number" id="stockUseQuantity${No}" name="stockUseQuantity" value="${line.stockUseQuantity}" readonly></td>                                     
                                           	<td><input type="number" id="orderQuantity${No}" name="orderQuantity" value="${Sum}"></td>  
                                           	<td><input type="text" id="comment" name="comment" value=" "></td>
                                      
                                        </tr>
                                        <input type="hidden" id="jobPurchaseId" name="jobPurchaseId" class="jobPurchaseId${No}" value="${line.id}">
                                        <input type="hidden" id="modelNo" name="modelNo" class="modelNo${No}"  value="${line.modelNo}">
                                        <input type="hidden" id="stockId${No}" name="stockId" class="stockId${No}" value=" ">
                                        <input type="hidden" id="requestId" name="requestId" class="requestId${No}" value="aa">
                                        
                                        <c:set var="No" value="${No + 1 }" />
                                        </c:forEach>
                                    </table>
                                    
                                    <input type="hidden" id="partnerId" name="partnerId" value="${partnerId}">
                                    <input type="hidden" id="rawJobOrderId" name="rawJobOrderId" value="${rawJobOrderId}">
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>
        </c:forEach>
        <!-- //팝업 콘텐츠 -->
        
            <div class="popBtn">
				<!--  
            	<button class="btn_blue" type="button" id="UpdateBtn">확인</button>
            	<button class="btn_blue" type="button" id="allChex">전체선택</button>  
              	<button class="btn_blue" type="button" id="delete">선택 된 LIST 행제외</button>
              	-->
              	<a href="#" class="btn_blue" id="UpdateBtn">확인</a>
              	<a href="#" class="btn_blue" id="allChex">전체선택</a>
              	<a href="#" class="btn_gray" id="delete">선택 된 LIST 행제외</a>
            </div>
        
        
        
	</div>


</div>
</form>
</body>
</html>
