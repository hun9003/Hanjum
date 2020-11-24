package com.hanjum.board.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanjum.action.Action;
import com.hanjum.board.service.ProjectProService;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.ActionForward;
import com.hanjum.vo.Constant;
import com.hanjum.vo.PageInfo;

public class ProjectSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProjectSearchListAction!");
		ActionForward forward = null;
		
		HashMap<String, String> search = new HashMap<String, String>();
		
		int page = 1;
		int limit = Constant.BOARD_PAGE_SIZE;
		
		String genre = request.getParameter("board_creator_genre");
		String price_n = request.getParameter("board_creator_cre_min_price");
		String price_x = request.getParameter("board_creator_cre_max_price");
		String search_type = request.getParameter("search_type");
		String keyword = request.getParameter("keyword");
		String recording = request.getParameter("board_creator_recording");
		String camnum = request.getParameter("board_creator_cam_num");
		String clipnum = request.getParameter("board_creator_ori_clip_num");
		String oriLength = request.getParameter("board_creator_ori_length");
		String editLength = request.getParameter("board_creator_edit_length");
		String transfer = request.getParameter("board_creator_ori_transfer");
		
		if(genre != null && genre != "") {search.put("genre",genre);}
		if(price_n != null && price_n != "") {search.put("price_n",price_n);}
		if(price_x != null && price_x != "") {search.put("price_x",price_x);}
		if(search_type != null && search_type != "") {search.put("search_type",search_type);}
		if(keyword != null && keyword != "") {search.put("keyword",keyword);}
		if(recording != null && recording != "") {search.put("recording",recording);}
		if(camnum != null && camnum != "") {search.put("camnum",camnum);}
		if(clipnum != null && clipnum != "") {search.put("clipnum",clipnum);}
		if(oriLength != null && oriLength != "") {search.put("oriLength",oriLength);}
		if(editLength != null && editLength != "") {search.put("editLength",editLength);}
		if(transfer != null && transfer != "") {search.put("transfer",transfer);}
		ProjectProService projectProService = new ProjectProService();
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		int listCount = projectProService.getProjectSearchListCount(search);
		System.out.println(listCount);
		list = projectProService.getListSearchProject(page, search);
		
		
		int maxPage = (int)((double)listCount / limit + 0.95);
		
		int startPage = ((int)(page / 10.0 + 0.9)-1)*10+1;
		
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("projectList", list);
		
		forward = new ActionForward();
		forward.setPath("/project/projectList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
