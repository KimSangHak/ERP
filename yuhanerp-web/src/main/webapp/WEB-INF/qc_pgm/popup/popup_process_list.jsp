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
        	<h1>가공품 입고 리스트
			</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 회원찾기 -->
            <div class="member">
                <form method="GET" id="FindForm">
                <input type="hidden" id="orderId" value="${orderId }" />
                <div class="moSearch">
                	<!-- 부서별 검색일경우 -->
                	<ul>
                        <li>
                        	<select id=searchType name="searchType" class="w120">
                                <option value="0" <c:if test="${searchType == '0'}">selected</c:if>>전체</option>
                                <option value="1" <c:if test="${searchType == '1'}">selected</c:if>>미 입고품</option>
                                <option value="2" <c:if test="${searchType == '2'}">selected</c:if>>입고품</option>
                            </select>
                        </li>
                        <li><a href="#" id="SearchButton" class="btn_blue">검색</a></li>
                    </ul>
                    <!-- //부서별 검색일경우 -->
                </div>
				</form>
            </div>
            <!-- //회원찾기 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">
                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:8%;" />
                        <col style="width:10%;" />
                        <col style="width:5%;" />
                        <col style="width:6%;" />
                        <col style="width:5%;" />
                        <col style="width:4%;" />
                        <col style="width:6%;" />
                        <col style="width:7%;" />
                        <col style="width:6%;" />
                        <col style="width:4%;" />
                        <col style="width:6%;" />
                        <col style="width:4%;" />
                        <col style="width:5%;" />
                        <col style="width:6%;" />
                        </colgroup>
                        <thead>
                        	<tr>
	                            <th>도면번호</th>
	                            <th>Description</th>
	                            <th>설계자</th>
	                            <th>Mat’l</th>
	                            <th>열처리</th>
	                            <th>수량</th>
	                            <th>후처리</th>
	                            <th>가공업체</th>
	                            <th>가공납기</th>
	                            <th>검사필요</th>
	                            <th>검사일</th>
	                            <th>판정</th>
	                            <th>인수자</th>
	                            <th>현재공정</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="jobitem" items="${items}">
                            <tr>
	                           	<td>${jobitem.drawingFullNo }</td>
	                           	<td>${jobitem.description }</td>
	                           	<td>${jobitem.drawingUserName }</td>
	                           	<td>${jobitem.material }</td>
	                           	<td>${jobitem.thermal }</td>
	                           	<td>${jobitem.quantity }/${jobitem.spare }</td>
	                           	<td>${jobitem.postprocessing }</td>
	                           	<td>${jobitem.outSourceName }</td>
                          		<c:choose>
               						<c:when test="${jobitem.processCompleted eq 'Y'}">
               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.processFinishDate }" /></td>
               						</c:when>
               						<c:otherwise>
               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.processFinishPlanDate }" /></td>
               						</c:otherwise>                       				
               					</c:choose>
	                           	<td>${jobitem.checking }</td>
	                           	
                          		<c:choose>
               						<c:when test="${jobitem.inspectId gt 0}">
               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.inspectDate }" /></td>
               							<td>${jobitem.inspectResult }</td>
               						</c:when>
               						<c:otherwise>
               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.inspectPlanDate }" /></td>
               							<td> </td>
               						</c:otherwise>                       				
               					</c:choose>
	                           	<td>${jobitem.assemblyTrToName }</td>
	                           	<td>${jobitem.currentStageText }-${jobitem.workStatusText }</td>
                        	</tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- //게시판 -->

            
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
		function	getFindParameter(){
			return "/qcPgm/popup/process_list?key=" + escape( $("#orderId").val() ) + "&searchType=" + escape( $("#searchType").val() );
		}
		$(document).ready(function(){
			
			$("#SearchButton").click(function(e){
				e.preventDefault();

				location.href=getFindParameter();
			});			
		});
	
	</script>

</body>
</html>
