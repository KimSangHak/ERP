<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	        	<!-- 메뉴 -->
			<div class="sub_menu">
				<div class="titArea">
					<h2>${name}</h2>
				</div>
				<div class="contArea">
					<c:set var="No" value="1"></c:set>
					<c:set var="NoLow" value="1"></c:set>
					<c:forEach var="item" items="${ListMenu}">
						<c:choose>
							<c:when test="${item.listCode eq '00'}">
								<c:if test="${NoLow eq 1}">
									<c:set var="NoLow" value="${NoLow + 1 }" />
					<dl>
								</c:if>
								<c:if test="${NoLow ne 1}">
					</dl>
					<dl>
								</c:if>	
						<dt>${item.title}</dt>
							</c:when> 
							<c:otherwise>				
								<c:choose>
									<c:when test="${No eq 1}">
										<dd class="sel"><a href="${item.url}">${item.title}</a></dd>
										<c:set var="No" value="${No + 1 }" />
									</c:when>
									<c:otherwise>
										<dd><a href="${item.url}">${item.title}</a></dd>
									</c:otherwise>                       				
								</c:choose>
							</c:otherwise>  	
						</c:choose>		
	                </c:forEach>
	                </dl>
				</div>

			</div> 
	            <!-- //메뉴 -->
