<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<style type="text/css">
	
	input:-webkit-autofill,
	input:-webkit-autofill:hover,
input:-webkit-autofill:active,
input:-webkit-autofill:focus {
    background-color: #1750a3 !important;
    -webkit-box-shadow: 0 0 0 1000px #1750a3 inset !important;
    -webkit-text-fill-color: #ffffff !important;
	}
	
	</style>
</head>

<body class="login" onkeydown="onKeyDown();">
<div id="container">

	<!-- BODY -->
	<div class="loginBody">
    	<div class="logCont">
    
            <!-- HEADER -->
            <div class="header">
                <h1><a href="#">로고</a></h1>
            </div>
            <!-- //HEADER -->
            
            <!-- CONTENTS -->
            <form method="POST" action="/login">
            <div class="contents">
            
                <ul class="lo_form">
                    <li><input type="text" name="userId" autocomplete="off" /></li>
                    <li><input type="password" name="password" autocomplete="off" /></li>
                </ul>
                        
                <div class="lo_check">
                    <span><input type="checkbox" id="keepId" checked />아이디 저장</span>
                    <span><input type="checkbox" id="keepLogin" />자동 로그인</span>
                </div>
                
                <div class="lo_btn_area"><a id="LoginButton" href="#">로그인</a></div>
                
                <div class="lo_txt">
                    <p>아이디와 비밀번호를 입력하여 주십시오</p>
                    <p>개인정보 보호를 위해 이용을 마치신 후 로그아웃하여 주십시오</p>
                </div>
            </div>
            </form>
            <!-- //CONTENTS -->
        
        </div>
    </div>
    <!-- //BODY -->
    
</div>
	<script type="text/javascript">
	
	function onKeyDown()
	{
	     if(event.keyCode == 13)
	     {
	    	 $("form").submit();
	     }
	}


	
	$(document).ready(function(){
		
		$("#LoginButton").click(function(e){
			e.preventDefault();
			
			$("form").submit();
		});
	});
	
	</script>
</body>

</html>
