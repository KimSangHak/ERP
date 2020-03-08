<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<base href="/" />
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

    <script type="text/javascript" src="js/menu.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popPrint">
    	
        <div class="popTitArea">
        	<h1>영업일보</h1>
            <div class="lastData">마지막 수정일 : 2016년 10월 20일</div>
        </div>
    
        <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                
                     
                     
                      	<c:import url="/internal/dailyreport/list/job/progress"/>
                        <c:import url="/internal/dailyreport/list/job/concept/finished"/>
                        <c:import url="/internal/dailyreport/list/job/concept"/>
                        
                        <c:import url="/internal/dailyreport/list/jig/progress"/>
                        <c:import url="/internal/dailyreport/list/jig/concept/finished"/>
                        <c:import url="/internal/dailyreport/list/jig/concept"/>
                   
                    
                    </div>
                    <!-- //테이블 콘텐츠 -->
        
    <!-- //서브 콘텐츠 -->
	</div>
</div>
</body>
</html>
