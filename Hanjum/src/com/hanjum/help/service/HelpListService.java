package com.hanjum.help.service;

import static com.hanjum.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.hanjum.help.dao.HelpDAO;
import com.hanjum.help.vo.HelpBean;

public class HelpListService {

	public ArrayList<HelpBean> getHelpList() {
		System.out.println("HelpListService!");
		
		ArrayList<HelpBean> list = null;
		
		Connection con = getConnection();

		HelpDAO helpDAO = HelpDAO.getInstance();

		helpDAO.setConnection(con);
		
		list = helpDAO.selectHelpList();
		
		close(con);
		
		return list;
	}
	public int getListCount() throws Exception{
		System.out.println("helpListService - getListCount()");
		int listCount = 0;
		
		Connection con = getConnection();
		
		HelpDAO helpDAO = HelpDAO.getInstance();
		
		helpDAO.setConnection(con);
		
		
		listCount = helpDAO.selectListCount();
		
		
		close(con);
		
		
		return listCount;
	}
	public boolean updateHelp(HelpBean helpBean) {
		boolean isUpdateSuccess = false;
		System.out.println("HelpListServiceUpdate!");
		Connection con = getConnection();
		
		HelpDAO helpDAO = HelpDAO.getInstance();
		
		helpDAO.setConnection(con);
		
		int updateCount = helpDAO.updateHelp(helpBean);
		System.out.println(updateCount);
		if(updateCount >0) {
				commit(con);
				isUpdateSuccess=true;
		}else {
				rollback(con);
		}
		
		close(con);
		return isUpdateSuccess;
	}
	public boolean deleteHelp(HelpBean helpBean) {
		boolean isDeleteSuccess = false;
		System.out.println("HelpListServiceDelete");
		Connection con = getConnection();
		
		HelpDAO helpDAO = HelpDAO.getInstance();
		
		helpDAO.setConnection(con);
		
		int deleteCount = helpDAO.delectHelp(helpBean);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess=true;
			
		} else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}
	
	
}