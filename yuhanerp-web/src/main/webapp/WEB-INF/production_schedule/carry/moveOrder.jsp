<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">

                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>입출고 관리</li>
                        <li>가공품 이관</li>
                    </ul>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                	<!-- 테이블 타이틀 -->
                    
                    <!-- //테이블 타이틀 -->
                    
               <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->

                    <!-- //테이블 타이틀 -->
                    
                    <form id="form1" name="form1" method="post">
                    
                     <div class="listArea">
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>이관 가능 Order(미완료)</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                               
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>Device</th>
                                        <th>비고</th>
                                        <th>완료처리</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                	<c:set var="No" value="${pageNo + 1}"></c:set>
                                	<c:forEach var="item2" items="${data2}">
                                	<tr id="${No}">
                                		<td>${item2.orderNo}</td>
										<td>${item2.device}</td>
										<td>${item2.note}</td>
										<td><button class="btn_blue" type="button" id="orderCompletBtn">Order완료</button></td>                                       
                                    </tr>
                                    
                                    <input type="hidden" id="id" name="id" class="id${No}" value="${item2.id}">
                                    <c:set var="No" value="${No + 1 }" />
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- //게시판 -->

                        
                    </div>
                    
                    </form>
                    <!-- //테이블 콘텐츠 -->
                    
                    
                    
                    
                    <div class="listArea">
                    	<!-- 타이틀 -->
                    	<div class="listTit">
                        	<h3>이관 가능 Order(완료)</h3>
                        </div>
                        <!-- //타이틀 -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table>
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                                <col style="width:25%;" />
                               
                                </colgroup>
                            	<thead>
                                	<tr>
                                    	<th>Order No</th>
                                    	<th>Device</th>
                                        <th>비고</th>
                                        <th>도면수량</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="item" items="${data}">
                                	<tr>
                                		<td><a href="javascript:PopWin('${path}/ps/carry/moveOrder/popup/${item.id}','1000','600','no');">${item.orderNo}</a></td>
										<td>${item.device}</td>
										<td>${item.note}</td>
										<td>${item.count}</td>                                       
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
			  
			    $("#orderCompletBtn").click(function(){
			    	
			    	var trid = $(this).closest('tr').attr('id');
			    	
			    	
			    	var id = $(".id"+trid).val();
			    
			        
			        if(confirm("Order 완료 처리를 하시겠습니까?")){
			        	
			        	document.form1.action = "${path}/ps/carry/moveOrder/completdo";
			        	document.form1.submit();

			        }
			    });
			   
			});	
		
		
		
		</script>
		
		<script type="text/javascript" src="/js/daily_report_common.js"></script>
	
	</layout:put>
</layout:extends>