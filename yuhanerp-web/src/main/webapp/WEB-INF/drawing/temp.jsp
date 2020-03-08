<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/DrawingMaster.jsp">

	<layout:put block="mainbody">
	
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>도면 이력</li>
                        <li>도면 이력 리스트</li>
                    </ul>
                    <h2>도면 이력 리스트</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->

                
	</layout:put>
	
</layout:extends>