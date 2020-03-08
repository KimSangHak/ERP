<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<layout:extends name="/WEB-INF/layout/PurchaseMaster.jsp">

	<layout:put block="mainbody">
            
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>견적/발주/입고</li>
                        <c:choose>
                         	<c:when test="${mode eq 'estimate'}">
                            	<li>견적 요청 LIST 조회</li>
                             </c:when>
                             <c:when test="${mode eq 'issue'}">
                                <li>발주 LIST 조회</li>
                             </c:when>
                         </c:choose>
                    </ul>
                    <c:choose>
                    	<c:when test="${mode eq 'estimate'}">
                        	<h2>견적 요청 LIST 조회</h2>
                        </c:when>
                        <c:when test="${mode eq 'issue'}">
                            <h2>발주 LIST 조회</h2>
                        </c:when>
                    </c:choose>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:12%;;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                    <th>검색유형</th>
                                    <td>
                                        <label><input type="radio" name="aa" checked />전체</label>
                                        <label><input type="radio" name="aa" />업체</label>
                                    </td>
                                    <th>Order No.</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w280">
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w140"> - <input type="date" name="" value=" " class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        
                         <c:choose>
                    		<c:when test="${mode eq 'estimate'}">
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:150px;" />
                                	<col style="width:70px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
									<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>견적No</th>
                                        	<th>견적요청일</th>
                                        	<th>OrderNo</th>
                                        	<th>UNIT</th>
                                        	<th>Description</th>
                                        	<th>Model/Size</th>
                                        	<th>Maker</th>
                                        	<th>구매처</th>
                                        	<th>수량</th>
                                        	<th>Spare</th>
                                        	<th>납기일</th>
                                        	<th>견적가</th>
                                        	<th>발주일</th>
                                        	<th>발주가</th>
                                        	<th>최저가</th>
                                        	<th>최고가</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                                		<tr>
                                    		<td id="taLeft"><a href="javascript:PopWin('${path}/purchase/history/estimate/${entry.job_purchase_id}/${entry.request_id}','1600','450','no');">${entry.request_id}</a></td>
                                        	<td>${entry.issue_date}</td>
                                        	<td>${entry.job_order_no}</td>
                                        	<td>${entry.unit_no}</td>
                                        	<td>${entry.description}</td>
                                        	<td>${entry.model_no}</td>
                                        	<td>${entry.maker}</td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.order_quantity}</td>
                                        	<td>${entry.spare}</td>
                                        	<td>${entry.receive_date}</td>
                                        	<td>${entry.estimated_price}</td>
                                        	<td>${entry.OrderIssue_date}</td>
                                        	<td>${entry.issue_pricell}</td>
                                        	<td>${entry.MinPrice}</td>
                                        	<td>${entry.MaxPrice}</td>
                                    	</tr>
                                    	
                                   
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                        	</c:when>
                        	<c:when test="${mode eq 'issue'}">
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	<col style="width:150px;" />
                                	<col style="width:70px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
									<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	<col style="width:50px;" />
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>발주No</th>
                                        	<th>발주일</th>
                                        	<th>OrderNo</th>
                                        	<th>UNIT</th>
                                        	<th>Description</th>
                                        	<th>Model/Size</th>
                                        	<th>Maker</th>
                                        	<th>구매처</th>
                                        	<th>최저가</th>
                                        	<th>최고가</th>
                                        	<th>구매수량</th>
                                        	<th>발주수량</th>
                                        	<th>재고수량</th>
                                        	<th>납기일</th>
                                        	<th>단가</th>
                                        	<th>공급가</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               	 		<fmt:parseNumber var="qty" value="${entry.order_quantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="price" value="${entry.issue_price}" integerOnly="true"/>
                                        <fmt:parseNumber var="Sum" value="${qty*price}" integerOnly="true"/>
                                        
                                        <fmt:parseNumber var="stockqty" value="${entry.stockQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="abQuantity" value="${entry.abQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="remind" value="${stockqty-abQuantity}" integerOnly="true"/>
                               	 		
                                		<tr>
                                		
                                			<td>${entry.request_id}</td>
                                        	<td>${entry.issue_date}</td>
                                        	<td>${entry.job_order_no}</td>
                                        	<td>${entry.unit_no}</td>
                                        	<td>${entry.description}</td>
                                        	<td id="taLeft"><a href="javascript:PopWin('${path}/purchase/issuehistory/${entry.job_purchase_id}/${entry.request_id}/${entry.model_no}','1600','450','no');">${entry.model_no}</a></td>
                                        	<td>${entry.maker}</td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.minPrice}</td>
                                        	<td>${entry.maxPrice}</td>
                                        	<td>${entry.quantity}</td>
                                        	<td>${entry.order_quantity}</td>
                                        	<td>${remind}</td>
                                        	<td>${entry.receive_date}</td>
                                        	<td>${entry.issue_price}</td>
                                        	<td>${Sum}</td>
                                    	</tr>
                                    	
                                   
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                           
                        	</c:when>
                    	</c:choose>
                        	
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
                
                                        		
	</layout:put>
	<layout:put block="BodyScriptBlock2">
		<script type="text/javascript">
			$(document).ready(function(){
			
				$(".SubmitButton").click(function(e){
					$("form").submit();				
				});
			
			});
		</script>
	
	</layout:put>
</layout:extends>