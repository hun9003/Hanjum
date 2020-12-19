<!DOCTYPE html>
<%@page import="com.hanjum.chat.vo.ChatListBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>chat</title>
<!-- 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" /> -->
	<link rel="stylesheet" href="css/chat.css" />
<body>
<div class="login-wrap p-4 p-md-5">
<div class="chat_container p-4">
<div class="chat_side">
<div class="chat_room_list">
<h3>채팅</h3>
<hr>
<div class="chat_room">
<%
	ArrayList<ChatListBean> list = (ArrayList<ChatListBean>)request.getAttribute("list");
	if(list != null){
		for(int i = 0; i < list.size(); i++){
			ChatListBean chatListBean = list.get(i);
%>

	<div class="chat_room_item p-2" id="ch<%=chatListBean.getBoard_id()%>">
		<p class="chat_subject"><%=chatListBean.getBoard_subject() %></p>
		<span class="chat_msg"><%=chatListBean.getChat_content() %></span>
		<span class="chat_date"><%=chatListBean.getChat_date() %></span>
	</div>
<%
		}
	}
%>
</div>
</div>
</div>
<div id="chat_content" class="chat_content">

		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).click(function(){
		$(".chat_room_item").click(function(){
			var board_id = $(this).attr("id").replace("ch","");
			$("#chat_content").empty();
			$("#chat_content").load("ChatContent.ch?board_id="+board_id);
		})
	})
</script>
</body>
</html>


