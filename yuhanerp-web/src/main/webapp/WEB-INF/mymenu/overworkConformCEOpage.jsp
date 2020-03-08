<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<layout:extends name="/WEB-INF/layout/MyPageMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>연장근무</li>
                        <li>연장근무 결제(대표이사)</li>
                         
                    </ul>
                  
                    <h2>연장근무 결제(대표이사)</h2>
                  
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
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                   <th>Title</th>
                                    <td>
                                        <input type="text" name="title" value="${title}" class="w280">
                                    </td>
                                     <th>연장근무 요청일</th>
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
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:20%;" />
                                		
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                       
                                    		<th>Title</th>
                                    		<th>요청자</th>
                                        	<th>연장/휴일</th>
                                        	<th>연장근무일</th>
                                        	<th>연장근무 시간</th>
                                        	<th>연장근무 요청일</th>
                                        	<th>결제현황</th>
                                  
                                    	</tr>
                        
                                </thead>
                                
                                <tbody>
                                
                                	<c:forEach items="${data }" var="entry">
                                	
                                	
                             		
                             		
                                			<tr>
                                    			<td>${entry.title}</td>
                                        		<td><a href="javascript:PopWin('/mymenu/overwork/conformCEOPop/${entry.id}','700','1500','no');">${entry.usrName}</a></td>
                                        		<td>
                                        			<c:choose>
                                        				<c:when test="${entry.kind eq 'A'}">
                                        					연장근무
                                        				</c:when>
                                        				<c:otherwise>
                                        					휴일근무	
                                        				</c:otherwise>
                                        			</c:choose>
                                        		</td>
                                        		<td><fmt:formatDate value="${entry.requestDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td>${entry.requestStrtime} ~ ${entry.requestEndtime}</td>
                                        		<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td>
                                        			<c:choose>
                                        				<c:when test="${entry.conformStage eq 'R'}">
                                        					결제 요청중
                                        				</c:when>
                                        				<c:when test="${entry.conformStage eq 'M'}">
                                        					팀장 결제 완료
                                        				</c:when>
                                        				<c:when test="${entry.conformStage eq 'J'}">
                                        					이사/공장장 결제 완료
                                        				</c:when>
                                        				<c:otherwise>
                                        					대표이사 결제 완료
                                        				</c:otherwise>
                                        			</c:choose>
                                        		
                                        		</td>
                                        		
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