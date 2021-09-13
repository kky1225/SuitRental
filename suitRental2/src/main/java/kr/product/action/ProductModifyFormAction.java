package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;

public class ProductModifyFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		
		ProductDetailVO productDetailVO = ProductDAO.getInstance().getBoard(x_code);
		
		request.setAttribute("productDetailVO", productDetailVO);
		
		return "/WEB-INF/views/product/productModifyForm.jsp";
	}

}
