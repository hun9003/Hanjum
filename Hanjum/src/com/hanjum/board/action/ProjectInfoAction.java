package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;
import com.mysql.jdbc.PreparedStatement.ParseInfo;

public class ProjectInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectInfoAction!");
		ActionForward forward = null;
		int board_id = 0;
		if(request.getParameter("board_id") != null) {
			board_id = Integer.parseInt(request.getParameter("board_id"));
		}
		
		BoardProService boardProService = new BoardProService();
		BoardBean boardBean = boardProService.getBoard(board_id);
		
		ProjectProService projectProService = new ProjectProService();
		ProjectBean project = projectProService.getProject(boardBean);
		request.setAttribute("page", request.getParameter("page"));
		request.setAttribute("project", project);
		forward = new ActionForward();
		forward.setPath("/project/projectContent.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
