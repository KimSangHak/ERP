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
    		var name = $("#name").val();
    		var engName = $("#engName").val();
    		var deptName = $("#deptName option:selected").val();
    		var position = $("#position option:selected").val();
    		var email = $("#email").val();
    		var kakaoId = $("#kakaoId").val();
    		var handPhone = $("#handPhone").val();
    		var inPhone = $("#inPhone").val();
    		var homeAddress = $("#homeAddress").val();
    		var passportExpireDate = $("#passportExpireDate").val();

        	if(confirm("해당 직원 정보를 수정하시겠습니까?")){
        	
        		document.form1.target="parent";
        		document.form1.action = "${path}/general/popup/generalUsrEditAction";
        		document.form1.submit();
        		self.close();
        	
        	}
    
    	});
    	
    	$("#outBtn").click(function(){
    		
    		var id = $("#id").val();
    		   
    		if(confirm("해당 직원을 퇴사처리 하시겠습니까?")){
            	
        		document.form1.target="parent";
        		document.form1.action = "${path}/general/popup/deleteUser";
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
        	<h1>직원 상세정보 수정</h1>
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
                        
                        <c:forEach items="${user}" var="user">
                        <tr>
                            <th>이름/영문 이름<span class="ncBlue">*</span></th>
                            <td><input type="text" name="name" id="w120" value="${user.name}"> <input type="text" name="engName" id="w120" value="${user.engName}"></td>
                        </tr>

                         <tr>
                            <th>부서/직위 <span class="ncBlue">*</span></th>
                            <td>
                                <select name="deptName" id="w260">
                                  	<c:forEach items="${nameDept}" var="nameDept">
                                    <option value="${nameDept.deptName}" <c:if test="${nameDept.deptName == user.deptName}">selected</c:if>>${nameDept.deptName}</option>
                                    </c:forEach>
                                </select>
                                
                                <select name="position" id="w260">
                                  	<c:forEach items="${positionId}" var="positionId">
                                    <option value="${positionId.id}" <c:if test="${positionId.id == user.position}">selected</c:if>>${positionId.id}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <th>Email/KaKaoId <span class="ncBlue">*</span></th>
                            <td><input type="text" name="email" value="${user.email}"> <input type="text" name="kakaoId" value="${user.kakaoId}"></td>
                        </tr>
                         
                        <tr>
                            <th>핸드폰 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="handPhone" value="${user.handPhone}"></td>
                        </tr>
                         
                        <tr>
                            <th>내선번호<span class="ncBlue">*</span></th>
                            <td><input type="text" name="inPhone" value="${user.inPhone}"></td>
                        </tr>
                        
                        <tr>
                            <th>주소<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="text" name="homeAddress" value="${user.homeAddress}"></p>
                     
                            </td>
                        </tr>

                         <tr>
                            <th>여권만료일<span class="ncBlue"></span></th>
                            <td>
                            	<input type="date" id="passportExpireDate" name="passportExpireDate" 
            						value="<fmt:formatDate value="${user.passportExpireDate}" pattern="yyyy-MM-dd"/>">
                     
                            </td>
                        </tr>

                  		<input type="hidden" id="id" name="id" value="${user.id}">
                        
                        </c:forEach>
                        
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="outBtn">퇴사처리</a>
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
