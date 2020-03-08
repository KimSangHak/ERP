<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
	<base href="/" />
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    
	<link rel="stylesheet" type="text/css" href="/css/style_pop.css">
	<link rel="stylesheet" type="text/css" href="/css/multi.css">
	
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/jquery.form.js"></script>
    <script type="text/javascript" src="/js/menu.js"></script>
	<script type="text/javascript" src="/js/auto_datepicker.js"></script>
    
    <!-- 트리메뉴 -->
    <script type="text/javascript" src="/js/checktree.js"></script>
    <script type="text/javascript">
		var checkmenu = new CheckTree('checkmenu');
	</script>
    <!-- //트리메뉴 -->
    
    <script type="text/javascript" src="/SmartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    
</head>

<body class="sub">
<div id="container">

	<div class="popForm">
    	
        <!-- 팝업 타이틀 -->
        <div class="popTitArea">
        	<h1>작업 발주(지시) 메일 발송</h1>
        </div>
        <!-- //팝업 타이틀 -->
    
        <!-- 팝업 콘텐츠 -->
        <div class="popCont">
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>메일 발송 회원목록</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
            
            <form id="SendMailForm" action="/mail/send" method="POST">
            <!-- 회원찾기 -->
            <div class="member">
                
                <div class="memSearch">
                	<!-- 부서별 검색일경우 -->
                	<ul>
                    	<li><span>검색유형</span></li>
                        <li>
                        	<select class="w120" id="findKey">
                                <option value="all">전체</option>
                                <option value="name">이름</option>
                                <option value="dept">부서</option>
                            </select>
                        </li>
                        <li class="w205">
                        	<input type="text" class="w120" id="findValue" />
                        </li>
                        <li><a href="#" class="btn_blue" id="FindButton">검색</a></li>
                    </ul>
                    <!-- //부서별 검색일경우 -->
                </div>
                
                <!-- 검색 리스트 -->
                    <div class="memSearchList">
                    
                        <div class="mslistCont">
                            <div class="leftCont">
                                <div class="sstit">
                                    <span>검색결과</span>
                                    <a href="#" class="btn_line_blue" id="addToRecipients">추가</a>
                                </div>
                                <div class="contlist" id="FindResult">
									<!-- 검색 결과가 여기에 표시 -->
                                </div>
                            </div>
                            
                            <div class="rightCont">
                                <div class="sstit">
                                	<span>선택된 직원</span>
                                	<a href="#" class="btn_line_red" id="deleteRecipients">삭제</a>
                                </div>
                                <div class="contlist" >
                                    <ul id="recipients">
										<!-- 선택된 수신자 -->
                                    </ul>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                	<!-- //검색 리스트 -->

            </div>
            <!-- //회원찾기 -->
            
            <!-- 팝업 서브타이틀 -->
            <div class="pcTit">
                <h2>메일 발송 내용</h2>
            </div>
            <!-- //팝업 서브타이틀 -->
          

                <!-- 메일본문내용 -->
                <div class="" style="padding : 10px 0">
                	<textarea style="width:100%; height:400px; display:none;" rows="30" id="ir1" name="ir1">
                	</textarea>
                </div>
                <!-- //메일본문내용 -->
                <input type="hidden" name="id" value="${id }"/>
                <input type="hidden" name="type" value="${type }"/>
                <input type="hidden" name="partnerId" value="${partnerId }"/>
                <input type="hidden" name="body" value=""/>
            </form>
            <!-- 팝업 버튼 -->
            <div class="popBtn">
                <a href="#" class="btn_gray CloseWindow">취소</a>
                <a href="#" class="btn_blue SendMailButton">메일 발송</a>
            </div>
            <!-- //팝업 버튼 -->
            
        </div>
        <!-- //팝업 콘텐츠 -->
        
	</div>
    
<!-- 멀티셀렉트 -->
<script src="/js/multiselect02.js"></script>
<script src="/js/multiselect01.js"></script>  
<!-- //멀티셀렉트 -->
</div>

	<script type="text/javascript">
	
		var oEditors = [];  // SmartEditor 인스턴스
        var sLang = "ko_KR";	// 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR
        
        var type = "${type}";
        var id = "${id}";
        var partnerId = "${partnerId}";
	
		$(document).ready(function(){
			
			// 맨 하단 취소 버튼
			$(".CloseWindow").click(function(e){
				e.preventDefault();
				
				if(!confirm("메일 발송을 중단하고 창을 닫으시겠습니까?"))
					return;
				
				window.close();
			});
			
			// 메일 발송 버튼 클릭
			$(".SendMailButton").click(function(e){
				e.preventDefault();
				
				$("#SendMailForm").ajaxSubmit({
					dataType : "json",
					beforeSerialize : function(){
						var content = oEditors.getById["ir1"].getIR();
						$("input[name='body']").val( content );
					},
					success : function(obj){
						if(obj.result == "OK"){
							alert("메일을 발송하였습니다");
							window.opener.location.reload();
							window.close();
						}else{
							alert(obj.reason);
						}
					},error : function(){
						alert("서버에 메일 발송 요청 중 오류가 발생하였습니다");
					}
					
				});
			});
			
			// 메일 발송 회원 검색
			$("#FindButton").click(function(e){
				e.preventDefault();
				
				$("#FindResult").load("/mail/find", { "key" : $("#findKey").val(), "text" : $("#findValue").val() });
			});
			
			// 수신자 추가
			$("#addToRecipients").click(function(e){
				e.preventDefault();
				
				$(".candidate_member:checked").each(function(){
					var userId = $(this).val();
					var userName = $(this).parent().text();
					
					if( $("#recipients li input[value=" + userId +"]").length == 0){
						var li = $("<li/>");
						var input = $("<input type=\"checkbox\" name=\"recipients\" checked class=\"recipient_member\" value=\"" + userId + "\"/>");
						var textNode = document.createTextNode(userName);
						li.append(input);
						li.append(textNode);
						console.log("li ==> " + li.prop("outerHTML") + " ,,, " + input.prop("outerHTML"));
						$("#recipients").append(li);
					}					
				});
			});
			
			// 수신자 삭제
			$("#deleteRecipients").click(function(e){
				e.preventDefault();
				
				$(".recipient_member:checked").each( function(){
					$(this).parent().remove();
				});
			});
			
			// SmartEditor 초기화 시킴
			nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "ir1",
                sSkinURI: "/SmartEditor/SmartEditor2Skin.html",
                htParams: {
                    bUseToolbar: true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseVerticalResizer: true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
                    bUseModeChanger: true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
                    //bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
                    //aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
                    fOnBeforeUnload: function () {
                        //alert("완료!");
                    },
                    I18N_LOCALE: sLang
                }, //boolean
                fOnAppLoad: function () {
                    //에디터 본문에 html 삽입
                    
                    $.ajax({
                    	url : "/mail/internal/" + type + "/" + id,
                    	type : "GET",
                    	dataType : "html",
                    	success : function(txt){
                    		oEditors.getById["ir1"].exec("PASTE_HTML", [ txt ]);		
                    	},
                    	error : function(){
                    		oEditors.getById["ir1"].exec("PASTE_HTML", [ "내용을 불러올 수 없습니다" ]);
                    	}
                    });                    
                    
                },
                fCreator: "createSEditor2"
            });
            
		});
	
	</script>
	
</body>
</html>
