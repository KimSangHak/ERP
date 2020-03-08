<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	// 거래처 조회
	// 디자인 파일명 : 02_plan04_pop03.html
%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
    
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
        	<h1>거래처 조회</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 회원찾기 -->
            <div class="member">
                
                <div class="moSearch">
                	<!-- 부서별 검색일경우 -->
                	<form>
                	<ul>
                        <li>
                        	<select class="w120" name="category">
                                <option selected="selected" value="">전체</option>
                            </select>
                        </li>
                        <li class="w310">
                            <input type="text" name="keyword" value="${keyword }">
                        </li>
                        <li><a href="#" class="btn_blue" id="SubmitButton">검색</a></li>
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
                        <col style="width: ;" />
                        <col style="width:150px;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>거래처</th>
                                <th>업체종류</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        	<c:forEach items="${data }" var="entry">
                            <tr>
                                <td><a href="#" class="partnerEntry" data-id="${entry.id }" >${entry.partnerName }</a></td>
                                <td>&nbsp;</td>
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
<script src='/js/multiselect03.js'></script>
<script src='/js/multiselect02.js'></script>
<script src="/js/multiselect01.js"></script>  
<!-- //멀티셀렉트 -->
</div>

	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$(".partnerEntry").click(function(e){
			
			var id = $(this).attr("data-id");
			var name = $(this).text();
			
			if(window.opener === undefined || window.opener.afterPartnerSelected === undefined){
				alert("데이터를 전달 할 수 없습니다");
				return;
			}
			
			window.opener.afterPartnerSelected(id, name);
			window.close();
			
		});
		
	});
	
	
	
	</script>

</body>
</html>
