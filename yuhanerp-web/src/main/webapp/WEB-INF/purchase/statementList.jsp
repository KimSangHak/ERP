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
                        <li>거래명세표관리</li>
                        <li>거래명세표 리스트</li>
                    
                    </ul>
             
                     <h2>거래명세표 리스트</h2>
                   
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
                                   
                                    <th>구매처</th>
                                    <td><input type="text" name="partnerName" value="${partnerName}" class="w280"></td>
                                    
                                 
                        			
                            	   	<th>Order No.</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    </td>
                                    
                                   
                                </tr>
                                
                                <tr>
                                
                                	
                            	    <th>거래명세일</th>
                        			
                                    
                                    <td colspan="5">
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
                                	<col style="width:12%;" />
                                	<col style="width:10%;" />
                                	<col style="width:12%;" />
                                	<col style="width:12%;" />
                                	<col style="width:12%;" />
                                	<col style="width:12%;" />
                                	<col style="width:10%;" />
                                	<col style="width:10%;" />
                                	<col style="width:10%;" />
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>구매처</th>
                                        	<th>거래명세번호</th>
                                        	<th>거래명세일</th>
                                        	<th>발주NO</th>
                                        	<th>OrderNo</th>
                                        	<th>항목수량</th>
                                        	<th>총거래금액</th>
                                        	<th>세액 합</th>
                                        	<th>합계</th>
                                        
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               	 		 
                               	 		 <fmt:parseNumber var="tax" value="${entry.sumPrice * 0.1}" integerOnly="true"/>
                               	 		 <fmt:parseNumber var="sum" value="${entry.sumPrice + tax}" integerOnly="true"/>
                               	 		
                                		<tr>
                                		
                                			<td>${entry.partnerName}</td>
                                			<td><a href="javascript:PopWin('/purchase/statementList/pop/${entry.id}','1900','800','no');">${entry.id}</a></td>
                                        	<td><fmt:formatDate value="${entry.issueDate }" pattern="yyyy.MM.dd"/></td>
                                        	<td>${entry.requestId}</td>
                                        	<td>${entry.jobOrderNo}</td>
                                        	<td>${entry.count}</td>
                                        	<td><fmt:formatNumber value="${entry.sumPrice}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${tax}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${sum}" pattern="#,###" /></td>
                                        	
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