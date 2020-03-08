<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"	prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<layout:extends name="/WEB-INF/layout/ProductionScheduleMaster.jsp">

	<layout:put block="mainbody">
                <!-- 서브 타이틀 -->
                <div class="titArea">
                    <ul class="navi">
                        <li><span class="icon_home">홈</span></li>
                        <li>거래명세서 관리</li>
                        <li>거래처 관리</li>
                    </ul>
                    <h2>거래처 관리</h2>
                </div>
                <!-- 서브 타이틀 -->
                
                <!-- 서브 콘텐츠 -->
                <div class="contArea">
                
                    
                    <!-- 테이블 콘텐츠 -->
                    <div class="listArea">
                    
                    	<!-- sort -->
                    	<div class="sort">
                            <ul class="sortLeft">
                            	<li>
                                	<select id="sortOrder" class="w100">
                                        <option value="0" selected="selected">업체명</option>
                                        <option value="1">업체 코드</option>
                                        <option value="2">업체 종류</option>
                                    </select>
                                </li>
                            </ul>
                            
                            		<c:choose>
                         				<c:when test="${isupdate eq 'Y'}">
                         					<ul class="sortRight">
                            					<li><a href="#" id="registerPartner" class="btn_line_blue">거래처 등록</a></li>
                            				</ul>
                         				</c:when>
                         				<c:otherwise>
                         						
                         				</c:otherwise>                       				
                         			</c:choose>
                            
                        </div>
                        <!-- //sort -->
                        
                        <!-- 게시판 -->
                        <div class="listBox">
                        	<table id="TBL" style="width : 100%">
                            	<thead>
                                	<tr>
                                        <th rowspan="2">업체 코드</th>
                                        <th rowspan="2">업체명</th>
                                    	<th rowspan="2">업체 종류</th>
                                        <th rowspan="2">사업자 번호</th>
                                        <th rowspan="2">주소</th>
                                        <th colspan="3">담당자</th>
                                        <th rowspan="2">결제 기준</th>
                                    </tr>
                                    <tr>
                                    	<th>연락처</th>
                                        <th>이름</th>
                                        <th>직급</th>
                                    </tr>
                                </thead>
                                
                                <tbody>
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
	
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	<script type="text/javascript">
	
		var mainTable;
	
		$(document).ready(function(){

			// 정렬 순서 변경
			$("#sortOrder").change(function(){
				
				mainTable.ajax.reload();
			});
			
			// 거래처 등록
			$("#registerPartner").click(function(e){
				e.preventDefault();
				
				PopWin('/ps/bill/popup/partner','1000','600','no');
			});
			
			// 업체명 클릭시 팝업
			$(document).on("click", ".PartnerRowClick", function(e){
				e.preventDefault();

				var id = $(this).attr("data-id");				
				PopWin('/ps/bill/popup/partner?id=' + encodeURI(id) ,'1000','600','no');				
			});
			
			// 거래명세표 불러오기
			mainTable = $("#TBL").DataTable({
				"paging" : true,
				"lengthChange" : false,
				"lengthMenu" : yerpPaingSize,
				"ordering" : false,
				"info" : false,
				"searching" : false,
				"language" : dataTableLanguageCommon,
				"serverSide" : true,
				"ajax" : {
					url : "/ps/bill/data/partner"			,
					data : function(d){
						d.sortOrder = $("#sortOrder").val();
					}					
				},
				"columns" : [
					{"data" : "id"},
					{"data" : "partnerName", render : function(data, type, full){
						return '<a href="#" class="PartnerRowClick" data-id="' + full.id + '">' + data + '</a>';
					}},					
					{"data" : "partnerTypeName"},					
					{"data" : "corporateNum"},
					{"data" : "corporateAddr"},
					{"data" : "personPhone"},
					{"data" : "personName"},					
					{"data" : "personPosition"},					
					{"data" : null, "render" : function(data, type, full){
						return (full.billingAfter != null && full.billingDay != null) ?full.billingAfter + "/" + full.billingDay : "-";
					} }			
				]
			});
			
		});
	
	
	</script>	
	
	</layout:put>
</layout:extends>