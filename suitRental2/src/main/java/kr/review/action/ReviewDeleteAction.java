package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;

public class ReviewDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		
		ReviewDAO.getInstance().reviewDelete(review_num);
		//FileUtil.removeFile(request, boardVO.getFilename());
		
		return "/WEB-INF/views/review/reviewDelete.jsp";
	}

}
