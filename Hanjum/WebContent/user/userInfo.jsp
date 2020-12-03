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
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
    <script src="js/jquery.min.js"></script>
<title>My</title>
<style>
.user_name {
	color: #007bff;
	font-weight: bold;
}
.form-group-content progress{
	height: 20px;
	padding: 15px 0px;
}
</style>
</head>
<body>
	<div class="login-wrap p-4 p-md-5">
              <h3 class="mb-4">My <span class="float-r m-r-10"><%=user_type %><span></span></span></h3>
             <div class="form-group">
             <span class="user_lv">Lv <%=userBean.getUser_level() %></span>
             <span class="user_name m-l-10"><%=userBean.getUser_name()%></span>
             <span class="float-r m-r-10">
             	<button class="btn btn-white">채팅</button>
             	<button class="btn btn-primary">알림</button>
             </span>
             </div> 
             <div class="form-group">
			 <span class="form-group-content">
			 	진행중인 프로젝트 <span class="float-r m-r-10"><a data-href="#">0</a>건</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	포인트 <span class="float-r m-r-10"><a data-href="#">0</a>p</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	Exp <span class="float-r m-r-10"><progress value="<%=userBean.getUser_lv_exp() %>" max="100"></progress></span>
			 </span>             
             </div>
             
             <div class="form-group">
			 <span class="form-group-content">
			 	<span class="float-r m-r-10">
			 	<a data-href="#">회원정보수정</a>
			 	<%
             		if(userBean.getUser_type() == 0){
            	 %>
			 	<a href="#" class="m-l-10">관리</a>
	             <%
	             	}
	             %>
			 	</span>
			 </span>             
             </div>
     </div>
</body>
</html>

