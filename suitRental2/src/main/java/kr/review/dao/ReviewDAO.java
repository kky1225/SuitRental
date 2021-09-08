package kr.review.dao;

public class ReviewDAO {
	private static ReviewDAO instance = new ReviewDAO();
	
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	public ReviewDAO() {
		
	}
}
