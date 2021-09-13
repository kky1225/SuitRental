package kr.rental.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.rental.dao.RentalDAO;
import kr.rental.vo.RentalVO;

public class RentalFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");

		if (user_num == null) {

			return "redirect:/member/loginForm.do";
		}

		
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		int Mem_num = user_num;

		ProductDAO dao = ProductDAO.getInstance();
		ProductDetailVO dto = dao.getBoard(x_code);

		
		request.setAttribute("dto", dto);


		return "/WEB-INF/views/rental/rentalForm.jsp";
	}

}
