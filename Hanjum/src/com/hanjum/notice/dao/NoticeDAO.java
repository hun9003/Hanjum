package com.hanjum.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.hanjum.notice.vo.NoticeBean;
import static com.hanjum.db.JdbcUtil.*;

public class NoticeDAO {

	// **Singleton Pattern
	
	// 외부에서 인스턴트 생성 불가능 하도록 private
	private NoticeDAO() {};
	
	// private 으로 설정해서 외부에서 접근 불가능
	// 생성자를 리턴하는 getInstance()에서 멤버변수에 접근 가능하도록 static으로 지정
	private static NoticeDAO instance =  new NoticeDAO();
	
	// 생성된 instance를 외부로 리턴하기위해 Getter -> getInstance() 정의
	// 외부에 인스턴스 생성없이 접근 가능하도록 static으로 정의
	public static NoticeDAO getInstance() {
		return instance;
	}
	
//---------------------------------------------------------------------------
	
	Connection con; // Connection 객체를 전달받아 저장할 멤버변수
	
	// Service 클래스로부터 Connection 객체를 전달받아 멤버변수에 저장하는 메서드
	public void setConnection(Connection con) {
		this.con = con;
	}
	
//---------------------------------------------------------------------------

	
	// ---------------GET---------------
	// 사용자에 해당하는 모든 알람 리스트 다 가져옴
	public ArrayList<NoticeBean> getNoticeList(String user_id) {
		System.out.println("DAO - getNoticeList()");

		ArrayList<NoticeBean> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			sql = "select * from notice where user_id=? order by notice_id desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs= pstmt.executeQuery();
		
			while(rs.next()) {
				NoticeBean notice = new NoticeBean();
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
				notice.setUser_id(user_id);
				notice.setNotice_type(rs.getInt("notice_type"));
				
				list.add(notice);
				
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	
	
	// 사용자에 해당하는 새로운 알람 5개 까지만 가져옴
	public ArrayList<NoticeBean> getNewNotice(String user_id) {
		System.out.println("DAO - getNewNotice()");

		ArrayList<NoticeBean> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			sql = "select * from notice where user_id=? and notice_read=0 order by notice_id desc limit 5"; 
//			sql = "select * from notice order by notice_id desc limit 5"; 

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs= pstmt.executeQuery();
		
			while(rs.next()) {
				list = new ArrayList<NoticeBean>();
				NoticeBean notice = new NoticeBean();
				notice.setBoard_id(rs.getInt("board_id"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_date(rs.getTimestamp("notice_date"));
				notice.setNotice_from_id(rs.getString("notice_from_id"));
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_read(rs.getInt("notice_read"));
				notice.setNotice_url(rs.getString("notice_url"));
//				notice.setUser_id(user_id);
				notice.setNotice_type(rs.getInt("notice_type"));

				
				list.add(notice);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	
	// 사용자에 해당하는 오래된 알람 5개만 출력
		public ArrayList<NoticeBean> getOldNotice(String user_id) {
			System.out.println("DAO - getOldNotice()");

			ArrayList<NoticeBean> list = null; 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			
			try  {
				
				sql = "select * from notice where user_id=? and notice_read=1 order by notice_id desc limit 5";
//				sql = "select * from notice order by notice_id desc limit 5"; 

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					list =  new ArrayList<NoticeBean>();
					NoticeBean notice = new NoticeBean();
					notice.setBoard_id(rs.getInt("board_id"));
					notice.setNotice_content(rs.getString("notice_content"));
					notice.setNotice_date(rs.getTimestamp("notice_date"));
					notice.setNotice_from_id(rs.getString("notice_from_id"));
					notice.setNotice_id(rs.getInt("notice_id"));
					notice.setNotice_read(rs.getInt("notice_read"));
					notice.setNotice_url(rs.getString("notice_url"));
					notice.setUser_id(rs.getString("user_id"));
					notice.setNotice_type(rs.getInt("notice_type"));

					
					list.add(notice);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return list;
		}
	
		
		
	// ---------------INSERT---------------
	// insertNotice 알람정보들 저장 - commit, rollback -> service에서 ! (성공하면 알람 보내기 !)
		public int insertNotice(NoticeBean noticeBean) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int notice_id = 0;
			int noticeSuccess = 0;
			
			try {
				
				// notice_id 번호 부여
				sql = "select max(notice_id) from notice";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					notice_id = rs.getInt("max(notice_id)") + 1; 
				}
				
				// 알람정보들 입력
				sql = "insert into notice(notice_date, notice_id, notice_content, notice_url, notice_read, board_id, user_id, notice_from_id, notice_type) values(now(), ?, ?, ?, 0, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, notice_id);
				pstmt.setString(2, noticeBean.getNotice_content());
				pstmt.setString(3, noticeBean.getNotice_url());
//				pstmt.setInt(4, noticeBean.getNotice_read()); // 처음에 notice_read는 무조건 0 
				pstmt.setInt(4, noticeBean.getBoard_id());
				pstmt.setString(5, noticeBean.getUser_id());
				pstmt.setString(6, noticeBean.getNotice_from_id());
				pstmt.setInt(7, noticeBean.getNotice_type());
				
				noticeSuccess = pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			}
			return noticeSuccess;
		}
		
		
		
	// ---------------UPDATE---------------
	// user가 클릭한 알람 notice_read = 1로 바꿔줌 (바꿔졌으면 1 - commit 하기, 아니면 0 - rollback 하기)
		public int updateStatus(int notice_id) {
			System.out.println("DAO - updateStatus()");
	
			PreparedStatement pstmt = null;
			int noticeRead = 0;
			String sql = null;
			
			try {
				
				sql = "update notice set notice_read=notice_read+1 where notice_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,  notice_id);
				noticeRead = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return noticeRead;
		}
	
	
	

	// ---------------SEND---------------
	// 클릭한 크리에이터에게 알람이 가는 메서드
	// (notice_from_id 가 클릭시 발생)
	// 어떤 종류의 알람인지 감시하고 메세지 보냄
	// notice
		public int sendNotification(NoticeBean noticeBean) {
			System.out.println("DAO - sendNotification()");
			int alertType = 0; // 
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
//			String notice_type = (String)noticeBean.getNotice_content();
			// notice_type 을 리턴값으로 int
			// 
			
			try {
				// notice_type 뭘로 바꿀건지 
					sql = "select notice_type from notice where user_id=? order by notice_id desc limit 1";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, noticeBean.getNotice_id());
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						alertType = rs.getInt("notice_type");
					}
					
					return alertType;
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);						
			}
			return alertType;
			
		}
		
	// 매칭되지않은 나머지 유저들에게 보내는 알람
	
	// notice_content 가 notice_type ! 다른거같은데 같나..?
		
		
}







