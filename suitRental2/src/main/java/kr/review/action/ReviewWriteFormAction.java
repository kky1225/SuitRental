package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class ReviewWriteFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		
		request.setAttribute("x_code", x_code);
		
		return "/WEB-INF/views/review/reviewWriteForm.jsp";
	}

}
