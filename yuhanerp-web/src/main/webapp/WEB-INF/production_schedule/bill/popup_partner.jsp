<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/datatables.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">

	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
	<script type="text/javascript" src="/js/ux.js"></script>	
	<script type="text/javascript" src="/js/datatables.min.js"></script>
	<script type="text/javascript" src="/js/datatable_common.js"></script>
	
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>거래처 등록(도면 관리)</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
        
        	<!-- 테이블 콘텐츠 -->
            <div class="popList">

                <!-- 게시판 -->
                <div class="popBox">
                	<form method="POST" id="regForm" action="/ps/bill/submit/partner">
                	<input type="hidden" name="originalPartnerId" value="${partnerId }" />
                	<input type="hidden" name="corporateNum" id="corporateNum" />
                    <table>
                        <caption> </caption>
                        <colgroup span="2">
                        <col style="width:20%;" />
                        <col style="width:80%;" />
                        <tr>
                            <th>업체 종류 <span class="ncBlue">*</span></th>
                            <td>
                                <c:import url="/internal/util/select/partner_type">
                                	<c:param value="partnerType" name="controlName"/>
                                	<c:param value="width : 260px;" name="style"/>
                                </c:import>
                            </td>
                        </tr>
                        <tr>
                            <th>업체코드</th>
                            <td><input type="text" id="id" name="id" value="" style="width: 120px" readonly> 
                            <a href="#" id="MakeNewPartnerId" class="btn_line_gray">업체코드 자동생성</a></td>
                        </tr>
                        <tr>
                            <th>업체명 <span class="ncBlue">*</span></th>
                            <td><input type="text" name="partnerName" id="partnerName" value=""></td>
                        </tr>
                        
                        <tr>
                            <th>사업자 번호<span class="ncBlue">*</span></th>
                            <td><input type="text" name="corporateNum1" value="" id="corporateNum1" maxlength="3" style="width :80px"> - 
                            	<input type="text" name="corporateNum2" value="" id="corporateNum2" maxlength="2" style="width :80px"> - 
                            	<input type="text" name="corporateNum3" value="" id="corporateNum3" maxlength="5" style="width :80px">
                            	</td>
                        </tr>
                        <tr>
                            <th>주소<span class="ncBlue">*</span></th>
                            <td>
                            	<p><input type="text" value="" id="corporateAddr" name="corporateAddr"></p>
                            </td>
                        </tr>
                        <tr>
                            <th>담당자 연락처 <span class="ncBlue"></span></th>
                            <td><input type="text" name="personPhone" id="personPhone" value="" style="width :240px"></td>
                        </tr>
                        <tr>
                            <th>담당자 <br/>성함/직급 <span class="ncBlue"></span></th>
                            <td><input type="text" value="" id="personName" name="personName"  style="width :150px" > 
                           		<input type="text" value="" id="personPosition" name="personPosition" style="width :150px"></td>
                        </tr>
                        <tr>
                            <th>담당자 메일 <span class="ncBlue"></span></th>
                            <td><input type="text" value="" id="personMail" name="personMail"></td>
                        </tr>
                        <tr>
                            <th>회사 연락처 <span class="ncBlue">*</span></th>
                            <td><input type="text" value="" id="corporatePhone" name="corporatePhone"></td>
                        </tr>
                        <tr>
                            <th>회사 메일 <span class="ncBlue">*</span></th>
                            <td><input type="text" value="" id="corporateMail" name="corporateMail"></td>
                        </tr>
                        <tr>
                        
                         <tr>
                            <th>즐겨찾기여부 <span class="ncBlue"></span></th>
                            <td>
                               <select name="processEnjoy" id="w120">
                               	 <option value="Y">Y</option>
                               	 <option value="N">N</option>    
                               </select>
                            </td>
                        </tr>
                            <th>결제 기준</th>
                            <td>
                            	<p class="staDay">
                                	<span>작성일로부터 몇개월 뒤</span>
                                    <span>
		                                <c:import url="/internal/util/select/billing_after">
		                                	<c:param value="billingAfter" name="controlName"/>
		                                	<c:param value="width : 120px" name="style"/>
		                                </c:import>
                                    </span>
                                </p>
                                <p class="staDay">
                                	<span>선택한 개월 뒤 날짜</span>
                                    <span>
		                                <c:import url="/internal/util/select/billing_day">
		                                	<c:param value="billingDay" name="controlName"/>
		                                	<c:param value="width : 120px" name="style"/>
		                                </c:import>
                                    </span>
                                </p>
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
                <a href="#" class="btn_gray">취소</a>
                <a href="#" id="Submit" class="btn_blue">등록확인</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
        
        
	</div>
</div>

	<script type="text/javascript">
	
	$(document).ready(function(){
		
		var originalPartnerId = "${partnerId}";
		
		// 거래처 수정
		if(originalPartnerId != "" ){
			
			$.ajax({
				url : "/ps/bill/data/partner_info",
				data : {
					partnerId : originalPartnerId
				},
				type : "POST",
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK" && obj.data != null){						
						
						var expectedPartnerType = obj.data.typeCode + obj.data.typeKind; 
						$("#partnerType").val( expectedPartnerType );
						$("#partnerType").prop('disabled', true);
						$("#id").val(obj.data.id);
						$("#partnerName").val(obj.data.partnerName);
						$("#corporateNum1").val(obj.data.corporateNum1);
						$("#corporateNum2").val(obj.data.corporateNum2);
						$("#corporateNum3").val(obj.data.corporateNum3);
						$("#corporateAddr").val(obj.data.corporateAddr);
						$("#personPhone").val(obj.data.personPhone);
						$("#personName").val(obj.data.personName);
						$("#personPosition").val(obj.data.personPosition);
						$("#personMail").val(obj.data.personMail);
						$("#corporatePhone").val(obj.data.corporatePhone);
						$("#corporateMail").val(obj.data.corporateMail);
						$("#billingAfter").val(obj.data.billingAfter);
						$("#billingDay").val(obj.data.billingDay);
						$("#processEnjoy option:selected").val(obj.data.processEnjoy);
						if(obj.data.processEnjoy == "Y"){
							$('select[name="processEnjoy"]').find('option:contains("Y")').attr("selected",true);
						}else{
							$('select[name="processEnjoy"]').find('option:contains("N")').attr("selected",true);
							
						}
						
						$("#Submit").text("수정");
						$("#MakeNewPartnerId").hide();
					}else{
						// error
						$("#Submit").hide();
						alert("거래처 정보를 찾을 수 없습니다");						
					}
				},error : function(){
					$("#Submit").hide();
					alert("데이터를 불러 올 수 없습니다");
				}
			});			
		}
		
		// [업체코드 자동생성] 클릭
		$("#MakeNewPartnerId").click(function(e){
			e.preventDefault();
			
			$("#partnerId").val("");
			$.ajax({
				url: "/ps/bill/data/next",
				data : {"partnerTypeId" : $("#partnerType").val()},
				dataType : "json",
				success : function(obj){
					if(obj.result == "OK")
						$("#id").val(obj.data);
					else
						alert(obj.reason);					
				},
				error : function(){
					alert("서버 오류로 처리할 수 없습니다");
				}
			});
		});
		
		$("#partnerType").change(function(){
			$("#id").val("");
		});
		
		$("#Submit").click(function(e){
			e.preventDefault();
			
			$("#regForm").ajaxSubmit({
				type : "POST",
				dataType : "json",
				beforeSerialize : function(){
					
					if( $("#id").val() == ""){
						alert("업체 코드를 입력해 주세요");
						return false;
					}
					
					$("#corporateNum").val( $("#corporateNum1").val() + '-' + $("#corporateNum2").val() + '-' + $("#corporateNum3").val()  );
					if( $("#corporateNum").val().length != 12){
						alert("사업자 번호를 올바르게 입력해 주세요" + $("#corporateNum").val());
						return false;
					}
					return true;
				},
				success : function(obj){
					if(obj.result == "OK"){
						alert("저장되었습니다");
						opener.location.reload();
						window.close();
					}else
						alert(obj.reason);
				},
				error : function(){
					alert("서버 오류로 처리 할 수 없습니다");
				}
			});
			
		});
	});
	
	</script>
</body>
</html>
