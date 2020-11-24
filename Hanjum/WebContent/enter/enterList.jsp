<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<script src="js/board_search.js"></script>
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
	<div class="search_form">
		<table class="search_table">
		<tr>
		<th class="td_s">분야</th><th class="td_m">연봉</th><th class="td_l">검색</th>
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
		<div class="input_price"><input class="defaultTextInput" type="number" id="MinPrice" step="1000" min="0" name="board_creator_cre_min_price" value="0"> 만원 ~ <input class="defaultTextInput" type="number" id="MaxPrice" step="1000" min="1000" name="board_creator_cre_max_price" value="5000"> 만원</div>
		</td>
		<td class="td_search">
		<select class="search_type" name="search_type">
		<option value="1">제목+소개</option>
		<option value="2">제목</option>
		<option value="3">소개</option>
		<option value="4">업무내용</option>
		<option value="5">회사명</option>
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
			<th>이용프로그램</th><td colspan="3">
			<div class="check_list"><input type="checkbox" id="program1" name="board_enter_program" value="1"><label for="program1"></label><span class="checkbox">프리미어</span></div>
			<div class="check_list"><input type="checkbox" id="program2" name="board_enter_program" value="2"><label for="program2"></label><span class="checkbox">파이널컷</span></div>
			<div class="check_list"><input type="checkbox" id="program3" name="board_enter_program" value="3"><label for="program3"></label><span class="checkbox">베가스</span></div>
			<div class="check_list"><input type="checkbox" id="program4" name="board_enter_program" value="4"><label for="program4"></label><span class="checkbox">에프터 이펙트</span></div>
			<div class="check_list"><input type="checkbox" id="program5" name="board_enter_program" value="5"><label for="program5"></label><span class="checkbox">포토샵</span></div>
			<div class="check_list"><input type="checkbox" id="program6" name="board_enter_program" value="6"><label for="program6"></label><span class="checkbox">일러스트</span></div>
			<div class="check_list"><input type="checkbox" id="program7" name="board_enter_program" value="7"><label for="program7"></label><span class="checkbox">베가스</span></div>
			<div class="check_list"><input type="checkbox" id="program8" name="board_enter_program" value="8"><label for="program8"></label><span class="checkbox">오디션</span></div>
			<div class="check_list"><input type="checkbox" id="program9" name="board_enter_program" value="9"><label for="program9"></label><span class="checkbox">기타</span></div>
			</td>
			</tr>
			<tr>
			<th>고용형태</th><td colspan="3">
			<div class="check_list"><input type="radio" id="Hiring1" name="board_enter_hiring" value="1"><label for="Hiring1"></label><span class="checkbox">계약직</span></div>
			<div class="check_list"><input type="radio" id="Hiring2" name="board_enter_hiring" value="2"><label for="Hiring2"></label><span class="checkbox">정규직</span></div>
			<div class="check_list"><input type="radio" id="Hiring3" name="board_enter_hiring" value="3"><label for="Hiring3"></label><span class="checkbox">비정규직</span></div>
			</td>
			</tr>
			<tr>
			<th>근무시간</th><td colspan="3">
			<div class="input_workTime"><input type="time" id="StartWork" class="smallTextInput" name="board_enter_start_work"> ~ <input type="time" id="FinWork" class="smallTextInput" name="board_enter_fin_work"></div>
			</td>
			</tr>
			<tr>
			<th>근무요일</th><td colspan="3">
			<div class="check_list"><input type="checkbox" id="WorkDays1" name="board_enter_work_days" value="1"><label for="WorkDays1"></label><span class="checkbox">월요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays2" name="board_enter_work_days" value="2"><label for="WorkDays2"></label><span class="checkbox">화요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays3" name="board_enter_work_days" value="3"><label for="WorkDays3"></label><span class="checkbox">수요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays4" name="board_enter_work_days" value="4"><label for="WorkDays4"></label><span class="checkbox">목요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays5" name="board_enter_work_days" value="5"><label for="WorkDays5"></label><span class="checkbox">금요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays6" name="board_enter_work_days" value="6"><label for="WorkDays6"></label><span class="checkbox">토요일</span></div>
			<div class="check_list"><input type="checkbox" id="WorkDays7" name="board_enter_work_days" value="7"><label for="WorkDays7"></label><span class="checkbox">일요일</span></div>
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
if(enterList != null && listCount > 0){
	String hiring = "";
	String program = "";
	int workday = 0;
	for(int i = 0; i < enterList.size(); i++){
		EnterBean enter = enterList.get(i);
		String[] workdayArr = enter.getBoard_enter_work_days().split(",");
		workday = workdayArr.length;
		SimpleDateFormat formatTime = new SimpleDateFormat("a HH시 mm분");
		SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
		Date startWorkTime = fm.parse(enter.getBoard_enter_start_work());
		Date finWorkTime = fm.parse(enter.getBoard_enter_fin_work());
		String startWork = formatTime.format(startWorkTime).replace("AM", "오전").replace("PM", "오후");
		String finWork = formatTime.format(finWorkTime).replace("AM", "오전").replace("PM", "오후");
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
	  		<tr><td><div class="detail_text">근무시간<span class="f_right margin_r_10"><%=startWork %> ~ <%=finWork %></span></div></td></tr>
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