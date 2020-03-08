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
	                	
	                	<c:forEach var="SubMenu" items="${SubMenu}">
	                	
	                	<dl>
	                    	<dt>${SubMenu.title}</dt>
	                    	<c:forEach var="ListMenu" items="${ListMenu}">
	                    	
	                    		
                         			<c:if test="${SubMenu.lowCode == ListMenu.lowCode}">                    				
                         				<c:choose>
                         					<c:when test="${No eq 1}">
                         						<dd class="sel"><a href="${ListMenu.url}">${ListMenu.title}</a></dd>
                         					</c:when>
                         					<c:otherwise>
                         						<dd><a href="${ListMenu.url}">${ListMenu.title}</a></dd>
                         					</c:otherwise>                       				
                         				</c:choose>
                            			
                             		</c:if>
                      		
	                    	<c:set var="No" value="${No + 1 }" />
	                    	</c:forEach>
	                    	
	                       
	                    </dl>
	                    </c:forEach>
	                  
	                </div>
	            </div> 
	            <!-- //메뉴 -->
