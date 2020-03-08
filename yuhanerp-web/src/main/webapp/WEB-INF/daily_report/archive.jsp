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
                        <li>지난 생산일정</li>
                    </ul>
                    <h2>지난 생산일정</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<div class="searchBox">
                    		<form method="GET" id="FindForm">
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
                                        <label><input type="checkbox" name="aa" checked />전체</label>
                                        <label><input type="checkbox" name="aa" />업체</label>
                                    </td>
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" name="orderNoBase" id="orderNoBase" value="${orderNoBase }" class="w130"> - <input type="text" name="orderNoExtra" id="orderNoExtra" value="${orderNoExtra }" class="w130">
                                    </td>
                                    <th>내용</th>
                                    <td>
                                        <input type="text" name="keyword" id="keyword" value="${keyword}" class="w280">
                                    </td>
                                </tr>
                                <tr>
                                    <th>제품 발송일</th>
                                    <td><input type="text" name="deliveryDateFrom" id="deliveryDateFrom" value="${deliveryDateFrom }" class="w140 AutoDatePicker"> - <input type="text" name="deliveryDateTo" id="deliveryDateTo" value="${deliveryDateTo }" class="w140 AutoDatePicker"></td>
                                    <th>등록일</th>
                                    <td colspan="3">
                                        <input type="text" name="orderDateFrom" id="orderDateFrom" value="${orderDateFrom}" class="w140 AutoDatePicker"> - <input type="text" name="orderDateTo" id="orderDateTo" value="${orderDateTo }" class="w140 AutoDatePicker">
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
						<c:import url="/internal/dailyreport/archive/job"/>

                        <c:import url="/internal/dailyreport/archive/job/concept/finished"/>
                        <c:import url="/internal/dailyreport/archive/job/concept"/>
                        
                        <c:import url="/internal/dailyreport/archive/jig"/>
                        <c:import url="/internal/dailyreport/archive/jig/concept/finished"/>
                        <c:import url="/internal/dailyreport/archive/jig/concept"/>

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
			
			// 검색 버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				$("#FindForm").submit();
			});
			
			$("a.ASButton").click(function(e){
				e.preventDefault();
				
				var orderType = $(this).attr("data-orderType");
				var id = $(this).attr("data-id");
				
				PopWin('/daily_report/popup/new_' + orderType + '/?fromJob=' + id ,'600','700','no');
			});
			
			$("a.REButton").click(function(e){
				e.preventDefault();
				
				var orderType = $(this).attr("data-orderType");
				var id = $(this).attr("data-id");
				
				PopWin('/daily_report/popupNew/new_' + orderType + '/?fromJob=' + id ,'600','700','no');
			});
			
			$(".loadModifyList").each(function(){
				
				var id = $(this).attr("data-id");
				var root = $(".loadModifyList[data-id=" + id + "]");
				
				$.ajax({
					url : "/daily_report/api/modification_list",
					data : { "id" : id},
					type : "POST",
					dataType : "JSON",
					success : function(obj){
						
						console.log(obj);
						root.children("dd").remove();
						
						for(var i=0;i<obj.data.length;i++){
							var dd = $("<dd/>");
							dd.text(obj.data[i].text);
							
							root.append(dd);
						}
						
						if(obj.data.length == 0){
							root.append("<dd>수정 이력이 없습니다</dd>")
						}
					}
				});
				
			});
			
		});
			
		
		</script>
	</layout:put>
	    
</layout:extends>    