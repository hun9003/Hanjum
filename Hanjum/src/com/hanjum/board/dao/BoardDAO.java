package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.board.vo.EnterBean;
import com.hanjum.board.vo.ProjectBean;
import com.hanjum.db.JdbcUtil;

public class BoardDAO {
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// GET ======================================================================================
	
	public int getBoardLastId() { // board테이블의 마지막 id 값
		System.out.println("BoardDAO - getBoardLastId()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int board_id = 0;
//		try {
//			String sql = "SELECT MAX(board_id) FROM board";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				board_id = rs.getInt(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(pstmt);
//			JdbcUtil.close(rs);
//		}
		return board_id;
	}
	
	public BoardBean getBoardInfo(int board_id) { // board 테이블 정보 조회
		System.out.println("BoardDAO - getBoardInfo()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
//		try {
//			String sql = "SELECT * FROM board WHERE board_id = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, board_id);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				
//				switch (rs.getInt("board_type")) {
//				case 1: boardBean = new ProjectBean();break;
//				case 2: boardBean = new EditorBean();break;
//				case 3: boardBean = new EnterBean();break;
//				default: boardBean = new BoardBean();
//					break;
//				}
//				
//				boardBean.setBoard_id(board_id);
//				boardBean.setBoard_subject(rs.getString("board_subject"));
//				boardBean.setBoard_content(rs.getString("board_content"));
//				boardBean.setBoard_date(rs.getTimestamp("board_date"));
//				boardBean.setReadcount(rs.getInt("board_readcount"));
//				boardBean.setBoard_type(rs.getInt("board_type"));
//				boardBean.setReport(rs.getInt("board_report"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(pstmt);
//			JdbcUtil.close(rs);
//		}
		return boardBean;
	}
	
	
	// INSERT ===================================================================================
	
	public int insertBoard(BoardBean boardBean) { // board 테이블 작성
		System.out.println("BoardDAO - insertBoard()");
		int insertCount = 0;
		
		return insertCount;
	}
	
	// UPDATE ===================================================================================
	
	public void updateBoardReadcount(int board_id) { // board 조회수 증가
		System.out.println("BoardDAO - updateBoardReadcount()");
	}
	
	public int updateBoard(BoardBean boardBean) { // board 테이블 수정
		System.out.println("BoardDAO - updateBoard()");
		int updateCount = 0;
		
		return updateCount;
	}
	// DELETE ===================================================================================
	
	public int deleteBoard(int board_id) {
		System.out.println("BoardDAO - deleteBoard()");
		int deleteBoard = 0;
		
		return deleteBoard;
	}
	
	
	
}
