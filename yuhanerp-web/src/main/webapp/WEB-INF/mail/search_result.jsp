<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

     <ul id="tree-mem01">
     	<c:forEach var="item" items="${data }">
     	<li><label><input type="checkbox" class="candidate_member" value="${item.id }" checked />${item.name } (${item.deptName })</label></li>
     	</c:forEach>
     </ul>
