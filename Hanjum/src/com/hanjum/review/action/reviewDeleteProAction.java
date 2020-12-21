package com.hanjum.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.review.service.ReviewService;
import com.hanjum.review.vo.ReviewBean;
import com.hanjum.vo.ActionForward;


public class reviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		System.out.println("reviewDeleteAction");
		ReviewBean reviewBean = new ReviewBean();
		int review_id = reviewBean.getReview_id();
//		int review_id = Integer.parseInt(request.getParameter("review_id"));
		String review_form_id = request.getParameter("review_form_id");
		
		ReviewService reviewService = new ReviewService();
		boolean isArticleWriter = 
				reviewService.isArticleWriter(review_id, review_form_id); 
		
		System.out.println("isArticleWriter = " + isArticleWriter);
		
		if(!isArticleWriter) { 
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { 
			boolean isDeleteSuccess = 
					ReviewService.removeArticle(review_id);
			
			
			if(!isDeleteSuccess) { 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { 
				forward = new ActionForward();
				forward.setPath(
						"ReviewList.rv?page=" + request.getParameter("page"));
				forward.setRedirect(true);
			}
		}
		
		
		return forward;
	}


	
	
}
