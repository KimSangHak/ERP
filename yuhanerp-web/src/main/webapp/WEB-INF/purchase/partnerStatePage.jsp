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
                        <li>매입업체 기간별</li>
                    
                    </ul>
             
                     <h2>매입업체 기간별</h2>
                   
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
                                
                                	
                            	    <th>거래명세일</th>
                        			
                                    
                                    <td colspan="5">
                                        <input type="date" name="designDateBegin" value="${designDateBegin}" style="width=200px;"> - <input type="date" name="designDateEnd" value="${designDateEnd}" style="width=200px;">
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
                                	<col style="width:25%;" />
                                	<col style="width:25%;" />
                                	<col style="width:25%;" />
                                	<col style="width:25%;" />

                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>매입 업체</th>
                                        	
                                        		<c:choose>
                                					<c:when test="${designDateBegin eq null && designDateEnd eq null}">
                                						<th colspan="2">전체 기간</th>				
                                					</c:when>
                                					<c:otherwise>
                                						<th colspan="2">${designDateBegin}&nbsp;~&nbsp;${designDateEnd}</th>		
                                					</c:otherwise>
                                			    </c:choose>
                                        	
                                        	<th>업체별 최종 합계 금액</th>
                                      
                                        
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               	 		  
                               	 		 
                               	 		
                                		<tr>
                                		
                                			<td style="font-weight: bold;">${entry.partnerName}</td>
                                			<td colspan="2">
                                				<table style="border-top:none; border-bottom:none;">
                                					<colgroup>
                                						<col style="width:50%;" />
                                						<col style="width:50%;" />                                	
                                					</colgroup>
                                					<thead>
                                						<tr>
                                        					<th>OrderNo</th>
                                        					<th>Order별 금액</th>
                                        				</tr>
                                					</thead>
                                					<tbody>
                                						<c:set var="sumPartner" value="0"></c:set>
                                						<c:forEach items="${data2}" var="entry2">
                                							
                                								<c:choose>
                                									<c:when test="${entry.partnerId eq entry2.partnerId}">
                                										<tr>
                                											<td>${entry2.jobOrderNo}</td>
                                											<td><fmt:formatNumber value="${entry2.sum}" pattern="#,###" /></td>
                                										</tr>
                                										<fmt:parseNumber var="smallsum" value="${entry2.sum}" integerOnly="true"/>
                                										<c:set var="sumPartner" value="${sumPartner + smallsum}"/>
                                									</c:when>
                                									<c:otherwise>
                                									
                                									</c:otherwise>
                                								</c:choose>
                                							
                                							
                                						</c:forEach>
                                						
                                					</tbody>
                                					
                                				</table>
                                			</td>
                                			<td><fmt:formatNumber value="${sumPartner}" pattern="#,###" /></td>
                                			
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