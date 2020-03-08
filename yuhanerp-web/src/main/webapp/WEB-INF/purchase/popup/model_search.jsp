<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	// Model No, Size 조회 팝업
	// 디자인 파일명 : 02_plan04_pop02.html
%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script type="text/javascript" src="/js/menu.js"></script>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    
    <!-- 멀티셀렉트 -->
    <link rel="stylesheet" type="text/css" href="/css/multi.css">
	<!-- //멀티셀렉트 -->
    <!-- 트리메뉴 -->
    <script type="text/javascript" src="/js/checktree.js"></script>
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
        	<h1>Model No./Size 조회</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 회원찾기 -->
            <div class="member">
                
                <form method="GET">
                <div class="moSearch">
                	<ul>
                        <li>
                        	<select class="w120" name="category" id="category">
                        		<option value="" selected="selected">전체</option>
                                <option value="model">Model</option>
                                <option value="maker">Maker</option>
                                <option value="provider">거래처</option>
                            </select>
                        </li>
                        <li class="w310">
                            <input type="text" value="${keyword }" id="keyword" name="keyword">
                        </li>
                        <li><a href="#" class="btn_blue SubmitButton">검색</a></li>
                    </ul>
                </div>
				</form>
            </div>
            <!-- //회원찾기 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width: ;" />
                        <col style="width:150px;" />
                        <col style="width:150px;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>Model No./Size</th>
                                <th>Maker</th>
                                <th>거래처</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        	<c:forEach items="${data }" var="item">
                            <tr>
                                <td><a href="#" class="ModelSelector" data-model="${item.modelNo }" data-maker="${item.maker }" data-provider="${item.provider }" >${item.modelNo }</a></td>
                                <td>${item.maker }</td>
                                <td>${item.provider }</td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- //게시판 -->
            
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

	<script type="text/javascript">
	
		var initialCategory = "${category}";
		$(document).ready( function(){

			if(initialCategory != "")
				$("#category").val("${category}");			
			
			// 해당 모델 선택
			$(".ModelSelector").click(function(e){
				e.preventDefault();
				
				var model = $(this).attr("data-model");
				var provider = $(this).attr("data-provider");
				var maker = $(this).attr("data-maker");
				
				if(window.opener === undefined)
					alert("팝업창이 닫혔습니다");
				
				if(window.opener.afterModelSelected === undefined)
					alert("팝업창에 데이터를 전송할 수 없습니다");
				
				window.opener.afterModelSelected(model, maker, provider);
				window.close();
			});
			
			$(".SubmitButton").click(function(e){
				e.preventDefault();
				
				$("form").submit();
			});
		});			
	</script>

</div>
</body>
</html>
