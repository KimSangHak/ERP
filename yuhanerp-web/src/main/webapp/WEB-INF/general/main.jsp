<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<layout:extends name="/WEB-INF/layout/GeneralMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>직원관리</li>
                        <li>임직원 LIST</li>
                        
                         
                    </ul>
                  
                    <h2>임직원 LIST</h2>
                    
                   
                  
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
                                       		<select name="isOut" id="isOut" style="width:200px;">
                                       			<option value="N" <c:if test="${isOut eq 'N'}">selected</c:if>>근무자</option>
                                				<option value="Y" <c:if test="${isOut eq 'Y'}">selected</c:if>>퇴사자</option>
                         					</select>
                                    </td>
                                    
                                     <th>부서</th>
                                    <td>
                                    	<select name="deptCode" id="deptCode" style="width:200px;">
                                    		<option value="A">선택</option>
                                    		<c:forEach items="${deptData}" var="entry">
                                    			<option value="${entry.id}">${entry.deptName}</option>
                                    		</c:forEach>
                                    	</select>	                           
                                    </td>
                                     <th>이름</th>
                                    <td>
                                        <input type="text" name="usrName" id="usrName" value="${usrName}" class="w280">
                                    </td>

                                    <th>입사년 이하</th>
                                    <td>
                                        <input type="date" name="DateBegin" id="DateBegin" value="${DateBegin}" class="w140">
                                    </td>
                                   </tr>
                           
                            </table>
                        </div>
                        
                       
                        <div class="searchBtn">
                       	<a href="#" class="btn_blue" id="FindButton">검색</a> &nbsp;&nbsp;
                       	
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
                                		<col style="width:25%;" />
                                		<col style="width:25%;" />
                                		<col style="width:25%;" />
                                		<col style="width:25%;" />
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                       
                                    		<th>부서</th>
                                    		<th>이름</th>
                                        	<th>직위</th>
                                        	<th>입사일</th>
                                       
                                        	
                                  
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

				PopWin('/general/popup/generalUsrEdit?id=' + encodeURI(id),'1000','600','no');				
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
					url : "/general/data/main",
					data : function(d){
						d.isOut = $("#isOut option:selected").val();
						d.deptCode =$("#deptCode option:selected").val();
						d.usrName = $("#usrName").val();
						d.DateBegin = $("#DateBegin").val();
					}					
				},
				"columns" : [
					
					
				
					{"data" : "deptName"},	
					{"data" : "name", render : function(data, type, full, meta){
			             return '<a href="#" class="RegPopup" data-id="' + full.id + '">'+ data +"</a>";
		             } 
					},
					{"data" : "position"},
					{"data" : "joinDate"}

				]
			});
			
		});
		
		
		</script>
	
	</layout:put>
</layout:extends>