<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<!-- 메뉴 -->
			<div class="menu">
				<ul class="gnb">
					<c:forEach var="item" items="${topMenu }">
					<li><a href="${item.url }"><c:out value="${item.title }"></c:out> </a></li>
					</c:forEach>
				</ul>
				<div class="snb">
					<ul class="subMenu">
						<c:set var="idx" value="1" />
						<c:forEach items="${subMenu }" var="eachSubMenu">
						<li class="m${idx }">
							<ul>
								<c:forEach var="eachSubMenuEntry" items="${eachSubMenu }">
								<li><a href="${eachSubMenuEntry.url }"><c:out value="${eachSubMenuEntry.title }" /></a></li>
								</c:forEach>
							</ul>
						</li>
						<c:set var="idx" value="${idx + 1 }" />
						</c:forEach>
						<!-- 
						<li class="m1">
							<ul>
								<li><a href="#">영업일보</a></li>
								<li><a href="#">작업 지시</a></li>
								<li><a href="#">월별 매출</a></li>
								<li><a href="#">매출업체 관리</a></li>
							</ul>
						</li>
						<li class="m2">
							<ul>
								<li><a href="#">도면 이력</a></li>
								<li><a href="#">도면 출도</a></li>
								<li><a href="#">도면 수정</a></li>
								<li><a href="#">구매 관리</a></li>
								<li><a href="#">발주서 발송</a></li>
							</ul>
						</li>
						<li class="m3">
							<ul>
								<li><a href="#">생산 일정</a></li>
								<li><a href="#">가공 일정 관리</a></li>
								<li><a href="#">거래 명세서 관리</a></li>
								<li><a href="#">매입업체 관리</a></li>
							</ul>
						</li>
						<li class="m4">
							<ul>
								<li><a href="#">가공 관리</a></li>
								<li><a href="#">QC 관리</a></li>
								<li><a href="#">조립 관리</a></li>
							</ul>
						</li>
						<li class="m5">
							<ul>
								<li><a href="#">경리 일보</a></li>
								<li><a href="#">매입/매출 관리</a></li>
								<li><a href="#">수출 관리</a></li>
								<li><a href="#">지출/카드 명세서</a></li>
								<li><a href="#">입출금 관리</a></li>
								<li><a href="#">업체관리</a></li>
							</ul>
						</li>
						<li class="m6">
							<ul>
								<li><a href="#">근태/휴무 관리</a></li>
								<li><a href="#">출장관리</a></li>
								<li><a href="#">차량관리</a></li>
								<li><a href="#">여권관리</a></li>
							</ul>
						</li>
						<li class="m7">
							<ul>
								<li><a href="#">개인 정보 수정 </a></li>
							</ul>
						</li>
						<li class="m8">
							<ul>
								<li><a href="#">USER 관리</a></li>
								<li><a href="#">홈페이지 관리</a></li>
							</ul>
						</li>
						<li class="m9">
							<ul>
								<li><a href="#">검색결과</a></li>
							</ul>
						</li>
						-->
					</ul>
				</div>
			</div>
			<!-- //메뉴 -->