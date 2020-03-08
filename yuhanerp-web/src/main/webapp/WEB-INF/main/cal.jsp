<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

                	<div class="titArea">
                        <span class="btn_prev"><a href="#" class="loadCalendar" data-year="${prevY }" data-month="${prevM }">이전</a></span>
                        <span class="month"><span>${title }</span></span>
                        <span class="btn_next"><a href="#" class="loadCalendar" data-year="${nextY }" data-month="${nextM }">다음</a></span>
                        <div class="btn_manage"><a href="#" title="스케쥴 관리">스케쥴 관리</a></div>
                    </div>

	    <div class="contArea">
                        <table>
                            <caption> </caption>
                            <colgroup span="2">
                            <col style="width:14%;;" />
                            <col style="width:14%;" />
                            <col style="width:14%;" />
                            <col style="width:14%;" />
                            <col style="width:14%;;" />
                            <col style="width:14%;" />
                            <col style="width:14%;" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th class="sun">Sun</th>
                                    <th>Mon</th>
                                    <th>Tue</th>
                                    <th>Wed</th>
                                    <th>Thu</th>
                                    <th>Fri </th>
                                    <th class="sat">Sat</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="lineNo" begin="0" end="6" >
                            		<tr>
                            			<c:forEach var="entry" items="${data }" begin="${lineNo * 7}" end="${lineNo * 7 + 6 }">
                            			<%--이달 날짜, 지난달 날짜, 다음달 날짜 구분용 css selector --%>
                            			<c:choose>
                            				<c:when test="${entry.prevMonth }"> <c:set var="td_class" value="past" />	</c:when>
                            				<c:when test="${entry.nextMonth }"> <c:set var="td_class" value="future" />	</c:when>
                            				<c:otherwise> <c:set var="td_class" value="" /> </c:otherwise>
                            			</c:choose>
                            			<%--일요일/다른요일 표기용 selector --%>
                            			<c:choose>
                            				<c:when test="${entry.sunday }"> <c:set var="td_class2" value="sun" />	</c:when>
                            				<c:otherwise><c:set var="td_class2" value="" /></c:otherwise>
                            			</c:choose>
                            			
	                                    <td class="${td_class} ${td_class2}">
	                                    	<div class="day">${entry.day }</div>
	                                    	<!-- 스케쥴 있냐 없냐에 따라 아래 div.dropdown 필요 여부가 갈라질듯.. -->
	                                    	<div class="dropdown">
	                                            <div class="dropbtn">	                                            
	                                            	<p class="dayoff">${entry.shortDayOffString }</p>
	                                            	<p class="progress">${entry.calendarLabel }</p>
	                                                <p class="deliver">${entry.deliveryString }</p>	                                                	                                                
	                                            </div>
	                                            <c:if test="${entry.deliveryString != null || entry.shortDayOffString != null || entry.calendarLabel != null   }">
	                                            <div class="dropContent">
	                                            	<c:if test="${entry.deliveryString != null }">
	                                            	<dl>
	                                                	<dt class="deliver">납품</dt>
	                                                    <dd>${entry.deliveryString }</dd>
	                                                </dl>
	                                                </c:if>
	                                                <c:if test="${entry.calendarLabel != null }">
	                                                <dl>
	                                                	<dt class="progress">일정</dt>
	                                                    <dd>${entry.calendarLabel }</dd>
	                                                </dl>
	                                                </c:if>	                                                
	                                                <c:if test="${entry.shortDayOffString != null }">
	                                                <dl>
	                                                	<dt class="dayoff">연차</dt>
	                                                    <dd>${entry.shortDayOffString }</dd>
	                                                </dl>
	                                                </c:if>
	                                            </div>
	                                            </c:if>
                                            </div>
                                     
	                                    </td>
	                            			
                            			</c:forEach>
                            		</tr>                            	
                            	</c:forEach>
                            <!-- 
                                <tr>
                                    <td class="past">
                                    	<div class="day sun">29</div>
                                        <div class="dropdown">
                                           &nbsp;
                                        </div>
                                    </td>
                                    <td class="past">
                                    	<div class="day">30</div>
                                        <div class="dropdown">
                                        	 <div class="dropbtn">
                                            	<p class="red">김정태 반차, 홍길동 연차</p>
                                            </div>
                                            <div class="dropContent">
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="past">
                                    	<div class="day">31</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                            	<p class="green">77-1,76-11,77-3</p>
                                                <p class="red">김정태 반차, 홍길동 연차</p>
                                                <p class="yellow">아모레 1차검수</p>
                                            </div>
                                            <div class="dropContent">
                                            	<dl>
                                                	<dt class="green">진행</dt>
                                                    <dd>77-1,76-11,77-3</dd>
                                                </dl>
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                                <dl>
                                                	<dt class="yellow">검수</dt>
                                                    <dd>아모레 1차검수</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">1</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">2</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">3</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                                <p class="red">김정태 반차, 홍길동 연차</p>
                                            </div>
                                            <div class="dropContent">
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day sat">4</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    	<div class="day sun">5</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">6</div>
                                        <div class="dropdown">
                                        	<div class="dropbtn">
                                                <p class="red">김정태 반차, 홍길동 연차</p>
                                            </div>
                                            <div class="dropContent">
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">7</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">8</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">9</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">10</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day sat">11</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    	<div class="day sun">12</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">13</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                            	<p class="green">77-1,76-11,77-3</p>
                                            </div>
                                            <div class="dropContent">
                                            	<dl>
                                                	<dt class="green">진행</dt>
                                                    <dd>77-1,76-11,77-3</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">14</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">15</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">16</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">17</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                                <p class="yellow">아모레 1차검수</p>
                                            </div>
                                            <div class="dropContent">
                                                <dl>
                                                	<dt class="yellow">검수</dt>
                                                    <dd>아모레 1차검수</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day sat">18</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    	<div class="day sun">19</div>
                                        <div class="dropdown">
                                            
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">20</div>
                                        <div class="dropdown">
                                            
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">21</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">22</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">23</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">24</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day sat">25</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    	<div class="day sun">26</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">27</div>
                                        <div class="dropdown">
                                            
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="day">28</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                            	<p class="green">77-1,76-11,77-3</p>
                                                <p class="red">김정태 반차, 이길동 반차, 홍길동 연차</p>
                                                <p class="yellow">아모레 1차검수</p>
                                            </div>
                                            <div class="dropContent">
                                            	<dl>
                                                	<dt class="green">진행</dt>
                                                    <dd>77-1,76-11,77-3</dd>
                                                </dl>
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                                <dl>
                                                	<dt class="yellow">검수</dt>
                                                    <dd>아모레 1차검수</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="future">
                                    	<div class="day">1</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                            	<p class="green">77-1,76-11,77-3</p>
                                            </div>
                                            <div class="dropContent">
                                            	<dl>
                                                	<dt class="green">진행</dt>
                                                    <dd>77-1,76-11,77-3</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="future">
                                    	<div class="day">2</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                    <td class="future">
                                    	<div class="day">3</div>
                                        <div class="dropdown">
                                            <div class="dropbtn">
                                                <p class="red">김정태 반차, 홍길동 반차, 홍길동 연차</p>
                                            </div>
                                            <div class="dropContent">
                                                <dl>
                                                	<dt class="red">연차</dt>
                                                    <dd>김정태 반차, 이길동 반차, 홍길동 연차</dd>
                                                </dl>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="future">
                                    	<div class="day sat">4</div>
                                        <div class="dropdown">
                                            &nbsp;
                                        </div>
                                    </td>
                                </tr>
                            -->
                            </tbody>
                        </table>
                    </div>
