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
<title>Yuhan NCI</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script language="JavaScript" src="/js/menu.js"></script>

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
                <div class="btn_area">
                	<span>
                    	<select class="w100">
                        	<option>분류</option>
                            <option>신규</option>
                            <option>추가제작</option>
                            <option>재제작</option>
                            <option>수정</option>
                            <option>구매</option>
                            <option>재료구매</option>
                            <option>도면교체</option>
                            <option>재출도</option>
                            <option>A/S제작</option>
                        </select>
                    </span>
                	<span>
                    	<select class="w100">
                        	<option>사유</option>
                            <option>설계불량</option>
                            <option>업체요구</option>
                            <option>가공불량</option>
                            <option>조립불량</option>
                            <option>공정불량</option>
                            <option>설계보완</option>
                            <option>가공보완</option>
                            <option>조립보완</option>
                            <option>기타</option>
                        </select>
                    </span>
                </div>
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
                            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${jobData.designDate}" /></td>
                            <td>${jobData.designCount }</td>
                        </tr>
                    </table>
                </div>
                <!-- //게시판 -->
                
            </div>
            <!-- //테이블 콘텐츠 -->
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_blue">엑셀저장</a>
                <a href="#" class="btn_blue">추가출도</a>
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
            
            <!-- 페이징 
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
            //페이징 -->
                
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>
</body>
</html>
