<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
/*
설비 컨셉 작업 지시 등록 팝업
01_business02_pop05.html
*/
%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title>${orderTypeDesc } 컨셉 작업 지시</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/api.js"></script>
	
	<style type="text/css">
	
	.w32 {width:32px;}
	
	</style>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>${orderTypeDesc } 컨셉 작업 지시 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

				<form id="submitConceptForm" action="/daily_report/register_concept" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="mode" value="${mode }" />
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="orderType" value="${orderType }" />
                <!-- 게시판 -->
                <div class="popBox">
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
                            	<c:choose>
                            		<c:when test="${mode == 'update' }">
                            			${orderTypeDesc } Concept 완료
                            		</c:when>
                            		<c:otherwise>
		                                	${orderTypeDesc } Concept
                            		</c:otherwise>
                           		</c:choose>
                            </td>
                            <th>등록일</th>
                            <td>${today }</td>
                        </tr>
                        <tr>
                            <th>업체</th>
                            <td>
                            	<c:choose>
                            		<c:when test="${mode == 'update' }">
                            			${item.customerName }
                            			<input type="hidden" name="customerId" value="${item.customerId }" />
                            		</c:when>
                            		<c:otherwise>
		                              	
		                              	 <c:import url="/internal/util/select/customer">
		                             		<c:param value="customerId" name="controlName"/>
		                             		<c:param value="${item.customerId }" name="defaultValue" />
		                             		<c:param value="true" name="showUnspecifiedItem" />
		                               	</c:import>
                            		</c:otherwise>
                            	</c:choose>
                            </td>
                            <th>Order No</th>
                            <td>
                            	<c:choose>
                            		<c:when test="${mode == 'update' }">
                            			${item.orderNo}
                            			<input type="hidden" name="orderNoBase" value="${item.orderNoBase }" />
                            			<input type="hidden" name="orderNoExtra" value="${item.orderNoExtra }" />
                            		</c:when>
                            		<c:otherwise>
										<input type="text" name="orderNoBase" value="${item.orderNoBase }" maxlength="6" class="w32">-<input type="text" name="orderNoExtra" maxlength="13" value="" class="w70" value="${item.orderNoExtra }"><button id="GetOrderNoButton" class="btn_blue">*</button>                            		
                            		</c:otherwise>
                            	</c:choose>
                           	
                            </td>
                        </tr>
                        <tr>
                            <th>Device</th>
                            <td colspan="3"><input type="text" name="device" maxlength="50" value="${item.device }"></td>
                        </tr>
                        <tr>
                            <th>수량</th>
                            <td><input type="number" name="quantity" value="${item.quantity }" id="quantity" ></td>
                            <th>영업담당</th>
                            <td>
                              	
                              	<c:import url="/internal/util/select/business">
                             		<c:param value="businessUserId" name="controlName"/>
                             		<c:param value="${item.businessUserId }" name="defaultValue" />
                               	</c:import>
                            </td>
                        </tr>
                        <tr>
                            <th>설계담당</th>
                            <td>
                            	
                            	<c:import url="/internal/util/select/design">
                                	<c:param value="designUserId" name="controlName"/>
                                    <c:param value="${item.designUserId }" name="defaultValue" />
                             	</c:import>
                            </td>
                            <th>고객담당</th>
                            <td><input type="text" name="customerUser" value="${item.customerUser }" maxlength="30" /></td>
                        </tr>
                        <tr>
                            <th>비고</th>
                            <td colspan="3" class="h60"><textarea name="note" rows="3">${item.note }</textarea></td>
                        </tr>
                        <c:if test="${mode == 'update'}">
                        <tr>
                            <th>내부단가</th>
                            <td><input type="number" name="internalUnitPrice" value="${item.internalUnitPrice }"></td>
                            <th>내부단가 <br/> 공유일</th>
                            <td><input type="text" name="internalUnitPriceSharedDate" class="AutoDatePicker" data-date-format="YYYY MMMM DD" value='<fmt:formatDate pattern = "yyyy-MM-dd" value="${item.internalUnitPriceSharedDate}" />'></td>
                        </tr>
                        <tr>
                            <th>견적금액</th>
                            <td><input type="number" name="estimatedPrice" value="${item.estimatedPrice }"></td>
                            <th>내고금액</th>
                            <td><input type="number" name="negotiatedPrice" value="${item.negotiatedPrice }"></td>
                        </tr>
                        <tr>
                            <th>내부단가 파일</th>
                            <td colspan="3"><input type="file" name="internalPriceFile" ></td>
                        </tr>
                        <tr>
                            <th>컨셉 도면 파일</th>
                            <td colspan="3"><input type="file" name="conceptFile" ></td>
                        </tr>
                        </c:if>
                    </table>
                </div>
                <!-- //게시판 -->
				</form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="ClosePopupButton btn_gray">취소</a>
                <a href="#" class="btn_blue" id="registerButton">등록</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
	
	<script type="text/javascript">
	
		function openSendMail(){
			// javascript:PopWin('01_business02_pop04.html','600','700','no');
		}
		
		function reloadOrderNo(){
			var currentSelected = $("input[name='orderNoBase']").val();
			getNextOrderSequenceNo("CONCEPT", currentSelected.substring(0,2), "${orderType}", function(v){
				$("input[name='orderNoBase']").val(currentSelected.substring(0,2) + v);
				$("input[name='orderNoExtra']").val("");
				});			
		}
	
		$(document).ready(function(){
				
			// 등록 버튼
			$("#registerButton").click(function(e){
				e.preventDefault();
				
				$("#submitConceptForm").ajaxSubmit({
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							alert("작업 관련 발송");
							// 팝업 띄워서 메일 보내기
							window.close();
							opener.location.reload();
						}else{
							alert(obj.reason);
						}
					},
					error : function(){
						alert("요청을 처리할 수 없습니다");
					}
				});				
			});
			
			// 업체 변경
			$("#customerId").change(function(){
				var selectedPartnerId = $(this).val();
				
				if( $("input[name='orderNoExtra']").val() != ""){
					if(!confirm("Order-ID 가 변경됩니다"))
						return;
				}
				
				$("input[name='orderNoBase']").val(selectedPartnerId);
				$("input[name='orderNoExtra']").val("");
				
				reloadOrderNo();
				
			}); // .trigger("change");
			
			$("#GetOrderNoButton").click(function(e){
				e.preventDefault();
				
				reloadOrderNo();
			});
			
			
		});
	</script>	
	<script type="text/javascript" src="/js/popup_equipment_common.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
		
</div>
</body>
</html>
