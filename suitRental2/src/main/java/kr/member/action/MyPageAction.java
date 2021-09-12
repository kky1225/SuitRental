package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.dao.OrderDAO;
import kr.member.vo.MemberVO;
import kr.member.vo.OrderVO;
import kr.util.PagingUtil;

public class MyPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {	
			return "redirect:/member/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)pageNum="1";
		
		OrderDAO odao = OrderDAO.getInstance();
		int count = odao.getOrderCount();
		
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO member = mdao.getMember(user_num);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count,100,1,null);
		
		List<OrderVO> list = null;
		if(count > 0) {
			list = odao.getOrderList(member, page.getStartCount(), page.getEndCount());
		}
		
		request.setAttribute("member", member);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		//request.setAttribute("pagingHtml", page.getPagingHtml());
		
		
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
