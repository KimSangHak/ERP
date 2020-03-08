<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                        
                        <!-- 3타이틀 -->
                    	<div class="listTit">
							<c:if test="${mode == 'job' }">                    	
                        	<h3>설비 Concept 진행 및 예정</h3>
                        	</c:if>
							<c:if test="${mode == 'jig' }">                    	
                        	<h3>치공구 Concept 진행 및 예정</h3>
                        	</c:if>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:30px;" />
                                <col style="width:70px;" />
                                <col style="width:130px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:120px;" />
                                <col style="width:60px;" />
                                <col style="width:100px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order No</th>
                                        <th>업체</th>
                                        <th>Device</th>
                                        <th>수량</th>
                                        <th>영업</th>
                                        <th>설계</th>
                                        <th>고객<br/>담당</th>
                                        <th>등록일</th>
                                        <th>컨셉완료예정</th>
                                        <th>비고(진행 정보)</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach items="${data}" var="item">                                	
                                	<tr>
                                        <td rowspan="2">${item.orderNo }</td>
                                        <td rowspan="2">${item.customerName }</td>
                                        <td>${item.device }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.businessUserName }</td>
                                        <td>${item.designUserName }</td>
                                        <td>${item.customerUser }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.orderDate}" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.conceptFinishDate}" /></td>
                                        <td id="taLeft">${item.note }</td>
                                    </tr>
                                    <tr>
                                    	<td colspan="17" id="taLeft">
                                        	<dl class="modifyList">
                                            	<dt>수정이력</dt>
                                                <dd>16.09.23 전진표 설계(박관규=>전진표) 수정</dd>
                                                <dd>16.09.25 박종선 비고(-진공이젝터 방향, - Air Gun설치) 추가</dd>
                                            </dl>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        

    