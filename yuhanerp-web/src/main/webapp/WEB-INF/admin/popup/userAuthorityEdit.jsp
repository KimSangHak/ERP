<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	

    <script language="JavaScript" src="/js/menu.js"></script>
    
    
    <script type="text/javascript">
    
    
    /*
    	function checkRecord(){
		
			var allChecked = $("input[name='chk_info']:checked");
			if(allChecked.length == 0){
				alert("선택된 항목이 없습니다");
				return false;
			}
		
		}
    */
    
    
    	function getAllTitle(){
			var ret = [];
			var allChecked = $("input[name='chk_info']:checked");
			
			if(allChecked.length == 0){
				alert("선택된 항목이 없습니다");
				return false;
			}

			
			if(allChecked.length > 0){
		
				for(var i=0;i<allChecked.length;i++){
				
					var menuTitle =  allChecked.eq(i).val();
					ret.push( menuTitle );
				}					
			}
			return ret;
		}
    
    	function getAllVisible(){
			var ret = [];
			var allChecked = $("input[name='chk_visible']:checked");
		
			if(allChecked.length == 0){
				alert("선택된 항목이 없습니다");
				return false;
			}

		
			if(allChecked.length > 0){
	
				for(var i=0;i<allChecked.length;i++){
			
					var menuTitle =  allChecked.eq(i).val();
					ret.push( menuTitle );
				}					
			}
			return ret;
		}
    	
    	function getAllUpdate(){
			var ret = [];
			var allChecked = $("input[name='chk_update']:checked");
		
			if(allChecked.length == 0){
				
				return null;
			}

		
			if(allChecked.length > 0){
	
				for(var i=0;i<allChecked.length;i++){
			
					var menuTitle =  allChecked.eq(i).val();
					ret.push( menuTitle );
				}					
			}
			return ret;
		}
    
    
    	
		
    	$(document).ready(function(){
    	$("#EditBtn").click(function(){
			
    		
    		if(!getAllTitle()){
    			
    		}
    		
    		else{
    			
    			var chk = getAllTitle();
    			var visible = getAllVisible();
    			var update = getAllUpdate();
    
    			document.form1.chks.value=chk;
    			document.form1.visibles.value=visible;
    			document.form1.updates.value=update;
    		
    		
    		
    			var userId = $("#userId").val();
    			var chks = $("#chks").val();
    			var visibles = $("#visibles").val();
    			var updates = $("#updates").val();
    		
        
       			if(confirm("직원 권한을 수정하시겠습니까? 바로 적용됩니다.")){
        	
       				document.form1.target="parent";
       				document.form1.action = "${path}/Admin/popup/userAuthorityEditAction";
       				document.form1.submit();
       				self.close();
        	
        		}
    		}
  
    });
    
    $("#escBtn").click(function(){
   
    	self.close();
    });
    
    
    
    
    $("input[name=chk_info]:checkbox").on('click', function(){
        if($(this).prop('checked')){
        	
        	var trid = $(this).closest('tr').attr('id');
        	var thid = $(this).closest('th').attr('id');
        	
        	$("#chk_visible"+trid).prop("checked", true);
        	
        	if(thid == 'Y'){
        		alert("해당 page는 Update 권한을 부여해야만 visible 권한이 부여됩니다. visible 권한만 부여 될 수 없습니다.");
    			$("#chk_visible"+trid).prop("checked", false);
    			$("#chk_info"+trid).prop("checked", false);
        	}
        
        }else {
			var trid = $(this).closest('tr').attr('id');
        	
        	$("#chk_visible"+trid).prop("checked", false);
        	$("#chk_update"+trid).prop("checked", false);
        	
           
        }
    });
    
    $("input[name=chk_visible]:checkbox").on('click', function(){
        if($(this).prop('checked')){
        	
        	var trid = $(this).closest('tr').attr('id');
        	var tdid = $(this).closest('td').attr('id');
        	
        	$("#chk_info"+trid).prop("checked", true);
        	
        	if(tdid == 'Y'){
        		
        			alert("해당 page는 Update 권한을 부여해야만 visible 권한이 부여됩니다. visible 권한만 부여될수 없습니다.");
        			$("#chk_visible"+trid).prop("checked", false);
        			$("#chk_info"+trid).prop("checked", false);
        
        	}
        
        }else {
			var trid = $(this).closest('tr').attr('id');
        	
        	$("#chk_info"+trid).prop("checked", false);
        	$("#chk_update"+trid).prop("checked", false);
        	
           
        }
    });
    
    
    $("input[name=chk_update]:checkbox").on('click', function(){
        if($(this).prop('checked')){
        	
        	var trid = $(this).closest('tr').attr('id');
        	var tdid = $(this).closest('td').attr('id');
        	
        	$("#chk_info"+trid).prop("checked", true);
        	$("#chk_visible"+trid).prop("checked", true);
        
        }else{
        	
        	var trid = $(this).closest('tr').attr('id');
        	var tdid = $(this).closest('td').attr('id');
        	
        	if(tdid == 'Y'){
        		$("#chk_info"+trid).prop("checked", false);
        		$("#chk_visible"+trid).prop("checked", false);
        	}
        }
    });
    
    
    
    
    
	});
	</script>

</head>

<body class="sub">
<form id="form1" name="form1" enctype="multipart/form-data" method="post">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>직원 권한 수정</h1>
        </div>
     
        <div class="popCont">
        
        	<div>
        		
        		
        			
        			<div class="popBox">
    					<table>
        					<caption> </caption>
        					<colgroup span="2">
        					<col style="width:50%;" />
        					<col style="width:50%;" />
					
						<c:set var="No" value="${pageNo + 1}"></c:set>
						<c:forEach items="${Menu}" var="Menu">
						<c:set var="highCode" value="${Menu.highCode}"></c:set>
						<c:set var="lowCode" value="${Menu.lowCode}"></c:set>
						<c:set var="listCode" value="${Menu.listCode}"></c:set>
						<c:set var="shim" value="a"></c:set>
						<c:set var="V" value="${highCode}${shim}${lowCode}${shim}${listCode}"></c:set>
						
        					<tr id="${No}">
            					<th id="${Menu.upAndview}">${Menu.title}&ensp;
            							<input type="checkbox" name="chk_info" id="chk_info${No}" value="${V}"
											<c:forEach items="${titleVisible}" var="titleVisible">
												<c:if test="${titleVisible.title == Menu.title}">checked</c:if>
											</c:forEach>
											<c:forEach items="${titleUpdate}" var="titleUpdate">
												<c:if test="${titleUpdate.title == Menu.title}">checked</c:if>
											</c:forEach>
										>
								</th>
            					<td id="${Menu.upAndview}">
            					Visible&ensp;
            						<input type="checkbox" name="chk_visible" id="chk_visible${No}" value="Y"
            							<c:forEach items="${titleVisible}" var="titleVisible">
											<c:if test="${titleVisible.title == Menu.title}">checked</c:if>
										</c:forEach>
										<c:forEach items="${titleUpdate}" var="titleUpdate">
											<c:if test="${titleUpdate.title == Menu.title}">checked</c:if>
										</c:forEach>
            						
            						> 
            					Update&ensp;
            						<input type="checkbox" name="chk_update" id="chk_update${No}" value="${V}"
            							<c:forEach items="${titleUpdate}" var="titleUpdate">
											<c:if test="${titleUpdate.title == Menu.title}">checked</c:if>
										</c:forEach>
            						>
            					</td>
        					</tr>
        				<c:set var="No" value="${No + 1 }" />
        				</c:forEach>
                    
    					</table>
					</div>
        			 
        			 
        			 
        		
        
        	</div>
        
        
             <input type="hidden" id="userId" name="userId" value="${userId}">
             <input type="hidden" name="chks" value="">
             <input type="hidden" name="visibles" value="">
             <input type="hidden" name="updates" value=""> 
     
            <div class="popBtn">
                <a href="#" class="btn_gray" id="escBtn">취소</a>
                <a href="#" class="btn_blue" id="outBtn">퇴사처리</a>
                <a href="#" class="btn_blue" id="EditBtn">수정</a>
                
            </div>
     
            
        </div>

        
        
        
	</div>
</div>
</form>
</body>
</html>
