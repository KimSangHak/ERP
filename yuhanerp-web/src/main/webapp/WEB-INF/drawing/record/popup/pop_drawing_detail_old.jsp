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
        	<h1>도면이력 상세보기</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <div class="btn_area">
                	<span>
                    	<select class="w100">
                        	<option>전체</option>
                            <option>Part1</option>
                            <option>Part2</option>
                        </select>
                    </span>
                	<span>
                    	<select class="w100">
                        	<option>분류</option>
                            <option>신규</option>
                            <option>추가제작</option>
                            <option>재제작</option>
                            <option>수정</option>
                            <option>구매</option>
                            <option>재료구매</option>
                            <option>도면교체</option>
                            <option>재출도</option>
                            <option>A/S제작</option>
                        </select>
                    </span>
                	<span>
                    	<select class="w100">
                        	<option>사유</option>
                            <option>설계불량</option>
                            <option>업체요구</option>
                            <option>가공불량</option>
                            <option>조립불량</option>
                            <option>공정불량</option>
                            <option>설계보완</option>
                            <option>가공보완</option>
                            <option>조립보완</option>
                            <option>기타</option>
                        </select>
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
                        <col style="width:7%;" />
                        <col style="width:10%;" />
                        <col style="width:15%;" />
                        <col style="width:10%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>업체</th>
                            <th>Device</th>
                            <th>Part or Unit</th>
                            <th>종류</th>
                            <th>납품일</th>
                            <th>영업담당</th>
                            <th>설계담당</th>
                            <th>출도일</th>
                            <th>도면수량</th>
                        </tr>
                        <tr>
                        	<td>70001</td>
                            <td>덕우전자</td>
                            <td id="taLeft">Full Auto D60 Width Inspection</td>
                            <td>Vision Bracket 수정</td>
                            <td>설비</td>
                            <td>16.09.01</td>
                            <td>박종선</td>
                            <td>전진표</td>
                            <td>16.08.05</td>
                            <td>100</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 --> 
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:10%;" />
                        <col style="width:10%;" />
                        <col style="width:12%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:7%;" />
                        <col style="width:10%;" />
                        </colgroup>
                        <tr>
                        	<th>도면번호</th>
                            <th>Description</th>
                            <th>Dimensions</th>
                            <th>Mat’l</th>
                            <th>열처리</th>
                            <th>수량</th>
                            <th>Spare</th>
                            <th>후처리</th>
                            <th>출도일</th>
                            <th>분류</th>
                            <th>사유</th>
                            <th>검사</th>
                            <th>비고</th>
                        </tr>
                        <tr>
                        	<td>70001-3001</td>
                            <td>Vacuum Tip</td>
                            <td>&nbsp;</td>
                            <td>A6061</td>
                            <td>&nbsp;</td>
                            <td>10</td>
                            <td>2</td>
                            <td>Nickel</td>
                            <td>16.08.05</td>
                            <td>신규</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td>70001-3002</td>
                            <td>Motor BK</td>
                            <td>&nbsp;</td>
                            <td>A6061</td>
                            <td>&nbsp;</td>
                            <td>1</td>
                            <td>&nbsp;</td>
                            <td>하드유광</td>
                            <td>16.08.05</td>
                            <td>신규</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
            </div>
            <!-- //테이블 콘텐츠 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:7%;" />
                        <col style="width:10%;" />
                        <col style="width:15%;" />
                        <col style="width:10%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>업체</th>
                            <th>Device</th>
                            <th>No.</th>
                            <th>종류</th>
                            <th>납품일</th>
                            <th>영업담당</th>
                            <th>설계담당</th>
                            <th>출도일</th>
                            <th>도면수량</th>
                        </tr>
                        <tr>
                        	<td>70001</td>
                            <td>덕우전자</td>
                            <td id="taLeft">Full Auto D60 Width Inspection</td>
                            <td>Vision Bracket 수정</td>
                            <td>설비</td>
                            <td>16.09.01</td>
                            <td>박종선</td>
                            <td>전진표</td>
                            <td>16.08.05</td>
                            <td>100</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
            </div>
            <!-- //테이블 콘텐츠 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:7%;" />
                        <col style="width:10%;" />
                        <col style="width:15%;" />
                        <col style="width:10%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>업체</th>
                            <th>Device</th>
                            <th>No.</th>
                            <th>종류</th>
                            <th>납품일</th>
                            <th>영업담당</th>
                            <th>설계담당</th>
                            <th>출도일</th>
                            <th>도면수량</th>
                        </tr>
                        <tr>
                        	<td>70001</td>
                            <td>덕우전자</td>
                            <td id="taLeft">Full Auto D60 Width Inspection</td>
                            <td>Vision Bracket 수정</td>
                            <td>설비</td>
                            <td>16.09.01</td>
                            <td>박종선</td>
                            <td>전진표</td>
                            <td>16.08.05</td>
                            <td>100</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray">취소</a>
                <a href="#" class="btn_blue">추가출도</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
