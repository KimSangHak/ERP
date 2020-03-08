
var standardDatePickerInitParam = {
		changeMonth : true,
		changeYear : true,
		showButtonPanel : true,
		nextText : "다음달",
		prevText : "이전달",
		closeText : "닫기",
		currentText : "오늘",
		numberOfMonths : [1,1],
		dateFormat : "yy-mm-dd",
		dayNames : ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
		dayNamesMin : ["일", "월", "화","수","목","금","토"],
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
	};

$(document).ready(function(){
	
	$(".AutoDatePicker").prop("readonly", true);	
	$(".AutoDatePicker").datepicker( standardDatePickerInitParam );			
	
});

// 스크립트에 의해 생성되는 date-picker 초기화
$(document).on("focus", ".lazyAutoDatePicker", function(){

	$(this).prop("readonly", true);	
	$(this).datepicker( standardDatePickerInitParam );
	$(this).removeClass("lazyAutoDatePicker");

});