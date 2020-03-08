<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/" />
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	<link rel="stylesheet" type="text/css" href="/css/ui.jqgrid.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
    
    
    <script type="text/javascript" src="/js/api.js"></script>
    
    
	<script type="text/javascript" src="/js/jquery.jqGrid.src.js"></script>

</head>

<body class="sub">

<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>(${base} ${extra}) 진행 도면 출도</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>출도 내용</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
            
            <!-- 출도 내용 -->
            <div class="planArea">
                <!-- 리스트 텍스트 -->
                 <div id="dropzone" class="planCont">
                		Drag &amp; Drop Files Here
                    <!-- <textarea></textarea>  -->
                </div>
                <!-- 리스트 텍스트 -->
                
                <!-- 버튼영역 -->
                <div class="btn_area">
                	<a href="#" class="btn_gray" id="ResetButton">다시작성</a> 
                	<input type="checkbox" id="newManufacture" />신규 제작 여부
                	<a href="#" class="btn_gray" id="AddButton">직접입력행추가</a>
               	</div>
                <!-- //버튼영역 -->
            </div>
            <!-- //출도 내용 -->
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>작업 발주(지시) 메일 발송</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">
            
            		<form id="submitDrawingForm" action="register_drawing_job" method="POST" enctype="form-data">
            		</form>
                <!-- 게시판 -->
                <div class="popBoard" id="jqOwner">
                    <table id="jqlist" class="planCont">
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <div class="progress">
            	<p class="blue">출도 달성 률은 현재 진행중인 Order에 해당되는 도면 출도 완료까지 몇 % 달성되었는지 입력하면 됩니다.</p>
                <p>
                	<span>출도 달성률</span>
                    <span><input type="number" id="achievementPercent" class="w100"> %</span>
                </p>
            </div>
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a id="quitBtn" class="btn_gray">취소</a>
                <a id="registerButton" class="btn_blue">도면출도 등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
	</div>
</div>

		<script type="text/javascript">
		
			$(document).ready(function () {
				
				var jobOrderId = ${jobOrderId};
				var jobOrderNo = "${jobOrder.orderNo}";
				
				// 파일 Drag&Drop 처리
			   	 var obj = $("#dropzone");
			     obj.on('dragenter', function (e) {
					e.stopPropagation();
 		  	   	    e.preventDefault();
					$(this).css('border', '2px solid #5272A0');		

		    		 });
			     obj.on('dragleave', function (e) {
			          e.stopPropagation();
			          e.preventDefault();
			     });
			     obj.on('dragover', function (e) {
			          e.stopPropagation();
			          e.preventDefault();
			     });
			     obj.on('drop', function (e) {
			          e.preventDefault();
			          $(this).css('border', '2px dotted #8296C2');
			          var files = e.originalEvent.dataTransfer.files;
			          if(files.length < 1)
			               return;
			          F_FileMultiUpload(files, obj);
			     });
			
			// 파일 멀티 업로드
			function F_FileMultiUpload(files, obj) {
				
				var file = files[0];
				if(confirm(file.name + "을 업로드 하시겠습니까?")) {
			        var data = new FormData();
					//for (var i = 0; i < files.length; i++) {
					//data.append('', files[i]);
					data.append('A1', file);
	        		 //}
	      		     var url = "/api/upload/excel/drawing";
	      	   $.ajax({
	   		         url: url,
	 	  	         method: 'post',
	 	             data: data,
	     	         dataType: 'json',
	           		 processData: false,
	    	            contentType: false,
	    		        success: function(res) {
	             	   F_FileMultiUpload_Callback(res);
	   	         	}
	    	     	});
				}
			}
			
			var resultData = "";
			var myGrid;
			// 파일 멀티 업로드 Callback
			function F_FileMultiUpload_Callback(res) {
				 console.log(res);
				 
				 if(res != null && res != undefined) {
					 
					 if(res.result == "OK") {
						 resultData = res;
						 var datas = res.data;
						 console.log("datas.length : " + datas.length);
						 console.log("datas : " + datas);				 
						 //setjqGrid(res);
						 //jqgrid 초기화 
						 $("#jqlist").clearGridData();

				        // 스크립트 변수에 담겨있는 json데이터의 길이만큼
				        // 			        			if($("#jqlist") != null || $("#jqlist") != undefined ) {

				        var assignNewStatus = $("#newManufacture").prop("checked");
				        console.log("newManufacture = " + newManufacture);
				        for(var i=0;i<datas.length;i++){
				        	var tmp = datas[i];
				        	console.log("line = " + typeof(tmp) + " " + tmp);
				        	if(assignNewStatus){	// 신규 제작 체크가 된 경우
					        	tmp.classification = "신규제작";
					        	tmp.reason = "";
				        	}
			             	myGrid.jqGrid('addRowData',i+1,tmp);
				        }
					 } else {
						 console.log(res.result);
					 }
				 }
			}
							
				// jqgrid			
				var pageWidth = $("#jqOwner").width() - 120;		
				var lastRowIndex = -1, lastColIndex, lastSelection = -1;
				
				//jqGrid껍데기 생성
			    myGrid = $("#jqlist").jqGrid({
			        //로컬그리드이용
			        datatype: "local",
			        gridview : true,
			        autowidth:true,
			        rownumbers: true,
	                cellsubmit: 'clientArray',
			        //cellEdit: true,			        
			        height: 'auto',	//그리드 높이
			        //컬럼명들
			        colNames:['도면번호','Description', 'Dimensions', 'Mat’l','열처리', '수량', 'Spare', '후처리', "분류", "사유", '검사', '비고'],
			        //컬럼모델
			        colModel:[
			            {name:'drawingNo',width: (pageWidth * (8/100)), editable : true},
			            {name:'description',width:(pageWidth * (13/100)), editable : true},
			            {name:'dimensions',width:(pageWidth * (13/100)), editable : true},
			            {name:'material',width:(pageWidth * (7/100)), editable : true},
			            {name:'heatTreat',width:(pageWidth * (7/100)), editable : true},
			            {name:'quantity',width:(pageWidth * (5/100)), editable : true},
			            {name:'spare',width:(pageWidth * (7/100)), editable : true},
			            {name:'appliedFinish',width:(pageWidth * (10/100)), editable : true},
			            {name:'classification',width:(pageWidth * (10/100)), editable : true, defaultValue : '신규제작', edittype : 'select', formatter : 'select', editoptions : {value : "신규제작:신규제작;추가제작:추가제작;재제작:재제작;수정:수정;구매:구매;재료구매:재료구매;도면교체:도면교체;재출도:재출도;A/S제작:A/S제작" } },
			            {name:'reason',width:(pageWidth * (10/100)), editable : true, defaultValue : '', edittype : 'select', formatter : 'select', editoptions : {value : "미지정:미지정;설계불량:설계불량;업체요구:업체요구;가공불량:가공불량;조립불량:조립불량;공정불량:공정불량;설계보완:설계보완;가공보완:가공보완;조립보완:조립보완;기타:기타"}  },
//			            {name:'classification',width:(pageWidth * (10/100)), editable : true, defaultValue : '1', edittype : 'select', formatter : 'select', editoptions : {value : "1:신규제작;2:추가제작;3:재제작;4:수정;5:구매;6:재료구매;7:도면교체;8:재출도;9:A/S제작" } },
//			            {name:'reason',width:(pageWidth * (10/100)), editable : true, defaultValue : '', edittype : 'select', formatter : 'select', editoptions : {value : ":미지정;A:설계불량;B:업체요구;C:가공불량;D:조립불량;E:공정불량;F:설계보완;G:가공보완;H:조립보완;I:기타"}  },
			            {name:'qc',width:(pageWidth * (7/100)), editable : true, defaultValue : 'N', edittype : 'select', formatter : 'select', editoptions : {value : "N:N;Y:Y"}},
			            {name:'note',width:(pageWidth * (13/100)), editable : true}
			        ],
			        //그리드타이틀
			        //caption: "그리드 목록",
			        //pager: "#jqGridPager",
			        // shrinkToFit:false,
			        //더블클릭 이벤트... 더블클릭할 경우 에디트 모드로. 
			        ondblClickRow: function (rowid, iRow, iCol) { 
			            var $this = $(this);

			            $this.jqGrid('setGridParam', {cellEdit: true});
			            $this.jqGrid('editCell', iRow, iCol, true);
			            $this.jqGrid('setGridParam', {cellEdit: false});
			        }, 	        	
			        //에디트가 종료되면, 셀의 에디트 가능 여부를 false 로 돌린다. 
		        	afterEditCell: function (rowid, cellname, value, iRow, iCol) { 
		        		console.log("stop editing " + iRow + "," + iCol);
		        	    
		        		var cellDOM = this.rows[iRow].cells[iCol], oldKeydown,
		                $cellInput = $('input, select, textarea', cellDOM),
		                events = $._data($cellInput[0], 'events'); // $cellInput.data('events'),
		                $this = $(this);
		        	    
			            if (events && events.keydown && events.keydown.length) {
			                oldKeydown = events.keydown[0].handler;
			                $cellInput.unbind('keydown', oldKeydown);
			                $cellInput.bind('keydown', function (e) {
			                    $this.jqGrid('setGridParam', {cellEdit: true});
			                    oldKeydown.call(this, e);
			                    $this.jqGrid('setGridParam', {cellEdit: false});
			                }).bind('focusout', function (e) {
			                	console.log("focusout " + iRow + "," + iCol);
			                    $this.jqGrid('setGridParam', {cellEdit: true});
			                    $this.jqGrid('saveCell', iRow, iCol);
			                    $this.jqGrid('setGridParam', {cellEdit: false});
			                    $(cellDOM).removeClass("ui-state-highlight");
			                });
			            }
		        	}
   			    });
				
				// window 크기 변경되면 jqgrid 크기 변경
				$(window).resize(function(){
					pageWidth = $("#jqOwner").width();
					console.log("new width is " + pageWidth);
					myGrid.jqGrid("setGridWidth", pageWidth);
				});
				
				$("#ResetButton").click(function(e){
					e.preventDefault();
					
					myGrid.jqGrid("clearGridData");
					
						
				}).trigger("click");
				
				$("#AddButton").click(function(e){
					e.preventDefault();
					
					
					
				
					myGrid.jqGrid("addRowData", undefined, {});
									
				}).trigger("click");

				// 등록 버튼 클릭 처리
				$("#registerButton").click(function(e){
					console.log("registerButton ");
					e.preventDefault();
					var gridData = $("#jqlist").getRowData();
					console.log("gridData :  " + gridData);
					console.log("datas.length : " + gridData.length);
					console.log("JSON : " + JSON.stringify( gridData ));
					
					$.ajax({
					   		type: "POST",
					       url: "/drawing/issue/register",
					       data : {	excelData:JSON.stringify( gridData ), 
					    	   		jobOrderId:jobOrderId, 
					    	   		achievementPercent : $("#achievementPercent").val()},					       
					       dataType : "json",
					       success: function(response) {
					    	   if(response.result == "OK"){
					    	   	alert("도면 출도 등록을 완료 했습니다.");
					    	   	opener.location.reload();
					    	   	window.close();
					    	   }else
					    		alert("처리 할 수 없습니다\n" + response.reason);
					       },
					       error: function(xhr, textStatus, errorThrown) {
					           alert("요청을 처리할 수 없습니다.");
					       }
					});
				});
				
				$("#quitBtn").click(function(e){
					self.close();
					
				});
				
				/*
				var getParameters = function (paramName) {
				    // 리턴값을 위한 변수 선언
				    var returnValue;

				    // 현재 URL 가져오기
				    var url = location.href;

				    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
				    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

				    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
				    for (var i = 0; i < parameters.length; i++) {
				        var varName = parameters[i].split('=')[0];
				        if (varName.toUpperCase() == paramName.toUpperCase()) {
				            returnValue = parameters[i].split('=')[1];
				            return decodeURIComponent(returnValue);
				        }
				    }
				};
				*/
		});
		
			
	</script>
</body>
</html>
