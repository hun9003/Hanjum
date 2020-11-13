package com.hanjum.user.dao;

import java.sql.Connection;

import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.UserBean;

public class UserDAO {
	
	private UserDAO() {}
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int insertUser(UserBean userBean) {
		System.out.println("UserDAO - insertUser() - user");
		int insertCount = 1;
		return insertCount;
	}
	public int insertUser(EditorBean editorBean) {
		System.out.println("UserDAO - insertUser() - editor");
		int insertCount = 1;
		return insertCount;
	}
	public int loginUser(String user_id, String user_pass) {
		System.out.println("UserDAO - loginUser()");
		int insertCount = 1;
		return insertCount;
	}
	public int updateUser(UserBean userBean) {
		System.out.println("UserDAO - updateUser()");
		int insertCount = 1;
		return insertCount;
	}
	public int deleteUser(UserBean userBean) {
		System.out.println("UserDAO - deleteUser()");
		int insertCount = 1;
		return insertCount;
	}
	
}
