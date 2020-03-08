<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<select id="${controlName }" name="${controlName }" class="${cssClass }" style="${style}">
	<c:if test="${showUnspecifiedItem }">
	<option value="">-- 미지정 --</option>
	</c:if>
	<!-- defaultValue = ${defaultValue}, controlName = ${controlName} -->      
	<c:forEach items="${data }" var="entry">
		<c:choose>
			<c:when test="${entry.key == defaultValue }">
		<option selected="selected" value="${entry.key}"><c:out value="${entry.value }" /></option>
			</c:when>
			<c:otherwise>
		<option value="${entry.key}"><c:out value="${entry.value }" /></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
