<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	// 구매 리스트 조회
%>
<layout:extends name="/WEB-INF/layout/AdminMaster.jsp">

	<layout:put block="mainbody">
             
         
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>관리자</li>
                        <li>사원 정보 관리</li>
                    </ul>
                    <h2>사원 정보 관리</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                 <ul class="sortRight">
                     <li><a href="javascript:PopWin('${path}/Admin/popup/userIns','600','1500','no');" class="btn_line_gray">사원 등록</a></li>
                 </ul>
                
                    
                  
                    	
                          
                        <!-- //sort -->
                        
                        <!-- 게시판 -->
                        
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:20%;" />
								<col style="width:20%;" />
                                <col style="width:20%;" />
                                <col style="width:20%;" />
                                <col style="width:20%;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>사원코드</th>
                                        <th>부서</th>
                                        <th>이름</th>
                                    	<th>직급</th>
                                        <th>연락처</th>
                                    </tr>
                                </thead>
                               <c:set var="No" value="${pageNo + 1}"></c:set>
                               <c:forEach items="${user}" var="user">
                                <tbody>
                                	<tr>
                                    	<td rowspan="2"><a href="javascript:PopWin('${path}/Admin/popup/userEdit/${user.id}','600','1500','no');"> ${user.id}</a></td>
                                        <td rowspan="2">${user.deptName}</td>
                                        <td rowspan="2"> ${user.name}</td>
                                        <td>${user.position}</td>
                                        <td>${user.handPhone}</td>
                                    </tr>
                                
                                  
                                </tbody>
                                <c:set var="No" value="${No + 1 }" />
                                </c:forEach>
                            </table>
                        </div>
                        
                        <!-- //게시판 -->
                        
                        <!-- 페이징 -->
                        <div class="listbtnArea">
                        	<div class="paging">
                                <a href="#" title="First" class="first">&nbsp;</a>
                                <a href="#" title="Prev" class="prev">&nbsp;</a>
                                <span>1</span>
                                <a href="#" title="2">2</a>
                                <a href="#" title="3">3</a>
                                <a href="#" title="4">4</a>
                                <a href="#" title="Next" class="next">&nbsp;</a>
                                <a href="#" title="Last" class="last">&nbsp;</a>
                            </div>
                        </div>
                        <!-- //페이징 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
                
        
                            		
	</layout:put>
	<layout:put block="BodyScriptBlock2">
		<script type="text/javascript">
			$(document).ready(function(){
			
				$(".SubmitButton").click(function(e){
					$("form").submit();				
				});
			
			});
		</script>
	
	</layout:put>
</layout:extends>