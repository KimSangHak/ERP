<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
/*
설비 진행 작업 지시 등록 팝업
01_business02_pop03.html
*/
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title>${jobTypeDesc } 진행 작업 지시 등록</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/api.js"></script>
	
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>${jobTypeDesc } 진행 작업 지시 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

				<form id="submitForm" action="/daily_report/register_job" method="POST">
				<input type="hidden" name="orderType" value="${jobType }" />
				<input type="hidden" name="fromConcept" value="${fromConcept }" />
                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:30%;" />
                        <col style="width:20%;" />
                        <col style="width:30%;" />
                        </colgroup>
                        <tr>
                            <th>종류</th>
                            <td>${jobTypeDesc } 진행</td>
                            <th>발주일</th>
                            <td>${today}</td>
                        </tr>
                        <tr>
                            <th>업체</th>
                            <td>
		                    	
		                    	<c:import url="/internal/util/select/customer">
		                        	<c:param value="customerId" name="controlName"/>
		                            <c:param value="${data.customerId }" name="defaultValue" />
							 	</c:import>
                            </td>
                            <th>Order No</th>
                            <td>
                            	<input type="text" name="orderNoBase" id="orderNoBase" value="${data.orderNoBase }" maxlength="5" >-
                            	<input type="text" name="orderNoExtra" id="orderNoExtra" value="${data.orderNoExtra }" >
                            	<button id="GetOrderNoButton" class="btn_blue">*</button>
                            </td>
                        </tr>
                        <tr>
                            <th>Device</th>
                            <td colspan="3"><input type="text" name="device" value="${data.device }" placeholder="제작할 설비"></td>
                        </tr>
                         <tr>
                            <th>가공품재고여부</th>
                            <td colspan="3">
                            	 <select name="moveYN" id="moveYN" style="width:263px;">
                   					<option value="N">N</option>
                   					<option value="Y">Y</option>
                 				 </select>
                            
                            </td>
                        </tr>
                        <tr>
                            <th>납품일</th>
                            <td><input type="text" name="installDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.installDate}" />"></td>
                            <th>실 납품일</th>
                            <td><input type="text" name="realInstallDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.realInstallDate}" />"></td>
                        </tr>
                        <tr>
                            <th>출고일</th>
                            <td><input type="text" name="shippingDate" class="AutoDatePicker" value="${data.shippingDate }"></td>
                            <th>영업담당</th>
                            <td>
                              	
                              	<c:import url="/internal/util/select/business">
                             		<c:param value="businessUserId" name="controlName"/>
                             		<c:param value="${item.businessUserId }" name="defaultValue" />
                               	</c:import>
                            </td>
                        </tr>
                        <tr>
                            <th>설계담당</th>
                            <td>
                            	
                            	<c:import url="/internal/util/select/design">
                                	<c:param value="designUserId" name="controlName"/>
                                    <c:param value="${item.designUserId }" name="defaultValue" />
                             	</c:import>
                            </td>
                            <th>고객담당</th>
                            <td><input type="text" name="customerUser" value="${data.customerUser }"></td>
                        </tr>
                        <tr>
                            <th>비고</th>
                            <td colspan="3" class="h100"><textarea rows="5" name="note"><c:out value="${data.note }"></c:out></textarea></td>
                        </tr>
                        <tr>
                            <th class="borTop">도면 출도일</th>
                            <td class="borTop"><input type="text" name="designDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.designDate}" />"></td>
                            <th class="borTop">가공완료일</th>
                            <td class="borTop"><input type="text" name="processDate" class="AutoDatePicker"  value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.processDate}" />"></td>
                        </tr>
                        <tr>
                            <th class="borTop">검사일</th>
                            <td class="borTop"><input type="text" name="testDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.testDate}" />"></td>
                            <th class="borTop">조립일</th>
                            <td class="borTop"><input type="text" name="assemblyDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.assemblyDate}" />"></td>
                        </tr>
                        <c:if test="${jobType == 'JOB' }">
                        <!-- 설비only -->
                        <tr>
                            <th class="borTop">배선/PGM</th>
                            <td class="borTop"><input type="text" name="testDate" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.testDate}" />"></td>
                            <th class="borTop">소요기간</th>
                            <td class="borTop"><input type="text" name="estimatedDays" value="${data.estimatedDays }"></td>
                        </tr>       
                        <!-- /설비only -->                 
                        </c:if>
                        <tr>
                            <th class="borTop">수량</th>
                            <td class="borTop"><input type="text" name="quantity" value="${data.quantity }"></td>
                            <th>내부단가</th>
                            <td><input type="text" name="internalUnitPrice" value="${data.internalUnitPrice }"></td>
                        </tr>
                        <tr>
                            <th>견적금액</th>
                            <td><input type="number" name="estimatedPrice" value="${data.estimatedPrice }"></td>
                            <th>내고금액</th>
                            <td><input type="text" name="negotiatedPrice" value="${data.negotiatedPrice }"></td>
                        </tr>
                        

                    </table>
                </div>
                </form>
                
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" id="" class="ClosePopupButton btn_gray">취소</a>
                <a id="registerButton" class="btn_blue">등록</a> <!-- href="javascript:PopWin('01_business02_pop04.html','600','700','no');"  -->
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
	</div>
	
	<!-- unit 삽입용 템플릿 -->
	<div id="UnitTemplate" style="visibility: hidden; ">
		<table><tbody>
                        <tr data-unit="1">
                            <th class="borTop">Unit <span>1</span>&nbsp;<a href="#" class="DeleteUnit">[삭제]</a> </th>
                            <td class="borTop"><input type="text" name="unitName" value=""></td>
                            <th class="borTop">출도일</th>
                            <td class="borTop"><input type="date" name="designCompleteDate" value=""></td>
                        </tr>
                        <tr>
                            <th>가공일</th>
                            <td><input type="date" name="processDate" value=""></td>
                            <th>검사일</th>
                            <td><input type="date" name="testDate" value=""></td>
                        </tr>
                        <tr>
                            <th>조립일</th>
                            <td><input type="date" name="assemblyDate" value=""></td>
                            <th>배선/PGM</th>
                            <td><input type="date" name="programDate" value=""></td>
                        </tr>
		</tbody></table>                   		
	</div>
 
	<script type="text/javascript">
	
	/*
	function insertUnit(){
		
		var last = $("div.popBox table tr[data-unit]:last").attr("data-unit");
		if(last === undefined)
			last = 0;
		
		var next = Number(last) + 1;	// 다음 순번
		$("#UnitTemplate table tbody tr:first").attr("data-unit", next);
		$("#UnitTemplate table tbody tr:first th span").text( next);
		
		var templates = $("#UnitTemplate table tbody tr");
		$.each(templates, function(){
			$("div.popBox table tr:last").after( $(this).clone() );	
		});				
	}*/
	function reloadOrderNo(){
		var currentSelected = $("input[name='orderNoBase']").val();
		getNextOrderSequenceNo("JOB", currentSelected.substring(0,2), "${orderType}", function(v){
			$("input[name='orderNoBase']").val(currentSelected.substring(0,2) + v);
			$("input[name='orderNoExtra']").val("");
			});			
	}
	
	$(document).ready(function(){
	
		// 업체 변경
		$("#customerId").change(function(){
			var selectedCustomerId = $(this).val();
			
			if( $("input[name='orderNoExtra']").val() != ""){
				if(!confirm("Order-ID 가 변경됩니다"))
					return;
			}
			
			$("input[name='orderNoBase']").val(selectedCustomerId);
			$("input[name='orderNoExtra']").val("");
			
			reloadOrderNo();
		});
		
		if($("#orderNoBase").val() == ""){
			//alert("A--" + $("#orderNoBase").val());
			$("#customerId").trigger("change");
		}
		
		$("#GetOrderNoButton").click(function(e){
			e.preventDefault();
			
			reloadOrderNo();
		});		
		
		// // 처음에 1개의 유닛 입력란을 만들어 준다
		// insertUnit();
		
		// Unit 의 이름을 기록할 때마다 다음 유닛 입력란을 추가해준다
		$(document).on("change", "input[name='unitName']", function(){
			var unit = $(this).parent().parent().attr("data-unit");
			var last = $("div.popBox table tr[data-unit]:last").attr("data-unit");
			if(unit == last)
				insertUnit();
		} );
		
		// 등록 버튼
		$("#registerButton").click(function(e){
			e.preventDefault();
			
			$("#submitForm").ajaxSubmit({
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						// 팝업 띄워서 작업 메일 보내기						
						PopWin('/mail/compose/order/' + obj.data,'800','900','no');
						
						window.close();
						opener.location.reload();
						
					}else{
						alert(obj.reason);
					}
				},
				error : function(){
					alert("요청을 처리할 수 없습니다");
				}
			});				
		});
		
		/*
		// 유닛 삭제
		$(document).on("click", "a.DeleteUnit" ,function(e){
			e.preventDefault();
			var last = $("div.popBox table tr[data-unit]").length;
			if(last == 1){
				alert("최소 1개의 Unit 이 있어야 합니다");
				return;
			}
			
			var rows = $(this).parent().parent().parent();
			var tr1 = $(this).parent().parent();
			var tr2 = $(this).parent().parent().next();
			var tr3 = tr2.next();
			
			tr1.remove();
			tr2.remove();
			tr3.remove();
		});
		*/
		
	});
	
	</script>
	<script type="text/javascript" src="/js/popup_equipment_common.js"></script>		
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
</div>
</body>
</html>
