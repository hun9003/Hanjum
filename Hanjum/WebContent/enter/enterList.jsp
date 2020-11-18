<%@page import="com.hanjum.board.vo.EnterBean"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.board.vo.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArrayList<EnterBean> enterList = (ArrayList<EnterBean>)request.getAttribute("enterList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	
	String pageUrl = "EnterList.bo";
	String contentUrl = "Enter.bo";
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
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
<div class = "main_banner">
	<div id="main_img" class="enter_banner">
	</div>
</div>
<div class="board_option_list">
	<form action="" method="post">
	<div class="search_category">
	<fieldset class="search_field">
	<legend>원하는 채용공고를 검색해보세요.</legend>
	<table class="search_table">
	<tr>
	<td>
		<span class="m-l-20">고용형태</span>
		<select name="hiring" id="Hiring" class="search_option">
			<option value="">고용형태</option>
			<option value="1">계약직</option>
			<option value="2">정규직</option>
			<option value="3">비정규직</option>
		</select>
	</td>
	<td>
	<span class="m-lr-20">연봉</span> 
		<input type="number" class="defaultTextInput" name="min_salary" placeholder="최소 연봉 입력"> ~ 
		<input type="number" class="defaultTextInput" name="min_salary" placeholder="최대 연봉 입력">
		만원
	</td>
	</tr>
	<tr>
	<td colspan="2"><span class="m-lr-20">근무요일</span> 
	<div class="check_list"><input type="checkbox" id="WorkDays1" name="work_days" value="1"><label for="WorkDays1"></label><span class="checkbox">월요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays2" name="work_days" value="2"><label for="WorkDays2"></label><span class="checkbox">화요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays3" name="work_days" value="3"><label for="WorkDays3"></label><span class="checkbox">수요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays4" name="work_days" value="4"><label for="WorkDays4"></label><span class="checkbox">목요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays5" name="work_days" value="5"><label for="WorkDays5"></label><span class="checkbox">금요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays6" name="work_days" value="6"><label for="WorkDays6"></label><span class="checkbox">토요일</span></div>
	<div class="check_list"><input type="checkbox" id="WorkDays7" name="work_days" value="7"><label for="WorkDays7"></label><span class="checkbox">일요일</span></div>
	</td>
	</tr>
	<tr>
	<td>
	<span class="m-l-20 m-r-10">근무지</span>
	<select name="region" id="region" class="search_option">
			<option value="">근무지</option>
			<option value="02">서울 </option>
            <option value="031">경기 </option>
            <option value="032">인천</option>
            <option value="033">강원 </option>
            <option value="041">충남 </option>
            <option value="042">대전 </option>
            <option value="043">충북 </option>
            <option value="051">부산 </option>
            <option value="052">울산 </option>
            <option value="053">대구 </option>
            <option value="054">경북 </option>
            <option value="054">경북 </option>
            <option value="055">경남 </option>
            <option value="061">전남 </option>
            <option value="062">광주 </option>
            <option value="063">전북 </option>
            <option value="064">제주 </option>
		</select>
	</td>
	<td>
		<select class="search_type m-l-20" name="searchType" id="SearchType">
			<option value="subject">제목</option>
			<option value="user_name">회사명</option>
		</select>
			<input type="text"  class="search_menu" placeholder="상세조건검색">
			<input type="submit" class="img_button" value="">
	</td>
	</tr>
	</table>
		
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
if(enterList != null && listCount > 0){
	String hiring = "";
	String program = "";
	int workday = 0;
	for(int i = 0; i < enterList.size(); i++){
		EnterBean enter = enterList.get(i);
		String[] workdayArr = enter.getBoard_enter_work_days().split(",");
		workday = workdayArr.length;
		program = enter.getBoard_enter_program()
				.replace("1", "프리미어")
				.replace("2", "파이널컷")
				.replace("3", "베가스")
				.replace("4", "에프터이펙트")
				.replace("5", "포토샵")
				.replace("6", "일려스트")
				.replace("7", "베가스")
				.replace("8", "오디션")
				.replace("9", "기타");
		switch(enter.getBoard_enter_hiring()){
		case 1: hiring = "계약직";break;
		case 2: hiring = "정규직";break;
		case 3: hiring = "비정규직";break;
		}

%>
	<div class="board_table" onclick="location.href = '<%=contentUrl%>?board_id=<%=enter.getBoard_id()%>'">
		<div class="content_enter">
		<div class="content_subject">제목 : <%=enter.getBoard_subject() %></div>
		<div class="content_text"><div class="content_text_detail"><%=enter.getBoard_content() %></div></div>
		<div class="content_catagory">
		<div class="content_text_detail"><span class="f-s-18">회사명 : <%=enter.getBoard_enter_company() %></span><span class="f-s-18 m-l-100">근무지 : <%=enter.getBoard_enter_location() %></span></div>
		</div>
		</div>
		<div class="detail">
		<table class="detail_table">
	  		<tr><td><div class="detail_text">고용형태<span class="f_right margin_r_10"><%=hiring %></span></div></td></tr>
	  		<tr><td><div class="detail_text">연봉<span class="f_right margin_r_10"><%=enter.getBoard_enter_salary() %>만원</span></div></td></tr>
	  		<tr><td><div class="detail_text">근무시간<span class="f_right margin_r_10"><%=enter.getBoard_enter_salary() %> ~ <%=enter.getBoard_enter_fin_work() %></span></div></td></tr>
	  		<tr><td><div class="detail_text">근무요일<span class="f_right margin_r_10">주 <%=workday %>일</span></div></td></tr>
	  	</table>
		</div>
	</div>
		
<%
	}
} else {
	%>
	<section id="emptyArea">등록된 채용공고가 없습니다.</section>
	<%
}
%>
</div>
<div id="writeBtn" class="write_btn" onclick="location.href='EnterWrite.bo'"></div>
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