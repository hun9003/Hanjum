package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CategoryProService;
import vo.ActionForward;
import vo.CategoryBean;

public class CategoryWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CategoryWriteProAction");
		ActionForward forward = null;
		String category_content = request.getParameter("category_content");
		System.out.println("글내용 : "+category_content);
		CategoryBean cb = new CategoryBean();
		cb.setCategory_content(category_content);
		
		CategoryProService service = new CategoryProService();
		boolean isWriteSuccess=service.insertCategory(cb);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setPath("CategoryList.cg");
			forward.setRedirect(true);
			System.out.println("리다이렉트 : " + forward.isRedirect());
		}
		
		
		
		return forward;
	}

}
