package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.product.dao.ProductDAO;
import kr.controller.Action;
import kr.productdetail.vo.ProductDetailVO;
import kr.util.FileUtil;
  
public class ProductWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		ProductDetailVO board = new ProductDetailVO();
		board.setX_name(multi.getParameter("name"));
		board.setX_brand(multi.getParameter("brand"));
		board.setX_file(multi.getFilesystemName("filename"));
		board.setX_price(Integer.parseInt(multi.getParameter("price")));
		board.setX_size(multi.getParameter("size"));
		board.setX_type(multi.getParameter("type"));
		board.setX_gender(multi.getParameter("gender"));
		if(multi.getParameter("gender").equals("male")) {
			board.setX_size(multi.getParameter("male_size"));
		}
		if(multi.getParameter("gender").equals("female")) {
			board.setX_size(multi.getParameter("female_size"));
		}
		board.setX_contents(multi.getParameter("content"));
		board.setX_stock(Integer.parseInt(multi.getParameter("stock")));
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.registerProduct(board);
		
		return "/WEB-INF/views/product/productWrite.jsp";
	}

}