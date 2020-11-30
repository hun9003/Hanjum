package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.HelpListService;
import vo.ActionForward;
import vo.HelpBean;

public class HelpListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HelpListProAction");
		ActionForward forward = null;
		
		ArrayList<HelpBean> list = new ArrayList<HelpBean>();
		
		HelpListService helpListService = new HelpListService();
		
		list = helpListService.getHelpList();
		
		request.setAttribute("list", list);
		
		forward = new ActionForward();
		forward.setPath("/help/help_list.jsp");
		
		return forward;
	}

}
