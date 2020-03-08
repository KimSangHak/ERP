<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/DailyReportMaster.jsp">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>생산일정리스트</li>
                        <li>생산일정 리스트</li>
                    </ul>
                    <h2>생산일정 리스트</h2>
                    <div class="btnArea"><a href="#" class="btn_print">인쇄</a></div>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                    		<form id="FindForm" method="GET">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:12%;;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                <col style="width:12%;" />
                                <col style="width:21%;" />
                                </colgroup>
                                <tr>
                                    <th>검색유형</th>
                                    <td>
                                        <label><input type="radio" name="aa" checked />전체</label>
                                        <label><input type="radio" name="aa" />업체</label>
                                    </td>
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w130"> - <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra }" class="w130">
                                    </td>
                                    <th>내용</th>
                                    <td>
                                        <input type="text" id="keyword" name="keyword" value="${keyword }" class="w280">
                                    </td>
                                </tr>
                            </table>
                            </form>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue" id="FindButton">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    	
                        <c:import url="/internal/dailyreport/list/job/progress"/>
                        <c:import url="/internal/dailyreport/list/job/concept/finished"/>
                        <c:import url="/internal/dailyreport/list/job/concept"/>
                        
                        <c:import url="/internal/dailyreport/list/jig/progress"/>
                        <c:import url="/internal/dailyreport/list/jig/concept/finished"/>
                        <c:import url="/internal/dailyreport/list/jig/concept"/>
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
	
	
	</layout:put>
	<layout:put block="BodyScriptBlock2">
	
		<script type="text/javascript">
		
			function	getFindParameter(){
				return "?keyword=" + escape( $("#keyword").val() ) + "&orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() );
			}
			
			$(document).ready(function(){
				
				$(".btn_file").click(function(e){
					e.preventDefault();
					
					var filehash = $(this).attr("data-filehash");
					if(filehash === undefined || filehash == ""){
						alert("등록된 파일이 없습니다");
						return;
					}
					
					location.href = "/link/" + filehash;
				});
				
				// 검색버튼
				$("#FindButton").click(function(e){
					e.preventDefault();
					
					location.href=getFindParameter();  
				});
				
				// 인쇄 버튼
				$(".btn_print").click(function(e){
					e.preventDefault();
					
					PopWin('/daily_report_print' + getFindParameter(),'1000','708','no');
				});
			});
		
		</script>
		
		<script type="text/javascript" src="/js/daily_report_common.js"></script>
	
	</layout:put>
</layout:extends>