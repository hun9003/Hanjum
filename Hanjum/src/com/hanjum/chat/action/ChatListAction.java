package com.hanjum.chat.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.hanjum.action.Action;
import com.hanjum.chat.svc.ChetListService;
import com.hanjum.chat.vo.ChatBean;
import com.hanjum.vo.ActionForward;



public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ChetListService chatListService = new ChetListService();
		
		ArrayList<ChatBean> list = new ArrayList<ChatBean>();
		list = chatListService.getListCount(2);  
		request.setAttribute("list", list);
		
		
		forward = new ActionForward();
		forward.setPath("/Chat/ChatWriteForm.jsp");
		return forward;
	}

}
