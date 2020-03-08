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
                        <li>도면 관리</li>
                        <li>구매 LIST 조회</li>
                    </ul>
                    <h2>구매 LIST 조회</h2>
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
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                    <th>검색유형</th>
                                    <td>
                                        <label><input type="checkbox" name="aa" checked />전체</label>
                                        <label><input type="checkbox" name="aa" />업체</label>
                                    </td>
                                    <th>Description</th>
                                    <td><input type="text" name="" value=" " class="w280"></td>
                                    <th>Model/Size</th>
                                    <td>
                                        <input type="text" name="" value=" " class="w280">
                                    </td>
                                </tr>
                                <tr>
                                    <th>Maker</th>
                                    <td><input type="text" name="" value=" " class="w280"></td>
                                    <th>거래처</th>
                                    <td><input type="text" name="" value=" " class="w280"></td>
                                    <th>No.</th>
                                    <td><input type="text" name="" value=" " class="w280"></td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>구매 LIST</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:7%;" />
                                <col style="width:15%;" />
                                <col style="width: ;" />
                                <col style="width:7%;" />
                                <col style="width:10%;" />
                                <col style="width:10%;" />
                                <col style="width:7%;" />
                                <col style="width:7%;" />
                                <col style="width:7%;" />
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
                                        <th>도면파일</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<tr>
                                    	<td>2016.09.01</td>
                                        <td>70001</td>
                                        <td>10</td>
                                        <td>Cylinder</td>
                                        <td><a href="javascript:PopWin('02_plan04_pop01.html','1000','450','no');">MXQ2-50BS-A93</a></td>
                                        <td>SMC</td>
                                        <td>태광뉴매틱</td>
                                        <td>1</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>2016.09.01</td>
                                        <td>70001</td>
                                        <td>10</td>
                                        <td>Stripper Bolt</td>
                                        <td><a href="javascript:PopWin('02_plan04_pop01.html','1000','450','no');">SNSB6-15</a></td>
                                        <td>MISUMI</td>
                                        <td>MISUMI</td>
                                        <td>4</td>
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
                            
	</layout:put>
	
</layout:extends>