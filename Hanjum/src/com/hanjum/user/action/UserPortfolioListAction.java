package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserPortfolioListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("UserPortfolioListAction!");
		
		UserProService userProService = new UserProService();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		ArrayList<PortfolioBean> list = userProService.getPortfolioList(userBean.getUser_id());
		int count = userProService.getCountPortfolio(userBean.getUser_id());
		request.setAttribute("portfolioList", list);
		request.setAttribute("pf_count", count);

		forward = new ActionForward();
		forward.setPath("/user/userPortfolioList.jsp");
		
		return forward;
	}

}
