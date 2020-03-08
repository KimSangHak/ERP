

$(document).ready(function(){
	
	// 닫기 버튼 처리
	$("#CloseWindowButton").click(function(e){
		e.preventDefault();
		
		window.close();
	});

	// 파일 첨부된 버튼 클릭 처리
	$(document).on("click", ".AttachedFileDownloadButton", function(e){
		
		var fileHash = $(this).attr("data-filehash");
		
		if(fileHash === undefined || fileHash == null || fileHash == "" || fileHash == "null"){
			alert("첨부된 파일이 없습니다");
			return;
		}
		
		// TODO :: 나중에 이거 어떻게 표시할지 결정 필요
		alert(fileHash);
	});
		
});