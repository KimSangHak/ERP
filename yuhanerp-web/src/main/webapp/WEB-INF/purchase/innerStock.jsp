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
                      
                            
                                <li>입고품 등록</li>
                          
                    </ul>
                
                        
                            <h2>입고품 등록</h2>
                    
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    <form method="get">
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
                                        	<select name="roundRobinYN" id="roundRobinYN" style="width:200px;">
                                				<option value="N" <c:if test="${roundRobinYN eq 'N'}">selected</c:if>>일반구매품</option>
                                				<option value="Y" <c:if test="${roundRobinYN eq 'Y'}">selected</c:if>>품의서구매품</option>
                         					</select>
                                    	</td>
                                    <th>거래처</th>
                                    <td>
                                        <input type="text" name="customer" value="${customer}" class="w280">
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                        <input type="date" name="DateBegin" value="${DateBegin}" class="w140"> - <input type="date" name="DateEnd" value="${DateEnd}" class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        
                       
                        	
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:30px;" />
                                	<col style="width:100px;" />
                                	<col style="width:90px;" />
                                	<col style="width:120px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:120px;" />
                                	<col style="width:140px;" />
                                	
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                			<th>No</th>
                                        	<th>발주No</th>
                                        	<th>OrderNo</th>
                                        	<th>발주요청일</th>
                                        	<th>거래처</th>
                                        	<th>품목수량</th>
                                        	<th>미 입고 품목</th>
                                        	<th>납기예정일</th>
                                        	<th>결제금액</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                                		<c:set var="No" value="${pageNo + 1}"></c:set>
                               	 		<c:forEach items="${data}" var="entry">
                               	 	
                                		<tr>
                                			<td>${No}</td>
                                			<td id="taCenter"><a href="javascript:PopWin('${path}/purchase/innerStock/popList/${entry.issueRequestId}','1600','450','no');">${entry.issueRequestId}</a></td>
                                			<td>${entry.jobOrderNo}</td>
                                        	<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.count}</td>
                                        	<td>${entry.noneinnersotck}</td>
                                        	<td><fmt:formatDate value="${entry.receiveDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td><fmt:formatNumber value="${entry.sumPrice}" pattern="#,###" /></td>
                                        
                                    	</tr>
                                    	
                                   	<c:set var="No" value="${No + 1 }" />
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
		<script type="text/javascript">
			$(document).ready(function(){
			
				$(".SubmitButton").click(function(e){
					$("form").submit();				
				});
			
			});
		</script>
	
	</layout:put>
</layout:extends>