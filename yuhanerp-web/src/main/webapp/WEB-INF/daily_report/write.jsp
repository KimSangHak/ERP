<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/DailyReportMaster.jsp">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>생산일정리스트</li>
                        <li>생산일정 등록&amp;수정</li>
                    </ul>
                    <h2>생산일정 등록&amp;수정</h2>
                    <div class="btnArea">
                    	<!-- 신규 작업지시  -->
                        <a href="javascript:PopWin('/daily_report/popup/new_job','600','700','no');" class="btn_line">설비 작업지시</a>
                        <!-- Ceoncept 진행 및 예정 신규 -->
                        <a href="javascript:PopWin('/daily_report/popup/job_concept','600','640','no');" class="btn_line">설비 컨셉</a>
                        
                        <!-- 진행 -->
                        <a href="javascript:PopWin('/daily_report/popup/new_jig','600','700','no');" class="btn_line">치공구 작업지시</a>
                        <a href="javascript:PopWin('/daily_report/popup/jig_concept','600','700','no');" class="btn_line">치공구 컨셉</a>
                    </div>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                    		<form method="GET">
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
                            		<th>검색유형</th>
                                    <td>
                                        <label><input type="radio" name="aa" checked />전체</label>
                                        <label><input type="radio" name="aa" />업체</label>
                                    </td>
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase }" maxlength="5" class="w130"> - <input type="text" id="orderNoExtra" name="orderNameExtra" value="${orderNoExtra }" class="w130">
                                    </td>
                                    <th>내용</th>
                                    <td>
                                        <input type="text" name="keyword" value="${keyword }" id="keyword" class="w280">
                                    </td>
                                </tr>
                            </table>
                            </form>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue" id="FindButton">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<c:import url="/internal/dailyreport/write/job/progress">
                    		<c:param value="" name="dummy"/>
                    	</c:import>
                   	
                    	<c:import url="/internal/dailyreport/write/job/concept/finished">
                    		<c:param value="" name="dummy"/>
                    	</c:import>
                   	
                    	<c:import url="/internal/dailyreport/write/job/concept">
                    		<c:param value="" name="dummy"/>
                    	</c:import>
               
                    	<c:import url="/internal/dailyreport/write/jig/progress">
                    		<c:param value="" name="dummy"/>
                    	</c:import>
                    	
                    	<c:import url="/internal/dailyreport/write/jig/concept/finished">
                    		<c:param value="" name="dummy"/>
                    	</c:import>
                   	
                   		<c:import url="/internal/dailyreport/write/jig/concept">
                    		<c:param value="" name="dummy"/>
                    	</c:import>                     

                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
                
				<form id="ConceptTransfer">
					<input type="hidden" name="orderNo" value="" />
					<input type="hidden" name="id" value="" />
					<input type="hidden" name="requestedAction" value="" />
					<input type="hidden" name="device" value="" />
					<input type="hidden" name="conceptFinishDate" value="" />
					<input type="hidden" name="quantity" value="" />
					<input type="hidden" name="customerUser" value="" />
					<input type="hidden" name="note" value="" />
				</form>
                
	</layout:put>

	<layout:put block="BodyScriptBlock2">
	<script type="text/javascript">

	var prompted = false;
	
	// 파일 업로드 완료 후 호출	
	function afterFileUpload(fileNo, id, dataPos, fieldName){
		//  
		var inputField = $("tr[id='" + dataPos + "'] td input[name='" + fieldName + "']");
		//alert(dataPos + " ==> " + inputField.length + ", current = " + inputField.val());
		inputField.val(fileNo);
	}
	
	function afterConceptFileAttached(fileNo, dataPos){
		var inputField = $("tr[id='" + dataPos + "'] td input[name='conceptFileNo']");
		inputField.val(fileNo);

		var targetForm = $("tr#" + dataPos ).find("input,select,textarea");
		var serializedData = targetForm.serializeArray();
		
		requestEditConcept(serializedData);
	}

	function	getFindParameter(){
		return "?keyword=" + escape( $("#keyword").val() ) + "&orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() );
	}
	
	function requestEditConcept(serializedData){
		$.ajax({
			url : "/daily_report/update/concept",
			data : serializedData,
			method : "POST",
			dataType : "json",
			success : function(obj){
				if(obj.result == "OK"){
					alert("변경되었습니다");
					location.reload();
				}else
					alert(obj.reason);
			},
			error : function(){
				alert("error");
			}
		});		
	}

	$(document).ready(function(){
		
		// 검색버튼
		$("#FindButton").click(function(e){
			e.preventDefault();
			
			location.href=getFindParameter();  
		});
		
		// 컨셉 수정 '확인' 버튼 클릭
		$(".ButtonActionForConcept").click(function(e){
			e.preventDefault();
			
			var dataTarget = $(this).attr("data-target");
			var targetForm = $("tr#" + dataTarget ).find("input,select,textarea");
			var requestedAction = "";
			var conceptFileNo = "";
			var conceptJobId = "";
			var orderType=  "";
			
			var serializedData = targetForm.serializeArray();
			for(var i = 0 ;i<serializedData.length;i++){
				if(serializedData[i].name == "requestedAction"){
					requestedAction = serializedData[i].value;
				}
				if(serializedData[i].name == "conceptFileNo"){
					conceptFileNo = serializedData[i].value;;
				}
				if(serializedData[i].name == "id"){
					conceptJobId = serializedData[i].value;;
				}
				
				if(serializedData[i].name == "orderType")
					orderType = serializedData[i].value;
				
			}
			if(requestedAction === undefined || requestedAction == ""){
				alert("동작을 선택해 주세요");
				return;
			}
			
			// 완료 선택
			if(requestedAction == "finish"){
				/*
				if(conceptFileNo == ""){
					
					if(!prompted){
						alert("완료하기 위해 컨셉 도면 파일을 첨부해야 합니다");
						prompted = true;
					}
			
					PopWin('/poup/upload/file?section=CONCEPT&fileCategory=IMG&openerFunctionName=afterConceptFileAttached&parameter1=' + dataTarget,'600','240','no');					
					return;
				}*/
				
				PopWin('daily_report/popup/' + orderType + '_concept?id=' + conceptJobId,'600','480','no');					
				return;
			}else if(requestedAction == "F"){
				// 작업 지시!
				PopWin('/daily_report/popup/new_' + orderType + '/?fromConcept=' + conceptJobId ,'600','700','no');		
				return;
			}
			
			// 삭제 선택
			if(requestedAction == "delete"){
				if(!confirm("정말 삭제하시겠습니까?"))
					return;
			}			
			
			requestEditConcept(serializedData);
		});
		
		// 설비/치공구 '확인' 버튼 클릭
		$(".ButtonActionForJobJig").click(function(e){
			e.preventDefault();
			
			var targetForm = $("tr#" + $(this).attr("data-target") ).find("input,select,textarea");
			var targetOrderNo = $("tr#" + $(this).attr("data-target") + " td input[name='orderNo']" ).val();
			var targetJobOrderId = $("tr#" + $(this).attr("data-target") + " td input[name='id']" ).val();
			var requestedAction = "";
			var serializedData = targetForm.serializeArray();
			for(var i = 0 ;i<serializedData.length;i++){
				if(serializedData[i].name == "requestedAction"){
					requestedAction = serializedData[i].value;
					break;
				}
			}
			
			if(requestedAction === undefined || requestedAction == ""){
				alert("동작을 선택해 주세요");
				return;
			}			
			// 배송 선택
			if(requestedAction == "deliver"){
				// 발송 선택
				PopWin('/daily_report/popup_job_delivery?jobOrderId=' + targetJobOrderId ,'600','680','no');
				return;
			}
			// 삭제 선택
			if(requestedAction == "delete"){
				if(!confirm("정말 삭제하시겠습니까?"))
					return;
			}
			$.ajax({
				url : "/daily_report/update/job",
				data : serializedData,
				method : "POST",
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						alert("변경되었습니다");
						location.reload();
					}else
						alert(obj.reason);
				},
				error : function(){
					alert("error");
				}
			});			
			
		});
		
		// 업로드 파일 팝업 띄우기
		$(".FileRegistrationButton").click(function(e){
			e.preventDefault();
			
			// Callback 데이터
			var fileSection = $(this).attr("data-file-section");
			
			var parameter1 = $(this).attr("data-id");	// ID
			var parameter2 = $(this).attr("data-pos");
			var parameter3 = $(this).attr("data-field");
			
			var callbackFunction = "afterFileUpload";
			
			PopWin('/poup/upload/file?section=' + fileSection + '&fileCategory=IMG&openerFunctionName=' + callbackFunction + '&parameter1=' + parameter1 + "&parameter2=" + parameter2 + "&parameter3=" + parameter3   ,'600','240','no');
		});
		
		$(".requestedActionSelector").change(function(e){
			e.preventDefault();
			
			var selected = $(this).val();
			if(selected == "finish"){
				
				
				
			}
		});
				
	});

</script>        
	<script type="text/javascript" src="/js/daily_report_common.js"></script>                	
	</layout:put>

</layout:extends>