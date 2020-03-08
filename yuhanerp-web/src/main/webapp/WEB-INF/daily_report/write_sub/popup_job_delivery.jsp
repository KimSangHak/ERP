<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	// 설비 배송 등록
	// 01_business02_pop02.html
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/api.js"></script>

</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>설비 배송 등록</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

				<form method="POST" id="submitForm" action="/daily_report/register_delivery" enctype="multipart/form-data" >
				<input type="hidden" name="orderNo" value="${data.orderNo}" />
				<input type="hidden" name="jobOrderId" value="${data.id}" />
				
                <!-- 게시판 -->
                <div class="popBox">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:80%;" />
                        </colgroup>
                        <tr>
                            <th>${data.orderNo }  </th>
                            <td>${data.device }</td>
                        </tr>
                        <tr>
                            <th>${data.customerName }</th>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.installDate}" /></td>
                        </tr>
                        <tr>
                            <th>발송 업체</th>
                            <td><input type="text" name="carrierName" value="" autocomplete="off"></td>
                        </tr>
                        <tr>
                            <th>발송 종류</th>
                            <td><input type="text" value="" name="carrierType" ></td>
                        </tr>
                        <tr>
                            <th>도착지</th>
                            <td><input type="text" value="구미" autocomplete="off" name="destination" ></td>
                        </tr>
                        <tr>
                            <th>배송비</th>
                            <td><input type="number" name="shippingFee" value="0" ></td>
                        </tr>
                        <tr>
                            <th>사진 1</th>
                            <td><input type="file" name="image_file1"></td>
                        </tr>
                        <tr>
                            <th>사진 2</th>
                            <td><input type="file" name="image_file2" value=""></td>
                        </tr>
                        <tr>
                            <th>사진 3</th>
                            <td><input type="file" name="image_file3" value=""></td>
                        </tr>
                        <tr>
                            <th>사진 4</th>
                            <td><input type="file" name="image_file4" value=""></td>
                        </tr>
                        <tr>
                            <th>사진 5</th>
                            <td><input type="file" name="image_file5" value=""></td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
				</form>
            </div>
            <!-- //테이블 콘텐츠 -->
            
            <!-- 팝업 버튼 -->
            <div class="popDes">고객사에 발송이 완료되면 반드시 발송 완료를 클릭하세요.</div>
            <div class="popBtn">
                <a href="#" class="ClosePopupButton btn_gray">배송 취소</a>
                <a href="#" id="SaveButton" class="btn_blue">배송 등록</a>
            </div>
            
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("#SaveButton").click(function(e){
				
				$("#submitForm").ajaxSubmit({
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							alert("작업 관련 발송");
							// 팝업 띄워서 메일 보내기
							window.close();
						}else{
							alert(obj.reason);
						}
					},
					error : function(){
						alert("요청을 처리할 수 없습니다");
					}
				});		
			});
			
		});
	
	</script>
</div>
</body>
</html>
