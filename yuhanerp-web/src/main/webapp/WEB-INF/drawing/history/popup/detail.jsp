<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	// 메인 페이지용 마스터 페이지
%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="utf-8">
<title>Yuhan NCI - 도면 이력 상세보기</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>

    <script type="text/javascript" src="/js/menu.js"></script>
    
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>도면이력 상세보기</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        	<!-- 팝업 서브타이틀 -->
            <div class="pcTit">
            	<form method="get">
            	<input type="hidden" name="orderId" value="${orderId }" />
                <div class="btn_area">
                	<span>
                    	<select name="classification" class="w100">
                        	<option value="">분류</option>
                            <option value="신규제작">신규</option>
                            <option value="추가제작">추가제작</option>
                            <option value="재제작">재제작</option>
                            <option value="수정">수정</option>
                            <option value="구매">구매</option>
                            <option value="재료구매">재료구매</option>
                            <option value="도면교체">도면교체</option>
                            <option value="재출도">재출도</option>
                            <option value="A/S제작">A/S제작</option>
                        </select>
                    </span>
                	<span>
                    	<select name="reason" class="w100">
                        	<option value="">사유</option>
                            <option value="설계불량">설계불량</option>
                            <option value="업체요구">업체요구</option>
                            <option value="가공불량">가공불량</option>
                            <option value="조립불량">조립불량</option>
                            <option value="공정불량">공정불량</option>
                            <option value="설계보완">설계보완</option>
                            <option value="가공보완">가공보완</option>
                            <option value="조립보완">조립보완</option>
                            <option value="기타">기타</option>
                        </select>
                    </span>
                </div>
                </form>
            </div>
            <!-- //팝업 서브타이틀 -->
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:18%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        </colgroup>
                        <tr>
                        	<th>Order No.</th>
                            <th>업체</th>
                            <th>Device</th>
                            <th>종류</th>
                            <th>납품일</th>
                            <th>영업담당</th>
                            <th>설계담당</th>
                            <th>출도일</th>
                            <th>도면수량</th>
                        </tr>
                        <tr>
                        	<td>${jobData.orderNo }</td>
                            <td>${jobData.customerName }</td>
                            <td id="taLeft">${jobData.device }</td>
                            <td>${jobData.isJigText() }</td>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${jobData.installDate}" /></td>
                            <td>${jobData.businessUserName }</td>
                            <td>${jobData.designUserName }</td>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${jobData.designEnd}" /></td>
                            <td>${jobData.designCount }</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 -->
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_blue" id="MakeExcel">엑셀저장</a>
                <a href="#" class="btn_blue" id="addExtra">추가출도</a>
            </div>
            <!-- //팝업 버튼 -->

            <!-- 팝업 서브타이틀 -->
            <div class="pcTit mt20">
                <h2>상세 리스트</h2>
            </div>
            <!-- //팝업 서브타이틀 --> 
                
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBoard">
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:10%;" />
                        <col style="width:15%;" />
                        <col style="width:8%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:5%;" />
                        <col style="width:7%;" />
                        <col style="width:7%;" />
                        <col style="width:6%;" />
                        <col style="width:7%;" />
                        <col style="width:5%;" />
                        <col style="width:8%;" />
                        </colgroup>
                        <tr>
                        	<th>도면번호</th>
                            <th>Description</th>
                            <th>Dimensions</th>
                            <th>Mat’l</th>
                            <th>열처리</th>
                            <th>수량</th>
                            <th>Spare</th>
                            <th>후처리</th>
                            <th>출도일</th>
                            <th>분류</th>
                            <th>사유</th>
                            <th>검사</th>
                            <th>비고</th>
                        </tr>
						<c:if test="${drawData != '' || drawData ne null}">
						<c:forEach items="${drawData }" var="item">
                        <tr>
                        	<td>${item.designDrawingNo }</td>
                            <td>${item.description }</td>
                            <td>${item.dimension }</td>
                            <td>${item.material }</td>
                            <td>${item.thermal }</td>
                            <td>${item.quantity }</td>
                            <td>${item.spare }</td>
                            <td>${item.postprocessing }</td>
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.designDate}" /></td>
                            <td>${item.classification }</td>
                            <td>${item.reason }</td>
                            <td>${item.checking }</td>
                            <td>${item.note }</td>
                        </tr>
						</c:forEach>
						</c:if>
                    </table>
                </div>
                <!-- //게시판 -->
            </div>
            <!-- //테이블 콘텐츠 -->

        </div>
        <!-- //팝업 콘텐츠 -->
        
	</div>
</div>
<script type="text/javascript" src="/js/drawing_commonNew.js"></script>
    
<script type="text/javascript">

	$(document).ready(function(){
		
		// 콤보박스 건들면 바로 폼 전송
		$("select").change(function(){
			$("form").submit();
		});
		
		// 기본값
		$("select[name='classification']").val( "${classification}" );
		$("select[name='reason']").val( "${reason}" );		
		$("#MakeExcel").click(function(e){
			e.preventDefault();
			alert("양식 확정 필요~");
		});
		
		$("#addExtra").click(function(e){
			e.preventDefault();
			
			openDesignDrawingRegWindowNew(${orderId });
		});
	});
</script>


</body>
</html>
