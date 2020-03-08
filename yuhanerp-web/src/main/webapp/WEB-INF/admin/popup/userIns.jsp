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
    		var password = $("#password").val();
    		var name = $("#name").val();
    		var engName = $("#engName").val();
    		var email = $("#email").val();
    		var kakaoId = $("#kakaoId").val();
    		var deptName = $("#deptName option:selected").val();
    		var position = $("#position option:selected").val();
    		var inPhone = $("#inPhone").val();
    		var directPhone = $("#directPhone").val();
    		var handPhone = $("#handPhone").val();
    		var homeAddress = $("#homeAddress").val();
    		var viewIndex = $("#viewIndex").val();
    		var isAdmin = $("#isAdmin option:selected").val();
    		
    		var isQc = $("#isQc option:selected").val();
    		var isPgm = $("#isPgm option:selected").val();
    		var isRobot = $("#isRobot option:selected").val();
    		
    		var homePhone = $("#homePhone").val();
    		var passportExpireDate = $("#passportExpireDate").val();
    		var mctPasswd = $("#mctPasswd").val();
    		
        	if(confirm("해당 정보로 직원을 추가하시겠습니까?")){
        	
        		document.form1.target="parent";
        		document.form1.action = "${path}/Admin/popup/userInsAction";
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
        	<h1>직원 추가(*는 꼭 입력하십시오)</h1>
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
                            <th>로그인 아이디(사번)/비밀번호<span class="ncBlue">*</span></th>
                            <td><input type="text" name="id" id="w120"> <input type="password" name="password" id="w120"></td>
                        </tr>
                        
                         <tr>
                            <th>이름/영문이름<span class="ncBlue">*</span></th>
                            <td><input type="text" name="name" id="w120"> <input type="text" name="engName" id="w120"></td>
                        </tr>
                        
                         <tr>
                            <th>Email/KaKaoId <span class="ncBlue">*</span></th>
                            <td><input type="text" name="email"> <input type="text" name="kakaoId"></td>
                        </tr>
                        
                         <tr>
                            <th>부서/직위 <span class="ncBlue">*</span></th>
                            <td>
                                <select name="deptName" id="w260">
                                  	<c:forEach items="${nameDept}" var="nameDept">
                                    <option value="${nameDept.deptName}">${nameDept.deptName}</option>
                                    </c:forEach>
                                </select>
                                
                                <select name="position" id="w260">
                                  	<c:forEach items="${positionId}" var="positionId">
                                    <option value="${positionId.id}">${positionId.id}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>내선번호/직통번호/휴대폰 <span class="ncBlue">*</span></th>
                            <td>
                            <input type="text" name="inPhone"> 
                            <input type="text" name="directPhone">
                            <input type="text" name="handPhone">
                            </td>
                        </tr>
                         
                        <tr>
                            <th>주소<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="text" name="homeAddress"></p>
                     
                            </td>
                        </tr>
                        
                        <tr>
                            <th>메인화면연락처순번<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="number" name="viewIndex" value="${viewCount}"></p>
                     
                            </td>
                        </tr>
                        
                        <tr>
                            <th>관리자 여부 <span class="ncBlue">*</span></th>
                            <td>
                                <select name="isAdmin" id="w260">
                                    <option value="Y">Yes </option>
                                    <option value="N" selected>No</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>검사담당자 여부<span class="ncBlue">*</span></th>
                            <td>
                                <select name="isQc" id="w260">
                                    <option value="Y">Yes </option>
                                    <option value="N" selected>No</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>프로그램담당자 여부<span class="ncBlue">*</span></th>
                            <td>
                                <select name="isPgm" id="w260">
                                    <option value="Y">Yes </option>
                                    <option value="N" selected>No</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>로봇티칭담당자 여부  <span class="ncBlue">*</span></th>
                            <td>
                                <select name="isRobot" id="w260">
                                    <option value="Y">Yes </option>
                                    <option value="N" selected>No</option>
                                </select>
                            </td>
                        </tr>
                        
                         <tr>
                            <th>집번호<span class="ncBlue"></span></th>
                            <td>
                            <input type="text" name="homePhone"> 
                            </td>
                        </tr>
                        
                         <tr>
                            <th>여권만료일<span class="ncBlue"></span></th>
                            <td>
                            	<input type="date" id="passportExpireDate" name="passportExpireDate">
                     
                            </td>
                        </tr>
                        
                        <tr>
                            <th>MCT비밀번호<span class="ncBlue"></span></th>
                            <td>
                            	<input type="text" id="mctPasswd" name="mctPasswd">
                     
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
                <a href="#" class="btn_blue" id="EditBtn">추가</a>
                
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</form>
</body>
</html>
