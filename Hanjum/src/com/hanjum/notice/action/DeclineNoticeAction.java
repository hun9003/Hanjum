package com.hanjum.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class DeclineNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 해당 notice_id 를 읽음으로 바꿈
	 	int notice_id = Integer.parseInt( request.getParameter("notice_id")); 
	
	// user_id = creator(거절한사람), notice_from_id = editor(거절당한사람)
		// notice_content == 5 :   notice_from_id 님의 프로젝트 제안을 거절했습니당ㅋㅋㅋ
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean.setNotice_content(5);
		noticeBean.setNotice_from_id(request.getParameter("notice_from_id"));
		noticeBean.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean1.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean.setNotice_url(request.getParameter("notice_url"));
		noticeBean.setUser_id(request.getParameter("user_id"));
		
		
		
		NoticeProService service = new NoticeProService();
		service.insertNotice(noticeBean);
		NoticeProService service3 = new NoticeProService();
		service3.updateStatus(notice_id); // 해당 notice_읽음; 같은 변수명쓰면 Connection is null error ! 
		
			// ======================================================================
		
		// user_id = editor(거절당한사람), notice_from_id = creator(거절하는 사람)
		// user_from_id 님께서 user_id님의 제안을 거절하였습니당 
		NoticeBean noticeBean2 = new NoticeBean();
		noticeBean2.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
		noticeBean2.setNotice_content(6);
		noticeBean2.setNotice_from_id(request.getParameter("user_id"));
		noticeBean2.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		// noticeBean2.setNotice_read(Integer.parseInt(request.getParameter("notice_read")));
		noticeBean2.setNotice_url(request.getParameter("notice_url"));
		noticeBean2.setUser_id(request.getParameter("notice_from_id"));
					
					
		NoticeProService service2 = new NoticeProService();
		service2.insertNotice(noticeBean2);
			
		return null;
	}

}
