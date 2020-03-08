<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공 관리</li>
                        <li>가공품 상세 이력</li>
                    </ul> 
                    <h2>가공품 상세 이력</h2>
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
                                    
                            		<th>도면 번호</th>
                                    <td><input type="text" id="orderNoBase" name="orderNoBase" value="" class="w130"> - 
                                    <input type="text" id="orderNoExtra" name="orderNoExtra" value=""  class="w130"> -
                                    <input type="text" id="drawingNo" name="drawingNo" value=""  class="w130">
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
                        	<h3>가공품 내역</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                		<th>도면ID</th>
                                    	<th>도면번호</th>
                                    	<th>품명</th>
                                        <th>수량</th>
                                        <th>spare</th>
                                      
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
			
			// 도면NO 클릭시 팝업
			$(document).on("click", ".RegPopup", function(e){
				e.preventDefault();

				var id = $(this).attr("data-id");
			
				PopWin('/ps/process/popup/historyPS?id=' + encodeURI(id),'1000','600','no');				
			});
			
			// List 불러오기
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
					url : "/ps/process/data/designdrawing"			,
					data : function(d){
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
					
					}					
				},
				"columns" : [
					{"data" : "id" },
					{"data" : "fdrawingNo", render : function(data, type, full, meta){
			             return '<a href="#" class="RegPopup" data-id="' + full.id + '">'+ data +"</a>";
		             } 
					},
					{"data" : "description"},
					{"data" : "quantity"},					
					{"data" : "spare"}
				]
			});
			
		});
	
	
	</script>	
	
	</layout:put>
</layout:extends>