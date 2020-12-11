package com.hanjum.user.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static com.hanjum.db.JdbcUtil.*;

import com.hanjum.user.dao.UserDAO;
import com.hanjum.user.exception.LoginException;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.PortfolioBean;
import com.hanjum.user.vo.UserBean;


public class UserProService {
	public boolean insertUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertUser(userBean);
		if(insertCount > 0) {
			commit(con); 
			isSuccess = true; 
		} else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
		
	}
	
	public boolean insertUser(EditorBean editorBean) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertUser(editorBean);
		if(insertCount > 0) {
			commit(con); 
			isSuccess = true; 
		} else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
		
	}
	
	
	public UserBean loginUser(String user_id, String user_pass) throws LoginException {
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		UserBean userBean = userDAO.loginUser(user_id, user_pass);
		System.out.println("디에이오 지나서 서비스" + userBean);
		if(userBean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return userBean;
	}
	
	public boolean updateUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.updateUser(userBean);
		if(insertCount > 0) {
			commit(con); 
			isSuccess = true; 
		} else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
		
	}
	
	public boolean updateUser(EditorBean editorBean) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.updateUser(editorBean);
		if(insertCount > 0) {
			commit(con); 
			isSuccess = true; 
		} else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
		
	}
	
	public boolean deleteUser(UserBean userBean) {
		boolean isSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int deleteCount = userDAO.deleteUser(userBean);
		if(deleteCount > 0) {
			commit(con);
			isSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isSuccess;
	}
	
	public UserBean getUserInfo(String user_id) {
		System.out.println("selectUserInfo- service");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		UserBean userBean = userDAO.getUserInfo(user_id);
		if(userBean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return userBean;
	}

	public EditorBean getEditorInfo(String user_id) {
		System.out.println("selectEditorInfo- service");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		EditorBean editorBean = userDAO.getEditorBean(user_id);
		if(editorBean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return editorBean;
		
		
	}

	public int getListCount() {
		System.out.println("getListCount - user");
		int listCount = 0;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
		
		// 2(공통). BoardDAO 객체 가져오기
		UserDAO userDAO = UserDAO.getInstance();
		
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		userDAO.setConnection(con);
		
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여 
		//    전체 게시물 수 가져오기
		listCount = userDAO.getListCount();
		
		// 5(공통). Connection 객체 반환하기
		close(con);
		
		return listCount;
	}
	public int getListCount(String search, String searchType) {
		System.out.println("getListCount - user");
		int listCount = 0;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
		
		// 2(공통). BoardDAO 객체 가져오기
		UserDAO userDAO = UserDAO.getInstance();
		
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		userDAO.setConnection(con);
		
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여 
		//    전체 게시물 수 가져오기
		listCount = userDAO.getListCount(search,searchType);
		
		// 5(공통). Connection 객체 반환하기
		close(con);
		
		return listCount;
	}

	public ArrayList<UserBean> getUserList(int page, int limit) {
		ArrayList<UserBean> userList = null;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		userList = userDAO.getUserList(page,limit);
		close(con);
		
		return userList;
	}


	public ArrayList<UserBean> getUserList(int page, int limit, String search, String searchType) {
		ArrayList<UserBean> userList = null;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		userList = userDAO.getUserList(page,limit,search,searchType);
		close(con);
		
		return userList;
	}

	public boolean userLike(String user_id, String like_userid) {
		boolean likeSuccess = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int insertCount = userDAO.changeUserLike(user_id,like_userid);
		
		if (insertCount > 0) {
			commit(con);
			likeSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return likeSuccess;
	}

	public int UserCheckId(String user_id) {
		int data = 0;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		data = userDAO.userCheckId(user_id);
		if (data > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return data;
		
	}

	public boolean userEmailCheckCode(String email, String checkCode) {
		boolean insertCheck = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		insertCheck = userDAO.userEmailCheckCode(email,checkCode);
		if(insertCheck) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return insertCheck;
		
		
	}

	public boolean codeCheck(String email, String code) {
		boolean success = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		success = userDAO.emailCodeCheck(email,code);
		if(success) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return success;
	}
		// 내정보 - 비밀번호 변경할때 실행하는 메서드
	public boolean changePass(String user_id, String user_pass, String user_changePass) {
		boolean success = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		success = userDAO.changePass(user_id,user_pass,user_changePass);
		if(success) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return success;
	}
	
		// 비밀번호찾기 - 비밀번호 변경할때 실행하는 메서드
	public boolean changePass(String user_id, String user_changePass) {
		boolean success = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		success = userDAO.changePass(user_id, user_changePass);
		if(success) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return success;
	}

	public int checkEmail(String user_id, String email) {
		int selectCount = 0;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		selectCount = userDAO.checkUserEmail(user_id,email);
		if(selectCount>0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return selectCount;
		
	}

	public boolean insertPortfolio(PortfolioBean portfolioBean) {
		System.out.println("insertPortfolio");
		boolean isInsertSuccess = false;
		
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		int insertCount = userDAO.insertPortfolio(portfolioBean);
		if(insertCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return isInsertSuccess;
	}
	
	public ArrayList<PortfolioBean> getPortfolioList(String user_id) {
		System.out.println("portfilioList");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		ArrayList<PortfolioBean> list = userDAO.getPortfolioList(user_id);
		close(con);
		return list;
	}

	public boolean updatePortfolio(PortfolioBean portfolioBean) {
		System.out.println("updatePortfolio");
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int updateCount = userDAO.updatePortfolio(portfolioBean);
		
		if(updateCount > 0) {
			commit(con);
			isUpdateSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isUpdateSuccess;
	}
	
	public boolean deletePortfolio(int editor_pf_id) {
		System.out.println("deletePortfolio");
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		int deleteCount = userDAO.deletePortfolio(editor_pf_id);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return isDeleteSuccess;
	}
	
	public int getCountPortfolio(String user_id) {
		System.out.println("getCountPortfolio");
		int count = 0;
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		count = userDAO.getCountPortfolio(user_id);
		close(con);
		
		return count;
	}
	
	public PortfolioBean getPortfolioInfo(int editor_pf_id) {
		System.out.println("getPortfolioInfo");
		Connection con = getConnection();
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.setConnection(con);
		
		PortfolioBean portfolioBean = userDAO.getPortfolioInfo(editor_pf_id);
		
		close(con);
		return portfolioBean;
	}
}
