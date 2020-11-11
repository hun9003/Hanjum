package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.ProjectDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.db.JdbcUtil;

public class ProjectProService {
	private BoardDAO boardDAO;
	private ProjectDAO projectDAO;
	private Connection con;
	public ProjectProService() {
		projectDAO = ProjectDAO.getInstance(); // 1단계
		boardDAO = BoardDAO.getInstance();
		con = JdbcUtil.getConnection(); // 2단계
		// 다수의 메서드를 활용하기 위해 1단계, 2단계를 생성자로 호출
		// 3단계는 각자의 메서드에서 호출 ( 여러 DAO를 활용해야해서 여기서 하기 애매한거같음)
	}
	
	// GET ======================================================================================
	
	public ProjectBean getProject(int board_id) { // 프로젝트 조회 서비스
		System.out.println("ProjectProService - getProject()");
		boardDAO.setConnection(con);
		BoardBean boardBean = boardDAO.getBoardInfo(board_id);
		
		projectDAO.setConnection(con);
		
		return projectDAO.getProjectInfo(boardBean); // ProjectBean
		
	}
	
	// INSERT ===================================================================================
	
	public boolean writeProject(ProjectBean projectBean) { // 프로젝트 작성 서비스
		System.out.println("ProjectProService - writeProject()");
		boolean isSuccess = false;
		projectDAO.setConnection(con);
		int count = projectDAO.insertProject(projectBean);
		if(count > 0) {
			JdbcUtil.commit(con);
			isSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isSuccess;
	}
	
	// UPDATE ===================================================================================
	
	public boolean modifyProject(ProjectBean projectBean) { // 프로젝트 수정 서비스
		System.out.println("ProjectProService - modifyProject()");
		boolean isSuccess = false;
		projectDAO.setConnection(con);
		int count = projectDAO.updateProject(projectBean);
		if(count > 0) {
			JdbcUtil.commit(con);
			isSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isSuccess;
	}
	
	// DELETE ===================================================================================
	
	public boolean dropProject(int board_id) { // 프로젝트 삭제 서비스
		System.out.println("ProjectProService - dropProject()");
		boolean isSuccess = false;
		projectDAO.setConnection(con);
		int count = projectDAO.deleteProject(board_id);
		if(count > 0) {
			JdbcUtil.commit(con);
			isSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isSuccess;
	}
	
	// LIST ===================================================================================

	public ArrayList<ProjectBean> listProject(int startRow){ // 프로젝트 리스트 서비스
		System.out.println("ProjectProService - listProject()");
		return projectDAO.listProject(startRow);
	}
	
	public ArrayList<ProjectBean> listSeatchProject(int startRow, HashMap<Integer, ArrayList<Object>> seatch){ // 프로젝트 검색 서비스
		System.out.println("ProjectProService - listProject()");
		return projectDAO.listSeatchProject(startRow, seatch);
	}
	
}