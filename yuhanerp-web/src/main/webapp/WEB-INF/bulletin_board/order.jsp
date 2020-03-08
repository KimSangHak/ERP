<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/"/>
	<meta charset="utf-8">
	<title></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/display.css">
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	
</head>

<body>
<div id="container">


	<div class="display">

		<!-- 타이틀 -->
    	<h1>Order 진행현황</h1>
        <!-- /타이틀 -->
        
        <!-- 내용 -->
        <div class="conTable">
        	<table>
            	<caption> </caption>
                <colgroup span="2">
                <col style="width:120px;" />
                <col style="width:440px;" />
                <col style="width:160px;" />
                <col style="width:125px;" />
                <col style="width:145px;" />
                <col style="width:125px;" />
                <col style="width:125px;" />
                <col style="width:125px;" />
                <col style="width:125px;" />
                <col style="width:125px;" />
                <col style="width:125px;" />
                </colgroup>
                <thead>
                    <tr>
                        <th>주문<br/>번호</th>
                        <th>제품명</th>
                        <th>고객사</th>
                        <th>납품일</th>
                        <th>상태</th>
                        <th>작업<br/>지시일</th>
                        <th>설계<br/>완료일</th>
                        <th>가공<br/>완료일</th>
                        <th>구매<br/>완료일</th>
                        <th>조립<br/>완료일</th>
                        <th>PGM/<br/>TEST</th>
                    </tr>
                </thead>                
                <tbody>                	
                    <tr class="firstLine" data-rowId="0">
                        <td rowspan="2" data-id="orderNo">&nbsp;</td>
                        <td rowspan="2" data-id="device">&nbsp;</td>
                        <td rowspan="2" data-id="partnerId">브이엠텍</td>
                        <td rowspan="2" data-id="installDate">08.11</td>
                        <td rowspan="2" data-id="status">가공중</td>
                        <td rowspan="2" data-id="orderDate">08.01</td>
                        <td data-id="designDate">08.07</td>
                        <td data-id="processDate">08.11</td>
                        <td data-id="purchaseDate">-</td>
                        <td data-id="assemblyDate">08.11</td>
                        <td data-id="programDate">-</td>
                    </tr>
                    <tr class="secondLine" data-rowId="0">
                        <td>
                            <p data-id="designProgress">50%</p>
                            <p>
                            	<div class="gArea">
                                	<div data-id="designProgress" class="" style="width:50%;">색상</div>
                                </div>
                            </p>
                        </td>
                        <td>
                            <p data-id="processProgress">90%</p>
                            <p>
                            	<div class="gArea">
                                	<div data-id="processProgress" class="" style="width:90%;">색상</div>
                                </div>
                            </p>
                        </td>
                        <td>
                            <p data-id="purchaseProgress">60</p>
                            <p>
                            	<div class="gArea">
                                	<div data-id="purchaseProgress"  class="" style="width:60%;">색상</div>
                                </div>
                            </p>
                        </td>      
                        <td>
                            <p data-id="assemblyProgress">10%</p>
                            <p>
                            	<div class="gArea">
                                	<div data-id="assemblyProgress" class="" style="width:10%;">색상</div>
                                </div>
                            </p>
                        </td>
                        <td>
                            <p data-id="programProgress">0</p>
                            <p>
                            	<div class="gArea">
                                	<div data-id="programProgress" class="green" style="width:0%;">색상</div>
                                </div>
                            </p>
                        </td>                    
                    </tr>


                </tbody>
            </table>
        </div>
        <!-- /내용 -->
        
    </div>
    
</div>
	<script type="text/javascript">
	
	var nextPageNo = 1;
	var refreshInterval = 5000;
	var linesPerPage = 4;
	
	function makeTemplate(){
		// 이미 한줄은 있으니... 1줄 뺸 나머지 복사해서 만들기..
		for(var i = 0; i < (linesPerPage - 1); i++){
			
			var firstLine = $("tr.firstLine[data-rowId='0']").clone().attr("data-rowId", i + 1);
			var secondLine = $("tr.secondLine[data-rowId='0']").clone().attr("data-rowId", i + 1);

			$("tbody").append(firstLine);
			$("tbody").append(secondLine);
			
		}
	}
	
	function getProgressBarColor(progress){
		return progress > 80 ? "green" :
				progress > 50 ? "blue" :
				progress > 30 ? "yellow" : "red";					
	}
	
	// 데이터 표시
	function showData(data){
		for(var i=0;i<4;i++){			
			var firstLine = $("tr.firstLine[data-rowId='" + i + "']");
			var secondLine = $("tr.secondLine[data-rowId='" + i + "']");
			
			if(i < data.length){
				firstLine.find("td[data-id='orderNo']").text(data[i].orderNo);
				firstLine.find("td[data-id='device']").text(data[i].device);
				firstLine.find("td[data-id='partnerName']").text(data[i].partnerName);
				firstLine.find("td[data-id='installDate']").text(data[i].installDate);
				firstLine.find("td[data-id='status']").text(data[i].status);
				firstLine.find("td[data-id='orderDate']").text(data[i].orderDate);
				firstLine.find("td[data-id='designDate']").text(data[i].designDate);
				firstLine.find("td[data-id='processDate']").text(data[i].processDate);
				firstLine.find("td[data-id='purchaseDate']").text(data[i].purchaseDate);
				firstLine.find("td[data-id='assemblyDate']").text(data[i].assemblyDate);
				firstLine.find("td[data-id='programDate']").text(data[i].programDate);
				
				secondLine.find("p[data-id='designProgress']").text(data[i].designProgress + '%');
				secondLine.find("p[data-id='processProgress']").text(data[i].processProgress + '%');
				secondLine.find("p[data-id='purchaseProgress']").text(data[i].purchaseProgress + '%');
				secondLine.find("p[data-id='assemblyProgress']").text(data[i].assemblyProgress + '%');
				secondLine.find("p[data-id='programProgress']").text(data[i].programProgress + '%');

				secondLine.find("div[data-id='designProgress']").removeClass("blue red yellow green")
				.addClass(getProgressBarColor(data[i].designProgress))
				.css("width" , data[i].designProgress + "%");
				secondLine.find("div[data-id='processProgress']").removeClass("blue red yellow green")
				.addClass(getProgressBarColor(data[i].processProgress))
				.css("width" , data[i].processProgress + "%");
				secondLine.find("div[data-id='purchaseProgress']").removeClass("blue red yellow green")
				.addClass(getProgressBarColor(data[i].purchaseProgress))
				.css("width" , data[i].purchaseProgress + "%");
				secondLine.find("div[data-id='assemblyProgress']").removeClass("blue red yellow green")
				.addClass(getProgressBarColor(data[i].assemblyProgress))
				.css("width" , data[i].assemblyProgress + "%");
				secondLine.find("div[data-id='programProgress']").removeClass("blue red yellow green")
				.addClass(getProgressBarColor(data[i].programProgress))
				.css("width" , data[i].programProgress + "%");
				
				firstLine.show();
				secondLine.show();
			}else{
				firstLine.hide();
				secondLine.hide();
			}
		}
	}	
	
	function loadAndFill(){
		
		$.ajax({
			url : "/bulletin/order/data",
			data : {"pageNo" : nextPageNo },
			dataType : "json",
			success : function(obj){
				if(obj.result == "OK"){
					nextPageNo = obj.value;
					showData(obj.data);
				}else{
					nextPageNo = 1;
				}
				setTimeout(loadAndFill, refreshInterval);				// 어쟀든 다음 페이지~
			},
			error : function(){
				nextPageNo = 1;
				setTimeout(loadAndFill, refreshInterval);				// 에러나면 있다가 다시 해보자~	
			}
		});
	}
	
	$(document).ready(function(){
		makeTemplate();
		loadAndFill();
	});
	
	</script>

</body>
</html>
    