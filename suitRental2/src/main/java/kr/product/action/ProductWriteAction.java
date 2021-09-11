package kr.product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.product.dao.ProductDAO;
import kr.productdetail.vo.ProductDetailVO;
import kr.review.dao.ReviewDAO;
import kr.util.FileUtil;
  
public class ProductWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		  HttpSession session = request.getSession(); 
		  Integer user_num = (Integer)session.getAttribute("user_num"); 
		  if(user_num == null)
		  { return "redirect:/member/loginForm.do"; }
		 
		 
		
		  MultipartRequest multi = FileUtil.createFile(request); 

		  ProductDetailVO board= new ProductDetailVO();
		  

			
			 // String x_name = request.getParameter("name");
			  //String x_brand =request.getParameter("brand");
			  //String x_file =multi.getParameter("filename");
			  //String x_gender =request.getParameter("gender");
			  //String x_size = request.getParameter("size");
			  //String x_type=request.getParameter("type");
			 
			  
		  
		  
		  board.setX_price(Integer.parseInt(multi.getParameter("price")));
		  //board.setX_stock(Integer.parseInt(multi.getParameter("stock")));
			 
			  board.setX_name(multi.getParameter("name"));
			  board.setX_brand(multi.getParameter("brand"));
			  //board.setX_file(multi.getParameter("filename"));
			  board.setX_gender(multi.getParameter("gender"));
			  board.setX_size(multi.getParameter("size"));
			  board.setX_type(multi.getParameter("type"));
			   
			  //int x_price=Integer.parseInt(request.getParameter("price"));
			  //int x_stock= Integer.parseInt(request.getParameter("stock"));
			 //  String x_size ="1";
			  String x_file = "1";
			// int x_price= 10;
			  int x_stock= 10;
			  
		  
			
			 //String x_contents=request.getParameter("content");
			 board.setX_contents("content");
		  
		  
		 // board.setX_name(x_name);
		 // board.setX_brand(x_brand);
		  board.setX_file(x_file);
		 // board.setX_gender(x_gender);
		 //board.setX_size(x_size);
		 // board.setX_type(x_type);
		 // board.setX_price(x_price);
		 // board.setX_stock(x_stock);
		 // board.setX_contents(x_contents);
		  
			
		  
		  ProductDAO.getInstance().registerProduct(board);
			
	
		  
		  
			  
		  
		  return "/WEB-INF/views/product/productWrite.jsp";
		
		
		
	}

}