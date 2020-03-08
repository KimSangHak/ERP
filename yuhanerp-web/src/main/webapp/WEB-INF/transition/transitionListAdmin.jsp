<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="../layout/TransitionMaster.jsp">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공&QC&조립</li>
                        <li>인수확인 LIST</li>
                    </ul>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                   
                    <!-- //테이블 타이틀 -->
                    
               <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->

                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>구매품 인수 확인 LIST</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:50%;" />
                                <col style="width:50%;" />

                               
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>인수 확인 부서</th>
                                        <th>품목수량</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                		<td><a href="javascript:PopWin('${path}/transition/popPurchaseAdminList/${item.receiveDept}','800','600','no');">${item.deptName}</a></td>
										<td>${item.count}</td>                                       
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                        <div class="listTit">
                        	<h3>재고품 인수 확인 LIST</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:50%;" />
                                <col style="width:50%;" />

                               
                                </colgroup>
                            	<thead>
                                	<tr>
                                    
                                        <th>인수 확인 부서</th>
                                        <th>품목수량</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data2}">
                                	<tr>
                                		<td><a href="javascript:PopWin('${path}/transition/popStockAdminList/${item.receiveDept}','800','600','no');">${item.deptName}</a></td>
										<td>${item.count}</td>                                                 
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
		
		
		
		</script>
		
		<script type="text/javascript" src="/js/daily_report_common.js"></script>
	
	</layout:put>
</layout:extends>