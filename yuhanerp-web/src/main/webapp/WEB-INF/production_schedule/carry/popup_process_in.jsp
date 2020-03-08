<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
    <script type="text/javascript" src="/js/menu.js"></script>
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>선택도면 가공 입고 처리</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>선택도면 완품 입고 처리</h2>
                
                <div class="btn_area">
                	<span class="pr10">가공입고 후 후처리 발주, 후처리업체 : </span>
                	<span>
                    	<select class="w100">
                        	<option>기성</option>
                            <option>선택</option>
                            <option>선택</option>
                        </select>
                    </span>
                	<span>
                    	<input type="date" id="w140">
                    </span>
                	<span>
                    	<a href="#" class="btn_line_gray">납기일 일괄 적용</a>
                    </span>
                    <span>
                    	<a href="#" class="btn_line_gray">후처리발주처리</a>
                    </span>
                </div>

            </div>
            <!-- //팝업 서브타이틀 -->
            
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:100px;" />
                        <col style="width:100px;" />
                        <col style="width:80px;" />
                        <col style="width:80px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:70px;" />
                        <col style="width:100px;" />
                        <col style="width:120px;" />
                        <col style="width:80px;" />
                        <col style="width:100px;" />
                        </colgroup>
                        <tr>
                        	<th>도면번호</th>
                            <th>발주번호</th>
                            <th>설계자</th>
                            <th>Mat’l</th>
                            <th>열처리</th>
                            <th>수량</th>
                            <th>Spare</th>
                            <th>후처리</th>
                            <th>가공업체</th>
                            <th>가공납기일</th>
                            <th>후처리 결과</th>
                        </tr>
                        <tr>
                        	<td>50001-3001</td>
                            <td>D001-161030-001</td>
                            <td>남기홍</td>
                            <td>A6061</td>
                            <td>&nbsp;</td>
                            <td>10</td>
                            <td>2</td>
                            <td>Nickel</td>
                            <td>H테크</td>
                            <td>17.09.11</td>
                            <td>후처리 완료 처리</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 -->

            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray">취소</a>
                <a href="#" class="btn_blue">가공입고</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
