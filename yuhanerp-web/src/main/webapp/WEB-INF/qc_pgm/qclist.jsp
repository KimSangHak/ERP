<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="/WEB-INF/layout/MctMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공&QC&조립</li>
                        <li>검사 현황</li>
                    </ul> 
                    <h2>검사 현황</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->
                	<form method="GET" id="FindForm">
                    <div class="searchArea">
                    	<div class="searchBox">
                    		
                        	<table>
                            	<caption> </caption>
                                <col style="width:10%;;" />
                                <col style="width:30%;" />
                                <col style="width:10%;" />
                                <col style="width:30%;" />
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w100"> - <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra }" class="w100"> - <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo }" class="w100">
                                    </td>
                                    <th>가공완료일</th>
                                    <td>
                                        <input type="text" name="mctDateFrom" id="mctDateFrom" value="${mctDateFrom}" class="w100 AutoDatePicker"> - <input type="text" name="mctDateTo" id="mctDateTo" value="${mctDateTo }" class="w100 AutoDatePicker">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn">
                        	<a href="#" class="btn_blue" id="FindButton">검색</a>
                        </div>
                    </div>
                    </form>
                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">

                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:80px;" />
                                <col style="width:190px;" />
                                <col style="width:100px;" />
                                <col style="width:100px;" />
                                <col style="width:100px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:80px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                <col style="width:60px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>도면번호</th>
                                        <th>Discription</th>
                                        <th>사내가공/<br/>외주업체</th>
                                        <th>후처리업체</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>가공완료일</th>
                                        <th>납품일</th>
                                        <th>현재상태</th>
                                        <th>검사시작</th>
                                        <th>홀딩</th>
                                        <th>일시정지</th>
                                        <th>완료</th>
                                        <th>인계</th>
                                    </tr>
                                </thead>

                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr class="" data-id="${item.id }" dw-id="${item.jobDrawingId }" tcnt="${item.quantity }">
                                    	<td>${item.drawingFullNo }</td>
                                        <td>${item.description }</td>
                                        <td>${item.outSourceName }</td>
                                        <td>기성</td>
                                        <td>${item.material }</td>
                                        <td>${item.thermal }</td>
                                        <td>${item.quantity }</td>
                                        <td>${item.spare }</td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.finishDate }" /></td>
                                        <td><fmt:formatDate pattern = "yy.MM.dd" value = "${item.installDate }" /></td>
                                        <td>${item.workStatusText }</td>
                                        <td>
                                        	<c:if test="${item.workStatus eq 'I'}">
                                        	<span class="icon_play">아이콘</span>
                                        	</c:if>
                                        	<c:if test="${item.workStatus ne 'I'}">
                                        		<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="#" class="RowPlay"><span class="icon_play2">아이콘</span></a>
                         							</c:when>
                         							<c:otherwise>
                         								<span class="icon_play2">아이콘</span>
                         							</c:otherwise>                       				
                         						</c:choose>
                                        	</c:if>
                                        </td>
                                        <td>
                                        	<c:if test="${item.workStatus eq 'H'}">
                                        	<span class="icon_lock">아이콘</span>
                                        	</c:if>
                                        	<c:if test="${item.workStatus ne 'H'}">
                                        		<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="#" class="RowHold"><span class="icon_nolock">아이콘</span></a>
                         							</c:when>
                         							<c:otherwise>
                         								<span class="icon_nolock">아이콘</span>
                         							</c:otherwise>                       				
                         						</c:choose>                       
                                        	</c:if>
                                        </td>
                                        <td>
                                        	<c:if test="${item.workStatus eq 'S'}">
                                        	<span class="icon_stop">아이콘</span>
                                        	</c:if>
                                        	<c:if test="${item.workStatus ne 'S'}">
                                        	
                                        		<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="#" class="RowStop"><span class="icon_nostop">아이콘</span></a>
                         							</c:when>
                         							<c:otherwise>
                         								<span class="icon_nostop">아이콘</span>
                         							</c:otherwise>                       				
                         						</c:choose>           
                                        		
                                        	</c:if>
                                        </td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${item.workStatus eq 'I' }">
                                        		
                                        			<c:choose>
                         								<c:when test="${isupdate eq 'Y'}">
                         									<a href="#" class="RowAction"><span class="icon_nocheck">아이콘</span></a>
                         								</c:when>
                         								<c:otherwise>
                         									<span class="icon_nocheck">아이콘</span>
                         								</c:otherwise>                       				
                         							</c:choose>           
                                        			
                                        		</c:when>
                                        		<c:when test="${item.workStatus eq 'F' }">
                                        			<span class="icon_check">아이콘</span>
                                        		</c:when>
                                        		<c:otherwise>
                                        			<span class="icon_nocheck">아이콘</span>
                                        		</c:otherwise>
                                        	</c:choose>
                                        </td>
                                        <td>
                                        	<c:if test="${item.workStatus eq 'F'}">
                                        		
                                        		<c:choose>
                         							<c:when test="${isupdate eq 'Y'}">
                         								<a href="#" class="RowDelive">인계하기</a>
                         							</c:when>
                         							<c:otherwise>
                         								인계하기
                         							</c:otherwise>                       				
                         						</c:choose>         
                                        		
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
		
		function getFindParameter(){
			return "?orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() )
			 + "&drawingNo=" + escape( $("#drawingNo").val() ) + "&mctDateFrom=" + escape( $("#mctDateFrom").val() )  + "&mctDateTo=" + escape( $("#mctDateTo").val() );
		}
		
		function getParameterUpdate(){
			return "&orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() )
			 + "&drawingNo=" + escape( $("#drawingNo").val() ) + "&mctDateFrom=" + escape( $("#mctDateFrom").val() )  + "&mctDateTo=" + escape( $("#mctDateTo").val() );
		}
		
		function afterInputCancelReason(keyVal, dwkeyVal, atypeVal, text, messageTxt){
			var suc_url = "/qcPgm/qclist" + getFindParameter();

			$.post( "/qcPgm/status_update", {
			    key: keyVal,
			    dwkey: dwkeyVal,
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
		
		function afterInputComplete(keyVal, dwkeyVal, resultOk, okCnt, failCnt ){
			var suc_url = "/qcPgm/qclist" + getFindParameter();
			
			//alert("key="+keyVal+",dw="+dwkeyVal+",res="+resultOk+",ok="+okCnt+",fail="+failCnt);

			$.post( "/qcPgm/qc_complete", {
			    key: keyVal,
			    dwkey: dwkeyVal,
			    resultok: resultOk,
			    okcnt: okCnt,
			    failcnt: failCnt
			}, function(jqXHR) {
			    alert( "검사완료 등록이 되었습니다." );
			    location.href=suc_url;
			}, 'json' /* xml, text, script, html */)
			.fail(function(jqXHR) {
			    alert( "감사 완료 처리에 오류가 발생했습니다." );
			});
		}
		
		function afterInputDelieve(keyVal, dwkeyVal, fromId, toId ){
			var suc_url = "/qcPgm/qclist" + getFindParameter();

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
			
			// 검색버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				location.href=getFindParameter();  
			});
		    $('.icon_play2').bind("mouseover", function(){ 
			     var color = $(this).css("background-color"); 
			     $(this).css("background", '#50b340  url(../images/icon_play.png) no-repeat center'); 
			     $(this).bind("mouseout", function(){ 
			     $(this).css("background", '#909090 url(../images/icon_play.png) no-repeat center'); 
			     }) 
		    });
		    $('.icon_nolock').bind("mouseover", function(){ 
			     var color = $(this).css("background-color"); 
			     $(this).css("background", '#e09900 url(../images/icon_lock.png) no-repeat center'); 
			     $(this).bind("mouseout", function(){ 
			     $(this).css("background", '#909090 url(../images/icon_lock.png) no-repeat center'); 
			     }) 
		    });
		    $('.icon_nostop').bind("mouseover", function(){ 
			     var color = $(this).css("background-color"); 
			     $(this).css("background", '#e16426 url(../images/icon_stop.png) no-repeat center'); 
			     $(this).bind("mouseout", function(){ 
			     $(this).css("background", '#909090 url(../images/icon_stop.png) no-repeat center'); 
			     }) 
		    });
		    $('.icon_nocheck').bind("mouseover", function(){ 
			     var color = $(this).css("background-color"); 
			     $(this).css("background", '#377bd8 url(../images/icon_check.png) no-repeat center'); 
			     $(this).bind("mouseout", function(){ 
			     $(this).css("background", '#909090 url(../images/icon_check.png) no-repeat center'); 
			     }) 
		    });
			// 작업시작 버튼 클릭
			$(".RowPlay").click(function(e){
				e.preventDefault();

				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
				var dwkey = TR.attr("dw-id");
 
				//location.href="?atype=I&key=" + key + getParameterUpdate();
				afterInputCancelReason(key, dwkey, "I", "Play", "검사시작으로 설정되었습니다.");
			});
			// 홀드 버튼 클릭
			$(".RowHold").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
				var dwkey = TR.attr("dw-id");
 
				PopWin('/qcPgm/popup/cancel_reason?callback=afterInputCancelReason&atype=H&key=' + key + '&dwkey=' + dwkey,'600','280','no');

			});

			// 정지 버튼 클릭
			$(".RowStop").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
				var dwkey = TR.attr("dw-id");
 
				PopWin('/qcPgm/popup/cancel_reason?callback=afterInputCancelReason&atype=S&key=' + key + '&dwkey=' + dwkey,'600','280','no');
			});
		    
			// 완료 버튼 클릭
			$(".RowAction").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
				var dwkey = TR.attr("dw-id");
				var totCnt = TR.attr("tcnt");
				
				var suc_url = "/qcPgm/popup/qc_complete?callback=afterInputComplete&dwkey=" + dwkey + "&totCnt=" + totCnt + "&key=" + key;
 				PopWin(suc_url,'600','280','no');
			});
		    
			// 인수인계 버튼 클릭
			$(".RowDelive").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
				var dwkey = TR.attr("dw-id");
 
				PopWin('/qcPgm/popup/qc_delive?callback=afterInputDelieve&key=' + key + '&dwkey=' + dwkey,'600','280','no');

			});		    
		});

		</script>
 
	</layout:put>
</layout:extends>