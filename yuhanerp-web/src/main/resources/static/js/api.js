/**
 * 
 */

// selector 에 업체 리스트 불러오는 코드
function loadPartnerListToSelect(selector, default_select_value){
	
	$(selector).empty();
	$.ajax({
		url : "/api/list/customer",
		dataType : "json",
		success : function(obj){

			for(var i=0;i<obj.length;i++){
				
				var option = $("<option/>")
								.attr("value", obj[i].id)
								.text(obj[i].name)
								.prop("selected", obj[i].id == default_select_value);
								
				$(selector).append( option );
				
			}			
			
			$(selector).trigger("change");
		},
		error : function(){
			alert("서버 오류로 업체 목록을 읽을 수 없습니다");
		}
	});	
	
}

//selector 에 영업/설계 작업자 리스트 불러오는 코드
function loadUserListToSelect(category, selector, default_select_value){
	
	if(!(category == "business" || category == "design")){
		alert("잘못된 카테고리 ID[" + category + "] 입니다");
		return;
	}
	
	$(selector).empty();
	
	$.ajax({
		url : "/api/list/user/" + category + "/",
		dataType : "json",
		success : function(obj){

			for(var i=0;i<obj.length;i++){
				
				var option = $("<option/>")
								.attr("value", obj[i].key)
								.text(obj[i].value)
								.prop("selected", obj[i].key == default_select_value);
				
				$(selector).append( option );				
			}			
			
		},
		error : function(){
			alert("서버 오류로 업체 목록을 읽을 수 없습니다");
		}
	});		
}

// Order-No 에 사용할 순번을 구하는데 사용
// 순번을 구한 후 after(순번) 호출해준다
function getNextOrderSequenceNo(where, partnerId, orderType, after){
	
	if(where === undefined){
		alert("작업 지시번호 순번을 요청하려면  where 가 필요합니다");
		return;
	}
	if(partnerId === undefined || partnerId.length != 2){
		alert("작업 지시번호 순번을 요청하려면  업체ID 2자리가 필요합니다");
		return;
	}
	
	orderType = orderType.toUpperCase();
	if(!(orderType == "JOB" || orderType == "JIG")){
		alert("orderType 은 JOB 또는 JIG 여야 합니다");
		return;
	}
	
	if(after == null){
		alert("Order-No 순번을 통지할 callback 이 없습니다");
		return;
	}
	
	$.ajax({
		url : "/api/orderno/next/" +where + "/" + partnerId + "/" + orderType
		, dataType : "json"
		, success : function(obj){
			if(obj.result == "OK"){
				after(obj.value);
				return;
			}
			alert(obj.reason);
		}, error : function(){
			alert("서버 오류로 순번을 확인 할 수 없습니다");
		}	
	})
	
}
