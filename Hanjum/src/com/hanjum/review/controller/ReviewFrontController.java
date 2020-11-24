package com.hanjum.review.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.action.ReviewWriteProAction;
import com.hanjum.vo.ActionForward;



@WebServlet("*.bo") // 서블릿 주소 중 XXX.bo 주소에 대한 요청을 전달받아 처리
public class ReviewFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 요청 시 GET 방식 또는 POST 방식의 요청이 들어오면 
		// 공통으로 처리하기 위해 doGet(), doPost() 메서드에서 호출하는 메서드
		// => 파라미터로 request 객체와 response 객체를 전달받음
		
		// POST 방식 요청에 대한 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println("요청 서블릿 주소 : " + command);
		
		// 각 요청 처리에 필요한 객체를 다루는 변수 선언
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Editor.bo")) {
			System.out.println("editor.bo");
			forward = new ActionForward();
			forward.setPath("/editor/reviewcontent.jsp");
		}else if(command.equals("/ReviewWritePro.bo")) {
			
			action = new ReviewWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(command.equals("/ReviewUpdateForm.bo")) {
			forward = new ActionForward();
			forward.setPath("/editor/reviewupdate.jsp");
		}
		
		
		if(forward != null) {
			
			if(forward.isRedirect()) {
			
				response.sendRedirect(forward.getPath());
			} else { 
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
	}   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 요청 시 GET 방식 요청이 들어오면 자동으로 호출되는 메서드
		// => 파라미터로 request 객체와 response 객체를 전달받음
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 요청 시 POST 방식 요청이 들어오면 자동으로 호출되는 메서드
		// => 파라미터로 request 객체와 response 객체를 전달받음
		doProcess(request, response);
	}

}
 