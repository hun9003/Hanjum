  
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자 회원";
		break;
	default:
		user_type = "관리자";
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
<div class="write_title"><h1>내 정보</h1></div>
<form action="UserUpdatePro.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content"><%=userBean.getUser_id()%></td></tr>
<tr><td class="td_name"><label for="Content">이름</label></td><td class="td_content"><%=userBean.getUser_name()%></td></tr>
<tr><td class="td_name"><label for="Content">이메일</label></td><td class="td_content"><%=userBean.getUser_email()%></td></tr>
<tr><td class="td_name"><label for="Content">휴대 전화</label></td><td class="td_content"><%=userBean.getUser_phone()%></td></tr>
<tr><td class="td_name"><label for="Content">레벨</label></td><td class="td_content">Lv <%=userBean.getUser_level() %></td></tr>
<tr><td class="td_name"><label for="Content">경험치</label></td><td class="td_content"><%=userBean.getUser_lv_exp() %> exp</td></tr>
<tr><td class="td_name"><label for="Content">총 거래 건수</label></td><td class="td_content"><%=userBean.getUser_project_count() %> 건</td></tr>
<tr><td class="td_name"><label for="Content">내 평점</label></td><td class="td_content"><%=userBean.getUser_score() %> 점</td></tr>
<tr><td class="td_name"><label for="Content">회원 타입</label></td><td class="td_content"><%=user_type %></td></tr>
<tr><td class="td_name"><label for="Content">로그인 횟수</label></td><td class="td_content"><%=userBean.getUser_login_count() %> 회</td></tr>
</table>
	<div class="write_form_submit"><input type="button" id="WriteSubmit" value="뒤로가기" onclick='history.back()'>
	<input type="button" id="WriteSubmit" value="좋아요" onclick="location.href='UserLike.uo?user_id=<%=session.getAttribute("user_id")%>&like_userid=<%=userBean.getUser_id()%>'">
	<input type="button" id="WriteSubmit" value="이새기 리폿" onclick="location.href='UserReportForm.uo?report_userid=<%=userBean.getUser_id()%>'">
	</div>
	
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>