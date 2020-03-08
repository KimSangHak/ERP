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
                        <li>구매품의서</li>
                        <li>구매품의서 등록/조회</li>
                         
                    </ul>
                  
                    <h2>구매품의서 등록/조회</h2>
                  
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
                                <col style="width:8%;" />
                                <col style="width:8%;" />
                                 <col style="width:8%;" />
                                <col style="width:8%;" />
                                 <col style="width:8%;" />
                                <col style="width:17%;" />
                                 <col style="width:10%;" />
                                <col style="width:29%;" />
                                </colgroup>
                                <tr>
                                
                                	 <th>검색</br>유형</th>
                                    <td>
                                       		<select name="kind" id="kind" style="width:200px;">
                                				<option value="A">전체보기</option>
                                				<option value="N" <c:if test="${kind eq 'N'}">selected</c:if>>결제 미완료 보기</option>
                         					</select>
                                    </td>
                                     <th>요청자</th>
                                    <td>
                                        <input type="text" name="usrName" value="${usrName}" class="w280">
                                    </td>
                                   <th>Title</th>
                                    <td>
                                        <input type="text" name="title" value="${title}" class="w280">
                                    </td>
                                     <th>품의서</br>요청일</th>
                                    <td>
                                        <input type="date" name="DateBegin" value="${DateBegin}" class="w140"> - <input type="date" name="DateEnd" value="${DateEnd}" class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                       
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a>&nbsp;<a href="javascript:PopWin('${path}/mymenu/roundRobin/insertPop','800','800','no');" class="btn_blue">품의서 등록</a></div>
                           
                               
                           
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
                                		<col style="width:15%;" />
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:15%;" />
                                		<col style="width:15%;" />
                                		<col style="width:15%;" />
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                        	<th>OrderNo</th>
                                    		<th>Title</th>
                                    		<th>요청자</th>
                                        	<th>품목갯수</th>
                                        	<th>품의서 요청일</th>
                                        	<th>결제현황</th>
                                        	<th>구매현황</th>
                                    	</tr>
                        
                                </thead>
                                
                                <tbody>
                                
                                	<c:forEach items="${data }" var="entry">
                                	
                                	
                             		
                             		
                                			<tr>
                                    			<td>${entry.jobOrderNo}</td>
                                        		<td><a href="javascript:PopWin('/mymenu/roundRobin/detailPop/${entry.roundNo}','800','1500','no');">${entry.title}</a></td>
                                        		<td>${entry.usrName}</td>
                                        		<td>${entry.count}</td>
                                        		<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td>
                                        			<c:choose>
                                        				<c:when test="${entry.conformStage eq 'R'}">
                                        					결제 요청중
                                        				</c:when>
                                        				<c:when test="${entry.conformStage eq 'M'}">
                                        					팀장 결제 완료
                                        				</c:when>
                                        				<c:when test="${entry.conformStage eq 'P'}">
                                        					구매부 결제 완료
                                        				</c:when>
                                        				<c:when test="${entry.conformStage eq 'J'}">
                                        					재무이사 결제 완료
                                        				</c:when>
                                        				<c:otherwise>
                                        					대표이사 결제 완료
                                        				</c:otherwise>
                                        			</c:choose>
                                        		
                                        		</td>
                                        		<td>
                                        			<c:choose>
                                        				<c:when test="${entry.stage eq 'R'}">
                                        					결제 진행중
                                        				</c:when>
                                        				
                                        				<c:when test="${(entry.stage eq 'P') && (entry.roundKind eq 'P')}">
                                        					구매부 인계
                                        				</c:when>
                                        				<c:when test="${(entry.stage eq 'P') && (entry.roundKind eq 'M')}">
                                        					관리부 인계
                                        				</c:when>
                                        				
                                        				<c:when test="${(entry.stage eq 'F') && (entry.roundKind eq 'P')}">
                                        					구매 등록(구매부)
                                        				</c:when>
                                        				<c:when test="${(entry.stage eq 'F') && (entry.roundKind eq 'M')}">
                                        					구매 등록(관리부)
                                        				</c:when>
                                        				
                                        				<c:otherwise>
                                        					Erro
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