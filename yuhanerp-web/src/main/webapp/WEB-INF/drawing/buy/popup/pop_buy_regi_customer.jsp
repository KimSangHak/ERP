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
        	<h1>거래처 등록(도면 관리)</h1>
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
                        
                        <tr>
                            <th>업체코드</th>
                            <td><input type="text" name="" value=" " id="w120"> <a href="#" class="btn_line_gray">업체코드 자동생성</a></td>
                        </tr>
                        <tr>
                            <th>업체명 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="" value="에스트라"></td>
                        </tr>
                        <tr>
                            <th>업체 종류 <span class="ncBlue">*</span></th>
                            <td>
                                <select id="w260">
                                    <option>선택</option>
                                    <option>전자 </option>
                                    <option>기계</option>
                                    <option>공업</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>사업자 번호</th>
                            <td><input type="text" name="" value=" " id="w80"> - <input type="text" name="" value=" " id="w80"> - <input type="text" name="" value=" " id="w80"></td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td>
                            	<p><input type="text" value=""></p>
                                <p class="mt5"><input type="text" value=""></p>
                            </td>
                        </tr>
                        <tr>
                            <th>담당자 연락처 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="" value=" " id="w80"> - <input type="text" name="" value=" " id="w80"> - <input type="text" name="" value=" " id="w80"></td>
                        </tr>
                        <tr>
                            <th>담당자 성함/직급 <span class="ncBlue">*</span></th>
                            <td><input type="text" value="" id="w150"> <input type="text" value="" id="w150"></td>
                        </tr>
                        <tr>
                            <th>담당자 메일 <span class="ncBlue">*</span></th>
                            <td><input type="text" value=""></td>
                        </tr>
                        <tr>
                            <th>회사 연락처</th>
                            <td><input type="text" value=""></td>
                        </tr>
                        <tr>
                            <th>회사 메일</th>
                            <td><input type="text" value=""></td>
                        </tr>
                        <tr>
                            <th>결제 기준</th>
                            <td>
                            	<p class="staDay">
                                	<span>작성일로부터 몇개월 뒤</span>
                                    <span>
                                    	<select id="w120">
                                            <option>당월</option>
                                            <option>1개월</option>
                                            <option>2개월</option>
                                            <option>3개월</option>
                                            <option>4개월</option>
                                            <option>5개월</option>
                                            <option>즉시결제</option>
                                        </select>
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>선택한 개월 뒤 날짜</span>
                                    <span>
                                    	<select id="w120">
                                            <option>말일</option>
                                            <option>1일</option>
                                            <option>5일</option>
                                            <option>10일</option>
                                            <option>15일</option>
                                            <option>20일</option>
                                        </select>
                                    </span>
                                </p>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray">취소</a>
                <a href="#" class="btn_blue">등록확인</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
