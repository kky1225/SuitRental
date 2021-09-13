package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.likey.dao.LikeyDAO;

public class LikeyDownAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		
		LikeyDAO.getInstance().likeyDown(x_code, user_num);
		
		request.setAttribute("x_code", x_code);
		
		return "/WEB-INF/views/product/likeyDown.jsp";
	}

}
