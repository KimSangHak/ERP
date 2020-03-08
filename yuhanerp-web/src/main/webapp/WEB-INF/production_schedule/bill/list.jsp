<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>거래명세서 관리</li>
                        <li>거래명세표 조회</li>
                    </ul> 
                    <h2>거래명세표 조회</h2>
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
                                    <th>거래처</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>                                    	
                                    </td>
                            		<th>Order NO</th>
                                    <td><input type="text" id="orderNoBase" name="orderNoBase" id="orderNoBase" value="" class="w130"> - 
                                    <input type="text" id="orderNoExtra" name="orderNoExtra" id="orderNoExtra" value=""  class="w130">
                                    </td>
                                  	<th>발주일</th>
                                    <td>
                                    	<input type="text" id="orderDateFrom" name="orderDateFrom" value="" class="w130 AutoDatePicker"> - 
                                    	<input type="text" id="orderDateTo" name="orderDateTo" value="" class="w130 AutoDatePicker">
                                    </td>  
                                </tr>
                                <tr>
                                	<th>입고일</th>
                                    <td>
                                    	<input type="text" id="CarryInDateFrom" value="" class="w130 AutoDatePicker"> - <input type="text" id="CarryInDateTo" value="" class="w130 AutoDatePicker">
                                    </td>
                                    <th>유형</th>
                                    <td>
                                        <label><input type="checkbox" id="includeProcessing" checked />가공발주</label>
                                        <label><input type="checkbox" id="includePostprocessing" checked />후처리발주</label>
                                    </td>
                                	<th>&nbsp;</th>
                                    <td>
                                        &nbsp;
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" id="FindButton" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>조회 내역</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                    	<th>거래처</th>
                                    	<th>거래명세 번호</th>
                                        <th>거래명세일</th>
                                        <th>발주NO</th>
                                        <th>발주일</th>
                                        <th>대표 도면번호</th>
                                        <th>도면수량</th>
                                        <th>등록일</th>
                                        <th>공급가액 합</th>
                                        <th>세액 합</th>
                                        <th>합계</th>
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
	

	<script type="text/javascript" src="/js/datatable_common.js"></script>
	<script type="text/javascript">
	
		var mainTable;
	
		$(document).ready(function(){

			$("#FindButton").click(function(e){
				e.preventDefault();
				
				mainTable.ajax.reload();
			});
			
			// 발주NO 클릭시 팝업
			$(document).on("click", ".RegPopup", function(e){
				e.preventDefault();

				var id = $(this).attr("data-id");				
				PopWin('/ps/bill/popup/view?statementId=' + encodeURI(id) ,'1000','600','no');				
			});
			
			// 거래명세표 불러오기
			mainTable = $("#TBL").DataTable({
				"paging" : true,
				"lengthChange" : false,
				"lengthMenu" : yerpPaingSize,
				"ordering" : false,
				"info" : false,
				"searching" : false,
				"language" : dataTableLanguageCommon,
				"serverSide" : true,
				"ajax" : {
					url : "/ps/bill/data/list"			,
					data : function(d){
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
						d.includeProcessing = $("#includeProcessing").is(":checked");
						d.includePostprocessing = $("#includePostprocessing").is(":checked");
						d.orderDateFrom = $("#orderDateFrom").val();
						d.orderDateTo = $("#orderDateTo").val();			
						d.carryInDateFrom = $("#CarryInDateFrom").val();
						d.carryInDateTo = $("#CarryInDateTo").val();
					}					
				},
				"columns" : [
					{"data" : "partnerName"},
					{"data" : "statementNo", render : function(data, type, full, meta){
			             return '<a href="#" class="RegPopup" data-id="' + full.id + '">' + data + "</a>";
		             } 
					},
					{"data" : "issueDate"},
					{"data" : "outsourcingOrderNo"},					
					{"data" : "outsourcingRequestDate"},					
					{"data" : "representiveDrawingNo"},
					{"data" : "drawingCount"},
					{"data" : "regDatetime"},
					{"data" : "net",render : $.fn.dataTable.render.number(",", ".") },					
					{"data" : "vat",render : $.fn.dataTable.render.number(",", ".") },					
					{"data" : "amount",render : $.fn.dataTable.render.number(",", ".") }			
				]
			});
			
		});
	
	
	</script>	
	
	
	</layout:put>
</layout:extends>