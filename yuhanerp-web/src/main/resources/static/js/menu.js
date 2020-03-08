
var zoomx = 100;
  $(document).ready(function() {
    $(".gnb").hover(
      function() {
        $(".snb").stop().slideDown(400);
      },
      function() {
        $(".snb").stop().slideUp(600);
      }
    );

    $(".snb").hover(
      function() {
        $(".snb").stop().slideDown(400);
      },
      function() {
        $(".snb").stop().slideUp(600);
      }
    );
  });

//top버튼  
$(document).ready(function(){

	// hide #back-top first
	$("#back-top").hide();
	
	// fade in #back-top
	$(function () {
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});

		// scroll body to 0px on click
		$('#back-top a').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 800);
			return false;
		});
	});

});

//새창
function PopWin(url, w, h, sb) {
 var newWin;
 var setting = "width="+w+", height="+h+", top=100, left=550, scrollbars="+sb;
 newWin = window.open (url, "", setting);
 newWin.focus();
}

//셀렉트 박스 안에 체크박스
$(document).ready(function() {
  // $('#example-getting-started').multiselect();
});

