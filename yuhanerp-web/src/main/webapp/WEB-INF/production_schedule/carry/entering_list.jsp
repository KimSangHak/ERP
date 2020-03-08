<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	//	생상일정관리
	//	 ㄴ 입고 리스트
%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>입출고관리</li>
                        <li>입고 리스트</li>
                    </ul> 
                    <h2>입고품 리스트</h2>
                </div>
                <!-- 서브 타이틀 -->

                <!-- 서브 콘텐츠 -->
                <div class="contArea">
					<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                    		<form id="SearchForm">
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
                            		<th>Order NO</th>
                                    <td>
                                    	<c:import url="/internal/util/select/order_no">
                                        	<c:param value="orderNo" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="orderNo" name="cssClass" />
                                        	<c:param value="width:150px" name="style" />
                                        </c:import>
                                        -
                                        <input type="text" id="drawingNo" name="drawingNo" value="" class="drawingNo" style="width:80px">
                                    </td>
                                    <th>거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>                                    	
                                    </td>
                                </tr>
                            </table>
                            </form>
                        </div>
                        <div class="searchBtn"><a href="#" id="SubmitButton" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                
                
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>입고 내역</h3>
                        	
                        	<c:choose>
                         		<c:when test="${isupdate eq 'Y'}">
                         			<div class="btnArea">
                            			<a href="javascript:openCarryInPopup('finishedIn');" class="btn_line_blue">선택도면 완품 입고</a> 
                            			<a href="javascript:openCarryInPopup('processIn');" class="btn_line_blue">선택도면 가공 입고</a>
                            			<a href="javascript:openCarryInPopup('postProcIn');" class="btn_line_blue">선택도면 후처리 가공 입고</a>
                            			<a href="javascript:openCarryInPopup('reprocessIn');" class="btn_line_blue">선택도면 가공 입고 후 재가공</a>
                            		</div>
                         		</c:when>
                         		<c:otherwise>
                         						
                         		</c:otherwise>                       				
                         	</c:choose>
                           
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
		                    <table>
		                        <caption> </caption>
		                        <colgroup span="2">
		                        <col style="width:2%;" />
		                        <col style="width:8%;" />
		                        <col style="width:10%;" />
		                        <col style="width:5%;" />
		                        <col style="width:5%;" />
		                        <col style="width:6%;" />
		                        <col style="width:5%;" />
		                        <col style="width:4%;" />
		                        <col style="width:6%;" />
		                        <col style="width:7%;" />
		                        <col style="width:6%;" />
		                        <col style="width:4%;" />
		                        <col style="width:6%;" />
		                        <col style="width:4%;" />
		                        <col style="width:5%;" />
		                        <col style="width:5%;" />
		                        </colgroup>
		                        <thead>
		                        	<tr>
		                        		<th><input type="checkbox" id="checkAll"></th>
			                            <th>도면번호</th>
			                            <th>품명</th>
			                            <th>설계자</th>
			                            <th>고객사<br>납품일</th>
			                            <th>Mat’l</th>
			                            <th>열처리</th>
			                            <th>수량</th>
			                            <th>후처리</th>
			                            <th>가공업체</th>
			                            <th>가공납기</th>
			                            <th>검사<br>필요</th>
			                            <th>검사일</th>
			                            <th>판정</th>
			                            <th>인수자</th>
			                            <th>인계자</th>
		                            </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach var="jobitem" items="${items}">
		                            <tr data-id="${jibitem.id }">
		                            	<td><input type="checkbox" class="RowCheckBox" name="id" value="${jobitem.id }"></td>
			                           	<td>${jobitem.drawingFullNo }</td>
			                           	<td>${jobitem.description }</td>
			                           	<td>${jobitem.drawingUserName }</td>
			                           	<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.installDate }" /></td>
			                           	<td>${jobitem.material }</td>
			                           	<td>${jobitem.thermal }</td>
			                           	<td>${jobitem.quantity }/${jobitem.spare }</td>
			                           	<td>${jobitem.postprocessing }</td>
			                           	<td>${jobitem.outSourceName }</td>
		                          		<c:choose>
		               						<c:when test="${jobitem.processCompleted eq 'Y'}">
		               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.processFinishDate }" /></td>
		               						</c:when>
		               						<c:otherwise>
		               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.processFinishPlanDate }" /></td>
		               						</c:otherwise>                       				
		               					</c:choose>
			                           	<td>${jobitem.checking }</td>
			                           	
		                          		<c:choose>
		               						<c:when test="${jobitem.inspectId gt 0}">
		               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.inspectDate }" /></td>
		               							<td>${jobitem.inspectResult }</td>
		               						</c:when>
		               						<c:otherwise>
		               							<td><fmt:formatDate pattern = "yy.MM.dd" value = "${jobitem.inspectPlanDate }" /></td>
		               							<td> </td>
		               						</c:otherwise>                       				
		               					</c:choose>
			                           	<td>${jobitem.assemblyTrToName }</td>
			                           	<td>${jobitem.assemblyTrFromName }</td>
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
		// 선택된 URL 에 파라미터 붙여서 열기				
		function openCarryInPopup(url){
			
			// 선택된 행의 도면ID 추가
			var errCheck = "N";
			var selected = [];
			$(".RowCheckBox:checked").each( function(){
				if (url=="reprocessIn"){
					var TR = $(this).parent().parent();
					var TD = TR.children();
					var key = TR.attr("data-id");
					var position = TR.attr("data-po");
					var drawingNo = TD.eq(1).text();
					if (position=="O") {
						if (key==null || key=="") {
							alert("선택한 도면("+ drawingNo + ")은 재가공 등록을 할 수 없는 도면입니다.");
							errCheck = "Y";
							return;
						}
					}
				}
				selected.push($(this).val());
			});
			
			if (errCheck=="Y") {
				return;
			}
			if(selected.length == 0){
				alert("입고 처리할 대상을 선택해주세요");
				return;
			}
			
			var extra = "?id=" + selected.join(",");
			
			PopWin('/ps/carry/popup/' + url + extra,'1000','600','no');
		}
		
		function	getFindParameter(){
			return "?key=" + escape( $("#orderNo").val() ) + "&drawingNo=" + escape( $("#drawingNo").val() ) + "&outPartnerId=" + escape( $("#outsourcingPartnerId").val() );
		}
		
		$(document).ready(function(){
		
			// 전체 선택/해제
			$("#checkAll").click(function(e){
			
				var checked = $(this).is(":checked");
				if(console)
					console.log("checked = " + checked);
				
				$(".RowCheckBox").each( function(){
					$(this).prop("checked", checked);
				});
										
			});
			
			// 검색버튼
			$("#SubmitButton").click(function(e){
				e.preventDefault();
				
				location.href=getFindParameter();  
			});
		
		});
		</script>
	</layout:put>
</layout:extends>