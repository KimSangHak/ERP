<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 메인 페이지용 마스터 페이지
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title>Yuhan NCI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/menu.js"></script>


</head>

<body class="main">
	<div id="container">

		<!-- HEADER -->
		<div class="header">
			<!-- 상단 -->
			<div class="hTopArea">
				<h1>
					<a href="/main">로고</a>
				</h1>
				<div class="hsearch">
					<input type="text"> <a href="#" class="btn_hsearch">검색</a>
				</div>
				<!--<jsp:include page="/internal/head"></jsp:include>-->
				<c:import url="/internal/head"/>
			</div>
			<!-- //상단 -->

			<!-- 메뉴 삽입 -->
			<!--<jsp:include page="/internal/menu" ></jsp:include>-->
			<c:import url="/internal/menu"/>
			<!-- 메뉴 삽입 -->

		</div>
		<!-- //HEADER -->

		<!-- CONTENTS -->
		<div class="mainBody">
			<div class="main_contents">
				
				    <div class="mainLeft">
            
                <!-- 메세지 -->
                <div class="message">
                    <div class="titArea">
                        <h2>최근 메세지</h2>
                        <div class="btn_more"><a href="/mymenu/memo">더보기</a></div>
                    </div>
                    <ul class="contArea">
                    	<c:forEach items="${recentMessage }" var="message">
                    	<c:set var="readclass" value="" />
                    	<c:if test="${message.whenRead != null}">
	                    	<c:set var="readclass" value="active" />
                    	</c:if>
                        <li class="${readclass }">
                            <a href="/mymenu/memo/${message.id }">
                                <div class="txt">${message.title }</div>
                                <div class="data">
                                    <span class="icon_cal">아이콘</span>
                                    <span class="txt_cal">${message.receivedDate }</span>
                                </div>
                            </a>
                        </li>
                    	</c:forEach>
                    	<c:if test="${recentMessage.size() == 0 }">
                    	<li>
                    		메세지가 없습니다
                    	</li>
                    	</c:if>
                    </ul>
                    <c:if test="${totalUnreadMessageCount > 4 }">
                    <!-- 리스트 갯수가 4개 이상일경우에 표현부탁드립니다 -->
                    <div class="noti">미확인 메세지가 <a href="#">${totalUnreadMessageCount }개</a> 있습니다</div>
                    </c:if>
                </div>
                <!-- //메세지 -->
                
                <!-- 공지사항 -->
                <div class="notice">
                    <div class="titArea">
                        <h2>사내 공지사항</h2>
                        <div class="btn_more"><a href="#">더보기</a></div>
                    </div>
                    <ul class="contArea">
                    	<c:forEach items="${recentBulletin }" var="item">
                        <li>
                            <a href="#">
                                <div class="txt">${item.title}</div>
                                <div class="data">
                                    <span>
                                        <em class="icon_cal">아이콘</em>
                                        <em class="txt_cal">2016.09.30</em>
                                    </span>
                                    <span>
                                        <em class="icon_per">아이콘</em>
                                        <em class="txt_per">[${item.userDeptName }] ${item.userName }</em>
                                    </span>
                                </div>
                            </a>
                        </li>
                    	</c:forEach>

                    </ul>
                    <!-- 리스트 갯수가 4개 이상일경우에 표현부탁드립니다 -->
                    <div class="noti">미확인 공지가 <a href="#">5개</a> 있습니다</div>
                    
                </div>
                <!-- 공지사항 -->
            </div>
            <!-- //LEFT -->
            
            <!-- CENTER -->
            <div class="mainCenter">
                <div class="calendar">
                	<!-- 캘린더 내려오는 영역 -->
                </div>                
            </div>
            <!-- //CENTER -->
            
            <!-- RIGHT -->
            <div class="mainRight">
            
                <!-- 투데이 -->
                <div class="today">
                    <div class="titArea">
                        <h2>Today Issue</h2>
                        <div class="btn_more"><a href="#">더보기</a></div>
                    </div>
                    <div class="contArea">
                        <dl>
                            <dt class="red">연차</dt>
                        	<c:forEach items="${todayIssueDayoff }" var="entry">
                            <dd>
                                <span>${entry.deptName}</span>
                                <span>${entry.userName} ${entry.position }</span>
                            </dd>
                            </c:forEach>
                            <c:if test="${todayIssueDayoff.size() == 0}">
                            <dd>
                                <span>(없음)</span>
                                <span>&nbsp;</span>
                            </dd>
                            </c:if>
                        </dl>
                        <dl>
                            <dt class="yellow">일정</dt>
                            <dd>
                            	${todayIssueCalendarData }
                            	<c:if test="${todayIssueCalendarData == null }">
                            	(없음)
                            	</c:if>                            
                            </dd>
                        </dl>
                        <dl>
                            <dt class="green">납품</dt>
                            <dd>
                            	${todayIssueInstall }
                            	<c:if test="${todayIssueInstall == null }">
                            	(없음)
                            	</c:if>
                            
                            </dd>
                        </dl>
                    </div>
                </div>
                <!-- //투데이 -->
                
                <!-- 사내 전화번호 -->
                <div class="call">
                    <div class="titArea">
                        <h2>사내 주요 연락처</h2>
                        <div class="btn_more"><a href="#">더보기</a></div>
                    </div>
                    <div class="contArea">
                        <dl>
                            <dt>영업팀 </dt>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>제품발주</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>도면팀 </dt>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>도면 출도</span>
                            </dd>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>구매리스트</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>생산팀</dt>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>외주가공 발주</span>
                            </dd>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>완품/가공</span>
                            </dd>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>후처리</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>가공팀</dt>
                            
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>재고관리</span>
                            </dd>
                        </dl>
                        <dl>
                            <dt>재무팀</dt>
                            <dd>
                                <span>[☎ 2560]</span>
                                <span>사대보험</span>
                            </dd>
                        </dl>
                    </div>
                </div>
                <!-- //사내 전화번호 -->
               
                
            </div>

			</div>

			<!-- FOOTER -->
			<div class="footer">
				<div class="copy">copyright ⓒ 2017 yuhan nci. all right
					reserved.</div>
				<div class="admin">관리자 : 김정태 (☎ 1234)</div>
			</div>
			<!-- //FOOTER -->

			<!-- 메세지 버튼 -->
			<div class="btn_message">
				<a href="javascript:PopWin('00_pop_message.html','600','700','no');">메세지</a>
			</div>
			<!-- //메세지 버튼 -->
			<!-- 맨 위로 가기 아이콘 -->
			<div id="back-top">
				<p class="btn_top">
					<a href="#top"></a>
				</p>
			</div>
			<!-- /맨 위로 가기 아이콘 -->

		</div>
		<!-- //CONTENTS -->
			
	</div>
	<script type="text/javascript">
		
			// 중아에 있는 달력 불러오기
			function loadCalendar(y,m){
				if(y === undefined ){
					$(".calendar").load("/main/calendar");
				}
				else {
				
					$(".calendar").load("/main/calendar", $.param({
						"year" : y,
						"month" : m
					}));
				}
			}
		
			$(document).ready(function(){
								
				loadCalendar();
				$(document).on("click", "a.loadCalendar", function(e){
					e.preventDefault();
					
					var newYear = $(this).attr("data-year");
					var newMonth = $(this).attr("data-month");
					loadCalendar(newYear, newMonth);
				});		
				
			});
		
		
		
		</script>
		
				
</body>

</html>
