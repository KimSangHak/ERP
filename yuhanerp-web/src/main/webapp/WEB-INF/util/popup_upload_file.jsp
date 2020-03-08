<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 파일 업로드하는 view
	// 업로드 된 파일은 FileLinkController 로 던져짐
	// obj.data 에는 파일 번호 (registered_file 테이블에 등록 번호)
	
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/"><!-- 원본 html 유지 -->
	
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">
	
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/jquery.form.min.js"></script>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>파일 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popCont">

                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:80%;" />
                        </colgroup>
                        <tr>
                            <th>파일 등록</th>
                            <td>
                            	<form id="FileUploadForm" method="POST" action="/upload/file" enctype="multipart/form-data">
                            	<input type="file" name="A" value="">
                            	<input type="hidden" name="fileFieldName" value="A" />
                            	<input type="hidden" name="fileCategory" value="${fileCategory }" />
                            	<input type="hidden" name="section" value="${section }" />
                            	</form>                            	
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popDes">파일용량을 줄여서 다시 등록해주세요</div>
            <div class="popBtn">
                <a href="#" class="btn_gray CloseButton">취소</a>
                <a href="#" class="btn_blue UploadButton">확인</a>
            </div>
            
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$(".CloseButton").click(function(e){
			window.close();
		});
		
		$(".UploadButton").click(function(e){
			e.preventDefault();
			
			
			$("#FileUploadForm").ajaxSubmit({
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK"){
						
						window.close();
						window.opener.${openerFunctionName}(obj.data
						<c:if test="${parameter != null}">
															, ${parameter}
						</c:if>
																);
						
					}else{
						alert(obj.reason);
					}
				},
				error : function(){
					alert("파일을 전송할 수 없습니다");
				}
			});
		});
		
	});
	
	</script>
</div>
</body>
</html>
