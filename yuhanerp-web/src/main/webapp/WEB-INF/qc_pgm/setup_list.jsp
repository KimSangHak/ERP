<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="../layout/MctMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공&QC&조립</li>
                        <li>Setup 현황</li>
                    </ul> 
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->

                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>Setup 진행</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:100px;" />
                                <col style="width:80px;" />
                                <col style="width:150px;" />
                                <col style="width:120px;" />
                                <col style="width:120px;" />
                                <col style="width:200px;" />
                                <col style="width:70px;" />
                                <col style="width:100px;" />
                                <col style="width:80px;" />
                                <col style="width:70px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>업체</th>
                                        <th>Device</th>
                                        <th>예상일정</th>
                                        <th>확정일정</th>
                                        <th>내용</th>
                                        <th>팀장</th>
                                        <th>담당자</th>
                                        <th>상태</th>
                                        <th>보고&요청</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${items}">
                                	<tr data="${item.id }">
                                    	<td>${item.orderFullNo }</td>
                                    	<td>${item.customerName }</td>
                                    	<td>${item.device }</td>
                                        <td>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	<a href="#" class="RowExpectDate">
	                                        </c:if>
	                                        	<c:choose>
	                                        		<c:when test="${(item.finishBuyoffInDate eq null) or (item.finishBuyoffInDate eq '')}">
	                                        		<p style="color:red"><b>내부Buyoff : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectBuyoffInDate }" /></b></p>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		내부Buyoff:<fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectBuyoffInDate }" />
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        	<c:choose>
	                                        		<c:when test="${(item.finishBuyoffOutDate eq null) or (item.finishBuyoffOutDate eq '')}">
	                                        		<p style="color:red"><b>고객사Buyoff : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectBuyoffOutDate }" /></b></p>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		<br>고객사Buyoff:<fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectBuyoffOutDate }" />
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        	<c:choose>
	                                        		<c:when test="${(item.shippingDate eq null) or (item.shippingDate eq '')}">
	                                        		<p style="color:red"><b>출고일 : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectShippingDate }" /></b></p>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		<br>출고일:<fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectShippingDate }" />
	                                        		</c:otherwise>
	                                        	</c:choose>
	                                        	<c:choose>
	                                        		<c:when test="${(item.outSetupFinishDate eq null) or (item.outSetupFinishDate eq '')}">
	                                        		<p style="color:red"><b>완료일 : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectFinishDate }" /></b></p>
	                                        		</c:when>
	                                        		<c:otherwise>
	                                        		<br>완료일:<fmt:formatDate pattern = "yy.MM.dd" value = "${item.expectFinishDate }" />
	                                        		</c:otherwise>
	                                        	</c:choose>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	</a>
	                                        </c:if>
                                        </td>
                                        <td>
                                  			내부Buyoff : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.finishBuyoffInDate }" /><br>
                                  			고객사Buyoff : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.finishBuyoffOutDate }" /><br>
                                  			출고일 : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.shippingDate }" /><br>
                                  			출장시작 : <fmt:formatDate pattern = "yy.MM.dd" value = "${item.outSetupStartDate }" />
                                        </td>
                                    	<td>${item.note }</td>
                                    	<td>${item.managerName }</td>
                                        <td>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	<a href="#" class="RowUserSet">
	                                        </c:if>
												조립:
		                                        <c:if test="${(item.assemble1Id ne null) and (item.assemble1Id ne '')}">
													${item.assemble1Name }
													<c:if test="${(item.assemble2Id ne null) and (item.assemble2Id ne '')}">
													, ${item.assemble2Name }
														<c:if test="${(item.assemble3Id ne null) and (item.assemble3Id ne '')}">
														, ${item.assemble3Name }
														</c:if>													
													</c:if>
		                                        </c:if><br>
												PGM:
		                                        <c:if test="${(item.pgm1Id ne null) and (item.pgm1Id ne '')}">
													${item.pgm1Name }
													<c:if test="${(item.pgm2Id ne null) and (item.pgm2Id ne '')}">
													, ${item.pgm2Name }
														<c:if test="${(item.pgm3Id ne null) and (item.pgm3Id ne '')}">
														, ${item.pgm3Name }
														</c:if>													
													</c:if>
		                                        </c:if><br>
		                                        <!-- 
												배선:
		                                        <c:if test="${(item.wiring1Id ne null) and (item.wiring1Id ne '')}">
													${item.wiring1Name }
													<c:if test="${(item.wiring2Id ne null) and (item.wiring2Id ne '')}">
													, ${item.wiring2Name }
														<c:if test="${(item.wiring3Id ne null) and (item.wiring3Id ne '')}">
														, ${item.wiring3Name }
														</c:if>													
													</c:if>
		                                        </c:if><br>
	                                         	-->
												영업:
		                                        <c:if test="${(item.business1Id ne null) and (item.business1Id ne '')}">
													${item.business1Name }
													<c:if test="${(item.business2Id ne null) and (item.business2Id ne '')}">
													, ${item.business2Name }
														<c:if test="${(item.business3Id ne null) and (item.business3Id ne '')}">
														, ${item.business3Name }
														</c:if>													
													</c:if>
		                                        </c:if><br>
												로봇:
		                                        <c:if test="${(item.robot1Id ne null) and (item.robot1Id ne '')}">
													${item.robot1Name }
													<c:if test="${(item.robot2Id ne null) and (item.robot2Id ne '')}">
													, ${item.robot2Name }
														<c:if test="${(item.robot3Id ne null) and (item.robot3Id ne '')}">
														, ${item.robot3Name }
														</c:if>													
													</c:if>
		                                        </c:if>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	</a>
	                                        </c:if>
                                    	</td>
                                    	<td>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	<a href="#" class="RowStatusSet">
	                                        </c:if>
                                        	<c:choose>
                                        		<c:when test="${item.workStatus eq 'I' }">
                                        			사내작업중<br><span class="icon_play">아이콘</span>
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'O' }">
                                        			고객사작업중<br><span class="icon_play">아이콘</span>
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'F' }">
                                        			완료<br><span class="icon_check">아이콘</span>
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'H' }">
                                        			홀딩<br><span class="icon_lock">아이콘</span>
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'S' }">
                                        			중단<br><span class="icon_stop">아이콘</span>
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'C' }">
                                        			취소<br><span class="icon_stop">아이콘</span>
                                        		</c:when>
                                        		<c:otherwise>
                                        			대기<br><span class="icon_play2">아이콘</span>
                                        		</c:otherwise>
                                        	</c:choose>
                                        	<c:if test="${(item.manager_id eq updateId) or (item.businessUserId eq updateId)}">
	                                        	</a>
	                                        </c:if>
                                    	</td>
                                    	<td>
                   							<a href="#" class="RowReport">보고</a><br>
                   							<a href="#" class="RowRequest">요청</a>
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
			var suc_url = "/qcPgm/programList";

			$.post( "/qcPgm/program_status", {
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

		function afterInputDelieve(keyVal, dwkeyVal, fromId, toId ){
			var suc_url = "/qcPgm/programList";

			$.post( "/qcPgm/qc_delivery", {
			    key: keyVal,
			    dwkey: dwkeyVal,
			    fromid: fromId,
			    toid: toId
			}, function(jqXHR) {
			    alert( "인수인계 처리가 완료되었습니다." );
			    location.href=suc_url;
			}, 'json' /* xml, text, script, html */)
			.fail(function(jqXHR) {
				alert( "인수인계 처리에 오류가 발생했습니다." );
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
			$(".RowStatusSet").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data");
				PopWin('/qcPgm/popup/setup_status?callback=afterInputStatus&key=' + key,'600','330','no');
			});
			
			// 홀드 버튼 클릭
			$(".RowProgress").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data");
				PopWin('/qcPgm/popup/program_progress?key=' + key,'400','230','no');
			});
			// 홀드 버튼 클릭
			$(".RowJobMctView").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data");
				PopWin('/qcPgm/popup/process_list?key=' + key,'1800','760','yes');
			});
		});

		</script>
 
	</layout:put>
</layout:extends>