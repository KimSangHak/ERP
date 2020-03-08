<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 날짜 일괄 등록
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<base href="/" />
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>날짜 일괄 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<div class="popText">
                선택된 모든 리스트에 일괄 적용됩니다.
            </div>
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width: ;" />
                        </colgroup>
                        <tr>
                            <th>가공완료일</th>
                            <td><input type="text" id="processDate" class="AutoDatePicker"></td>
                        </tr>
                        <tr>
                            <th>후처리완료일</th>
                            <td><input type="text" id="postprocessingDate" class="AutoDatePicker"></td>
                        </tr>
                        <tr>
                            <th>검사완료일</th>
                            <td><input type="text" id="inspectDate" class="AutoDatePicker"></td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                <a href="#" class="btn_blue" id="RegButton">등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
	</div>
	
	<script type="text/javascript" src="/js/ux.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("#RegButton").click(function(e){
				e.preventDefault();
				
				var processDate = $("#processDate").val();
				var postprocessingDate = $("#postprocessingDate").val();
				var inspectDate = $("#inspectDate").val();
				
				if(processDate == "" && postprocessingDate == "" && inspectDate == ""){
					alert("한개 이상의 날짜를 입력해주세요");
					return;
				}
				
				//alert(processDate + ", " + postprocessingDate + ", inspectDate = " + inspectDate);
				opener.${callback}(processDate, postprocessingDate, inspectDate);
				window.close();
			});
			
		});
	
	</script>
</div>
</body>
</html>
