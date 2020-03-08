<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/"/>
	<meta charset="utf-8">
	<title></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/display.css">
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	
</head>

<body>
<div id="container">


	<div class="display">

		<!-- 타이틀 -->
    	<h1>
       	<c:choose>
       		<c:when test="${orderType ne 'JIG' }">
       			설비
       		</c:when>
       		<c:otherwise>
       			치공구
       		</c:otherwise>
       	</c:choose>
    	 진행현황</h1>
        <!-- /타이틀 -->
        
        <!-- 내용 -->
        <div class="conTable">
           	<table>
               	<caption> </caption>
                   <colgroup span="2">
	                <col style="width:150px;" />
	                <col style="width:340px;" />
	                <col style="width:160px;" />
	                <col style="width:135px;" />
	                <col style="width:135px;" />
	                <col style="width:140px;" />
	                <col style="width:140px;" />
	                <col style="width:140px;" />
	                <col style="width:140px;" />
	                <col style="width:140px;" />
                   </colgroup>
               	<thead>
                   	<tr>
                        <th>Order<br/>No</th>
                        <th>제품명</th>
                        <th>고객사</th>
                        <th>납품일</th>
                        <th>작업<br/>지시일</th>
                        <th>설계<br/>완료일</th>
                        <th>가공<br/>완료일</th>
                        <th>구매<br/>완료일</th>
                        <th>조립<br/>완료일</th>
                        <th>PGM<br/>완료일</th>
                   	</tr>
                </thead>
                <tbody>
                   	<c:forEach var="jobitem" items="${items}">
                   	<tr data="${jobitem.id }">
                       	<td>${jobitem.orderFullNo }</td>
                       	<td>${jobitem.device }</td>
                       	<td>${jobitem.customerName }</td>
                       	<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.installDate }" /></td>
                       	<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.orderDate }" /></td>
                        <td>
	                    	<c:choose>
	                       	<c:when test="${jobitem.designStatus eq 'F' }">
	                       		완료일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.designEnd }" /><br>
	                       	</c:when>
	                       	<c:otherwise>
	                       		<c:if test="${jobitem.designDate ne null}">
	                       		예정일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.designDate }" /><br>
	                       		</c:if>
	                    	</c:otherwise>
	                    	</c:choose>
	                    	${jobitem.designProgress }%<br>
	                    	<c:choose>
	                    		<c:when test="${jobitem.designProgress < 30 }">
	                    			<div class="gArea"><div class="red" style="width:${jobitem.designProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.designProgress < 50 }">
	                    			<div class="gArea"><div class="yellow" style="width:${jobitem.designProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.designProgress < 80 }">
	                    			<div class="gArea"><div class="blue" style="width:${jobitem.designProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="gArea"><div class="green" style="width:${jobitem.designProgress }%;"></div></div>
	                    		</c:otherwise>
	                    	</c:choose>
                        </td>
                       	<td>
                       		<c:if test="${jobitem.GetProcessProg() < 100}">
                       		<c:if test="${jobitem.processDate ne null}">
                       		예정일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.processDate }" /><br>
                       		</c:if>
	                    	</c:if>
	                    	${jobitem.GetProcessProg() }%<br>
	                    	<c:choose>
	                    		<c:when test="${jobitem.GetProcessProg() < 30 }">
	                    			<div class="gArea"><div class="red" style="width:${jobitem.GetProcessProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.GetProcessProg() < 50 }">
	                    			<div class="gArea"><div class="yellow" style="width:${jobitem.GetProcessProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.GetProcessProg() < 80 }">
	                    			<div class="gArea"><div class="blue" style="width:${jobitem.GetProcessProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="gArea"><div class="green" style="width:${jobitem.GetProcessProg() }%;"></div></div>
	                    		</c:otherwise>
	                    	</c:choose>
                       	</td>
                       	<td>
                       		<c:if test="${jobitem.GetPurchaseProg() < 100}">
                       		<c:if test="${jobitem.purchaseDate ne null}">
                       		예정일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.purchaseDate }" /><br>
                       		</c:if>
                       		</c:if>
                       		
                       		<c:choose>
                       			<c:when test = "${jobitem.GetPurchaseProg() > 0 }">
                       				${jobitem.GetPurchaseProg() }%<br>
                       			</c:when>
                       			<c:otherwise>
                       					구매품 없음<br>
                       			</c:otherwise>
                       		
                       		</c:choose>
	                    	
	                    	<c:choose>
	                    		<c:when test="${jobitem.GetPurchaseProg() < 30 }">
	                    			<div class="gArea"><div class="red" style="width:${jobitem.GetPurchaseProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.GetPurchaseProg() < 50 }">
	                    			<div class="gArea"><div class="yellow" style="width:${jobitem.GetPurchaseProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.GetPurchaseProg() < 80 }">
	                    			<div class="gArea"><div class="blue" style="width:${jobitem.GetPurchaseProg() }%;"></div></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="gArea"><div class="green" style="width:${jobitem.GetPurchaseProg() }%;"></div></div>
	                    		</c:otherwise>
	                    	</c:choose>
                       	</td>
                       	<td>
	                    	<c:choose>
	                       	<c:when test="${jobitem.assemblyStatus eq 'F' }">
	                       		완료일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.assemblyEnd }" /><br>
	                       	</c:when>
	                       	<c:otherwise>
	                       		<c:if test="${jobitem.assemblyDate ne null}">
	                       		예정일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.assemblyDate }" /><br>
	                       		</c:if>
	                    	</c:otherwise>
	                    	</c:choose>
	                    	${jobitem.assemblyProgress }%<br>
	                    	<c:choose>
	                    		<c:when test="${jobitem.assemblyProgress < 30 }">
	                    			<div class="gArea"><div class="red" style="width:${jobitem.assemblyProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.assemblyProgress < 50 }">
	                    			<div class="gArea"><div class="yellow" style="width:${jobitem.assemblyProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.assemblyProgress < 80 }">
	                    			<div class="gArea"><div class="blue" style="width:${jobitem.assemblyProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="gArea"><div class="green" style="width:${jobitem.assemblyProgress }%;"></div></div>
	                    		</c:otherwise>
	                    	</c:choose>
                       	</td>
                       	<td>
						<c:if test="${jobitem.orderType ne 'JIG'}">
	                    	<c:choose>
	                       	<c:when test="${jobitem.programStatus eq 'F' }">
	                       		완료일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.programEnd }" /><br>
	                       	</c:when>
	                       	<c:otherwise>
	                       		<c:if test="${jobitem.programDate ne null}">
	                       		예정일<br><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.programDate }" /><br>
	                       		</c:if>
	                    	</c:otherwise>
	                    	</c:choose>
	                    	${jobitem.programProgress }%<br>
	                    	<c:choose>
	                    		<c:when test="${jobitem.programProgress < 30 }">
	                    			<div class="gArea"><div class="red" style="width:${jobitem.programProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.programProgress < 50 }">
	                    			<div class="gArea"><div class="yellow" style="width:${jobitem.programProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:when test="${jobitem.programProgress < 80 }">
	                    			<div class="gArea"><div class="blue" style="width:${jobitem.programProgress }%;"></div></div>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="gArea"><div class="green" style="width:${jobitem.programProgress }%;"></div></div>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </c:if>
                       	</td>
                    </tr>
                	</c:forEach>
            	</tbody>
        	</table>
        </div>
        <!-- /내용 -->
        
    </div>
    
</div>
	<script type="text/javascript">

	</script>

</body>
</html>
    