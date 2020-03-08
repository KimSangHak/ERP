/**
 * 
 */

		$(document).ready(function(){
			
			$("#CancelButton").click(function(e){
				e.preventDefault();
				
				window.close();
			});
			
			$("#SaveButton").click(function(e){
				e.preventDefault();
				
				$("form").ajaxSubmit({
					
					dataType : "json",
					success : function(obj){
						if(obj.result == "OK"){
							window.opener.location.reload();
							alert("변경했습니다");
							window.close();
						}else{
							alert(obj.reason);							
						}
					},
					error : function(){
						alert("서버 오류가 발생하였습니다");
					}					
				});	
				
			});
			
		});
		
