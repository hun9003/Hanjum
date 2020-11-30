package com.hanjum.review.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import com.hanjum.review.dao.ReviewDAO;
import com.hanjum.review.vo.ReviewBean;

public class ReviewWriteService {

	public boolean rigisterWriter(ReviewBean reviewBean) throws Exception{
		System.out.println("reviewrigisterWriter");
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(con);
		
		int insertCount = reviewDAO.insertArticle(reviewBean);
		
		if(insertCount>0) {
			commit(con);
			isWriteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isWriteSuccess;
	}

	

	

}
