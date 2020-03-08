<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
도면 출도 리스트
02_plan01.html 
-->
<layout:extends name="/WEB-INF/layout/DrawingMaster.jsp">

	<layout:put block="mainbody">
	
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>도면 출도</li>
                        <li>도면 등록</li>
                    </ul>
                    <h2>도면 등록</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea"> 
                
                 <div class="searchArea">
                    <form method="get">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:50%;" />
                              	<col style="width:50%;" />
                                </colgroup>
                                <tr>
                                   
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    </td>
                                  
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
                    </div>
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
	                    <!--설비진행 -->
    	                <c:import url="/drawing/internal/issue/job/progress">
        	           		<c:param value="false" name="editMode"/>
        	           		<c:param value="${orderNoBase}" name="orderNoBase"/>
        	           		<c:param value="${orderNoExtra}" name="orderNoExtra"/>
            	       	</c:import>
                    	
                    	<!-- 치공구 진행  -->
                	    
                	    <c:import url="/drawing/internal/issue/jig/progress">
                    		<c:param value="false" name="editMode"/>
                    		<c:param value="${orderNoBase}" name="orderNoBase"/>
        	           		<c:param value="${orderNoExtra}" name="orderNoExtra"/>
                   		</c:import>
                   		                    
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->	
	
	<script type="text/javascript" src="/js/drawing_common.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		
			$(".SubmitButton").click(function(e){
				$("form").submit();				
			});
		});
		
	</script>
	
	</layout:put>
	
</layout:extends>