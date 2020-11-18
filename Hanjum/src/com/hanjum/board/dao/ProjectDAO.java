package com.hanjum.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.hanjum.board.vo.BoardBean;
import com.hanjum.board.vo.ProjectBean;
import static com.hanjum.db.JdbcUtil.*;
import com.hanjum.vo.Constant;

public class ProjectDAO {
	private ProjectDAO() {}
	
	private static ProjectDAO instance = new ProjectDAO();

	public static ProjectDAO getInstance() {
		return instance;
	}
	
	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	// GET ======================================================================================
	
	public ProjectBean selectProjectInfo(BoardBean boardBean) { // 에디터 게시물 조회
		System.out.println("ProjectDAO - selectProjectInfo()");
		ProjectBean projectBean = null;
		if(boardBean instanceof ProjectBean) {
			projectBean = (ProjectBean)boardBean;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from board_creator WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectBean.getBoard_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				projectBean.setBoard_creator_cam_num(rs.getInt("board_creator_cam_num"));
				projectBean.setBoard_creator_content_detail(rs.getString("board_creator_content_detail"));
				projectBean.setBoard_creator_cre_max_price(rs.getInt("board_creator_cre_max_price"));
				projectBean.setBoard_creator_cre_min_price(rs.getInt("board_creator_cre_min_price"));
				projectBean.setBoard_creator_cre_ref(rs.getString("board_creator_cre_ref"));
				projectBean.setBoard_creator_edit_length(rs.getInt("board_creator_edit_length"));
				projectBean.setBoard_creator_genre(rs.getString("board_creator_genre"));
				projectBean.setBoard_creator_ori_clip_num(rs.getInt("board_creator_ori_clip_num"));
				projectBean.setBoard_creator_ori_length(rs.getInt("board_creator_ori_length"));
				projectBean.setBoard_creator_ori_transfer(rs.getInt("board_creator_ori_transfer"));
				projectBean.setBoard_creator_recording(rs.getInt("board_creator_recording"));
				projectBean.setBoard_creator_status(rs.getInt("board_creator_status"));
			}
		} catch (Exception e) {
			System.out.println("selectProjectInfo() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return projectBean;
	}
	
	// CHECK ====================================================================================
		
		
		
	// INSERT ===================================================================================
	
	public int insertProject(ProjectBean projectBean) { // 에디터 게시물 생성
		System.out.println("ProjectDAO - insertProject()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO board_creator (board_id, board_creator_content_detail, board_creator_genre, "
					+ "board_creator_recording, board_creator_cam_num, board_creator_ori_clip_num, board_creator_ori_length, "
					+ "board_creator_edit_length, board_creator_ori_transfer, board_creator_cre_ref, "
					+ "board_creator_cre_min_price, board_creator_cre_max_price, board_creator_status)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectBean.getBoard_id());
			pstmt.setString(2, projectBean.getBoard_creator_content_detail());
			pstmt.setString(3, projectBean.getBoard_creator_genre());
			pstmt.setInt(4, projectBean.getBoard_creator_recording());
			pstmt.setInt(5, projectBean.getBoard_creator_cam_num());
			pstmt.setInt(6, projectBean.getBoard_creator_ori_clip_num());
			pstmt.setInt(7, projectBean.getBoard_creator_ori_length());
			pstmt.setInt(8, projectBean.getBoard_creator_edit_length());
			pstmt.setInt(9, projectBean.getBoard_creator_ori_transfer());
			pstmt.setString(10, projectBean.getBoard_creator_cre_ref());
			pstmt.setInt(11, projectBean.getBoard_creator_cre_min_price());
			pstmt.setInt(12, projectBean.getBoard_creator_cre_max_price());
			pstmt.setInt(13, projectBean.getBoard_creator_status());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertProject() 오류! - " + e.getMessage());

		} finally {
			close(pstmt);
		}
		return insertCount;
	}
		
	// UPDATE ===================================================================================
		
	public int updateProject(ProjectBean projectBean) { // 에디터 게시물 수정
		System.out.println("ProjectDAO - updateProject()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE board_creator SET board_creator_content_detail = ?, board_creator_genre = ?, board_creator_recording = ?, "
					+ "board_creator_cam_num = ?,board_creator_ori_clip_num = ?, board_creator_ori_length = ?, board_creator_edit_length = ?, "
					+ "board_creator_ori_transfer = ?, board_creator_cre_ref = ?, board_creator_cre_min_price = ?, board_creator_cre_max_price = ?, board_creator_status = ? "
					+ "WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, projectBean.getBoard_creator_content_detail());
			pstmt.setString(2, projectBean.getBoard_creator_genre());
			pstmt.setInt(3, projectBean.getBoard_creator_recording());
			pstmt.setInt(4, projectBean.getBoard_creator_cam_num());
			pstmt.setInt(5, projectBean.getBoard_creator_ori_clip_num());
			pstmt.setInt(6, projectBean.getBoard_creator_ori_length());
			pstmt.setInt(7, projectBean.getBoard_creator_edit_length());
			pstmt.setInt(8, projectBean.getBoard_creator_ori_transfer());
			pstmt.setString(9, projectBean.getBoard_creator_cre_ref());
			pstmt.setInt(10, projectBean.getBoard_creator_cre_min_price());
			pstmt.setInt(11, projectBean.getBoard_creator_cre_max_price());
			pstmt.setInt(12, projectBean.getBoard_creator_status());
			pstmt.setInt(13, projectBean.getBoard_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
		
	// DELETE ===================================================================================
		
	public int deleteProject(int board_id) { // 에디터 게시글 삭제
		System.out.println("ProjectDAO - deleteProject()");
		int deleteProject = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM board_creator WHERE board_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			deleteProject = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteProject;
	}
		
	// LIST =====================================================================================
		
	public ArrayList<ProjectBean> selectListProject(int page){ // 에디터 게시글 리스트
		System.out.println("ProjectDAO - selectListProject()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int limit = Constant.BOARD_PAGE_SIZE;
		int startRow = (page - 1) * limit;
		ArrayList<ProjectBean> list = null;
		
		try {
			String sql = "SELECT * FROM board b " + 
					"JOIN board_creator c " + 
					"ON b.board_id = c.board_id " +
					"ORDER BY b.board_date DESC " +
					"LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			list = new ArrayList<ProjectBean>();
			
			while(rs.next()) {
				ProjectBean projectBean = new ProjectBean();
				projectBean.setBoard_id(rs.getInt("board_id"));
				projectBean.setBoard_subject(rs.getString("board_subject"));
				projectBean.setBoard_content(rs.getString("board_content"));
				projectBean.setBoard_date(rs.getTimestamp("board_date"));
				projectBean.setBoard_type(rs.getInt("board_type"));
				projectBean.setUser_id(rs.getString("user_id"));
				projectBean.setBoard_creator_cam_num(rs.getInt("board_creator_cam_num"));
				projectBean.setBoard_creator_content_detail(rs.getString("board_creator_content_detail"));
				projectBean.setBoard_creator_cre_max_price(rs.getInt("board_creator_cre_max_price"));
				projectBean.setBoard_creator_cre_min_price(rs.getInt("board_creator_cre_min_price"));
				projectBean.setBoard_creator_cre_ref(rs.getString("board_creator_cre_ref"));
				projectBean.setBoard_creator_edit_length(rs.getInt("board_creator_edit_length"));
				projectBean.setBoard_creator_genre(rs.getString("board_creator_genre"));
				projectBean.setBoard_creator_ori_clip_num(rs.getInt("board_creator_ori_clip_num"));
				projectBean.setBoard_creator_ori_length(rs.getInt("board_creator_ori_length"));
				projectBean.setBoard_creator_ori_transfer(rs.getInt("board_creator_ori_transfer"));
				projectBean.setBoard_creator_recording(rs.getInt("board_creator_recording"));
				projectBean.setBoard_creator_status(rs.getInt("board_creator_status"));
				
				list.add(projectBean);
			}
		} catch (Exception e) {
			System.out.println("selectListProject() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
 		
		return list;
	}
	
	public ArrayList<ProjectBean> selectListSearchProject(int startRow, HashMap<Integer, ArrayList<Object>> search){ // 에디터 검색
		System.out.println("ProjectDAO - selectListSearchProject()");
		ArrayList<ProjectBean> list = null;
		
		return list;
	}
		
		
	// OTHER ====================================================================================
	
}
