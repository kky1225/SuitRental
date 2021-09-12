package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.OrderDAO;
import kr.member.vo.OrderVO;

public class ModifyItemAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 회원제 서비스
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
						
		if(user_num == null) {	// 로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		// 전송된 데이터 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 데이터 VO에 저장
		OrderVO order = new OrderVO();
		order.setRent_num(Integer.parseInt(request.getParameter("rent_num")));
		order.setRental_date(request.getParameter("rental_date"));
		order.setReturn_date(request.getParameter("return_date"));
		order.setRental_type(request.getParameter("rental_type"));
		order.setReturn_type(request.getParameter("return_type"));
		
		OrderDAO dao = OrderDAO.getInstance();
		dao.updateOrder(order);
		
		return "/WEB-INF/views/member/modifyItem.jsp";
	}

}
