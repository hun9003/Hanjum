package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.HelpDAO;
import vo.HelpBean;

public class HelpListService {

	public ArrayList<HelpBean> getHelpList() {
		// TODO Auto-generated method stub
		
		ArrayList<HelpBean> list = null;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();

		// 2(공통). BoardDAO 객체 가져오기
		HelpDAO helpDAO = HelpDAO.getInstance();

		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		helpDAO.setConnection(con);
		
		list = helpDAO.selectHelpList();
		
		close(con);
		
		return list;
	}


}
