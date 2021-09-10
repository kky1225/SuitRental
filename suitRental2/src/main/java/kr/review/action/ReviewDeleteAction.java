package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;

public class ReviewDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		ReviewDAO.getInstance().reviewDelete(review_num);
		
		return "/WEB-INF/views/review/reviewDelete.jsp";
	}

}
