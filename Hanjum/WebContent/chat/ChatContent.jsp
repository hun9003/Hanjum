<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hanjum.chat.vo.ChatBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줌에디터</title>
<script type="text/javascript">
		$(function(){
			$('.chat_room_item').click(function(){
				$('.chat_side').css('width','0px');
				$('.chat_content').css('width','100%');
			});
			$('#chat_list').click(function(){
				$('.chat_side').css('width','100%');
				$('.chat_content').css('width','0px');
			})
			
		})
	</script>
	<script>
// 		$(function(){
// 			$("input[type='text']").keypress(function(e){  //엔터키를 눌렀을때 글이 들어가게 하는 JQury 임.
// 				if(e.keyCode == 13 && $(this).val().length){
// 					var _val = $(this).val();
// 					var _class = $(this).attr("class");
// 					$(this).val('');
// 					var _tar = $(".chat_wrap .inner").append('<div class="item '+_class+'"><div class="box"><p class="msg">'+_val+'</p><span class="time">'+currentTime()+'</span></div></div>');
// 				}
				
// 				setTimeout(function(){
// 					$(".chat_wrap .inner").find(".item:last").addClass("on");
// 				},10)
				
// 				$(".chat_wrap .inner").stop().animate({scrollTop:$(".chat_wrap .inner").height()},500);
				
// 			})
// 		});
		$(function(){
			$("#chat_text_form").focus();
		})
		var currentTime = function(){    //시간 실시간으로.
			var date = new Date();
			var hh = date.getHours();
			var mm = date.getMinutes();
			var apm = hh > 12 ? "오후":"오전";
			var ct = apm+" "+hh+":"+mm+"";
			return ct;
		};
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	
<%
ArrayList<ChatBean> list = (ArrayList<ChatBean>)request.getAttribute("list");
String user_id = "chat_editor_id";
%>

</head>
<body>
  <div class="chat_wrap">
  		<div class="chat-menu">
  		<div class="p-2"><span class="btn btn-light" id="chat_list">목록</span></div>
  		</div>
	<form action="ChatWritePro.ch" method="post" id="ChatWritePro.ch">
			<div class="inner">
				
					<%
					if(list != null){
						
					for(int i = 0; i < list.size(); i++) { 
						SimpleDateFormat sdf = new SimpleDateFormat("a HH:mm");
						String date = sdf.format(list.get(i).getChat_date());
						%>
					<div class="item on <%if(user_id.equals(list.get(i).getChat_to_id())){%>mymsg<%}%>">
					<div class="msgBox">
	<!-- 					<div class="msgBox"> -->
							<p class="msg">
								<%=list.get(i).getChat_content()%>
							</p>
							<span class="time">
								<%=date %>
							</span>
	<!-- 					</div> -->
					</div>
	<%-- 				<%=list.get(i).getChat_creator_id()%> --%>
					</div>
					<%
						}
					}
					%>
				</div>
	
				
			<input type="text" id="chat_text_form" class="mymsg" placeholder="내용 입력" name="Chat_content">
			<input type="submit" id="chat_content" value="전송">
			
	
		</form>
		</div>
</body>
</html>