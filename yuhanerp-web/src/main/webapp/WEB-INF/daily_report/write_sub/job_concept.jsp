<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                        <!-- 2타이틀 -->
                    	<div class="listTit">
                        	<h3>설비 Concept 진행 및 예정</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:80px;" />
                                <col style="width:100px;" />
                                <col style="width:190px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:50px;" />
                                <col style="width:60px;" />
                                <col style="width:120px;" />
                                <col style="width:60px;" />
                                <col style="width:20px;" />
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
                                        <th>비고</th>
                                        <th>수정</th>
                                        <th>확인</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach items="${data }" var="item">
                                	<tr id="Concept${item.id }">
                                        <td>${item.orderNo }</td>
                                        <td>${item.customerName }</td>
                                        <td id="taLeft"><input type="text" name="device" value="<c:out value="${item.device }" />"></td>                                        
                                        <td><input type="text" name="quantity" value="${item.quantity }"></td>
                                        <td>${item.businessUserName }</td>
                                        <td>${item.designUserName }</td>
                                        <td><input type="text" name="customerUser" value="<c:out value="${item.customerUser }" />"></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value="${item.orderDate}" /></td>
                                        <td id="taLeft"><textarea rows="3" name="note"><c:out value="${item.note }" /></textarea></td>
                                        <td>
                                        	<select name="requestedAction" class="requestedActionSelector">
                                                <option value="edit">수정</option>
                                                <option value="delete">삭제</option>
                                                <option value="finish">컨셉완료</option>
                                            </select>
                                        </td>
                                        <td><a href="#" class="btn_line_gray ButtonActionForConcept" data-target="Concept${item.id }">확인</a>
                                        	<input type="hidden" name="orderNo" value="${item.orderNo }" />
                                			<input type="hidden" name="id" value="${item.id }" />           
                                			<input type="hidden" name="conceptFileNo" />                             
                                        	<input type="hidden" name="orderType" value="${item.orderType}" />
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->

