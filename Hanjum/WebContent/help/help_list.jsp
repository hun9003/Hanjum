
<%@page import="vo.HelpBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<HelpBean> list = (ArrayList<HelpBean>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet" type="text/css">
<link href="css/intro.css" rel="stylesheet" type="text/css">
<link href="css/front.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.5.1.js"></script>
<title>MVC 게시판</title>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		border: 1p solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: orange;
		text-align: center;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
	}
	
	a {
		text-decoration: none;
	}
	
</style>
</head>
<body>
<div id="wrap">

<div class="clear"></div>
	<!-- 게시판 리스트 -->
	<div style="height: 1300px;">
	<section id="listForm">
		<h2>Q&A 자주묻는질문 </h2>
		<table>
				<colgroup>
					<col width="10%"/>
				    <col width="45%"/>
				    <col width="45%"/>
				</colgroup>
				<tr id="tr_top">
					<td>번호</td>
					<td>질문</td>
					<td>답변</td>
				</tr>
				<%
				for(int i=0;i<list.size();i++){
				%>
				<tr>
					<td align="center"><%=list.get(i).getHelp_id() %></td>
					<td align="center"><%=list.get(i).getHelp_question()%></td>
					<td align="center"><%=list.get(i).getHelp_answer() %></td>
				</tr>
				<%
				}
				%>
		</table>
	</section>
	<section id="buttonArea">
		<input type="button" value="글쓰기" onclick="location.href='HelpWriteForm.hp'" />
	</section>
	<section id="pageList">
	</section>
	</div>
<div class="clear"></div>
</div>
</body>
</html>
