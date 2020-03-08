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
    
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">
    <link rel="stylesheet" type="text/css" href="css/multi.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
    <script type="text/javascript" src="/js/ux.js"></script>
    <!-- 트리메뉴 -->
    <script type="text/javascript" src="js/checktree.js"></script>
    <script type="text/javascript">
		var checkmenu = new CheckTree('checkmenu');
	</script>
    <!-- //트리메뉴 -->
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>${item.orderFullNo } ${item.device }<br> 프로그램 진행율 설정
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
                <!-- 폼 영역 -->
            	<form name="submitForm" id="submitForm" >
            	<input type="hidden" id="orderId" value="${item.id }" />
                <div class="popBoard">
                   	<table>
                       	<caption> </caption>
                        <col style="width:160px;" />
                        <col style="width:80px;" />
                        <tbody>
                        	<tr>
                        		<th>프로그램 진행율</th>
                        		<td>
									<input type="number" id="assambleProg" value="${item.programProgress}">
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                <!-- 팝업 버튼 -->
                <div class="popBtn">
                    <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                    <a href="#" id="SaveButton" class="btn_blue">확인</a>
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
	
		$(document).ready(function(){

			$("#SaveButton").click(function(e){
				e.preventDefault();

				var orderId = $("#orderId").val();

				var progVal = $("#assambleProg").val();

				if (orderId==""){
					alert("데이터 전달 오류. 페이지를 새로 고친 후 다시 실행하시기 바랍니다.");
					return;
				}

				if (progVal<1 || progVal>100){
					alert("1~100 사이의 숫자를 입력하세요.");
					return;
				}

				$.ajax({   
					   type: "POST"  
					  ,url: "/qcPgm/program_progset"
					  ,data: {
						  key:orderId,
						  progValue:progVal
						  }
					  ,success:function(data){
							alert( "프로그램 진행률 수정 처리 되었습니다." );
							
							window.close();
							opener.location.reload();
					  }
					  ,error:function(data){
						  alert("요청을 처리할 수 없습니다");
					  }
			  	});

			});
		});
	
	</script>

</body>
</html>
