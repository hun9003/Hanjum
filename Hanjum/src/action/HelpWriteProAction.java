package action;

import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.HelpWriteProService;
import vo.ActionForward;
import vo.HelpBean;

public class HelpWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelpWriteProAction!");
		
		ActionForward forward =  null;

		int help_id = Integer.parseInt(request.getParameter("help_id"));
		String help_question = request.getParameter("help_question");
		String help_answer = request.getParameter("help_answer");
		
		HelpBean helpBean = new HelpBean();
		helpBean.setHelp_id(help_id);
		helpBean.setHelp_question(help_question);
		helpBean.setHelp_answer(help_answer);
		
		HelpWriteProService helpWriteProService = new HelpWriteProService();
		boolean isWriteSuccess = helpWriteProService.registArticle(helpBean);
		
		if(!isWriteSuccess) {
			// 글쓰기 작업 실패 시 자바스크립트를 통해 
			// 실패 메세지 출력 후 이전 페이지로 이동
			// => 자바 코드를 사용하여 응답페이지 생성
			// 1. response 객체의 setContentType() 메서드를 호출하여
			// 문서 타입 및 캐릭터셋 설정
			response.setContentType("text/html; charset=UTF-8");
			// 2. response 객체의 getWrite() 메서드를 호출하여
			//    출력스트림 객체를 리턴받아 PrintWriter 타입으로 저장
			PrintWriter out = response.getWriter();
			// 3. PrintWriter 객체의 println() 메서드를 호출하여
			//    응답페이지에서 수행할 작업을 기술
			//    => 모든 작업(자바스크립트, 태그 등)은 문자열로 지정
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('글 등록 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			forward = new ActionForward();
			forward.setPath("/HelpList.hp");
			forward.setRedirect(true);
		}
		return forward;
		
	}
	
}
