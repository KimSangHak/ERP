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
                        <li>구매 LIST 조회</li>
                    </ul>
                    <h2>구매 LIST 조회</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    <div class="searchArea">
                    <form method="get">
                    	<div class="searchBox">
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
                                    <th>Description</th>
                                    <td><input type="text" name="desc" value="${desc}" class="w280"></td>
                                    <th>Model/Size</th>
                                    <td>
                                        <input type="text" name="modelNo" value="${modelNo}" class="w280">
                                    </td>
                                </tr>
                                <tr>
                                    <th>Maker</th>
                                    <td><input type="text" name="maker" value="${maker}" class="w280"></td>
                                    <th>거래처</th>
                                    <td><input type="text" name="customer" value="${customer}" class="w280"></td>
                                    <th>UNITNo.</th>
                                    <td><input type="text" name="unitNo" value="${unitNo}" class="w280"></td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue SubmitButton">검색</a></div>
                        </form>
                    </div>
                    <!-- //테이블 타이틀 -->
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>구매 LIST</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:5%;" />
                                <col style="width:7%;" />
                                <col style="width:10%;" />
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
                                		<th>Seq</th>
                                        <th>등록일</th>
                                        <th>Order No.</th>
                                        <th>UNITNo.</th>
                                        <th>Description</th>
                                        <th>Model No./Size</th>
                                        <th>Maker</th>
                                        <th>거래처</th>
                                        <th>수량</th>
                                        <th>Spare</th>
                                        <th>Code</th>
                                        <th>Comment</th>
                                        <th>Remark</th>
                                        <th>도면파일</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach items="${data}" var="line">
                                	<tr>
                                		<td>${line.id }</td>
                                    	<td><fmt:formatDate value="${line.regDate }" pattern="yyyy.MM.dd"/></td>
                                        <td>${line.jobOrderNo }</td>
                                        <td>${line.unitNo }</td>
                                        <td>${line.description }</td>
                                        <td>
                                        	<c:choose>
                         						<c:when test="${isupdate eq 'Y'}">
                         							<a href="javascript:PopWin('/purchase/popup/edit_purchase_list/${line.id }','1000','450','no');">${line.modelNo }</a>
                         						</c:when>
                         						<c:otherwise>
                         							${line.modelNo }
                         						</c:otherwise>                       				
                         					</c:choose>
                                        	
                                        	
                                       </td>
                                        <td>${line.maker }</td>
                                        <td>${line.partnerName }</td>
                                        <td>${line.quantity }</td>
                                        <td>${line.spare }</td>
                                        <td>${line.code }</td>
                                        <td>${line.comment }</td>
                                        <td>${line.remark }</td>
                                        <td>-</td>
                                    </tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->
                        
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