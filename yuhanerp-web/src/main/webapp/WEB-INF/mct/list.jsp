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
                        <li>가공현황</li>
                    </ul> 
                    <h2>(사내)가공현황</h2>
                </div>
                <!-- 서브 타이틀 -->

                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:180px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
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
                                        <th>완료예정</th>
                                        <th>시작일</th>
                                        <th>기계번호</th>
                                        <th>작업자</th>
                                        <th>완료수량</th>
                                        <th>현재상태</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr data-id="${item.drawingId }">
                                        <td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.postprocessing }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.processDate }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd HH:mm" value = "${item.startDatetime }" /></td>
                                        <td>${item.mctNum }</td>
                                        <td>${item.userName }</td>
                                        <td>${item.workQuantity }</td>
                                        <td>${item.finishStatusName }</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
	</layout:put>
	<layout:put block="BodyScriptBlock2">

	</layout:put>
</layout:extends>