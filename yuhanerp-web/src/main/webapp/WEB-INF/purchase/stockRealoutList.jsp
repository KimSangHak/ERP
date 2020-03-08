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
                        <li>재고관리</li>

                             
                                <li>재고 불출 LIST</li>
                          
                    </ul>
                   
                        
                            <h2>재고 불출 LIST</h2>
                        
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
                                    <th>사용 된 OrderNo</th>
                                		<td>
                                        	<input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                  		</td>
                                    <th>CodeNO.</th>
                                    <td><input type="text" name="CodeNO" value="${CodeNO}" class="w280"></td>
                                    <th>Model/Size</th>
                                    <td>
                                        <input type="text" name="modelNo" value="${modelNo}" class="w280">
                                    </td>
                                </tr>
                                <tr>
                                    <th>Maker</th>
                                    <td><input type="text" name="maker" value="${maker}" class="w280"></td>
                                    <th>품명</th>
                                    <td><input type="text" name="desc" value="${desc}" class="w280"></td>
                                    
                                 
                            		<th>불출일자</th>
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

                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	
                                	<col style="width:90px;" />
                                	<col style="width:90px;" />
                                	<col style="width:150px;" />
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>CodeNo.</th>
                                        	<th>사용 된 OrderNo</th>
                                        	<th>Maker</th>
                                        	<th>모델명</th>
                                        	<th>품명</th>
                                        	
                                        	<th>불출수량</th>
                                        	<th>인수부서</th>
                                        	<th>인수자</th>
                                        	
                                        	<th>불출일자</th>                                    
                                        	
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                               	 		<c:forEach items="${data}" var="entry">
                               
                                    
                                		<tr>
                                		
                                			<td><a href="javascript:PopWin('${path}/purchase/stockoutListpop/${entry.id}','800','600','no');">J${entry.did}</a></td>
                                			<td>${entry.orderNo}</td>
                                			<td>${entry.maker}</td>
                                			<td>${entry.modelNo}</td>
                                			<td>${entry.description}</td>
                                		
                                			<td>${entry.outQuantity}</td>
                                			<td>${entry.deptName}</td>
                                			<td>${entry.receiverUsr}</td>
                                			
                                			<td><fmt:formatDate value="${entry.passDate}" pattern="yyyy-MM-dd"/></td>
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