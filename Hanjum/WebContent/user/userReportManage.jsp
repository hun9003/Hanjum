  
<%@page import="com.hanjum.user.vo.ReportBean"%>
<%@page import="com.hanjum.user.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	
<%
	ArrayList<ReportBean> reportList = (ArrayList<ReportBean>)request.getAttribute("reportList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
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
<div class="write_title"><h1><span onclick="location.href='UserManage.uo'">유저 관리</span> / <span onclick="location.href='UserReportManage.uo'">신고 관리</span></h1></div>
<form action="UserSearchReportManage.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<%
			if(reportList != null && listCount > 0) {
			%>
				<tr id="tr_top" >
					<td class="td_name">신고 번호</td>
					<td class="td_name">피해 유저</td>
					<td class="td_name">가해 유저</td>
					<td class="td_name">신고 타입</td>
					<td class="td_name">내용</td>
				</tr>
				<%for(int i = 0; i < reportList.size(); i++) {
				 	String user_type = null;
				%>
				<tr >
<%-- 					<td class="td_content" onclick="location.href='UserInfo.uo?user_id=<%=userList.get(i).getUser_id()%>'" ><%=userList.get(i).getUser_id() %></td> --%>
					<td class="td_content"><%=reportList.get(i).getReport_id()%></td>
					<td class="td_content"><%=reportList.get(i).getUser_id()%></td>
					<td class="td_content"><%=reportList.get(i).getReport_userId() %></td>
					<td class="td_content"><%switch (reportList.get(i).getReport_type()) {
					// 
					case 1:%>욕설(1)<%break;
					case 2:%>불건전한 아이디(2)<%break;
					case 3:%>사기(3)<%break;
					case 4:%>기타(4)<%break;
					}%></td>
					<td class="td_content"><%=reportList.get(i).getReport_content() %></td>
					
				</tr>
					<%} %>
				<tr>
				<td colspan="4">
				<%if(nowPage <= 1) {%>
			<input type="button" value="이전">&nbsp;
	<%} else {%>
			<input type="button" value="이전" onclick="location.href='UserReportManage.uo?page=<%=nowPage - 1 %>'">&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				[<%=i %>]&nbsp;
			<%} else { %>
					<a href="UserReportManage.uo?page=<%=i %>">[<%=i %>]</a>&nbsp;
			<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<input type="button" value="다음">
	<%} else { %>
			<input type="button" value="다음" onclick="location.href='UserReportManage.uo?page=<%=nowPage + 1 %>'">
	<%} %>
	<%}
	else {
	%>
	<section id="emptyArea">등록된 신고가 없습니다</section>
	<%
	}
	%>
	</td>
	<td colspan="1" class="td_name">
	검색 분류&nbsp;<select name="searchType"><option>신고 번호</option><option>피해 유저</option><option>가해 유저</option><option>신고 타입</option></select>
	<input type="text" name="search" id="search">
	<input type="submit" value="검색">
	</td>
	
	</tr>
</table>
</form>

</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>