<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>설비 진행</h3>
                        </div>	
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:70px;" />
                                <col style="width:100px;" />
                                <col style="width:70px;" />
                                <col style="width:250px;" />
                                <col style="width:40px;" />                                
                                <col style="width:70px;" />
                                <col style="width:60px;" />

                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:240px;" />                                
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />

<!--
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:70px;" />
                                <col style="width:70px;" />
-->
                                <col style="width:115px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order <br/> No</th>
                                        <th>업체</th>
                                        <th>납품일</th>
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

<!--
                                        <th>가공일</th>
                                        <th>검사일</th>
                                        <th>조립일</th>
                                        <th>베선<br/>/PGM</th>
                                        <th>배송</th>
-->
                                        <th>현재<br/>공정</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                <c:forEach var="item" items="${drawing}">
                                	<tr data-id="${item.id }" data-orderNoBase="${item.orderNoBase }" data-orderNoExtra="${item.orderNoExtra }">
                                		<td>
                                		
                                			<c:choose>
                                				<c:when test="${item.designStatus eq 'F' }">
                                					${item.orderNo }
                                				</c:when>
                                				<c:otherwise>
                                					<a href="#" class="popup_job_progress_design" style="color:red">${item.orderNo }</a>
                                				</c:otherwise>
                                			</c:choose>
                                			
                                		</td>
                                		<td>${item.customerName }</td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.installDate}"/></td>
                                		<td>${item.device }</td>
                                		<td>${item.quantity }</td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.shippingDate }"/></td>
                                		<td>${item.businessUserName }</td>             
                                		<td>${item.designUserName }</td>
                                		<td>${item.customerUser }</td>                                		  
                                		<td>${item.note }</td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.orderDate }"/></td>                               
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.designDate }"/></td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.purchaseDate }"/></td>

<!--
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.processDate }"/></td>                                		
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.testDate }"/></td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.assemblyDate }"/></td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.programDate }"/></td>
                                		<td><fmt:formatDate pattern = "yy-MM-dd" value = "${item.deliveryDate }"/></td>
-->
               							<td>${item.jobStatus }</td>  
									</tr>               						 
								</c:forEach>
                                     
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->