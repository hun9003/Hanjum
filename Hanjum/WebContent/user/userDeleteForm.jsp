  
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
UserBean userBean = new UserBean();
userBean = (UserBean)session.getAttribute("userBean");
String user_id = userBean.getUser_id();
	
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script>

</script>
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
<div class="write_title"><h1>회원 탈퇴</h1></div>
<form action="UserDeletePro.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<tr><h1 align="center">정말로 탈퇴하시려면 비밀번호를 입력해주세요.</h1></tr>
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content"><input id="user_id" type="text" name="user_id" value="<%=user_id%>" readonly="readonly"></td></tr>
<tr><td class="td_name"><label for="Content">비밀번호</label></td><td class="td_content"><input id="user_pass" type="text" name="user_pass" ></td></tr>
</table>
	<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="탈퇴 완료"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>