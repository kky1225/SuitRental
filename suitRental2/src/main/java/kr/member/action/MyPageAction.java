package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션에서 로그인이 되어있는지 정보를 받아와야함
		// 회원제 서비스일 경우 이게 맨 위에 있어야 한다.
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {	// 로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);
		
		request.setAttribute("member", member);
		
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
