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
<link href="css/util.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
<script src="js/board_search.js"></script>
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
	<form action="ProjectSearchList.bo" method="post">
	<div class="search_category">
	<fieldset class="search_field">
	<legend>원하는 프로젝트를 검색해보세요.</legend>
	<div class="search_form">
		<table class="search_table">
		<tr>
		<th class="td_s">분야</th><th class="td_m">단가</th><th class="td_l">검색</th>
		</tr>
		<tr>
		<td>
		<select class="search_option" name="board_creator_genre">
		<option value="">분야를 선택하세요</option>
		<option value="1">유튜브</option>
		<option value="2">홍보</option>
		<option value="3">광고</option>
		<option value="4">뮤직비디오</option>
		<option value="5">드라마</option>
		<option value="6">모션그래픽</option>
		<option value="7">기타</option>
		</select>
		</td>
		<td>
		<div class="input_price"><input class="defaultTextInput" type="number" id="MinPrice" step="1000" min="0" name="board_creator_cre_min_price" placeholder="최소단가를 입력하세요"> 원 ~ <input class="defaultTextInput" type="number" id="MaxPrice" step="1000" min="1000" name="board_creator_cre_max_price" placeholder="최대단가를 입력하세요."> 원</div>
		</td>
		<td class="td_search">
		<select class="search_type" name="search_type">
		<option value="1">제목+소개</option>
		<option value="2">제목</option>
		<option value="3">소개</option>
		<option value="4">세부설명</option>
		<option value="5">닉네임</option>
		</select>
		<input class="smallTextInput" type="text" name="keyword">
		<button class="defaultBtn searchBtn m-l-10">검색</button>
		</td>		
		</tr>
		<tr>
		<th colspan="3" id="search_detail">상세내용 찾기<input class='searchReset m-r-10 defaultBtn' type='reset' value='초기화'></th>
		</tr>
		</table>
		<div class="search_detail_form">
			<table class="search_detail_table">
			<tr>
			<th>동시 녹음 유무</th><td colspan="3">
			<div class="check_list"><input type="radio" id="Recording1" name="board_creator_recording" value="1"><label for="Recording1"></label><span class="checkbox">싱크 작업 필요</span></div>
			<div class="check_list"><input type="radio" id="Recording2"  name="board_creator_recording" value="2"><label for="Recording2"></label><span class="checkbox">부분적 필요</span></div>
			<div class="check_list"><input type="radio" id="Recording3"  name="board_creator_recording" value="3"><label for="Recording3"></label><span class="checkbox">원본 녹음 사용</span></div>
			</td>
			</tr>
			<tr>
			<th>녹화에 이용된 캠</th><td colspan="3">
			<div class="check_list"><input type="radio" id="CamNum1" name="board_creator_cam_num" value="1"><label for="CamNum1"></label><span class="checkbox">1캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum2" name="board_creator_cam_num" value="2"><label for="CamNum2"></label><span class="checkbox">2캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum3" name="board_creator_cam_num" value="3"><label for="CamNum3"></label><span class="checkbox">3캠</span></div>
			<div class="check_list"><input type="radio" id="CamNum4" name="board_creator_cam_num" value="4"><label for="CamNum4"></label><span class="checkbox">4캠 이상</span></div>
			</td>
			</tr>
			<tr>
			<th>원본 클립</th><td colspan="3">
			<div class="check_list"><input type="radio" id="OriClipNum1" name="board_creator_ori_clip_num" value="1"><label for="OriClipNum1"></label><span class="checkbox">5개 이하</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum2" name="board_creator_ori_clip_num" value="2"><label for="OriClipNum2"></label><span class="checkbox">5개 ~ 20개</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum3" name="board_creator_ori_clip_num" value="3"><label for="OriClipNum3"></label><span class="checkbox">20 ~ 50개</span></div>
			<div class="check_list"><input type="radio" id="OriClipNum4" name="board_creator_ori_clip_num" value="4"><label for="OriClipNum4"></label><span class="checkbox">50개 이상</span></div>
			</td>
			</tr>
			<tr>
			<th>편집 전 런타임</th><td colspan="3">
			<div class="check_list"><input type="radio" id="OriLength1" name="board_creator_ori_length" value="1"><label for="OriLength1"></label><span class="checkbox">10분 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength2" name="board_creator_ori_length" value="2"><label for="OriLength2"></label><span class="checkbox">30분 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength3" name="board_creator_ori_length" value="3"><label for="OriLength3"></label><span class="checkbox">1시간 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength4" name="board_creator_ori_length" value="4"><label for="OriLength4"></label><span class="checkbox">5시간 이하</span></div>
			<div class="check_list"><input type="radio" id="OriLength5" name="board_creator_ori_length" value="5"><label for="OriLength5"></label><span class="checkbox">5시간 이상</span></div>
			<div class="check_list"><input type="radio" id="OriLength6" name="board_creator_ori_length" value="6"><label for="OriLength6"></label><span class="checkbox">정확히 알 수 없음</span></div>
			</td>
			</tr>
			<tr>
			<th>편집 후 런타임</th><td colspan="3">
			<div class="check_list"><input type="radio" id="EditLength1" name="board_creator_edit_length" value="1"><label for="EditLength1"></label><span class="checkbox">5분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength2" name="board_creator_edit_length" value="2"><label for="EditLength2"></label><span class="checkbox">10분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength3" name="board_creator_edit_length" value="3"><label for="EditLength3"></label><span class="checkbox">30분 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength4" name="board_creator_edit_length" value="4"><label for="EditLength4"></label><span class="checkbox">1시간 이하</span></div>
			<div class="check_list"><input type="radio" id="EditLength5" name="board_creator_edit_length" value="5"><label for="EditLength5"></label><span class="checkbox">1시간 이상</span></div>
			</td>
			</tr>
			<tr>
			<th>파일의 전달방식</th><td colspan="3">
			<div class="check_list"><input type="radio" id="OriTransfer1" name="board_creator_ori_transfer" value="1"><label for="OriTransfer1"></label><span class="checkbox">이메일</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer2" name="board_creator_ori_transfer" value="2"><label for="OriTransfer2"></label><span class="checkbox">웹하드</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer3" name="board_creator_ori_transfer" value="3"><label for="OriTransfer3"></label><span class="checkbox">NAS</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer4" name="board_creator_ori_transfer" value="4"><label for="OriTransfer4"></label><span class="checkbox">SMS</span></div>
			<div class="check_list"><input type="radio" id="OriTransfer5" name="board_creator_ori_transfer" value="5"><label for="OriTransfer5"></label><span class="checkbox">직접전달</span></div>
			</td>
			</tr>
			</table>
		</div>
	</div>
	
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
	for(int i= 0; i < projectList.size(); i++){
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