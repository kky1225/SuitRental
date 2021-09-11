package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.util.StringUtil;

public class ProductDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		
		ProductDAO.getInstance().updateReadcount(x_code);
		
		ProductDetailVO board = ProductDAO.getInstance().getBoard(x_code);
		
		board.setX_contents(StringUtil.useBrNoHtml(board.getX_contents()));
		
		request.setAttribute("board", board);
		
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException발생!");
			
		}
	
		
		return "/WEB-INF/views/product/productDetail.jsp";
	}

}
