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
                        <li>매입정산</li>
                        <li>OrderNo 총합</li>
                    
                    </ul>
             
                     <h2>OrderNo 총합</h2>
                   
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
                                <colgroup span="1">
                                <col style="width:12%;;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                
                              
                                
                                <tr>
                                
                                	
                            	   <th>Order No.</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
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
                                	<colgroup>
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />
                                	<col style="width:20%;" />

                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>OrderNo</th>
                                        	<th colspan="2">업체 목록</th>                                        	
                                        	<th>최종 합계 금액</th>
                                        	<th>Order 진행 상태</th>
                                      
                                        
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               	 		  
                               	 		 
                               	 		
                                		<tr>
                                		
                                			<td style="font-weight: bold;">${entry.jobOrderNo}</td>
                                			<td colspan="2">
                                				<table style="border-top:none; border-bottom:none;">
                                					<colgroup>
                                						<col style="width:50%;" />
                                						<col style="width:50%;" />                                	
                                					</colgroup>
                                					<thead>
                                						<tr>
                                        					<th>매입 업체</th>
                                        					<th>업체별 금액</th>
                                        				</tr>
                                					</thead>
                                					<tbody>
                                						<c:set var="sumOrder" value="0"></c:set>
                                						<c:forEach items="${data2}" var="entry2">
                                							
                                								<c:choose>
                                									<c:when test="${entry.jobOrderId eq entry2.jobOrderId}">
                                										<tr>
                                											<td>${entry2.partnerName}</td>
                                											<td><fmt:formatNumber value="${entry2.sum}" pattern="#,###" /></td>
                                										</tr>
                                										<fmt:parseNumber var="smallsum" value="${entry2.sum}" integerOnly="true"/>
                                										<c:set var="sumOrder" value="${sumOrder + smallsum}"/>
                                									</c:when>
                                									<c:otherwise>
                                									
                                									</c:otherwise>
                                								</c:choose>
                                							
                                							
                                						</c:forEach>
                                						
                                					</tbody>
                                					
                                				</table>
                                			</td>
                                			<td><fmt:formatNumber value="${sumOrder}" pattern="#,###" /></td>
                                			<c:choose>
                                				<c:when test="${entry.currnet eq 'F'}">
                                					<td>완료</td>
                                				</c:when>
                                				<c:when test="${entry.currnet eq 'S'}">
                                					<td>SetUp중</td>
                                				</c:when>
                                				<c:when test="${entry.currnet eq 'H'}">
                                					<td>홀딩</td>
                                				</c:when>
                                				<c:otherwise>
                                					<td>진행중</td>
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                    	</tr>
                                    	
                                   
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