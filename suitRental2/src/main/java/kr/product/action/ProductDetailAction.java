package kr.product.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.likey.dao.LikeyDAO;
import kr.likey.vo.LikeyVO;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.rental.dao.RentalDAO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.PagingUtil;
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
		
		/* ----------------------------------------------- */
		
		String pageNum = request.getParameter("page_num");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int count = ReviewDAO.getInstance().getReviewCount("", "", x_code);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 20, 10, "reviewList.do");
		
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		if(count > 0) {
			reviewList = ReviewDAO.getInstance().getListReview(page.getStartCount(), page.getEndCount(), x_code);
		}
		
		LikeyVO likeyVO = null;
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num != null) {
			likeyVO = LikeyDAO.getInstance().getLikey(x_code, user_num);
		}
		
		if(likeyVO == null) {
			request.setAttribute("likey", true);
		}else {
			request.setAttribute("likey", false);
		}
		
		/* ----------------------------------------------- */
		int reviewCount = 0;
		int rentalCount = 0;
		
		if(user_num != null) {
			rentalCount = RentalDAO.getInstance().checkRental(x_code, user_num);
			reviewCount = ReviewDAO.getInstance().checkReview(x_code, user_num);
		}
		
		if(reviewCount < rentalCount) {
			request.setAttribute("review", true);
		}else {
			request.setAttribute("review", false);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pagingHtml", page.getPagingHtml());
		request.setAttribute("x_code", x_code);
		
		return "/WEB-INF/views/product/productDetail.jsp";
	}

}
