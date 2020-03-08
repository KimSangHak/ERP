<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<layout:extends name="/WEB-INF/layout/DailyReportMaster.jsp">
	<layout:put block="mainbody"> 
            
                <!-- 서브 타이틀 --> 
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>매출 거래처 관리</li>
                        <li>매출 거래처 리스트</li>
                    </ul>
                    <h2>매출 거래처 리스트</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- sort -->
                    	<div class="sort">
                            <ul class="sortLeft">
                            	<li>
                                	<select class="w100" name="orderBy">
                                        <option>정렬기준</option>
                                        <!-- 
                                        <option>납품일 기준</option>
                                        <option>발주일 기준</option>
                                        -->
                                        <option selected="selected">업체별 기준</option>
                                    </select>
                                </li>
                                <li>
                                    <select class="w100" name="sortOrder">
                                        <option value="desc" selected="selected">내림차순</option>
                                        <option value="asc">오름차순</option>
                                    </select>
                                </li>
                            </ul>
                            <ul class="sortRight">
                            
                            	<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<li><a href="javascript:PopWin('/partner/popup/new','600','430','no');" class="btn_line_gray">매출처 등록</a></li>
                         			</c:when>
                         			<c:otherwise>
                         						
                         			</c:otherwise>                       				
                         		</c:choose>
                            	
                            </ul>
                        </div>
                        <!-- //sort -->

                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:5%;" />
								<col style="width:5%;" />
                                <col style="width:15%;" />
                                <col style="width:15%;" />
                                <col style="width:10%;" />
                                <col style="width: ;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>No</th>
                                        <th>업체 코드</th>
                                        <th>업체명</th>
                                    	<th>계산서업체명</th>
                                    	<th>결제 기준</th>
                                        <th>위치 주소</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                               	<c:set var="lastPartner" value=""/>
                               	<c:set var="No" value="${data.pageOffset + 1 }" />
                               	<c:forEach items="${data.data}" var="line">
                                	<c:choose>
                               		<c:when test="${lastPartner != line.partnerId }">
                                	<tr>
                                    	<td rowspan="${line.billingLines}">${No}</td>
                                        <td rowspan="${line.billingLines}"><a href="javascript:PopWin('/partner/popup/edit/${line.partnerId}','600','380','no');">${line.partnerId }</a></td>
                                        <td rowspan="${line.billingLines}">${line.partnerName }</td>
                                        <td>${line.billingName}</td>
                                        <td>${line.payment }</td>
                                        <td>${line.region }</td>
                                    </tr>                                		
                               		</c:when>
                               		<c:otherwise>
                                    <tr>
                                    	<td>${line.billingName }</td>
                                        <td>${line.payment }</td>
                                    </tr>
                               		</c:otherwise>
                                	</c:choose>
                              		<c:set var="lastPartner" value="${line.partnerId }" />
                              		<c:set var="No" value="${No + 1 }" />
                               	</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
                        <!-- 페이징 -->
                        <div class="listbtnArea">
                        	<div class="paging">
                                <a href="#" title="First" class="first MovePageTo" data-pageNo="${data.previous10PageNo }">&nbsp;</a>
                                <a href="#" title="Prev" class="prev MovePageTo" data-pageNo="${data.previousPageNo }">&nbsp;</a>
                                <c:forEach var="pageNo" begin="${data.initialIndexPageNo }" end="${data.lastIndexPageNo }">
                                	<c:choose>
                                		<c:when test="${pageNo == data.currentPageNo }"><span>${pageNo }</span></c:when>
                                		<c:otherwise>
                                		<a href="#" title="${pageNo }" class="MovePageTo" data-pageNo="${pageNo }" >${pageNo }</a>
                                		</c:otherwise>
                                	</c:choose>                                	
                                </c:forEach>
                                <a href="#" title="Next" class="next MovePageTo" data-pageNo="${data.nextPageNo }">&nbsp;</a>
                                <a href="#" title="Last" class="last MovePageTo" data-pageNo="${data.next10PageNo }">&nbsp;</a>
                            </div>
                        </div>
                        <!-- //페이징 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
                    
                </div>
                <!-- //서브 콘텐츠 -->

	</layout:put>               
	<layout:put  block="BodyScriptBlock2" >
	
		<script type="text/javascript">
		$(document).ready(function(){
		
			$(".MovePageTo").click(function(e){
				e.preventDefault();
				
				location.href = "?pageNo=" + $(this).attr("data-pageNo");
			});
		
		});
		</script>
	</layout:put>		
</layout:extends>


