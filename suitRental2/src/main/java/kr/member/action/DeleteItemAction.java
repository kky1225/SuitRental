package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.OrderDAO;
import kr.member.vo.OrderVO;

public class DeleteItemAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {	
			return "redirect:/member/loginForm.do";
		}
		
		Integer rent_num = (Integer)session.getAttribute("user_rent_num");
		
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO order = dao.getOrder(rent_num);
		
		dao.deleteOrder(order);
		
		return "/WEB-INF/views/member/deleteItem.jsp";
	}

}
