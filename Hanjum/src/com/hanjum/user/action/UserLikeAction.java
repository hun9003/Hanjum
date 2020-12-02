package com.hanjum.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.hanjum.action.Action;
import com.hanjum.user.svc.UserProService;
import com.hanjum.vo.ActionForward;

public class UserLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String user_id = request.getParameter("user_id");
		String like_userid = request.getParameter("like_userid");
		
		if(user_id.equals(like_userid)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('자기애가 강하시군요')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			UserProService userLikeService = new UserProService();
			boolean likeSuccess = userLikeService.userLike(user_id,like_userid);
			
			if(!likeSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('좋아요 실패~!')"); // 다이얼로그 메세지 출력
				out.println("history.back()"); // 이전 페이지로 이동
				out.println("</script>"); // 자바스크립트 끝 태그
			} else {
				// 1. ActionForward 객체 생성
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter(); 
				out.println("<script>"); // 자바스크립트 시작 태그
				out.println("alert('좋아요 성공!')"); // 다이얼로그 메세지 출력
				out.println("</script>"); // 자바스크립트 끝 태그
				forward = new ActionForward();
				// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
				forward.setPath("Home.uo");
				// 3. 포워딩 방식(Redirect 방식) 지정
				forward.setRedirect(true);
			}
		}
		
		
		return forward;
	}

}
