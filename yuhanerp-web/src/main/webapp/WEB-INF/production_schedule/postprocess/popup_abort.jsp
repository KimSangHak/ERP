<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.js"></script>
	<script type="text/javascript" src="/js/datatables.min.js"></script>
    <script type="text/javascript" src="/js/ux.js"></script>
    <script type="text/javascript" src="/js/auto_datepicker.js"></script>
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/css/dataTables.jqueryui.min.css"/>
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script type="text/javascript" src="/js/menu.js"></script>
</head>

<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1> 후처리 발주 취소</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
           	<form method="POST" id="OrderForm" action="#">
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2> 후처리 발주 취소 등록</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                
                    <table id="TBL">
                        <caption></caption>
                        <colgroup span="2">
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:80px;" />
                        <col style="width:80px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:100px;" />
                        <col style="width:120px;" />
                        <col style="width:80px;" />
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        </colgroup>
                        <thead>
                        <tr>
                        	<th>업체</th>
                            <th>Device</th>
                            <th>도면번호</th>
                            <th>설계자</th>
                            <th>Description</th>
                            <th>Dimensions</th>
                            <th>Mat’l</th>
                            <th>열처리</th>
                            <th>수량</th>
                            <th>Spare</th>
                            <th>후처리</th>
                            <th>납기일</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    
                </div>
                <!-- //게시판 -->
                
            </div>
            </form>
            <!-- //테이블 콘텐츠 -->
           
            <div class="progress">
                <p>
                	<span class="pr20"><input type="radio" name="coatingAbortType" value="AbortCoatingOrderOnly" checked="checked"> 후처리 발주만 취소</span>
                    <span class="pr20"><input type="radio" name="coatingAbortType" value="CoatingSkip" disabled="disabled"> 후처리 스킵</span>
                    <span class="pr20"><input type="radio" name="coatingAbortType" value="AsFinishedProduct" disabled="disabled"> 완품으로 처리</span>
                </p>
                <p class="pt10"><textarea rows="10" id="cancelReason" placeholder="후처리 취소 사유를 입력하세요"></textarea></p>
            </div>
           
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                <a href="#" id="MakeCancel" class="btn_blue">발주 취소</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	<script type="text/javascript">
		var drawingNo = "${targetIdList}";

		$(document).ready(function() {
			
			$("#MakeCancel").click(function(e){
				e.preventDefault();
								
				var cancelTypeValue = $("input[name='coatingAbortType']:checked").val();
				var cancelReason = $("#cancelReason").val();
				
				$.ajax({
					url : "/ps/postprocess/make/abort",
					data : {
						"id" : drawingNo,
						"coatingAbortType" : cancelTypeValue,
						"reason" : cancelReason
					},
					type : "POST",
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							alert("처리되었습니다");
							opener.location.reload();
							window.close();
						}else{
							alert(obj.reason);
						}							
					},error : function(){
						alert("서버 오류로 인해 처리할 수 없습니다");
					}
				});
			});
			
			// 취소할 목록 보여주기
			$("#TBL").DataTable({
				"paging" : false,
				"ordering" : false,
				"info" : false,
				"searching" : false,
				"language" : dataTableLanguageCommon,
				"serverSide" : true,
				"ajax" : {
					url : "/ps/postprocess/data/order?id=" + drawingNo
				},
				"columns" : [
					{"data" : "outsourcingPartnerName"},
					{"data" : "device"},
					{"data" : "designDrawingNo"	},
					{"data" : "designUserName"	},
					{"data" : "description"		},
					{"data" : "dimension"		},
					{"data" : "material"		},
					{"data" : "thermal"			},
					{"data" : "quantity"		},
					{"data" : "spare"			},
					{"data" : "postprocessing"	}, 
					{"data" : "deliveryPlanDate"}
				]
				 
			});

		});
	</script>

</body>
</html>
