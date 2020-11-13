package com.hanjum.user.svc;

import java.sql.Connection;

import com.hanjum.db.JdbcUtil;
import com.hanjum.user.dao.UserDAO;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;

public class UserProService {
	public boolean insertUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertUser(userBean);
		if(insertCount > 0) {
			JdbcUtil.commit(con); 
			isSuccess = true; 
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isSuccess;
		
	}
	
	public boolean insertUser(EditorBean editorBean) {
		boolean isSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertUser(editorBean);
		if(insertCount > 0) {
			JdbcUtil.commit(con); 
			isSuccess = true; 
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isSuccess;
		
	}
	
	
	public boolean loginUser(String user_id, String user_pass) {
		boolean isSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.loginUser(user_id, user_pass);
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isSuccess;
	}
	
	public boolean updateUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.updateUser(userBean);
		if(insertCount > 0) {
			JdbcUtil.commit(con); 
			isSuccess = true; 
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isSuccess;
		
	}
	
	public boolean deleteUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int deleteCount = userDAO.deleteUser(userBean);
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		return isSuccess;
	}
}
