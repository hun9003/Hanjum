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
		
		return boardDAO.selectBoardInfo(board_id);
	}
	
}
