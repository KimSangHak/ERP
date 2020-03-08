<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <script language="JavaScript" src="/js/menu.js"></script>
    
    <script type="text/javascript">
		$(document).ready(function(){
    	$("#insertBtn").click(function(){
    		
    		var id = $("#id").val();
    		var partnerName = $("#partnerName").val();
    		var corporateNum1 = $("#corporateNum1").val();
    		var corporateNum2 = $("#corporateNum2").val();
    		var corporateNum3 = $("#corporateNum3").val();
    		var corporateAddr1 = $("#corporateAddr1").val();
    		var corporateAddr2 = $("#corporateAddr2").val();
    		var personPhone1 = $("#personPhone1").val();
    		var personPhone2 = $("#personPhone2").val();
    		var personPhone3 = $("#personPhone3").val();
    		var personName = $("#personName").val();
    		var personPosition = $("#personPosition").val();
    		var personMail = $("#personMail").val();
    		var billingName = $("#billingName").val();
    		var corporatePhone = $("#corporatePhone").val();
    		var corporateMail = $("#corporateMail").val();
    		var billingAfter = $("#billingAfter option:selected").val();
    		var billingDay = $("#billingDay option:selected").val();
    		var bankName = $("#bankName").val();
    		var bankAccount = $("#bankAccount").val();
    		var note = $("#note").val();
    
    		
        
        if(confirm("새로운 거래처를 등록 하시겠습니까?")){
        	
        	
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/Purchase_Partner/popup/insert";
        	document.form1.submit();
        	self.close();
        	
        }
        
     
       
    });
    	
	$("#AutoPartnerBtn").click(function(){
    		
			alert("업체코드 생성 후 최종 등록을 하지 않거나 업체코드를 변경하시면 생성 된 업체번호는 폐기됩니다.");
    		var typeCode = $("#typeCode option:selected").val();
    		var typeKind = $("#typeKind option:selected").val();

        	document.form1.action = "${path}/purchase/Purchase_Partner/popup/autoPartner";
        	document.form1.submit();
 
    });
	
	$("#typeCode").on('change', function(){
		
		var param =  $(this).val();
		var param2 = $("#typeKind option:selected").val();
		

		
		 	$.ajax({
				url : "/purchase/Purchase_Partner/popup/typekind",
				dataType : "html",
				type : "POST",
				data : {param:param, param2:param2},
				success:function(data){
				
					$("#kind").html(data);
					
					
				},
				error : function(){
					
				}
			});
		
	
		
	});
    
    $("#escBtn").click(function(){
   
    	self.close();
    });
});
</script>

</head>

<body class="sub">
<form id="form1" name="form1" enctype="multipart/form-data" method="post">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>거래처 등록</h1>
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
                        <col style="width:20%;" />
                        <col style="width:80%;" />
                        
                        <tr>
                            <th>업체코드<span class="ncBlue">*</span></th>
                            <td><input type="text" name="id" id="w120" value="${countPartnerResult}" readonly> 
                            <a href="#" class="btn_line_gray" id="AutoPartnerBtn">업체코드 자동생성</a></td>
                        </tr>
                        <tr>
                            <th>업체명 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="partnerName"></td>
                        </tr>
                        <tr>
                            <th>업체 대분류 <span class="ncBlue">*</span></th>
                            <td>
                                <select name="typeCode" id="typeCode" style="width:263px;">
                                	<option value="none" <c:if test="${typeCode == 'none'}">selected</c:if>>선택</option>
                                	<c:forEach items="${alltypecode}" var="item">
                                		<option value="${item.typeCode}"<c:if test="${typeCode == item.typeCode}">selected</c:if>>${item.typeName}</option>
                                	</c:forEach>
                                	
                             
                                  
                                </select>
                            </td>
                        </tr>
                      
                        <tr id="kind">
                        
                        </tr>
                        
                        <tr>
                            <th>사업자 번호<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporateNum1" id="w80"> - <input type="text" name="corporateNum2" id="w80"> - <input type="text" name="corporateNum3" id="w80"></td>
                        </tr>
                        <tr>
                            <th>주소<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="text" name="corporateAddr1"></p>
                                <p class="mt5"><input type="text" name="corporateAddr2"></p>
                            </td>
                        </tr>
                        <tr>
                            <th>담당자 연락처 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personPhone1" id="w80"> - <input type="text" name="personPhone2" id="w80"> - <input type="text" name="personPhone3" id="w80"></td>
                        </tr>
                        <tr>
                            <th>담당자 성함/직급 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personName" id="w150"> <input type="text" name="personPosition" id="w150"></td>
                        </tr>
                        <tr>
                            <th>담당자 메일 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personMail"></td>
                        </tr>
                     
                        <tr>
                            <th>회사 연락처<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporatePhone"></td>
                        </tr>
                        <tr>
                            <th>회사 메일<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporateMail"></td>
                        </tr>
                        <tr>
                            <th>결제 기준</th>
                            <td>
                            	<p class="staDay">
                                	<span>작성일로부터 몇개월 뒤</span>
                                    <span>
                                    	<select name="billingAfter" id="w120">
                                    		<option value="null" selected>선택</option>
                                    		<c:forEach items="${billingMonth}" var="item">
                                				<option value="${item.id}">${item.id}</option>
                                			</c:forEach>
                                        
                                        </select>
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>선택한 개월 뒤 날짜</span>
                                    <span>
                                    	<select name="billingDay" id="w120">
                                    		<option value="null" selected>선택</option>
                                            <c:forEach items="${billingDay}" var="item">
                                				<option value="${item.id}">${item.id}</option>
                                			</c:forEach>
                                        </select>
                                    </span>
                                </p>
                            </td>
                        </tr>
                        
                          <tr>
                            <th>결제 정보</th>
                            <td>
                            	<p class="staDay">
                                	<span>은행</span>
                                    <span>
                                   		<input type="text" name="bankName" id="bankName">
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>계좌번호</span>
                                    <span>
                                    	<input type="text" name="bankAccount" id="bankAccount">
                                    </span>
                                </p>
                                  <p class="staDay">
                                	<span>계산서업체명</span>
                                    <span>
                                    	<input type="text" name="billingName" id="billingName">
                                    </span>
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                        	<th>비고</th>
                        	<td>
                        		<input type="text" name="note" id="note">
                        	</td>
                        
                        </tr>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->
				
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="insertBtn">등록확인</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
