package kr.rental.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.OrderDAO;
import kr.member.vo.OrderVO;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.rental.dao.RentalDAO;
import kr.rental.vo.RentalVO;

public class RentalAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");

		if (user_num == null) {

			return "redirect:/member/loginForm.do";
		}

		int x_code =Integer.parseInt(request.getParameter("code")) ;
		int mem_num = user_num;
			
			String rental_date = request.getParameter("rental_date");
			String return_date = request.getParameter("return_date");
			String rental_type = request.getParameter("rental_type");
			String return_type = request.getParameter("return_type");
			
			RentalVO board = new RentalVO();
			
			board.setX_code(x_code);
			board.setMem_num(mem_num);
			board.setRental_date(rental_date);			
			board.setReturn_date(return_date);
			board.setRental_type(rental_type);
			board.setReturn_type(return_type);
			
			RentalDAO dao = RentalDAO.getInstance();
			dao.rent(board);
	
			return "/WEB-INF/views/rental/rental.jsp";
			}
}
		
		
		