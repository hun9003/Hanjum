package com.hanjum.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.vo.ActionForward;

public class ReviewListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		System.out.println("ReviewListAction");
		
		
		
		if(request.getParameter("page") != null) {
			
		}
		
		
		
		return forward;
	}
	
}
