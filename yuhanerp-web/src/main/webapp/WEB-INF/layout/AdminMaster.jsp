<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 영업일보용 마스터 페이지
%>
<layout:extends name="/WEB-INF/layout/master2.jsp">

	<layout:put block="left_side">
	        
	        	<!-- 메뉴 -->
	            <div class="sub_menu">
	            	<div class="titArea">
	            		<h2>관리자</h2>
	                </div>
	                <div class="contArea">
	                	<dl>
	                    	<dt>등록 정보 관리</dt>
	                        <dd class="sel"><a href="#">사원 정보 관리</a></dd>
	                        <dd><a href="#">미정</a></dd>
	                        <dd><a href="#">미정</a></dd>
	                    </dl>
	                  
	                </div>
	            </div>
	            <!-- //메뉴 -->
	            
	            <!-- 메세지 -->
	            
	            <c:import url="/daily_report_left_message"/>
	            <!-- //메세지 -->
	                
	</layout:put>
  	<layout:put block="main">
		<layout:block name="mainbody">
		
		</layout:block>                  	
  	</layout:put>
	<layout:put block="BodyScriptBlock">
	
		<script type="text/javascript">
		
			$(document).ready(function(){
				
				// TODO :: 
				
			});
		
		</script>
	
		<layout:block name="BodyScriptBlock2"></layout:block>
	</layout:put>
</layout:extends>
