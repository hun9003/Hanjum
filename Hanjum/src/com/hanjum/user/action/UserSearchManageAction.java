package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.PageInfo;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

public class UserSearchManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String search = request.getParameter("search");
		String searchType = request.getParameter("searchType");
		if(searchType.equals("ID")) {
			searchType="user_id";
		} else if (searchType.equals("이름")) {
			searchType="user_name";
		} else if (searchType.equals("이메일")) {
			searchType="user_email";
		} else if (searchType.equals("휴대전화")){
			searchType="user_phone";
		} else {
			System.out.println("널임");
		}
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 페이지 당 표시할 게시물 수를 결정하는 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		UserProService userManageService = new UserProService();
		int listCount = userManageService.getListCount(search,searchType);
		System.out.println("전체 게시물 수 : " + listCount);
		
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		userList = userManageService.getUserList(page,limit,search,searchType);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("userList", userList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("/user/userManage.jsp");
		
		return forward;
	}

}
