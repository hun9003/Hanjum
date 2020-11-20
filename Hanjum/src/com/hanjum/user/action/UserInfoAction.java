package com.hanjum.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.svc.UserProService;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		UserProService userInfoService = new UserProService();
		UserBean userBean = userInfoService.getUserInfo(user_id);
		request.setAttribute("userBean", userBean);
		
		if(userBean != null) {
			forward = new ActionForward();
			forward.setPath("user/userInfo.jsp");
		}
		return forward;
	}

}
