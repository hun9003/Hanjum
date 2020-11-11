package com.hanjum.board.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EnterBean;
import com.hanjum.vo.Constant;

public class EnterDAO {
	private EnterDAO() {}
	
	private static EnterDAO instance = new EnterDAO();

	public static EnterDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	// GET ======================================================================================
	
	public EnterBean getEnterInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("EnterDAO - getEnterInfo()");
		EnterBean EnterBean = null;
		if(boardBean instanceof EnterBean) {
			EnterBean = (EnterBean)boardBean;
		}
		
		return EnterBean;
	}
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
	
	public int insertEnter(EnterBean EnterBean) { // 채용공고 게시물 생성
		System.out.println("EnterDAO - insertEnter()");
		int insertCount = 0;
		
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateEnter(EnterBean EnterBean) { // 채용공고 게시물 수정
		System.out.println("EnterDAO - updateEnter()");
		int updateCount = 0;
		
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteEnter(int board_id) { // 채용공고 게시글 삭제
		System.out.println("EnterDAO - deleteEnter()");
		int deleteEnter = 0;
		
		return deleteEnter;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<EnterBean> listEnter(int startRow){ // 채용공고 게시글 리스트
		System.out.println("EnterDAO - listEnter()");
		int pageSize = Constant.BOARD_PAGE_SIZE;
		ArrayList<EnterBean> list = null;
		
		
		return list;
	}
	
	public ArrayList<EnterBean> listSeatchEnter(int startRow, HashMap<Integer, ArrayList<Object>> seatch){ // 채용공고 검색
		ArrayList<EnterBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
}
