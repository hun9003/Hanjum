<%@page import="com.hanjum.notice.service.NoticeProService"%>
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
<% 

UserBean userBean = new UserBean();
userBean = (UserBean)session.getAttribute("userBean");
String id = null;
int type = 0;
int login = 0;

if(session.getAttribute("userBean") != null){
	id = userBean.getUser_id();
	type = userBean.getUser_type();
} else {
	id = null;
	type = 0;
	login = 0;
}

if(id!= null){
login=1;	
}
%>
<div id="top">
<jsp:include page="side.jsp"/>
	<div id="top_title">
		<h1 id="main_title" class="font_white no_margin" onclick="location.href='home'">한줌에디터</h1>
	</div>
	<div id="top_menu">
		<ul class="menu_col menu_main">
			<li><a href="intro" class="btn_link btn_main font_white">사이트소개</a></li>
			<li><a href="ProjectList.bo" class="btn_link btn_main font_white">프로젝트찾기</a></li>
			<li><a href="EditorList.bo" class="btn_link btn_main font_white">편집자찾기</a></li>
			<li><a href="EnterList.bo" class="btn_link btn_main font_white">채용공고</a></li>
		</ul>
	</div>
	<%
	if(login == 1){
		%>
		<div class="top_member_menu">
		<ul class="menu_col menu_member">
<<<<<<< HEAD
			<li><div id="setting" class="menu_setting"><img src="img/settings-5-fill.png" alt="세팅"></div>
=======
		<%if (type==0){%>
			<li><div id="setting" class="menu_setting"><a href="UserManage.uo"><img src="img/settings-5-fill.png" alt="세팅"></a></div><%}%>
>>>>>>> origin/pje
			<li><div id="noticeBtn" class="menu_notice"><img src="img/notice.png" alt="알림" style="width:40px;"></div></li>
			<li><div id="menuBtn" class="menu_user"><img src="img/menu_white.png" alt="메뉴"></div></li>
		</ul>
		<div id="mySet" class ="menu_manager_content">
			<ul id="manager_list" class="lstyle_n overlay_content no_margin no_padding pullRight">
			<li class="bg_red">관리자 페이지</li>
			 <li><a href="#">회원 관리</a></li>
			 <li><a href="#">계약 관리</a></li>
			 <li><a href="#">카테고리 관리</a></li>
			 <li><a href="#">Help 관리</a></li>
			</ul>
			
		</div>
		<div id="myNav" class="menu_user_content">
			<ul class="lstyle_n overlay_content no_margin no_padding pullRight">
			 <li class="bg_red">메뉴</li>
			 <li><a href="UserUpdateForm.uo?user_id=<%=id%>">My INFO</a></li>
			 <li><a href="#">1:1 문의</a></li>
			 <li><a href="intro#QnA">자주 묻는 질문</a></li>
			 <li><a href="user/userLogout.jsp">로그아웃</a></li>
			</ul>
  		</div>
  		
  		
  		<!--  notice -->
<%
String user_id = "user_id"; // 유저아이디
NoticeProService svc = new NoticeProService();
int count = svc.getNoticeCount(user_id);
%>

<form action="applyNotice.nt">
	<input type="submit" value="프로젝트 신청">
</form>

<form action="matchNotice.nt">
	<input type="submit" value="수락" >
</form>

<form action="declineNotice.nt">
	<input type="reset" value="거절">
</form>

<form action="deleteNotice.nt">
	<input type="submit" value="알림삭제">
</form>



<a href="applyNotice.nt"><input type="hidden" value="'+item.notice_id+'">수락</a>


	<div id="notice_table">
		
		<btn id="btn">
			<%if(count > 0) {%>
			<div id="notice_count"><%=count%></div>
			<%} %>
			<input type="button" id="BtnIcon">
		</btn>
		
		
		<ul id="list">
			<li>	
				<!-- 읽지않은 알람 리스트 뿌림 -->
				<div id="new_notice">
					<table id="new_notice_list">
							<!--  여기에 읽지않은 알람 -->
					</table>
				</div>
			</li>
			
			<li>
				<!--지난 알람 리스트 뿌림 -->
				<div id="old_notice">
					<table id="old_notice_list">
						<!--  읽은알람  -->
					</table>
				</div>
			</li>	
			<li id="li_btn">
				<button id="get_notice_btn">+</button>
			</li>
			
		</ul>	
	</div>
	
	
  		
  		
  		
  		
  		
  		
		</div>
		<%
	} else {
		%>
		<div class="top_member_menu">
		<ul class="menu_col menu_member">
			<li><a href="UserLoginForm.uo" class="btn_link btn_main font_white">로그인</a></li>
			<li><a href="UserInsertMain.uo" class="btn_link btn_main font_white">회원가입</a></li>
		</ul>
		</div>
		<%
	}
	%>
	
</div>
</header>