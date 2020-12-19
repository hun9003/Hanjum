package com.hanjum.chat.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.chat.service.ChatListService;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.vo.ActionForward;

public class ChatInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ChatListService chatListService = new ChatListService();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		ArrayList<ChatBean> list = chatListService.getListCount(board_id);
		
		request.setAttribute("list", list);
		
		forward = new ActionForward();
		forward.setPath("/chat/ChatContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
