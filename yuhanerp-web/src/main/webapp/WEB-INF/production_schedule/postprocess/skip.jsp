<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>후처리 관리</li>
                        <li>후처리 스킵리스트</li>
                    </ul> 
                    <h2>후처리 스킵리스트</h2>
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
                                    <td><input type="text" id="orderNoBase" name="orderNoBase" id="orderNoBase" value="" class="w130"> - 
                                    <input type="text" id="orderNoExtra" name="orderNoExtra" id="orderNoExtra" value=""  class="w130">
                                    </td>
                                    <th>거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
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
                                    </td>                                </tr>
                                <tr>
                                	<th>상품유형</th>
                                    <td colspan="5">
                                        <label><input type="checkbox" id="processInsourcing" name="processInsourcing" value="Y" checked/>사내가공품</label>
                                        <label><input type="checkbox" id="processOutsourcing" name="processOutsourcing" value="Y" checked/>외주 가공품</label>
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
                        	<h3>스킵 내역</h3>
                        		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<div class="btnArea"><a href="#" class="btn_line_blue" id="MakePP">선택 리스트 후처리 발주 전환</a>
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
                                    	<th>발주번호</th>
                                        <th>설계자</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>가공업체</th>
                                        <th>후처리등록</th>
                                        <th>검사여부</th>
                                        <th>검사일</th>
                                        <th>판정</th>
                                        <th>성적서</th>
                                        <th>현재 공정</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<!-- 
                                	<tr>
                                    	<td><input type="checkbox"></td>
                                    	<td>50001-3001</td>
                                        <td>D001-161030-001</td>
                                        <td>남기홍</td>
                                        <td>A6061</td>
                                        <td>&nbsp;</td>
                                        <td>10</td>
                                        <td>2</td>
                                        <td>Nickel</td>
                                        <td>H테크</td>
                                        <td>17.09.11</td>
                                        <td>Y</td>
                                        <td>17.09.12</td>
                                        <td>OK</td>
                                        <td><a href="#" class="btn_file">파일</a></td>
                                        <td>검사대기</td>
                                    </tr>
                                    -->
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
	
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	<script type="text/javascript">
	
		var mainTable ;
	
		$(document).ready(function(){

			$("#FindButton").click(function(e){
				e.preventDefault();
				
				mainTable.ajax.reload();
			});
						
			$("#MakePP").click(function(e){
				e.preventDefault();
				
				var checked = [];
				$(".RowCheckBox:checked").each(function(){
					checked.push( $(this).val() );
				});
				
				if(checked.length == 0){
					alert("전환할 대상을 선택해 주세요");
					return;
				}
				
				var url = "/ps/postprocess/popup/postprocess_order?pageFrom=replace&id=";
				
				PopWin(url + checked.join() ,'1000','600','no');
				
			});
			
			// 입고 내역 불러오기
			mainTable = $("#TBL").DataTable({
				"paging" : false,
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
					url : "/ps/postprocess/data/skip",
					data : function(d){
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
						d.processInsourcing = $("#processInsourcing").is(":checked");
						d.processOutsourcing = $("#processOutsourcing").is(":checked");
						d.orderDateFrom = $("#orderDateFrom").val();
						d.orderDateTo = $("#orderDateTo").val();								
					}
				},
				"columns" : [						
					{ "data" : "drawingId", 'render': function (data, type, full, meta){
			             return '<input type="checkbox" class="RowCheckBox" name="id" value="' + $('<div/>').text(data).html() + '">';
			             }  
					},
					{"data" : "designDrawingNo"},
					{"data" : "outsourcingRequestId"},
					{"data" : "designUserName"},
					{"data" : "material"},
					
					{"data" : "thermal"},
					{"data" : "quantity"},
					{"data" : "spare"},
					{"data" : "postprocessing"},
					{"data" : "outsourcingPartnerName"},	// 가공 업체					
					{"data" : "outsourcingRequestDate"},	// TODO :: 후처리 등록(일)
					{"data" : "checking"},				// TODO :: 검사여부
					{"data" : "inspectDate"},			// TODO :: 검사일
					{"data" : "resultOk"},			// TODO :: 판정
					{"data" : "inspectFileHash", "render" : function (data, type, full, meta){		// TODO :: 성적서						
						return '<a href="#" class="btn_file">파일</a>';
					}},
					{"data" : "currentStage"}					// TODO :: 현재 공정 
				]
			});
			
		});
	
	
	</script>	
	
		

	
	</layout:put>
</layout:extends>