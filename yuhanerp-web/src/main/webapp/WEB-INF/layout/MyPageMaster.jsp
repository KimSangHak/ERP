<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 인수인계용 마스터 페이지
%>
<layout:extends name="/WEB-INF/layout/master2.jsp">

	<layout:put block="left_side">


			<c:import url="/sidemenu/MyMenu"/>
	      
	        
	        <c:import url="/daily_report_left_message"/>
	        
	        
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
