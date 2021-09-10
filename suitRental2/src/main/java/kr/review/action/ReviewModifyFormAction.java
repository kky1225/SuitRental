package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class ReviewModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		ReviewVO reviewVO = ReviewDAO.getInstance().getReview(review_num);
		
		request.setAttribute("reviewVO", reviewVO);
		
		return "/WEB-INF/views/review/reviewModifyForm.jsp";
	}

}
