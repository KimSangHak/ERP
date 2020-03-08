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
        	<h1>구매 리스트 등록(${order.orderNo})</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <!-- <h2>출도 내용</h2> -->
            </div>
            <!-- //팝업 서브타이틀 -->
            
            <!-- 출도 내용 -->
            <div class="planArea">
                <!-- 리스트 텍스트 -->
                 <div id="dropzone" class="planCont">
                		엑셀 양식 파일을 여기에 Drag &amp; Drop 해주세요
                    
                </div>
                <!-- 리스트 텍스트 -->
                
                <!-- 버튼영역 -->
                <div class="btn_area">
                	<a href="#" class="btn_gray" id="ResetButton">다시작성</a>
                	<a href="#" class="btn_gray" id="AddButton">직접입력행추가</a>
               	</div>
                <!-- //버튼영역 -->
            </div>
            <!-- //출도 내용 -->
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>내역</h2>
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
            
         
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="Cancle">취소</a>
                <a id="registerButton" class="btn_blue">구매 리스트 등록</a>
               
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
	</div>
</div>

		<script type="text/javascript">
		
			var myGrid;
			var currentJobOrderId = "${order.id}";
			
			function afterFileUploaded(fileNo, param1){
				console.log("첨부 파일 번호 = " + fileNo + ", RowID=" + param1);
				
				var rowId = param1;
				myGrid.jqGrid("setRowData", rowId, { designFileNo : fileNo });
			}
			
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
		      		     var url = "/api/upload/excel/purchase";
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
					        for(var i=0;i<datas.length;i++){
					        	var tmp = datas[i];
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
			        colNames:['*Unit No','*Description', '*MODEL No./SIZE' ,'*Maker', '*Provider','*수량', 'spare', 'code', 'comment', "remark", '도면파일', 'designFileNo'],
			        //컬럼모델
			        colModel:[
			            {name:'unitNo',width: (pageWidth * (8/100)), editable : true},
			            {name:'description',width:(pageWidth * (13/100)), editable : true},
			            {name:'modelNo',width:(pageWidth * (13/100)), editable : true},
			            {name:'maker',width:(pageWidth * (13/100)), editable : true},
			            {name:'provider',width:(pageWidth * (7/100)), editable : true},
			            {name:'quantity',width:(pageWidth * (7/100)), editable : true},
			            {name:'spareQuantity',width:(pageWidth * (5/100)), editable : true},
			            {name:'code',width:(pageWidth * (7/100)), editable : true},
			            {name:'comment',width:(pageWidth * (10/100)), editable : true},
			            {name:'remark',width:(pageWidth * (10/100)), editable : true},
			            {name:'designFile', index:"designFile", width: (pageWidth * (10/100)), editable :false},
			            {name:'designFileNo', hidden : true}
			        ],
			        //그리드타이틀 l'/km			        //caption: "그리드 목록", ji		        : \jkib
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
		        	},
		        	gridComplete: function(){
		        		var ids = $(this).jqGrid("getDataIDs");
		        		console.log("init - " + ids.length);
		        		for(var i=0;i<ids.length;i++){
		        			$(this).jqGrid("setRowData", ids[i], { "designFile" : "<input type=\"button\" class=\"PostFile\" data-id=\"" + ids[i] + "\" value=\"파일 첨부\" />" });
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
					
					
					// 시작하면 빈줄 삽입
							
				}).trigger("click");
				
				
				
				$("#AddButton").click(function(e){
					e.preventDefault();
					
					
					
				
					myGrid.jqGrid("addRowData", undefined, {});
									
				}).trigger("click");
				
				
				$("#Cancle").click(function(e){
					
					self.close();
					
					
							
				});
				
				
	
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
					       url: "/purchase/perform_reg_purchase",
					       data : {	"gridData":JSON.stringify( gridData ), 
					    	   		"jobOrderId":currentJobOrderId 
					    	   		},					       
					       dataType : "json",
					       success: function(response) {
					    	   if(response.result == "OK"){
					    	   	alert("구매 LIST 등록을 완료 했습니다.");
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
							
				$(document).on("click", ".PostFile", function(e){
					e.preventDefault();
					
					// 선택된 행 ID
					var rowId = $(this).attr("data-id");
					console.log("row-id is " + rowId);
					
					PopWin("/poup/upload/file?sectionId=PURCHASE&fileCategory=DOC&openerFunctionName=afterFileUploaded&parameter1=" + rowId, '500', '200', "no"); 
					
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
