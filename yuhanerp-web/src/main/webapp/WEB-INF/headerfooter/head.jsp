<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<ul class="hlink">
					<li><span class="icon_message">아이콘</span> <span class="htxt"><a
							href="#">메세지</a></span></li>
					<li><span class="icon_member">아이콘</span> <span class="htxt">${loginData.userName }님</span>
						<span>[${loginData.deptName } ${loginData.position }]</span></li>
					<li><span class="icon_admin">아이콘</span> <span class="htxt">
					
					 	<c:choose>
                         	<c:when test="${admin eq 'Y'}">
                            	<a href="/Admin/Main">${loginData.userTypeLabel }</a>
                             </c:when>
                             <c:otherwise>
                             		${loginData.userTypeLabel }
                             </c:otherwise>
                         </c:choose>
					
					</span>
					</li>
					<li><span class="icon_logout">아이콘</span> <span class="htxt"><a
							href="/logout">로그아웃</a></span></li>
				</ul>