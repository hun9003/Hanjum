package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.vo.ActionForward;

public class EnterUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EnterUpdateProAction!");
		ActionForward forward = null;
		
		/*
		 * 	서비스 호출
		 */
		
		/*
		 *  서비스 리턴 값 검사 후 포워딩
		 */
		
		forward = new ActionForward();
		forward.setPath("EnterContent.bo");
		forward.setRedirect(false);
		return forward;
	}
	
}
