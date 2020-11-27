package com.hanjum.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.notice.action.NoticeListAction;
import com.hanjum.notice.action.NoticeNewAlarmAction;
import com.hanjum.notice.action.NoticeOldAlarmAction;
import com.hanjum.notice.action.NoticeSetAction;
import com.hanjum.notice.action.NoticeUpdateStatusAction;
import com.hanjum.vo.ActionForward;



@WebServlet("*.nt")
public class NoticeFrontController extends HttpServlet {
	 // get/post 방식에 맞게 처리하는 메서드. doGet(), doPost()로부터 호출됨
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // POST 방식 요청
		
		// 서블릿 주소 가져옴
		String command = request.getServletPath();
		System.out.println("Servlet Path : " + command);		
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/getNewNotice.nt")) { 
			// 새로운 알람 불러오기
			System.out.println("getNewNotice.nt");
			
			action = new NoticeNewAlarmAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getNoticeList.nt")) {
			// 알람 전체
			System.out.println("getNoticeList.nt");
			
			action = new NoticeListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else if(command.equals("/getOldNotice.nt")) {
			// 확인된 알람
			System.out.println("getOldNotice.nt");
			
			action = new NoticeOldAlarmAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/setNotice.nt")) {
			// 알람 값들 입력 
			System.out.println("setNotice.nt");
			
			action = new NoticeSetAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateStatus.nt")) {
			// 안읽은알람 -> 읽은알람상태로 바꿔줌
			System.out.println("updateStatus.nt");
			
			action = new NoticeUpdateStatusAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		else if(command.equals("/")){
//			
//		}
		
		
		
		
		// forward 존재여부판별해서 : dispatcher or redirect
		if(forward != null) {
			if(forward.isRedirect()) {
				// redirect - true
				response.sendRedirect(forward.getPath());			
			}else {
				// dispatcher - false
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}
			
		} // forward 객체 있는지?
		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
