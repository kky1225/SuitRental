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
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		
		MultipartRequest multi = FileUtil.createFile(request);
		ProductDetailVO board = new ProductDetailVO();
		board.setX_name(multi.getParameter("x_name"));
		board.setX_brand(multi.getParameter("x_brand"));
		board.setX_file(multi.getFilesystemName("x_file"));
		board.setX_gender(multi.getParameter("x_gender"));
		board.setX_size(multi.getParameter("x_size"));
		
		board.setX_contents(multi.getParameter("x_contents"));
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.registerProduct(multi);
		
		return "/WEB-INF/views/product/productWrite.jsp";
	}

}