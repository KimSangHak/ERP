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
                        <li>구매 거래처 관리</li>
                    </ul>
                    <h2>구매 거래처 관리</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- sort -->
                    	<div class="sort">
                            <ul class="sortLeft">
                            	<li>
                                	<select class="w100">
                                    	<option>정렬기준</option>
                                        <option>업체명</option>
                                        <option>업체 코드</option>
                                        <option>업체 종류</option>
                                    </select>
                                </li>
                            </ul>
                            <ul class="sortRight">
                            	<li><a href="javascript:PopWin('02_plan05_pop01.html','600','700','no');" class="btn_line_gray">거래처 등록</a></li>
                            </ul>
                        </div>
                        <!-- //sort -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:5%;" />
								<col style="width:5%;" />
                                <col style="width:10%;" />
                                <col style="width:30%;" />
                                <col style="width: ;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>No</th>
                                        <th>업체 코드</th>
                                        <th>업체명</th>
                                    	<th>계산서업체명</th>
                                        <th>결제 기준</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<tr>
                                    	<td rowspan="2">1</td>
                                        <td rowspan="2">10</td>
                                        <td rowspan="2">캠시스</td>
                                        <td><a href="javascript:PopWin('01_business06_pop02.html','600','380','no');">주식회사 캠시스</a></td>
                                        <td>작성일로부터 2개월 뒤 말일</td>
                                    </tr>
                                    <tr>
                                    	<td><a href="javascript:PopWin('01_business06_pop02.html','600','380','no');">주식회사 알오씨(ROC)</a></td>
                                        <td>작성일로부터 2개월 뒤 말일</td>
                                    </tr>
                                    <tr>
                                    	<td>2</td>
                                        <td>11</td>
                                        <td>한강전자</td>
                                        <td><a href="javascript:PopWin('01_business06_pop02.html','600','380','no');">(주)한강전자</a></td>
                                        <td>작성일로부터 3개월 뒤 말일</td>
                                    </tr>
                                    <tr>
                                    	<td>3</td>
                                        <td>12</td>
                                        <td>재영솔루텍</td>
                                        <td><a href="javascript:PopWin('01_business06_pop02.html','600','380','no');">재영솔루텍(주)</a></td>
                                        <td>작성일로부터 3개월 뒤 말일</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
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
                        
                        <!-- 페이징 -->
                        <div class="listbtnArea">
                        	<div class="paging">
                                <a href="#" title="First" class="first">&nbsp;</a>
                                <a href="#" title="Prev" class="prev">&nbsp;</a>
                                <span>1</span>
                                <a href="#" title="2">2</a>
                                <a href="#" title="3">3</a>
                                <a href="#" title="4">4</a>
                                <a href="#" title="Next" class="next">&nbsp;</a>
                                <a href="#" title="Last" class="last">&nbsp;</a>
                            </div>
                        </div>
                        <!-- //페이징 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->

                
	</layout:put>
	
</layout:extends>