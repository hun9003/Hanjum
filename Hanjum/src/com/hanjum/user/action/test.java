package com.hanjum.user.action;

import java.util.Random;

public class test {

	public static void main(String[] args) {
		String searchType = "user_id";
		String sql = "select Count(user_id) from user where "+searchType + " like ?";
		System.out.println(sql);
		

        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수
        
        for(int i=0;i<6;i++) {
            
            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        System.out.println(numStr);
	}

}
