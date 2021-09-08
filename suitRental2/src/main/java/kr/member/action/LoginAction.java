package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberVO memberVO = MemberDAO.getInstance().checkMember(id);
		
		if(memberVO == null) {
			return "/WEB-INF/views/member/login.jsp";
		}
		
		if(passwd.equals(memberVO.getPasswd())) {
			HttpSession session = request.getSession();
			session.setAttribute("user_num", memberVO.getMem_num());
			session.setAttribute("user_id", memberVO.getId());
			session.setAttribute("user_auth", memberVO.getAuth());
			
			return "redirect:/main/main.do";
		}else{
			return "/WEB-INF/views/member/login.jsp";
		}
	}
}
