package com.hanjum.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectUpdateAction!");
		ActionForward forward = null;
		int board_id = 0;
		if(request.getParameter("board_id") != null) {
			board_id = Integer.parseInt(request.getParameter("board_id"));
		}
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		HttpSession session = request.getSession();
		UserBean userSession = (UserBean)session.getAttribute("userBean");
		String user_id = userSession.getUser_id();
		
		BoardProService boardProService = new BoardProService();
		boolean isWriter = boardProService.checkBoardWriter(board_id, user_id);
		if(isWriter) {
			boardProService = new BoardProService();
			BoardBean boardBean = boardProService.getBoard(board_id);
			
			ProjectProService projectProService = new ProjectProService();
			ProjectBean project = projectProService.getProject(boardBean);
			
			request.setAttribute("project", project);
			request.setAttribute("page", nowPage);
			forward = new ActionForward();
			forward.setPath("/project/projectUpdate.jsp");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.isNotWriter);
		}
		
		
		return forward;
	}
	
}
