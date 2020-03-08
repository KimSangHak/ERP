<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	// 전체 일괄 등록
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">	
	<base href="/" />
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>전체 일괄 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<div class="popText">
                선택된 모든 리스트에 일괄 적용됩니다.
            </div>
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

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
                            <th>가공종류</th>
                            <td>
                            	<c:import url="/internal/util/select/process">
                            		<c:param value="" name="defaultValue"/>
                            		<c:param value="workPosition" name="controlName"/>
                            	</c:import>
                            </td>
                            <th>발주 업체</th>
                            <td><input type="text" id="partnerName" readonly="readonly"><input type="hidden" id="partnerId" /></td>
                        </tr>
                        <tr>
                            <th>가공완료일</th>
                            <td><input type="text" class="AutoDatePicker" id="finishDate" readonly></td>
                            <th>후처리완료일</th>
                            <td><input type="text" id="postprocessingDate" value="" class="AutoDatePicker" readonly></td>
                        </tr>
                        <tr>
                            <th>검사완료일</th>
                            <td><input type="text" name="inspectDate" id="inspectDate" value="" class="AutoDatePicker" readonly></td>
                            <th>&nbsp;</th>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" id="CloseButton" class="btn_gray">취소</a>
                <a href="#" id="RegButton" class="btn_blue">등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
     
	</div>
	
	<script type="text/javascript">
	
		function afterBuyPartnerSelected(id, name){
			$("input#partnerName").val(name);
			$("input#partnerId").val(id);
			
			//$("#finishDate").focus();	// 다른곳으로 포커스 이동
		}
	
		var lastFocus = "";
		$(document).ready(function(){
			
			$("#CloseButton").click(function(e){
				e.preventDefault();
				
				window.close();
			});
			
			$("#RegButton").click(function(e){
				e.preventDefault();
				
				var workPosition = $("#workPosition").val();
				var partnerName = $("#partnerName").val();
				var partnerId = $("#partnerId").val();
				var finishDate = $("#finishDate").val();
				var postprocessingDate = $("#postprocessingDate").val();				
				var inspectDate = $("#inspectDate").val();				
				
				opener.${callback}(workPosition, partnerName, partnerId, finishDate, postprocessingDate, inspectDate);
				window.close();
			});
			
			$("input").focusout(function(){
				lastFocus = $(this).attr("id");
				//console.log(lastFocus);
			});
			
			$("input#partnerName").focus(function(e){
				if(lastFocus == $(this).attr("id"))
					return;
				
				PopWin('/popup/buy_partner_list?callback=afterBuyPartnerSelected','600','410','no');				
			});
		});
	
	</script>
</div>
</body>
</html>
