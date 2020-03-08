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
    	$("#EditBtn").click(function(){
    		
    		var id = $("#id").val();
    		var partnerName = $("#partnerName").val();
    		var corporateNum = $("#corporateNum").val();
    		var corporateAddr = $("#corporateAddr").val();
    		var personPhone = $("#personPhone").val();
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
    		
        
        if(confirm("거래처 내용을 수정 하시겠습니까?")){
        	
        	document.form1.target="parent";
        	document.form1.action = "${path}/purchase/Purchase_Partner/popup_Ed/update";
        	document.form1.submit();
        	self.close();
        	
        }
    
       
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
        	<h1>거래처 수정(도면 관리)</h1>
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
                        
                        <c:forEach items="${partner}" var="item">
                        <tr>
                            <th>업체코드<span class="ncBlue">*</span></th>
                            <td><input type="text" name="id" id="w120" value="${item.id}" readonly></td>
                        </tr>
                        <tr>
                            <th>업체명 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="partnerName" value="${item.partnerName}"></td>
                        </tr>
                        <tr>
                            <th>업체 대분류 <span class="ncBlue">*</span></th>
                            <td>
                                <select id="w260">
                                    <option value="${item.typeCode}" selected disabled>${item.typeCode}</option>   
                                </select>
                                
                            </td>
                        </tr>
                        <tr>
                            <th>업체 소분류 <span class="ncBlue">*</span></th>
                            <td>
                                <select id="w260">
                                    <option value="${item.typeKind}" selected disabled>${item.typeKind}</option>                       
                                </select>
                               
                            </td>
                        </tr>
                        <tr>
                            <th>사업자 번호<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporateNum" value="${item.corporateNum}"></td>
                        </tr>
                        <tr>
                            <th>주소<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="text" name="corporateAddr" value="${item.corporateAddr}"></p>
                     
                            </td>
                        </tr>
                        <tr>
                            <th>담당자 연락처 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personPhone" value="${item.personPhone}"></td>
                        </tr>
                        <tr>
                            <th>담당자 성함/직급 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personName" id="w150" value="${item.personName}"> <input type="text" name="personPosition" id="w150" value="${item.personPosition}"></td>
                        </tr>
                        <tr>
                            <th>담당자 메일 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="personMail" value="${item.personMail}"></td>
                        </tr>
                     
                        <tr>
                            <th>회사 연락처<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporatePhone" value="${item.corporatePhone}"></td>
                        </tr>
                        <tr>
                            <th>회사 메일<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporateMail" value="${item.corporateMail}"></td>
                        </tr>
                        <tr>
                            <th>결제 기준</th>
                            <td>
                            	<p class="staDay">
                                	<span>작성일로부터 몇개월 뒤</span>
                                    <span>
                                    	<select name="billingAfter" id="w120">
                                    		<option value="null">선택</option>
                                    		<c:forEach items="${billingMonth}" var="item2">
                                				<option value="${item2.id}" <c:if test="${item2.id == item.billingAfter}">selected</c:if>>${item2.id}</option>
                                			</c:forEach>                        
                                        </select>
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>선택한 개월 뒤 날짜</span>
                                    <span>
                                    	<select name="billingDay" id="w120">
                                    		<option value="null">선택</option>
                                    		<c:forEach items="${billingDay}" var="item2">
                                				<option value="${item2.id}" <c:if test="${item2.id == item.billingDay}">selected</c:if>>${item2.id}</option>
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
                                   		<input type="text" name="bankName" id="bankName" value="${item.bankName}">
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>계좌번호</span>
                                    <span>
                                    	<input type="text" name="bankAccount" id="bankAccount" value="${item.bankAccount}">
                                    </span>
                                </p>
                                  <p class="staDay">
                                	<span>계산서업체명</span>
                                    <span>
                                    	<input type="text" name="billingName" id="billingName" value="${item.billingName}">
                                    </span>
                                </p>
                            </td>
                        </tr>
                        
                        
                         <tr>
                        	<th>비고</th>
                        	<td>
                        		<input type="text" name="note" id="note" value="${item.note}">
                        	</td>
                        
                        </tr>
                        
                        </c:forEach>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="EditBtn">수정</a>
                
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
