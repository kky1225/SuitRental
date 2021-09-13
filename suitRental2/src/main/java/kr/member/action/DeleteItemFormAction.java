package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.OrderDAO;
import kr.member.vo.OrderVO;

public class DeleteItemFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		int rent_num = Integer.parseInt(request.getParameter("rent_num"));
		// 회원제 서비스
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
				
		if(user_num == null) {	// 로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO order = dao.getOrder(rent_num);
		
		if(user_num != null && user_num.equals(order.getMem_num())) {
			request.setAttribute("order", order);
			session.setAttribute("user_rent_num", order.getRent_num());
			
			return "/WEB-INF/views/member/deleteItemForm.jsp";
		}
		
		return "redirect:/member/myPage.do";
		
		//session.setAttribute("user_rent_num", order.getRent_num());
		
		
	}

}


































