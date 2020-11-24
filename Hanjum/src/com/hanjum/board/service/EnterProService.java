package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.EnterDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EnterBean;

import static com.hanjum.db.JdbcUtil.*;

public class EnterProService {
	private EnterDAO enterDAO;
	private Connection con;
	public EnterProService() {
		enterDAO = EnterDAO.getInstance(); // 1단계
		con = getConnection(); // 2단계
		enterDAO.setConnection(con); // 3단계
	}
	
	// GET ======================================================================================
	
	public EnterBean getEnter(BoardBean boardBean) { // 채용공고 조회 서비스
		System.out.println("EnterProService - getEnter()");
		EnterBean enterBean = enterDAO.selectEnterInfo(boardBean);
		close(con);
		return enterBean; // EnterBean
		
	}
	
	// INSERT ===================================================================================
	
	public boolean writeEnter(EnterBean enterBean) { // 채용공고 작성 서비스
		System.out.println("EnterProService - writeEnter()");
		BoardBean boardBean = enterBean;
		BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
		boardDAO.setConnection(con); // 3단계
		
		boolean isSuccess = false;
		int count = boardDAO.insertBoard(boardBean);
		if(count > 0) {
			count = enterDAO.insertEnter(enterBean);
			if(count > 0) {
				commit(con);
				isSuccess = true;
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// UPDATE ===================================================================================
	
	public boolean modifyEnter(EnterBean enterBean) { // 채용공고 수정 서비스
		System.out.println("EnterProService - modifyEnter()");
		boolean isSuccess = false;
		int count = enterDAO.updateEnter(enterBean);
		if(count > 0) {
			BoardBean boardBean = enterBean;
			BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
			boardDAO.setConnection(con); // 3단계
			count = boardDAO.updateBoard(boardBean);
			if(count > 0) {
				commit(con);
				isSuccess = true;
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// DELETE ===================================================================================
	
	public boolean dropEnter(int board_id) { // 채용공고 삭제 서비스
		System.out.println("EnterProService - dropEnter()");
		boolean isSuccess = false;
		int count = enterDAO.deleteEnter(board_id);
		if(count > 0) {
			BoardDAO boardDAO = BoardDAO.getInstance(); // 2단계
			boardDAO.setConnection(con); // 3단계
			count = boardDAO.deleteBoard(board_id);
			if(count > 0) {
				commit(con);
				isSuccess = true;
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	// LIST ===================================================================================

	public ArrayList<EnterBean> getListEnter(int startRow){ // 채용공고 리스트 서비스
		System.out.println("EnterProService - getListEnter()");
		ArrayList<EnterBean> list = enterDAO.selectListEnter(startRow);
		close(con);
		return list;
	}
	
	public ArrayList<EnterBean> getListSearchEnter(int startRow, HashMap<String, String> search){ // 채용공고 검색 서비스
		System.out.println("EnterProService - getListSearchEnter()");
		close(con);
		return enterDAO.selectListSearchEnter(startRow, search);
	}
	
}
