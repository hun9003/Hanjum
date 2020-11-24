package com.hanjum.review.service;

import java.sql.Connection;

import com.hanjum.db.JdbcUtil;
import com.hanjum.review.dao.ReviewDAO;




public class ReviewWriteService {

	public boolean rigisterWriter(com.hanjum.review.vo.ReviewBean reviewBean) throws Exception{
		System.out.println("reviewrigisterWriter");
		boolean isWriteSuccess = false;
		Connection con = JdbcUtil.getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int insertCount = reviewDAO.insertArticle(reviewBean);
		
		if(insertCount>0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isWriteSuccess;
	}

	

	

}
