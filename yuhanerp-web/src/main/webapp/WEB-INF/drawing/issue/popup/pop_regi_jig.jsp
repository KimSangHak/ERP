<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

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
    	
        <!-- íì íì´í -->
        <div class="popTitArea">
        	<h1>ì¹ê³µêµ¬ ì§í ëë©´ ì¶ë ë±ë¡</h1>
        </div>
        <!-- //íì íì´í -->
    
        <!-- íì ì½íì¸  -->
        <div class="popCont">
        
        	<!-- íì ìë¸íì´í -->
            <div class="pcTit">
                <h2>ì¶ë ë´ì©</h2>
                <div class="btn_area">
                	<span><input type="text" value="íì¶ê°" onfocus="this.className='on';this.value=''" id="w120"></span>
                	<span><a href="#" class="btn_plus">ì¶ê°</a></span>
                    <span><a href="#" class="btn_minus">ì­ì </a></span>
                </div>
            </div>
            <!-- //íì ìë¸íì´í -->
            
            <!-- ì¶ë ë´ì© -->
            <div class="planArea">
                <!-- ë¦¬ì¤í¸ íì¤í¸ -->
                <div class="planCont">
                    <textarea></textarea>
                </div>
                <!-- ë¦¬ì¤í¸ íì¤í¸ -->
                
                <!-- ë²í¼ìì­ -->
                <div class="btn_area"><a href="#" class="btn_gray">ë¤ììì±</a> <a href="#" class="btn_blue">ë¦¬ì¤í¸ ì ì¡</a></div>
                <!-- //ë²í¼ìì­ -->
            </div>
            <!-- //ì¶ë ë´ì© -->
            
            <!-- íì ìë¸íì´í -->
            <div class="pcTit">
                <h2>ìì ë°ì£¼(ì§ì) ë©ì¼ ë°ì¡</h2>
            </div>
            <!-- //íì ìë¸íì´í -->
                
        	<!-- íì´ë¸ ì½íì¸  -->
            <div class="popList">

                <!-- ê²ìí -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:12%;" />
                        <col style="width:13%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:10%;" />
                        <col style="width:7%;" />
                        <col style="width:13%;" />
                        </colgroup>
                        <tr>
                        	<th>ëë©´ë²í¸</th>
                            <th>ëë©´ë²í¸</th>
                            <th>Description</th>
                            <th>Dimensions</th>
                            <th>Matâl</th>
                            <th>ì´ì²ë¦¬</th>
                            <th>ìë</th>
                            <th>Spare</th>
                            <th>íì²ë¦¬</th>
                            <th>ë¶ë¥</th>
                            <th>ì¬ì </th>
                            <th>ê²ì¬</th>
                            <th>ë¹ê³ </th>
                        </tr>
                        <tr>
                        	<td>
                            	<select id="w80">
                                	<option>ì íì íì íì í</option>
                                    <option>ì í</option>
                                    <option>ì í</option>
                                </select>
                            </td>
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
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td>
                            	<select id="w80">
                                	<option>ì íì íì íì í</option>
                                    <option>ì í</option>
                                    <option>ì í</option>
                                </select>
                            </td>
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
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <!-- //ê²ìí -->

            </div>
            <!-- //íì´ë¸ ì½íì¸  -->
            
            <div class="progress">
            	<p class="blue">ì¶ë ë¬ì± ë¥ ì íì¬ ì§íì¤ì¸ Orderì í´ë¹ëë ëë©´ ì¶ë ìë£ê¹ì§ ëª % ë¬ì±ëìëì§ ìë ¥íë©´ ë©ëë¤.</p>
                <p>
                	<span>ì¶ë ë¬ì±ë¥ </span>
                    <span><input type="text" class="w100"> %</span>
                </p>
            </div>
            
            <!-- íì ë²í¼ -->
            <div class="popBtn">
                <a href="javascript:PopWin('00_pop_ok.html','400','200','no');" class="btn_gray">ì·¨ì</a>
                <a href="#" class="btn_blue">ëë©´ì¶ë ë±ë¡</a>
            </div>
            <!-- //íì ë²í¼ -->
            
        </div>
        <!-- //íì ì½íì¸  -->
        
        
        
	</div>
</div>
</body>
</html>
