<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

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
                        <li>외주 가공 발주</li>
                    </ul> 
                    <h2>외주 가공 발주</h2>
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
                                <col style="width:21%;" />
                                <col style="width:7%;" />
                                <col style="width:12%;" />
                                <col style="width:5%;" />
                                <col style="width:12%;" />
                                <col style="width:6%;" />
                                <col style="width:12%;" />
                                </colgroup>
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" name="orderNoBase" id="orderNoBase" value="" class="w70"> - 
                                        <input type="text" name="orderNoExtra" id=orderNoExtra""  value="" class="w130"> - 
                                        <input type="text" name="drawingNo" id="drawingNo" value="" class="w70">
                                    </td>
                                    <th>발주업체</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="partnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>
                                    </td>
                                    <th>Device</th>
                                    <td>
                                    	<input type="text" name="device" id="device" value="" class="w150">
                                    </td>
                                    <th>고객사</th>
                                    <td>
                                        
                                        <c:import url="/internal/util/select/customer">
                                        	<c:param value="selectedCustomer" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w160" name="cssClass" />
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>
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
                        	<h3>가공 내역</h3>
                            <div class="btnArea">
                            	<a id="requestEstimationButton" href="#" class="btn_line_blue">견적 요청</a> <a id="requestOrderButton" href="#" class="btn_line_blue">발주 요청</a> <a id="preProcess" href="#" class="btn_line_blue">가공 내역 이동</a></div>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                        <th><input type="checkbox" id="checkAll"></th>
                                        <th>업체</th>
                                        <th>Device</th>
                                        <th>도면번호</th>
                                        <th>설계자</th>
                                        <th>Description</th>
                                        <th>Dimensions</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>발주업체</th>
                                        <th>가공<br/>완료예정</th>
                                        <th>후처리<br/>완료예정</th>
                                        <th>검사<br/>완료예정</th>
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
	
	function getCheckAndGo(baseUrl){
		var checkedLines = $("table tbody tr td input[type='checkbox']:checked");
		if( checkedLines.length == 0 ){
			alert("요청할 내역을 선택해 주세요");
			return;
		}
		
		var lastPartnerId = null;
		var differentPartnerIdFound = false;
		var url = baseUrl + "?";
		checkedLines.each(function(i){
			url += "id=" + $(this).val() + ( (i + 1 != checkedLines.length) ? "&" : "" );
			var curPartnerId = $(this).parent().parent().attr("data-partnerId");
			if(lastPartnerId == null)
				lastPartnerId = curPartnerId;
			else{
				if(lastPartnerId != curPartnerId){
					differentPartnerIdFound = true;		
				}
			}
		});
		
		if(differentPartnerIdFound){
			alert("한번에 한 업체에 대한 견적/발주가 가능합니다");
			return;
		}
		url += "&partnerId=" + lastPartnerId;
		PopWin(url ,'1400','600','no');		
	}
	
	function getCheckAndGo2(baseUrl){
		var checkedLines = $("table tbody tr td input[type='checkbox']:checked");
		if( checkedLines.length == 0 ){
			alert("요청할 내역을 선택해 주세요");
			return;
		}
		
		
		var url = baseUrl + "?";
		checkedLines.each(function(i){
			url += "id=" + $(this).val() + ( (i + 1 != checkedLines.length) ? "&" : "" );
			
		});
		
	
		location.href = url;
	}
	
	var mainTable;
	
	$(document).ready(function(){
		
		$("input[name='searchPartnerTarget'][value='${searchPartnerTarget}']").prop("checked", true);
				
		// 검색 버튼
		$("#FindButton").click(function(e){
			e.preventDefault();
			// $("form").submit();
			mainTable.ajax.reload();
		});
		
		// 전체 체크/해제
		$("#checkAll").change(function(){
			var checked = $(this).prop("checked");
			$("table tbody tr td input[type='checkbox']").prop("checked", checked);
		});
		
		// 견적 요청 버튼
		$("#requestEstimationButton").click(function(e){
			e.preventDefault();
			
			getCheckAndGo("/ps/process/popup/request_outsourcing/estimates");
		});
		
		//가공내역관리 이동 버튼
		$("#preProcess").click(function(e){
			e.preventDefault();
			
			 if(confirm("선택 된 도면을 가공 내역 관리로 이동하시겠습니까?(이전단계)")){
			
				getCheckAndGo2("/ps/process/data/statusEditFromI");
			
			 }
		});
		
		// PopWin('','1000','600','no')
		$("#requestOrderButton").click(function(e){
			e.preventDefault();

			getCheckAndGo("/ps/process/popup/request_outsourcing/order");
		});		
		
		// 입고 내역 불러오기
		mainTable = $("#TBL").DataTable({
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
				url : "/ps/process/data/outsourcing",
				data : function(d){
					d.device = $("#device").val();
					d.orderNoBase = $("#orderNoBase").val();
					d.orderNoExtra = $("#orderNoExtra").val();
					d.selectedCustomer = $("#selectedCustomer").val();
					d.partnerId = $("#partnerId").val();
				}
			},
			"createdRow" : function(row, data){
				$(row).attr("data-partnerId", data.outsourcingPartnerId);
			},
			"columns" : [						
				{ "data" : "id", 'render': function (data, type, full, meta){
		             return '<input type="checkbox" class="RowCheckBox" name="id" value="' + $('<div/>').text(data).html() + '">';
		             }  
				},
				{"data" : "customerName"},
				{"data" : "device"},
				{"data" : "designDrawingNo"},
				{"data" : "designUserName"},
				
				{"data" : "description"},
				{"data" : "dimension"},
				{"data" : "material"},
				{"data" : "thermal"},
				{"data" : "quantity"},
				{"data" : "spare"},
				{"data" : "postprocessing"},
				{"data" : "outsourcingPartnerName"},
				{"data" : "processFinishPlanDate"},
				{"data" : "coatingFinishPlanDate"},
				{"data" : "inspectPlanDate"}
			]
		});
	});
	
	</script>
		

	
	</layout:put>
</layout:extends>