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
        	<h1>${item.orderFullNo } ${item.device } 설계 진행 상태변경
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
                <div class="popBoard">
                   	<table>
                       	<caption> </caption>
                        <col style="width:60px;" />
                        <col style="width:180px;" />
                       	<!--<thead>
                           	<tr>
	                           <th>상태변경</th>
	                           <th>사유</th>
                           </tr>
                        </thead>-->
                        <tbody>
                        	<c:if test="${item.assemblyStatus eq 'R'}">
                        	<tr>
                        		<th>설계담당</th>
                        		<td>
                                <c:import url="/internal/util/select/design">
	                               	<c:param value="setuser" name="controlName"/>
	                               	<c:param value="true" name="showUnspecifiedItem"/>
	                               	<c:param value="w100" name="cssClass" />
                                </c:import>
                        		</td>
                        	</tr>
                       		</c:if>
                        	<tr>
                        		<th>상태변경</th>
                        		<td>
									<select  id="status" name="status">
										<option value="I" <c:if test="${item.assemblyStatus eq 'I'}">selected</c:if>>작업시작</option>
										<option value="F" <c:if test="${item.assemblyStatus eq 'F'}">selected</c:if>>작업완료</option>
										<option value="H" <c:if test="${item.assemblyStatus eq 'H'}">selected</c:if>>홀딩</option>
										<option value="S" <c:if test="${item.assemblyStatus eq 'S'}">selected</c:if>>중지</option>
									</select>
                        		</td>
                        	</tr>
                        	<tr>
                        		<th>사유</th>
                        		<td>
									<textarea id="cancelReasonText" rows="3"></textarea>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                <!-- //폼 영역 -->
                
                <!-- 팝업 버튼 -->
                <div class="popBtn">
                    <a href="#" id="CloseWindowButton" class="btn_gray">취소</a>
                    <a href="#" id="SaveButton" class="btn_blue">확인</a>
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
				e.preventDefault();

				var text = $("#cancelReasonText").val();
				var atype = $("#status").val();
				var setUser = $("#setuser").val();
				if (atype=='H' || atype=='S') {
					if (text==null || text=='') {
						alert("중지/홀딩 사유를 입력하세요!");
						return;
					}
				}
				
				if (setUser==""){
					alert("설계 담당자를 설정하시기 바랍니다.");
					return;
				} else {
					opener.${callback}("${key}", atype, setUser, text, "상태변경이 완료되었습니다." );
					window.close();
				}
			});			
		});
	
	</script>

</body>
</html>
