<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script language="JavaScript" src="/js/menu.js"></script>
      <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script type="text/javascript">
	  $(document).ready(function(){
		  
		  $("#register").click(function(){
			   
		    
		    	var region = $("#region").val();
		    	var id = $("#id").val();
		    	var name = $("#name").val();
		    	
		    
		    	
		        if(confirm("거래처 등록을 하시겠습니까?")){
		        	document.form1.target="parent";
		        	document.form1.action = "/partner/popup/register";
		        	document.form1.submit();
		        	self.close();
		        }
		    });
		  
		  
		});	
	
	
	</script>

</head>

<body class="sub">
<div id="container">
<form id="form1" name="form1" enctype="multipart/form-data" method="post">
	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>매출처 등록(영업일보)</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:80%;" />
                        </colgroup>
                        <tr>
                            <th>업체 지역</th>
                           <td><input type="text" name="region" id="w120">
                        </tr>
                        <tr>
                            <th>업체코드</th>
                            <td><input type="text" name="id" id="w120">
                        </tr>
                        <tr>
                            <th>업체명</th>
                            <td><input type="text" name="name"></td>
                        </tr>
                       
                      
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="cancle">취소</a>
                <a href="#" class="btn_blue" id="register">등록확인</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
	</form>
</div>
</body>
</html>
