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
                        <li>월별 합계</li>
                    
                    </ul>
             
                     <h2>월별 합계</h2>
                   
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
                                
                                	
                            	  
                        			
                                  <th>검색유형</th>
                                    <td>
                                        	<select name="type" id="type" style="width:200px;">
                                				<option value="P">매입업체별</option>
                                				<option value="O">OrderNo별</option>
                         					</select>
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
                        
                        	<c:choose>
                        		<c:when test="${type eq 'O'}">
                        		
                        		<table>
                            		<caption> </caption>
                                	<colgroup>
                                	<col style="width:16%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>OrderNo</th>
                                        	<th>1월</th>
                                        	<th>2월</th>
                                        	<th>3월</th>
                                        	<th>4월</th>
                                        	<th>5월</th>
                                        	<th>6월</th>
                                        	<th>7월</th>
                                        	<th>8월</th>
                                        	<th>9월</th>
                                        	<th>10월</th>
                                        	<th>11월</th>
                                        	<th>12월</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                                		<tr>
                                		
                                			<td style="font-weight: bold;">${entry.jobOrderNo}</td>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jan}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				<c:set var="loop_flag" value="false" />
                                				<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Jan}" var="month1">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month1.jobOrderId}">
                                								<td><fmt:formatNumber value="${month1.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJan}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                						<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Feb}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				<c:set var="loop_flag" value="false" />
                                				<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Feb}" var="month2">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month2.jobOrderId}">
                                								<td><fmt:formatNumber value="${month2.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countFeb}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                						<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Mar}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				<c:set var="loop_flag" value="false" />
                                				<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Mar}" var="month3">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month3.jobOrderId}">
                                								<td><fmt:formatNumber value="${month3.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                									<c:choose>
                                									<c:when test="${No eq countMar}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                						<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Apr}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				<c:set var="loop_flag" value="false" />
                                				<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Apr}" var="month4">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month4.jobOrderId}">
                                								<td><fmt:formatNumber value="${month4.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countApr}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                								
                                							</c:otherwise>
                                						</c:choose>
                                						<c:set var="No" value="${No + 1}"/>	
                                					</c:if>
                                					
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			
                                			<c:choose>
                                				<c:when test="${empty May}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${May}" var="month5">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month5.jobOrderId}">
                                								<td><fmt:formatNumber value="${month5.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countMay}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                						<c:set var="No" value="${No + 1}"/>	
                                					</c:if>
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jun}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                					<c:forEach items="${Jun}" var="month6">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month6.jobOrderId}">
                                								<td><fmt:formatNumber value="${month6.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJun}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jul}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                					<c:forEach items="${Jul}" var="month7">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month7.jobOrderId}">
                                								<td><fmt:formatNumber value="${month7.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJul}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Aug}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                					<c:forEach items="${Aug}" var="month8">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month8.jobOrderId}">
                                								<td><fmt:formatNumber value="${month8.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countAug}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                						
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Sep}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Sep}" var="month9">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month9.jobOrderId}">
                                								<td><fmt:formatNumber value="${month9.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countSep}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Oct}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Oct}" var="month10">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month10.jobOrderId}">
                                								<td><fmt:formatNumber value="${month10.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countOct}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Nov}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Nov}" var="month11">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month11.jobOrderId}">
                                								<td><fmt:formatNumber value="${month11.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countNov}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Dec}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Dec}" var="month12">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.jobOrderId eq month12.jobOrderId}">
                                								<td><fmt:formatNumber value="${month12.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countDec}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>

                                 	</c:forEach>
                                	</tbody>
                            	</table>
                        		
                        		
                        		</c:when>
                        		
                        		<c:otherwise>
                        		
                        		<table>
                            		<caption> </caption>
                                	<colgroup>
                                	<col style="width:16%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	<col style="width:7%;" />
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>매입업체</th>
                                        	<th>1월</th>
                                        	<th>2월</th>
                                        	<th>3월</th>
                                        	<th>4월</th>
                                        	<th>5월</th>
                                        	<th>6월</th>
                                        	<th>7월</th>
                                        	<th>8월</th>
                                        	<th>9월</th>
                                        	<th>10월</th>
                                        	<th>11월</th>
                                        	<th>12월</th>
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                                		<tr>
                                		
                                			<td style="font-weight: bold;">${entry.partnerName}</td>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jan}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Jan}" var="month1">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month1.partnerId}">
                                								<td><fmt:formatNumber value="${month1.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJanPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Feb}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Feb}" var="month2">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month2.partnerId}">
                                								<td><fmt:formatNumber value="${month2.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countFebPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Mar}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Mar}" var="month3">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month3.partnerId}">
                                								<td><fmt:formatNumber value="${month3.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countMarPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>			
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Apr}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Apr}" var="month4">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month4.partnerId}">
                                								<td><fmt:formatNumber value="${month4.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countAprPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        																	
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			
                                			<c:choose>
                                				<c:when test="${empty May}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />	
                                					<c:forEach items="${May}" var="month5">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month5.partnerId}">
                                								<td><fmt:formatNumber value="${month5.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countMayPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>			
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jun}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                					<c:forEach items="${Jun}" var="month6">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month6.partnerId}">
                                								<td><fmt:formatNumber value="${month6.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJunPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>			
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Jul}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                					<c:forEach items="${Jul}" var="month7">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month7.partnerId}">
                                								<td><fmt:formatNumber value="${month7.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countJulPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Aug}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                						
                                						
                                					<c:forEach items="${Aug}" var="month8">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month8.partnerId}">
                                								<td><fmt:formatNumber value="${month8.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countAugPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Sep}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Sep}" var="month9">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month9.partnerId}">
                                								<td><fmt:formatNumber value="${month9.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countSepPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>	
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Oct}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                				
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Oct}" var="month10">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month10.partnerId}">
                                								<td><fmt:formatNumber value="${month10.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countOctPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Nov}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Nov}" var="month11">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month11.partnerId}">
                                								<td><fmt:formatNumber value="${month11.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countNovPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>			
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                			
                                			<c:choose>
                                				<c:when test="${empty Dec}">
                                					<td></td>
                                				</c:when>
                                				
                                				<c:otherwise>
                                					<c:set var="loop_flag" value="false" />
                                					<c:set var="No" value="${pageNo + 1}" />
                                					<c:forEach items="${Dec}" var="month12">
                                					<c:if test="${not loop_flag }">
                                						<c:choose>
                                							<c:when test="${entry.partnerId eq month12.partnerId}">
                                								<td><fmt:formatNumber value="${month12.sum}" pattern="#,###" /></td>
                                								<c:set var="loop_flag" value="true" />
                                							</c:when>
                                							<c:otherwise>
                                								<c:choose>
                                									<c:when test="${No eq countDecPartner}">
                                										<td></td>
                                									</c:when>
                                									<c:otherwise>
        
                                									</c:otherwise>
                                								</c:choose>
                                							</c:otherwise>
                                						</c:choose>
                                					<c:set var="No" value="${No + 1}"/>	
                                					</c:if>		
                                					</c:forEach>
                                				
                                				</c:otherwise>
                                			
                                			</c:choose>
                                	
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                        		
                        		
                        		</c:otherwise>
                        	
                        	
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