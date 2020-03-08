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
                        <li>견적/발주/입고</li>
                         <c:choose>
                         	<c:when test="${mode eq 'estimate'}">
                            	<li>견적 요청 LIST 등록</li>
                             </c:when>
                             <c:when test="${mode eq 'issue'}">
                                <li>발주 LIST 등록</li>
                             </c:when>
                         </c:choose>
                    </ul>
                    <c:choose>
                    	<c:when test="${mode eq 'estimate'}">
                        	<h2>견적 요청 LIST 등록</h2>
                        </c:when>
                        <c:when test="${mode eq 'issue'}">
                            <h2>발주 LIST 등록</h2>
                        </c:when>
                    </c:choose>
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
                                     <th>검색유형</th>
                                    <td>
                                        	<select name="roundRobinYN" id="roundRobinYN" style="width:200px;">
                                				<option value="N" <c:if test="${roundRobinYN eq 'N'}">selected</c:if>>일반구매품</option>
                                				<option value="Y" <c:if test="${roundRobinYN eq 'Y'}">selected</c:if>>품의서구매품</option>
                         					</select>
                                    	</td>
                                    <th>Order No.</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                         <c:choose>
                         	<c:when test="${mode eq 'estimate'}">
                            	<div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a>&nbsp;<a href="#" class="btn_blue" id="OpenEstimateButton">견적요청</a></div>
                             </c:when>
                             <c:when test="${mode eq 'issue'}">
                                <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a>&nbsp;<a href="#" class="btn_blue" id="OpenIssueButton">선택/신규 발주 요청</a></div>
                             </c:when>
                         </c:choose>
                        </form>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>              	
                        		<c:choose>
                         			<c:when test="${mode eq 'estimate'}">
                            			<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:10%;" />
                                		<col style="width:20%;" />
                                		<col style="width: ;" />
                                		<col style="width:20%;" />
                                		</colgroup>
                             		</c:when>
                             		<c:when test="${mode eq 'issue'}">
                                		<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:19%;" />
                                		<col style="width:19%;" />
                                		<col style="width:19%;" />
                                		<col style="width:19%;" />
                                		<col style="width:19%;" />
                                		<col style="width:5%;" />
                                		</colgroup>
                             		</c:when>
                         		</c:choose>
                            	<thead>
                            	<c:choose>
                         			<c:when test="${mode eq 'estimate'}">
                            			<tr>
                                    		<th>No.</th>
                                        	<th>Order No.</th>
                                        	<th>거래처</th>
                                        	<th>개수</th>
                                        	<th>선택</th>
                                    	</tr>
                             		</c:when>
                             		<c:when test="${mode eq 'issue'}">
                                		<tr>
                                        	<th>No.</th>
                                    		<th>OrderNo</th>
                                    		<th>거래처</th>
                                        	<th>개수</th>
                                        	<th>구매등록일</th>
                                        	<th>선택</th>
                                    	</tr>
                             		</c:when>
                         		</c:choose>
             
                                </thead>
                                
                                <tbody>
                                	 <c:set var="No" value="${pageNo + 1}"></c:set>
                                	<c:forEach items="${data }" var="entry">
                                	
                                	<c:choose>
                         				<c:when test="${mode eq 'estimate'}">
                         					<tr>
                                    			<td>${No}</td>
                                    			<td>${entry.jobOrderNo }</td>
                                        		<td><!-- <a href="javascript:PopWin('/purchase/popup/${mode }/partner_detail?partnerId=${entry.partnerId }&jobOrderId=${entry.jobOrderId}','1000','450','no');">-->
                                        			${entry.partnerName }
                                        			<!-- </a> -->
                                        		</td>
                                        		<td>${entry.count }</td>
                                        		<td><input type="checkbox" name="Record" value="${entry.partnerId },${entry.jobOrderId}" />
                                    		</tr>
                             			</c:when>
                             			<c:when test="${mode eq 'issue'}">
                                			<tr>
                                    			<td>${No}</td>
                                        		<td><!-- <a href="javascript:PopWin('/purchase/popup/${mode }/partner_detail?partnerId=${entry.partnerId }&jobOrderId=${entry.jobOrderId}','1000','450','no');">-->
                                        			${entry.jobOrderNo}
                                        			<!-- </a> -->
                                        		</td>
                                        		<td>${entry.partnerName}</td>
                                        		<td>${entry.count}</td>
                                        		<td><fmt:formatDate value="${entry.regDate}" pattern="yyyy-MM-dd"/></td>
                                        		<td><input type="checkbox" name="Record" value="${entry.partnerId },${entry.jobOrderId}" />
                                    		</tr>
                             			</c:when>
                         			</c:choose>
                                	
                                	
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
		
			var activePartnerId = null;
			
		
			function getPartnerId(val){
				var tokens = val.split(",");
				return tokens[0];
			}
			function getJobOrderId(val){
				var tokens = val.split(",");
				return tokens[1];
			}
		
		
			// 같은 partner 가 선택된건지 확인
			function checkRecord(){
				
				var allChecked = $("input[name='Record']:checked");
				if(allChecked.length == 0){
					alert("선택된 항목이 없습니다");
					return false;
				}
				
				var firstPartnerId = getPartnerId( allChecked.first().val() );
				
				for(var i=1;i<allChecked.length;i++){
					if(firstPartnerId != getPartnerId( allChecked.eq(i).val() )){
						alert("같은 거래처에 대해서만 한번에 견적 요청할 수 있습니다");
						return false;
					}
				}
				
				activePartnerId = firstPartnerId;
				
				return true;
			}
			
			function getAllJobOrderId(){
				var ret = [];
				var allChecked = $("input[name='Record']:checked");
				if(allChecked.length > 0){
				
					for(var i=0;i<allChecked.length;i++){
						
						var jobOrderId = getJobOrderId( allChecked.eq(i).val() );
						ret.push( jobOrderId );
					}					
				}
				return ret;
			}
		
			$(document).ready(function(){
			
				$(".SubmitButton").click(function(e){
					$("form").submit();				
				});
							
			
				$("#OpenEstimateButton").click(function(e){
					e.preventDefault();
					
					var jobOrderId ="";
					
					if(!checkRecord())
						return;
					
					var jobOrderIds = getAllJobOrderId();
					
					
					for(var i=0;i<jobOrderIds.length;i++ )
						jobOrderId += jobOrderIds[i] + ",";
					
					
					var url = '/purchase/estimate/pre/' + activePartnerId +'/'+ jobOrderId;
				
					PopWin(url,'1500','650','no');
				});
				
				$("#OpenIssueButton").click(function(e){
					e.preventDefault();
					
					var jobOrderId ="";
					
					if(!checkRecord())
						return;
					
					var jobOrderIds = getAllJobOrderId();
					
					for(var i=0;i<jobOrderIds.length;i++ )
						jobOrderId += jobOrderIds[i] + ",";
					
				
					var url = '/purchase/issue/pre/' + activePartnerId +'/'+ jobOrderId;
				
					PopWin(url,'1500','650','no');
				});
			});
		</script>
	
	</layout:put>
</layout:extends>