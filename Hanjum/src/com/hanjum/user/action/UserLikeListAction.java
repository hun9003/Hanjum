package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.PageInfo;
import com.hanjum.user.vo.UserBean;
import com.hanjum.vo.ActionForward;

// ㅠ 팔로우한사람 누군지 보는 리스트인데 저장된 데이터가 아이디밖에없어서 만들고나니 쓸데가없음... 우선 보류
public class UserLikeListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 페이지 당 표시할 게시물 수를 결정하는 변수
		String user_id = request.getParameter("user_id");
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		UserProService userLikeListService = new UserProService();
		int listCount = userLikeListService.getLikeListCount(user_id);
		System.out.println("전체 팔로워 수 : " + listCount);
		
		ArrayList<String> likeList = new ArrayList<String>();
		likeList = userLikeListService.getLikeList(page,limit,user_id);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("likeList", likeList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("");
		return forward;
	}

}
