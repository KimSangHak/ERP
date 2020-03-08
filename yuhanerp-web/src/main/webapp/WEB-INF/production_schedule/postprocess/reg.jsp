<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%
	// 생산일정관리 - 후처리관리 - 후처리 등록
%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
		<style>
			.dataTables_wrapper .ui-toolbar {
				padding : 0px;
			}
		</style>
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>후처리 관리</li>
                        <li>후처리 등록</li>
                    </ul> 
                    <h2>후처리 등록</h2>
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
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue" id="FindButton">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>등록 내역</h3>
                        		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<div class="btnArea"><a href="#" id="RegSkip" class="btn_line_blue">후처리 스킵</a> <a href="#" id="RegPP" class="btn_line_blue">후처리 발주 등록</a></div>
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
                                        <th>가공납기일</th>
                                        <th>검사여부</th>
                                        <th>검사일</th>
                                        <th>판정</th>
                                        <th>성적서</th>
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

			// 후처리 스킵 등록 
			$("#RegSkip").click(function(e){
				e.preventDefault();
				
				var url = "/ps/postprocess/popup/skip?id=";
				
				var checked = [];
				$(".RowCheckBox:checked").each(function(){
					checked.push( $(this).val() );
				});
				
				if(checked.length == 0){
					alert("후처리 스킵 등록할 대상을 선택해주세요");
					return;
				}
				
				PopWin(url + checked.join() ,'1600','600','no');
			});
			
			// 후처리 발주 등록
			$("#RegPP").click(function(e){
				e.preventDefault();
				
				var url = "/ps/postprocess/popup/postprocess_order?id=";
				
				var checked = [];
				$(".RowCheckBox:checked").each(function(){
					checked.push( $(this).val() );
				});

				if(checked.length == 0){
					alert("발주할 대상을 선택해주세요");
					return;
				}				
				
				PopWin(url + checked.join() ,'1600','600','no');
				
			});
			
			// 입고 내역 불러오기
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
					url : "/ps/postprocess/data/reg",
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
					{ "data" : "id", 'render': function (data, type, full, meta){
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
					{"data" : "outsourcingPartnerName"},
					
					{"data" : "processFinishDate"},
					{"data" : "checking"},
					{"data" : "inspectDate"},
					{"data" : "testResult"},
					{"data" : "id", "render" : function (data, type, full, meta){						
						return '<a href="#" class="btn_file">파일</a>';
					} }
				]
			});
			
		});
	
	
	</script>	
	
	

	
	</layout:put>
</layout:extends>