<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ProjectBean project = (ProjectBean)request.getAttribute("project");
	
	String pageUrl = "Project.bo";
	
	if(project != null){
		String genre = "";
		String recording = "";
		String ori_clip = "";
		String ori_length = "";
		String edit_length = "";
		String ori_transfer	= "";
		String min_price = "";
		String max_price = "";
		String cam_num = "";
		String[] ref = project.getBoard_creator_cre_ref().split(",");
		genre = project.getBoard_creator_genre()
				.replace("1", "유튜브")
				.replace("2", "홍보")
				.replace("3", "광고")
				.replace("4", "뮤직비디오")
				.replace("5", "드라마")
				.replace("6", "모션그래픽")
				.replace("7", "기타");
		switch(project.getBoard_creator_recording()){
			case 1: recording = "싱크 작업 필요"; break;
			case 2: recording = "부분적 필요"; break;
			case 3: recording = "원본 녹음 사용"; break;
		}
		switch(project.getBoard_creator_ori_clip_num()){
			case 1: ori_clip = "5개 이하"; break;
			case 2: ori_clip = "5개 ~ 20개"; break;
			case 3: ori_clip = "20개 ~ 50개"; break;
			case 4: ori_clip = "50개 이상"; break;
		}
		switch(project.getBoard_creator_ori_length()){
			case 1: ori_length = "10분 이하"; break;
			case 2: ori_length = "30분 이하"; break;
			case 3: ori_length = "1시간 이하"; break;
			case 4: ori_length = "5시간 이하"; break;
			case 5: ori_length = "5시간 이상"; break;
			case 6: ori_length = "정확히 알 수 없음"; break;
		}
		switch(project.getBoard_creator_edit_length()){
			case 1: edit_length = "5분 이하"; break;
			case 2: edit_length = "10분 이하"; break;
			case 3: edit_length = "30분 이하"; break;
			case 4: edit_length = "1시간 이하"; break;
			case 5: edit_length = "1시간 이상"; break;
		}
		switch(project.getBoard_creator_ori_transfer()){
			case 1: ori_transfer = "이메일"; break;
			case 2: ori_transfer = "웹하드"; break;
			case 3: ori_transfer = "NAS"; break;
			case 4: ori_transfer = "SMS"; break;
			case 5: ori_transfer = "직접 전달"; break;
		}
		switch(project.getBoard_creator_cam_num()){
			case 1: cam_num = "1캠"; break;
			case 2: cam_num = "2캠";break;
			case 3: cam_num = "3캠";break;
			case 4: cam_num = "4캠 이상";break;
		}
		if((project.getBoard_creator_cre_min_price() / 1000) < 10){
			min_price = (project.getBoard_creator_cre_min_price() / 1000)+"천원";
		} else {
			if(project.getBoard_creator_cre_min_price() % 10000 > 0){
				min_price = (project.getBoard_creator_cre_min_price() / 10000.0)+"만원";
			} else {
				min_price = (project.getBoard_creator_cre_min_price() / 10000)+"만원";	
			}
		}
		if((project.getBoard_creator_cre_max_price() / 1000) < 10){
			max_price = (project.getBoard_creator_cre_max_price() / 1000)+"천원";
		} else {
			if(project.getBoard_creator_cre_max_price() % 10000 > 0){
			max_price = (project.getBoard_creator_cre_max_price() / 10000.0)+"만원";
			} else {
			max_price = (project.getBoard_creator_cre_max_price() / 10000)+"만원";	
			}
				
		}
			
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>프로젝트</h1></div>
<table class="write_table">
<tr><td class="td_name">프로젝트 제목</td><td class="td_content"><div class="board_subject"><%=project.getBoard_subject() %></div></td></tr>
<tr><td class="td_name">작성자</td><td class="td_content"><div class="board_content"><%=project.getUser_id() %></div></td></tr>
<tr><td class="td_name">프로젝트 소개</td><td class="td_content"><div class="board_content"><%=project.getBoard_content() %></div></td></tr>
<tr><td class="td_name">장르</td><td class="td_content"><div class="board_option"><%=genre %></div></td></tr>
<tr><td class="td_name">세부설명</td><td class="td_content"><div class="board_detail_content"><%=project.getBoard_creator_content_detail()%></div></td></tr>
<tr><td class="td_name">동시 녹음 유무</td><td class="td_content"><div class="board_option"><%=recording %></div></td></tr>
<tr><td class="td_name">녹화에 이용된 캠</td><td class="td_content"><div class="board_option"><%=cam_num %></div></td></tr>
<tr><td class="td_name">원본 클립</td><td class="td_content"><div class="board_option"><%=ori_clip %></div></td></tr>
<tr><td class="td_name">편집 전 런타임</td><td class="td_content"><div class="board_option"><%=ori_length %></div></td></tr>
<tr><td class="td_name">편집 후 런타임</td><td class="td_content"><div class="board_option"><%=edit_length %></div></td></tr>
<tr><td class="td_name">파일의 전달방식</td><td class="td_content"><div class="board_option"><%=ori_transfer %></div></td></tr>
<tr><td class="td_name">예상 단가</td><td class="td_content"><div class="board_price"><%=min_price %> ~ <%=max_price %></div></td></tr>
<tr><td class="td_name"><label for="Ref1">레퍼런스 링크</label></td><td class="td_content">
<%
for(int i = 0; i < ref.length; i++){
%>
<div class="board_ref"><iframe width="560" height="315" src="https://www.youtube.com/embed/<%=ref[i] %>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
<%
}
%>
</td></tr>
</table>
</div>
<%
}
%>
<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>