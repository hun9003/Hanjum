package com.hanjum.user.action;

public class test {

	public static void main(String[] args) {
		String searchType = "user_id";
		String sql = "select Count(user_id) from user where "+searchType + " like ?";
		System.out.println(sql);
	}

}
