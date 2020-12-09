  
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="write_title"><h1>이새기 신고해야돼</h1></div>
<form action="UserReportPro.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<tr><td class="td_name"><label for="Subject">신고자</label></td><td class="td_content"><input id="user_id" type="text" name="user_id" value="<%=session.getAttribute("user_id")%>" readonly="readonly"></td></tr>
<tr><td class="td_name"><label for="Content">신고대상</label></td><td class="td_content"><input id="user_id" type="text" name="report_userid" value="<%=request.getParameter("report_userid")%>" readonly="readonly"/></td></tr>
<tr><td class="td_name">신고 타입</td><td class="td_content">
<div class="check_list"><input type="radio" id="type1" name="report_type" value="1" ><label for="type1"></label><span class="checkbox">욕설</span></div>
<div class="check_list"><input type="radio" id="type2" name="report_type" value="2" ><label for="type2"></label><span class="checkbox">불건전한 아이디</span></div>
<div class="check_list"><input type="radio" id="type3" name="report_type" value="3" ><label for="type3"></label><span class="checkbox">사기</span></div>
<div class="check_list"><input type="radio" id="type4" name="report_type" value="4" ><label for="type4"></label><span class="checkbox">기타</span></div>
</td></tr>
<tr><td class="td_name"><label for="Content">내용</label></td><td class="td_content">
<textarea id="DetailContent" name="report_content" ></textarea>
</td></tr>
</table>
	<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="신고 하기"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>