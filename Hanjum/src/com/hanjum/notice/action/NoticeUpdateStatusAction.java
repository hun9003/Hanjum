package com.hanjum.notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.vo.ActionForward;

public class NoticeUpdateStatusAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeUpdateStatusAction");
		
		// 새 알람을 읽은알람으로 전환
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		NoticeProService service = new NoticeProService();
		service.updateStatus(notice_id);
		
		
		return null;
	}

}
