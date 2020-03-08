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
                        <li>견적/발주/입고</li>
                        <li>입고품 등록</li>
                    </ul>
                    <h2>입고품 등록</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <div class="contArea">
                  <div class="searchArea">
                    	<div class="searchBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:12%;;" />
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
                                    <th>Order No.</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w280">
                                    </td>
                                    <th>발주일</th>
                                    <td>
                                        <input type="date" name="" value=" " class="w140"> - <input type="date" name="" value=" " class="w140">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn"><a href="#" class="btn_blue">검색</a></div>
                    </div>
                
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	
                        <!-- 게시판 -->
                        
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:16%;" />
								<col style="width:16%;" />
                                <col style="width:16%;" />
                                <col style="width:10%;" />
                                <col style="width:10%;" />
                                <col style="width:16%;" />
                                <col style="width:16%;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>발주No</th>
                                        <th>발주요청일</th>
                                        <th>업체</th>
                                    	<th>품목수량</th>
                                        <th>미입고 수량</th>
                                        <th>납기예정일</th>
                                        <th>결제금액</th>
                                    </tr>
                                </thead>
                               
                               <c:forEach items="${data}" var="data">
                                <tbody>
                                	<tr>
                                    	<td rowspan="2"><a href="javascript:PopWin('${path}/purchase/Warehousing_ins/pop/${data.job_purchase_id}-${data.request_id}','1800','600','no');"> ${data.job_purchase_id}-${data.request_id}</a></td>
                                        <td rowspan="2"> ${data.estimate_requested_when}</td>
                                        <td rowspan="2"> ${data.partnerName}</td>
                                        <td rowspan="2"> ${data.quantity}</td>
                                        <td rowspan="2">&nbsp;</td>
                                        <td rowspan="2">&nbsp;</td>
                                        <td rowspan="2">${data.supply_price}</td>
                                    </tr>
                                
                                  
                                </tbody>
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
                
        </div>
                            		
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