<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<layout:extends name="/WEB-INF/layout/PidbackMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>피드백</li>
                        <li>피드백 LIST</li>
                        
                         
                    </ul>
                  
                    <h2>피드백 LIST</h2>
                    
                   
                  
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    <form method="get">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:8%;;" />
                                <col style="width:10%;" />
                                 <col style="width:8%;;" />
                                <col style="width:10%;" />
                                 <col style="width:8%;;" />
                                <col style="width:17%;" />
                                 <col style="width:10%;;" />
                                <col style="width:29%;" />
                                </colgroup>
                                <tr>
                                
                                	 <th>검색</br>유형</th>
                                    <td>
                                       		<select name="kind" id="kind" style="width:200px;">
                                       			<option value="A">전체</option>
                                				<option value="D">도면</option>
                                				<option value="P" <c:if test="${kind eq 'P'}">selected</c:if>>구매품</option>
                         					</select>
                                    </td>
                                     <th>작성자</th>
                                    <td>
                                        <input type="text" name="usrName" id="usrName" value="${usrName}" class="w280">
                                    </td>
                                   <th>Title</th>
                                    <td>
                                        <input type="text" name="title" id="title" value="${title}" class="w280">
                                    </td>
                                    
                                  </tr>
                                    
                                    
                                   <tr> 
                                    <th>도면번호</th>
                                    <td colspan="4">
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w140" placeholder="OrderNo"> - 
                                        <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra}" class="w140" placeholder="AS 번호"> - 
                                        <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo}" class="w140" placeholder="도면 번호">
                                    </td>
                                    </tr>
                                    
                                    
                                    
                                    
                                   <tr>
                                    <th>구매번호</th>
                                    <td><input type="text" id="seq" name="seq" value="" class="w140"></td>
                                  
                              
                                    
                                     <th>피드백</br>작성일</th>
                                    <td colspan="3">
                                        <input type="date" name="DateBegin" id="DateBegin" value="${DateBegin}" class="w140"> - <input type="date" name="DateEnd" id="DateEnd" value="${DateEnd}" class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                       
                        <div class="searchBtn">
                       	<a href="#" class="btn_blue" id="FindButton">검색</a> &nbsp;&nbsp;
                       	<a href="javascript:PopWin('${path}/pidback/pidbackIns/popup','1000','1500','no');" class="btn_blue" id="InsButton">피드백 작성</a>
                        </div>
                           
                               
                           
                        </form>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">              	
                        		
                             
                                		<caption> </caption>
                                		<colgroup span="2">
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:25%;" />
                                		<col style="width:10%;" />
                                		<col style="width:20%;" />
                                		<col style="width:10%;" />
                                		<col style="width:5%;" />
                                		
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                       
                                    		<th>도면/구매 번호</th>
                                    		<th>OrderNo</th>
                                        	<th>Title</th>
                                        	<th>작성자</th>
                                        	<th>작성일자</th>
                                        	<th>댓글수</th>
                                        	<th>조회수</th>
                                        	
                                  
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
		
		var mainTable;
		
		$(document).ready(function(){

			$("#FindButton").click(function(e){
				e.preventDefault();
				
				mainTable.ajax.reload();
			});
		
			$(document).on("click", ".RegPopup", function(e){
				e.preventDefault();

				var id = $(this).attr("data-id");
				
				
				location.href = '/pidback/detail/view?id=' + encodeURI(id);
			
				//PopWin('/pidback/detail/view?id=' + encodeURI(id),'1000','600','no');				
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
					url : "/pidback/data/main",
					data : function(d){
						d.kind = $("#kind option:selected").val();
						d.usrName = $("#usrName").val();
						d.title = $("#title").val();
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
						d.seq = $("#seq").val();
						d.DateBegin = $("#DateBegin").val();
						d.DateEnd = $("#DateEnd").val();

					}					
				},
				"columns" : [
					
					
					{"data" : "kind", render : function(data, type, full, meta){
						if(full.kind == 'D'){
							data = full.fdrawingNo;
						}else if(full.kind == 'P'){
							data = full.jobPurchaseId;
						}
						
			             return data;
		             } 
					},
					
					
					{"data" : "jobOrderNo"},
					{"data" : "title", render : function(data, type, full, meta){
			             return '<a href="#" class="RegPopup" data-id="' + full.id + '">'+ data +"</a>";
		             } 
					},
					{"data" : "usrName"},
					{"data" : "whenCreated"},
					{"data" : "comentCount"},
					{"data" : "view"}
				]
			});
			
		});
		
		
		</script>
	
	</layout:put>
</layout:extends>