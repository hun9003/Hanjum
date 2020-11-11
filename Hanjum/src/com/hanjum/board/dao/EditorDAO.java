package com.hanjum.board.dao;

import java.sql.Connection;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;

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
	
	public EditorBean getEditorInfo(BoardBean boardBean) {
		System.out.println("EditorDAO - getEditorInfo()");
		EditorBean editorBean = null;
		if(boardBean instanceof EditorBean) {
			editorBean = (EditorBean)boardBean;
		}
		
		return editorBean;
	}
	
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
		
	public int insertEditor(EditorBean ediorBean) {
		System.out.println("EditorDAO - insertEditor()");
		int insertCount = 1;
		
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
		
		
	// DELETE ===================================================================================
		
		
		
	// LIST =====================================================================================
		
		
		
	// OTHER ====================================================================================
	
	
}
