<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/" />
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">
    <link rel="stylesheet" type="text/css" href="css/multi.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
    <script type="text/javascript" src="/js/ux.js"></script>
    <!-- 트리메뉴 -->
    <script type="text/javascript" src="js/checktree.js"></script>
    <script type="text/javascript">
		var checkmenu = new CheckTree('checkmenu');
	</script>
    <!-- //트리메뉴 -->
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>구매 항목 삭제 or 사유 입력
</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 설명 -->
            <div class="popText" >
            	<!-- 항목1 -->
            	선택한 구매항목의 삭제 사유를 입력하시기 바랍니다.<br/>
            	(주의) 삭제 시 견적요청 리스트도 함께 삭제 됩니다
                
                <!-- 항목2 -->
                <!--
                취소 사유를 입력하시오.
				-->
            </div>
            <!-- //설명 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 폼 영역 -->
                <div class="popBoard">
                    <textarea id="cancelReasonText" rows="11"></textarea>
                </div>
                <!-- //폼 영역 -->
                
                <!-- 팝업 버튼 -->
                <div class="popBtn">
                    <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                    <a href="#" id="SaveButton" class="btn_blue">확인</a>
                </div>
                <!-- //팝업 버튼 -->
            
            </div>
            <!-- //테이블 콘텐츠 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->

	</div>
    
<!-- 멀티셀렉트 -->
<script src='js/multiselect03.js'></script>
<script src='js/multiselect02.js'></script>
<script src="js/multiselect01.js"></script>  
<!-- //멀티셀렉트 -->
</div>

	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("#SaveButton").click(function(e){
				
				var text = $("#cancelReasonText").val();
				
				window.close();
				
				// TODO :: 개발 환경상 한계에 의한 수정 
				// 크롬은 alert 띄우는 시점의 active window 에서 alert 창이 표출
				// 취소 사유 입력 팝업에서 입력받고 아래 콜백을 먼저 호출하여 opener 측에서 alert 시키면 지금 팝업이 아닌 opener 쪽에 alert 이 뜬다! 
				// ==> 임시 조치로 창을 닫고 처리 하는 방향으로..
				
				opener.${callback}(window, "${key}", text );				
			});			
		});
	
	</script>

</body>
</html>
