<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="/WEB-INF/layout/DrawingMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>설계 현황</li>
                        <li>설계 현황</li>
                    </ul> 
                    <h2>설계 현황</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                
                	 <div class="searchArea">
                    <form method="get">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:50%;" />
                              	<col style="width:50%;" />
                                </colgroup>
                                <tr>
                                   
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    </td>
                                  
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
                    </div>
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->

                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>설비 진행</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:100px;" />
                                <col style="width:80px;" />
                                <col style="width:200px;" />
                                <col style="width:150px;" />
                                <col style="width:50px;" />
                                <col style="width:70px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>업체</th>
                                        <th>출고일</th>
                                        <th>Device</th>
                                        <th>비고</th>
                                        <th>수량</th>
                                        <th>설계담당</th>
                                        <th>상태</th>
                                        <th>진행률</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="jobitem" items="${jobData}">
                                	<tr data="${jobitem.id }">
                                    	<td>${jobitem.orderFullNo }</td>
                                    	<td>${jobitem.customerName }</td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${jobitem.currentStage eq 'A' }">
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.shippingDate }" />
                                        		</c:when>
                                        		<c:when test="${jobitem.currentStage eq 'H' }">
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.shippingDate }" />
                                        		</c:when>
                                        		<c:otherwise>
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.deliveryDate }" />
                                        		</c:otherwise>
                                        	</c:choose>
                                        </td>
                                    	<td>${jobitem.device }</td>
                                    	<td>${jobitem.note }</td>
                                    	<td>${jobitem.quantity }</td>
                                    	<td>${jobitem.designUserName }</td>
                                        <td>
                                        	<c:choose>
                                       		<c:when test="${jobitem.designStatus eq 'F' }">
                                       			완료<br>
                                       			<span class="icon_check">아이콘</span>
                                       		</c:when>
											<c:otherwise>
	                                        	<c:choose>
	                         						<c:when test="${isupdate eq 'Y'}">
	                         							<a href="#" class="RowJobAction">
	                         						</c:when>
	                         					</c:choose>
	                                        	<c:choose>
	                                        		<c:when test="${jobitem.designStatus eq 'I' }">
	                                        			<a href="#" class="RowJobAction">진행중<br>
	                                        			<span class="icon_play">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:when test="${jobitem.designStatus eq 'H' }">
	                                        			<a href="#" class="RowJobAction">홀딩<br>
	                                        			<span class="icon_lock">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:when test="${jobitem.designStatus eq 'S' }">
	                                        			<a href="#" class="RowJobAction">중단<br>
	                                        			<span class="icon_stop">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        			<a href="#" class="RowJobAction">대기<br>
	                                        			<span class="icon_play2">아이콘</span></a>
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        	<c:choose>
	                         						<c:when test="${isupdate eq 'Y'}">
	                         							</a>
	                         						</c:when>
	                         					</c:choose>
	                         				</c:otherwise>
	                         				</c:choose>
                                        </td>
                                        <td>
                                        	<c:if test="${(jobitem.designStatus eq 'I') and (isupdate eq 'Y')}">
                                        	<a href="#" class="RowProgress">${jobitem.designProgress }%</a>
                                        	</c:if>
                                        	<c:if test="${jobitem.designStatus ne 'I'}">
                                        	${jobitem.designProgress }%
                                        	</c:if>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                        <!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>치공구 진행</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:100px;" />
                                <col style="width:80px;" />
                                <col style="width:200px;" />
                                <col style="width:150px;" />
                                <col style="width:50px;" />
                                <col style="width:70px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>업체</th>
                                        <th>출고일</th>
                                        <th>Device</th>
                                        <th>비고</th>
                                        <th>수량</th>
                                        <th>설계담당</th>
                                        <th>상태</th>
                                        <th>진행률</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="jibitem" items="${jigData}">
                                	<tr data="${jibitem.id }">
                                    	<td>${jibitem.orderFullNo }</td>
                                    	<td>${jibitem.customerName }</td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${jibitem.currentStage eq 'A' }">
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jibitem.shippingDate }" />
                                        		</c:when>
                                        		<c:when test="${jibitem.currentStage eq 'H' }">
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jibitem.shippingDate }" />
                                        		</c:when>
                                        		<c:otherwise>
                                        			<fmt:formatDate pattern = "yy.MM.dd" value = "${jibitem.deliveryDate }" />
                                        		</c:otherwise>
                                        	</c:choose>
                                        </td>
                                    	<td>${jibitem.device }</td>
                                    	<td>${jibitem.note }</td>
                                    	<td>${jibitem.quantity }</td>
                                    	<td>${jibitem.designUserName }</td>
                                        <td>
                                        	<c:choose>
                                       		<c:when test="${jibitem.designStatus eq 'F' }">
                                       			완료<br>
                                       			<span class="icon_check">아이콘</span>
                                       		</c:when>
											<c:otherwise>
	                                        	<c:choose>
	                         						<c:when test="${isupdate eq 'Y'}">
	                         							<a href="#" class="RowJobAction">
	                         						</c:when>
	                         					</c:choose>
	                                        	<c:choose>
	                                        		<c:when test="${jibitem.designStatus eq 'I' }">
	                                        			<a href="#" class="RowJobAction">진행중<br>
	                                        			<span class="icon_play">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:when test="${jibitem.designStatus eq 'H' }">
	                                        			<a href="#" class="RowJobAction">홀딩<br>
	                                        			<span class="icon_lock">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:when test="${jibitem.designStatus eq 'S' }">
	                                        			<a href="#" class="RowJobAction">중단<br>
	                                        			<span class="icon_stop">아이콘</span></a>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        			<a href="#" class="RowJobAction">대기<br>
	                                        			<span class="icon_play2">아이콘</span></a>
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        	<c:choose>
	                         						<c:when test="${isupdate eq 'Y'}">
	                         							</a>
	                         						</c:when>
	                         					</c:choose>
	                         				</c:otherwise>
	                         				</c:choose>
                                        </td>
                                        <td>
                                        	<c:if test="${(jibitem.designStatus eq 'I') and (isupdate eq 'Y')}">
                                        	<a href="#" class="RowProgress">${jibitem.designProgress }%</a>
                                        	</c:if>
                                        	<c:if test="${jibitem.designStatus ne 'I'}">
                                        	${jibitem.designProgress }%
                                        	</c:if>
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

		function afterInputStatus(keyVal, atypeVal, setUser, text, messageTxt){
			var suc_url = "/drawing/drawing_status";

			$.post( "/drawing/status_update", {
			    key: keyVal,
			    setuser: setUser,
			    atype: atypeVal,
			    reason: text
			}, function(jqXHR) {
				alert( messageTxt );
			    location.href=suc_url;
			}, 'json' /* xml, text, script, html */)
			.fail(function(jqXHR) {
			    alert( "상태 업데이트 오류" );
			});
		}

		$(document).ready(function(){
			
		    $('.icon_play2').bind("mouseover", function(){ 
			     var color = $(this).css("background-color"); 
			     $(this).css("background", '#50b340  url(../images/icon_play.png) no-repeat center'); 
			     $(this).bind("mouseout", function(){ 
			     $(this).css("background", '#909090 url(../images/icon_play.png) no-repeat center'); 
			     }) 
		    });

			// 홀드 버튼 클릭
			$(".RowJobAction").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data");
				PopWin('/drawing/issue/popup/drawing_status?callback=afterInputStatus&key=' + key,'600','330','no');
			});

			// 홀드 버튼 클릭
			$(".RowProgress").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data");
				PopWin('/drawing/popup/drawing_progress?key=' + key,'400','230','no');
			});
			
			$(".SubmitButton").click(function(e){
				$("form").submit();				
			});
		});

		</script>
 
	</layout:put>
</layout:extends>