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
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		if(keyfield == null) {
			keyfield = "";
		}
		if(keyword == null) {
			keyword = "";
		}
		
		int count = ProductDAO.getInstance().getProductCount(keyfield, keyword);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 20, 10, "productList.do");
		
		List<ProductDetailVO> productList = new ArrayList<ProductDetailVO>();
		List<ProductDetailVO> productList2 = new ArrayList<ProductDetailVO>();
		List<ProductDetailVO> productList3 = new ArrayList<ProductDetailVO>();
		List<ProductDetailVO> productList4 = new ArrayList<ProductDetailVO>();
		
		
		if(count > 0) {
			productList = ProductDAO.getInstance().getProductList(page.getStartCount(), page.getEndCount(), keyfield, keyword);
			productList2 = ProductDAO.getInstance().getBestPurchaseList(page.getStartCount(), page.getEndCount(),keyfield,keyword);
			productList3 = ProductDAO.getInstance().getMaleProductList(page.getStartCount(), page.getEndCount(), keyfield, keyword);
			productList4 = ProductDAO.getInstance().getFemaleProductList(page.getStartCount(), page.getEndCount(), keyfield, keyword);
		}

		int list = 1;
		
		if(request.getParameter("list") != null) {
			list = Integer.parseInt(request.getParameter("list"));
		}
        
		request.setAttribute("list", list);
        request.setAttribute("count", count);
		request.setAttribute("productList", productList);
		request.setAttribute("productList2", productList2);
		request.setAttribute("productList3", productList3);
		request.setAttribute("productList4", productList4);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		
		return "/WEB-INF/views/product/productList.jsp";
	}

}
