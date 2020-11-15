package com.hanjum.board.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.BoardProService;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;

public class ProjectWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectWriteProAction!");
		
		ActionForward forward = null;
		boolean isSuccess = false;
		
		BoardBean boardBean = new ProjectBean();
		boardBean.setBoard_subject(request.getParameter("board_subject"));
		boardBean.setBoard_content(request.getParameter("board_content"));
		boardBean.setBoard_date(new Timestamp(System.currentTimeMillis()));
		boardBean.setBoard_type(1);
		BoardProService boardProService = new BoardProService();
		
		boardBean.setBoard_id(boardProService.getBoardLastId());
		isSuccess = boardProService.writeBoard(boardBean);
		if(isSuccess) {
			if(boardBean instanceof ProjectBean) {
				String[] genreArr = request.getParameterValues("board_creator_genre[]");
				String genre = Arrays.toString(genreArr).replaceAll("[\\[\\] ]", "");
				
				String[] refArr = request.getParameterValues("board_creator_cre_ref[]");
				String ref = Arrays.toString(refArr).replaceAll("[\\[\\] ]", "");
				
				ProjectBean projectBean = (ProjectBean)boardBean;
				projectBean.setBoard_creator_content_detail(request.getParameter("board_creator_content_detail"));
				projectBean.setBoard_creator_genre(genre);
				projectBean.setBoard_creator_recording(Integer.parseInt(request.getParameter("board_creator_recording")));
				projectBean.setBoard_creator_cam_num(Integer.parseInt(request.getParameter("board_creator_cam_num")));
				projectBean.setBoard_creator_ori_clip_num(Integer.parseInt(request.getParameter("board_creator_ori_clip_num")));
				projectBean.setBoard_creator_ori_length(Integer.parseInt(request.getParameter("board_creator_ori_length")));
				projectBean.setBoard_creator_edit_length(Integer.parseInt(request.getParameter("board_creator_edit_length")));
				projectBean.setBoard_creator_ori_transfer(Integer.parseInt(request.getParameter("board_creator_ori_transfer")));
				projectBean.setBoard_creator_cre_ref(ref);
				projectBean.setBoard_creator_cre_min_price(Integer.parseInt(request.getParameter("board_creator_cre_min_price")));
				projectBean.setBoard_creator_cre_max_price(Integer.parseInt(request.getParameter("board_creator_cre_max_price")));
				projectBean.setBoard_creator_status(1);
				
				ProjectProService projectWriteProService = new ProjectProService();
				isSuccess = projectWriteProService.writeProject(projectBean);
				if(!isSuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(Constant.arrMsg);
				} else {
						forward = new ActionForward();
						forward.setPath("ProjectList.bo");
						forward.setRedirect(true);
				}
				
			} else {
				System.out.println("형변환 실패!");
			}
		}
		return forward;
	}
}
