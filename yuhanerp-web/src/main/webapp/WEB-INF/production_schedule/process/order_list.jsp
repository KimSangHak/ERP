<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	//	생상일정관리 - 가공 관리 - 가공 발주 리스트
	//
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
                        <li>생산일정관리</li>
                        <li>가공 발주 리스트</li>
                    </ul> 
                    <h2>가공 발주 리스트</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<form method="GET">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:7%;" />
                                <col style="width:25%;" />
                                <col style="width:7%;" />
                                <col style="width:20%;" />
                                <col style="width:7%;" />
                                <col style="width:25%;" />
                                </colgroup>
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w70"> - 
                                        <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra}" class="w130"> - 
                                        <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo}" class="w70">
                                    </td>
                                    <th>발주업체</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="${outsourcingPartnerId }" name="defaultValue" />
                                        </c:import>
                                    </td>
                                    <th>발주일</th>
                                    <td colspan="5"><input type="text" size="2" id="orderDateFrom" name="orderDateFrom" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${orderDateFrom}" />"> -
                                    				<input type="text" size="2" id="orderDateTo" name="orderDateTo" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${orderDateTo}" />">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        </form>
                        <div class="searchBtn"><a href="#" id="FindButton" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>내역 리스트</h3>
                        		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				 <div class="btnArea"><a href="#" id="UpdateOutsourcingOrder" class="btn_line_blue">선택한 리스트 발주 수정</a></div>
                         			</c:when>
                         			<c:otherwise>
                         						
                         			</c:otherwise>                       				
                         		</c:choose>
                        	
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                        <th><input type="checkbox" id="checkAll"></th>
                                        <th>발주처</th>
                                        <th>발주NO</th>
                                        <th>발주일</th>
                                        <th>도면번호</th>
                                        <th>Description</th>
                                        <th>Dimensions</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>납기일</th>
                                        <th>단가</th>
                                        <th>공급가</th>
                                        <th>비고</th>
                                        <th>상태</th>
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
		<script type="text/javascript">
		
		var mainTable;
		
		$(document).ready(function(){

			// 검색 버튼
			$("#FindButton").click(function(e){
				e.preventDefault();

				mainTable.ajax.reload();		
			});
			
			// 전체 선택/해제
			$("#outsourcingSelector").click(function(e){
				e.preventDefault();
				
				var checked = $(this).is("checked");
				$(".outsourcingSelector").each(function(){
					$(this).prop("checked", checked);
				});
			});
			
			// 선택한 리스트 발주 수정
			$("#UpdateOutsourcingOrder").click(function(e){
				e.preventDefault();
								
				var checkedRequests = [];
				$(".RowCheckBox:checked").each(function(){
					checkedRequests.push( $(this).val() );
				});
				
				if(checkedRequests.length == 0){
					alert("수정할 발주 내역을 선택해 주세요");
					return;
				}
								
				PopWin('/ps/process/popup/request_update_order?id=' + checkedRequests.join(),'1400','600','no');				
			});
			
			// 전체 선택/해제
			$("#checkAll").click(function(e){
			
				var checked = $(this).is(":checked");
				if(console)
					console.log("checked = " + checked);
				
				$(".RowCheckBox").each( function(){
					$(this).prop("checked", checked);
				});										
			});
			
			mainTable = $("#TBL").DataTable({
				"paging" : true,
				"lengthMenu": yerpPaingSize, 
				"lengthChange": false,
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
					"url" : "/ps/process/data/order_list",
					"type" : "POST",
					"data" : function(d){
						d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
						
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
						
						d.orderDateFrom = $("#orderDateFrom").val();
						d.orderDateTo = $("#orderDateTo").val();
					}
				},
				"columns" : [						
					{ "data" : "id", 'render': function (data, type, full, meta){
			             return '<input type="checkbox" class="RowCheckBox" name="id" value="' + $('<div/>').text(data).html() + '">';
			             }  
					},
					{"data" : "outsourcingPartnerName"},
					{"data" : "outsourcingRequestNo"},
					{"data" : "outsourcingRequestDate"},
					
					{"data" : "designDrawingNo"},
					{"data" : "description"},
					{"data" : "dimension"},
					{"data" : "material"},					
					{"data" : "thermal"},
					{"data" : "quantity"},
					{"data" : "spare"},
					{"data" : "postprocessing"},					
					{"data" : "deliveryPlanDate"},
					{"data" : "unitPrice"},
					{"data" : "null", "render" : function(data, type, full){
						return full.unitPrice * full.quantity;
					} },	
					{"data" : "note"},	// 비고
					{"data" : "drawingCurrentStatus"}
				]
			});
			
		});
		
		</script>
	</layout:put>
	<layout:put block="BodyScriptBlock2">
	

	
	</layout:put>
</layout:extends>