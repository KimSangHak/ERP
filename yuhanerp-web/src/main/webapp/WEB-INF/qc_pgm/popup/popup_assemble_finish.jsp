<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
<link rel="stylesheet" type="text/css" href="/css/multi.css">

<script type="text/javascript" src="/js/parseurl.js"></script>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/datatables.min.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>
 <script type="text/javascript">
	window.name="parent";
</script>

<style type="text/css">
	.ui-widget-header {
		border : 0;
		background : 0;		
	}
</style>

</head>
 
<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>${item.orderFullNo } ${item.device } 조립완료 >> 셋업등록
			</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 설명 -->
            <div class="popText" >

            </div>
            <!-- //설명 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">
            	<form name="submitForm" id="submitForm" action="/qcPgm/assemble_finish" method="POST">
            	<input type="hidden" name="orderId" value="${item.id }" />
                <!-- 폼 영역 -->
                <div class="popBoard">
                   	<table>
                       	<caption> </caption>
                        <col style="width:60px;" />
                        <col style="width:100px;" />
                        <col style="width:60px;" />
                        <col style="width:100px;" />

                        <tbody>
                        	<tr>
                        		<th>내부 BuyOff 날짜</th>
                        		<td>
                                	<input type="text" name="expectBuyoffInDate" id="expectBuyoffInDate" value="" class="w100 AutoDatePicker">
                        		</td>
                        		<th>고객사 BuyOff 날짜</th>
                        		<td>
                                	<input type="text" name="expectBuyoffOutDate" id="expectBuyoffOutDate" value="" class="w100 AutoDatePicker">
                        		</td>
                        	</tr>
                        	<tr>
                        		<th>출고 날짜</th>
                        		<td>
                        			<input type="text" name="expectShippingDate" id="expectShippingDate" value="" class="w100 AutoDatePicker">
                        		</td>
                        		<th>셋업 완료 날짜</th>
                        		<td>
                        			<input type="text" name="expectFinishDate" id="expectFinishDate" value="" class="w100 AutoDatePicker">
                        		</td>
                        	</tr>
                        	<tr>
                        		<th colspan="4">동행 출장자 등록</th>
                        	</tr>
                        	<tr>
                        		<th>메인 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="managerId" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        		<th>조립 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="assemble1Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="assemble2Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="assemble3Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        	</tr>
                        	<tr>
                        		<th>PGM 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/pgmuser">
	                               	<c:param value="pgm1Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                                <c:import url="/internal/util/select/pgmuser">
	                               	<c:param value="pgm2Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                                <c:import url="/internal/util/select/pgmuser">
	                               	<c:param value="pgm3Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        		<th>영업 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/business">
	                               	<c:param value="business1Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/business">
	                               	<c:param value="business2Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/business">
	                               	<c:param value="business3Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        	</tr>
                        	<tr>
                        		<th>배선 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="wiring1Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="wiring2Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                                <c:import url="/internal/util/select/assembleuser">
	                               	<c:param value="wiring3Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        		<th>로봇 담당자</th>
                        		<td>
                                <c:import url="/internal/util/select/robotuser">
	                               	<c:param value="robot1Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/robotuser">
	                               	<c:param value="robot2Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import><br>
                                <c:import url="/internal/util/select/robotuser">
	                               	<c:param value="robot3Id" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                
                <!-- 팝업 버튼 -->
                <div class="popBtn">
                    <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                    <a id="SaveButton" class="btn_blue">확인</a>
                </div>
                <!-- //팝업 버튼 -->
                <!-- //폼 영역 -->
            	</form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->

	</div>
    
<!-- 멀티셀렉트 -->
<script src='js/multiselect03.js'></script>
<script src='js/multiselect02.js'></script>
<script src="js/multiselect01.js"></script>  
<!-- //멀티셀렉트 -->
</div>

	<script type="text/javascript">

	var standardDatePickerInitParam = {
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true,
			minDate: 0,
			nextText : "다음달",
			prevText : "이전달",
			closeText : "닫기",
			currentText : "오늘",
			numberOfMonths : [1,1],
			dateFormat : "yy-mm-dd",
			dayNames : ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
			dayNamesMin : ["월", "화","수","목","금","토", "일"],
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
		};
	
		function dateCompare(day1, day2){
			if (day1>day2)
				return 1;
			if (day1<day2)
				return -1;
			return 0;
		}

		$(document).ready(function(){
			$(".AutoDatePicker").prop("readonly", true);	
			$(".AutoDatePicker").datepicker( standardDatePickerInitParam );
			
			$("#buyoffIn").click(function(e){
				//$(this).prop("readonly", true);
				//$(this).datepicker( standardDatePickerInitParam );
			});

			$("#SaveButton").click(function(e){
				e.preventDefault();
				var userCount;
				userCount = 0;

				var orderId = $("#orderId").val();

				var buyoffIn = $("#expectBuyoffInDate").val();
				var buyoffOut = $("#expectBuyoffOutDate").val();
				var shipping = $("#expectShippingDate").val();
				var finish = $("#expectFinishDate").val();
				
				var mainuser = $("#managerId").val();
				
				var assembleUser1 = $("#assemble1Id").val();
				var assembleUser2 = $("#assemble2Id").val();
				var assembleUser3 = $("#assemble3Id").val();
				
				var pgmUser1 = $("#pgm1Id").val();
				var pgmUser2 = $("#pgm2Id").val();
				var pgmUser3 = $("#pgm3Id").val();
				
				var wiringUser1 = $("#wiring1Id").val();
				var wiringUser2 = $("#wiring2Id").val();
				var wiringUser3 = $("#wiring3Id").val();
				
				var robotUser1 = $("#robot1Id").val();
				var robotUser2 = $("#robot2Id").val();
				var robotUser3 = $("#robot3Id").val();
				
				var businessUser1 = $("#business1Id").val();
				var businessUser2 = $("#business2Id").val();
				var businessUser3 = $("#business3Id").val();
				
				if (orderId==""){
					alert("데이터 전달 오류. 페이지를 새로 고친 후 다시 실행하시기 바랍니다.");
					return;
				}
				if (mainuser==""){
					alert("메인 담당자를 지정해 주시기 바랍니다.");
					return;
				}

				if (buyoffIn==""){
					alert("내부 Buyoff날짜를 반드시 입력하셔야 합니다.");
					return;
				}
				if (buyoffOut==""){
					alert("고객사 Buyoff날짜를 반드시 입력하셔야 합니다.");
					return;
				}
				if (shipping==""){
					alert("설비 출고 날짜를 반드시 입력하셔야 합니다.");
					return;
				}
				if (finish==""){
					alert("Setup완료 날짜를 반드시 입력하셔야 합니다.");
					return;
				}
				if (dateCompare(buyoffIn, buyoffOut)>0) {
					alert("고객사 Buyoff가 내부 Buyoff 이전에 실시 할 수는 없습니다.");
					return;
				}
				if (dateCompare(buyoffOut, shipping)>0) {
					alert("설비 출고를 고객사 Buyoff 이전에 할 수는 없습니다.");
					return;
				}
				if (dateCompare(shipping, finish)>0) {
					alert("Setup완료 날짜 설정을 설비 출고 이전에 할 수는 없습니다.");
					return;
				}
				
				if (assembleUser1!=""){
					userCount++;
				} else {
					if (assembleUser2!=""){
						alert("첫번째 조립 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
					if (assembleUser3!=""){
						alert("첫번째 조립 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (assembleUser2==""){
					if (assembleUser3!=""){
						alert("두번째 조립 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}

				if (pgmUser1!=""){
					userCount++;
				} else {
					if (pgmUser2!=""){
						alert("첫번째 프로그램 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
					if (pgmUser3!=""){
						alert("첫번째 프로그램 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (pgmUser2==""){
					if (pgmUser3!=""){
						alert("두번째 프로그램 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}

				if (wiringUser1!=""){
					userCount++;
				} else {
					if (wiringUser2!=""){
						alert("첫번째 배선 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
					if (wiringUser3!=""){
						alert("첫번째 배선 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (wiringUser2==""){
					if (wiringUser3!=""){
						alert("두번째 배선 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				
				if (robotUser1!=""){
					userCount++;
				} else {
					if (robotUser2!=""){
						alert("첫번째 로봇 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
					if (robotUser3!=""){
						alert("첫번째 로봇 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (robotUser2==""){
					if (robotUser3!=""){
						alert("두번째 로봇 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				
				if (businessUser1!=""){
					userCount++;
				} else {
					if (businessUser2!=""){
						alert("첫번째 영업 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
					if (businessUser3!=""){
						alert("첫번째 영업 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (businessUser2==""){
					if (businessUser3!=""){
						alert("두번째 영업 출장자를 설정 후 설정하시기 바랍니다.");
						return;
					}
				}
				if (userCount<1){
					alert("등록된 출장자가 없습니다.");
					return;
				}

				var param = $("form[name=submitForm]").serialize();

				alert( "submitForm 호출" );
				$.ajax({   
					   type: "POST"  
					  ,url: "/qcPgm/assemble_finish"
					  ,data: param
					  ,success:function(data){
							alert( "조립 완료 처리 되었습니다." );
							
							window.close();
							opener.location.reload();
					  }
					  ,error:function(data){
						  alert("요청을 처리할 수 없습니다");
					  }
			  	});

			});			
		});

		$(document).on("focus", ".lazyAutoDatePicker", function(){

			$(this).prop("readonly", true);	
			$(this).datepicker( standardDatePickerInitParam );
			$(this).removeClass("lazyAutoDatePicker");

		});

	</script>

</body>
</html>
