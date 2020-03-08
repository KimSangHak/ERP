<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	// 구매 리스트 조회
%>
<layout:extends name="/WEB-INF/layout/PurchaseMaster.jsp">

	<layout:put block="mainbody">
             
         
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>구매 LIST</li>
                        <li>구매 거래처 관리</li>
                    </ul>
                    <h2>구매 거래처 관리</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="searchArea">
                    <form method="get">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                               
                                <col style="width:21%;" />
                               
                                <col style="width:21%;" />
                             
                                </colgroup>
                                 <tr>
                                 
                                    <th>업체명</th>
                                    <td><input type="text" name="partnerName" value="${partnerName}" class="w280"></td>
                                    <th>업체코드</th>
                                    <td><input type="text" name="Code" value="${Code}" class="w280"></td>
                                </tr>
                                
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
                          <ul class="sortRight">
                          
                          		<c:choose>
                         			<c:when test="${isupdate eq 'Y'}">
                         				<li><a href="javascript:PopWin('${path}/purchase/Purchase_Partner/popup','600','1500','no');" class="btn_line_gray">거래처 등록</a></li>
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
                                <col style="width:10%;" />
                                <col style="width:30%;" />
                                <col style="width: ;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>No</th>
                                        <th>업체 코드</th>
                                        <th>업체명</th>
                                    	<th>계산서업체명</th>
                                        <th>결제 기준</th>
                                    </tr>
                                </thead>
                               <c:set var="No" value="${pageNo + 1}"></c:set>
                               <c:forEach items="${partner }" var="partner">
                                <tbody>
                                	<tr>
                                    	<td rowspan="2">${No}</td>
                                        <td rowspan="2"><a href="javascript:PopWin('${path}/purchase/Purchase_Partner/popup_Ed/${partner.id}','600','1500','no');"> ${partner.id}</a></td>
                                        <td rowspan="2"> ${partner.partnerName}</td>
                                        <td> ${partner.billingName}</td>
                                        <td>${partner.billingAfter}</td>
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