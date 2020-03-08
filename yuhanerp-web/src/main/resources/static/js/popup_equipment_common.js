/**
 * 
 */

$(document).ready(function(){
	
	$(".ClosePopupButton").click(function(e){
		e.preventDefault();
		
		if(confirm("창을 닫겠습니까?"))
			window.close();
	});
	
	// 종류 변경시 해당 페이지로 이동시킨다
	$("#EquipJobType").change(function(){
		var selected = $(this).val();
		
		if(selected == "job")
			location.href = "/daily_report/new_job_concept";
		else
			location.href = "/popup_equipment_concept_job";
	});
	
	loadPartnerListToSelect("#partner_list");
	loadUserListToSelect("business", "#business_user");		
	loadUserListToSelect("design", "#design_user");		
	
});

