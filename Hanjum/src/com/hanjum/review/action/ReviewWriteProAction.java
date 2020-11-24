package com.hanjum.review.action;

import java.io.PrintWriter;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.service.ReviewWriteService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.vo.ActionForward;



public class ReviewWriteProAction implements Action {

	@Override
	public com.hanjum.vo.ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("review pro action");
		
		ActionForward forward = null;
		
		ReviewBean reviewBean = new ReviewBean();
		reviewBean.setReview_content(request.getParameter("review_content"));
		reviewBean.setReview_speciality(Integer.parseInt(request.getParameter("review_speciality")));
		reviewBean.setReview_satisfaction(Integer.parseInt(request.getParameter("review_satisfaction")));
		reviewBean.setReview_positivity(Integer.parseInt(request.getParameter("review_positivity")));
		reviewBean.setReview_communication(Integer.parseInt(request.getParameter("review_communication")));
		reviewBean.setReview_form_id(request.getParameter("review_form_id"));
//		reviewBean.setReview_date(new Timestamp(System.currentTimeMillis()));
		reviewBean.setUser_id(request.getParameter("user_id"));
		
		
		ReviewWriteService reviewWriteService = new ReviewWriteService();
		boolean isWriteSuccess = reviewWriteService.rigisterWriter(reviewBean);
		
		if(!isWriteSuccess) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('리뷰 등록 실패')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		} else {
			
			forward = new ActionForward();
			
			forward.setPath("Editor.bo");
			
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
