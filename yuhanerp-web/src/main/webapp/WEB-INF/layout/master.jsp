<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 메인 페이지용 마스터 페이지
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title>Yuhan NCI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>

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
			<!-- 
			
			//상단 -->

			<!-- 메뉴 삽입 -->
			<c:import url="/internal/menu"/>
			<!-- 메뉴 삽입 -->

		</div>
		<!-- //HEADER -->

		<!-- CONTENTS -->
		<div class="mainBody">
			<div class="main_contents">
				
				<layout:block name="MainContentsBlock">
				
				</layout:block>

			</div>

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
		<!-- //CONTENTS -->
			
	</div>
	<layout:block name="BodyScriptBlock">
	</layout:block>
				
</body>

</html>
