package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.util.FileUtil;

public class ProductDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}

		int x_code = Integer.parseInt(request.getParameter("x_code"));
		
		ProductDetailVO productDetailVO = ProductDAO.getInstance().getBoard(x_code);
		
		ProductDAO.getInstance().deleteProduct(x_code);
		FileUtil.removeFile(request, productDetailVO.getX_file());
		
		return "/WEB-INF/views/product/productDelete.jsp";
	}

}
