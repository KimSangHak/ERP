
$(document).ready(function(){
	
	// 견적가 수정 팝업
	$(".edit_estimation").click(function(e){
		e.preventDefault();
		var id = $(this).parent().parent().attr("data-id");	// tr
		if(id == null || id === undefined){
			alert("견적가 수업 팝업을 위한 ID 를 찾을 수 없습니다");
			return;
		}
		
		PopWin('/daily_report/popup/edit_estimation?jobId=' + id,'600','580','no');
	});	
	
	
});