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
						
                             
                                <li>재고 LIST</li>
                                
                                <c:if test="${isPQ eq 'Y'}">
                                
                                	<li style="color:red;"><b>재고 총 금액 : <fmt:formatNumber value="${sumStockAll}" pattern="#,###" /></b></li>
                          		</c:if>
                    </ul>
                   
                        
                            <h2>재고 LIST</h2>
                        
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    <form id="form" name="form" method="get">
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
                                    <th>검색유형</th>
                                    <td>
                                     		<select name="kind" id="kind" style="width:200px;">
                                				<option value="A">전체검색</option>
                                				<option value="B">불출대기재고검색</option>
                         					</select>
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
                                    
                                 
                            		<th>재고등록일</th>
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
                    
                    
                    <form id="form1" name="form1" method="post">
                     <div>
                     <a href="javascript:PopWin('${path}/purchase/stockRealList/addPop','500','500','no');" class="btn_blue" >재고등록</a>
                     </div>
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        
                       
                        	
                        		<table>
                            		<caption> </caption>
                                	<colgroup span="2">
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:80px;" />
                                	<col style="width:150px;" />
                                	<col style="width:80px;" />
                                	
                                	</colgroup>
                            		<thead>
                                		<tr>
                                        	<th>CodeNo.</th>
                                        	<th>Maker</th>
                                        	<th>모델명</th>
                                        	<th>품명</th>
                                        	<th>단가</th>
                                        	<th>재고 입고 수량<br/>(재입고 포함)</th>
                                        	<th>불출대기수량</th>
                                        	<th>불출수량</th>
                                        	<th>잔여수량<br/>(불출 가능 수량)</th>
                                        	<th>최종수정일자<br/>(불출 및 등록)</th>
                                        	<th>삭제</th>
                                        	
                                        	
                                    	</tr>
                                	</thead>
                                
                                	<tbody>
                                	
                                		<c:set var="No" value="${pageNo + 1}"></c:set>
                               	 		<c:forEach items="${data}" var="entry">
                               
                                        
                                        <fmt:parseNumber var="stockqty" value="${entry.quantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="abQt" value="${entry.abQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="outQt" value="${entry.outQuantity}" integerOnly="true"/>
                                        <fmt:parseNumber var="remind" value="${stockqty-(abQt+outQt)}" integerOnly="true"/>
                               	 		
                                		<tr>
                                		
                                			<td id="taCenter">
                                				
                                				<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="javascript:PopWin('${path}/purchase/stockRealList/pop/${entry.id}','800','700','no');">J${entry.id}</a>
                         							</c:when>
                         							<c:otherwise>
                         								J${entry.id}
                         							</c:otherwise>                       				
                         						</c:choose>
                                				
                                				
                                			</td>
                                			<td>${entry.maker}</td>
                                			<td>
                                				<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="javascript:PopWin('${path}/purchase/stockRealEd/popEd/${entry.id}','600','500','no');">${entry.modelNo}</a>
                         							</c:when>
                         							<c:otherwise>
                         								${entry.modelNo}
                         							</c:otherwise>                       				
                         						</c:choose>
                                			</td>
                                			<td>${entry.description}</td>
                                			<td><fmt:formatNumber value="${entry.issuePrice}" pattern="#,###" /></td>
                                			<td>${entry.quantity}</td>
                                			<td>${entry.abQuantity}</td>
                                			<td>${entry.outQuantity}</td>
                                			<td>${remind}</td>
                                			<td><fmt:formatDate value="${entry.finalDate}" pattern="yyyy-MM-dd"/></td>
                                			
                                			<c:choose>
                         							<c:when test="${entry.abQuantity eq 0}">
                         								<td id="${No}"><a href="/purchase/stockRealList/deleted/${entry.id}">삭제</a></td>
                         							</c:when>
                         							<c:otherwise>
                         								<td style="color:red;">불출 대기 수량 확인</td>
                         							</c:otherwise>                       				
                         					</c:choose>
                                    	</tr>
                                    	
                                    	 <input type="hidden" id="id${No}" name="id" class="id${No}" value="${entry.id}">
                                   		<c:set var="No" value="${No + 1 }" />
                                 	</c:forEach>
                                	</tbody>
                            	</table>
                         
                        	
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    </form>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
                
                                        		
	</layout:put>
	<layout:put block="BodyScriptBlock2">
		<script type="text/javascript">
			$(document).ready(function(){
				
				
			
				$(".SubmitButton").click(function(e){
					document.form.submit();				
				});
				
				$(".DeleteBtn").click(function(){
					
					var trid = $(this).closest('td').attr('id');
					   
					var id = $("#id"+trid).val();
			
					alert(trid);
					
					alert(id);
			    	
			        if(confirm("재고를 삭제 하시겠습니까?")){
			        	document.form1.action = "/purchase/stockRealList/deleted";
			        	document.form1.submit();
			        }
			    });
			
			});
		</script>
	
	</layout:put>
</layout:extends>