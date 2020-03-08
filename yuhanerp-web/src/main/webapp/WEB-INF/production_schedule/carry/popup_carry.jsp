<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
	<script type="text/javascript" src="/js/ux.js"></script>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1> ${deptName }로 인계</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        	
             <!-- 설명 -->
            <div class="popText">
            	인계자 정보를 입력하시기 바랍니다.
            </div>
            <!-- //설명 -->

        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <form method="POST" action="/ps/carry/update/carry" id="SendForm">
                <input type="hidden" name="id" value="${id }" />
                <input type="hidden" name="dept" value="${dept }" />
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
                            <th>인계자</th>
                            <td>
                            	<c:import url="/internal/util/select/manager">
                            		<c:param value="from" name="controlName"/>
                            	</c:import>
                            </td>
                          
                        </tr>
                    </table>
                </div>
                </form>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="CloseWindowButton">취소</a>
                <a href="#" class="btn_blue" id="PostButton">확인</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#PostButton").click(function(e){
			
			e.preventDefault();
			$("#SendForm").ajaxSubmit({
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						alert("처리되었습니다");
						
						opener.location.reload();
						window.close();
					}
					else
						alert(obj.reason);
				},
				error : function(){
					alert("서버와 통신 오류가 발생하였습니다");
				}
			});
			
		});
		
	});
	
	</script>
</body>
</html>
    