package vo;

import java.sql.Timestamp;

public class ChatBean {
	
	private int chat_id;
	private String chat_editor_id;
	private String chat_creator_id;
	private String chat_content;
	private Timestamp chat_date;
	private int board_id;
	
	
	public ChatBean() {}
	
	public ChatBean(int chat_id, String chat_editor_id, String chat_creator_id, String chat_content,
			Timestamp chat_date, int board_id) {
		super();
		this.chat_id = chat_id;
		this.chat_editor_id = chat_editor_id;
		this.chat_creator_id = chat_creator_id;
		this.chat_content = chat_content;
		this.chat_date = chat_date;
		this.board_id = board_id;
	}
	
	
	
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getChat_editor_id() {
		return chat_editor_id;
	}
	public void setChat_editor_id(String chat_editor_id) {
		this.chat_editor_id = chat_editor_id;
	}
	public String getChat_creator_id() {
		return chat_creator_id;
	}
	public void setChat_creator_id(String chat_creator_id) {
		this.chat_creator_id = chat_creator_id;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public Timestamp getChat_date() {
		return chat_date;
	}
	public void setChat_date(Timestamp chat_date) {
		this.chat_date = chat_date;
	}
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	
	

}
