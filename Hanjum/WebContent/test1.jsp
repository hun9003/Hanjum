<%@page import="java.util.ArrayList"%>
<%@page import="com.hanjum.notice.vo.NoticeBean"%>
<%@page import="com.hanjum.notice.service.NoticeProService"%>
<%@page import="com.hanjum.notice.dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/front.css" rel="stylesheet" type="text/css">
<link href="css/notice.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/rollingJob.js"></script>
<script src="js/topMenu.js"></script>


<script type="text/javascript">
// disabled <- ifstmt
		
	$(document).ready(function () {
		
	
		$('#btn').mouseover(function () {
			$.getJSON('getNewNotice.nt', function (rdata) {
				
				$('#new_notice_list *').remove();
			
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						// 주소값넣기
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다</a></td><tr>');
					}else if(item.notice_content == 2){
// 					$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><div id="btns"><form action="matchNotice.nt"><input type="hidden" value="'+item.notice_id+'"><button type="submit" id="submit">수락</button></form><form action="declineNotice.nt"><input type="hidden" value="'+item.notice_id+'"><button type="submit" id="decline">거절</button></form></div></td><tr>');
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><div id="btns"><a href="matchNotice.nt?notice_id='+item.notice_id+'">수락</a><a href="declineNotice.nt?notice_id='+item.notice_id+'">거절</a></td><tr>');

					}else if(item.notice_content == 3){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+'</b> 님, '+item.notice_from_id+' 님의 프로젝트를 요청을 수락하였습니다</a></td><tr>');
					}else if(item.notice_content == 4){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 수락하였습니다</a></td><tr>');
					}else if(item.notice_content == 5){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+' </b>님의 프로젝트를 요청을 거절하였습니다</a></td><tr>');
					}else if(item.notice_content == 6){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 거절하였습니다</a></td><tr>');
					}

				});	
			}); //getJSON
		}); //click - 새알람
		
		
		
		$('#get_notice_btn').click(function () {
			$.getJSON('getOldNotice.nt', function (rdata) {
				
				$('#old_notice_list *').remove();
				
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						// 주소값넣기
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다</a></td><tr>');
					}else if(item.notice_content == 2){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><form action="matchNotice.nt"><input type="hidden" value="'+item.notice_id+'"><input type="submit" value="수락" ></form><form action="declineNotice.nt"><input type="hidden" value="'+item.notice_id+'"><input type="submit" value="거절"></form></td><tr>');
					}else if(item.notice_content == 3){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+' 님, '+item.notice_from_id+' 님의 프로젝트를 요청을 수락하였습니다</a></td><tr>');
					}else if(item.notice_content == 4){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 수락하였습니다</a></td><tr>');
					}else if(item.notice_content == 5){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'"><b>'+item.user_id+' </b>님의 프로젝트를 요청을 거절하였습니다</a></td><tr>');
					}else if(item.notice_content == 6){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 거절하였습니다</a></td><tr>');
					}

				});	
			}); //getJSON
		}); //click - 새알람
		
		
		
		
}); //ready	
	
	
</script>



</head>

<body>

<%
String user_id = "test"; // 유저아이디
NoticeProService svc = new NoticeProService();
int count = svc.getNoticeCount(user_id);
%>

<form action="applyNotice.nt">
	<input type="submit" value="프로젝트 신청">
</form>

<form action="matchNotice.nt">
	<input type="submit" value="수락" >
</form>

<form action="declineNotice.nt">
	<input type="reset" value="거절">
</form>

<form action="deleteNotice.nt">
	<input type="submit" value="알림삭제">
</form>



<a href="applyNotice.nt"><input type="hidden" value="'+item.notice_id+'">수락</a>


	<div id="notice_table">
		
		<btn id="btn">
			<%if(count > 0) {%>
			<div id="notice_count"><%=count%></div>
			<%} %>
			<input type="button" id="BtnIcon">
		</btn>
		
		
		<ul id="list">
			<li>	
				<!-- 읽지않은 알람 리스트 뿌림 -->
				<div id="new_notice">
					<table id="new_notice_list">
							<!--  여기에 읽지않은 알람 -->
					</table>
				</div>
			</li>
			
			<li>
				<!--지난 알람 리스트 뿌림 -->
				<div id="old_notice">
					<table id="old_notice_list">
						<!--  읽은알람  -->
					</table>
				</div>
			</li>	
			<li id="li_btn">
				<button id="get_notice_btn">+</button>
			</li>
			
		</ul>	
	</div>
	
	


		


</body>
</html>





