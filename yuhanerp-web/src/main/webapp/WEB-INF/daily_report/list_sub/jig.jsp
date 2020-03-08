<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                        <!-- 3타이틀 -->
                    	<div class="listTit">
                        	<h3>치공구 진행</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:30px;" />
                                <col style="width:70px;" />
                                <col style="width:60px;" />
                                <c:if test="isBusinessUser">                                
                                <col style="width:60px;" />
                                </c:if>                                
                                <col style="width:130px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:150px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:115px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order No</th>
                                        <th>업체</th>
                                        <th>납품일</th>
                                        <c:if test="isBusinessUser">
                                        <th>실납품일</th>
                                        </c:if>
                                        <th>Device</th>
                                        <th>수량</th>
                                        
                                        <th>출고일</th>
                                        <th>영업</th>
                                        <th>설계</th>
                                        <th>고객<br/>담당</th>
                                        <th>비고</th>
                                        
                                        <th>발주일</th>
                                        <th>출도일</th>
                                        <th>구매일</th>
                                        <th>가공일</th>
                                        <th>검사일</th>
                                        
                                        <th>조립일</th>
                                        <th>배송</th>
                                        <th>현재공정</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach var="item" items="${jobs}">
                                	<tr data-id="${item.id }">
                                        <td>
                                        	<c:choose>
                         						<c:when test="${isupdate eq 'Y'}">
                         							<a href="#" class="edit_estimation">${item.orderNo }</a>
                         						</c:when>
                         						<c:otherwise>
                         							${item.orderNo }
                         						</c:otherwise>                       				
                         					</c:choose>
                                        	
                                        
                                        </td>
                                        <td>${item.customerName }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.installDate }" /></td>
                                        <c:if test="isBusinessUser">
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.realInstallDate }" /></td>
                                        </c:if>
                                        <td id="taLeft"><c:out value="${item.device }"></c:out></td>
                                        <td>${item.quantity }</td>
                                        
                                        <td>16.11.03</td>
                                        <td>${item.businessUserName }</td>
                                        <td>${item.designUserName }</td>
                                        <td>${item.customerUser }</td>                                        
                                        <td id="taLeft">${item.note }</td>
                                        
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.orderDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.designDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.purchaseDate}" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.processDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.testDate }" /></td>
                                        
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.assemblyDate }" /></td>                                        
                                        <td>(배송정보)</td>
                                        <td>${item.jobStatus }</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
