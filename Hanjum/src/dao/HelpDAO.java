package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.HelpBean;

public class HelpDAO {
	
	private HelpDAO() {}
	
	private static HelpDAO instance =new HelpDAO();
	
	public static HelpDAO getInstance() {
		return instance;
		
	}
	Connection con ;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int insertHelp(HelpBean helpBean) {
		System.out.println("HelpBean - insertHelp()");
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1 ;
		try {
			String sql ="SELECT MAX(help_id) FROM Help";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num =rs.getInt(1) + 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return insertCount;
		
	}

	public ArrayList<HelpBean> selectHelpList() {
		// TODO Auto-generated method stub
		ArrayList<HelpBean> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			String sql = "SELECT HELP_ID,HELP_QUESTION,HELP_ANSWER FROM HELP";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<HelpBean>();
			
			while(rs.next()) {
				HelpBean temp = new HelpBean();
				temp.setHelp_id(rs.getInt("help_id"));
				temp.setHelp_question(rs.getString("help_question"));
				temp.setHelp_answer(rs.getString("help_answer"));
				
				list.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
}
