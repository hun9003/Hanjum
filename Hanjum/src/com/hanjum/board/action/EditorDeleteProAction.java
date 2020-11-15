package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EditorProService;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EditorDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorDeleteProAction!");
		ActionForward forward = null;
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		EditorProService editorProService = new EditorProService();
		boolean isSuccess = editorProService.dropEditor(board_id);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		} else {
			forward = new ActionForward();
			forward.setPath("EditorList.bo");
			forward.setRedirect(false);
		}
		return forward;
	}
	
}
