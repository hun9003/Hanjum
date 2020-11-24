<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.board.vo.EnterBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    EnterBean enter = (EnterBean)request.getAttribute("enter");
    
    String pageUrl = "Enter.bo";
    
    if(enter != null){
    	String hiring = "";
    	String program = "";
    	int workday = 0;
    	String[] workdayArr = enter.getBoard_enter_work_days().split(",");
		workday = workdayArr.length;
		String workday_list = enter.getBoard_enter_work_days()
				.replace("1", "월요일")
				.replace("2", "화요일")
				.replace("3", "수요일")
				.replace("4", "목요일")
				.replace("5", "금요일")
				.replace("6", "토요일")
				.replace("7", "일요일");
		;
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
		SimpleDateFormat formatTime = new SimpleDateFormat("a HH시 mm분");
		SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
		Date startWorkTime = fm.parse(enter.getBoard_enter_start_work());
		Date finWorkTime = fm.parse(enter.getBoard_enter_fin_work());
		String startWork = formatTime.format(startWorkTime).replace("AM", "오전").replace("PM", "오후");
		String finWork = formatTime.format(finWorkTime).replace("AM", "오전").replace("PM", "오후");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<link href="css/util.css" rel="stylesheet">
<link href="css/map.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4b78be2bf24684204d83a8137ecef2ff&libraries=services"></script>

<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>채용공고</h1></div>
<table class="write_table">
<tr><td class="td_name"><label for="Subject">채용공고 제목</label></td><td class="td_content"><%=enter.getBoard_subject() %></td></tr>
<tr><td class="td_name"><label for="Content">채용공고 소개</label></td><td class="td_content"><%=enter.getBoard_content() %></td></tr>
<tr><td class="td_name"><label for="Company">회사명</label></td><td class="td_content"><%=enter.getBoard_enter_company() %></td></tr>
<tr><td class="td_name"><label for="Location">근무지</label></td><td class="td_content">
<div id="mapSearch" class="m-all-10"><%=enter.getBoard_enter_location() %></div>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

    <div id="menu_wrap" class="bg_white" style="display: none">
        <div class="option">
            <div>
                    키워드 : <input type="text" value="<%=enter.getBoard_enter_location() %>" id="keyword" size="15" onKeyPress="if( event.keyCode==13 ){searchPlaces();}"> 
                    <button type="button" onclick="searchPlaces(); return false;">검색하기</button> 
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>

<script type="text/javascript" src="js/map.js"></script>

</td></tr>
<tr><td class="td_name"><label for="DetailContent">업무내용</label></td><td class="td_content"><%=enter.getBoard_enter_content_detail() %></td></tr>
<tr><td class="td_name">이용 프로그램</td><td class="td_content"><%=program %></td></tr>
<tr><td class="td_name">고용형태</td><td class="td_content"><%=hiring %></td></tr>
<tr><td class="td_name"><label for="Salary">연봉</label></td><td class="td_content"><%=enter.getBoard_enter_salary()	%>만원</td></tr>
<tr><td class="td_name">근무시간</td><td class="td_content"><%=startWork%> ~ <%=finWork %></td></tr>
<tr><td class="td_name">근무요일</td><td class="td_content">주 <%=workday %>일 (<%=workday_list %>)</td></tr>
<tr><td class="td_name"><label for="Ref1">회사 링크</label></td>
<td id="td_ref" class="td_content"><a href="<%=enter.getBoard_enter_ent_ref()%>" target="_blank"><%=enter.getBoard_enter_ent_ref() %></a></td></tr>
</table>
<div class="write_form_submit">
<input type="button" id="UpdateBtn" value="수정하기" onclick="location.href = 'EnterUpdate.bo?board_id=<%=enter.getBoard_id()%>'">
<input type="button" id="DeleteBtn" value="삭제하기" onclick="location.href = 'EnterDeletePro.bo?board_id=<%=enter.getBoard_id()%>'">
<input type="button" id="ListBtn" value="목록으로" onclick="location.href = 'EnterList.bo'">
</div>
</div>
<%
    }
%>
<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>