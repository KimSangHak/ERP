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

                             
                                <li>입고품 LIST 조회</li>
                          
                    </ul>
                   
                        
                            <h2>입고품 LIST 조회</h2>
                        
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
                                        	<select name="stage" id="stage" style="width:200px;">
                                				<option value="F" <c:if test="${stage eq 'F'}">selected</c:if>>미분출입고품</option>
                                				<option value="O" <c:if test="${stage eq 'O'}">selected</c:if>>분출입고품</option>
                         					</select>
                                    	</td>
                                    <th>거래처</th>
                                    <td><input type="text" name="customer" value="${customer}" class="w280"></td>
                                    
                                 
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
                                	<col style="width:5%;" />
                                	<col style="width:20%;" />
                                	<col style="width:15%;" />
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />
                                	
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                			<th>No</th>
                                        	<th>발주No</th>
                                        	<th>OrderNo</th>
                                        	<th>발주요청일</th>
                                        	<th>거래처</th>
                                        	<th>품목수량</th>
                                        	
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                                	
                                		<c:set var="No" value="${pageNo + 1}"></c:set>
                               	 		<c:forEach items="${data}" var="entry">
                               
                               	 		
                                		<tr>
                                			<td>${No}</td>
                                			<td id="taCenter">
                                				<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="javascript:PopWin('${path}/purchase/innerstockList/popList/${entry.issueRequestId}/${entry.stage}','1600','450','no');">${entry.issueRequestId}</a>
                         							</c:when>
                         							<c:otherwise>
                         								${entry.issueRequestId}
                         							</c:otherwise>                       				
                         						</c:choose>
                                				
                                			</td>
                                			<td>${entry.jobOrderNo}</td>
                                			<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        	<td>${entry.partnerName}</td>
                                        	<td>${entry.count}</td>
                                        
                                        	
                                        	
                  
                                        	
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