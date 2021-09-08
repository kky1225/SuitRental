package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 회원제 서비스
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {	// 로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		// 현재 로그인 한 아이디
		String user_id = (String)session.getAttribute("user_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member != null && id.equals(user_id)) {
			check = member.isCheckedPassword(passwd);
		}
		
		if(check) {
			dao.deleteMember(user_num);
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/member/deleteUser.jsp";
	}

}
































