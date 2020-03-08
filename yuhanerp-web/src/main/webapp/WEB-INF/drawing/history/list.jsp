<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!--  -->
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
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    	<form method="GET">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:5%;" />
                                <col style="width:5%;" />
                                <col style="width:7%;" />
                                <col style="width:18%;" />
                                <col style="width:5%;" />
                                <col style="width:15%;" />
                                <col style="width:7%;" />
                                <col style="width:18%;" />
                                </colgroup>
                                <tr>
                                    <th>업체</th>
                                    <td>
                                        <input type="text" name="partnerId" value="${partnerId}" class="w50">
                                    </td>
                                    <th>Order NO</th>
                                    <td>
                                        <input type="text" name="orderNoBase" value="${orderNoBase}" class="w70"> - <input type="text" name="orderNoExtra" value="${orderNoExtra}" class="w130">
                                    </td>
                                    <th>Device</th>
                                    <td>
                                        <input type="text" name="keyword" value="${keyword}" class="w150">
                                    </td>
                                    <th>출도일</th>
                                    <td colspan="5"><input type="text" size="2" name="drawingDateFrom" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${drawingDateFrom}" />"> -
                                    				<input type="text" size="2" name="drawingDateTo" class="AutoDatePicker" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${drawingDateTo}" />">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        </form>
                        <div class="searchBtn"><a href="#" id="FindButton" class="btn_blue">검색</a></div>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>이력 리스트</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:7%;" />
                                <col style="width:15%;" />
                                <col style="width: ;" />
                                <col style="width:7%;" />
                                <col style="width:10%;" />
                                <col style="width:10%;" />
                                <col style="width:7%;" />
                                <col style="width:7%;" />
                                <col style="width:7%;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>Order <br/> No</th>
                                        <th>업체</th>
                                        <th>Device</th>
                                        <th>종류</th>
                                        <th>납품일</th>
                                        <th>영업담당</th>
                                        <th>설계담당</th>
                                        <th>출도일</th>
                                        <th>도면수량</th>
                                        <th>진행률</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:if test="${data != '' || data ne null}">
									<c:forEach items="${data }" var="item">
                                	<tr class="" data-id="${item.id }">
                                    	<td>
                                    		<input type="hidden" name="designDrawingId" value="${item.id }" />
                                    		<c:choose>
                         						<c:when test="${isupdate eq 'Y'}">
                         							<a href="#" class="RowAction">${item.orderNo}</a>
                         						</c:when>
                         						<c:otherwise>
                         							${item.orderNo}
                         						</c:otherwise>                       				
                         					</c:choose>
                                    		
                                    		
                                    	</td>
                                        <td>${item.customerName }</td>
                                        <td id="taLeft">${item.device }</td>
                                        <td>${item.isJigText() }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.installDate}" /></td>
                                        <td>${item.businessUserName }</td>
                                        <td>${item.designUserName }</td>
                                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.designEnd}" /></td>
                                        <td>${item.designCount }</td>
                                        <td>${item.designProgress }%</td>
                                    </tr>
                                    </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->
		<script type="text/javascript">
		$(document).ready(function(){

			// 검색 버튼
			$("#FindButton").click(function(e){
				e.preventDefault();

				if ($('input[name=allCheck]').is(":checked")) {
				    $('input[name=allSelect]').val('Y');
				} else {
				    $('input[name=allSelect]').val('N');
				}

				$("form").submit();
			});


			// 확인 버튼 클릭
			$(".RowAction").click(function(e){
				e.preventDefault();
				
				var TR = $(this).parent().parent();
				var key = TR.attr("data-id");
 
				PopWin('/drawing/history/popup/detail?orderId=' + key,'1100','700','no');
			});
		});
		</script>
	</layout:put>

</layout:extends>