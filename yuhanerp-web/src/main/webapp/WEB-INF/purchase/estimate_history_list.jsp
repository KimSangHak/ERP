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
                    <form method="get">
                    
                    <c:choose>
                    	<c:when  test="${mode eq 'estimate'}">
                    		<div class="searchBox">
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:12%;;" />
                                	<col style="width:21%;" />
                                	<col style="width:12%;" />
                                	<col style="width:21%;" />
                                	<col style="width:12%;" />
                                	<col style="width:21%;" />
                                	</colgroup>
                                 	<tr>
                                   	 <th>검색유형</th>
                                    	<td>
                                        	<select name="kind" id="kind" style="width:100px;">
                                				<option value="A">전체조회</option>
                                				<option value="H">과거이력제외</option>
                         					</select>
                                    	</td>
                                    	<th>Device</th>
                                    	<td><input type="text" name="desc" value="${desc}" class="w280"></td>
                                    	 <th>Order No.</th>
                                   		 <td>
                                        	<input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    	</td>
                                	</tr>
                                	<tr>
                                    	<th>Maker</th>
                                    	<td><input type="text" name="maker" value="${maker}" class="w280"></td>
                                    	<th>견적처</th>
                                    	<td><input type="text" name="customer" value="${customer}" class="w280"></td>
                                    
                        				<th>견적요청일</th>              				
                                   	 	<td>
                                       	 	<input type="date" name="DateBegin" value="${DateBegin}" class="w140"> - <input type="date" name="DateEnd" value="${DateEnd}" class="w140">
                                    	</td>
                                	</tr>
                                 
                            	</table>
                        	</div>
                        </c:when>
                        <c:otherwise>
                        		<div class="searchBox">
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:12%;;" />
                                	<col style="width:21%;" />
                                	<col style="width:12%;" />
                                	<col style="width:21%;" />
                                	<col style="width:12%;" />
                                	<col style="width:21%;" />
                                	</colgroup>
                                 	<tr>
                                   	 <th>검색유형</th>
                                    	<td>
                                        	<select name="kind" id="kind" style="width:100px;">
                                				<option value="A">전체조회</option>
                                				<option value="H">직접거래조회</option>
                         					</select>
                                    	</td>
                                    	<th>Device</th>
                                    	<td><input type="text" name="desc" value="${desc}" class="w280"></td>
                                    	 <th>Order No.</th>
                                    	<td>
                                        	<input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    	</td>
                                	</tr>
                                	<tr>
                                    	<th>발주처</th>  
                                    	<td><input type="text" name="customer" value="${customer}" class="w280"></td>
                                    
                        				<th>발주요청일</th>              				
                                   	 	<td colspan="4">
                                       	 	<input type="date" name="DateBegin" value="${DateBegin}" class="w140"> - <input type="date" name="DateEnd" value="${DateEnd}" class="w140">
                                    	</td>
                                	</tr>
                                 
                            	</table>
                        	</div>
                        
                        </c:otherwise>
                       </c:choose>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
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
                                	<col style="width:30px;" />
                                	<col style="width:90px;" />
                                	<col style="width:120px;" />
                                	<col style="width:90px;" />
                                	<col style="width:200px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:50px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />                             	                         
                                	</colgroup>
                            		<thead>
                                		<tr>
                                			<th>No</th>
                                        	<th>견적No</th>
                                        	<th>견적요청일</th>
                                        	<th>OrderNo</th>
                                        	<th>Device</th>
                                        	<th>도면구매처</th>
                                        	<th>견적처</th>
                                        	<th>품목수량</th>
                                        	<th>납기일</th>
                                        	<th>견적가 총합</th>                                    
                                        	
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                                		<c:set var="No" value="${pageNo + 1}"></c:set>
                               	 		<c:forEach items="${data}" var="entry">
                                		<tr>
                                			<td>${No}</td>
                                    		<td id="taLeft">
                                    			<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="javascript:PopWin('${path}/purchase/history/estimate/${entry.estimateRequestId}','2000','450','no');">${entry.estimateRequestId}</a>
                         							</c:when>
                         							<c:otherwise>
                         								${entry.estimateRequestId}
                         							</c:otherwise>                       				
                         						</c:choose>
                                    		
                                    		
                                    		</td>
                                        	<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td>${entry.jobOrderNo}</td>
                                        	<td>${entry.device}</td>
                                        	<td>${entry.partnerNameD}</td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.count}</td>
                                        	<td><fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td><fmt:formatNumber value="${entry.sum}" pattern="#,###" /></td>
                                    	</tr>
                                    	
                                    <c:set var="No" value="${No + 1 }" />
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                        	</c:when>
                        	<c:when test="${mode eq 'issue'}">
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:30px;" />
                                	<col style="width:90px;" />
                                	<col style="width:120px;" />
                                	<col style="width:90px;" />
                                	<col style="width:200px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:50px;" />
                                	<col style="width:150px;" />
                                	<col style="width:90px;" />        
                                	</colgroup>
                            		<thead>
                                		<tr>
                                			<th>No</th>
                                        	<th>발주No</th>
                                        	<th>발주요청일</th>
                                        	<th>OrderNo</th>
                                        	<th>Device</th>
                                        	<th>도면구매처</th>
                                        	<th>발주처</th>
                                        	<th>품목수량</th>
                                        	<th>납기일</th>
                                        	<th>발주가 총합</th>        
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                                		<c:set var="No" value="${pageNo + 1}"></c:set>
                               	 		<c:forEach items="${data}" var="entry">
                               	 	
                               	 		
                                		<tr>
                                			<td>${No}</td>
                                			<td>
                                				<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="javascript:PopWin('${path}/purchase/history/issue/${entry.issueRequestId}','1600','450','no');">${entry.issueRequestId}</a>
                         							</c:when>
                         							<c:otherwise>
                         								<a href="javascript:PopWin('${path}/purchase/history/issueView/${entry.issueRequestId}','1600','450','no');">${entry.issueRequestId}</a>
                         							</c:otherwise>                       				
                         						</c:choose>
                                			</td>
                                			<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td>${entry.jobOrderNo}</td>
                                        	<td>${entry.device}</td>
                                        	<td>${entry.partnerNameD}</td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.count}</td>
                                        	<td><fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td><fmt:formatNumber value="${entry.sum}" pattern="#,###" /></td>
                                    	</tr>
                                    	
                                    <c:set var="No" value="${No + 1 }" />
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