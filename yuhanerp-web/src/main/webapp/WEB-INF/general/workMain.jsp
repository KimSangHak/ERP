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
                        <li>근태관리</li>
                        
                         
                    </ul>
                  
                    <h2>근태관리</h2>
                    
                   
                  
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
                                 <col style="width:8%;" />
                                <col style="width:17%;" />
                                 <col style="width:10%;;" />
                                <col style="width:29%;" />
                                </colgroup>
                                <tr>

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
                                    
                                    
                                    <th>출근일</th>
                                    <td>
                                        <input type="date" name="DateBegin" id="DateBegin" value="${DateBegin}" class="w280">
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
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:16%;" />
                                		<col style="width:12%;" />
                                		<col style="width:12%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		<col style="width:10%;" />
                                		</colgroup>
                            
                            	<thead>
                            	
                             
                                		<tr>
                                       
                                    		<th>부서</th>
                                    		<th>이름</th>
                                        	<th>출근일</th>
                                        	<th>출근시간</th>
                                        	<th>퇴근시간</th>
                                        	<th>출근판정</th>
                                        	<th>퇴근판정</th>
                                        	<th>출근수정</th>
                                        	<th>퇴근수정</th>
                                       
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
		
		
		function inputTimeColon(time) {



		    // replace 함수를 사용하여 콜론( : )을 공백으로 치환한다.
		    var replaceTime = time.value.replace(/\:/g, "");



		    // 텍스트박스의 입력값이 4~5글자 사이가 되는 경우에만 실행한다.
		    if(replaceTime.length >= 4 && replaceTime.length < 5) {

		        var hours = replaceTime.substring(0, 2);      // 선언한 변수 hours에 시간값을 담는다.
		        var minute = replaceTime.substring(2, 4);    // 선언한 변수 minute에 분을 담는다.



		        // isFinite함수를 사용하여 문자가 선언되었는지 확인한다.
		        if(isFinite(hours + minute) == false) {
		            alert("문자는 입력하실 수 없습니다.");
		            time.value = "00:00";
		            return false;
		        }

		        // 두 변수의 시간과 분을 합쳐 입력한 시간이 24시가 넘는지를 체크한다.
		        if(hours + minute > 2400) {
		            alert("시간은 24시를 넘길 수 없습니다.");
		            time.value = "24:00";
		            return false;
		        }

		        // 입력한 분의 값이 60분을 넘는지 체크한다.
		        if(minute > 60) {
		            alert("분은 60분을 넘길 수 없습니다.");
		            time.value = hours + ":00";
		            return false;
		        }

		        time.value = hours + ":" + minute;
		    }
		}
		
		
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
					url : "/general/data/work",
					data : function(d){
						
						d.deptCode =$("#deptCode option:selected").val();
						d.usrName = $("#usrName").val();
						d.DateBegin = $("#DateBegin").val();
					
					}					
				},
				"columns" : [

					{"data" : "deptName"},	
					{"data" : "usrName"},
					
					
					
					{"data" : "registrationDate", render : function(data, type, full, meta){
			             return   '<input type="text" id="regDate" class="regDate" value=' + data + ' style="text-align:center;" readOnly/>';
		             } 
					},
					
					
					{"data" : "commmuteStart", render : function(data, type, full, meta){
						
						if(data == null){
							
							if(full.startkind == 'D'){
								data = '<input type="text" id= "startT" class="startT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;" value= ' + data + ' readOnly />';
							}else{
								
								data = '<input type="text" id= "startT" class="startT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;color:RED;" value= ' + data + ' />';
							}
							
							  
						}else{
							data = '<input type="text" id= "startT" class="startT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;" value= ' + data + ' readOnly />';
						}
			             return  data 
		             } 
					},
					
					{"data" : "commmuteEnd", render : function(data, type, full, meta){
						if(data == null){
							
							if(full.endkind == 'D'){
								
								data = '<input type="text" id= "endT" class="endT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;" value= ' + data + ' readOnly/>';
								
							}else{
								data = '<input type="text" id= "endT" class="endT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;color:RED;" value= ' + data + ' />';
							}
							  
						}else{
							data = '<input type="text" id= "endT" class="endT" onKeyup="inputTimeColon(this);" placeholder="HH:MM" maxlength="5" style="text-align:center;" value= ' + data + ' readOnly/>';
						}
			             return  data   
		             } 
					},
					
					{"data" : "startkind", render : function(data, type, full, meta){
						if(data == 'G'){
							data = '<b>정상출근</b>';
						}else if(data == 'E'){
							data = '<b style="color:RED;">판정에러</b>';
						}else if(data == 'H'){
							data = '<b style="color:BLUE;">휴일출근</b>';
						}else if(data == 'D'){
							data = '<b style="color:#FFBF00;">결근</b>';
						}
						
			             return data;
		             } 
					},
					
					{"data" : "endkind", render : function(data, type, full, meta){
						if(data == 'G'){
							data = '<b>정상출근</b>';
						}else if(data == 'E'){
							data = '<b style="color:RED;">판정에러</b>';
						}else if(data == 'H'){
							data = '<b style="color:BLUE;">휴일출근</b>';
						}else if(data == 'D'){
							data = '<b style="color:#FFBF00;">결근</b>';
						}
						
			             return data;
		             } 
					},
					
					
					{"data" : "yn", render : function(data, type, full, meta){
						
						if(data == 'Y'){
							
							
							
							if(full.startkind == 'E'){
								data = '<a href="#" class="StartEd" data-id="' + full.id + '">'+ "출근수정" +"</a>";
							}
							else{
								data = '<b>수정불가</b>';
							}
							
							
						}else{
							
							data = '<b>권한없음</b>';
							
						}
						
						 return data;
		             } 
					},
					{"data" : "yn", render : function(data, type, full, meta){
						
						if(data == 'Y'){
							
							
							
							if(full.endkind == 'E'){
								data = '<a href="#" class="" data-id="' + full.id + '">'+ "퇴근수정" +"</a>";
							}
							else{
								data = '<b>수정불가</b>';
							}
							
							
						}else{
							
							data = '<b>권한없음</b>';
							
						}
						
						 return data;
		             } 
					}

				]
			});
			
		});
		
		
		</script>
	
	</layout:put>
</layout:extends>