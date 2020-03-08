<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<layout:extends name="/WEB-INF/layout/MctMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>QC</li>
                        <li>검사 현황</li>
                    </ul> 
                    <h2>검사 완료 리스트 </h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                    <!-- 테이블 콘텐츠 -->
                    
                	<!-- 테이블 타이틀 -->
                	<form method="GET" id="FindForm">
                    <div class="searchArea">
                    	<div class="searchBox">
                    		
                        	<table>
                            	<caption> </caption>
                                <col style="width:10%;;" />
                                <col style="width:30%;" />
                                <col style="width:10%;" />
                                <col style="width:30%;" />
                                <tr>
                                    <th>도면번호</th>
                                    <td>
                                        <input type="text" id="orderNoBase" name="orderNoBase" value="${orderNoBase}" class="w100"> - <input type="text" id="orderNoExtra" name="orderNoExtra" value="${orderNoExtra }" class="w100"> - <input type="text" id="drawingNo" name="drawingNo" value="${drawingNo }" class="w100">
                                    </td>
                                    <th>검사완료일</th>
                                    <td>
                                        <input type="text" name="mctDateFrom" id="mctDateFrom" value="${mctDateFrom}" class="w100 AutoDatePicker"> - <input type="text" name="mctDateTo" id="mctDateTo" value="${mctDateTo }" class="w100 AutoDatePicker">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="searchBtn">
                        	<a href="#" class="btn_blue" id="FindButton">검색</a>
                        </div>
                    </div>
                    </form>
                    <!-- //테이블 타이틀 -->
                    
                    <div class="listArea">

                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL">
                            	<caption> </caption>
                                <colgroup span="2">
                                <col style="width:7%;" />
                                <col style="width:13%;" />
                                <col style="width:9%;" />
                                <col style="width:9%;" />
                                <col style="width:6%;" />
                                <col style="width:5%;" />
                                <col style="width:5%;" />
                                <col style="width:6%;" />
                                <col style="width:7%;" />
                                <col style="width:7%;" />
                                <col style="width:4%;" />
                                <col style="width:5%;" />
                                <col style="width:5%;" />
                                <col style="width:5%;" />
                                <col style="width:7%;" />
                                </colgroup>
                            	<thead>
                                	<tr>
                                        <th>도면번호</th>
                                        <th>Discription</th>
                                        <th>사내가공/<br/>외주업체</th>
                                        <th>후처리업체</th>
                                        <th>Mat’l</th>
                                        <th>열처리</th>
                                        <th>수량</th>
                                        <th>가공완료일</th>
                                        <th>검사인수일</th>
                                        <th>생관인수일</th>
                                        <th>판정</th>
                                        <th>합격<br/>수</th>
                                        <th>불합<br/>격수</th>
                                        <th>인수자</th>
                                        <th>중지사유</th>
                                    </tr>
                                </thead>

                                <tbody>
                                <!-- 
                                	<tr>
                                    	<td><input type="checkbox"></td>
                                        <td>D001-161030-001</td>
                                        <td>남기홍</td>
                                        <td>A6061</td>
                                        <td>&nbsp;</td>
                                        <td>10</td>
                                        <td>2</td>
                                        <td>Nickel</td>
                                        <td>H테크</td>
                                        <td>17.09.11</td>
                                        <td>Y</td>
                                        <td>17.09.12</td>
                                        <td>OK</td>
                                        <td><a href="#" class="btn_file">파일</a></td>
                                        <td>후처리 중</td>
                                    </tr>
                                    -->
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
		<style type="text/css">
			.dataTables_wrapper .ui-toolbar {
				padding : 0px ;
			}
		</style>
		<script type="text/javascript">
		
		function getFindParameter(){
			return "?orderNoBase=" + escape( $("#orderNoBase").val() ) + "&orderNoExtra=" + escape( $("#orderNoExtra").val() )
			 + "&drawingNo=" + escape( $("#drawingNo").val() ) + "&mctDateFrom=" + escape( $("#mctDateFrom").val() )  + "&mctDateTo=" + escape( $("#mctDateTo").val() );
		}
		
		$(document).ready(function(){
			
			// 검색버튼
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				location.href=getFindParameter();  
			});

			// 입고 내역 불러오기
			$("#TBL").DataTable({
				"paging" : true,
				"lengthMenu" : [20],
				"lengthChange" : false,
				"ordering" : false,
				"info" : false,
				"searching" : false,

				"language" : {
					"lengthMenu" : "Display _MENU_ records per page",
					"zeroRecords" : "표시할 데이터가 없습니다",
					"info" : "Showing page _PAGE_ of _PAGES_",
					"infoEmpty" : "No records available",
					"infoFiltered" : "(filtered from _MAX_ total records)"
				},
				"serverSide" : true,
				"ajax" : {
					"url" : "/qcPgm/qcFinishList/data",
					"data" : function(d){
						d.orderNoBase = $("#orderNoBase").val();
						d.orderNoExtra = $("#orderNoExtra").val();
						d.drawingNo = $("#drawingNo").val();
						d.mctDateFrom = $("#mctDateFrom").val();
						d.mctDateTo = $("#mctDateTo").val();
					}
				},
				"columns" : [						
					{"data" : "drawingFullNo"},
					{"data" : "description"},
					{"data" : "outSourceName"},
					{"data" : "coatingParterName"},
					{"data" : "material"},
					{"data" : "thermal"},
					{"data" : "quantity"},
					{"data" : "finishDate"},
					{"data" : "getDate"},
					{"data" : "deliveDate"},
					{"data" : "resultOk"},
					{"data" : "okQuantity"},
					{"data" : "failQuantity"},
					{"data" : "putToName"},
					{"data" : "holdReason"}
				]
			});    
		});

		</script>
 
	</layout:put>
</layout:extends>