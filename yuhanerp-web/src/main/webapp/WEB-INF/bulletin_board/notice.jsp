<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<link rel="stylesheet" type="text/css" href="/css/display.css">
</head>

<body>
<div id="container">


	<div class="display">

		<!-- 타이틀 -->
    	<h1>알람 현황</h1>
        <!-- /타이틀 -->
        
        <!-- 내용 -->
        <div class="conTable">
        	<table>
            	<caption> </caption>
                <colgroup span="2">
                <col style="width:300px;" />
                <col style="width: ;" />
                </colgroup>
                <thead>
                    <tr>
                        <th>상태</th>
                        <th>내용</th>
                    </tr>
                </thead>
                
                <tbody>
                	<c:forEach items="${LIST }" var="item">
                    <tr>
                        <td>${item.messageType }</td>
                        <td id="tdLeft">
                        	<p>${item.orderNo } ${item.device }</p>
                        	<p>${item.message }</p>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- /내용 -->
        
    </div>
    
</div>

</body>
</html>
