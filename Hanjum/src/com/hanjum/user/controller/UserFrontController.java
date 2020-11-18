package com.hanjum.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.action.UserLoginProAction;
import com.hanjum.user.action.UserUpdateEditorProAction;
import com.hanjum.user.action.UserUpdateFormAction;
import com.hanjum.user.action.UserUpdateProAction;
import com.hanjum.user.action.UserDeleteProAction;
import com.hanjum.user.action.UserInsertEditorProAction;
import com.hanjum.user.action.UserInsertProAction;
import com.hanjum.vo.ActionForward;

@WebServlet("*.uo")
public class UserFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글처리

		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command); // 서블릿주소 가져오기

		Action action = null;
		ActionForward forward = null; // 요청에 필요한 변수 선언
		
		if (command.equals("/UserInsertMain.uo")) {
			
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userInsertMain.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/UserInsertForm.uo")) { // command 주소 검사
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userInsertForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/UserInsertEditorForm.uo")) {
			
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userInsertEditorForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
			
		} else if (command.equals("/UserInsertPro.uo")) {
			
			action = new UserInsertProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/UserInsertEditorPro.uo")) {
			
			action = new UserInsertEditorProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		else if (command.equals("/UserLoginForm.uo")) {

			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userLoginForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		} else if (command.equals("/UserLoginPro.uo")) {
			
			action = new UserLoginProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/Home.uo")) {
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("home"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			forward.setRedirect(true);
			
		} else if (command.equals("/UserUpdateForm.uo")) {
			action = new UserUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserUpdatePro.uo")) {
			
			action = new UserUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/UserUpdateEditorPro.uo")) {
			
			action = new UserUpdateEditorProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (command.equals("/UserDeletePro.uo")) {
			
			action = new UserDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		// -------------------------------------------------------------------------------------------------------------------
		// 기본 작업 후 공통 작업 수행
		if (forward != null) { // 포워드가 값이 있다 = 포워드 객체가 생성 되었다 -> 실행
			if (forward.isRedirect()) { // Redirect방식 이라면 실행
				response.sendRedirect(forward.getPath()); // Redirect 방식으로 포워딩
			} else { // Redirect방식이 아닐때 = 디스패쳐 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // 디스패쳐 객체생성
				dispatcher.forward(request, response); // Dispatcher방식으로 포워딩
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response); // doProcess로

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response); // doProcess로
	}

}
