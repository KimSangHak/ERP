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
        	<h1>구매 LIST 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>출도 내용</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
            
            <!-- 출도 내용 -->
            <div class="planArea">
                <!-- 리스트 텍스트 -->
                <div class="planCont">
                    엑셀영역
                </div>
                <!-- 리스트 텍스트 -->
                
                <!-- 버튼영역 -->
                <div class="btn_area"><a href="#" class="btn_gray">다시작성</a> <a href="#" class="btn_blue">리스트 전송</a></div>
                <!-- //버튼영역 -->
            </div>
            <!-- //출도 내용 -->
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>작업 발주(지시) 메일 발송</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:13%;" />
                        <col style="width:5%;" />
                        <col style="width:12%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:10%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:7%;" />
                        </colgroup>
                        <tr>
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
                            <th>도면파일</th>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><a href="javascript:PopWin('00_pop_file.html','600','240','no');" class="btn_line_gray">등록</a></td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <div class="progress">
            	<p class="blue">출도 달성 률은 현재 진행중인 Order에 해당되는 도면 출도 완료까지 몇 % 달성되었는지 입력하면 됩니다.</p>
                <p>
                	<span>출도 달성률</span>
                    <span><input type="text" class="w100"> %</span>
                </p>
            </div>
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="javascript:PopWin('00_pop_ok.html','400','200','no');" class="btn_gray">취소</a>
                <a href="#" class="btn_blue">도면출도 등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
