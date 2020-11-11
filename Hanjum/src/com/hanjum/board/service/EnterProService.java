package com.hanjum.board.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.dao.EnterDAO;
import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EnterBean;
import com.hanjum.db.JdbcUtil;

public class EnterProService {
	private BoardDAO boardDAO;
	private EnterDAO enterDAO;
	private Connection con;
	public EnterProService() {
		enterDAO = EnterDAO.getInstance(); // 1단계
		boardDAO = BoardDAO.getInstance();
		con = JdbcUtil.getConnection(); // 2단계
		// 다수의 메서드를 활용하기 위해 1단계, 2단계를 생성자로 호출
		// 3단계는 각자의 메서드에서 호출 ( 여러 DAO를 활용해야해서 여기서 하기 애매한거같음)
	}
	
	// GET ======================================================================================
	
	public EnterBean getEnter(int board_id) { // 채용공고 조회 서비스
		System.out.println("EnterProService - getEnter()");
		boardDAO.setConnection(con);
		BoardBean boardBean = boardDAO.getBoardInfo(board_id);
		
		enterDAO.setConnection(con);
		
		return enterDAO.getEnterInfo(boardBean); // EnterBean
		
	}
	
	// INSERT ===================================================================================
	
	public boolean writeEnter(EnterBean enterBean) { // 채용공고 작성 서비스
		System.out.println("EnterProService - writeEnter()");
		boolean isSuccess = false;
		enterDAO.setConnection(con);
		int count = enterDAO.insertEnter(enterBean);
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
	
	public boolean modifyEnter(EnterBean enterBean) { // 채용공고 수정 서비스
		System.out.println("EnterProService - modifyEnter()");
		boolean isSuccess = false;
		enterDAO.setConnection(con);
		int count = enterDAO.updateEnter(enterBean);
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
	
	public boolean dropEnter(int board_id) { // 채용공고 삭제 서비스
		System.out.println("EnterProService - dropEnter()");
		boolean isSuccess = false;
		enterDAO.setConnection(con);
		int count = enterDAO.deleteEnter(board_id);
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

	public ArrayList<EnterBean> listEnter(int startRow){ // 채용공고 리스트 서비스
		System.out.println("EnterProService - listEnter()");
		return enterDAO.listEnter(startRow);
	}
	
	public ArrayList<EnterBean> listSeatchEnter(int startRow, HashMap<Integer, ArrayList<Object>> seatch){ // 채용공고 검색 서비스
		System.out.println("EnterProService - listEnter()");
		return enterDAO.listSeatchEnter(startRow, seatch);
	}
	
}
