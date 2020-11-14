package com.hanjum.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.vo.ActionForward;

public class ProjectListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectListAction!");
		ActionForward forward = null;
		
		ProjectProService projectProService = new ProjectProService();
		projectProService.listProject(1);
		
		/*
		 *  서비스 리턴 값 검사 후 포워딩
		 */
		
		forward = new ActionForward();
		forward.setPath("/project/projectList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
