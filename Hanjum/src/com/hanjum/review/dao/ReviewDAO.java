package com.hanjum.review.dao;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hanjum.review.vo.ReviewBean;
import com.sun.xml.internal.ws.Closeable;





public class ReviewDAO {
private ReviewDAO() {}
	
	private static ReviewDAO instance = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return instance;
	}
	

	Connection con; 
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertArticle(ReviewBean reviewBean) {
		System.out.println("reviewDAO");
		int insertCount=0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into review(user_id,review_content, review_speciality, review_satisfaction, review_positivity, review_communication, review_form_id, review_date) values(?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, reviewBean.getUser_id());
			pstmt.setString(2, reviewBean.getReview_content());
			pstmt.setInt(3, reviewBean.getReview_speciality());
			pstmt.setInt(4, reviewBean.getReview_satisfaction());
			pstmt.setInt(5, reviewBean.getReview_positivity());
			pstmt.setInt(6, reviewBean.getReview_communication());
//			pstmt.setTimestamp(7, reviewBean.getReview_date());
			pstmt.setString(7, reviewBean.getReview_form_id());
			
			insertCount = pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			System.out.println("insertArticle() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			
			
		}
		
		return insertCount;
	}

}
