<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="write_title"><h1>채용공고 등록</h1></div>
<form action="EnterWritePro.bo" method="post" name="fr_write" id="WriteForm">
<table class="write_table">
<tr><td class="td_name"><label for="Subject">채용공고 제목</label></td><td class="td_content"><div class="input_subject"><input id="Subject" type="text" name="board_subject" required="required"></div></td></tr>
<tr><td class="td_name"><label for="Content">채용공고 소개</label></td><td class="td_content"><textarea id="Content" name="board_content" cols="100" rows="5" ></textarea></td></tr>
<tr><td class="td_name"><label for="Company">회사명</label></td><td class="td_content"><div class="input_subject"><input id="Company" type="text" name="board_enter_company" required="required"></div></td></tr>
<tr><td class="td_name"><label for="Location">근무지</label></td><td class="td_content">
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                    키워드 : <input type="text" value="아이티윌 부산교육센터" id="keyword" size="15" onKeyPress="if( event.keyCode==13 ){searchPlaces();}"> 
                    <button type="button" onclick="searchPlaces(); return false;">검색하기</button> 
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>

<script type="text/javascript" src="js/map.js"></script>
<div id="mapSearch" class="m-all-10"><input id="Location" type="text" name="board_enter_location"></div>

</td></tr>
<tr><td class="td_name"><label for="DetailContent">업무내용</label></td><td class="td_content">
<textarea id="DetailContent" name="board_enter_content_detail"></textarea>
</td></tr>
<tr><td class="td_name">이용 프로그램</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="program1" name="board_enter_program" value="1"><label for="program1"></label><span class="checkbox">프리미어</span></div>
<div class="check_list"><input type="checkbox" id="program2" name="board_enter_program" value="2"><label for="program2"></label><span class="checkbox">파이널컷</span></div>
<div class="check_list"><input type="checkbox" id="program3" name="board_enter_program" value="3"><label for="program3"></label><span class="checkbox">베가스</span></div>
<div class="check_list"><input type="checkbox" id="program4" name="board_enter_program" value="4"><label for="program4"></label><span class="checkbox">에프터 이펙트</span></div>
<div class="check_list"><input type="checkbox" id="program5" name="board_enter_program" value="5"><label for="program5"></label><span class="checkbox">포토샵</span></div>
<div class="check_list"><input type="checkbox" id="program6" name="board_enter_program" value="6"><label for="program6"></label><span class="checkbox">일러스트</span></div>
<div class="check_list"><input type="checkbox" id="program7" name="board_enter_program" value="7"><label for="program7"></label><span class="checkbox">베가스</span></div>
<div class="check_list"><input type="checkbox" id="program8" name="board_enter_program" value="8"><label for="program8"></label><span class="checkbox">오디션</span></div>
<div class="check_list"><input type="checkbox" id="program9" name="board_enter_program" value="9"><label for="program9"></label><span class="checkbox">기타</span></div>
</td></tr>
<tr><td class="td_name">고용형태</td><td class="td_content">
<div class="check_list"><input type="radio" id="Hiring1" name="board_enter_hiring" value="1"><label for="Hiring1"></label><span class="checkbox">계약직</span></div>
<div class="check_list"><input type="radio" id="Hiring2" name="board_enter_hiring" value="2"><label for="Hiring2"></label><span class="checkbox">정규직</span></div>
<div class="check_list"><input type="radio" id="Hiring3" name="board_enter_hiring" value="3"><label for="Hiring3"></label><span class="checkbox">비정규직</span></div>
</td></tr>
<tr><td class="td_name"><label for="Salary">급여</label></td><td class="td_content">
<div class="input_salary">연봉 <input type="number" id="Salary" name="board_enter_salary" min="0" placeholder="ex)2500"> 만원</div>
</td></tr>
<tr><td class="td_name">근무시간</td><td class="td_content">
<div class="input_workTime"><input type="text" id="StartWork" name="board_enter_start_work" placeholder="출근시간 ex)오전 9시"> ~ <input type="text" id="FinWork" name="board_enter_fin_work" placeholder="퇴근시간 ex)오후 6시"></div>
</td></tr>
<tr><td class="td_name">근무요일</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="WorkDays1" name="board_enter_work_days" value="1"><label for="WorkDays1"></label><span class="checkbox">월요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays2" name="board_enter_work_days" value="2"><label for="WorkDays2"></label><span class="checkbox">화요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays3" name="board_enter_work_days" value="3"><label for="WorkDays3"></label><span class="checkbox">수요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays4" name="board_enter_work_days" value="4"><label for="WorkDays4"></label><span class="checkbox">목요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays5" name="board_enter_work_days" value="5"><label for="WorkDays5"></label><span class="checkbox">금요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays6" name="board_enter_work_days" value="6"><label for="WorkDays6"></label><span class="checkbox">토요일</span></div>
<div class="check_list"><input type="checkbox" id="WorkDays7" name="board_enter_work_days" value="7"><label for="WorkDays7"></label><span class="checkbox">일요일</span></div>
</td></tr>
<tr><td class="td_name"><label for="Ref1">회사 링크</label></td>
<td id="td_ref" class="td_content">
<div id="divRef1" class="input_link m-tb-15"><input type="url" name="board_enter_ent_ref" id="Ref1" placeholder="링크를 업로드 하세요."></div>
</td></tr>
</table>
<div class="write_form_submit"><input type="button" id="WriteSubmit" value="등록하기" onclick="document.fr_write.submit()"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>