<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/datatables.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
	<script type="text/javascript" src="/js/ux.js"></script>	
	<script type="text/javascript" src="/js/datatables.min.js"></script>
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	
</head>
<body>


	<c:choose>
		<c:when test="${result eq '1'}">
			<h1 style="color:red;">DrawingNo(도면번호)가 이관 될 order에 있지 않습니다.</h1>
		</c:when>
		
		<c:when test="${result eq '2'}">
			<h1 style="color:red;">이관 할 Order에 재고 도면 수량 보다 많은 수량을 이관 할 수 없습니다.(가공품 재고 부족)</h1>
		</c:when>
		
		<c:when test="${result eq '3'}">
			<h1 style="color:red;">이관 될 수량이 0일 순 없습니다.</h1>
		</c:when>
		
		<c:when test="${result eq '4'}">
			<h1 style="color:red;">이관 받을 Order에서 도면 제작 수량 보다 많은 수량을 이관 받을 순 없습니다.</h1>
		</c:when>
	
	</c:choose>

</body>
</html>