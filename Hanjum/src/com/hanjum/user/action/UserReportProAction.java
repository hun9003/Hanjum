package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.svc.UserProService;
import com.hanjum.vo.ActionForward;

public class UserReportProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		String report_userid = request.getParameter("report_userid");
		int report_type = Integer.parseInt(request.getParameter("report_type"));
		String report_content = request.getParameter("report_content");
		if(user_id.equals(report_userid)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('자수는 불가능합니다.')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			UserProService userReportService = new UserProService();
			boolean reportSuccess = userReportService.userReport(user_id,report_userid,report_type,report_content);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			if(!reportSuccess) {
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('신고 실패!')"); // 다이얼로그 메세지 출력
				out.println("history.back()"); // 이전 페이지로 이동
				out.println("</script>"); // 자바스크립트 끝 태그
			} else {
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('성공적으로 신고하였습니다.')"); // 다이얼로그 메세지 출력
				out.println("location.href='Home.uo'"); // 이전 페이지로 이동
				out.println("</script>"); // 자바스크립트 끝 태그
			}
		}
		
		
		return forward;
	}

}
