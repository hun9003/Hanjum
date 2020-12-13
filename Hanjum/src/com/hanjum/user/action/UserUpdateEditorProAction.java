package com.hanjum.user.action;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.user.service.UserProService;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.vo.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UserUpdateEditorProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateEditorProAction!");
		ActionForward forward = null;
		ServletContext context = request.getServletContext();
		
		String saveFolder = "/editorUserPhotoUpload";
		
		String realFolder = context.getRealPath(saveFolder);
		
		int fileSize = 1024 * 1024 * 10; // 10MB
		MultipartRequest multi = new MultipartRequest(
				request, // HttpServletRequest(request) 객체 
				realFolder, // 실제 업로드 폴더
				fileSize, // 한번에 업로드 가능한 1개파일 최대 크기
				"UTF-8", // 파일명에 대한 인코딩 방식
				new DefaultFileRenamePolicy() // 파일명 중복 시 중복 처리 객체
		);
		String[] programArr = multi.getParameterValues("editor_program");
		String program = Arrays.toString(programArr).replaceAll("[\\[\\] ]", "");
		String[] solutionArr = multi.getParameterValues("editor_solution");
		String solution = Arrays.toString(solutionArr).replaceAll("[\\[\\] ]", "");
		String[] inventoryArr = multi.getParameterValues("editor_inventory");
		String inventory = Arrays.toString(inventoryArr).replaceAll("[\\[\\] ]", "");
		EditorBean editorBean = new EditorBean();
		
		editorBean.setUser_id(multi.getParameter("user_id"));
		editorBean.setUser_pass(multi.getParameter("user_pass"));
		editorBean.setUser_name(multi.getParameter("user_name"));
		editorBean.setUser_email(multi.getParameter("user_email"));
		editorBean.setUser_phone(multi.getParameter("user_phone"));
		editorBean.setEditor_photo(multi.getOriginalFileName("editor_photo"));
		editorBean.setEditor_des(multi.getParameter("editor_des"));
		editorBean.setEditor_profile(multi.getParameter("editor_profile"));
		editorBean.setEditor_program(program);
		editorBean.setEditor_solution(solution);
		editorBean.setEditor_inventory(inventory);
		editorBean.setEditor_upload(Integer.parseInt(multi.getParameter("editor_upload")));
		editorBean.setEditor_work(Integer.parseInt(multi.getParameter("editor_work")));
		editorBean.setEditor_meeting(Integer.parseInt(multi.getParameter("editor_meeting")));
		editorBean.setEditor_fort(Integer.parseInt(multi.getParameter("editor_fort")));
		editorBean.setEditor_sample(Integer.parseInt(multi.getParameter("editor_sample")));
		editorBean.setEditor_ed_min_price(Integer.parseInt(multi.getParameter("editor_ed_min_price")));
		editorBean.setEditor_ed_max_price(Integer.parseInt(multi.getParameter("editor_ed_max_price")));
		editorBean.setEditor_address(multi.getParameter("editor_address"));
		editorBean.setUser_type(2);
		UserProService userUpdateEditorProService = new UserProService();
		boolean isSuccess = userUpdateEditorProService.updateUser(editorBean);
		System.out.println("서비스 성공!");
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('회원 수정 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			// 1. ActionForward 객체 생성
			forward = new ActionForward();
			// 2. 포워딩 경로(URL) 지정 (주의! 경로명 앞에 슬래시(/) 기호를 붙이지 말 것!)
			forward.setPath("Home.uo");
			// 3. 포워딩 방식(Redirect 방식) 지정
			forward.setRedirect(true);
		}
		return forward;
	}

}
