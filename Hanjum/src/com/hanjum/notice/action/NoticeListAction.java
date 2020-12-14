package com.hanjum.notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hanjum.action.Action;
import com.hanjum.notice.service.NoticeProService;
import com.hanjum.notice.vo.NoticeBean;
import com.hanjum.vo.ActionForward;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		
		// NoticeProService에 수행할 작업 요청
		NoticeProService service = new NoticeProService();
		ArrayList<NoticeBean>  noticeList = service.getNoticeList(user_id);
		JSONArray noticeJsonList = new JSONArray();
		
		for(int i = 0; i < noticeList.size(); i++) {
			NoticeBean notice = (NoticeBean)noticeList.get(i);
			JSONObject jo = new JSONObject();
			jo.put("notice_id", notice.getNotice_id());
			jo.put("notice_content", notice.getNotice_content());
			jo.put("notice_date", notice.getNotice_date());
			jo.put("notice_url", notice.getNotice_url());
			jo.put("notice_read", notice.getNotice_read());
			jo.put("board_id", notice.getBoard_id());
			jo.put("user_id", notice.getUser_id());
			jo.put("notice_from_id", notice.getNotice_from_id());
			
			noticeJsonList.add(jo);		
		}
		
		System.out.println(noticeJsonList);
		
		if(!noticeList.isEmpty()) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(noticeJsonList);
			out.close();
		}
		return null;
	}

}
