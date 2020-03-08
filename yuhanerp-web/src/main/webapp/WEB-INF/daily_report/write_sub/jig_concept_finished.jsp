<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
                        <!-- 2타이틀 -->
                    	<div class="listTit">
                        	<h3>치공구 Concept  완료</h3>
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
                                <!-- <col style="width:50px;" />-->
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:120px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:115px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:90px;" />
                                <col style="width:20px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order No</th>
                                        <th>업체</th>
                                        <th>Device</th>
                                        <!-- <th>컨셉완료일</th> -->
                                        <th>수량</th>
                                        <th>영업</th>
                                        <th>설계</th>
                                        <th>고객<br/>담당</th>
                                        <th>비고</th>
                                        <th>등록일</th>
                                        <th>내부단가<br/> 공유일</th>
                                        <th>현재공정</th>
                                        <th>컨셉도면</th>
                                        <th>내부단가</th>
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
                                        <!-- <td><input type="text" name="conceptFinishDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.conceptFinishDate}" />"></td>  -->
                                        <td><input type="text" name="quantity" value="${item.quantity}"></td>
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
                                        <td ><input type="text" name="customerUser" value="<c:out value="${item.customerUser }" />"></td>
                                        <td id="taLeft"><textarea name="note">${item.note }</textarea></td>
                                        <td><input type="text" class="AutoDatePicker" name="orderDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.orderDate}" />"></td>
                                        <td><input type="text" class="AutoDatePicker" name="internalUnitPriceSharedDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.internalUnitPriceSharedDate}" />"></td>
                                        <td>${item.currentStageDescription}</td>
                                        <td><a href="#" data-id="${item.id }" data-pos="Concept${item.id }" data-file-section="CONCEPT_DRAWING" data-field="conceptFileNo"  class="btn_line_gray FileRegistrationButton">등록</a></td>
                                        <td><a href="#" data-id="${item.id }" data-pos="Concept${item.id }" data-file-section="CONCEPT_DRAWING" data-field="internalPriceFileNo"  class="btn_line_gray FileRegistrationButton">등록</a></td>
                                        <td>
                                        	<select name="requestedAction">
                                                <option value="">선택</option>
                                                <option value="edit">수정</option>
                                                <option value="delete">삭제</option>
                                                <option value="A">단가계산</option>
                                                <option value="B">Conform대기</option>
                                                <option value="C">견적작업</option>
                                                <option value="D">견적송부</option>
                                                <option value="F">작업지시(발주)</option>
                                            </select>
                                        </td>
                                        <td><!-- href="javascript:PopWin('01_business02_pop13.html','600','600','no');" -->
	                                        <a  class="btn_line_gray ButtonActionForConcept" data-target="Concept${item.id }">확인</a>
                                        	<input type="hidden" name="orderNo" value="${item.orderNo }" />
                                			<input type="hidden" name="id" value="${item.id }" />
                                            <input type="hidden" name="internalPriceFileNo" />
                                			<input type="hidden" name="conceptFileNo" />                                        	
                                        	<input type="hidden" name="orderType" value="${item.orderType}" />
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->