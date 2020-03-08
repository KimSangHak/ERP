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
                        <li>발주 LIST 등록</li>
                    </ul>
                    <h2>발주 LIST 등록</h2>
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
                                        <label><input type="radio" name="aa" checked />전체</label>
                                        <label><input type="radio" name="aa" />업체</label>
                                    </td>
                                    <th>Order No.</th>
                                    <td>
                                        <input type="text" name="" value=" " class="w130"> - <input type="text" name="" value=" " class="w130">
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
                                <col style="width:10%;" />
                                <col style="width:20%;" />
                                <col style="width: ;" />
                                <col style="width:20%;" />

                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>No.</th>
                                        <th>Order No.</th>
                                        <th>거래처</th>
                                        <th>개수</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<tr>
                                    	<td>3</td>
                                    	<td>70001-10</td>
                                        <td><a href="javascript:PopWin('02_plan06_pop01.html','1000','450','no');">태광뉴매틱</a></td>
                                        <td>5</td>
                                    </tr>
                                    <tr>
                                    	<td>2</td>
                                    	<td>70001-10</td>
                                        <td><a href="javascript:PopWin('02_plan06_pop01.html','1000','450','no');">MISUMI</a></td>
                                        <td>8</td>
                                    </tr>
                                    <tr>
                                    	<td>1</td>
                                    	<td>70001-20</td>
                                        <td><a href="javascript:PopWin('02_plan06_pop01.html','1000','450','no');">태광뉴매틱</a></td>
                                        <td>4</td>
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