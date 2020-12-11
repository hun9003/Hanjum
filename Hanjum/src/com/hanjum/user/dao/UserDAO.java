package com.hanjum.user.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.hanjum.db.JdbcUtil.*;

import com.hanjum.user.exception.LoginException;
import com.hanjum.user.vo.EditorBean;
import com.hanjum.user.vo.PortfolioBean;
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
	
	// insert
	public int insertUser(UserBean userBean) {
		System.out.println("UserDAO - insertUser() - user");
		int insertCount = 0;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		System.out.println(userBean.getUser_id());
		
		try {
			String sql = "insert into user(user_id,user_pass,user_name,user_email,user_phone,user_level,user_lv_exp,user_score,user_project_count,user_type,user_login_count) values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userBean.getUser_id());
			pstmt.setString(2, userBean.getUser_pass());
			pstmt.setString(3, userBean.getUser_name());
			pstmt.setString(4, userBean.getUser_email());
			pstmt.setString(5, userBean.getUser_phone());
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, userBean.getUser_type());
			pstmt.setInt(11, 0);
			insertCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertUser() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
//			close(rs);
			close(pstmt);
		}
		
		
		
		return insertCount;
	}
	public int insertUser(EditorBean editorBean) {
		System.out.println("UserDAO - insertUser() - editor");
		int insertCount = 0;
		int userInsertCount = 0;
		PreparedStatement pstmt = null;
		UserBean userbean = (UserBean)editorBean;
		System.out.println(editorBean.getEditor_program());
		userInsertCount=insertUser(userbean);
		
		if(userInsertCount > 0) {
			try {
				String sql = "insert into editor(user_id,editor_photo,editor_des,editor_profile,editor_program,editor_solution,editor_inventory,editor_upload,editor_work,editor_meeting,editor_fort,editor_sample,editor_ed_min_price,editor_ed_max_price,editor_address,editor_like) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, editorBean.getUser_id());
				pstmt.setString(2, editorBean.getEditor_photo());
				pstmt.setString(3, editorBean.getEditor_des());
				pstmt.setString(4, editorBean.getEditor_profile());
				pstmt.setString(5, editorBean.getEditor_program());
				pstmt.setString(6, editorBean.getEditor_solution());
				pstmt.setString(7, editorBean.getEditor_inventory());
				pstmt.setInt(8, editorBean.getEditor_upload());
				pstmt.setInt(9, editorBean.getEditor_work());
				pstmt.setInt(10, editorBean.getEditor_meeting());
				pstmt.setInt(11, editorBean.getEditor_fort());
				pstmt.setInt(12, editorBean.getEditor_sample());
				pstmt.setInt(13, editorBean.getEditor_ed_min_price());
				pstmt.setInt(14, editorBean.getEditor_ed_max_price());
				pstmt.setString(15, editorBean.getEditor_address());
				pstmt.setInt(16, 0);
				insertCount=pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("insertEditorUser() 오류! - " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		} else {
			rollback(con);
		}
		
		return insertCount;
	}
	
	// login
	public UserBean loginUser(String user_id, String user_pass) throws LoginException {
		System.out.println("UserDAO - loginUser()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserBean userBean = null;
		
		try {
			String sql = "select * from user where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			System.out.println(user_id);
			System.out.println(user_pass);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(user_pass.equals(rs.getString("user_pass"))) {
					userBean = new UserBean();
					String sql2 = "update user set user_login_count=user_login_count+1 where user_id=?";
					pstmt=con.prepareStatement(sql2);
					pstmt.setString(1, user_id);
					insertCount=pstmt.executeUpdate();
					if(insertCount>0) {
						userBean.setUser_type(Integer.parseInt(rs.getString("user_type")));
						userBean.setUser_id(rs.getString("user_id"));
						userExp(user_id, 10);
					}
				} else {
					throw new LoginException("패스워드 틀림!");
				} 
			} else {
				throw new LoginException("아이디 없음!");
			}
			System.out.println(insertCount);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(userBean + "DAO안");
		return userBean;
	}
	public void userExp(String user_id, int exp) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int level = rs.getInt("user_level");
				int lv_exp = rs.getInt("user_lv_exp")+exp;
				int levelupExp = level * 20;
				if(lv_exp >=  levelupExp) {
					level ++;
					lv_exp -= levelupExp;
					levelupExp = level * 20;
				}
				System.out.println(level);
				String sql2 = "update user set user_level=?,user_lv_exp=? where user_id=?";
				pstmt=con.prepareStatement(sql2);
				pstmt.setInt(1, level);
				pstmt.setInt(2, lv_exp);
				pstmt.setString(3, user_id);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	}
	
	public int updateUser(UserBean userBean) {
		System.out.println("UserDAO - updateUser()");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set user_phone=? where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userBean.getUser_phone());
			pstmt.setString(2, userBean.getUser_id());
			insertCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateUser() 오류! - user" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return insertCount;
	}
	public int updateUser(EditorBean editorBean) {
		System.out.println("UserDAO - insertUser() - editor");
		int updateCount = 0;
		int userUpdateCount = 0;
		PreparedStatement pstmt = null;
		UserBean userbean = (UserBean)editorBean;
		System.out.println(editorBean.getEditor_program());
		userUpdateCount=updateUser(userbean);
		System.out.println("user 성공!" + userbean.getUser_name());
		if(userUpdateCount > 0) {
			try {
				String sql = "update editor set editor_photo=?,editor_des=?,editor_profile=?,editor_program=?,editor_solution=?,editor_inventory=?,editor_upload=?,editor_work=?,editor_meeting=?,editor_fort=?,editor_sample=?,editor_ed_min_price=?,editor_ed_max_price=?,editor_address=? where user_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, editorBean.getEditor_photo());
				pstmt.setString(2, editorBean.getEditor_des());
				pstmt.setString(3, editorBean.getEditor_profile());
				pstmt.setString(4, editorBean.getEditor_program());
				pstmt.setString(5, editorBean.getEditor_solution());
				pstmt.setString(6, editorBean.getEditor_inventory());
				pstmt.setInt(7, editorBean.getEditor_upload());
				pstmt.setInt(8, editorBean.getEditor_work());
				pstmt.setInt(9, editorBean.getEditor_meeting());
				pstmt.setInt(10, editorBean.getEditor_fort());
				pstmt.setInt(11, editorBean.getEditor_sample());
				pstmt.setInt(12, editorBean.getEditor_ed_min_price());
				pstmt.setInt(13, editorBean.getEditor_ed_max_price());
				pstmt.setString(14, editorBean.getEditor_address());
				pstmt.setString(15, editorBean.getUser_id());
				updateCount=pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("updateUser() 오류! - editor " + e.getMessage());
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		} else {
			rollback(con);
		}
		
		return updateCount;
	}
	
	public int deleteUser(UserBean userBean) {
		System.out.println("UserDAO - deleteUser()");
		int insertCount = 1;
		return insertCount;
	}

	public UserBean getUserInfo(String user_id) {
		System.out.println("selectUserInfo - dao");
		UserBean userBean = new UserBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where user_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				userBean.setUser_id(rs.getString("user_id"));
				userBean.setUser_phone(rs.getString("user_phone"));
				userBean.setUser_level(Integer.parseInt(rs.getString("user_level")));
				userBean.setUser_lv_exp(Integer.parseInt(rs.getString("user_lv_exp")));
				userBean.setUser_email(rs.getString("user_email"));
				userBean.setUser_name(rs.getString("user_name"));
				userBean.setUser_project_count(Integer.parseInt(rs.getString("user_project_count")));
				userBean.setUser_score(Integer.parseInt(rs.getString("user_score")));
				userBean.setUser_type(Integer.parseInt(rs.getString("user_type")));
				userBean.setUser_login_count(Integer.parseInt(rs.getString("user_login_count")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return userBean;
	}

	public EditorBean getEditorBean(String user_id) {
		System.out.println("selectUserInfo - dao");
		EditorBean editorBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from editor where user_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				editorBean = new EditorBean();
				editorBean.setUser_id(user_id);
				editorBean.setEditor_photo(rs.getString("editor_photo"));
				editorBean.setEditor_des(rs.getString("editor_des"));
				editorBean.setEditor_profile(rs.getString("editor_profile"));
				editorBean.setEditor_program(rs.getString("editor_program"));
				editorBean.setEditor_solution(rs.getString("editor_solution"));
				editorBean.setEditor_inventory(rs.getString("editor_inventory"));
				editorBean.setEditor_upload(rs.getInt("editor_upload"));
				editorBean.setEditor_work(rs.getInt("editor_work"));
				editorBean.setEditor_meeting(rs.getInt("editor_meeting"));
				editorBean.setEditor_fort(rs.getInt("editor_fort"));
				editorBean.setEditor_sample(rs.getInt("editor_sample"));
				editorBean.setEditor_ed_min_price(rs.getInt("editor_ed_min_price"));
				editorBean.setEditor_ed_max_price(rs.getInt("editor_ed_max_price"));
				editorBean.setEditor_address(rs.getString("editor_address"));
				editorBean.setEditor_like(rs.getInt("editor_like"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return editorBean;
	}

	public int getListCount() {
	int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// SELECT 구문을 사용하여 전체 게시물 수 조회
			// => count()함수 사용, 조회 대상 컬럼 1개 지정하거나 * 사용
			String sql = "select Count(user_id) from user";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 (= 게시물이 하나라도 존재하는 경우)
			// => 게시물 수를 listCount에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// 자원 반환
			// 주의! DAO 클래스 내에서 Connection 객체 반환 금지!
			close(rs);
			close(pstmt);
		}
		
		
		
		return listCount;
	}
	public int getListCount(String search, String searchType) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// SELECT 구문을 사용하여 전체 게시물 수 조회
			// => count()함수 사용, 조회 대상 컬럼 1개 지정하거나 * 사용
			String sql = "select Count(user_id) from user where "+searchType + " like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs=pstmt.executeQuery();
			
			// 조회 결과가 있을 경우 (= 게시물이 하나라도 존재하는 경우)
			// => 게시물 수를 listCount에 저장
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getListCount() 오류! - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// 자원 반환
			// 주의! DAO 클래스 내에서 Connection 객체 반환 금지!
			close(rs);
			close(pstmt);
		}
		
		
		
		return listCount;
	}

	public ArrayList<UserBean> getUserList(int page, int limit) {
		ArrayList<UserBean> userList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1) * limit;
		
		try {
			String sql = "select * from user limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			
			userList = new ArrayList<UserBean>();
			while(rs.next()) {
				UserBean userBean = new UserBean();
				userBean.setUser_id(rs.getString("user_id"));
				userBean.setUser_pass(rs.getString("user_pass"));
				userBean.setUser_name(rs.getString("user_name"));
				userBean.setUser_email(rs.getString("user_email"));
				userBean.setUser_phone(rs.getString("user_phone"));
				userBean.setUser_level(Integer.parseInt(rs.getString("user_level")));
				userBean.setUser_lv_exp(Integer.parseInt(rs.getString("user_lv_exp")));
				userBean.setUser_score(Integer.parseInt(rs.getString("user_score")));
				userBean.setUser_project_count(Integer.parseInt(rs.getString("user_project_count")));
				userBean.setUser_type(Integer.parseInt(rs.getString("user_type")));
				userBean.setUser_login_count(Integer.parseInt(rs.getString("user_login_count")));
				
				userList.add(userBean);
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return userList;
	}

	public ArrayList<UserBean> getUserList(int page, int limit, String search, String searchType) {
		ArrayList<UserBean> userList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("getUserList_검색");
		int startRow = (page-1) * limit;
		
		try {
			String sql = "select * from user where " +searchType + " like ? limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs=pstmt.executeQuery();
			
			userList = new ArrayList<UserBean>();
			while(rs.next()) {
				UserBean userBean = new UserBean();
				userBean.setUser_id(rs.getString("user_id"));
				userBean.setUser_pass(rs.getString("user_pass"));
				userBean.setUser_name(rs.getString("user_name"));
				userBean.setUser_email(rs.getString("user_email"));
				userBean.setUser_phone(rs.getString("user_phone"));
				userBean.setUser_level(Integer.parseInt(rs.getString("user_level")));
				userBean.setUser_lv_exp(Integer.parseInt(rs.getString("user_lv_exp")));
				userBean.setUser_score(Integer.parseInt(rs.getString("user_score")));
				userBean.setUser_project_count(Integer.parseInt(rs.getString("user_project_count")));
				userBean.setUser_type(Integer.parseInt(rs.getString("user_type")));
				userBean.setUser_login_count(Integer.parseInt(rs.getString("user_login_count")));
				
				userList.add(userBean);
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return userList;
	}

	public int changeUserLike(String user_id, String like_userid) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select like_id from user_likeuser where user_id=? and like_userid=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			pstmt.setString(2, like_userid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				sql = "delete from user_likeuser where like_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt(1));
				insertCount=pstmt.executeUpdate();
			} else {
				sql = "insert into user_likeuser (user_id,like_userid) values(?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, like_userid);
				insertCount=pstmt.executeUpdate();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	
	public int userCheckId(String user_id) {
		int data = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select user_id from user where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return data;
	}
	
	// 이메일 코드 생성하는건데 이미 해당 이메일에 대해 코드가 있으면 제거하고 다시 보냄(재전송 시)
	public boolean userEmailCheckCode(String email, String checkCode) {
		boolean insertCheck = false;
		int insertCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from email_code where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			String sql2 = "insert into email_code(email,email_code_content) value(?,?)";
			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1, email);
			pstmt.setString(2, checkCode);
			
			insertCount = pstmt.executeUpdate();
			
			if(insertCount > 0) {
				insertCheck = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCheck;
	}
	
	// 이메일 코드 체크 성공하면 디비에서 제거함
	public boolean emailCodeCheck(String email, String code) {
		boolean success = false;
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from email_code where email=? and email_code_content=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setNString(2, code);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String sql2 = "delete from email_code where email=?";
				pstmt=con.prepareStatement(sql2);
				pstmt.setString(1, email);
				deleteCount = pstmt.executeUpdate();
			}
			
			
			if(deleteCount > 0) {
				success = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return success;
	}
	// 내정보 - 비밀번호 변경
	public boolean changePass(String user_id, String user_pass, String user_changePass) {
		boolean success = false;
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "update user set user_pass=? where user_id=? and user_pass =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_changePass);
			pstmt.setString(2, user_id);
			pstmt.setString(3, user_pass);
			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0 ) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return success;
	}
	// 비밀번호 찾기 - 비밀번호 변경
	public boolean changePass(String user_id, String user_changePass) {
		boolean success = false;
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { // Email은 이미 메일을 전송할때 제어하여서 확인을 했기 때문에 굳이 파라미터 값을 받지않았습니다.String 파라미터 3개는 내정보에서 이미사용함ㅠ
			String sql = "update user set user_pass=? where user_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_changePass);
			pstmt.setString(2, user_id);
			updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0 ) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return success;
	}
	
	
	// 비밀번호찾기 user_id + user_email 매칭
	public int checkUserEmail(String user_id, String email) {
		int selectCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from user where user_id=? and user_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				selectCount ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return selectCount;
	}

	public int insertPortfolio(PortfolioBean portfolioBean) { // 포트폴리오 삽입
		System.out.println("userDAO - insertPortfolio()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		int pfId = 1;
		try {
			String sql = "SELECT MAX(editor_pf_id) FROM editor_portfolio";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pfId = rs.getInt(1)+1;
			}
			
			sql = "INSERT INTO editor_portfolio VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, portfolioBean.getUser_id());
			pstmt.setInt(2, pfId);
			pstmt.setString(3, portfolioBean.getEditor_pf_category());
			pstmt.setString(4, portfolioBean.getEditor_pf_subject());
			pstmt.setString(5, portfolioBean.getEditor_pf_link());
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertPortfolio 오류 : "+e.getMessage());
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return insertCount;
	}

	public ArrayList<PortfolioBean> getPortfolioList(String user_id){ // 포트폴리오 리스트
		System.out.println("UserDAO - portfolioList()");
		ArrayList<PortfolioBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM editor_portfolio WHERE user_id = ? ORDER BY editor_pf_id DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			list = new ArrayList<PortfolioBean>();
			while (rs.next()) {
				PortfolioBean portfolioBean = new PortfolioBean();
				portfolioBean.setUser_id(rs.getString("user_id"));
				portfolioBean.setEditor_pf_category(rs.getString("editor_pf_category"));
				portfolioBean.setEditor_pf_id(rs.getInt("editor_pf_id"));
				portfolioBean.setEditor_pf_link(rs.getString("editor_pf_link"));
				portfolioBean.setEditor_pf_subject(rs.getString("editor_pf_subject"));
				list.add(portfolioBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int updatePortfolio(PortfolioBean portfolioBean) { // 포트폴리오 수정
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE editor_portfolio SET editor_pf_category = ?, editor_pf_subject = ?, editor_pf_link = ? WHERE editor_pf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, portfolioBean.getEditor_pf_category());
			pstmt.setString(2, portfolioBean.getEditor_pf_subject());
			pstmt.setString(3, portfolioBean.getEditor_pf_link());
			pstmt.setInt(4, portfolioBean.getEditor_pf_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deletePortfolio(int editor_pf_id) { // 포트폴리오 삭제
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM editor_portfolio WHERE editor_pf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, editor_pf_id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public int getCountPortfolio(String user_id) { // 포트폴리오 갯수
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(editor_pf_id) FROM editor_portfolio WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return count;
	}
	
	public PortfolioBean getPortfolioInfo(int editor_pf_id) {
		PortfolioBean portfolioBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM editor_portfolio WHERE editor_pf_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, editor_pf_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				portfolioBean = new PortfolioBean();
				portfolioBean.setEditor_pf_id(editor_pf_id);
				portfolioBean.setEditor_pf_category(rs.getString("editor_pf_category"));
				portfolioBean.setEditor_pf_link(rs.getString("editor_pf_link"));
				portfolioBean.setEditor_pf_subject(rs.getString("editor_pf_subject"));
				portfolioBean.setUser_id(rs.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return portfolioBean;
	}
	
}
