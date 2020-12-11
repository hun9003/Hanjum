package com.hanjum.chat.svc;

import java.sql.Connection;
import static com.hanjum.db.JdbcUtil.*;
import com.hanjum.chat.dao.ChatDAO;



public class ChatDeleteProService {

	public static boolean isArticleWriter(String chat_editor_id, String chat_creator_id) {
		System.out.println("ChatDeleteProService");
		boolean isArticleWriter = false;
		
		Connection con = getConnection();
		
		ChatDAO chatDAO = ChatDAO.getInstance();
		
		chatDAO.setConnection(con);
		
		isArticleWriter = chatDAO.isArticleChatWriter(chat_editor_id,chat_creator_id); 
		
		close(con);
		
		return isArticleWriter;
	}

}
