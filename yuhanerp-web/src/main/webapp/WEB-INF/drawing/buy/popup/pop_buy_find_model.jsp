<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">

    <script language="JavaScript" src="js/menu.js"></script>
    
    <!-- 멀티셀렉트 -->
    <link rel="stylesheet" type="text/css" href="css/multi.css">
	<!-- //멀티셀렉트 -->
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
        	<h1>Model No./Size 조회</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 회원찾기 -->
            <div class="member">
                
                <div class="moSearch">
                	<!-- 부서별 검색일경우 -->
                	<ul>
                        <li>
                        	<select class="w120">
                                <option>Model</option>
                                <option>Maker</option>
                                <option>거래처</option>
                            </select>
                        </li>
                        <li class="w310">
                            <input type="text" value="">
                        </li>
                        <li><a href="#" class="btn_blue">검색</a></li>
                    </ul>
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
                            <tr>
                                <td>MXQ2-50BS-A93</td>
                                <td>SMC</td>
                                <td>태광뉴매틱</td>
                            </tr>
                            <tr>
                                <td>MXQ12L-20B-M9BV</td>
                                <td>SMC</td>
                                <td>태광뉴매틱</td>
                            </tr>
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
</body>
</html>
