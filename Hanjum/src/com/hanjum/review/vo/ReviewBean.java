package com.hanjum.review.vo;

import java.sql.Date;
import java.sql.Timestamp;



public class ReviewBean {
	private String user_id;
	private String review_content;
	private int review_speciality;
	private int review_satisfaction;
	private int review_positivity;
	private int review_communication;
	private Timestamp review_date;
	private String review_form_id;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getReview_speciality() {
		return review_speciality;
	}
	public void setReview_speciality(int review_speciality) {
		this.review_speciality = review_speciality;
	}
	
	public int getReview_satisfaction() {
		return review_satisfaction;
	}
	public void setReview_satisfaction(int review_satisfaction) {
		this.review_satisfaction = review_satisfaction;
	}
	public int getReview_positivity() {
		return review_positivity;
	}
	public void setReview_positivity(int review_positivity) {
		this.review_positivity = review_positivity;
	}
	public int getReview_communication() {
		return review_communication;
	}
	public void setReview_communication(int review_communication) {
		this.review_communication = review_communication;
	}
	
	public Timestamp getReview_date() {
		return review_date;
	}
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public String getReview_form_id() {
		return review_form_id;
	}
	public void setReview_form_id(String review_form_id) {
		this.review_form_id = review_form_id;
	}
	
	
	
}
