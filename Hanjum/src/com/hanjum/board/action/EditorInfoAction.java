package com.hanjum.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.EditorProService;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.contract.service.ContractCheckSuccessService;
import com.hanjum.review.service.ReviewService;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class EditorInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EditorInfoAction!");
		ActionForward forward = null;
		
		
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		BoardProService boardProService = new BoardProService();
		String user_id = boardProService.getUser_id(board_id);
		EditorProService editorProService = new EditorProService();
		
		EditorBean editorBean = editorProService.getEditor(user_id);
		request.setAttribute("editorBean", editorBean);
		
		UserProService userProService = new UserProService();
		int pf_count = userProService.getCountPortfolio(user_id);
		request.setAttribute("pf_count", pf_count);
		
		ArrayList<PortfolioBean> portfolioList = userProService.getPortfolioList(user_id);
		request.setAttribute("portfolioList", portfolioList);
		
		
		forward = new ActionForward();
		forward.setPath("/editor/editorContent.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
