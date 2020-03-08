<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   					
                            <th>업체 소분류 <span class="ncBlue">*</span></th>
                            <td>
                                <select name="typeKind" id="typeKind" style="width:263px;">
                                   <option value="none" <c:if test="${typeKind == 'none'}">selected</c:if>>선택</option>
                                	<c:forEach items="${result}" var="item">
                                		<option value="${item.typeKind}"<c:if test="${typeKind == item.typeKind}">selected</c:if>>${item.typeName}</option>
                                	</c:forEach>
                               
                                </select>
                            </td>
                      