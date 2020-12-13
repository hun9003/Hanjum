package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.EditorBean;
import com.hanjum.vo.Constant;

import static com.hanjum.db.JdbcUtil.*;

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
	
	public EditorBean selectEditorInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("EditorDAO - selectEditorInfo()");
		EditorBean editorBean = null;
		if(boardBean instanceof EditorBean) {
			editorBean = (EditorBean)boardBean;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM board_ed where board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardBean.getBoard_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				editorBean.setBoard_ed_address(rs.getString("board_ed_address"));
				editorBean.setBoard_ed_category(rs.getString("board_ed_category"));
				editorBean.setBoard_ed_content_detail(rs.getString("board_ed_content_detail"));
				editorBean.setBoard_ed_fort(rs.getInt("board_ed_fort"));
				editorBean.setBoard_ed_inventory(rs.getString("board_ed_inventory"));
				editorBean.setBoard_ed_link(rs.getString("board_ed_link"));
				editorBean.setBoard_ed_max_price(rs.getInt("board_ed_max_price"));
				editorBean.setBoard_ed_meeting(rs.getInt("board_ed_meeting"));
				editorBean.setBoard_ed_min_price(rs.getInt("board_ed_min_price"));
				editorBean.setBoard_ed_program(rs.getString("board_ed_program"));
				editorBean.setBoard_ed_sample(rs.getInt("board_ed_sample"));
				editorBean.setBoard_ed_solution(rs.getString("board_ed_solution"));
				editorBean.setBoard_ed_subject(rs.getString("board_ed_subject"));
				editorBean.setBoard_ed_upload(rs.getInt("board_ed_upload"));
				editorBean.setBoard_ed_work(rs.getInt("board_ed_work"));
			}
		} catch (Exception e) {
			System.out.println("selectProjectInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return editorBean;
	}
	
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
		
	public int insertEditor(EditorBean editorBean) { // 에디터 게시물 생성
		System.out.println("EditorDAO - insertEditor()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO board_ed VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, editorBean.getBoard_id());
			pstmt.setString(2, editorBean.getBoard_ed_program());
			pstmt.setString(3, editorBean.getBoard_ed_solution());
			pstmt.setString(4, editorBean.getBoard_ed_inventory());
			pstmt.setInt(5, editorBean.getBoard_ed_upload());
			pstmt.setInt(6, editorBean.getBoard_ed_work());
			pstmt.setInt(7, editorBean.getBoard_ed_sample());
			pstmt.setInt(8, editorBean.getBoard_ed_fort());
			pstmt.setInt(9, editorBean.getBoard_ed_min_price());
			pstmt.setInt(10, editorBean.getBoard_ed_max_price());
			pstmt.setInt(11, editorBean.getBoard_ed_meeting());
			pstmt.setString(12, editorBean.getBoard_ed_content_detail());
			pstmt.setString(13, editorBean.getBoard_ed_address());
			pstmt.setString(14, editorBean.getBoard_ed_category());
			pstmt.setString(15, editorBean.getBoard_ed_subject());
			pstmt.setString(16, editorBean.getBoard_ed_link());
			insertCount = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("insertEditor() 오류! - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateEditor(EditorBean editorBean) { // 에디터 게시물 수정
		System.out.println("EditorDAO - updateEditor()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE board_ed SET board_ed_program = ?, board_ed_solution = ?, board_ed_inventory = ?, "
					+ "board_ed_upload = ?, board_ed_work = ?, baord_ed_sample = ?, board_ed_fort = ?, board_ed_min_price = ?, "
					+ "baord_ed_max_price = ?, board_ed_meeting = ?, board_ed_content_detail = ?, board_ed_address = ?, "
					+ "board_ed_category = ?, board_ed_subject = ?, board_ed_link = ? WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editorBean.getBoard_ed_program());
			pstmt.setString(2, editorBean.getBoard_ed_solution());
			pstmt.setString(3, editorBean.getBoard_ed_inventory());
			pstmt.setInt(4, editorBean.getBoard_ed_upload());
			pstmt.setInt(5, editorBean.getBoard_ed_work());
			pstmt.setInt(6, editorBean.getBoard_ed_sample());
			pstmt.setInt(7, editorBean.getBoard_ed_fort());
			pstmt.setInt(8, editorBean.getBoard_ed_min_price());
			pstmt.setInt(9, editorBean.getBoard_ed_max_price());
			pstmt.setInt(10, editorBean.getBoard_ed_meeting());
			pstmt.setString(11, editorBean.getBoard_ed_content_detail());
			pstmt.setString(12, editorBean.getBoard_ed_address());
			pstmt.setString(13, editorBean.getBoard_ed_category());
			pstmt.setString(14, editorBean.getBoard_ed_subject());
			pstmt.setString(15, editorBean.getBoard_ed_link());
			pstmt.setInt(16, editorBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteEditor(String user_id) { // 에디터 게시글 삭제
		System.out.println("EditorDAO - deleteEditor()");
		int deleteEditor = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM board_ed WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			deleteEditor = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteEditor;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<EditorBean> selectListEditor(int page){ // 에디터 게시글 리스트
		System.out.println("EditorDAO - selectListEditor()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		ArrayList<EditorBean> list = null;
		
		try {
			String sql = "SELECT * FROM board b "
					+ "JOIN board_ed e "
					+ "ON b.board_id = e.board_id "
					+ "JOIN editor ed "
					+ "ON b.user_id = ed.user_id "
					+ "WHERE ed.editor_status = 1 "
					+ "ORDER BY b.board_date DESC "
					+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<EditorBean>();
			
			while(rs.next()) {
				EditorBean editorBean = new EditorBean();
				editorBean.setBoard_id(rs.getInt("board_id"));
				editorBean.setBoard_subject(rs.getString("board_subject"));
				editorBean.setBoard_content(rs.getString("board_content"));
				editorBean.setBoard_date(rs.getTimestamp("board_date"));
				editorBean.setBoard_type(rs.getInt("board_type"));
				editorBean.setUser_id(rs.getString("user_id"));
				editorBean.setBoard_ed_address(rs.getString("board_ed_address"));
				editorBean.setBoard_ed_category(rs.getString("board_ed_category"));
				editorBean.setBoard_ed_content_detail(rs.getString("board_ed_content_detail"));
				editorBean.setBoard_ed_fort(rs.getInt("board_ed_fort"));
				editorBean.setBoard_ed_inventory(rs.getString("board_ed_inventory"));
				editorBean.setBoard_ed_link(rs.getString("board_ed_link"));
				editorBean.setBoard_ed_max_price(rs.getInt("board_ed_max_price"));
				editorBean.setBoard_ed_meeting(rs.getInt("board_ed_meeting"));
				editorBean.setBoard_ed_min_price(rs.getInt("board_ed_min_price"));
				editorBean.setBoard_ed_program(rs.getString("board_ed_program"));
				editorBean.setBoard_ed_sample(rs.getInt("board_ed_sample"));
				editorBean.setBoard_ed_solution(rs.getString("board_ed_solution"));
				editorBean.setBoard_ed_subject(rs.getString("board_ed_subject"));
				editorBean.setBoard_ed_upload(rs.getInt("board_ed_upload"));
				editorBean.setBoard_ed_work(rs.getInt("board_ed_work"));
				
				list.add(editorBean);
			}
			
		} catch (Exception e) {
			System.out.println("selectListEditor() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	public ArrayList<EditorBean> selectListSearchEditor(int startRow, HashMap<String, String> search){ // 에디터 검색
		System.out.println("EditorDAO - selectListSearchEditor()");
		ArrayList<EditorBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
	
}
