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
import com.hanjum.user.action.UserLogoutAction;
import com.hanjum.user.action.UserManageAction;
import com.hanjum.user.action.UserSearchManageAction;
import com.hanjum.user.action.UserUpdateEditorProAction;
import com.hanjum.user.action.UserUpdateFormAction;
import com.hanjum.user.action.UserUpdateProAction;
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
		
		if (command.equals("/Join.uo")) { // 회원가입 =========================================================
			forward = new ActionForward();
			forward.setPath("/user/userInsertForm.jsp");
		} else if (command.equals("/JoinEditor.uo")) { // 편집자 회원가입 =======================================
			forward = new ActionForward();
			forward.setPath("/user/userInsertEditorForm.jsp");
		} else if (command.equals("/JoinPro.uo")) { // 회원가입 액션 ============================================
			action = new UserInsertProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/JoinEditorPro.uo")) { // 편집자 회원가입 액션 =================================
			action = new UserInsertEditorProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/Login.uo")) { // 로그인 ===================================================
			forward = new ActionForward();
			forward.setPath("/user/userLoginForm.jsp");
		} else if (command.equals("/LoginPro.uo")) { // 로그인 액션 =============================================
			action = new UserLoginProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserUpdateForm.uo")) { // 회원 & 편집자 정보 수정 ==============================
			action = new UserUpdateFormAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserUpdatePro.uo")) { // 회원 정보 수정 액션 ===================================
			action = new UserUpdateProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserUpdateEditorPro.uo")) { // 편집자 정보 수정 액션 ===========================
			action = new UserUpdateEditorProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserDeletePro.uo")) {
			action = new UserDeleteProAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserManage.uo")) {
			action = new UserManageAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserInfo.uo")) {
			action = new UserInfoAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserSearchManage.uo")) {
			action = new UserSearchManageAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserLike.uo")) {
			action = new UserLikeAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserLogout.uo")) {
			action = new UserLogoutAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
		} else if (command.equals("/UserCheck.uo")) {
			System.out.println("에이젝스로왔따!!");
			action = new UserCheckIdAction();
			try {forward = action.execute(request, response);} catch (Exception e) {e.printStackTrace();}
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
