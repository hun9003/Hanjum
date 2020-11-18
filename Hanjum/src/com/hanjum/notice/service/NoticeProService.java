package com.hanjum.notice.service;

import java.sql.Connection;
import java.util.ArrayList;
import com.hanjum.board.dao.BoardDAO;
import com.hanjum.notice.dao.NoticeDAO;
import com.hanjum.notice.vo.NoticeBean;
import static com.hanjum.db.JdbcUtil.*;

public class NoticeProService {
	// Action 클래스로부터 요청받은 작업에 대한 데이터등을 전달받아
	// Model(DAO)를 통해 실제 작업처리를 요청하고
	// 처리결과를 리턴받아 해당 결과에 대한 판별을 통해
	// 결과값으로 처리할 데이터를 리턴
	private NoticeDAO noticeDAO;
	private Connection con;
	public NoticeProService() {
		BoardDAO.getInstance();
		con = getConnection();
		noticeDAO.setConnection(con);
	}
	
	
//	----------------DAO호출-------------------
 
	// 모든 알람 리스트 가져오기
	public ArrayList<NoticeBean> getNoticeList(String user_id){
		System.out.println("svc - getNoticeList()");
		ArrayList<NoticeBean> list = noticeDAO.getNoticeList(user_id);
		close(con);
		return list;		
	}
	
	
	// 새로운 알람 5개 가져오기
	public ArrayList<NoticeBean> getNewNotice(String user_id) {
		System.out.println("svc - getNewNotice()");
		ArrayList<NoticeBean> list = noticeDAO.getNewNotice(user_id);
		close(con);
		return list;
	}
	
	
	// 지난(읽은) 알람 5개 가져오기
	public ArrayList<NoticeBean> getOldNotice(String user_id){
		System.out.println("svc - getOldNotice()");
		ArrayList<NoticeBean> list = noticeDAO.getOldNotice(user_id);
		close(con);
		return list;
	}
	
	
	// 신청된 정보들을 담아서 insertNotice에 입력하러감 룰루
	public void insertNotice(NoticeBean noticeBean) {
		System.out.println("svc - insertNotice()");
		int noticeSuccess = noticeDAO.insertNotice(noticeBean);
		
		if(noticeSuccess > 0) {
			commit(con);
			// 여기서 알람보내는 method 호출???****
//			noticeDAO.sendNotification(user_id, notice_from_id)
		}else {
			rollback(con);
		}
		close(con);
	}
	
	
	// 클릭한(읽은) 알람상태를 바꿔주는 메서드 호출
	public void updateStatus(int notice_id) {
		System.out.println("svc - updateStatus()");
		int noticeRead = noticeDAO.updateStatus(notice_id);
		
		if(noticeRead > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
	}
	
	
	// 
	public int sendNotification(String user_id, String notice_from_id) {
		int alert = 0;
		
		return alert;
	}
	
	
	
	
	
	
}


















