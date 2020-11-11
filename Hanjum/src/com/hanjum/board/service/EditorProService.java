package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.EditorDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.db.JdbcUtil;

public class EditorProService {
	private BoardDAO boardDAO;
	private EditorDAO editorDAO;
	private Connection con;
	public EditorProService() {
		editorDAO = EditorDAO.getInstance(); // 1단계
		boardDAO = BoardDAO.getInstance();
		con = JdbcUtil.getConnection(); // 2단계
		// 다수의 메서드를 활용하기 위해 1단계, 2단계를 생성자로 호출
		// 3단계는 각자의 메서드에서 호출 ( 여러 DAO를 활용해야해서 여기서 하기 애매한거같음)
	}
	
	// GET ======================================================================================
	
	public EditorBean getEditor(int board_id) { // 에디터 조회 서비스
		System.out.println("EditorProService - getEditor()");
		boardDAO.setConnection(con);
		BoardBean boardBean = boardDAO.getBoardInfo(board_id);
		
		editorDAO.setConnection(con);
		
		return editorDAO.getEditorInfo(boardBean); // EditorBean
		
	}
	
	// INSERT ===================================================================================
	
	public boolean writeEditor(EditorBean editorBean) { // 에디터 작성 서비스
		System.out.println("EditorProService - writeEditor()");
		boolean isSuccess = false;
		editorDAO.setConnection(con);
		int count = editorDAO.insertEditor(editorBean);
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
	
	public boolean modifyEditor(EditorBean editorBean) { // 에디터 수정 서비스
		System.out.println("EditorProService - modifyEditor()");
		boolean isSuccess = false;
		editorDAO.setConnection(con);
		int count = editorDAO.updateEditor(editorBean);
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
	
	public boolean dropEditor(int board_id) { // 에디터 삭제 서비스
		System.out.println("EditorProService - dropEditor()");
		boolean isSuccess = false;
		editorDAO.setConnection(con);
		int count = editorDAO.deleteEditor(board_id);
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

	public ArrayList<EditorBean> listEditor(int startRow){ // 에디터 리스트 서비스
		System.out.println("EditorProService - listEditor()");
		return editorDAO.listEditor(startRow);
	}
	
	public ArrayList<EditorBean> listSeatchEditor(int startRow, HashMap<Integer, ArrayList<Object>> seatch){ // 에디터 검색 서비스
		System.out.println("EditorProService - listEditor()");
		return editorDAO.listSeatchEditor(startRow, seatch);
	}
	
}
