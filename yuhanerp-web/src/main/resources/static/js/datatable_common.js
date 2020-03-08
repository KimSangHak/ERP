/**
 * 
 */

// DataTable 에서 자주 사용하는 기능

var dataTableLanguageCommon = {
		"lengthMenu" : "Display _MENU_ records per page",
		"zeroRecords" : "표시할 데이터가 없습니다",
		"info" : "Showing page _PAGE_ of _PAGES_",
		"infoEmpty" : "No records available",
		"infoFiltered" : "(filtered from _MAX_ total records)"
	};

$(document).ready(function(){

	$("#checkAll").click(function(e){

		var checked = $(this).is(":checked");
		if(console)
			console.log("checked = " + checked);
		
		$(".RowCheckBox").each( function(){
			$(this).prop("checked", checked);
		});											
	});
	
});
