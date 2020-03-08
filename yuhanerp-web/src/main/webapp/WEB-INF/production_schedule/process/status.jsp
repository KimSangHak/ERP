<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script src="js/plugins/dataTables/datatables.min.js"></script>
    <script src="js/plugins/dataTables/datatables.select.min.js"></script>
    <link href="css/plugins/dataTables/datatables.min.css" rel="stylesheet">
    <link href="css/plugins/dataTables/select.datatables.min.css" rel="stylesheet">

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>가공 관리</li>
                        <li>가공 및 검사 현황</li>
                    </ul> 
                    <h2>가공 및 검사 현황</h2>
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
                                <col style="width:15%;" />
                                <col style="width:7%;" />
                                <col style="width:20%;" />
                                </colgroup>
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="" class="w70"> - 
                                        <input type="text" id="orderNoExtra" name="orderNoExtra" value="" class="w130"> - 
                                        <input type="text" id="drawingNo" name="drawingNo" value="" class="w70">
                                    </td>
                                    <th>발주업체</th>
                                    <td>
                                        <c:import url="/internal/util/select/buy_partner">
                                        	<c:param value="outsourcingPartnerId" name="controlName"/>
                                        	<c:param value="true" name="showUnspecifiedItem"/>
                                        	<c:param value="w150" name="cssClass" />
                                        	<c:param value="" name="defaultValue" />
                                        </c:import>                                        
                                    </td>
                                    
                                   
                                    <th>발주일</th>
                                    <td colspan="6"><input type="text" readonly="readonly" id="orderDateFrom" name="orderDateFrom" value="" class="w100 AutoDatePicker"> - 
                                    		<input type="text" id="orderDateTo" name="orderDateTo" readonly="readonly" value="" class="w100 AutoDatePicker">
                                    </td>
                                    
                                      <th>가공분류</th>
                                    <td>
                                    
                                    		<select name="inProcessYN" id="inProcessYN" style="width:100px;">
                                				<option value="N" <c:if test="${inProcessYN eq 'N' || inProcessYN eq null}">selected</c:if>>전체보기</option>
                                				<option value="Y" <c:if test="${inProcessYN eq 'Y'}">selected</c:if>>사내가공</option>
                                				<option value="O" <c:if test="${inProcessYN eq 'O'}">selected</c:if>>사외가공</option>
                                				<option value="R" <c:if test="${inProcessYN eq 'R'}">selected</c:if>>사내가공(대기)</option>
                         					</select>
                                                                       
                                    </td>
                                </tr>
                            </table>
                        </div>
                        </form>
                        <div class="searchBtn">
                        	
                        	<a href="#" id="FindButton" class="btn_blue">검색</a>
                        </div>
                    </div>
                    <!-- //테이블 타이틀 -->

                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>가공 및 검사 현황 List</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        <form id="form1" name="form1" enctype="multipart/form-data" method="post">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                		<th>seq</th>
                                    	<th>도면번호</th>
                                        <th>발주번호</th>
                                    	<th>설계자</th>
                                        <th>mat`I</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>후처리</th>
                                        <th>가공업체</th>
                                        <th>가공<br/>납기일</th>
                                        <th>가공상태</th>
                                        <th>검사여부</th>
                                        <th>검사일</th>
                                        <th>판정</th>
                                        <th>성적서</th>
                                        <th>현재 공정</th>
                                        <th>이전 복원</th>
                                    </tr>
                                </thead>
                                
                                <tbody>

                                </tbody>
                            </table>
                            </form>
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

		
		$(document).ready(function(){

			// 검색 버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				mainTable.ajax.reload();
			});
			
			//가공 내역 관리 이동 버튼
			$(document).on('click', 'button[name="updatePre"]', function() {
				
				var id = mainTable.row( $(this).parents('tr:first') ).data().id;

				 if(confirm("가공 내역 관리로 이동하시겠습니까?(이전단계)")){
					 
					 $.ajax({
							async : false,
							url : "/ps/process/data/statusEdit",
							data : {
								id : id
							
							},
							dataType : "json",
							type : "POST",
							success : function(obj){
								if(obj.result == "OK"){
									alert("가공내역관리 로 이동되었습니다.");
									location.reload();
									window.close();
								}else{
									alert(obj.reason);	
								}
							},error : function(){
								alert("사내 작업중/외주 건은 되돌릴 수 없습니다.");
							}
						});		              	
			        }
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
				"select" : {
					style : 'multi' 
				},
				
				"ajax" : {
					"url" : "/ps/process/data/status",
					"type" : "POST",
					"data" : function(d){
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
						
						d.outsourcingPartnerId = $("#outsourcingPartnerId").val();
						
						d.orderDateFrom = $("#orderDateFrom").val();
						d.orderDateTo = $("#orderDateTo").val();
						d.inProcessYN = $("#inProcessYN option:selected").val();
						
						}
				},
				"columns" : [		
					
					{"data" : "id"},
					{"data" : "designDrawingNo"},
					{"data" : "outsourcingOrderNo"},
					{"data" : "designUserName"},
					{"data" : "material"},
					{"data" : "thermal"},
					{"data" : "quantity"},
					{"data" : "spare"},
					{"data" : "postprocessing"},
					
					{"data" : "outsourcingPartnerName"},					
					{"data" : "deliveryPlanDate"},	
					{"data" : "processStatus"},
					
					{"data" : "checking"},
					{"data" : "inspectDate"},					
					{"data" : "testResult"},
					{"data" : "inspectFileHash"},
					{"data" : "currentStage"},
					{"data": "" }
				],
				
				  "columnDefs" :  [
                   		{"targets"    : [ 17 ],
                   		 "orderable"    : false,
                   		 "searchable": false,
                   		 "className" : "center",
                   		 "render"    : function ( data, type, row ) {
                                   			var html = '<button type="button" name="updatePre" class="updatePre">가공내역이동</button>';
                                   			
                                   			return html;
                                   		}
                   		}
                  ]

			});
			
			 $('#TBL tbody').on( 'click', 'tr', function () {
				 
				 
				
			        $(this).toggleClass('selected');
			        
			        
			    } );
		});
		
		</script>
		
	</layout:put>
</layout:extends>