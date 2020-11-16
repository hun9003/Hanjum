<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArrayList<ProjectBean> projectList = (ArrayList<ProjectBean>)request.getAttribute("projectList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	
	String pageUrl = "ProjectList.bo";
	String contentUrl = "Project.bo";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/front.css" rel="stylesheet">
<link href="css/ListBoard.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
<div class = "main_banner">
	<div id="main_img" class="project_banner">
	</div>
</div>
<div class="board_option_list">
	<form action="" method="post">
	<div class="search_category">
	<fieldset class="search_field">
	<legend>원하는 프로젝트를 검색해보세요.</legend>
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
</select>
</span>
</div>
<div class="clear"></div>
<div class="board_list">
<%
if(projectList != null && listCount > 0){
	String genre = "";
	String recording = "";
	String ori_clip = "";
	String ori_length = "";
	String edit_length = "";
	String ori_transfer	= "";
	String min_price = "";
	String max_price = "";
	for(int i=0; i < projectList.size(); i++){
		ProjectBean project = projectList.get(i);
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
	<div class="board_table" onclick="location.href = '<%=contentUrl%>?board_id=<%=project.getBoard_id()%>'">
		<div class="profile">
		<div class="profile_photo"><img alt="profile" src="img/customer-service-2-fill.svg"></div>
		<div class="profile_name"><%=project.getUser_id() %></div>
		</div>
		
		<div class="content">
		<div class="content_subject"><%=project.getBoard_subject() %></div>
		<div class="content_text"><div class="content_text_detail"><%=project.getBoard_content() %></div></div>
		<div class="content_catagory"><div class="content_text_detail">0명의 지원자가 있습니다.</div></div>
		</div>
		
		<div class="detail">
		<table class="detail_table">
	  		<tr><td><div class="detail_text">분야<span class="f_right margin_r_10"><%=genre %></span></div></td></tr>
	  		<tr><td><div class="detail_text">동시녹음유무<span class="f_right margin_r_10"><%=recording %></span></div></td></tr>
	  		<tr><td><div class="detail_text">편집전 런타임<span class="f_right margin_r_10"><%=ori_length %></span></div></td></tr>
	  		<tr><td><div class="detail_text">편집후 런타임<span class="f_right margin_r_10"><%=edit_length %></span></div></td></tr>
	  		<tr><td><div class="detail_text">영상클립<span class="f_right margin_r_10"><%=ori_clip %></span></div></td></tr>
	  		<tr><td><div class="detail_text">전달방식<span class="f_right margin_r_10"><%=ori_transfer %></span></div></td></tr>
	  		<tr><td><div class="detail_text">편집단가<span class="f_right margin_r_10"><%=min_price %>~<%=max_price %></span></div></td></tr>
	  	</table>
		</div>
	</div>
	<%
		}
	} else {
		%>
		<section id="emptyArea">등록된 프로젝트가 없습니다.</section>
		<%
	}
	%>
</div>
<div id="writeBtn" class="write_btn" onclick="location.href='ProjectWrite.bo'"></div>
<div id="boardPage">
	<div class="light">
		<div class="wp-pagenavi">
		<%if(nowPage>1){ %>
		<a href="<%=pageUrl%>?page=1"><span class="first"></span></a>
		<a href="<%=pageUrl%>?page=<%=nowPage-1%>"><span class="previouspostslink"></span></a>	
		<% 
		}
		for(int i=startPage; i<=endPage; i++){
			if(i == nowPage){
				%>
		<span class="current"><%=i %></span>
		<% } else { %>
		<span><a href="<%=pageUrl%>?page=<%=i%>"><%=i %></a></span>
		<% } %>
		<%
			}
		if(nowPage < maxPage){ 
		%>
		<a href="<%=pageUrl%>?page=<%=nowPage+1%>"><span class="nextpostslink"></span></a>
		<a href="<%=pageUrl%>?page=<%=endPage%>"><span class="last"></span></a>	
		<%
		}
		%>
		</div>
	</div>
</div>
<div style="height: 100px;"></div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>