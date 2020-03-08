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
                        <li>구매품의서</li>
                        <li>품의서 구매 LIST 등록</li>
                         
                    </ul>
                  
                    <h2>품의서 구매 LIST 등록</h2>
                  
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
                                		<col style="width:5%;" />
                                		<col style="width:15%;" />
                                		<col style="width:18%;" />
                                		<col style="width:10%;" />
                                		<col style="width:17%;" />
                                		<col style="width:20%;" />
                                		<col style="width:15%;" />
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                			<th>seq</th>
                                        	<th>OrderNo</th>
                                    		<th>Title</th>
                                        	<th>품목갯수</th>
                                        	<th>품의서 요청일</th>
                                        	<th>요청부서</th>
                                        	<th>요청자</th>
                                    	</tr>
                        
                                </thead>
                                
                                <tbody>
                                	<c:set var="No" value="${pageNo + 1}"></c:set>
                                	<c:forEach items="${data }" var="entry">
                                	
                                	
                             		
                             		
                                			<tr>
                                				<td>${No}</td>
                                    			<td>${entry.jobOrderNo}</td>
                                        		<td><a href="javascript:PopWin('/purchase/purchaseroundRobin/insPop/${entry.roundNo}','800','1500','no');">${entry.title}</a></td>
                                        		<td>${entry.count}</td>
                                        		<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td>${entry.deptName}</td>
                                        		<td>${entry.usrName}</td>
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
		
		
		</script>
	
	</layout:put>
</layout:extends>