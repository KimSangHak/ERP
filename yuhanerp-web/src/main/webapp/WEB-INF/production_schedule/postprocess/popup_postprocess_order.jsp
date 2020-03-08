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
        	<h1> 후처리 발주 ${pageExtraTitle }등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
           	<form method="POST" id="OrderForm" action="/ps/postprocess/perform/order">
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2> 후처리 발주 ${pageExtraTitle}등록</h2>
                
                <div class="btn_area">
                	<span class="pr10">후처리업체 : </span>
                	<span>
                		<c:import url="/internal/util/select/postprocess_partner">
                			<c:param value="targetPartner" name="controlName"/>
                			<c:param value="w100" name="cssClass"/>
                		</c:import>
                    </span>
                	<span>
                    	<input type="text" class="AutoDatePicker" style="width: 140px" id="GlobalDate" >
                    </span>
                	<span>
                    	<a href="#" class="btn_line_gray" id="ApplyAll">납기일 일괄 적용</a>
                    </span>
                </div>
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
            
           
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                <a href="#" id="MakeOrder" class="btn_blue">발주 요청 하기</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
	<script type="text/javascript">
		var drawingNo = "${targetIdList}";

		$(document).ready(function() {
			
			// 납기일 일괄 적용
			$("#ApplyAll").click(function(e){
				e.preventDefault();
				
				var val = $("#GlobalDate").val();
				console.log("date is " + val);
				
				$("input[name='deliveryDate']").each(function(){
					$(this).val(val);	
				});
			});
			
			// 1. 후처리 발주 요청
			// 2. 후처리 전환 발주 요청
			$("#MakeOrder").click(function(e){
				e.preventDefault();
				
				// 목록 존재 유무 확인
				// 후처리 업체
				var selectedPartner = $("#targetPartner").val();
				if(selectedPartner == "" || selectedPartner == null){
					alert("등록된 후처리 업체가 없어 발주 할 수 없습니다");
					return;
				}
				console.log("selected-partnerid = " + selectedPartner);
				
				if( $("input[name='id']").length == 0){
					alert("전환 대상이 없습니다");
					return;
				}				
				
				$("form").ajaxSubmit({
					url : "/ps/postprocess/make/outsourcing_order?pageFrom=${pageFrom}",
					type : "POST",
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							alert("처리되었습니다");
							PopWin("/mail/postprocess/" + obj.data + "/" + selectedPartner  ,'1600','600','no');
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
			
			// 후처리 발주 등록
			$("#TBL").DataTable({
				"paging" : false,
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
					url : "/ps/postprocess/data/${queryPage }?id=" + drawingNo
				},
				"columns" : [
					{"data" : "customerName"},
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
					{
						targets : -1,
						data : null,
						render : function(data, type, row, meta){ return '<input type="hidden" name="id" value="' + row.id + '"/><input name="deliveryDate" type="text" class="lazyAutoDatePicker" />'; } 
					}
				]
				 
			});

		});
	</script>

</body>
</html>
