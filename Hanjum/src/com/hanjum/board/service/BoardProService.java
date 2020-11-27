package com.hanjum.board.service;

import java.sql.Connection;

import com.hanjum.board.dao.BoardDAO;
import com.hanjum.board.vo.BoardBean;
import static com.hanjum.db.JdbcUtil.*;

public class BoardProService {
	private BoardDAO boardDAO;
	private Connection con;
	public BoardProService() {
		boardDAO = BoardDAO.getInstance();// 1단계
		con = getConnection(); // 2단계
		boardDAO.setConnection(con);// 3단계
	}
	
	public BoardBean getBoard(int board_id) {
		System.out.println("BoardProService - getBoard()");
		BoardBean boardBean = boardDAO.selectBoardInfo(board_id);
		close(con);
		return boardBean;
	}
	
	public int getBoardLastId() {
		System.out.println("BoardProService - getBoardLastId()");
		int board_id = boardDAO.selectBoardLastId();
		return board_id;
	}
	
	public int getBoardCount(int board_type) {
		System.out.println("BoardProService - getBoardCount()");
		int count = boardDAO.selectListCount(board_type);
		close(con);
		return count;
	}
	
	
	
	public boolean writeBoard(BoardBean boardBean) {
		System.out.println("BoardProService - writeBoard()");
		boolean isSuccess = false;
		int result = boardDAO.insertBoard(boardBean);
		if(result > 0) {
			isSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
}
