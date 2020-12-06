package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CategoryProService;
import vo.ActionForward;
import vo.CategoryBean;

public class CategoryUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CategoryUpdateAction");
		ActionForward forward = null;
		int category_id= Integer.parseInt(request.getParameter("category_id"));
		CategoryBean cb = new CategoryBean();
		cb.setCategory_id(category_id);
		cb.setCategory_content(request.getParameter("category_content"));
		CategoryProService categoryProService = new CategoryProService();
		boolean isUpdateSuccess=categoryProService.updateCategory(cb);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(!isUpdateSuccess) {
			out.println("<script>");
			out.println("alert('글수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글수정성공')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setPath("CategoryList.cg");
			forward.setRedirect(true);
		}
		return forward;
	}

}
