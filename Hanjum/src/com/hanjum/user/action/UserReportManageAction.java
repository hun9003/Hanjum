package com.hanjum.user.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.vo.PageInfo;
import com.hanjum.user.vo.ReportBean;
import com.hanjum.vo.ActionForward;


public class UserReportManageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 10; // 페이지 당 표시할 게시물 수를 결정하는 변수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		UserProService userReportManageService = new UserProService();
		int listCount = userReportManageService.getReportListCount();
		System.out.println("전체 게시물 수 : " + listCount);
		
		ArrayList<ReportBean> reportList = new ArrayList<ReportBean>();
		reportList = userReportManageService.getReportList(page,limit);
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)((double)page / 10 + 0.9) - 1) * 10 + 1;
		
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		forward.setPath("/admin/admin_user_report.jsp");
		return forward;
	}

}
