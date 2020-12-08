package com.hanjum.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.action.UserLoginProAction;
import com.hanjum.user.action.UserLogoutAction;
import com.hanjum.user.action.UserManageAction;
import com.hanjum.user.action.UserSearchManageAction;
import com.hanjum.user.action.UserUpdateEditorProAction;
import com.hanjum.user.action.UserUpdateFormAction;
import com.hanjum.user.action.UserUpdateProAction;
import com.hanjum.user.svc.UserProService;
import com.hanjum.user.action.UserCheckIdAction;
import com.hanjum.user.action.UserDeleteProAction;
import com.hanjum.user.action.UserInfoAction;
import com.hanjum.user.action.UserInsertEditorProAction;
import com.hanjum.user.action.UserInsertProAction;
import com.hanjum.user.action.UserLikeAction;
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
			
		} else if (command.equals("/UserManage.uo")) {
			action = new UserManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserInfo.uo")) {
			action = new UserInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserSearchManage.uo")) {
			action = new UserSearchManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserLike.uo")) {
			action = new UserLikeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserLogout.uo")) {
			action = new UserLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		
		else if (command.equals("/UserCheck.uo")) {
			System.out.println("에이젝스로왔따!!");
			action = new UserCheckIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		else if (command.equals("/CodeCheck.uo")) {   // 메일발송한거 코드체크
			String email = request.getParameter("email");
			String code = request.getParameter("code");
			UserProService codeCheckService = new UserProService();
			boolean success = codeCheckService.codeCheck(email,code);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				System.out.println(1);
				out.println("1");
				out.close();
			}else {
				System.out.println(0);
				out.println("0");
				out.close();
			}
		}
		
		else if (command.equals("/changePass.uo")) { // 비밀번호 변경
			System.out.println("에이젝스로왔따!!");
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			String user_changePass = request.getParameter("user_changePass");
			UserProService userChangePass = new UserProService();
			boolean success = userChangePass.changePass(user_id,user_pass,user_changePass);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				System.out.println(1);
				out.println("1");
				out.close();
			}else {
				System.out.println(0);
				out.println("0");
				out.close();
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
