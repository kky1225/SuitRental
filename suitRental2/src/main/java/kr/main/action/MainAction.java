package kr.main.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductDAO dao = ProductDAO.getInstance();
		int count = dao.getProductCount(null, null);
		
		List<ProductDetailVO> list = new ArrayList<ProductDetailVO>();
		if(count > 0) {
			list = dao.getProductList(1, 4, null, null);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/main/main.jsp";
	}

}
