<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	//	생상일정관리
	//	 ㄴ 완품/가공 입고
%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>입출고관리</li>
                        <li>완품/가공 입고</li>
                    </ul> 
                    <h2>완품/가공 입고</h2>
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
                                    <td><input type="text" id="orderNoBase" name="orderNoBase" id="orderNoBase" value="" class="w130"> - 
                                    <input type="text" id="orderNoExtra" name="orderNoExtra" id="orderNoExtra" value=""  class="w130">
                                    </td>
                                    <th>거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>                                    	
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                    	<input type="text" id="orderDateFrom" name="orderDateFrom" value="" class="w130 AutoDatePicker"> - 
                                    	<input type="text" id="orderDateTo" name="orderDateTo" value="" class="w130 AutoDatePicker">
                                    </td>
                                </tr>
                                <tr>
                                	<th>상품유형</th>
                                    <td colspan="5">
                                        <label><input type="checkbox" id="processInsourcing" name="processInsourcing" value="Y" checked/>사내가공품</label>
                                        <label><input type="checkbox" id="processOutsourcing" name="processOutsourcing" value="Y" checked/>외주 가공품</label>
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
                         				<a href="javascript:openCarryInPopup2('checkTrue');" class="btn_line_blue">검사 필요 변경</a>
                            			<a href="javascript:openCarryInPopup('finishedIn');" class="btn_line_blue">선택도면 완품 입고</a> 
                            			<a href="javascript:openCarryInPopup('processIn');" class="btn_line_blue">선택도면 가공 입고</a>
                            			<a href="javascript:openCarryInPopup('postProcIn');" class="btn_line_blue">선택도면 후처리 가공 입고</a>
                            			<a href="javascript:openCarryInPopup('reprocessIn');" class="btn_line_blue">선택도면 가공 입고 후 사외 재가공</a>
                            			<a href="javascript:openCarryInPopup3('reprocessOutToIn');" class="btn_line_blue">사외 가공 입고 후 사내 재가공</a>
                            		</div>
                         		</c:when>
                         		<c:otherwise>
                         						
                         		</c:otherwise>                       				
                         	</c:choose>
                           
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="DataTableList" style="width : 100%">
                            	<thead>
                                	<tr>
                                    	<th><input type="checkbox" id="checkAll"></th>
                                    	<th>도면번호</th>
                                    	<th>품명</th>
                                    	<th>발주번호</th>
                                        <th>설계자</th>
                                        <th>Mat’l</th>
                                        
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>가공업체</th>
                                        
                                        <th>가공납기예정일</th>
                                        <th>검사여부</th>
                                        <th>검사일</th>
                                        <th>판정</th>
                                    </tr>
                                </thead>
                                
                                <tbody>

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
		
			var mainTable;
		
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
			
			
			function openCarryInPopup2(url){
				
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
				
				if(confirm("검사 필요한 도면으로 변경하시겠습니까?")){
				
				
					location.href = '/ps/carry/popup/'+ url + extra;
				}
				
			}
			
			
			function openCarryInPopup3(url){
				
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
				
				if(confirm("사외 가공 입고 후 사내 재가공을 하시겠습니까?")){
				
				
					location.href = '/ps/carry/popup/'+ url + extra;
				}
				
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
				
				$("#SubmitButton").click(function(e){
					e.preventDefault();
					
					mainTable.ajax.reload();
				});
				
				// 입고 내역 불러오기
				mainTable = $("#DataTableList").DataTable({
					"paging" : true,
					"lengthChange" : false,
					"lengthMenu" : yerpPaingSize,
					"ordering" : false,
					"info" : false,
					"searching" : false,
					"language" : {
						"lengthMenu" : "Display _MENU_ records per page",
						"zeroRecords" : "표시할 데이터가 없습니다",
						"info" : "Showing page _PAGE_ of _PAGES_",
						"infoEmpty" : "No records available",
						"infoFiltered" : "(filtered from _MAX_ total records)"
					},
					"serverSide" : true,
					"ajax" : {
						url : "/ps/carry/data/in",
						"type" : "POST",
						data : function(d){
							d.orderNoBase = $("#orderNoBase").val();
							d.orderNoExtra = $("#orderNoExtra").val();
							d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
							d.processInsourcing = $("#processInsourcing:checked").val();
							d.processOutsourcing = $("#processOutsourcing:checked").val();
							d.orderDateFrom = $("#orderDateFrom").val();
							d.orderDateTo = $("#orderDateTo").val();
						}
					},
					'select': {
				         'style': 'multi'
				      },
					"createdRow" : function(row,data,dataIndex){
						$(row).attr("data-id", data.outUid);
						$(row).attr("data-po", data.workPosition);
					},
					"columns" : [						
						{ "data" : "id", 'render': function (data, type, full, meta){
				             return '<input type="checkbox" class="RowCheckBox" name="id" value="' + $('<div/>').text(data).html() + '">';
				             }  
						},
						{"data" : "designDrawingNo"},
						{"data" : "description"},
						{"data" : "outsourcingOrderNo"},
						{"data" : "designUserName"},
						{"data" : "material"},
						
						{"data" : "thermal"},
						{"data" : "quantity"},
						{"data" : "spare"},
						{"data" : "postprocessing"},
						{"data" : "outsourcingPartnerName"},
						
						{"data" : "deliveryPlanDate"},
						{"data" : "checking"},
						{"data" : "inspectDate"},
						{"data" : "testResult"}
					]
				});
			
			});
		</script>
	
	</layout:put>
</layout:extends>