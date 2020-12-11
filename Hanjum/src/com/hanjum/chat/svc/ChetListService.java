package com.hanjum.chat.svc;

import static com.hanjum.db.JdbcUtil.*; 

import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.chat.dao.ChatDAO;
import com.hanjum.chat.vo.ChatBean;


public class ChetListService {

	public ArrayList<ChatBean> getListCount(int board_id) throws Exception {
		
		int listCount = 0;
		ArrayList<ChatBean> list = new ArrayList<ChatBean>();
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
				
		// 2(공통). BoardDAO 객체 가져오기
		ChatDAO chatDAO = ChatDAO.getInstance();
				
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		chatDAO.setConnection(con);
				
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여
		//    전체 게시물 수 가져오기
		list = chatDAO.selectListCount(board_id);
				
		// 5(공통). Connection 객체 반환하기
		close(con);
				
		// 6. 전체 게시물 수 리턴
		return list;
		
	}
	
	
	

}
