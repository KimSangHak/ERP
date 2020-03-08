<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>입출고관리</li>
                        <li>가공품 인계</li>
                    </ul> 
                    <h2>가공품 인계</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
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
                            		<th>Order NO</th>
                                    <td><input type="text" id="orderNoBase" value="" class="w130"> - <input type="text" id="orderNoExtra" value="" class="w130"></td>
                                    <th>거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="partnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>                                    	
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                    	<input type="text" id="orderDate1" value="" class="w130 AutoDatePicker"> - <input type="text" id="orderDate2" value="" class="w130 AutoDatePicker">
                                    </td>
                                </tr>
                                <tr>
                                	<th>상품유형</th>
                                    <td colspan="5">
                                        <label><input type="checkbox" id="processInsourcing" value="Y" checked="checked" />사내가공품</label>
                                        <label><input type="checkbox" id="processOutsourcing" value="Y" checked="checked"  />외주 가공품</label>
                                        <label><input type="checkbox" id="postprocessFinished" value="Y" checked="checked" />후처리 완료</label>
                                        <label><input type="checkbox" id="checkFinished"  value="Y" checked="checked"/>검사 완료</label>
                                        <label><input type="checkbox" id="checkUnfinished" value="Y" checked="checked"/>검사 미완료</label>
                                        <label><input type="checkbox" id="checkNg" value="Y" />NG 가공품</label>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue" id="FindButton">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>가공품 인계</h3>
                        	
                        		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<div class="btnArea">
                         					<a href="#" class="btn_line_blue CarryTo2">검사 필요 변경</a>
                         					<a href="#" class="btn_line_blue CarryTo3">NG처리 재가공 등록</a>
                         					<a href="#" class="btn_line_blue CarryTo" data-dept="PQ">구매부로 인계</a>
                            				<a href="#" class="btn_line_blue CarryTo" data-dept="INSPECT">검사부로 인계</a>
                            				<a href="#" class="btn_line_blue CarryTo" data-dept="ASSEMBLY">조립부로 인계</a>
                            				<a href="#" class="btn_line_blue CarryTo" data-dept="MC">가공부로 인계</a>
                            				<a href="#" class="btn_line_blue CarryTo" data-dept="TS">영업부로 인계</a>
                            				<a href="#" class="btn_line_blue CarryTo" data-dept="T1">설계부로 인계</a>

                            				
                            			</div>
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
                                    	<th>도면번호</th>
                                    	<th>설계자</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>가공업체</th>
                                        <th>가공납기일</th>
                                        <th>검사여부</th>
                                        
                                        <th>검사일</th>
                                        <th>판정</th>
                                        <th>성적서</th>
                                        <th>현재 공정</th>
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
	
	<script type="text/javascript" src=/js/datatable_common.js"></script>
	<script type="text/javascript">
	
	var mainTable;
	$(document).ready(function(){
		
		//전체 선택
		$("#checkAll").click(function(e){
			
			var checked = $(this).is(":checked");
			if(console)
				console.log("checked = " + checked);
			
			$(".RowCheckBox").each( function(){
				$(this).prop("checked", checked);
			});
									
		});
		
		// 검사부/조립부 인계 버튼 클릭
		$(".CarryTo").click(function(e){
			e.preventDefault();
			
			// 선택된 행의 도면ID 추가
			var selected = [], noCheckingDesignDrawingNo = [];
			$(".RowCheckBox:checked").each( function(){
				selected.push($(this).val());
				
				if( $(this).attr("data-check") == "N" )
					noCheckingDesignDrawingNo.push( $(this).attr("data-designDrawingNo") );
			});
			
			if(selected.length == 0){
				alert("인계 대상을 선택해주세요");
				return;
			}
			
			var extra = "?id=" + selected.join(",");
			var dept = $(this).attr("data-dept");

			// 검사 대상 도면이 아닌 경우
			
			if(dept == "INSPECT" && noCheckingDesignDrawingNo.length > 0){
				alert("도면[" + noCheckingDesignDrawingNo.join() + "]은 검사가 필요없는 도면입니다" );
				return;
			}			
			
			PopWin('/ps/carry/popup/carry/' + dept + extra,'1000','600','no');			
		});
		
		
		
		$(".CarryTo2").click(function(e){
			e.preventDefault();
			
			// 선택된 행의 도면ID 추가
			var selected = [], noCheckingDesignDrawingNo = [];
			$(".RowCheckBox:checked").each( function(){
				selected.push($(this).val());
				
				if( $(this).attr("data-check") == "N" )
					noCheckingDesignDrawingNo.push( $(this).attr("data-designDrawingNo") );
			});
			
			if(selected.length == 0){
				alert("인계 대상을 선택해주세요");
				return;
			}
			
			var extra = "?id=" + selected.join(",");
			

			if(confirm("선택 된 도면을 검사가 필요한 항목으로 변경 하시겠습니까?")){
				location.href = '/ps/carry/checkEditAssembly/' + extra;
			}
		
		});
		
		//NG처리 재가공 등록
		$(".CarryTo3").click(function(e){
			e.preventDefault();
			
			// 선택된 행의 도면ID 추가
			var selected = [], noCheckingDesignDrawingNo = [];
			$(".RowCheckBox:checked").each( function(){
				selected.push($(this).val());
				
				if( $(this).attr("data-check") == "N" )
					noCheckingDesignDrawingNo.push( $(this).attr("data-designDrawingNo") );
			});
			
			if(selected.length == 0){
				alert("인계 대상을 선택해주세요");
				return;
			}
			
			var extra = "?id=" + selected.join(",");
		
			
			PopWin('/ps/carry/popup/carryNG/'+ extra,'1000','600','no');			
		});
		
		// 검색버튼
		$("#FindButton").click(function(e){
			e.preventDefault();
			
			mainTable.ajax.reload();
		});
		
		$("#checkAll").click(function(e){
			//e.preventDefault();
			
			var newCheckStatus = $(this).is(":checked");					
			$("input.rowCheck").attr("checked", newCheckStatus);
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
				url : "/ps/carry/data/assembly_check",
				type : "POST",
				data : function(d){
					d.orderNoBase = $("#orderNoBase").val(),
					d.orderNoExtra = $("#orderNoExtra").val(),
					d.partnerId = $("#partnerId").val(),
					d.orderDate1 = $("#orderDate1").val(),
					d.orderDate2 = $("#orderDate2").val(),
					d.processInsourcing = $("#processInsourcing").is(":checked");
					d.processOutsourcing = $("#processOutsourcing").is(":checked");
					d.postprocessFinished = $("#postprocessFinished").is(":checked");
					d.checkFinished = $("#checkFinished").is(":checked");
					d.checkUnfinished = $("#checkUnfinished").is(":checked");
					d.checkNg = $("#checkNg").is(":checked");
				}
			},
			'select': {
		         'style': 'multi'
		      },
			"columns" : [						
				{ "data" : "id", 'render': function (data, type, full, meta){
		             return '<input type="checkbox" class="RowCheckBox" name="id" data-designDrawingNo="' + full.designDrawingNo + '" data-check="' + full.checking + '" value="' + $('<div/>').text(data).html() + '">';
		             }  
				},
				{"data" : "designDrawingNo"},
				{"data" : "designUserName"},
				{"data" : "material"},
				
				{"data" : "thermal"},
				{"data" : "quantity"},
				{"data" : "spare"},
				{"data" : "postprocessing"},
				
				{"data" : "outsourcingPartnerName"},				
				{"data" : "deliveryPlanDate"},
				
				{"data" : "checking"},
				{"data" : "inspectPlanDate"},
				{"data" : "testResult"},
				{"data" : "inspectFileHash", "render" : function(data, type, full, meta){
					return '<a target="_blank" href="/link/' + data + '" class="btn_file">파일</a>';
				}},
				{"data" : "drawingCurrentStatus"}
			]
		});
	
	});
	
	
	
	</script>

	
	</layout:put>
</layout:extends>