<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        	<h1>거래처 조회</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 설명 -->
            <div class="popText">
            	조회된 리스트의 거래처를 클릭하시면 발주번호가<br/>
                발주 번호가 생성된 후 창이 닫힙니다.
            </div>
            <!-- //설명 -->
            
            <!-- 회원찾기 -->
            <div class="member">
                
                <div class="moSearch">
                	<!-- 부서별 검색일경우 -->
                	<form method="GET" >
                	<input type="hidden" name="callback" value="${callback }" />
                	<ul>
                        <li>
                        	<select class="w120" name="category">
                                <option value="name">거래처</option>
                                <option value="code">거래처 코드</option>
                            </select>
                        </li>
                        <li class="w310">
                            <input type="text" name="keyword" value="${keyword }">
                        </li>
                        <li><a href="#" class="btn_blue" id="FindButton">검색</a></li>
                    </ul>
                    </form>
                    <!-- //부서별 검색일경우 -->
                </div>

            </div>
            <!-- //회원찾기 -->
            
            <!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:150px;" />
                        <col style="width: ;" />
                        <col style="width:150px;" />
                        </colgroup>
                        <thead>
                            <tr>
                            	<th>업체코드</th>
                                <th>거래처</th>
                                <th>업체종류</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        	<c:forEach items="${data }" var="item">
                            <tr>
                            	<td>${item.id }</td>
                                <td><a href="#" class="partnerLink" data-id="${item.id }">${item.partnerName }</a></td>
                                <td>${item.buyType }</td>
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


</div>

	<script type="text/javascript">
		
		$(document).ready(function(){
			
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				$("form").submit();
			});
			
			$("a.partnerLink").click(function(e){
				e.preventDefault();
				
				var id = $(this).attr("data-id");
				var partnerName = $(this).text();
				
				opener.${callback}(id, partnerName);
				window.close();
			});
			
		});
	
	</script>

</body>
</html>
