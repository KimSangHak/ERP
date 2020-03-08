
	
function openDesignDrawingRegWindow(id){
	if(id == null || id === undefined){
		alert("설비 진행 도면 출도 팝업을 위한 ID 를 찾을 수 없습니다.");
		return;
	}
	PopWin('/drawing/issue/popup/design?jobOrderId=' + id,'1000','680','no');		
}

$(document).ready(function(){
	
	// 설비 진행 도면 출도 
	$(".popup_job_progress_design").click(function(e){
		e.preventDefault();
		
		var TR = $(this).parent().parent();
		var id = TR.attr("data-id");
		
		openDesignDrawingRegWindow(id);
	});	
	
});