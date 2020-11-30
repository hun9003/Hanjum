package com.hanjum.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class NoticeSetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeSetAction.java");
		
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean.setNotice_content(request.getParameter("notice_content"));
		noticeBean.setNotice_from_id(request.getParameter("notice_from_id"));
		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		noticeBean.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setNotice_url(request.getParameter("notice_url"));
		noticeBean.setUser_id(request.getParameter("user_id"));
		noticeBean.setNotice_type(Integer.parseInt(request.getParameter("notice_type")));
		
		NoticeProService service = new NoticeProService();
		// 얘 리턴값? + 어디서 호출당하는지
		service.insertNotice(noticeBean);
		
		// return null 인지 아닌지
		return null;
	}

}
