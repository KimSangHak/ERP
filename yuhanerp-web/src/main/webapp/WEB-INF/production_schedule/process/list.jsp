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
                        <li>가공 내역 관리</li>
                    </ul> 
                    <h2>가공 내역 관리</h2>
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
                                <col style="width:5%;" />
                                <col style="width:12%;" />
                                <col style="width:6%;" />
                                <col style="width:12%;" />
                                </colgroup>
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w70"> - 
                                        <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra}" class="w130"> - 
                                        <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo}" class="w70">
                                    </td>
                                    <th>Device</th>
                                    <td>
                                    	<input type="text" id="device" name="device" value="${device }" class="w150">
                                    </td>
                                    <th>고객사</th>
                                    <td>                 
                                         <c:import url="/internal/util/select/customer">
                                        	<c:param value="selectedCustomer" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w160" name="cssClass" />
                                        	<c:param value="${selectedCustomer }" name="defaultValue" />
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
                            <div class="btnArea"><a href="#" id="DateBatchInput" class="btn_line_blue">날짜 일괄 등록</a> 
                            					<a href="#" id="BatchInput" class="btn_line_blue">전체 일괄 등록</a> |
                            					<a href="#" id="RegButton" class="btn_line_blue ActionButton" data-action="REG">가공 등록</a> 
                            					<a href="#" id="StopButton" class="btn_line_blue ActionButton" data-action="CANCEL">가공 취소</a> 
                            					</div>
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
                                        <th>도면<br/>번호</th>
                                        <th>설계자</th>
                                        
                                        <th>품명</th>
                                        <th>Dim'</th>
                                        <th>mat`I</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        
                                        <th>Sp</th>
                                        <th>후처리</th>
                                        <th>출도일</th>
                                        <th>분류</th>
                                        <th>검사</th>
                                        
                                        <th class="workPositionColumn">가공</th>
                                        <th class="outsourcingPartnerListColumn">발주<br/>업체</th>
                                        <th>가공<br/> 완료일</th>
                                        <th>후처리<br/> 완료일</th>                                        
                                        <th>검사<br/> 완료일</th>
                                        <!-- 
                                        
                                        <th class="InputColumn">입력</th>
                                        <th>확인</th>
                                        -->
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
                
    <div id="processColumnHtml" style="display : none;" >
		<c:import url="/internal/util/select/process">
        	<c:param value="workPosition" name="controlName"/>
            <c:param value="${item.workPosition }" name="defaultValue" />
        </c:import>    
    </div>    
    <div id="outsourcingPartnerListHtml" style="display : none;">
		<c:import url="/internal/util/select/buy_partner">
        	<c:param value="outsourcingPartnerId" name="controlName"/>
            <c:param value="${item.outsourcingPartnerId }" name="defaultValue" />
        </c:import>    
    </div>
	
	</layout:put>
	
	<layout:put block="BodyScriptBlock2">
		
		<script type="text/javascript">
		
		var mainTable;
		var selectedLines;
		
		function afterDateBatchInput(processDate, postprocessingDate, inspectDate){
			
			$("input.rowCheck:checked").each(function(){
				var row = $(this).parent().parent();
				if(processDate != '')
					row.find("input[name='finishDate']").val(processDate);
				if(postprocessingDate != '')
					row.find("input[name='coatingDate']").val(postprocessingDate);
				if(inspectDate != '')
					row.find("input[name='inspectDate']").val(inspectDate);
			});
			
		}
		
		function afterBatchInput(workPosition, partnerName, partnerId, finishDate, postprocessingDate, inspectDate){
			
			$("input.rowCheck:checked").each(function(){
				
				var row = $(this).parent().parent();
				if(workPosition != null) row.find("#workPosition").val(workPosition);
				if(partnerId != null) row.find("#outsourcingPartnerId").val(partnerId);
				if(postprocessingDate != null) row.find("input[name='coatingDate']").val(postprocessingDate);
				if(inspectDate != null) row.find("input[name='inspectDate']").val(inspectDate);
				if(finishDate != null) row.find("input[name='finishDate']").val(finishDate);
				
			});			
		}
		
		// 서버로 가공 등록/취소 전송
		function submitLines(action, reason ){
			$.ajax({
				url : "/ps/process/update/lists",
				data : 
					JSON.stringify({
					"data" : selectedLines,
					"action" : action,
					"reason" : reason })					
				,
				method : "POST",
				dataType : "JSON",
				contentType : "application/json",
				traditional : true,
				success : function(result){
					if(result.result == "OK"){
						alert("처리가 완료되었습니다");
						$("#FindButton").trigger("click");
					}
					else
						alert(result.reason);
				},
				error : function(){
					alert("서버 오류가 발생하였습니다");
				}
			});
		}
		
		function afterInputCancelReason(wnd, concated_key, text){
			submitLines("CANCEL", text);
		}
		
		
		$(document).ready(function(){

			$("input[name='searchPartnerTarget'][value='${searchPartnerTarget}']").prop("checked", true);
			
			// 검색 버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				//$("form").submit();
				mainTable.ajax.reload();
			});
			
			// 전체 체크
			$("#checkAll").click(function(e){
				//e.preventDefault();
				
				var newCheckStatus = $(this).is(":checked");					
				$("input.rowCheck").attr("checked", newCheckStatus);
			});
		
			// [날짜 일괄 등록] 버튼 클릭
			$("#DateBatchInput").click(function(e){
				e.preventDefault();
				
				var checkedCount = $("input.rowCheck:checked").length;
				if(checkedCount == 0){
					alert("일괄 등록할 항목을 선택해 주세요");
					return;
				}
				
				PopWin('/ps/process/popup/date_batch_input?callback=afterDateBatchInput','600','340','no');
			});
		
			// [전체 일괄 등록] 버튼 클릭
			$("#BatchInput").click(function(e){
				e.preventDefault();
				
				var checkedCount = $("input.rowCheck:checked").length;
				if(checkedCount == 0){
					alert("일괄 등록할 항목을 선택해 주세요");
					return;
				}
				
				PopWin('/ps/process/popup/batch_input?callback=afterBatchInput','600','340','no');	
			});


			// [가공 등록], [가공 취소] 버튼 클릭 처리
			$(".ActionButton").click(function(e){
				e.preventDefault();
				
				var rows = [];
				$("input.rowCheck:checked").each(function(){
					
					var line = { };
					$(this).parent().parent().find("input,select").each(function(){
						var key = $(this).attr("name") ;
						var val = $(this).val();
						line[ key ] = val;
					});					
					
					rows.push( line );
				});
				
				selectedLines = rows; // JSON.stringify(rows);
				console.log("data == > " + JSON.stringify(selectedLines) );
				
				if(rows.length == 0){
					alert("등록할 도면을 선택해 주세요");
					return;
				}
				
				var action = $(this).attr("data-action"	);
				
				if(action == "CANCEL")
					PopWin('/ps/process/popup/cancel_reason?callback=afterInputCancelReason&key=0' ,'600','410','no');
				else {
					if(confirm(rows.length + "건에 대해 가공 등록하시겠습니까?"))
						submitLines("REG", null);
				}
			});
			
			
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
					url : "/ps/process/data/list",
					data : function(d){
						d.device = $("#device").val();

						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
						
						d.selectedCustomer = $("#selectedCustomer").val();
					}
				},
		
				"createdRow" : function(row,data,dataIndex){
					$(row).attr("data-id", data.id);
				},
				"columns" : [						
					{ "data" : "id", 'render': function (data, type, full, meta){
						var line3 = '<input type="checkbox" class="rowCheck" name="id" value="' + data + '">'; 
			             return line3;
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
					{"data" : "designDate"},

					{"data" : "classification"},					
					{"data" : "checking"},					
					// 가공
					{"data" : "workPosition", "render" : function(data){
						
						$("#processColumnHtml select option:selected").removeAttr("selected");
						$("#processColumnHtml select option[value='" + data + "']").attr("selected", "selected");
						var html = $("#processColumnHtml").html();
						return html;
					} },
					// 발주업체
					{"data" : "outsourcingPartnerId", "className" : "outsourcingPartnerListColumn" , "render" : function(data, type, full){
						$("#outsourcingPartnerListHtml select option:selected").removeAttr("selected");
						$("#outsourcingPartnerListHtml select option[value='" + data + "']").attr("selected", "selected");
						
						var html = $("#outsourcingPartnerListHtml").html();
						return html;
					} },
					// 가공 완료일
					{"data" : "processFinishPlanDate", "render" : function(data){
						return '<input type="text" class="lazyAutoDatePicker" style="width:70px" name="finishDate" value="' + $('<div/>').text(data).html() + '">';
					}},
					// 후처리 완료일
					{"data" : "coatingFinishPlanDate", "render" : function(data){
						return '<input type="text" class="lazyAutoDatePicker" style="width:70px" name="coatingDate" value="' + $('<div/>').text(data).html() + '">';
					}},
					// 검사완료일
					{"data" : "inspectPlanDate", "render" : function(data){
						return '<input type="text" class="lazyAutoDatePicker" style="width:70px" name="inspectDate" value="' + $('<div/>').text(data).html() + '">';
					}}

				],
				"columnDefs" : [

					{
						targets : ["InputColumn"],
						render : function(){
							var html = $("#InputComboHtml").html();
							return html;
						}
					}
				]
			});			
		});
		</script>
	</layout:put>
</layout:extends>