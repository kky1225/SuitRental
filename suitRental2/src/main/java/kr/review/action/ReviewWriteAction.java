package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.FileUtil;

public class ReviewWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setTitle(multi.getParameter("title"));
		reviewVO.setContent(multi.getParameter("content"));
		reviewVO.setIp(request.getRemoteAddr());
		reviewVO.setFilename(multi.getFilesystemName("filename"));
		reviewVO.setMem_num(user_num);
		reviewVO.setX_code(Integer.parseInt(multi.getParameter("x_code")));
		
		ReviewDAO.getInstance().reviewInsert(reviewVO);
			
		return "/WEB-INF/views/review/reviewWrite.jsp";
	}
	
}
