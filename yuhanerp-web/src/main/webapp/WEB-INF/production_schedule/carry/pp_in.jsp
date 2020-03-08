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
                        <li>후처리 완료 입고</li>
                    </ul> 
                    <h2>후처리 완료 입고</h2>
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
                                    <th>후처리 거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/postprocess_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
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
                                        <label><input type="checkbox" id="processInsourcing" name="processInsourcing" value="Y" checked />사내가공품</label>
                                        <label><input type="checkbox" id="processOutsourcing" name="processOutsourcing" value="Y" checked />외주 가공품</label>
                                        <!--	PPT 와 디자인 불일치하여 제거 
                                        <label><input type="checkbox" id="postprocessFinished" name="postprocessFinished" value="Y" checked/>후처리 완료</label>
                                        <label><input type="checkbox" id="inspectFinished" name="inspectFinished" value="Y" checked/>검사 완료</label>
                                        <label><input type="checkbox" id="inspectUnfinished" name="inspectUnfinished" value="Y" checked/>검사 미완료</label>
                                        -->
                                    </td>
                                </tr>
                            </table>
                            </form>
                        </div>
                        <div class="searchBtn"><a href="#" id="SearchButton" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>후처리 완료 입고 리스트</h3>
                        		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<div class="btnArea"><a href="#" id="PPFinish" class="btn_line_blue">후처리 완료 입고</a></div>
                         				<div class="btnArea"><a href="javascript:openCarryInPopup('ppinprocessre');" class="btn_line_blue">후처리 완료 후 재가공</a></div>
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
                                    	<th>품명</th>
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
	
		<style type="text/css">
			.dataTables_wrapper .ui-toolbar {
				padding : 0px ;
			}
		</style>
		
		<script type="text/javascript">
			
			var mainTable;
			
			
			// 선택된 URL 에 파라미터 붙여서 열기				
			function openCarryInPopup(url){
				
				// 선택된 행의 도면ID 추가
				var selected = [];
				$(".RowCheckBox:checked").each( function(){
					selected.push($(this).val());
				});
				
				if(selected.length == 0){
					alert("입고 처리할 대상을 선택해주세요");
					return;
				}
				
				var extra = "?id=" + selected.join(",");
				
				PopWin('/ps/carry/popup/' + url + extra,'1000','600','no');
			}
		
			$(document).ready(function(){
				
				// 검색
				$("#SearchButton").click(function(e){
					e.preventDefault();
					
					mainTable.ajax.reload();
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
				
				// 후처리 완료 입고 버튼 클릭
				$("#PPFinish").click(function(e){
					
					e.preventDefault();
					
					// 선택된 행의 도면ID 추가
					var selected = [];
					$(".RowCheckBox:checked").each( function(){
						selected.push($(this).val());
					});
					
					if(selected.length == 0){
						alert("입고 처리할 대상을 선택해주세요");
						return;
					}
					
					if(!confirm("선택한 항목을 후처리 완료 입고 처리하겠습니까?"))
						return;
					
					$.ajax({
						url : "/ps/carry/perform/finish_pp",
						data : {
							id : selected.join(",")
						},
						dataType : "json",
						success : function(obj){
							if(obj.result == "OK"){
								alert("입고 처리가 완료되었습니다");
								location.reload();
							}
							else
								alert(obj.reason);
						},error : function(){
							alert("서버와 통신 오류가 발생하였습니다");
						}
					});
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
						url : "/ps/carry/data/pp_in",
						"type" : "POST",
						data : function(d){
							d.orderNoBase = $("#orderNoBase").val();
							d.orderNoExtra = $("#orderNoExtra").val();
							d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
							d.processInsourcing = $("#processInsourcing").val();
							d.processOutsourcing = $("#processOutsourcing").val();
							/*	// PPT 와 디자인이 불일치 하여 주석처리
							d.postprocessFinished = $("#postprocessFinished").val();
							d.inspectFinished = $("#inspectFinished").val();
							d.inspectUnfinished = $("#inspectUnfinished").val();
							*/
							d.orderDateFrom = $("#orderDateFrom").val();
							d.orderDateTo = $("#orderDateTo").val();						}						
					},
					'select': {
				         'style': 'multi'
				      },
					"columns" : [						
						{ "data" : "id", 'render': function (data, type, full, meta){
				             return '<input type="checkbox" class="RowCheckBox" name="id" value="' + $('<div/>').text(data).html() + '">';
				             }  
						},
						{"data" : "designDrawingNo"},
						{"data" : "description"},
						{"data" : "designUserName"},
						{"data" : "material"},
						
						{"data" : "thermal"},
						{"data" : "quantity"},
						{"data" : "spare"},
						{"data" : "postprocessing"},
						{"data" : "outsourcingPartnerName"},
						
						{"data" : "outsourcingDeliveryDate"},
						{"data" : "checking"},
						{"data" : "inspectDate"},
						{"data" : "testResult"},
						{"data" : "inspectFileHash", "render" : function(data){
							var url = (data != null && data != "") ? "/link/" + data : "#";
							return '<a href="' + url + '" class="btn_file">파일</a>';
						}},
						{"data" : "currentStage"}
					]
				});
			
			});
		</script>
	
	</layout:put>
</layout:extends>