<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/front.css" rel="stylesheet">
<link href="css/ListBoard.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('#new_notice').click(function () {
		$.getJSON('getNewNotice.nt', function (rdata) {
			$.each(rdata, function (index, item) {
				$('ul#notice_new_msg').html('<li style="padding:15px;"><a href="'+item.notice_url+'">'+item.notice_content+'</a></li>');
			}); // each
		}); //getJSON
	}); //click - 새알람
	
	$('#old_notice').click(function () {
		$.getJSON('getOldNotice.nt', function (rdata) {
			$.each(rdata, function (index, item) {
				$('ul#notice_old_msg').html('<li style="padding:15px;"><a href="'+item.notice_url+'">'+item.notice_content+'</a></li>');
			}); // each
		}); //getJSON
	}); //click - 확인한 알람
}); //ready		
</script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
<div class = "main_banner">
	<div id="main_img" class="editor_banner">
	</div>
</div>
<div class="board_option_list">
	<form action="" method="post">
	<div class="search_category">
	<fieldset class="search_field">
	<legend>원하는 편집자를 검색해보세요.</legend>
		<select name="field" id="field" class="search_option">
			<option value="">분야</option>
			<option value="youtube">유튜브</option>
			<option value="ad">광고</option>
			<option value="promotion">홍보영상</option>
			<option value="motion">모션그래픽</option>
		</select>
		<select name="price" id=price class="search_option">
			<option value="">단가</option>
			<option value="5">0~5만원</option>
			<option value="10">5~10만원</option>
			<option value="15">10~15만원</option>
			<option value="20">15~20만원</option>
			<option value="over">그 이상</option>
		</select>
		<select name="region" id="region" class="search_option">
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
		<select class="search_type" name="searchType" id="SearchType">
			<option value="subject">제목</option>
			<option value="user_name">닉네임</option>
		</select>
		<input type="text"  class="search_menu" placeholder="상세조건검색">
		<input type="submit" class="img_button" value="">
	</fieldset>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="board_list_type"><span class="f_right margin_r_10">
정렬 : <select name="order_type">
<option value="1">최신순</option>
<option value="2">오래된순</option>
<option value="3">별점 높은순</option>
<option value="4">별점 낮은순</option>
</select>
</span>
</div>
<div class="clear"></div>
<div class="board_list">
	<div class="board_table">
		<div class="profile">
		<div class="profile_photo"><img alt="profile" src="img/customer-service-2-fill.svg"></div>
		<div class="profile_name">test1234</div>
		</div>
		
		<div class="content">
		<div class="content_subject">"최선을 다해 편집하겠습니다."</div>
		<div class="content_text"><div class="content_text_detail">광고, 유튜브 전문 편집자 입니다 경력 5년차고 원하시는걸 말로 표현만 하시면 바로 만들어드립니다.</div></div>
		<div class="content_catagory"><div class="content_text_detail">분야 : 유튜브 / 광고</div></div>
		</div>
		
		<div class="detail">
		<table class="detail_table">
	  		<tr><td><div class="detail_text">평균점수
			<span class="f_right margin_r_10">
				<span class="starR on">별1</span>
	 	 		<span class="starR">별2</span>
	  			<span class="starR">별3</span>
	  			<span class="starR">별4</span>
	  			<span class="starR">별5</span>
	  		</span>
	  		</div></td></tr>
	  		<tr><td><div class="detail_text">전문성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">만족도 <progress value="8" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">적극성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">소통 <progress value="9" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">포트폴리오<span class="f_right margin_r_10">0개</span></div></td></tr>
	  		<tr><td><div class="detail_text">평균단가<span class="f_right margin_r_10">5만원~10만원</span></div></td></tr>
	  	</table>
		</div>
		
	</div>
		<div class="board_table">
		<div class="profile">
		<div class="profile_photo"><img alt="profile" src="img/customer-service-2-fill.svg"></div>
		<div class="profile_name">test1234</div>
		</div>
		
		<div class="content">
		<div class="content_subject">"최선을 다해 편집하겠습니다."</div>
		<div class="content_text"><div class="content_text_detail">광고, 유튜브 전문 편집자 입니다 경력 5년차고 원하시는걸 말로 표현만 하시면 바로 만들어드립니다.</div></div>
		<div class="content_catagory"><div class="content_text_detail">분야 : 유튜브 / 광고</div></div>
		</div>
		
		<div class="detail">
			<table class="detail_table">
	  		<tr><td><div class="detail_text">평균점수
			<span class="f_right margin_r_10">
				<span class="starR on">별1</span>
	 	 		<span class="starR">별2</span>
	  			<span class="starR">별3</span>
	  			<span class="starR">별4</span>
	  			<span class="starR">별5</span>
	  		</span>
	  		</div></td></tr>
	  		<tr><td><div class="detail_text">전문성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">만족도 <progress value="8" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">적극성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">소통 <progress value="9" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">포트폴리오<span class="f_right margin_r_10">0개</span></div></td></tr>
	  		<tr><td><div class="detail_text">평균단가<span class="f_right margin_r_10">5만원~10만원</span></div></td></tr>
	  		</table>
	  		</div>
		
	</div>
		<div class="board_table">
		<div class="profile">
		<div class="profile_photo"><img alt="profile" src="img/customer-service-2-fill.svg"></div>
		<div class="profile_name">test1234</div>
		</div>
		
		<div class="content">
		<div class="content_subject">"최선을 다해 편집하겠습니다."</div>
		<div class="content_text"><div class="content_text_detail">광고, 유튜브 전문 편집자 입니다 경력 5년차고 원하시는걸 말로 표현만 하시면 바로 만들어드립니다.</div></div>
		<div class="content_catagory"><div class="content_text_detail">분야 : 유튜브 / 광고</div></div>
		</div>
		
		<div class="detail">
		
	  		<table class="detail_table">
	  		<tr><td><div class="detail_text">평균점수
			<span class="f_right margin_r_10">
				<span class="starR on">별1</span>
	 	 		<span class="starR">별2</span>
	  			<span class="starR">별3</span>
	  			<span class="starR">별4</span>
	  			<span class="starR">별5</span>
	  		</span>
	  		</div></td></tr>
	  		<tr><td><div class="detail_text">전문성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">만족도 <progress value="8" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">적극성 <progress value="7" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">소통 <progress value="9" max="10"></progress></div></td></tr>
	  		<tr><td><div class="detail_text">포트폴리오<span class="f_right margin_r_10">0개</span></div></td></tr>
	  		<tr><td><div class="detail_text">평균단가<span class="f_right margin_r_10">5만원~10만원</span></div></td></tr>
	  		</table>
		
		
		
		
		
		
		</div>
		
	</div>
</div>
<div id="boardPage">
	<div class="light">
		<div class="wp-pagenavi">
		<a href="#"><span class="first"></span></a>
		<a href="#"><span class="previouspostslink"></span></a>	
		<span class="current">1</span>
		<span><a href="#">2</a></span>
		<span><a href="#">3</a></span>
		<span><a href="#">4</a></span>
		<span><a href="#">5</a></span>
		<span><a href="#">6</a></span>
		<span><a href="#">7</a></span>
		<span><a href="#">8</a></span>
		<span><a href="#">9</a></span>
		<span><a href="#">10</a></span>
		<a href="#"><span class="nextpostslink"></span></a>
		<a href="#"><span class="last"></span></a>	
		</div>
	</div>
</div>
<div style="height: 100px;"></div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>