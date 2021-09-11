package kr.product.action;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.util.StringUtil;

public class ProductDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int x_code = Integer.parseInt(request.getParameter("x_code"));
		ProductDAO.getInstance().updateReadcount(x_code);
		
		ProductDetailVO productDetailVO = ProductDAO.getInstance().getBoard(x_code);
		
		productDetailVO.setX_name(StringUtil.useBrNoHtml(productDetailVO.getX_name()));
		productDetailVO.setX_contents(StringUtil.useBrNoHtml(productDetailVO.getX_contents()));
		
		DecimalFormat df = new DecimalFormat("###,###");
		
		String price = df.format(productDetailVO.getX_price());

		request.setAttribute("price", price);
		request.setAttribute("productDetailVO", productDetailVO);
		
		return "/WEB-INF/views/product/productDetail.jsp";
	}

}
