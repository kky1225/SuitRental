package kr.product.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.util.PagingUtil;

public class ProductListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = request.getParameter("page_num");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int count = ProductDAO.getInstance().getProductCount();
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 20, 10, "productList.do");
		
		List<ProductDetailVO> productList = new ArrayList<ProductDetailVO>();
		if(count > 0) {
			productList = ProductDAO.getInstance().getProductList(page.getStartCount(), page.getEndCount());
		}
        
        request.setAttribute("count", count);
		request.setAttribute("productList", productList);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/product/productList.jsp";
	}

}
