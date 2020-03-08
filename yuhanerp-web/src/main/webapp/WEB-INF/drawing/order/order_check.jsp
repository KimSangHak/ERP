<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/DrawingMaster.jsp">

	<layout:put block="mainbody">
 <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>구매 발주</li>
                        <li>발주 LIST 조회</li>
                    </ul>
                    <h2>발주 LIST 조회</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:12%;;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                    <th>검색유형</th>
                                    <td>
                                        <label><input type="radio" name="aa" checked />전체</label>
                                        <label><input type="radio" name="aa" />업체</label>
                                    </td>
                                    <th>Order No.</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w280">
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w140"> - <input type="date" name="" value=" " class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:90px;" />
                                <col style="width:150px;" />
                                <col style="width:90px;" />
                                <col style="width:90px;" />
                                <col style="width:90px;" />
                                <col style="width:150px;" />
                                <col style="width:150px;" />
                                <col style="width:70px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
                                <col style="width:50px;" />
								<col style="width:50px;" />
                                <col style="width:50px;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order No.</th>
                                        <th>Device</th>
                                        <th>업체</th>
                                        <th>발주일</th>
                                        <th>Description</th>
                                        <th>Model/Size</th>
                                        <th>Maker</th>
                                        <th>단가</th>
                                        <th>수량</th>
                                        <th>공급가액</th>
                                        <th>납기일</th>
                                        <th>입고수량</th>
                                        <th>입고일자</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<tr>
                                    	<td>700010</td>
                                        <td>SI2692 PCB Bending System</td>
                                        <td>현준FA</td>
                                        <td>2016.09.01</td>
                                        <td>Servo Motor</td>
                                        <td id="taLeft"><a href="javascript:PopWin('02_plan07_pop01.html','1000','450','no');">HG-KR13</a></td>
                                        <td>MITSUBISHI</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>700010</td>
                                        <td>SI2692 PCB Bending System</td>
                                        <td>현준FA</td>
                                        <td>2016.09.01</td>
                                        <td>Servo Motor</td>
                                        <td id="taLeft"><a href="javascript:PopWin('02_plan07_pop01.html','1000','450','no');">MR-J3ENSCBL1M-A1-L</a></td>
                                        <td>MITSUBISHI</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
                
            </div>
        </div>
        <!-- 서브콘텐츠 -->
	</layout:put>
	
</layout:extends>