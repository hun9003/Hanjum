package com.hanjum.board.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.vo.Constant;

public class ProjectDAO {
	private ProjectDAO() {}
	
	private static ProjectDAO instance = new ProjectDAO();

	public static ProjectDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	// GET ======================================================================================
	
	public ProjectBean getProjectInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("ProjectDAO - getProjectInfo()");
		ProjectBean projectBean = null;
		if(boardBean instanceof ProjectBean) {
			projectBean = (ProjectBean)boardBean;
		}
		
		return projectBean;
	}
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
	
	public int insertProject(ProjectBean ProjectBean) { // 에디터 게시물 생성
		System.out.println("ProjectDAO - insertProject()");
		int insertCount = 0;
		
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateProject(ProjectBean ProjectBean) { // 에디터 게시물 수정
		System.out.println("ProjectDAO - updateProject()");
		int updateCount = 0;
		
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteProject(int board_id) { // 에디터 게시글 삭제
		System.out.println("ProjectDAO - deleteProject()");
		int deleteProject = 0;
		
		return deleteProject;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<ProjectBean> listProject(int startRow){ // 에디터 게시글 리스트
		System.out.println("ProjectDAO - listProject()");
		int pageSize = Constant.BOARD_PAGE_SIZE;
		ArrayList<ProjectBean> list = null;
		
		
		return list;
	}
	
	public ArrayList<ProjectBean> listSeatchProject(int startRow, HashMap<Integer, ArrayList<Object>> seatch){ // 에디터 검색
		ArrayList<ProjectBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
}
