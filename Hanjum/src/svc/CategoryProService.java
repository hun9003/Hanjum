package svc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CategoryDAO;
import static db.JdbcUtill.*;
import vo.CategoryBean;

public class CategoryProService {
	private CategoryDAO categoryDAO;
	private Connection con;
	
	public CategoryProService() {
		categoryDAO = CategoryDAO.getInstance();
		con = getConnection(); 
		categoryDAO.setConnection(con);
	}
	
	public boolean insertCategory(CategoryBean cb) {
		System.out.println("CategoryWriteProService");
		boolean isWriteSuccess = false;
		
		int insertCount = categoryDAO.insertCategory(cb);
		System.out.println(insertCount);
		if(insertCount >0) {
				commit(con);
				isWriteSuccess=true;
		}else {
				rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

	public int getListCount() {
		int listCount = 0;
		
		listCount=categoryDAO.categoryListCount();
		
		return listCount;
	}

	public ArrayList<CategoryBean> getCategoryList() {
		System.out.println("getCategoryList");
		ArrayList<CategoryBean> categoryList = null;
		
		categoryList = categoryDAO.selectCategoryList();
		
		close(con);
		
		return categoryList;
	}
	
}
