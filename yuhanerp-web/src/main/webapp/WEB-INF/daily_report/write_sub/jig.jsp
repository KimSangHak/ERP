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
                                <col style="width:60px;" />
                                <col style="width:130px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:100px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:80px;" />
                                <col style="width:20px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order No</th>
                                        <th>업체</th>
                                        <th>납품일</th>
                                        <th>실납품</th>
                                        <th>Device</th>
                                        <th>수량</th>
                                        <th>출고일</th>
                                        <th>영업</th>
                                        <th>설계</th>
                                        <th>고객<br/>담당</th>
                                        <th>내부단가</th>
                                        <th>견적금액</th>
                                        <th>내고금액</th>
                                        <th>비고</th>
                                        <th>발주일</th>
                                        <th>출도일</th>
                                        <th>구매일</th>
                                        <th>가공일</th>
                                        <th>검사일</th>
                                        <th>조립일</th>
                                        <th>배송</th>
                                        <th>현재공정</th>
                                        <th>수정</th>
                                        <th>확인</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach items="${items }" var="item">
                                	<tr id="JobJig${item.id }" data-id="${item.id }">
                                        <td>
											<input type="hidden" name="orderNo" value="${item.orderNo }" />
                                			<input type="hidden" name="id" value="${item.id }" />                                        
                                        	<a href="#" class="edit_estimation">${item.orderNo }</a>                                        
                                        </td>
                                        <td>${item.customerName }</td>
                                        <td><input type="text" class="AutoDatePicker" name="installDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.installDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="realInstallDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.realInstallDate}" />"></td>
                                        <td id="taLeft"><input type="text" name="device" value="${item.device }"></td>
                                        <td><input type="text" name="quantity" value="${item.quantity }"></td>
                                        <td><input type="text" class="AutoDatePicker" name="shippingDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.shippingDate}" />" ></td>
                                        <td>
                                        	
                                        	<c:import url="/internal/util/select/business">
                                        		<c:param value="businessUserId" name="controlName"/>
                                        		<c:param value="${item.businessUserId }" name="defaultValue" />
                                        	</c:import>
                                        </td>
                                        <td>
                                        	
                                        	<c:import url="/internal/util/select/design">
                                        		<c:param value="designUserId" name="controlName"/>
                                        		<c:param value="${item.designUserId }" name="defaultValue" />
                                        	</c:import>
                                        </td>
                                        <td><input type="text" name="customerUser" value="${item.customerUser }"></td>
                                        <td><input type="number" name="internalUnitPrice" value="${item.internalUnitPrice }"></td>
                                        <td><input type="number" name="estimatedPrice" value="${item.estimatedPrice }"></td>
                                        <td><input type="number" name="estimatedPrice" value="${item.negotiatedPrice }"></td>
                                        <td id="taLeft"><textarea name="note">${item.note }</textarea></td>
                                        <td><input type="text" class="AutoDatePicker" name="orderDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.orderDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="designDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.designDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="purchaseDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.purchaseDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="processDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.processDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="testDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.testDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="assemblyDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.assemblyDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="deliveryDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.deliveryDate}" />"></td>                                        
                                        <td>${item.jobStatus }</td>
                                        <td>
                                        	<select name="requestedAction">
                                            	<option>리스트목록</option>
                                                <option value="edit" selected>수정</option>
                                                <option value="delete">삭제</option>
                                                <option value="deliver">발송</option>
                                                <option value="finish">완료</option>
                                            </select>
                                        </td>
                                        <td><a href="#" data-target="JobJig${item.id }" class="btn_line_gray ButtonActionForJobJig">확인</a></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->