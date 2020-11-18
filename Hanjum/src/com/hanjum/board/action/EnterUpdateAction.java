package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EnterProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EnterBean;
import com.hanjum.vo.ActionForward;

public class EnterUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EnterUpdateAction!");
		ActionForward forward = null;
		int board_id = 0;
		if(request.getParameter("board_id") != null) {
			board_id = Integer.parseInt(request.getParameter("board_id"));
		}
		
		BoardProService boardProService = new BoardProService();
		BoardBean boardBean = boardProService.getBoard(board_id);
		
		EnterProService enterProService = new EnterProService();
		EnterBean enter = enterProService.getEnter(boardBean);
		
		request.setAttribute("enter", enter);
		
		forward = new ActionForward();
		forward.setPath("/enter/enterUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
