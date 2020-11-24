package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.EditorDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import static com.hanjum.db.JdbcUtil.*;

public class EditorProService {
	private EditorDAO editorDAO;
	private Connection con;
	public EditorProService() {
		editorDAO = EditorDAO.getInstance(); // 1단계
		con = getConnection(); // 2단계
		editorDAO.setConnection(con); // 3단계
	}
	
	// GET ======================================================================================
	
	public EditorBean getEditor(BoardBean boardBean) { // 에디터 조회 서비스
		System.out.println("EditorProService - getEditor()");
		
		editorDAO.setConnection(con);
		
		return editorDAO.selectEditorInfo(boardBean); // EditorBean
		
	}
	
	// INSERT ===================================================================================
	
	public boolean writeEditor(EditorBean editorBean) { // 에디터 작성 서비스
		System.out.println("EditorProService - writeEditor()");
		boolean isSuccess = false;
		int count = editorDAO.insertEditor(editorBean);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// UPDATE ===================================================================================
	
	public boolean modifyEditor(EditorBean editorBean) { // 에디터 수정 서비스
		System.out.println("EditorProService - modifyEditor()");
		boolean isSuccess = false;
		int count = editorDAO.updateEditor(editorBean);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// DELETE ===================================================================================
	
	public boolean dropEditor(int board_id) { // 에디터 삭제 서비스
		System.out.println("EditorProService - dropEditor()");
		boolean isSuccess = false;
		int count = editorDAO.deleteEditor(board_id);
		if(count > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// LIST ===================================================================================

	public ArrayList<EditorBean> getListEditor(int startRow){ // 에디터 리스트 서비스
		System.out.println("EditorProService - getListEditor()");
		close(con);
		return editorDAO.selectListEditor(startRow);
	}
	
	public ArrayList<EditorBean> getListSearchEditor(int startRow, HashMap<String, String> search){ // 에디터 검색 서비스
		System.out.println("EditorProService - getListSearchEditor()");
		close(con);
		return editorDAO.selectListSearchEditor(startRow, search);
	}
	
}
