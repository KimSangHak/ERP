<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// main 에도 동일한 코드 있음. 나중에 병합 필요
%>
                <div class="message">
                    <div class="titArea">
                        <h2>최근 메세지</h2>
                        <div class="btn_more"><a href="#">더보기</a></div>
                    </div>
                    <ul class="contArea">
                    	<c:forEach items="${recentMessage }" var="message">
                    	<c:set var="readclass" value="" />
                    	<c:if test="${message.whenRead != null}">
	                    	<c:set var="readclass" value="active" />
                    	</c:if>
                        <li class="${readclass }">
                            <a href="#">
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
