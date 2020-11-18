package com.hanjum.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.EnterProService;
import com.hanjum.board.vo.EnterBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class EnterUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EnterUpdateProAction!");
		ActionForward forward = null;
		
		boolean isSuccess = false;
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		EnterBean enterBean = new EnterBean();
		enterBean.setBoard_subject(request.getParameter("board_subject"));
		enterBean.setBoard_content(request.getParameter("board_content"));
		enterBean.setBoard_date(new Timestamp(System.currentTimeMillis()));
		enterBean.setBoard_type(1);
		enterBean.setBoard_id(board_id);
		
		String[] programOriArr = request.getParameterValues("board_enter_program");
		ArrayList<String> programArr = new ArrayList<String>();
		for(String programStr : programOriArr) {
			if(!programStr.equals("")) {
				programArr.add(programStr);
			}
		}
		String program = programArr.toString().replaceAll("[\\[\\] ]", "");
		
		String[] workDayOriArr = request.getParameterValues("board_enter_work_days");
		ArrayList<String> workDayArr = new ArrayList<String>();
		for(String workDayStr : workDayOriArr) {
			if(!workDayStr.equals("")) {
				workDayArr.add(workDayStr);
			}
		}
		String workDay = workDayArr.toString().replaceAll("[\\[\\] ]", "");
		
		enterBean.setBoard_enter_company(request.getParameter("board_enter_company"));
		enterBean.setBoard_enter_content_detail(request.getParameter("board_enter_content_detail"));
		enterBean.setBoard_enter_ent_ref(request.getParameter("board_enter_ent_ref"));
		enterBean.setBoard_enter_fin_work(request.getParameter("board_enter_fin_work"));
		enterBean.setBoard_enter_hiring(Integer.parseInt(request.getParameter("board_enter_hiring")));
		enterBean.setBoard_enter_location(request.getParameter("board_enter_location"));
		enterBean.setBoard_enter_program(program);
		enterBean.setBoard_enter_salary(Integer.parseInt(request.getParameter("board_enter_salary")));
		enterBean.setBoard_enter_start_work(request.getParameter("board_enter_start_work"));
		enterBean.setBoard_enter_work_days(workDay);
		
		EnterProService enterProService = new EnterProService();
		isSuccess = enterProService.modifyEnter(enterBean);
		
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(Constant.arrMsg);
		} else {
			forward = new ActionForward();
			forward.setPath("Enter.bo?board_id="+enterBean.getBoard_id());
			forward.setRedirect(true);
		}
		return forward;
	}
	
}
