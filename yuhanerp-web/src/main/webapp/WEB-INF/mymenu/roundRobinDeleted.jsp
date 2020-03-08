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
                        <li>취소/반려 품의서 조회</li>
                         
                    </ul>
                  
                    <h2>취소/반려 품의서 조회</h2>
                  
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
                                    
                                     <th>요청자</th>
                                    <td>
                                        <input type="text" name="usrName" value="${usrName}" class="w280">
                                    </td>
                                     <th>품의서 요청일</th>
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
                                		<col style="width:15%;" />
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:15%;" />
                                		<col style="width:30%;" />
                                		<col style="width:10%;" />
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                        	<th>OrderNo</th>
                                    		<th>Title</th>
                                        	<th>품목갯수</th>
                                        	<th>품의서 요청일</th>
                                        	<th>취소/반려 사유</th>
                                        	<th>반려자</th>
                                    	</tr>
                        
                                </thead>
                                
                                <tbody>
                                
                                	<c:forEach items="${data }" var="entry">
                                	
                                	
                             		
                             		
                                			<tr>
                                    			<td>${entry.jobOrderNo}</td>
                                        		<td><a href="javascript:PopWin('/mymenu/roundRobin/detailPop/${entry.roundNo}','800','1500','no');">${entry.title}</a></td>
                                        		<td>${entry.count}</td>
                                        		<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td>${entry.deleteReason}</td>
                                        		<td>${entry.usrName}</td>
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