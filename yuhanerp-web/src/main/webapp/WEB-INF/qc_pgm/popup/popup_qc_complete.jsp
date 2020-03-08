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
        	<h1>검사항목 결과 등록
</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 설명 -->
            <div class="popText" >
            	<!-- 항목1 -->
            	검사 결과를 등록하시기 바랍니다.<br/>
                
                <!-- 항목2 -->
                <!--
                취소 사유를 입력하시오.
				-->
            </div>
            <!-- //설명 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 폼 영역 -->
                <div class="popBoard">
                   	<table>
                       	<caption> </caption>
                        <col style="width:80px;" />
                        <col style="width:80px;" />
                        <col style="width:80px;" />
                       	<thead>
                           	<tr>
	                           <th>판정</th>
	                           <th>합격수량</th>
	                           <th>불량수량</th>
                           </tr>
                        </thead>
                        <tbody>
                        	<tr>
                        		<td>
                        		<input type="hidden" id="totCnt" name="totCnt" value="${totCnt }" />
                             	<select name="resultOk" id="resultOk">
                             		<option value="PASS">PASS</option>
                                	<option value="NG">NG</option>
                                </select>
                        		</td>
                        		<td><input type="number" id="okCnt" name="okCnt" value="${totCnt }" min="1" max="3"></td>
                        		<td><input type="number" id="failCnt" name="failCnt" value="0" min="1" max="3"></td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                <!-- //폼 영역 -->
                
                <!-- 팝업 버튼 -->
                <div class="popBtn">
                    <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                    <a href="#" id="SaveButton" class="btn_blue">등록</a>
                </div>
                <!-- //팝업 버튼 -->
            
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
				
				var resultVal = $("#resultOk").val();
				var okVal = $("#okCnt").val();
				var failVal = $("#failCnt").val();
				var totCnt = "${totCnt}";

				if (Number(totCnt) > (Number(okVal)+Number(failVal))) {
					alert("합격+불량 수량이 전체 수량보다 작습니다.");
					return false;
				} else {
					opener.${callback}("${key}", "${dwkey}", resultVal, okVal, failVal );
					window.close();
				}
			});			
		});
	
	</script>

</body>
</html>
