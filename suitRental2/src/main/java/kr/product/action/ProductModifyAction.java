package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.review.dao.ReviewDAO;
import kr.util.FileUtil;

public class ProductModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		ProductDetailVO productDetailVO = new ProductDetailVO();
		productDetailVO.setX_code(Integer.parseInt(multi.getParameter("x_code")));
		productDetailVO.setX_name(multi.getParameter("name"));
		productDetailVO.setX_brand(multi.getParameter("brand"));
		productDetailVO.setX_gender(multi.getParameter("gender"));
		
		if(multi.getParameter("gender").equals("male")) {
			productDetailVO.setX_size(multi.getParameter("male_size"));
		}
		if(multi.getParameter("gender").equals("female")) {
			productDetailVO.setX_size(multi.getParameter("female_size"));
		}
		
		productDetailVO.setX_file(multi.getFilesystemName("filename"));
		productDetailVO.setX_type(multi.getParameter("type"));
		productDetailVO.setX_price(Integer.parseInt(multi.getParameter("price")));
		productDetailVO.setX_stock(Integer.parseInt(multi.getParameter("stock")));
		productDetailVO.setX_contents(multi.getParameter("content"));
		
		ProductDAO.getInstance().updateProduct(productDetailVO);
		
		return "/WEB-INF/views/product/productModify.jsp";
	}

}
