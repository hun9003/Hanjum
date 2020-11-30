package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.HelpListProAction;
import action.HelpWriteProAction;
import vo.ActionForward;

@WebServlet("*.hp")
public class HelpFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/helpList.hp")) {

			action = new HelpListProAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command.equals("/HelpWriteForm.hp")) {
			forward = new ActionForward();
			forward.setPath("/help/help_write.jsp");

		} else if (command.equals("/HelpWritePro.hp")) { // BoardWritePro.bo 서블릿 요청에 대한 처리
			// 1. BoardWriteProAction 클래스 객체 생성
			// => Action 클래스는 Action 인터페이스를 구현하므로 다형성 활용 가능
			action = new HelpWriteProAction();

			try {
				// 2. Action 클래스의 execute() 메서드 호출
				// => 리턴되는 ActionForward 객체 전달받기(직접 생성하지 않음!)
				// => throws 에 의해 예외가 전달되므로 try ~ catch 필요
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		if (forward != null) {
			// 2. ActionForward 객체 내의 포워딩 방식에 따라 각각의 포워딩 수행
			// => Redirect 방식 : isRedirect() == true,
			// Dispatcher 방식 : isRedirect() == false
			if (forward.isRedirect()) {
				// 3. Redirect 방식일 경우
				// response 객체의 sendRedirect() 메서드를 호출하여 포워딩
				// => 파라미터 : 포워딩 할 URL
				response.sendRedirect(forward.getPath());
			} else {
				// 4. Dispatcher 방식일 경우
				// 4-1. request 객체의 getRequestDispatcher() 메서드를 호출하여
				// RequestDispatcher 객체를 리턴받기
				// => 파라미터 : 포워딩 할 URL
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				// 4-2. RequestDispatcher 객체의 forward() 메서드를 호출하여
				// 포워딩 수행(파라미터 : request, response 객체)
				System.out.println(forward.getPath());
				System.out.println(forward.isRedirect());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
