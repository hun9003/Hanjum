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
import com.hanjum.user.action.UserReportManageAction;
import com.hanjum.user.action.UserReportProAction;
import com.hanjum.user.action.UserSearchManageAction;
import com.hanjum.user.action.UserSearchReportManageAction;
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
		
		// 회원가입
		if (command.equals("/UserInsertMain.uo")) { // 종류구분 선택
			
			forward = new ActionForward(); 
			forward.setPath("/user/userInsertMain.jsp"); 
			
		} else if (command.equals("/UserInsertForm.uo")) { //일반 회원가입 폼
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); 
			forward.setPath("/user/userInsertForm.jsp"); 
			
		} else if (command.equals("/UserInsertEditorForm.uo")) { // 에디터 회원가입 폼
			
			forward = new ActionForward(); 
			forward.setPath("/user/userInsertEditorForm.jsp"); 
			
			
		} else if (command.equals("/UserInsertPro.uo")) { // 일반 회원가입 Pro
			
			action = new UserInsertProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/UserInsertEditorPro.uo")) { // 에디터 회원가입 Pro
			
			action = new UserInsertEditorProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		// 로그인 
		else if (command.equals("/UserLoginForm.uo")) {

			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userLoginForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			
		}else if (command.equals("/UserLoginPro.uo")) {
			
			action = new UserLoginProAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 메인으로 이동
		else if (command.equals("/Home.uo")) {
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("home"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
			forward.setRedirect(true);
			
		}
		// 회원 정보 수정 updateForm은 하나로 user_type으로 편집자랑 일반이랑 폼 뿌려주는거 나눠놨어요 action 안에
		else if (command.equals("/UserUpdateForm.uo")) {
			action = new UserUpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserUpdatePro.uo")) { // 일반 회원 수정 Pro
			
			action = new UserUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/UserUpdateEditorPro.uo")) { // 편집 회언 수정 pro
			
			action = new UserUpdateEditorProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 유저 삭제 (미구현)
		else if (command.equals("/UserDeletePro.uo")) {
			
			action = new UserDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 신고 관리
		else if (command.equals("/UserReportManage.uo")) {
			action = new UserReportManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UserSearchReportManage.uo")) {
			action = new UserSearchReportManageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 유저 관리
		else if (command.equals("/UserManage.uo")) {
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
		}
		
		// 유저 좋아요 기능
		else if (command.equals("/UserLike.uo")) {
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
		
		
		// 아이디 체크 얘는 ajax지만 첫시도작이라 action을 만들어서 해봤음 굳이 action만들 필요없어서 다음거부터는 ajax는 action 없음
		else if (command.equals("/UserCheck.uo")) {
			System.out.println("에이젝스로왔따!!");
			action = new UserCheckIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		// 이멜 코드 체크 ajax라 action이 없음
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
		
		// 비밀번호 변경
		else if (command.equals("/changePass.uo")) { // 비밀번호 변경 ajax
			System.out.println("에이젝스로왔따!!");
			String user_id = request.getParameter("user_id");
			String user_pass = request.getParameter("user_pass");
			String user_changePass = request.getParameter("user_changePass");
			UserProService userChangePass = new UserProService();
			boolean success = false;
			System.out.println(user_pass);
			System.out.println("id : " +user_id);
			System.out.println("바꿀비번 : " +user_changePass);
			if(user_pass == null) { // 유저 패스가 없으면 비밀번호 찾기로 온거
				success = userChangePass.changePass(user_id, user_changePass);
			} else { // 유저 패스가 있으면 정상 비밀번호 변경
				success = userChangePass.changePass(user_id,user_pass,user_changePass);
			}
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
		
		// 리포트 폼
		else if (command.equals("/UserReportForm.uo")) { // command 주소 검사
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); // 포워드 객체 생성
			forward.setPath("/user/userReportForm.jsp"); // 포워드경로 지정 , 디스패쳐 방식으로 해야되니 redirect값은 안줌
		}
		
		// 리포트 pro
		else if (command.equals("/UserReportPro.uo")) { // command 주소 검사
			action = new UserReportProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		// 구인상태 변경 - Action 없음 ajax
		else if (command.equals("/ChangeStatus.uo")) {
			String user_id = request.getParameter("user_id");
			int editor_status = Integer.parseInt(request.getParameter("editor_status"));
			
			
			// 상태 변경 노는중(0번) -> 구인중(1번) 으로 바꿔서 디비업뎃
			switch (editor_status) {
			case 1:
				editor_status = 0;
				break;

			case 0:
				editor_status = 1;
				break;
			}
			UserProService userChangePass = new UserProService();
			boolean success = false;
			success = userChangePass.changeStatus(user_id,editor_status);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(success) {
				System.out.println("변경성공! 현재 :editor_status : " + editor_status);
				out.println(editor_status);
				out.close();
			}else {
				// 디비 저장 실패했을때 원래 값으로 다시 돌아가기
				switch (editor_status) {
				case 1:
					editor_status = 0;
					break;

				case 0:
					editor_status = 1;
					break;
				}
				
				System.out.println("변경실패! 현재 :editor_status : " + editor_status);
				out.println(editor_status);
				out.close();
			}
		}
		else if (command.equals("/UserDeleteForm.uo")) { //회원탈퇴
			// 바로 View로 포워딩 실행
			forward = new ActionForward(); 
			forward.setPath("/user/userDeleteForm.jsp"); 
			
		}
		else if (command.equals("/UserDeletePro.uo")) { //회원탈퇴
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
