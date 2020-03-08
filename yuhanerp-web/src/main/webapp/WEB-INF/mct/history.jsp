<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="/WEB-INF/layout/MctMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공</li>
                        <li>지난 가공리스트</li>
                    </ul> 
                    <h2>지난 가공리스트</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->
                	<form method="GET" id="FindForm">
                    <div class="searchArea">
                    	<div class="searchBox">
                    		
                        	<table>
                            	<caption> </caption>
                                <col style="width:10%;;" />
                                <col style="width:30%;" />
                                <col style="width:10%;" />
                                <col style="width:50%;" />
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w100"> - <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra }" class="w100"> - <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo }" class="w100">
                                    </td>
                                    <th>가공일</th>
                                    <td>
                                        <input type="text" name="mctDateFrom" id="mctDateFrom" value="${mctDateFrom}" class="w100 AutoDatePicker"> - <input type="text" name="mctDateTo" id="mctDateTo" value="${mctDateTo }" class="w100 AutoDatePicker">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn">
                        	표출 데이터 Grouping 기준 : 
							<select  id="orderBy" name="orderBy">
								<option value="All" <c:if test="${param.orderBy == 'All'}">selected</c:if>>전체</option>
								<option value="DW_USER" <c:if test="${param.orderBy == 'DW_USER'}">selected</c:if>>도면번호+작업자</option>
								<option value="DW_MCT" <c:if test="${param.orderBy == 'DW_MCT'}">selected</c:if>>도면번호+가공기계</option>
								<option value="OR" <c:if test="${param.orderBy == 'OR'}">selected</c:if>>Order NO</option>
								<option value="DW" <c:if test="${param.orderBy == 'DW'}">selected</c:if>>도면번호</option>
								<option value="USER" <c:if test="${param.orderBy == 'USER'}">selected</c:if>>작업자</option>
								<option value="MCT" <c:if test="${param.orderBy == 'MCT'}">selected</c:if>>가공기계</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;
                        	<a href="#" class="btn_blue" id="FindButton">검색</a>
                        </div>
                    </div>
                    </form>
                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                        	<c:choose>
                        		<c:when test="${param.orderBy == 'DW_USER'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:180px;" />
                                <col style="width:55px;" />
                                <col style="width:55px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>도면번호</th>
                                    	<th>Description</th>
                                        <th>Mat`l</th>
                                        <th>열처리</th>
                                        <th>후처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>납기일</th>
                                        <th>작업자</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.postprocessing }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.userName }</td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>
                        		<c:when test="${param.orderBy == 'DW_MCT'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:180px;" />
                                <col style="width:55px;" />
                                <col style="width:55px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>도면번호</th>
                                    	<th>Description</th>
                                        <th>Mat`l</th>
                                        <th>열처리</th>
                                        <th>후처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>납기일</th>
                                        <th>기계No</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.postprocessing }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.mctId }</td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>

                        		<c:when test="${param.orderBy == 'OR'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:280px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>Device</th>
                                        <th>납기일</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>
                                
								<c:when test="${param.orderBy == 'DW'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:180px;" />
                                <col style="width:55px;" />
                                <col style="width:55px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>도면번호</th>
                                    	<th>Description</th>
                                        <th>Mat`l</th>
                                        <th>열처리</th>
                                        <th>후처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>납기일</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.postprocessing }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>

								<c:when test="${param.orderBy == 'USER'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:280px;" />
								<col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>Device</th>
										<th>작업자</th>
                                        <th>납기일</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
										<td>${item.userName }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>

                        		<c:when test="${param.orderBy == 'MCT'}">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:280px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>Device</th>
                                        <th>납기일</th>
                                        <th>기계No</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                        <th>최초시작</th>
                                        <th>최종완료</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.mctId }</td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>

                        		<c:otherwise>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:180px;" />
                                <col style="width:55px;" />
                                <col style="width:55px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>도면번호</th>
                                    	<th>Description</th>
                                        <th>Mat`l</th>
                                        <th>열처리</th>
                                        <th>후처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>납기일</th>
                                        <th>기계No</th>
                                        <th>작업자</th>
                                        <th>시작</th>
                                        <th>종료</th>
                                        <th>소요시간</th>
                                        <th>완료수량</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.postprocessing }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.mctId }</td>
                                        <td>${item.userName }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.endDatetime }" /></td>
                                        <td>${item.workTime }</td>
                                        <td>${item.workQuantity }</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                </c:otherwise>
                                
							</c:choose>
                            </table>
                        </div>
                        <!-- //게시판 -->
                    </div>
                    <!-- //테이블 콘텐츠 -->

                </div>
                <!-- //서브 콘텐츠 -->
	</layout:put>
	<layout:put block="BodyScriptBlock2">
		<script type="text/javascript">
		
		function	getFindParameter(){
			return "?orderBy=" + escape( $("#orderBy").val() ) + "&orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() )
			 + "&drawingNo=" + escape( $("#drawingNo").val() ) + "&mctDateFrom=" + escape( $("#mctDateFrom").val() )  + "&mctDateTo=" + escape( $("#mctDateTo").val() );
		}
		
		$(document).ready(function(){
			
			// 검색버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				location.href=getFindParameter();  
			});

		});

		</script>
	</layout:put>
</layout:extends>