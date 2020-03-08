<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// master2 는 메인 페이지를 제외한,
	// 왼쪽 사이드 메뉴, 나머지 본문을 표기하는 템플릿임
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title>Yuhan NCI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/css/datatables.min.css">
<link rel="stylesheet" type="text/css" href="/css/style.css">

<script type="text/javascript" src="/js/parseurl.js"></script>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/datatables.min.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>
<script type="text/javascript" src="/js/ux.js"></script>

 <script type="text/javascript">
	window.name="parent";
	
	// 2019-03-20 :: 페이징 설정 이동
	var yerpPaingSize = [ 10 ];

</script>

<style type="text/css">
	.ui-widget-header {
		border : 0;
		background : 0;		
	}
</style>

<layout:block name="HeadBlock" >
</layout:block>
</head>

<body class="main">
	<div id="container">

		<!-- HEADER -->
		<div class="header">
			<!-- 상단 -->
			<div class="hTopArea">
				<h1>
					<a href="/main">로고</a>
				</h1>
				<div class="hsearch">
					<input type="text"> <a href="#" class="btn_hsearch">검색</a>
				</div>
			
				<c:import url="/internal/head"/>
			</div>
			<!-- //상단 -->

			<!-- 메뉴 삽입 -->
	
			<c:import url="/internal/menu"/>
	
		</div>
		<!-- //HEADER -->

		<!-- CONTENTS -->
		<div class="subContArea">
			
	    	<!-- 사이드메뉴 -->
	    	<div class="sideMenu">
				<layout:block name="left_side" ></layout:block>
	        </div>
        	<!-- //사이드메뉴 -->
        
	        <div class="subCont">
	        	<div class="subArea">
	        	<layout:block name="main"></layout:block>
	        	</div>
	        </div>		
		</div>
		<!-- //CONTENTS -->
		
			<!-- FOOTER -->
			<div class="footer">
				<div class="copy">copyright ⓒ 2017 yuhan nci. all right
					reserved.</div>
				<div class="admin">관리자 : 김정태 (☎ 1234)</div>
			</div>
			<!-- //FOOTER -->

			<!-- 메세지 버튼 -->
			<div class="btn_message">
				<a href="javascript:PopWin('00_pop_message.html','600','700','no');">메세지</a>
			</div>
			<!-- //메세지 버튼 -->
			<!-- 맨 위로 가기 아이콘 -->
			<div id="back-top">
				<p class="btn_top">
					<a href="#top"></a>
				</p>
			</div>
			<!-- /맨 위로 가기 아이콘 -->


			
	</div>
	<layout:block name="BodyScriptBlock">
	</layout:block>
				
	<script type="text/javascript">
		// 왼쪽 측면 메뉴에 현재 페이지 링크를 선택된것처럼 만드는 코드
		$(document).ready(function(){
			
			var uri = parseURL(location.href).path;	// 현재 URL 중 path 부분
					
			$("div[class='sub_menu'] > div > dl > dd a[href]").each( function(idx) {
				if( $(this).attr("href") == uri)
					$(this).parent().addClass("sel");
				else
					$(this).parent().removeClass("sel");
			});			
						
		});
	</script>
	<script type="text/javascript" src="/js/auto_datepicker.js" ></script>
</body>

</html>
