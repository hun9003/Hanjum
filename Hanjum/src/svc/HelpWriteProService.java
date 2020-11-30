package svc;

import java.sql.Connection;

import dao.HelpDAO;
import db.JdbcUtil;
import vo.HelpBean;
import static db.JdbcUtil.*;
public class HelpWriteProService {
	
	public boolean registArticle(HelpBean helpbean) {
		System.out.println("HelpWriteProService - registArticle()");
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		HelpDAO helpDAO = HelpDAO.getInstance();
		
		int insertCount = helpDAO.insertHelp(helpbean);
		if(insertCount > 0) {
			commit(con); // DB 커밋 작업 수행
			isWriteSuccess = true; // 리턴할 작업 수행 결과를 true 로 설정
		} else {
			rollback(con);
		}
		
		// 6(공통). 사용이 완료된 Connection 객체 반환하기
		close(con);

		// 7. 글 등록 성공 여부 리턴 => BoardWriteProAction 클래스로 전달
		return isWriteSuccess;
		
		
	}

}
