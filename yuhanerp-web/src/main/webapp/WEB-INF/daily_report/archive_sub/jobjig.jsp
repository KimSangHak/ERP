<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                    	<!-- 타이틀 -->
                    	<div class="listTit">
                    	<c:if test="${mode == 'job' }">
                        	<h3>설비 진행</h3>
                        </c:if>
                        <c:if test="${mode == 'jig' }">
                        	<h3>치공구 진행</h3>
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
                                <col style="width:60px;" />
                                <col style="width:130px;" />
                                <col style="width:40px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:120px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <c:if test="${mode == 'job' }">
                                <col style="width:70px;" />
                                </c:if>
                                <col style="width:70px;" />
                                <col style="width:70px;" />
                                <c:if test="${mode == 'jig' }">
                                <col style="width:70px;" />
                                </c:if>
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
                                        <th>가공일</th>
                                        <th>검사일</th>
                                        <th>조립일</th>
                                        <c:if test="${mode == 'job' }">                                        
                                        <th>베선<br/>/PGM</th>
                                        </c:if>
                                        <th>배송</th>
                                        <th>AS발주</th>
                                        <c:if test="${mode == 'jig' }">                                        
                                        <th>재제작</th>
                                        </c:if>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach items="${data }" var="item">
                                	<tr>
                                        <td rowspan="3">${item.orderNo }</td>
                                        <td rowspan="3">${item.customerName }</td>
                                        <td rowspan="3"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.installDate }" /></td>
                                        <td id="taLeft">${item.device }</td>
                                        <td>${item.quantity }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.shippingDate }" /></td>
                                        <td>${item.businessUserName }</td>
                                        <td>${item.designUserName }</td>
                                        <td>${item.customerUser }</td>
                                        <td id="taLeft">${item.note }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.orderDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.designDate}" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.purchaseDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.processDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.testDate }" /></td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.assemblyDate }" /></td>
                                        <c:if test="${mode == 'job' }">
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.programDate }" /></td>
                                        </c:if>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.deliveryDate }" /></td>
                                        <td><a href="#" class="ASButton btn_line_gray" data-orderType="${item.orderType }" data-id="${item.id }">AS발주</a></td>
                                        <c:if test="${mode == 'jig' }">
                                        <td><a href="#" class="REButton btn_line_gray" data-orderType="${item.orderType }" data-id="${item.id }">재제작</a></td>
                                        </c:if>
                                    </tr>
                                    <tr>
                                    	<td colspan="17" id="taLeft">
                                        	<ul class="device_img">
                                        		<c:forEach items="${images[item.id] }" var="images">                                        		
                                        			<li><a href="javascript:PopWin('/popup/image_viewer/${images }','800','500','no');" ><img src="/link/${images }"></a></li>
                                        		</c:forEach>                                        		
                                            </ul>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td colspan="17" id="taLeft">
                                        	<dl class="modifyList loadModifyList" data-id="${item.id}" >
                                            	<dt>수정이력</dt>
                                                <dd>...</dd>
                                            </dl>
                                        </td>
                                    </tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->

                        