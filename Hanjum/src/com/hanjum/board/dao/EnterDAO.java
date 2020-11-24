package com.hanjum.board.dao;

import static com.hanjum.db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public EnterBean selectEnterInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("EnterDAO - selectEnterInfo()");
		EnterBean enterBean = null;
		if(boardBean instanceof EnterBean) {
			enterBean = (EnterBean)boardBean;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board_enter WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, enterBean.getBoard_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				enterBean.setBoard_enter_company(rs.getString("board_enter_company"));
				enterBean.setBoard_enter_content_detail(rs.getString("board_enter_content_detail"));
				enterBean.setBoard_enter_ent_ref(rs.getString("board_enter_ent_ref"));
				enterBean.setBoard_enter_fin_work(rs.getString("board_enter_fin_work"));
				enterBean.setBoard_enter_hiring(rs.getInt("board_enter_hiring"));
				enterBean.setBoard_enter_location(rs.getString("board_enter_location"));
				enterBean.setBoard_enter_program(rs.getString("board_enter_program"));
				enterBean.setBoard_enter_salary(rs.getInt("board_enter_salary"));
				enterBean.setBoard_enter_start_work(rs.getString("board_enter_start_work"));
				enterBean.setBoard_enter_work_days(rs.getString("board_enter_work_days"));
			}
		} catch (Exception e) {
			System.out.println("selectEnterInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return enterBean;
	}
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
	
	public int insertEnter(EnterBean enterBean) { // 채용공고 게시물 생성
		System.out.println("EnterDAO - insertEnter()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO board_enter (board_id, board_enter_company, board_enter_location, "
					+ "board_enter_content_detail, board_enter_program, board_enter_hiring, board_enter_salary, "
					+ "board_enter_start_work, board_enter_fin_work, board_enter_work_days, board_enter_ent_ref) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, enterBean.getBoard_id());
			pstmt.setString(2, enterBean.getBoard_enter_company());
			pstmt.setString(3, enterBean.getBoard_enter_location());
			pstmt.setString(4, enterBean.getBoard_enter_content_detail());
			pstmt.setString(5, enterBean.getBoard_enter_program());
			pstmt.setInt(6, enterBean.getBoard_enter_hiring());
			pstmt.setInt(7, enterBean.getBoard_enter_salary());
			pstmt.setString(8, enterBean.getBoard_enter_start_work());
			pstmt.setString(9, enterBean.getBoard_enter_fin_work());
			pstmt.setString(10, enterBean.getBoard_enter_work_days());
			pstmt.setString(11, enterBean.getBoard_enter_ent_ref());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertEnter() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateEnter(EnterBean enterBean) { // 채용공고 게시물 수정
		System.out.println("EnterDAO - updateEnter()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE board_enter SET board_enter_company = ?, board_enter_location = ?, board_enter_content_detail = ?, "
					+ "board_enter_program = ?, board_enter_hiring = ?, board_enter_salary = ?, board_enter_start_work = ?, "
					+ "board_enter_fin_work = ?, board_enter_work_days = ?, board_enter_ent_ref = ? "
					+ "WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, enterBean.getBoard_enter_company());
			pstmt.setString(2, enterBean.getBoard_enter_location());
			pstmt.setString(3, enterBean.getBoard_enter_content_detail());
			pstmt.setString(4, enterBean.getBoard_enter_program());
			pstmt.setInt(5, enterBean.getBoard_enter_hiring());
			pstmt.setInt(6, enterBean.getBoard_enter_salary());
			pstmt.setString(7, enterBean.getBoard_enter_start_work());
			pstmt.setString(8, enterBean.getBoard_enter_fin_work());
			pstmt.setString(9, enterBean.getBoard_enter_work_days());
			pstmt.setString(10, enterBean.getBoard_enter_ent_ref());
			pstmt.setInt(11, enterBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateEnter() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteEnter(int board_id) { // 채용공고 게시글 삭제
		System.out.println("EnterDAO - deleteEnter()");
		int deleteEnter = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM board_enter WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteEnter = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteEnter() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteEnter;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<EnterBean> selectListEnter(int page){ // 채용공고 게시글 리스트
		System.out.println("EnterDAO - selectListEnter()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		ArrayList<EnterBean> list = null;
		
		try {
			String sql = "SELECT * FROM board b " + 
					"JOIN board_enter e " + 
					"ON b.board_id = e.board_id " +
					"ORDER BY b.board_date DESC " +
					"LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<EnterBean>();
			
			while(rs.next()) {
				EnterBean enterBean = new EnterBean();
				enterBean.setBoard_id(rs.getInt("board_id"));
				enterBean.setBoard_subject(rs.getString("board_subject"));
				enterBean.setBoard_content(rs.getString("board_content"));
				enterBean.setBoard_date(rs.getTimestamp("board_date"));
				enterBean.setBoard_type(rs.getInt("board_type"));
				enterBean.setUser_id(rs.getString("user_id"));
				enterBean.setBoard_enter_company(rs.getString("board_enter_company"));
				enterBean.setBoard_enter_content_detail(rs.getString("board_enter_content_detail"));
				enterBean.setBoard_enter_ent_ref(rs.getString("board_enter_ent_ref"));
				enterBean.setBoard_enter_fin_work(rs.getString("board_enter_fin_work"));
				enterBean.setBoard_enter_hiring(rs.getInt("board_enter_hiring"));
				enterBean.setBoard_enter_location(rs.getString("board_enter_location"));
				enterBean.setBoard_enter_program(rs.getString("board_enter_program"));
				enterBean.setBoard_enter_salary(rs.getInt("board_enter_salary"));
				enterBean.setBoard_enter_start_work(rs.getString("board_enter_start_work"));
				enterBean.setBoard_enter_work_days(rs.getString("board_enter_work_days"));
				list.add(enterBean);
			}
		} catch (Exception e) {
			System.out.println("selectListEnter() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
 		
		return list;
	}
	
	public ArrayList<EnterBean> selectListSearchEnter(int startRow, HashMap<String, String> search){ // 채용공고 검색
		System.out.println("EnterDAO - selectListSearchEnter()");
		ArrayList<EnterBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
}
