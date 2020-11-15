package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EnterProService;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EnterDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EnterDeleteProAction!");
		ActionForward forward = null;
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		EnterProService enterProService = new EnterProService();
		boolean isSuccess = enterProService.dropEnter(board_id);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		} else {
			forward = new ActionForward();
			forward.setPath("EnterList.bo");
			forward.setRedirect(false);
		}
		return forward;
	}
	
}
