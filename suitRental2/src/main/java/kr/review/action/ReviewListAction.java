package kr.review.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.PagingUtil;

public class ReviewListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pageNum = request.getParameter("page_num");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int count = ReviewDAO.getInstance().getReviewCount();
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 20, 10, "reviewList.do");
		
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		if(count > 0) {
			reviewList = ReviewDAO.getInstance().getListReview(page.getStartCount(), page.getEndCount());
		}
		
		request.setAttribute("count", count);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/review/reviewList.jsp";
	}

}
