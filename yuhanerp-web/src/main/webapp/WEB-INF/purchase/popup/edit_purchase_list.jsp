<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	// 구매 리스트 수정 팝업
	// 디자인 파일명 : 02_plan04_pop01.html
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/jquery.form.min.js"></script>
    
    <script type="text/javascript" src="/js/menu.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>구매 LIST 수정</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>구매 LIST</h2>
                <div class="btn_area">
                	
                	<span class="pr10"><label><input type="checkbox" class="withIssue" id="w27">발주리스트와 함께</label></span>
                    <span><a href="#" class="btn_line_gray" id="DeleteButton">삭제</a></span>
                    <span><a href="#" class="btn_line_gray" id="EditButton">수정</a></span>
                </div>
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                	<form method="POST" action="/purchase/perform_update_purchase" id="EditForm">
                	<input type="hidden" name="id" value="${data.id }" />
                	
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:70px;" />
                        <col style="width:50px;" />
                        <col style="width:40px;" />
                        <col style="width:120px;" />
                        <col style="width:160px;" />
                        <col style="width:100px;" />
                        <col style="width:160px;" />
                        <col style="width:50px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>등록일</th>
                                <th>Order No.</th>
                                <th>No.</th>
                                <th>Description</th>
                                <th>Model No./Size</th>
                                <th>Maker</th>
                                <th>거래처</th>
                                <th>수량</th>
                                <th>Spare</th>
                                <th>Code</th>
                                <th>Comment</th>
                                <th>Remark</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                                <td><fmt:formatDate value="${data.regDate }" pattern="yyyy.MM.dd"/></td>
                                <td>${data.jobOrderNo }</td>
                                <td>${data.unitNo }</td>
                                <td>${data.description }</td>
                                <td><input type="text" id="w100" name="modelNo" value="${data.modelNo }" readonly><a href="javascript:PopWin('/purchase/popup/model_search','600','410','no');" class="btn_search">검색</a></td>
                                <td><input type="text" name="maker" value="${data.maker }" readonly></td>
                                <td><input type="text" id="w100" name="partnerName" value="${data.partnerName }">
                                	<input type="hidden" name="partnerId" value="${data.partnerId}">
                                	<a href="javascript:PopWin('/purchase/popup/partner_search','600','410','no');" class="btn_search">검색</a></td>
                                <td><input type="text" name="quantity" value="${data.quantity }"></td>
                                <td><input type="text" name="spare" value="${data.spare }"></td>
                                <td><input type="text" name="code" value="${data.code }"></td>
                                <td><input type="text" name="comment" value="${data.comment }"></td>
                                <td><input type="text" name="remark" value="${data.remark }"></td>
                            </tr>
                        </tbody>
                    </table>
                    </form>
                </div>
                <!-- //게시판 -->
            
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 테이블 콘텐츠 -->
                        <!-- 
            
            <div class="pcTit mt20">
                <h2>발주 LIST</h2>
            </div>
            
        	<div class="popList">

                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:70px;" />
                        <col style="width:200px;" />
                        <col style="width:70px;" />
                        <col style="width:120px;" />
                        <col style="width:120px;" />
                        <col style="width:80px;" />
                        <col style="width:50px;" />
                        <col style="width:50px;" />
                        <col style="width:80px;" />
                        <col style="width:70px;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>Device</th>
                            <th>구매<br/>등록일</th>
                            <th>Description</th>
                            <th>Model/Size</th>
                            <th>Maker</th>
                            <th>단가</th>
                            <th>수량</th>
                            <th>재고수량</th>
                            <th>공급가액</th>
                            <th>납기일</th>
                        </tr>
                        <tr>
                        	<td>70001</td>
                            <td>SI2692 PCB Bending System</td>
                            <td>2016.09.01</td>
                            <td>Cylinder</td>
                            <td>MXQ20-50BS-A93</td>
                            <td>SMC</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                        -->
                </div>
                
            </div>
            
        </div>
            <!-- //테이블 콘텐츠 -->
        
        
        
	</div>
</div>

	<script type="text/javascript">
	
	var currentPurchaseId = "${jobPurchaseId}";
	
	// 모델 팝업에서 선택시 호출
	function afterModelSelected(model, maker, provider){
		console.log("model = [" + model + "], maker = [" + maker + "]");
		$("input[name='modelNo']").val( model );
		$("input[name='maker']").val( maker );
		// $("input[name='provider']").val( provider );
	}
	
	function afterPartnerSelected(newPartnerId, newPartnerName){
		console.log("new id=[" + newPartnerId + "], partnerName=[" + newPartnerName + "]");
		
		$("input[name='partnerId']").val( newPartnerId);
		$("input[name='partnerName']").val( newPartnerName);		
	}
	
	$(document).ready(function(){
		
		// 삭제 버튼 클릭
		$("#DeleteButton").click(function(e){
			e.preventDefault();
			
			var withIssueList = $(".withIssue").prop("checked") ? "Y" : "N";
			
			
			var deleteReason = prompt("삭제 사유를 입력해 주세요", "");
			if($.trim(deleteReason) == ""){
				alert("사유를 입력해야 합니다");
				return;
			}
			
			$.ajax({
				url : "/purchase/delete_purchase",
				data : { "id" : currentPurchaseId, 
						 "reason" : deleteReason,
						 "withIssue" : withIssueList

				},
				type : "POST",
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						alert("삭제되었습니다");
						window.close();
					}else{
						alert(obj.reason);						
					}
				},
				error : function(){
					alert("내부 오류로 처리 할 수 없습니다");
				}
			}); // end of ajax
		}); // end of #DeleteButton
		
		// 수정 버튼 클릭
		$("#EditButton").click(function(e){
			e.preventDefault();

			var withIssueList = $(".withIssue").prop("checked") ? "Y" : "N";
			
			$("#EditForm").ajaxSubmit({
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						alert("변경 되었습니다");
					}else{
						alert(obj.reason);						
					}					
				},
				error : function(){
					alert("내부 오류로 처리 할 수 없습니다");
				}
			})	;		
						
		});
	});
	
	</script>

</body>
</html>
