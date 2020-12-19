package com.hanjum.chat.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.chat.service.ChatWriteProService;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.vo.ActionForward;



public class ChatWriteProAction implements Action {
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ChatWriteProAction!");
		
		System.out.println(request.getParameter("Chat_content"));
		
		ActionForward forward = null;
		
		String chat_to_id = request.getParameter("chat_to_id");
		String chat_from_id = request.getParameter("chat_from_id");
		
		ChatBean chatBean = new ChatBean();
		chatBean.setChat_to_id(chat_to_id);
		chatBean.setChat_from_id(chat_from_id);
		chatBean.setChat_content(request.getParameter("Chat_content"));
		chatBean.setBoard_id(2); // 임시값
//		chatBean.setBoard_id(requset.getParameter("board_id")); // 이거 살려서 써야됨
		
		System.out.println(chatBean.getChat_content());
		
		ChatWriteProService ChatWriteProService = new ChatWriteProService();
		boolean isWriteSuccess = ChatWriteProService.registChat(chatBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/heml; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성에 실패했습니다. 계속 실패 시 관리자에게 문의해주십시오.')"); 
			out.println("history.back()"); 
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("ChatWriteForm.ch");
			forward.setRedirect(true);
		}
		return forward;
			
			
		}
		
		
		
		
		
	

}
