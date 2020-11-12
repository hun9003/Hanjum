<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/default.css" rel="stylesheet">
<link href="../css/front.css" rel="stylesheet">
<link href="../css/findEditor.css" rel="stylesheet">
<script src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(window).scroll(function() {
	var scroll = $(window).scrollTop();
	//console.log(scroll);
	if (scroll >= 50) {
		//console.log('a');
		$("header").addClass("fx_header");
	} else {
		//console.log('a');
		$("header").removeClass("fx_header");
		
	}
});
$(document).ready(function(){
	$(".job_bullet label:nth-child(1)").css('background','#666');
	var width = 1400;
	var jobCount = $("input[name=job_pos]").length;
	$(".job_left").click(function(){
		$(".job_left").css("pointer-events","none");
		$(".job_right").css("pointer-events","none");
		
		setTimeout(function(){
			$(".job_left").css("pointer-events","auto");
			$(".job_right").css("pointer-events","auto");
		},1000);
		
		var job_margin = $(".main_job_form").css('marginLeft').replace('px','');
		if(job_margin != 0){
			var job_pos = Number(job_margin / -1400) + Number(0);
			var job_pos_next = Number(job_margin / -1400) + Number(1);
			$(".main_job_form").css('marginLeft',Number(job_margin)+Number(width)+"px");
			$(".job_bullet label:nth-child("+job_pos_next+")").css('background','#fff');
			$(".job_bullet label:nth-child("+job_pos+")").css('background','#666');
		} else {
			$(".main_job_form").css('marginLeft',-width*(jobCount-1)+"px");
			$(".job_bullet label:nth-child(1)").css('background','#fff');
			$(".job_bullet label:nth-child("+jobCount+")").css('background','#666');
		}
	});
	$(".job_right").click(function(){
		var job_margin = $(".main_job_form").css('marginLeft').replace('px','');
		
		$(".job_left").css("pointer-events","none");
		$(".job_right").css("pointer-events","none");
		setTimeout(function(){
			$(".job_left").css("pointer-events","auto");
			$(".job_right").css("pointer-events","auto");
		},1000);
		
		if(job_margin != width*(1-jobCount)){
			var job_pos = Number(job_margin / -1400) + Number(1);
			var job_pos_next = Number(job_margin / -1400) + Number(2);
			$(".main_job_form").css('marginLeft',Number(job_margin)-Number(width)+"px");
			$(".job_bullet label:nth-child("+job_pos_next+")").css('background','#666');
			$(".job_bullet label:nth-child("+job_pos+")").css('background','#fff');
		} else {
			$(".main_job_form").css('marginLeft',0+"px");
			$(".job_bullet label:nth-child(1)").css('background','#666');
			$(".job_bullet label:nth-child("+jobCount+")").css('background','#fff');
		}
	});
});
$(document).ready(function(){
	$("#field").change(function(){
  		alert(this.value)
	});
	$("#price").change(function(){
		alert(this.value)
			});
	$("#region").change(function(){
		alert(this.value)
			});
});


</script>

</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
<div class = "main_banner">
	<div id="main_img">
	</div>
</div>
<div class="editor_option">
	<select name="field" id="field">
		<option value="">분야</option>
		<option value="youtube">유튜브</option>
		<option value="ad">광고</option>
		<option value="promotion">홍보영상</option>
		<option value="motion">모션그래픽</option>
	</select>
	<select name="price" id=price>
		<option value="">단가</option>
		<option value="5">0~5만원</option>
		<option value="10">5~10만원</option>
		<option value="15">10~15만원</option>
		<option value="20">15~20만원</option>
		<option value="over">그 이상</option>
	</select>
	<select name="region" id="region">
		<option value="">거주지</option>
		<option value="seoul">서울</option>
		<option value="gyeonggi">경기</option>
		<option value="incheon">인천</option>
		<option value="busan">부산</option>
		<option value="deagu">대구</option>
		<option value="gwangju">광주</option>
		<option value="deajeon">대전</option>
		<option value="ulsan">울산</option>
		<option value="sejong">세종</option>
		<option value="gangwon">강원</option>
		<option value="gyeongnam">경남</option>
		<option value="gyeongbuk">경북</option>
		<option value="jeonnam">전남</option>
		<option value="jeonbuk">전북</option>
		<option value="chungnam">충남</option>
		<option value="chungbuk">충북</option>
		<option value="jeju">제주</option>
	</select>
	<form action="" method="post">
		<input type="text"  class="search_menu" placeholder="상세조건검색">
		<input type="submit" class="img_button" value="">
	</form>
</div>
<hr>

<div class="clear"></div>
<div class="editor_table">
<div class="editor_img"></div>
<div class="editor_name">홍길동 조아요</div>
</div>


<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>