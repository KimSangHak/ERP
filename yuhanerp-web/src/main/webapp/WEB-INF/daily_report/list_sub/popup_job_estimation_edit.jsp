<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/" />
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="css/style_pop.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
	
    <script type="text/javascript" src="js/menu.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>설비 진행 견적가 수정</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBox">
                	<form action="/daily_report/update/estimation" method="POST" >     
                	<input type="hidden" name="id" value="${jobId }" />           	
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:30%;" />
                        <col style="width:20%;" />
                        <col style="width:30%;" />
                        </colgroup>
                        <tr>
                            <th>종류</th>
                            <td>
                            	설비진행
                            </td>
                            <th>등록일</th>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${data.orderDate}" /></td>
                        </tr>
                        <tr>
                            <th>업체</th>
                            <td>
                            	${data.customerName }
                            </td>
                            <th>Order No</th>
                            <td>${data.orderNo }</td>
                        </tr>
                        <tr>
                            <th>Device</th>
                            <td colspan="3"><input type="text" name="device" value="${data.device}"></td>
                        </tr>
                        <tr>
                        	<th>납품일</th>
                        	<td><input type="text" class="AutoDatePicker" readonly="readonly" name="installDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${data.installDate}" />" ></td>
                        	<th>실 납품일</th>
                        	<td><input type="text" class="AutoDatePicker" readonly="readonly" name="realInstallDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${data.realInstallDate}" />" ></td>
                        </tr>
                        <tr>
                        	<th>출고일</th>
                        	<td><input type="text" class="AutoDatePicker" readonly="readonly" name="shippingDate" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${data.shippingDate}" />" ></td>
                        	<th>영업담당</th>
                        	<td>
                                        	
                                        	<c:import url="/internal/util/select/business">
                                        		<c:param value="businessUserId" name="controlName"/>
                                        		<c:param value="${data.businessUserId }" name="defaultValue" />
                                        	</c:import>
                        	</td>
                        </tr>
                        <tr>
                        	<th>비고</th>
	                        <td colspan="3" id="taLeft"><textarea rows="3" name="note">${data.note }</textarea></td>                        
                        </tr>
                        <tr>
                            <th>수량</th>
                            <td><input type="number" name="quantity" value="${data.quantity }"></td>
                            <th>내부단가</th>
                            <td><input type="number" name="internalUnitPrice" value="${data.internalUnitPrice }"></td>
                        </tr>
                        <tr>
                            <th>견적금액</th>
                            <td><input type="number" name="estimatedPrice" value="${data.estimatedPrice }"></td>
                            <th>내고금액</th>
                            <td><input type="number" name="negotiatedPrice" value="${data.negotiatedPrice }"></td>
                        </tr>
                        
                          <tr>
                        	<th>가공 재고 여부</th>
	                        <td colspan="3" id="taLeft">
	                        	 <select name="moveYN" id="moveYN" style="width:263px;">
                   					<option value="N" <c:if test="${data.moveYN == 'N'}">selected</c:if>>N</option>
                   					<option value="Y" <c:if test="${data.moveYN == 'Y'}">selected</c:if>>Y</option>
                 				 </select>
	                        </td>                        
                        </tr>
                    </table>
                    </form>
                </div>
                <!-- //게시판 -->

            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray" id="CancelButton">취소</a>
                <a href="#" class="btn_blue" id="SaveButton">등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
	</div>
	<script type="text/javascript" src="/js/popup/popup_estimation_edit.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
</div>
</body>
</html>
