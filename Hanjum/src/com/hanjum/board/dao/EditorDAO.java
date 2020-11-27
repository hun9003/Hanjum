package com.hanjum.board.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.vo.Constant;

public class EditorDAO {
	private EditorDAO() {}
	
	private static EditorDAO instance = new EditorDAO();

	public static EditorDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// GET ======================================================================================
	
	public EditorBean selectEditorInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("EditorDAO - selectEditorInfo()");
		EditorBean editorBean = null;
		if(boardBean instanceof EditorBean) {
			editorBean = (EditorBean)boardBean;
		}
		
		return editorBean;
	}
	
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
		
	public int insertEditor(EditorBean editorBean) { // 에디터 게시물 생성
		System.out.println("EditorDAO - insertEditor()");
		int insertCount = 0;
		
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateEditor(EditorBean editorBean) { // 에디터 게시물 수정
		System.out.println("EditorDAO - updateEditor()");
		int updateCount = 0;
		
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteEditor(int board_id) { // 에디터 게시글 삭제
		System.out.println("EditorDAO - deleteEditor()");
		int deleteEditor = 0;
		
		return deleteEditor;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<EditorBean> selectListEditor(int startRow){ // 에디터 게시글 리스트
		System.out.println("EditorDAO - selectListEditor()");
		int pageSize = Constant.BOARD_PAGE_SIZE;
		ArrayList<EditorBean> list = null;
		
		
		return list;
	}
	
	public ArrayList<EditorBean> selectListSearchEditor(int startRow, HashMap<Integer, ArrayList<Object>> search){ // 에디터 검색
		System.out.println("EditorDAO - selectListSearchEditor()");
		ArrayList<EditorBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
	
}
