<%@page import="java.util.HashMap"%>
<%@page import="com.hanjum.user.vo.EditorBean"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	HashMap<String, String> editorInfo = (HashMap<String, String>)request.getAttribute("editorInfo");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자";
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
    <script type="text/javascript">
    $(document).ready(function(){
    	$("#portfolioArea").load("UserPortfolioList.uo");
    })
    </script>
<title>My</title>
<style>
.color-primary {
	color: #007bff;
}
.form-group-content progress{
	height: 20px;
	padding: 15px 0px;
}
.form-group-content:hover > span {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="login-wrap p-4 p-md-5">
              <h3 class="mb-4">My <span id="USER_ID" class=".color-primary m-l-20"><%=userBean.getUser_id()%></span><span class="float-r m-r-10"><%=user_type %><span></span></span></h3>
             <div class="form-group">
             <span class="user_lv">Lv <%=userBean.getUser_level() %></span>
             
             <input type="hidden" id="user_id" name="user_id" value="<%=userBean.getUser_id() %>">
             <span class=".color-primary m-l-10"><%=userBean.getUser_name()%></span>
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
			 	포인트 <span class="float-r m-r-10"><a data-href="#">0</a> p</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	Exp <span class="float-r m-r-10"><progress value="<%=userBean.getUser_lv_exp() %>" max="<%=userBean.getUser_level()*20 %>" title="경험치 <%=userBean.getUser_lv_exp() %> / <%=userBean.getUser_level()*20 %>"></progress></span>
			 </span>             
             </div>
             <%
             if(editorInfo != null){
            	 %>
             <div class="form-group">
			 <span class="form-group-content">
			 	내 평점 <span class="float-r m-r-10"><span class="color-primary"><%=userBean.getUser_score() %></span> 점</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	접속 횟수 <span class="float-r m-r-10"><span class="color-primary"><%=userBean.getUser_login_count() %></span> 회</span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	이용 프로그램 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("program") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	최종 납품 해상도 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("solution") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	작업 가능 항목 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("inventory") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	납품 파일 공유 방식 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("upload") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	녹화에 이용된 캠 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("work") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	실물 미팅 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("meeting") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	선호 작업 유형 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("fort") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	샘플 작업 가능 유무 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("sample") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	예상 단가 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("min_price") %> ~ <%=editorInfo.get("max_price") %></span></span>
			 </span>             
             </div>
             <div class="form-group">
			 <span class="form-group-content">
			 	거주지 <span class="float-r m-r-10"><span class="color-primary"><%=editorInfo.get("address") %></span></span>
			 </span>             
             </div>
             <div id="portfolioArea">
<!--                포트폴리오 공간              -->
             </div>
            	 <%
             }
             %>
             <div class="form-group">
			 <span class="form-group-content">
			 	<span class="float-r m-r-10">
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

