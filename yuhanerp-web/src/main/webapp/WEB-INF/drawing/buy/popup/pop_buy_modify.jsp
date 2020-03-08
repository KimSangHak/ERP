<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

    <script language="JavaScript" src="js/menu.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>구매 LIST 수정</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>구매 LIST</h2>
                <div class="btn_area">
                	<span class="pr10"><label><input type="checkbox" id="w27">발주리스트와 함께</label></span>
                    <span><a href="#" class="btn_line_gray">삭제</a></span>
                    <span><a href="#" class="btn_line_gray">수정</a></span>
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
                        <col style="width:70px;" />
                        <col style="width:50px;" />
                        <col style="width:40px;" />
                        <col style="width:120px;" />
                        <col style="width:160px;" />
                        <col style="width:100px;" />
                        <col style="width:160px;" />
                        <col style="width:50px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        <col style="width:60px;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>등록일</th>
                                <th>Order No.</th>
                                <th>No.</th>
                                <th>Description</th>
                                <th>Model No./Size</th>
                                <th>Maker</th>
                                <th>거래처</th>
                                <th>수량</th>
                                <th>Spare</th>
                                <th>Code</th>
                                <th>Comment</th>
                                <th>Remark</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                                <td>2016.09.01</td>
                                <td>70001</td>
                                <td>10</td>
                                <td>Cylinder</td>
                                <td><input type="text" id="w100"> <a href="javascript:PopWin('02_plan04_pop02.html','600','410','no');" class="btn_search">검색</a></td>
                                <td><input type="text"></td>
                                <td><input type="text" id="w100"> <a href="javascript:PopWin('02_plan04_pop03.html','600','410','no');" class="btn_search">검색</a></td>
                                <td><input type="text" value="1"></td>
                                <td><input type="text"></td>
                                <td><input type="text"></td>
                                <td><input type="text"></td>
                                <td><input type="text"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- //게시판 -->
            
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>발주 LIST</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
            
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:70px;" />
                        <col style="width:200px;" />
                        <col style="width:70px;" />
                        <col style="width:120px;" />
                        <col style="width:120px;" />
                        <col style="width:80px;" />
                        <col style="width:50px;" />
                        <col style="width:50px;" />
                        <col style="width:80px;" />
                        <col style="width:70px;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>Device</th>
                            <th>구매<br/>등록일</th>
                            <th>Description</th>
                            <th>Model/Size</th>
                            <th>Maker</th>
                            <th>단가</th>
                            <th>수량</th>
                            <th>재고수량</th>
                            <th>공급가액</th>
                            <th>납기일</th>
                        </tr>
                        <tr>
                        	<td>70001</td>
                            <td>SI2692 PCB Bending System</td>
                            <td>2016.09.01</td>
                            <td>Cylinder</td>
                            <td>MXQ20-50BS-A93</td>
                            <td>SMC</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
