<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

    <script language="JavaScript" src="/js/menu.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
  <script type="text/javascript">
  
  window.name="parent2";
  
	
	
  </script>

</head>

<body class="sub">
<form id="form1" name="form1" method="post">
<div id="container">



	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>거래명세표</h1>
        </div>
        <!-- //팝업 타이틀 -->
        
          <div class="pcTit mt20">
          		
                <h1>거래명세표</h1>
                </br>
          		</br>
           		<div class="btn_area">
           			<span>거래명세일 : <fmt:formatDate value="${issueDate}" pattern="yyyy-MM-dd" /> </span>
           		</div>
            </div>
            
            <div class="popBoard">
					<table id="Supplier" style="width : 300px"  />
						<colgroup>
							<col style="width : 100px;" />
							<col style="width : 100px;" />
							<col style="width : 100px;"/>
						</colgroup>
						<thead>
						</thead>
						<tbody>
							<tr>
								<th rowspan="3">공급자</th>
								<th>사업자번호</th>
								<td>${partner.corporateNum }</td>
							</tr>
							<tr>
								<th>상호</th>
								<td>${partner.partnerName }</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${partner.corporatePhone }</td>
							</tr>
						</tbody>
					</table>
				</div>
    
    
                <!-- 테이블 콘텐츠 -->
                <div class="popList">
                
                
                  
        	<!-- 테이블 콘텐츠 -->
            <div class="listArea">
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	  <table>
                                        <caption> </caption>
                                        <colgroup span="2">
      
                                        <col style="width:15%;" />
                                        <col style="width:10%;" />
                                        <col style="width:10%;" />
                                        <col style="width:5%;" />
                                        <col style="width:15%;" />
                                        <col style="width:15%;" />
                                        <col style="width:15%;" />
                                        <col style="width:15%;" />
                                   
                                    
                                        </colgroup>
                                        <tr>
                                            
                                            <th>OderNo</th>
                                            <th>품명</th>
                                            <th>ModelNo</th>
                                            <th>수량</th>
                                            <th>공급단가</th>
                                            <th>공급가액</th>
                                            <th>세액</th>
                                            <th>합계</th>

                                          

                                         
                                            
                                         
                                        </tr>
                                        
                                        <c:set var="supply" value="${pageNo}"></c:set>
                                        <c:set var="sumtax" value="${pageNo}"></c:set>
                                        <c:set var="sumpre" value="${pageNo}"></c:set>
                                 
                                 		
                                        <c:forEach items="${data}" var="line">
                                     	
                                     	<fmt:parseNumber var="unitsum" value="${line.issuedUnitPrice * line.issuedQuantity}" integerOnly="true"/>
                                     	<fmt:parseNumber var="tax" value="${unitsum * 0.1}" integerOnly="true"/>
                                     	<fmt:parseNumber var="unitsumtax" value="${unitsum + tax}" integerOnly="true"/>
                                     	

                                        <tr>
                                        
                                            
                                            <td>${line.jobOrderNo}</td>
                                            <td>${line.description}</td>
                                            <td>${line.modelNo}</td>
                                            <td>${line.issuedQuantity}</td>
                                            <td><fmt:formatNumber value="${line.issuedUnitPrice}" pattern="#,###" /></td>
                                            <td><fmt:formatNumber value="${unitsum}" pattern="#,###" /></td>
                                            <td><fmt:formatNumber value="${tax}" pattern="#,###" /></td>
                                            <td><fmt:formatNumber value="${unitsumtax}" pattern="#,###" /></td>
                                     
                                            
                                           
                                        </tr>
                                     	<c:set var="supply" value="${supply + unitsum}" />
                                     	<c:set var="sumtax" value="${sumtax + tax}" />
                                     	<c:set var="sumpre" value="${sumpre + unitsumtax}" />
                           				
                                        </c:forEach>
                                        
                                        <fmt:parseNumber var="nego" value="${negoPrice * -1}" integerOnly="true"/>
                                        <fmt:parseNumber var="taxnego" value="${nego * 0.1}" integerOnly="true"/>
                                        <tr>
                                        	<td colspan="5">네고 전 합계 금액</td>
                                        	<td><fmt:formatNumber value="${supply}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${sumtax}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${sumpre}" pattern="#,###" />&nbsp;&nbsp;&nbsp;(tax포함)</td>
                                        </tr>
                                        
                                        
                                        <fmt:parseNumber var="negoTaxnego" value="${nego + taxnego}" integerOnly="true"/>
                                        <tr>
                                        	<td colspan="5">네고 금액</td>
                                        	<td><fmt:formatNumber value="${nego}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${taxnego}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${negoTaxnego}" pattern="#,###" />&nbsp;&nbsp;&nbsp;(tax포함)</td>
                                        </tr>
                                        
                                        <fmt:parseNumber var="negoafter" value="${supply + nego}" integerOnly="true"/>
                                        <fmt:parseNumber var="taxafter" value="${sumtax + taxnego}" integerOnly="true"/>
                                        
                                        <tr>
                                        	<td colspan="5">네고 후 최종 금액</td>
                                        	<td><fmt:formatNumber value="${negoafter}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${taxafter}" pattern="#,###" /></td>
                                        	<td><fmt:formatNumber value="${sumPrice + taxafter}" pattern="#,###" />&nbsp;&nbsp;&nbsp;(tax포함)</td>
                                        </tr>
                                    </table>
                           
                           
                           
                                    
                        </div>
                        <!-- //게시판 -->
                        
                    </div>
                    <!-- //테이블 콘텐츠 -->
            <!-- //테이블 콘텐츠 -->

                
            
        </div>

   


</div>
</form>
</body>
</html>
